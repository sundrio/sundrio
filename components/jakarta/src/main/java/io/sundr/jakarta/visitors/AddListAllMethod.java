package io.sundr.jakarta.visitors;

import static io.sundr.jakarta.JakartaRs.GET_REF;

import io.sundr.builder.Visitor;
import io.sundr.jakarta.JakartaRs;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeDefFluent;

public class AddListAllMethod implements Visitor<TypeDefFluent<?>> {

  public static String DEFAULT_METHOD_NAME = "listAll";

  private final String methodName;
  private final ClassRef entityType;

  public AddListAllMethod(ClassRef entityType) {
    this(DEFAULT_METHOD_NAME, entityType);
  }

  public AddListAllMethod(String methodName, ClassRef entityType) {
    this.methodName = methodName;
    this.entityType = entityType;
  }

  @Override
  public void visit(TypeDefFluent<?> type) {
    type.addNewMethod()
        .withName(methodName)
        .withReturnType(JakartaRs.RESPONSE_REF)
        .addNewAnnotation()
        .withClassRef(GET_REF)
        .endAnnotation()
        .withNewBlock()
        .addNewStringStatementStatement("")
        .endBlock()
        .endMethod();
  }
}
