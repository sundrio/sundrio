/**
 * Test to verify that ternary expressions are properly handled by the source adapter.
 */

package io.sundr.adapter.source;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.Test;

import com.github.javaparser.JavaParser;
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

    CompilationUnit cu = JavaParser.parse(new StringReader(javaCode));
    MethodDeclaration method = (MethodDeclaration) cu.getTypes().get(0).getMembers().get(0);
    ReturnStmt returnStmt = (ReturnStmt) method.getBody().getStmts().get(0);
    ConditionalExpr conditionalExpr = (ConditionalExpr) returnStmt.getExpr();

    // Convert the ternary expression
    Expression converted = ExpressionConverter.convertExpression(conditionalExpr);

    // Verify it's converted to a Ternary
    assertNotNull("Ternary expression should be converted", converted);
    assertTrue("Should be a Ternary instance", converted instanceof Ternary);

    Ternary ternary = (Ternary) converted;
    assertNotNull("Condition should not be null", ternary.getCondition());
    assertNotNull("Result should not be null", ternary.getResult());
    assertNotNull("Alternative should not be null", ternary.getAlternative());

    // Verify rendering works without NullPointerException
    String rendered = ternary.render();
    assertNotNull("Rendered string should not be null", rendered);
    assertTrue("Should contain ternary operator", rendered.contains(" ? "));
    assertTrue("Should contain colon", rendered.contains(" : "));
  }
}
