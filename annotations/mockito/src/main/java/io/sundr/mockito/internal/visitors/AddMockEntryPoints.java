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
import io.sundr.model.Construct;
import io.sundr.model.LocalVariable;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.TypeDefFluent;

/**
 * Adds the entry points of a generated mock DSL class: the mock field, the private
 * constructor, the static {@code mock()} and {@code stub(mock)} factories and the
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
        .withName("stub")
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
  }
}
