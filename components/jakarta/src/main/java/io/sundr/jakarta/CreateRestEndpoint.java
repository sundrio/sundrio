package io.sundr.jakarta;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.model.Property;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.visitors.CreateCopyMethod;
import io.sundr.model.visitors.ReplaceType;

public class CreateRestEndpoint {

  private static final AdapterContext CONTEXT = AdapterContext.getContext();
  private static final TypeDef TARGET = Sources.readTypeDefsFromResource("io/sundr/jakarta/templates/Target.java", CONTEXT)
      .get(0);
  private static final TypeDef TEMPLATE = Sources
      .readTypeDefsFromResource("io/sundr/jakarta/templates/TemplateEndpoint.java", CONTEXT)
      .get(0);

  public static TypeDef fromEntity(TypeDef entity) {
    String name = entity.getName() + "Endpoint";
    RichTypeDef richTypeDef = RichTypeDef.createFrom(entity);

    String idProperty = richTypeDef.getAllProperties().stream()
        .filter(p -> p.getAnnotations().stream()
            .anyMatch(a -> a.getClassRef().getFullyQualifiedName().equals("jakarta.persistence.Id")))
        .map(Property::getName)
        .findFirst()
        .orElse("identity");

    return new TypeDefBuilder(TEMPLATE)
        .withName(name)
        .accept(new ReplaceType(TARGET.toInternalReference(), entity.toInternalReference()),
            new CreateCopyMethod(entity, "copy", idProperty))
        .build();
  }
}
