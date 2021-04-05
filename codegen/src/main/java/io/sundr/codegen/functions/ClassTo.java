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

package io.sundr.codegen.functions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.FunctionFactory;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.AnnotationRefBuilder;
import io.sundr.codegen.model.AttributeKey;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.PrimitiveRefBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRefBuilder;
import io.sundr.codegen.model.WildcardRefBuilder;

public class ClassTo {

  private static final String ARGUMENT_PREFIX = "arg";

  public static final Function<Class, Kind> KIND = FunctionFactory.cache(new Function<Class, Kind>() {
    public Kind apply(Class item) {
      if (item.isAnnotation()) {
        return Kind.ANNOTATION;
      } else if (item.isEnum()) {
        return Kind.ENUM;
      } else if (item.isInterface()) {
        return Kind.INTERFACE;
      } else {
        return Kind.CLASS;
      }
    }
  });

  public static final Function<Type, TypeRef> TYPEREF = FunctionFactory.cache(new Function<Type, TypeRef>() {
    public TypeRef apply(Type item) {
      if (item == null) {
        return new VoidRefBuilder().build();
      } else if (item instanceof WildcardType) {
        return new WildcardRefBuilder().withBounds(Arrays.asList(((WildcardType) item).getLowerBounds()).stream()
            .map(t -> TYPEREF.apply(t)).collect(Collectors.toList())).build();
      } else if (item instanceof TypeVariable) {
        return new TypeParamRefBuilder().withName(((TypeVariable) item).getName()).build();
      } else if (item instanceof GenericArrayType) {
        Type target = item;
        int dimensions = 0;
        while (target instanceof GenericArrayType) {
          target = ((GenericArrayType) target).getGenericComponentType();
          dimensions++;
        }
        TypeRef targetRef = TYPEREF.apply(target);
        return targetRef.withDimensions(dimensions + targetRef.getDimensions());

      } else if (item instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType) item;
        Type rawType = parameterizedType.getRawType();
        List<TypeRef> arguments = new ArrayList<TypeRef>();
        for (Type arg : parameterizedType.getActualTypeArguments()) {
          arguments.add(TYPEREF.apply(arg));
        }
        return new ClassRefBuilder((ClassRef) TYPEREF.apply(rawType))
            .withArguments(arguments)
            .build();
      } else if (Object.class.equals(item)) {
        return ClassRef.OBJECT;
      } else if (item instanceof Class) {
        Class c = (Class) item;
        if (c.isArray()) {
          Class target = c;
          int dimensions = 0;
          while (target.isArray()) {
            target = ((Class) target).getComponentType();
            dimensions++;
          }
          TypeRef targetRef = TYPEREF.apply(target);
          return targetRef.withDimensions(dimensions + targetRef.getDimensions());
        }

        if (c.isPrimitive()) {
          return new PrimitiveRefBuilder().withName(c.getName()).build();
        } else {
          List<TypeRef> arguments = new ArrayList<TypeRef>();
          for (TypeVariable v : c.getTypeParameters()) {
            arguments.add(TYPEREF.apply(v));
          }
          return new ClassRefBuilder()
              .withDefinition(TYPEDEF.apply(c))
              .withArguments(arguments)
              .build();
        }
      }
      throw new IllegalArgumentException("Can't convert type:" + item + " to a TypeRef");
    }
  });

  public static final Function<Class<? extends Annotation>, AnnotationRef> ANNOTATIONTYPEREF = FunctionFactory
      .cache(new Function<Class<? extends Annotation>, AnnotationRef>() {

        @Override
        public AnnotationRef apply(Class<? extends Annotation> item) {
          //An annotation can't be a primitive or a void type, so its safe to cast.
          ClassRef classRef = (ClassRef) TYPEREF.apply(item);
          Map<String, Object> parameters;

          return new AnnotationRefBuilder().withClassRef(classRef).build();
        }
      });

  private static final Function<Class, TypeDef> INTERNAL_TYPEDEF = new Function<Class, TypeDef>() {
    public TypeDef apply(Class item) {

      if (Object.class.equals(item)) {
        return TypeDef.OBJECT;
      }
      Kind kind = KIND.apply(item);
      List<ClassRef> extendsList = new ArrayList<ClassRef>();
      List<ClassRef> implementsList = new ArrayList<ClassRef>();
      List<Property> properties = new ArrayList<Property>();
      List<Method> methods = new ArrayList<Method>();
      List<Method> constructors = new ArrayList<Method>();
      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

      if (item.getSuperclass() != null && kind == Kind.INTERFACE) {
        extendsList.add((ClassRef) TYPEREF.apply(item.getSuperclass()));
      }

      constructors.addAll(getConstructors(item));
      methods.addAll(getMethods(item));
      properties.addAll(getProperties(item));

      for (Class interfaceClass : item.getInterfaces()) {
        TypeRef ref = TYPEREF.apply(interfaceClass);
        if (ref instanceof ClassRef) {
          implementsList.add((ClassRef) ref);
        }
      }

      for (TypeVariable typeVariable : item.getTypeParameters()) {
        List<ClassRef> bounds = new ArrayList<ClassRef>();
        for (Type boundType : typeVariable.getBounds()) {
          TypeRef typeRef = TYPEREF.apply(boundType);
          if (typeRef instanceof ClassRef) {
            bounds.add((ClassRef) typeRef);
          }
        }
        parameters.add(new TypeParamDefBuilder()
            .withName(typeVariable.getName())
            .withBounds(bounds)
            .build());
      }

      TypeDef declaringType = item.getDeclaringClass() != null ? TYPEDEF.apply(item.getDeclaringClass()) : null;
      return DefinitionRepository.getRepository().register(new TypeDefBuilder()
          .withKind(kind)
          .withOuterType(declaringType)
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
    }
  };

  private static final Function<Class, TypeDef> INTERNAL_SHALLOW_TYPEDEF = new Function<Class, TypeDef>() {

    public TypeDef apply(Class item) {
      if (Object.class.equals(item)) {
        return TypeDef.OBJECT;
      }
      Kind kind = KIND.apply(item);

      return new TypeDefBuilder()
          .withKind(kind)
          .withName(item.getSimpleName())
          .withPackageName(item.getPackage() != null ? item.getPackage().getName() : null)
          .withModifiers(item.getModifiers())
          .withParameters()
          .build();
    }
  };

  public static final Function<Class, TypeDef> TYPEDEF = FunctionFactory.cache(INTERNAL_TYPEDEF)
      .withFallback(INTERNAL_SHALLOW_TYPEDEF).withMaximumRecursionLevel(10).withMaximumNestingDepth(10);

  private static Function<Type, TypeParamDef> TYPEPARAMDEF = FunctionFactory.cache(new Function<Type, TypeParamDef>() {

    public TypeParamDef apply(Type item) {
      if (item instanceof TypeVariable) {
        TypeVariable typeVariable = (TypeVariable) item;
        String name = typeVariable.getName();
        List<ClassRef> bounds = new ArrayList<ClassRef>();

        for (Type b : typeVariable.getBounds()) {
          if (b instanceof Class) {
            Class c = (Class) b;
            bounds.add((ClassRef) TYPEREF.apply(c));
          }
        }
        return new TypeParamDefBuilder().withName(name).withBounds(bounds).build();
      }
      return null;
    }
  });

  private static Set<Property> getProperties(Class item) {
    Set<Property> properties = new HashSet<Property>();
    for (Field field : item.getDeclaredFields()) {
      List<AnnotationRef> annotationRefs = new ArrayList<AnnotationRef>();
      for (Annotation annotation : field.getDeclaredAnnotations()) {
        annotationRefs.add(ANNOTATIONTYPEREF.apply(annotation.annotationType()));
      }
      field.getDeclaringClass();
      // If property contains generic bounds, we need to process them too.
      if (field.getGenericType() instanceof ParameterizedType) {
        ParameterizedType p = (ParameterizedType) field.getGenericType();
        Stream.of(p.getActualTypeArguments()).filter(t -> t instanceof Class)
            .map(t -> (Class) t)
            .forEach(a -> TYPEDEF.apply(a));
      }
      properties.add(new PropertyBuilder()
          .withName(field.getName())
          .withModifiers(field.getModifiers())
          .withAnnotations(annotationRefs)
          .withTypeRef(TYPEREF.apply(field.getGenericType()))
          .build());
    }
    return properties;
  }

  private static Set<Method> getConstructors(Class item) {
    Set<Method> constructors = new HashSet<Method>();
    for (java.lang.reflect.Constructor constructor : item.getDeclaredConstructors()) {
      List<AnnotationRef> annotationRefs = new ArrayList<AnnotationRef>();
      for (Annotation annotation : constructor.getDeclaredAnnotations()) {
        annotationRefs.add(ANNOTATIONTYPEREF.apply(annotation.annotationType()));
      }

      List<ClassRef> exceptionRefs = new ArrayList<>();
      for (Class exceptionType : constructor.getExceptionTypes()) {
        exceptionRefs.add((ClassRef) TYPEREF.apply(exceptionType));
      }

      List<Property> arguments = new ArrayList<Property>();
      for (int i = 1; i <= constructor.getGenericParameterTypes().length; i++) {
        Type argumentType = constructor.getGenericParameterTypes()[i - 1];
        arguments.add(new PropertyBuilder()
            .withName(ARGUMENT_PREFIX + i)
            .withTypeRef(TYPEREF.apply(argumentType))
            .build());
      }

      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
      for (Type type : constructor.getGenericParameterTypes()) {

        TypeParamDef typeParamDef = TYPEPARAMDEF.apply(type);
        if (typeParamDef != null) {
          parameters.add(typeParamDef);
        }
      }

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

  private static Set<Method> getMethods(Class item) {
    Set<Method> methods = new HashSet<Method>();
    for (java.lang.reflect.Method method : item.getDeclaredMethods()) {
      List<AnnotationRef> annotationRefs = new ArrayList<>();
      for (Annotation annotation : method.getDeclaredAnnotations()) {
        annotationRefs.add(ANNOTATIONTYPEREF.apply(annotation.annotationType()));
      }

      List<ClassRef> exceptionRefs = new ArrayList<>();
      for (Class exceptionType : method.getExceptionTypes()) {
        exceptionRefs.add((ClassRef) TYPEREF.apply(exceptionType));
      }

      references.add(method.getReturnType());
      List<Property> arguments = new ArrayList<Property>();
      for (int i = 1; i <= method.getGenericParameterTypes().length; i++) {
        Type argumentType = method.getGenericParameterTypes()[i - 1];
        arguments.add(new PropertyBuilder()
            .withName(ARGUMENT_PREFIX + i)
            .withTypeRef(TYPEREF.apply(argumentType))
            .build());
      }

      List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
      for (Type type : method.getGenericParameterTypes()) {

        TypeParamDef typeParamDef = TYPEPARAMDEF.apply(type);
        if (typeParamDef != null) {
          parameters.add(typeParamDef);
        }
      }
      Map<AttributeKey, Object> attributes = new HashMap<>();
      if (method.getDefaultValue() != null) {
        attributes.put(Attributeable.DEFAULT_VALUE, method.getDefaultValue());
      }

      methods.add(new MethodBuilder()
          .withName(method.getName())
          .withDefaultMethod(method.isDefault())
          .withModifiers(method.getModifiers())
          .withReturnType(TYPEREF.apply(method.getReturnType()))
          .withArguments(arguments)
          .withParameters(parameters)
          .withExceptions(exceptionRefs)
          .withAnnotations(annotationRefs)
          .withAttributes(attributes)
          .build());
    }
    return methods;
  }
}
