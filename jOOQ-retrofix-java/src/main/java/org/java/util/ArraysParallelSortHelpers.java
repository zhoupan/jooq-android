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

import java.util.Comparator;
import org.java.util.concurrent.CountedCompleter;

/**
 * Helper utilities for the parallel sort methods in Arrays.parallelSort.
 *
 * <p>For each primitive type, plus Object, we define a static class to contain the Sorter and
 * Merger implementations for that type:
 *
 * <p>Sorter classes based mainly on CilkSort <A href="http://supertech.lcs.mit.edu/cilk/">Cilk</A>:
 * Basic algorithm: if array size is small, just use a sequential sort (via Arrays.sort) Otherwise:
 * 1. Break array in half. 2. For each half, a. break the half in half (i.e., quarters), b. sort the
 * quarters c. merge them together 3. merge together the two halves.
 *
 * <p>One reason for splitting in quarters is that this guarantees that the final sort is in the
 * main array, not the workspace array. (workspace and main swap roles on each subsort step.)
 * Leaf-level sorts use the associated sequential sort.
 *
 * <p>Merger classes perform merging for Sorter. They are structured such that if the underlying
 * sort is stable (as is true for TimSort), then so is the full sort. If big enough, they split the
 * largest of the two partitions in half, find the greatest point in smaller partition less than the
 * beginning of the second half of larger via binary search; and then merge in parallel the two
 * partitions. In part to ensure tasks are triggered in stability-preserving order, the current
 * CountedCompleter design requires some little tasks to serve as place holders for triggering
 * completion tasks. These classes (EmptyCompleter and Relay) don't need to keep track of the
 * arrays, and are never themselves forked, so don't hold any task state.
 *
 * <p>The base sequential sorts rely on non-public versions of TimSort, ComparableTimSort sort
 * methods that accept temp workspace array slices that we will have already allocated, so avoids
 * redundant allocation.
 */
/*package*/
final class ArraysParallelSortHelpers {

  /*
   * Style note: The task classes have a lot of parameters, that are
   * stored as task fields and copied to local variables and used in
   * compute() methods, We pack these into as few lines as possible,
   * and hoist consistency checks among them before main loops, to
   * reduce distraction.
   */
  /**
   * A placeholder task for Sorters, used for the lowest quartile task, that does not need to
   * maintain array state.
   */
  static final class EmptyCompleter extends CountedCompleter<Void> {

    static final long serialVersionUID = 2446542900576103244L;

    EmptyCompleter(CountedCompleter<?> p) {
      super(p);
    }

    public final void compute() {}
  }

  /** A trigger for secondary merge of two merges */
  static final class Relay extends CountedCompleter<Void> {

    static final long serialVersionUID = 2446542900576103244L;

    final CountedCompleter<?> task;

    Relay(CountedCompleter<?> task) {
      super(null, 1);
      this.task = task;
    }

    public final void compute() {}

    public final void onCompletion(CountedCompleter<?> t) {
      task.compute();
    }
  }

  /** Object + Comparator support class */
  static final class FJObject {

    static final class Sorter<T> extends CountedCompleter<Void> {

      static final long serialVersionUID = 2446542900576103244L;

      final T[] a;

      final T[] w;

      final int base, size, wbase, gran;

      Comparator<? super T> comparator;

      Sorter(
          CountedCompleter<?> par,
          T[] a,
          T[] w,
          int base,
          int size,
          int wbase,
          int gran,
          Comparator<? super T> comparator) {
        super(par);
        this.a = a;
        this.w = w;
        this.base = base;
        this.size = size;
        this.wbase = wbase;
        this.gran = gran;
        this.comparator = comparator;
      }

      public final void compute() {
        CountedCompleter<?> s = this;
        Comparator<? super T> c = this.comparator;
        // localize all params
        T[] a = this.a, w = this.w;
        int b = this.base, n = this.size, wb = this.wbase, g = this.gran;
        while (n > g) {
          // quartiles
          int h = n >>> 1, q = h >>> 1, u = h + q;
          Relay fc = new Relay(new Merger<>(s, w, a, wb, h, wb + h, n - h, b, g, c));
          Relay rc = new Relay(new Merger<>(fc, a, w, b + h, q, b + u, n - u, wb + h, g, c));
          new Sorter<>(rc, a, w, b + u, n - u, wb + u, g, c).fork();
          new Sorter<>(rc, a, w, b + h, q, wb + h, g, c).fork();
          Relay bc = new Relay(new Merger<>(fc, a, w, b, q, b + q, h - q, wb, g, c));
          new Sorter<>(bc, a, w, b + q, h - q, wb + q, g, c).fork();
          s = new EmptyCompleter(bc);
          n = q;
        }
        TimSort.sort(a, b, b + n, c, w, wb, n);
        s.tryComplete();
      }
    }

    static final class Merger<T> extends CountedCompleter<Void> {

      static final long serialVersionUID = 2446542900576103244L;

      // main and workspace arrays
      final T[] a;

      final T[] w;

      final int lbase, lsize, rbase, rsize, wbase, gran;

      Comparator<? super T> comparator;

      Merger(
          CountedCompleter<?> par,
          T[] a,
          T[] w,
          int lbase,
          int lsize,
          int rbase,
          int rsize,
          int wbase,
          int gran,
          Comparator<? super T> comparator) {
        super(par);
        this.a = a;
        this.w = w;
        this.lbase = lbase;
        this.lsize = lsize;
        this.rbase = rbase;
        this.rsize = rsize;
        this.wbase = wbase;
        this.gran = gran;
        this.comparator = comparator;
      }

      public final void compute() {
        Comparator<? super T> c = this.comparator;
        // localize all params
        T[] a = this.a, w = this.w;
        int lb = this.lbase,
            ln = this.lsize,
            rb = this.rbase,
            rn = this.rsize,
            k = this.wbase,
            g = this.gran;
        if (a == null || w == null || lb < 0 || rb < 0 || k < 0 || c == null)
          // hoist checks
          throw new IllegalStateException();
        for (int lh, rh; ; ) {
          // split larger, find point in smaller
          if (ln >= rn) {
            if (ln <= g) break;
            rh = rn;
            T split = a[(lh = ln >>> 1) + lb];
            for (int lo = 0; lo < rh; ) {
              int rm = (lo + rh) >>> 1;
              if (c.compare(split, a[rm + rb]) <= 0) rh = rm;
              else lo = rm + 1;
            }
          } else {
            if (rn <= g) break;
            lh = ln;
            T split = a[(rh = rn >>> 1) + rb];
            for (int lo = 0; lo < lh; ) {
              int lm = (lo + lh) >>> 1;
              if (c.compare(split, a[lm + lb]) <= 0) lh = lm;
              else lo = lm + 1;
            }
          }
          Merger<T> m =
              new Merger<>(this, a, w, lb + lh, ln - lh, rb + rh, rn - rh, k + lh + rh, g, c);
          rn = rh;
          ln = lh;
          addToPendingCount(1);
          m.fork();
        }
        // index bounds
        int lf = lb + ln, rf = rb + rn;
        while (lb < lf && rb < rf) {
          T t, al, ar;
          if (c.compare((al = a[lb]), (ar = a[rb])) <= 0) {
            lb++;
            t = al;
          } else {
            rb++;
            t = ar;
          }
          w[k++] = t;
        }
        if (rb < rf) System.arraycopy(a, rb, w, k, rf - rb);
        else if (lb < lf) System.arraycopy(a, lb, w, k, lf - lb);
        tryComplete();
      }
    }
  }
}
