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

import java.util.List;

import io.sundr.builder.Visitor;
import io.sundr.mockito.internal.Constants;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.model.ClassRef;
import io.sundr.model.LocalVariable;
import io.sundr.model.Return;
import io.sundr.model.TypeDefFluent;

/**
 * Adds the members of the aggregator class: one {@code stub} overload per mockable
 * target, so a single static import covers every mock of the suite.
 */
public class AddAggregatorMethods implements Visitor<TypeDefFluent<?>> {

  private final List<MockTarget> targets;

  public AddAggregatorMethods(List<MockTarget> targets) {
    this.targets = targets;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
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
          .withName("stub")
          .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
          .withNewBlock()
          .addToStatements(new Return(mockRef.call("stub",
              LocalVariable.newLocalVariable(targetRef, Constants.MOCK))))
          .endBlock()
          .endMethod();
    }
  }
}
