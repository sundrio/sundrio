package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class BinaryExpressionFluent<A extends BinaryExpressionFluent<A>> extends BaseFluent<A> {
  public BinaryExpressionFluent() {
  }

  public BinaryExpressionFluent(BinaryExpression instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> left;
  private VisitableBuilder<? extends Expression, ?> right;

  protected void copyInstance(BinaryExpression instance) {
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
  }

  public Expression buildLeft() {
    return this.left != null ? this.left.build() : null;
  }

  public A withLeft(Expression left) {
    if (left == null) {
      this.left = null;
      this._visitables.remove("left");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(left);
      ;
      this._visitables.get("left").clear();
      this._visitables.get("left").add(builder);
      this.left = builder;
      return (A) this;
    }
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public MultiplyLeftNested<A> withNewMultiplyLeft() {
    return new MultiplyLeftNested(null);
  }

  public MultiplyLeftNested<A> withNewMultiplyLeftLike(Multiply item) {
    return new MultiplyLeftNested(item);
  }

  public A withNewMultiplyLeft(Object left, Object right) {
    return (A) withLeft(new Multiply(left, right));
  }

  public NewArrayLeftNested<A> withNewNewArrayLeft() {
    return new NewArrayLeftNested(null);
  }

  public NewArrayLeftNested<A> withNewNewArrayLeftLike(NewArray item) {
    return new NewArrayLeftNested(item);
  }

  public A withNewNewArrayLeft(Class type, Integer[] sizes) {
    return (A) withLeft(new NewArray(type, sizes));
  }

  public InstanceOfLeftNested<A> withNewInstanceOfLeft() {
    return new InstanceOfLeftNested(null);
  }

  public InstanceOfLeftNested<A> withNewInstanceOfLeftLike(InstanceOf item) {
    return new InstanceOfLeftNested(item);
  }

  public MethodCallLeftNested<A> withNewMethodCallLeft() {
    return new MethodCallLeftNested(null);
  }

  public MethodCallLeftNested<A> withNewMethodCallLeftLike(MethodCall item) {
    return new MethodCallLeftNested(item);
  }

  public InverseLeftNested<A> withNewInverseLeft() {
    return new InverseLeftNested(null);
  }

  public InverseLeftNested<A> withNewInverseLeftLike(Inverse item) {
    return new InverseLeftNested(item);
  }

  public IndexLeftNested<A> withNewIndexLeft() {
    return new IndexLeftNested(null);
  }

  public IndexLeftNested<A> withNewIndexLeftLike(Index item) {
    return new IndexLeftNested(item);
  }

  public GreaterThanOrEqualLeftNested<A> withNewGreaterThanOrEqualLeft() {
    return new GreaterThanOrEqualLeftNested(null);
  }

  public GreaterThanOrEqualLeftNested<A> withNewGreaterThanOrEqualLeftLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualLeftNested(item);
  }

  public A withNewGreaterThanOrEqualLeft(Object left, Object right) {
    return (A) withLeft(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndLeftNested<A> withNewBitwiseAndLeft() {
    return new BitwiseAndLeftNested(null);
  }

  public BitwiseAndLeftNested<A> withNewBitwiseAndLeftLike(BitwiseAnd item) {
    return new BitwiseAndLeftNested(item);
  }

  public A withNewBitwiseAndLeft(Object left, Object right) {
    return (A) withLeft(new BitwiseAnd(left, right));
  }

  public MinusLeftNested<A> withNewMinusLeft() {
    return new MinusLeftNested(null);
  }

  public MinusLeftNested<A> withNewMinusLeftLike(Minus item) {
    return new MinusLeftNested(item);
  }

  public A withNewMinusLeft(Object left, Object right) {
    return (A) withLeft(new Minus(left, right));
  }

  public LogicalOrLeftNested<A> withNewLogicalOrLeft() {
    return new LogicalOrLeftNested(null);
  }

  public LogicalOrLeftNested<A> withNewLogicalOrLeftLike(LogicalOr item) {
    return new LogicalOrLeftNested(item);
  }

  public A withNewLogicalOrLeft(Object left, Object right) {
    return (A) withLeft(new LogicalOr(left, right));
  }

  public NotEqualsLeftNested<A> withNewNotEqualsLeft() {
    return new NotEqualsLeftNested(null);
  }

  public NotEqualsLeftNested<A> withNewNotEqualsLeftLike(NotEquals item) {
    return new NotEqualsLeftNested(item);
  }

  public A withNewNotEqualsLeft(Object left, Object right) {
    return (A) withLeft(new NotEquals(left, right));
  }

  public DivideLeftNested<A> withNewDivideLeft() {
    return new DivideLeftNested(null);
  }

  public DivideLeftNested<A> withNewDivideLeftLike(Divide item) {
    return new DivideLeftNested(item);
  }

  public A withNewDivideLeft(Object left, Object right) {
    return (A) withLeft(new Divide(left, right));
  }

  public LessThanLeftNested<A> withNewLessThanLeft() {
    return new LessThanLeftNested(null);
  }

  public LessThanLeftNested<A> withNewLessThanLeftLike(LessThan item) {
    return new LessThanLeftNested(item);
  }

  public A withNewLessThanLeft(Object left, Object right) {
    return (A) withLeft(new LessThan(left, right));
  }

  public BitwiseOrLeftNested<A> withNewBitwiseOrLeft() {
    return new BitwiseOrLeftNested(null);
  }

  public BitwiseOrLeftNested<A> withNewBitwiseOrLeftLike(BitwiseOr item) {
    return new BitwiseOrLeftNested(item);
  }

  public A withNewBitwiseOrLeft(Object left, Object right) {
    return (A) withLeft(new BitwiseOr(left, right));
  }

  public PropertyRefLeftNested<A> withNewPropertyRefLeft() {
    return new PropertyRefLeftNested(null);
  }

  public PropertyRefLeftNested<A> withNewPropertyRefLeftLike(PropertyRef item) {
    return new PropertyRefLeftNested(item);
  }

  public RightShiftLeftNested<A> withNewRightShiftLeft() {
    return new RightShiftLeftNested(null);
  }

  public RightShiftLeftNested<A> withNewRightShiftLeftLike(RightShift item) {
    return new RightShiftLeftNested(item);
  }

  public A withNewRightShiftLeft(Object left, Object right) {
    return (A) withLeft(new RightShift(left, right));
  }

  public GreaterThanLeftNested<A> withNewGreaterThanLeft() {
    return new GreaterThanLeftNested(null);
  }

  public GreaterThanLeftNested<A> withNewGreaterThanLeftLike(GreaterThan item) {
    return new GreaterThanLeftNested(item);
  }

  public A withNewGreaterThanLeft(Object left, Object right) {
    return (A) withLeft(new GreaterThan(left, right));
  }

  public DeclareLeftNested<A> withNewDeclareLeft() {
    return new DeclareLeftNested(null);
  }

  public DeclareLeftNested<A> withNewDeclareLeftLike(Declare item) {
    return new DeclareLeftNested(item);
  }

  public A withNewDeclareLeft(Class type, String name) {
    return (A) withLeft(new Declare(type, name));
  }

  public A withNewDeclareLeft(Class type, String name, Object value) {
    return (A) withLeft(new Declare(type, name, value));
  }

  public CastLeftNested<A> withNewCastLeft() {
    return new CastLeftNested(null);
  }

  public CastLeftNested<A> withNewCastLeftLike(Cast item) {
    return new CastLeftNested(item);
  }

  public ModuloLeftNested<A> withNewModuloLeft() {
    return new ModuloLeftNested(null);
  }

  public ModuloLeftNested<A> withNewModuloLeftLike(Modulo item) {
    return new ModuloLeftNested(item);
  }

  public A withNewModuloLeft(Object left, Object right) {
    return (A) withLeft(new Modulo(left, right));
  }

  public ValueRefLeftNested<A> withNewValueRefLeft() {
    return new ValueRefLeftNested(null);
  }

  public ValueRefLeftNested<A> withNewValueRefLeftLike(ValueRef item) {
    return new ValueRefLeftNested(item);
  }

  public A withNewValueRefLeft(Object value) {
    return (A) withLeft(new ValueRef(value));
  }

  public LeftShiftLeftNested<A> withNewLeftShiftLeft() {
    return new LeftShiftLeftNested(null);
  }

  public LeftShiftLeftNested<A> withNewLeftShiftLeftLike(LeftShift item) {
    return new LeftShiftLeftNested(item);
  }

  public A withNewLeftShiftLeft(Object left, Object right) {
    return (A) withLeft(new LeftShift(left, right));
  }

  public TernaryLeftNested<A> withNewTernaryLeft() {
    return new TernaryLeftNested(null);
  }

  public TernaryLeftNested<A> withNewTernaryLeftLike(Ternary item) {
    return new TernaryLeftNested(item);
  }

  public BinaryExpressionLeftNested<A> withNewBinaryExpressionLeft() {
    return new BinaryExpressionLeftNested(null);
  }

  public BinaryExpressionLeftNested<A> withNewBinaryExpressionLeftLike(BinaryExpression item) {
    return new BinaryExpressionLeftNested(item);
  }

  public EqualsLeftNested<A> withNewEqualsLeft() {
    return new EqualsLeftNested(null);
  }

  public EqualsLeftNested<A> withNewEqualsLeftLike(Equals item) {
    return new EqualsLeftNested(item);
  }

  public A withNewEqualsLeft(Object left, Object right) {
    return (A) withLeft(new Equals(left, right));
  }

  public EnclosedLeftNested<A> withNewEnclosedLeft() {
    return new EnclosedLeftNested(null);
  }

  public EnclosedLeftNested<A> withNewEnclosedLeftLike(Enclosed item) {
    return new EnclosedLeftNested(item);
  }

  public PreDecrementLeftNested<A> withNewPreDecrementLeft() {
    return new PreDecrementLeftNested(null);
  }

  public PreDecrementLeftNested<A> withNewPreDecrementLeftLike(PreDecrement item) {
    return new PreDecrementLeftNested(item);
  }

  public PostDecrementLeftNested<A> withNewPostDecrementLeft() {
    return new PostDecrementLeftNested(null);
  }

  public PostDecrementLeftNested<A> withNewPostDecrementLeftLike(PostDecrement item) {
    return new PostDecrementLeftNested(item);
  }

  public LambdaLeftNested<A> withNewLambdaLeft() {
    return new LambdaLeftNested(null);
  }

  public LambdaLeftNested<A> withNewLambdaLeftLike(Lambda item) {
    return new LambdaLeftNested(item);
  }

  public NotLeftNested<A> withNewNotLeft() {
    return new NotLeftNested(null);
  }

  public NotLeftNested<A> withNewNotLeftLike(Not item) {
    return new NotLeftNested(item);
  }

  public AssignLeftNested<A> withNewAssignLeft() {
    return new AssignLeftNested(null);
  }

  public AssignLeftNested<A> withNewAssignLeftLike(Assign item) {
    return new AssignLeftNested(item);
  }

  public ThisLeftNested<A> withNewThisLeft() {
    return new ThisLeftNested(null);
  }

  public ThisLeftNested<A> withNewThisLeftLike(This item) {
    return new ThisLeftNested(item);
  }

  public NegativeLeftNested<A> withNewNegativeLeft() {
    return new NegativeLeftNested(null);
  }

  public NegativeLeftNested<A> withNewNegativeLeftLike(Negative item) {
    return new NegativeLeftNested(item);
  }

  public LogicalAndLeftNested<A> withNewLogicalAndLeft() {
    return new LogicalAndLeftNested(null);
  }

  public LogicalAndLeftNested<A> withNewLogicalAndLeftLike(LogicalAnd item) {
    return new LogicalAndLeftNested(item);
  }

  public A withNewLogicalAndLeft(Object left, Object right) {
    return (A) withLeft(new LogicalAnd(left, right));
  }

  public PostIncrementLeftNested<A> withNewPostIncrementLeft() {
    return new PostIncrementLeftNested(null);
  }

  public PostIncrementLeftNested<A> withNewPostIncrementLeftLike(PostIncrement item) {
    return new PostIncrementLeftNested(item);
  }

  public RightUnsignedShiftLeftNested<A> withNewRightUnsignedShiftLeft() {
    return new RightUnsignedShiftLeftNested(null);
  }

  public RightUnsignedShiftLeftNested<A> withNewRightUnsignedShiftLeftLike(RightUnsignedShift item) {
    return new RightUnsignedShiftLeftNested(item);
  }

  public A withNewRightUnsignedShiftLeft(Object left, Object right) {
    return (A) withLeft(new RightUnsignedShift(left, right));
  }

  public PlusLeftNested<A> withNewPlusLeft() {
    return new PlusLeftNested(null);
  }

  public PlusLeftNested<A> withNewPlusLeftLike(Plus item) {
    return new PlusLeftNested(item);
  }

  public A withNewPlusLeft(Object left, Object right) {
    return (A) withLeft(new Plus(left, right));
  }

  public ConstructLeftNested<A> withNewConstructLeft() {
    return new ConstructLeftNested(null);
  }

  public ConstructLeftNested<A> withNewConstructLeftLike(Construct item) {
    return new ConstructLeftNested(item);
  }

  public XorLeftNested<A> withNewXorLeft() {
    return new XorLeftNested(null);
  }

  public XorLeftNested<A> withNewXorLeftLike(Xor item) {
    return new XorLeftNested(item);
  }

  public A withNewXorLeft(Object left, Object right) {
    return (A) withLeft(new Xor(left, right));
  }

  public PreIncrementLeftNested<A> withNewPreIncrementLeft() {
    return new PreIncrementLeftNested(null);
  }

  public PreIncrementLeftNested<A> withNewPreIncrementLeftLike(PreIncrement item) {
    return new PreIncrementLeftNested(item);
  }

  public LessThanOrEqualLeftNested<A> withNewLessThanOrEqualLeft() {
    return new LessThanOrEqualLeftNested(null);
  }

  public LessThanOrEqualLeftNested<A> withNewLessThanOrEqualLeftLike(LessThanOrEqual item) {
    return new LessThanOrEqualLeftNested(item);
  }

  public A withNewLessThanOrEqualLeft(Object left, Object right) {
    return (A) withLeft(new LessThanOrEqual(left, right));
  }

  public PositiveLeftNested<A> withNewPositiveLeft() {
    return new PositiveLeftNested(null);
  }

  public PositiveLeftNested<A> withNewPositiveLeftLike(Positive item) {
    return new PositiveLeftNested(item);
  }

  public Expression buildRight() {
    return this.right != null ? this.right.build() : null;
  }

  public A withRight(Expression right) {
    if (right == null) {
      this.right = null;
      this._visitables.remove("right");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(right);
      ;
      this._visitables.get("right").clear();
      this._visitables.get("right").add(builder);
      this.right = builder;
      return (A) this;
    }
  }

  public boolean hasRight() {
    return this.right != null;
  }

  public MultiplyRightNested<A> withNewMultiplyRight() {
    return new MultiplyRightNested(null);
  }

  public MultiplyRightNested<A> withNewMultiplyRightLike(Multiply item) {
    return new MultiplyRightNested(item);
  }

  public A withNewMultiplyRight(Object left, Object right) {
    return (A) withRight(new Multiply(left, right));
  }

  public NewArrayRightNested<A> withNewNewArrayRight() {
    return new NewArrayRightNested(null);
  }

  public NewArrayRightNested<A> withNewNewArrayRightLike(NewArray item) {
    return new NewArrayRightNested(item);
  }

  public A withNewNewArrayRight(Class type, Integer[] sizes) {
    return (A) withRight(new NewArray(type, sizes));
  }

  public InstanceOfRightNested<A> withNewInstanceOfRight() {
    return new InstanceOfRightNested(null);
  }

  public InstanceOfRightNested<A> withNewInstanceOfRightLike(InstanceOf item) {
    return new InstanceOfRightNested(item);
  }

  public MethodCallRightNested<A> withNewMethodCallRight() {
    return new MethodCallRightNested(null);
  }

  public MethodCallRightNested<A> withNewMethodCallRightLike(MethodCall item) {
    return new MethodCallRightNested(item);
  }

  public InverseRightNested<A> withNewInverseRight() {
    return new InverseRightNested(null);
  }

  public InverseRightNested<A> withNewInverseRightLike(Inverse item) {
    return new InverseRightNested(item);
  }

  public IndexRightNested<A> withNewIndexRight() {
    return new IndexRightNested(null);
  }

  public IndexRightNested<A> withNewIndexRightLike(Index item) {
    return new IndexRightNested(item);
  }

  public GreaterThanOrEqualRightNested<A> withNewGreaterThanOrEqualRight() {
    return new GreaterThanOrEqualRightNested(null);
  }

  public GreaterThanOrEqualRightNested<A> withNewGreaterThanOrEqualRightLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualRightNested(item);
  }

  public A withNewGreaterThanOrEqualRight(Object left, Object right) {
    return (A) withRight(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndRightNested<A> withNewBitwiseAndRight() {
    return new BitwiseAndRightNested(null);
  }

  public BitwiseAndRightNested<A> withNewBitwiseAndRightLike(BitwiseAnd item) {
    return new BitwiseAndRightNested(item);
  }

  public A withNewBitwiseAndRight(Object left, Object right) {
    return (A) withRight(new BitwiseAnd(left, right));
  }

  public MinusRightNested<A> withNewMinusRight() {
    return new MinusRightNested(null);
  }

  public MinusRightNested<A> withNewMinusRightLike(Minus item) {
    return new MinusRightNested(item);
  }

  public A withNewMinusRight(Object left, Object right) {
    return (A) withRight(new Minus(left, right));
  }

  public LogicalOrRightNested<A> withNewLogicalOrRight() {
    return new LogicalOrRightNested(null);
  }

  public LogicalOrRightNested<A> withNewLogicalOrRightLike(LogicalOr item) {
    return new LogicalOrRightNested(item);
  }

  public A withNewLogicalOrRight(Object left, Object right) {
    return (A) withRight(new LogicalOr(left, right));
  }

  public NotEqualsRightNested<A> withNewNotEqualsRight() {
    return new NotEqualsRightNested(null);
  }

  public NotEqualsRightNested<A> withNewNotEqualsRightLike(NotEquals item) {
    return new NotEqualsRightNested(item);
  }

  public A withNewNotEqualsRight(Object left, Object right) {
    return (A) withRight(new NotEquals(left, right));
  }

  public DivideRightNested<A> withNewDivideRight() {
    return new DivideRightNested(null);
  }

  public DivideRightNested<A> withNewDivideRightLike(Divide item) {
    return new DivideRightNested(item);
  }

  public A withNewDivideRight(Object left, Object right) {
    return (A) withRight(new Divide(left, right));
  }

  public LessThanRightNested<A> withNewLessThanRight() {
    return new LessThanRightNested(null);
  }

  public LessThanRightNested<A> withNewLessThanRightLike(LessThan item) {
    return new LessThanRightNested(item);
  }

  public A withNewLessThanRight(Object left, Object right) {
    return (A) withRight(new LessThan(left, right));
  }

  public BitwiseOrRightNested<A> withNewBitwiseOrRight() {
    return new BitwiseOrRightNested(null);
  }

  public BitwiseOrRightNested<A> withNewBitwiseOrRightLike(BitwiseOr item) {
    return new BitwiseOrRightNested(item);
  }

  public A withNewBitwiseOrRight(Object left, Object right) {
    return (A) withRight(new BitwiseOr(left, right));
  }

  public PropertyRefRightNested<A> withNewPropertyRefRight() {
    return new PropertyRefRightNested(null);
  }

  public PropertyRefRightNested<A> withNewPropertyRefRightLike(PropertyRef item) {
    return new PropertyRefRightNested(item);
  }

  public RightShiftRightNested<A> withNewRightShiftRight() {
    return new RightShiftRightNested(null);
  }

  public RightShiftRightNested<A> withNewRightShiftRightLike(RightShift item) {
    return new RightShiftRightNested(item);
  }

  public A withNewRightShiftRight(Object left, Object right) {
    return (A) withRight(new RightShift(left, right));
  }

  public GreaterThanRightNested<A> withNewGreaterThanRight() {
    return new GreaterThanRightNested(null);
  }

  public GreaterThanRightNested<A> withNewGreaterThanRightLike(GreaterThan item) {
    return new GreaterThanRightNested(item);
  }

  public A withNewGreaterThanRight(Object left, Object right) {
    return (A) withRight(new GreaterThan(left, right));
  }

  public DeclareRightNested<A> withNewDeclareRight() {
    return new DeclareRightNested(null);
  }

  public DeclareRightNested<A> withNewDeclareRightLike(Declare item) {
    return new DeclareRightNested(item);
  }

  public A withNewDeclareRight(Class type, String name) {
    return (A) withRight(new Declare(type, name));
  }

  public A withNewDeclareRight(Class type, String name, Object value) {
    return (A) withRight(new Declare(type, name, value));
  }

  public CastRightNested<A> withNewCastRight() {
    return new CastRightNested(null);
  }

  public CastRightNested<A> withNewCastRightLike(Cast item) {
    return new CastRightNested(item);
  }

  public ModuloRightNested<A> withNewModuloRight() {
    return new ModuloRightNested(null);
  }

  public ModuloRightNested<A> withNewModuloRightLike(Modulo item) {
    return new ModuloRightNested(item);
  }

  public A withNewModuloRight(Object left, Object right) {
    return (A) withRight(new Modulo(left, right));
  }

  public ValueRefRightNested<A> withNewValueRefRight() {
    return new ValueRefRightNested(null);
  }

  public ValueRefRightNested<A> withNewValueRefRightLike(ValueRef item) {
    return new ValueRefRightNested(item);
  }

  public A withNewValueRefRight(Object value) {
    return (A) withRight(new ValueRef(value));
  }

  public LeftShiftRightNested<A> withNewLeftShiftRight() {
    return new LeftShiftRightNested(null);
  }

  public LeftShiftRightNested<A> withNewLeftShiftRightLike(LeftShift item) {
    return new LeftShiftRightNested(item);
  }

  public A withNewLeftShiftRight(Object left, Object right) {
    return (A) withRight(new LeftShift(left, right));
  }

  public TernaryRightNested<A> withNewTernaryRight() {
    return new TernaryRightNested(null);
  }

  public TernaryRightNested<A> withNewTernaryRightLike(Ternary item) {
    return new TernaryRightNested(item);
  }

  public BinaryExpressionRightNested<A> withNewBinaryExpressionRight() {
    return new BinaryExpressionRightNested(null);
  }

  public BinaryExpressionRightNested<A> withNewBinaryExpressionRightLike(BinaryExpression item) {
    return new BinaryExpressionRightNested(item);
  }

  public EqualsRightNested<A> withNewEqualsRight() {
    return new EqualsRightNested(null);
  }

  public EqualsRightNested<A> withNewEqualsRightLike(Equals item) {
    return new EqualsRightNested(item);
  }

  public A withNewEqualsRight(Object left, Object right) {
    return (A) withRight(new Equals(left, right));
  }

  public EnclosedRightNested<A> withNewEnclosedRight() {
    return new EnclosedRightNested(null);
  }

  public EnclosedRightNested<A> withNewEnclosedRightLike(Enclosed item) {
    return new EnclosedRightNested(item);
  }

  public PreDecrementRightNested<A> withNewPreDecrementRight() {
    return new PreDecrementRightNested(null);
  }

  public PreDecrementRightNested<A> withNewPreDecrementRightLike(PreDecrement item) {
    return new PreDecrementRightNested(item);
  }

  public PostDecrementRightNested<A> withNewPostDecrementRight() {
    return new PostDecrementRightNested(null);
  }

  public PostDecrementRightNested<A> withNewPostDecrementRightLike(PostDecrement item) {
    return new PostDecrementRightNested(item);
  }

  public LambdaRightNested<A> withNewLambdaRight() {
    return new LambdaRightNested(null);
  }

  public LambdaRightNested<A> withNewLambdaRightLike(Lambda item) {
    return new LambdaRightNested(item);
  }

  public NotRightNested<A> withNewNotRight() {
    return new NotRightNested(null);
  }

  public NotRightNested<A> withNewNotRightLike(Not item) {
    return new NotRightNested(item);
  }

  public AssignRightNested<A> withNewAssignRight() {
    return new AssignRightNested(null);
  }

  public AssignRightNested<A> withNewAssignRightLike(Assign item) {
    return new AssignRightNested(item);
  }

  public ThisRightNested<A> withNewThisRight() {
    return new ThisRightNested(null);
  }

  public ThisRightNested<A> withNewThisRightLike(This item) {
    return new ThisRightNested(item);
  }

  public NegativeRightNested<A> withNewNegativeRight() {
    return new NegativeRightNested(null);
  }

  public NegativeRightNested<A> withNewNegativeRightLike(Negative item) {
    return new NegativeRightNested(item);
  }

  public LogicalAndRightNested<A> withNewLogicalAndRight() {
    return new LogicalAndRightNested(null);
  }

  public LogicalAndRightNested<A> withNewLogicalAndRightLike(LogicalAnd item) {
    return new LogicalAndRightNested(item);
  }

  public A withNewLogicalAndRight(Object left, Object right) {
    return (A) withRight(new LogicalAnd(left, right));
  }

  public PostIncrementRightNested<A> withNewPostIncrementRight() {
    return new PostIncrementRightNested(null);
  }

  public PostIncrementRightNested<A> withNewPostIncrementRightLike(PostIncrement item) {
    return new PostIncrementRightNested(item);
  }

  public RightUnsignedShiftRightNested<A> withNewRightUnsignedShiftRight() {
    return new RightUnsignedShiftRightNested(null);
  }

  public RightUnsignedShiftRightNested<A> withNewRightUnsignedShiftRightLike(RightUnsignedShift item) {
    return new RightUnsignedShiftRightNested(item);
  }

  public A withNewRightUnsignedShiftRight(Object left, Object right) {
    return (A) withRight(new RightUnsignedShift(left, right));
  }

  public PlusRightNested<A> withNewPlusRight() {
    return new PlusRightNested(null);
  }

  public PlusRightNested<A> withNewPlusRightLike(Plus item) {
    return new PlusRightNested(item);
  }

  public A withNewPlusRight(Object left, Object right) {
    return (A) withRight(new Plus(left, right));
  }

  public ConstructRightNested<A> withNewConstructRight() {
    return new ConstructRightNested(null);
  }

  public ConstructRightNested<A> withNewConstructRightLike(Construct item) {
    return new ConstructRightNested(item);
  }

  public XorRightNested<A> withNewXorRight() {
    return new XorRightNested(null);
  }

  public XorRightNested<A> withNewXorRightLike(Xor item) {
    return new XorRightNested(item);
  }

  public A withNewXorRight(Object left, Object right) {
    return (A) withRight(new Xor(left, right));
  }

  public PreIncrementRightNested<A> withNewPreIncrementRight() {
    return new PreIncrementRightNested(null);
  }

  public PreIncrementRightNested<A> withNewPreIncrementRightLike(PreIncrement item) {
    return new PreIncrementRightNested(item);
  }

  public LessThanOrEqualRightNested<A> withNewLessThanOrEqualRight() {
    return new LessThanOrEqualRightNested(null);
  }

  public LessThanOrEqualRightNested<A> withNewLessThanOrEqualRightLike(LessThanOrEqual item) {
    return new LessThanOrEqualRightNested(item);
  }

  public A withNewLessThanOrEqualRight(Object left, Object right) {
    return (A) withRight(new LessThanOrEqual(left, right));
  }

  public PositiveRightNested<A> withNewPositiveRight() {
    return new PositiveRightNested(null);
  }

  public PositiveRightNested<A> withNewPositiveRightLike(Positive item) {
    return new PositiveRightNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    BinaryExpressionFluent that = (BinaryExpressionFluent) o;
    if (!java.util.Objects.equals(left, that.left))
      return false;
    if (!java.util.Objects.equals(right, that.right))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(left, right, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (left != null) {
      sb.append("left:");
      sb.append(left + ",");
    }
    if (right != null) {
      sb.append("right:");
      sb.append(right);
    }
    sb.append("}");
    return sb.toString();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
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
      case "io.sundr.model." + "This":
        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);
      case "io.sundr.model." + "Negative":
        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);
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

  public class MultiplyLeftNested<N> extends MultiplyFluent<MultiplyLeftNested<N>> implements Nested<N> {
    MultiplyLeftNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endMultiplyLeft() {
      return and();
    }

  }

  public class NewArrayLeftNested<N> extends NewArrayFluent<NewArrayLeftNested<N>> implements Nested<N> {
    NewArrayLeftNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNewArrayLeft() {
      return and();
    }

  }

  public class InstanceOfLeftNested<N> extends InstanceOfFluent<InstanceOfLeftNested<N>> implements Nested<N> {
    InstanceOfLeftNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endInstanceOfLeft() {
      return and();
    }

  }

  public class MethodCallLeftNested<N> extends MethodCallFluent<MethodCallLeftNested<N>> implements Nested<N> {
    MethodCallLeftNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endMethodCallLeft() {
      return and();
    }

  }

  public class InverseLeftNested<N> extends InverseFluent<InverseLeftNested<N>> implements Nested<N> {
    InverseLeftNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endInverseLeft() {
      return and();
    }

  }

  public class IndexLeftNested<N> extends IndexFluent<IndexLeftNested<N>> implements Nested<N> {
    IndexLeftNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endIndexLeft() {
      return and();
    }

  }

  public class GreaterThanOrEqualLeftNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualLeftNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualLeftNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endGreaterThanOrEqualLeft() {
      return and();
    }

  }

  public class BitwiseAndLeftNested<N> extends BitwiseAndFluent<BitwiseAndLeftNested<N>> implements Nested<N> {
    BitwiseAndLeftNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endBitwiseAndLeft() {
      return and();
    }

  }

  public class MinusLeftNested<N> extends MinusFluent<MinusLeftNested<N>> implements Nested<N> {
    MinusLeftNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endMinusLeft() {
      return and();
    }

  }

  public class LogicalOrLeftNested<N> extends LogicalOrFluent<LogicalOrLeftNested<N>> implements Nested<N> {
    LogicalOrLeftNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLogicalOrLeft() {
      return and();
    }

  }

  public class NotEqualsLeftNested<N> extends NotEqualsFluent<NotEqualsLeftNested<N>> implements Nested<N> {
    NotEqualsLeftNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNotEqualsLeft() {
      return and();
    }

  }

  public class DivideLeftNested<N> extends DivideFluent<DivideLeftNested<N>> implements Nested<N> {
    DivideLeftNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endDivideLeft() {
      return and();
    }

  }

  public class LessThanLeftNested<N> extends LessThanFluent<LessThanLeftNested<N>> implements Nested<N> {
    LessThanLeftNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLessThanLeft() {
      return and();
    }

  }

  public class BitwiseOrLeftNested<N> extends BitwiseOrFluent<BitwiseOrLeftNested<N>> implements Nested<N> {
    BitwiseOrLeftNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endBitwiseOrLeft() {
      return and();
    }

  }

  public class PropertyRefLeftNested<N> extends PropertyRefFluent<PropertyRefLeftNested<N>> implements Nested<N> {
    PropertyRefLeftNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPropertyRefLeft() {
      return and();
    }

  }

  public class RightShiftLeftNested<N> extends RightShiftFluent<RightShiftLeftNested<N>> implements Nested<N> {
    RightShiftLeftNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endRightShiftLeft() {
      return and();
    }

  }

  public class GreaterThanLeftNested<N> extends GreaterThanFluent<GreaterThanLeftNested<N>> implements Nested<N> {
    GreaterThanLeftNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endGreaterThanLeft() {
      return and();
    }

  }

  public class DeclareLeftNested<N> extends DeclareFluent<DeclareLeftNested<N>> implements Nested<N> {
    DeclareLeftNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endDeclareLeft() {
      return and();
    }

  }

  public class CastLeftNested<N> extends CastFluent<CastLeftNested<N>> implements Nested<N> {
    CastLeftNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endCastLeft() {
      return and();
    }

  }

  public class ModuloLeftNested<N> extends ModuloFluent<ModuloLeftNested<N>> implements Nested<N> {
    ModuloLeftNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endModuloLeft() {
      return and();
    }

  }

  public class ValueRefLeftNested<N> extends ValueRefFluent<ValueRefLeftNested<N>> implements Nested<N> {
    ValueRefLeftNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endValueRefLeft() {
      return and();
    }

  }

  public class LeftShiftLeftNested<N> extends LeftShiftFluent<LeftShiftLeftNested<N>> implements Nested<N> {
    LeftShiftLeftNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLeftShiftLeft() {
      return and();
    }

  }

  public class TernaryLeftNested<N> extends TernaryFluent<TernaryLeftNested<N>> implements Nested<N> {
    TernaryLeftNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endTernaryLeft() {
      return and();
    }

  }

  public class BinaryExpressionLeftNested<N> extends BinaryExpressionFluent<BinaryExpressionLeftNested<N>>
      implements Nested<N> {
    BinaryExpressionLeftNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endBinaryExpressionLeft() {
      return and();
    }

  }

  public class EqualsLeftNested<N> extends EqualsFluent<EqualsLeftNested<N>> implements Nested<N> {
    EqualsLeftNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endEqualsLeft() {
      return and();
    }

  }

  public class EnclosedLeftNested<N> extends EnclosedFluent<EnclosedLeftNested<N>> implements Nested<N> {
    EnclosedLeftNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endEnclosedLeft() {
      return and();
    }

  }

  public class PreDecrementLeftNested<N> extends PreDecrementFluent<PreDecrementLeftNested<N>> implements Nested<N> {
    PreDecrementLeftNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPreDecrementLeft() {
      return and();
    }

  }

  public class PostDecrementLeftNested<N> extends PostDecrementFluent<PostDecrementLeftNested<N>> implements Nested<N> {
    PostDecrementLeftNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPostDecrementLeft() {
      return and();
    }

  }

  public class LambdaLeftNested<N> extends LambdaFluent<LambdaLeftNested<N>> implements Nested<N> {
    LambdaLeftNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLambdaLeft() {
      return and();
    }

  }

  public class NotLeftNested<N> extends NotFluent<NotLeftNested<N>> implements Nested<N> {
    NotLeftNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNotLeft() {
      return and();
    }

  }

  public class AssignLeftNested<N> extends AssignFluent<AssignLeftNested<N>> implements Nested<N> {
    AssignLeftNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endAssignLeft() {
      return and();
    }

  }

  public class ThisLeftNested<N> extends ThisFluent<ThisLeftNested<N>> implements Nested<N> {
    ThisLeftNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endThisLeft() {
      return and();
    }

  }

  public class NegativeLeftNested<N> extends NegativeFluent<NegativeLeftNested<N>> implements Nested<N> {
    NegativeLeftNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNegativeLeft() {
      return and();
    }

  }

  public class LogicalAndLeftNested<N> extends LogicalAndFluent<LogicalAndLeftNested<N>> implements Nested<N> {
    LogicalAndLeftNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLogicalAndLeft() {
      return and();
    }

  }

  public class PostIncrementLeftNested<N> extends PostIncrementFluent<PostIncrementLeftNested<N>> implements Nested<N> {
    PostIncrementLeftNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPostIncrementLeft() {
      return and();
    }

  }

  public class RightUnsignedShiftLeftNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftLeftNested<N>>
      implements Nested<N> {
    RightUnsignedShiftLeftNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endRightUnsignedShiftLeft() {
      return and();
    }

  }

  public class PlusLeftNested<N> extends PlusFluent<PlusLeftNested<N>> implements Nested<N> {
    PlusLeftNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPlusLeft() {
      return and();
    }

  }

  public class ConstructLeftNested<N> extends ConstructFluent<ConstructLeftNested<N>> implements Nested<N> {
    ConstructLeftNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endConstructLeft() {
      return and();
    }

  }

  public class XorLeftNested<N> extends XorFluent<XorLeftNested<N>> implements Nested<N> {
    XorLeftNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endXorLeft() {
      return and();
    }

  }

  public class PreIncrementLeftNested<N> extends PreIncrementFluent<PreIncrementLeftNested<N>> implements Nested<N> {
    PreIncrementLeftNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPreIncrementLeft() {
      return and();
    }

  }

  public class LessThanOrEqualLeftNested<N> extends LessThanOrEqualFluent<LessThanOrEqualLeftNested<N>> implements Nested<N> {
    LessThanOrEqualLeftNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLessThanOrEqualLeft() {
      return and();
    }

  }

  public class PositiveLeftNested<N> extends PositiveFluent<PositiveLeftNested<N>> implements Nested<N> {
    PositiveLeftNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPositiveLeft() {
      return and();
    }

  }

  public class MultiplyRightNested<N> extends MultiplyFluent<MultiplyRightNested<N>> implements Nested<N> {
    MultiplyRightNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endMultiplyRight() {
      return and();
    }

  }

  public class NewArrayRightNested<N> extends NewArrayFluent<NewArrayRightNested<N>> implements Nested<N> {
    NewArrayRightNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNewArrayRight() {
      return and();
    }

  }

  public class InstanceOfRightNested<N> extends InstanceOfFluent<InstanceOfRightNested<N>> implements Nested<N> {
    InstanceOfRightNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endInstanceOfRight() {
      return and();
    }

  }

  public class MethodCallRightNested<N> extends MethodCallFluent<MethodCallRightNested<N>> implements Nested<N> {
    MethodCallRightNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endMethodCallRight() {
      return and();
    }

  }

  public class InverseRightNested<N> extends InverseFluent<InverseRightNested<N>> implements Nested<N> {
    InverseRightNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endInverseRight() {
      return and();
    }

  }

  public class IndexRightNested<N> extends IndexFluent<IndexRightNested<N>> implements Nested<N> {
    IndexRightNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endIndexRight() {
      return and();
    }

  }

  public class GreaterThanOrEqualRightNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualRightNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualRightNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endGreaterThanOrEqualRight() {
      return and();
    }

  }

  public class BitwiseAndRightNested<N> extends BitwiseAndFluent<BitwiseAndRightNested<N>> implements Nested<N> {
    BitwiseAndRightNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endBitwiseAndRight() {
      return and();
    }

  }

  public class MinusRightNested<N> extends MinusFluent<MinusRightNested<N>> implements Nested<N> {
    MinusRightNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endMinusRight() {
      return and();
    }

  }

  public class LogicalOrRightNested<N> extends LogicalOrFluent<LogicalOrRightNested<N>> implements Nested<N> {
    LogicalOrRightNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLogicalOrRight() {
      return and();
    }

  }

  public class NotEqualsRightNested<N> extends NotEqualsFluent<NotEqualsRightNested<N>> implements Nested<N> {
    NotEqualsRightNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNotEqualsRight() {
      return and();
    }

  }

  public class DivideRightNested<N> extends DivideFluent<DivideRightNested<N>> implements Nested<N> {
    DivideRightNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endDivideRight() {
      return and();
    }

  }

  public class LessThanRightNested<N> extends LessThanFluent<LessThanRightNested<N>> implements Nested<N> {
    LessThanRightNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLessThanRight() {
      return and();
    }

  }

  public class BitwiseOrRightNested<N> extends BitwiseOrFluent<BitwiseOrRightNested<N>> implements Nested<N> {
    BitwiseOrRightNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endBitwiseOrRight() {
      return and();
    }

  }

  public class PropertyRefRightNested<N> extends PropertyRefFluent<PropertyRefRightNested<N>> implements Nested<N> {
    PropertyRefRightNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPropertyRefRight() {
      return and();
    }

  }

  public class RightShiftRightNested<N> extends RightShiftFluent<RightShiftRightNested<N>> implements Nested<N> {
    RightShiftRightNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endRightShiftRight() {
      return and();
    }

  }

  public class GreaterThanRightNested<N> extends GreaterThanFluent<GreaterThanRightNested<N>> implements Nested<N> {
    GreaterThanRightNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endGreaterThanRight() {
      return and();
    }

  }

  public class DeclareRightNested<N> extends DeclareFluent<DeclareRightNested<N>> implements Nested<N> {
    DeclareRightNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endDeclareRight() {
      return and();
    }

  }

  public class CastRightNested<N> extends CastFluent<CastRightNested<N>> implements Nested<N> {
    CastRightNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endCastRight() {
      return and();
    }

  }

  public class ModuloRightNested<N> extends ModuloFluent<ModuloRightNested<N>> implements Nested<N> {
    ModuloRightNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endModuloRight() {
      return and();
    }

  }

  public class ValueRefRightNested<N> extends ValueRefFluent<ValueRefRightNested<N>> implements Nested<N> {
    ValueRefRightNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endValueRefRight() {
      return and();
    }

  }

  public class LeftShiftRightNested<N> extends LeftShiftFluent<LeftShiftRightNested<N>> implements Nested<N> {
    LeftShiftRightNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLeftShiftRight() {
      return and();
    }

  }

  public class TernaryRightNested<N> extends TernaryFluent<TernaryRightNested<N>> implements Nested<N> {
    TernaryRightNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endTernaryRight() {
      return and();
    }

  }

  public class BinaryExpressionRightNested<N> extends BinaryExpressionFluent<BinaryExpressionRightNested<N>>
      implements Nested<N> {
    BinaryExpressionRightNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endBinaryExpressionRight() {
      return and();
    }

  }

  public class EqualsRightNested<N> extends EqualsFluent<EqualsRightNested<N>> implements Nested<N> {
    EqualsRightNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endEqualsRight() {
      return and();
    }

  }

  public class EnclosedRightNested<N> extends EnclosedFluent<EnclosedRightNested<N>> implements Nested<N> {
    EnclosedRightNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endEnclosedRight() {
      return and();
    }

  }

  public class PreDecrementRightNested<N> extends PreDecrementFluent<PreDecrementRightNested<N>> implements Nested<N> {
    PreDecrementRightNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPreDecrementRight() {
      return and();
    }

  }

  public class PostDecrementRightNested<N> extends PostDecrementFluent<PostDecrementRightNested<N>> implements Nested<N> {
    PostDecrementRightNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPostDecrementRight() {
      return and();
    }

  }

  public class LambdaRightNested<N> extends LambdaFluent<LambdaRightNested<N>> implements Nested<N> {
    LambdaRightNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLambdaRight() {
      return and();
    }

  }

  public class NotRightNested<N> extends NotFluent<NotRightNested<N>> implements Nested<N> {
    NotRightNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNotRight() {
      return and();
    }

  }

  public class AssignRightNested<N> extends AssignFluent<AssignRightNested<N>> implements Nested<N> {
    AssignRightNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endAssignRight() {
      return and();
    }

  }

  public class ThisRightNested<N> extends ThisFluent<ThisRightNested<N>> implements Nested<N> {
    ThisRightNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endThisRight() {
      return and();
    }

  }

  public class NegativeRightNested<N> extends NegativeFluent<NegativeRightNested<N>> implements Nested<N> {
    NegativeRightNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNegativeRight() {
      return and();
    }

  }

  public class LogicalAndRightNested<N> extends LogicalAndFluent<LogicalAndRightNested<N>> implements Nested<N> {
    LogicalAndRightNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLogicalAndRight() {
      return and();
    }

  }

  public class PostIncrementRightNested<N> extends PostIncrementFluent<PostIncrementRightNested<N>> implements Nested<N> {
    PostIncrementRightNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPostIncrementRight() {
      return and();
    }

  }

  public class RightUnsignedShiftRightNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftRightNested<N>>
      implements Nested<N> {
    RightUnsignedShiftRightNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endRightUnsignedShiftRight() {
      return and();
    }

  }

  public class PlusRightNested<N> extends PlusFluent<PlusRightNested<N>> implements Nested<N> {
    PlusRightNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPlusRight() {
      return and();
    }

  }

  public class ConstructRightNested<N> extends ConstructFluent<ConstructRightNested<N>> implements Nested<N> {
    ConstructRightNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endConstructRight() {
      return and();
    }

  }

  public class XorRightNested<N> extends XorFluent<XorRightNested<N>> implements Nested<N> {
    XorRightNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endXorRight() {
      return and();
    }

  }

  public class PreIncrementRightNested<N> extends PreIncrementFluent<PreIncrementRightNested<N>> implements Nested<N> {
    PreIncrementRightNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPreIncrementRight() {
      return and();
    }

  }

  public class LessThanOrEqualRightNested<N> extends LessThanOrEqualFluent<LessThanOrEqualRightNested<N>> implements Nested<N> {
    LessThanOrEqualRightNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLessThanOrEqualRight() {
      return and();
    }

  }

  public class PositiveRightNested<N> extends PositiveFluent<PositiveRightNested<N>> implements Nested<N> {
    PositiveRightNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPositiveRight() {
      return and();
    }

  }

}
