package io.sundr.model;

import static java.util.regex.Pattern.quote;
import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

public class WhileTest {

  static final ClassRef STRING = ClassRef.forName("java.lang.String");
  static final ClassRef INTEGER = ClassRef.forName("java.lang.Integer");

  static String unindent(String s) {
    return s.replaceAll(Pattern.quote(Node.NEWLINE), "").replaceAll(quote("{  "), "{");
  }

  @Test
  public void testSimpleCondition() {
    Property x = Property.newProperty(INTEGER, "x");
    Property y = Property.newProperty(INTEGER, "y");
    While stmt = While.lt(x.toReference(), ValueRef.from(10))
        .body(new Assign(x, x.toReference().plus(1)));
    assertEquals("while (x < 10) {x = x + 1;}", unindent(stmt.render()));
  }

  @Test
  public void testConvenienceMethod() {
    Property x = Property.newProperty(INTEGER, "x");
    Property y = Property.newProperty(INTEGER, "y");
    While stmt = While.lt(x.toReference(), ValueRef.from(10))
        .body(new Assign(x, x.toReference().plus(1)));
    assertEquals("while (x < 10) {x = x + 1;}", unindent(stmt.render()));
  }
}
