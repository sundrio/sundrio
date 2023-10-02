package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class NewArrayFluent<A extends NewArrayFluent<A>> extends BaseFluent<A> {
  public NewArrayFluent() {
  }

  public NewArrayFluent(NewArray instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends TypeRef, ?> type;
  private ArrayList<VisitableBuilder<? extends Expression, ?>> expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();

  protected void copyInstance(NewArray instance) {
    if (instance != null) {
      this.withType(instance.getType());
      this.withExpressions(instance.getExpressions());
    }
  }

  public TypeRef buildType() {
    return this.type != null ? this.type.build() : null;
  }

  public A withType(TypeRef type) {
    if (type == null) {
      this.type = null;
      _visitables.remove("type");
      return (A) this;
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(type);
    _visitables.get("type").clear();
    _visitables.get("type").add(builder);
    this.type = builder;
    return (A) this;
  }

  public boolean hasType() {
    return this.type != null;
  }

  public ClassRefTypeNested<A> withNewClassRefType() {
    return new ClassRefTypeNested(null);
  }

  public ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item) {
    return new ClassRefTypeNested(item);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefType() {
    return new PrimitiveRefTypeNested(null);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item) {
    return new PrimitiveRefTypeNested(item);
  }

  public VoidRefTypeNested<A> withNewVoidRefType() {
    return new VoidRefTypeNested(null);
  }

  public VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item) {
    return new VoidRefTypeNested(item);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefType() {
    return new TypeParamRefTypeNested(null);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item) {
    return new TypeParamRefTypeNested(item);
  }

  public WildcardRefTypeNested<A> withNewWildcardRefType() {
    return new WildcardRefTypeNested(null);
  }

  public WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item) {
    return new WildcardRefTypeNested(item);
  }

  public A addToExpressions(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    _visitables.get("expressions").add(builder);
    this.expressions.add(builder);
    return (A) this;
  }

  public A addToExpressions(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    if (index < 0 || index >= expressions.size()) {
      _visitables.get("expressions").add(builder);
      expressions.add(builder);
    } else {
      _visitables.get("expressions").add(index, builder);
      expressions.add(index, builder);
    }
    return (A) this;
  }

  public A addToExpressions(int index, Expression item) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= expressions.size()) {
      _visitables.get("expressions").add(builder);
      expressions.add(builder);
    } else {
      _visitables.get("expressions").add(index, builder);
      expressions.add(index, builder);
    }
    return (A) this;
  }

  public A setToExpressions(int index, Expression item) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= expressions.size()) {
      _visitables.get("expressions").add(builder);
      expressions.add(builder);
    } else {
      _visitables.get("expressions").set(index, builder);
      expressions.set(index, builder);
    }
    return (A) this;
  }

  public A addToExpressions(io.sundr.model.Expression... items) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("expressions").add(builder);
      this.expressions.add(builder);
    }
    return (A) this;
  }

  public A addAllToExpressions(Collection<Expression> items) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("expressions").add(builder);
      this.expressions.add(builder);
    }
    return (A) this;
  }

  public A removeFromExpressions(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.expressions == null)
      return (A) this;
    _visitables.get("expressions").remove(builder);
    this.expressions.remove(builder);
    return (A) this;
  }

  public A removeFromExpressions(io.sundr.model.Expression... items) {
    if (this.expressions == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("expressions").remove(builder);
      this.expressions.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromExpressions(Collection<Expression> items) {
    if (this.expressions == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("expressions").remove(builder);
      this.expressions.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromExpressions(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    if (expressions == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends Expression, ?>> each = expressions.iterator();
    final List visitables = _visitables.get("expressions");
    while (each.hasNext()) {
      VisitableBuilder<? extends Expression, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<Expression> buildExpressions() {
    return build(expressions);
  }

  public Expression buildExpression(int index) {
    return this.expressions.get(index).build();
  }

  public Expression buildFirstExpression() {
    return this.expressions.get(0).build();
  }

  public Expression buildLastExpression() {
    return this.expressions.get(expressions.size() - 1).build();
  }

  public Expression buildMatchingExpression(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : expressions) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingExpression(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : expressions) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withExpressions(List<Expression> expressions) {
    if (expressions != null) {
      this.expressions = new ArrayList();
      for (Expression item : expressions) {
        this.addToExpressions(item);
      }
    } else {
      this.expressions = null;
    }
    return (A) this;
  }

  public A withExpressions(io.sundr.model.Expression... expressions) {
    if (this.expressions != null) {
      this.expressions.clear();
      _visitables.remove("expressions");
    }
    if (expressions != null) {
      for (Expression item : expressions) {
        this.addToExpressions(item);
      }
    }
    return (A) this;
  }

  public boolean hasExpressions() {
    return expressions != null && !expressions.isEmpty();
  }

  public MultiplyExpressionsNested<A> addNewMultiplyExpression() {
    return new MultiplyExpressionsNested(-1, null);
  }

  public MultiplyExpressionsNested<A> addNewMultiplyExpressionLike(Multiply item) {
    return new MultiplyExpressionsNested(-1, item);
  }

  public A addNewMultiplyExpression(Object left, Object right) {
    return (A) addToExpressions(new Multiply(left, right));
  }

  public MultiplyExpressionsNested<A> setNewMultiplyExpressionLike(int index, Multiply item) {
    return new MultiplyExpressionsNested(index, item);
  }

  public NewArrayExpressionsNested<A> addNewNewArrayExpression() {
    return new NewArrayExpressionsNested(-1, null);
  }

  public NewArrayExpressionsNested<A> addNewNewArrayExpressionLike(NewArray item) {
    return new NewArrayExpressionsNested(-1, item);
  }

  public A addNewNewArrayExpression(Class type, Integer[] sizes) {
    return (A) addToExpressions(new NewArray(type, sizes));
  }

  public NewArrayExpressionsNested<A> setNewNewArrayExpressionLike(int index, NewArray item) {
    return new NewArrayExpressionsNested(index, item);
  }

  public InstanceOfExpressionsNested<A> addNewInstanceOfExpression() {
    return new InstanceOfExpressionsNested(-1, null);
  }

  public InstanceOfExpressionsNested<A> addNewInstanceOfExpressionLike(InstanceOf item) {
    return new InstanceOfExpressionsNested(-1, item);
  }

  public InstanceOfExpressionsNested<A> setNewInstanceOfExpressionLike(int index, InstanceOf item) {
    return new InstanceOfExpressionsNested(index, item);
  }

  public MethodCallExpressionsNested<A> addNewMethodCallExpression() {
    return new MethodCallExpressionsNested(-1, null);
  }

  public MethodCallExpressionsNested<A> addNewMethodCallExpressionLike(MethodCall item) {
    return new MethodCallExpressionsNested(-1, item);
  }

  public MethodCallExpressionsNested<A> setNewMethodCallExpressionLike(int index, MethodCall item) {
    return new MethodCallExpressionsNested(index, item);
  }

  public InverseExpressionsNested<A> addNewInverseExpression() {
    return new InverseExpressionsNested(-1, null);
  }

  public InverseExpressionsNested<A> addNewInverseExpressionLike(Inverse item) {
    return new InverseExpressionsNested(-1, item);
  }

  public InverseExpressionsNested<A> setNewInverseExpressionLike(int index, Inverse item) {
    return new InverseExpressionsNested(index, item);
  }

  public IndexExpressionsNested<A> addNewIndexExpression() {
    return new IndexExpressionsNested(-1, null);
  }

  public IndexExpressionsNested<A> addNewIndexExpressionLike(Index item) {
    return new IndexExpressionsNested(-1, item);
  }

  public IndexExpressionsNested<A> setNewIndexExpressionLike(int index, Index item) {
    return new IndexExpressionsNested(index, item);
  }

  public GreaterThanOrEqualExpressionsNested<A> addNewGreaterThanOrEqualExpression() {
    return new GreaterThanOrEqualExpressionsNested(-1, null);
  }

  public GreaterThanOrEqualExpressionsNested<A> addNewGreaterThanOrEqualExpressionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpressionsNested(-1, item);
  }

  public A addNewGreaterThanOrEqualExpression(Object left, Object right) {
    return (A) addToExpressions(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualExpressionsNested<A> setNewGreaterThanOrEqualExpressionLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpressionsNested(index, item);
  }

  public BitwiseAndExpressionsNested<A> addNewBitwiseAndExpression() {
    return new BitwiseAndExpressionsNested(-1, null);
  }

  public BitwiseAndExpressionsNested<A> addNewBitwiseAndExpressionLike(BitwiseAnd item) {
    return new BitwiseAndExpressionsNested(-1, item);
  }

  public A addNewBitwiseAndExpression(Object left, Object right) {
    return (A) addToExpressions(new BitwiseAnd(left, right));
  }

  public BitwiseAndExpressionsNested<A> setNewBitwiseAndExpressionLike(int index, BitwiseAnd item) {
    return new BitwiseAndExpressionsNested(index, item);
  }

  public MinusExpressionsNested<A> addNewMinusExpression() {
    return new MinusExpressionsNested(-1, null);
  }

  public MinusExpressionsNested<A> addNewMinusExpressionLike(Minus item) {
    return new MinusExpressionsNested(-1, item);
  }

  public A addNewMinusExpression(Object left, Object right) {
    return (A) addToExpressions(new Minus(left, right));
  }

  public MinusExpressionsNested<A> setNewMinusExpressionLike(int index, Minus item) {
    return new MinusExpressionsNested(index, item);
  }

  public LogicalOrExpressionsNested<A> addNewLogicalOrExpression() {
    return new LogicalOrExpressionsNested(-1, null);
  }

  public LogicalOrExpressionsNested<A> addNewLogicalOrExpressionLike(LogicalOr item) {
    return new LogicalOrExpressionsNested(-1, item);
  }

  public A addNewLogicalOrExpression(Object left, Object right) {
    return (A) addToExpressions(new LogicalOr(left, right));
  }

  public LogicalOrExpressionsNested<A> setNewLogicalOrExpressionLike(int index, LogicalOr item) {
    return new LogicalOrExpressionsNested(index, item);
  }

  public NotEqualsExpressionsNested<A> addNewNotEqualsExpression() {
    return new NotEqualsExpressionsNested(-1, null);
  }

  public NotEqualsExpressionsNested<A> addNewNotEqualsExpressionLike(NotEquals item) {
    return new NotEqualsExpressionsNested(-1, item);
  }

  public A addNewNotEqualsExpression(Object left, Object right) {
    return (A) addToExpressions(new NotEquals(left, right));
  }

  public NotEqualsExpressionsNested<A> setNewNotEqualsExpressionLike(int index, NotEquals item) {
    return new NotEqualsExpressionsNested(index, item);
  }

  public DivideExpressionsNested<A> addNewDivideExpression() {
    return new DivideExpressionsNested(-1, null);
  }

  public DivideExpressionsNested<A> addNewDivideExpressionLike(Divide item) {
    return new DivideExpressionsNested(-1, item);
  }

  public A addNewDivideExpression(Object left, Object right) {
    return (A) addToExpressions(new Divide(left, right));
  }

  public DivideExpressionsNested<A> setNewDivideExpressionLike(int index, Divide item) {
    return new DivideExpressionsNested(index, item);
  }

  public LessThanExpressionsNested<A> addNewLessThanExpression() {
    return new LessThanExpressionsNested(-1, null);
  }

  public LessThanExpressionsNested<A> addNewLessThanExpressionLike(LessThan item) {
    return new LessThanExpressionsNested(-1, item);
  }

  public A addNewLessThanExpression(Object left, Object right) {
    return (A) addToExpressions(new LessThan(left, right));
  }

  public LessThanExpressionsNested<A> setNewLessThanExpressionLike(int index, LessThan item) {
    return new LessThanExpressionsNested(index, item);
  }

  public BitwiseOrExpressionsNested<A> addNewBitwiseOrExpression() {
    return new BitwiseOrExpressionsNested(-1, null);
  }

  public BitwiseOrExpressionsNested<A> addNewBitwiseOrExpressionLike(BitwiseOr item) {
    return new BitwiseOrExpressionsNested(-1, item);
  }

  public A addNewBitwiseOrExpression(Object left, Object right) {
    return (A) addToExpressions(new BitwiseOr(left, right));
  }

  public BitwiseOrExpressionsNested<A> setNewBitwiseOrExpressionLike(int index, BitwiseOr item) {
    return new BitwiseOrExpressionsNested(index, item);
  }

  public PropertyRefExpressionsNested<A> addNewPropertyRefExpression() {
    return new PropertyRefExpressionsNested(-1, null);
  }

  public PropertyRefExpressionsNested<A> addNewPropertyRefExpressionLike(PropertyRef item) {
    return new PropertyRefExpressionsNested(-1, item);
  }

  public PropertyRefExpressionsNested<A> setNewPropertyRefExpressionLike(int index, PropertyRef item) {
    return new PropertyRefExpressionsNested(index, item);
  }

  public RightShiftExpressionsNested<A> addNewRightShiftExpression() {
    return new RightShiftExpressionsNested(-1, null);
  }

  public RightShiftExpressionsNested<A> addNewRightShiftExpressionLike(RightShift item) {
    return new RightShiftExpressionsNested(-1, item);
  }

  public A addNewRightShiftExpression(Object left, Object right) {
    return (A) addToExpressions(new RightShift(left, right));
  }

  public RightShiftExpressionsNested<A> setNewRightShiftExpressionLike(int index, RightShift item) {
    return new RightShiftExpressionsNested(index, item);
  }

  public GreaterThanExpressionsNested<A> addNewGreaterThanExpression() {
    return new GreaterThanExpressionsNested(-1, null);
  }

  public GreaterThanExpressionsNested<A> addNewGreaterThanExpressionLike(GreaterThan item) {
    return new GreaterThanExpressionsNested(-1, item);
  }

  public A addNewGreaterThanExpression(Object left, Object right) {
    return (A) addToExpressions(new GreaterThan(left, right));
  }

  public GreaterThanExpressionsNested<A> setNewGreaterThanExpressionLike(int index, GreaterThan item) {
    return new GreaterThanExpressionsNested(index, item);
  }

  public DeclareExpressionsNested<A> addNewDeclareExpression() {
    return new DeclareExpressionsNested(-1, null);
  }

  public DeclareExpressionsNested<A> addNewDeclareExpressionLike(Declare item) {
    return new DeclareExpressionsNested(-1, item);
  }

  public A addNewDeclareExpression(Class type, String name) {
    return (A) addToExpressions(new Declare(type, name));
  }

  public A addNewDeclareExpression(Class type, String name, Object value) {
    return (A) addToExpressions(new Declare(type, name, value));
  }

  public DeclareExpressionsNested<A> setNewDeclareExpressionLike(int index, Declare item) {
    return new DeclareExpressionsNested(index, item);
  }

  public CastExpressionsNested<A> addNewCastExpression() {
    return new CastExpressionsNested(-1, null);
  }

  public CastExpressionsNested<A> addNewCastExpressionLike(Cast item) {
    return new CastExpressionsNested(-1, item);
  }

  public CastExpressionsNested<A> setNewCastExpressionLike(int index, Cast item) {
    return new CastExpressionsNested(index, item);
  }

  public ModuloExpressionsNested<A> addNewModuloExpression() {
    return new ModuloExpressionsNested(-1, null);
  }

  public ModuloExpressionsNested<A> addNewModuloExpressionLike(Modulo item) {
    return new ModuloExpressionsNested(-1, item);
  }

  public A addNewModuloExpression(Object left, Object right) {
    return (A) addToExpressions(new Modulo(left, right));
  }

  public ModuloExpressionsNested<A> setNewModuloExpressionLike(int index, Modulo item) {
    return new ModuloExpressionsNested(index, item);
  }

  public ValueRefExpressionsNested<A> addNewValueRefExpression() {
    return new ValueRefExpressionsNested(-1, null);
  }

  public ValueRefExpressionsNested<A> addNewValueRefExpressionLike(ValueRef item) {
    return new ValueRefExpressionsNested(-1, item);
  }

  public A addNewValueRefExpression(Object value) {
    return (A) addToExpressions(new ValueRef(value));
  }

  public ValueRefExpressionsNested<A> setNewValueRefExpressionLike(int index, ValueRef item) {
    return new ValueRefExpressionsNested(index, item);
  }

  public LeftShiftExpressionsNested<A> addNewLeftShiftExpression() {
    return new LeftShiftExpressionsNested(-1, null);
  }

  public LeftShiftExpressionsNested<A> addNewLeftShiftExpressionLike(LeftShift item) {
    return new LeftShiftExpressionsNested(-1, item);
  }

  public A addNewLeftShiftExpression(Object left, Object right) {
    return (A) addToExpressions(new LeftShift(left, right));
  }

  public LeftShiftExpressionsNested<A> setNewLeftShiftExpressionLike(int index, LeftShift item) {
    return new LeftShiftExpressionsNested(index, item);
  }

  public TernaryExpressionsNested<A> addNewTernaryExpression() {
    return new TernaryExpressionsNested(-1, null);
  }

  public TernaryExpressionsNested<A> addNewTernaryExpressionLike(Ternary item) {
    return new TernaryExpressionsNested(-1, item);
  }

  public TernaryExpressionsNested<A> setNewTernaryExpressionLike(int index, Ternary item) {
    return new TernaryExpressionsNested(index, item);
  }

  public BinaryExpressionExpressionsNested<A> addNewBinaryExpressionExpression() {
    return new BinaryExpressionExpressionsNested(-1, null);
  }

  public BinaryExpressionExpressionsNested<A> addNewBinaryExpressionExpressionLike(BinaryExpression item) {
    return new BinaryExpressionExpressionsNested(-1, item);
  }

  public BinaryExpressionExpressionsNested<A> setNewBinaryExpressionExpressionLike(int index, BinaryExpression item) {
    return new BinaryExpressionExpressionsNested(index, item);
  }

  public EqualsExpressionsNested<A> addNewEqualsExpression() {
    return new EqualsExpressionsNested(-1, null);
  }

  public EqualsExpressionsNested<A> addNewEqualsExpressionLike(Equals item) {
    return new EqualsExpressionsNested(-1, item);
  }

  public A addNewEqualsExpression(Object left, Object right) {
    return (A) addToExpressions(new Equals(left, right));
  }

  public EqualsExpressionsNested<A> setNewEqualsExpressionLike(int index, Equals item) {
    return new EqualsExpressionsNested(index, item);
  }

  public EnclosedExpressionsNested<A> addNewEnclosedExpression() {
    return new EnclosedExpressionsNested(-1, null);
  }

  public EnclosedExpressionsNested<A> addNewEnclosedExpressionLike(Enclosed item) {
    return new EnclosedExpressionsNested(-1, item);
  }

  public EnclosedExpressionsNested<A> setNewEnclosedExpressionLike(int index, Enclosed item) {
    return new EnclosedExpressionsNested(index, item);
  }

  public PreDecrementExpressionsNested<A> addNewPreDecrementExpression() {
    return new PreDecrementExpressionsNested(-1, null);
  }

  public PreDecrementExpressionsNested<A> addNewPreDecrementExpressionLike(PreDecrement item) {
    return new PreDecrementExpressionsNested(-1, item);
  }

  public PreDecrementExpressionsNested<A> setNewPreDecrementExpressionLike(int index, PreDecrement item) {
    return new PreDecrementExpressionsNested(index, item);
  }

  public PostDecrementExpressionsNested<A> addNewPostDecrementExpression() {
    return new PostDecrementExpressionsNested(-1, null);
  }

  public PostDecrementExpressionsNested<A> addNewPostDecrementExpressionLike(PostDecrement item) {
    return new PostDecrementExpressionsNested(-1, item);
  }

  public PostDecrementExpressionsNested<A> setNewPostDecrementExpressionLike(int index, PostDecrement item) {
    return new PostDecrementExpressionsNested(index, item);
  }

  public LambdaExpressionsNested<A> addNewLambdaExpression() {
    return new LambdaExpressionsNested(-1, null);
  }

  public LambdaExpressionsNested<A> addNewLambdaExpressionLike(Lambda item) {
    return new LambdaExpressionsNested(-1, item);
  }

  public LambdaExpressionsNested<A> setNewLambdaExpressionLike(int index, Lambda item) {
    return new LambdaExpressionsNested(index, item);
  }

  public NotExpressionsNested<A> addNewNotExpression() {
    return new NotExpressionsNested(-1, null);
  }

  public NotExpressionsNested<A> addNewNotExpressionLike(Not item) {
    return new NotExpressionsNested(-1, item);
  }

  public NotExpressionsNested<A> setNewNotExpressionLike(int index, Not item) {
    return new NotExpressionsNested(index, item);
  }

  public AssignExpressionsNested<A> addNewAssignExpression() {
    return new AssignExpressionsNested(-1, null);
  }

  public AssignExpressionsNested<A> addNewAssignExpressionLike(Assign item) {
    return new AssignExpressionsNested(-1, item);
  }

  public AssignExpressionsNested<A> setNewAssignExpressionLike(int index, Assign item) {
    return new AssignExpressionsNested(index, item);
  }

  public NegativeExpressionsNested<A> addNewNegativeExpression() {
    return new NegativeExpressionsNested(-1, null);
  }

  public NegativeExpressionsNested<A> addNewNegativeExpressionLike(Negative item) {
    return new NegativeExpressionsNested(-1, item);
  }

  public NegativeExpressionsNested<A> setNewNegativeExpressionLike(int index, Negative item) {
    return new NegativeExpressionsNested(index, item);
  }

  public ThisExpressionsNested<A> addNewThisExpression() {
    return new ThisExpressionsNested(-1, null);
  }

  public ThisExpressionsNested<A> addNewThisExpressionLike(This item) {
    return new ThisExpressionsNested(-1, item);
  }

  public ThisExpressionsNested<A> setNewThisExpressionLike(int index, This item) {
    return new ThisExpressionsNested(index, item);
  }

  public LogicalAndExpressionsNested<A> addNewLogicalAndExpression() {
    return new LogicalAndExpressionsNested(-1, null);
  }

  public LogicalAndExpressionsNested<A> addNewLogicalAndExpressionLike(LogicalAnd item) {
    return new LogicalAndExpressionsNested(-1, item);
  }

  public A addNewLogicalAndExpression(Object left, Object right) {
    return (A) addToExpressions(new LogicalAnd(left, right));
  }

  public LogicalAndExpressionsNested<A> setNewLogicalAndExpressionLike(int index, LogicalAnd item) {
    return new LogicalAndExpressionsNested(index, item);
  }

  public PostIncrementExpressionsNested<A> addNewPostIncrementExpression() {
    return new PostIncrementExpressionsNested(-1, null);
  }

  public PostIncrementExpressionsNested<A> addNewPostIncrementExpressionLike(PostIncrement item) {
    return new PostIncrementExpressionsNested(-1, item);
  }

  public PostIncrementExpressionsNested<A> setNewPostIncrementExpressionLike(int index, PostIncrement item) {
    return new PostIncrementExpressionsNested(index, item);
  }

  public RightUnsignedShiftExpressionsNested<A> addNewRightUnsignedShiftExpression() {
    return new RightUnsignedShiftExpressionsNested(-1, null);
  }

  public RightUnsignedShiftExpressionsNested<A> addNewRightUnsignedShiftExpressionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExpressionsNested(-1, item);
  }

  public A addNewRightUnsignedShiftExpression(Object left, Object right) {
    return (A) addToExpressions(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftExpressionsNested<A> setNewRightUnsignedShiftExpressionLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftExpressionsNested(index, item);
  }

  public PlusExpressionsNested<A> addNewPlusExpression() {
    return new PlusExpressionsNested(-1, null);
  }

  public PlusExpressionsNested<A> addNewPlusExpressionLike(Plus item) {
    return new PlusExpressionsNested(-1, item);
  }

  public A addNewPlusExpression(Object left, Object right) {
    return (A) addToExpressions(new Plus(left, right));
  }

  public PlusExpressionsNested<A> setNewPlusExpressionLike(int index, Plus item) {
    return new PlusExpressionsNested(index, item);
  }

  public ConstructExpressionsNested<A> addNewConstructExpression() {
    return new ConstructExpressionsNested(-1, null);
  }

  public ConstructExpressionsNested<A> addNewConstructExpressionLike(Construct item) {
    return new ConstructExpressionsNested(-1, item);
  }

  public ConstructExpressionsNested<A> setNewConstructExpressionLike(int index, Construct item) {
    return new ConstructExpressionsNested(index, item);
  }

  public XorExpressionsNested<A> addNewXorExpression() {
    return new XorExpressionsNested(-1, null);
  }

  public XorExpressionsNested<A> addNewXorExpressionLike(Xor item) {
    return new XorExpressionsNested(-1, item);
  }

  public A addNewXorExpression(Object left, Object right) {
    return (A) addToExpressions(new Xor(left, right));
  }

  public XorExpressionsNested<A> setNewXorExpressionLike(int index, Xor item) {
    return new XorExpressionsNested(index, item);
  }

  public PreIncrementExpressionsNested<A> addNewPreIncrementExpression() {
    return new PreIncrementExpressionsNested(-1, null);
  }

  public PreIncrementExpressionsNested<A> addNewPreIncrementExpressionLike(PreIncrement item) {
    return new PreIncrementExpressionsNested(-1, item);
  }

  public PreIncrementExpressionsNested<A> setNewPreIncrementExpressionLike(int index, PreIncrement item) {
    return new PreIncrementExpressionsNested(index, item);
  }

  public LessThanOrEqualExpressionsNested<A> addNewLessThanOrEqualExpression() {
    return new LessThanOrEqualExpressionsNested(-1, null);
  }

  public LessThanOrEqualExpressionsNested<A> addNewLessThanOrEqualExpressionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExpressionsNested(-1, item);
  }

  public A addNewLessThanOrEqualExpression(Object left, Object right) {
    return (A) addToExpressions(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualExpressionsNested<A> setNewLessThanOrEqualExpressionLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualExpressionsNested(index, item);
  }

  public PositiveExpressionsNested<A> addNewPositiveExpression() {
    return new PositiveExpressionsNested(-1, null);
  }

  public PositiveExpressionsNested<A> addNewPositiveExpressionLike(Positive item) {
    return new PositiveExpressionsNested(-1, item);
  }

  public PositiveExpressionsNested<A> setNewPositiveExpressionLike(int index, Positive item) {
    return new PositiveExpressionsNested(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    NewArrayFluent that = (NewArrayFluent) o;
    if (!java.util.Objects.equals(type, that.type))
      return false;
    if (!java.util.Objects.equals(expressions, that.expressions))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(type, expressions, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (type != null) {
      sb.append("type:");
      sb.append(type + ",");
    }
    if (expressions != null && !expressions.isEmpty()) {
      sb.append("expressions:");
      sb.append(expressions);
    }
    sb.append("}");
    return sb.toString();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "io.sundr.model." + "ClassRef":
        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);
      case "io.sundr.model." + "PrimitiveRef":
        return (VisitableBuilder<T, ?>) new PrimitiveRefBuilder((PrimitiveRef) item);
      case "io.sundr.model." + "VoidRef":
        return (VisitableBuilder<T, ?>) new VoidRefBuilder((VoidRef) item);
      case "io.sundr.model." + "TypeParamRef":
        return (VisitableBuilder<T, ?>) new TypeParamRefBuilder((TypeParamRef) item);
      case "io.sundr.model." + "WildcardRef":
        return (VisitableBuilder<T, ?>) new WildcardRefBuilder((WildcardRef) item);
      case "io.sundr.model." + "Multiply":
        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);
      case "io.sundr.model." + "NewArray":
        return (VisitableBuilder<T, ?>) new NewArrayBuilder((NewArray) item);
      case "io.sundr.model." + "InstanceOf":
        return (VisitableBuilder<T, ?>) new InstanceOfBuilder((InstanceOf) item);
      case "io.sundr.model." + "MethodCall":
        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);
      case "io.sundr.model." + "Inverse":
        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);
      case "io.sundr.model." + "Index":
        return (VisitableBuilder<T, ?>) new IndexBuilder((Index) item);
      case "io.sundr.model." + "GreaterThanOrEqual":
        return (VisitableBuilder<T, ?>) new GreaterThanOrEqualBuilder((GreaterThanOrEqual) item);
      case "io.sundr.model." + "BitwiseAnd":
        return (VisitableBuilder<T, ?>) new BitwiseAndBuilder((BitwiseAnd) item);
      case "io.sundr.model." + "Minus":
        return (VisitableBuilder<T, ?>) new MinusBuilder((Minus) item);
      case "io.sundr.model." + "LogicalOr":
        return (VisitableBuilder<T, ?>) new LogicalOrBuilder((LogicalOr) item);
      case "io.sundr.model." + "NotEquals":
        return (VisitableBuilder<T, ?>) new NotEqualsBuilder((NotEquals) item);
      case "io.sundr.model." + "Divide":
        return (VisitableBuilder<T, ?>) new DivideBuilder((Divide) item);
      case "io.sundr.model." + "LessThan":
        return (VisitableBuilder<T, ?>) new LessThanBuilder((LessThan) item);
      case "io.sundr.model." + "BitwiseOr":
        return (VisitableBuilder<T, ?>) new BitwiseOrBuilder((BitwiseOr) item);
      case "io.sundr.model." + "PropertyRef":
        return (VisitableBuilder<T, ?>) new PropertyRefBuilder((PropertyRef) item);
      case "io.sundr.model." + "RightShift":
        return (VisitableBuilder<T, ?>) new RightShiftBuilder((RightShift) item);
      case "io.sundr.model." + "GreaterThan":
        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);
      case "io.sundr.model." + "Declare":
        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);
      case "io.sundr.model." + "Cast":
        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);
      case "io.sundr.model." + "Modulo":
        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);
      case "io.sundr.model." + "ValueRef":
        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);
      case "io.sundr.model." + "LeftShift":
        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);
      case "io.sundr.model." + "Ternary":
        return (VisitableBuilder<T, ?>) new TernaryBuilder((Ternary) item);
      case "io.sundr.model." + "BinaryExpression":
        return (VisitableBuilder<T, ?>) new BinaryExpressionBuilder((BinaryExpression) item);
      case "io.sundr.model." + "Equals":
        return (VisitableBuilder<T, ?>) new EqualsBuilder((Equals) item);
      case "io.sundr.model." + "Enclosed":
        return (VisitableBuilder<T, ?>) new EnclosedBuilder((Enclosed) item);
      case "io.sundr.model." + "PreDecrement":
        return (VisitableBuilder<T, ?>) new PreDecrementBuilder((PreDecrement) item);
      case "io.sundr.model." + "PostDecrement":
        return (VisitableBuilder<T, ?>) new PostDecrementBuilder((PostDecrement) item);
      case "io.sundr.model." + "Lambda":
        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);
      case "io.sundr.model." + "Not":
        return (VisitableBuilder<T, ?>) new NotBuilder((Not) item);
      case "io.sundr.model." + "Assign":
        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);
      case "io.sundr.model." + "Negative":
        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);
      case "io.sundr.model." + "This":
        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);
      case "io.sundr.model." + "LogicalAnd":
        return (VisitableBuilder<T, ?>) new LogicalAndBuilder((LogicalAnd) item);
      case "io.sundr.model." + "PostIncrement":
        return (VisitableBuilder<T, ?>) new PostIncrementBuilder((PostIncrement) item);
      case "io.sundr.model." + "RightUnsignedShift":
        return (VisitableBuilder<T, ?>) new RightUnsignedShiftBuilder((RightUnsignedShift) item);
      case "io.sundr.model." + "Plus":
        return (VisitableBuilder<T, ?>) new PlusBuilder((Plus) item);
      case "io.sundr.model." + "Construct":
        return (VisitableBuilder<T, ?>) new ConstructBuilder((Construct) item);
      case "io.sundr.model." + "Xor":
        return (VisitableBuilder<T, ?>) new XorBuilder((Xor) item);
      case "io.sundr.model." + "PreIncrement":
        return (VisitableBuilder<T, ?>) new PreIncrementBuilder((PreIncrement) item);
      case "io.sundr.model." + "LessThanOrEqual":
        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);
      case "io.sundr.model." + "Positive":
        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class ClassRefTypeNested<N> extends ClassRefFluent<ClassRefTypeNested<N>> implements Nested<N> {
    ClassRefTypeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;

    public N and() {
      return (N) NewArrayFluent.this.withType(builder.build());
    }

    public N endClassRefType() {
      return and();
    }

  }

  public class PrimitiveRefTypeNested<N> extends PrimitiveRefFluent<PrimitiveRefTypeNested<N>> implements Nested<N> {
    PrimitiveRefTypeNested(PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBuilder builder;

    public N and() {
      return (N) NewArrayFluent.this.withType(builder.build());
    }

    public N endPrimitiveRefType() {
      return and();
    }

  }

  public class VoidRefTypeNested<N> extends VoidRefFluent<VoidRefTypeNested<N>> implements Nested<N> {
    VoidRefTypeNested(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBuilder builder;

    public N and() {
      return (N) NewArrayFluent.this.withType(builder.build());
    }

    public N endVoidRefType() {
      return and();
    }

  }

  public class TypeParamRefTypeNested<N> extends TypeParamRefFluent<TypeParamRefTypeNested<N>> implements Nested<N> {
    TypeParamRefTypeNested(TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBuilder builder;

    public N and() {
      return (N) NewArrayFluent.this.withType(builder.build());
    }

    public N endTypeParamRefType() {
      return and();
    }

  }

  public class WildcardRefTypeNested<N> extends WildcardRefFluent<WildcardRefTypeNested<N>> implements Nested<N> {
    WildcardRefTypeNested(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBuilder builder;

    public N and() {
      return (N) NewArrayFluent.this.withType(builder.build());
    }

    public N endWildcardRefType() {
      return and();
    }

  }

  public class MultiplyExpressionsNested<N> extends MultiplyFluent<MultiplyExpressionsNested<N>> implements Nested<N> {
    MultiplyExpressionsNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endMultiplyExpression() {
      return and();
    }

  }

  public class NewArrayExpressionsNested<N> extends NewArrayFluent<NewArrayExpressionsNested<N>> implements Nested<N> {
    NewArrayExpressionsNested(int index, NewArray item) {
      this.index = index;
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endNewArrayExpression() {
      return and();
    }

  }

  public class InstanceOfExpressionsNested<N> extends InstanceOfFluent<InstanceOfExpressionsNested<N>> implements Nested<N> {
    InstanceOfExpressionsNested(int index, InstanceOf item) {
      this.index = index;
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endInstanceOfExpression() {
      return and();
    }

  }

  public class MethodCallExpressionsNested<N> extends MethodCallFluent<MethodCallExpressionsNested<N>> implements Nested<N> {
    MethodCallExpressionsNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endMethodCallExpression() {
      return and();
    }

  }

  public class InverseExpressionsNested<N> extends InverseFluent<InverseExpressionsNested<N>> implements Nested<N> {
    InverseExpressionsNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endInverseExpression() {
      return and();
    }

  }

  public class IndexExpressionsNested<N> extends IndexFluent<IndexExpressionsNested<N>> implements Nested<N> {
    IndexExpressionsNested(int index, Index item) {
      this.index = index;
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endIndexExpression() {
      return and();
    }

  }

  public class GreaterThanOrEqualExpressionsNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExpressionsNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualExpressionsNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endGreaterThanOrEqualExpression() {
      return and();
    }

  }

  public class BitwiseAndExpressionsNested<N> extends BitwiseAndFluent<BitwiseAndExpressionsNested<N>> implements Nested<N> {
    BitwiseAndExpressionsNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endBitwiseAndExpression() {
      return and();
    }

  }

  public class MinusExpressionsNested<N> extends MinusFluent<MinusExpressionsNested<N>> implements Nested<N> {
    MinusExpressionsNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endMinusExpression() {
      return and();
    }

  }

  public class LogicalOrExpressionsNested<N> extends LogicalOrFluent<LogicalOrExpressionsNested<N>> implements Nested<N> {
    LogicalOrExpressionsNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endLogicalOrExpression() {
      return and();
    }

  }

  public class NotEqualsExpressionsNested<N> extends NotEqualsFluent<NotEqualsExpressionsNested<N>> implements Nested<N> {
    NotEqualsExpressionsNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endNotEqualsExpression() {
      return and();
    }

  }

  public class DivideExpressionsNested<N> extends DivideFluent<DivideExpressionsNested<N>> implements Nested<N> {
    DivideExpressionsNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endDivideExpression() {
      return and();
    }

  }

  public class LessThanExpressionsNested<N> extends LessThanFluent<LessThanExpressionsNested<N>> implements Nested<N> {
    LessThanExpressionsNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endLessThanExpression() {
      return and();
    }

  }

  public class BitwiseOrExpressionsNested<N> extends BitwiseOrFluent<BitwiseOrExpressionsNested<N>> implements Nested<N> {
    BitwiseOrExpressionsNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endBitwiseOrExpression() {
      return and();
    }

  }

  public class PropertyRefExpressionsNested<N> extends PropertyRefFluent<PropertyRefExpressionsNested<N>> implements Nested<N> {
    PropertyRefExpressionsNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPropertyRefExpression() {
      return and();
    }

  }

  public class RightShiftExpressionsNested<N> extends RightShiftFluent<RightShiftExpressionsNested<N>> implements Nested<N> {
    RightShiftExpressionsNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endRightShiftExpression() {
      return and();
    }

  }

  public class GreaterThanExpressionsNested<N> extends GreaterThanFluent<GreaterThanExpressionsNested<N>> implements Nested<N> {
    GreaterThanExpressionsNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endGreaterThanExpression() {
      return and();
    }

  }

  public class DeclareExpressionsNested<N> extends DeclareFluent<DeclareExpressionsNested<N>> implements Nested<N> {
    DeclareExpressionsNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endDeclareExpression() {
      return and();
    }

  }

  public class CastExpressionsNested<N> extends CastFluent<CastExpressionsNested<N>> implements Nested<N> {
    CastExpressionsNested(int index, Cast item) {
      this.index = index;
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endCastExpression() {
      return and();
    }

  }

  public class ModuloExpressionsNested<N> extends ModuloFluent<ModuloExpressionsNested<N>> implements Nested<N> {
    ModuloExpressionsNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endModuloExpression() {
      return and();
    }

  }

  public class ValueRefExpressionsNested<N> extends ValueRefFluent<ValueRefExpressionsNested<N>> implements Nested<N> {
    ValueRefExpressionsNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endValueRefExpression() {
      return and();
    }

  }

  public class LeftShiftExpressionsNested<N> extends LeftShiftFluent<LeftShiftExpressionsNested<N>> implements Nested<N> {
    LeftShiftExpressionsNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endLeftShiftExpression() {
      return and();
    }

  }

  public class TernaryExpressionsNested<N> extends TernaryFluent<TernaryExpressionsNested<N>> implements Nested<N> {
    TernaryExpressionsNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endTernaryExpression() {
      return and();
    }

  }

  public class BinaryExpressionExpressionsNested<N> extends BinaryExpressionFluent<BinaryExpressionExpressionsNested<N>>
      implements Nested<N> {
    BinaryExpressionExpressionsNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endBinaryExpressionExpression() {
      return and();
    }

  }

  public class EqualsExpressionsNested<N> extends EqualsFluent<EqualsExpressionsNested<N>> implements Nested<N> {
    EqualsExpressionsNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endEqualsExpression() {
      return and();
    }

  }

  public class EnclosedExpressionsNested<N> extends EnclosedFluent<EnclosedExpressionsNested<N>> implements Nested<N> {
    EnclosedExpressionsNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endEnclosedExpression() {
      return and();
    }

  }

  public class PreDecrementExpressionsNested<N> extends PreDecrementFluent<PreDecrementExpressionsNested<N>>
      implements Nested<N> {
    PreDecrementExpressionsNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPreDecrementExpression() {
      return and();
    }

  }

  public class PostDecrementExpressionsNested<N> extends PostDecrementFluent<PostDecrementExpressionsNested<N>>
      implements Nested<N> {
    PostDecrementExpressionsNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPostDecrementExpression() {
      return and();
    }

  }

  public class LambdaExpressionsNested<N> extends LambdaFluent<LambdaExpressionsNested<N>> implements Nested<N> {
    LambdaExpressionsNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endLambdaExpression() {
      return and();
    }

  }

  public class NotExpressionsNested<N> extends NotFluent<NotExpressionsNested<N>> implements Nested<N> {
    NotExpressionsNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endNotExpression() {
      return and();
    }

  }

  public class AssignExpressionsNested<N> extends AssignFluent<AssignExpressionsNested<N>> implements Nested<N> {
    AssignExpressionsNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endAssignExpression() {
      return and();
    }

  }

  public class NegativeExpressionsNested<N> extends NegativeFluent<NegativeExpressionsNested<N>> implements Nested<N> {
    NegativeExpressionsNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endNegativeExpression() {
      return and();
    }

  }

  public class ThisExpressionsNested<N> extends ThisFluent<ThisExpressionsNested<N>> implements Nested<N> {
    ThisExpressionsNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endThisExpression() {
      return and();
    }

  }

  public class LogicalAndExpressionsNested<N> extends LogicalAndFluent<LogicalAndExpressionsNested<N>> implements Nested<N> {
    LogicalAndExpressionsNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endLogicalAndExpression() {
      return and();
    }

  }

  public class PostIncrementExpressionsNested<N> extends PostIncrementFluent<PostIncrementExpressionsNested<N>>
      implements Nested<N> {
    PostIncrementExpressionsNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPostIncrementExpression() {
      return and();
    }

  }

  public class RightUnsignedShiftExpressionsNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExpressionsNested<N>>
      implements Nested<N> {
    RightUnsignedShiftExpressionsNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endRightUnsignedShiftExpression() {
      return and();
    }

  }

  public class PlusExpressionsNested<N> extends PlusFluent<PlusExpressionsNested<N>> implements Nested<N> {
    PlusExpressionsNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPlusExpression() {
      return and();
    }

  }

  public class ConstructExpressionsNested<N> extends ConstructFluent<ConstructExpressionsNested<N>> implements Nested<N> {
    ConstructExpressionsNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endConstructExpression() {
      return and();
    }

  }

  public class XorExpressionsNested<N> extends XorFluent<XorExpressionsNested<N>> implements Nested<N> {
    XorExpressionsNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endXorExpression() {
      return and();
    }

  }

  public class PreIncrementExpressionsNested<N> extends PreIncrementFluent<PreIncrementExpressionsNested<N>>
      implements Nested<N> {
    PreIncrementExpressionsNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPreIncrementExpression() {
      return and();
    }

  }

  public class LessThanOrEqualExpressionsNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExpressionsNested<N>>
      implements Nested<N> {
    LessThanOrEqualExpressionsNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endLessThanOrEqualExpression() {
      return and();
    }

  }

  public class PositiveExpressionsNested<N> extends PositiveFluent<PositiveExpressionsNested<N>> implements Nested<N> {
    PositiveExpressionsNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;
    int index;

    public N and() {
      return (N) NewArrayFluent.this.setToExpressions(index, builder.build());
    }

    public N endPositiveExpression() {
      return and();
    }

  }

}
