package io.sundr.jakarta;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.reflect.ClassTo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

public class JakartaPersistence {

  public static TypeDef ENTITY_DEF = ClassTo.TYPEDEF.apply(Entity.class);
  public static ClassRef ENTITY_REF = ENTITY_DEF.toInternalReference();

  public static TypeDef ENTITYMANAGER_DEF = ClassTo.TYPEDEF.apply(EntityManager.class);
  public static ClassRef ENTITYMANAGER_REF = ENTITYMANAGER_DEF.toInternalReference();
}
