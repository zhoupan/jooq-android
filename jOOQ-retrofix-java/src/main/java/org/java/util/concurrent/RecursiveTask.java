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
package org.java.util.concurrent;

/**
 * A recursive result-bearing {@link ForkJoinTask}.
 *
 * <p>For a classic example, here is a task computing Fibonacci numbers:
 *
 * <pre>{@code
 * class Fibonacci extends RecursiveTask<Integer> {
 *   final int n;
 *   Fibonacci(int n) { this.n = n; }
 *   protected Integer compute() {
 *     if (n <= 1)
 *       return n;
 *     Fibonacci f1 = new Fibonacci(n - 1);
 *     f1.fork();
 *     Fibonacci f2 = new Fibonacci(n - 2);
 *     return f2.compute() + f1.join();
 *   }
 * }
 * }</pre>
 *
 * However, besides being a dumb way to compute Fibonacci functions (there is a simple fast linear
 * algorithm that you'd use in practice), this is likely to perform poorly because the smallest
 * subtasks are too small to be worthwhile splitting up. Instead, as is the case for nearly all
 * fork/join applications, you'd pick some minimum granularity size (for example 10 here) for which
 * you always sequentially solve rather than subdividing.
 *
 * @since 1.7
 * @author Doug Lea
 */
public abstract class RecursiveTask<V> extends ForkJoinTask<V> {

  // CVS rev. 1.11
  private static final long serialVersionUID = 5232453952276485270L;

  /** Constructor for subclasses to call. */
  public RecursiveTask() {}

  /** The result of the computation. */
  V result;

  /**
   * The main computation performed by this task.
   *
   * @return the result of the computation
   */
  protected abstract V compute();

  public final V getRawResult() {
    return result;
  }

  protected final void setRawResult(V value) {
    result = value;
  }

  /** Implements execution conventions for RecursiveTask. */
  protected final boolean exec() {
    result = compute();
    return true;
  }
}
