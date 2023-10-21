package io.sundr.model;

import java.util.function.Function;

public interface Expression extends Renderable {

  public static Expression NULL = new ValueRef(null);

  default String renderExpression() {
    return render();
  }

  public static Expression not(Expression expression) {
    return new Not(expression);
  }

  default Expression not() {
    return new Not(this);
  }

  public static Expression and(Expression left, Expression right) {
    return new LogicalAnd(left, right);
  }

  default Expression and(Expression expression) {
    return and(this, expression);
  }

  default Expression and(Function<Expression, Expression> function) {
    return and(this, function.apply(this));
  }

  public static Expression or(Expression left, Expression right) {
    return new LogicalOr(left, right);
  }

  default Expression or(Expression expression) {
    return or(this, expression);
  }

  default Expression or(Function<Expression, Expression> function) {
    return or(this, function.apply(this));
  }

  public static Expression enclosed(Expression expression) {
    return new Enclosed(expression);
  }

  default Expression enclosed() {
    return new Enclosed(this);
  }

  public static Expression positive(Expression expression) {
    return new Positive(expression);
  }

  default Expression positive() {
    return new Positive(this);
  }

  public static Expression negative(Expression expression) {
    return new Negative(expression);
  }

  default Expression negative() {
    return new Negative(this);
  }

  public static Expression cast(TypeRef type, Expression expression) {
    return new Cast(type, expression);
  }

  default Expression cast(TypeRef type) {
    return new Cast(type, this);
  }

  public static Expression cast(Class type, Expression expression) {
    return cast(ClassRef.forClass(type), expression);
  }

  default Expression cast(Class type) {
    return cast(ClassRef.forClass(type));
  }

  public static Expression eq(Expression left, Expression right) {
    return new Equals(left, right);
  }

  default Expression eq(Expression expression) {
    return new Equals(this, expression);
  }

  public static Expression ne(Expression left, Expression right) {
    return new NotEquals(left, right);
  }

  default Expression ne(Expression expression) {
    return new NotEquals(this, expression);
  }

  public static Expression notNull(Expression expression) {
    return new NotEquals(expression, ValueRef.from(null));
  }

  default Expression notNull() {
    return notNull(this);
  }

  public static Expression isNull(Expression expression) {
    return new Equals(expression, ValueRef.from(null));
  }

  default Expression isNull() {
    return isNull(this);
  }

  default Expression instanceOf(ClassRef classRef) {
    return new InstanceOf(this, classRef);
  }

  default Expression instanceOf(Class c) {
    return new InstanceOf(this, c);
  }

  public static Expression plus(Expression left, Expression right) {
    return new Plus(left, right);
  }

  default Expression plus(Expression expression) {
    return new Plus(this, expression);
  }

  default Expression plus(Number number) {
    return new Plus(this, number);
  }

  public static Expression minus(Expression left, Expression right) {
    return new Minus(left, right);
  }

  default Expression minus(Expression expression) {
    return new Minus(this, expression);
  }

  default Expression minus(Number number) {
    return new Minus(this, number);
  }

  public static Expression multiply(Expression left, Expression right) {
    return new Multiply(left, right);
  }

  default Expression multiply(Expression expression) {
    return new Multiply(this, expression);
  }

  public static Expression divide(Expression left, Expression right) {
    return new Divide(left, right);
  }

  default Expression divide(Expression expression) {
    return new Divide(this, expression);
  }

  public static Expression modulo(Expression left, Expression right) {
    return new Modulo(left, right);
  }

  default Expression modulo(Expression expression) {
    return new Modulo(this, expression);
  }

  default Expression property(String name) {
    return new PropertyRef(name, this);
  }

  default Expression property(TypeRef type, String name) {
    return new PropertyRef(type, name, this);
  }

  default Expression property(Property property) {
    return new PropertyRef(property, this);
  }

  public static MethodCall call(Class type, String name, Expression... expression) {
    return new MethodCall(name, type, expression);
  }

  public static MethodCall call(TypeRef type, String name, Expression... expression) {
    return new MethodCall(name, type, expression);
  }

  public static MethodCall call(TypeDef type, String name, Expression... expression) {
    return new MethodCall(name, type, expression);
  }

  public static MethodCall newCall(String name, Expression... expression) {
    return new MethodCall(name, (Expression) null, expression);
  }

  default MethodCall call(String name, Expression... expression) {
    return new MethodCall(name, this, expression);
  }

  default Index index(Expression expression) {
    return new Index(this, expression);
  }

  default Index index(int index) {
    return new Index(this, ValueRef.from(index));
  }

  default Expression postIncrement() {
    return new PostIncrement(this);
  }

  default Expression postDecrement() {
    return new PostDecrement(this);
  }

  default Expression preIncrement() {
    return new PreIncrement(this);
  }

  default Expression pretDecrement() {
    return new PreDecrement(this);
  }

  public static Ternary ternary(Expression condition, Expression thenExpression, Expression elseExpression) {
    return new Ternary(condition, thenExpression, elseExpression);
  }

  default Assign assign(Expression expression) {
    return new Assign(this, expression);
  }

  default Assign assign(Property property) {
    return new Assign(this, property.toReference());
  }

  default Assign assignNew(Class type, Expression... arguments) {
    return new Assign(this, new Construct(type, arguments));
  }

  default Assign assignNew(ClassRef type, Expression... arguments) {
    return new Assign(this, new Construct(type, arguments));
  }

  default Assign assignNull() {
    return new Assign(this, ValueRef.NULL);
  }

  public static Construct createNew(Class type, Expression... arguments) {
    return new Construct(type, arguments);
  }

  public static Construct createNew(ClassRef type, Expression... arguments) {
    return new Construct(type, arguments);
  }

  public static NewArray createNewArray(Class type, Expression... expressions) {
    return new NewArray(type, expressions);
  }

  public static NewArray createNewArray(TypeRef type, Expression... expressions) {
    return new NewArray(type, expressions);
  }

  public static NewArray createNewArray(Class type, Integer... sizes) {
    return new NewArray(type, sizes);
  }

  public static NewArray createNewArray(TypeRef type, Integer... sizes) {
    return new NewArray(type, sizes);
  }

  public static Lambda lamba(Property parameter, Statement statement) {
    return new Lambda(parameter.getName(), statement);
  }

  public static Lambda lamba(Property parameter, Expression expression) {
    return new Lambda(parameter.getName(), expression);
  }

  public static Lambda lamba(String parameter, Expression expression) {
    return new Lambda(parameter, expression);
  }

  default Statement ret() {
    return Statement.ret(this);
  }
}
