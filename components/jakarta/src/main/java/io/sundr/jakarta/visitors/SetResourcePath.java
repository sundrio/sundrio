package io.sundr.jakarta.visitors;

import static io.sundr.jakarta.JakartaRs.PATH_REF;

import io.sundr.builder.Visitor;
import io.sundr.model.MethodFluent;
import io.sundr.model.TypeDefFluent;

public class SetResourcePath {

  public static class OnType implements Visitor<TypeDefFluent<?>> {
    private final String resourcePath;

    public OnType(String resourcePath) {
      this.resourcePath = resourcePath;
    }

    @Override
    public void visit(TypeDefFluent<?> type) {
      type.removeMatchingFromAnnotations(a -> a.buildClassRef().equals(PATH_REF));

      type.addNewAnnotation()
          .withClassRef(PATH_REF)
          .addToParameters("value", resourcePath)
          .endAnnotation();
    }
  }

  public static class OnMethod implements Visitor<MethodFluent<?>> {
    private final String resourcePath;

    public OnMethod(String resourcePath) {
      this.resourcePath = resourcePath;
    }

    @Override
    public void visit(MethodFluent<?> method) {
      method.removeMatchingFromAnnotations(a -> a.buildClassRef().equals(PATH_REF));

      method.addNewAnnotation()
          .withClassRef(PATH_REF)
          .addToParameters("value", resourcePath)
          .endAnnotation();
    }
  }
}
