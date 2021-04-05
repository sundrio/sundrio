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

package io.sundr.codegen.functions.element;

import static io.sundr.codegen.utils.ModelUtils.getClassName;
import static io.sundr.codegen.utils.ModelUtils.getPackageName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

public class TypeElementToTypeDef implements Function<TypeElement, TypeDef> {

  private static final String OBJECT_BOUND = "java.lang.Object";
  private static final String JAVA_PEFIX = "java.";
  private static final String JAVAX_PEFIX = "javax.";
  private static final String COM_SUN_PREFIX = "com.sun.";
  private static final String EMPTY_PARENTHESIS = "()";
  private static final String EMPTY = "";
  private static final String NEWLINE_PATTERN = "\r|\n";

  private final ElementContext context;

  public TypeElementToTypeDef(ElementContext context) {
    this.context = context;
  }

  @Override
  public TypeDef apply(TypeElement classElement) {
    // Check SuperClass
    Kind kind = Kind.CLASS;

    TypeMirror superClass = classElement.getSuperclass();
    TypeRef superClassType = TypeDef.OBJECT_REF;

    if (superClass == null) {
      // ignore
    } else if (superClass instanceof NoType) {
      // ignore
    } else if (superClass.toString().equals(TypeDef.OBJECT.getFullyQualifiedName())) {
      // ignore
    } else {
      superClassType = context.getTypeMirrorToTypeRef().apply(superClass);
    }

    List<TypeParamDef> genericTypes = new ArrayList<TypeParamDef>();
    List<ClassRef> interfaces = new ArrayList<ClassRef>();

    if (classElement.getKind() == ElementKind.INTERFACE) {
      kind = Kind.INTERFACE;
    } else if (classElement.getKind() == ElementKind.CLASS) {
      kind = Kind.CLASS;
    } else if (classElement.getKind() == ElementKind.ANNOTATION_TYPE) {
      kind = Kind.ANNOTATION;
    } else if (classElement.getKind() == ElementKind.ENUM) {
      kind = Kind.ENUM;
    }

    String comments = CodegenContext.getContext().getElements().getDocComment(classElement);
    List<String> commentList = StringUtils.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());

    for (TypeMirror interfaceTypeMirrror : classElement.getInterfaces()) {
      TypeRef interfaceType = context.getTypeMirrorToTypeRef().apply(interfaceTypeMirrror);
      if (interfaceType instanceof ClassRef) {
        interfaces.add((ClassRef) interfaceType);
      } else {
        throw new IllegalStateException("Interface: [" + interfaceType + "] not mapped to a class ref.");
      }
    }

    for (TypeParameterElement typeParameter : classElement.getTypeParameters()) {
      List<ClassRef> genericBounds = new ArrayList<ClassRef>();
      try {
        if (!typeParameter.getBounds().isEmpty()) {
          TypeMirror bound = typeParameter.getBounds().get(0);
          if (!OBJECT_BOUND.equals(bound.toString())) {
            TypeRef boundRef = context.getTypeMirrorToTypeRef().apply(bound);
            if (boundRef instanceof ClassRef) {
              genericBounds.add((ClassRef) boundRef);
            } else {
              throw new IllegalStateException("Parameter bound: [" + boundRef + "] not mapped to a class ref.");
            }
          }
        }

        TypeParamDef genericType = new TypeParamDefBuilder().withName(typeParameter.getSimpleName().toString())
            .withBounds(genericBounds).build();

        genericTypes.add(genericType);

      } catch (Exception e) {
        // ignore
      }
    }

    TypeDef baseType = new TypeDefBuilder()
        .withComments(commentList).withKind(kind)
        .withModifiers(TypeUtils.modifiersToInt(classElement.getModifiers()))
        .withPackageName(getPackageName(classElement))
        .withName(getClassName(classElement))
        .withParameters(genericTypes)
        .withExtendsList(superClassType instanceof ClassRef ? (ClassRef) superClassType : null)
        .withImplementsList(interfaces)
        .withOuterTypeName(
            classElement.getEnclosingElement() instanceof TypeElement
                ? classElement.getEnclosingElement().toString()
                : null)
        .build();

    //We will register the base type first and will replace it with the full blown version later.
    DefinitionRepository.getRepository().registerIfAbsent(baseType);

    List<TypeDef> innerTypes = new ArrayList<TypeDef>();
    for (TypeElement innerElement : ElementFilter.typesIn(classElement.getEnclosedElements())) {
      TypeDef innerType = DefinitionRepository.getRepository().register(apply(innerElement));
      if (innerType == null) {
        throw new IllegalStateException("Inner type for:" + innerElement + " is null");
      }
      innerType = new TypeDefBuilder(innerType).withOuterTypeName(baseType.getFullyQualifiedName()).build();
      DefinitionRepository.getRepository().register(innerType);
      innerTypes.add(innerType);
    }

    TypeDefBuilder builder = new TypeDefBuilder(baseType).withInnerTypes(innerTypes);

    for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
      builder.addToConstructors(context.getExecutableElementToMethod().apply(constructor));
    }

    // Populate Fields
    for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
      builder.addToProperties(context.getVariableElementToProperty().apply(variableElement));
    }

    Set<ExecutableElement> allMethods = new LinkedHashSet<ExecutableElement>();
    allMethods.addAll(ElementFilter.methodsIn(classElement.getEnclosedElements()));
    allMethods.addAll(getInheritedMethods(classElement));

    for (ExecutableElement method : allMethods) {
      builder.addToMethods(context.getExecutableElementToMethod().apply(method));
    }

    for (AnnotationMirror annotationMirror : classElement.getAnnotationMirrors()) {
      builder.addToAnnotations(context.getAnnotationMirrorToAnnotationRef().apply(annotationMirror));
    }
    //Let's register the full blown definition
    TypeDef result = DefinitionRepository.getRepository().register(builder.build());

    //Also register other types
    if (context.isDeep()) {
      Set<TypeElement> references = new HashSet<>(context.getReferences());
      references.stream()
          .filter(t -> !t.equals(classElement))
          .forEach(t -> {
            String fqcn = t.toString();
            TypeDef existing = DefinitionRepository.getRepository().getDefinition(fqcn);
            if (existing == null) {
              DefinitionRepository.getRepository().registerIfAbsent(fqcn, () -> apply(t));
            }
            context.getReferences().remove(t);
          });

    }

    return result;
  }

  public Set<ExecutableElement> getInheritedMethods(TypeElement typeElement) {
    Set<ExecutableElement> result = new LinkedHashSet<ExecutableElement>();
    if (typeElement != null) {
      for (ExecutableElement method : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
        if (!method.getModifiers().contains(Modifier.PRIVATE)) {
          result.add(method);
        }
      }
      result.addAll(getInheritedMethods(typeElement.getSuperclass() != null
          ? CodegenContext.getContext().getElements().getTypeElement(typeElement.getSuperclass().toString())
          : null));

    }
    return result;
  }
}
