/**
 * Test to verify that ternary expressions are properly handled by the source adapter.
 */

package io.sundr.adapter.source;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;

import io.sundr.model.Expression;
import io.sundr.model.Ternary;

public class TernaryExpressionTest {

  @Test
  public void shouldConvertTernaryExpression() throws Exception {
    String javaCode = "public class Test {" +
        "  public String getValue(boolean flag) {" +
        "    return flag ? \"true\" : \"false\";" +
        "  }" +
        "}";

    CompilationUnit cu = StaticJavaParser.parse(javaCode);
    MethodDeclaration method = (MethodDeclaration) cu.getTypes().get(0).getMembers().get(0);
    ReturnStmt returnStmt = (ReturnStmt) method.getBody().get().getStatements().get(0);
    ConditionalExpr conditionalExpr = (ConditionalExpr) returnStmt.getExpression().get();

    // Convert the ternary expression
    Expression converted = ExpressionConverter.convertExpression(conditionalExpr);

    // Verify it's converted to a Ternary
    assertNotNull(converted, "Ternary expression should be converted");
    assertTrue(converted instanceof Ternary, "Should be a Ternary instance");

    Ternary ternary = (Ternary) converted;
    assertNotNull(ternary.getCondition(), "Condition should not be null");
    assertNotNull(ternary.getResult(), "Result should not be null");
    assertNotNull(ternary.getAlternative(), "Alternative should not be null");

    // Verify rendering works without NullPointerException
    String rendered = ternary.render();
    assertNotNull(rendered, "Rendered string should not be null");
    assertTrue(rendered.contains(" ? "), "Should contain ternary operator");
    assertTrue(rendered.contains(" : "), "Should contain colon");
  }
}
