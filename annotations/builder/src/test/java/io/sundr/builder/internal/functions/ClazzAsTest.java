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

package io.sundr.builder.internal.functions;

import org.junit.Test;

import io.sundr.builder.Constants;
import io.sundr.builder.internal.processor.AbstractProcessorTest;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.utils.TypeArguments;

public class ClazzAsTest extends AbstractProcessorTest {

  @Test
  public void testToFluent() {
    TypeDef type = new TypeDefBuilder()
        .withName("MyClass")
        .withPackageName(getClass().getPackage().getName())
        .withParameters()
        .build();

    Method constructor = new MethodBuilder()
        .withReturnType(type.toReference())
        .withAnnotations(Constants.BUILDABLE_ANNOTATION)
        .build();

    type = new TypeDefBuilder(type)
        .withConstructors(constructor)
        .build();

    TypeDef result = ClazzAs.FLUENT_IMPL.apply(TypeArguments.apply(type));
    System.out.println(result);
  }
}
