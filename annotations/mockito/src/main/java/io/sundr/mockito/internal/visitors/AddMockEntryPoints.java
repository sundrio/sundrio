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

import io.sundr.builder.Visitor;
import io.sundr.mockito.internal.Constants;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.model.Assign;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Construct;
import io.sundr.model.LocalVariable;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.WildcardRef;

/**
 * Adds the entry points of a generated mock DSL class: the mock field, the private
 * constructor, the static {@code mock()} and {@code mock(mock)} factories and the
 * {@code when()} / {@code verify()} mode selectors.
 */
public class AddMockEntryPoints implements Visitor<TypeDefFluent<?>> {

  private final MockTarget target;

  public AddMockEntryPoints(MockTarget target) {
    this.target = target;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    if (!target.getMockName().equals(def.getName())) {
      return;
    }
    ClassRef targetRef = target.getTargetRef();
    ClassRef mockRef = target.getMockRef();
    LocalVariable mockVar = LocalVariable.newLocalVariable(targetRef, Constants.MOCK);

    def.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetRef).withName(Constants.MOCK)
        .endField();

    def.addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock().addToStatements(new Assign(This.ref(Constants.MOCK), mockVar)).endBlock()
        .endConstructor();

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(targetRef)
        .withName(Constants.MOCK)
        .withNewBlock()
        .addToStatements(new Return(Constants.MOCKITO.call(Constants.MOCK, targetRef)))
        .endBlock()
        .endMethod();

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(mockRef)
        .withName(Constants.MOCK)
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Return(new Construct(mockRef, mockVar)))
        .endBlock()
        .endMethod();

    ClassRef stubRouterRef = target.nestedRef(Constants.STUB_ROUTER);
    def.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(stubRouterRef)
        .withName("when")
        .withNewBlock()
        .addToStatements(new Return(new Construct(stubRouterRef, This.ref(Constants.MOCK))))
        .endBlock()
        .endMethod();

    if (target.isVerificationEnabled()) {
      ClassRef verifyRouterRef = target.nestedRef(Constants.VERIFY_ROUTER);
      def.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(verifyRouterRef)
          .withName("verify")
          .withNewBlock()
          .addToStatements(new Return(new Construct(verifyRouterRef, This.ref(Constants.MOCK))))
          .endBlock()
          .endMethod();
    }

    addDoFamily(def);
  }

  /**
   * Adds the answer-first do-family entry points: each static {@code doX(...)} returns a
   * {@code DoStubber} wrapping the matching Mockito stubber, whose {@code when(mock)} opens the
   * answer-first router. The value/answer is supplied here, so the chain closes with {@code done()}.
   */
  private void addDoFamily(TypeDefFluent<?> def) {
    ClassRef doStubberRef = target.nestedRef(target.doStubberName());

    LocalVariable value = LocalVariable.newLocalVariable(Constants.OBJECT, "value");
    addDoFactory(def, doStubberRef, "doReturn", Constants.OBJECT, "value", false,
        Constants.MOCKITO.call("doReturn", value));

    ClassRef throwableArray = new ClassRefBuilder(Constants.THROWABLE).withDimensions(1).build();
    LocalVariable throwables = LocalVariable.newLocalVariable(throwableArray, "throwables");
    addDoFactory(def, doStubberRef, "doThrow", throwableArray, "throwables", true,
        Constants.MOCKITO.call("doThrow", throwables));

    ClassRef throwableType = new ClassRefBuilder(Constants.CLASS)
        .withArguments(new WildcardRef(WildcardRef.BoundKind.EXTENDS,
            java.util.Collections.singletonList(Constants.THROWABLE), java.util.Collections.emptyMap()))
        .build();
    LocalVariable type = LocalVariable.newLocalVariable(throwableType, "throwableType");
    addDoFactory(def, doStubberRef, "doThrow", throwableType, "throwableType", false,
        Constants.MOCKITO.call("doThrow", type));

    addDoFactory(def, doStubberRef, "doNothing", null, null, false,
        Constants.MOCKITO.call("doNothing"));

    ClassRef answerRef = new ClassRefBuilder(Constants.ANSWER)
        .withArguments(new WildcardRef()).build();
    LocalVariable answer = LocalVariable.newLocalVariable(answerRef, "answer");
    addDoFactory(def, doStubberRef, "doAnswer", answerRef, "answer", false,
        Constants.MOCKITO.call("doAnswer", answer));

    addDoFactory(def, doStubberRef, "doCallRealMethod", null, null, false,
        Constants.MOCKITO.call("doCallRealMethod"));
  }

  private void addDoFactory(TypeDefFluent<?> def, ClassRef doStubberRef, String name,
      ClassRef argumentType, String argumentName, boolean varArg, io.sundr.model.Expression stubberCall) {
    java.util.List<io.sundr.model.Argument> arguments = argumentType == null
        ? java.util.Collections.emptyList()
        : java.util.Collections.singletonList(io.sundr.model.Argument.newArgument(argumentType, argumentName));
    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(doStubberRef)
        .withName(name)
        .withArguments(arguments)
        .withVarArgPreferred(varArg)
        .withNewBlock()
        .addToStatements(new Return(new Construct(doStubberRef, stubberCall)))
        .endBlock()
        .endMethod();
  }
}
