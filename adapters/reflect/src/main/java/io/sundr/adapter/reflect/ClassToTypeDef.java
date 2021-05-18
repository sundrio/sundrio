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

package io.sundr.adapter.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.AnnotationRef;
import io.sundr.model.AnnotationRefBuilder;
import io.sundr.model.AttributeKey;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeRef;

public class ClassToTypeDef implements Function<Class, TypeDef> {

  private static final String ARGUMENT_PREFIX = "arg";

  private final AdapterContext context;
  private final Set<Class> references;
  private final Function<Type, TypeRef> typeToTypeRef;
  private final Function<Type, TypeParamDef> typeToTypeParamDef;
  private final Function<Class<? extends Annotation>, AnnotationRef> annotationTypeToAnnotationRef;
  private final Function<Class, Kind> classToKind;

  public ClassToTypeDef(AdapterContext context, Set<Class> references, Function<Type, TypeRef> typeToTypeRef,
      Function<Type, TypeParamDef> typeToTypeParamDef,
      Function<Class<? extends Annotation>, AnnotationRef> annotationTypeToAnnotationRef,
      Function<Class, Kind> classToKind) {
    this.context = context;
    this.references = references;
    this.typeToTypeRef = typeToTypeRef;
    this.typeToTypeParamDef = typeToTypeParamDef;
    this.annotationTypeToAnnotationRef = annotationTypeToAnnotationRef;
    this.classToKind = classToKind;
  }

  @Override
  public TypeDef apply(Class item) {
    if (Object.class.equals(item)) {
      return TypeDef.OBJECT;
    }
    Kind kind = classToKind.apply(item);
    List<ClassRef> extendsList = new ArrayList<>();
    List<ClassRef> implementsList = new ArrayList<>();
    List<Property> properties = new ArrayList<>();
    List<Method> methods = new ArrayList<>();
    List<Method> constructors = new ArrayList<>();
    List<TypeParamDef> parameters = new ArrayList<>();

    if (item.getSuperclass() != null) {
      extendsList.add((ClassRef) typeToTypeRef.apply(item.getGenericSuperclass()));
      references.add(item.getSuperclass());
    }

    for (Class interfaceClass : item.getInterfaces()) {
      references.add(interfaceClass);
    }
    for (Type interfaceClass : item.getGenericInterfaces()) {
      TypeRef ref = typeToTypeRef.apply(interfaceClass);
      if (ref instanceof ClassRef) {
        implementsList.add((ClassRef) ref);
      }
    }

    constructors.addAll(getConstructors(item, references));
    methods.addAll(getMethods(item, references));
    properties.addAll(getProperties(item, references));

    for (TypeVariable typeVariable : item.getTypeParameters()) {
      List<ClassRef> bounds = new ArrayList<>();
      for (Type boundType : typeVariable.getBounds()) {
        TypeRef typeRef = typeToTypeRef.apply(boundType);
        if (typeRef instanceof ClassRef) {
          bounds.add((ClassRef) typeRef);
        }
      }
      parameters.add(new TypeParamDefBuilder()
          .withName(typeVariable.getName())
          .withBounds(bounds)
          .build());

    }

    String outerFQCN = item.getDeclaringClass() != null ? item.getDeclaringClass().getName() : null;
    TypeDef result = context.getDefinitionRepository().register(new TypeDefBuilder()
        .withKind(kind)
        .withOuterTypeName(outerFQCN)
        .withName(item.getSimpleName())
        .withPackageName(item.getPackage() != null ? item.getPackage().getName() : null)
        .withModifiers(item.getModifiers())
        .withParameters(parameters)
        .withConstructors(constructors)
        .withMethods(methods)
        .withProperties(properties)
        .withExtendsList(extendsList)
        .withImplementsList(implementsList)
        .build());

    Set<Class> copy = new HashSet<>(references);
    copy.stream()
        .peek(c -> references.remove(c))
        .filter(c -> !c.equals(item))
        .filter(c -> !c.getName().startsWith("sun.") && !c.getName().toString().startsWith("com.sun."))
        .forEach(c -> {
          String referenceFQCN = c.getName().replaceAll(Pattern.quote("$"), ".");
          context.getDefinitionRepository().registerIfAbsent(referenceFQCN, () -> apply(c));
        });

    return result;
  }

  private Set<Property> getProperties(Class item, Set<Class> references) {
    Set<Property> properties = new HashSet<Property>();
    for (Field field : item.getDeclaredFields()) {
      List<AnnotationRef> annotationRefs = new ArrayList<AnnotationRef>();
      processAnnotatedElement(field, annotationRefs);

      if (field.getGenericType() instanceof Class) {
        references.add((Class) field.getGenericType());
      }
      // If property contains generic bounds, we need to process them too.
      if (field.getGenericType() instanceof ParameterizedType) {
        ParameterizedType p = (ParameterizedType) field.getGenericType();
        references.addAll(Stream.of(p.getActualTypeArguments()).filter(t -> t instanceof Class)
            .map(t -> (Class) t)
            .filter(c -> !item.equals(c))
            .collect(Collectors.toList()));
      }
      properties.add(new PropertyBuilder()
          .withName(field.getName())
          .withModifiers(field.getModifiers())
          .withAnnotations(annotationRefs)
          .withTypeRef(typeToTypeRef.apply(field.getGenericType()))
          .build());
    }
    return properties;
  }

  private Set<Method> getConstructors(Class item, Set<Class> references) {
    Set<Method> constructors = new HashSet<Method>();
    for (java.lang.reflect.Constructor constructor : item.getDeclaredConstructors()) {
      List<AnnotationRef> annotationRefs = new ArrayList<AnnotationRef>();
      List<ClassRef> exceptionRefs = new ArrayList<>();
      List<Property> arguments = new ArrayList<Property>();
      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
      processMethod(references, constructor, annotationRefs, exceptionRefs, arguments, parameters);

      constructors.add(new MethodBuilder()
          .withName(constructor.getName())
          .withModifiers(constructor.getModifiers())
          .withArguments(arguments)
          .withParameters(parameters)
          .withAnnotations(annotationRefs)
          .withExceptions(exceptionRefs)
          .build());
    }
    return constructors;
  }

  private Set<Method> getMethods(Class item, Set<Class> references) {
    Set<Method> methods = new HashSet<Method>();
    for (java.lang.reflect.Method method : item.getDeclaredMethods()) {
      List<AnnotationRef> annotationRefs = new ArrayList<>();
      List<ClassRef> exceptionRefs = new ArrayList<>();
      List<Property> arguments = new ArrayList<Property>();
      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
      processMethod(references, method, annotationRefs, exceptionRefs, arguments, parameters);

      Map<AttributeKey, Object> attributes = new HashMap<>();
      if (method.getDefaultValue() != null) {
        attributes.put(Attributeable.DEFAULT_VALUE, method.getDefaultValue());
      }

      methods.add(new MethodBuilder()
          .withName(method.getName())
          .withDefaultMethod(method.isDefault())
          .withModifiers(method.getModifiers())
          .withReturnType(typeToTypeRef.apply(method.getReturnType()))
          .withArguments(arguments)
          .withParameters(parameters)
          .withExceptions(exceptionRefs)
          .withAnnotations(annotationRefs)
          .withAttributes(attributes)
          .build());
    }
    return methods;
  }

  private void processAnnotatedElement(AnnotatedElement field, List<AnnotationRef> annotationRefs) {
    for (Annotation annotation : field.getDeclaredAnnotations()) {
      final Class<? extends Annotation> annotationType = annotation.annotationType();
      AnnotationRef annotationRef = annotationTypeToAnnotationRef.apply(annotationType);
      Map<String, Object> parameters = new HashMap<>();
      for (java.lang.reflect.Method method : annotationType.getDeclaredMethods()) {
        final String name = method.getName();
        try {
          final Object value = method.invoke(annotation, (Object[]) null);
          parameters.put(name, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
          //Let's not pollute output with internal jdk stuff. 
          if (!annotationType.getName().startsWith("jdk.")) {
            System.out.printf("Couldn't retrieve '%s' parameter value for %s%n", name, annotationType.getName());
          }
        }
      }
      annotationRef = new AnnotationRefBuilder(annotationRef).withParameters(parameters).build();

      annotationRefs.add(annotationRef);
    }
  }

  private void processMethod(Set<Class> references, java.lang.reflect.Executable method,
      List<AnnotationRef> annotationRefs, List<ClassRef> exceptionRefs, List<Property> arguments,
      List<TypeParamDef> parameters) {
    processAnnotatedElement(method, annotationRefs);

    for (Class exceptionType : method.getExceptionTypes()) {
      exceptionRefs.add((ClassRef) typeToTypeRef.apply(exceptionType));
    }

    for (int i = 1; i <= method.getGenericParameterTypes().length; i++) {
      Type argumentType = method.getGenericParameterTypes()[i - 1];
      arguments.add(new PropertyBuilder()
          .withName(ARGUMENT_PREFIX + i)
          .withTypeRef(typeToTypeRef.apply(argumentType))
          .build());

      if (argumentType instanceof Class) {
        references.add((Class) argumentType);
      }
    }

    for (Type type : method.getGenericParameterTypes()) {

      TypeParamDef typeParamDef = typeToTypeParamDef.apply(type);
      if (typeParamDef != null) {
        parameters.add(typeParamDef);
      }
    }
  }
}
