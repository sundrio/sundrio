package io.sundr.model.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.sundr.model.Argument;
import io.sundr.model.Expression;
import io.sundr.model.Field;
import io.sundr.model.LocalVariable;
import io.sundr.model.Variable;

public final class Resolvers {

  private Resolvers() {
    //Utility class
  }

  public static Map<String, Expression> createResolutionMap(List<? extends Variable> variables) {
    Map<String, Expression> map = new HashMap<>();
    for (Variable variable : variables) {
      map.put(variable.getName(), variable);
    }
    return map;
  }

  public static Map<String, Expression> createResolutionMapFromArguments(List<Argument> arguments) {
    Map<String, Expression> map = new HashMap<>();
    for (Argument argument : arguments) {
      map.put(argument.getName(), argument);
    }
    return map;
  }

  public static Map<String, Expression> createResolutionMapFromFields(List<Field> fields) {
    Map<String, Expression> map = new HashMap<>();
    for (Field field : fields) {
      map.put(field.getName(), field);
    }
    return map;
  }

  public static Map<String, Expression> createResolutionMapFromLocalVariables(List<LocalVariable> localVariables) {
    Map<String, Expression> map = new HashMap<>();
    for (LocalVariable localVariable : localVariables) {
      map.put(localVariable.getName(), localVariable);
    }
    return map;
  }
}
