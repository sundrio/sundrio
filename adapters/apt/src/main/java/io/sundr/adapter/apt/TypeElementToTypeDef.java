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

package io.sundr.adapter.apt;

import static io.sundr.adapter.apt.utils.Apt.getClassName;
import static io.sundr.adapter.apt.utils.Apt.getPackageName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import io.sundr.SundrException;
import io.sundr.model.AnnotationRef;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class TypeElementToTypeDef implements Function<TypeElement, TypeDef> {

  private static final String OBJECT_BOUND = "java.lang.Object";
  private static final String JAVA_PEFIX = "java.";
  private static final String JAVAX_PEFIX = "javax.";
  private static final String COM_SUN_PREFIX = "com.sun.";
  private static final String EMPTY_PARENTHESIS = "()";
  private static final String EMPTY = "";
  private static final String NEWLINE_PATTERN = "\r|\n";
  private static final String ANY = "<any?>";

  private final AptContext context;
  private final Function<TypeMirror, TypeRef> referenceAdapterFunction;
  private final Function<VariableElement, Property> propertyAdapterFunction;
  private final Function<ExecutableElement, Method> methodAdapterFunction;
  private final Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction;
  private final Function<TypeParameterElement, TypeParamDef> typeParamAdapterFunction;

  public TypeElementToTypeDef(AptContext context, Function<TypeMirror, TypeRef> referenceAdapterFunction,
      Function<VariableElement, Property> propertyAdapterFunction,
      Function<ExecutableElement, Method> methodAdapterFunction,
      Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction,
      Function<TypeParameterElement, TypeParamDef> typeParamAdapterFunction) {
    this.context = context;
    this.referenceAdapterFunction = referenceAdapterFunction;
    this.propertyAdapterFunction = propertyAdapterFunction;
    this.methodAdapterFunction = methodAdapterFunction;
    this.annotationAdapterFunction = annotationAdapterFunction;
    this.typeParamAdapterFunction = typeParamAdapterFunction;
  }

  @Override
  public TypeDef apply(TypeElement classElement) {
    // Check SuperClass
    Kind kind = Kind.CLASS;

    Element enclosing = classElement.getEnclosingElement();
    if (enclosing != null && ANY.equals(enclosing.getSimpleName().toString())) {
      throw new SundrException("Failed to read class element:" + classElement.getQualifiedName().toString() + ". "
          + Messages.POTENTIAL_UNRESOLVED_SYMBOL);
    }

    TypeMirror superClass = classElement.getSuperclass();
    TypeRef superClassType = TypeDef.OBJECT_REF;

    if (superClass == null) {
      // ignore
    } else if (superClass instanceof NoType) {
      // ignore
    } else if (superClass.toString().equals(TypeDef.OBJECT.getFullyQualifiedName())) {
      // ignore
    } else {
      superClassType = referenceAdapterFunction.apply(superClass);
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

    String comments = AptContext.getContext().getElements().getDocComment(classElement);
    List<String> commentList = Strings.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());

    for (TypeMirror interfaceTypeMirrror : classElement.getInterfaces()) {
      TypeRef interfaceType = referenceAdapterFunction.apply(interfaceTypeMirrror);
      if (interfaceType instanceof ClassRef) {
        interfaces.add((ClassRef) interfaceType);
      } else {
        throw new IllegalStateException("Interface: [" + interfaceType + "] not mapped to a class ref.");
      }
    }

    for (TypeParameterElement typeParameter : classElement.getTypeParameters()) {
      TypeParamDef genericType = typeParamAdapterFunction.apply(typeParameter);
      genericTypes.add(genericType);
    }

    TypeDef baseType = new TypeDefBuilder()
        .withComments(commentList).withKind(kind)
        .withModifiers(Types.modifiersToInt(classElement.getModifiers()))
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
    context.getDefinitionRepository().registerIfAbsent(baseType);

    List<TypeDef> innerTypes = new ArrayList<TypeDef>();
    for (TypeElement innerElement : ElementFilter.typesIn(classElement.getEnclosedElements())) {
      TypeDef innerType = context.getDefinitionRepository().register(apply(innerElement));
      if (innerType == null) {
        throw new IllegalStateException("Inner type for:" + innerElement + " is null");
      }
      innerType = new TypeDefBuilder(innerType).withOuterTypeName(baseType.getFullyQualifiedName()).build();
      context.getDefinitionRepository().register(innerType);
      innerTypes.add(innerType);
    }

    TypeDefBuilder builder = new TypeDefBuilder(baseType).withInnerTypes(innerTypes);

    for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
      builder.addToConstructors(methodAdapterFunction.apply(constructor));
    }

    // Populate Fields
    for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
      builder.addToProperties(propertyAdapterFunction.apply(variableElement));
    }

    Set<ExecutableElement> allMethods = new LinkedHashSet<ExecutableElement>();
    allMethods.addAll(ElementFilter.methodsIn(classElement.getEnclosedElements()));
    allMethods.addAll(getInheritedMethods(classElement));

    for (ExecutableElement method : allMethods) {
      builder.addToMethods(methodAdapterFunction.apply(method));
    }

    for (AnnotationMirror annotationMirror : classElement.getAnnotationMirrors()) {
      builder.addToAnnotations(annotationAdapterFunction.apply(annotationMirror));
    }
    //Let's register the full blown definition
    TypeDef result = context.getDefinitionRepository().register(builder.build());

    //Also register other types
    if (context.isDeep()) {
      Set<TypeElement> references = new HashSet<>(context.getReferences());
      references.stream()
          .filter(t -> !t.equals(classElement))
          .filter(t -> !t.toString().startsWith("sun.") && !t.toString().startsWith("com.sun."))
          .forEach(t -> {
            String fqcn = t.toString();
            TypeDef existing = context.getDefinitionRepository().getDefinition(fqcn);
            if (existing == null) {
              context.getDefinitionRepository().registerIfAbsent(fqcn, () -> apply(t));
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
          ? AptContext.getContext().getElements().getTypeElement(typeElement.getSuperclass().toString())
          : null));

    }
    return result;
  }

}
