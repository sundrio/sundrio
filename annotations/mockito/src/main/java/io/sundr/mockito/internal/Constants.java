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

package io.sundr.mockito.internal;

import java.util.HashMap;
import java.util.Map;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.TypeRef;

public final class Constants {

  public static final String MOCK = "mock";
  public static final String PINNED = "pinned";
  public static final String SELECTOR = "selector";
  public static final String EXACT = "exact";
  public static final String STUB_ROUTER = "Stub";
  public static final String VERIFY_ROUTER = "Verify";
  public static final String DO_ROUTER = "DoStub";
  public static final String DO_STUBBER = "DoStubber";
  public static final String STUBBER = "stubber";

  public static final ClassRef MOCKITO = ClassRef.forName("org.mockito.Mockito");
  public static final ClassRef ARGUMENT_MATCHER = ClassRef.forName("org.mockito.ArgumentMatcher");
  public static final ClassRef ARGUMENT_CAPTOR = ClassRef.forName("org.mockito.ArgumentCaptor");
  public static final ClassRef ONGOING_STUBBING = ClassRef.forName("org.mockito.stubbing.OngoingStubbing");
  public static final ClassRef ANSWER = ClassRef.forName("org.mockito.stubbing.Answer");
  public static final ClassRef STUBBER_TYPE = ClassRef.forName("org.mockito.stubbing.Stubber");
  public static final ClassRef VERIFICATION_MODE = ClassRef.forName("org.mockito.verification.VerificationMode");
  public static final ClassRef VERIFICATION_WITH_TIMEOUT = ClassRef
      .forName("org.mockito.verification.VerificationWithTimeout");
  public static final ClassRef VERIFICATION_AFTER_DELAY = ClassRef
      .forName("org.mockito.verification.VerificationAfterDelay");
  public static final ClassRef IN_ORDER = ClassRef.forName("org.mockito.InOrder");
  public static final ClassRef OBJECT = ClassRef.forName("java.lang.Object");
  public static final ClassRef OBJECT_ARRAY = ClassRef.forName("java.lang.Object").withDimensions(1);
  public static final ClassRef ARRAYS = ClassRef.forName("java.util.Arrays");
  public static final ClassRef THROWABLE = ClassRef.forName("java.lang.Throwable");
  public static final ClassRef RUNTIME_EXCEPTION = ClassRef.forName("java.lang.RuntimeException");
  public static final ClassRef CLASS = ClassRef.forName("java.lang.Class");
  public static final ClassRef BOXED_VOID = ClassRef.forName("java.lang.Void");
  public static final ClassRef STRING = ClassRef.forName("java.lang.String");
  public static final ClassRef INTEGER = ClassRef.forName("java.lang.Integer");
  public static final ClassRef SET = ClassRef.forName("java.util.Set");
  public static final ClassRef LINKED_HASH_SET = ClassRef.forName("java.util.LinkedHashSet");
  public static final ClassRef LIST = ClassRef.forName("java.util.List");
  public static final ClassRef ARRAY_LIST = ClassRef.forName("java.util.ArrayList");
  public static final ClassRef SUPPLIER = ClassRef.forName("java.util.function.Supplier");
  public static final ClassRef CONSUMER = ClassRef.forName("java.util.function.Consumer");
  public static final ClassRef ARG = ClassRef.forName("io.sundr.mockito.Arg");
  public static final ClassRef OVERLOAD_SELECTOR = ClassRef.forName("io.sundr.mockito.OverloadSelector");
  public static final ClassRef FAN_OUT_STUBBING = ClassRef.forName("io.sundr.mockito.FanOutStubbing");
  public static final ClassRef DO_STUBBING = ClassRef.forName("io.sundr.mockito.DoStubbing");

  private static final Map<String, ClassRef> PRIMITIVE_ARGS = new HashMap<>();

  static {
    PRIMITIVE_ARGS.put("boolean", ClassRef.forName("io.sundr.mockito.BooleanArg"));
    PRIMITIVE_ARGS.put("int", ClassRef.forName("io.sundr.mockito.IntArg"));
    PRIMITIVE_ARGS.put("long", ClassRef.forName("io.sundr.mockito.LongArg"));
    PRIMITIVE_ARGS.put("double", ClassRef.forName("io.sundr.mockito.DoubleArg"));
    PRIMITIVE_ARGS.put("float", ClassRef.forName("io.sundr.mockito.FloatArg"));
    PRIMITIVE_ARGS.put("short", ClassRef.forName("io.sundr.mockito.ShortArg"));
    PRIMITIVE_ARGS.put("byte", ClassRef.forName("io.sundr.mockito.ByteArg"));
    PRIMITIVE_ARGS.put("char", ClassRef.forName("io.sundr.mockito.CharArg"));
  }

  private Constants() {
  }

  /**
   * Returns the slot type used to hold a matcher for an argument of the given type.
   *
   * @param argumentType the mocked method's argument type.
   * @return a primitive slot ref for primitives, a parameterized {@code Arg} ref otherwise.
   */
  public static ClassRef slotFor(TypeRef argumentType) {
    if (argumentType instanceof PrimitiveRef) {
      ClassRef primitiveSlot = PRIMITIVE_ARGS.get(((PrimitiveRef) argumentType).getName());
      if (primitiveSlot != null) {
        return primitiveSlot;
      }
    }
    return new ClassRefBuilder(ARG).withArguments(argumentType).build();
  }
}
