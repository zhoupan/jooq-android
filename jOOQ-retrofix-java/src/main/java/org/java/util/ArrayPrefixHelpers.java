/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.java.util;

import org.java.util.concurrent.CountedCompleter;
import org.java.util.concurrent.ForkJoinPool;
import org.java.util.function.BinaryOperator;
import org.java.util.function.DoubleBinaryOperator;
import org.java.util.function.IntBinaryOperator;
import org.java.util.function.LongBinaryOperator;

/**
 * ForkJoin tasks to perform Arrays.parallelPrefix operations.
 *
 * @author Doug Lea
 * @since 1.8
 */
class ArrayPrefixHelpers {

  // CVS rev. 1.13
  // non-instantiable
  private ArrayPrefixHelpers() {}

  /*
   * Parallel prefix (aka cumulate, scan) task classes
   * are based loosely on Guy Blelloch's original
   * algorithm (http://www.cs.cmu.edu/~scandal/alg/scan.html):
   *  Keep dividing by two to threshold segment size, and then:
   *   Pass 1: Create tree of partial sums for each segment
   *   Pass 2: For each segment, cumulate with offset of left sibling
   *
   * This version improves performance within FJ framework mainly by
   * allowing the second pass of ready left-hand sides to proceed
   * even if some right-hand side first passes are still executing.
   * It also combines first and second pass for leftmost segment,
   * and skips the first pass for rightmost segment (whose result is
   * not needed for second pass).  It similarly manages to avoid
   * requiring that users supply an identity basis for accumulations
   * by tracking those segments/subtasks for which the first
   * existing element is used as base.
   *
   * Managing this relies on ORing some bits in the pendingCount for
   * phases/states: CUMULATE, SUMMED, and FINISHED. CUMULATE is the
   * main phase bit. When false, segments compute only their sum.
   * When true, they cumulate array elements. CUMULATE is set at
   * root at beginning of second pass and then propagated down. But
   * it may also be set earlier for subtrees with lo==0 (the left
   * spine of tree). SUMMED is a one bit join count. For leafs, it
   * is set when summed. For internal nodes, it becomes true when
   * one child is summed.  When the second child finishes summing,
   * we then moves up tree to trigger the cumulate phase. FINISHED
   * is also a one bit join count. For leafs, it is set when
   * cumulated. For internal nodes, it becomes true when one child
   * is cumulated.  When the second child finishes cumulating, it
   * then moves up tree, completing at the root.
   *
   * To better exploit locality and reduce overhead, the compute
   * method loops starting with the current task, moving if possible
   * to one of its subtasks rather than forking.
   *
   * As usual for this sort of utility, there are 4 versions, that
   * are simple copy/paste/adapt variants of each other.  (The
   * double and int versions differ from long version solely by
   * replacing "long" (with case-matching)).
   */
  // see above
  static final int CUMULATE = 1;

  static final int SUMMED = 2;

  static final int FINISHED = 4;

  /** The smallest subtask array partition size to use as threshold */
  static final int MIN_PARTITION = 16;

  @SuppressWarnings("serial")
  static final class CumulateTask<T> extends CountedCompleter<Void> {

    final T[] array;

    final BinaryOperator<T> function;

    CumulateTask<T> left, right;

    T in;

    T out;

    final int lo, hi, origin, fence, threshold;

    /** Root task constructor */
    public CumulateTask(
        CumulateTask<T> parent, BinaryOperator<T> function, T[] array, int lo, int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.lo = this.origin = lo;
      this.hi = this.fence = hi;
      int p;
      this.threshold =
          (p = (hi - lo) / (ForkJoinPool.getCommonPoolParallelism() << 3)) <= MIN_PARTITION
              ? MIN_PARTITION
              : p;
    }

    /** Subtask constructor */
    CumulateTask(
        CumulateTask<T> parent,
        BinaryOperator<T> function,
        T[] array,
        int origin,
        int fence,
        int threshold,
        int lo,
        int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.origin = origin;
      this.fence = fence;
      this.threshold = threshold;
      this.lo = lo;
      this.hi = hi;
    }

    public final void compute() {
      BinaryOperator<T> fn;
      T[] a;
      if ((fn = this.function) == null || (a = this.array) == null)
        // hoist checks
        throw new NullPointerException();
      int th = threshold, org = origin, fnc = fence, l, h;
      CumulateTask<T> t = this;
      outer:
      while ((l = t.lo) >= 0 && (h = t.hi) <= a.length) {
        if (h - l > th) {
          CumulateTask<T> lt = t.left, rt = t.right, f;
          if (lt == null) {
            // first pass
            int mid = (l + h) >>> 1;
            f = rt = t.right = new CumulateTask<T>(t, fn, a, org, fnc, th, mid, h);
            t = lt = t.left = new CumulateTask<T>(t, fn, a, org, fnc, th, l, mid);
          } else {
            // possibly refork
            T pin = t.in;
            lt.in = pin;
            f = t = null;
            if (rt != null) {
              T lout = lt.out;
              rt.in = (l == org ? lout : fn.apply(pin, lout));
              for (int c; ; ) {
                if (((c = rt.getPendingCount()) & CUMULATE) != 0) break;
                if (rt.compareAndSetPendingCount(c, c | CUMULATE)) {
                  t = rt;
                  break;
                }
              }
            }
            for (int c; ; ) {
              if (((c = lt.getPendingCount()) & CUMULATE) != 0) break;
              if (lt.compareAndSetPendingCount(c, c | CUMULATE)) {
                if (t != null) f = t;
                t = lt;
                break;
              }
            }
            if (t == null) break;
          }
          if (f != null) f.fork();
        } else {
          // Transition to sum, cumulate, or both
          int state;
          for (int b; ; ) {
            if (((b = t.getPendingCount()) & FINISHED) != 0)
              // already done
              break outer;
            state = ((b & CUMULATE) != 0 ? FINISHED : (l > org) ? SUMMED : (SUMMED | FINISHED));
            if (t.compareAndSetPendingCount(b, b | state)) break;
          }
          T sum;
          if (state != SUMMED) {
            int first;
            if (l == org) {
              // leftmost; no in
              sum = a[org];
              first = org + 1;
            } else {
              sum = t.in;
              first = l;
            }
            for ( // cumulate
            int i = first; // cumulate
                i < h; // cumulate
                ++i) a[i] = sum = fn.apply(sum, a[i]);
          } else if (h < fnc) {
            // skip rightmost
            sum = a[l];
            for ( // sum only
            int i = l + 1; // sum only
                i < h; // sum only
                ++i) sum = fn.apply(sum, a[i]);
          } else sum = t.in;
          t.out = sum;
          for (CumulateTask<T> par; ; ) {
            // propagate
            @SuppressWarnings("unchecked")
            CumulateTask<T> partmp = (CumulateTask<T>) t.getCompleter();
            if ((par = partmp) == null) {
              if ( // enable join
              (state & FINISHED) != 0) t.quietlyComplete();
              break outer;
            }
            int b = par.getPendingCount();
            if ((b & state & FINISHED) != 0)
              // both done
              t = par;
            else if ((b & state & SUMMED) != 0) {
              // both summed
              int nextState;
              CumulateTask<T> lt, rt;
              if ((lt = par.left) != null && (rt = par.right) != null) {
                T lout = lt.out;
                par.out = (rt.hi == fnc ? lout : fn.apply(lout, rt.out));
              }
              int refork = (((b & CUMULATE) == 0 && par.lo == org) ? CUMULATE : 0);
              if ((nextState = b | state | refork) == b
                  || par.compareAndSetPendingCount(b, nextState)) {
                // drop finished
                state = SUMMED;
                t = par;
                if (refork != 0) par.fork();
              }
            } else if (par.compareAndSetPendingCount(b, b | state))
              // sib not ready
              break outer;
          }
        }
      }
    }
  }

  @SuppressWarnings("serial")
  static final class LongCumulateTask extends CountedCompleter<Void> {

    final long[] array;

    final LongBinaryOperator function;

    LongCumulateTask left, right;
    long in, out;
    final int lo, hi, origin, fence, threshold;

    /** Root task constructor */
    public LongCumulateTask(
        LongCumulateTask parent, LongBinaryOperator function, long[] array, int lo, int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.lo = this.origin = lo;
      this.hi = this.fence = hi;
      int p;
      this.threshold =
          (p = (hi - lo) / (ForkJoinPool.getCommonPoolParallelism() << 3)) <= MIN_PARTITION
              ? MIN_PARTITION
              : p;
    }

    /** Subtask constructor */
    LongCumulateTask(
        LongCumulateTask parent,
        LongBinaryOperator function,
        long[] array,
        int origin,
        int fence,
        int threshold,
        int lo,
        int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.origin = origin;
      this.fence = fence;
      this.threshold = threshold;
      this.lo = lo;
      this.hi = hi;
    }

    public final void compute() {
      LongBinaryOperator fn;
      long[] a;
      if ((fn = this.function) == null || (a = this.array) == null)
        // hoist checks
        throw new NullPointerException();
      int th = threshold, org = origin, fnc = fence, l, h;
      LongCumulateTask t = this;
      outer:
      while ((l = t.lo) >= 0 && (h = t.hi) <= a.length) {
        if (h - l > th) {
          LongCumulateTask lt = t.left, rt = t.right, f;
          if (lt == null) {
            // first pass
            int mid = (l + h) >>> 1;
            f = rt = t.right = new LongCumulateTask(t, fn, a, org, fnc, th, mid, h);
            t = lt = t.left = new LongCumulateTask(t, fn, a, org, fnc, th, l, mid);
          } else {
            // possibly refork
            long pin = t.in;
            lt.in = pin;
            f = t = null;
            if (rt != null) {
              long lout = lt.out;
              rt.in = (l == org ? lout : fn.applyAsLong(pin, lout));
              for (int c; ; ) {
                if (((c = rt.getPendingCount()) & CUMULATE) != 0) break;
                if (rt.compareAndSetPendingCount(c, c | CUMULATE)) {
                  t = rt;
                  break;
                }
              }
            }
            for (int c; ; ) {
              if (((c = lt.getPendingCount()) & CUMULATE) != 0) break;
              if (lt.compareAndSetPendingCount(c, c | CUMULATE)) {
                if (t != null) f = t;
                t = lt;
                break;
              }
            }
            if (t == null) break;
          }
          if (f != null) f.fork();
        } else {
          // Transition to sum, cumulate, or both
          int state;
          for (int b; ; ) {
            if (((b = t.getPendingCount()) & FINISHED) != 0)
              // already done
              break outer;
            state = ((b & CUMULATE) != 0 ? FINISHED : (l > org) ? SUMMED : (SUMMED | FINISHED));
            if (t.compareAndSetPendingCount(b, b | state)) break;
          }
          long sum;
          if (state != SUMMED) {
            int first;
            if (l == org) {
              // leftmost; no in
              sum = a[org];
              first = org + 1;
            } else {
              sum = t.in;
              first = l;
            }
            for ( // cumulate
            int i = first; // cumulate
                i < h; // cumulate
                ++i) a[i] = sum = fn.applyAsLong(sum, a[i]);
          } else if (h < fnc) {
            // skip rightmost
            sum = a[l];
            for ( // sum only
            int i = l + 1; // sum only
                i < h; // sum only
                ++i) sum = fn.applyAsLong(sum, a[i]);
          } else sum = t.in;
          t.out = sum;
          for (LongCumulateTask par; ; ) {
            // propagate
            if ((par = (LongCumulateTask) t.getCompleter()) == null) {
              if ( // enable join
              (state & FINISHED) != 0) t.quietlyComplete();
              break outer;
            }
            int b = par.getPendingCount();
            if ((b & state & FINISHED) != 0)
              // both done
              t = par;
            else if ((b & state & SUMMED) != 0) {
              // both summed
              int nextState;
              LongCumulateTask lt, rt;
              if ((lt = par.left) != null && (rt = par.right) != null) {
                long lout = lt.out;
                par.out = (rt.hi == fnc ? lout : fn.applyAsLong(lout, rt.out));
              }
              int refork = (((b & CUMULATE) == 0 && par.lo == org) ? CUMULATE : 0);
              if ((nextState = b | state | refork) == b
                  || par.compareAndSetPendingCount(b, nextState)) {
                // drop finished
                state = SUMMED;
                t = par;
                if (refork != 0) par.fork();
              }
            } else if (par.compareAndSetPendingCount(b, b | state))
              // sib not ready
              break outer;
          }
        }
      }
    }
  }

  @SuppressWarnings("serial")
  static final class DoubleCumulateTask extends CountedCompleter<Void> {

    final double[] array;

    final DoubleBinaryOperator function;

    DoubleCumulateTask left, right;
    double in, out;
    final int lo, hi, origin, fence, threshold;

    /** Root task constructor */
    public DoubleCumulateTask(
        DoubleCumulateTask parent, DoubleBinaryOperator function, double[] array, int lo, int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.lo = this.origin = lo;
      this.hi = this.fence = hi;
      int p;
      this.threshold =
          (p = (hi - lo) / (ForkJoinPool.getCommonPoolParallelism() << 3)) <= MIN_PARTITION
              ? MIN_PARTITION
              : p;
    }

    /** Subtask constructor */
    DoubleCumulateTask(
        DoubleCumulateTask parent,
        DoubleBinaryOperator function,
        double[] array,
        int origin,
        int fence,
        int threshold,
        int lo,
        int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.origin = origin;
      this.fence = fence;
      this.threshold = threshold;
      this.lo = lo;
      this.hi = hi;
    }

    public final void compute() {
      DoubleBinaryOperator fn;
      double[] a;
      if ((fn = this.function) == null || (a = this.array) == null)
        // hoist checks
        throw new NullPointerException();
      int th = threshold, org = origin, fnc = fence, l, h;
      DoubleCumulateTask t = this;
      outer:
      while ((l = t.lo) >= 0 && (h = t.hi) <= a.length) {
        if (h - l > th) {
          DoubleCumulateTask lt = t.left, rt = t.right, f;
          if (lt == null) {
            // first pass
            int mid = (l + h) >>> 1;
            f = rt = t.right = new DoubleCumulateTask(t, fn, a, org, fnc, th, mid, h);
            t = lt = t.left = new DoubleCumulateTask(t, fn, a, org, fnc, th, l, mid);
          } else {
            // possibly refork
            double pin = t.in;
            lt.in = pin;
            f = t = null;
            if (rt != null) {
              double lout = lt.out;
              rt.in = (l == org ? lout : fn.applyAsDouble(pin, lout));
              for (int c; ; ) {
                if (((c = rt.getPendingCount()) & CUMULATE) != 0) break;
                if (rt.compareAndSetPendingCount(c, c | CUMULATE)) {
                  t = rt;
                  break;
                }
              }
            }
            for (int c; ; ) {
              if (((c = lt.getPendingCount()) & CUMULATE) != 0) break;
              if (lt.compareAndSetPendingCount(c, c | CUMULATE)) {
                if (t != null) f = t;
                t = lt;
                break;
              }
            }
            if (t == null) break;
          }
          if (f != null) f.fork();
        } else {
          // Transition to sum, cumulate, or both
          int state;
          for (int b; ; ) {
            if (((b = t.getPendingCount()) & FINISHED) != 0)
              // already done
              break outer;
            state = ((b & CUMULATE) != 0 ? FINISHED : (l > org) ? SUMMED : (SUMMED | FINISHED));
            if (t.compareAndSetPendingCount(b, b | state)) break;
          }
          double sum;
          if (state != SUMMED) {
            int first;
            if (l == org) {
              // leftmost; no in
              sum = a[org];
              first = org + 1;
            } else {
              sum = t.in;
              first = l;
            }
            for ( // cumulate
            int i = first; // cumulate
                i < h; // cumulate
                ++i) a[i] = sum = fn.applyAsDouble(sum, a[i]);
          } else if (h < fnc) {
            // skip rightmost
            sum = a[l];
            for ( // sum only
            int i = l + 1; // sum only
                i < h; // sum only
                ++i) sum = fn.applyAsDouble(sum, a[i]);
          } else sum = t.in;
          t.out = sum;
          for (DoubleCumulateTask par; ; ) {
            // propagate
            if ((par = (DoubleCumulateTask) t.getCompleter()) == null) {
              if ( // enable join
              (state & FINISHED) != 0) t.quietlyComplete();
              break outer;
            }
            int b = par.getPendingCount();
            if ((b & state & FINISHED) != 0)
              // both done
              t = par;
            else if ((b & state & SUMMED) != 0) {
              // both summed
              int nextState;
              DoubleCumulateTask lt, rt;
              if ((lt = par.left) != null && (rt = par.right) != null) {
                double lout = lt.out;
                par.out = (rt.hi == fnc ? lout : fn.applyAsDouble(lout, rt.out));
              }
              int refork = (((b & CUMULATE) == 0 && par.lo == org) ? CUMULATE : 0);
              if ((nextState = b | state | refork) == b
                  || par.compareAndSetPendingCount(b, nextState)) {
                // drop finished
                state = SUMMED;
                t = par;
                if (refork != 0) par.fork();
              }
            } else if (par.compareAndSetPendingCount(b, b | state))
              // sib not ready
              break outer;
          }
        }
      }
    }
  }

  @SuppressWarnings("serial")
  static final class IntCumulateTask extends CountedCompleter<Void> {

    final int[] array;

    final IntBinaryOperator function;

    IntCumulateTask left, right;
    int in, out;
    final int lo, hi, origin, fence, threshold;

    /** Root task constructor */
    public IntCumulateTask(
        IntCumulateTask parent, IntBinaryOperator function, int[] array, int lo, int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.lo = this.origin = lo;
      this.hi = this.fence = hi;
      int p;
      this.threshold =
          (p = (hi - lo) / (ForkJoinPool.getCommonPoolParallelism() << 3)) <= MIN_PARTITION
              ? MIN_PARTITION
              : p;
    }

    /** Subtask constructor */
    IntCumulateTask(
        IntCumulateTask parent,
        IntBinaryOperator function,
        int[] array,
        int origin,
        int fence,
        int threshold,
        int lo,
        int hi) {
      super(parent);
      this.function = function;
      this.array = array;
      this.origin = origin;
      this.fence = fence;
      this.threshold = threshold;
      this.lo = lo;
      this.hi = hi;
    }

    public final void compute() {
      IntBinaryOperator fn;
      int[] a;
      if ((fn = this.function) == null || (a = this.array) == null)
        // hoist checks
        throw new NullPointerException();
      int th = threshold, org = origin, fnc = fence, l, h;
      IntCumulateTask t = this;
      outer:
      while ((l = t.lo) >= 0 && (h = t.hi) <= a.length) {
        if (h - l > th) {
          IntCumulateTask lt = t.left, rt = t.right, f;
          if (lt == null) {
            // first pass
            int mid = (l + h) >>> 1;
            f = rt = t.right = new IntCumulateTask(t, fn, a, org, fnc, th, mid, h);
            t = lt = t.left = new IntCumulateTask(t, fn, a, org, fnc, th, l, mid);
          } else {
            // possibly refork
            int pin = t.in;
            lt.in = pin;
            f = t = null;
            if (rt != null) {
              int lout = lt.out;
              rt.in = (l == org ? lout : fn.applyAsInt(pin, lout));
              for (int c; ; ) {
                if (((c = rt.getPendingCount()) & CUMULATE) != 0) break;
                if (rt.compareAndSetPendingCount(c, c | CUMULATE)) {
                  t = rt;
                  break;
                }
              }
            }
            for (int c; ; ) {
              if (((c = lt.getPendingCount()) & CUMULATE) != 0) break;
              if (lt.compareAndSetPendingCount(c, c | CUMULATE)) {
                if (t != null) f = t;
                t = lt;
                break;
              }
            }
            if (t == null) break;
          }
          if (f != null) f.fork();
        } else {
          // Transition to sum, cumulate, or both
          int state;
          for (int b; ; ) {
            if (((b = t.getPendingCount()) & FINISHED) != 0)
              // already done
              break outer;
            state = ((b & CUMULATE) != 0 ? FINISHED : (l > org) ? SUMMED : (SUMMED | FINISHED));
            if (t.compareAndSetPendingCount(b, b | state)) break;
          }
          int sum;
          if (state != SUMMED) {
            int first;
            if (l == org) {
              // leftmost; no in
              sum = a[org];
              first = org + 1;
            } else {
              sum = t.in;
              first = l;
            }
            for ( // cumulate
            int i = first; // cumulate
                i < h; // cumulate
                ++i) a[i] = sum = fn.applyAsInt(sum, a[i]);
          } else if (h < fnc) {
            // skip rightmost
            sum = a[l];
            for ( // sum only
            int i = l + 1; // sum only
                i < h; // sum only
                ++i) sum = fn.applyAsInt(sum, a[i]);
          } else sum = t.in;
          t.out = sum;
          for (IntCumulateTask par; ; ) {
            // propagate
            if ((par = (IntCumulateTask) t.getCompleter()) == null) {
              if ( // enable join
              (state & FINISHED) != 0) t.quietlyComplete();
              break outer;
            }
            int b = par.getPendingCount();
            if ((b & state & FINISHED) != 0)
              // both done
              t = par;
            else if ((b & state & SUMMED) != 0) {
              // both summed
              int nextState;
              IntCumulateTask lt, rt;
              if ((lt = par.left) != null && (rt = par.right) != null) {
                int lout = lt.out;
                par.out = (rt.hi == fnc ? lout : fn.applyAsInt(lout, rt.out));
              }
              int refork = (((b & CUMULATE) == 0 && par.lo == org) ? CUMULATE : 0);
              if ((nextState = b | state | refork) == b
                  || par.compareAndSetPendingCount(b, nextState)) {
                // drop finished
                state = SUMMED;
                t = par;
                if (refork != 0) par.fork();
              }
            } else if (par.compareAndSetPendingCount(b, b | state))
              // sib not ready
              break outer;
          }
        }
      }
    }
  }
}
