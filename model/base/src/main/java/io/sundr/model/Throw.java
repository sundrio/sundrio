package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Throw implements Statement {

  private final Expression exception;

  public Throw(Expression exception) {
    this.exception = exception;
  }

  public Expression getException() {
    return exception;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    refs.addAll(exception.getReferences());
    return refs;
  }

  @Override
  public String render() {
    return "throw " + exception.renderExpression() + SEMICOLN;
  }

  //
  // Static Factory Methods
  //

  /**
   * Creates a throw statement for a new instance of the specified exception class with a message.
   *
   * @param exceptionClass The exception class
   * @param message The exception message
   * @return A new Throw statement
   */
  public static Throw newInstance(Class<?> exceptionClass, String message) {
    return new Throw(Expression.createNew(exceptionClass, ValueRef.from(message)));
  }

  /**
   * Creates a throw statement for a new instance of the specified exception class with a message.
   *
   * @param exceptionClassRef The exception class reference
   * @param message The exception message
   * @return A new Throw statement
   */
  public static Throw newInstance(ClassRef exceptionClassRef, String message) {
    return new Throw(Expression.createNew(exceptionClassRef, ValueRef.from(message)));
  }

  /**
   * Creates a throw statement for a new instance of the specified exception class with an expression message.
   *
   * @param exceptionClass The exception class
   * @param messageExpression The exception message expression
   * @return A new Throw statement
   */
  public static Throw newInstance(Class<?> exceptionClass, Expression messageExpression) {
    return new Throw(Expression.createNew(exceptionClass, messageExpression));
  }

  /**
   * Creates a throw statement for a new instance of the specified exception class with an expression message.
   *
   * @param exceptionClassRef The exception class reference
   * @param messageExpression The exception message expression
   * @return A new Throw statement
   */
  public static Throw newInstance(ClassRef exceptionClassRef, Expression messageExpression) {
    return new Throw(Expression.createNew(exceptionClassRef, messageExpression));
  }

  /**
   * Creates a throw statement for RuntimeException with a simple message.
   *
   * @param message The exception message
   * @return A new Throw statement
   */
  public static Throw runtimeException(String message) {
    return newInstance(RuntimeException.class, message);
  }

  /**
   * Creates a throw statement for RuntimeException with an expression message.
   *
   * @param messageExpression The exception message expression
   * @return A new Throw statement
   */
  public static Throw runtimeException(Expression messageExpression) {
    return newInstance(RuntimeException.class, messageExpression);
  }

  /**
   * Creates a throw statement for IllegalArgumentException with a simple message.
   *
   * @param message The exception message
   * @return A new Throw statement
   */
  public static Throw illegalArgument(String message) {
    return newInstance(IllegalArgumentException.class, message);
  }

  /**
   * Creates a throw statement for IllegalArgumentException with an expression message.
   *
   * @param messageExpression The exception message expression
   * @return A new Throw statement
   */
  public static Throw illegalArgument(Expression messageExpression) {
    return newInstance(IllegalArgumentException.class, messageExpression);
  }

  /**
   * Creates a throw statement for IllegalStateException with a simple message.
   *
   * @param message The exception message
   * @return A new Throw statement
   */
  public static Throw illegalState(String message) {
    return newInstance(IllegalStateException.class, message);
  }

  /**
   * Creates a throw statement for IllegalStateException with an expression message.
   *
   * @param messageExpression The exception message expression
   * @return A new Throw statement
   */
  public static Throw illegalState(Expression messageExpression) {
    return newInstance(IllegalStateException.class, messageExpression);
  }
}