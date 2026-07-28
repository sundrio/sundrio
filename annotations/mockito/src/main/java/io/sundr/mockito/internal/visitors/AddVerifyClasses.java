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
import io.sundr.model.Construct;
import io.sundr.model.Method;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeDefFluent;

/**
 * Adds the verification side of a generated mock DSL class: the {@code Verify} router and
 * one verify builder per method name. Positive verifications target one concrete
 * signature, {@code never()} fans out to every overload matching the pinned arguments.
 */
public class AddVerifyClasses implements Visitor<TypeDefFluent<?>> {

  private final MockTarget target;

  public AddVerifyClasses(MockTarget target) {
    this.target = target;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    if (!target.isVerificationEnabled() || !target.getMockName().equals(def.getName())) {
      return;
    }
    def.addToInnerTypes(router());
    for (Map.Entry<String, List<Method>> entry : target.getMethodsByName().entrySet()) {
      def.addToInnerTypes(verifyClass(entry.getKey(), entry.getValue()));
    }
  }

  private TypeDef router() {
    TypeDefBuilder builder = DslMethods.innerClass(target, Constants.VERIFY_ROUTER);
    for (String name : target.getMethodsByName().keySet()) {
      ClassRef verifyRef = target.nestedRef(MockTarget.verifyClassName(name));
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(verifyRef)
          .withName(name)
          .withNewBlock()
          .addToStatements(new Return(new Construct(verifyRef, This.ref(Constants.MOCK))))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private TypeDef verifyClass(String name, List<Method> overloads) {
    String className = MockTarget.verifyClassName(name);
    ClassRef selfRef = target.nestedRef(className);
    TypeDefBuilder builder = DslMethods.innerClass(target, className);
    List<ClassRef> exceptions = DslMethods.unionExceptions(overloads);

    if (overloads.size() == 1) {
      Method method = overloads.get(0);
      DslMethods.addSlots(builder, method.getArguments());
      DslMethods.addWithers(builder, method.getArguments(), selfRef, false);
      DslMethods.addCapturing(builder, method.getArguments(), selfRef, false);
      DslMethods.addVerifyTerminals(builder,
          mode -> Collections.singletonList(DslMethods.verification(method, mode)),
          Collections.singletonList(new This().call("verified", Constants.MOCKITO.call("never"))), exceptions);
    } else {
      Collection<Argument> union = DslMethods.unionArguments(overloads);
      DslMethods.addSlots(builder, union);
      DslMethods.addOverloadSelection(builder, overloads, selfRef);
      DslMethods.addWithers(builder, union, selfRef, true);
      DslMethods.addAnyPins(builder, union, selfRef);
      DslMethods.addCapturing(builder, union, selfRef, true);
      java.util.Set<String> widened = DslMethods.widenedArgumentNames(overloads);
      DslMethods.addVerifyTerminals(builder,
          mode -> DslMethods.selectOneDispatch(overloads, method -> DslMethods.verification(method, mode, widened)),
          DslMethods.fanOutNeverBody(overloads), exceptions);
    }
    return builder.build();
  }
}
