package io.sundr.builder.internal.visitors;

import java.util.function.Predicate;

import io.sundr.builder.Visitor;
import io.sundr.model.Kind;
import io.sundr.model.Property;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDefFluent;

public class AddGetters implements Visitor<TypeDefFluent<?>> {

  private final Predicate<TypeDefFluent<?>> predicate;

  public AddGetters() {
    this(p -> true);
  }

  public AddGetters(Predicate<TypeDefFluent<?>> predicate) {
    this.predicate = predicate;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {

    if (def.getKind() != Kind.CLASS) {
      return;
    }

    if (!predicate.test(def)) {
      return;
    }

    for (Property p : def.buildProperties()) {
      String setterName = "get" + p.getNameCapitalized();
      if (def.hasMatchingMethod(m -> m.getName().equals(setterName))) {
        continue;
      }
      def.addNewMethod()
          .withName(setterName)
          .withReturnType(p.getTypeRef())
          .withArguments()
          .withNewBlock()
          .withStatements(new StringStatement("return this." + p.getName() + ";"))
          .endBlock()
          .endMethod();
    }
  }
}
