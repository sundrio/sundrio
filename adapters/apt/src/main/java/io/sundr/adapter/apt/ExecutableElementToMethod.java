package io.sundr.adapter.apt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.model.AnnotationRef;
import io.sundr.model.AttributeKey;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class ExecutableElementToMethod implements Function<ExecutableElement, Method> {

  private static final String NEWLINE_PATTERN = "\r|\n";

  private final AptContext context;
  private final Function<TypeMirror, TypeRef> referenceAdapterFunction;
  private final Function<VariableElement, Property> propertyAdapterFunction;
  private final Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction;

  public ExecutableElementToMethod(AptContext context, Function<TypeMirror, TypeRef> referenceAdapterFunction,
      Function<VariableElement, Property> propertyAdapterFunction,
      Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction) {
    this.context = context;
    this.referenceAdapterFunction = referenceAdapterFunction;
    this.propertyAdapterFunction = propertyAdapterFunction;
    this.annotationAdapterFunction = annotationAdapterFunction;
  }

  public Method apply(ExecutableElement executableElement) {
    Map<AttributeKey, Object> attributes = new HashMap<>();
    if (executableElement.getDefaultValue() != null) {
      attributes.put(Attributeable.DEFAULT_VALUE, executableElement.getDefaultValue().getValue());
    }
    String comments = context.getElements().getDocComment(executableElement);
    List<String> commentList = Strings.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
    MethodBuilder methodBuilder = new MethodBuilder().withComments(commentList)
        .withDefaultMethod(executableElement.isDefault())
        .withModifiers(Types.modifiersToInt(executableElement.getModifiers()))
        .withName(executableElement.getSimpleName().toString())
        .withReturnType(referenceAdapterFunction.apply(executableElement.getReturnType()))
        .withVarArgPreferred(executableElement.isVarArgs()).withAttributes(attributes);

    // Populate constructor parameters
    for (VariableElement variableElement : executableElement.getParameters()) {
      methodBuilder = methodBuilder.addToArguments(propertyAdapterFunction.apply(variableElement));
    }
    List<ClassRef> exceptionRefs = new ArrayList<ClassRef>();
    for (TypeMirror thrownType : executableElement.getThrownTypes()) {
      TypeRef thrownRef = referenceAdapterFunction.apply(thrownType);
      if (thrownRef instanceof ClassRef) {
        exceptionRefs.add((ClassRef) thrownRef);
      }
      methodBuilder = methodBuilder.withExceptions(exceptionRefs);
    }

    for (AnnotationMirror annotationMirror : executableElement.getAnnotationMirrors()) {
      methodBuilder = methodBuilder.withAnnotations(annotationAdapterFunction.apply(annotationMirror));
    }
    return methodBuilder.build();
  }

}
