package io.sundr.model.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.sundr.model.Expression;
import io.sundr.model.Property;

public final class Resolvers {

  private Resolvers() {
    //Utility class
  }

  public static Map<String, Expression> createResolutionMap(List<Property> properties) {
    Map<String, Expression> map = new HashMap<>();
    for (Property property : properties) {
      map.put(property.getName(), property.toReference());
    }
    return map;
  }
}
