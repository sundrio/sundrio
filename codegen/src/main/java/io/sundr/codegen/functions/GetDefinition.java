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

package io.sundr.codegen.functions;

import static io.sundr.codegen.utils.Predicates.after;
import static io.sundr.codegen.utils.Predicates.until;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;

public class GetDefinition implements Function<ClassRef, TypeDef> {

  public static GetDefinition FUNCTION = new GetDefinition();

  public static TypeDef of(ClassRef classRef) {
    return FUNCTION.apply(classRef);
  }

  @Override
  public TypeDef apply(ClassRef t) {
    String fullyQualifiedName = t.getFullyQualifiedName();
    TypeDef def = DefinitionRepository.getRepository().getDefinition(fullyQualifiedName);
    if (def != null) {
      return def;
    }
    Predicate<String> isLowerCase = w -> Character.isUpperCase(w.charAt(0));
    Predicate<String> inPackage = until(isLowerCase);
    Predicate<String> outOfPackage = after(isLowerCase);

    String packageName = Arrays.stream(fullyQualifiedName.split("\\.")).filter(inPackage).collect(Collectors.joining("."));
    String className = Arrays.stream(fullyQualifiedName.split("\\.")).filter(outOfPackage).collect(Collectors.joining("."));

    String ownerClassName = className.contains(".") ? className.substring(0, className.indexOf(".")) : null;

    if (ownerClassName != null) {
      className = className.substring(ownerClassName.length() + 1);
      return new TypeDefBuilder()
          .withName(className)
          .withPackageName(packageName)
          .withOuterTypeName(packageName + "." + ownerClassName)
          .build();
    }

    return new TypeDefBuilder()
        .withName(className)
        .withPackageName(packageName)
        .build();
  }
}
