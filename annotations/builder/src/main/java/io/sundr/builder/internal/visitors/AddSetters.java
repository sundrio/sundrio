package io.sundr.builder.internal.visitors;

import java.util.function.Predicate;

import io.sundr.builder.Visitor;
import io.sundr.model.Assign;
import io.sundr.model.Kind;
import io.sundr.model.Property;
import io.sundr.model.This;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.utils.Types;

public class AddSetters implements Visitor<TypeDefFluent<?>> {

  private final Predicate<TypeDefFluent<?>> predicate;

  public AddSetters() {
    this(p -> true);
  }

  public AddSetters(Predicate<TypeDefFluent<?>> predicate) {
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
      String setterName = "set" + p.getNameCapitalized();
      if (def.hasMatchingMethod(m -> m.getName().equals(setterName))) {
        continue;
      }
      def.addNewMethod()
          .withName(setterName)
          .withReturnType(Types.VOID)
          .withArguments(p)
          .withNewBlock()
          .withStatements(new Assign(This.ref(p), p))
          .endBlock()
          .endMethod();
    }
  }
}
