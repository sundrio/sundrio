package io.sundr.adapter.source;

import static io.sundr.adapter.source.ExpressionConverter.convertExpression;
import static io.sundr.adapter.source.ExpressionConverter.convertVarDeclaration;

import java.util.stream.Collectors;

import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;

import io.sundr.model.Do;
import io.sundr.model.For;
import io.sundr.model.Foreach;
import io.sundr.model.If;
import io.sundr.model.StringStatement;
import io.sundr.model.While;

public class StatementConverter {

  public static io.sundr.model.Statement convertStatement(Statement statement) {
    if (statement == null) {
      return null;
    }

    if (statement instanceof IfStmt) {
      IfStmt ifStmt = (IfStmt) statement;
      return new If(convertExpression(ifStmt.getCondition()), convertStatement(ifStmt.getThenStmt()),
          convertStatement(ifStmt.getElseStmt()));
    } else if (statement instanceof WhileStmt) {
      WhileStmt whileStmt = (WhileStmt) statement;
      return new While(convertExpression(whileStmt.getCondition()), convertStatement(whileStmt.getBody()));
    } else if (statement instanceof ForStmt) {
      ForStmt forStmt = (ForStmt) statement;
      return new For(forStmt.getInit().stream().map(ExpressionConverter::convertExpression).collect(Collectors.toList()),
          convertExpression(forStmt.getCompare()),
          forStmt.getUpdate().stream().map(ExpressionConverter::convertExpression).collect(Collectors.toList()),
          convertStatement(forStmt.getBody()));
    } else if (statement instanceof ForeachStmt) {
      ForeachStmt foreachStmt = (ForeachStmt) statement;
      return new Foreach(convertVarDeclaration(foreachStmt.getVariable()), convertExpression(foreachStmt.getIterable()),
          convertStatement(foreachStmt.getBody()));
    } else if (statement instanceof DoStmt) {
      DoStmt doStmt = (DoStmt) statement;
      return new Do(convertExpression(doStmt.getCondition()), convertStatement(doStmt.getBody()));
    } else if (statement instanceof BreakStmt) {
      return new io.sundr.model.Break();
    } else if (statement instanceof ContinueStmt) {
      return new io.sundr.model.Continue();
    }

    return new StringStatement(statement.toString());
  }
}
