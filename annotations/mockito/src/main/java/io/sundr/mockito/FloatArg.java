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

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;

/**
 * An {@code Arg} variant for {@code float} parameters, using primitive-safe matchers.
 */
public final class FloatArg {

  private ArgKind kind = ArgKind.ANY;
  private float value;
  private ArgumentMatcher<Float> matcher;
  private ArgumentCaptor<Float> captor;

  /**
   * Pins the slot to an exact value.
   *
   * @param value the expected value.
   */
  public void eq(float value) {
    this.kind = ArgKind.EQ;
    this.value = value;
  }

  /**
   * Pins the slot to a custom matcher.
   *
   * @param matcher the matcher the argument must satisfy.
   */
  public void matching(ArgumentMatcher<Float> matcher) {
    this.kind = ArgKind.MATCHING;
    this.matcher = matcher;
  }

  /**
   * Pins the slot to an argument captor.
   *
   * @param captor the captor that receives the actual argument.
   */
  public void capturing(ArgumentCaptor<Float> captor) {
    this.kind = ArgKind.CAPTURING;
    this.captor = captor;
  }

  /**
   * Registers the Mockito matcher this slot represents.
   *
   * @return the value Mockito associates with the registered matcher.
   */
  public float resolve() {
    switch (kind) {
      case EQ:
        return ArgumentMatchers.eq(value);
      case MATCHING:
        return ArgumentMatchers.floatThat(matcher);
      case CAPTURING:
        return captor.capture();
      default:
        return ArgumentMatchers.anyFloat();
    }
  }

  /**
   * Tests whether a recorded invocation argument satisfies this slot's pin, used to select the
   * invoked overload rather than to register a Mockito matcher.
   *
   * @param actual the recorded argument value.
   * @return true when the slot is unpinned or the actual value satisfies the pin.
   */
  public boolean matches(Object actual) {
    switch (kind) {
      case EQ:
        return java.util.Objects.equals(value, actual);
      case MATCHING:
        try {
          return matcher.matches((Float) actual);
        } catch (ClassCastException e) {
          return false;
        }
      default:
        return true;
    }
  }
}
