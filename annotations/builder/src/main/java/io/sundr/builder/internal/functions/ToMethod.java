/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.builder.internal.functions;

import static io.sundr.builder.Constants.DESCENDANTS;
import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.builder.Constants.GENERIC_TYPE_REF;
import static io.sundr.builder.Constants.INDEX;
import static io.sundr.builder.Constants.INIT_EXPRESSION;
import static io.sundr.builder.Constants.INIT_EXPRESSION_FUNCTION;
import static io.sundr.builder.Constants.OUTER_TYPE;
import static io.sundr.builder.internal.functions.TypeAs.ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.BOXED_OF;
import static io.sundr.builder.internal.functions.TypeAs.BUILDER_REF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_OPTIONAL_OF;
import static io.sundr.builder.internal.functions.TypeAs.VISITABLE_BUILDER_REF;
import static io.sundr.builder.internal.functions.TypeAs.combine;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.model.Expression.call;
import static io.sundr.model.Expression.cast;
import static io.sundr.model.Expression.enclosed;
import static io.sundr.model.utils.Collections.COLLECTION;
import static io.sundr.model.utils.Collections.IS_COLLECTION;
import static io.sundr.model.utils.Collections.IS_LIST;
import static io.sundr.model.utils.Collections.IS_MAP;
import static io.sundr.model.utils.Collections.IS_SET;
import static io.sundr.model.utils.Optionals.OPTIONAL;
import static io.sundr.model.utils.Types.N_REF;
import static io.sundr.model.utils.Types.Q;
import static io.sundr.model.utils.Types.T_REF;
import static io.sundr.model.utils.Types.isAbstract;
import static io.sundr.model.utils.Types.isArray;
import static io.sundr.model.utils.Types.isList;
import static io.sundr.model.utils.Types.isMap;
import static io.sundr.model.utils.Types.isOptional;
import static io.sundr.model.utils.Types.isOptionalDouble;
import static io.sundr.model.utils.Types.isOptionalInt;
import static io.sundr.model.utils.Types.isOptionalLong;
import static io.sundr.model.utils.Types.isPrimitive;
import static io.sundr.model.utils.Types.isSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import io.sundr.FunctionFactory;
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.functions.Singularize;
import io.sundr.model.AnnotationRef;
import io.sundr.model.Block;
import io.sundr.model.Break;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Declare;
import io.sundr.model.Expression;
import io.sundr.model.For;
import io.sundr.model.Foreach;
import io.sundr.model.GreaterThanOrEqual;
import io.sundr.model.If;
import io.sundr.model.InstanceOf;
import io.sundr.model.Lambda;
import io.sundr.model.LessThan;
import io.sundr.model.LessThanOrEqual;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.PropertyRef;
import io.sundr.model.Return;
import io.sundr.model.Statement;
import io.sundr.model.Ternary;
import io.sundr.model.This;
import io.sundr.model.Throw;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeRef;
import io.sundr.model.ValueRef;
import io.sundr.model.While;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.Getter;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

class ToMethod {

  static String getterOrBuildMethodName(Property item) {
    return (useBuildMethod(item) ? "build" + item.getNameCapitalized() : Getter.name(item));
  }

  private static boolean useBuildMethod(Property item) {
    if (Types.isMap(item.getTypeRef())) {
      return false;
    }
    TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());
    return BuilderUtils.isBuildable(unwrapped)
        || !Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(item).isEmpty();
  }

  private enum GeneratorType {
    FIRST("First", p -> ValueRef.from(0)), LAST("Last", p -> p.call("size").minus(1)), INDEXED("",
        p -> Property.newProperty("index"), true);

    GeneratorType(String name, Function<Property, Expression> toIndexExpression) {
      this(name, toIndexExpression, false);
    }

    GeneratorType(String name, Function<Property, Expression> toIndexExpression, boolean appendIndexArg) {
      this.name = name;
      this.toIndexExpression = toIndexExpression;
      this.appendIndexArg = appendIndexArg;
    }

    private Expression indexExpression(Property property) {
      return toIndexExpression.apply(property);
    }

    private void addIndexIfNeeded(MethodBuilder methodBuilder) {
      if (appendIndexArg) {
        methodBuilder.addToArguments(INDEX);
      }
    }

    private final String name;
    private final Function<Property, Expression> toIndexExpression;
    private final boolean appendIndexArg;
  }

  private interface GeneratorCustomizer {
    GeneratorCustomizer defaultCustomizer = new GeneratorCustomizer() {
      @Override
      public String methodPrefix(Property property) {
        return Getter.prefix(property);
      }

      @Override
      public Expression doWithItem(Expression expression) {
        return expression;
      }
    };
    GeneratorCustomizer builderCustomizer = new GeneratorCustomizer() {
      @Override
      public String methodPrefix(Property property) {
        return "build";
      }

      @Override
      public Expression doWithItem(Expression expression) {
        return expression.call("build");
      }
    };

    String methodPrefix(Property property);

    Expression doWithItem(Expression expression);
  }

  private static class GetterGenerator {
    GetterGenerator(ToMethod.GeneratorType type) {
      this(type, ToMethod.GeneratorCustomizer.defaultCustomizer);
    }

    GetterGenerator(ToMethod.GeneratorType type, ToMethod.GeneratorCustomizer customizer) {
      this.type = type;
      this.customizer = customizer;
    }

    private Method method(Property property, TypeRef unwrapped) {
      final MethodBuilder methodBuilder = new MethodBuilder()
          .withComments()
          .withAnnotations()
          .withNewModifiers().withPublic().endModifiers()
          .withName(customizer.methodPrefix(property) + type.name + Singularize.FUNCTION.apply(property.getNameCapitalized()))
          .withReturnType(unwrapped);
      type.addIndexIfNeeded(methodBuilder);

      methodBuilder.withNewBlock()
          .withStatements(
              new Return(customizer.doWithItem(new This().property(property).call("get", type.indexExpression(property)))))
          .endBlock()
          .build();
      return methodBuilder.build();
    }

    private final ToMethod.GeneratorCustomizer customizer;
    private final ToMethod.GeneratorType type;
  }

  private static final GetterGenerator GET_FIRST = new GetterGenerator(GeneratorType.FIRST);
  private static final GetterGenerator BUILD_FIRST = new GetterGenerator(GeneratorType.FIRST,
      GeneratorCustomizer.builderCustomizer);
  private static final GetterGenerator GET_LAST = new GetterGenerator(GeneratorType.LAST);
  private static final GetterGenerator BUILD_LAST = new GetterGenerator(GeneratorType.LAST,
      GeneratorCustomizer.builderCustomizer);
  private static final GetterGenerator GET_INDEXED = new GetterGenerator(GeneratorType.INDEXED);
  private static final GetterGenerator BUILD_INDEXED = new GetterGenerator(GeneratorType.INDEXED,
      GeneratorCustomizer.builderCustomizer);

  private enum MatchingType {
    BUILD("item.build()", "null"), HAS("true", "false"), GET("item", "null"), REMOVE(null, null);

    private Method method(Property property, TypeRef returnType, TypeDef predicate, TypeRef builderRef,
        List<AnnotationRef> annotations, List<String> comments) {
      return new MethodBuilder()
          .withComments(comments)
          .withAnnotations(annotations)
          .withNewModifiers().withPublic().endModifiers()
          .withName(operationName() + Singularize.FUNCTION.apply(property.getNameCapitalized()))
          .addNewArgument()
          .withName("predicate")
          .withTypeRef(predicate.toReference(builderRef))
          .endArgument()
          .withReturnType(returnType)
          .withNewBlock()
          .withStatements(statement(property, builderRef))
          .endBlock()
          .build();
    }

    private String operationName() {
      if (this != REMOVE) {
        return name().toLowerCase() + "Matching";
      } else {
        return "removeAllMatchingFrom";
      }
    }

    private Statement statement(Property property, TypeRef builderRef) {
      Property item = Property.newProperty(builderRef, "item");
      Property predicate = Property.newProperty("predicate");
      Property matchProperty = Property.newProperty(match);
      Property nonMatchProperty = Property.newProperty(nonMatch);

      if (match != null && nonMatch != null) {
        return new Block(new Foreach(item, property, If.condition(predicate.call("test", item))
            .then(Return.variable(matchProperty))
            .end()),
            Return.variable(nonMatchProperty));
      } else {
        return Return.variable(property).call("removeIf", new Lambda("item", (Expression) predicate.call("test", item)));
      }
    }

    MatchingType(String match, String nonMatch) {
      this.match = match;
      this.nonMatch = nonMatch;
    }

    private final String match;
    private final String nonMatch;
  }

  static final Function<Property, Method> WITH = FunctionFactory.cache(new Function<Property, Method>() {

    @Override
    public Method apply(Property property) {
      TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
      String methodName = "with" + property.getNameCapitalized();

      List<TypeParamDef> parameters = new ArrayList<>();
      if (property.getTypeRef() instanceof ClassRef) {
        ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF, UNWRAP_OPTIONAL_OF)
            .apply(property.getTypeRef());
        parameters.addAll(GetDefinition.of(baseType).getParameters());
      }

      return new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters).withName(methodName)
          .withReturnType(returnType).withArguments(property).withVarArgPreferred(true).withNewBlock()
          .withStatements(getStatements(property)).endBlock()
          .build();
    }

    private List<Statement> getStatements(Property property) {
      TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
      String fieldName = property.getName();
      TypeRef type = property.getTypeRef();
      TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
      List<Statement> statements = new ArrayList<>();
      Set<Property> descendants = property.hasAttribute(DESCENDANTS) ? property.getAttribute(DESCENDANTS)
          : Collections.emptySet();

      if (property.hasAttribute(DESCENDANT_OF)) {
        Property descendantOf = property.getAttribute(DESCENDANT_OF);
        fieldName = descendantOf.getName();
      }

      if (isBuildable(unwrapped)) {
        if (IS_COLLECTION.apply(type)) {
          statements.add(If.notNull(new This().property(fieldName))
              .then(new This().property("_visitables").call("get", ValueRef.from(fieldName)).call("clear"))
              .end());
        } else if (IS_MAP.apply(type)) {
          // There is no such thing as buildable map yet.
        } else {
          statements.add(new This().property("_visitables").call("remove", ValueRef.from(fieldName)));
        }
      }

      if (IS_MAP.apply(type)) {
        statements.add(If.isNull(property)
            .then(new This().property(property).assignNull())
            .orElse(new This().property(property).assignNew(LinkedHashMap.class, property)));

        statements.add(new Return(Expression.cast(returnType, new This())));
        return statements;
      } else if (IS_LIST.apply(type) || IS_SET.apply(type)) {
        String addToMethodName = "addTo" + property.getNameCapitalized();
        ClassRef newInstanceType = IS_LIST.apply(type) ? io.sundr.model.utils.Collections.ARRAY_LIST.toReference(unwrapped)
            : io.sundr.model.utils.Collections.LINKED_HASH_SET.toReference(unwrapped);
        if (Types.isConcrete(type)) {
          newInstanceType = (ClassRef) type;
        }

        Property item = Property.newProperty(unwrapped, "item");
        statements.add(If.notNull(property)
            .then(new This().property(property).assignNew(newInstanceType),
                new Foreach(item, property, new This().call(addToMethodName, item)))
            .orElse(new This().property(property).assignNull()));

        statements.add(new Return(Expression.cast(returnType, new This())));
        return statements;
      }

      if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
        ClassRef builder = BUILDER_REF.apply((ClassRef) unwrapped);
        statements.add(If.notNull(property)
            .then(new This().property(property).assignNew(builder, property),
                new This().property("_visitables").call("get", ValueRef.from(property.getName())).call("add",
                    new This().property(property)))
            .orElse(new This().property(property).assignNull(),
                new This().property("_visitables").call("get", ValueRef.from(property.getName())).call("remove",
                    new This().property(property))));
        statements.add(new Return(Expression.cast(returnType, new This())));
        return statements;
      }

      if (!descendants.isEmpty()) {
        Property builder = Property.newProperty(VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped), "builder");
        Property field = Property.newProperty(builder.getTypeRef(), fieldName);
        statements.add(If.isNull(property)
            .then(new This().property(field).assignNull(),
                new This().property("_visitables").call("remove", ValueRef.from(field.getName())),
                new Return(Expression.cast(returnType, new This())))
            .orElse(new Declare(builder, Expression.newCall("builder", property)),
                new This().property("_visitables").call("get", ValueRef.from(field.getName())).call("clear"),
                new This().property("_visitables").call("get", ValueRef.from(field.getName())).call("add",
                    builder),
                new This().property(field).assign(builder),
                new Return(Expression.cast(returnType, new This()))));
        return statements;
      }

      statements.add(new This().property(property).assign(property));
      statements.add(new Return(Expression.cast(returnType, new This())));
      return statements;
    }
  });

  static final Function<Property, Method> WITH_ARRAY = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;

    String methodName = "with" + property.getNameCapitalized();
    TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
    String addToMethodName = "addTo" + property.getNameCapitalized();

    TypeRef arrayType = ARRAY_OF.apply(unwraped);
    Property arrayProperty = new PropertyBuilder(property).withTypeRef(arrayType).build();

    Property _visitables = Property.newProperty("_visitables");
    Property item = Property.newProperty(unwraped, "item");

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(arrayProperty)
        .withVarArgPreferred(true)
        .withNewBlock()
        .withStatements(
            If.notNull(new This().property(property))
                .then(new This().property(property).call("clear"),
                    _visitables.call("remove", ValueRef.from(property.getName())))
                .end(),
            If.notNull(property)
                .then(new Foreach(new Declare(item), arrayProperty,
                    new This().call(addToMethodName, item)))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Property, List<Method>> WITH_OPTIONAL = FunctionFactory.cache(property -> {
    List<Method> methods = new ArrayList<>();
    TypeRef unwrapped = combine(UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    String methodName = "with" + property.getNameCapitalized();
    String fieldName = property.getName();
    Expression sourceRef = property;
    ClassRef builder = BUILDER_REF.apply((ClassRef) unwrapped);

    Property b = Property.newProperty(builder, "b");
    Block prepareBlock;
    if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
      prepareBlock = new Block(
          new Declare(b, Expression.createNew(builder, sourceRef)),
          Property.newProperty("_visitables").call("get", ValueRef.from(fieldName)).call("add", b),
          new This().property(property)
              .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.singletonList(b))));
    } else {
      prepareBlock = new Block(new This().property(property)
          .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.singletonList(sourceRef))));
    }

    methods.add(
        new MethodBuilder()
            .withNewModifiers().withPublic().endModifiers()
            .withName(methodName)
            .withReturnType(returnType)
            .withArguments(property)
            .withNewBlock()
            .withStatements(
                If.condition(property.isNull().or(property.call("isPresent").not()))
                    .then(new This().property(property).assign(Expression.call(property.getTypeRef(), "empty")))
                    .orElse(isBuildable(unwrapped) && !isAbstract(unwrapped)
                        ? Block.wrap(
                            new Declare(b, Expression.createNew(builder, property.call("get"))),
                            Property.newProperty("_visitables").call("get", ValueRef.from(property.getName()))
                                .call("add", b),
                            new This().property(property).assign(Expression.call(Optional.class, "of", b)))
                        : new This().property(property).assign(property)),
                new Return(Expression.cast(returnType, new This())))
            .endBlock()
            .build());

    Property genericProperty = new PropertyBuilder(property).withTypeRef(unwrapped).build();
    methods.add(new MethodBuilder().withNewModifiers().withPublic().endModifiers().withName(methodName)
        .withReturnType(returnType).withArguments(genericProperty).withNewBlock()
        .withStatements(If.isNull(property)
            .then(new This().property(fieldName).assign(property.getAttribute(INIT_EXPRESSION)))
            .orElse(prepareBlock),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build());

    return methods;
  });

  static final Function<Property, Method> HAS = FunctionFactory.cache(property -> {
    String prefix = "has";
    String methodName = prefix + property.getNameCapitalized();
    List<Statement> statements = new ArrayList<>();

    if (isPrimitive(property.getTypeRef())) {
      statements.add(Return.True());
    } else if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
      statements
          .add(new Return(new This().property(property).notNull().and(new This().property(property).call("isEmpty").not())));
    } else if (isOptional(property.getTypeRef()) || isOptionalInt(property.getTypeRef())
        || isOptionalLong(property.getTypeRef()) || isOptionalDouble(property.getTypeRef())) {
      statements.add(new Return(new This().property(property).notNull().and(new This().property(property).call("isPresent"))));
    } else {
      statements.add(new Return(new This().property(property).notNull()));
    }

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(Types.PRIMITIVE_BOOLEAN_REF)
        .withArguments()
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .build();
  });

  static final Function<Property, List<Method>> GETTER = FunctionFactory.cache(property -> {
    List<Method> methods = new ArrayList<>();
    TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

    TypeDef predicate = Constants.PREDICATE;
    String getterName = Getter.name(property);
    String builderName = "build" + property.getNameCapitalized();
    List<AnnotationRef> annotations = new ArrayList<>();
    List<String> comments = new ArrayList<>();
    List<Statement> statements = new ArrayList<>();
    boolean isNested = false;
    boolean isMap = isMap(property.getTypeRef());
    boolean isList = isList(property.getTypeRef());
    boolean isAbstractList = isList && Types.isAbstract(property.getTypeRef());
    boolean isSet = isSet(property.getTypeRef());
    boolean isAbstractSet = isSet && Types.isAbstract(property.getTypeRef());
    boolean isOptional = isOptional(property.getTypeRef()) || isOptionalDouble(property.getTypeRef())
        || isOptionalInt(property.getTypeRef()) || isOptionalLong(property.getTypeRef());

    TreeSet<Property> descendants = new TreeSet<>(Comparator.comparing(Property::getName));
    descendants.addAll(Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property));

    if (isMap) {
      statements.add(new This().property(property).ret());
    } else if (isBuildable(unwrapped)) {
      isNested = true;
      if (isList || isSet) {
        if (isAbstractList || isAbstractSet) {
          statements.add(new Return(Expression.ternary(new This().property(property).notNull(),
              Expression.newCall("build", property),
              Expression.NULL)));
        } else {
          statements.add(new Return(Expression.ternary(new This().property(property).notNull(),
              Expression.createNew((ClassRef) property.getTypeRef(), Expression.newCall("build", property)),
              Expression.NULL)));
        }
      } else if (isOptional) {
        statements.add(new Return(cast(property.getTypeRef(),
            enclosed(
                new Ternary(
                    //Condition
                    new This().property(property).notNull().and(new This().property(property).call("isPresent")),
                    //Then
                    call(OPTIONAL, "of", new This().property(property).call("get").call("build")),
                    //Else
                    call(OPTIONAL, "empty"))))));
      } else {
        statements.add(new Return(new Ternary(
            //Condition
            new This().property(property).notNull(),
            //Then
            new This().property(property).call("build"),
            //Else
            Expression.NULL)));
      }
    } else if (!descendants.isEmpty()) {
      isNested = true;
      if (isList || isSet) {
        statements.add(new Return(Expression.newCall("build", property)));
      } else {
        statements.add(new Return(new Ternary(
            //Condition
            new This().property(property).notNull(),
            //Then
            new This().property(property).call("build"),
            //Else
            Expression.NULL)));
      }
    } else {
      statements.add(new Return(new This().property(property)));
    }

    Method getter = new MethodBuilder()
        .withComments(comments)
        .withAnnotations(annotations)
        .withNewModifiers().withPublic().endModifiers()
        .withName(isNested ? builderName : getterName)
        .withReturnType(property.getTypeRef())
        .withArguments(new Property[] {})
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .build();

    methods.add(getter);
    if (isNested) {

      ClassRef builderRef = Types.isConcrete(unwrapped)
          ? TypeAs.BUILDER_REF.apply((ClassRef) unwrapped)
          : TypeAs.VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped);

      if (isList) {
        methods.add(BUILD_INDEXED.method(property, unwrapped));
        methods.add(BUILD_FIRST.method(property, unwrapped));
        methods.add(BUILD_LAST.method(property, unwrapped));
      }

      if (isList || isSet) {
        methods.add(MatchingType.BUILD.method(property, unwrapped, predicate, builderRef, Collections.emptyList(),
            Collections.emptyList()));
        methods
            .add(MatchingType.HAS.method(property, Types.PRIMITIVE_BOOLEAN_REF, predicate, builderRef, Collections.emptyList(),
                Collections.emptyList()));
      }
    } else if (isList) {
      methods.add(GET_INDEXED.method(property, unwrapped));
      methods.add(GET_FIRST.method(property, unwrapped));
      methods.add(GET_LAST.method(property, unwrapped));
      methods.add(MatchingType.GET.method(property, unwrapped, predicate, unwrapped, annotations, Collections.emptyList()));
      methods.add(MatchingType.HAS.method(property, Types.PRIMITIVE_BOOLEAN_REF, predicate, unwrapped, annotations,
          Collections.emptyList()));
    }
    return methods;
  });

  static final Function<Property, List<Method>> GETTER_ARRAY = FunctionFactory.cache(property -> {
    List<Method> methods = new ArrayList<>();
    List<AnnotationRef> annotations = new ArrayList<>();
    List<String> comments = new ArrayList<>();

    String getterName = Getter.name(property);
    String builderName = "build" + property.getNameCapitalized();
    TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
    TypeDef predicate = Constants.PREDICATE;

    TypeRef type = property.getTypeRef();
    Boolean isBuildable = isBuildable(unwrapped);
    TypeRef targetType = isBuildable ? VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped) : UNWRAP_ARRAY_OF.apply(type);

    List<Statement> statements = new ArrayList<>();

    Property size = Property.newProperty(Types.PRIMITIVE_INT_REF, "size");
    Property result = Property.newProperty(property.getTypeRef(), "result");
    Property index = Property.newProperty(Types.PRIMITIVE_INT_REF, "index");
    Property item = Property.newProperty(targetType, "item");

    statements.addAll(Arrays.asList(
        new Declare(size,
            Expression.ternary(property.notNull(), property.call("size"), ValueRef.from(0))),
        new Declare(result, Expression.createNewArray(unwrapped, size)),
        If.eq(size, ValueRef.from(0)).then(new Return(result)).end(),
        new Declare(index, ValueRef.from(0)),
        new Foreach(item, property,
            result.index(index.postIncrement())
                .assign(isBuildable ? item.call("build") : item)),
        new Return(result)));

    Method getter = new MethodBuilder()
        .withAnnotations(annotations)
        .withComments(comments)
        .withNewModifiers().withPublic().endModifiers()
        .withName(isBuildable ? builderName : getterName)
        .withReturnType(property.getTypeRef())
        .withArguments()
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .build();

    methods.add(getter);

    if (isBuildable) {

      ClassRef builderRef = Types.isConcrete(unwrapped)
          ? TypeAs.BUILDER_REF.apply((ClassRef) unwrapped)
          : TypeAs.VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped);

      methods.add(BUILD_INDEXED.method(property, unwrapped));
      methods.add(BUILD_FIRST.method(property, unwrapped));
      methods.add(BUILD_LAST.method(property, unwrapped));

      methods.add(MatchingType.BUILD.method(property, unwrapped, predicate, builderRef, Collections.emptyList(),
          Collections.emptyList()));

      methods.add(MatchingType.HAS.method(property, Types.PRIMITIVE_BOOLEAN_REF, predicate, builderRef, Collections.emptyList(),
          Collections.emptyList()));
    }
    return methods;
  });

  static final Function<Property, List<Method>> ADD_TO_COLLECTION = FunctionFactory
      .cache(new Function<Property, List<Method>>() {
        @Override
        public List<Method> apply(final Property property) {
          List<Method> methods = new ArrayList<>();
          TypeRef baseType = UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
          TypeRef builderType = VISITABLE_BUILDER_REF.apply((ClassRef) baseType);
          Property builderProperty = new PropertyBuilder(property).withName("builder").withTypeRef(builderType).build();

          TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

          TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
          final TypeRef unwrapped = BOXED_OF.apply(combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef()));

          Property items = new PropertyBuilder(property).withName("items").withTypeRef(unwrapped.withDimensions(1)).build();

          Property unwrappedProperty = new PropertyBuilder(property).withName("item").withTypeRef(unwrapped).build();

          List<TypeParamDef> parameters = new ArrayList<>();

          String addVarargMethodName = "addTo" + property.getNameCapitalized();
          String setMethodName = "setTo" + property.getNameCapitalized();
          String addAllMethodName = "addAllTo" + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef);

          Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);

          String propertyName = property.getName();
          if (property.hasAttribute(Constants.DESCENDANT_OF)) {
            Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
            if (attrValue != null) {
              propertyName = (attrValue).getName();
            }
          }

          If init = If.isNull(new This().property(propertyName))
              .then(new This().property(propertyName)
                  .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.emptyList())))
              .end();

          Method addSingleItemAtIndex = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(addVarargMethodName).withReturnType(returnType).addToArguments(INDEX)
              .addToArguments(unwrappedProperty).withNewBlock()
              .withStatements(init,
                  new This().property(propertyName).call("add", Property.newProperty("index"), Property.newProperty("item")),
                  new Return(Expression.cast(returnType, new This())))
              .endBlock()
              .build();

          Method setSingleItemAtIndex = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(setMethodName).withReturnType(returnType).addToArguments(INDEX)
              .addToArguments(unwrappedProperty).withNewBlock()
              .withStatements(init,
                  new This().property(propertyName).call("set", Property.newProperty("index"), Property.newProperty("item")),
                  new Return(Expression.cast(returnType, new This())))
              .endBlock().build();

          List<Statement> statements = new ArrayList<>();
          statements.add(init);

          if (isBuildable(unwrapped)) {
            TypeDef originalDef = GetDefinition.of((ClassRef) unwrapped);
            final ClassRef targetType = isAbstract(unwrapped) ? ToPojo.getPojoRef(originalDef) : (ClassRef) unwrapped;
            parameters.addAll(GetDefinition.of(targetType).getParameters());

            String builderClass = targetType.getFullyQualifiedName() + "Builder";

            //We need to do it more
            Property item = Property.newProperty(unwrapped, "item");
            Property builder = Property.newProperty(BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Property.newProperty("items"),
                Block.wrap(
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), item)),
                    Property.newProperty("_visitables").call("get", ValueRef.from(propertyName)).call("add", builder),
                    new This().property(propertyName).call("add", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));

            addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), Property.newProperty("item"))),
                    createAddOrSetIndex("add", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

            setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), Property.newProperty("item"))),
                    createAddOrSetIndex("set", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

          } else if (!descendants.isEmpty()) {
            final ClassRef targetType = (ClassRef) unwrapped;
            parameters.addAll(GetDefinition.of(targetType).getParameters());

            Property item = Property.newProperty(targetType, "item");
            Property builder = Property.newProperty(VISITABLE_BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Property.newProperty("items"),
                Block.wrap(
                    new Declare(builder, Expression.newCall("builder", item)),
                    Property.newProperty("_visitables").call("get", ValueRef.from(propertyName)).call("add", builder),
                    new This().property(propertyName).call("add", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));

            addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.newCall("builder", Property.newProperty("item"))),
                    createAddOrSetIndex("add", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

            setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.newCall("builder", Property.newProperty("item"))),
                    createAddOrSetIndex("set", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

            methods
                .add(
                    new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters)
                        .withName(addVarargMethodName).withReturnType(returnType).withArguments(builderProperty).withNewBlock()
                        .addToStatements(init,
                            Property.newProperty("_visitables").call("get", ValueRef.from(propertyName)).call("add",
                                Property.newProperty("builder")),
                            new This().property(propertyName).call("add", Property.newProperty("builder")),
                            new Return(Expression.cast(returnType, new This())))
                        .endBlock().build());

            methods.add(new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters)
                .withName(addVarargMethodName).withReturnType(returnType).withArguments(INDEX, builderProperty).withNewBlock()
                .addToStatements(init, createAddOrSetIndex("add", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build());

          } else {
            Property item = Property.newProperty(unwrapped, "item");
            statements.add(new Foreach(item, Property.newProperty("items"),
                new This().property(property.getName()).call("add", item)));
            statements.add(new Return(Expression.cast(returnType, new This())));
          }

          Method addVaragToCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(addVarargMethodName).withReturnType(returnType).withArguments(items)
              .withVarArgPreferred(true).withNewBlock().addAllToStatements(statements).endBlock()
              .build();

          Method addAllToCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(addAllMethodName).withReturnType(returnType)
              .withArguments(new PropertyBuilder(items).withTypeRef(COLLECTION.toReference(unwrapped)).build()).withNewBlock()
              .addAllToStatements(statements).endBlock().build();

          if (io.sundr.model.utils.Collections.IS_LIST.apply(property.getTypeRef())) {
            methods.add(addSingleItemAtIndex);
            methods.add(setSingleItemAtIndex);
          }
          methods.add(addVaragToCollection);
          methods.add(addAllToCollection);

          return methods;
        }

        private Statement createAddOrSetIndex(String op, String propertyName, String returnType) {
          PropertyRef index = Property.newProperty("index").toReference();
          PropertyRef property = Property.newProperty(propertyName).toReference();
          ValueRef propertyNameValue = ValueRef.from(propertyName);
          PropertyRef _visitables = Property.newProperty("_visitables").toReference();
          PropertyRef builder = Property.newProperty("builder").toReference();
          ValueRef zero = ValueRef.from(0);
          return If.condition(Expression.or(new LessThan(index, zero), new GreaterThanOrEqual(index, property.call("size"))))
              .then(_visitables.call("get", propertyNameValue).call("add", builder),
                  property.call("add", builder))
              .orElse(_visitables.call("get", propertyNameValue).call("add", builder),
                  property.call(op, index, builder));
        }
      });

  static final Function<Property, List<Method>> REMOVE_FROM_COLLECTION = FunctionFactory
      .cache(new Function<Property, List<Method>>() {
        @Override
        public List<Method> apply(final Property property) {
          List<Method> methods = new ArrayList<>();
          ClassRef baseType = (ClassRef) UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
          TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

          TypeRef builderType = VISITABLE_BUILDER_REF.apply(baseType);
          Property visitableBuilderProperty = new PropertyBuilder(property).withName("builder").withTypeRef(builderType)
              .build();

          TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
          final TypeRef unwrapped = BOXED_OF.apply(combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef()));
          Property items = new PropertyBuilder(property).withName("items").withTypeRef(unwrapped.withDimensions(1)).build();

          List<TypeParamDef> parameters = new ArrayList<>();

          String removeVarargMethodName = "removeFrom" + property.getNameCapitalized();
          String removeAllMethodName = "removeAllFrom" + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef);
          String removeMatchingMethodName = "removeMatchingFrom"
              + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef);

          String propertyName = property.getName();
          List<Statement> statements = new ArrayList<>();
          boolean isSimple = false;

          Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);
          if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
            final ClassRef targetType = (ClassRef) unwrapped;
            if (property.hasAttribute(Constants.DESCENDANT_OF)) {
              Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
              if (attrValue != null) {
                propertyName = attrValue.getName();
              }
            }
            String targetClass = targetType.getFullyQualifiedName();
            parameters.addAll(GetDefinition.of(targetType).getParameters());
            String builderClass = targetClass + "Builder";

            //We need to do it more elegantly
            statements.add(nullCheck(returnType, propertyName));
            Property item = Property.newProperty(targetType, "item");
            Property builder = Property.newProperty(BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Property.newProperty("items"),
                Block.wrap(
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), item)),
                    Property.newProperty("_visitables").call("get", ValueRef.from(propertyName)).call("remove", builder),
                    new This().property(propertyName).call("remove", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));
          } else if (!descendants.isEmpty()) {
            final ClassRef targetType = (ClassRef) unwrapped;
            parameters.addAll(GetDefinition.of(targetType).getParameters());
            statements.add(nullCheck(returnType, propertyName));
            Property item = Property.newProperty(targetType, "item");
            Property builder = Property.newProperty(VISITABLE_BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Property.newProperty("items"),
                Block.wrap(
                    new Declare(builder, Expression.newCall("builder", item)),
                    Property.newProperty("_visitables").call("get", ValueRef.from(property.getName())).call("remove", builder),
                    new This().property(property.getName()).call("remove", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));

            methods.add(new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters)
                .withName(removeVarargMethodName).withReturnType(returnType).withArguments(visitableBuilderProperty)
                .withNewBlock()
                .addToStatements(nullCheck(returnType, propertyName),
                    Property.newProperty("_visitables").call("get", ValueRef.from(propertyName)).call("remove",
                        Property.newProperty("builder")),
                    new This().property(propertyName).call("remove", Property.newProperty("builder")),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build());
          } else {
            isSimple = true;
            statements.add(nullCheck(returnType, propertyName));
            Property item = Property.newProperty(unwrapped, "item");
            statements.add(new Foreach(item, Property.newProperty("items"),
                new This().property(property.getName()).call("remove", item)));
            statements.add(new Return(Expression.cast(returnType, new This())));
          }

          Method removeVarargFromCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withName(removeVarargMethodName).withParameters(parameters).withReturnType(returnType).withArguments(items)
              .withVarArgPreferred(true).withNewBlock().withStatements(statements).endBlock().build();

          Method removeAllFromCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(removeAllMethodName).withReturnType(returnType)
              .withArguments(new PropertyBuilder(items).withTypeRef(COLLECTION.toReference(unwrapped)).build()).withNewBlock()
              .withStatements(statements).endBlock().build();

          methods.add(removeVarargFromCollection);
          methods.add(removeAllFromCollection);

          if (!isSimple) {
            ClassRef builder = null;
            if (Types.isConcrete(unwrapped) && !property.hasAttribute(DESCENDANT_OF)) {
              builder = BUILDER_REF.apply((ClassRef) unwrapped);
            } else {
              if (property.hasAttribute(DESCENDANT_OF)) {
                builderType = VISITABLE_BUILDER_REF.apply((ClassRef) property.getAttribute(DESCENDANT_OF).getTypeRef());
              }
              builder = (ClassRef) builderType;
            }
            // Create property references for cleaner code
            Property propertyRef = Property.newProperty(propertyName);
            Property eachProperty = Property.newProperty(
                new ClassRefBuilder().withFullyQualifiedName("java.util.Iterator").withArguments(builder).build(), "each");
            Property visitablesProperty = Property
                .newProperty(new ClassRefBuilder().withFullyQualifiedName("java.util.List").build(), "visitables");

            Property builderProperty = Property.newProperty(builder, "builder");
            Property predicateProperty = Property.newProperty("predicate");

            methods.add(new MethodBuilder().withNewModifiers()
                .withPublic().endModifiers().withReturnType(returnType).withParameters(parameters)
                .withName(removeMatchingMethodName).addNewArgument().withName("predicate")
                .withTypeRef(Constants.PREDICATE.toReference(builder)).endArgument().withNewBlock()
                .withStatements(
                    // if (propertyName == null) return (returnType) this;
                    If.isNull(propertyRef)
                        .then(new Return(Expression.cast(returnType, new This())))
                        .end(),
                    // final Iterator<builder> each = propertyName.iterator();
                    new Declare(eachProperty, propertyRef.call("iterator")),
                    // final List visitables = _visitables.get("propertyName");
                    new Declare(visitablesProperty,
                        Property.newProperty("_visitables").call("get", ValueRef.from(propertyName))),
                    // while (each.hasNext()) { ... }
                    While.condition(eachProperty.call("hasNext"))
                        .body(
                            // builder builder = each.next();
                            new Declare(builderProperty, eachProperty.call("next")),
                            // if (predicate.test(builder)) { visitables.remove(builder); each.remove(); }
                            If.condition(predicateProperty.call("test", builderProperty))
                                .then(
                                    visitablesProperty.call("remove", builderProperty),
                                    eachProperty.call("remove"))
                                .end()),
                    // return (returnType) this;
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build());
          }
          return methods;
        }

        private If nullCheck(TypeRef returnType, String propertyName) {
          return If.isNull(new This().property(propertyName))
              .then(new Return(Expression.cast(returnType, new This())))
              .end();
        }
      });

  static final Function<Property, Method> ADD_MAP_TO_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeRef mapType = property.getTypeRef();
    Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
    String methodName = "addTo" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(mapProperty)
        .withNewBlock()
        .withStatements(
            If.condition(new This().property(property.getName()).isNull().and(Property.newProperty("map").notNull()))
                .then(new This().property(property.getName())
                    .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.emptyList())))
                .end(),
            If.notNull(Property.newProperty("map"))
                .then(new This().property(property.getName()).call("putAll", Property.newProperty("map")))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Property, List<Method>> ADD_NEW_VALUE_TO_MAP = property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Map type and found:" + property.getTypeRef());
    }
    ClassRef mapType = (ClassRef) property.getTypeRef();
    TypeRef keyType = mapType.getArguments().get(0);
    TypeRef valueType = mapType.getArguments().get(1);

    Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
    Property valueProperty = new PropertyBuilder().withName("value").withTypeRef(valueType).build();
    ClassRef targetType = (ClassRef) valueType;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);
    TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

    List<TypeParamDef> parameters = GetDefinition.of(targetType).getParameters();
    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef arg : targetType.getArguments()) {
      typeArguments.add(arg);
    }
    typeArguments.add(returnType);

    ClassRef rewraped = nestedType.toReference(typeArguments);
    String methodSuffix = BuilderUtils.qualifyPropertyName(property, valueType, originTypeDef);
    String propertyName = property.getName();
    TypeRef baseType = valueType;
    if (property.hasAttribute(Constants.DESCENDANT_OF)) {
      Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
      if (attrValue != null) {
        propertyName = (attrValue).getName();
        baseType = ((ClassRef) attrValue.getTypeRef()).getArguments().get(1);
      }
    }
    String fullyQualifiedBaseType = ((ClassRef) baseType).getFullyQualifiedName();
    String fullyQualifiedValueType = ((ClassRef) valueType).getFullyQualifiedName();

    Method addNewValueTo = new MethodBuilder()
        .withParameters(parameters)
        .withNewModifiers().withPublic().endModifiers()
        .withName("addNewValueTo" + methodSuffix)
        .withReturnType(rewraped)
        .withArguments(keyProperty)
        .withNewBlock()
        .withStatements(new Return(Expression.createNew(rewraped, keyProperty, Expression.NULL)))
        .endBlock()
        .build();

    Method addNewValueLikeTo = new MethodBuilder()
        .withParameters(parameters)
        .withNewModifiers().withPublic().endModifiers()
        .withName("addNewValueLikeTo" + methodSuffix)
        .withReturnType(rewraped)
        .withArguments(keyProperty, valueProperty)
        .withNewBlock()
        .withStatements(new Return(Expression.createNew(rewraped, keyProperty, valueProperty)))
        .endBlock()
        .build();

    // Create property references for cleaner code
    Expression thisPropertyRef = new This().property(propertyName);
    Property keyRef = Property.newProperty(keyProperty.getName());
    Property toEditProperty = Property.newProperty(new ClassRefBuilder().withFullyQualifiedName(fullyQualifiedBaseType).build(),
        "toEdit");
    ClassRef runtimeExceptionRef = new ClassRefBuilder().withFullyQualifiedName("java.lang.RuntimeException").build();
    ClassRef valueTypeRef = new ClassRefBuilder().withFullyQualifiedName(fullyQualifiedValueType).build();

    Method editValueIn = new MethodBuilder()
        .withParameters(parameters)
        .withNewModifiers().withPublic().endModifiers()
        .withName("editValueIn" + methodSuffix)
        .withReturnType(rewraped)
        .withArguments(keyProperty)
        .withNewBlock()
        .withStatements(
            // if (this.propertyName == null || !this.propertyName.containsKey(key)) throw new RuntimeException(...);
            If.condition(thisPropertyRef.isNull().or(thisPropertyRef.call("containsKey", keyRef).not()))
                .then(Throw.runtimeException(
                    Expression.call(String.class, "format",
                        ValueRef.from("Can't edit %s. Entry for key '%s' doesn't exist."),
                        ValueRef.from(propertyName), keyRef)))
                .end(),
            // BaseType toEdit = this.propertyName.get(key);
            new Declare(toEditProperty, thisPropertyRef.call("get", keyRef)),
            // if (toEdit instanceof ValueType == false) throw new RuntimeException(...);
            If.condition(new InstanceOf(toEditProperty, valueTypeRef).not())
                .then(Throw.runtimeException(
                    Expression.call(String.class, "format",
                        ValueRef.from("Can't edit %s. Entry for key '%s' is not instance of %s."),
                        ValueRef.from(propertyName), keyRef, ValueRef.from(fullyQualifiedValueType))))
                .end(),
            // return new WrappedType(key, (ValueType) toEdit);
            new Return(Expression.createNew(rewraped, keyRef, Expression.cast(valueTypeRef, toEditProperty))))
        .endBlock()
        .build();

    Method editOrAddValueIn = new MethodBuilder()
        .withParameters(parameters)
        .withNewModifiers().withPublic().endModifiers()
        .withName("editOrAddValueIn" + methodSuffix)
        .withReturnType(rewraped)
        .withArguments(keyProperty)
        .withNewBlock()
        .withStatements(
            // if (this.propertyName != null && this.propertyName.containsKey(key)) { ... } else { ... }
            If.condition(thisPropertyRef.notNull().and(thisPropertyRef.call("containsKey", keyRef)))
                .then(
                    // BaseType toEdit = this.propertyName.get(key);
                    new Declare(toEditProperty, thisPropertyRef.call("get", keyRef)),
                    // if (toEdit instanceof ValueType == false) throw new RuntimeException(...);
                    If.condition(new InstanceOf(toEditProperty, valueTypeRef).not())
                        .then(Throw.runtimeException(
                            Expression.call(String.class, "format",
                                ValueRef.from("Can't edit %s. Entry for key '%s' is not instance of %s."),
                                ValueRef.from(propertyName), keyRef, ValueRef.from(fullyQualifiedValueType))))
                        .end(),
                    // return new WrappedType(key, (ValueType) toEdit);
                    new Return(Expression.createNew(rewraped, keyRef, Expression.cast(valueTypeRef, toEditProperty))))
                .orElse(
                    // return new WrappedType(key, null);
                    new Return(Expression.createNew(rewraped, keyRef, Expression.NULL))))
        .endBlock()
        .build();

    return Arrays.asList(addNewValueTo, addNewValueLikeTo, editValueIn, editOrAddValueIn);
  };

  static final Function<Property, Method> ADD_TO_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Map type and found:" + property.getTypeRef());
    }
    ClassRef mapType = (ClassRef) property.getTypeRef();
    TypeRef keyType = mapType.getArguments().get(0);
    TypeRef valueType = mapType.getArguments().get(1);

    Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
    Property valueProperty = new PropertyBuilder().withName("value").withTypeRef(valueType).build();
    String methodName = "addTo" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(new Property[] { keyProperty, valueProperty })
        .withNewBlock()
        .withStatements(
            If.condition(new This().property(property.getName()).isNull()
                .and(Property.newProperty("key").notNull())
                .and(Property.newProperty("value").notNull()))
                .then(new This().property(property.getName())
                    .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.emptyList())))
                .end(),
            If.condition(Property.newProperty("key").notNull().and(Property.newProperty("value").notNull()))
                .then(new This().property(property.getName()).call("put", Property.newProperty("key"),
                    Property.newProperty("value")))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Property, Method> REMOVE_MAP_FROM_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeRef mapType = property.getTypeRef();
    Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
    String methodName = "removeFrom" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(mapProperty)
        .withNewBlock()
        .withStatements(
            If.isNull(new This().property(property.getName()))
                .then(new Return(Expression.cast(returnType, new This())))
                .end(),
            If.notNull(Property.newProperty("map"))
                .then(new Foreach(Property.newProperty(Object.class, "key"), Property.newProperty("map").call("keySet"),
                    If.notNull(new This().property(property.getName()))
                        .then(new This().property(property.getName()).call("remove", Property.newProperty("key")))
                        .end()))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Property, Method> REMOVE_FROM_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    ClassRef mapType = (ClassRef) property.getTypeRef();
    TypeRef keyType = mapType.getArguments().get(0);

    Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
    String methodName = "removeFrom" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(keyProperty)
        .withNewBlock()
        .withStatements(
            If.isNull(new This().property(property.getName()))
                .then(new Return(Expression.cast(returnType, new This())))
                .end(),
            If.condition(Property.newProperty("key").notNull().and(new This().property(property.getName()).notNull()))
                .then(new This().property(property.getName()).call("remove", Property.newProperty("key")))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Property, Method> WITH_NEW_NESTED = property -> {
    ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF, UNWRAP_OPTIONAL_OF)
        .apply(property.getTypeRef());

    TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

    //Let's reload the class from the repository if available....
    TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository()
        .getDefinition((baseType).getFullyQualifiedName());
    if (propertyTypeDef != null) {
      baseType = propertyTypeDef.toInternalReference();
    }

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);

    List<TypeParamDef> parameters = GetDefinition.of(baseType).getParameters();
    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef arg : baseType.getArguments()) {
      typeArguments.add(arg);
    }
    typeArguments.add(returnType);

    ClassRef rewraped = nestedType.toReference(typeArguments);

    boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
    String prefix = isCollection ? "addNew" : "withNew";

    String methodName = prefix + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef, isCollection);

    boolean hasIndex = Types.isArray(property.getTypeRef()) || Types.isList(property.getTypeRef());

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withParameters(parameters)
        .withReturnType(rewraped)
        .withName(methodName)
        .withNewBlock()
        .withStatements(new Return(hasIndex
            ? Expression.createNew(rewraped, ValueRef.from(-1), Expression.NULL)
            : Expression.createNew(rewraped, Expression.NULL)))
        .endBlock()
        .build();

  };

  static final Function<Property, Set<Method>> WITH_NESTED_INLINE = property -> {
    TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

    if (originTypeDef.isEnum()) {
      return Collections.emptySet();
    }

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    Set<Method> result = new LinkedHashSet<>();
    TypeRef unwrappedType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
    TypeDef baseType = DefinitionRepository.getRepository().getDefinition(unwrappedType);

    for (Method constructor : getInlineableConstructors(property)) {
      boolean shouldIgnore = ((ClassRef) property.getTypeRef()).getFullyQualifiedName().equals(String.class.getName());
      if (shouldIgnore) {
        continue;
      }

      boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
      String ownPrefix = isCollection ? "addNew" : "withNew";

      String ownName = ownPrefix
          + BuilderUtils.qualifyPropertyName(property, baseType.toInternalReference(), originTypeDef, isCollection);

      String delegatePrefix = isCollection ? "addTo" : "with";
      String delegateName = delegatePrefix + property.getNameCapitalized();

      if (property.hasAttribute(Constants.DESCENDANT_OF)) {
        Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
        if (attrValue != null) {
          delegateName = delegatePrefix + (attrValue).getNameCapitalized();
        }
      }

      String args = Strings.join(constructor.getArguments(), Property::getName, ", ");

      result.add(new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(returnType)
          .withArguments(constructor.getArguments()).withName(ownName).withParameters(baseType.getParameters()).withNewBlock()
          .withStatements(new Return(Expression.cast(returnType,
              new This().call(delegateName, Expression.createNew(baseType.toInternalReference(),
                  constructor.getArguments().stream().map(Property::toReference).toArray(Expression[]::new))))))
          .endBlock().build());
    }

    return result;
  };

  static final Function<Property, Method> EDIT_OR_NEW = property -> {
    ClassRef baseType = (ClassRef) property.getTypeRef();

    TypeRef unwrappedType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(baseType);
    if (!(unwrappedType instanceof ClassRef)) {
      throw new IllegalStateException("Expected Editable/Buildable type and found:" + unwrappedType);
    }

    ClassRef unwrappedClassRef = (ClassRef) unwrappedType;
    ClassRef builderType = TypeAs.BUILDER_REF.apply(unwrappedClassRef);

    //Let's reload the class from the repository if available....
    TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository()
        .getDefinition((baseType).getFullyQualifiedName());
    if (propertyTypeDef != null) {
      baseType = propertyTypeDef.toInternalReference();
    }

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);

    List<TypeParamDef> parameters = GetDefinition.of(baseType).getParameters();
    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef ignore : baseType.getArguments()) {
      typeArguments.add(Q);
    }
    typeArguments.add(returnType);
    ClassRef rewraped = nestedType.toReference(typeArguments);

    String prefix = "editOrNew";
    String methodNameBase = property.getNameCapitalized();
    String methodName = prefix + methodNameBase;

    Expression elseValue = Expression.createNew(builderType).call("build");
    Statement statement = createWithNewStatementTyped(property, methodNameBase, elseValue);

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withParameters(parameters)
        .withReturnType(rewraped)
        .withName(methodName)
        .withNewBlock()
        .withStatements(statement)
        .endBlock()
        .build();

  };

  static final Function<Property, Method> EDIT_OR_NEW_LIKE = property -> {
    TypeRef unwrappedType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

    if (!(unwrappedType instanceof ClassRef)) {
      throw new IllegalStateException("Expected Editable/Buildable type and found:" + unwrappedType);
    }

    ClassRef unwrappedClassRef = (ClassRef) unwrappedType;
    ClassRef baseType = (ClassRef) property.getTypeRef();

    //Let's reload the class from the repository if available....
    TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository()
        .getDefinition(unwrappedClassRef.getFullyQualifiedName());
    if (propertyTypeDef != null) {
      baseType = propertyTypeDef.toInternalReference();
    }

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);

    List<TypeParamDef> parameters = GetDefinition.of(baseType).getParameters();
    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef ignore : baseType.getArguments()) {
      typeArguments.add(Q);
    }
    typeArguments.add(returnType);
    ClassRef rewraped = nestedType.toReference(typeArguments);

    String prefix = "editOrNew";
    String suffix = "Like";
    String methodNameBase = property.getNameCapitalized();
    String methodName = prefix + methodNameBase + suffix;

    Expression elseValue = Property.newProperty("item");
    Statement statement = createWithNewStatementTyped(property, methodNameBase, elseValue);

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withParameters(parameters)
        .withReturnType(rewraped)
        .withName(methodName)
        .addNewArgument()
        .withName("item")
        .withTypeRef(baseType)
        .endArgument()
        .withNewBlock()
        .withStatements(statement)
        .endBlock()
        .build();

  };

  static final Function<Property, Method> WITH_NEW_LIKE_NESTED = property -> {
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
    }
    ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
    //Let's reload the class from the repository if available....
    TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository()
        .getDefinition((baseType).getFullyQualifiedName());
    if (propertyTypeDef != null) {
      baseType = propertyTypeDef.toInternalReference();
    }

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);

    List<TypeParamDef> parameters = GetDefinition.of(baseType).getParameters();
    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef ignore : baseType.getArguments()) {
      typeArguments.add(Q);
    }
    typeArguments.add(returnType);

    ClassRef rewraped = nestedType.toReference(typeArguments);

    boolean isList = IS_LIST.apply(property.getTypeRef());
    boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());

    String prefix = isCollection ? "addNew" : "withNew";
    String suffix = "Like";
    String methodName = (prefix + (isCollection
        ? Singularize.FUNCTION.apply(property.getNameCapitalized())
        : property.getNameCapitalized()) + suffix);

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withParameters(parameters)
        .withReturnType(rewraped)
        .withName(methodName)
        .addNewArgument()
        .withName("item")
        .withTypeRef(baseType)
        .endArgument()
        .withNewBlock()
        .withStatements(new Return(isList
            ? Expression.createNew(rewraped, ValueRef.from(-1), Property.newProperty("item"))
            : Expression.createNew(rewraped, Property.newProperty("item"))))
        .endBlock()
        .build();

  };

  static final Function<Property, Method> WITH_NEW_LIKE_NESTED_AT_INDEX = property -> {
    Method method = WITH_NEW_LIKE_NESTED.apply(property);

    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
    }

    ClassRef baseType = (ClassRef) UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);

    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef ignore : baseType.getArguments()) {
      typeArguments.add(Q);
    }
    typeArguments.add(returnType);
    ClassRef rewraped = nestedType.toReference(typeArguments);

    return new MethodBuilder(method)
        .addToArguments(0, INDEX)
        .withName(method.getName().replaceFirst("add", "set"))
        .editBlock()
        .withStatements(new Return(Expression.createNew(rewraped, Property.newProperty("index"), Property.newProperty("item"))))
        .endBlock()
        .build();
  };

  static final Function<Property, List<Method>> EDIT_NESTED = property -> {
    List<Method> methods = new ArrayList<>();
    TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
    }
    ClassRef unwrapped = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF, UNWRAP_OPTIONAL_OF)
        .apply(property.getTypeRef());

    ClassRef builderRef = Types.isConcrete(unwrapped)
        ? TypeAs.BUILDER_REF.apply(unwrapped)
        : TypeAs.VISITABLE_BUILDER_REF.apply(unwrapped);

    //Let's reload the class from the repository if available....
    TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository()
        .getDefinition((unwrapped).getFullyQualifiedName());
    if (propertyTypeDef != null) {
      unwrapped = propertyTypeDef.toInternalReference();
    }

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeDef nestedType = PropertyAs.NESTED_CLASS_TYPE.apply(property);

    List<TypeRef> typeArguments = new ArrayList<>();
    for (TypeRef ignore : unwrapped.getArguments()) {
      typeArguments.add(Q);
    }
    typeArguments.add(returnType);

    ClassRef rewraped = nestedType.toReference(typeArguments);

    String methodName = "edit" + BuilderUtils.qualifyPropertyName(property, property.getTypeRef(), originTypeDef);
    String methodNameBase = property.getNameCapitalized();

    Expression elseValue = Expression.NULL;
    Statement statement = createWithNewStatementTyped(property, methodNameBase, elseValue);

    Method base = new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(rewraped)
        .withName(methodName)
        .withNewBlock()
        .withStatements(statement)
        .endBlock()
        .build();

    if (isList(property.getTypeRef()) || isArray(property.getTypeRef())) {
      String suffix = Singularize.FUNCTION.apply(property.getNameCapitalized());
      // Create property references for cleaner code
      Property propertyRef = Property.newProperty(property.getName());
      Property indexProperty = Property.newProperty(int.class, "index");

      methods.add(new MethodBuilder(base)
          .withArguments(INDEX)
          .withName("edit" + suffix)
          .editBlock()
          .withStatements(
              // if (property.size() <= index) throw new RuntimeException(...);
              If.condition(new LessThanOrEqual(propertyRef.call("size"), indexProperty))
                  .then(Throw.runtimeException(
                      Expression.call(String.class, "format",
                          ValueRef.from("Can't edit %s. Index exceeds size."),
                          ValueRef.from(property.getName()))))
                  .end(),
              // return setNewSuffixLike(index, buildSuffix(index));
              new Return(new This().call("setNew" + suffix + "Like",
                  indexProperty,
                  new This().call("build" + suffix, indexProperty))))
          .endBlock()
          .build());

      methods.add(new MethodBuilder(base)
          .withName("editFirst" + suffix)
          .withArguments()
          .editBlock()
          .withStatements(
              // if (property.size() == 0) throw new RuntimeException(...);
              If.eq(propertyRef.call("size"), ValueRef.from(0))
                  .then(Throw.runtimeException(
                      Expression.call(String.class, "format",
                          ValueRef.from("Can't edit first %s. The list is empty."),
                          ValueRef.from(property.getName()))))
                  .end(),
              // return setNewSuffixLike(0, buildSuffix(0));
              new Return(new This().call("setNew" + suffix + "Like",
                  ValueRef.from(0),
                  new This().call("build" + suffix, ValueRef.from(0)))))
          .endBlock()
          .build());

      methods.add(new MethodBuilder(base)
          .withName("editLast" + suffix)
          .withArguments()
          .editBlock()
          .withStatements(
              // int index = property.size() - 1;
              new Declare(indexProperty, propertyRef.call("size").minus(1)),
              // if (index < 0) throw new RuntimeException(...);
              If.lt(indexProperty, ValueRef.from(0))
                  .then(Throw.runtimeException(
                      Expression.call(String.class, "format",
                          ValueRef.from("Can't edit last %s. The list is empty."),
                          ValueRef.from(property.getName()))))
                  .end(),
              // return setNewSuffixLike(index, buildSuffix(index));
              new Return(new This().call("setNew" + suffix + "Like",
                  indexProperty,
                  new This().call("build" + suffix, indexProperty))))
          .endBlock()
          .build());

      // Additional properties for editMatching method
      Property iProperty = Property.newProperty(Types.PRIMITIVE_INT_REF, "i");
      Property predicateProperty = Property.newProperty("predicate");

      methods.add(new MethodBuilder(base)
          .withName("editMatching" + suffix)
          .addNewArgument()
          .withName("predicate")
          .withTypeRef(Constants.PREDICATE.toReference(builderRef))
          .endArgument()
          .editBlock()
          .withStatements(
              // int index = -1;
              new Declare(indexProperty, ValueRef.from(-1)),
              // for (int i=0; i<property.size(); i++) { if (predicate.test(property.get(i))) {index = i; break;} }
              For.init(iProperty, ValueRef.from(0))
                  .lt(iProperty, propertyRef.call("size"))
                  .update(iProperty.postIncrement())
                  .body(
                      If.condition(predicateProperty.call("test", propertyRef.call("get", iProperty)))
                          .then(
                              indexProperty.assign(iProperty),
                              new Break())
                          .end()),
              // if (index < 0) throw new RuntimeException(...);
              If.lt(indexProperty, ValueRef.from(0))
                  .then(Throw.runtimeException(
                      Expression.call(String.class, "format",
                          ValueRef.from("Can't edit matching %s. No match found."),
                          ValueRef.from(property.getName()))))
                  .end(),
              // return setNewSuffixLike(index, buildSuffix(index));
              Return.This().call("setNew" + suffix + "Like",
                  indexProperty,
                  new This().call("build" + suffix, indexProperty)))
          .endBlock()
          .build());
    } else {
      methods.add(base);
    }
    return methods;
  };

  private static String createWithNewStatement(Property property, String methodNameBase, String elseValue) {
    return "return withNew" + methodNameBase + "Like(Optional.ofNullable("
        + getterOrBuildMethodName(property) + "())"
        + (isOptional(property.getTypeRef()) ? ".flatMap(Function.identity())" : "")
        + ".orElse(" + elseValue + "));";
  }

  private static Statement createWithNewStatementTyped(Property property, String methodNameBase, Expression elseValue) {
    // Build the method call chain: Optional.ofNullable(getterOrBuildMethodName())
    Expression optionalCall = Expression.call(Optional.class, "ofNullable",
        new This().call(getterOrBuildMethodName(property)));

    // Add flatMap if the property type is optional
    if (isOptional(property.getTypeRef())) {
      ClassRef functionRef = new ClassRefBuilder()
          .withFullyQualifiedName("java.util.function.Function")
          .build();
      optionalCall = optionalCall.call("flatMap",
          Expression.call(functionRef, "identity"));
    }

    // Add orElse with the provided else value
    Expression orElseCall = optionalCall.call("orElse", elseValue);

    // Create the return statement: return withNewMethodBaseLike(...)
    return Return.This().call("withNew" + methodNameBase + "Like", orElseCall);
  }

  static final Function<Property, Method> AND = new Function<Property, Method>() {
    @Override
    public Method apply(Property property) {
      String classPrefix = getClassPrefix(property);

      boolean isArray = Types.isArray(property.getTypeRef());
      boolean isList = Types.isList(property.getTypeRef());
      boolean isMap = Types.isMap(property.getTypeRef());

      String prefix = "with";
      if (isArray || isList) {
        prefix = "setTo";
      } else if (isMap) {
        prefix = "addTo";
      }
      String indexOrKey = "";
      if (isArray || isList) {
        indexOrKey = "index,";
      } else if (isMap) {
        indexOrKey = "key,";
      }
      String withMethodName = prefix + property.getNameCapitalized();
      if (property.hasAttribute(Constants.DESCENDANT_OF)) {
        Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
        if (attrValue != null) {
          withMethodName = prefix + (attrValue).getNameCapitalized();
        }
      }

      List<Expression> args = new ArrayList<>();
      if (isArray || isList) {
        args.add(Property.newProperty("index"));
      } else if (isMap) {
        args.add(Property.newProperty("key"));
      }
      args.add(Property.newProperty("builder").call("build"));

      Expression target = classPrefix.isEmpty() ? new This()
          : Property.newProperty(classPrefix.substring(0, classPrefix.length() - 1)); // Remove trailing "."

      return new MethodBuilder().withNewModifiers().withPublic().endModifiers().withReturnType(N_REF).withName("and")
          .withNewBlock()
          .withStatements(new Return(Expression.cast(N_REF, target.call(withMethodName, args.toArray(new Expression[0])))))
          .endBlock().build();

    }

    private String getClassPrefix(Property property) {
      ClassRef memberOf = property.getAttribute(OUTER_TYPE);
      if (memberOf != null) {
        return memberOf.getName() + ".this.";
      } else {
        return "";
      }
    }

  };

  static final Function<Property, Method> END = FunctionFactory.cache(property -> {
    TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);
    boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
    String methodName = "end" + BuilderUtils.qualifyPropertyName(property, property.getTypeRef(), originTypeDef, isCollection);

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(N_REF)
        .withName(methodName)
        .withNewBlock()
        .withStatements(new Return(Expression.newCall("and")))
        .endBlock()
        .build();
  });
}
