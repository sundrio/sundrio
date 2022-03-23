package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class BlockFluentImpl<A extends BlockFluent<A>> extends BaseFluent<A> implements BlockFluent<A> {
  public BlockFluentImpl() {
  }

  public BlockFluentImpl(io.sundr.model.Block instance) {
    this.withStatements(instance.getStatements());
  }

  private ArrayList<VisitableBuilder<? extends Statement, ?>> statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();

  public A addToStatements(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    _visitables.get("statements").add(builder);
    this.statements.add(builder);
    return (A) this;
  }

  public A addToStatements(Integer index, io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    _visitables.get("statements").add(index, builder);
    this.statements.add(index, builder);
    return (A) this;
  }

  public A addToStatements(java.lang.Integer index, io.sundr.model.Statement item) {
    if (item instanceof StringStatement) {
      addToStringStatementStatements(index, (io.sundr.model.StringStatement) item);
    }

    return (A) this;
  }

  public A setToStatements(java.lang.Integer index, io.sundr.model.Statement item) {
    if (item instanceof io.sundr.model.StringStatement) {
      setToStringStatementStatements(index, (io.sundr.model.StringStatement) item);
    }

    return (A) this;
  }

  public A addToStatements(io.sundr.model.Statement... items) {
    if (items != null && items.length > 0 && this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    for (io.sundr.model.Statement item : items) {
      if (item instanceof io.sundr.model.StringStatement) {
        addToStringStatementStatements((io.sundr.model.StringStatement) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.Statement, ?> builder = builderOf(item);
        _visitables.get("statements").add(builder);
        this.statements.add(builder);
      }
    }
    return (A) this;
  }

  public A addAllToStatements(Collection<io.sundr.model.Statement> items) {
    if (items != null && items.size() > 0 && this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    for (io.sundr.model.Statement item : items) {
      if (item instanceof io.sundr.model.StringStatement) {
        addToStringStatementStatements((io.sundr.model.StringStatement) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.Statement, ?> builder = builderOf(item);
        _visitables.get("statements").add(builder);
        this.statements.add(builder);
      }
    }
    return (A) this;
  }

  public A removeFromStatements(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    _visitables.get("statements").remove(builder);
    this.statements.remove(builder);
    return (A) this;
  }

  public A removeFromStatements(io.sundr.model.Statement... items) {
    for (io.sundr.model.Statement item : items) {
      if (item instanceof io.sundr.model.StringStatement) {
        removeFromStringStatementStatements((io.sundr.model.StringStatement) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.Statement, ?> builder = builderOf(item);
        _visitables.get("statements").remove(builder);
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromStatements(java.util.Collection<io.sundr.model.Statement> items) {
    for (io.sundr.model.Statement item : items) {
      if (item instanceof io.sundr.model.StringStatement) {
        removeFromStringStatementStatements((io.sundr.model.StringStatement) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.Statement, ?> builder = builderOf(item);
        _visitables.get("statements").remove(builder);
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildStatements instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.Statement> getStatements() {
    return build(statements);
  }

  public java.util.List<io.sundr.model.Statement> buildStatements() {
    return build(statements);
  }

  public io.sundr.model.Statement buildStatement(java.lang.Integer index) {
    return this.statements.get(index).build();
  }

  public io.sundr.model.Statement buildFirstStatement() {
    return this.statements.get(0).build();
  }

  public io.sundr.model.Statement buildLastStatement() {
    return this.statements.get(statements.size() - 1).build();
  }

  public io.sundr.model.Statement buildMatchingStatement(
      Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> predicate) {
    for (io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingStatement(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> predicate) {
    for (io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withStatements(java.util.List<io.sundr.model.Statement> statements) {
    if (statements != null) {
      this.statements = new java.util.ArrayList();
      for (io.sundr.model.Statement item : statements) {
        this.addToStatements(item);
      }
    } else {
      this.statements = null;
    }
    return (A) this;
  }

  public A withStatements(io.sundr.model.Statement... statements) {
    if (this.statements != null) {
      this.statements.clear();
    }
    if (statements != null) {
      for (io.sundr.model.Statement item : statements) {
        this.addToStatements(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasStatements() {
    return statements != null && !statements.isEmpty();
  }

  public A addToStringStatementStatements(java.lang.Integer index, io.sundr.model.StringStatement item) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    StringStatementBuilder builder = new io.sundr.model.StringStatementBuilder(item);
    _visitables.get("statements").add(index >= 0 ? index : _visitables.get("statements").size(), builder);
    this.statements.add(index >= 0 ? index : statements.size(), builder);
    return (A) this;
  }

  public A setToStringStatementStatements(java.lang.Integer index, io.sundr.model.StringStatement item) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    io.sundr.model.StringStatementBuilder builder = new io.sundr.model.StringStatementBuilder(item);
    if (index < 0 || index >= _visitables.get("statements").size()) {
      _visitables.get("statements").add(builder);
    } else {
      _visitables.get("statements").set(index, builder);
    }
    if (index < 0 || index >= statements.size()) {
      statements.add(builder);
    } else {
      statements.set(index, builder);
    }
    return (A) this;
  }

  public A addToStringStatementStatements(io.sundr.model.StringStatement... items) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    for (io.sundr.model.StringStatement item : items) {
      io.sundr.model.StringStatementBuilder builder = new io.sundr.model.StringStatementBuilder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A addAllToStringStatementStatements(java.util.Collection<io.sundr.model.StringStatement> items) {
    if (this.statements == null) {
      this.statements = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>>();
    }
    for (io.sundr.model.StringStatement item : items) {
      io.sundr.model.StringStatementBuilder builder = new io.sundr.model.StringStatementBuilder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A removeFromStringStatementStatements(io.sundr.model.StringStatement... items) {
    for (io.sundr.model.StringStatement item : items) {
      io.sundr.model.StringStatementBuilder builder = new io.sundr.model.StringStatementBuilder(item);
      _visitables.get("statements").remove(builder);
      if (this.statements != null) {
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromStringStatementStatements(java.util.Collection<io.sundr.model.StringStatement> items) {
    for (io.sundr.model.StringStatement item : items) {
      io.sundr.model.StringStatementBuilder builder = new io.sundr.model.StringStatementBuilder(item);
      _visitables.get("statements").remove(builder);
      if (this.statements != null) {
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromStringStatementStatements(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> predicate) {
    if (statements == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> each = statements.iterator();
    final List visitables = _visitables.get("statements");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatement() {
    return new BlockFluentImpl.StringStatementStatementsNestedImpl();
  }

  public io.sundr.model.BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatementLike(
      io.sundr.model.StringStatement item) {
    return new BlockFluentImpl.StringStatementStatementsNestedImpl(-1, item);
  }

  public A addNewStringStatementStatement(String data) {
    return (A) addToStringStatementStatements(new StringStatement(data));
  }

  public A addNewStringStatementStatement(java.lang.String data, Object[] parameters) {
    return (A) addToStringStatementStatements(new StringStatement(data, parameters));
  }

  public io.sundr.model.BlockFluent.StringStatementStatementsNested<A> setNewStringStatementStatementLike(
      java.lang.Integer index, io.sundr.model.StringStatement item) {
    return new io.sundr.model.BlockFluentImpl.StringStatementStatementsNestedImpl(index, item);
  }

  public boolean equals(java.lang.Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BlockFluentImpl that = (BlockFluentImpl) o;
    if (statements != null ? !statements.equals(that.statements) : that.statements != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(statements, super.hashCode());
  }

  public java.lang.String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (statements != null && !statements.isEmpty()) {
      sb.append("statements:");
      sb.append(statements);
    }
    sb.append("}");
    return sb.toString();
  }

  class StringStatementStatementsNestedImpl<N> extends StringStatementFluentImpl<BlockFluent.StringStatementStatementsNested<N>>
      implements io.sundr.model.BlockFluent.StringStatementStatementsNested<N>, Nested<N> {
    StringStatementStatementsNestedImpl(java.lang.Integer index, StringStatement item) {
      this.index = index;
      this.builder = new StringStatementBuilder(this, item);
    }

    StringStatementStatementsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.StringStatementBuilder(this);
    }

    io.sundr.model.StringStatementBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) BlockFluentImpl.this.setToStatements(index, builder.build());
    }

    public N endStringStatementStatement() {
      return and();
    }

  }

}
