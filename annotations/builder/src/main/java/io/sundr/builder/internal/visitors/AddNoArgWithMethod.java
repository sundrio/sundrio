/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.builder.internal.visitors;

import java.util.List;
import java.util.stream.Collectors;

import io.sundr.builder.Visitor;
import io.sundr.model.Method;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.utils.Types;

public class AddNoArgWithMethod implements Visitor<TypeDefFluent<?>> {

  @Override
  public void visit(TypeDefFluent<?> type) {
    List<Method> withBooleanMethods = type.buildMethods().stream()
        //Only focus on methods that start `with` but not `withNew` as it can cause conflicts with nested buildables that are inlinable using a single boolean argument
        .filter(m -> m.getName().startsWith("with") && !m.getName().startsWith("withNew"))
        .filter(m -> m.getArguments().size() == 1)
        .filter(m -> m.getArguments().get(0).getTypeRef().equals(Types.BOOLEAN_REF)
            || m.getArguments().get(0).getTypeRef().equals(Types.PRIMITIVE_BOOLEAN_REF))
        .collect(Collectors.toList());

    for (Method withBooleanMethod : withBooleanMethods) {
      type.addNewMethodLike(withBooleanMethod)
          .withArguments()
          .withNewBlock()
          .addNewStringStatementStatement("return " + withBooleanMethod.getName() + "(true);")
          .endBlock()
          .endMethod();
    }
  }
}
