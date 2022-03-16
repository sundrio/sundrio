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

package io.sundr.model.functions;

import java.util.function.Function;

import io.sundr.model.ClassRef;
import io.sundr.model.Nameable;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.repo.DefinitionRepository;

public class GetDefinition implements Function<ClassRef, TypeDef> {

  public static GetDefinition FUNCTION = new GetDefinition();

  public static TypeDef of(ClassRef classRef) {
    return FUNCTION.apply(classRef);
  }

  public static TypeDef of(String fqcn) {
    return FUNCTION.apply(ClassRef.forName(fqcn));
  }

  @Override
  public TypeDef apply(ClassRef t) {
    String fullyQualifiedName = t.getFullyQualifiedName();
    TypeDef def = DefinitionRepository.getRepository().getDefinition(fullyQualifiedName);
    if (def != null) {
      return def;
    }

    String packageName = Nameable.getPackageName(fullyQualifiedName);
    String className = Nameable.getClassName(fullyQualifiedName);
    String outerTypeName = Nameable.getOuterTypeName(fullyQualifiedName);

    return new TypeDefBuilder()
        .withName(className)
        .withPackageName(packageName)
        .withOuterTypeName(outerTypeName)
        .build();
  }

}
