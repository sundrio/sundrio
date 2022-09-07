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

  public A addToStatements(Integer index, VisitableBuilder<? extends Statement, ?> builder);

  public A addToStatements(Integer index, Statement item);

  public A setToStatements(Integer index, Statement item);

  public A addToStatements(io.sundr.model.Statement... items);

  public A addAllToStatements(Collection<Statement> items);

  public A removeFromStatements(VisitableBuilder<? extends Statement, ?> builder);

  public A removeFromStatements(io.sundr.model.Statement... items);

  public A removeAllFromStatements(Collection<Statement> items);

  /**
   * This method has been deprecated, please use method buildStatements instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<Statement> getStatements();

  public List<Statement> buildStatements();

  public Statement buildStatement(Integer index);

  public Statement buildFirstStatement();

  public Statement buildLastStatement();

  public Statement buildMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate);

  public Boolean hasMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate);

  public A withStatements(List<Statement> statements);

  public A withStatements(io.sundr.model.Statement... statements);

  public Boolean hasStatements();

  public A addToStringStatementStatements(Integer index, StringStatement item);

  public A setToStringStatementStatements(Integer index, StringStatement item);

  public A addToStringStatementStatements(io.sundr.model.StringStatement... items);

  public A addAllToStringStatementStatements(Collection<StringStatement> items);

  public A removeFromStringStatementStatements(io.sundr.model.StringStatement... items);

  public A removeAllFromStringStatementStatements(Collection<StringStatement> items);

  public A removeMatchingFromStringStatementStatements(Predicate<VisitableBuilder<? extends Statement, ?>> predicate);

  public BlockFluent.StringStatementStatementsNested<A> setNewStringStatementStatementLike(Integer index, StringStatement item);

  public BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatement();

  public BlockFluent.StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item);

  public A addNewStringStatementStatement(String data);

  public A addNewStringStatementStatement(String data, Object[] parameters);

  public interface StringStatementStatementsNested<N>
      extends Nested<N>, StringStatementFluent<BlockFluent.StringStatementStatementsNested<N>> {
    public N and();

    public N endStringStatementStatement();

  }

}
