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

import io.sundr.model.AttributeKey;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class ExecutableElementToMethod implements Function<ExecutableElement, Method> {

  private static final String NEWLINE_PATTERN = "\r|\n";

  private final AptContext context;

  public ExecutableElementToMethod(AptContext context) {
    this.context = context;
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
        .withReturnType(context.getTypeMirrorToTypeRef().apply(executableElement.getReturnType()))
        .withVarArgPreferred(executableElement.isVarArgs()).withAttributes(attributes);

    // Populate constructor parameters
    for (VariableElement variableElement : executableElement.getParameters()) {
      methodBuilder = methodBuilder.addToArguments(context.getVariableElementToProperty().apply(variableElement));

      List<ClassRef> exceptionRefs = new ArrayList<ClassRef>();
      for (TypeMirror thrownType : executableElement.getThrownTypes()) {
        TypeRef thrownRef = context.getTypeMirrorToTypeRef().apply(thrownType);
        if (thrownRef instanceof ClassRef) {
          exceptionRefs.add((ClassRef) thrownRef);
        }
      }
      methodBuilder = methodBuilder.withExceptions(exceptionRefs);
    }

    for (AnnotationMirror annotationMirror : executableElement.getAnnotationMirrors()) {
      methodBuilder = methodBuilder.withAnnotations(context.getAnnotationMirrorToAnnotationRef().apply(annotationMirror));
    }
    return methodBuilder.build();
  }

}
