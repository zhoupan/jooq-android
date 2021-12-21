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
package org.java.util.stream;

import org.java.util.Objects;
import org.java.util.Spliterator;
import org.java.util.function.DoublePredicate;
import org.java.util.function.IntPredicate;
import org.java.util.function.LongPredicate;
import org.java.util.function.Predicate;
import org.java.util.function.Supplier;

/**
 * Factory for instances of a short-circuiting {@code TerminalOp} that implement quantified
 * predicate matching on the elements of a stream. Supported variants include match-all, match-any,
 * and match-none.
 *
 * @since 1.8
 */
final class MatchOps {

  private MatchOps() {}

  /** Enum describing quantified match options -- all match, any match, none match. */
  enum MatchKind {

    /** Do any elements match the predicate? */
    ANY(true, true),
    /** Do all elements match the predicate? */
    ALL(false, false),
    /** Do no elements match the predicate? */
    NONE(true, false);

    final boolean stopOnPredicateMatches;

    final boolean shortCircuitResult;

    private MatchKind(boolean stopOnPredicateMatches, boolean shortCircuitResult) {
      this.stopOnPredicateMatches = stopOnPredicateMatches;
      this.shortCircuitResult = shortCircuitResult;
    }
  }

  /**
   * Constructs a quantified predicate matcher for a Stream.
   *
   * @param <T> the type of stream elements
   * @param predicate the {@code Predicate} to apply to stream elements
   * @param matchKind the kind of quantified match (all, any, none)
   * @return a {@code TerminalOp} implementing the desired quantified match criteria
   */
  public static <T> TerminalOp<T, Boolean> makeRef(
      Predicate<? super T> predicate, MatchKind matchKind) {
    Objects.requireNonNull(predicate);
    Objects.requireNonNull(matchKind);
    class MatchSink extends BooleanTerminalSink<T> {

      MatchSink() {
        super(matchKind);
      }

      @Override
      public void accept(T t) {
        if (!stop && predicate.test(t) == matchKind.stopOnPredicateMatches) {
          stop = true;
          value = matchKind.shortCircuitResult;
        }
      }
    }
    return new MatchOp<>(StreamShape.REFERENCE, matchKind, MatchSink::new);
  }

  /**
   * Constructs a quantified predicate matcher for an {@code IntStream}.
   *
   * @param predicate the {@code Predicate} to apply to stream elements
   * @param matchKind the kind of quantified match (all, any, none)
   * @return a {@code TerminalOp} implementing the desired quantified match criteria
   */
  public static TerminalOp<Integer, Boolean> makeInt(IntPredicate predicate, MatchKind matchKind) {
    Objects.requireNonNull(predicate);
    Objects.requireNonNull(matchKind);
    class MatchSink extends BooleanTerminalSink<Integer> implements Sink.OfInt {

      MatchSink() {
        super(matchKind);
      }

      @Override
      public void accept(int t) {
        if (!stop && predicate.test(t) == matchKind.stopOnPredicateMatches) {
          stop = true;
          value = matchKind.shortCircuitResult;
        }
      }

      @Override
      public void accept(Integer i) {
        SinkDefaults.OfInt.accept(this, i);
      }
    }
    return new MatchOp<>(StreamShape.INT_VALUE, matchKind, MatchSink::new);
  }

  /**
   * Constructs a quantified predicate matcher for a {@code LongStream}.
   *
   * @param predicate the {@code Predicate} to apply to stream elements
   * @param matchKind the kind of quantified match (all, any, none)
   * @return a {@code TerminalOp} implementing the desired quantified match criteria
   */
  public static TerminalOp<Long, Boolean> makeLong(LongPredicate predicate, MatchKind matchKind) {
    Objects.requireNonNull(predicate);
    Objects.requireNonNull(matchKind);
    class MatchSink extends BooleanTerminalSink<Long> implements Sink.OfLong {

      MatchSink() {
        super(matchKind);
      }

      @Override
      public void accept(long t) {
        if (!stop && predicate.test(t) == matchKind.stopOnPredicateMatches) {
          stop = true;
          value = matchKind.shortCircuitResult;
        }
      }

      @Override
      public void accept(Long i) {
        SinkDefaults.OfLong.accept(this, i);
      }
    }
    return new MatchOp<>(StreamShape.LONG_VALUE, matchKind, MatchSink::new);
  }

  /**
   * Constructs a quantified predicate matcher for a {@code DoubleStream}.
   *
   * @param predicate the {@code Predicate} to apply to stream elements
   * @param matchKind the kind of quantified match (all, any, none)
   * @return a {@code TerminalOp} implementing the desired quantified match criteria
   */
  public static TerminalOp<Double, Boolean> makeDouble(
      DoublePredicate predicate, MatchKind matchKind) {
    Objects.requireNonNull(predicate);
    Objects.requireNonNull(matchKind);
    class MatchSink extends BooleanTerminalSink<Double> implements Sink.OfDouble {

      MatchSink() {
        super(matchKind);
      }

      @Override
      public void accept(double t) {
        if (!stop && predicate.test(t) == matchKind.stopOnPredicateMatches) {
          stop = true;
          value = matchKind.shortCircuitResult;
        }
      }

      @Override
      public void accept(Double i) {
        SinkDefaults.OfDouble.accept(this, i);
      }
    }
    return new MatchOp<>(StreamShape.DOUBLE_VALUE, matchKind, MatchSink::new);
  }

  /**
   * A short-circuiting {@code TerminalOp} that evaluates a predicate on the elements of a stream
   * and determines whether all, any or none of those elements match the predicate.
   *
   * @param <T> the output type of the stream pipeline
   */
  private static final class MatchOp<T> implements TerminalOp<T, Boolean> {

    private final StreamShape inputShape;

    final MatchKind matchKind;

    final Supplier<BooleanTerminalSink<T>> sinkSupplier;

    /**
     * Constructs a {@code MatchOp}.
     *
     * @param shape the output shape of the stream pipeline
     * @param matchKind the kind of quantified match (all, any, none)
     * @param sinkSupplier {@code Supplier} for a {@code Sink} of the appropriate shape which
     *     implements the matching operation
     */
    MatchOp(StreamShape shape, MatchKind matchKind, Supplier<BooleanTerminalSink<T>> sinkSupplier) {
      this.inputShape = shape;
      this.matchKind = matchKind;
      this.sinkSupplier = sinkSupplier;
    }

    @Override
    public int getOpFlags() {
      return StreamOpFlag.IS_SHORT_CIRCUIT | StreamOpFlag.NOT_ORDERED;
    }

    @Override
    public StreamShape inputShape() {
      return inputShape;
    }

    @Override
    public <S> Boolean evaluateSequential(PipelineHelper<T> helper, Spliterator<S> spliterator) {
      return helper.wrapAndCopyInto(sinkSupplier.get(), spliterator).getAndClearState();
    }

    @Override
    public <S> Boolean evaluateParallel(PipelineHelper<T> helper, Spliterator<S> spliterator) {
      // Approach for parallel implementation:
      // - Decompose as per usual
      // - run match on leaf chunks, call result "b"
      // - if b == matchKind.shortCircuitOn, complete early and return b
      // - else if we complete normally, return !shortCircuitOn
      return new MatchTask<>(this, helper, spliterator).invoke();
    }
  }

  /**
   * Boolean specific terminal sink to avoid the boxing costs when returning results. Subclasses
   * implement the shape-specific functionality.
   *
   * @param <T> The output type of the stream pipeline
   */
  private abstract static class BooleanTerminalSink<T> implements Sink<T> {

    boolean stop;

    boolean value;

    BooleanTerminalSink(MatchKind matchKind) {
      value = !matchKind.shortCircuitResult;
    }

    public boolean getAndClearState() {
      return value;
    }

    @Override
    public boolean cancellationRequested() {
      return stop;
    }

    @Override
    public void begin(long size) {}

    @Override
    public void end() {}

    @Override
    public void accept(int value) {
      SinkDefaults.reject();
    }

    @Override
    public void accept(long value) {
      SinkDefaults.reject();
    }

    @Override
    public void accept(double value) {
      SinkDefaults.reject();
    }
  }

  /**
   * ForkJoinTask implementation to implement a parallel short-circuiting quantified match
   *
   * @param <P_IN> the type of source elements for the pipeline
   * @param <P_OUT> the type of output elements for the pipeline
   */
  @SuppressWarnings("serial")
  private static final class MatchTask<P_IN, P_OUT>
      extends AbstractShortCircuitTask<P_IN, P_OUT, Boolean, MatchTask<P_IN, P_OUT>> {

    private final MatchOp<P_OUT> op;

    /** Constructor for root node */
    MatchTask(MatchOp<P_OUT> op, PipelineHelper<P_OUT> helper, Spliterator<P_IN> spliterator) {
      super(helper, spliterator);
      this.op = op;
    }

    /** Constructor for non-root node */
    MatchTask(MatchTask<P_IN, P_OUT> parent, Spliterator<P_IN> spliterator) {
      super(parent, spliterator);
      this.op = parent.op;
    }

    @Override
    protected MatchTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
      return new MatchTask<>(this, spliterator);
    }

    @Override
    protected Boolean doLeaf() {
      boolean b = helper.wrapAndCopyInto(op.sinkSupplier.get(), spliterator).getAndClearState();
      if (b == op.matchKind.shortCircuitResult) shortCircuit(b);
      return null;
    }

    @Override
    protected Boolean getEmptyResult() {
      return !op.matchKind.shortCircuitResult;
    }
  }
}
