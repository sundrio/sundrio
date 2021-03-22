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

import static io.sundr.codegen.utils.ModelUtils.getClassName;
import static io.sundr.codegen.utils.ModelUtils.getPackageName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;

import io.sundr.FunctionFactory;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.converters.TypeRefTypeVisitor;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.AnnotationRefBuilder;
import io.sundr.codegen.model.AttributeKey;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

public class ElementTo {

  private static final String OBJECT_BOUND = "java.lang.Object";
  private static final String JAVA_PEFIX = "java.";
  private static final String JAVAX_PEFIX = "javax.";
  private static final String COM_SUN_PREFIX = "com.sun.";
  private static final String EMPTY_PARENTHESIS = "()";
  private static final String EMPTY = "";
  private static final String NEWLINE_PATTERN = "\r|\n";

  private static final Predicate<TypeMirror> IS_JAVA_TYPE_MIRROR = new Predicate<TypeMirror>() {
    public boolean test(TypeMirror item) {
      String fqn = item.toString();
      return fqn.startsWith(JAVA_PEFIX) || fqn.startsWith(JAVAX_PEFIX) || fqn.startsWith(COM_SUN_PREFIX);
    }
  };

  private static final Predicate<TypeElement> IS_JAVA_ELEMENT = new Predicate<TypeElement>() {
    public boolean test(TypeElement item) {
      String fqn = item.toString();
      return fqn.startsWith(JAVA_PEFIX) || fqn.startsWith(JAVAX_PEFIX) || fqn.startsWith(COM_SUN_PREFIX);
    }
  };

  private static final Function<TypeMirror, TypeRef> DEEP_MIRROR_TO_TYPEREF = new Function<TypeMirror, TypeRef>() {
    public TypeRef apply(TypeMirror item) {
      if (item instanceof NoType) {
        return new VoidRef();
      }

      Element element = CodegenContext.getContext().getTypes().asElement(item);
      TypeDef known = element != null ? CodegenContext.getContext().getDefinitionRepository().getDefinition(element.toString())
          : null;

      if (known == null && element instanceof TypeElement) {
        known = TYPEDEF.apply((TypeElement) element);
      }
      TypeRef typeRef = item.accept(new TypeRefTypeVisitor(), 0);
      if (typeRef instanceof ClassRef && known != null) {
        return new ClassRefBuilder((ClassRef) typeRef).withDefinition(known).build();
      }
      return typeRef;
    }
  };

  private static final Function<TypeMirror, TypeRef> SHALLOW_MIRROR_TO_TYPEREF = new Function<TypeMirror, TypeRef>() {
    public TypeRef apply(TypeMirror item) {
      return item.accept(new TypeRefTypeVisitor(), 0);
    }
  };

  public static final Function<TypeMirror, TypeRef> MIRROR_TO_TYPEREF = FunctionFactory.cache(DEEP_MIRROR_TO_TYPEREF)
      .withFallback(SHALLOW_MIRROR_TO_TYPEREF)
      .withFallbackPredicate(IS_JAVA_TYPE_MIRROR)
      .withMaximumRecursionLevel(10)
      .withMaximumNestingDepth(10);

  public static final Function<TypeParameterElement, TypeParamDef> TYPEPARAMDEF = new Function<TypeParameterElement, TypeParamDef>() {

    public TypeParamDef apply(TypeParameterElement item) {
      List<ClassRef> typeRefs = new ArrayList();

      for (TypeMirror typeMirror : item.getBounds()) {
        //TODO: Fix this
        //typeRefs.add(toTypeRef.apply(typeMirror));
      }

      return new TypeParamDefBuilder()
          .withName(item.getSimpleName().toString())
          .withBounds(typeRefs)
          .build();
    }

  };

  public static final Function<TypeVariable, TypeParamRef> TYPEVARIABLE_TO_TYPEPARAM_REF = new Function<TypeVariable, TypeParamRef>() {

    public TypeParamRef apply(TypeVariable item) {
      return new TypeParamRefBuilder().withName(item.asElement().getSimpleName().toString()).build();
    }
  };

  public static final Function<VariableElement, Property> PROPERTY = new Function<VariableElement, Property>() {
    public Property apply(final VariableElement variableElement) {
      String name = variableElement.getSimpleName().toString();

      TypeRef type = MIRROR_TO_TYPEREF.apply(variableElement.asType());
      List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
      for (AnnotationMirror annotationMirror : variableElement.getAnnotationMirrors()) {
        annotations.add(ANNOTATION_REF.apply(annotationMirror));
      }

      String comments = CodegenContext.getContext().getElements().getDocComment(variableElement);
      List<String> commentList = StringUtils.isNullOrEmpty(comments) ? new ArrayList<>()
          : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
              .collect(Collectors.toList());
      return new PropertyBuilder()
          .withComments(commentList)
          .withName(name)
          .withTypeRef(type)
          .withAnnotations(annotations)
          .withModifiers(TypeUtils.modifiersToInt(variableElement.getModifiers()))
          .build();
    }
  };

  public static final Function<ExecutableElement, Method> METHOD = new Function<ExecutableElement, Method>() {

    public Method apply(ExecutableElement executableElement) {
      Map<AttributeKey, Object> attributes = new HashMap<>();
      if (executableElement.getDefaultValue() != null) {
        attributes.put(Attributeable.DEFAULT_VALUE, executableElement.getDefaultValue().getValue());
      }
      String comments = CodegenContext.getContext().getElements().getDocComment(executableElement);
      List<String> commentList = StringUtils.isNullOrEmpty(comments) ? new ArrayList<>()
          : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
              .collect(Collectors.toList());
      MethodBuilder methodBuilder = new MethodBuilder()
          .withComments(commentList)
          .withDefaultMethod(executableElement.isDefault())
          .withModifiers(TypeUtils.modifiersToInt(executableElement.getModifiers()))
          .withName(executableElement.getSimpleName().toString())
          .withReturnType(MIRROR_TO_TYPEREF.apply(executableElement.getReturnType()))
          .withVarArgPreferred(executableElement.isVarArgs())
          .withAttributes(attributes);

      //Populate constructor parameters
      for (VariableElement variableElement : executableElement.getParameters()) {
        methodBuilder = methodBuilder.addToArguments(PROPERTY.apply(variableElement));

        List<ClassRef> exceptionRefs = new ArrayList<ClassRef>();
        for (TypeMirror thrownType : executableElement.getThrownTypes()) {
          if (thrownType instanceof ClassRef) {
            exceptionRefs.add((ClassRef) thrownType);
          }
        }
        methodBuilder = methodBuilder.withExceptions(exceptionRefs);
      }

      List<ClassRef> annotationRefs = new ArrayList<ClassRef>();
      for (AnnotationMirror annotationMirror : executableElement.getAnnotationMirrors()) {
        methodBuilder.withAnnotations(ANNOTATION_REF.apply(annotationMirror));
      }
      return methodBuilder.build();
    }
  };

  public static final Function<TypeElement, TypeDef> INTERNAL_TYPEDEF = new Function<TypeElement, TypeDef>() {
    public TypeDef apply(TypeElement classElement) {
      //Check SuperClass
      Kind kind = Kind.CLASS;

      TypeMirror superClass = classElement.getSuperclass();
      TypeRef superClassType = TypeDef.OBJECT_REF;

      if (superClass == null) {
        //ignore
      } else if (superClass instanceof NoType) {
        //ignore
      } else if (superClass.toString().equals(TypeDef.OBJECT.getFullyQualifiedName())) {
        //ignore
      } else {
        superClassType = MIRROR_TO_TYPEREF.apply(superClass);
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

      for (TypeMirror interfaceTypeMirrror : classElement.getInterfaces()) {
        TypeRef interfaceType = MIRROR_TO_TYPEREF.apply(interfaceTypeMirrror);
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
              TypeRef boundRef = MIRROR_TO_TYPEREF.apply(bound);
              if (boundRef instanceof ClassRef) {
                genericBounds.add((ClassRef) boundRef);
              } else {
                throw new IllegalStateException("Parameter bound: [" + boundRef + "] not mapped to a class ref.");
              }
            }
          }

          TypeParamDef genericType = new TypeParamDefBuilder().withName(typeParameter.getSimpleName().toString())
              .withBounds(genericBounds)
              .build();

          genericTypes.add(genericType);

        } catch (Exception e) {
          //ignore
        }
      }

      String comments = CodegenContext.getContext().getElements().getDocComment(classElement);
      List<String> commentList = StringUtils.isNullOrEmpty(comments) ? new ArrayList<>()
          : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
              .collect(Collectors.toList());
      TypeDef baseType = new TypeDefBuilder()
          .withComments(commentList)
          .withKind(kind)
          .withModifiers(TypeUtils.modifiersToInt(classElement.getModifiers()))
          .withPackageName(getPackageName(classElement))
          .withName(getClassName(classElement))
          .withParameters(genericTypes)
          .withExtendsList(superClassType instanceof ClassRef ? (ClassRef) superClassType : null)
          .withImplementsList(interfaces)
          .withOuterType(classElement.getEnclosingElement() instanceof TypeElement
              ? TYPEDEF.apply((TypeElement) classElement.getEnclosingElement())
              : null)
          .build();

      List<TypeDef> innerTypes = new ArrayList<TypeDef>();
      for (TypeElement innerElement : ElementFilter.typesIn(classElement.getEnclosedElements())) {
        TypeDef innerType = TYPEDEF.apply(innerElement);
        innerType = new TypeDefBuilder(innerType).withOuterType(baseType).build();
        DefinitionRepository.getRepository().register(innerType);
        innerTypes.add(innerType);
      }

      TypeDefBuilder builder = new TypeDefBuilder(baseType)
          .withInnerTypes(innerTypes);

      for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
        builder.addToConstructors(METHOD.apply(constructor));
      }

      //Populate Fields
      for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
        builder.addToProperties(PROPERTY.apply(variableElement));
      }

      Set<ExecutableElement> allMethods = new LinkedHashSet<ExecutableElement>();
      allMethods.addAll(ElementFilter.methodsIn(classElement.getEnclosedElements()));
      allMethods.addAll(getInheritedMethods(classElement));

      for (ExecutableElement method : allMethods) {
        builder.addToMethods(METHOD.apply(method));
      }

      for (AnnotationMirror annotationMirror : classElement.getAnnotationMirrors()) {
        builder.addToAnnotations(ANNOTATION_REF.apply(annotationMirror));
      }
      return DefinitionRepository.getRepository().register(builder.build());
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
  };

  public static final Function<TypeElement, TypeDef> SHALLOW_TYPEDEF = new Function<TypeElement, TypeDef>() {

    public TypeDef apply(TypeElement classElement) {
      List<ClassRef> extendsList = new ArrayList<ClassRef>();

      //Check SuperClass
      Kind kind = Kind.CLASS;
      if (classElement.getKind() == ElementKind.INTERFACE) {
        kind = Kind.INTERFACE;
      } else if (classElement.getKind() == ElementKind.CLASS) {
        kind = Kind.CLASS;
        extendsList.add(TypeDef.OBJECT_REF);
      } else if (classElement.getKind() == ElementKind.ANNOTATION_TYPE) {
        kind = Kind.ANNOTATION;
      } else if (classElement.getKind() == ElementKind.ENUM) {
        kind = Kind.ENUM;
      }

      Set<Method> allMethods = new LinkedHashSet<Method>();

      for (ExecutableElement method : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
      }
      return new TypeDefBuilder()
          .withKind(kind)
          .withModifiers(TypeUtils.modifiersToInt(classElement.getModifiers()))
          .withPackageName(getPackageName(classElement))
          .withName(getClassName(classElement))
          .withExtendsList(extendsList)
          .addAllToMethods(allMethods)
          .withOuterType(classElement.getEnclosingElement() instanceof TypeElement
              ? SHALLOW_TYPEDEF.apply((TypeElement) classElement.getEnclosingElement())
              : null)
          .build();
    }
  };

  public static final Function<TypeElement, TypeDef> TYPEDEF = FunctionFactory.cache(INTERNAL_TYPEDEF)
      .withFallback(SHALLOW_TYPEDEF)
      .withFallbackPredicate(IS_JAVA_ELEMENT)
      .withMaximumRecursionLevel(10)
      .withMaximumNestingDepth(10);

  private static Function<AnnotationMirror, AnnotationRef> ANNOTATION_REF = FunctionFactory
      .cache(new Function<AnnotationMirror, AnnotationRef>() {
        @Override
        public AnnotationRef apply(AnnotationMirror item) {
          TypeRef annotationType = item.getAnnotationType().accept(new TypeRefTypeVisitor(), 0);
          Map<String, Object> parameters = new HashMap<String, Object>();
          if (annotationType instanceof ClassRef) {
            for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : item.getElementValues().entrySet()) {
              String key = entry.getKey().toString().replace(EMPTY_PARENTHESIS, EMPTY);
              Object value = mapAnnotationValue(entry.getValue().getValue());
              parameters.put(key, value);
            }
            return new AnnotationRefBuilder()
                .withClassRef((ClassRef) annotationType)
                .withParameters(parameters)
                .build();
          }

          throw new IllegalStateException("Annotation type: [" + annotationType + "] is not a class reference.");
        }
      });

  private static Object mapAnnotationValue(Object value) {
    if (value instanceof Collection) {
      return ((Collection) value).stream()
          .map(ElementTo::mapAnnotationValue)
          .collect(Collectors.toList());
    } else if (value instanceof AnnotationMirror) {
      return ANNOTATION_REF.apply((AnnotationMirror) value);
    } else if (value instanceof AnnotationValue) {
      return ((AnnotationValue) value).getValue();
    } else if (value instanceof TypeMirror) {
      return MIRROR_TO_TYPEREF.apply((TypeMirror) value);
    } else {
      return value;
    }
  }

}
