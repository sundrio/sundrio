/*
 * Copyright 2016 The original authors.
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

package io.sundr.dsl.internal.visitors;

import java.util.Collection;

import io.sundr.builder.TypedVisitor;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;

public class TypeParamDefColletor extends TypedVisitor<TypeDefBuilder> {

  private final Collection<TypeParamDef> collection;

  public TypeParamDefColletor(Collection<TypeParamDef> collection) {
    this.collection = collection;
  }

  public void visit(TypeDefBuilder builder) {
    collection.addAll(builder.buildParameters());
  }
}
