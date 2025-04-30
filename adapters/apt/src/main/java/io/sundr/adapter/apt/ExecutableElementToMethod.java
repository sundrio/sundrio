package io.sundr.adapter.apt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.model.AnnotationRef;
import io.sundr.model.AttributeKey;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Modifiers;
import io.sundr.model.Property;
import io.sundr.model.TypeRef;
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
    if (executableElement.getDefaultValue() != null && executableElement.getDefaultValue().getValue() != null) {
      Object defaultValue = executableElement.getDefaultValue().accept(new AnnotationValueVisitor() {
        public Object visit(AnnotationValue av) {
          return String.valueOf(av);
        }

        @Override
        public Object visit(AnnotationValue av, Object p) {
          return String.valueOf(av);
        }

        @Override
        public Object visitBoolean(boolean b, Object p) {
          return String.valueOf(b);
        }

        @Override
        public Object visitByte(byte b, Object p) {
          return String.valueOf(b);
        }

        @Override
        public Object visitChar(char c, Object p) {
          return String.valueOf(c);
        }

        @Override
        public Object visitDouble(double d, Object p) {
          return String.valueOf(d);
        }

        @Override
        public Object visitFloat(float f, Object p) {
          return String.valueOf(f);
        }

        @Override
        public Object visitInt(int i, Object p) {
          return String.valueOf(i);
        }

        @Override
        public Object visitLong(long i, Object p) {
          return String.valueOf(i) + "L";
        }

        @Override
        public Object visitShort(short s, Object p) {
          return String.valueOf(s);
        }

        @Override
        public Object visitString(String s, Object p) {
          return s;
        }

        @Override
        public Object visitType(TypeMirror t, Object p) {
          return t.toString();
        }

        @Override
        public Object visitEnumConstant(VariableElement c, Object p) {
          if (c.getSimpleName().toString().contains(c.getEnclosingElement().toString())) {
            return c.getSimpleName().toString();
          }
          return c.getEnclosingElement() + "." + c.getSimpleName();
        }

        @Override
        public Object visitAnnotation(AnnotationMirror a, Object p) {
          return a;
        }

        @Override
        public Object visitArray(List vals, Object p) {
          return vals;
        }

        @Override
        public Object visitUnknown(AnnotationValue av, Object p) {
          return av;
        }
      }, null);

      attributes.put(Attributeable.DEFAULT_VALUE, String.valueOf(defaultValue));
    }
    String comments = context.getElements().getDocComment(executableElement);
    List<String> commentList = Strings.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
    MethodBuilder methodBuilder = new MethodBuilder().withComments(commentList)
        .withDefaultMethod(executableElement.isDefault())
        .withModifiers(Modifiers.from(executableElement.getModifiers()))
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
      methodBuilder = methodBuilder.addToAnnotations(annotationAdapterFunction.apply(annotationMirror));
    }
    return methodBuilder.build();
  }
}
