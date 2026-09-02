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

package io.sundr.mockito.internal.visitors;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.mockito.internal.Constants;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.model.Argument;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.VoidRef;

/**
 * Adds the stubbing side of a generated mock DSL class: the {@code Stub} router and one
 * stub builder per method name, shared by all overloads of that name.
 */
public class AddStubClasses implements Visitor<TypeDefFluent<?>> {

  private final MockTarget target;

  public AddStubClasses(MockTarget target) {
    this.target = target;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    if (!target.getMockName().equals(def.getName())) {
      return;
    }
    def.addToInnerTypes(router());
    for (Map.Entry<String, List<Method>> entry : target.getMethodsByName().entrySet()) {
      def.addToInnerTypes(stubClass(entry.getKey(), entry.getValue()));
    }
  }

  private TypeDef router() {
    TypeDefBuilder builder = DslMethods.innerClass(target, Constants.STUB_ROUTER);
    for (String name : target.getMethodsByName().keySet()) {
      ClassRef stubRef = target.nestedRef(MockTarget.stubClassName(name));
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(stubRef)
          .withName(name)
          .withNewBlock()
          .addToStatements(new Return(stubRef.construct(This.ref(Constants.MOCK))))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private TypeDef stubClass(String name, List<Method> overloads) {
    String className = MockTarget.stubClassName(name);
    ClassRef selfRef = target.nestedRef(className);
    TypeDefBuilder builder = DslMethods.innerClass(target, className);
    boolean isVoid = overloads.get(0).getReturnType() instanceof VoidRef;
    List<ClassRef> exceptions = DslMethods.unionExceptions(overloads);

    if (overloads.size() == 1) {
      Method method = overloads.get(0);
      DslMethods.addSlots(builder, method.getArguments());
      DslMethods.addWithers(builder, method.getArguments(), selfRef, false);
      if (isVoid) {
        DslMethods.addVoidTerminals(builder, (doName, doArgs) -> Collections
            .singletonList(DslMethods.doStubbing(method, Constants.MOCKITO.call(doName, doArgs))), exceptions);
      } else {
        DslMethods.addStubbingTerminals(builder, method, method.getReturnType(), exceptions);
      }
    } else {
      Collection<Argument> union = DslMethods.unionArguments(overloads);
      DslMethods.addSlots(builder, union);
      DslMethods.addOverloadSelection(builder, overloads, selfRef);
      DslMethods.addWithers(builder, union, selfRef, true);
      DslMethods.addAnyPins(builder, union, selfRef);
      if (isVoid) {
        DslMethods.addVoidTerminals(builder,
            (doName, doArgs) -> DslMethods.fanOutVoidBody(overloads, doName, doArgs), exceptions);
      } else {
        DslMethods.addFanOutStubbing(builder, overloads, overloads.get(0).getReturnType(), exceptions);
      }
    }
    return builder.build();
  }
}
