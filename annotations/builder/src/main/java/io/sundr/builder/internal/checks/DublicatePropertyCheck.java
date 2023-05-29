package io.sundr.builder.internal.checks;

import java.util.HashMap;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.model.Property;
import io.sundr.model.TypeDefFluent;
import io.sundr.utils.Strings;

public class DublicatePropertyCheck implements Visitor<TypeDefFluent<?>> {

  @Override
  public void visit(TypeDefFluent<?> def) {
    Map<String, String> mappings = new HashMap<>();

    for (Property p : def.buildProperties()) {
      String name = p.getName();
      String field = Strings.camelCase(name);
      if (mappings.containsValue(field)) {
        String existingKey = null;
        for (Map.Entry<String, String> entry : mappings.entrySet()) {
          if (entry.getValue().equals(field)) {
            existingKey = entry.getKey();
            break;
          }
        }
        throw new IllegalStateException("Found multiple properties in: " + def.getName() + " that map to: " + field
            + ". Properties: " + name + " and " + existingKey + "!");
      }
      mappings.put(name, field);
    }
  }
}
