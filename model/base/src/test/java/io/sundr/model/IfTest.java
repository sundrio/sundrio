package io.sundr.model;

import static java.util.regex.Pattern.quote;
import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

public class IfTest {

  static final ClassRef STRING = ClassRef.forName("java.lang.String");
  static final ClassRef INTEGER = ClassRef.forName("java.lang.Integer");

  static String unindent(String s) {
    return s.replaceAll(Pattern.quote(Node.NEWLINE), "").replaceAll(quote("{  "), "{");
  }

  @Test
  public void testSingleCondition() {
    Property x = Property.newProperty(INTEGER, "x");
    If stmt = If.condition(ValueRef.from(true)).then(new Assign(x, 1)).end();
    assertEquals("if (true) {x = 1;}", unindent(stmt.render()));
  }

  @Test
  public void testElse() {
    Property x = Property.newProperty(INTEGER, "x");
    If stmt = If.condition(ValueRef.from(true)).then(new Assign(x, 1)).orElse(new Assign(x, 2));
    assertEquals("if (true) {x = 1;} else {x = 2;}", unindent(stmt.render()));
  }

  @Test
  public void testElseIf() {
    Property x = Property.newProperty(INTEGER, "x");
    Property y = Property.newProperty(INTEGER, "y");
    Property z = Property.newProperty(INTEGER, "z");

    If stmt = If.lt(x.toReference(), y.toReference()).then(new Assign(z, 1))
        .orElse(If.eq(x.toReference(), y.toReference()).then(new Assign(z, 2))
            .orElse(new Assign(z, 3)));
    assertEquals("if (x < y) {z = 1;} else if (x == y) {z = 2;} else {z = 3;}", unindent(stmt.render()));
  }

}
