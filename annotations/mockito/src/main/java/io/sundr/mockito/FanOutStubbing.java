/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

/**
 * An {@link OngoingStubbing} over every overload a stubbing matched.
 * <p>
 * Mockito appends chained answers to the latest stubbing of a mock, so saved stubbings of
 * earlier overloads cannot be extended directly. Instead each chained call replays the
 * full answer chain per overload: the overload is stubbed again from scratch, shadowing
 * its previous stubbing, so all matched overloads always share the same behavior sequence.
 *
 * @param <T> the stubbed return type.
 */
public final class FanOutStubbing<T> implements OngoingStubbing<T> {

  private final List<Supplier<OngoingStubbing<T>>> starters;
  private final List<UnaryOperator<OngoingStubbing<T>>> steps = new ArrayList<>();
  private OngoingStubbing<T> last;

  private FanOutStubbing(List<Supplier<OngoingStubbing<T>>> starters, UnaryOperator<OngoingStubbing<T>> first) {
    this.starters = starters;
    apply(first);
  }

  /**
   * Stubs every matched overload with the given first answer.
   *
   * @param starters one stubbing starter per matched overload.
   * @param first the first answer of the behavior chain.
   * @param <T> the stubbed return type.
   * @return a stubbing fanning out chained answers to all matched overloads.
   */
  public static <T> OngoingStubbing<T> of(List<Supplier<OngoingStubbing<T>>> starters,
      UnaryOperator<OngoingStubbing<T>> first) {
    if (starters.size() == 1) {
      return first.apply(starters.get(0).get());
    }
    return new FanOutStubbing<>(starters, first);
  }

  private OngoingStubbing<T> apply(UnaryOperator<OngoingStubbing<T>> step) {
    steps.add(step);
    for (Supplier<OngoingStubbing<T>> starter : starters) {
      OngoingStubbing<T> stubbing = starter.get();
      for (UnaryOperator<OngoingStubbing<T>> current : steps) {
        stubbing = current.apply(stubbing);
      }
      last = stubbing;
    }
    return this;
  }

  @Override
  public OngoingStubbing<T> thenReturn(T value) {
    return apply(stubbing -> stubbing.thenReturn(value));
  }

  @Override
  public OngoingStubbing<T> thenReturn(T value, T... values) {
    return apply(stubbing -> stubbing.thenReturn(value, values));
  }

  @Override
  public OngoingStubbing<T> thenThrow(Throwable... throwables) {
    return apply(stubbing -> stubbing.thenThrow(throwables));
  }

  @Override
  public OngoingStubbing<T> thenThrow(Class<? extends Throwable> throwableType) {
    return apply(stubbing -> stubbing.thenThrow(throwableType));
  }

  @Override
  public OngoingStubbing<T> thenThrow(Class<? extends Throwable> throwableType,
      Class<? extends Throwable>... throwableTypes) {
    return apply(stubbing -> stubbing.thenThrow(throwableType, throwableTypes));
  }

  @Override
  public OngoingStubbing<T> thenAnswer(Answer<?> answer) {
    return apply(stubbing -> stubbing.thenAnswer(answer));
  }

  @Override
  public OngoingStubbing<T> then(Answer<?> answer) {
    return thenAnswer(answer);
  }

  @Override
  public OngoingStubbing<T> thenCallRealMethod() {
    return apply(OngoingStubbing::thenCallRealMethod);
  }

  @Override
  public <M> M getMock() {
    return last.getMock();
  }
}
