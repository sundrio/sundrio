package io.sundr.builder.internal.visitors;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.sundr.builder.Visitor;
import io.sundr.model.AttributeKey;
import io.sundr.model.Kind;
import io.sundr.model.Property;
import io.sundr.model.Statement;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDefFluent;

public class AddAllArgsConstructor implements Visitor<TypeDefFluent<?>> {

  private static final AttributeKey<String> DEFAULT_VALUE = new AttributeKey<String>("DEFAULT_VALUE", String.class);

  private final Predicate<TypeDefFluent<?>> predicate;

  public AddAllArgsConstructor() {
    this(p -> true);
  }

  public AddAllArgsConstructor(Predicate<TypeDefFluent<?>> predicate) {
    this.predicate = predicate;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {

    if (def.getKind() != Kind.CLASS) {
      return;
    }

    if (def.buildModifiers().isAbstract()) {
      return;
    }

    if (def.hasExtendsList()) {
      // This is a little triciker to implement as we will have to lookup for the parent class constructors and so on.
      // So, let's skip them for now.
      return;
    }

    if (!predicate.test(def)) {
      return;
    }

    List<Property> arguments = def.buildProperties().stream().filter(p -> !p.isStatic() && !isFinalWithValue(p))
        .collect(Collectors.toList());
    List<Statement> setValues = arguments.stream()
        .map(a -> new StringStatement("this." + a.getName() + " = " + a.getName() + ";")).collect(Collectors.toList());

    def.addNewConstructor()
        .withArguments(arguments)
        .withNewBlock()
        .withStatements(setValues)
        .endBlock()
        .endConstructor();
  }

  private static boolean isFinalWithValue(Property p) {
    return p.isFinal() && p.hasAttribute(DEFAULT_VALUE);
  }
}
