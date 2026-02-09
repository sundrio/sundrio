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

package io.sundr.adapter.source;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.WildcardType;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeParamRefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.WildcardRef;
import io.sundr.model.WildcardRefBuilder;

public class ClassOrInterfaceToTypeRef implements Function<ClassOrInterfaceType, TypeRef> {

  private static final Function<Node, String> PACKAGENAME = new NodeToPackage();
  private final Function<Type, TypeRef> typeToTypeRef;

  public ClassOrInterfaceToTypeRef() {
    this.typeToTypeRef = new TypeToTypeRef(this);
  }

  public ClassOrInterfaceToTypeRef(Function<Type, TypeRef> typeToTypeRef) {
    this.typeToTypeRef = typeToTypeRef;
  }

  @Override
  public TypeRef apply(ClassOrInterfaceType classOrInterfaceType) {
    String boundPackage = PACKAGENAME.apply(classOrInterfaceType);
    String boundName = classOrInterfaceType.getNameAsString();

    List<TypeRef> arguments = new ArrayList<TypeRef>();
    List<Type> typeArgs = classOrInterfaceType.getTypeArguments().map(ArrayList::new).orElse(new ArrayList<>());
    for (Type arg : typeArgs) {
      if (arg instanceof ArrayType) {
        ArrayType arrayType = (ArrayType) arg;
        Type elementType = arrayType.getElementType();
        int dimensions = arrayType.getArrayLevel();
        if (elementType instanceof ClassOrInterfaceType) {
          TypeRef intermediateRef = apply((ClassOrInterfaceType) elementType);
          if (intermediateRef instanceof ClassRef) {
            arguments.add(new ClassRefBuilder((ClassRef) intermediateRef).withDimensions(dimensions).build());
          } else if (intermediateRef instanceof TypeParamRef) {
            arguments.add(new TypeParamRefBuilder((TypeParamRef) intermediateRef).withDimensions(dimensions).build());
          } else {
            throw new IllegalStateException("Expected class or type param reference");
          }
        } else {
          String name = arrayType.toString();
          arguments.add(new TypeParamRefBuilder().withName(name).withDimensions(dimensions).build());
        }
      } else if (arg instanceof ClassOrInterfaceType) {
        arguments.add(apply((ClassOrInterfaceType) arg));
      } else if (arg instanceof WildcardType) {
        WildcardType wildcardType = (WildcardType) arg;
        if (wildcardType.getExtendedType().isPresent()) {
          TypeRef bound = typeToTypeRef.apply(wildcardType.getExtendedType().get());
          arguments.add(new WildcardRefBuilder().addToBounds(bound).build());
        } else if (wildcardType.getSuperType().isPresent()) {
          TypeRef bound = typeToTypeRef.apply(wildcardType.getSuperType().get());
          arguments.add(new WildcardRefBuilder().addToBounds(bound).withBoundKind(WildcardRef.BoundKind.SUPER).build());
        } else {
          arguments.add(new WildcardRef());
        }
      }
    }

    if (classOrInterfaceType.getParentNode().orElse(null) == classOrInterfaceType) {
      return new TypeParamRefBuilder().withName(boundName).build();
    }

    String fqcn = boundPackage + "." + boundName;
    // TODO:The lines below more accurate, however the fail in some circumstances
    // with more recent version of JDK
    // as some of the known type definition are missing parameters.
    // return arguments.isEmpty()
    // ? new ClassRefBuilder().withDefinition(knownDefinition).build()
    // : knownDefinition.toReference(arguments);
    if (!classOrInterfaceType.getTypeArguments().isPresent() && boundName.length() == 1) {
      // We are doing our best here to distinguish between class refs and type
      // parameter refs.
      return new TypeParamRefBuilder().withName(boundName).build();
    } else {
      return new ClassRefBuilder().withFullyQualifiedName(fqcn).withArguments(arguments).build();
    }
  }
}
