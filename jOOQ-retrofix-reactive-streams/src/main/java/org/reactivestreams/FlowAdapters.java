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
package org.reactivestreams;

import static java.util.Objects.requireNonNull;

import org.java.util.concurrent.Flow;

/** Bridge between Reactive Streams API and the Java 9 {@link java.util.concurrent.Flow} API. */
public final class FlowAdapters {

  /** Utility class. */
  private FlowAdapters() {
    throw new IllegalStateException("No instances!");
  }

  /**
   * Converts a Flow Publisher into a Reactive Streams Publisher.
   *
   * @param <T> the element type
   * @param flowPublisher the source Flow Publisher to convert
   * @return the equivalent Reactive Streams Publisher
   */
  @SuppressWarnings("unchecked")
  public static <T> org.reactivestreams.Publisher<T> toPublisher(
      Flow.Publisher<? extends T> flowPublisher) {
    requireNonNull(flowPublisher, "flowPublisher");
    final org.reactivestreams.Publisher<T> publisher;
    if (flowPublisher instanceof FlowPublisherFromReactive) {
      publisher =
          (org.reactivestreams.Publisher<T>)
              (((FlowPublisherFromReactive<T>) flowPublisher).reactiveStreams);
    } else if (flowPublisher instanceof org.reactivestreams.Publisher) {
      publisher = (org.reactivestreams.Publisher<T>) flowPublisher;
    } else {
      publisher = new ReactivePublisherFromFlow<T>(flowPublisher);
    }
    return publisher;
  }

  /**
   * Converts a Reactive Streams Publisher into a Flow Publisher.
   *
   * @param <T> the element type
   * @param reactiveStreamsPublisher the source Reactive Streams Publisher to convert
   * @return the equivalent Flow Publisher
   */
  @SuppressWarnings("unchecked")
  public static <T> Flow.Publisher<T> toFlowPublisher(
      org.reactivestreams.Publisher<? extends T> reactiveStreamsPublisher) {
    requireNonNull(reactiveStreamsPublisher, "reactiveStreamsPublisher");
    final Flow.Publisher<T> flowPublisher;
    if (reactiveStreamsPublisher instanceof ReactivePublisherFromFlow) {
      flowPublisher =
          (Flow.Publisher<T>) (((ReactivePublisherFromFlow<T>) reactiveStreamsPublisher).flow);
    } else if (reactiveStreamsPublisher instanceof Flow.Publisher) {
      flowPublisher = (Flow.Publisher<T>) reactiveStreamsPublisher;
    } else {
      flowPublisher = new FlowPublisherFromReactive<T>(reactiveStreamsPublisher);
    }
    return flowPublisher;
  }

  /**
   * Converts a Flow Processor into a Reactive Streams Processor.
   *
   * @param <T> the input value type
   * @param <U> the output value type
   * @param flowProcessor the source Flow Processor to convert
   * @return the equivalent Reactive Streams Processor
   */
  @SuppressWarnings("unchecked")
  public static <T, U> org.reactivestreams.Processor<T, U> toProcessor(
      Flow.Processor<? super T, ? extends U> flowProcessor) {
    requireNonNull(flowProcessor, "flowProcessor");
    final org.reactivestreams.Processor<T, U> processor;
    if (flowProcessor instanceof FlowToReactiveProcessor) {
      processor =
          (org.reactivestreams.Processor<T, U>)
              (((FlowToReactiveProcessor<T, U>) flowProcessor).reactiveStreams);
    } else if (flowProcessor instanceof org.reactivestreams.Processor) {
      processor = (org.reactivestreams.Processor<T, U>) flowProcessor;
    } else {
      processor = new ReactiveToFlowProcessor<T, U>(flowProcessor);
    }
    return processor;
  }

  /**
   * Converts a Reactive Streams Processor into a Flow Processor.
   *
   * @param <T> the input value type
   * @param <U> the output value type
   * @param reactiveStreamsProcessor the source Reactive Streams Processor to convert
   * @return the equivalent Flow Processor
   */
  @SuppressWarnings("unchecked")
  public static <T, U> Flow.Processor<T, U> toFlowProcessor(
      org.reactivestreams.Processor<? super T, ? extends U> reactiveStreamsProcessor) {
    requireNonNull(reactiveStreamsProcessor, "reactiveStreamsProcessor");
    final Flow.Processor<T, U> flowProcessor;
    if (reactiveStreamsProcessor instanceof ReactiveToFlowProcessor) {
      flowProcessor =
          (Flow.Processor<T, U>) (((ReactiveToFlowProcessor<T, U>) reactiveStreamsProcessor).flow);
    } else if (reactiveStreamsProcessor instanceof Flow.Processor) {
      flowProcessor = (Flow.Processor<T, U>) reactiveStreamsProcessor;
    } else {
      flowProcessor = new FlowToReactiveProcessor<T, U>(reactiveStreamsProcessor);
    }
    return flowProcessor;
  }

  /**
   * Converts a Reactive Streams Subscriber into a Flow Subscriber.
   *
   * @param <T> the input and output value type
   * @param reactiveStreamsSubscriber the Reactive Streams Subscriber instance to convert
   * @return the equivalent Flow Subscriber
   */
  @SuppressWarnings("unchecked")
  public static <T> Flow.Subscriber<T> toFlowSubscriber(
      org.reactivestreams.Subscriber<T> reactiveStreamsSubscriber) {
    requireNonNull(reactiveStreamsSubscriber, "reactiveStreamsSubscriber");
    final Flow.Subscriber<T> flowSubscriber;
    if (reactiveStreamsSubscriber instanceof ReactiveToFlowSubscriber) {
      flowSubscriber =
          (Flow.Subscriber<T>) ((ReactiveToFlowSubscriber<T>) reactiveStreamsSubscriber).flow;
    } else if (reactiveStreamsSubscriber instanceof Flow.Subscriber) {
      flowSubscriber = (Flow.Subscriber<T>) reactiveStreamsSubscriber;
    } else {
      flowSubscriber = new FlowToReactiveSubscriber<T>(reactiveStreamsSubscriber);
    }
    return flowSubscriber;
  }

  /**
   * Converts a Flow Subscriber into a Reactive Streams Subscriber.
   *
   * @param <T> the input and output value type
   * @param flowSubscriber the Flow Subscriber instance to convert
   * @return the equivalent Reactive Streams Subscriber
   */
  @SuppressWarnings("unchecked")
  public static <T> org.reactivestreams.Subscriber<T> toSubscriber(
      Flow.Subscriber<T> flowSubscriber) {
    requireNonNull(flowSubscriber, "flowSubscriber");
    final org.reactivestreams.Subscriber<T> subscriber;
    if (flowSubscriber instanceof FlowToReactiveSubscriber) {
      subscriber =
          (org.reactivestreams.Subscriber<T>)
              ((FlowToReactiveSubscriber<T>) flowSubscriber).reactiveStreams;
    } else if (flowSubscriber instanceof org.reactivestreams.Subscriber) {
      subscriber = (org.reactivestreams.Subscriber<T>) flowSubscriber;
    } else {
      subscriber = new ReactiveToFlowSubscriber<T>(flowSubscriber);
    }
    return subscriber;
  }

  /** Wraps a Reactive Streams Subscription and converts the calls to a Flow Subscription. */
  static final class FlowToReactiveSubscription implements Flow.Subscription {

    final org.reactivestreams.Subscription reactiveStreams;

    public FlowToReactiveSubscription(org.reactivestreams.Subscription reactive) {
      this.reactiveStreams = reactive;
    }

    @Override
    public void request(long n) {
      reactiveStreams.request(n);
    }

    @Override
    public void cancel() {
      reactiveStreams.cancel();
    }
  }

  /** Wraps a Flow Subscription and converts the calls to a Reactive Streams Subscription. */
  static final class ReactiveToFlowSubscription implements org.reactivestreams.Subscription {

    final Flow.Subscription flow;

    public ReactiveToFlowSubscription(Flow.Subscription flow) {
      this.flow = flow;
    }

    @Override
    public void request(long n) {
      flow.request(n);
    }

    @Override
    public void cancel() {
      flow.cancel();
    }
  }

  /**
   * Wraps a Reactive Streams Subscriber and forwards methods of the Flow Subscriber to it.
   *
   * @param <T> the element type
   */
  static final class FlowToReactiveSubscriber<T> implements Flow.Subscriber<T> {

    final org.reactivestreams.Subscriber<? super T> reactiveStreams;

    public FlowToReactiveSubscriber(org.reactivestreams.Subscriber<? super T> reactive) {
      this.reactiveStreams = reactive;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      reactiveStreams.onSubscribe(
          (subscription == null) ? null : new ReactiveToFlowSubscription(subscription));
    }

    @Override
    public void onNext(T item) {
      reactiveStreams.onNext(item);
    }

    @Override
    public void onError(Throwable throwable) {
      reactiveStreams.onError(throwable);
    }

    @Override
    public void onComplete() {
      reactiveStreams.onComplete();
    }
  }

  /**
   * Wraps a Flow Subscriber and forwards methods of the Reactive Streams Subscriber to it.
   *
   * @param <T> the element type
   */
  static final class ReactiveToFlowSubscriber<T> implements org.reactivestreams.Subscriber<T> {

    final Flow.Subscriber<? super T> flow;

    public ReactiveToFlowSubscriber(Flow.Subscriber<? super T> flow) {
      this.flow = flow;
    }

    @Override
    public void onSubscribe(org.reactivestreams.Subscription subscription) {
      flow.onSubscribe(
          (subscription == null) ? null : new FlowToReactiveSubscription(subscription));
    }

    @Override
    public void onNext(T item) {
      flow.onNext(item);
    }

    @Override
    public void onError(Throwable throwable) {
      flow.onError(throwable);
    }

    @Override
    public void onComplete() {
      flow.onComplete();
    }
  }

  /**
   * Wraps a Flow Processor and forwards methods of the Reactive Streams Processor to it.
   *
   * @param <T> the input type
   * @param <U> the output type
   */
  static final class ReactiveToFlowProcessor<T, U> implements org.reactivestreams.Processor<T, U> {

    final Flow.Processor<? super T, ? extends U> flow;

    public ReactiveToFlowProcessor(Flow.Processor<? super T, ? extends U> flow) {
      this.flow = flow;
    }

    @Override
    public void onSubscribe(org.reactivestreams.Subscription subscription) {
      flow.onSubscribe(
          (subscription == null) ? null : new FlowToReactiveSubscription(subscription));
    }

    @Override
    public void onNext(T t) {
      flow.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
      flow.onError(t);
    }

    @Override
    public void onComplete() {
      flow.onComplete();
    }

    @Override
    public void subscribe(org.reactivestreams.Subscriber<? super U> s) {
      flow.subscribe((s == null) ? null : new FlowToReactiveSubscriber<U>(s));
    }
  }

  /**
   * Wraps a Reactive Streams Processor and forwards methods of the Flow Processor to it.
   *
   * @param <T> the input type
   * @param <U> the output type
   */
  static final class FlowToReactiveProcessor<T, U> implements Flow.Processor<T, U> {

    final org.reactivestreams.Processor<? super T, ? extends U> reactiveStreams;

    public FlowToReactiveProcessor(org.reactivestreams.Processor<? super T, ? extends U> reactive) {
      this.reactiveStreams = reactive;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      reactiveStreams.onSubscribe(
          (subscription == null) ? null : new ReactiveToFlowSubscription(subscription));
    }

    @Override
    public void onNext(T t) {
      reactiveStreams.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
      reactiveStreams.onError(t);
    }

    @Override
    public void onComplete() {
      reactiveStreams.onComplete();
    }

    @Override
    public void subscribe(Flow.Subscriber<? super U> s) {
      reactiveStreams.subscribe((s == null) ? null : new ReactiveToFlowSubscriber<U>(s));
    }
  }

  /**
   * Reactive Streams Publisher that wraps a Flow Publisher.
   *
   * @param <T> the element type
   */
  static final class ReactivePublisherFromFlow<T> implements org.reactivestreams.Publisher<T> {

    final Flow.Publisher<? extends T> flow;

    public ReactivePublisherFromFlow(Flow.Publisher<? extends T> flowPublisher) {
      this.flow = flowPublisher;
    }

    @Override
    public void subscribe(org.reactivestreams.Subscriber<? super T> reactive) {
      flow.subscribe((reactive == null) ? null : new FlowToReactiveSubscriber<T>(reactive));
    }
  }

  /**
   * Flow Publisher that wraps a Reactive Streams Publisher.
   *
   * @param <T> the element type
   */
  static final class FlowPublisherFromReactive<T> implements Flow.Publisher<T> {

    final org.reactivestreams.Publisher<? extends T> reactiveStreams;

    public FlowPublisherFromReactive(org.reactivestreams.Publisher<? extends T> reactivePublisher) {
      this.reactiveStreams = reactivePublisher;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super T> flow) {
      reactiveStreams.subscribe((flow == null) ? null : new ReactiveToFlowSubscriber<T>(flow));
    }
  }
}
