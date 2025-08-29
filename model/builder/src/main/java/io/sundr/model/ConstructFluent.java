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
public class ConstructFluent<A extends ConstructFluent<A>> extends BaseFluent<A> {
  public ConstructFluent() {
  }

  public ConstructFluent(Construct instance) {
    this.copyInstance(instance);
  }

  private ClassRefBuilder type;
  private ArrayList<VisitableBuilder<? extends TypeRef, ?>> parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
  private ArrayList<VisitableBuilder<? extends Expression, ?>> arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();

  protected void copyInstance(Construct instance) {
    if (instance != null) {
      this.withType(instance.getType());
      this.withParameters(instance.getParameters());
      this.withArguments(instance.getArguments());
    }
  }

  public ClassRef buildType() {
    return this.type != null ? this.type.build() : null;
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

  public boolean hasType() {
    return this.type != null;
  }

  public TypeNested<A> withNewType() {
    return new TypeNested(null);
  }

  public TypeNested<A> withNewTypeLike(ClassRef item) {
    return new TypeNested(item);
  }

  public TypeNested<A> editType() {
    return withNewTypeLike(java.util.Optional.ofNullable(buildType()).orElse(null));
  }

  public TypeNested<A> editOrNewType() {
    return withNewTypeLike(java.util.Optional.ofNullable(buildType()).orElse(new ClassRefBuilder().build()));
  }

  public TypeNested<A> editOrNewTypeLike(ClassRef item) {
    return withNewTypeLike(java.util.Optional.ofNullable(buildType()).orElse(item));
  }

  public A addToParameters(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("parameters").add(builder);
    this.parameters.add(builder);
    return (A) this;
  }

  public A addToParameters(int index, VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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
      this.parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A setToParameters(int index, TypeRef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A addToParameters(io.sundr.model.TypeRef... items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A addAllToParameters(Collection<TypeRef> items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A removeFromParameters(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.parameters == null)
      return (A) this;
    _visitables.get("parameters").remove(builder);
    this.parameters.remove(builder);
    return (A) this;
  }

  public A removeFromParameters(io.sundr.model.TypeRef... items) {
    if (this.parameters == null)
      return (A) this;
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromParameters(Collection<TypeRef> items) {
    if (this.parameters == null)
      return (A) this;
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromParameters(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (parameters == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = parameters.iterator();
    final List visitables = _visitables.get("parameters");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<TypeRef> buildParameters() {
    return build(parameters);
  }

  public TypeRef buildParameter(int index) {
    return this.parameters.get(index).build();
  }

  public TypeRef buildFirstParameter() {
    return this.parameters.get(0).build();
  }

  public TypeRef buildLastParameter() {
    return this.parameters.get(parameters.size() - 1).build();
  }

  public TypeRef buildMatchingParameter(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : parameters) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingParameter(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
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

  public A withParameters(io.sundr.model.TypeRef... parameters) {
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

  public boolean hasParameters() {
    return this.parameters != null && !this.parameters.isEmpty();
  }

  public ClassRefParametersNested<A> addNewClassRefParameter() {
    return new ClassRefParametersNested(-1, null);
  }

  public ClassRefParametersNested<A> addNewClassRefParameterLike(ClassRef item) {
    return new ClassRefParametersNested(-1, item);
  }

  public ClassRefParametersNested<A> setNewClassRefParameterLike(int index, ClassRef item) {
    return new ClassRefParametersNested(index, item);
  }

  public PrimitiveRefParametersNested<A> addNewPrimitiveRefParameter() {
    return new PrimitiveRefParametersNested(-1, null);
  }

  public PrimitiveRefParametersNested<A> addNewPrimitiveRefParameterLike(PrimitiveRef item) {
    return new PrimitiveRefParametersNested(-1, item);
  }

  public PrimitiveRefParametersNested<A> setNewPrimitiveRefParameterLike(int index, PrimitiveRef item) {
    return new PrimitiveRefParametersNested(index, item);
  }

  public VoidRefParametersNested<A> addNewVoidRefParameter() {
    return new VoidRefParametersNested(-1, null);
  }

  public VoidRefParametersNested<A> addNewVoidRefParameterLike(VoidRef item) {
    return new VoidRefParametersNested(-1, item);
  }

  public VoidRefParametersNested<A> setNewVoidRefParameterLike(int index, VoidRef item) {
    return new VoidRefParametersNested(index, item);
  }

  public TypeParamRefParametersNested<A> addNewTypeParamRefParameter() {
    return new TypeParamRefParametersNested(-1, null);
  }

  public TypeParamRefParametersNested<A> addNewTypeParamRefParameterLike(TypeParamRef item) {
    return new TypeParamRefParametersNested(-1, item);
  }

  public TypeParamRefParametersNested<A> setNewTypeParamRefParameterLike(int index, TypeParamRef item) {
    return new TypeParamRefParametersNested(index, item);
  }

  public WildcardRefParametersNested<A> addNewWildcardRefParameter() {
    return new WildcardRefParametersNested(-1, null);
  }

  public WildcardRefParametersNested<A> addNewWildcardRefParameterLike(WildcardRef item) {
    return new WildcardRefParametersNested(-1, item);
  }

  public WildcardRefParametersNested<A> setNewWildcardRefParameterLike(int index, WildcardRef item) {
    return new WildcardRefParametersNested(index, item);
  }

  public A addToArguments(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    _visitables.get("arguments").add(builder);
    this.arguments.add(builder);
    return (A) this;
  }

  public A addToArguments(int index, VisitableBuilder<? extends Expression, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
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
      this.arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
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

  public A setToArguments(int index, Expression item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
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

  public A addToArguments(io.sundr.model.Expression... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToArguments(Collection<Expression> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends Expression, ?>>();
    }
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromArguments(VisitableBuilder<? extends Expression, ?> builder) {
    if (this.arguments == null)
      return (A) this;
    _visitables.get("arguments").remove(builder);
    this.arguments.remove(builder);
    return (A) this;
  }

  public A removeFromArguments(io.sundr.model.Expression... items) {
    if (this.arguments == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromArguments(Collection<Expression> items) {
    if (this.arguments == null)
      return (A) this;
    for (Expression item : items) {
      VisitableBuilder<? extends Expression, ?> builder = builder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromArguments(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends Expression, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      VisitableBuilder<? extends Expression, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<Expression> buildArguments() {
    return build(arguments);
  }

  public Expression buildArgument(int index) {
    return this.arguments.get(index).build();
  }

  public Expression buildFirstArgument() {
    return this.arguments.get(0).build();
  }

  public Expression buildLastArgument() {
    return this.arguments.get(arguments.size() - 1).build();
  }

  public Expression buildMatchingArgument(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : arguments) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends Expression, ?>> predicate) {
    for (VisitableBuilder<? extends Expression, ?> item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
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

  public A withArguments(io.sundr.model.Expression... arguments) {
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

  public boolean hasArguments() {
    return this.arguments != null && !this.arguments.isEmpty();
  }

  public MultiplyArgumentsNested<A> addNewMultiplyArgument() {
    return new MultiplyArgumentsNested(-1, null);
  }

  public MultiplyArgumentsNested<A> addNewMultiplyArgumentLike(Multiply item) {
    return new MultiplyArgumentsNested(-1, item);
  }

  public A addNewMultiplyArgument(Object left, Object right) {
    return (A) addToArguments(new Multiply(left, right));
  }

  public MultiplyArgumentsNested<A> setNewMultiplyArgumentLike(int index, Multiply item) {
    return new MultiplyArgumentsNested(index, item);
  }

  public NewArrayArgumentsNested<A> addNewNewArrayArgument() {
    return new NewArrayArgumentsNested(-1, null);
  }

  public NewArrayArgumentsNested<A> addNewNewArrayArgumentLike(NewArray item) {
    return new NewArrayArgumentsNested(-1, item);
  }

  public A addNewNewArrayArgument(Class type, Integer[] sizes) {
    return (A) addToArguments(new NewArray(type, sizes));
  }

  public NewArrayArgumentsNested<A> setNewNewArrayArgumentLike(int index, NewArray item) {
    return new NewArrayArgumentsNested(index, item);
  }

  public InstanceOfArgumentsNested<A> addNewInstanceOfArgument() {
    return new InstanceOfArgumentsNested(-1, null);
  }

  public InstanceOfArgumentsNested<A> addNewInstanceOfArgumentLike(InstanceOf item) {
    return new InstanceOfArgumentsNested(-1, item);
  }

  public InstanceOfArgumentsNested<A> setNewInstanceOfArgumentLike(int index, InstanceOf item) {
    return new InstanceOfArgumentsNested(index, item);
  }

  public MethodCallArgumentsNested<A> addNewMethodCallArgument() {
    return new MethodCallArgumentsNested(-1, null);
  }

  public MethodCallArgumentsNested<A> addNewMethodCallArgumentLike(MethodCall item) {
    return new MethodCallArgumentsNested(-1, item);
  }

  public MethodCallArgumentsNested<A> setNewMethodCallArgumentLike(int index, MethodCall item) {
    return new MethodCallArgumentsNested(index, item);
  }

  public InverseArgumentsNested<A> addNewInverseArgument() {
    return new InverseArgumentsNested(-1, null);
  }

  public InverseArgumentsNested<A> addNewInverseArgumentLike(Inverse item) {
    return new InverseArgumentsNested(-1, item);
  }

  public InverseArgumentsNested<A> setNewInverseArgumentLike(int index, Inverse item) {
    return new InverseArgumentsNested(index, item);
  }

  public IndexArgumentsNested<A> addNewIndexArgument() {
    return new IndexArgumentsNested(-1, null);
  }

  public IndexArgumentsNested<A> addNewIndexArgumentLike(Index item) {
    return new IndexArgumentsNested(-1, item);
  }

  public IndexArgumentsNested<A> setNewIndexArgumentLike(int index, Index item) {
    return new IndexArgumentsNested(index, item);
  }

  public GreaterThanOrEqualArgumentsNested<A> addNewGreaterThanOrEqualArgument() {
    return new GreaterThanOrEqualArgumentsNested(-1, null);
  }

  public GreaterThanOrEqualArgumentsNested<A> addNewGreaterThanOrEqualArgumentLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualArgumentsNested(-1, item);
  }

  public A addNewGreaterThanOrEqualArgument(Object left, Object right) {
    return (A) addToArguments(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualArgumentsNested<A> setNewGreaterThanOrEqualArgumentLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualArgumentsNested(index, item);
  }

  public BitwiseAndArgumentsNested<A> addNewBitwiseAndArgument() {
    return new BitwiseAndArgumentsNested(-1, null);
  }

  public BitwiseAndArgumentsNested<A> addNewBitwiseAndArgumentLike(BitwiseAnd item) {
    return new BitwiseAndArgumentsNested(-1, item);
  }

  public A addNewBitwiseAndArgument(Object left, Object right) {
    return (A) addToArguments(new BitwiseAnd(left, right));
  }

  public BitwiseAndArgumentsNested<A> setNewBitwiseAndArgumentLike(int index, BitwiseAnd item) {
    return new BitwiseAndArgumentsNested(index, item);
  }

  public MinusArgumentsNested<A> addNewMinusArgument() {
    return new MinusArgumentsNested(-1, null);
  }

  public MinusArgumentsNested<A> addNewMinusArgumentLike(Minus item) {
    return new MinusArgumentsNested(-1, item);
  }

  public A addNewMinusArgument(Object left, Object right) {
    return (A) addToArguments(new Minus(left, right));
  }

  public MinusArgumentsNested<A> setNewMinusArgumentLike(int index, Minus item) {
    return new MinusArgumentsNested(index, item);
  }

  public LogicalOrArgumentsNested<A> addNewLogicalOrArgument() {
    return new LogicalOrArgumentsNested(-1, null);
  }

  public LogicalOrArgumentsNested<A> addNewLogicalOrArgumentLike(LogicalOr item) {
    return new LogicalOrArgumentsNested(-1, item);
  }

  public A addNewLogicalOrArgument(Object left, Object right) {
    return (A) addToArguments(new LogicalOr(left, right));
  }

  public LogicalOrArgumentsNested<A> setNewLogicalOrArgumentLike(int index, LogicalOr item) {
    return new LogicalOrArgumentsNested(index, item);
  }

  public NotEqualsArgumentsNested<A> addNewNotEqualsArgument() {
    return new NotEqualsArgumentsNested(-1, null);
  }

  public NotEqualsArgumentsNested<A> addNewNotEqualsArgumentLike(NotEquals item) {
    return new NotEqualsArgumentsNested(-1, item);
  }

  public A addNewNotEqualsArgument(Object left, Object right) {
    return (A) addToArguments(new NotEquals(left, right));
  }

  public NotEqualsArgumentsNested<A> setNewNotEqualsArgumentLike(int index, NotEquals item) {
    return new NotEqualsArgumentsNested(index, item);
  }

  public DivideArgumentsNested<A> addNewDivideArgument() {
    return new DivideArgumentsNested(-1, null);
  }

  public DivideArgumentsNested<A> addNewDivideArgumentLike(Divide item) {
    return new DivideArgumentsNested(-1, item);
  }

  public A addNewDivideArgument(Object left, Object right) {
    return (A) addToArguments(new Divide(left, right));
  }

  public DivideArgumentsNested<A> setNewDivideArgumentLike(int index, Divide item) {
    return new DivideArgumentsNested(index, item);
  }

  public LessThanArgumentsNested<A> addNewLessThanArgument() {
    return new LessThanArgumentsNested(-1, null);
  }

  public LessThanArgumentsNested<A> addNewLessThanArgumentLike(LessThan item) {
    return new LessThanArgumentsNested(-1, item);
  }

  public A addNewLessThanArgument(Object left, Object right) {
    return (A) addToArguments(new LessThan(left, right));
  }

  public LessThanArgumentsNested<A> setNewLessThanArgumentLike(int index, LessThan item) {
    return new LessThanArgumentsNested(index, item);
  }

  public BitwiseOrArgumentsNested<A> addNewBitwiseOrArgument() {
    return new BitwiseOrArgumentsNested(-1, null);
  }

  public BitwiseOrArgumentsNested<A> addNewBitwiseOrArgumentLike(BitwiseOr item) {
    return new BitwiseOrArgumentsNested(-1, item);
  }

  public A addNewBitwiseOrArgument(Object left, Object right) {
    return (A) addToArguments(new BitwiseOr(left, right));
  }

  public BitwiseOrArgumentsNested<A> setNewBitwiseOrArgumentLike(int index, BitwiseOr item) {
    return new BitwiseOrArgumentsNested(index, item);
  }

  public PropertyRefArgumentsNested<A> addNewPropertyRefArgument() {
    return new PropertyRefArgumentsNested(-1, null);
  }

  public PropertyRefArgumentsNested<A> addNewPropertyRefArgumentLike(PropertyRef item) {
    return new PropertyRefArgumentsNested(-1, item);
  }

  public PropertyRefArgumentsNested<A> setNewPropertyRefArgumentLike(int index, PropertyRef item) {
    return new PropertyRefArgumentsNested(index, item);
  }

  public RightShiftArgumentsNested<A> addNewRightShiftArgument() {
    return new RightShiftArgumentsNested(-1, null);
  }

  public RightShiftArgumentsNested<A> addNewRightShiftArgumentLike(RightShift item) {
    return new RightShiftArgumentsNested(-1, item);
  }

  public A addNewRightShiftArgument(Object left, Object right) {
    return (A) addToArguments(new RightShift(left, right));
  }

  public RightShiftArgumentsNested<A> setNewRightShiftArgumentLike(int index, RightShift item) {
    return new RightShiftArgumentsNested(index, item);
  }

  public GreaterThanArgumentsNested<A> addNewGreaterThanArgument() {
    return new GreaterThanArgumentsNested(-1, null);
  }

  public GreaterThanArgumentsNested<A> addNewGreaterThanArgumentLike(GreaterThan item) {
    return new GreaterThanArgumentsNested(-1, item);
  }

  public A addNewGreaterThanArgument(Object left, Object right) {
    return (A) addToArguments(new GreaterThan(left, right));
  }

  public GreaterThanArgumentsNested<A> setNewGreaterThanArgumentLike(int index, GreaterThan item) {
    return new GreaterThanArgumentsNested(index, item);
  }

  public DeclareArgumentsNested<A> addNewDeclareArgument() {
    return new DeclareArgumentsNested(-1, null);
  }

  public DeclareArgumentsNested<A> addNewDeclareArgumentLike(Declare item) {
    return new DeclareArgumentsNested(-1, item);
  }

  public A addNewDeclareArgument(Class type, String name) {
    return (A) addToArguments(new Declare(type, name));
  }

  public A addNewDeclareArgument(Class type, String name, Object value) {
    return (A) addToArguments(new Declare(type, name, value));
  }

  public DeclareArgumentsNested<A> setNewDeclareArgumentLike(int index, Declare item) {
    return new DeclareArgumentsNested(index, item);
  }

  public CastArgumentsNested<A> addNewCastArgument() {
    return new CastArgumentsNested(-1, null);
  }

  public CastArgumentsNested<A> addNewCastArgumentLike(Cast item) {
    return new CastArgumentsNested(-1, item);
  }

  public CastArgumentsNested<A> setNewCastArgumentLike(int index, Cast item) {
    return new CastArgumentsNested(index, item);
  }

  public ModuloArgumentsNested<A> addNewModuloArgument() {
    return new ModuloArgumentsNested(-1, null);
  }

  public ModuloArgumentsNested<A> addNewModuloArgumentLike(Modulo item) {
    return new ModuloArgumentsNested(-1, item);
  }

  public A addNewModuloArgument(Object left, Object right) {
    return (A) addToArguments(new Modulo(left, right));
  }

  public ModuloArgumentsNested<A> setNewModuloArgumentLike(int index, Modulo item) {
    return new ModuloArgumentsNested(index, item);
  }

  public ValueRefArgumentsNested<A> addNewValueRefArgument() {
    return new ValueRefArgumentsNested(-1, null);
  }

  public ValueRefArgumentsNested<A> addNewValueRefArgumentLike(ValueRef item) {
    return new ValueRefArgumentsNested(-1, item);
  }

  public A addNewValueRefArgument(Object value) {
    return (A) addToArguments(new ValueRef(value));
  }

  public ValueRefArgumentsNested<A> setNewValueRefArgumentLike(int index, ValueRef item) {
    return new ValueRefArgumentsNested(index, item);
  }

  public LeftShiftArgumentsNested<A> addNewLeftShiftArgument() {
    return new LeftShiftArgumentsNested(-1, null);
  }

  public LeftShiftArgumentsNested<A> addNewLeftShiftArgumentLike(LeftShift item) {
    return new LeftShiftArgumentsNested(-1, item);
  }

  public A addNewLeftShiftArgument(Object left, Object right) {
    return (A) addToArguments(new LeftShift(left, right));
  }

  public LeftShiftArgumentsNested<A> setNewLeftShiftArgumentLike(int index, LeftShift item) {
    return new LeftShiftArgumentsNested(index, item);
  }

  public TernaryArgumentsNested<A> addNewTernaryArgument() {
    return new TernaryArgumentsNested(-1, null);
  }

  public TernaryArgumentsNested<A> addNewTernaryArgumentLike(Ternary item) {
    return new TernaryArgumentsNested(-1, item);
  }

  public TernaryArgumentsNested<A> setNewTernaryArgumentLike(int index, Ternary item) {
    return new TernaryArgumentsNested(index, item);
  }

  public BinaryExpressionArgumentsNested<A> addNewBinaryExpressionArgument() {
    return new BinaryExpressionArgumentsNested(-1, null);
  }

  public BinaryExpressionArgumentsNested<A> addNewBinaryExpressionArgumentLike(BinaryExpression item) {
    return new BinaryExpressionArgumentsNested(-1, item);
  }

  public BinaryExpressionArgumentsNested<A> setNewBinaryExpressionArgumentLike(int index, BinaryExpression item) {
    return new BinaryExpressionArgumentsNested(index, item);
  }

  public EqualsArgumentsNested<A> addNewEqualsArgument() {
    return new EqualsArgumentsNested(-1, null);
  }

  public EqualsArgumentsNested<A> addNewEqualsArgumentLike(Equals item) {
    return new EqualsArgumentsNested(-1, item);
  }

  public A addNewEqualsArgument(Object left, Object right) {
    return (A) addToArguments(new Equals(left, right));
  }

  public EqualsArgumentsNested<A> setNewEqualsArgumentLike(int index, Equals item) {
    return new EqualsArgumentsNested(index, item);
  }

  public EnclosedArgumentsNested<A> addNewEnclosedArgument() {
    return new EnclosedArgumentsNested(-1, null);
  }

  public EnclosedArgumentsNested<A> addNewEnclosedArgumentLike(Enclosed item) {
    return new EnclosedArgumentsNested(-1, item);
  }

  public EnclosedArgumentsNested<A> setNewEnclosedArgumentLike(int index, Enclosed item) {
    return new EnclosedArgumentsNested(index, item);
  }

  public PreDecrementArgumentsNested<A> addNewPreDecrementArgument() {
    return new PreDecrementArgumentsNested(-1, null);
  }

  public PreDecrementArgumentsNested<A> addNewPreDecrementArgumentLike(PreDecrement item) {
    return new PreDecrementArgumentsNested(-1, item);
  }

  public PreDecrementArgumentsNested<A> setNewPreDecrementArgumentLike(int index, PreDecrement item) {
    return new PreDecrementArgumentsNested(index, item);
  }

  public PostDecrementArgumentsNested<A> addNewPostDecrementArgument() {
    return new PostDecrementArgumentsNested(-1, null);
  }

  public PostDecrementArgumentsNested<A> addNewPostDecrementArgumentLike(PostDecrement item) {
    return new PostDecrementArgumentsNested(-1, item);
  }

  public PostDecrementArgumentsNested<A> setNewPostDecrementArgumentLike(int index, PostDecrement item) {
    return new PostDecrementArgumentsNested(index, item);
  }

  public LambdaArgumentsNested<A> addNewLambdaArgument() {
    return new LambdaArgumentsNested(-1, null);
  }

  public LambdaArgumentsNested<A> addNewLambdaArgumentLike(Lambda item) {
    return new LambdaArgumentsNested(-1, item);
  }

  public LambdaArgumentsNested<A> setNewLambdaArgumentLike(int index, Lambda item) {
    return new LambdaArgumentsNested(index, item);
  }

  public NotArgumentsNested<A> addNewNotArgument() {
    return new NotArgumentsNested(-1, null);
  }

  public NotArgumentsNested<A> addNewNotArgumentLike(Not item) {
    return new NotArgumentsNested(-1, item);
  }

  public NotArgumentsNested<A> setNewNotArgumentLike(int index, Not item) {
    return new NotArgumentsNested(index, item);
  }

  public AssignArgumentsNested<A> addNewAssignArgument() {
    return new AssignArgumentsNested(-1, null);
  }

  public AssignArgumentsNested<A> addNewAssignArgumentLike(Assign item) {
    return new AssignArgumentsNested(-1, item);
  }

  public AssignArgumentsNested<A> setNewAssignArgumentLike(int index, Assign item) {
    return new AssignArgumentsNested(index, item);
  }

  public ThisArgumentsNested<A> addNewThisArgument() {
    return new ThisArgumentsNested(-1, null);
  }

  public ThisArgumentsNested<A> addNewThisArgumentLike(This item) {
    return new ThisArgumentsNested(-1, item);
  }

  public ThisArgumentsNested<A> setNewThisArgumentLike(int index, This item) {
    return new ThisArgumentsNested(index, item);
  }

  public NegativeArgumentsNested<A> addNewNegativeArgument() {
    return new NegativeArgumentsNested(-1, null);
  }

  public NegativeArgumentsNested<A> addNewNegativeArgumentLike(Negative item) {
    return new NegativeArgumentsNested(-1, item);
  }

  public NegativeArgumentsNested<A> setNewNegativeArgumentLike(int index, Negative item) {
    return new NegativeArgumentsNested(index, item);
  }

  public LogicalAndArgumentsNested<A> addNewLogicalAndArgument() {
    return new LogicalAndArgumentsNested(-1, null);
  }

  public LogicalAndArgumentsNested<A> addNewLogicalAndArgumentLike(LogicalAnd item) {
    return new LogicalAndArgumentsNested(-1, item);
  }

  public A addNewLogicalAndArgument(Object left, Object right) {
    return (A) addToArguments(new LogicalAnd(left, right));
  }

  public LogicalAndArgumentsNested<A> setNewLogicalAndArgumentLike(int index, LogicalAnd item) {
    return new LogicalAndArgumentsNested(index, item);
  }

  public PostIncrementArgumentsNested<A> addNewPostIncrementArgument() {
    return new PostIncrementArgumentsNested(-1, null);
  }

  public PostIncrementArgumentsNested<A> addNewPostIncrementArgumentLike(PostIncrement item) {
    return new PostIncrementArgumentsNested(-1, item);
  }

  public PostIncrementArgumentsNested<A> setNewPostIncrementArgumentLike(int index, PostIncrement item) {
    return new PostIncrementArgumentsNested(index, item);
  }

  public RightUnsignedShiftArgumentsNested<A> addNewRightUnsignedShiftArgument() {
    return new RightUnsignedShiftArgumentsNested(-1, null);
  }

  public RightUnsignedShiftArgumentsNested<A> addNewRightUnsignedShiftArgumentLike(RightUnsignedShift item) {
    return new RightUnsignedShiftArgumentsNested(-1, item);
  }

  public A addNewRightUnsignedShiftArgument(Object left, Object right) {
    return (A) addToArguments(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftArgumentsNested<A> setNewRightUnsignedShiftArgumentLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftArgumentsNested(index, item);
  }

  public PlusArgumentsNested<A> addNewPlusArgument() {
    return new PlusArgumentsNested(-1, null);
  }

  public PlusArgumentsNested<A> addNewPlusArgumentLike(Plus item) {
    return new PlusArgumentsNested(-1, item);
  }

  public A addNewPlusArgument(Object left, Object right) {
    return (A) addToArguments(new Plus(left, right));
  }

  public PlusArgumentsNested<A> setNewPlusArgumentLike(int index, Plus item) {
    return new PlusArgumentsNested(index, item);
  }

  public ConstructArgumentsNested<A> addNewConstructArgument() {
    return new ConstructArgumentsNested(-1, null);
  }

  public ConstructArgumentsNested<A> addNewConstructArgumentLike(Construct item) {
    return new ConstructArgumentsNested(-1, item);
  }

  public ConstructArgumentsNested<A> setNewConstructArgumentLike(int index, Construct item) {
    return new ConstructArgumentsNested(index, item);
  }

  public XorArgumentsNested<A> addNewXorArgument() {
    return new XorArgumentsNested(-1, null);
  }

  public XorArgumentsNested<A> addNewXorArgumentLike(Xor item) {
    return new XorArgumentsNested(-1, item);
  }

  public A addNewXorArgument(Object left, Object right) {
    return (A) addToArguments(new Xor(left, right));
  }

  public XorArgumentsNested<A> setNewXorArgumentLike(int index, Xor item) {
    return new XorArgumentsNested(index, item);
  }

  public PreIncrementArgumentsNested<A> addNewPreIncrementArgument() {
    return new PreIncrementArgumentsNested(-1, null);
  }

  public PreIncrementArgumentsNested<A> addNewPreIncrementArgumentLike(PreIncrement item) {
    return new PreIncrementArgumentsNested(-1, item);
  }

  public PreIncrementArgumentsNested<A> setNewPreIncrementArgumentLike(int index, PreIncrement item) {
    return new PreIncrementArgumentsNested(index, item);
  }

  public PropertyArgumentsNested<A> addNewPropertyArgument() {
    return new PropertyArgumentsNested(-1, null);
  }

  public PropertyArgumentsNested<A> addNewPropertyArgumentLike(Property item) {
    return new PropertyArgumentsNested(-1, item);
  }

  public PropertyArgumentsNested<A> setNewPropertyArgumentLike(int index, Property item) {
    return new PropertyArgumentsNested(index, item);
  }

  public LessThanOrEqualArgumentsNested<A> addNewLessThanOrEqualArgument() {
    return new LessThanOrEqualArgumentsNested(-1, null);
  }

  public LessThanOrEqualArgumentsNested<A> addNewLessThanOrEqualArgumentLike(LessThanOrEqual item) {
    return new LessThanOrEqualArgumentsNested(-1, item);
  }

  public A addNewLessThanOrEqualArgument(Object left, Object right) {
    return (A) addToArguments(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualArgumentsNested<A> setNewLessThanOrEqualArgumentLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualArgumentsNested(index, item);
  }

  public PositiveArgumentsNested<A> addNewPositiveArgument() {
    return new PositiveArgumentsNested(-1, null);
  }

  public PositiveArgumentsNested<A> addNewPositiveArgumentLike(Positive item) {
    return new PositiveArgumentsNested(-1, item);
  }

  public PositiveArgumentsNested<A> setNewPositiveArgumentLike(int index, Positive item) {
    return new PositiveArgumentsNested(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ConstructFluent that = (ConstructFluent) o;
    if (!java.util.Objects.equals(type, that.type))
      return false;
    if (!java.util.Objects.equals(parameters, that.parameters))
      return false;
    if (!java.util.Objects.equals(arguments, that.arguments))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(type, parameters, arguments, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (type != null) {
      sb.append("type:");
      sb.append(type + ",");
    }
    if (parameters != null && !parameters.isEmpty()) {
      sb.append("parameters:");
      sb.append(parameters + ",");
    }
    if (arguments != null && !arguments.isEmpty()) {
      sb.append("arguments:");
      sb.append(arguments);
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
      case "io.sundr.model." + "Positive":
        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class TypeNested<N> extends ClassRefFluent<TypeNested<N>> implements Nested<N> {
    TypeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;

    public N and() {
      return (N) ConstructFluent.this.withType(builder.build());
    }

    public N endType() {
      return and();
    }

  }

  public class ClassRefParametersNested<N> extends ClassRefFluent<ClassRefParametersNested<N>> implements Nested<N> {
    ClassRefParametersNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endClassRefParameter() {
      return and();
    }

  }

  public class PrimitiveRefParametersNested<N> extends PrimitiveRefFluent<PrimitiveRefParametersNested<N>>
      implements Nested<N> {
    PrimitiveRefParametersNested(int index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endPrimitiveRefParameter() {
      return and();
    }

  }

  public class VoidRefParametersNested<N> extends VoidRefFluent<VoidRefParametersNested<N>> implements Nested<N> {
    VoidRefParametersNested(int index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endVoidRefParameter() {
      return and();
    }

  }

  public class TypeParamRefParametersNested<N> extends TypeParamRefFluent<TypeParamRefParametersNested<N>>
      implements Nested<N> {
    TypeParamRefParametersNested(int index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endTypeParamRefParameter() {
      return and();
    }

  }

  public class WildcardRefParametersNested<N> extends WildcardRefFluent<WildcardRefParametersNested<N>> implements Nested<N> {
    WildcardRefParametersNested(int index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToParameters(index, builder.build());
    }

    public N endWildcardRefParameter() {
      return and();
    }

  }

  public class MultiplyArgumentsNested<N> extends MultiplyFluent<MultiplyArgumentsNested<N>> implements Nested<N> {
    MultiplyArgumentsNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endMultiplyArgument() {
      return and();
    }

  }

  public class NewArrayArgumentsNested<N> extends NewArrayFluent<NewArrayArgumentsNested<N>> implements Nested<N> {
    NewArrayArgumentsNested(int index, NewArray item) {
      this.index = index;
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNewArrayArgument() {
      return and();
    }

  }

  public class InstanceOfArgumentsNested<N> extends InstanceOfFluent<InstanceOfArgumentsNested<N>> implements Nested<N> {
    InstanceOfArgumentsNested(int index, InstanceOf item) {
      this.index = index;
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endInstanceOfArgument() {
      return and();
    }

  }

  public class MethodCallArgumentsNested<N> extends MethodCallFluent<MethodCallArgumentsNested<N>> implements Nested<N> {
    MethodCallArgumentsNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endMethodCallArgument() {
      return and();
    }

  }

  public class InverseArgumentsNested<N> extends InverseFluent<InverseArgumentsNested<N>> implements Nested<N> {
    InverseArgumentsNested(int index, Inverse item) {
      this.index = index;
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endInverseArgument() {
      return and();
    }

  }

  public class IndexArgumentsNested<N> extends IndexFluent<IndexArgumentsNested<N>> implements Nested<N> {
    IndexArgumentsNested(int index, Index item) {
      this.index = index;
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endIndexArgument() {
      return and();
    }

  }

  public class GreaterThanOrEqualArgumentsNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualArgumentsNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualArgumentsNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endGreaterThanOrEqualArgument() {
      return and();
    }

  }

  public class BitwiseAndArgumentsNested<N> extends BitwiseAndFluent<BitwiseAndArgumentsNested<N>> implements Nested<N> {
    BitwiseAndArgumentsNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endBitwiseAndArgument() {
      return and();
    }

  }

  public class MinusArgumentsNested<N> extends MinusFluent<MinusArgumentsNested<N>> implements Nested<N> {
    MinusArgumentsNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endMinusArgument() {
      return and();
    }

  }

  public class LogicalOrArgumentsNested<N> extends LogicalOrFluent<LogicalOrArgumentsNested<N>> implements Nested<N> {
    LogicalOrArgumentsNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLogicalOrArgument() {
      return and();
    }

  }

  public class NotEqualsArgumentsNested<N> extends NotEqualsFluent<NotEqualsArgumentsNested<N>> implements Nested<N> {
    NotEqualsArgumentsNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNotEqualsArgument() {
      return and();
    }

  }

  public class DivideArgumentsNested<N> extends DivideFluent<DivideArgumentsNested<N>> implements Nested<N> {
    DivideArgumentsNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endDivideArgument() {
      return and();
    }

  }

  public class LessThanArgumentsNested<N> extends LessThanFluent<LessThanArgumentsNested<N>> implements Nested<N> {
    LessThanArgumentsNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLessThanArgument() {
      return and();
    }

  }

  public class BitwiseOrArgumentsNested<N> extends BitwiseOrFluent<BitwiseOrArgumentsNested<N>> implements Nested<N> {
    BitwiseOrArgumentsNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endBitwiseOrArgument() {
      return and();
    }

  }

  public class PropertyRefArgumentsNested<N> extends PropertyRefFluent<PropertyRefArgumentsNested<N>> implements Nested<N> {
    PropertyRefArgumentsNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPropertyRefArgument() {
      return and();
    }

  }

  public class RightShiftArgumentsNested<N> extends RightShiftFluent<RightShiftArgumentsNested<N>> implements Nested<N> {
    RightShiftArgumentsNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endRightShiftArgument() {
      return and();
    }

  }

  public class GreaterThanArgumentsNested<N> extends GreaterThanFluent<GreaterThanArgumentsNested<N>> implements Nested<N> {
    GreaterThanArgumentsNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endGreaterThanArgument() {
      return and();
    }

  }

  public class DeclareArgumentsNested<N> extends DeclareFluent<DeclareArgumentsNested<N>> implements Nested<N> {
    DeclareArgumentsNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endDeclareArgument() {
      return and();
    }

  }

  public class CastArgumentsNested<N> extends CastFluent<CastArgumentsNested<N>> implements Nested<N> {
    CastArgumentsNested(int index, Cast item) {
      this.index = index;
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endCastArgument() {
      return and();
    }

  }

  public class ModuloArgumentsNested<N> extends ModuloFluent<ModuloArgumentsNested<N>> implements Nested<N> {
    ModuloArgumentsNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endModuloArgument() {
      return and();
    }

  }

  public class ValueRefArgumentsNested<N> extends ValueRefFluent<ValueRefArgumentsNested<N>> implements Nested<N> {
    ValueRefArgumentsNested(int index, ValueRef item) {
      this.index = index;
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endValueRefArgument() {
      return and();
    }

  }

  public class LeftShiftArgumentsNested<N> extends LeftShiftFluent<LeftShiftArgumentsNested<N>> implements Nested<N> {
    LeftShiftArgumentsNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLeftShiftArgument() {
      return and();
    }

  }

  public class TernaryArgumentsNested<N> extends TernaryFluent<TernaryArgumentsNested<N>> implements Nested<N> {
    TernaryArgumentsNested(int index, Ternary item) {
      this.index = index;
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endTernaryArgument() {
      return and();
    }

  }

  public class BinaryExpressionArgumentsNested<N> extends BinaryExpressionFluent<BinaryExpressionArgumentsNested<N>>
      implements Nested<N> {
    BinaryExpressionArgumentsNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endBinaryExpressionArgument() {
      return and();
    }

  }

  public class EqualsArgumentsNested<N> extends EqualsFluent<EqualsArgumentsNested<N>> implements Nested<N> {
    EqualsArgumentsNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endEqualsArgument() {
      return and();
    }

  }

  public class EnclosedArgumentsNested<N> extends EnclosedFluent<EnclosedArgumentsNested<N>> implements Nested<N> {
    EnclosedArgumentsNested(int index, Enclosed item) {
      this.index = index;
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endEnclosedArgument() {
      return and();
    }

  }

  public class PreDecrementArgumentsNested<N> extends PreDecrementFluent<PreDecrementArgumentsNested<N>> implements Nested<N> {
    PreDecrementArgumentsNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPreDecrementArgument() {
      return and();
    }

  }

  public class PostDecrementArgumentsNested<N> extends PostDecrementFluent<PostDecrementArgumentsNested<N>>
      implements Nested<N> {
    PostDecrementArgumentsNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPostDecrementArgument() {
      return and();
    }

  }

  public class LambdaArgumentsNested<N> extends LambdaFluent<LambdaArgumentsNested<N>> implements Nested<N> {
    LambdaArgumentsNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLambdaArgument() {
      return and();
    }

  }

  public class NotArgumentsNested<N> extends NotFluent<NotArgumentsNested<N>> implements Nested<N> {
    NotArgumentsNested(int index, Not item) {
      this.index = index;
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNotArgument() {
      return and();
    }

  }

  public class AssignArgumentsNested<N> extends AssignFluent<AssignArgumentsNested<N>> implements Nested<N> {
    AssignArgumentsNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endAssignArgument() {
      return and();
    }

  }

  public class ThisArgumentsNested<N> extends ThisFluent<ThisArgumentsNested<N>> implements Nested<N> {
    ThisArgumentsNested(int index, This item) {
      this.index = index;
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endThisArgument() {
      return and();
    }

  }

  public class NegativeArgumentsNested<N> extends NegativeFluent<NegativeArgumentsNested<N>> implements Nested<N> {
    NegativeArgumentsNested(int index, Negative item) {
      this.index = index;
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endNegativeArgument() {
      return and();
    }

  }

  public class LogicalAndArgumentsNested<N> extends LogicalAndFluent<LogicalAndArgumentsNested<N>> implements Nested<N> {
    LogicalAndArgumentsNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLogicalAndArgument() {
      return and();
    }

  }

  public class PostIncrementArgumentsNested<N> extends PostIncrementFluent<PostIncrementArgumentsNested<N>>
      implements Nested<N> {
    PostIncrementArgumentsNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPostIncrementArgument() {
      return and();
    }

  }

  public class RightUnsignedShiftArgumentsNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftArgumentsNested<N>>
      implements Nested<N> {
    RightUnsignedShiftArgumentsNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endRightUnsignedShiftArgument() {
      return and();
    }

  }

  public class PlusArgumentsNested<N> extends PlusFluent<PlusArgumentsNested<N>> implements Nested<N> {
    PlusArgumentsNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPlusArgument() {
      return and();
    }

  }

  public class ConstructArgumentsNested<N> extends ConstructFluent<ConstructArgumentsNested<N>> implements Nested<N> {
    ConstructArgumentsNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endConstructArgument() {
      return and();
    }

  }

  public class XorArgumentsNested<N> extends XorFluent<XorArgumentsNested<N>> implements Nested<N> {
    XorArgumentsNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endXorArgument() {
      return and();
    }

  }

  public class PreIncrementArgumentsNested<N> extends PreIncrementFluent<PreIncrementArgumentsNested<N>> implements Nested<N> {
    PreIncrementArgumentsNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPreIncrementArgument() {
      return and();
    }

  }

  public class PropertyArgumentsNested<N> extends PropertyFluent<PropertyArgumentsNested<N>> implements Nested<N> {
    PropertyArgumentsNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPropertyArgument() {
      return and();
    }

  }

  public class LessThanOrEqualArgumentsNested<N> extends LessThanOrEqualFluent<LessThanOrEqualArgumentsNested<N>>
      implements Nested<N> {
    LessThanOrEqualArgumentsNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endLessThanOrEqualArgument() {
      return and();
    }

  }

  public class PositiveArgumentsNested<N> extends PositiveFluent<PositiveArgumentsNested<N>> implements Nested<N> {
    PositiveArgumentsNested(int index, Positive item) {
      this.index = index;
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;
    int index;

    public N and() {
      return (N) ConstructFluent.this.setToArguments(index, builder.build());
    }

    public N endPositiveArgument() {
      return and();
    }

  }

}
