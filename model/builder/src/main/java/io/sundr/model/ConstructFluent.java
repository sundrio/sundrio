package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ConstructFluent<A extends io.sundr.model.ConstructFluent<A>> extends BaseFluent<A> {

  private ArrayList<VisitableBuilder<? extends Expression, ?>> arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
  private ArrayList<VisitableBuilder<? extends TypeRef, ?>> parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
  private ClassRefBuilder type;

  public ConstructFluent() {
  }

  public ConstructFluent(Construct instance) {
    this.copyInstance(instance);
  }

  public A addAllToArguments(Collection<Expression> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToParameters(Collection<TypeRef> items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public AssignArgumentsNested<A> addNewAssignArgument() {
    return new AssignArgumentsNested(-1, null);
  }

  public AssignArgumentsNested<A> addNewAssignArgumentLike(Assign item) {
    return new AssignArgumentsNested(-1, item);
  }

  public BinaryExpressionArgumentsNested<A> addNewBinaryExpressionArgument() {
    return new BinaryExpressionArgumentsNested(-1, null);
  }

  public BinaryExpressionArgumentsNested<A> addNewBinaryExpressionArgumentLike(BinaryExpression item) {
    return new BinaryExpressionArgumentsNested(-1, item);
  }

  public BitwiseAndArgumentsNested<A> addNewBitwiseAndArgument() {
    return new BitwiseAndArgumentsNested(-1, null);
  }

  public A addNewBitwiseAndArgument(Object left, Object right) {
    return (A) this.addToArguments(new BitwiseAnd(left, right));
  }

  public BitwiseAndArgumentsNested<A> addNewBitwiseAndArgumentLike(BitwiseAnd item) {
    return new BitwiseAndArgumentsNested(-1, item);
  }

  public BitwiseOrArgumentsNested<A> addNewBitwiseOrArgument() {
    return new BitwiseOrArgumentsNested(-1, null);
  }

  public A addNewBitwiseOrArgument(Object left, Object right) {
    return (A) this.addToArguments(new BitwiseOr(left, right));
  }

  public BitwiseOrArgumentsNested<A> addNewBitwiseOrArgumentLike(BitwiseOr item) {
    return new BitwiseOrArgumentsNested(-1, item);
  }

  public CastArgumentsNested<A> addNewCastArgument() {
    return new CastArgumentsNested(-1, null);
  }

  public CastArgumentsNested<A> addNewCastArgumentLike(Cast item) {
    return new CastArgumentsNested(-1, item);
  }

  public ClassRefArgumentsNested<A> addNewClassRefArgument() {
    return new ClassRefArgumentsNested(-1, null);
  }

  public ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item) {
    return new ClassRefArgumentsNested(-1, item);
  }

  public ClassRefParametersNested<A> addNewClassRefParameter() {
    return new ClassRefParametersNested(-1, null);
  }

  public ClassRefParametersNested<A> addNewClassRefParameterLike(ClassRef item) {
    return new ClassRefParametersNested(-1, item);
  }

  public ConstructArgumentsNested<A> addNewConstructArgument() {
    return new ConstructArgumentsNested(-1, null);
  }

  public ConstructArgumentsNested<A> addNewConstructArgumentLike(Construct item) {
    return new ConstructArgumentsNested(-1, item);
  }

  public ContextRefArgumentsNested<A> addNewContextRefArgument() {
    return new ContextRefArgumentsNested(-1, null);
  }

  public A addNewContextRefArgument(String name) {
    return (A) this.addToArguments(new ContextRef(name));
  }

  public ContextRefArgumentsNested<A> addNewContextRefArgumentLike(ContextRef item) {
    return new ContextRefArgumentsNested(-1, item);
  }

  public DeclareArgumentsNested<A> addNewDeclareArgument() {
    return new DeclareArgumentsNested(-1, null);
  }

  public A addNewDeclareArgument(Class type, String name) {
    return (A) this.addToArguments(new Declare(type, name));
  }

  public A addNewDeclareArgument(Class type, String name, Object value) {
    return (A) this.addToArguments(new Declare(type, name, value));
  }

  public DeclareArgumentsNested<A> addNewDeclareArgumentLike(Declare item) {
    return new DeclareArgumentsNested(-1, item);
  }

  public DivideArgumentsNested<A> addNewDivideArgument() {
    return new DivideArgumentsNested(-1, null);
  }

  public A addNewDivideArgument(Object left, Object right) {
    return (A) this.addToArguments(new Divide(left, right));
  }

  public DivideArgumentsNested<A> addNewDivideArgumentLike(Divide item) {
    return new DivideArgumentsNested(-1, item);
  }

  public DotClassArgumentsNested<A> addNewDotClassArgument() {
    return new DotClassArgumentsNested(-1, null);
  }

  public DotClassArgumentsNested<A> addNewDotClassArgumentLike(DotClass item) {
    return new DotClassArgumentsNested(-1, item);
  }

  public EmptyArgumentsNested<A> addNewEmptyArgument() {
    return new EmptyArgumentsNested(-1, null);
  }

  public EmptyArgumentsNested<A> addNewEmptyArgumentLike(Empty item) {
    return new EmptyArgumentsNested(-1, item);
  }

  public EnclosedArgumentsNested<A> addNewEnclosedArgument() {
    return new EnclosedArgumentsNested(-1, null);
  }

  public EnclosedArgumentsNested<A> addNewEnclosedArgumentLike(Enclosed item) {
    return new EnclosedArgumentsNested(-1, item);
  }

  public EqualsArgumentsNested<A> addNewEqualsArgument() {
    return new EqualsArgumentsNested(-1, null);
  }

  public A addNewEqualsArgument(Object left, Object right) {
    return (A) this.addToArguments(new Equals(left, right));
  }

  public EqualsArgumentsNested<A> addNewEqualsArgumentLike(Equals item) {
    return new EqualsArgumentsNested(-1, item);
  }

  public GreaterThanArgumentsNested<A> addNewGreaterThanArgument() {
    return new GreaterThanArgumentsNested(-1, null);
  }

  public A addNewGreaterThanArgument(Object left, Object right) {
    return (A) this.addToArguments(new GreaterThan(left, right));
  }

  public GreaterThanArgumentsNested<A> addNewGreaterThanArgumentLike(GreaterThan item) {
    return new GreaterThanArgumentsNested(-1, item);
  }

  public GreaterThanOrEqualArgumentsNested<A> addNewGreaterThanOrEqualArgument() {
    return new GreaterThanOrEqualArgumentsNested(-1, null);
  }

  public A addNewGreaterThanOrEqualArgument(Object left, Object right) {
    return (A) this.addToArguments(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualArgumentsNested<A> addNewGreaterThanOrEqualArgumentLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualArgumentsNested(-1, item);
  }

  public IndexArgumentsNested<A> addNewIndexArgument() {
    return new IndexArgumentsNested(-1, null);
  }

  public IndexArgumentsNested<A> addNewIndexArgumentLike(Index item) {
    return new IndexArgumentsNested(-1, item);
  }

  public InstanceOfArgumentsNested<A> addNewInstanceOfArgument() {
    return new InstanceOfArgumentsNested(-1, null);
  }

  public InstanceOfArgumentsNested<A> addNewInstanceOfArgumentLike(InstanceOf item) {
    return new InstanceOfArgumentsNested(-1, item);
  }

  public InverseArgumentsNested<A> addNewInverseArgument() {
    return new InverseArgumentsNested(-1, null);
  }

  public InverseArgumentsNested<A> addNewInverseArgumentLike(Inverse item) {
    return new InverseArgumentsNested(-1, item);
  }

  public LambdaArgumentsNested<A> addNewLambdaArgument() {
    return new LambdaArgumentsNested(-1, null);
  }

  public LambdaArgumentsNested<A> addNewLambdaArgumentLike(Lambda item) {
    return new LambdaArgumentsNested(-1, item);
  }

  public LeftShiftArgumentsNested<A> addNewLeftShiftArgument() {
    return new LeftShiftArgumentsNested(-1, null);
  }

  public A addNewLeftShiftArgument(Object left, Object right) {
    return (A) this.addToArguments(new LeftShift(left, right));
  }

  public LeftShiftArgumentsNested<A> addNewLeftShiftArgumentLike(LeftShift item) {
    return new LeftShiftArgumentsNested(-1, item);
  }

  public LessThanArgumentsNested<A> addNewLessThanArgument() {
    return new LessThanArgumentsNested(-1, null);
  }

  public A addNewLessThanArgument(Object left, Object right) {
    return (A) this.addToArguments(new LessThan(left, right));
  }

  public LessThanArgumentsNested<A> addNewLessThanArgumentLike(LessThan item) {
    return new LessThanArgumentsNested(-1, item);
  }

  public LessThanOrEqualArgumentsNested<A> addNewLessThanOrEqualArgument() {
    return new LessThanOrEqualArgumentsNested(-1, null);
  }

  public A addNewLessThanOrEqualArgument(Object left, Object right) {
    return (A) this.addToArguments(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualArgumentsNested<A> addNewLessThanOrEqualArgumentLike(LessThanOrEqual item) {
    return new LessThanOrEqualArgumentsNested(-1, item);
  }

  public LogicalAndArgumentsNested<A> addNewLogicalAndArgument() {
    return new LogicalAndArgumentsNested(-1, null);
  }

  public A addNewLogicalAndArgument(Object left, Object right) {
    return (A) this.addToArguments(new LogicalAnd(left, right));
  }

  public LogicalAndArgumentsNested<A> addNewLogicalAndArgumentLike(LogicalAnd item) {
    return new LogicalAndArgumentsNested(-1, item);
  }

  public LogicalOrArgumentsNested<A> addNewLogicalOrArgument() {
    return new LogicalOrArgumentsNested(-1, null);
  }

  public A addNewLogicalOrArgument(Object left, Object right) {
    return (A) this.addToArguments(new LogicalOr(left, right));
  }

  public LogicalOrArgumentsNested<A> addNewLogicalOrArgumentLike(LogicalOr item) {
    return new LogicalOrArgumentsNested(-1, item);
  }

  public MethodCallArgumentsNested<A> addNewMethodCallArgument() {
    return new MethodCallArgumentsNested(-1, null);
  }

  public MethodCallArgumentsNested<A> addNewMethodCallArgumentLike(MethodCall item) {
    return new MethodCallArgumentsNested(-1, item);
  }

  public MinusArgumentsNested<A> addNewMinusArgument() {
    return new MinusArgumentsNested(-1, null);
  }

  public A addNewMinusArgument(Object left, Object right) {
    return (A) this.addToArguments(new Minus(left, right));
  }

  public MinusArgumentsNested<A> addNewMinusArgumentLike(Minus item) {
    return new MinusArgumentsNested(-1, item);
  }

  public ModuloArgumentsNested<A> addNewModuloArgument() {
    return new ModuloArgumentsNested(-1, null);
  }

  public A addNewModuloArgument(Object left, Object right) {
    return (A) this.addToArguments(new Modulo(left, right));
  }

  public ModuloArgumentsNested<A> addNewModuloArgumentLike(Modulo item) {
    return new ModuloArgumentsNested(-1, item);
  }

  public MultiplyArgumentsNested<A> addNewMultiplyArgument() {
    return new MultiplyArgumentsNested(-1, null);
  }

  public A addNewMultiplyArgument(Object left, Object right) {
    return (A) this.addToArguments(new Multiply(left, right));
  }

  public MultiplyArgumentsNested<A> addNewMultiplyArgumentLike(Multiply item) {
    return new MultiplyArgumentsNested(-1, item);
  }

  public NegativeArgumentsNested<A> addNewNegativeArgument() {
    return new NegativeArgumentsNested(-1, null);
  }

  public NegativeArgumentsNested<A> addNewNegativeArgumentLike(Negative item) {
    return new NegativeArgumentsNested(-1, item);
  }

  public NewArrayArgumentsNested<A> addNewNewArrayArgument() {
    return new NewArrayArgumentsNested(-1, null);
  }

  public A addNewNewArrayArgument(Class type, Integer[] sizes) {
    return (A) this.addToArguments(new NewArray(type, sizes));
  }

  public NewArrayArgumentsNested<A> addNewNewArrayArgumentLike(NewArray item) {
    return new NewArrayArgumentsNested(-1, item);
  }

  public NotArgumentsNested<A> addNewNotArgument() {
    return new NotArgumentsNested(-1, null);
  }

  public NotArgumentsNested<A> addNewNotArgumentLike(Not item) {
    return new NotArgumentsNested(-1, item);
  }

  public NotEqualsArgumentsNested<A> addNewNotEqualsArgument() {
    return new NotEqualsArgumentsNested(-1, null);
  }

  public A addNewNotEqualsArgument(Object left, Object right) {
    return (A) this.addToArguments(new NotEquals(left, right));
  }

  public NotEqualsArgumentsNested<A> addNewNotEqualsArgumentLike(NotEquals item) {
    return new NotEqualsArgumentsNested(-1, item);
  }

  public PlusArgumentsNested<A> addNewPlusArgument() {
    return new PlusArgumentsNested(-1, null);
  }

  public A addNewPlusArgument(Object left, Object right) {
    return (A) this.addToArguments(new Plus(left, right));
  }

  public PlusArgumentsNested<A> addNewPlusArgumentLike(Plus item) {
    return new PlusArgumentsNested(-1, item);
  }

  public PositiveArgumentsNested<A> addNewPositiveArgument() {
    return new PositiveArgumentsNested(-1, null);
  }

  public PositiveArgumentsNested<A> addNewPositiveArgumentLike(Positive item) {
    return new PositiveArgumentsNested(-1, item);
  }

  public PostDecrementArgumentsNested<A> addNewPostDecrementArgument() {
    return new PostDecrementArgumentsNested(-1, null);
  }

  public PostDecrementArgumentsNested<A> addNewPostDecrementArgumentLike(PostDecrement item) {
    return new PostDecrementArgumentsNested(-1, item);
  }

  public PostIncrementArgumentsNested<A> addNewPostIncrementArgument() {
    return new PostIncrementArgumentsNested(-1, null);
  }

  public PostIncrementArgumentsNested<A> addNewPostIncrementArgumentLike(PostIncrement item) {
    return new PostIncrementArgumentsNested(-1, item);
  }

  public PreDecrementArgumentsNested<A> addNewPreDecrementArgument() {
    return new PreDecrementArgumentsNested(-1, null);
  }

  public PreDecrementArgumentsNested<A> addNewPreDecrementArgumentLike(PreDecrement item) {
    return new PreDecrementArgumentsNested(-1, item);
  }

  public PreIncrementArgumentsNested<A> addNewPreIncrementArgument() {
    return new PreIncrementArgumentsNested(-1, null);
  }

  public PreIncrementArgumentsNested<A> addNewPreIncrementArgumentLike(PreIncrement item) {
    return new PreIncrementArgumentsNested(-1, item);
  }

  public PrimitiveRefParametersNested<A> addNewPrimitiveRefParameter() {
    return new PrimitiveRefParametersNested(-1, null);
  }

  public PrimitiveRefParametersNested<A> addNewPrimitiveRefParameterLike(PrimitiveRef item) {
    return new PrimitiveRefParametersNested(-1, item);
  }

  public PropertyArgumentsNested<A> addNewPropertyArgument() {
    return new PropertyArgumentsNested(-1, null);
  }

  public PropertyArgumentsNested<A> addNewPropertyArgumentLike(Property item) {
    return new PropertyArgumentsNested(-1, item);
  }

  public PropertyRefArgumentsNested<A> addNewPropertyRefArgument() {
    return new PropertyRefArgumentsNested(-1, null);
  }

  public PropertyRefArgumentsNested<A> addNewPropertyRefArgumentLike(PropertyRef item) {
    return new PropertyRefArgumentsNested(-1, item);
  }

  public RightShiftArgumentsNested<A> addNewRightShiftArgument() {
    return new RightShiftArgumentsNested(-1, null);
  }

  public A addNewRightShiftArgument(Object left, Object right) {
    return (A) this.addToArguments(new RightShift(left, right));
  }

  public RightShiftArgumentsNested<A> addNewRightShiftArgumentLike(RightShift item) {
    return new RightShiftArgumentsNested(-1, item);
  }

  public RightUnsignedShiftArgumentsNested<A> addNewRightUnsignedShiftArgument() {
    return new RightUnsignedShiftArgumentsNested(-1, null);
  }

  public A addNewRightUnsignedShiftArgument(Object left, Object right) {
    return (A) this.addToArguments(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftArgumentsNested<A> addNewRightUnsignedShiftArgumentLike(RightUnsignedShift item) {
    return new RightUnsignedShiftArgumentsNested(-1, item);
  }

  public SuperArgumentsNested<A> addNewSuperArgument() {
    return new SuperArgumentsNested(-1, null);
  }

  public SuperArgumentsNested<A> addNewSuperArgumentLike(Super item) {
    return new SuperArgumentsNested(-1, item);
  }

  public TernaryArgumentsNested<A> addNewTernaryArgument() {
    return new TernaryArgumentsNested(-1, null);
  }

  public TernaryArgumentsNested<A> addNewTernaryArgumentLike(Ternary item) {
    return new TernaryArgumentsNested(-1, item);
  }

  public ThisArgumentsNested<A> addNewThisArgument() {
    return new ThisArgumentsNested(-1, null);
  }

  public ThisArgumentsNested<A> addNewThisArgumentLike(This item) {
    return new ThisArgumentsNested(-1, item);
  }

  public TypeParamRefParametersNested<A> addNewTypeParamRefParameter() {
    return new TypeParamRefParametersNested(-1, null);
  }

  public TypeParamRefParametersNested<A> addNewTypeParamRefParameterLike(TypeParamRef item) {
    return new TypeParamRefParametersNested(-1, item);
  }

  public ValueRefArgumentsNested<A> addNewValueRefArgument() {
    return new ValueRefArgumentsNested(-1, null);
  }

  public A addNewValueRefArgument(Object value) {
    return (A) this.addToArguments(new ValueRef(value));
  }

  public ValueRefArgumentsNested<A> addNewValueRefArgumentLike(ValueRef item) {
    return new ValueRefArgumentsNested(-1, item);
  }

  public VoidRefParametersNested<A> addNewVoidRefParameter() {
    return new VoidRefParametersNested(-1, null);
  }

  public VoidRefParametersNested<A> addNewVoidRefParameterLike(VoidRef item) {
    return new VoidRefParametersNested(-1, item);
  }

  public WildcardRefParametersNested<A> addNewWildcardRefParameter() {
    return new WildcardRefParametersNested(-1, null);
  }

  public WildcardRefParametersNested<A> addNewWildcardRefParameterLike(WildcardRef item) {
    return new WildcardRefParametersNested(-1, item);
  }

  public XorArgumentsNested<A> addNewXorArgument() {
    return new XorArgumentsNested(-1, null);
  }

  public A addNewXorArgument(Object left, Object right) {
    return (A) this.addToArguments(new Xor(left, right));
  }

  public XorArgumentsNested<A> addNewXorArgumentLike(Xor item) {
    return new XorArgumentsNested(-1, item);
  }

  public A addToArguments(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    _visitables.get("arguments").add(builder);
    this.arguments.add(builder);
    return (A) this;
  }

  public A addToArguments(Expression... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addToArguments(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(builder);
      arguments.add(index, builder);
    }
    return (A) this;
  }

  public A addToArguments(int index, Expression item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(builder);
      arguments.add(index, builder);
    }
    return (A) this;
  }

  public A addToParameters(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    _visitables.get("parameters").add(builder);
    this.parameters.add(builder);
    return (A) this;
  }

  public A addToParameters(TypeRef... items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A addToParameters(int index, VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").add(builder);
      parameters.add(index, builder);
    }
    return (A) this;
  }

  public A addToParameters(int index, TypeRef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").add(builder);
      parameters.add(index, builder);
    }
    return (A) this;
  }

  public Expression buildArgument(int index) {
    return this.arguments.get(index).build();
  }

  public List<Expression> buildArguments() {
    return build(arguments);
  }

  public Expression buildFirstArgument() {
    return this.arguments.get(0).build();
  }

  public TypeRef buildFirstParameter() {
    return this.parameters.get(0).build();
  }

  public Expression buildLastArgument() {
    return this.arguments.get(arguments.size() - 1).build();
  }

  public TypeRef buildLastParameter() {
    return this.parameters.get(parameters.size() - 1).build();
  }

  public Expression buildMatchingArgument(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : arguments) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public TypeRef buildMatchingParameter(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : parameters) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public TypeRef buildParameter(int index) {
    return this.parameters.get(index).build();
  }

  public List<TypeRef> buildParameters() {
    return build(parameters);
  }

  public ClassRef buildType() {
    return this.type != null ? this.type.build() : null;
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "ClassRef":

        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);

      case "PrimitiveRef":

        return (VisitableBuilder<T, ?>) new PrimitiveRefBuilder((PrimitiveRef) item);

      case "VoidRef":

        return (VisitableBuilder<T, ?>) new VoidRefBuilder((VoidRef) item);

      case "TypeParamRef":

        return (VisitableBuilder<T, ?>) new TypeParamRefBuilder((TypeParamRef) item);

      case "WildcardRef":

        return (VisitableBuilder<T, ?>) new WildcardRefBuilder((WildcardRef) item);

      case "Multiply":

        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);

      case "NewArray":

        return (VisitableBuilder<T, ?>) new NewArrayBuilder((NewArray) item);

      case "InstanceOf":

        return (VisitableBuilder<T, ?>) new InstanceOfBuilder((InstanceOf) item);

      case "MethodCall":

        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);

      case "Inverse":

        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);

      case "Index":

        return (VisitableBuilder<T, ?>) new IndexBuilder((Index) item);

      case "GreaterThanOrEqual":

        return (VisitableBuilder<T, ?>) new GreaterThanOrEqualBuilder((GreaterThanOrEqual) item);

      case "BitwiseAnd":

        return (VisitableBuilder<T, ?>) new BitwiseAndBuilder((BitwiseAnd) item);

      case "Minus":

        return (VisitableBuilder<T, ?>) new MinusBuilder((Minus) item);

      case "LogicalOr":

        return (VisitableBuilder<T, ?>) new LogicalOrBuilder((LogicalOr) item);

      case "NotEquals":

        return (VisitableBuilder<T, ?>) new NotEqualsBuilder((NotEquals) item);

      case "Divide":

        return (VisitableBuilder<T, ?>) new DivideBuilder((Divide) item);

      case "LessThan":

        return (VisitableBuilder<T, ?>) new LessThanBuilder((LessThan) item);

      case "BitwiseOr":

        return (VisitableBuilder<T, ?>) new BitwiseOrBuilder((BitwiseOr) item);

      case "PropertyRef":

        return (VisitableBuilder<T, ?>) new PropertyRefBuilder((PropertyRef) item);

      case "RightShift":

        return (VisitableBuilder<T, ?>) new RightShiftBuilder((RightShift) item);

      case "Super":

        return (VisitableBuilder<T, ?>) new SuperBuilder((Super) item);

      case "GreaterThan":

        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);

      case "Declare":

        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);

      case "Cast":

        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);

      case "Modulo":

        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);

      case "DotClass":

        return (VisitableBuilder<T, ?>) new DotClassBuilder((DotClass) item);

      case "ValueRef":

        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);

      case "LeftShift":

        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);

      case "Empty":

        return (VisitableBuilder<T, ?>) new EmptyBuilder((Empty) item);

      case "Ternary":

        return (VisitableBuilder<T, ?>) new TernaryBuilder((Ternary) item);

      case "BinaryExpression":

        return (VisitableBuilder<T, ?>) new BinaryExpressionBuilder((BinaryExpression) item);

      case "Equals":

        return (VisitableBuilder<T, ?>) new EqualsBuilder((Equals) item);

      case "Enclosed":

        return (VisitableBuilder<T, ?>) new EnclosedBuilder((Enclosed) item);

      case "PreDecrement":

        return (VisitableBuilder<T, ?>) new PreDecrementBuilder((PreDecrement) item);

      case "PostDecrement":

        return (VisitableBuilder<T, ?>) new PostDecrementBuilder((PostDecrement) item);

      case "Lambda":

        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);

      case "Not":

        return (VisitableBuilder<T, ?>) new NotBuilder((Not) item);

      case "Assign":

        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);

      case "This":

        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);

      case "Negative":

        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);

      case "LogicalAnd":

        return (VisitableBuilder<T, ?>) new LogicalAndBuilder((LogicalAnd) item);

      case "PostIncrement":

        return (VisitableBuilder<T, ?>) new PostIncrementBuilder((PostIncrement) item);

      case "RightUnsignedShift":

        return (VisitableBuilder<T, ?>) new RightUnsignedShiftBuilder((RightUnsignedShift) item);

      case "Plus":

        return (VisitableBuilder<T, ?>) new PlusBuilder((Plus) item);

      case "Construct":

        return (VisitableBuilder<T, ?>) new ConstructBuilder((Construct) item);

      case "Xor":

        return (VisitableBuilder<T, ?>) new XorBuilder((Xor) item);

      case "PreIncrement":

        return (VisitableBuilder<T, ?>) new PreIncrementBuilder((PreIncrement) item);

      case "Property":

        return (VisitableBuilder<T, ?>) new PropertyBuilder((Property) item);

      case "LessThanOrEqual":

        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);

      case "ContextRef":

        return (VisitableBuilder<T, ?>) new ContextRefBuilder((ContextRef) item);

      case "Positive":

        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
  }

  protected void copyInstance(Construct instance) {
    if (instance != null) {
      this.withType(instance.getType());
      this.withParameters(instance.getParameters());
      this.withArguments(instance.getArguments());
    }
  }

  public TypeNested<A> editOrNewType() {
    return this.withNewTypeLike(Optional.ofNullable(this.buildType()).orElse(new ClassRefBuilder().build()));
  }

  public TypeNested<A> editOrNewTypeLike(ClassRef item) {
    return this.withNewTypeLike(Optional.ofNullable(this.buildType()).orElse(item));
  }

  public TypeNested<A> editType() {
    return this.withNewTypeLike(Optional.ofNullable(this.buildType()).orElse(null));
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    ConstructFluent that = (ConstructFluent) o;
    if (!(Objects.equals(type, that.type))) {
      return false;
    }
    if (!(Objects.equals(parameters, that.parameters))) {
      return false;
    }
    if (!(Objects.equals(arguments, that.arguments))) {
      return false;
    }
    return true;
  }

  public boolean hasArguments() {
    return this.arguments != null && !(this.arguments.isEmpty());
  }

  public boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingParameter(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasParameters() {
    return this.parameters != null && !(this.parameters.isEmpty());
  }

  public boolean hasType() {
    return this.type != null;
  }

  public int hashCode() {
    return Objects.hash(type, parameters, arguments);
  }

  public A removeAllFromArguments(Collection<Expression> items) {
    if (this.arguments == null) {
      return (A) this;
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromParameters(Collection<TypeRef> items) {
    if (this.parameters == null) {
      return (A) this;
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeFromArguments(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.arguments == null) {
      return (A) this;
    }
    _visitables.get("arguments").remove(builder);
    this.arguments.remove(builder);
    return (A) this;
  }

  public A removeFromArguments(Expression... items) {
    if (this.arguments == null) {
      return (A) this;
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeFromParameters(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.parameters == null) {
      return (A) this;
    }
    _visitables.get("parameters").remove(builder);
    this.parameters.remove(builder);
    return (A) this;
  }

  public A removeFromParameters(TypeRef... items) {
    if (this.parameters == null) {
      return (A) this;
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromArguments(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    if (arguments == null) {
      return (A) this;
    }
    Iterator<VisitableBuilder<? extends Expression, ?>> each = arguments.iterator();
    List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      VisitableBuilder<? extends Expression, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public A removeMatchingFromParameters(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (parameters == null) {
      return (A) this;
    }
    Iterator<VisitableBuilder<? extends TypeRef, ?>> each = parameters.iterator();
    List visitables = _visitables.get("parameters");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public AssignArgumentsNested<A> setNewAssignArgumentLike(int index, Assign item) {
    return new AssignArgumentsNested(index, item);
  }

  public BinaryExpressionArgumentsNested<A> setNewBinaryExpressionArgumentLike(int index, BinaryExpression item) {
    return new BinaryExpressionArgumentsNested(index, item);
  }

  public BitwiseAndArgumentsNested<A> setNewBitwiseAndArgumentLike(int index, BitwiseAnd item) {
    return new BitwiseAndArgumentsNested(index, item);
  }

  public BitwiseOrArgumentsNested<A> setNewBitwiseOrArgumentLike(int index, BitwiseOr item) {
    return new BitwiseOrArgumentsNested(index, item);
  }

  public CastArgumentsNested<A> setNewCastArgumentLike(int index, Cast item) {
    return new CastArgumentsNested(index, item);
  }

  public ClassRefArgumentsNested<A> setNewClassRefArgumentLike(int index, ClassRef item) {
    return new ClassRefArgumentsNested(index, item);
  }

  public ClassRefParametersNested<A> setNewClassRefParameterLike(int index, ClassRef item) {
    return new ClassRefParametersNested(index, item);
  }

  public ConstructArgumentsNested<A> setNewConstructArgumentLike(int index, Construct item) {
    return new ConstructArgumentsNested(index, item);
  }

  public ContextRefArgumentsNested<A> setNewContextRefArgumentLike(int index, ContextRef item) {
    return new ContextRefArgumentsNested(index, item);
  }

  public DeclareArgumentsNested<A> setNewDeclareArgumentLike(int index, Declare item) {
    return new DeclareArgumentsNested(index, item);
  }

  public DivideArgumentsNested<A> setNewDivideArgumentLike(int index, Divide item) {
    return new DivideArgumentsNested(index, item);
  }

  public DotClassArgumentsNested<A> setNewDotClassArgumentLike(int index, DotClass item) {
    return new DotClassArgumentsNested(index, item);
  }

  public EmptyArgumentsNested<A> setNewEmptyArgumentLike(int index, Empty item) {
    return new EmptyArgumentsNested(index, item);
  }

  public EnclosedArgumentsNested<A> setNewEnclosedArgumentLike(int index, Enclosed item) {
    return new EnclosedArgumentsNested(index, item);
  }

  public EqualsArgumentsNested<A> setNewEqualsArgumentLike(int index, Equals item) {
    return new EqualsArgumentsNested(index, item);
  }

  public GreaterThanArgumentsNested<A> setNewGreaterThanArgumentLike(int index, GreaterThan item) {
    return new GreaterThanArgumentsNested(index, item);
  }

  public GreaterThanOrEqualArgumentsNested<A> setNewGreaterThanOrEqualArgumentLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualArgumentsNested(index, item);
  }

  public IndexArgumentsNested<A> setNewIndexArgumentLike(int index, Index item) {
    return new IndexArgumentsNested(index, item);
  }

  public InstanceOfArgumentsNested<A> setNewInstanceOfArgumentLike(int index, InstanceOf item) {
    return new InstanceOfArgumentsNested(index, item);
  }

  public InverseArgumentsNested<A> setNewInverseArgumentLike(int index, Inverse item) {
    return new InverseArgumentsNested(index, item);
  }

  public LambdaArgumentsNested<A> setNewLambdaArgumentLike(int index, Lambda item) {
    return new LambdaArgumentsNested(index, item);
  }

  public LeftShiftArgumentsNested<A> setNewLeftShiftArgumentLike(int index, LeftShift item) {
    return new LeftShiftArgumentsNested(index, item);
  }

  public LessThanArgumentsNested<A> setNewLessThanArgumentLike(int index, LessThan item) {
    return new LessThanArgumentsNested(index, item);
  }

  public LessThanOrEqualArgumentsNested<A> setNewLessThanOrEqualArgumentLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualArgumentsNested(index, item);
  }

  public LogicalAndArgumentsNested<A> setNewLogicalAndArgumentLike(int index, LogicalAnd item) {
    return new LogicalAndArgumentsNested(index, item);
  }

  public LogicalOrArgumentsNested<A> setNewLogicalOrArgumentLike(int index, LogicalOr item) {
    return new LogicalOrArgumentsNested(index, item);
  }

  public MethodCallArgumentsNested<A> setNewMethodCallArgumentLike(int index, MethodCall item) {
    return new MethodCallArgumentsNested(index, item);
  }

  public MinusArgumentsNested<A> setNewMinusArgumentLike(int index, Minus item) {
    return new MinusArgumentsNested(index, item);
  }

  public ModuloArgumentsNested<A> setNewModuloArgumentLike(int index, Modulo item) {
    return new ModuloArgumentsNested(index, item);
  }

  public MultiplyArgumentsNested<A> setNewMultiplyArgumentLike(int index, Multiply item) {
    return new MultiplyArgumentsNested(index, item);
  }

  public NegativeArgumentsNested<A> setNewNegativeArgumentLike(int index, Negative item) {
    return new NegativeArgumentsNested(index, item);
  }

  public NewArrayArgumentsNested<A> setNewNewArrayArgumentLike(int index, NewArray item) {
    return new NewArrayArgumentsNested(index, item);
  }

  public NotArgumentsNested<A> setNewNotArgumentLike(int index, Not item) {
    return new NotArgumentsNested(index, item);
  }

  public NotEqualsArgumentsNested<A> setNewNotEqualsArgumentLike(int index, NotEquals item) {
    return new NotEqualsArgumentsNested(index, item);
  }

  public PlusArgumentsNested<A> setNewPlusArgumentLike(int index, Plus item) {
    return new PlusArgumentsNested(index, item);
  }

  public PositiveArgumentsNested<A> setNewPositiveArgumentLike(int index, Positive item) {
    return new PositiveArgumentsNested(index, item);
  }

  public PostDecrementArgumentsNested<A> setNewPostDecrementArgumentLike(int index, PostDecrement item) {
    return new PostDecrementArgumentsNested(index, item);
  }

  public PostIncrementArgumentsNested<A> setNewPostIncrementArgumentLike(int index, PostIncrement item) {
    return new PostIncrementArgumentsNested(index, item);
  }

  public PreDecrementArgumentsNested<A> setNewPreDecrementArgumentLike(int index, PreDecrement item) {
    return new PreDecrementArgumentsNested(index, item);
  }

  public PreIncrementArgumentsNested<A> setNewPreIncrementArgumentLike(int index, PreIncrement item) {
    return new PreIncrementArgumentsNested(index, item);
  }

  public PrimitiveRefParametersNested<A> setNewPrimitiveRefParameterLike(int index, PrimitiveRef item) {
    return new PrimitiveRefParametersNested(index, item);
  }

  public PropertyArgumentsNested<A> setNewPropertyArgumentLike(int index, Property item) {
    return new PropertyArgumentsNested(index, item);
  }

  public PropertyRefArgumentsNested<A> setNewPropertyRefArgumentLike(int index, PropertyRef item) {
    return new PropertyRefArgumentsNested(index, item);
  }

  public RightShiftArgumentsNested<A> setNewRightShiftArgumentLike(int index, RightShift item) {
    return new RightShiftArgumentsNested(index, item);
  }

  public RightUnsignedShiftArgumentsNested<A> setNewRightUnsignedShiftArgumentLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftArgumentsNested(index, item);
  }

  public SuperArgumentsNested<A> setNewSuperArgumentLike(int index, Super item) {
    return new SuperArgumentsNested(index, item);
  }

  public TernaryArgumentsNested<A> setNewTernaryArgumentLike(int index, Ternary item) {
    return new TernaryArgumentsNested(index, item);
  }

  public ThisArgumentsNested<A> setNewThisArgumentLike(int index, This item) {
    return new ThisArgumentsNested(index, item);
  }

  public TypeParamRefParametersNested<A> setNewTypeParamRefParameterLike(int index, TypeParamRef item) {
    return new TypeParamRefParametersNested(index, item);
  }

  public ValueRefArgumentsNested<A> setNewValueRefArgumentLike(int index, ValueRef item) {
    return new ValueRefArgumentsNested(index, item);
  }

  public VoidRefParametersNested<A> setNewVoidRefParameterLike(int index, VoidRef item) {
    return new VoidRefParametersNested(index, item);
  }

  public WildcardRefParametersNested<A> setNewWildcardRefParameterLike(int index, WildcardRef item) {
    return new WildcardRefParametersNested(index, item);
  }

  public XorArgumentsNested<A> setNewXorArgumentLike(int index, Xor item) {
    return new XorArgumentsNested(index, item);
  }

  public A setToArguments(int index, Expression item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList();
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(item);
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(builder);
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A setToParameters(int index, TypeRef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").add(builder);
      parameters.set(index, builder);
    }
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(type == null)) {
      sb.append("type:");
      sb.append(type);
      sb.append(",");
    }
    if (!(parameters == null) && !(parameters.isEmpty())) {
      sb.append("parameters:");
      sb.append(parameters);
      sb.append(",");
    }
    if (!(arguments == null) && !(arguments.isEmpty())) {
      sb.append("arguments:");
      sb.append(arguments);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withArguments(List<Expression> arguments) {
    if (arguments != null) {
      this.arguments = new ArrayList();
      for (Expression item : arguments) {
        this.addToArguments(item);
      }
    } else {
      this.arguments = null;
    }
    return (A) this;
  }

  public A withArguments(Expression... arguments) {
    if (this.arguments != null) {
      this.arguments.clear();
      _visitables.remove("arguments");
    }
    if (arguments != null) {
      for (Expression item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public TypeNested<A> withNewType() {
    return new TypeNested(null);
  }

  public TypeNested<A> withNewTypeLike(ClassRef item) {
    return new TypeNested(item);
  }

  public A withParameters(List<TypeRef> parameters) {
    if (parameters != null) {
      this.parameters = new ArrayList();
      for (TypeRef item : parameters) {
        this.addToParameters(item);
      }
    } else {
      this.parameters = null;
    }
    return (A) this;
  }

  public A withParameters(TypeRef... parameters) {
    if (this.parameters != null) {
      this.parameters.clear();
      _visitables.remove("parameters");
    }
    if (parameters != null) {
      for (TypeRef item : parameters) {
        this.addToParameters(item);
      }
    }
    return (A) this;
  }

  public A withType(ClassRef type) {
    this._visitables.remove("type");
    if (type != null) {
      this.type = new ClassRefBuilder(type);
      this._visitables.get("type").add(this.type);
    } else {
      this.type = null;
      this._visitables.get("type").remove(this.type);
    }
    return (A) this;
  }

  public class AssignArgumentsNested<N> extends AssignFluent<AssignArgumentsNested<N>> implements Nested<N> {

    AssignBuilder builder;
    int index;

    AssignArgumentsNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endAssignArgument() {
      return and();
    }

  }

  public class BinaryExpressionArgumentsNested<N> extends BinaryExpressionFluent<BinaryExpressionArgumentsNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;
    int index;

    BinaryExpressionArgumentsNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endBinaryExpressionArgument() {
      return and();
    }

  }

  public class BitwiseAndArgumentsNested<N> extends BitwiseAndFluent<BitwiseAndArgumentsNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;
    int index;

    BitwiseAndArgumentsNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endBitwiseAndArgument() {
      return and();
    }

  }

  public class BitwiseOrArgumentsNested<N> extends BitwiseOrFluent<BitwiseOrArgumentsNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;
    int index;

    BitwiseOrArgumentsNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endBitwiseOrArgument() {
      return and();
    }

  }

  public class CastArgumentsNested<N> extends CastFluent<CastArgumentsNested<N>> implements Nested<N> {

    CastBuilder builder;
    int index;

    CastArgumentsNested(int index, Cast item) {
      this.index = index;
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endCastArgument() {
      return and();
    }

  }

  public class ClassRefArgumentsNested<N> extends ClassRefFluent<ClassRefArgumentsNested<N>> implements Nested<N> {

    ClassRefBuilder builder;
    int index;

    ClassRefArgumentsNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endClassRefArgument() {
      return and();
    }

  }

  public class ClassRefParametersNested<N> extends ClassRefFluent<ClassRefParametersNested<N>> implements Nested<N> {

    ClassRefBuilder builder;
    int index;

    ClassRefParametersNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endClassRefParameter() {
      return and();
    }

  }

  public class ConstructArgumentsNested<N> extends ConstructFluent<ConstructArgumentsNested<N>> implements Nested<N> {

    ConstructBuilder builder;
    int index;

    ConstructArgumentsNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endConstructArgument() {
      return and();
    }

  }

  public class ContextRefArgumentsNested<N> extends ContextRefFluent<ContextRefArgumentsNested<N>> implements Nested<N> {

    ContextRefBuilder builder;
    int index;

    ContextRefArgumentsNested(int index, ContextRef item) {
      this.index = index;
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endContextRefArgument() {
      return and();
    }

  }

  public class DeclareArgumentsNested<N> extends DeclareFluent<DeclareArgumentsNested<N>> implements Nested<N> {

    DeclareBuilder builder;
    int index;

    DeclareArgumentsNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endDeclareArgument() {
      return and();
    }

  }

  public class DivideArgumentsNested<N> extends DivideFluent<DivideArgumentsNested<N>> implements Nested<N> {

    DivideBuilder builder;
    int index;

    DivideArgumentsNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endDivideArgument() {
      return and();
    }

  }

  public class DotClassArgumentsNested<N> extends DotClassFluent<DotClassArgumentsNested<N>> implements Nested<N> {

    DotClassBuilder builder;
    int index;

    DotClassArgumentsNested(int index, DotClass item) {
      this.index = index;
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endDotClassArgument() {
      return and();
    }

  }

  public class EmptyArgumentsNested<N> extends EmptyFluent<EmptyArgumentsNested<N>> implements Nested<N> {

    EmptyBuilder builder;
    int index;

    EmptyArgumentsNested(int index, Empty item) {
      this.index = index;
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endEmptyArgument() {
      return and();
    }

  }

  public class EnclosedArgumentsNested<N> extends EnclosedFluent<EnclosedArgumentsNested<N>> implements Nested<N> {

    EnclosedBuilder builder;
    int index;

    EnclosedArgumentsNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endEnclosedArgument() {
      return and();
    }

  }

  public class EqualsArgumentsNested<N> extends EqualsFluent<EqualsArgumentsNested<N>> implements Nested<N> {

    EqualsBuilder builder;
    int index;

    EqualsArgumentsNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endEqualsArgument() {
      return and();
    }

  }

  public class GreaterThanArgumentsNested<N> extends GreaterThanFluent<GreaterThanArgumentsNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;
    int index;

    GreaterThanArgumentsNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endGreaterThanArgument() {
      return and();
    }

  }

  public class GreaterThanOrEqualArgumentsNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualArgumentsNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;
    int index;

    GreaterThanOrEqualArgumentsNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endGreaterThanOrEqualArgument() {
      return and();
    }

  }

  public class IndexArgumentsNested<N> extends IndexFluent<IndexArgumentsNested<N>> implements Nested<N> {

    IndexBuilder builder;
    int index;

    IndexArgumentsNested(int index, Index item) {
      this.index = index;
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endIndexArgument() {
      return and();
    }

  }

  public class InstanceOfArgumentsNested<N> extends InstanceOfFluent<InstanceOfArgumentsNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;
    int index;

    InstanceOfArgumentsNested(int index, InstanceOf item) {
      this.index = index;
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endInstanceOfArgument() {
      return and();
    }

  }

  public class InverseArgumentsNested<N> extends InverseFluent<InverseArgumentsNested<N>> implements Nested<N> {

    InverseBuilder builder;
    int index;

    InverseArgumentsNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endInverseArgument() {
      return and();
    }

  }

  public class LambdaArgumentsNested<N> extends LambdaFluent<LambdaArgumentsNested<N>> implements Nested<N> {

    LambdaBuilder builder;
    int index;

    LambdaArgumentsNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLambdaArgument() {
      return and();
    }

  }

  public class LeftShiftArgumentsNested<N> extends LeftShiftFluent<LeftShiftArgumentsNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;
    int index;

    LeftShiftArgumentsNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLeftShiftArgument() {
      return and();
    }

  }

  public class LessThanArgumentsNested<N> extends LessThanFluent<LessThanArgumentsNested<N>> implements Nested<N> {

    LessThanBuilder builder;
    int index;

    LessThanArgumentsNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLessThanArgument() {
      return and();
    }

  }

  public class LessThanOrEqualArgumentsNested<N> extends LessThanOrEqualFluent<LessThanOrEqualArgumentsNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;
    int index;

    LessThanOrEqualArgumentsNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLessThanOrEqualArgument() {
      return and();
    }

  }

  public class LogicalAndArgumentsNested<N> extends LogicalAndFluent<LogicalAndArgumentsNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;
    int index;

    LogicalAndArgumentsNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLogicalAndArgument() {
      return and();
    }

  }

  public class LogicalOrArgumentsNested<N> extends LogicalOrFluent<LogicalOrArgumentsNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;
    int index;

    LogicalOrArgumentsNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLogicalOrArgument() {
      return and();
    }

  }

  public class MethodCallArgumentsNested<N> extends MethodCallFluent<MethodCallArgumentsNested<N>> implements Nested<N> {

    MethodCallBuilder builder;
    int index;

    MethodCallArgumentsNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endMethodCallArgument() {
      return and();
    }

  }

  public class MinusArgumentsNested<N> extends MinusFluent<MinusArgumentsNested<N>> implements Nested<N> {

    MinusBuilder builder;
    int index;

    MinusArgumentsNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endMinusArgument() {
      return and();
    }

  }

  public class ModuloArgumentsNested<N> extends ModuloFluent<ModuloArgumentsNested<N>> implements Nested<N> {

    ModuloBuilder builder;
    int index;

    ModuloArgumentsNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endModuloArgument() {
      return and();
    }

  }

  public class MultiplyArgumentsNested<N> extends MultiplyFluent<MultiplyArgumentsNested<N>> implements Nested<N> {

    MultiplyBuilder builder;
    int index;

    MultiplyArgumentsNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endMultiplyArgument() {
      return and();
    }

  }

  public class NegativeArgumentsNested<N> extends NegativeFluent<NegativeArgumentsNested<N>> implements Nested<N> {

    NegativeBuilder builder;
    int index;

    NegativeArgumentsNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNegativeArgument() {
      return and();
    }

  }

  public class NewArrayArgumentsNested<N> extends NewArrayFluent<NewArrayArgumentsNested<N>> implements Nested<N> {

    NewArrayBuilder builder;
    int index;

    NewArrayArgumentsNested(int index, NewArray item) {
      this.index = index;
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNewArrayArgument() {
      return and();
    }

  }

  public class NotArgumentsNested<N> extends NotFluent<NotArgumentsNested<N>> implements Nested<N> {

    NotBuilder builder;
    int index;

    NotArgumentsNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNotArgument() {
      return and();
    }

  }

  public class NotEqualsArgumentsNested<N> extends NotEqualsFluent<NotEqualsArgumentsNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;
    int index;

    NotEqualsArgumentsNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNotEqualsArgument() {
      return and();
    }

  }

  public class PlusArgumentsNested<N> extends PlusFluent<PlusArgumentsNested<N>> implements Nested<N> {

    PlusBuilder builder;
    int index;

    PlusArgumentsNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPlusArgument() {
      return and();
    }

  }

  public class PositiveArgumentsNested<N> extends PositiveFluent<PositiveArgumentsNested<N>> implements Nested<N> {

    PositiveBuilder builder;
    int index;

    PositiveArgumentsNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPositiveArgument() {
      return and();
    }

  }

  public class PostDecrementArgumentsNested<N> extends PostDecrementFluent<PostDecrementArgumentsNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;
    int index;

    PostDecrementArgumentsNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPostDecrementArgument() {
      return and();
    }

  }

  public class PostIncrementArgumentsNested<N> extends PostIncrementFluent<PostIncrementArgumentsNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;
    int index;

    PostIncrementArgumentsNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPostIncrementArgument() {
      return and();
    }

  }

  public class PreDecrementArgumentsNested<N> extends PreDecrementFluent<PreDecrementArgumentsNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;
    int index;

    PreDecrementArgumentsNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPreDecrementArgument() {
      return and();
    }

  }

  public class PreIncrementArgumentsNested<N> extends PreIncrementFluent<PreIncrementArgumentsNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;
    int index;

    PreIncrementArgumentsNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPreIncrementArgument() {
      return and();
    }

  }

  public class PrimitiveRefParametersNested<N> extends PrimitiveRefFluent<PrimitiveRefParametersNested<N>>
      implements Nested<N> {

    PrimitiveRefBuilder builder;
    int index;

    PrimitiveRefParametersNested(int index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endPrimitiveRefParameter() {
      return and();
    }

  }

  public class PropertyArgumentsNested<N> extends PropertyFluent<PropertyArgumentsNested<N>> implements Nested<N> {

    PropertyBuilder builder;
    int index;

    PropertyArgumentsNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPropertyArgument() {
      return and();
    }

  }

  public class PropertyRefArgumentsNested<N> extends PropertyRefFluent<PropertyRefArgumentsNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;
    int index;

    PropertyRefArgumentsNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPropertyRefArgument() {
      return and();
    }

  }

  public class RightShiftArgumentsNested<N> extends RightShiftFluent<RightShiftArgumentsNested<N>> implements Nested<N> {

    RightShiftBuilder builder;
    int index;

    RightShiftArgumentsNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endRightShiftArgument() {
      return and();
    }

  }

  public class RightUnsignedShiftArgumentsNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftArgumentsNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;
    int index;

    RightUnsignedShiftArgumentsNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endRightUnsignedShiftArgument() {
      return and();
    }

  }

  public class SuperArgumentsNested<N> extends SuperFluent<SuperArgumentsNested<N>> implements Nested<N> {

    SuperBuilder builder;
    int index;

    SuperArgumentsNested(int index, Super item) {
      this.index = index;
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endSuperArgument() {
      return and();
    }

  }

  public class TernaryArgumentsNested<N> extends TernaryFluent<TernaryArgumentsNested<N>> implements Nested<N> {

    TernaryBuilder builder;
    int index;

    TernaryArgumentsNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endTernaryArgument() {
      return and();
    }

  }

  public class ThisArgumentsNested<N> extends ThisFluent<ThisArgumentsNested<N>> implements Nested<N> {

    ThisBuilder builder;
    int index;

    ThisArgumentsNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endThisArgument() {
      return and();
    }

  }

  public class TypeNested<N> extends ClassRefFluent<TypeNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    TypeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.withType(builder.build());
    }

    public N endType() {
      return and();
    }

  }

  public class TypeParamRefParametersNested<N> extends TypeParamRefFluent<TypeParamRefParametersNested<N>>
      implements Nested<N> {

    TypeParamRefBuilder builder;
    int index;

    TypeParamRefParametersNested(int index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endTypeParamRefParameter() {
      return and();
    }

  }

  public class ValueRefArgumentsNested<N> extends ValueRefFluent<ValueRefArgumentsNested<N>> implements Nested<N> {

    ValueRefBuilder builder;
    int index;

    ValueRefArgumentsNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endValueRefArgument() {
      return and();
    }

  }

  public class VoidRefParametersNested<N> extends VoidRefFluent<VoidRefParametersNested<N>> implements Nested<N> {

    VoidRefBuilder builder;
    int index;

    VoidRefParametersNested(int index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endVoidRefParameter() {
      return and();
    }

  }

  public class WildcardRefParametersNested<N> extends WildcardRefFluent<WildcardRefParametersNested<N>> implements Nested<N> {

    WildcardRefBuilder builder;
    int index;

    WildcardRefParametersNested(int index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endWildcardRefParameter() {
      return and();
    }

  }

  public class XorArgumentsNested<N> extends XorFluent<XorArgumentsNested<N>> implements Nested<N> {

    XorBuilder builder;
    int index;

    XorArgumentsNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endXorArgument() {
      return and();
    }

  }
}
