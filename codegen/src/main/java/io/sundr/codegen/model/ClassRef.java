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

package io.sundr.codegen.model;

import static io.sundr.codegen.utils.Predicates.after;
import static io.sundr.codegen.utils.Predicates.until;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.utils.StringUtils;

public class ClassRef extends TypeRef implements Nameable {

  public static final String UNKNOWN = "<unknown>";
  public static final String BRACKETS = "[]";

  public static final ClassRef OBJECT = new ClassRefBuilder()
      .withFullyQualifiedName("java.lang.Object")
      .build();

  private final String fullyQualifiedName;
  private final int dimensions;
  private final List<TypeRef> arguments;

  public ClassRef(String fullyQualifiedName, int dimensions, List<TypeRef> arguments,
      Map<AttributeKey, Object> attributes) {
    super(attributes);
    this.dimensions = dimensions;
    this.arguments = arguments;
    this.fullyQualifiedName = fullyQualifiedName;
  }

  public TypeDef getDefinition() {
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

  public String getFullyQualifiedName() {
    return fullyQualifiedName;
  }

  public int getDimensions() {
    return dimensions;
  }

  public List<TypeRef> getArguments() {
    return arguments;
  }

  public ClassRef withDimensions(int dimensions) {
    return new ClassRefBuilder(this).withDimensions(dimensions).build();
  }

  public boolean isAssignableFrom(TypeRef other) {
    if (this == other) {
      return true;
    } else if (other == null) {
      return false;
    } else if (other instanceof PrimitiveRef) {
      if (getDefinition() == null) {
        return false;
      }

      if (getDefinition() != null && !JAVA_LANG.equals(getDefinition().getPackageName())) {
        return false;
      }

      if (!getDefinition().getName().toUpperCase().startsWith(((PrimitiveRef) other).getName().toUpperCase())) {
        return false;
      }
      return true;
    }

    if (!(other instanceof ClassRef)) {
      return false;
    }

    if (this == other || this.equals(other)) {
      return true;
    }

    return getDefinition().isAssignableFrom(((ClassRef) other).getDefinition());
  }

  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new LinkedHashSet<ClassRef>();
    for (TypeRef argument : arguments) {
      if (argument instanceof ClassRef) {
        refs.addAll(((ClassRef) argument).getReferences());
      }
    }
    refs.add(this);
    return refs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ClassRef classRef = (ClassRef) o;
    if (dimensions != classRef.dimensions)
      return false;
    if (fullyQualifiedName != null ? !fullyQualifiedName.equals(classRef.getFullyQualifiedName())
        : classRef.getFullyQualifiedName() != null)
      return false;
    return arguments != null ? arguments.equals(classRef.arguments) : classRef.arguments == null;
  }

  @Override
  public int hashCode() {
    int result = fullyQualifiedName != null ? fullyQualifiedName.hashCode() : 0;
    result = 31 * result + dimensions;
    result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(fullyQualifiedName);
    if (arguments.size() > 0) {
      sb.append(LT);
      sb.append(StringUtils.join(arguments, COMA));
      sb.append(GT);
    }

    for (int i = 0; i < dimensions; i++) {
      sb.append(BRACKETS);
    }
    return sb.toString();
  }

  @Override
  public String render(TypeDef enclosingType) {
    StringBuilder sb = new StringBuilder();
    sb.append(fullyQualifiedName);
    if (arguments.size() > 0) {
      sb.append(LT);
      sb.append(StringUtils.join(arguments, a -> a.render(enclosingType), COMA));
      sb.append(GT);
    }

    for (int i = 0; i < dimensions; i++) {
      sb.append(BRACKETS);
    }
    return sb.toString();
  }
}
