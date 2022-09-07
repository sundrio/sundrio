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

  public BlockFluentImpl(Block instance) {
    this.withStatements(instance.getStatements());
  }

  private ArrayList<VisitableBuilder<? extends Statement, ?>> statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();

  public A addToStatements(VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    _visitables.get("statements").add(builder);
    this.statements.add(builder);
    return (A) this;
  }

  public A addToStatements(Integer index, VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    _visitables.get("statements").add(index, builder);
    this.statements.add(index, builder);
    return (A) this;
  }

  public A addToStatements(Integer index, Statement item) {
    if (item instanceof StringStatement) {
      addToStringStatementStatements(index, (StringStatement) item);
    }

    return (A) this;
  }

  public A setToStatements(Integer index, Statement item) {
    if (item instanceof StringStatement) {
      setToStringStatementStatements(index, (StringStatement) item);
    }

    return (A) this;
  }

  public A addToStatements(io.sundr.model.Statement... items) {
    if (items != null && items.length > 0 && this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    for (Statement item : items) {
      if (item instanceof StringStatement) {
        addToStringStatementStatements((StringStatement) item);
      }

      else {
        VisitableBuilder<? extends Statement, ?> builder = builderOf(item);
        _visitables.get("statements").add(builder);
        this.statements.add(builder);
      }
    }
    return (A) this;
  }

  public A addAllToStatements(Collection<Statement> items) {
    if (items != null && items.size() > 0 && this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    for (Statement item : items) {
      if (item instanceof StringStatement) {
        addToStringStatementStatements((StringStatement) item);
      }

      else {
        VisitableBuilder<? extends Statement, ?> builder = builderOf(item);
        _visitables.get("statements").add(builder);
        this.statements.add(builder);
      }
    }
    return (A) this;
  }

  public A removeFromStatements(VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    _visitables.get("statements").remove(builder);
    this.statements.remove(builder);
    return (A) this;
  }

  public A removeFromStatements(io.sundr.model.Statement... items) {
    for (Statement item : items) {
      if (item instanceof StringStatement) {
        removeFromStringStatementStatements((StringStatement) item);
      }

      else {
        VisitableBuilder<? extends Statement, ?> builder = builderOf(item);
        _visitables.get("statements").remove(builder);
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromStatements(Collection<Statement> items) {
    for (Statement item : items) {
      if (item instanceof StringStatement) {
        removeFromStringStatementStatements((StringStatement) item);
      }

      else {
        VisitableBuilder<? extends Statement, ?> builder = builderOf(item);
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
  public List<Statement> getStatements() {
    return build(statements);
  }

  public List<Statement> buildStatements() {
    return build(statements);
  }

  public Statement buildStatement(Integer index) {
    return this.statements.get(index).build();
  }

  public Statement buildFirstStatement() {
    return this.statements.get(0).build();
  }

  public Statement buildLastStatement() {
    return this.statements.get(statements.size() - 1).build();
  }

  public Statement buildMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    for (VisitableBuilder<? extends Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    for (VisitableBuilder<? extends Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withStatements(List<Statement> statements) {
    if (statements != null) {
      this.statements = new ArrayList();
      for (Statement item : statements) {
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
      for (Statement item : statements) {
        this.addToStatements(item);
      }
    }
    return (A) this;
  }

  public Boolean hasStatements() {
    return statements != null && !statements.isEmpty();
  }

  public A addToStringStatementStatements(Integer index, StringStatement item) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    StringStatementBuilder builder = new StringStatementBuilder(item);
    _visitables.get("statements").add(index >= 0 ? index : _visitables.get("statements").size(), builder);
    this.statements.add(index >= 0 ? index : statements.size(), builder);
    return (A) this;
  }

  public A setToStringStatementStatements(Integer index, StringStatement item) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    StringStatementBuilder builder = new StringStatementBuilder(item);
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
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    for (StringStatement item : items) {
      StringStatementBuilder builder = new StringStatementBuilder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A addAllToStringStatementStatements(Collection<StringStatement> items) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    for (StringStatement item : items) {
      StringStatementBuilder builder = new StringStatementBuilder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A removeFromStringStatementStatements(io.sundr.model.StringStatement... items) {
    for (StringStatement item : items) {
      StringStatementBuilder builder = new StringStatementBuilder(item);
      _visitables.get("statements").remove(builder);
      if (this.statements != null) {
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromStringStatementStatements(Collection<StringStatement> items) {
    for (StringStatement item : items) {
      StringStatementBuilder builder = new StringStatementBuilder(item);
      _visitables.get("statements").remove(builder);
      if (this.statements != null) {
        this.statements.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromStringStatementStatements(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    if (statements == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends Statement, ?>> each = statements.iterator();
    final List visitables = _visitables.get("statements");
    while (each.hasNext()) {
      VisitableBuilder<? extends Statement, ?> builder = each.next();
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

  public BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item) {
    return new BlockFluentImpl.StringStatementStatementsNestedImpl(-1, item);
  }

  public A addNewStringStatementStatement(String data) {
    return (A) addToStringStatementStatements(new StringStatement(data));
  }

  public A addNewStringStatementStatement(String data, Object[] parameters) {
    return (A) addToStringStatementStatements(new StringStatement(data, parameters));
  }

  public BlockFluent.StringStatementStatementsNested<A> setNewStringStatementStatementLike(Integer index,
      StringStatement item) {
    return new BlockFluentImpl.StringStatementStatementsNestedImpl(index, item);
  }

  public boolean equals(Object o) {
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

  public String toString() {
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
      implements BlockFluent.StringStatementStatementsNested<N>, Nested<N> {
    StringStatementStatementsNestedImpl(Integer index, StringStatement item) {
      this.index = index;
      this.builder = new StringStatementBuilder(this, item);
    }

    StringStatementStatementsNestedImpl() {
      this.index = -1;
      this.builder = new StringStatementBuilder(this);
    }

    StringStatementBuilder builder;
    Integer index;

    public N and() {
      return (N) BlockFluentImpl.this.setToStatements(index, builder.build());
    }

    public N endStringStatementStatement() {
      return and();
    }

  }

}
