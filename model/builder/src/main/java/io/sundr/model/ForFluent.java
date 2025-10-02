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
public class ForFluent<A extends ForFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Statement, ?> body;
  private VisitableBuilder<? extends Expression, ?> compare;
  private ArrayList<VisitableBuilder<? extends Expression, ?>> init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
  private ArrayList<VisitableBuilder<? extends Expression, ?>> update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();

  public ForFluent() {
  }

  public ForFluent(For instance) {
    this.copyInstance(instance);
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

  public AssignInitNested<A> addNewAssignInit() {
    return new AssignInitNested(-1, null);
  }

  public AssignInitNested<A> addNewAssignInitLike(Assign item) {
    return new AssignInitNested(-1, item);
  }

  public AssignUpdateNested<A> addNewAssignUpdate() {
    return new AssignUpdateNested(-1, null);
  }

  public AssignUpdateNested<A> addNewAssignUpdateLike(Assign item) {
    return new AssignUpdateNested(-1, item);
  }

  public BinaryExpressionInitNested<A> addNewBinaryExpressionInit() {
    return new BinaryExpressionInitNested(-1, null);
  }

  public BinaryExpressionInitNested<A> addNewBinaryExpressionInitLike(BinaryExpression item) {
    return new BinaryExpressionInitNested(-1, item);
  }

  public BinaryExpressionUpdateNested<A> addNewBinaryExpressionUpdate() {
    return new BinaryExpressionUpdateNested(-1, null);
  }

  public BinaryExpressionUpdateNested<A> addNewBinaryExpressionUpdateLike(BinaryExpression item) {
    return new BinaryExpressionUpdateNested(-1, item);
  }

  public BitwiseAndInitNested<A> addNewBitwiseAndInit() {
    return new BitwiseAndInitNested(-1, null);
  }

  public A addNewBitwiseAndInit(Object left, Object right) {
    return (A) addToInit(new BitwiseAnd(left, right));
  }

  public BitwiseAndInitNested<A> addNewBitwiseAndInitLike(BitwiseAnd item) {
    return new BitwiseAndInitNested(-1, item);
  }

  public BitwiseAndUpdateNested<A> addNewBitwiseAndUpdate() {
    return new BitwiseAndUpdateNested(-1, null);
  }

  public A addNewBitwiseAndUpdate(Object left, Object right) {
    return (A) addToUpdate(new BitwiseAnd(left, right));
  }

  public BitwiseAndUpdateNested<A> addNewBitwiseAndUpdateLike(BitwiseAnd item) {
    return new BitwiseAndUpdateNested(-1, item);
  }

  public BitwiseOrInitNested<A> addNewBitwiseOrInit() {
    return new BitwiseOrInitNested(-1, null);
  }

  public A addNewBitwiseOrInit(Object left, Object right) {
    return (A) addToInit(new BitwiseOr(left, right));
  }

  public BitwiseOrInitNested<A> addNewBitwiseOrInitLike(BitwiseOr item) {
    return new BitwiseOrInitNested(-1, item);
  }

  public BitwiseOrUpdateNested<A> addNewBitwiseOrUpdate() {
    return new BitwiseOrUpdateNested(-1, null);
  }

  public A addNewBitwiseOrUpdate(Object left, Object right) {
    return (A) addToUpdate(new BitwiseOr(left, right));
  }

  public BitwiseOrUpdateNested<A> addNewBitwiseOrUpdateLike(BitwiseOr item) {
    return new BitwiseOrUpdateNested(-1, item);
  }

  public CastInitNested<A> addNewCastInit() {
    return new CastInitNested(-1, null);
  }

  public CastInitNested<A> addNewCastInitLike(Cast item) {
    return new CastInitNested(-1, item);
  }

  public CastUpdateNested<A> addNewCastUpdate() {
    return new CastUpdateNested(-1, null);
  }

  public CastUpdateNested<A> addNewCastUpdateLike(Cast item) {
    return new CastUpdateNested(-1, item);
  }

  public ClassRefInitNested<A> addNewClassRefInit() {
    return new ClassRefInitNested(-1, null);
  }

  public ClassRefInitNested<A> addNewClassRefInitLike(ClassRef item) {
    return new ClassRefInitNested(-1, item);
  }

  public ClassRefUpdateNested<A> addNewClassRefUpdate() {
    return new ClassRefUpdateNested(-1, null);
  }

  public ClassRefUpdateNested<A> addNewClassRefUpdateLike(ClassRef item) {
    return new ClassRefUpdateNested(-1, item);
  }

  public ConstructInitNested<A> addNewConstructInit() {
    return new ConstructInitNested(-1, null);
  }

  public ConstructInitNested<A> addNewConstructInitLike(Construct item) {
    return new ConstructInitNested(-1, item);
  }

  public ConstructUpdateNested<A> addNewConstructUpdate() {
    return new ConstructUpdateNested(-1, null);
  }

  public ConstructUpdateNested<A> addNewConstructUpdateLike(Construct item) {
    return new ConstructUpdateNested(-1, item);
  }

  public ContextRefInitNested<A> addNewContextRefInit() {
    return new ContextRefInitNested(-1, null);
  }

  public A addNewContextRefInit(String name) {
    return (A) addToInit(new ContextRef(name));
  }

  public ContextRefInitNested<A> addNewContextRefInitLike(ContextRef item) {
    return new ContextRefInitNested(-1, item);
  }

  public ContextRefUpdateNested<A> addNewContextRefUpdate() {
    return new ContextRefUpdateNested(-1, null);
  }

  public A addNewContextRefUpdate(String name) {
    return (A) addToUpdate(new ContextRef(name));
  }

  public ContextRefUpdateNested<A> addNewContextRefUpdateLike(ContextRef item) {
    return new ContextRefUpdateNested(-1, item);
  }

  public DeclareInitNested<A> addNewDeclareInit() {
    return new DeclareInitNested(-1, null);
  }

  public A addNewDeclareInit(Class type, String name) {
    return (A) addToInit(new Declare(type, name));
  }

  public A addNewDeclareInit(Class type, String name, Object value) {
    return (A) addToInit(new Declare(type, name, value));
  }

  public DeclareInitNested<A> addNewDeclareInitLike(Declare item) {
    return new DeclareInitNested(-1, item);
  }

  public DeclareUpdateNested<A> addNewDeclareUpdate() {
    return new DeclareUpdateNested(-1, null);
  }

  public A addNewDeclareUpdate(Class type, String name) {
    return (A) addToUpdate(new Declare(type, name));
  }

  public A addNewDeclareUpdate(Class type, String name, Object value) {
    return (A) addToUpdate(new Declare(type, name, value));
  }

  public DeclareUpdateNested<A> addNewDeclareUpdateLike(Declare item) {
    return new DeclareUpdateNested(-1, item);
  }

  public DivideInitNested<A> addNewDivideInit() {
    return new DivideInitNested(-1, null);
  }

  public A addNewDivideInit(Object left, Object right) {
    return (A) addToInit(new Divide(left, right));
  }

  public DivideInitNested<A> addNewDivideInitLike(Divide item) {
    return new DivideInitNested(-1, item);
  }

  public DivideUpdateNested<A> addNewDivideUpdate() {
    return new DivideUpdateNested(-1, null);
  }

  public A addNewDivideUpdate(Object left, Object right) {
    return (A) addToUpdate(new Divide(left, right));
  }

  public DivideUpdateNested<A> addNewDivideUpdateLike(Divide item) {
    return new DivideUpdateNested(-1, item);
  }

  public DotClassInitNested<A> addNewDotClassInit() {
    return new DotClassInitNested(-1, null);
  }

  public DotClassInitNested<A> addNewDotClassInitLike(DotClass item) {
    return new DotClassInitNested(-1, item);
  }

  public DotClassUpdateNested<A> addNewDotClassUpdate() {
    return new DotClassUpdateNested(-1, null);
  }

  public DotClassUpdateNested<A> addNewDotClassUpdateLike(DotClass item) {
    return new DotClassUpdateNested(-1, item);
  }

  public EmptyInitNested<A> addNewEmptyInit() {
    return new EmptyInitNested(-1, null);
  }

  public EmptyInitNested<A> addNewEmptyInitLike(Empty item) {
    return new EmptyInitNested(-1, item);
  }

  public EmptyUpdateNested<A> addNewEmptyUpdate() {
    return new EmptyUpdateNested(-1, null);
  }

  public EmptyUpdateNested<A> addNewEmptyUpdateLike(Empty item) {
    return new EmptyUpdateNested(-1, item);
  }

  public EnclosedInitNested<A> addNewEnclosedInit() {
    return new EnclosedInitNested(-1, null);
  }

  public EnclosedInitNested<A> addNewEnclosedInitLike(Enclosed item) {
    return new EnclosedInitNested(-1, item);
  }

  public EnclosedUpdateNested<A> addNewEnclosedUpdate() {
    return new EnclosedUpdateNested(-1, null);
  }

  public EnclosedUpdateNested<A> addNewEnclosedUpdateLike(Enclosed item) {
    return new EnclosedUpdateNested(-1, item);
  }

  public EqualsInitNested<A> addNewEqualsInit() {
    return new EqualsInitNested(-1, null);
  }

  public A addNewEqualsInit(Object left, Object right) {
    return (A) addToInit(new Equals(left, right));
  }

  public EqualsInitNested<A> addNewEqualsInitLike(Equals item) {
    return new EqualsInitNested(-1, item);
  }

  public EqualsUpdateNested<A> addNewEqualsUpdate() {
    return new EqualsUpdateNested(-1, null);
  }

  public A addNewEqualsUpdate(Object left, Object right) {
    return (A) addToUpdate(new Equals(left, right));
  }

  public EqualsUpdateNested<A> addNewEqualsUpdateLike(Equals item) {
    return new EqualsUpdateNested(-1, item);
  }

  public GreaterThanInitNested<A> addNewGreaterThanInit() {
    return new GreaterThanInitNested(-1, null);
  }

  public A addNewGreaterThanInit(Object left, Object right) {
    return (A) addToInit(new GreaterThan(left, right));
  }

  public GreaterThanInitNested<A> addNewGreaterThanInitLike(GreaterThan item) {
    return new GreaterThanInitNested(-1, item);
  }

  public GreaterThanOrEqualInitNested<A> addNewGreaterThanOrEqualInit() {
    return new GreaterThanOrEqualInitNested(-1, null);
  }

  public A addNewGreaterThanOrEqualInit(Object left, Object right) {
    return (A) addToInit(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualInitNested<A> addNewGreaterThanOrEqualInitLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualInitNested(-1, item);
  }

  public GreaterThanOrEqualUpdateNested<A> addNewGreaterThanOrEqualUpdate() {
    return new GreaterThanOrEqualUpdateNested(-1, null);
  }

  public A addNewGreaterThanOrEqualUpdate(Object left, Object right) {
    return (A) addToUpdate(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualUpdateNested<A> addNewGreaterThanOrEqualUpdateLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualUpdateNested(-1, item);
  }

  public GreaterThanUpdateNested<A> addNewGreaterThanUpdate() {
    return new GreaterThanUpdateNested(-1, null);
  }

  public A addNewGreaterThanUpdate(Object left, Object right) {
    return (A) addToUpdate(new GreaterThan(left, right));
  }

  public GreaterThanUpdateNested<A> addNewGreaterThanUpdateLike(GreaterThan item) {
    return new GreaterThanUpdateNested(-1, item);
  }

  public IndexInitNested<A> addNewIndexInit() {
    return new IndexInitNested(-1, null);
  }

  public IndexInitNested<A> addNewIndexInitLike(Index item) {
    return new IndexInitNested(-1, item);
  }

  public IndexUpdateNested<A> addNewIndexUpdate() {
    return new IndexUpdateNested(-1, null);
  }

  public IndexUpdateNested<A> addNewIndexUpdateLike(Index item) {
    return new IndexUpdateNested(-1, item);
  }

  public InstanceOfInitNested<A> addNewInstanceOfInit() {
    return new InstanceOfInitNested(-1, null);
  }

  public InstanceOfInitNested<A> addNewInstanceOfInitLike(InstanceOf item) {
    return new InstanceOfInitNested(-1, item);
  }

  public InstanceOfUpdateNested<A> addNewInstanceOfUpdate() {
    return new InstanceOfUpdateNested(-1, null);
  }

  public InstanceOfUpdateNested<A> addNewInstanceOfUpdateLike(InstanceOf item) {
    return new InstanceOfUpdateNested(-1, item);
  }

  public InverseInitNested<A> addNewInverseInit() {
    return new InverseInitNested(-1, null);
  }

  public InverseInitNested<A> addNewInverseInitLike(Inverse item) {
    return new InverseInitNested(-1, item);
  }

  public InverseUpdateNested<A> addNewInverseUpdate() {
    return new InverseUpdateNested(-1, null);
  }

  public InverseUpdateNested<A> addNewInverseUpdateLike(Inverse item) {
    return new InverseUpdateNested(-1, item);
  }

  public LambdaInitNested<A> addNewLambdaInit() {
    return new LambdaInitNested(-1, null);
  }

  public LambdaInitNested<A> addNewLambdaInitLike(Lambda item) {
    return new LambdaInitNested(-1, item);
  }

  public LambdaUpdateNested<A> addNewLambdaUpdate() {
    return new LambdaUpdateNested(-1, null);
  }

  public LambdaUpdateNested<A> addNewLambdaUpdateLike(Lambda item) {
    return new LambdaUpdateNested(-1, item);
  }

  public LeftShiftInitNested<A> addNewLeftShiftInit() {
    return new LeftShiftInitNested(-1, null);
  }

  public A addNewLeftShiftInit(Object left, Object right) {
    return (A) addToInit(new LeftShift(left, right));
  }

  public LeftShiftInitNested<A> addNewLeftShiftInitLike(LeftShift item) {
    return new LeftShiftInitNested(-1, item);
  }

  public LeftShiftUpdateNested<A> addNewLeftShiftUpdate() {
    return new LeftShiftUpdateNested(-1, null);
  }

  public A addNewLeftShiftUpdate(Object left, Object right) {
    return (A) addToUpdate(new LeftShift(left, right));
  }

  public LeftShiftUpdateNested<A> addNewLeftShiftUpdateLike(LeftShift item) {
    return new LeftShiftUpdateNested(-1, item);
  }

  public LessThanInitNested<A> addNewLessThanInit() {
    return new LessThanInitNested(-1, null);
  }

  public A addNewLessThanInit(Object left, Object right) {
    return (A) addToInit(new LessThan(left, right));
  }

  public LessThanInitNested<A> addNewLessThanInitLike(LessThan item) {
    return new LessThanInitNested(-1, item);
  }

  public LessThanOrEqualInitNested<A> addNewLessThanOrEqualInit() {
    return new LessThanOrEqualInitNested(-1, null);
  }

  public A addNewLessThanOrEqualInit(Object left, Object right) {
    return (A) addToInit(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualInitNested<A> addNewLessThanOrEqualInitLike(LessThanOrEqual item) {
    return new LessThanOrEqualInitNested(-1, item);
  }

  public LessThanOrEqualUpdateNested<A> addNewLessThanOrEqualUpdate() {
    return new LessThanOrEqualUpdateNested(-1, null);
  }

  public A addNewLessThanOrEqualUpdate(Object left, Object right) {
    return (A) addToUpdate(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualUpdateNested<A> addNewLessThanOrEqualUpdateLike(LessThanOrEqual item) {
    return new LessThanOrEqualUpdateNested(-1, item);
  }

  public LessThanUpdateNested<A> addNewLessThanUpdate() {
    return new LessThanUpdateNested(-1, null);
  }

  public A addNewLessThanUpdate(Object left, Object right) {
    return (A) addToUpdate(new LessThan(left, right));
  }

  public LessThanUpdateNested<A> addNewLessThanUpdateLike(LessThan item) {
    return new LessThanUpdateNested(-1, item);
  }

  public LogicalAndInitNested<A> addNewLogicalAndInit() {
    return new LogicalAndInitNested(-1, null);
  }

  public A addNewLogicalAndInit(Object left, Object right) {
    return (A) addToInit(new LogicalAnd(left, right));
  }

  public LogicalAndInitNested<A> addNewLogicalAndInitLike(LogicalAnd item) {
    return new LogicalAndInitNested(-1, item);
  }

  public LogicalAndUpdateNested<A> addNewLogicalAndUpdate() {
    return new LogicalAndUpdateNested(-1, null);
  }

  public A addNewLogicalAndUpdate(Object left, Object right) {
    return (A) addToUpdate(new LogicalAnd(left, right));
  }

  public LogicalAndUpdateNested<A> addNewLogicalAndUpdateLike(LogicalAnd item) {
    return new LogicalAndUpdateNested(-1, item);
  }

  public LogicalOrInitNested<A> addNewLogicalOrInit() {
    return new LogicalOrInitNested(-1, null);
  }

  public A addNewLogicalOrInit(Object left, Object right) {
    return (A) addToInit(new LogicalOr(left, right));
  }

  public LogicalOrInitNested<A> addNewLogicalOrInitLike(LogicalOr item) {
    return new LogicalOrInitNested(-1, item);
  }

  public LogicalOrUpdateNested<A> addNewLogicalOrUpdate() {
    return new LogicalOrUpdateNested(-1, null);
  }

  public A addNewLogicalOrUpdate(Object left, Object right) {
    return (A) addToUpdate(new LogicalOr(left, right));
  }

  public LogicalOrUpdateNested<A> addNewLogicalOrUpdateLike(LogicalOr item) {
    return new LogicalOrUpdateNested(-1, item);
  }

  public MethodCallInitNested<A> addNewMethodCallInit() {
    return new MethodCallInitNested(-1, null);
  }

  public MethodCallInitNested<A> addNewMethodCallInitLike(MethodCall item) {
    return new MethodCallInitNested(-1, item);
  }

  public MethodCallUpdateNested<A> addNewMethodCallUpdate() {
    return new MethodCallUpdateNested(-1, null);
  }

  public MethodCallUpdateNested<A> addNewMethodCallUpdateLike(MethodCall item) {
    return new MethodCallUpdateNested(-1, item);
  }

  public MinusInitNested<A> addNewMinusInit() {
    return new MinusInitNested(-1, null);
  }

  public A addNewMinusInit(Object left, Object right) {
    return (A) addToInit(new Minus(left, right));
  }

  public MinusInitNested<A> addNewMinusInitLike(Minus item) {
    return new MinusInitNested(-1, item);
  }

  public MinusUpdateNested<A> addNewMinusUpdate() {
    return new MinusUpdateNested(-1, null);
  }

  public A addNewMinusUpdate(Object left, Object right) {
    return (A) addToUpdate(new Minus(left, right));
  }

  public MinusUpdateNested<A> addNewMinusUpdateLike(Minus item) {
    return new MinusUpdateNested(-1, item);
  }

  public ModuloInitNested<A> addNewModuloInit() {
    return new ModuloInitNested(-1, null);
  }

  public A addNewModuloInit(Object left, Object right) {
    return (A) addToInit(new Modulo(left, right));
  }

  public ModuloInitNested<A> addNewModuloInitLike(Modulo item) {
    return new ModuloInitNested(-1, item);
  }

  public ModuloUpdateNested<A> addNewModuloUpdate() {
    return new ModuloUpdateNested(-1, null);
  }

  public A addNewModuloUpdate(Object left, Object right) {
    return (A) addToUpdate(new Modulo(left, right));
  }

  public ModuloUpdateNested<A> addNewModuloUpdateLike(Modulo item) {
    return new ModuloUpdateNested(-1, item);
  }

  public MultiplyInitNested<A> addNewMultiplyInit() {
    return new MultiplyInitNested(-1, null);
  }

  public A addNewMultiplyInit(Object left, Object right) {
    return (A) addToInit(new Multiply(left, right));
  }

  public MultiplyInitNested<A> addNewMultiplyInitLike(Multiply item) {
    return new MultiplyInitNested(-1, item);
  }

  public MultiplyUpdateNested<A> addNewMultiplyUpdate() {
    return new MultiplyUpdateNested(-1, null);
  }

  public A addNewMultiplyUpdate(Object left, Object right) {
    return (A) addToUpdate(new Multiply(left, right));
  }

  public MultiplyUpdateNested<A> addNewMultiplyUpdateLike(Multiply item) {
    return new MultiplyUpdateNested(-1, item);
  }

  public NegativeInitNested<A> addNewNegativeInit() {
    return new NegativeInitNested(-1, null);
  }

  public NegativeInitNested<A> addNewNegativeInitLike(Negative item) {
    return new NegativeInitNested(-1, item);
  }

  public NegativeUpdateNested<A> addNewNegativeUpdate() {
    return new NegativeUpdateNested(-1, null);
  }

  public NegativeUpdateNested<A> addNewNegativeUpdateLike(Negative item) {
    return new NegativeUpdateNested(-1, item);
  }

  public NewArrayInitNested<A> addNewNewArrayInit() {
    return new NewArrayInitNested(-1, null);
  }

  public A addNewNewArrayInit(Class type, Integer[] sizes) {
    return (A) addToInit(new NewArray(type, sizes));
  }

  public NewArrayInitNested<A> addNewNewArrayInitLike(NewArray item) {
    return new NewArrayInitNested(-1, item);
  }

  public NewArrayUpdateNested<A> addNewNewArrayUpdate() {
    return new NewArrayUpdateNested(-1, null);
  }

  public A addNewNewArrayUpdate(Class type, Integer[] sizes) {
    return (A) addToUpdate(new NewArray(type, sizes));
  }

  public NewArrayUpdateNested<A> addNewNewArrayUpdateLike(NewArray item) {
    return new NewArrayUpdateNested(-1, item);
  }

  public NotEqualsInitNested<A> addNewNotEqualsInit() {
    return new NotEqualsInitNested(-1, null);
  }

  public A addNewNotEqualsInit(Object left, Object right) {
    return (A) addToInit(new NotEquals(left, right));
  }

  public NotEqualsInitNested<A> addNewNotEqualsInitLike(NotEquals item) {
    return new NotEqualsInitNested(-1, item);
  }

  public NotEqualsUpdateNested<A> addNewNotEqualsUpdate() {
    return new NotEqualsUpdateNested(-1, null);
  }

  public A addNewNotEqualsUpdate(Object left, Object right) {
    return (A) addToUpdate(new NotEquals(left, right));
  }

  public NotEqualsUpdateNested<A> addNewNotEqualsUpdateLike(NotEquals item) {
    return new NotEqualsUpdateNested(-1, item);
  }

  public NotInitNested<A> addNewNotInit() {
    return new NotInitNested(-1, null);
  }

  public NotInitNested<A> addNewNotInitLike(Not item) {
    return new NotInitNested(-1, item);
  }

  public NotUpdateNested<A> addNewNotUpdate() {
    return new NotUpdateNested(-1, null);
  }

  public NotUpdateNested<A> addNewNotUpdateLike(Not item) {
    return new NotUpdateNested(-1, item);
  }

  public PlusInitNested<A> addNewPlusInit() {
    return new PlusInitNested(-1, null);
  }

  public A addNewPlusInit(Object left, Object right) {
    return (A) addToInit(new Plus(left, right));
  }

  public PlusInitNested<A> addNewPlusInitLike(Plus item) {
    return new PlusInitNested(-1, item);
  }

  public PlusUpdateNested<A> addNewPlusUpdate() {
    return new PlusUpdateNested(-1, null);
  }

  public A addNewPlusUpdate(Object left, Object right) {
    return (A) addToUpdate(new Plus(left, right));
  }

  public PlusUpdateNested<A> addNewPlusUpdateLike(Plus item) {
    return new PlusUpdateNested(-1, item);
  }

  public PositiveInitNested<A> addNewPositiveInit() {
    return new PositiveInitNested(-1, null);
  }

  public PositiveInitNested<A> addNewPositiveInitLike(Positive item) {
    return new PositiveInitNested(-1, item);
  }

  public PositiveUpdateNested<A> addNewPositiveUpdate() {
    return new PositiveUpdateNested(-1, null);
  }

  public PositiveUpdateNested<A> addNewPositiveUpdateLike(Positive item) {
    return new PositiveUpdateNested(-1, item);
  }

  public PostDecrementInitNested<A> addNewPostDecrementInit() {
    return new PostDecrementInitNested(-1, null);
  }

  public PostDecrementInitNested<A> addNewPostDecrementInitLike(PostDecrement item) {
    return new PostDecrementInitNested(-1, item);
  }

  public PostDecrementUpdateNested<A> addNewPostDecrementUpdate() {
    return new PostDecrementUpdateNested(-1, null);
  }

  public PostDecrementUpdateNested<A> addNewPostDecrementUpdateLike(PostDecrement item) {
    return new PostDecrementUpdateNested(-1, item);
  }

  public PostIncrementInitNested<A> addNewPostIncrementInit() {
    return new PostIncrementInitNested(-1, null);
  }

  public PostIncrementInitNested<A> addNewPostIncrementInitLike(PostIncrement item) {
    return new PostIncrementInitNested(-1, item);
  }

  public PostIncrementUpdateNested<A> addNewPostIncrementUpdate() {
    return new PostIncrementUpdateNested(-1, null);
  }

  public PostIncrementUpdateNested<A> addNewPostIncrementUpdateLike(PostIncrement item) {
    return new PostIncrementUpdateNested(-1, item);
  }

  public PreDecrementInitNested<A> addNewPreDecrementInit() {
    return new PreDecrementInitNested(-1, null);
  }

  public PreDecrementInitNested<A> addNewPreDecrementInitLike(PreDecrement item) {
    return new PreDecrementInitNested(-1, item);
  }

  public PreDecrementUpdateNested<A> addNewPreDecrementUpdate() {
    return new PreDecrementUpdateNested(-1, null);
  }

  public PreDecrementUpdateNested<A> addNewPreDecrementUpdateLike(PreDecrement item) {
    return new PreDecrementUpdateNested(-1, item);
  }

  public PreIncrementInitNested<A> addNewPreIncrementInit() {
    return new PreIncrementInitNested(-1, null);
  }

  public PreIncrementInitNested<A> addNewPreIncrementInitLike(PreIncrement item) {
    return new PreIncrementInitNested(-1, item);
  }

  public PreIncrementUpdateNested<A> addNewPreIncrementUpdate() {
    return new PreIncrementUpdateNested(-1, null);
  }

  public PreIncrementUpdateNested<A> addNewPreIncrementUpdateLike(PreIncrement item) {
    return new PreIncrementUpdateNested(-1, item);
  }

  public PropertyInitNested<A> addNewPropertyInit() {
    return new PropertyInitNested(-1, null);
  }

  public PropertyInitNested<A> addNewPropertyInitLike(Property item) {
    return new PropertyInitNested(-1, item);
  }

  public PropertyRefInitNested<A> addNewPropertyRefInit() {
    return new PropertyRefInitNested(-1, null);
  }

  public PropertyRefInitNested<A> addNewPropertyRefInitLike(PropertyRef item) {
    return new PropertyRefInitNested(-1, item);
  }

  public PropertyRefUpdateNested<A> addNewPropertyRefUpdate() {
    return new PropertyRefUpdateNested(-1, null);
  }

  public PropertyRefUpdateNested<A> addNewPropertyRefUpdateLike(PropertyRef item) {
    return new PropertyRefUpdateNested(-1, item);
  }

  public PropertyUpdateNested<A> addNewPropertyUpdate() {
    return new PropertyUpdateNested(-1, null);
  }

  public PropertyUpdateNested<A> addNewPropertyUpdateLike(Property item) {
    return new PropertyUpdateNested(-1, item);
  }

  public RightShiftInitNested<A> addNewRightShiftInit() {
    return new RightShiftInitNested(-1, null);
  }

  public A addNewRightShiftInit(Object left, Object right) {
    return (A) addToInit(new RightShift(left, right));
  }

  public RightShiftInitNested<A> addNewRightShiftInitLike(RightShift item) {
    return new RightShiftInitNested(-1, item);
  }

  public RightShiftUpdateNested<A> addNewRightShiftUpdate() {
    return new RightShiftUpdateNested(-1, null);
  }

  public A addNewRightShiftUpdate(Object left, Object right) {
    return (A) addToUpdate(new RightShift(left, right));
  }

  public RightShiftUpdateNested<A> addNewRightShiftUpdateLike(RightShift item) {
    return new RightShiftUpdateNested(-1, item);
  }

  public RightUnsignedShiftInitNested<A> addNewRightUnsignedShiftInit() {
    return new RightUnsignedShiftInitNested(-1, null);
  }

  public A addNewRightUnsignedShiftInit(Object left, Object right) {
    return (A) addToInit(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftInitNested<A> addNewRightUnsignedShiftInitLike(RightUnsignedShift item) {
    return new RightUnsignedShiftInitNested(-1, item);
  }

  public RightUnsignedShiftUpdateNested<A> addNewRightUnsignedShiftUpdate() {
    return new RightUnsignedShiftUpdateNested(-1, null);
  }

  public A addNewRightUnsignedShiftUpdate(Object left, Object right) {
    return (A) addToUpdate(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftUpdateNested<A> addNewRightUnsignedShiftUpdateLike(RightUnsignedShift item) {
    return new RightUnsignedShiftUpdateNested(-1, item);
  }

  public SuperInitNested<A> addNewSuperInit() {
    return new SuperInitNested(-1, null);
  }

  public SuperInitNested<A> addNewSuperInitLike(Super item) {
    return new SuperInitNested(-1, item);
  }

  public SuperUpdateNested<A> addNewSuperUpdate() {
    return new SuperUpdateNested(-1, null);
  }

  public SuperUpdateNested<A> addNewSuperUpdateLike(Super item) {
    return new SuperUpdateNested(-1, item);
  }

  public TernaryInitNested<A> addNewTernaryInit() {
    return new TernaryInitNested(-1, null);
  }

  public TernaryInitNested<A> addNewTernaryInitLike(Ternary item) {
    return new TernaryInitNested(-1, item);
  }

  public TernaryUpdateNested<A> addNewTernaryUpdate() {
    return new TernaryUpdateNested(-1, null);
  }

  public TernaryUpdateNested<A> addNewTernaryUpdateLike(Ternary item) {
    return new TernaryUpdateNested(-1, item);
  }

  public ThisInitNested<A> addNewThisInit() {
    return new ThisInitNested(-1, null);
  }

  public ThisInitNested<A> addNewThisInitLike(This item) {
    return new ThisInitNested(-1, item);
  }

  public ThisUpdateNested<A> addNewThisUpdate() {
    return new ThisUpdateNested(-1, null);
  }

  public ThisUpdateNested<A> addNewThisUpdateLike(This item) {
    return new ThisUpdateNested(-1, item);
  }

  public ValueRefInitNested<A> addNewValueRefInit() {
    return new ValueRefInitNested(-1, null);
  }

  public A addNewValueRefInit(Object value) {
    return (A) addToInit(new ValueRef(value));
  }

  public ValueRefInitNested<A> addNewValueRefInitLike(ValueRef item) {
    return new ValueRefInitNested(-1, item);
  }

  public ValueRefUpdateNested<A> addNewValueRefUpdate() {
    return new ValueRefUpdateNested(-1, null);
  }

  public A addNewValueRefUpdate(Object value) {
    return (A) addToUpdate(new ValueRef(value));
  }

  public ValueRefUpdateNested<A> addNewValueRefUpdateLike(ValueRef item) {
    return new ValueRefUpdateNested(-1, item);
  }

  public XorInitNested<A> addNewXorInit() {
    return new XorInitNested(-1, null);
  }

  public A addNewXorInit(Object left, Object right) {
    return (A) addToInit(new Xor(left, right));
  }

  public XorInitNested<A> addNewXorInitLike(Xor item) {
    return new XorInitNested(-1, item);
  }

  public XorUpdateNested<A> addNewXorUpdate() {
    return new XorUpdateNested(-1, null);
  }

  public A addNewXorUpdate(Object left, Object right) {
    return (A) addToUpdate(new Xor(left, right));
  }

  public XorUpdateNested<A> addNewXorUpdateLike(Xor item) {
    return new XorUpdateNested(-1, item);
  }

  public A addToInit(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    _visitables.get("init").add(builder);
    this.init.add(builder);
    return (A) this;
  }

  public A addToInit(Expression... items) {
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

  public A addToInit(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.init == null) {
      this.init = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    if (index < 0 || index >= init.size()) {
      _visitables.get("init").add(builder);
      init.add(builder);
    } else {
      _visitables.get("init").add(builder);
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
      _visitables.get("init").add(builder);
      init.add(index, builder);
    }
    return (A) this;
  }

  public A addToUpdate(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    _visitables.get("update").add(builder);
    this.update.add(builder);
    return (A) this;
  }

  public A addToUpdate(Expression... items) {
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

  public A addToUpdate(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.update == null) {
      this.update = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    if (index < 0 || index >= update.size()) {
      _visitables.get("update").add(builder);
      update.add(builder);
    } else {
      _visitables.get("update").add(builder);
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
      _visitables.get("update").add(builder);
      update.add(index, builder);
    }
    return (A) this;
  }

  public Statement buildBody() {
    return this.body != null ? this.body.build() : null;
  }

  public Expression buildCompare() {
    return this.compare != null ? this.compare.build() : null;
  }

  public Expression buildFirstInit() {
    return this.init.get(0).build();
  }

  public Expression buildFirstUpdate() {
    return this.update.get(0).build();
  }

  public List<Expression> buildInit() {
    return build(init);
  }

  public Expression buildInit(int index) {
    return this.init.get(index).build();
  }

  public Expression buildLastInit() {
    return this.init.get(init.size() - 1).build();
  }

  public Expression buildLastUpdate() {
    return this.update.get(update.size() - 1).build();
  }

  public Expression buildMatchingInit(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : init) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Expression buildMatchingUpdate(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : update) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public List<Expression> buildUpdate() {
    return build(update);
  }

  public Expression buildUpdate(int index) {
    return this.update.get(index).build();
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
      case "io.sundr.model." + "ClassRef":
        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);
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
      case "io.sundr.model." + "Super":
        return (VisitableBuilder<T, ?>) new SuperBuilder((Super) item);
      case "io.sundr.model." + "GreaterThan":
        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);
      case "io.sundr.model." + "Declare":
        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);
      case "io.sundr.model." + "Cast":
        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);
      case "io.sundr.model." + "Modulo":
        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);
      case "io.sundr.model." + "DotClass":
        return (VisitableBuilder<T, ?>) new DotClassBuilder((DotClass) item);
      case "io.sundr.model." + "ValueRef":
        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);
      case "io.sundr.model." + "LeftShift":
        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);
      case "io.sundr.model." + "Empty":
        return (VisitableBuilder<T, ?>) new EmptyBuilder((Empty) item);
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
      case "io.sundr.model." + "Property":
        return (VisitableBuilder<T, ?>) new PropertyBuilder((Property) item);
      case "io.sundr.model." + "LessThanOrEqual":
        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);
      case "io.sundr.model." + "ContextRef":
        return (VisitableBuilder<T, ?>) new ContextRefBuilder((ContextRef) item);
      case "io.sundr.model." + "Positive":
        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);
      case "io.sundr.model." + "ReturnDslThisStep":
        return (VisitableBuilder<T, ?>) new ReturnDslThisStepBuilder((ReturnDslThisStep) item);
      case "io.sundr.model." + "Try":
        return (VisitableBuilder<T, ?>) new TryBuilder((Try) item);
      case "io.sundr.model." + "Switch":
        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);
      case "io.sundr.model." + "Synchronized":
        return (VisitableBuilder<T, ?>) new SynchronizedBuilder((Synchronized) item);
      case "io.sundr.model." + "Break":
        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);
      case "io.sundr.model." + "While":
        return (VisitableBuilder<T, ?>) new WhileBuilder((While) item);
      case "io.sundr.model." + "Continue":
        return (VisitableBuilder<T, ?>) new ContinueBuilder((Continue) item);
      case "io.sundr.model." + "Throw":
        return (VisitableBuilder<T, ?>) new ThrowBuilder((Throw) item);
      case "io.sundr.model." + "StringStatement":
        return (VisitableBuilder<T, ?>) new StringStatementBuilder((StringStatement) item);
      case "io.sundr.model." + "Do":
        return (VisitableBuilder<T, ?>) new DoBuilder((Do) item);
      case "io.sundr.model." + "Foreach":
        return (VisitableBuilder<T, ?>) new ForeachBuilder((Foreach) item);
      case "io.sundr.model." + "Block":
        return (VisitableBuilder<T, ?>) new BlockBuilder((Block) item);
      case "io.sundr.model." + "ReturnDslVariableStep":
        return (VisitableBuilder<T, ?>) new ReturnDslVariableStepBuilder((ReturnDslVariableStep) item);
      case "io.sundr.model." + "If":
        return (VisitableBuilder<T, ?>) new IfBuilder((If) item);
      case "io.sundr.model." + "Return":
        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);
      case "io.sundr.model." + "IfDslThenStep":
        return (VisitableBuilder<T, ?>) new IfDslThenStepBuilder((IfDslThenStep) item);
      case "io.sundr.model." + "For":
        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  protected void copyInstance(For instance) {
    if (instance != null) {
      this.withInit(instance.getInit());
      this.withCompare(instance.getCompare());
      this.withUpdate(instance.getUpdate());
      this.withBody(instance.getBody());
    }
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

  public boolean hasBody() {
    return this.body != null;
  }

  public boolean hasCompare() {
    return this.compare != null;
  }

  public boolean hasInit() {
    return this.init != null && !(this.init.isEmpty());
  }

  public boolean hasMatchingInit(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : init) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingUpdate(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : update) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasUpdate() {
    return this.update != null && !(this.update.isEmpty());
  }

  public int hashCode() {
    return java.util.Objects.hash(init, compare, update, body, super.hashCode());
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

  public A removeFromInit(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.init == null)
      return (A) this;
    _visitables.get("init").remove(builder);
    this.init.remove(builder);
    return (A) this;
  }

  public A removeFromInit(Expression... items) {
    if (this.init == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("init").remove(builder);
      this.init.remove(builder);
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

  public A removeFromUpdate(Expression... items) {
    if (this.update == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("update").remove(builder);
      this.update.remove(builder);
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

  public AssignInitNested<A> setNewAssignInitLike(int index, Assign item) {
    return new AssignInitNested(index, item);
  }

  public AssignUpdateNested<A> setNewAssignUpdateLike(int index, Assign item) {
    return new AssignUpdateNested(index, item);
  }

  public BinaryExpressionInitNested<A> setNewBinaryExpressionInitLike(int index, BinaryExpression item) {
    return new BinaryExpressionInitNested(index, item);
  }

  public BinaryExpressionUpdateNested<A> setNewBinaryExpressionUpdateLike(int index, BinaryExpression item) {
    return new BinaryExpressionUpdateNested(index, item);
  }

  public BitwiseAndInitNested<A> setNewBitwiseAndInitLike(int index, BitwiseAnd item) {
    return new BitwiseAndInitNested(index, item);
  }

  public BitwiseAndUpdateNested<A> setNewBitwiseAndUpdateLike(int index, BitwiseAnd item) {
    return new BitwiseAndUpdateNested(index, item);
  }

  public BitwiseOrInitNested<A> setNewBitwiseOrInitLike(int index, BitwiseOr item) {
    return new BitwiseOrInitNested(index, item);
  }

  public BitwiseOrUpdateNested<A> setNewBitwiseOrUpdateLike(int index, BitwiseOr item) {
    return new BitwiseOrUpdateNested(index, item);
  }

  public CastInitNested<A> setNewCastInitLike(int index, Cast item) {
    return new CastInitNested(index, item);
  }

  public CastUpdateNested<A> setNewCastUpdateLike(int index, Cast item) {
    return new CastUpdateNested(index, item);
  }

  public ClassRefInitNested<A> setNewClassRefInitLike(int index, ClassRef item) {
    return new ClassRefInitNested(index, item);
  }

  public ClassRefUpdateNested<A> setNewClassRefUpdateLike(int index, ClassRef item) {
    return new ClassRefUpdateNested(index, item);
  }

  public ConstructInitNested<A> setNewConstructInitLike(int index, Construct item) {
    return new ConstructInitNested(index, item);
  }

  public ConstructUpdateNested<A> setNewConstructUpdateLike(int index, Construct item) {
    return new ConstructUpdateNested(index, item);
  }

  public ContextRefInitNested<A> setNewContextRefInitLike(int index, ContextRef item) {
    return new ContextRefInitNested(index, item);
  }

  public ContextRefUpdateNested<A> setNewContextRefUpdateLike(int index, ContextRef item) {
    return new ContextRefUpdateNested(index, item);
  }

  public DeclareInitNested<A> setNewDeclareInitLike(int index, Declare item) {
    return new DeclareInitNested(index, item);
  }

  public DeclareUpdateNested<A> setNewDeclareUpdateLike(int index, Declare item) {
    return new DeclareUpdateNested(index, item);
  }

  public DivideInitNested<A> setNewDivideInitLike(int index, Divide item) {
    return new DivideInitNested(index, item);
  }

  public DivideUpdateNested<A> setNewDivideUpdateLike(int index, Divide item) {
    return new DivideUpdateNested(index, item);
  }

  public DotClassInitNested<A> setNewDotClassInitLike(int index, DotClass item) {
    return new DotClassInitNested(index, item);
  }

  public DotClassUpdateNested<A> setNewDotClassUpdateLike(int index, DotClass item) {
    return new DotClassUpdateNested(index, item);
  }

  public EmptyInitNested<A> setNewEmptyInitLike(int index, Empty item) {
    return new EmptyInitNested(index, item);
  }

  public EmptyUpdateNested<A> setNewEmptyUpdateLike(int index, Empty item) {
    return new EmptyUpdateNested(index, item);
  }

  public EnclosedInitNested<A> setNewEnclosedInitLike(int index, Enclosed item) {
    return new EnclosedInitNested(index, item);
  }

  public EnclosedUpdateNested<A> setNewEnclosedUpdateLike(int index, Enclosed item) {
    return new EnclosedUpdateNested(index, item);
  }

  public EqualsInitNested<A> setNewEqualsInitLike(int index, Equals item) {
    return new EqualsInitNested(index, item);
  }

  public EqualsUpdateNested<A> setNewEqualsUpdateLike(int index, Equals item) {
    return new EqualsUpdateNested(index, item);
  }

  public GreaterThanInitNested<A> setNewGreaterThanInitLike(int index, GreaterThan item) {
    return new GreaterThanInitNested(index, item);
  }

  public GreaterThanOrEqualInitNested<A> setNewGreaterThanOrEqualInitLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualInitNested(index, item);
  }

  public GreaterThanOrEqualUpdateNested<A> setNewGreaterThanOrEqualUpdateLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualUpdateNested(index, item);
  }

  public GreaterThanUpdateNested<A> setNewGreaterThanUpdateLike(int index, GreaterThan item) {
    return new GreaterThanUpdateNested(index, item);
  }

  public IndexInitNested<A> setNewIndexInitLike(int index, Index item) {
    return new IndexInitNested(index, item);
  }

  public IndexUpdateNested<A> setNewIndexUpdateLike(int index, Index item) {
    return new IndexUpdateNested(index, item);
  }

  public InstanceOfInitNested<A> setNewInstanceOfInitLike(int index, InstanceOf item) {
    return new InstanceOfInitNested(index, item);
  }

  public InstanceOfUpdateNested<A> setNewInstanceOfUpdateLike(int index, InstanceOf item) {
    return new InstanceOfUpdateNested(index, item);
  }

  public InverseInitNested<A> setNewInverseInitLike(int index, Inverse item) {
    return new InverseInitNested(index, item);
  }

  public InverseUpdateNested<A> setNewInverseUpdateLike(int index, Inverse item) {
    return new InverseUpdateNested(index, item);
  }

  public LambdaInitNested<A> setNewLambdaInitLike(int index, Lambda item) {
    return new LambdaInitNested(index, item);
  }

  public LambdaUpdateNested<A> setNewLambdaUpdateLike(int index, Lambda item) {
    return new LambdaUpdateNested(index, item);
  }

  public LeftShiftInitNested<A> setNewLeftShiftInitLike(int index, LeftShift item) {
    return new LeftShiftInitNested(index, item);
  }

  public LeftShiftUpdateNested<A> setNewLeftShiftUpdateLike(int index, LeftShift item) {
    return new LeftShiftUpdateNested(index, item);
  }

  public LessThanInitNested<A> setNewLessThanInitLike(int index, LessThan item) {
    return new LessThanInitNested(index, item);
  }

  public LessThanOrEqualInitNested<A> setNewLessThanOrEqualInitLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualInitNested(index, item);
  }

  public LessThanOrEqualUpdateNested<A> setNewLessThanOrEqualUpdateLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualUpdateNested(index, item);
  }

  public LessThanUpdateNested<A> setNewLessThanUpdateLike(int index, LessThan item) {
    return new LessThanUpdateNested(index, item);
  }

  public LogicalAndInitNested<A> setNewLogicalAndInitLike(int index, LogicalAnd item) {
    return new LogicalAndInitNested(index, item);
  }

  public LogicalAndUpdateNested<A> setNewLogicalAndUpdateLike(int index, LogicalAnd item) {
    return new LogicalAndUpdateNested(index, item);
  }

  public LogicalOrInitNested<A> setNewLogicalOrInitLike(int index, LogicalOr item) {
    return new LogicalOrInitNested(index, item);
  }

  public LogicalOrUpdateNested<A> setNewLogicalOrUpdateLike(int index, LogicalOr item) {
    return new LogicalOrUpdateNested(index, item);
  }

  public MethodCallInitNested<A> setNewMethodCallInitLike(int index, MethodCall item) {
    return new MethodCallInitNested(index, item);
  }

  public MethodCallUpdateNested<A> setNewMethodCallUpdateLike(int index, MethodCall item) {
    return new MethodCallUpdateNested(index, item);
  }

  public MinusInitNested<A> setNewMinusInitLike(int index, Minus item) {
    return new MinusInitNested(index, item);
  }

  public MinusUpdateNested<A> setNewMinusUpdateLike(int index, Minus item) {
    return new MinusUpdateNested(index, item);
  }

  public ModuloInitNested<A> setNewModuloInitLike(int index, Modulo item) {
    return new ModuloInitNested(index, item);
  }

  public ModuloUpdateNested<A> setNewModuloUpdateLike(int index, Modulo item) {
    return new ModuloUpdateNested(index, item);
  }

  public MultiplyInitNested<A> setNewMultiplyInitLike(int index, Multiply item) {
    return new MultiplyInitNested(index, item);
  }

  public MultiplyUpdateNested<A> setNewMultiplyUpdateLike(int index, Multiply item) {
    return new MultiplyUpdateNested(index, item);
  }

  public NegativeInitNested<A> setNewNegativeInitLike(int index, Negative item) {
    return new NegativeInitNested(index, item);
  }

  public NegativeUpdateNested<A> setNewNegativeUpdateLike(int index, Negative item) {
    return new NegativeUpdateNested(index, item);
  }

  public NewArrayInitNested<A> setNewNewArrayInitLike(int index, NewArray item) {
    return new NewArrayInitNested(index, item);
  }

  public NewArrayUpdateNested<A> setNewNewArrayUpdateLike(int index, NewArray item) {
    return new NewArrayUpdateNested(index, item);
  }

  public NotEqualsInitNested<A> setNewNotEqualsInitLike(int index, NotEquals item) {
    return new NotEqualsInitNested(index, item);
  }

  public NotEqualsUpdateNested<A> setNewNotEqualsUpdateLike(int index, NotEquals item) {
    return new NotEqualsUpdateNested(index, item);
  }

  public NotInitNested<A> setNewNotInitLike(int index, Not item) {
    return new NotInitNested(index, item);
  }

  public NotUpdateNested<A> setNewNotUpdateLike(int index, Not item) {
    return new NotUpdateNested(index, item);
  }

  public PlusInitNested<A> setNewPlusInitLike(int index, Plus item) {
    return new PlusInitNested(index, item);
  }

  public PlusUpdateNested<A> setNewPlusUpdateLike(int index, Plus item) {
    return new PlusUpdateNested(index, item);
  }

  public PositiveInitNested<A> setNewPositiveInitLike(int index, Positive item) {
    return new PositiveInitNested(index, item);
  }

  public PositiveUpdateNested<A> setNewPositiveUpdateLike(int index, Positive item) {
    return new PositiveUpdateNested(index, item);
  }

  public PostDecrementInitNested<A> setNewPostDecrementInitLike(int index, PostDecrement item) {
    return new PostDecrementInitNested(index, item);
  }

  public PostDecrementUpdateNested<A> setNewPostDecrementUpdateLike(int index, PostDecrement item) {
    return new PostDecrementUpdateNested(index, item);
  }

  public PostIncrementInitNested<A> setNewPostIncrementInitLike(int index, PostIncrement item) {
    return new PostIncrementInitNested(index, item);
  }

  public PostIncrementUpdateNested<A> setNewPostIncrementUpdateLike(int index, PostIncrement item) {
    return new PostIncrementUpdateNested(index, item);
  }

  public PreDecrementInitNested<A> setNewPreDecrementInitLike(int index, PreDecrement item) {
    return new PreDecrementInitNested(index, item);
  }

  public PreDecrementUpdateNested<A> setNewPreDecrementUpdateLike(int index, PreDecrement item) {
    return new PreDecrementUpdateNested(index, item);
  }

  public PreIncrementInitNested<A> setNewPreIncrementInitLike(int index, PreIncrement item) {
    return new PreIncrementInitNested(index, item);
  }

  public PreIncrementUpdateNested<A> setNewPreIncrementUpdateLike(int index, PreIncrement item) {
    return new PreIncrementUpdateNested(index, item);
  }

  public PropertyInitNested<A> setNewPropertyInitLike(int index, Property item) {
    return new PropertyInitNested(index, item);
  }

  public PropertyRefInitNested<A> setNewPropertyRefInitLike(int index, PropertyRef item) {
    return new PropertyRefInitNested(index, item);
  }

  public PropertyRefUpdateNested<A> setNewPropertyRefUpdateLike(int index, PropertyRef item) {
    return new PropertyRefUpdateNested(index, item);
  }

  public PropertyUpdateNested<A> setNewPropertyUpdateLike(int index, Property item) {
    return new PropertyUpdateNested(index, item);
  }

  public RightShiftInitNested<A> setNewRightShiftInitLike(int index, RightShift item) {
    return new RightShiftInitNested(index, item);
  }

  public RightShiftUpdateNested<A> setNewRightShiftUpdateLike(int index, RightShift item) {
    return new RightShiftUpdateNested(index, item);
  }

  public RightUnsignedShiftInitNested<A> setNewRightUnsignedShiftInitLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftInitNested(index, item);
  }

  public RightUnsignedShiftUpdateNested<A> setNewRightUnsignedShiftUpdateLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftUpdateNested(index, item);
  }

  public SuperInitNested<A> setNewSuperInitLike(int index, Super item) {
    return new SuperInitNested(index, item);
  }

  public SuperUpdateNested<A> setNewSuperUpdateLike(int index, Super item) {
    return new SuperUpdateNested(index, item);
  }

  public TernaryInitNested<A> setNewTernaryInitLike(int index, Ternary item) {
    return new TernaryInitNested(index, item);
  }

  public TernaryUpdateNested<A> setNewTernaryUpdateLike(int index, Ternary item) {
    return new TernaryUpdateNested(index, item);
  }

  public ThisInitNested<A> setNewThisInitLike(int index, This item) {
    return new ThisInitNested(index, item);
  }

  public ThisUpdateNested<A> setNewThisUpdateLike(int index, This item) {
    return new ThisUpdateNested(index, item);
  }

  public ValueRefInitNested<A> setNewValueRefInitLike(int index, ValueRef item) {
    return new ValueRefInitNested(index, item);
  }

  public ValueRefUpdateNested<A> setNewValueRefUpdateLike(int index, ValueRef item) {
    return new ValueRefUpdateNested(index, item);
  }

  public XorInitNested<A> setNewXorInitLike(int index, Xor item) {
    return new XorInitNested(index, item);
  }

  public XorUpdateNested<A> setNewXorUpdateLike(int index, Xor item) {
    return new XorUpdateNested(index, item);
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
      _visitables.get("init").add(builder);
      init.set(index, builder);
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
      _visitables.get("update").add(builder);
      update.set(index, builder);
    }
    return (A) this;
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

  public A withBody(Statement body) {
    if (body == null) {
      this.body = null;
      this._visitables.remove("body");
      return (A) this;
    } else {
      VisitableBuilder<? extends Statement, ?> builder = builder(body);
      this._visitables.get("body").clear();
      this._visitables.get("body").add(builder);
      this.body = builder;
      return (A) this;
    }
  }

  public A withCompare(Expression compare) {
    if (compare == null) {
      this.compare = null;
      this._visitables.remove("compare");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(compare);
      this._visitables.get("compare").clear();
      this._visitables.get("compare").add(builder);
      this.compare = builder;
      return (A) this;
    }
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

  public A withInit(Expression... init) {
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

  public AssignBodyNested<A> withNewAssignBody() {
    return new AssignBodyNested(null);
  }

  public AssignBodyNested<A> withNewAssignBodyLike(Assign item) {
    return new AssignBodyNested(item);
  }

  public AssignCompareNested<A> withNewAssignCompare() {
    return new AssignCompareNested(null);
  }

  public AssignCompareNested<A> withNewAssignCompareLike(Assign item) {
    return new AssignCompareNested(item);
  }

  public BinaryExpressionBodyNested<A> withNewBinaryExpressionBody() {
    return new BinaryExpressionBodyNested(null);
  }

  public BinaryExpressionBodyNested<A> withNewBinaryExpressionBodyLike(BinaryExpression item) {
    return new BinaryExpressionBodyNested(item);
  }

  public BinaryExpressionCompareNested<A> withNewBinaryExpressionCompare() {
    return new BinaryExpressionCompareNested(null);
  }

  public BinaryExpressionCompareNested<A> withNewBinaryExpressionCompareLike(BinaryExpression item) {
    return new BinaryExpressionCompareNested(item);
  }

  public BitwiseAndBodyNested<A> withNewBitwiseAndBody() {
    return new BitwiseAndBodyNested(null);
  }

  public A withNewBitwiseAndBody(Object left, Object right) {
    return (A) withBody(new BitwiseAnd(left, right));
  }

  public BitwiseAndBodyNested<A> withNewBitwiseAndBodyLike(BitwiseAnd item) {
    return new BitwiseAndBodyNested(item);
  }

  public BitwiseAndCompareNested<A> withNewBitwiseAndCompare() {
    return new BitwiseAndCompareNested(null);
  }

  public A withNewBitwiseAndCompare(Object left, Object right) {
    return (A) withCompare(new BitwiseAnd(left, right));
  }

  public BitwiseAndCompareNested<A> withNewBitwiseAndCompareLike(BitwiseAnd item) {
    return new BitwiseAndCompareNested(item);
  }

  public BitwiseOrBodyNested<A> withNewBitwiseOrBody() {
    return new BitwiseOrBodyNested(null);
  }

  public A withNewBitwiseOrBody(Object left, Object right) {
    return (A) withBody(new BitwiseOr(left, right));
  }

  public BitwiseOrBodyNested<A> withNewBitwiseOrBodyLike(BitwiseOr item) {
    return new BitwiseOrBodyNested(item);
  }

  public BitwiseOrCompareNested<A> withNewBitwiseOrCompare() {
    return new BitwiseOrCompareNested(null);
  }

  public A withNewBitwiseOrCompare(Object left, Object right) {
    return (A) withCompare(new BitwiseOr(left, right));
  }

  public BitwiseOrCompareNested<A> withNewBitwiseOrCompareLike(BitwiseOr item) {
    return new BitwiseOrCompareNested(item);
  }

  public BlockBodyNested<A> withNewBlockBody() {
    return new BlockBodyNested(null);
  }

  public BlockBodyNested<A> withNewBlockBodyLike(Block item) {
    return new BlockBodyNested(item);
  }

  public BreakBodyNested<A> withNewBreakBody() {
    return new BreakBodyNested(null);
  }

  public BreakBodyNested<A> withNewBreakBodyLike(Break item) {
    return new BreakBodyNested(item);
  }

  public CastCompareNested<A> withNewCastCompare() {
    return new CastCompareNested(null);
  }

  public CastCompareNested<A> withNewCastCompareLike(Cast item) {
    return new CastCompareNested(item);
  }

  public ClassRefCompareNested<A> withNewClassRefCompare() {
    return new ClassRefCompareNested(null);
  }

  public ClassRefCompareNested<A> withNewClassRefCompareLike(ClassRef item) {
    return new ClassRefCompareNested(item);
  }

  public ConstructBodyNested<A> withNewConstructBody() {
    return new ConstructBodyNested(null);
  }

  public ConstructBodyNested<A> withNewConstructBodyLike(Construct item) {
    return new ConstructBodyNested(item);
  }

  public ConstructCompareNested<A> withNewConstructCompare() {
    return new ConstructCompareNested(null);
  }

  public ConstructCompareNested<A> withNewConstructCompareLike(Construct item) {
    return new ConstructCompareNested(item);
  }

  public ContextRefCompareNested<A> withNewContextRefCompare() {
    return new ContextRefCompareNested(null);
  }

  public A withNewContextRefCompare(String name) {
    return (A) withCompare(new ContextRef(name));
  }

  public ContextRefCompareNested<A> withNewContextRefCompareLike(ContextRef item) {
    return new ContextRefCompareNested(item);
  }

  public ContinueBodyNested<A> withNewContinueBody() {
    return new ContinueBodyNested(null);
  }

  public ContinueBodyNested<A> withNewContinueBodyLike(Continue item) {
    return new ContinueBodyNested(item);
  }

  public DeclareBodyNested<A> withNewDeclareBody() {
    return new DeclareBodyNested(null);
  }

  public A withNewDeclareBody(Class type, String name) {
    return (A) withBody(new Declare(type, name));
  }

  public A withNewDeclareBody(Class type, String name, Object value) {
    return (A) withBody(new Declare(type, name, value));
  }

  public DeclareBodyNested<A> withNewDeclareBodyLike(Declare item) {
    return new DeclareBodyNested(item);
  }

  public DeclareCompareNested<A> withNewDeclareCompare() {
    return new DeclareCompareNested(null);
  }

  public A withNewDeclareCompare(Class type, String name) {
    return (A) withCompare(new Declare(type, name));
  }

  public A withNewDeclareCompare(Class type, String name, Object value) {
    return (A) withCompare(new Declare(type, name, value));
  }

  public DeclareCompareNested<A> withNewDeclareCompareLike(Declare item) {
    return new DeclareCompareNested(item);
  }

  public DivideBodyNested<A> withNewDivideBody() {
    return new DivideBodyNested(null);
  }

  public A withNewDivideBody(Object left, Object right) {
    return (A) withBody(new Divide(left, right));
  }

  public DivideBodyNested<A> withNewDivideBodyLike(Divide item) {
    return new DivideBodyNested(item);
  }

  public DivideCompareNested<A> withNewDivideCompare() {
    return new DivideCompareNested(null);
  }

  public A withNewDivideCompare(Object left, Object right) {
    return (A) withCompare(new Divide(left, right));
  }

  public DivideCompareNested<A> withNewDivideCompareLike(Divide item) {
    return new DivideCompareNested(item);
  }

  public DoBodyNested<A> withNewDoBody() {
    return new DoBodyNested(null);
  }

  public DoBodyNested<A> withNewDoBodyLike(Do item) {
    return new DoBodyNested(item);
  }

  public DotClassCompareNested<A> withNewDotClassCompare() {
    return new DotClassCompareNested(null);
  }

  public DotClassCompareNested<A> withNewDotClassCompareLike(DotClass item) {
    return new DotClassCompareNested(item);
  }

  public EmptyBodyNested<A> withNewEmptyBody() {
    return new EmptyBodyNested(null);
  }

  public EmptyBodyNested<A> withNewEmptyBodyLike(Empty item) {
    return new EmptyBodyNested(item);
  }

  public EmptyCompareNested<A> withNewEmptyCompare() {
    return new EmptyCompareNested(null);
  }

  public EmptyCompareNested<A> withNewEmptyCompareLike(Empty item) {
    return new EmptyCompareNested(item);
  }

  public EnclosedCompareNested<A> withNewEnclosedCompare() {
    return new EnclosedCompareNested(null);
  }

  public EnclosedCompareNested<A> withNewEnclosedCompareLike(Enclosed item) {
    return new EnclosedCompareNested(item);
  }

  public EqualsBodyNested<A> withNewEqualsBody() {
    return new EqualsBodyNested(null);
  }

  public A withNewEqualsBody(Object left, Object right) {
    return (A) withBody(new Equals(left, right));
  }

  public EqualsBodyNested<A> withNewEqualsBodyLike(Equals item) {
    return new EqualsBodyNested(item);
  }

  public EqualsCompareNested<A> withNewEqualsCompare() {
    return new EqualsCompareNested(null);
  }

  public A withNewEqualsCompare(Object left, Object right) {
    return (A) withCompare(new Equals(left, right));
  }

  public EqualsCompareNested<A> withNewEqualsCompareLike(Equals item) {
    return new EqualsCompareNested(item);
  }

  public ForBodyNested<A> withNewForBody() {
    return new ForBodyNested(null);
  }

  public ForBodyNested<A> withNewForBodyLike(For item) {
    return new ForBodyNested(item);
  }

  public ForeachBodyNested<A> withNewForeachBody() {
    return new ForeachBodyNested(null);
  }

  public ForeachBodyNested<A> withNewForeachBodyLike(Foreach item) {
    return new ForeachBodyNested(item);
  }

  public GreaterThanBodyNested<A> withNewGreaterThanBody() {
    return new GreaterThanBodyNested(null);
  }

  public A withNewGreaterThanBody(Object left, Object right) {
    return (A) withBody(new GreaterThan(left, right));
  }

  public GreaterThanBodyNested<A> withNewGreaterThanBodyLike(GreaterThan item) {
    return new GreaterThanBodyNested(item);
  }

  public GreaterThanCompareNested<A> withNewGreaterThanCompare() {
    return new GreaterThanCompareNested(null);
  }

  public A withNewGreaterThanCompare(Object left, Object right) {
    return (A) withCompare(new GreaterThan(left, right));
  }

  public GreaterThanCompareNested<A> withNewGreaterThanCompareLike(GreaterThan item) {
    return new GreaterThanCompareNested(item);
  }

  public GreaterThanOrEqualBodyNested<A> withNewGreaterThanOrEqualBody() {
    return new GreaterThanOrEqualBodyNested(null);
  }

  public A withNewGreaterThanOrEqualBody(Object left, Object right) {
    return (A) withBody(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualBodyNested<A> withNewGreaterThanOrEqualBodyLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualBodyNested(item);
  }

  public GreaterThanOrEqualCompareNested<A> withNewGreaterThanOrEqualCompare() {
    return new GreaterThanOrEqualCompareNested(null);
  }

  public A withNewGreaterThanOrEqualCompare(Object left, Object right) {
    return (A) withCompare(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualCompareNested<A> withNewGreaterThanOrEqualCompareLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualCompareNested(item);
  }

  public IfBodyNested<A> withNewIfBody() {
    return new IfBodyNested(null);
  }

  public IfBodyNested<A> withNewIfBodyLike(If item) {
    return new IfBodyNested(item);
  }

  public IfDslThenStepBodyNested<A> withNewIfDslThenStepBody() {
    return new IfDslThenStepBodyNested(null);
  }

  public IfDslThenStepBodyNested<A> withNewIfDslThenStepBodyLike(IfDslThenStep item) {
    return new IfDslThenStepBodyNested(item);
  }

  public IndexCompareNested<A> withNewIndexCompare() {
    return new IndexCompareNested(null);
  }

  public IndexCompareNested<A> withNewIndexCompareLike(Index item) {
    return new IndexCompareNested(item);
  }

  public InstanceOfCompareNested<A> withNewInstanceOfCompare() {
    return new InstanceOfCompareNested(null);
  }

  public InstanceOfCompareNested<A> withNewInstanceOfCompareLike(InstanceOf item) {
    return new InstanceOfCompareNested(item);
  }

  public InverseCompareNested<A> withNewInverseCompare() {
    return new InverseCompareNested(null);
  }

  public InverseCompareNested<A> withNewInverseCompareLike(Inverse item) {
    return new InverseCompareNested(item);
  }

  public LambdaBodyNested<A> withNewLambdaBody() {
    return new LambdaBodyNested(null);
  }

  public LambdaBodyNested<A> withNewLambdaBodyLike(Lambda item) {
    return new LambdaBodyNested(item);
  }

  public LambdaCompareNested<A> withNewLambdaCompare() {
    return new LambdaCompareNested(null);
  }

  public LambdaCompareNested<A> withNewLambdaCompareLike(Lambda item) {
    return new LambdaCompareNested(item);
  }

  public LeftShiftBodyNested<A> withNewLeftShiftBody() {
    return new LeftShiftBodyNested(null);
  }

  public A withNewLeftShiftBody(Object left, Object right) {
    return (A) withBody(new LeftShift(left, right));
  }

  public LeftShiftBodyNested<A> withNewLeftShiftBodyLike(LeftShift item) {
    return new LeftShiftBodyNested(item);
  }

  public LeftShiftCompareNested<A> withNewLeftShiftCompare() {
    return new LeftShiftCompareNested(null);
  }

  public A withNewLeftShiftCompare(Object left, Object right) {
    return (A) withCompare(new LeftShift(left, right));
  }

  public LeftShiftCompareNested<A> withNewLeftShiftCompareLike(LeftShift item) {
    return new LeftShiftCompareNested(item);
  }

  public LessThanBodyNested<A> withNewLessThanBody() {
    return new LessThanBodyNested(null);
  }

  public A withNewLessThanBody(Object left, Object right) {
    return (A) withBody(new LessThan(left, right));
  }

  public LessThanBodyNested<A> withNewLessThanBodyLike(LessThan item) {
    return new LessThanBodyNested(item);
  }

  public LessThanCompareNested<A> withNewLessThanCompare() {
    return new LessThanCompareNested(null);
  }

  public A withNewLessThanCompare(Object left, Object right) {
    return (A) withCompare(new LessThan(left, right));
  }

  public LessThanCompareNested<A> withNewLessThanCompareLike(LessThan item) {
    return new LessThanCompareNested(item);
  }

  public LessThanOrEqualBodyNested<A> withNewLessThanOrEqualBody() {
    return new LessThanOrEqualBodyNested(null);
  }

  public A withNewLessThanOrEqualBody(Object left, Object right) {
    return (A) withBody(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualBodyNested<A> withNewLessThanOrEqualBodyLike(LessThanOrEqual item) {
    return new LessThanOrEqualBodyNested(item);
  }

  public LessThanOrEqualCompareNested<A> withNewLessThanOrEqualCompare() {
    return new LessThanOrEqualCompareNested(null);
  }

  public A withNewLessThanOrEqualCompare(Object left, Object right) {
    return (A) withCompare(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualCompareNested<A> withNewLessThanOrEqualCompareLike(LessThanOrEqual item) {
    return new LessThanOrEqualCompareNested(item);
  }

  public LogicalAndBodyNested<A> withNewLogicalAndBody() {
    return new LogicalAndBodyNested(null);
  }

  public A withNewLogicalAndBody(Object left, Object right) {
    return (A) withBody(new LogicalAnd(left, right));
  }

  public LogicalAndBodyNested<A> withNewLogicalAndBodyLike(LogicalAnd item) {
    return new LogicalAndBodyNested(item);
  }

  public LogicalAndCompareNested<A> withNewLogicalAndCompare() {
    return new LogicalAndCompareNested(null);
  }

  public A withNewLogicalAndCompare(Object left, Object right) {
    return (A) withCompare(new LogicalAnd(left, right));
  }

  public LogicalAndCompareNested<A> withNewLogicalAndCompareLike(LogicalAnd item) {
    return new LogicalAndCompareNested(item);
  }

  public LogicalOrBodyNested<A> withNewLogicalOrBody() {
    return new LogicalOrBodyNested(null);
  }

  public A withNewLogicalOrBody(Object left, Object right) {
    return (A) withBody(new LogicalOr(left, right));
  }

  public LogicalOrBodyNested<A> withNewLogicalOrBodyLike(LogicalOr item) {
    return new LogicalOrBodyNested(item);
  }

  public LogicalOrCompareNested<A> withNewLogicalOrCompare() {
    return new LogicalOrCompareNested(null);
  }

  public A withNewLogicalOrCompare(Object left, Object right) {
    return (A) withCompare(new LogicalOr(left, right));
  }

  public LogicalOrCompareNested<A> withNewLogicalOrCompareLike(LogicalOr item) {
    return new LogicalOrCompareNested(item);
  }

  public MethodCallBodyNested<A> withNewMethodCallBody() {
    return new MethodCallBodyNested(null);
  }

  public MethodCallBodyNested<A> withNewMethodCallBodyLike(MethodCall item) {
    return new MethodCallBodyNested(item);
  }

  public MethodCallCompareNested<A> withNewMethodCallCompare() {
    return new MethodCallCompareNested(null);
  }

  public MethodCallCompareNested<A> withNewMethodCallCompareLike(MethodCall item) {
    return new MethodCallCompareNested(item);
  }

  public MinusBodyNested<A> withNewMinusBody() {
    return new MinusBodyNested(null);
  }

  public A withNewMinusBody(Object left, Object right) {
    return (A) withBody(new Minus(left, right));
  }

  public MinusBodyNested<A> withNewMinusBodyLike(Minus item) {
    return new MinusBodyNested(item);
  }

  public MinusCompareNested<A> withNewMinusCompare() {
    return new MinusCompareNested(null);
  }

  public A withNewMinusCompare(Object left, Object right) {
    return (A) withCompare(new Minus(left, right));
  }

  public MinusCompareNested<A> withNewMinusCompareLike(Minus item) {
    return new MinusCompareNested(item);
  }

  public ModuloBodyNested<A> withNewModuloBody() {
    return new ModuloBodyNested(null);
  }

  public A withNewModuloBody(Object left, Object right) {
    return (A) withBody(new Modulo(left, right));
  }

  public ModuloBodyNested<A> withNewModuloBodyLike(Modulo item) {
    return new ModuloBodyNested(item);
  }

  public ModuloCompareNested<A> withNewModuloCompare() {
    return new ModuloCompareNested(null);
  }

  public A withNewModuloCompare(Object left, Object right) {
    return (A) withCompare(new Modulo(left, right));
  }

  public ModuloCompareNested<A> withNewModuloCompareLike(Modulo item) {
    return new ModuloCompareNested(item);
  }

  public MultiplyBodyNested<A> withNewMultiplyBody() {
    return new MultiplyBodyNested(null);
  }

  public A withNewMultiplyBody(Object left, Object right) {
    return (A) withBody(new Multiply(left, right));
  }

  public MultiplyBodyNested<A> withNewMultiplyBodyLike(Multiply item) {
    return new MultiplyBodyNested(item);
  }

  public MultiplyCompareNested<A> withNewMultiplyCompare() {
    return new MultiplyCompareNested(null);
  }

  public A withNewMultiplyCompare(Object left, Object right) {
    return (A) withCompare(new Multiply(left, right));
  }

  public MultiplyCompareNested<A> withNewMultiplyCompareLike(Multiply item) {
    return new MultiplyCompareNested(item);
  }

  public NegativeCompareNested<A> withNewNegativeCompare() {
    return new NegativeCompareNested(null);
  }

  public NegativeCompareNested<A> withNewNegativeCompareLike(Negative item) {
    return new NegativeCompareNested(item);
  }

  public NewArrayCompareNested<A> withNewNewArrayCompare() {
    return new NewArrayCompareNested(null);
  }

  public A withNewNewArrayCompare(Class type, Integer[] sizes) {
    return (A) withCompare(new NewArray(type, sizes));
  }

  public NewArrayCompareNested<A> withNewNewArrayCompareLike(NewArray item) {
    return new NewArrayCompareNested(item);
  }

  public NotCompareNested<A> withNewNotCompare() {
    return new NotCompareNested(null);
  }

  public NotCompareNested<A> withNewNotCompareLike(Not item) {
    return new NotCompareNested(item);
  }

  public NotEqualsBodyNested<A> withNewNotEqualsBody() {
    return new NotEqualsBodyNested(null);
  }

  public A withNewNotEqualsBody(Object left, Object right) {
    return (A) withBody(new NotEquals(left, right));
  }

  public NotEqualsBodyNested<A> withNewNotEqualsBodyLike(NotEquals item) {
    return new NotEqualsBodyNested(item);
  }

  public NotEqualsCompareNested<A> withNewNotEqualsCompare() {
    return new NotEqualsCompareNested(null);
  }

  public A withNewNotEqualsCompare(Object left, Object right) {
    return (A) withCompare(new NotEquals(left, right));
  }

  public NotEqualsCompareNested<A> withNewNotEqualsCompareLike(NotEquals item) {
    return new NotEqualsCompareNested(item);
  }

  public PlusBodyNested<A> withNewPlusBody() {
    return new PlusBodyNested(null);
  }

  public A withNewPlusBody(Object left, Object right) {
    return (A) withBody(new Plus(left, right));
  }

  public PlusBodyNested<A> withNewPlusBodyLike(Plus item) {
    return new PlusBodyNested(item);
  }

  public PlusCompareNested<A> withNewPlusCompare() {
    return new PlusCompareNested(null);
  }

  public A withNewPlusCompare(Object left, Object right) {
    return (A) withCompare(new Plus(left, right));
  }

  public PlusCompareNested<A> withNewPlusCompareLike(Plus item) {
    return new PlusCompareNested(item);
  }

  public PositiveCompareNested<A> withNewPositiveCompare() {
    return new PositiveCompareNested(null);
  }

  public PositiveCompareNested<A> withNewPositiveCompareLike(Positive item) {
    return new PositiveCompareNested(item);
  }

  public PostDecrementBodyNested<A> withNewPostDecrementBody() {
    return new PostDecrementBodyNested(null);
  }

  public PostDecrementBodyNested<A> withNewPostDecrementBodyLike(PostDecrement item) {
    return new PostDecrementBodyNested(item);
  }

  public PostDecrementCompareNested<A> withNewPostDecrementCompare() {
    return new PostDecrementCompareNested(null);
  }

  public PostDecrementCompareNested<A> withNewPostDecrementCompareLike(PostDecrement item) {
    return new PostDecrementCompareNested(item);
  }

  public PostIncrementBodyNested<A> withNewPostIncrementBody() {
    return new PostIncrementBodyNested(null);
  }

  public PostIncrementBodyNested<A> withNewPostIncrementBodyLike(PostIncrement item) {
    return new PostIncrementBodyNested(item);
  }

  public PostIncrementCompareNested<A> withNewPostIncrementCompare() {
    return new PostIncrementCompareNested(null);
  }

  public PostIncrementCompareNested<A> withNewPostIncrementCompareLike(PostIncrement item) {
    return new PostIncrementCompareNested(item);
  }

  public PreDecrementBodyNested<A> withNewPreDecrementBody() {
    return new PreDecrementBodyNested(null);
  }

  public PreDecrementBodyNested<A> withNewPreDecrementBodyLike(PreDecrement item) {
    return new PreDecrementBodyNested(item);
  }

  public PreDecrementCompareNested<A> withNewPreDecrementCompare() {
    return new PreDecrementCompareNested(null);
  }

  public PreDecrementCompareNested<A> withNewPreDecrementCompareLike(PreDecrement item) {
    return new PreDecrementCompareNested(item);
  }

  public PreIncrementBodyNested<A> withNewPreIncrementBody() {
    return new PreIncrementBodyNested(null);
  }

  public PreIncrementBodyNested<A> withNewPreIncrementBodyLike(PreIncrement item) {
    return new PreIncrementBodyNested(item);
  }

  public PreIncrementCompareNested<A> withNewPreIncrementCompare() {
    return new PreIncrementCompareNested(null);
  }

  public PreIncrementCompareNested<A> withNewPreIncrementCompareLike(PreIncrement item) {
    return new PreIncrementCompareNested(item);
  }

  public PropertyCompareNested<A> withNewPropertyCompare() {
    return new PropertyCompareNested(null);
  }

  public PropertyCompareNested<A> withNewPropertyCompareLike(Property item) {
    return new PropertyCompareNested(item);
  }

  public PropertyRefBodyNested<A> withNewPropertyRefBody() {
    return new PropertyRefBodyNested(null);
  }

  public PropertyRefBodyNested<A> withNewPropertyRefBodyLike(PropertyRef item) {
    return new PropertyRefBodyNested(item);
  }

  public PropertyRefCompareNested<A> withNewPropertyRefCompare() {
    return new PropertyRefCompareNested(null);
  }

  public PropertyRefCompareNested<A> withNewPropertyRefCompareLike(PropertyRef item) {
    return new PropertyRefCompareNested(item);
  }

  public ReturnBodyNested<A> withNewReturnBody() {
    return new ReturnBodyNested(null);
  }

  public A withNewReturnBody(Object object) {
    return (A) withBody(new Return(object));
  }

  public ReturnBodyNested<A> withNewReturnBodyLike(Return item) {
    return new ReturnBodyNested(item);
  }

  public ReturnDslThisStepBodyNested<A> withNewReturnDslThisStepBody() {
    return new ReturnDslThisStepBodyNested(null);
  }

  public ReturnDslThisStepBodyNested<A> withNewReturnDslThisStepBodyLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepBodyNested(item);
  }

  public ReturnDslVariableStepBodyNested<A> withNewReturnDslVariableStepBody() {
    return new ReturnDslVariableStepBodyNested(null);
  }

  public A withNewReturnDslVariableStepBody(String name) {
    return (A) withBody(new ReturnDslVariableStep(name));
  }

  public ReturnDslVariableStepBodyNested<A> withNewReturnDslVariableStepBodyLike(ReturnDslVariableStep item) {
    return new ReturnDslVariableStepBodyNested(item);
  }

  public RightShiftBodyNested<A> withNewRightShiftBody() {
    return new RightShiftBodyNested(null);
  }

  public A withNewRightShiftBody(Object left, Object right) {
    return (A) withBody(new RightShift(left, right));
  }

  public RightShiftBodyNested<A> withNewRightShiftBodyLike(RightShift item) {
    return new RightShiftBodyNested(item);
  }

  public RightShiftCompareNested<A> withNewRightShiftCompare() {
    return new RightShiftCompareNested(null);
  }

  public A withNewRightShiftCompare(Object left, Object right) {
    return (A) withCompare(new RightShift(left, right));
  }

  public RightShiftCompareNested<A> withNewRightShiftCompareLike(RightShift item) {
    return new RightShiftCompareNested(item);
  }

  public RightUnsignedShiftBodyNested<A> withNewRightUnsignedShiftBody() {
    return new RightUnsignedShiftBodyNested(null);
  }

  public A withNewRightUnsignedShiftBody(Object left, Object right) {
    return (A) withBody(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftBodyNested<A> withNewRightUnsignedShiftBodyLike(RightUnsignedShift item) {
    return new RightUnsignedShiftBodyNested(item);
  }

  public RightUnsignedShiftCompareNested<A> withNewRightUnsignedShiftCompare() {
    return new RightUnsignedShiftCompareNested(null);
  }

  public A withNewRightUnsignedShiftCompare(Object left, Object right) {
    return (A) withCompare(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftCompareNested<A> withNewRightUnsignedShiftCompareLike(RightUnsignedShift item) {
    return new RightUnsignedShiftCompareNested(item);
  }

  public StringStatementBodyNested<A> withNewStringStatementBody() {
    return new StringStatementBodyNested(null);
  }

  public A withNewStringStatementBody(String data) {
    return (A) withBody(new StringStatement(data));
  }

  public A withNewStringStatementBody(String data, Object[] parameters) {
    return (A) withBody(new StringStatement(data, parameters));
  }

  public StringStatementBodyNested<A> withNewStringStatementBodyLike(StringStatement item) {
    return new StringStatementBodyNested(item);
  }

  public SuperCompareNested<A> withNewSuperCompare() {
    return new SuperCompareNested(null);
  }

  public SuperCompareNested<A> withNewSuperCompareLike(Super item) {
    return new SuperCompareNested(item);
  }

  public SwitchBodyNested<A> withNewSwitchBody() {
    return new SwitchBodyNested(null);
  }

  public SwitchBodyNested<A> withNewSwitchBodyLike(Switch item) {
    return new SwitchBodyNested(item);
  }

  public SynchronizedBodyNested<A> withNewSynchronizedBody() {
    return new SynchronizedBodyNested(null);
  }

  public SynchronizedBodyNested<A> withNewSynchronizedBodyLike(Synchronized item) {
    return new SynchronizedBodyNested(item);
  }

  public TernaryCompareNested<A> withNewTernaryCompare() {
    return new TernaryCompareNested(null);
  }

  public TernaryCompareNested<A> withNewTernaryCompareLike(Ternary item) {
    return new TernaryCompareNested(item);
  }

  public ThisCompareNested<A> withNewThisCompare() {
    return new ThisCompareNested(null);
  }

  public ThisCompareNested<A> withNewThisCompareLike(This item) {
    return new ThisCompareNested(item);
  }

  public ThrowBodyNested<A> withNewThrowBody() {
    return new ThrowBodyNested(null);
  }

  public ThrowBodyNested<A> withNewThrowBodyLike(Throw item) {
    return new ThrowBodyNested(item);
  }

  public TryBodyNested<A> withNewTryBody() {
    return new TryBodyNested(null);
  }

  public TryBodyNested<A> withNewTryBodyLike(Try item) {
    return new TryBodyNested(item);
  }

  public ValueRefCompareNested<A> withNewValueRefCompare() {
    return new ValueRefCompareNested(null);
  }

  public A withNewValueRefCompare(Object value) {
    return (A) withCompare(new ValueRef(value));
  }

  public ValueRefCompareNested<A> withNewValueRefCompareLike(ValueRef item) {
    return new ValueRefCompareNested(item);
  }

  public WhileBodyNested<A> withNewWhileBody() {
    return new WhileBodyNested(null);
  }

  public WhileBodyNested<A> withNewWhileBodyLike(While item) {
    return new WhileBodyNested(item);
  }

  public XorBodyNested<A> withNewXorBody() {
    return new XorBodyNested(null);
  }

  public A withNewXorBody(Object left, Object right) {
    return (A) withBody(new Xor(left, right));
  }

  public XorBodyNested<A> withNewXorBodyLike(Xor item) {
    return new XorBodyNested(item);
  }

  public XorCompareNested<A> withNewXorCompare() {
    return new XorCompareNested(null);
  }

  public A withNewXorCompare(Object left, Object right) {
    return (A) withCompare(new Xor(left, right));
  }

  public XorCompareNested<A> withNewXorCompareLike(Xor item) {
    return new XorCompareNested(item);
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

  public A withUpdate(Expression... update) {
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

  public class AssignBodyNested<N> extends AssignFluent<AssignBodyNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignBodyNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endAssignBody() {
      return and();
    }

  }

  public class AssignCompareNested<N> extends AssignFluent<AssignCompareNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignCompareNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endAssignCompare() {
      return and();
    }

  }

  public class AssignInitNested<N> extends AssignFluent<AssignInitNested<N>> implements Nested<N> {

    AssignBuilder builder;
    int index;

    AssignInitNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endAssignInit() {
      return and();
    }

  }

  public class AssignUpdateNested<N> extends AssignFluent<AssignUpdateNested<N>> implements Nested<N> {

    AssignBuilder builder;
    int index;

    AssignUpdateNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endAssignUpdate() {
      return and();
    }

  }

  public class BinaryExpressionBodyNested<N> extends BinaryExpressionFluent<BinaryExpressionBodyNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionBodyNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBinaryExpressionBody() {
      return and();
    }

  }

  public class BinaryExpressionCompareNested<N> extends BinaryExpressionFluent<BinaryExpressionCompareNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionCompareNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endBinaryExpressionCompare() {
      return and();
    }

  }

  public class BinaryExpressionInitNested<N> extends BinaryExpressionFluent<BinaryExpressionInitNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;
    int index;

    BinaryExpressionInitNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endBinaryExpressionInit() {
      return and();
    }

  }

  public class BinaryExpressionUpdateNested<N> extends BinaryExpressionFluent<BinaryExpressionUpdateNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;
    int index;

    BinaryExpressionUpdateNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endBinaryExpressionUpdate() {
      return and();
    }

  }

  public class BitwiseAndBodyNested<N> extends BitwiseAndFluent<BitwiseAndBodyNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndBodyNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBitwiseAndBody() {
      return and();
    }

  }

  public class BitwiseAndCompareNested<N> extends BitwiseAndFluent<BitwiseAndCompareNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndCompareNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endBitwiseAndCompare() {
      return and();
    }

  }

  public class BitwiseAndInitNested<N> extends BitwiseAndFluent<BitwiseAndInitNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;
    int index;

    BitwiseAndInitNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endBitwiseAndInit() {
      return and();
    }

  }

  public class BitwiseAndUpdateNested<N> extends BitwiseAndFluent<BitwiseAndUpdateNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;
    int index;

    BitwiseAndUpdateNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endBitwiseAndUpdate() {
      return and();
    }

  }

  public class BitwiseOrBodyNested<N> extends BitwiseOrFluent<BitwiseOrBodyNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrBodyNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBitwiseOrBody() {
      return and();
    }

  }

  public class BitwiseOrCompareNested<N> extends BitwiseOrFluent<BitwiseOrCompareNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrCompareNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endBitwiseOrCompare() {
      return and();
    }

  }

  public class BitwiseOrInitNested<N> extends BitwiseOrFluent<BitwiseOrInitNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;
    int index;

    BitwiseOrInitNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endBitwiseOrInit() {
      return and();
    }

  }

  public class BitwiseOrUpdateNested<N> extends BitwiseOrFluent<BitwiseOrUpdateNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;
    int index;

    BitwiseOrUpdateNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endBitwiseOrUpdate() {
      return and();
    }

  }

  public class BlockBodyNested<N> extends BlockFluent<BlockBodyNested<N>> implements Nested<N> {

    BlockBuilder builder;

    BlockBodyNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBlockBody() {
      return and();
    }

  }

  public class BreakBodyNested<N> extends BreakFluent<BreakBodyNested<N>> implements Nested<N> {

    BreakBuilder builder;

    BreakBodyNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endBreakBody() {
      return and();
    }

  }

  public class CastCompareNested<N> extends CastFluent<CastCompareNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastCompareNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endCastCompare() {
      return and();
    }

  }

  public class CastInitNested<N> extends CastFluent<CastInitNested<N>> implements Nested<N> {

    CastBuilder builder;
    int index;

    CastInitNested(int index, Cast item) {
      this.index = index;
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endCastInit() {
      return and();
    }

  }

  public class CastUpdateNested<N> extends CastFluent<CastUpdateNested<N>> implements Nested<N> {

    CastBuilder builder;
    int index;

    CastUpdateNested(int index, Cast item) {
      this.index = index;
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endCastUpdate() {
      return and();
    }

  }

  public class ClassRefCompareNested<N> extends ClassRefFluent<ClassRefCompareNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefCompareNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endClassRefCompare() {
      return and();
    }

  }

  public class ClassRefInitNested<N> extends ClassRefFluent<ClassRefInitNested<N>> implements Nested<N> {

    ClassRefBuilder builder;
    int index;

    ClassRefInitNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endClassRefInit() {
      return and();
    }

  }

  public class ClassRefUpdateNested<N> extends ClassRefFluent<ClassRefUpdateNested<N>> implements Nested<N> {

    ClassRefBuilder builder;
    int index;

    ClassRefUpdateNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endClassRefUpdate() {
      return and();
    }

  }

  public class ConstructBodyNested<N> extends ConstructFluent<ConstructBodyNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructBodyNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endConstructBody() {
      return and();
    }

  }

  public class ConstructCompareNested<N> extends ConstructFluent<ConstructCompareNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructCompareNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endConstructCompare() {
      return and();
    }

  }

  public class ConstructInitNested<N> extends ConstructFluent<ConstructInitNested<N>> implements Nested<N> {

    ConstructBuilder builder;
    int index;

    ConstructInitNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endConstructInit() {
      return and();
    }

  }

  public class ConstructUpdateNested<N> extends ConstructFluent<ConstructUpdateNested<N>> implements Nested<N> {

    ConstructBuilder builder;
    int index;

    ConstructUpdateNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endConstructUpdate() {
      return and();
    }

  }

  public class ContextRefCompareNested<N> extends ContextRefFluent<ContextRefCompareNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefCompareNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endContextRefCompare() {
      return and();
    }

  }

  public class ContextRefInitNested<N> extends ContextRefFluent<ContextRefInitNested<N>> implements Nested<N> {

    ContextRefBuilder builder;
    int index;

    ContextRefInitNested(int index, ContextRef item) {
      this.index = index;
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endContextRefInit() {
      return and();
    }

  }

  public class ContextRefUpdateNested<N> extends ContextRefFluent<ContextRefUpdateNested<N>> implements Nested<N> {

    ContextRefBuilder builder;
    int index;

    ContextRefUpdateNested(int index, ContextRef item) {
      this.index = index;
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endContextRefUpdate() {
      return and();
    }

  }

  public class ContinueBodyNested<N> extends ContinueFluent<ContinueBodyNested<N>> implements Nested<N> {

    ContinueBuilder builder;

    ContinueBodyNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endContinueBody() {
      return and();
    }

  }

  public class DeclareBodyNested<N> extends DeclareFluent<DeclareBodyNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareBodyNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endDeclareBody() {
      return and();
    }

  }

  public class DeclareCompareNested<N> extends DeclareFluent<DeclareCompareNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareCompareNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endDeclareCompare() {
      return and();
    }

  }

  public class DeclareInitNested<N> extends DeclareFluent<DeclareInitNested<N>> implements Nested<N> {

    DeclareBuilder builder;
    int index;

    DeclareInitNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endDeclareInit() {
      return and();
    }

  }

  public class DeclareUpdateNested<N> extends DeclareFluent<DeclareUpdateNested<N>> implements Nested<N> {

    DeclareBuilder builder;
    int index;

    DeclareUpdateNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endDeclareUpdate() {
      return and();
    }

  }

  public class DivideBodyNested<N> extends DivideFluent<DivideBodyNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideBodyNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endDivideBody() {
      return and();
    }

  }

  public class DivideCompareNested<N> extends DivideFluent<DivideCompareNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideCompareNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endDivideCompare() {
      return and();
    }

  }

  public class DivideInitNested<N> extends DivideFluent<DivideInitNested<N>> implements Nested<N> {

    DivideBuilder builder;
    int index;

    DivideInitNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endDivideInit() {
      return and();
    }

  }

  public class DivideUpdateNested<N> extends DivideFluent<DivideUpdateNested<N>> implements Nested<N> {

    DivideBuilder builder;
    int index;

    DivideUpdateNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endDivideUpdate() {
      return and();
    }

  }

  public class DoBodyNested<N> extends DoFluent<DoBodyNested<N>> implements Nested<N> {

    DoBuilder builder;

    DoBodyNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endDoBody() {
      return and();
    }

  }

  public class DotClassCompareNested<N> extends DotClassFluent<DotClassCompareNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassCompareNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endDotClassCompare() {
      return and();
    }

  }

  public class DotClassInitNested<N> extends DotClassFluent<DotClassInitNested<N>> implements Nested<N> {

    DotClassBuilder builder;
    int index;

    DotClassInitNested(int index, DotClass item) {
      this.index = index;
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endDotClassInit() {
      return and();
    }

  }

  public class DotClassUpdateNested<N> extends DotClassFluent<DotClassUpdateNested<N>> implements Nested<N> {

    DotClassBuilder builder;
    int index;

    DotClassUpdateNested(int index, DotClass item) {
      this.index = index;
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endDotClassUpdate() {
      return and();
    }

  }

  public class EmptyBodyNested<N> extends EmptyFluent<EmptyBodyNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyBodyNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endEmptyBody() {
      return and();
    }

  }

  public class EmptyCompareNested<N> extends EmptyFluent<EmptyCompareNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyCompareNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endEmptyCompare() {
      return and();
    }

  }

  public class EmptyInitNested<N> extends EmptyFluent<EmptyInitNested<N>> implements Nested<N> {

    EmptyBuilder builder;
    int index;

    EmptyInitNested(int index, Empty item) {
      this.index = index;
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endEmptyInit() {
      return and();
    }

  }

  public class EmptyUpdateNested<N> extends EmptyFluent<EmptyUpdateNested<N>> implements Nested<N> {

    EmptyBuilder builder;
    int index;

    EmptyUpdateNested(int index, Empty item) {
      this.index = index;
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endEmptyUpdate() {
      return and();
    }

  }

  public class EnclosedCompareNested<N> extends EnclosedFluent<EnclosedCompareNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedCompareNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endEnclosedCompare() {
      return and();
    }

  }

  public class EnclosedInitNested<N> extends EnclosedFluent<EnclosedInitNested<N>> implements Nested<N> {

    EnclosedBuilder builder;
    int index;

    EnclosedInitNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endEnclosedInit() {
      return and();
    }

  }

  public class EnclosedUpdateNested<N> extends EnclosedFluent<EnclosedUpdateNested<N>> implements Nested<N> {

    EnclosedBuilder builder;
    int index;

    EnclosedUpdateNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endEnclosedUpdate() {
      return and();
    }

  }

  public class EqualsBodyNested<N> extends EqualsFluent<EqualsBodyNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsBodyNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endEqualsBody() {
      return and();
    }

  }

  public class EqualsCompareNested<N> extends EqualsFluent<EqualsCompareNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsCompareNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endEqualsCompare() {
      return and();
    }

  }

  public class EqualsInitNested<N> extends EqualsFluent<EqualsInitNested<N>> implements Nested<N> {

    EqualsBuilder builder;
    int index;

    EqualsInitNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endEqualsInit() {
      return and();
    }

  }

  public class EqualsUpdateNested<N> extends EqualsFluent<EqualsUpdateNested<N>> implements Nested<N> {

    EqualsBuilder builder;
    int index;

    EqualsUpdateNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endEqualsUpdate() {
      return and();
    }

  }

  public class ForBodyNested<N> extends ForFluent<ForBodyNested<N>> implements Nested<N> {

    ForBuilder builder;

    ForBodyNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endForBody() {
      return and();
    }

  }

  public class ForeachBodyNested<N> extends ForeachFluent<ForeachBodyNested<N>> implements Nested<N> {

    ForeachBuilder builder;

    ForeachBodyNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endForeachBody() {
      return and();
    }

  }

  public class GreaterThanBodyNested<N> extends GreaterThanFluent<GreaterThanBodyNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanBodyNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endGreaterThanBody() {
      return and();
    }

  }

  public class GreaterThanCompareNested<N> extends GreaterThanFluent<GreaterThanCompareNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanCompareNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endGreaterThanCompare() {
      return and();
    }

  }

  public class GreaterThanInitNested<N> extends GreaterThanFluent<GreaterThanInitNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;
    int index;

    GreaterThanInitNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endGreaterThanInit() {
      return and();
    }

  }

  public class GreaterThanOrEqualBodyNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualBodyNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualBodyNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endGreaterThanOrEqualBody() {
      return and();
    }

  }

  public class GreaterThanOrEqualCompareNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualCompareNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualCompareNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endGreaterThanOrEqualCompare() {
      return and();
    }

  }

  public class GreaterThanOrEqualInitNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualInitNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;
    int index;

    GreaterThanOrEqualInitNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endGreaterThanOrEqualInit() {
      return and();
    }

  }

  public class GreaterThanOrEqualUpdateNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualUpdateNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;
    int index;

    GreaterThanOrEqualUpdateNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endGreaterThanOrEqualUpdate() {
      return and();
    }

  }

  public class GreaterThanUpdateNested<N> extends GreaterThanFluent<GreaterThanUpdateNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;
    int index;

    GreaterThanUpdateNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endGreaterThanUpdate() {
      return and();
    }

  }

  public class IfBodyNested<N> extends IfFluent<IfBodyNested<N>> implements Nested<N> {

    IfBuilder builder;

    IfBodyNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endIfBody() {
      return and();
    }

  }

  public class IfDslThenStepBodyNested<N> extends IfDslThenStepFluent<IfDslThenStepBodyNested<N>> implements Nested<N> {

    IfDslThenStepBuilder builder;

    IfDslThenStepBodyNested(IfDslThenStep item) {
      this.builder = new IfDslThenStepBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endIfDslThenStepBody() {
      return and();
    }

  }

  public class IndexCompareNested<N> extends IndexFluent<IndexCompareNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexCompareNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endIndexCompare() {
      return and();
    }

  }

  public class IndexInitNested<N> extends IndexFluent<IndexInitNested<N>> implements Nested<N> {

    IndexBuilder builder;
    int index;

    IndexInitNested(int index, Index item) {
      this.index = index;
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endIndexInit() {
      return and();
    }

  }

  public class IndexUpdateNested<N> extends IndexFluent<IndexUpdateNested<N>> implements Nested<N> {

    IndexBuilder builder;
    int index;

    IndexUpdateNested(int index, Index item) {
      this.index = index;
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endIndexUpdate() {
      return and();
    }

  }

  public class InstanceOfCompareNested<N> extends InstanceOfFluent<InstanceOfCompareNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfCompareNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endInstanceOfCompare() {
      return and();
    }

  }

  public class InstanceOfInitNested<N> extends InstanceOfFluent<InstanceOfInitNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;
    int index;

    InstanceOfInitNested(int index, InstanceOf item) {
      this.index = index;
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endInstanceOfInit() {
      return and();
    }

  }

  public class InstanceOfUpdateNested<N> extends InstanceOfFluent<InstanceOfUpdateNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;
    int index;

    InstanceOfUpdateNested(int index, InstanceOf item) {
      this.index = index;
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endInstanceOfUpdate() {
      return and();
    }

  }

  public class InverseCompareNested<N> extends InverseFluent<InverseCompareNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseCompareNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endInverseCompare() {
      return and();
    }

  }

  public class InverseInitNested<N> extends InverseFluent<InverseInitNested<N>> implements Nested<N> {

    InverseBuilder builder;
    int index;

    InverseInitNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endInverseInit() {
      return and();
    }

  }

  public class InverseUpdateNested<N> extends InverseFluent<InverseUpdateNested<N>> implements Nested<N> {

    InverseBuilder builder;
    int index;

    InverseUpdateNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endInverseUpdate() {
      return and();
    }

  }

  public class LambdaBodyNested<N> extends LambdaFluent<LambdaBodyNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaBodyNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endLambdaBody() {
      return and();
    }

  }

  public class LambdaCompareNested<N> extends LambdaFluent<LambdaCompareNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaCompareNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLambdaCompare() {
      return and();
    }

  }

  public class LambdaInitNested<N> extends LambdaFluent<LambdaInitNested<N>> implements Nested<N> {

    LambdaBuilder builder;
    int index;

    LambdaInitNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLambdaInit() {
      return and();
    }

  }

  public class LambdaUpdateNested<N> extends LambdaFluent<LambdaUpdateNested<N>> implements Nested<N> {

    LambdaBuilder builder;
    int index;

    LambdaUpdateNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLambdaUpdate() {
      return and();
    }

  }

  public class LeftShiftBodyNested<N> extends LeftShiftFluent<LeftShiftBodyNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftBodyNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endLeftShiftBody() {
      return and();
    }

  }

  public class LeftShiftCompareNested<N> extends LeftShiftFluent<LeftShiftCompareNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftCompareNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLeftShiftCompare() {
      return and();
    }

  }

  public class LeftShiftInitNested<N> extends LeftShiftFluent<LeftShiftInitNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;
    int index;

    LeftShiftInitNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLeftShiftInit() {
      return and();
    }

  }

  public class LeftShiftUpdateNested<N> extends LeftShiftFluent<LeftShiftUpdateNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;
    int index;

    LeftShiftUpdateNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLeftShiftUpdate() {
      return and();
    }

  }

  public class LessThanBodyNested<N> extends LessThanFluent<LessThanBodyNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanBodyNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endLessThanBody() {
      return and();
    }

  }

  public class LessThanCompareNested<N> extends LessThanFluent<LessThanCompareNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanCompareNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLessThanCompare() {
      return and();
    }

  }

  public class LessThanInitNested<N> extends LessThanFluent<LessThanInitNested<N>> implements Nested<N> {

    LessThanBuilder builder;
    int index;

    LessThanInitNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLessThanInit() {
      return and();
    }

  }

  public class LessThanOrEqualBodyNested<N> extends LessThanOrEqualFluent<LessThanOrEqualBodyNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualBodyNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endLessThanOrEqualBody() {
      return and();
    }

  }

  public class LessThanOrEqualCompareNested<N> extends LessThanOrEqualFluent<LessThanOrEqualCompareNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualCompareNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLessThanOrEqualCompare() {
      return and();
    }

  }

  public class LessThanOrEqualInitNested<N> extends LessThanOrEqualFluent<LessThanOrEqualInitNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;
    int index;

    LessThanOrEqualInitNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLessThanOrEqualInit() {
      return and();
    }

  }

  public class LessThanOrEqualUpdateNested<N> extends LessThanOrEqualFluent<LessThanOrEqualUpdateNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;
    int index;

    LessThanOrEqualUpdateNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLessThanOrEqualUpdate() {
      return and();
    }

  }

  public class LessThanUpdateNested<N> extends LessThanFluent<LessThanUpdateNested<N>> implements Nested<N> {

    LessThanBuilder builder;
    int index;

    LessThanUpdateNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLessThanUpdate() {
      return and();
    }

  }

  public class LogicalAndBodyNested<N> extends LogicalAndFluent<LogicalAndBodyNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndBodyNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endLogicalAndBody() {
      return and();
    }

  }

  public class LogicalAndCompareNested<N> extends LogicalAndFluent<LogicalAndCompareNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndCompareNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLogicalAndCompare() {
      return and();
    }

  }

  public class LogicalAndInitNested<N> extends LogicalAndFluent<LogicalAndInitNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;
    int index;

    LogicalAndInitNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLogicalAndInit() {
      return and();
    }

  }

  public class LogicalAndUpdateNested<N> extends LogicalAndFluent<LogicalAndUpdateNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;
    int index;

    LogicalAndUpdateNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLogicalAndUpdate() {
      return and();
    }

  }

  public class LogicalOrBodyNested<N> extends LogicalOrFluent<LogicalOrBodyNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrBodyNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endLogicalOrBody() {
      return and();
    }

  }

  public class LogicalOrCompareNested<N> extends LogicalOrFluent<LogicalOrCompareNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrCompareNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endLogicalOrCompare() {
      return and();
    }

  }

  public class LogicalOrInitNested<N> extends LogicalOrFluent<LogicalOrInitNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;
    int index;

    LogicalOrInitNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endLogicalOrInit() {
      return and();
    }

  }

  public class LogicalOrUpdateNested<N> extends LogicalOrFluent<LogicalOrUpdateNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;
    int index;

    LogicalOrUpdateNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endLogicalOrUpdate() {
      return and();
    }

  }

  public class MethodCallBodyNested<N> extends MethodCallFluent<MethodCallBodyNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallBodyNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endMethodCallBody() {
      return and();
    }

  }

  public class MethodCallCompareNested<N> extends MethodCallFluent<MethodCallCompareNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallCompareNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endMethodCallCompare() {
      return and();
    }

  }

  public class MethodCallInitNested<N> extends MethodCallFluent<MethodCallInitNested<N>> implements Nested<N> {

    MethodCallBuilder builder;
    int index;

    MethodCallInitNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endMethodCallInit() {
      return and();
    }

  }

  public class MethodCallUpdateNested<N> extends MethodCallFluent<MethodCallUpdateNested<N>> implements Nested<N> {

    MethodCallBuilder builder;
    int index;

    MethodCallUpdateNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endMethodCallUpdate() {
      return and();
    }

  }

  public class MinusBodyNested<N> extends MinusFluent<MinusBodyNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusBodyNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endMinusBody() {
      return and();
    }

  }

  public class MinusCompareNested<N> extends MinusFluent<MinusCompareNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusCompareNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endMinusCompare() {
      return and();
    }

  }

  public class MinusInitNested<N> extends MinusFluent<MinusInitNested<N>> implements Nested<N> {

    MinusBuilder builder;
    int index;

    MinusInitNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endMinusInit() {
      return and();
    }

  }

  public class MinusUpdateNested<N> extends MinusFluent<MinusUpdateNested<N>> implements Nested<N> {

    MinusBuilder builder;
    int index;

    MinusUpdateNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endMinusUpdate() {
      return and();
    }

  }

  public class ModuloBodyNested<N> extends ModuloFluent<ModuloBodyNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloBodyNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endModuloBody() {
      return and();
    }

  }

  public class ModuloCompareNested<N> extends ModuloFluent<ModuloCompareNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloCompareNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endModuloCompare() {
      return and();
    }

  }

  public class ModuloInitNested<N> extends ModuloFluent<ModuloInitNested<N>> implements Nested<N> {

    ModuloBuilder builder;
    int index;

    ModuloInitNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endModuloInit() {
      return and();
    }

  }

  public class ModuloUpdateNested<N> extends ModuloFluent<ModuloUpdateNested<N>> implements Nested<N> {

    ModuloBuilder builder;
    int index;

    ModuloUpdateNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endModuloUpdate() {
      return and();
    }

  }

  public class MultiplyBodyNested<N> extends MultiplyFluent<MultiplyBodyNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyBodyNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endMultiplyBody() {
      return and();
    }

  }

  public class MultiplyCompareNested<N> extends MultiplyFluent<MultiplyCompareNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyCompareNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endMultiplyCompare() {
      return and();
    }

  }

  public class MultiplyInitNested<N> extends MultiplyFluent<MultiplyInitNested<N>> implements Nested<N> {

    MultiplyBuilder builder;
    int index;

    MultiplyInitNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endMultiplyInit() {
      return and();
    }

  }

  public class MultiplyUpdateNested<N> extends MultiplyFluent<MultiplyUpdateNested<N>> implements Nested<N> {

    MultiplyBuilder builder;
    int index;

    MultiplyUpdateNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endMultiplyUpdate() {
      return and();
    }

  }

  public class NegativeCompareNested<N> extends NegativeFluent<NegativeCompareNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeCompareNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNegativeCompare() {
      return and();
    }

  }

  public class NegativeInitNested<N> extends NegativeFluent<NegativeInitNested<N>> implements Nested<N> {

    NegativeBuilder builder;
    int index;

    NegativeInitNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNegativeInit() {
      return and();
    }

  }

  public class NegativeUpdateNested<N> extends NegativeFluent<NegativeUpdateNested<N>> implements Nested<N> {

    NegativeBuilder builder;
    int index;

    NegativeUpdateNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNegativeUpdate() {
      return and();
    }

  }

  public class NewArrayCompareNested<N> extends NewArrayFluent<NewArrayCompareNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayCompareNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNewArrayCompare() {
      return and();
    }

  }

  public class NewArrayInitNested<N> extends NewArrayFluent<NewArrayInitNested<N>> implements Nested<N> {

    NewArrayBuilder builder;
    int index;

    NewArrayInitNested(int index, NewArray item) {
      this.index = index;
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNewArrayInit() {
      return and();
    }

  }

  public class NewArrayUpdateNested<N> extends NewArrayFluent<NewArrayUpdateNested<N>> implements Nested<N> {

    NewArrayBuilder builder;
    int index;

    NewArrayUpdateNested(int index, NewArray item) {
      this.index = index;
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNewArrayUpdate() {
      return and();
    }

  }

  public class NotCompareNested<N> extends NotFluent<NotCompareNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotCompareNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNotCompare() {
      return and();
    }

  }

  public class NotEqualsBodyNested<N> extends NotEqualsFluent<NotEqualsBodyNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsBodyNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endNotEqualsBody() {
      return and();
    }

  }

  public class NotEqualsCompareNested<N> extends NotEqualsFluent<NotEqualsCompareNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsCompareNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endNotEqualsCompare() {
      return and();
    }

  }

  public class NotEqualsInitNested<N> extends NotEqualsFluent<NotEqualsInitNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;
    int index;

    NotEqualsInitNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNotEqualsInit() {
      return and();
    }

  }

  public class NotEqualsUpdateNested<N> extends NotEqualsFluent<NotEqualsUpdateNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;
    int index;

    NotEqualsUpdateNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNotEqualsUpdate() {
      return and();
    }

  }

  public class NotInitNested<N> extends NotFluent<NotInitNested<N>> implements Nested<N> {

    NotBuilder builder;
    int index;

    NotInitNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endNotInit() {
      return and();
    }

  }

  public class NotUpdateNested<N> extends NotFluent<NotUpdateNested<N>> implements Nested<N> {

    NotBuilder builder;
    int index;

    NotUpdateNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endNotUpdate() {
      return and();
    }

  }

  public class PlusBodyNested<N> extends PlusFluent<PlusBodyNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusBodyNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endPlusBody() {
      return and();
    }

  }

  public class PlusCompareNested<N> extends PlusFluent<PlusCompareNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusCompareNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPlusCompare() {
      return and();
    }

  }

  public class PlusInitNested<N> extends PlusFluent<PlusInitNested<N>> implements Nested<N> {

    PlusBuilder builder;
    int index;

    PlusInitNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPlusInit() {
      return and();
    }

  }

  public class PlusUpdateNested<N> extends PlusFluent<PlusUpdateNested<N>> implements Nested<N> {

    PlusBuilder builder;
    int index;

    PlusUpdateNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPlusUpdate() {
      return and();
    }

  }

  public class PositiveCompareNested<N> extends PositiveFluent<PositiveCompareNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveCompareNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPositiveCompare() {
      return and();
    }

  }

  public class PositiveInitNested<N> extends PositiveFluent<PositiveInitNested<N>> implements Nested<N> {

    PositiveBuilder builder;
    int index;

    PositiveInitNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPositiveInit() {
      return and();
    }

  }

  public class PositiveUpdateNested<N> extends PositiveFluent<PositiveUpdateNested<N>> implements Nested<N> {

    PositiveBuilder builder;
    int index;

    PositiveUpdateNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPositiveUpdate() {
      return and();
    }

  }

  public class PostDecrementBodyNested<N> extends PostDecrementFluent<PostDecrementBodyNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementBodyNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endPostDecrementBody() {
      return and();
    }

  }

  public class PostDecrementCompareNested<N> extends PostDecrementFluent<PostDecrementCompareNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementCompareNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPostDecrementCompare() {
      return and();
    }

  }

  public class PostDecrementInitNested<N> extends PostDecrementFluent<PostDecrementInitNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;
    int index;

    PostDecrementInitNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPostDecrementInit() {
      return and();
    }

  }

  public class PostDecrementUpdateNested<N> extends PostDecrementFluent<PostDecrementUpdateNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;
    int index;

    PostDecrementUpdateNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPostDecrementUpdate() {
      return and();
    }

  }

  public class PostIncrementBodyNested<N> extends PostIncrementFluent<PostIncrementBodyNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementBodyNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endPostIncrementBody() {
      return and();
    }

  }

  public class PostIncrementCompareNested<N> extends PostIncrementFluent<PostIncrementCompareNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementCompareNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPostIncrementCompare() {
      return and();
    }

  }

  public class PostIncrementInitNested<N> extends PostIncrementFluent<PostIncrementInitNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;
    int index;

    PostIncrementInitNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPostIncrementInit() {
      return and();
    }

  }

  public class PostIncrementUpdateNested<N> extends PostIncrementFluent<PostIncrementUpdateNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;
    int index;

    PostIncrementUpdateNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPostIncrementUpdate() {
      return and();
    }

  }

  public class PreDecrementBodyNested<N> extends PreDecrementFluent<PreDecrementBodyNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementBodyNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endPreDecrementBody() {
      return and();
    }

  }

  public class PreDecrementCompareNested<N> extends PreDecrementFluent<PreDecrementCompareNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementCompareNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPreDecrementCompare() {
      return and();
    }

  }

  public class PreDecrementInitNested<N> extends PreDecrementFluent<PreDecrementInitNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;
    int index;

    PreDecrementInitNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPreDecrementInit() {
      return and();
    }

  }

  public class PreDecrementUpdateNested<N> extends PreDecrementFluent<PreDecrementUpdateNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;
    int index;

    PreDecrementUpdateNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPreDecrementUpdate() {
      return and();
    }

  }

  public class PreIncrementBodyNested<N> extends PreIncrementFluent<PreIncrementBodyNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementBodyNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endPreIncrementBody() {
      return and();
    }

  }

  public class PreIncrementCompareNested<N> extends PreIncrementFluent<PreIncrementCompareNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementCompareNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPreIncrementCompare() {
      return and();
    }

  }

  public class PreIncrementInitNested<N> extends PreIncrementFluent<PreIncrementInitNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;
    int index;

    PreIncrementInitNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPreIncrementInit() {
      return and();
    }

  }

  public class PreIncrementUpdateNested<N> extends PreIncrementFluent<PreIncrementUpdateNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;
    int index;

    PreIncrementUpdateNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPreIncrementUpdate() {
      return and();
    }

  }

  public class PropertyCompareNested<N> extends PropertyFluent<PropertyCompareNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyCompareNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPropertyCompare() {
      return and();
    }

  }

  public class PropertyInitNested<N> extends PropertyFluent<PropertyInitNested<N>> implements Nested<N> {

    PropertyBuilder builder;
    int index;

    PropertyInitNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPropertyInit() {
      return and();
    }

  }

  public class PropertyRefBodyNested<N> extends PropertyRefFluent<PropertyRefBodyNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefBodyNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endPropertyRefBody() {
      return and();
    }

  }

  public class PropertyRefCompareNested<N> extends PropertyRefFluent<PropertyRefCompareNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefCompareNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endPropertyRefCompare() {
      return and();
    }

  }

  public class PropertyRefInitNested<N> extends PropertyRefFluent<PropertyRefInitNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;
    int index;

    PropertyRefInitNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endPropertyRefInit() {
      return and();
    }

  }

  public class PropertyRefUpdateNested<N> extends PropertyRefFluent<PropertyRefUpdateNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;
    int index;

    PropertyRefUpdateNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPropertyRefUpdate() {
      return and();
    }

  }

  public class PropertyUpdateNested<N> extends PropertyFluent<PropertyUpdateNested<N>> implements Nested<N> {

    PropertyBuilder builder;
    int index;

    PropertyUpdateNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endPropertyUpdate() {
      return and();
    }

  }

  public class ReturnBodyNested<N> extends ReturnFluent<ReturnBodyNested<N>> implements Nested<N> {

    ReturnBuilder builder;

    ReturnBodyNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endReturnBody() {
      return and();
    }

  }

  public class ReturnDslThisStepBodyNested<N> extends ReturnDslThisStepFluent<ReturnDslThisStepBodyNested<N>>
      implements Nested<N> {

    ReturnDslThisStepBuilder builder;

    ReturnDslThisStepBodyNested(ReturnDslThisStep item) {
      this.builder = new ReturnDslThisStepBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endReturnDslThisStepBody() {
      return and();
    }

  }

  public class ReturnDslVariableStepBodyNested<N> extends ReturnDslVariableStepFluent<ReturnDslVariableStepBodyNested<N>>
      implements Nested<N> {

    ReturnDslVariableStepBuilder builder;

    ReturnDslVariableStepBodyNested(ReturnDslVariableStep item) {
      this.builder = new ReturnDslVariableStepBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endReturnDslVariableStepBody() {
      return and();
    }

  }

  public class RightShiftBodyNested<N> extends RightShiftFluent<RightShiftBodyNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftBodyNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endRightShiftBody() {
      return and();
    }

  }

  public class RightShiftCompareNested<N> extends RightShiftFluent<RightShiftCompareNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftCompareNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endRightShiftCompare() {
      return and();
    }

  }

  public class RightShiftInitNested<N> extends RightShiftFluent<RightShiftInitNested<N>> implements Nested<N> {

    RightShiftBuilder builder;
    int index;

    RightShiftInitNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endRightShiftInit() {
      return and();
    }

  }

  public class RightShiftUpdateNested<N> extends RightShiftFluent<RightShiftUpdateNested<N>> implements Nested<N> {

    RightShiftBuilder builder;
    int index;

    RightShiftUpdateNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endRightShiftUpdate() {
      return and();
    }

  }

  public class RightUnsignedShiftBodyNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftBodyNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftBodyNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endRightUnsignedShiftBody() {
      return and();
    }

  }

  public class RightUnsignedShiftCompareNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftCompareNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftCompareNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endRightUnsignedShiftCompare() {
      return and();
    }

  }

  public class RightUnsignedShiftInitNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftInitNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;
    int index;

    RightUnsignedShiftInitNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endRightUnsignedShiftInit() {
      return and();
    }

  }

  public class RightUnsignedShiftUpdateNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftUpdateNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;
    int index;

    RightUnsignedShiftUpdateNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endRightUnsignedShiftUpdate() {
      return and();
    }

  }

  public class StringStatementBodyNested<N> extends StringStatementFluent<StringStatementBodyNested<N>> implements Nested<N> {

    StringStatementBuilder builder;

    StringStatementBodyNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endStringStatementBody() {
      return and();
    }

  }

  public class SuperCompareNested<N> extends SuperFluent<SuperCompareNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperCompareNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endSuperCompare() {
      return and();
    }

  }

  public class SuperInitNested<N> extends SuperFluent<SuperInitNested<N>> implements Nested<N> {

    SuperBuilder builder;
    int index;

    SuperInitNested(int index, Super item) {
      this.index = index;
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endSuperInit() {
      return and();
    }

  }

  public class SuperUpdateNested<N> extends SuperFluent<SuperUpdateNested<N>> implements Nested<N> {

    SuperBuilder builder;
    int index;

    SuperUpdateNested(int index, Super item) {
      this.index = index;
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endSuperUpdate() {
      return and();
    }

  }

  public class SwitchBodyNested<N> extends SwitchFluent<SwitchBodyNested<N>> implements Nested<N> {

    SwitchBuilder builder;

    SwitchBodyNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endSwitchBody() {
      return and();
    }

  }

  public class SynchronizedBodyNested<N> extends SynchronizedFluent<SynchronizedBodyNested<N>> implements Nested<N> {

    SynchronizedBuilder builder;

    SynchronizedBodyNested(Synchronized item) {
      this.builder = new SynchronizedBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endSynchronizedBody() {
      return and();
    }

  }

  public class TernaryCompareNested<N> extends TernaryFluent<TernaryCompareNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryCompareNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endTernaryCompare() {
      return and();
    }

  }

  public class TernaryInitNested<N> extends TernaryFluent<TernaryInitNested<N>> implements Nested<N> {

    TernaryBuilder builder;
    int index;

    TernaryInitNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endTernaryInit() {
      return and();
    }

  }

  public class TernaryUpdateNested<N> extends TernaryFluent<TernaryUpdateNested<N>> implements Nested<N> {

    TernaryBuilder builder;
    int index;

    TernaryUpdateNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endTernaryUpdate() {
      return and();
    }

  }

  public class ThisCompareNested<N> extends ThisFluent<ThisCompareNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisCompareNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endThisCompare() {
      return and();
    }

  }

  public class ThisInitNested<N> extends ThisFluent<ThisInitNested<N>> implements Nested<N> {

    ThisBuilder builder;
    int index;

    ThisInitNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endThisInit() {
      return and();
    }

  }

  public class ThisUpdateNested<N> extends ThisFluent<ThisUpdateNested<N>> implements Nested<N> {

    ThisBuilder builder;
    int index;

    ThisUpdateNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endThisUpdate() {
      return and();
    }

  }

  public class ThrowBodyNested<N> extends ThrowFluent<ThrowBodyNested<N>> implements Nested<N> {

    ThrowBuilder builder;

    ThrowBodyNested(Throw item) {
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endThrowBody() {
      return and();
    }

  }

  public class TryBodyNested<N> extends TryFluent<TryBodyNested<N>> implements Nested<N> {

    TryBuilder builder;

    TryBodyNested(Try item) {
      this.builder = new TryBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endTryBody() {
      return and();
    }

  }

  public class ValueRefCompareNested<N> extends ValueRefFluent<ValueRefCompareNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefCompareNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endValueRefCompare() {
      return and();
    }

  }

  public class ValueRefInitNested<N> extends ValueRefFluent<ValueRefInitNested<N>> implements Nested<N> {

    ValueRefBuilder builder;
    int index;

    ValueRefInitNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endValueRefInit() {
      return and();
    }

  }

  public class ValueRefUpdateNested<N> extends ValueRefFluent<ValueRefUpdateNested<N>> implements Nested<N> {

    ValueRefBuilder builder;
    int index;

    ValueRefUpdateNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endValueRefUpdate() {
      return and();
    }

  }

  public class WhileBodyNested<N> extends WhileFluent<WhileBodyNested<N>> implements Nested<N> {

    WhileBuilder builder;

    WhileBodyNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endWhileBody() {
      return and();
    }

  }

  public class XorBodyNested<N> extends XorFluent<XorBodyNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorBodyNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withBody(builder.build());
    }

    public N endXorBody() {
      return and();
    }

  }

  public class XorCompareNested<N> extends XorFluent<XorCompareNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorCompareNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.withCompare(builder.build());
    }

    public N endXorCompare() {
      return and();
    }

  }

  public class XorInitNested<N> extends XorFluent<XorInitNested<N>> implements Nested<N> {

    XorBuilder builder;
    int index;

    XorInitNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToInit(index, builder.build());
    }

    public N endXorInit() {
      return and();
    }

  }

  public class XorUpdateNested<N> extends XorFluent<XorUpdateNested<N>> implements Nested<N> {

    XorBuilder builder;
    int index;

    XorUpdateNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ForFluent.this.setToUpdate(index, builder.build());
    }

    public N endXorUpdate() {
      return and();
    }

  }
}
