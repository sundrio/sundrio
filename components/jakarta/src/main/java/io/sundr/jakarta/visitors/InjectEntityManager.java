package io.sundr.jakarta.visitors;

import static io.sundr.jakarta.JakartaCdi.INJECT_REF;
import static io.sundr.jakarta.JakartaPersistence.ENTITYMANAGER_REF;

import io.sundr.builder.Visitor;
import io.sundr.model.TypeDefFluent;

public class InjectEntityManager implements Visitor<TypeDefFluent<?>> {

  private static final String DEFAULT_PROPERTY_NAME = "entityManager";

  private final String propertyName;

  public InjectEntityManager() {
    this(DEFAULT_PROPERTY_NAME);
  }

  public InjectEntityManager(String propertyName) {
    this.propertyName = propertyName;
  }

  @Override
  public void visit(TypeDefFluent<?> type) {
    type.addNewProperty()
        .withName(propertyName)
        .withTypeRef(ENTITYMANAGER_REF)
        .addNewAnnotation()
        .withClassRef(INJECT_REF)
        .endAnnotation()
        .endProperty();
  }
}
