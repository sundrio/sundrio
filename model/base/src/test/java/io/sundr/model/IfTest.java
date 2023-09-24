package io.sundr.model;

import static java.util.regex.Pattern.quote;
import static org.junit.Assert.assertEquals;

import java.util.Optional;
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
    If stmt = new If(ValueRef.from(true), new Assign(x, 1).asStatement(), Optional.empty());
    assertEquals("if (true) {x = 1;}", unindent(stmt.render()));
  }

  @Test
  public void testElse() {
    Property x = Property.newProperty(INTEGER, "x");
    If stmt = new If(ValueRef.from(true), new Assign(x, 1).asStatement(), new Assign(x, 2).asStatement());
    assertEquals("if (true) {x = 1;} else {x = 2;}", unindent(stmt.render()));
  }

  @Test
  public void testElseIf() {
    Property x = Property.newProperty(INTEGER, "x");
    Property y = Property.newProperty(INTEGER, "y");
    Property z = Property.newProperty(INTEGER, "z");

    If stmt = new If(new LessThan(x, y), new Assign(z, 1).asStatement(),
        new If(new Equals(x, y), new Assign(z, 2).asStatement(),
            Optional.of(new Assign(z, 3).asStatement())));
    assertEquals("if (x < y) {z = 1;} else if (x == y) {z = 2;} else {z = 3;}", unindent(stmt.render()));
  }

}
