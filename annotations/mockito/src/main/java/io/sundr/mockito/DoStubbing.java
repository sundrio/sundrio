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
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.BaseStubber;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.stubbing.Stubber;

/**
 * An {@link OngoingStubbing} that registers a behavior sequence through Mockito's answer-first
 * {@code doReturn/doThrow/doAnswer(...).when(mock).method(matchers)} family.
 * <p>
 * The {@code when(mock.method(matchers))} form actually invokes the mocked method to capture the
 * stubbing, which fires any previously registered broad answer and can blow up during setup (for
 * instance a prior {@code thenAnswer} echoing its first argument, called here with a null matcher
 * placeholder). The do-family never invokes the method, so it is safe regardless of prior stubs.
 * <p>
 * Mockito's do-family is stubber-based and cannot chain onto an existing {@link OngoingStubbing},
 * so consecutive answers are supported by replay: each chained call rebuilds the full answer
 * sequence from scratch as {@code Mockito.doX(...).doY(...)...when(mock).method(matchers)},
 * shadowing the previous registration. This mirrors {@link FanOutStubbing}.
 *
 * @param <T> the stubbed return type.
 */
public final class DoStubbing<T> implements OngoingStubbing<T> {

  /**
   * A {@link BaseStubber} whose do-family entry points forward to Mockito's strict statics, so the
   * first answer of a non-lenient chain and a chained answer share the same {@code Stubber}-based API.
   */
  private static final BaseStubber NON_LENIENT = new BaseStubber() {
    @Override
    public Stubber doThrow(Throwable... toBeThrown) {
      return Mockito.doThrow(toBeThrown);
    }

    @Override
    public Stubber doThrow(Class<? extends Throwable> toBeThrown) {
      return Mockito.doThrow(toBeThrown);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stubber doThrow(Class<? extends Throwable> toBeThrown, Class<? extends Throwable>... nextToBeThrown) {
      return Mockito.doThrow(toBeThrown, nextToBeThrown);
    }

    @Override
    public Stubber doAnswer(Answer answer) {
      return Mockito.doAnswer(answer);
    }

    @Override
    public Stubber doNothing() {
      return Mockito.doNothing();
    }

    @Override
    public Stubber doReturn(Object toBeReturned) {
      return Mockito.doReturn(toBeReturned);
    }

    @Override
    public Stubber doReturn(Object toBeReturned, Object... nextToBeReturned) {
      return Mockito.doReturn(toBeReturned, nextToBeReturned);
    }

    @Override
    public Stubber doCallRealMethod() {
      return Mockito.doCallRealMethod();
    }
  };

  private final List<Consumer<Stubber>> appliers;
  private final boolean lenient;
  private final List<UnaryOperator<Stubber>> steps = new ArrayList<>();

  private DoStubbing(List<Consumer<Stubber>> appliers, boolean lenient) {
    this.appliers = appliers;
    this.lenient = lenient;
  }

  /**
   * Returns a fresh stubbing whose behavior sequence is applied to the target through the
   * do-family. The chain starts empty; the first {@code thenXxx} call registers the first answer.
   *
   * @param applier applies a built {@link Stubber} to the target as {@code stubber.when(mock).method(matchers)}.
   * @param <T> the stubbed return type.
   * @return a stubbing over the do-family.
   */
  public static <T> DoStubbing<T> of(Consumer<Stubber> applier) {
    List<Consumer<Stubber>> appliers = new ArrayList<>();
    appliers.add(applier);
    return new DoStubbing<>(appliers, false);
  }

  /**
   * Returns a fresh stubbing that fans its behavior sequence out to every matched overload. Each
   * applier registers the shared answer chain against one overload through the do-family, so all
   * matched overloads always carry the same behavior sequence. When more than one overload is
   * matched the stubbings are registered leniently, so strict stubbing does not flag the variants
   * a test never exercises.
   *
   * @param appliers one applier per matched overload.
   * @param <T> the stubbed return type.
   * @return a stubbing over the do-family that fans out.
   */
  public static <T> DoStubbing<T> ofAll(List<Consumer<Stubber>> appliers) {
    return new DoStubbing<>(new ArrayList<>(appliers), appliers.size() > 1);
  }

  private OngoingStubbing<T> apply(UnaryOperator<Stubber> step) {
    steps.add(step);
    for (Consumer<Stubber> applier : appliers) {
      Stubber stubber = null;
      for (UnaryOperator<Stubber> current : steps) {
        stubber = current.apply(stubber);
      }
      applier.accept(stubber);
    }
    return this;
  }

  private BaseStubber first() {
    return lenient ? Mockito.lenient() : NON_LENIENT;
  }

  @Override
  public OngoingStubbing<T> thenReturn(T value) {
    return apply(stubber -> stubber == null ? first().doReturn(value) : stubber.doReturn(value));
  }

  @Override
  @SuppressWarnings("unchecked")
  public OngoingStubbing<T> thenReturn(T value, T... values) {
    return apply(stubber -> chainReturns(stubber == null ? first().doReturn(value) : stubber.doReturn(value), values));
  }

  private static Stubber chainReturns(Stubber stubber, Object[] values) {
    Stubber current = stubber;
    for (Object value : values) {
      current = current.doReturn(value);
    }
    return current;
  }

  @Override
  public OngoingStubbing<T> thenThrow(Throwable... throwables) {
    return apply(stubber -> stubber == null ? first().doThrow(throwables) : stubber.doThrow(throwables));
  }

  @Override
  public OngoingStubbing<T> thenThrow(Class<? extends Throwable> throwableType) {
    return apply(stubber -> stubber == null ? first().doThrow(throwableType) : stubber.doThrow(throwableType));
  }

  @Override
  @SuppressWarnings("unchecked")
  public OngoingStubbing<T> thenThrow(Class<? extends Throwable> throwableType,
      Class<? extends Throwable>... throwableTypes) {
    return apply(stubber -> chainThrows(
        stubber == null ? first().doThrow(throwableType) : stubber.doThrow(throwableType), throwableTypes));
  }

  private static Stubber chainThrows(Stubber stubber, Class<? extends Throwable>[] throwableTypes) {
    Stubber current = stubber;
    for (Class<? extends Throwable> throwableType : throwableTypes) {
      current = current.doThrow(throwableType);
    }
    return current;
  }

  @Override
  public OngoingStubbing<T> thenAnswer(Answer<?> answer) {
    return apply(stubber -> stubber == null ? first().doAnswer(answer) : stubber.doAnswer(answer));
  }

  @Override
  public OngoingStubbing<T> then(Answer<?> answer) {
    return thenAnswer(answer);
  }

  @Override
  public OngoingStubbing<T> thenCallRealMethod() {
    return apply(stubber -> stubber == null ? first().doCallRealMethod() : stubber.doCallRealMethod());
  }

  @Override
  public <M> M getMock() {
    throw new UnsupportedOperationException("getMock() is not supported on a do-family stubbing");
  }
}
