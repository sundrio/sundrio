package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ReturnDslVariableStepFluent<A extends io.sundr.model.ReturnDslVariableStepFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Variable, ?> variable;

  public ReturnDslVariableStepFluent() {
  }

  public ReturnDslVariableStepFluent(ReturnDslVariableStep instance) {
    this.copyInstance(instance);
  }

  public Variable buildVariable() {
    return this.variable != null ? this.variable.build() : null;
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "LocalVariable":

        return (VisitableBuilder<T, ?>) new LocalVariableBuilder((LocalVariable) item);

      case "Argument":

        return (VisitableBuilder<T, ?>) new ArgumentBuilder((Argument) item);

      case "Field":

        return (VisitableBuilder<T, ?>) new FieldBuilder((Field) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
  }

  protected void copyInstance(ReturnDslVariableStep instance) {
    if (instance != null) {
      this.withVariable(instance.getVariable());
    }
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    ReturnDslVariableStepFluent that = (ReturnDslVariableStepFluent) o;
    if (!(Objects.equals(variable, that.variable))) {
      return false;
    }
    return true;
  }

  public boolean hasVariable() {
    return this.variable != null;
  }

  public int hashCode() {
    return Objects.hash(variable);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(variable == null)) {
      sb.append("variable:");
      sb.append(variable);
    }
    sb.append("}");
    return sb.toString();
  }

  public ArgumentVariableNested<A> withNewArgumentVariable() {
    return new ArgumentVariableNested(null);
  }

  public ArgumentVariableNested<A> withNewArgumentVariableLike(Argument item) {
    return new ArgumentVariableNested(item);
  }

  public FieldVariableNested<A> withNewFieldVariable() {
    return new FieldVariableNested(null);
  }

  public FieldVariableNested<A> withNewFieldVariableLike(Field item) {
    return new FieldVariableNested(item);
  }

  public LocalVariableNested<A> withNewLocalVariable() {
    return new LocalVariableNested(null);
  }

  public LocalVariableNested<A> withNewLocalVariableLike(LocalVariable item) {
    return new LocalVariableNested(item);
  }

  public <T extends TypeRef> A withVariable(Variable variable) {
    if (variable == null) {
      this.variable = null;
      this._visitables.remove("variable");
      return (A) this;
    } else {
      VisitableBuilder<? extends Variable, ?> builder = builder(variable);
      this._visitables.get("variable").clear();
      this._visitables.get("variable").add(builder);
      this.variable = builder;
      return (A) this;
    }
  }

  public class ArgumentVariableNested<N> extends ArgumentFluent<ArgumentVariableNested<N>> implements Nested<N> {

    ArgumentBuilder builder;

    ArgumentVariableNested(Argument item) {
      this.builder = new ArgumentBuilder(this, item);
    }

    public N and() {
      return (N) ReturnDslVariableStepFluent.this.withVariable(builder.build());
    }

    public N endArgumentVariable() {
      return and();
    }

  }

  public class FieldVariableNested<N> extends FieldFluent<FieldVariableNested<N>> implements Nested<N> {

    FieldBuilder builder;

    FieldVariableNested(Field item) {
      this.builder = new FieldBuilder(this, item);
    }

    public N and() {
      return (N) ReturnDslVariableStepFluent.this.withVariable(builder.build());
    }

    public N endFieldVariable() {
      return and();
    }

  }

  public class LocalVariableNested<N> extends LocalVariableFluent<LocalVariableNested<N>> implements Nested<N> {

    LocalVariableBuilder builder;

    LocalVariableNested(LocalVariable item) {
      this.builder = new LocalVariableBuilder(this, item);
    }

    public N and() {
      return (N) ReturnDslVariableStepFluent.this.withVariable(builder.build());
    }

    public N endLocalVariable() {
      return and();
    }

  }
}
