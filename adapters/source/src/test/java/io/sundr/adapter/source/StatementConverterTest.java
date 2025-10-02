package io.sundr.adapter.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;

import io.sundr.model.Empty;
import io.sundr.model.Return;

public class StatementConverterTest {

  @Test
  public void testReturnWithValue() {
    ReturnStmt returnStmt = new ReturnStmt(new IntegerLiteralExpr("42"));

    io.sundr.model.Statement converted = StatementConverter.convertStatement(returnStmt);
    assertTrue(converted instanceof Return);

    Return returnModel = (Return) converted;
    assertNotNull(returnModel.getExpression());
  }

  @Test
  public void testReturnVoid() {
    ReturnStmt returnStmt = new ReturnStmt();

    io.sundr.model.Statement converted = StatementConverter.convertStatement(returnStmt);
    assertTrue(converted instanceof Return);

    Return returnModel = (Return) converted;
    assertNull(returnModel.getExpression());
  }

  @Test
  public void testBreakStatement() {
    BreakStmt breakStmt = new BreakStmt();

    io.sundr.model.Statement converted = StatementConverter.convertStatement(breakStmt);
    assertTrue(converted instanceof io.sundr.model.Break);
  }

  @Test
  public void testContinueStatement() {
    ContinueStmt continueStmt = new ContinueStmt();

    io.sundr.model.Statement converted = StatementConverter.convertStatement(continueStmt);
    assertTrue(converted instanceof io.sundr.model.Continue);
  }

  @Test
  public void testEmptyStatement() {
    EmptyStmt emptyStmt = new EmptyStmt();

    io.sundr.model.Statement converted = StatementConverter.convertStatement(emptyStmt);
    assertTrue(converted instanceof Empty);
  }

  @Test
  public void testBlockStatement() {
    BlockStmt blockStmt = new BlockStmt();

    io.sundr.model.Statement converted = StatementConverter.convertStatement(blockStmt);
    assertTrue(converted instanceof io.sundr.model.Block);
  }

  @Test
  public void testNullStatement() {
    io.sundr.model.Statement converted = StatementConverter.convertStatement(null);
    assertEquals(new Empty(), converted);
  }
}
