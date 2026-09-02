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

import java.util.Collections;
import java.util.List;

import io.sundr.builder.Visitor;
import io.sundr.mockito.internal.Constants;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.model.Argument;
import io.sundr.model.Assign;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Expression;
import io.sundr.model.Kind;
import io.sundr.model.LocalVariable;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.TypeParamRef;
import io.sundr.model.WildcardRef;
import io.sundr.model.utils.Types;

/**
 * Adds the members of the aggregator class: one {@code mock} overload per mockable target
 * returning its fluent handle, plus passthroughs to the most common {@code Mockito} statics
 * ({@code when}, {@code verify}, {@code verifyNoInteractions}, {@code verifyNoMoreInteractions},
 * {@code inOrder}, {@code reset}, {@code clearInvocations}, {@code timeout}, {@code after}), so a
 * single static import covers both the fluent DSL and plain Mockito across the suite.
 */
public class AddAggregatorMethods implements Visitor<TypeDefFluent<?>> {

  private final List<MockTarget> targets;
  private final String aggregatorFqcn;

  public AddAggregatorMethods(List<MockTarget> targets, String aggregatorFqcn) {
    this.targets = targets;
    this.aggregatorFqcn = aggregatorFqcn;
  }

  private ClassRef nestedRef(String simpleName) {
    return ClassRef.forName(aggregatorFqcn + "." + simpleName);
  }

  private String aggregatorSimpleName() {
    int idx = aggregatorFqcn.lastIndexOf('.');
    return idx < 0 ? aggregatorFqcn : aggregatorFqcn.substring(idx + 1);
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    if (!aggregatorSimpleName().equals(def.getName())) {
      return;
    }
    def.addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .withNewBlock().endBlock()
        .endConstructor();

    for (MockTarget target : targets) {
      ClassRef mockRef = target.getMockRef();
      ClassRef targetRef = target.getTargetRef();
      def.addNewMethod()
          .withNewModifiers().withPublic().withStatic().endModifiers()
          .withReturnType(mockRef)
          .withName(Constants.MOCK)
          .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
          .withNewBlock()
          .addToStatements(new Return(mockRef.call(Constants.MOCK,
              LocalVariable.newLocalVariable(targetRef, Constants.MOCK))))
          .endBlock()
          .endMethod();
    }

    addMockitoPassthroughs(def);
    addDoFamily(def);
  }

  /**
   * Adds the aggregator-level answer-first do-family: each static {@code doX(...)} returns the
   * aggregator's own {@code DoStubber}, whose {@code when(mock)} has one overload per mockable
   * type delegating to {@code XxxMock.doStub(stubber, mock)} — mirroring how {@code mock(T)}
   * delegates to {@code XxxMock.mock(T)}.
   */
  private void addDoFamily(TypeDefFluent<?> def) {
    def.addToInnerTypes(doStubber());

    ClassRef doStubberRef = nestedRef(Constants.DO_STUBBER);

    LocalVariable value = LocalVariable.newLocalVariable(Constants.OBJECT, "value");
    addDoFactory(def, doStubberRef, "doReturn", Constants.OBJECT, "value", false,
        Constants.MOCKITO.call("doReturn", value));

    ClassRef throwableArray = new ClassRefBuilder(Constants.THROWABLE).withDimensions(1).build();
    LocalVariable throwables = LocalVariable.newLocalVariable(throwableArray, "throwables");
    addDoFactory(def, doStubberRef, "doThrow", throwableArray, "throwables", true,
        Constants.MOCKITO.call("doThrow", throwables));

    ClassRef throwableType = new ClassRefBuilder(Constants.CLASS)
        .withArguments(new WildcardRef(WildcardRef.BoundKind.EXTENDS,
            Collections.singletonList(Constants.THROWABLE), Collections.emptyMap()))
        .build();
    LocalVariable type = LocalVariable.newLocalVariable(throwableType, "throwableType");
    addDoFactory(def, doStubberRef, "doThrow", throwableType, "throwableType", false,
        Constants.MOCKITO.call("doThrow", type));

    addDoFactory(def, doStubberRef, "doNothing", null, null, false,
        Constants.MOCKITO.call("doNothing"));

    ClassRef answerRef = new ClassRefBuilder(Constants.ANSWER).withArguments(new WildcardRef()).build();
    LocalVariable answer = LocalVariable.newLocalVariable(answerRef, "answer");
    addDoFactory(def, doStubberRef, "doAnswer", answerRef, "answer", false,
        Constants.MOCKITO.call("doAnswer", answer));

    addDoFactory(def, doStubberRef, "doCallRealMethod", null, null, false,
        Constants.MOCKITO.call("doCallRealMethod"));
  }

  private void addDoFactory(TypeDefFluent<?> def, ClassRef doStubberRef, String name,
      ClassRef argumentType, String argumentName, boolean varArg, Expression stubberCall) {
    List<Argument> arguments = argumentType == null
        ? Collections.emptyList()
        : Collections.singletonList(Argument.newArgument(argumentType, argumentName));
    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(doStubberRef)
        .withName(name)
        .withArguments(arguments)
        .withVarArgPreferred(varArg)
        .withNewBlock()
        .addToStatements(new Return(doStubberRef.construct(stubberCall)))
        .endBlock()
        .endMethod();
  }

  private TypeDef doStubber() {
    ClassRef stubberType = Constants.STUBBER_TYPE;
    LocalVariable stubber = LocalVariable.newLocalVariable(stubberType, Constants.STUBBER);
    TypeDefBuilder builder = new TypeDefBuilder()
        .withName(Constants.DO_STUBBER)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withStatic().withFinal().endModifiers()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(stubberType).withName(Constants.STUBBER)
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(stubberType).withName(Constants.STUBBER).endArgument()
        .withNewBlock()
        .addToStatements(new Assign(This.ref(Constants.STUBBER), stubber))
        .endBlock()
        .endConstructor();

    for (MockTarget target : targets) {
      ClassRef targetRef = target.getTargetRef();
      ClassRef routerRef = target.nestedRef(target.doRouterName());
      ClassRef mockRef = target.getMockRef();
      LocalVariable mock = LocalVariable.newLocalVariable(targetRef, Constants.MOCK);
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(routerRef)
          .withName("when")
          .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
          .withNewBlock()
          .addToStatements(new Return(mockRef.call("doStub", This.ref(Constants.STUBBER), mock)))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private void addMockitoPassthroughs(TypeDefFluent<?> def) {
    TypeParamRef t = Types.T.toReference();
    ClassRef ongoingRef = new ClassRefBuilder(Constants.ONGOING_STUBBING).withArguments(t).build();
    LocalVariable call = LocalVariable.newLocalVariable(t, "methodCall");
    LocalVariable mock = LocalVariable.newLocalVariable(t, Constants.MOCK);
    LocalVariable mode = LocalVariable.newLocalVariable(Constants.VERIFICATION_MODE, "mode");

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withParameters(Types.T)
        .withReturnType(ongoingRef)
        .withName("when")
        .addNewArgument().withTypeRef(t).withName("methodCall").endArgument()
        .withNewBlock()
        .addToStatements(new Return(Constants.MOCKITO.call("when", call)))
        .endBlock()
        .endMethod();

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withParameters(Types.T)
        .withReturnType(t)
        .withName("verify")
        .addNewArgument().withTypeRef(t).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Return(Constants.MOCKITO.call("verify", mock)))
        .endBlock()
        .endMethod();

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withParameters(Types.T)
        .withReturnType(t)
        .withName("verify")
        .addNewArgument().withTypeRef(t).withName(Constants.MOCK).endArgument()
        .addNewArgument().withTypeRef(Constants.VERIFICATION_MODE).withName("mode").endArgument()
        .withNewBlock()
        .addToStatements(new Return(Constants.MOCKITO.call("verify", mock, mode)))
        .endBlock()
        .endMethod();

    ClassRef objectArray = new ClassRefBuilder(Constants.OBJECT).withDimensions(1).build();
    LocalVariable mocks = LocalVariable.newLocalVariable(objectArray, "mocks");

    addVoidObjectVarArg(def, "verifyNoInteractions", mocks);
    addVoidObjectVarArg(def, "verifyNoMoreInteractions", mocks);

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(Constants.IN_ORDER)
        .withName("inOrder")
        .addNewArgument().withTypeRef(objectArray).withName("mocks").endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addToStatements(new Return(Constants.MOCKITO.call("inOrder", mocks)))
        .endBlock()
        .endMethod();

    TypeParamRef typeArray = t.withDimensions(1);
    addGenericVarArg(def, "reset", typeArray);
    addGenericVarArg(def, "clearInvocations", typeArray);

    addLongToVerificationMode(def, "timeout", Constants.VERIFICATION_WITH_TIMEOUT);
    addLongToVerificationMode(def, "after", Constants.VERIFICATION_AFTER_DELAY);
  }

  private void addVoidObjectVarArg(TypeDefFluent<?> def, String name, LocalVariable mocks) {
    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(Types.VOID)
        .withName(name)
        .addNewArgument().withTypeRef(mocks.getTypeRef()).withName("mocks").endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addToStatements(Constants.MOCKITO.call(name, mocks))
        .endBlock()
        .endMethod();
  }

  private void addGenericVarArg(TypeDefFluent<?> def, String name, TypeParamRef typeArray) {
    LocalVariable mocks = LocalVariable.newLocalVariable(typeArray, "mocks");
    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withParameters(Types.T)
        .withReturnType(Types.VOID)
        .withName(name)
        .addNewArgument().withTypeRef(typeArray).withName("mocks").endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addToStatements(Constants.MOCKITO.call(name, mocks))
        .endBlock()
        .endMethod();
  }

  private void addLongToVerificationMode(TypeDefFluent<?> def, String name, ClassRef returnType) {
    LocalVariable millis = LocalVariable.newLocalVariable(Types.PRIMITIVE_LONG_REF, "millis");
    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(returnType)
        .withName(name)
        .addNewArgument().withTypeRef(Types.PRIMITIVE_LONG_REF).withName("millis").endArgument()
        .withNewBlock()
        .addToStatements(new Return(Constants.MOCKITO.call(name, millis)))
        .endBlock()
        .endMethod();
  }
}
