package io.sundr.model;

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
public class ForFluent<A extends ForFluent<A>> extends BaseFluent<A> {
  public ForFluent() {
  }

  public ForFluent(For instance) {
    this.copyInstance(instance);
  }

  private ArrayList<VisitableBuilder<? extends Expression, ?>> init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
  private VisitableBuilder<? extends Expression, ?> compare;
  private ArrayList<VisitableBuilder<? extends Expression, ?>> update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
  private VisitableBuilder<? extends Statement, ?> body;

  protected void copyInstance(For instance) {
    if (instance != null) {
      this.withInit(instance.getInit());
      this.withCompare(instance.getCompare());
      this.withUpdate(instance.getUpdate());
      this.withBody(instance.getBody());
    }
  }

  public A addToInit(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    _visitables.get("init").add(builder);
    this.init.add(builder);
    return (A) this;
  }

  public A addToInit(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    if (index < 0 || index >= init.size()) {
      _visitables.get("init").add(builder);
      init.add(builder);
    } else {
      _visitables.get("init").add(index, builder);
      init.add(index, builder);
    }
    return (A) this;
  }

  public A addToInit(int index, Expression item) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= init.size()) {
      _visitables.get("init").add(builder);
      init.add(builder);
    } else {
      _visitables.get("init").add(index, builder);
      init.add(index, builder);
    }
    return (A) this;
  }

  public A setToInit(int index, Expression item) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= init.size()) {
      _visitables.get("init").add(builder);
      init.add(builder);
    } else {
      _visitables.get("init").set(index, builder);
      init.set(index, builder);
    }
    return (A) this;
  }

  public A addToInit(io.sundr.model.Expression... items) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("init").add(builder);
      this.init.add(builder);
    }
    return (A) this;
  }

  public A addAllToInit(Collection<Expression> items) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("init").add(builder);
      this.init.add(builder);
    }
    return (A) this;
  }

  public A removeFromInit(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.init == null)
      return (A) this;
    _visitables.get("init").remove(builder);
    this.init.remove(builder);
    return (A) this;
  }

  public A removeFromInit(io.sundr.model.Expression... items) {
    if (this.init == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("init").remove(builder);
      this.init.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromInit(Collection<Expression> items) {
    if (this.init == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("init").remove(builder);
      this.init.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromInit(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    if (init == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends Expression, ?>> each = init.iterator();
    final List visitables = _visitables.get("init");
    while (each.hasNext()) {
      VisitableBuilder<? extends Expression, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<Expression> buildInit() {
    return build(init);
  }

  public Expression buildInit(int index) {
    return this.init.get(index).build();
  }

  public Expression buildFirstInit() {
    return this.init.get(0).build();
  }

  public Expression buildLastInit() {
    return this.init.get(init.size() - 1).build();
  }

  public Expression buildMatchingInit(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : init) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingInit(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : init) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withInit(List<Expression> init) {
    if (init != null) {
      this.init = new ArrayList();
      for (Expression item : init) {
        this.addToInit(item);
      }
    } else {
      this.init = null;
    }
    return (A) this;
  }

  public A withInit(io.sundr.model.Expression... init) {
    if (this.init != null) {
      this.init.clear();
      _visitables.remove("init");
    }
    if (init != null) {
      for (Expression item : init) {
        this.addToInit(item);
      }
    }
    return (A) this;
  }

  public boolean hasInit() {
    return init != null && !init.isEmpty();
  }

  public MultiplyInitNested<A> addNewMultiplyInit() {
    return new MultiplyInitNested(-1, null);
  }

  public MultiplyInitNested<A> addNewMultiplyInitLike(Multiply item) {
    return new MultiplyInitNested(-1, item);
  }

  public A addNewMultiplyInit(Object left, Object right) {
    return (A) addToInit(new Multiply(left, right));
  }

  public MultiplyInitNested<A> setNewMultiplyInitLike(int index, Multiply item) {
    return new MultiplyInitNested(index, item);
  }

  public MethodCallInitNested<A> addNewMethodCallInit() {
    return new MethodCallInitNested(-1, null);
  }

  public MethodCallInitNested<A> addNewMethodCallInitLike(MethodCall item) {
    return new MethodCallInitNested(-1, item);
  }

  public MethodCallInitNested<A> setNewMethodCallInitLike(int index, MethodCall item) {
    return new MethodCallInitNested(index, item);
  }

  public InverseInitNested<A> addNewInverseInit() {
    return new InverseInitNested(-1, null);
  }

  public InverseInitNested<A> addNewInverseInitLike(Inverse item) {
    return new InverseInitNested(-1, item);
  }

  public InverseInitNested<A> setNewInverseInitLike(int index, Inverse item) {
    return new InverseInitNested(index, item);
  }

  public GreaterThanOrEqualInitNested<A> addNewGreaterThanOrEqualInit() {
    return new GreaterThanOrEqualInitNested(-1, null);
  }

  public GreaterThanOrEqualInitNested<A> addNewGreaterThanOrEqualInitLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualInitNested(-1, item);
  }

  public A addNewGreaterThanOrEqualInit(Object left, Object right) {
    return (A) addToInit(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualInitNested<A> setNewGreaterThanOrEqualInitLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualInitNested(index, item);
  }

  public BitwiseAndInitNested<A> addNewBitwiseAndInit() {
    return new BitwiseAndInitNested(-1, null);
  }

  public BitwiseAndInitNested<A> addNewBitwiseAndInitLike(BitwiseAnd item) {
    return new BitwiseAndInitNested(-1, item);
  }

  public A addNewBitwiseAndInit(Object left, Object right) {
    return (A) addToInit(new BitwiseAnd(left, right));
  }

  public BitwiseAndInitNested<A> setNewBitwiseAndInitLike(int index, BitwiseAnd item) {
    return new BitwiseAndInitNested(index, item);
  }

  public MinusInitNested<A> addNewMinusInit() {
    return new MinusInitNested(-1, null);
  }

  public MinusInitNested<A> addNewMinusInitLike(Minus item) {
    return new MinusInitNested(-1, item);
  }

  public A addNewMinusInit(Object left, Object right) {
    return (A) addToInit(new Minus(left, right));
  }

  public MinusInitNested<A> setNewMinusInitLike(int index, Minus item) {
    return new MinusInitNested(index, item);
  }

  public LogicalOrInitNested<A> addNewLogicalOrInit() {
    return new LogicalOrInitNested(-1, null);
  }

  public LogicalOrInitNested<A> addNewLogicalOrInitLike(LogicalOr item) {
    return new LogicalOrInitNested(-1, item);
  }

  public A addNewLogicalOrInit(Object left, Object right) {
    return (A) addToInit(new LogicalOr(left, right));
  }

  public LogicalOrInitNested<A> setNewLogicalOrInitLike(int index, LogicalOr item) {
    return new LogicalOrInitNested(index, item);
  }

  public NotEqualsInitNested<A> addNewNotEqualsInit() {
    return new NotEqualsInitNested(-1, null);
  }

  public NotEqualsInitNested<A> addNewNotEqualsInitLike(NotEquals item) {
    return new NotEqualsInitNested(-1, item);
  }

  public A addNewNotEqualsInit(Object left, Object right) {
    return (A) addToInit(new NotEquals(left, right));
  }

  public NotEqualsInitNested<A> setNewNotEqualsInitLike(int index, NotEquals item) {
    return new NotEqualsInitNested(index, item);
  }

  public DivideInitNested<A> addNewDivideInit() {
    return new DivideInitNested(-1, null);
  }

  public DivideInitNested<A> addNewDivideInitLike(Divide item) {
    return new DivideInitNested(-1, item);
  }

  public A addNewDivideInit(Object left, Object right) {
    return (A) addToInit(new Divide(left, right));
  }

  public DivideInitNested<A> setNewDivideInitLike(int index, Divide item) {
    return new DivideInitNested(index, item);
  }

  public LessThanInitNested<A> addNewLessThanInit() {
    return new LessThanInitNested(-1, null);
  }

  public LessThanInitNested<A> addNewLessThanInitLike(LessThan item) {
    return new LessThanInitNested(-1, item);
  }

  public A addNewLessThanInit(Object left, Object right) {
    return (A) addToInit(new LessThan(left, right));
  }

  public LessThanInitNested<A> setNewLessThanInitLike(int index, LessThan item) {
    return new LessThanInitNested(index, item);
  }

  public BitwiseOrInitNested<A> addNewBitwiseOrInit() {
    return new BitwiseOrInitNested(-1, null);
  }

  public BitwiseOrInitNested<A> addNewBitwiseOrInitLike(BitwiseOr item) {
    return new BitwiseOrInitNested(-1, item);
  }

  public A addNewBitwiseOrInit(Object left, Object right) {
    return (A) addToInit(new BitwiseOr(left, right));
  }

  public BitwiseOrInitNested<A> setNewBitwiseOrInitLike(int index, BitwiseOr item) {
    return new BitwiseOrInitNested(index, item);
  }

  public PropertyRefInitNested<A> addNewPropertyRefInit() {
    return new PropertyRefInitNested(-1, null);
  }

  public PropertyRefInitNested<A> addNewPropertyRefInitLike(PropertyRef item) {
    return new PropertyRefInitNested(-1, item);
  }

  public PropertyRefInitNested<A> setNewPropertyRefInitLike(int index, PropertyRef item) {
    return new PropertyRefInitNested(index, item);
  }

  public RightShiftInitNested<A> addNewRightShiftInit() {
    return new RightShiftInitNested(-1, null);
  }

  public RightShiftInitNested<A> addNewRightShiftInitLike(RightShift item) {
    return new RightShiftInitNested(-1, item);
  }

  public A addNewRightShiftInit(Object left, Object right) {
    return (A) addToInit(new RightShift(left, right));
  }

  public RightShiftInitNested<A> setNewRightShiftInitLike(int index, RightShift item) {
    return new RightShiftInitNested(index, item);
  }

  public GreaterThanInitNested<A> addNewGreaterThanInit() {
    return new GreaterThanInitNested(-1, null);
  }

  public GreaterThanInitNested<A> addNewGreaterThanInitLike(GreaterThan item) {
    return new GreaterThanInitNested(-1, item);
  }

  public A addNewGreaterThanInit(Object left, Object right) {
    return (A) addToInit(new GreaterThan(left, right));
  }

  public GreaterThanInitNested<A> setNewGreaterThanInitLike(int index, GreaterThan item) {
    return new GreaterThanInitNested(index, item);
  }

  public ModuloInitNested<A> addNewModuloInit() {
    return new ModuloInitNested(-1, null);
  }

  public ModuloInitNested<A> addNewModuloInitLike(Modulo item) {
    return new ModuloInitNested(-1, item);
  }

  public A addNewModuloInit(Object left, Object right) {
    return (A) addToInit(new Modulo(left, right));
  }

  public ModuloInitNested<A> setNewModuloInitLike(int index, Modulo item) {
    return new ModuloInitNested(index, item);
  }

  public ValueRefInitNested<A> addNewValueRefInit() {
    return new ValueRefInitNested(-1, null);
  }

  public ValueRefInitNested<A> addNewValueRefInitLike(ValueRef item) {
    return new ValueRefInitNested(-1, item);
  }

  public A addNewValueRefInit(Object value) {
    return (A) addToInit(new ValueRef(value));
  }

  public ValueRefInitNested<A> setNewValueRefInitLike(int index, ValueRef item) {
    return new ValueRefInitNested(index, item);
  }

  public LeftShiftInitNested<A> addNewLeftShiftInit() {
    return new LeftShiftInitNested(-1, null);
  }

  public LeftShiftInitNested<A> addNewLeftShiftInitLike(LeftShift item) {
    return new LeftShiftInitNested(-1, item);
  }

  public A addNewLeftShiftInit(Object left, Object right) {
    return (A) addToInit(new LeftShift(left, right));
  }

  public LeftShiftInitNested<A> setNewLeftShiftInitLike(int index, LeftShift item) {
    return new LeftShiftInitNested(index, item);
  }

  public TernaryInitNested<A> addNewTernaryInit() {
    return new TernaryInitNested(-1, null);
  }

  public TernaryInitNested<A> addNewTernaryInitLike(Ternary item) {
    return new TernaryInitNested(-1, item);
  }

  public TernaryInitNested<A> setNewTernaryInitLike(int index, Ternary item) {
    return new TernaryInitNested(index, item);
  }

  public BinaryExpressionInitNested<A> addNewBinaryExpressionInit() {
    return new BinaryExpressionInitNested(-1, null);
  }

  public BinaryExpressionInitNested<A> addNewBinaryExpressionInitLike(BinaryExpression item) {
    return new BinaryExpressionInitNested(-1, item);
  }

  public BinaryExpressionInitNested<A> setNewBinaryExpressionInitLike(int index, BinaryExpression item) {
    return new BinaryExpressionInitNested(index, item);
  }

  public EqualsInitNested<A> addNewEqualsInit() {
    return new EqualsInitNested(-1, null);
  }

  public EqualsInitNested<A> addNewEqualsInitLike(Equals item) {
    return new EqualsInitNested(-1, item);
  }

  public A addNewEqualsInit(Object left, Object right) {
    return (A) addToInit(new Equals(left, right));
  }

  public EqualsInitNested<A> setNewEqualsInitLike(int index, Equals item) {
    return new EqualsInitNested(index, item);
  }

  public EnclosedInitNested<A> addNewEnclosedInit() {
    return new EnclosedInitNested(-1, null);
  }

  public EnclosedInitNested<A> addNewEnclosedInitLike(Enclosed item) {
    return new EnclosedInitNested(-1, item);
  }

  public EnclosedInitNested<A> setNewEnclosedInitLike(int index, Enclosed item) {
    return new EnclosedInitNested(index, item);
  }

  public PreDecrementInitNested<A> addNewPreDecrementInit() {
    return new PreDecrementInitNested(-1, null);
  }

  public PreDecrementInitNested<A> addNewPreDecrementInitLike(PreDecrement item) {
    return new PreDecrementInitNested(-1, item);
  }

  public PreDecrementInitNested<A> setNewPreDecrementInitLike(int index, PreDecrement item) {
    return new PreDecrementInitNested(index, item);
  }

  public PostDecrementInitNested<A> addNewPostDecrementInit() {
    return new PostDecrementInitNested(-1, null);
  }

  public PostDecrementInitNested<A> addNewPostDecrementInitLike(PostDecrement item) {
    return new PostDecrementInitNested(-1, item);
  }

  public PostDecrementInitNested<A> setNewPostDecrementInitLike(int index, PostDecrement item) {
    return new PostDecrementInitNested(index, item);
  }

  public NotInitNested<A> addNewNotInit() {
    return new NotInitNested(-1, null);
  }

  public NotInitNested<A> addNewNotInitLike(Not item) {
    return new NotInitNested(-1, item);
  }

  public NotInitNested<A> setNewNotInitLike(int index, Not item) {
    return new NotInitNested(index, item);
  }

  public AssignInitNested<A> addNewAssignInit() {
    return new AssignInitNested(-1, null);
  }

  public AssignInitNested<A> addNewAssignInitLike(Assign item) {
    return new AssignInitNested(-1, item);
  }

  public AssignInitNested<A> setNewAssignInitLike(int index, Assign item) {
    return new AssignInitNested(index, item);
  }

  public NegativeInitNested<A> addNewNegativeInit() {
    return new NegativeInitNested(-1, null);
  }

  public NegativeInitNested<A> addNewNegativeInitLike(Negative item) {
    return new NegativeInitNested(-1, item);
  }

  public NegativeInitNested<A> setNewNegativeInitLike(int index, Negative item) {
    return new NegativeInitNested(index, item);
  }

  public ThisInitNested<A> addNewThisInit() {
    return new ThisInitNested(-1, null);
  }

  public ThisInitNested<A> addNewThisInitLike(This item) {
    return new ThisInitNested(-1, item);
  }

  public ThisInitNested<A> setNewThisInitLike(int index, This item) {
    return new ThisInitNested(index, item);
  }

  public LogicalAndInitNested<A> addNewLogicalAndInit() {
    return new LogicalAndInitNested(-1, null);
  }

  public LogicalAndInitNested<A> addNewLogicalAndInitLike(LogicalAnd item) {
    return new LogicalAndInitNested(-1, item);
  }

  public A addNewLogicalAndInit(Object left, Object right) {
    return (A) addToInit(new LogicalAnd(left, right));
  }

  public LogicalAndInitNested<A> setNewLogicalAndInitLike(int index, LogicalAnd item) {
    return new LogicalAndInitNested(index, item);
  }

  public PostIncrementInitNested<A> addNewPostIncrementInit() {
    return new PostIncrementInitNested(-1, null);
  }

  public PostIncrementInitNested<A> addNewPostIncrementInitLike(PostIncrement item) {
    return new PostIncrementInitNested(-1, item);
  }

  public PostIncrementInitNested<A> setNewPostIncrementInitLike(int index, PostIncrement item) {
    return new PostIncrementInitNested(index, item);
  }

  public RightUnsignedShiftInitNested<A> addNewRightUnsignedShiftInit() {
    return new RightUnsignedShiftInitNested(-1, null);
  }

  public RightUnsignedShiftInitNested<A> addNewRightUnsignedShiftInitLike(RightUnsignedShift item) {
    return new RightUnsignedShiftInitNested(-1, item);
  }

  public A addNewRightUnsignedShiftInit(Object left, Object right) {
    return (A) addToInit(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftInitNested<A> setNewRightUnsignedShiftInitLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftInitNested(index, item);
  }

  public PlusInitNested<A> addNewPlusInit() {
    return new PlusInitNested(-1, null);
  }

  public PlusInitNested<A> addNewPlusInitLike(Plus item) {
    return new PlusInitNested(-1, item);
  }

  public A addNewPlusInit(Object left, Object right) {
    return (A) addToInit(new Plus(left, right));
  }

  public PlusInitNested<A> setNewPlusInitLike(int index, Plus item) {
    return new PlusInitNested(index, item);
  }

  public ConstructInitNested<A> addNewConstructInit() {
    return new ConstructInitNested(-1, null);
  }

  public ConstructInitNested<A> addNewConstructInitLike(Construct item) {
    return new ConstructInitNested(-1, item);
  }

  public ConstructInitNested<A> setNewConstructInitLike(int index, Construct item) {
    return new ConstructInitNested(index, item);
  }

  public XorInitNested<A> addNewXorInit() {
    return new XorInitNested(-1, null);
  }

  public XorInitNested<A> addNewXorInitLike(Xor item) {
    return new XorInitNested(-1, item);
  }

  public A addNewXorInit(Object left, Object right) {
    return (A) addToInit(new Xor(left, right));
  }

  public XorInitNested<A> setNewXorInitLike(int index, Xor item) {
    return new XorInitNested(index, item);
  }

  public PreIncrementInitNested<A> addNewPreIncrementInit() {
    return new PreIncrementInitNested(-1, null);
  }

  public PreIncrementInitNested<A> addNewPreIncrementInitLike(PreIncrement item) {
    return new PreIncrementInitNested(-1, item);
  }

  public PreIncrementInitNested<A> setNewPreIncrementInitLike(int index, PreIncrement item) {
    return new PreIncrementInitNested(index, item);
  }

  public LessThanOrEqualInitNested<A> addNewLessThanOrEqualInit() {
    return new LessThanOrEqualInitNested(-1, null);
  }

  public LessThanOrEqualInitNested<A> addNewLessThanOrEqualInitLike(LessThanOrEqual item) {
    return new LessThanOrEqualInitNested(-1, item);
  }

  public A addNewLessThanOrEqualInit(Object left, Object right) {
    return (A) addToInit(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualInitNested<A> setNewLessThanOrEqualInitLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualInitNested(index, item);
  }

  public PositiveInitNested<A> addNewPositiveInit() {
    return new PositiveInitNested(-1, null);
  }

  public PositiveInitNested<A> addNewPositiveInitLike(Positive item) {
    return new PositiveInitNested(-1, item);
  }

  public PositiveInitNested<A> setNewPositiveInitLike(int index, Positive item) {
    return new PositiveInitNested(index, item);
  }

  public Expression buildCompare() {
    return this.compare != null ? this.compare.build() : null;
  }

  public A withCompare(Expression compare) {
    if (compare == null) {
      this.compare = null;
      _visitables.remove("compare");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(compare);
    _visitables.get("compare").clear();
    _visitables.get("compare").add(builder);
    this.compare = builder;
    return (A) this;
  }

  public boolean hasCompare() {
    return this.compare != null;
  }

  public MultiplyCompareNested<A> withNewMultiplyCompare() {
    return new MultiplyCompareNested(null);
  }

  public MultiplyCompareNested<A> withNewMultiplyCompareLike(Multiply item) {
    return new MultiplyCompareNested(item);
  }

  public A withNewMultiplyCompare(Object left, Object right) {
    return (A) withCompare(new Multiply(left, right));
  }

  public MethodCallCompareNested<A> withNewMethodCallCompare() {
    return new MethodCallCompareNested(null);
  }

  public MethodCallCompareNested<A> withNewMethodCallCompareLike(MethodCall item) {
    return new MethodCallCompareNested(item);
  }

  public InverseCompareNested<A> withNewInverseCompare() {
    return new InverseCompareNested(null);
  }

  public InverseCompareNested<A> withNewInverseCompareLike(Inverse item) {
    return new InverseCompareNested(item);
  }

  public GreaterThanOrEqualCompareNested<A> withNewGreaterThanOrEqualCompare() {
    return new GreaterThanOrEqualCompareNested(null);
  }

  public GreaterThanOrEqualCompareNested<A> withNewGreaterThanOrEqualCompareLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualCompareNested(item);
  }

  public A withNewGreaterThanOrEqualCompare(Object left, Object right) {
    return (A) withCompare(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndCompareNested<A> withNewBitwiseAndCompare() {
    return new BitwiseAndCompareNested(null);
  }

  public BitwiseAndCompareNested<A> withNewBitwiseAndCompareLike(BitwiseAnd item) {
    return new BitwiseAndCompareNested(item);
  }

  public A withNewBitwiseAndCompare(Object left, Object right) {
    return (A) withCompare(new BitwiseAnd(left, right));
  }

  public MinusCompareNested<A> withNewMinusCompare() {
    return new MinusCompareNested(null);
  }

  public MinusCompareNested<A> withNewMinusCompareLike(Minus item) {
    return new MinusCompareNested(item);
  }

  public A withNewMinusCompare(Object left, Object right) {
    return (A) withCompare(new Minus(left, right));
  }

  public LogicalOrCompareNested<A> withNewLogicalOrCompare() {
    return new LogicalOrCompareNested(null);
  }

  public LogicalOrCompareNested<A> withNewLogicalOrCompareLike(LogicalOr item) {
    return new LogicalOrCompareNested(item);
  }

  public A withNewLogicalOrCompare(Object left, Object right) {
    return (A) withCompare(new LogicalOr(left, right));
  }

  public NotEqualsCompareNested<A> withNewNotEqualsCompare() {
    return new NotEqualsCompareNested(null);
  }

  public NotEqualsCompareNested<A> withNewNotEqualsCompareLike(NotEquals item) {
    return new NotEqualsCompareNested(item);
  }

  public A withNewNotEqualsCompare(Object left, Object right) {
    return (A) withCompare(new NotEquals(left, right));
  }

  public DivideCompareNested<A> withNewDivideCompare() {
    return new DivideCompareNested(null);
  }

  public DivideCompareNested<A> withNewDivideCompareLike(Divide item) {
    return new DivideCompareNested(item);
  }

  public A withNewDivideCompare(Object left, Object right) {
    return (A) withCompare(new Divide(left, right));
  }

  public LessThanCompareNested<A> withNewLessThanCompare() {
    return new LessThanCompareNested(null);
  }

  public LessThanCompareNested<A> withNewLessThanCompareLike(LessThan item) {
    return new LessThanCompareNested(item);
  }

  public A withNewLessThanCompare(Object left, Object right) {
    return (A) withCompare(new LessThan(left, right));
  }

  public BitwiseOrCompareNested<A> withNewBitwiseOrCompare() {
    return new BitwiseOrCompareNested(null);
  }

  public BitwiseOrCompareNested<A> withNewBitwiseOrCompareLike(BitwiseOr item) {
    return new BitwiseOrCompareNested(item);
  }

  public A withNewBitwiseOrCompare(Object left, Object right) {
    return (A) withCompare(new BitwiseOr(left, right));
  }

  public PropertyRefCompareNested<A> withNewPropertyRefCompare() {
    return new PropertyRefCompareNested(null);
  }

  public PropertyRefCompareNested<A> withNewPropertyRefCompareLike(PropertyRef item) {
    return new PropertyRefCompareNested(item);
  }

  public RightShiftCompareNested<A> withNewRightShiftCompare() {
    return new RightShiftCompareNested(null);
  }

  public RightShiftCompareNested<A> withNewRightShiftCompareLike(RightShift item) {
    return new RightShiftCompareNested(item);
  }

  public A withNewRightShiftCompare(Object left, Object right) {
    return (A) withCompare(new RightShift(left, right));
  }

  public GreaterThanCompareNested<A> withNewGreaterThanCompare() {
    return new GreaterThanCompareNested(null);
  }

  public GreaterThanCompareNested<A> withNewGreaterThanCompareLike(GreaterThan item) {
    return new GreaterThanCompareNested(item);
  }

  public A withNewGreaterThanCompare(Object left, Object right) {
    return (A) withCompare(new GreaterThan(left, right));
  }

  public ModuloCompareNested<A> withNewModuloCompare() {
    return new ModuloCompareNested(null);
  }

  public ModuloCompareNested<A> withNewModuloCompareLike(Modulo item) {
    return new ModuloCompareNested(item);
  }

  public A withNewModuloCompare(Object left, Object right) {
    return (A) withCompare(new Modulo(left, right));
  }

  public ValueRefCompareNested<A> withNewValueRefCompare() {
    return new ValueRefCompareNested(null);
  }

  public ValueRefCompareNested<A> withNewValueRefCompareLike(ValueRef item) {
    return new ValueRefCompareNested(item);
  }

  public A withNewValueRefCompare(Object value) {
    return (A) withCompare(new ValueRef(value));
  }

  public LeftShiftCompareNested<A> withNewLeftShiftCompare() {
    return new LeftShiftCompareNested(null);
  }

  public LeftShiftCompareNested<A> withNewLeftShiftCompareLike(LeftShift item) {
    return new LeftShiftCompareNested(item);
  }

  public A withNewLeftShiftCompare(Object left, Object right) {
    return (A) withCompare(new LeftShift(left, right));
  }

  public TernaryCompareNested<A> withNewTernaryCompare() {
    return new TernaryCompareNested(null);
  }

  public TernaryCompareNested<A> withNewTernaryCompareLike(Ternary item) {
    return new TernaryCompareNested(item);
  }

  public BinaryExpressionCompareNested<A> withNewBinaryExpressionCompare() {
    return new BinaryExpressionCompareNested(null);
  }

  public BinaryExpressionCompareNested<A> withNewBinaryExpressionCompareLike(BinaryExpression item) {
    return new BinaryExpressionCompareNested(item);
  }

  public EqualsCompareNested<A> withNewEqualsCompare() {
    return new EqualsCompareNested(null);
  }

  public EqualsCompareNested<A> withNewEqualsCompareLike(Equals item) {
    return new EqualsCompareNested(item);
  }

  public A withNewEqualsCompare(Object left, Object right) {
    return (A) withCompare(new Equals(left, right));
  }

  public EnclosedCompareNested<A> withNewEnclosedCompare() {
    return new EnclosedCompareNested(null);
  }

  public EnclosedCompareNested<A> withNewEnclosedCompareLike(Enclosed item) {
    return new EnclosedCompareNested(item);
  }

  public PreDecrementCompareNested<A> withNewPreDecrementCompare() {
    return new PreDecrementCompareNested(null);
  }

  public PreDecrementCompareNested<A> withNewPreDecrementCompareLike(PreDecrement item) {
    return new PreDecrementCompareNested(item);
  }

  public PostDecrementCompareNested<A> withNewPostDecrementCompare() {
    return new PostDecrementCompareNested(null);
  }

  public PostDecrementCompareNested<A> withNewPostDecrementCompareLike(PostDecrement item) {
    return new PostDecrementCompareNested(item);
  }

  public NotCompareNested<A> withNewNotCompare() {
    return new NotCompareNested(null);
  }

  public NotCompareNested<A> withNewNotCompareLike(Not item) {
    return new NotCompareNested(item);
  }

  public AssignCompareNested<A> withNewAssignCompare() {
    return new AssignCompareNested(null);
  }

  public AssignCompareNested<A> withNewAssignCompareLike(Assign item) {
    return new AssignCompareNested(item);
  }

  public NegativeCompareNested<A> withNewNegativeCompare() {
    return new NegativeCompareNested(null);
  }

  public NegativeCompareNested<A> withNewNegativeCompareLike(Negative item) {
    return new NegativeCompareNested(item);
  }

  public ThisCompareNested<A> withNewThisCompare() {
    return new ThisCompareNested(null);
  }

  public ThisCompareNested<A> withNewThisCompareLike(This item) {
    return new ThisCompareNested(item);
  }

  public LogicalAndCompareNested<A> withNewLogicalAndCompare() {
    return new LogicalAndCompareNested(null);
  }

  public LogicalAndCompareNested<A> withNewLogicalAndCompareLike(LogicalAnd item) {
    return new LogicalAndCompareNested(item);
  }

  public A withNewLogicalAndCompare(Object left, Object right) {
    return (A) withCompare(new LogicalAnd(left, right));
  }

  public PostIncrementCompareNested<A> withNewPostIncrementCompare() {
    return new PostIncrementCompareNested(null);
  }

  public PostIncrementCompareNested<A> withNewPostIncrementCompareLike(PostIncrement item) {
    return new PostIncrementCompareNested(item);
  }

  public RightUnsignedShiftCompareNested<A> withNewRightUnsignedShiftCompare() {
    return new RightUnsignedShiftCompareNested(null);
  }

  public RightUnsignedShiftCompareNested<A> withNewRightUnsignedShiftCompareLike(RightUnsignedShift item) {
    return new RightUnsignedShiftCompareNested(item);
  }

  public A withNewRightUnsignedShiftCompare(Object left, Object right) {
    return (A) withCompare(new RightUnsignedShift(left, right));
  }

  public PlusCompareNested<A> withNewPlusCompare() {
    return new PlusCompareNested(null);
  }

  public PlusCompareNested<A> withNewPlusCompareLike(Plus item) {
    return new PlusCompareNested(item);
  }

  public A withNewPlusCompare(Object left, Object right) {
    return (A) withCompare(new Plus(left, right));
  }

  public ConstructCompareNested<A> withNewConstructCompare() {
    return new ConstructCompareNested(null);
  }

  public ConstructCompareNested<A> withNewConstructCompareLike(Construct item) {
    return new ConstructCompareNested(item);
  }

  public XorCompareNested<A> withNewXorCompare() {
    return new XorCompareNested(null);
  }

  public XorCompareNested<A> withNewXorCompareLike(Xor item) {
    return new XorCompareNested(item);
  }

  public A withNewXorCompare(Object left, Object right) {
    return (A) withCompare(new Xor(left, right));
  }

  public PreIncrementCompareNested<A> withNewPreIncrementCompare() {
    return new PreIncrementCompareNested(null);
  }

  public PreIncrementCompareNested<A> withNewPreIncrementCompareLike(PreIncrement item) {
    return new PreIncrementCompareNested(item);
  }

  public LessThanOrEqualCompareNested<A> withNewLessThanOrEqualCompare() {
    return new LessThanOrEqualCompareNested(null);
  }

  public LessThanOrEqualCompareNested<A> withNewLessThanOrEqualCompareLike(LessThanOrEqual item) {
    return new LessThanOrEqualCompareNested(item);
  }

  public A withNewLessThanOrEqualCompare(Object left, Object right) {
    return (A) withCompare(new LessThanOrEqual(left, right));
  }

  public PositiveCompareNested<A> withNewPositiveCompare() {
    return new PositiveCompareNested(null);
  }

  public PositiveCompareNested<A> withNewPositiveCompareLike(Positive item) {
    return new PositiveCompareNested(item);
  }

  public A addToUpdate(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    _visitables.get("update").add(builder);
    this.update.add(builder);
    return (A) this;
  }

  public A addToUpdate(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    if (index < 0 || index >= update.size()) {
      _visitables.get("update").add(builder);
      update.add(builder);
    } else {
      _visitables.get("update").add(index, builder);
      update.add(index, builder);
    }
    return (A) this;
  }

  public A addToUpdate(int index, Expression item) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= update.size()) {
      _visitables.get("update").add(builder);
      update.add(builder);
    } else {
      _visitables.get("update").add(index, builder);
      update.add(index, builder);
    }
    return (A) this;
  }

  public A setToUpdate(int index, Expression item) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= update.size()) {
      _visitables.get("update").add(builder);
      update.add(builder);
    } else {
      _visitables.get("update").set(index, builder);
      update.set(index, builder);
    }
    return (A) this;
  }

  public A addToUpdate(io.sundr.model.Expression... items) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("update").add(builder);
      this.update.add(builder);
    }
    return (A) this;
  }

  public A addAllToUpdate(Collection<Expression> items) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("update").add(builder);
      this.update.add(builder);
    }
    return (A) this;
  }

  public A removeFromUpdate(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.update == null)
      return (A) this;
    _visitables.get("update").remove(builder);
    this.update.remove(builder);
    return (A) this;
  }

  public A removeFromUpdate(io.sundr.model.Expression... items) {
    if (this.update == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("update").remove(builder);
      this.update.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromUpdate(Collection<Expression> items) {
    if (this.update == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("update").remove(builder);
      this.update.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromUpdate(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    if (update == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends Expression, ?>> each = update.iterator();
    final List visitables = _visitables.get("update");
    while (each.hasNext()) {
      VisitableBuilder<? extends Expression, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<Expression> buildUpdate() {
    return build(update);
  }

  public Expression buildUpdate(int index) {
    return this.update.get(index).build();
  }

  public Expression buildFirstUpdate() {
    return this.update.get(0).build();
  }

  public Expression buildLastUpdate() {
    return this.update.get(update.size() - 1).build();
  }

  public Expression buildMatchingUpdate(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : update) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingUpdate(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : update) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withUpdate(List<Expression> update) {
    if (update != null) {
      this.update = new ArrayList();
      for (Expression item : update) {
        this.addToUpdate(item);
      }
    } else {
      this.update = null;
    }
    return (A) this;
  }

  public A withUpdate(io.sundr.model.Expression... update) {
    if (this.update != null) {
      this.update.clear();
      _visitables.remove("update");
    }
    if (update != null) {
      for (Expression item : update) {
        this.addToUpdate(item);
      }
    }
    return (A) this;
  }

  public boolean hasUpdate() {
    return update != null && !update.isEmpty();
  }

  public MultiplyUpdateNested<A> addNewMultiplyUpdate() {
    return new MultiplyUpdateNested(-1, null);
  }

  public MultiplyUpdateNested<A> addNewMultiplyUpdateLike(Multiply item) {
    return new MultiplyUpdateNested(-1, item);
  }

  public A addNewMultiplyUpdate(Object left, Object right) {
    return (A) addToUpdate(new Multiply(left, right));
  }

  public MultiplyUpdateNested<A> setNewMultiplyUpdateLike(int index, Multiply item) {
    return new MultiplyUpdateNested(index, item);
  }

  public MethodCallUpdateNested<A> addNewMethodCallUpdate() {
    return new MethodCallUpdateNested(-1, null);
  }

  public MethodCallUpdateNested<A> addNewMethodCallUpdateLike(MethodCall item) {
    return new MethodCallUpdateNested(-1, item);
  }

  public MethodCallUpdateNested<A> setNewMethodCallUpdateLike(int index, MethodCall item) {
    return new MethodCallUpdateNested(index, item);
  }

  public InverseUpdateNested<A> addNewInverseUpdate() {
    return new InverseUpdateNested(-1, null);
  }

  public InverseUpdateNested<A> addNewInverseUpdateLike(Inverse item) {
    return new InverseUpdateNested(-1, item);
  }

  public InverseUpdateNested<A> setNewInverseUpdateLike(int index, Inverse item) {
    return new InverseUpdateNested(index, item);
  }

  public GreaterThanOrEqualUpdateNested<A> addNewGreaterThanOrEqualUpdate() {
    return new GreaterThanOrEqualUpdateNested(-1, null);
  }

  public GreaterThanOrEqualUpdateNested<A> addNewGreaterThanOrEqualUpdateLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualUpdateNested(-1, item);
  }

  public A addNewGreaterThanOrEqualUpdate(Object left, Object right) {
    return (A) addToUpdate(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualUpdateNested<A> setNewGreaterThanOrEqualUpdateLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualUpdateNested(index, item);
  }

  public BitwiseAndUpdateNested<A> addNewBitwiseAndUpdate() {
    return new BitwiseAndUpdateNested(-1, null);
  }

  public BitwiseAndUpdateNested<A> addNewBitwiseAndUpdateLike(BitwiseAnd item) {
    return new BitwiseAndUpdateNested(-1, item);
  }

  public A addNewBitwiseAndUpdate(Object left, Object right) {
    return (A) addToUpdate(new BitwiseAnd(left, right));
  }

  public BitwiseAndUpdateNested<A> setNewBitwiseAndUpdateLike(int index, BitwiseAnd item) {
    return new BitwiseAndUpdateNested(index, item);
  }

  public MinusUpdateNested<A> addNewMinusUpdate() {
    return new MinusUpdateNested(-1, null);
  }

  public MinusUpdateNested<A> addNewMinusUpdateLike(Minus item) {
    return new MinusUpdateNested(-1, item);
  }

  public A addNewMinusUpdate(Object left, Object right) {
    return (A) addToUpdate(new Minus(left, right));
  }

  public MinusUpdateNested<A> setNewMinusUpdateLike(int index, Minus item) {
    return new MinusUpdateNested(index, item);
  }

  public LogicalOrUpdateNested<A> addNewLogicalOrUpdate() {
    return new LogicalOrUpdateNested(-1, null);
  }

  public LogicalOrUpdateNested<A> addNewLogicalOrUpdateLike(LogicalOr item) {
    return new LogicalOrUpdateNested(-1, item);
  }

  public A addNewLogicalOrUpdate(Object left, Object right) {
    return (A) addToUpdate(new LogicalOr(left, right));
  }

  public LogicalOrUpdateNested<A> setNewLogicalOrUpdateLike(int index, LogicalOr item) {
    return new LogicalOrUpdateNested(index, item);
  }

  public NotEqualsUpdateNested<A> addNewNotEqualsUpdate() {
    return new NotEqualsUpdateNested(-1, null);
  }

  public NotEqualsUpdateNested<A> addNewNotEqualsUpdateLike(NotEquals item) {
    return new NotEqualsUpdateNested(-1, item);
  }

  public A addNewNotEqualsUpdate(Object left, Object right) {
    return (A) addToUpdate(new NotEquals(left, right));
  }

  public NotEqualsUpdateNested<A> setNewNotEqualsUpdateLike(int index, NotEquals item) {
    return new NotEqualsUpdateNested(index, item);
  }

  public DivideUpdateNested<A> addNewDivideUpdate() {
    return new DivideUpdateNested(-1, null);
  }

  public DivideUpdateNested<A> addNewDivideUpdateLike(Divide item) {
    return new DivideUpdateNested(-1, item);
  }

  public A addNewDivideUpdate(Object left, Object right) {
    return (A) addToUpdate(new Divide(left, right));
  }

  public DivideUpdateNested<A> setNewDivideUpdateLike(int index, Divide item) {
    return new DivideUpdateNested(index, item);
  }

  public LessThanUpdateNested<A> addNewLessThanUpdate() {
    return new LessThanUpdateNested(-1, null);
  }

  public LessThanUpdateNested<A> addNewLessThanUpdateLike(LessThan item) {
    return new LessThanUpdateNested(-1, item);
  }

  public A addNewLessThanUpdate(Object left, Object right) {
    return (A) addToUpdate(new LessThan(left, right));
  }

  public LessThanUpdateNested<A> setNewLessThanUpdateLike(int index, LessThan item) {
    return new LessThanUpdateNested(index, item);
  }

  public BitwiseOrUpdateNested<A> addNewBitwiseOrUpdate() {
    return new BitwiseOrUpdateNested(-1, null);
  }

  public BitwiseOrUpdateNested<A> addNewBitwiseOrUpdateLike(BitwiseOr item) {
    return new BitwiseOrUpdateNested(-1, item);
  }

  public A addNewBitwiseOrUpdate(Object left, Object right) {
    return (A) addToUpdate(new BitwiseOr(left, right));
  }

  public BitwiseOrUpdateNested<A> setNewBitwiseOrUpdateLike(int index, BitwiseOr item) {
    return new BitwiseOrUpdateNested(index, item);
  }

  public PropertyRefUpdateNested<A> addNewPropertyRefUpdate() {
    return new PropertyRefUpdateNested(-1, null);
  }

  public PropertyRefUpdateNested<A> addNewPropertyRefUpdateLike(PropertyRef item) {
    return new PropertyRefUpdateNested(-1, item);
  }

  public PropertyRefUpdateNested<A> setNewPropertyRefUpdateLike(int index, PropertyRef item) {
    return new PropertyRefUpdateNested(index, item);
  }

  public RightShiftUpdateNested<A> addNewRightShiftUpdate() {
    return new RightShiftUpdateNested(-1, null);
  }

  public RightShiftUpdateNested<A> addNewRightShiftUpdateLike(RightShift item) {
    return new RightShiftUpdateNested(-1, item);
  }

  public A addNewRightShiftUpdate(Object left, Object right) {
    return (A) addToUpdate(new RightShift(left, right));
  }

  public RightShiftUpdateNested<A> setNewRightShiftUpdateLike(int index, RightShift item) {
    return new RightShiftUpdateNested(index, item);
  }

  public GreaterThanUpdateNested<A> addNewGreaterThanUpdate() {
    return new GreaterThanUpdateNested(-1, null);
  }

  public GreaterThanUpdateNested<A> addNewGreaterThanUpdateLike(GreaterThan item) {
    return new GreaterThanUpdateNested(-1, item);
  }

  public A addNewGreaterThanUpdate(Object left, Object right) {
    return (A) addToUpdate(new GreaterThan(left, right));
  }

  public GreaterThanUpdateNested<A> setNewGreaterThanUpdateLike(int index, GreaterThan item) {
    return new GreaterThanUpdateNested(index, item);
  }

  public ModuloUpdateNested<A> addNewModuloUpdate() {
    return new ModuloUpdateNested(-1, null);
  }

  public ModuloUpdateNested<A> addNewModuloUpdateLike(Modulo item) {
    return new ModuloUpdateNested(-1, item);
  }

  public A addNewModuloUpdate(Object left, Object right) {
    return (A) addToUpdate(new Modulo(left, right));
  }

  public ModuloUpdateNested<A> setNewModuloUpdateLike(int index, Modulo item) {
    return new ModuloUpdateNested(index, item);
  }

  public ValueRefUpdateNested<A> addNewValueRefUpdate() {
    return new ValueRefUpdateNested(-1, null);
  }

  public ValueRefUpdateNested<A> addNewValueRefUpdateLike(ValueRef item) {
    return new ValueRefUpdateNested(-1, item);
  }

  public A addNewValueRefUpdate(Object value) {
    return (A) addToUpdate(new ValueRef(value));
  }

  public ValueRefUpdateNested<A> setNewValueRefUpdateLike(int index, ValueRef item) {
    return new ValueRefUpdateNested(index, item);
  }

  public LeftShiftUpdateNested<A> addNewLeftShiftUpdate() {
    return new LeftShiftUpdateNested(-1, null);
  }

  public LeftShiftUpdateNested<A> addNewLeftShiftUpdateLike(LeftShift item) {
    return new LeftShiftUpdateNested(-1, item);
  }

  public A addNewLeftShiftUpdate(Object left, Object right) {
    return (A) addToUpdate(new LeftShift(left, right));
  }

  public LeftShiftUpdateNested<A> setNewLeftShiftUpdateLike(int index, LeftShift item) {
    return new LeftShiftUpdateNested(index, item);
  }

  public TernaryUpdateNested<A> addNewTernaryUpdate() {
    return new TernaryUpdateNested(-1, null);
  }

  public TernaryUpdateNested<A> addNewTernaryUpdateLike(Ternary item) {
    return new TernaryUpdateNested(-1, item);
  }

  public TernaryUpdateNested<A> setNewTernaryUpdateLike(int index, Ternary item) {
    return new TernaryUpdateNested(index, item);
  }

  public BinaryExpressionUpdateNested<A> addNewBinaryExpressionUpdate() {
    return new BinaryExpressionUpdateNested(-1, null);
  }

  public BinaryExpressionUpdateNested<A> addNewBinaryExpressionUpdateLike(BinaryExpression item) {
    return new BinaryExpressionUpdateNested(-1, item);
  }

  public BinaryExpressionUpdateNested<A> setNewBinaryExpressionUpdateLike(int index, BinaryExpression item) {
    return new BinaryExpressionUpdateNested(index, item);
  }

  public EqualsUpdateNested<A> addNewEqualsUpdate() {
    return new EqualsUpdateNested(-1, null);
  }

  public EqualsUpdateNested<A> addNewEqualsUpdateLike(Equals item) {
    return new EqualsUpdateNested(-1, item);
  }

  public A addNewEqualsUpdate(Object left, Object right) {
    return (A) addToUpdate(new Equals(left, right));
  }

  public EqualsUpdateNested<A> setNewEqualsUpdateLike(int index, Equals item) {
    return new EqualsUpdateNested(index, item);
  }

  public EnclosedUpdateNested<A> addNewEnclosedUpdate() {
    return new EnclosedUpdateNested(-1, null);
  }

  public EnclosedUpdateNested<A> addNewEnclosedUpdateLike(Enclosed item) {
    return new EnclosedUpdateNested(-1, item);
  }

  public EnclosedUpdateNested<A> setNewEnclosedUpdateLike(int index, Enclosed item) {
    return new EnclosedUpdateNested(index, item);
  }

  public PreDecrementUpdateNested<A> addNewPreDecrementUpdate() {
    return new PreDecrementUpdateNested(-1, null);
  }

  public PreDecrementUpdateNested<A> addNewPreDecrementUpdateLike(PreDecrement item) {
    return new PreDecrementUpdateNested(-1, item);
  }

  public PreDecrementUpdateNested<A> setNewPreDecrementUpdateLike(int index, PreDecrement item) {
    return new PreDecrementUpdateNested(index, item);
  }

  public PostDecrementUpdateNested<A> addNewPostDecrementUpdate() {
    return new PostDecrementUpdateNested(-1, null);
  }

  public PostDecrementUpdateNested<A> addNewPostDecrementUpdateLike(PostDecrement item) {
    return new PostDecrementUpdateNested(-1, item);
  }

  public PostDecrementUpdateNested<A> setNewPostDecrementUpdateLike(int index, PostDecrement item) {
    return new PostDecrementUpdateNested(index, item);
  }

  public NotUpdateNested<A> addNewNotUpdate() {
    return new NotUpdateNested(-1, null);
  }

  public NotUpdateNested<A> addNewNotUpdateLike(Not item) {
    return new NotUpdateNested(-1, item);
  }

  public NotUpdateNested<A> setNewNotUpdateLike(int index, Not item) {
    return new NotUpdateNested(index, item);
  }

  public AssignUpdateNested<A> addNewAssignUpdate() {
    return new AssignUpdateNested(-1, null);
  }

  public AssignUpdateNested<A> addNewAssignUpdateLike(Assign item) {
    return new AssignUpdateNested(-1, item);
  }

  public AssignUpdateNested<A> setNewAssignUpdateLike(int index, Assign item) {
    return new AssignUpdateNested(index, item);
  }

  public NegativeUpdateNested<A> addNewNegativeUpdate() {
    return new NegativeUpdateNested(-1, null);
  }

  public NegativeUpdateNested<A> addNewNegativeUpdateLike(Negative item) {
    return new NegativeUpdateNested(-1, item);
  }

  public NegativeUpdateNested<A> setNewNegativeUpdateLike(int index, Negative item) {
    return new NegativeUpdateNested(index, item);
  }

  public ThisUpdateNested<A> addNewThisUpdate() {
    return new ThisUpdateNested(-1, null);
  }

  public ThisUpdateNested<A> addNewThisUpdateLike(This item) {
    return new ThisUpdateNested(-1, item);
  }

  public ThisUpdateNested<A> setNewThisUpdateLike(int index, This item) {
    return new ThisUpdateNested(index, item);
  }

  public LogicalAndUpdateNested<A> addNewLogicalAndUpdate() {
    return new LogicalAndUpdateNested(-1, null);
  }

  public LogicalAndUpdateNested<A> addNewLogicalAndUpdateLike(LogicalAnd item) {
    return new LogicalAndUpdateNested(-1, item);
  }

  public A addNewLogicalAndUpdate(Object left, Object right) {
    return (A) addToUpdate(new LogicalAnd(left, right));
  }

  public LogicalAndUpdateNested<A> setNewLogicalAndUpdateLike(int index, LogicalAnd item) {
    return new LogicalAndUpdateNested(index, item);
  }

  public PostIncrementUpdateNested<A> addNewPostIncrementUpdate() {
    return new PostIncrementUpdateNested(-1, null);
  }

  public PostIncrementUpdateNested<A> addNewPostIncrementUpdateLike(PostIncrement item) {
    return new PostIncrementUpdateNested(-1, item);
  }

  public PostIncrementUpdateNested<A> setNewPostIncrementUpdateLike(int index, PostIncrement item) {
    return new PostIncrementUpdateNested(index, item);
  }

  public RightUnsignedShiftUpdateNested<A> addNewRightUnsignedShiftUpdate() {
    return new RightUnsignedShiftUpdateNested(-1, null);
  }

  public RightUnsignedShiftUpdateNested<A> addNewRightUnsignedShiftUpdateLike(RightUnsignedShift item) {
    return new RightUnsignedShiftUpdateNested(-1, item);
  }

  public A addNewRightUnsignedShiftUpdate(Object left, Object right) {
    return (A) addToUpdate(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftUpdateNested<A> setNewRightUnsignedShiftUpdateLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftUpdateNested(index, item);
  }

  public PlusUpdateNested<A> addNewPlusUpdate() {
    return new PlusUpdateNested(-1, null);
  }

  public PlusUpdateNested<A> addNewPlusUpdateLike(Plus item) {
    return new PlusUpdateNested(-1, item);
  }

  public A addNewPlusUpdate(Object left, Object right) {
    return (A) addToUpdate(new Plus(left, right));
  }

  public PlusUpdateNested<A> setNewPlusUpdateLike(int index, Plus item) {
    return new PlusUpdateNested(index, item);
  }

  public ConstructUpdateNested<A> addNewConstructUpdate() {
    return new ConstructUpdateNested(-1, null);
  }

  public ConstructUpdateNested<A> addNewConstructUpdateLike(Construct item) {
    return new ConstructUpdateNested(-1, item);
  }

  public ConstructUpdateNested<A> setNewConstructUpdateLike(int index, Construct item) {
    return new ConstructUpdateNested(index, item);
  }

  public XorUpdateNested<A> addNewXorUpdate() {
    return new XorUpdateNested(-1, null);
  }

  public XorUpdateNested<A> addNewXorUpdateLike(Xor item) {
    return new XorUpdateNested(-1, item);
  }

  public A addNewXorUpdate(Object left, Object right) {
    return (A) addToUpdate(new Xor(left, right));
  }

  public XorUpdateNested<A> setNewXorUpdateLike(int index, Xor item) {
    return new XorUpdateNested(index, item);
  }

  public PreIncrementUpdateNested<A> addNewPreIncrementUpdate() {
    return new PreIncrementUpdateNested(-1, null);
  }

  public PreIncrementUpdateNested<A> addNewPreIncrementUpdateLike(PreIncrement item) {
    return new PreIncrementUpdateNested(-1, item);
  }

  public PreIncrementUpdateNested<A> setNewPreIncrementUpdateLike(int index, PreIncrement item) {
    return new PreIncrementUpdateNested(index, item);
  }

  public LessThanOrEqualUpdateNested<A> addNewLessThanOrEqualUpdate() {
    return new LessThanOrEqualUpdateNested(-1, null);
  }

  public LessThanOrEqualUpdateNested<A> addNewLessThanOrEqualUpdateLike(LessThanOrEqual item) {
    return new LessThanOrEqualUpdateNested(-1, item);
  }

  public A addNewLessThanOrEqualUpdate(Object left, Object right) {
    return (A) addToUpdate(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualUpdateNested<A> setNewLessThanOrEqualUpdateLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualUpdateNested(index, item);
  }

  public PositiveUpdateNested<A> addNewPositiveUpdate() {
    return new PositiveUpdateNested(-1, null);
  }

  public PositiveUpdateNested<A> addNewPositiveUpdateLike(Positive item) {
    return new PositiveUpdateNested(-1, item);
  }

  public PositiveUpdateNested<A> setNewPositiveUpdateLike(int index, Positive item) {
    return new PositiveUpdateNested(index, item);
  }

  public Statement buildBody() {
    return this.body != null ? this.body.build() : null;
  }

  public A withBody(Statement body) {
    if (body == null) {
      this.body = null;
      _visitables.remove("body");
      return (A) this;
    }
    VisitableBuilder<? extends Statement, ?> builder = builder(body);
    _visitables.get("body").clear();
    _visitables.get("body").add(builder);
    this.body = builder;
    return (A) this;
  }

  public boolean hasBody() {
    return this.body != null;
  }

  public SwitchBodyNested<A> withNewSwitchBody() {
    return new SwitchBodyNested(null);
  }

  public SwitchBodyNested<A> withNewSwitchBodyLike(Switch item) {
    return new SwitchBodyNested(item);
  }

  public BreakBodyNested<A> withNewBreakBody() {
    return new BreakBodyNested(null);
  }

  public BreakBodyNested<A> withNewBreakBodyLike(Break item) {
    return new BreakBodyNested(item);
  }

  public DeclareBodyNested<A> withNewDeclareBody() {
    return new DeclareBodyNested(null);
  }

  public DeclareBodyNested<A> withNewDeclareBodyLike(Declare item) {
    return new DeclareBodyNested(item);
  }

  public WhileBodyNested<A> withNewWhileBody() {
    return new WhileBodyNested(null);
  }

  public WhileBodyNested<A> withNewWhileBodyLike(While item) {
    return new WhileBodyNested(item);
  }

  public ContinueBodyNested<A> withNewContinueBody() {
    return new ContinueBodyNested(null);
  }

  public ContinueBodyNested<A> withNewContinueBodyLike(Continue item) {
    return new ContinueBodyNested(item);
  }

  public StringStatementBodyNested<A> withNewStringStatementBody() {
    return new StringStatementBodyNested(null);
  }

  public StringStatementBodyNested<A> withNewStringStatementBodyLike(StringStatement item) {
    return new StringStatementBodyNested(item);
  }

  public A withNewStringStatementBody(String data) {
    return (A) withBody(new StringStatement(data));
  }

  public A withNewStringStatementBody(String data, Object[] parameters) {
    return (A) withBody(new StringStatement(data, parameters));
  }

  public DoBodyNested<A> withNewDoBody() {
    return new DoBodyNested(null);
  }

  public DoBodyNested<A> withNewDoBodyLike(Do item) {
    return new DoBodyNested(item);
  }

  public ForeachBodyNested<A> withNewForeachBody() {
    return new ForeachBodyNested(null);
  }

  public ForeachBodyNested<A> withNewForeachBodyLike(Foreach item) {
    return new ForeachBodyNested(item);
  }

  public BlockBodyNested<A> withNewBlockBody() {
    return new BlockBodyNested(null);
  }

  public BlockBodyNested<A> withNewBlockBodyLike(Block item) {
    return new BlockBodyNested(item);
  }

  public IfBodyNested<A> withNewIfBody() {
    return new IfBodyNested(null);
  }

  public IfBodyNested<A> withNewIfBodyLike(If item) {
    return new IfBodyNested(item);
  }

  public ReturnBodyNested<A> withNewReturnBody() {
    return new ReturnBodyNested(null);
  }

  public ReturnBodyNested<A> withNewReturnBodyLike(Return item) {
    return new ReturnBodyNested(item);
  }

  public AssignBodyNested<A> withNewAssignBody() {
    return new AssignBodyNested(null);
  }

  public AssignBodyNested<A> withNewAssignBodyLike(Assign item) {
    return new AssignBodyNested(item);
  }

  public ForBodyNested<A> withNewForBody() {
    return new ForBodyNested(null);
  }

  public ForBodyNested<A> withNewForBodyLike(For item) {
    return new ForBodyNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ForFluent that = (ForFluent) o;
    if (!java.util.Objects.equals(init, that.init))
      return false;

    if (!java.util.Objects.equals(compare, that.compare))
      return false;

    if (!java.util.Objects.equals(update, that.update))
      return false;

    if (!java.util.Objects.equals(body, that.body))
      return false;

    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(init, compare, update, body, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (init != null && !init.isEmpty()) {
      sb.append("init:");
      sb.append(init + ",");
    }
    if (compare != null) {
      sb.append("compare:");
      sb.append(compare + ",");
    }
    if (update != null && !update.isEmpty()) {
      sb.append("update:");
      sb.append(update + ",");
    }
    if (body != null) {
      sb.append("body:");
      sb.append(body);
    }
    sb.append("}");
    return sb.toString();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "io.sundr.model." + "Multiply":
        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);
      case "io.sundr.model." + "MethodCall":
        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);
      case "io.sundr.model." + "Inverse":
        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);
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
      case "io.sundr.model." + "Switch":
        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);
      case "io.sundr.model." + "Break":
        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);
      case "io.sundr.model." + "Declare":
        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);
      case "io.sundr.model." + "While":
        return (VisitableBuilder<T, ?>) new WhileBuilder((While) item);
      case "io.sundr.model." + "Continue":
        return (VisitableBuilder<T, ?>) new ContinueBuilder((Continue) item);
      case "io.sundr.model." + "StringStatement":
        return (VisitableBuilder<T, ?>) new StringStatementBuilder((StringStatement) item);
      case "io.sundr.model." + "Do":
        return (VisitableBuilder<T, ?>) new DoBuilder((Do) item);
      case "io.sundr.model." + "Foreach":
        return (VisitableBuilder<T, ?>) new ForeachBuilder((Foreach) item);
      case "io.sundr.model." + "Block":
        return (VisitableBuilder<T, ?>) new BlockBuilder((Block) item);
      case "io.sundr.model." + "If":
        return (VisitableBuilder<T, ?>) new IfBuilder((If) item);
      case "io.sundr.model." + "Return":
        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);
      case "io.sundr.model." + "For":
        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class MultiplyInitNested<N> extends MultiplyFluent<MultiplyInitNested<N>> implements Nested<N> {
    MultiplyInitNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endMultiplyInit() {
      return and();
    }

  }

  public class MethodCallInitNested<N> extends MethodCallFluent<MethodCallInitNested<N>> implements Nested<N> {
    MethodCallInitNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endMethodCallInit() {
      return and();
    }

  }

  public class InverseInitNested<N> extends InverseFluent<InverseInitNested<N>> implements Nested<N> {
    InverseInitNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endInverseInit() {
      return and();
    }

  }

  public class GreaterThanOrEqualInitNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualInitNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualInitNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endGreaterThanOrEqualInit() {
      return and();
    }

  }

  public class BitwiseAndInitNested<N> extends BitwiseAndFluent<BitwiseAndInitNested<N>> implements Nested<N> {
    BitwiseAndInitNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endBitwiseAndInit() {
      return and();
    }

  }

  public class MinusInitNested<N> extends MinusFluent<MinusInitNested<N>> implements Nested<N> {
    MinusInitNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endMinusInit() {
      return and();
    }

  }

  public class LogicalOrInitNested<N> extends LogicalOrFluent<LogicalOrInitNested<N>> implements Nested<N> {
    LogicalOrInitNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLogicalOrInit() {
      return and();
    }

  }

  public class NotEqualsInitNested<N> extends NotEqualsFluent<NotEqualsInitNested<N>> implements Nested<N> {
    NotEqualsInitNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNotEqualsInit() {
      return and();
    }

  }

  public class DivideInitNested<N> extends DivideFluent<DivideInitNested<N>> implements Nested<N> {
    DivideInitNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endDivideInit() {
      return and();
    }

  }

  public class LessThanInitNested<N> extends LessThanFluent<LessThanInitNested<N>> implements Nested<N> {
    LessThanInitNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLessThanInit() {
      return and();
    }

  }

  public class BitwiseOrInitNested<N> extends BitwiseOrFluent<BitwiseOrInitNested<N>> implements Nested<N> {
    BitwiseOrInitNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endBitwiseOrInit() {
      return and();
    }

  }

  public class PropertyRefInitNested<N> extends PropertyRefFluent<PropertyRefInitNested<N>> implements Nested<N> {
    PropertyRefInitNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPropertyRefInit() {
      return and();
    }

  }

  public class RightShiftInitNested<N> extends RightShiftFluent<RightShiftInitNested<N>> implements Nested<N> {
    RightShiftInitNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endRightShiftInit() {
      return and();
    }

  }

  public class GreaterThanInitNested<N> extends GreaterThanFluent<GreaterThanInitNested<N>> implements Nested<N> {
    GreaterThanInitNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endGreaterThanInit() {
      return and();
    }

  }

  public class ModuloInitNested<N> extends ModuloFluent<ModuloInitNested<N>> implements Nested<N> {
    ModuloInitNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endModuloInit() {
      return and();
    }

  }

  public class ValueRefInitNested<N> extends ValueRefFluent<ValueRefInitNested<N>> implements Nested<N> {
    ValueRefInitNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endValueRefInit() {
      return and();
    }

  }

  public class LeftShiftInitNested<N> extends LeftShiftFluent<LeftShiftInitNested<N>> implements Nested<N> {
    LeftShiftInitNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLeftShiftInit() {
      return and();
    }

  }

  public class TernaryInitNested<N> extends TernaryFluent<TernaryInitNested<N>> implements Nested<N> {
    TernaryInitNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endTernaryInit() {
      return and();
    }

  }

  public class BinaryExpressionInitNested<N> extends BinaryExpressionFluent<BinaryExpressionInitNested<N>>
      implements Nested<N> {
    BinaryExpressionInitNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endBinaryExpressionInit() {
      return and();
    }

  }

  public class EqualsInitNested<N> extends EqualsFluent<EqualsInitNested<N>> implements Nested<N> {
    EqualsInitNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endEqualsInit() {
      return and();
    }

  }

  public class EnclosedInitNested<N> extends EnclosedFluent<EnclosedInitNested<N>> implements Nested<N> {
    EnclosedInitNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endEnclosedInit() {
      return and();
    }

  }

  public class PreDecrementInitNested<N> extends PreDecrementFluent<PreDecrementInitNested<N>> implements Nested<N> {
    PreDecrementInitNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPreDecrementInit() {
      return and();
    }

  }

  public class PostDecrementInitNested<N> extends PostDecrementFluent<PostDecrementInitNested<N>> implements Nested<N> {
    PostDecrementInitNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPostDecrementInit() {
      return and();
    }

  }

  public class NotInitNested<N> extends NotFluent<NotInitNested<N>> implements Nested<N> {
    NotInitNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNotInit() {
      return and();
    }

  }

  public class AssignInitNested<N> extends AssignFluent<AssignInitNested<N>> implements Nested<N> {
    AssignInitNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endAssignInit() {
      return and();
    }

  }

  public class NegativeInitNested<N> extends NegativeFluent<NegativeInitNested<N>> implements Nested<N> {
    NegativeInitNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNegativeInit() {
      return and();
    }

  }

  public class ThisInitNested<N> extends ThisFluent<ThisInitNested<N>> implements Nested<N> {
    ThisInitNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endThisInit() {
      return and();
    }

  }

  public class LogicalAndInitNested<N> extends LogicalAndFluent<LogicalAndInitNested<N>> implements Nested<N> {
    LogicalAndInitNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLogicalAndInit() {
      return and();
    }

  }

  public class PostIncrementInitNested<N> extends PostIncrementFluent<PostIncrementInitNested<N>> implements Nested<N> {
    PostIncrementInitNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPostIncrementInit() {
      return and();
    }

  }

  public class RightUnsignedShiftInitNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftInitNested<N>>
      implements Nested<N> {
    RightUnsignedShiftInitNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endRightUnsignedShiftInit() {
      return and();
    }

  }

  public class PlusInitNested<N> extends PlusFluent<PlusInitNested<N>> implements Nested<N> {
    PlusInitNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPlusInit() {
      return and();
    }

  }

  public class ConstructInitNested<N> extends ConstructFluent<ConstructInitNested<N>> implements Nested<N> {
    ConstructInitNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endConstructInit() {
      return and();
    }

  }

  public class XorInitNested<N> extends XorFluent<XorInitNested<N>> implements Nested<N> {
    XorInitNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endXorInit() {
      return and();
    }

  }

  public class PreIncrementInitNested<N> extends PreIncrementFluent<PreIncrementInitNested<N>> implements Nested<N> {
    PreIncrementInitNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPreIncrementInit() {
      return and();
    }

  }

  public class LessThanOrEqualInitNested<N> extends LessThanOrEqualFluent<LessThanOrEqualInitNested<N>> implements Nested<N> {
    LessThanOrEqualInitNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLessThanOrEqualInit() {
      return and();
    }

  }

  public class PositiveInitNested<N> extends PositiveFluent<PositiveInitNested<N>> implements Nested<N> {
    PositiveInitNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPositiveInit() {
      return and();
    }

  }

  public class MultiplyCompareNested<N> extends MultiplyFluent<MultiplyCompareNested<N>> implements Nested<N> {
    MultiplyCompareNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endMultiplyCompare() {
      return and();
    }

  }

  public class MethodCallCompareNested<N> extends MethodCallFluent<MethodCallCompareNested<N>> implements Nested<N> {
    MethodCallCompareNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endMethodCallCompare() {
      return and();
    }

  }

  public class InverseCompareNested<N> extends InverseFluent<InverseCompareNested<N>> implements Nested<N> {
    InverseCompareNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endInverseCompare() {
      return and();
    }

  }

  public class GreaterThanOrEqualCompareNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualCompareNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualCompareNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endGreaterThanOrEqualCompare() {
      return and();
    }

  }

  public class BitwiseAndCompareNested<N> extends BitwiseAndFluent<BitwiseAndCompareNested<N>> implements Nested<N> {
    BitwiseAndCompareNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endBitwiseAndCompare() {
      return and();
    }

  }

  public class MinusCompareNested<N> extends MinusFluent<MinusCompareNested<N>> implements Nested<N> {
    MinusCompareNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endMinusCompare() {
      return and();
    }

  }

  public class LogicalOrCompareNested<N> extends LogicalOrFluent<LogicalOrCompareNested<N>> implements Nested<N> {
    LogicalOrCompareNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLogicalOrCompare() {
      return and();
    }

  }

  public class NotEqualsCompareNested<N> extends NotEqualsFluent<NotEqualsCompareNested<N>> implements Nested<N> {
    NotEqualsCompareNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNotEqualsCompare() {
      return and();
    }

  }

  public class DivideCompareNested<N> extends DivideFluent<DivideCompareNested<N>> implements Nested<N> {
    DivideCompareNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endDivideCompare() {
      return and();
    }

  }

  public class LessThanCompareNested<N> extends LessThanFluent<LessThanCompareNested<N>> implements Nested<N> {
    LessThanCompareNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLessThanCompare() {
      return and();
    }

  }

  public class BitwiseOrCompareNested<N> extends BitwiseOrFluent<BitwiseOrCompareNested<N>> implements Nested<N> {
    BitwiseOrCompareNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endBitwiseOrCompare() {
      return and();
    }

  }

  public class PropertyRefCompareNested<N> extends PropertyRefFluent<PropertyRefCompareNested<N>> implements Nested<N> {
    PropertyRefCompareNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPropertyRefCompare() {
      return and();
    }

  }

  public class RightShiftCompareNested<N> extends RightShiftFluent<RightShiftCompareNested<N>> implements Nested<N> {
    RightShiftCompareNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endRightShiftCompare() {
      return and();
    }

  }

  public class GreaterThanCompareNested<N> extends GreaterThanFluent<GreaterThanCompareNested<N>> implements Nested<N> {
    GreaterThanCompareNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endGreaterThanCompare() {
      return and();
    }

  }

  public class ModuloCompareNested<N> extends ModuloFluent<ModuloCompareNested<N>> implements Nested<N> {
    ModuloCompareNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endModuloCompare() {
      return and();
    }

  }

  public class ValueRefCompareNested<N> extends ValueRefFluent<ValueRefCompareNested<N>> implements Nested<N> {
    ValueRefCompareNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endValueRefCompare() {
      return and();
    }

  }

  public class LeftShiftCompareNested<N> extends LeftShiftFluent<LeftShiftCompareNested<N>> implements Nested<N> {
    LeftShiftCompareNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLeftShiftCompare() {
      return and();
    }

  }

  public class TernaryCompareNested<N> extends TernaryFluent<TernaryCompareNested<N>> implements Nested<N> {
    TernaryCompareNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endTernaryCompare() {
      return and();
    }

  }

  public class BinaryExpressionCompareNested<N> extends BinaryExpressionFluent<BinaryExpressionCompareNested<N>>
      implements Nested<N> {
    BinaryExpressionCompareNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endBinaryExpressionCompare() {
      return and();
    }

  }

  public class EqualsCompareNested<N> extends EqualsFluent<EqualsCompareNested<N>> implements Nested<N> {
    EqualsCompareNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endEqualsCompare() {
      return and();
    }

  }

  public class EnclosedCompareNested<N> extends EnclosedFluent<EnclosedCompareNested<N>> implements Nested<N> {
    EnclosedCompareNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endEnclosedCompare() {
      return and();
    }

  }

  public class PreDecrementCompareNested<N> extends PreDecrementFluent<PreDecrementCompareNested<N>> implements Nested<N> {
    PreDecrementCompareNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPreDecrementCompare() {
      return and();
    }

  }

  public class PostDecrementCompareNested<N> extends PostDecrementFluent<PostDecrementCompareNested<N>> implements Nested<N> {
    PostDecrementCompareNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPostDecrementCompare() {
      return and();
    }

  }

  public class NotCompareNested<N> extends NotFluent<NotCompareNested<N>> implements Nested<N> {
    NotCompareNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNotCompare() {
      return and();
    }

  }

  public class AssignCompareNested<N> extends AssignFluent<AssignCompareNested<N>> implements Nested<N> {
    AssignCompareNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endAssignCompare() {
      return and();
    }

  }

  public class NegativeCompareNested<N> extends NegativeFluent<NegativeCompareNested<N>> implements Nested<N> {
    NegativeCompareNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNegativeCompare() {
      return and();
    }

  }

  public class ThisCompareNested<N> extends ThisFluent<ThisCompareNested<N>> implements Nested<N> {
    ThisCompareNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endThisCompare() {
      return and();
    }

  }

  public class LogicalAndCompareNested<N> extends LogicalAndFluent<LogicalAndCompareNested<N>> implements Nested<N> {
    LogicalAndCompareNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLogicalAndCompare() {
      return and();
    }

  }

  public class PostIncrementCompareNested<N> extends PostIncrementFluent<PostIncrementCompareNested<N>> implements Nested<N> {
    PostIncrementCompareNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPostIncrementCompare() {
      return and();
    }

  }

  public class RightUnsignedShiftCompareNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftCompareNested<N>>
      implements Nested<N> {
    RightUnsignedShiftCompareNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endRightUnsignedShiftCompare() {
      return and();
    }

  }

  public class PlusCompareNested<N> extends PlusFluent<PlusCompareNested<N>> implements Nested<N> {
    PlusCompareNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPlusCompare() {
      return and();
    }

  }

  public class ConstructCompareNested<N> extends ConstructFluent<ConstructCompareNested<N>> implements Nested<N> {
    ConstructCompareNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endConstructCompare() {
      return and();
    }

  }

  public class XorCompareNested<N> extends XorFluent<XorCompareNested<N>> implements Nested<N> {
    XorCompareNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endXorCompare() {
      return and();
    }

  }

  public class PreIncrementCompareNested<N> extends PreIncrementFluent<PreIncrementCompareNested<N>> implements Nested<N> {
    PreIncrementCompareNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPreIncrementCompare() {
      return and();
    }

  }

  public class LessThanOrEqualCompareNested<N> extends LessThanOrEqualFluent<LessThanOrEqualCompareNested<N>>
      implements Nested<N> {
    LessThanOrEqualCompareNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLessThanOrEqualCompare() {
      return and();
    }

  }

  public class PositiveCompareNested<N> extends PositiveFluent<PositiveCompareNested<N>> implements Nested<N> {
    PositiveCompareNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPositiveCompare() {
      return and();
    }

  }

  public class MultiplyUpdateNested<N> extends MultiplyFluent<MultiplyUpdateNested<N>> implements Nested<N> {
    MultiplyUpdateNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endMultiplyUpdate() {
      return and();
    }

  }

  public class MethodCallUpdateNested<N> extends MethodCallFluent<MethodCallUpdateNested<N>> implements Nested<N> {
    MethodCallUpdateNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endMethodCallUpdate() {
      return and();
    }

  }

  public class InverseUpdateNested<N> extends InverseFluent<InverseUpdateNested<N>> implements Nested<N> {
    InverseUpdateNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endInverseUpdate() {
      return and();
    }

  }

  public class GreaterThanOrEqualUpdateNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualUpdateNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualUpdateNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endGreaterThanOrEqualUpdate() {
      return and();
    }

  }

  public class BitwiseAndUpdateNested<N> extends BitwiseAndFluent<BitwiseAndUpdateNested<N>> implements Nested<N> {
    BitwiseAndUpdateNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endBitwiseAndUpdate() {
      return and();
    }

  }

  public class MinusUpdateNested<N> extends MinusFluent<MinusUpdateNested<N>> implements Nested<N> {
    MinusUpdateNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endMinusUpdate() {
      return and();
    }

  }

  public class LogicalOrUpdateNested<N> extends LogicalOrFluent<LogicalOrUpdateNested<N>> implements Nested<N> {
    LogicalOrUpdateNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLogicalOrUpdate() {
      return and();
    }

  }

  public class NotEqualsUpdateNested<N> extends NotEqualsFluent<NotEqualsUpdateNested<N>> implements Nested<N> {
    NotEqualsUpdateNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNotEqualsUpdate() {
      return and();
    }

  }

  public class DivideUpdateNested<N> extends DivideFluent<DivideUpdateNested<N>> implements Nested<N> {
    DivideUpdateNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endDivideUpdate() {
      return and();
    }

  }

  public class LessThanUpdateNested<N> extends LessThanFluent<LessThanUpdateNested<N>> implements Nested<N> {
    LessThanUpdateNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLessThanUpdate() {
      return and();
    }

  }

  public class BitwiseOrUpdateNested<N> extends BitwiseOrFluent<BitwiseOrUpdateNested<N>> implements Nested<N> {
    BitwiseOrUpdateNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endBitwiseOrUpdate() {
      return and();
    }

  }

  public class PropertyRefUpdateNested<N> extends PropertyRefFluent<PropertyRefUpdateNested<N>> implements Nested<N> {
    PropertyRefUpdateNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPropertyRefUpdate() {
      return and();
    }

  }

  public class RightShiftUpdateNested<N> extends RightShiftFluent<RightShiftUpdateNested<N>> implements Nested<N> {
    RightShiftUpdateNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endRightShiftUpdate() {
      return and();
    }

  }

  public class GreaterThanUpdateNested<N> extends GreaterThanFluent<GreaterThanUpdateNested<N>> implements Nested<N> {
    GreaterThanUpdateNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endGreaterThanUpdate() {
      return and();
    }

  }

  public class ModuloUpdateNested<N> extends ModuloFluent<ModuloUpdateNested<N>> implements Nested<N> {
    ModuloUpdateNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endModuloUpdate() {
      return and();
    }

  }

  public class ValueRefUpdateNested<N> extends ValueRefFluent<ValueRefUpdateNested<N>> implements Nested<N> {
    ValueRefUpdateNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endValueRefUpdate() {
      return and();
    }

  }

  public class LeftShiftUpdateNested<N> extends LeftShiftFluent<LeftShiftUpdateNested<N>> implements Nested<N> {
    LeftShiftUpdateNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLeftShiftUpdate() {
      return and();
    }

  }

  public class TernaryUpdateNested<N> extends TernaryFluent<TernaryUpdateNested<N>> implements Nested<N> {
    TernaryUpdateNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endTernaryUpdate() {
      return and();
    }

  }

  public class BinaryExpressionUpdateNested<N> extends BinaryExpressionFluent<BinaryExpressionUpdateNested<N>>
      implements Nested<N> {
    BinaryExpressionUpdateNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endBinaryExpressionUpdate() {
      return and();
    }

  }

  public class EqualsUpdateNested<N> extends EqualsFluent<EqualsUpdateNested<N>> implements Nested<N> {
    EqualsUpdateNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endEqualsUpdate() {
      return and();
    }

  }

  public class EnclosedUpdateNested<N> extends EnclosedFluent<EnclosedUpdateNested<N>> implements Nested<N> {
    EnclosedUpdateNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endEnclosedUpdate() {
      return and();
    }

  }

  public class PreDecrementUpdateNested<N> extends PreDecrementFluent<PreDecrementUpdateNested<N>> implements Nested<N> {
    PreDecrementUpdateNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPreDecrementUpdate() {
      return and();
    }

  }

  public class PostDecrementUpdateNested<N> extends PostDecrementFluent<PostDecrementUpdateNested<N>> implements Nested<N> {
    PostDecrementUpdateNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPostDecrementUpdate() {
      return and();
    }

  }

  public class NotUpdateNested<N> extends NotFluent<NotUpdateNested<N>> implements Nested<N> {
    NotUpdateNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNotUpdate() {
      return and();
    }

  }

  public class AssignUpdateNested<N> extends AssignFluent<AssignUpdateNested<N>> implements Nested<N> {
    AssignUpdateNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endAssignUpdate() {
      return and();
    }

  }

  public class NegativeUpdateNested<N> extends NegativeFluent<NegativeUpdateNested<N>> implements Nested<N> {
    NegativeUpdateNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNegativeUpdate() {
      return and();
    }

  }

  public class ThisUpdateNested<N> extends ThisFluent<ThisUpdateNested<N>> implements Nested<N> {
    ThisUpdateNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endThisUpdate() {
      return and();
    }

  }

  public class LogicalAndUpdateNested<N> extends LogicalAndFluent<LogicalAndUpdateNested<N>> implements Nested<N> {
    LogicalAndUpdateNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLogicalAndUpdate() {
      return and();
    }

  }

  public class PostIncrementUpdateNested<N> extends PostIncrementFluent<PostIncrementUpdateNested<N>> implements Nested<N> {
    PostIncrementUpdateNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPostIncrementUpdate() {
      return and();
    }

  }

  public class RightUnsignedShiftUpdateNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftUpdateNested<N>>
      implements Nested<N> {
    RightUnsignedShiftUpdateNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endRightUnsignedShiftUpdate() {
      return and();
    }

  }

  public class PlusUpdateNested<N> extends PlusFluent<PlusUpdateNested<N>> implements Nested<N> {
    PlusUpdateNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPlusUpdate() {
      return and();
    }

  }

  public class ConstructUpdateNested<N> extends ConstructFluent<ConstructUpdateNested<N>> implements Nested<N> {
    ConstructUpdateNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endConstructUpdate() {
      return and();
    }

  }

  public class XorUpdateNested<N> extends XorFluent<XorUpdateNested<N>> implements Nested<N> {
    XorUpdateNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endXorUpdate() {
      return and();
    }

  }

  public class PreIncrementUpdateNested<N> extends PreIncrementFluent<PreIncrementUpdateNested<N>> implements Nested<N> {
    PreIncrementUpdateNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPreIncrementUpdate() {
      return and();
    }

  }

  public class LessThanOrEqualUpdateNested<N> extends LessThanOrEqualFluent<LessThanOrEqualUpdateNested<N>>
      implements Nested<N> {
    LessThanOrEqualUpdateNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLessThanOrEqualUpdate() {
      return and();
    }

  }

  public class PositiveUpdateNested<N> extends PositiveFluent<PositiveUpdateNested<N>> implements Nested<N> {
    PositiveUpdateNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;
    int index;

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPositiveUpdate() {
      return and();
    }

  }

  public class SwitchBodyNested<N> extends SwitchFluent<SwitchBodyNested<N>> implements Nested<N> {
    SwitchBodyNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    SwitchBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endSwitchBody() {
      return and();
    }

  }

  public class BreakBodyNested<N> extends BreakFluent<BreakBodyNested<N>> implements Nested<N> {
    BreakBodyNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    BreakBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBreakBody() {
      return and();
    }

  }

  public class DeclareBodyNested<N> extends DeclareFluent<DeclareBodyNested<N>> implements Nested<N> {
    DeclareBodyNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endDeclareBody() {
      return and();
    }

  }

  public class WhileBodyNested<N> extends WhileFluent<WhileBodyNested<N>> implements Nested<N> {
    WhileBodyNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    WhileBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endWhileBody() {
      return and();
    }

  }

  public class ContinueBodyNested<N> extends ContinueFluent<ContinueBodyNested<N>> implements Nested<N> {
    ContinueBodyNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    ContinueBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endContinueBody() {
      return and();
    }

  }

  public class StringStatementBodyNested<N> extends StringStatementFluent<StringStatementBodyNested<N>> implements Nested<N> {
    StringStatementBodyNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    StringStatementBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endStringStatementBody() {
      return and();
    }

  }

  public class DoBodyNested<N> extends DoFluent<DoBodyNested<N>> implements Nested<N> {
    DoBodyNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    DoBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endDoBody() {
      return and();
    }

  }

  public class ForeachBodyNested<N> extends ForeachFluent<ForeachBodyNested<N>> implements Nested<N> {
    ForeachBodyNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    ForeachBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endForeachBody() {
      return and();
    }

  }

  public class BlockBodyNested<N> extends BlockFluent<BlockBodyNested<N>> implements Nested<N> {
    BlockBodyNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    BlockBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBlockBody() {
      return and();
    }

  }

  public class IfBodyNested<N> extends IfFluent<IfBodyNested<N>> implements Nested<N> {
    IfBodyNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    IfBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endIfBody() {
      return and();
    }

  }

  public class ReturnBodyNested<N> extends ReturnFluent<ReturnBodyNested<N>> implements Nested<N> {
    ReturnBodyNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    ReturnBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endReturnBody() {
      return and();
    }

  }

  public class AssignBodyNested<N> extends AssignFluent<AssignBodyNested<N>> implements Nested<N> {
    AssignBodyNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endAssignBody() {
      return and();
    }

  }

  public class ForBodyNested<N> extends ForFluent<ForBodyNested<N>> implements Nested<N> {
    ForBodyNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    ForBuilder builder;

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endForBody() {
      return and();
    }

  }

}
