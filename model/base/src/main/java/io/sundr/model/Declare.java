package io.sundr.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Declare implements ExpressionOrStatement {

  private final List<LocalVariable> localVariables;
  private final Optional<Expression> value;

  public Declare(List<LocalVariable> localVariables, Optional<Expression> value) {
    this.localVariables = localVariables;
    this.value = value;
  }

  //
  // Auxliliary constructors
  //
  public Declare(Variable<?> variable, Expression expression) {
    this(Arrays.asList(variable.asLocalVariable()), Optional.of(expression));
  }

  public Declare(Variable<?> variable, Object value, Object... rest) {
    this(Arrays.asList(variable.asLocalVariable()), Optional.of(ValueRef.from(value, rest)));
  }

  public Declare(Variable<?> variable, Variable<?> valueVariable) {
    this(Arrays.asList(variable.asLocalVariable()), Optional.of(valueVariable));
  }

  public Declare(Variable<?> variable) {
    this.localVariables = Arrays.asList(variable.asLocalVariable());
    this.value = Optional.empty();
  }

  public Declare(Class type, String name) {
    this.localVariables = Arrays.asList(LocalVariable.newLocalVariable(ClassRef.forClass(type), name));
    this.value = Optional.empty();
  }

  public Declare(Class type, String name, Object value) {
    this.localVariables = Arrays.asList(LocalVariable.newLocalVariable(ClassRef.forClass(type), name));
    this.value = Optional.of(ValueRef.from(value));
  }

  //
  // Static factory methods
  //
  public static Declare newInstance(String name, Class type, Expression... arguments) {
    if (arguments.length == 0) {
      return new Declare(LocalVariable.newLocalVariable(ClassRef.forClass(type), name), new Construct(type));
    } else if (arguments.length == 1) {
      return new Declare(LocalVariable.newLocalVariable(ClassRef.forClass(type), name), new Construct(type, arguments[0]));
    } else {
      return new Declare(LocalVariable.newLocalVariable(ClassRef.forClass(type), name), new Construct(type, arguments));
    }
  }

  public static Declare newInstance(String name, ClassRef type, Expression... arguments) {
    if (arguments.length == 0) {
      return new Declare(LocalVariable.newLocalVariable(type, name), new Construct(type));
    } else if (arguments.length == 1) {
      return new Declare(LocalVariable.newLocalVariable(type, name), new Construct(type, arguments[0]));
    } else {
      return new Declare(LocalVariable.newLocalVariable(type, name), new Construct(type, arguments));
    }
  }

  public static Declare cast(String name, ClassRef type, Expression target) {
    return new Declare(LocalVariable.newLocalVariable(type, name), new Cast(type, target));
  }

  public List<LocalVariable> getLocalVariables() {
    return localVariables;
  }

  public Optional<Expression> getValue() {
    return value;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    for (LocalVariable localVariable : localVariables) {
      refs.addAll(localVariable.getReferences());
    }
    value.ifPresent(v -> refs.addAll(v.getReferences()));
    return refs;
  }

  @Override
  public String render() {
    return renderExpression();
  }

  @Override
  public String renderExpression() {
    StringBuilder sb = new StringBuilder();
    TypeRef typeRef = localVariables.get(0).getTypeRef();
    sb.append(typeRef.render());
    sb.append(SPACE);
    sb.append(localVariables.stream().map(LocalVariable::getName).collect(Collectors.joining(", ")));
    sb.append(value.map(v -> " = " + v.renderExpression()).orElse(""));
    return sb.toString();
  }
}
