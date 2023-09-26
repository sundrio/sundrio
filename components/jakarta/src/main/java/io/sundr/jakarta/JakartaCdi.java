package io.sundr.jakarta;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.reflect.ClassTo;
import jakarta.inject.Inject;

public class JakartaCdi {

  public static TypeDef INJECT = ClassTo.TYPEDEF.apply(Inject.class);
  public static ClassRef INJECT_REF = INJECT.toInternalReference();

}
