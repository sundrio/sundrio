package io.sundr.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    this(Arrays.asList(asLocalVariable(variable)), Optional.of(expression));
  }

  public Declare(Variable<?> variable, Object value, Object... rest) {
    this(Arrays.asList(asLocalVariable(variable)), Optional.of(ValueRef.from(value, rest)));
  }

  public Declare(Variable<?> variable, Variable<?> valueVariable) {
    this(Arrays.asList(asLocalVariable(variable)), Optional.of(valueVariable));
  }

  public Declare(Variable<?> variable) {
    this.localVariables = Arrays.asList(asLocalVariable(variable));
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

  /**
   * Use the variable as-is when it's already a LocalVariable. This preserves properties
   * such as annotations on the generated declaration. Otherwise, for non-LocalVariable
   * instances, convert it.
   */
  private static LocalVariable asLocalVariable(Variable<?> variable) {
    return variable instanceof LocalVariable ? (LocalVariable) variable : variable.asLocalVariable();
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
      return new Declare(LocalVariable.newLocalVariable(type, name), type.construct());
    } else if (arguments.length == 1) {
      return new Declare(LocalVariable.newLocalVariable(type, name), type.construct(arguments[0]));
    } else {
      return new Declare(LocalVariable.newLocalVariable(type, name), type.construct(arguments));
    }
  }

  public static Declare cast(String name, ClassRef type, Expression target) {
    return new Declare(LocalVariable.newLocalVariable(type, name), target.cast(type));
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
    // Since we can have multiple variables but only a single value expression,
    // each variable will be assigned the same value, if present. It is up to the
    // caller to ensure the value is valid for all variables. All variable
    // declarations will include any annotations that may be present.
    for (int i = 0, m = localVariables.size(); i < m; i++) {
      if (i > 0) {
        sb.append(SEMICOLN);
        sb.append(NEWLINE);
      }
      sb.append(localVariables.get(i).renderAnnotations());
      sb.append(localVariables.get(i).render());
      value.ifPresent(v -> sb.append(" = ").append(v.renderExpression()));
    }
    return sb.toString();
  }
}
