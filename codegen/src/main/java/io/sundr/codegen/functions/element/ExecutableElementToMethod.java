package io.sundr.codegen.functions.element;

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

import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.model.AttributeKey;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

public class ExecutableElementToMethod implements Function<ExecutableElement, Method> {

  private static final String NEWLINE_PATTERN = "\r|\n";

  private final ElementContext context;

  public Method apply(ExecutableElement executableElement) {
    Map<AttributeKey, Object> attributes = new HashMap<>();
    if (executableElement.getDefaultValue() != null) {
      attributes.put(Attributeable.DEFAULT_VALUE, executableElement.getDefaultValue().getValue());
    }
    String comments = CodegenContext.getContext().getElements().getDocComment(executableElement);
    List<String> commentList = StringUtils.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
    MethodBuilder methodBuilder = new MethodBuilder().withComments(commentList)
        .withDefaultMethod(executableElement.isDefault())
        .withModifiers(TypeUtils.modifiersToInt(executableElement.getModifiers()))
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

  public ExecutableElementToMethod(ElementContext context) {
    this.context = context;
  }

}
