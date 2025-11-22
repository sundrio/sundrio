package io.sundr.builder.internal.checks;

import java.util.HashMap;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.model.Field;
import io.sundr.model.Kind;
import io.sundr.model.TypeDefFluent;

public class DuplicateFieldCheck implements Visitor<TypeDefFluent<?>> {

  @Override
  public void visit(TypeDefFluent<?> def) {
    if (def.getKind() == Kind.ENUM) {
      return;
    }

    Map<String, String> mappings = new HashMap<>();

    for (Field p : def.buildFields()) {
      String name = p.getName();
      String field = p.getNameCapitalized();
      String existing = mappings.put(field, name);
      if (existing != null) {
        throw new IllegalStateException("Found multiple fields in: " + def.getName() + " that map to: " + field
            + ". Properties: " + name + " and " + existing + "!");
      }
    }
  }
}
