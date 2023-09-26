package io.sundr.jakarta.visitors;

import static io.sundr.jakarta.JakartaRs.CONSUMES_REF;
import static io.sundr.jakarta.JakartaRs.PRODUCES_REF;

import io.sundr.builder.Visitor;
import io.sundr.model.MethodFluent;
import io.sundr.model.TypeDefFluent;

public class SetMediaType {

  public static class OnType implements Visitor<TypeDefFluent<?>> {

    private final String mediaType;

    public OnType(String mediaType) {
      this.mediaType = mediaType;
    }

    @Override
    public void visit(TypeDefFluent<?> type) {
      type.removeMatchingFromAnnotations(a -> a.buildClassRef().equals(PRODUCES_REF));
      type.removeMatchingFromAnnotations(a -> a.buildClassRef().equals(CONSUMES_REF));

      type.addNewAnnotation()
          .withClassRef(PRODUCES_REF)
          .addToParameters("value", mediaType)
          .endAnnotation()
          .addNewAnnotation()
          .withClassRef(CONSUMES_REF)
          .addToParameters("value", mediaType)
          .endAnnotation();
    }
  }

  public static class OnMethod implements Visitor<MethodFluent<?>> {

    private final String mediaType;

    public OnMethod(String mediaType) {
      this.mediaType = mediaType;
    }

    @Override
    public void visit(MethodFluent<?> method) {
      method.removeMatchingFromAnnotations(a -> a.buildClassRef().equals(PRODUCES_REF));
      method.removeMatchingFromAnnotations(a -> a.buildClassRef().equals(CONSUMES_REF));

      method.addNewAnnotation()
          .withClassRef(PRODUCES_REF)
          .addToParameters("value", mediaType)
          .endAnnotation()
          .addNewAnnotation()
          .withClassRef(CONSUMES_REF)
          .addToParameters("value", mediaType)
          .endAnnotation();
    }
  }
}
