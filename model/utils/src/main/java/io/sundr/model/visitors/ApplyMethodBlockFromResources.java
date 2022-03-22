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

package io.sundr.model.visitors;

import io.sundr.SundrException;
import io.sundr.builder.Visitor;
import io.sundr.model.MethodFluent;
import io.sundr.model.utils.Parsers;
import io.sundr.utils.Strings;

public class ApplyMethodBlockFromResources implements Visitor<MethodFluent<?>> {

  private final String name;
  private final String content;
  private final boolean isInterface;

  public ApplyMethodBlockFromResources(String name, String resourceName) {
    this(name, resourceName, false);
  }

  public ApplyMethodBlockFromResources(String name, String resourceName, boolean isInterface) {
    this.name = name;
    this.content = Strings.loadResourceQuietly(resourceName);
    this.isInterface = isInterface;
  }

  @Override
  public void visit(MethodFluent<?> method) {
    try {
      String nameOrType = Strings.isNotNullOrEmpty(method.getName()) ? method.getName() : name;
      method.withNewBlock()
          .addNewStringStatementStatement(Parsers.parseMethodBody(content, nameOrType, method.buildArguments()))
          .endBlock();
    } catch (Exception e) {
      if (!isInterface || method.isDefaultMethod()) {
        throw SundrException.launderThrowable(e);
      }
    }
  }
}
