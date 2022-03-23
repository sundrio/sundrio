package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
public interface BlockFluent<A extends BlockFluent<A>> extends Fluent<A> {
  public A addToStatements(VisitableBuilder<? extends Statement, ?> builder);

  public A addToStatements(Integer index, io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> builder);

  public A addToStatements(java.lang.Integer index, io.sundr.model.Statement item);

  public A setToStatements(java.lang.Integer index, io.sundr.model.Statement item);

  public A addToStatements(io.sundr.model.Statement... items);

  public A addAllToStatements(Collection<io.sundr.model.Statement> items);

  public A removeFromStatements(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?> builder);

  public A removeFromStatements(io.sundr.model.Statement... items);

  public A removeAllFromStatements(java.util.Collection<io.sundr.model.Statement> items);

  /**
   * This method has been deprecated, please use method buildStatements instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.Statement> getStatements();

  public java.util.List<io.sundr.model.Statement> buildStatements();

  public io.sundr.model.Statement buildStatement(java.lang.Integer index);

  public io.sundr.model.Statement buildFirstStatement();

  public io.sundr.model.Statement buildLastStatement();

  public io.sundr.model.Statement buildMatchingStatement(
      Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> predicate);

  public Boolean hasMatchingStatement(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> predicate);

  public A withStatements(java.util.List<io.sundr.model.Statement> statements);

  public A withStatements(io.sundr.model.Statement... statements);

  public java.lang.Boolean hasStatements();

  public A addToStringStatementStatements(java.lang.Integer index, StringStatement item);

  public A setToStringStatementStatements(java.lang.Integer index, io.sundr.model.StringStatement item);

  public A addToStringStatementStatements(io.sundr.model.StringStatement... items);

  public A addAllToStringStatementStatements(java.util.Collection<io.sundr.model.StringStatement> items);

  public A removeFromStringStatementStatements(io.sundr.model.StringStatement... items);

  public A removeAllFromStringStatementStatements(java.util.Collection<io.sundr.model.StringStatement> items);

  public A removeMatchingFromStringStatementStatements(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.Statement, ?>> predicate);

  public BlockFluent.StringStatementStatementsNested<A> setNewStringStatementStatementLike(java.lang.Integer index,
      io.sundr.model.StringStatement item);

  public io.sundr.model.BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatement();

  public io.sundr.model.BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatementLike(
      io.sundr.model.StringStatement item);

  public A addNewStringStatementStatement(String data);

  public A addNewStringStatementStatement(java.lang.String data, Object[] parameters);

  public interface StringStatementStatementsNested<N>
      extends Nested<N>, StringStatementFluent<BlockFluent.StringStatementStatementsNested<N>> {
    public N and();

    public N endStringStatementStatement();

  }

}
