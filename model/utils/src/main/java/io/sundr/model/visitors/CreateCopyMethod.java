package io.sundr.model.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.sundr.builder.Visitor;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.RichTypeDef;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Getter;
import io.sundr.model.utils.Setter;
import io.sundr.model.utils.Types;

public class CreateCopyMethod implements Visitor<TypeDefFluent<?>> {

  private final TypeDef typeDef;
  private final String name;
  private final boolean utility;
  private final boolean internal;
  private final String[] ignoreProperties;

  public CreateCopyMethod(TypeDef typeDef) {
    this(typeDef, "copy");
  }

  public CreateCopyMethod(TypeDef typeDef, String name) {
    this(typeDef, name, false, false, new String[0]);
  }

  public CreateCopyMethod(TypeDef typeDef, String name, String... ignoreProperties) {
    this(typeDef, name, false, false, ignoreProperties);
  }

  public CreateCopyMethod(TypeDef typeDef, String name, boolean utility, boolean internal, String... ignoreProperties) {
    this.typeDef = typeDef;
    this.name = name;
    this.utility = utility;
    this.internal = internal;
    this.ignoreProperties = ignoreProperties;
  }

  @Override
  public void visit(TypeDefFluent<?> type) {
    type.removeMatchingFromMethods(m -> {
      if (!m.getName().equals(name)) {
        return false;
      }

      List<Property> arguments = m.buildArguments();
      if (arguments.size() != 2) {
        return false;
      }

      TypeRef first = arguments.get(0).getTypeRef();
      if (!(first instanceof ClassRef)) {
        return false;
      }
      ClassRef firstClassRef = (ClassRef) first;
      if (!firstClassRef.getFullyQualifiedName().equals(typeDef.getFullyQualifiedName())) {
        return false;
      }

      TypeRef second = arguments.get(1).getTypeRef();
      if (!(second instanceof ClassRef)) {
        return false;
      }
      ClassRef secondClassRef = (ClassRef) first;
      if (!secondClassRef.getFullyQualifiedName().equals(typeDef.getFullyQualifiedName())) {
        return false;
      }
      return true;
    });

    type.addNewMethod()
        .withReturnType(Types.VOID)
        .withName(name)
        .withNewModifiers()
        .withPublic(!internal)
        .withStatic(utility)
        .endModifiers()
        .addNewArgument()
        .withName("from")
        .withTypeRef(typeDef.toReference())
        .endArgument()
        .addNewArgument()
        .withName("to")
        .withTypeRef(typeDef.toReference())
        .endArgument()
        .withNewBlock()
        .withStatements(copyAssignments(typeDef, "from", "to", ignoreProperties).stream().map(StringStatement::new)
            .collect(Collectors.toList()))
        .endBlock()
        .endMethod();
  }

  private List<String> copyAssignments(TypeDef typeDef, String fromInstanceName, String toInstanceName,
      String... ignoreProperties) {

    List<String> ignorePropertyList = Arrays.asList(ignoreProperties);
    RichTypeDef richDef = RichTypeDef.createFrom(typeDef);
    List<Property> publicProperties = richDef.getAllProperties().stream().filter(p -> p.getModifiers().isPublic())
        .collect(Collectors.toList());

    List<Method> getters = richDef.getAllMethods().stream().filter(m -> m.getModifiers().isPublic())
        .filter(Getter::is)
        .collect(Collectors.toList());

    List<Method> setters = richDef.getAllMethods().stream().filter(m -> m.getModifiers().isPublic())
        .filter(Setter::is)
        .collect(Collectors.toList());

    List<String> assignments = new ArrayList<>();

    for (Method getter : getters) {
      String propertyName = Getter.propertyName(getter);
      if (ignorePropertyList.contains(propertyName)) {
        continue;
      }
      String setterName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
      Optional<Method> setter = setters.stream().filter(s -> s.getName().equals(setterName)).findFirst();
      setter.ifPresent(
          s -> assignments.add(toInstanceName + "." + s.getName() + "(" + fromInstanceName + "." + getter.getName() + "());"));
    }

    for (Property property : publicProperties) {
      if (ignorePropertyList.contains(property.getName())) {
        continue;
      }
      assignments.add(toInstanceName + "." + property.getName() + " = " + fromInstanceName + "." + property.getName() + ";");
    }
    return assignments;
  }
}
