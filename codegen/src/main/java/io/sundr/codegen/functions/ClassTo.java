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
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.FunctionFactory;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.*;
import io.sundr.codegen.model.Method;

public class ClassTo {

  private static final String ARGUMENT_PREFIX = "arg";
  private static final Set<Class> references = new HashSet<>();

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
        if (target instanceof Class) {
          references.add((Class) target);
        }
        TypeRef targetRef = TYPEREF.apply(target);
        return targetRef.withDimensions(dimensions + targetRef.getDimensions());

      } else if (item instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType) item;
        Type rawType = parameterizedType.getRawType();
        List<TypeRef> arguments = new ArrayList<TypeRef>();
        for (Type arg : parameterizedType.getActualTypeArguments()) {
          arguments.add(TYPEREF.apply(arg));
          if (arg instanceof Class) {
            references.add((Class) arg);
          }
        }
        if (rawType instanceof Class) {
          references.add((Class) rawType);
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
          references.add(target);
          return targetRef.withDimensions(dimensions + targetRef.getDimensions());
        }

        if (c.isPrimitive()) {
          return new PrimitiveRefBuilder().withName(c.getName()).withDimensions(0).build();
        } else {
          List<TypeRef> arguments = new ArrayList<TypeRef>();
          for (TypeVariable v : c.getTypeParameters()) {
            arguments.add(TYPEREF.apply(v));
          }
          String fqcn = c.getName().replaceAll(Pattern.quote("$"), ".");
          return new ClassRefBuilder()
              .withFullyQualifiedName(fqcn)
              .withArguments(arguments)
              .build();
        }
      }
      throw new IllegalArgumentException("Can't convert type:" + item + " to a TypeRef");
    }
  });

  public static final Function<Class<? extends Annotation>, AnnotationRef> ANNOTATIONTYPEREF = FunctionFactory
      .cache(item -> {
        //An annotation can't be a primitive or a void type, so its safe to cast.
        ClassRef classRef = (ClassRef) TYPEREF.apply(item);
        return new AnnotationRefBuilder().withClassRef(classRef).build();
      });

  private static final Function<Class, TypeDef> INTERNAL_TYPEDEF = new Function<Class, TypeDef>() {
    public TypeDef apply(Class item) {

      if (Object.class.equals(item)) {
        return TypeDef.OBJECT;
      }
      Kind kind = KIND.apply(item);
      List<ClassRef> extendsList = new ArrayList<>();
      List<ClassRef> implementsList = new ArrayList<>();
      List<Property> properties = new ArrayList<>();
      List<Method> methods = new ArrayList<>();
      List<Method> constructors = new ArrayList<>();
      List<TypeParamDef> parameters = new ArrayList<>();

      if (item.getSuperclass() != null) {
        extendsList.add((ClassRef) TYPEREF.apply(item.getGenericSuperclass()));
        references.add(item.getSuperclass());
      }

      for (Class interfaceClass : item.getInterfaces()) {
        references.add(interfaceClass);
      }
      for (Type interfaceClass : item.getGenericInterfaces()) {
        TypeRef ref = TYPEREF.apply(interfaceClass);
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

      String outerFQCN = item.getDeclaringClass() != null ? item.getDeclaringClass().getName() : null;
      TypeDef result = DefinitionRepository.getRepository().register(new TypeDefBuilder()
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
            DefinitionRepository.getRepository().registerIfAbsent(referenceFQCN, () -> apply(c));
          });

      return result;
    }
  };

  public static final Function<Class, TypeDef> TYPEDEF = INTERNAL_TYPEDEF;
  //  public static final Function<Class, TypeDef> TYPEDEF = FunctionFactory.cache(INTERNAL_TYPEDEF);
  //      .withFallback(INTERNAL_SHALLOW_TYPEDEF).withMaximumRecursionLevel(10).withMaximumNestingDepth(10);

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

  private static Set<Property> getProperties(Class item, Set<Class> references) {
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
          .withTypeRef(TYPEREF.apply(field.getGenericType()))
          .build());
    }
    return properties;
  }

  private static void processAnnotatedElement(AnnotatedElement field, List<AnnotationRef> annotationRefs) {
    for (Annotation annotation : field.getDeclaredAnnotations()) {
      final Class<? extends Annotation> annotationType = annotation.annotationType();
      AnnotationRef annotationRef = ANNOTATIONTYPEREF.apply(annotationType);
      Map<String, Object> parameters = new HashMap<>();
      for (java.lang.reflect.Method method : annotationType.getDeclaredMethods()) {
        final String name = method.getName();
        try {
          final Object value = method.invoke(annotation, (Object[]) null);
          parameters.put(name, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
          System.out.printf("Couldn't retrieve '%s' parameter value for %s%n", name, annotationType.getName());
        }
      }
      annotationRef = new AnnotationRefBuilder(annotationRef).withParameters(parameters).build();

      annotationRefs.add(annotationRef);
    }
  }

  private static Set<Method> getConstructors(Class item, Set<Class> references) {
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

  private static Set<Method> getMethods(Class item, Set<Class> references) {
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

  private static void processMethod(Set<Class> references, java.lang.reflect.Executable method,
      List<AnnotationRef> annotationRefs, List<ClassRef> exceptionRefs, List<Property> arguments,
      List<TypeParamDef> parameters) {
    processAnnotatedElement(method, annotationRefs);

    for (Class exceptionType : method.getExceptionTypes()) {
      exceptionRefs.add((ClassRef) TYPEREF.apply(exceptionType));
    }

    for (int i = 1; i <= method.getGenericParameterTypes().length; i++) {
      Type argumentType = method.getGenericParameterTypes()[i - 1];
      arguments.add(new PropertyBuilder()
          .withName(ARGUMENT_PREFIX + i)
          .withTypeRef(TYPEREF.apply(argumentType))
          .build());

      if (argumentType instanceof Class) {
        references.add((Class) argumentType);
      }
    }

    for (Type type : method.getGenericParameterTypes()) {

      TypeParamDef typeParamDef = TYPEPARAMDEF.apply(type);
      if (typeParamDef != null) {
        parameters.add(typeParamDef);
      }
    }
  }
}
