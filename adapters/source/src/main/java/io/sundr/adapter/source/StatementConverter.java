package io.sundr.adapter.source;

import static io.sundr.adapter.source.ExpressionConverter.convertExpression;
import static io.sundr.adapter.source.ExpressionConverter.convertParameter;
import static io.sundr.adapter.source.ExpressionConverter.convertVarDeclaration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import io.sundr.model.Block;
import io.sundr.model.Break;
import io.sundr.model.Continue;
import io.sundr.model.Do;
import io.sundr.model.Empty;
import io.sundr.model.For;
import io.sundr.model.Foreach;
import io.sundr.model.If;
import io.sundr.model.MethodCall;
import io.sundr.model.Property;
import io.sundr.model.Return;
import io.sundr.model.Switch;
import io.sundr.model.Throw;
import io.sundr.model.Try;
import io.sundr.model.While;

public class StatementConverter {

  public static io.sundr.model.Statement convertStatement(Statement statement) {
    if (statement == null) {
      return new Empty();
    }

    if (statement instanceof IfStmt) {
      IfStmt ifStmt = (IfStmt) statement;
      return If.condition(convertExpression(ifStmt.getCondition()))
          .then(convertStatement(ifStmt.getThenStmt()))
          .orElse(convertStatement(ifStmt.getElseStmt()));
    } else if (statement instanceof WhileStmt) {
      WhileStmt whileStmt = (WhileStmt) statement;
      return While.condition(convertExpression(whileStmt.getCondition()))
          .body(convertStatement(whileStmt.getBody()));
    } else if (statement instanceof ForStmt) {
      ForStmt forStmt = (ForStmt) statement;
      List<io.sundr.model.Expression> initExpressions = forStmt.getInit().stream()
          .map(ExpressionConverter::convertExpression)
          .collect(Collectors.toList());
      List<io.sundr.model.Expression> updateExpressions = forStmt.getUpdate().stream()
          .map(ExpressionConverter::convertExpression)
          .collect(Collectors.toList());
      return For.init(initExpressions)
          .compare(convertExpression(forStmt.getCompare()))
          .update(updateExpressions)
          .body(convertStatement(forStmt.getBody()));
    } else if (statement instanceof ForeachStmt) {
      ForeachStmt foreachStmt = (ForeachStmt) statement;
      return new Foreach(convertVarDeclaration(foreachStmt.getVariable()), convertExpression(foreachStmt.getIterable()),
          convertStatement(foreachStmt.getBody()));
    } else if (statement instanceof DoStmt) {
      DoStmt doStmt = (DoStmt) statement;
      return new Do(convertExpression(doStmt.getCondition()), convertStatement(doStmt.getBody()));
    } else if (statement instanceof BreakStmt) {
      return new Break();
    } else if (statement instanceof ContinueStmt) {
      return new Continue();
    } else if (statement instanceof ReturnStmt) {
      ReturnStmt returnStmt = (ReturnStmt) statement;
      return returnStmt.getExpr() != null
          ? new Return(convertExpression(returnStmt.getExpr()))
          : Return.Nothing();
    } else if (statement instanceof ThrowStmt) {
      ThrowStmt throwStmt = (ThrowStmt) statement;
      return new Throw(convertExpression(throwStmt.getExpr()));
    } else if (statement instanceof BlockStmt) {
      return new BlockStmtToBlock().apply((BlockStmt) statement);
    } else if (statement instanceof ExpressionStmt) {
      ExpressionStmt exprStmt = (ExpressionStmt) statement;
      return (io.sundr.model.Statement) convertExpression(exprStmt.getExpression());
    } else if (statement instanceof SwitchStmt) {
      SwitchStmt switchStmt = (SwitchStmt) statement;
      // For now, create a basic switch with empty cases
      // TODO: Implement full switch case parsing
      return Switch.expression(convertExpression(switchStmt.getSelector())).defaultCase();
    } else if (statement instanceof EmptyStmt) {
      return new Empty();
    } else if (statement instanceof TryStmt) {
      TryStmt tryStmt = (TryStmt) statement;

      // Handle try-with-resources
      List<io.sundr.model.Statement> resources = new ArrayList<>();
      for (VariableDeclarationExpr resource : tryStmt.getResources()) {
        resources.add(convertVarDeclaration(resource));
      }

      Block tryBlock = new BlockStmtToBlock().apply(tryStmt.getTryBlock());

      List<Try.Catch> catchBlocks = new ArrayList<>();
      for (CatchClause catchClause : tryStmt.getCatchs()) {
        Property parameter = convertParameter(catchClause.getParam());
        Block catchBlock = new BlockStmtToBlock().apply(catchClause.getCatchBlock());
        catchBlocks.add(new Try.Catch(parameter, catchBlock));
      }

      Optional<Block> finallyBlock = tryStmt.getFinallyBlock() != null
          ? Optional.of(new BlockStmtToBlock().apply(tryStmt.getFinallyBlock()))
          : Optional.empty();

      return new Try(resources, tryBlock, catchBlocks, finallyBlock);
    } else if (statement instanceof ExplicitConstructorInvocationStmt) {
      ExplicitConstructorInvocationStmt explicitStmt = (ExplicitConstructorInvocationStmt) statement;

      // Convert this() or super() calls to MethodCall
      String methodName = explicitStmt.isThis() ? "this" : "super";

      // Convert arguments
      List<io.sundr.model.Expression> arguments = explicitStmt.getArgs().stream()
          .map(ExpressionConverter::convertExpression)
          .collect(Collectors.toList());

      return new MethodCall(methodName, null, Collections.emptyList(), arguments);
    } else if (statement instanceof com.github.javaparser.ast.stmt.SynchronizedStmt) {
      com.github.javaparser.ast.stmt.SynchronizedStmt syncStmt = (com.github.javaparser.ast.stmt.SynchronizedStmt) statement;

      // Convert synchronized statement to our model
      io.sundr.model.Expression lockExpression = ExpressionConverter.convertExpression(syncStmt.getExpr());
      io.sundr.model.Statement body = new BlockStmtToBlock().apply(syncStmt.getBlock());

      return new io.sundr.model.Synchronized(lockExpression, body);
    }

    throw new IllegalArgumentException("Statement of type " + statement.getClass().getName());
  }
}
