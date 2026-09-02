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
import java.util.List;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.mockito.internal.Constants;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.model.Argument;
import io.sundr.model.ClassRef;
import io.sundr.model.LocalVariable;
import io.sundr.model.Method;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeDefFluent;

/**
 * Adds the answer-first do-family side of a generated mock DSL class: the {@code DoStubber}
 * carrying a Mockito {@code Stubber}, the {@code DoStub} router and one answer-first builder per
 * method name. The value/answer is supplied up front to {@code doReturn/doThrow/...}, so the
 * builder's {@code done()} terminal only replays {@code stubber.when(mock).method(matchers)},
 * which is spy- and void-safe because it never invokes the real method during stubbing.
 */
public class AddDoStubClasses implements Visitor<TypeDefFluent<?>> {

  private final MockTarget target;

  public AddDoStubClasses(MockTarget target) {
    this.target = target;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    if (!target.getMockName().equals(def.getName())) {
      return;
    }
    def.addToInnerTypes(doStubber());
    def.addToInnerTypes(router());
    for (Map.Entry<String, List<Method>> entry : target.getMethodsByName().entrySet()) {
      def.addToInnerTypes(doStubClass(entry.getKey(), entry.getValue()));
    }
    addDoStubFactory(def);
  }

  /**
   * A package-crossing bridge for the aggregator: it hands a pre-built stubber and a mock to
   * this type's answer-first router, mirroring how the aggregator delegates {@code mock(T)} to
   * {@code XxxMock.mock(T)}.
   */
  private void addDoStubFactory(TypeDefFluent<?> def) {
    ClassRef targetRef = target.getTargetRef();
    ClassRef routerRef = target.nestedRef(target.doRouterName());
    LocalVariable stubber = LocalVariable.newLocalVariable(Constants.STUBBER_TYPE, Constants.STUBBER);
    LocalVariable mock = LocalVariable.newLocalVariable(targetRef, Constants.MOCK);
    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(routerRef)
        .withName("doStub")
        .addNewArgument().withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER).endArgument()
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Return(routerRef.construct(stubber, mock)))
        .endBlock()
        .endMethod();
  }

  private TypeDef doStubber() {
    ClassRef targetRef = target.getTargetRef();
    ClassRef routerRef = target.nestedRef(target.doRouterName());
    LocalVariable stubber = LocalVariable.newLocalVariable(Constants.STUBBER_TYPE, Constants.STUBBER);
    LocalVariable mock = LocalVariable.newLocalVariable(targetRef, Constants.MOCK);
    return new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(target.doStubberName())
        .withKind(io.sundr.model.Kind.CLASS)
        .withNewModifiers().withPublic().withStatic().withFinal().endModifiers()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER)
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER).endArgument()
        .withNewBlock()
        .addToStatements(new io.sundr.model.Assign(This.ref(Constants.STUBBER), stubber))
        .endBlock()
        .endConstructor()
        .addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(routerRef)
        .withName("when")
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Return(routerRef.construct(This.ref(Constants.STUBBER), mock)))
        .endBlock()
        .endMethod()
        .build();
  }

  private TypeDef router() {
    ClassRef targetRef = target.getTargetRef();
    TypeDefBuilder builder = new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(target.doRouterName())
        .withKind(io.sundr.model.Kind.CLASS)
        .withNewModifiers().withPublic().withStatic().withFinal().endModifiers()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER)
        .endField()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetRef).withName(Constants.MOCK)
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER).endArgument()
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new io.sundr.model.Assign(This.ref(Constants.STUBBER),
            LocalVariable.newLocalVariable(Constants.STUBBER_TYPE, Constants.STUBBER)))
        .addToStatements(new io.sundr.model.Assign(This.ref(Constants.MOCK),
            LocalVariable.newLocalVariable(targetRef, Constants.MOCK)))
        .endBlock()
        .endConstructor();

    for (String name : target.getMethodsByName().keySet()) {
      ClassRef doStubRef = target.nestedRef(MockTarget.doStubClassName(name));
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(doStubRef)
          .withName(name)
          .withNewBlock()
          .addToStatements(new Return(doStubRef.construct(This.ref(Constants.STUBBER), This.ref(Constants.MOCK))))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private TypeDef doStubClass(String name, List<Method> overloads) {
    String className = MockTarget.doStubClassName(name);
    ClassRef selfRef = target.nestedRef(className);
    TypeDefBuilder builder = DslMethods.doInnerClass(target, className);
    List<ClassRef> exceptions = DslMethods.unionExceptions(overloads);

    if (overloads.size() == 1) {
      Method method = overloads.get(0);
      DslMethods.addSlots(builder, method.getArguments());
      DslMethods.addWithers(builder, method.getArguments(), selfRef, false);
      DslMethods.addDoTerminal(builder, method, exceptions);
    } else {
      Collection<Argument> union = DslMethods.unionArguments(overloads);
      DslMethods.addSlots(builder, union);
      DslMethods.addOverloadSelection(builder, overloads, selfRef);
      DslMethods.addWithers(builder, union, selfRef, true);
      DslMethods.addAnyPins(builder, union, selfRef);
      DslMethods.addFanOutDoTerminal(builder, overloads, exceptions);
    }
    return builder.build();
  }
}
