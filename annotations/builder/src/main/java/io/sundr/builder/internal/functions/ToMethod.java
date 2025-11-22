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
import static io.sundr.builder.Constants.INDEX_FIELD;
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
import io.sundr.model.Argument;
import io.sundr.model.ArgumentBuilder;
import io.sundr.model.Block;
import io.sundr.model.Break;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Declare;
import io.sundr.model.Expression;
import io.sundr.model.Field;
import io.sundr.model.FieldBuilder;
import io.sundr.model.For;
import io.sundr.model.Foreach;
import io.sundr.model.GreaterThanOrEqual;
import io.sundr.model.If;
import io.sundr.model.InstanceOf;
import io.sundr.model.Lambda;
import io.sundr.model.LessThan;
import io.sundr.model.LessThanOrEqual;
import io.sundr.model.LocalVariable;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
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

  static String getterOrBuildMethodName(Field item) {
    return (useBuildMethod(item) ? "build" + item.getNameCapitalized() : Getter.name(item));
  }

  private static boolean useBuildMethod(Field item) {
    if (Types.isMap(item.getTypeRef())) {
      return false;
    }
    TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());
    return BuilderUtils.isBuildable(unwrapped)
        || !Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(item).isEmpty();
  }

  private enum GeneratorType {
    FIRST("First", p -> ValueRef.from(0)), LAST("Last", p -> p.call("size").minus(1)), INDEXED("",
        p -> Field.newField("index"), true);

    GeneratorType(String name, Function<Field, Expression> toIndexExpression) {
      this(name, toIndexExpression, false);
    }

    GeneratorType(String name, Function<Field, Expression> toIndexExpression, boolean appendIndexArg) {
      this.name = name;
      this.toIndexExpression = toIndexExpression;
      this.appendIndexArg = appendIndexArg;
    }

    private Expression indexExpression(Field field) {
      return toIndexExpression.apply(field);
    }

    private void addIndexIfNeeded(MethodBuilder methodBuilder) {
      if (appendIndexArg) {
        methodBuilder.addToArguments(INDEX_FIELD.asArgument());
      }
    }

    private final String name;
    private final Function<Field, Expression> toIndexExpression;
    private final boolean appendIndexArg;
  }

  private interface GeneratorCustomizer {
    GeneratorCustomizer defaultCustomizer = new GeneratorCustomizer() {
      @Override
      public String methodPrefix(Field field) {
        return Getter.prefix(field);
      }

      @Override
      public Expression doWithItem(Expression expression) {
        return expression;
      }
    };
    GeneratorCustomizer builderCustomizer = new GeneratorCustomizer() {
      @Override
      public String methodPrefix(Field field) {
        return "build";
      }

      @Override
      public Expression doWithItem(Expression expression) {
        return expression.call("build");
      }
    };

    String methodPrefix(Field field);

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

    private Method method(Field field, TypeRef unwrapped) {
      final MethodBuilder methodBuilder = new MethodBuilder()
          .withComments()
          .withAnnotations()
          .withNewModifiers().withPublic().endModifiers()
          .withName(customizer.methodPrefix(field) + type.name + Singularize.FUNCTION.apply(field.getNameCapitalized()))
          .withReturnType(unwrapped);
      type.addIndexIfNeeded(methodBuilder);

      methodBuilder.withNewBlock()
          .withStatements(
              new Return(customizer.doWithItem(new This().ref(field).call("get", type.indexExpression(field)))))
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

    private Method method(Field field, TypeRef returnType, TypeDef predicate, TypeRef builderRef,
        List<AnnotationRef> annotations, List<String> comments) {
      return new MethodBuilder()
          .withComments(comments)
          .withAnnotations(annotations)
          .withNewModifiers().withPublic().endModifiers()
          .withName(operationName() + Singularize.FUNCTION.apply(field.getNameCapitalized()))
          .addNewArgument()
          .withName("predicate")
          .withTypeRef(predicate.toReference(builderRef))
          .endArgument()
          .withReturnType(returnType)
          .withNewBlock()
          .withStatements(statement(field, builderRef))
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

    private Statement statement(Field field, TypeRef builderRef) {
      LocalVariable item = LocalVariable.newLocalVariable(builderRef, "item");
      Argument predicate = Argument.newArgument("predicate");
      LocalVariable matchProperty = LocalVariable.newLocalVariable(match);
      LocalVariable nonMatchProperty = LocalVariable.newLocalVariable(nonMatch);

      if (match != null && nonMatch != null) {
        return new Block(new Foreach(item, field, If.condition(predicate.call("test", item))
            .then(Return.variable(matchProperty))
            .end()),
            Return.variable(nonMatchProperty.getName()));
      } else {
        return Return.variable(field).call("removeIf", new Lambda("item", (Expression) predicate.call("test", item)));
      }
    }

    MatchingType(String match, String nonMatch) {
      this.match = match;
      this.nonMatch = nonMatch;
    }

    private final String match;
    private final String nonMatch;
  }

  static final Function<Field, Method> WITH = FunctionFactory.cache(new Function<Field, Method>() {

    @Override
    public Method apply(Field field) {
      TypeRef returnType = field.hasAttribute(GENERIC_TYPE_REF) ? field.getAttribute(GENERIC_TYPE_REF) : T_REF;
      String methodName = "with" + field.getNameCapitalized();

      List<TypeParamDef> parameters = new ArrayList<>();
      if (field.getTypeRef() instanceof ClassRef) {
        ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF)
            .apply(field.getTypeRef());
        parameters.addAll(GetDefinition.of(baseType).getParameters());
      }

      return new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters).withName(methodName)
          .withReturnType(returnType).withArguments(field.asArgument()).withVarArgPreferred(true).withNewBlock()
          .withStatements(getStatements(field)).endBlock()
          .build();
    }

    private List<Statement> getStatements(Field field) {
      TypeRef returnType = field.hasAttribute(GENERIC_TYPE_REF) ? field.getAttribute(GENERIC_TYPE_REF) : T_REF;
      String fieldName = field.getName();
      TypeRef type = field.getTypeRef();
      TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(field.getTypeRef());
      List<Statement> statements = new ArrayList<>();
      Set<Field> descendants = field.hasAttribute(DESCENDANTS) ? field.getAttribute(DESCENDANTS)
          : Collections.emptySet();

      if (field.hasAttribute(DESCENDANT_OF)) {
        Field descendantOf = field.getAttribute(DESCENDANT_OF);
        fieldName = descendantOf.getName();
      }

      if (isBuildable(unwrapped)) {
        if (IS_COLLECTION.apply(type)) {
          statements.add(If.notNull(This.ref(fieldName))
              .then(new This().property("_visitables").call("get", ValueRef.from(fieldName)).call("clear"))
              .end());
        } else if (IS_MAP.apply(type)) {
          // There is no such thing as buildable map yet.
        } else {
          statements.add(new This().property("_visitables").call("remove", ValueRef.from(fieldName)));
        }
      }

      if (IS_MAP.apply(type)) {
        statements.add(If.isNull(field)
            .then(new This().ref(field).assignNull())
            .orElse(new This().ref(field).assignNew(LinkedHashMap.class, field)));

        statements.add(new Return(Expression.cast(returnType, new This())));
        return statements;
      } else if (IS_LIST.apply(type) || IS_SET.apply(type)) {
        String addToMethodName = "addTo" + field.getNameCapitalized();
        ClassRef newInstanceType = IS_LIST.apply(type) ? io.sundr.model.utils.Collections.ARRAY_LIST.toReference(unwrapped)
            : io.sundr.model.utils.Collections.LINKED_HASH_SET.toReference(unwrapped);
        if (Types.isConcrete(type)) {
          newInstanceType = (ClassRef) type;
        }

        Field item = Field.newField(unwrapped, "item");
        statements.add(If.notNull(field)
            .then(new This().ref(field).assignNew(newInstanceType),
                new Foreach(item, field, new This().call(addToMethodName, item)))
            .orElse(new This().ref(field).assignNull()));

        statements.add(new Return(Expression.cast(returnType, new This())));
        return statements;
      }

      if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
        ClassRef builder = BUILDER_REF.apply((ClassRef) unwrapped);
        statements.add(If.notNull(field)
            .then(new This().ref(field).assignNew(builder, field),
                new This().property("_visitables").call("get", ValueRef.from(field.getName())).call("add",
                    new This().ref(field)))
            .orElse(new This().ref(field).assignNull(),
                new This().property("_visitables").call("get", ValueRef.from(field.getName())).call("remove",
                    new This().ref(field))));
        statements.add(new Return(Expression.cast(returnType, new This())));
        return statements;
      }

      if (!descendants.isEmpty()) {
        Field builder = Field.newField(VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped), "builder");
        Field targetField = Field.newField(builder.getTypeRef(), fieldName);
        statements.add(If.isNull(targetField)
            .then(new This().ref(targetField).assignNull(),
                new This().ref("_visitables").call("remove", ValueRef.from(targetField.getName())),
                new Return(Expression.cast(returnType, new This())))
            .orElse(new Declare(builder, Expression.newCall("builder", targetField)),
                new This().ref("_visitables").call("get", ValueRef.from(targetField.getName())).call("clear"),
                new This().ref("_visitables").call("get", ValueRef.from(targetField.getName())).call("add",
                    builder),
                new This().ref(targetField).assign(builder),
                new Return(Expression.cast(returnType, new This()))));
        return statements;
      }

      statements.add(new This().ref(field).assign(field));
      statements.add(new Return(Expression.cast(returnType, new This())));
      return statements;
    }
  });

  static final Function<Field, Method> WITH_ARRAY = FunctionFactory.cache(field -> {
    TypeRef returnType = field.hasAttribute(GENERIC_TYPE_REF) ? field.getAttribute(GENERIC_TYPE_REF) : T_REF;

    String methodName = "with" + field.getNameCapitalized();
    TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(field.getTypeRef());
    String addToMethodName = "addTo" + field.getNameCapitalized();

    TypeRef arrayType = ARRAY_OF.apply(unwraped);
    Argument arrayField = new FieldBuilder(field).withTypeRef(arrayType).build().asArgument();

    Field _visitables = Field.newField("_visitables");
    LocalVariable item = LocalVariable.newLocalVariable(unwraped, "item");

    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(arrayField)
        .withVarArgPreferred(true)
        .withNewBlock()
        .withStatements(
            If.notNull(This.ref(field))
                .then(This.ref(field).call("clear"),
                    _visitables.call("remove", ValueRef.from(field.getName())))
                .end(),
            If.notNull(field)
                .then(new Foreach(new Declare(item), arrayField,
                    new This().call(addToMethodName, item)))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Field, List<Method>> WITH_OPTIONAL = FunctionFactory.cache(property -> {
    List<Method> methods = new ArrayList<>();
    TypeRef unwrapped = combine(UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    String methodName = "with" + property.getNameCapitalized();
    String fieldName = property.getName();
    Expression sourceRef = property;

    // Check for descendants
    TreeSet<Field> descendants = new TreeSet<>(Comparator.comparing(Field::getName));
    descendants.addAll(Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property));

    Field b;
    Block prepareBlock;
    if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
      ClassRef builder = BUILDER_REF.apply((ClassRef) unwrapped);
      b = Field.newField(builder, "b");
      prepareBlock = new Block(
          new Declare(b, Expression.createNew(builder, sourceRef)),
          This.ref("_visitables").call("get", ValueRef.from(fieldName)).call("add", b),
          This.ref(property)
              .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.singletonList(b))));
    } else if (!descendants.isEmpty()) {
      TypeRef builderType = VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped);
      b = Field.newField(builderType, "b");
      prepareBlock = new Block(
          new Declare(b, Expression.newCall("builder", sourceRef)),
          This.ref("_visitables").call("get", ValueRef.from(fieldName)).call("add", b),
          This.ref(property)
              .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.singletonList(b))));
    } else {
      b = null;
      prepareBlock = new Block(This.ref(property)
          .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.singletonList(sourceRef))));
    }

    methods.add(
        new MethodBuilder()
            .withNewModifiers().withPublic().endModifiers()
            .withName(methodName)
            .withReturnType(returnType)
            .withArguments(property.asArgument())
            .withNewBlock()
            .withStatements(
                If.condition(property.isNull().or(property.call("isPresent").not()))
                    .then(This.ref(property).assign(Expression.call(property.getTypeRef(), "empty")))
                    .orElse((isBuildable(unwrapped) && !isAbstract(unwrapped)) || !descendants.isEmpty()
                        ? Block.wrap(
                            new Declare(b, (isBuildable(unwrapped) && !isAbstract(unwrapped))
                                ? Expression.createNew(BUILDER_REF.apply((ClassRef) unwrapped), property.call("get"))
                                : Expression.newCall("builder", property.call("get"))),
                            This.ref("_visitables").call("get", ValueRef.from(property.getName()))
                                .call("add", b),
                            This.ref(property).assign(Expression.call(Optional.class, "of", b)))
                        : This.ref(property).assign(property)),
                new Return(Expression.cast(returnType, new This())))
            .endBlock()
            .build());

    Argument genericProperty = new FieldBuilder(property).withTypeRef(unwrapped).build().asArgument();
    methods.add(new MethodBuilder().withNewModifiers().withPublic().endModifiers().withName(methodName)
        .withReturnType(returnType).withArguments(genericProperty).withNewBlock()
        .withStatements(If.isNull(property)
            .then(This.ref(fieldName).assign(property.getAttribute(INIT_EXPRESSION)))
            .orElse(prepareBlock),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build());

    return methods;
  });

  static final Function<Field, Method> HAS = FunctionFactory.cache(property -> {
    String prefix = "has";
    String methodName = prefix + property.getNameCapitalized();
    List<Statement> statements = new ArrayList<>();

    if (isPrimitive(property.getTypeRef())) {
      statements.add(Return.True());
    } else if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
      statements
          .add(new Return(This.ref(property).notNull().and(This.ref(property).call("isEmpty").not())));
    } else if (isOptional(property.getTypeRef()) || isOptionalInt(property.getTypeRef())
        || isOptionalLong(property.getTypeRef()) || isOptionalDouble(property.getTypeRef())) {
      statements.add(new Return(This.ref(property).notNull().and(This.ref(property).call("isPresent"))));
    } else {
      statements.add(new Return(This.ref(property).notNull()));
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

  static final Function<Field, List<Method>> GETTER = FunctionFactory.cache(property -> {
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

    TreeSet<Field> descendants = new TreeSet<>(Comparator.comparing(Field::getName));
    descendants.addAll(Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property));

    if (isMap) {
      statements.add(This.ref(property).ret());
    } else if (isBuildable(unwrapped)) {
      isNested = true;
      if (isList || isSet) {
        if (isAbstractList || isAbstractSet) {
          statements.add(new Return(Expression.ternary(This.ref(property).notNull(),
              Expression.newCall("build", property),
              Expression.NULL)));
        } else {
          statements.add(new Return(Expression.ternary(This.ref(property).notNull(),
              Expression.createNew((ClassRef) property.getTypeRef(), Expression.newCall("build", property)),
              Expression.NULL)));
        }
      } else if (isOptional) {
        statements.add(new Return(
            new Ternary(
                //Condition
                This.ref(property).notNull(),
                //Then
                This.ref(property).call("map", Expression.lambda("v", LocalVariable.newLocalVariable("v").call("build"))),
                //Else
                call(OPTIONAL, "empty"))));
      } else {
        statements.add(new Return(new Ternary(
            //Condition
            This.ref(property).notNull(),
            //Then
            This.ref(property).call("build"),
            //Else
            Expression.NULL)));
      }
    } else if (!descendants.isEmpty()) {
      isNested = true;
      if (isList || isSet) {
        statements.add(new Return(Expression.newCall("build", property)));
      } else if (isOptional) {
        statements.add(new Return(
            new Ternary(
                //Condition
                This.ref(property).notNull(),
                //Then
                This.ref(property).call("map", Expression.lambda("v", LocalVariable.newLocalVariable("v").call("build"))),
                //Else
                call(OPTIONAL, "empty"))));
      } else {
        statements.add(new Return(new Ternary(
            //Condition
            This.ref(property).notNull(),
            //Then
            This.ref(property).call("build"),
            //Else
            Expression.NULL)));
      }
    } else {
      statements.add(new Return(This.ref(property)));
    }

    // Determine the return type - use wildcard for interfaces with descendants
    TypeRef returnType = property.getTypeRef();
    if (isNested && !descendants.isEmpty()) {
      if (isOptional) {
        // For build methods of Optional<InterfaceType> with descendants, keep original type
        // to match constructor signature - no wildcard needed
      } else if (isList || isSet) {
        // For build methods of List<InterfaceType> with descendants, keep original type
        // to match constructor signature - no wildcard needed
      }
    }

    Method getter = new MethodBuilder()
        .withComments(comments)
        .withAnnotations(annotations)
        .withNewModifiers().withPublic().endModifiers()
        .withName(isNested ? builderName : getterName)
        .withReturnType(returnType)
        .withArguments()
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

  static final Function<Field, List<Method>> GETTER_ARRAY = FunctionFactory.cache(property -> {
    List<Method> methods = new ArrayList<>();
    List<AnnotationRef> annotations = new ArrayList<>();
    List<String> comments = new ArrayList<>();

    String getterName = Getter.name(property);
    String builderName = "build" + property.getNameCapitalized();
    TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
    TypeDef predicate = Constants.PREDICATE;

    TypeRef type = property.getTypeRef();
    Boolean isBuildable = isBuildable(unwrapped);
    boolean isOptional = isOptional(property.getTypeRef()) || isOptionalDouble(property.getTypeRef())
        || isOptionalInt(property.getTypeRef()) || isOptionalLong(property.getTypeRef());
    TypeRef targetType = isBuildable ? VISITABLE_BUILDER_REF.apply((ClassRef) unwrapped) : UNWRAP_ARRAY_OF.apply(type);

    List<Statement> statements = new ArrayList<>();

    LocalVariable size = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "size");
    LocalVariable result = LocalVariable.newLocalVariable(property.getTypeRef(), "result");
    LocalVariable index = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "index");
    Field item = Field.newField(targetType, "item");

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

    if (isBuildable && !isOptional) {

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

  static final Function<Field, List<Method>> ADD_TO_COLLECTION = FunctionFactory
      .cache(new Function<Field, List<Method>>() {
        @Override
        public List<Method> apply(final Field property) {
          List<Method> methods = new ArrayList<>();
          TypeRef baseType = UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
          TypeRef builderType = VISITABLE_BUILDER_REF.apply((ClassRef) baseType);
          Argument builderProperty = new FieldBuilder(property).withName("builder").withTypeRef(builderType).build()
              .asArgument();

          TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

          TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
          final TypeRef unwrapped = BOXED_OF.apply(combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef()));

          Argument items = new FieldBuilder(property).withName("items").withTypeRef(unwrapped.withDimensions(1)).build()
              .asArgument();

          Argument unwrappedProperty = new FieldBuilder(property).withName("item").withTypeRef(unwrapped).build().asArgument();

          List<TypeParamDef> parameters = new ArrayList<>();

          String addVarargMethodName = "addTo" + property.getNameCapitalized();
          String setMethodName = "setTo" + property.getNameCapitalized();
          String addAllMethodName = "addAllTo" + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef);

          Set<Field> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);

          String propertyName = property.getName();
          if (property.hasAttribute(Constants.DESCENDANT_OF)) {
            Field attrValue = property.getAttribute(Constants.DESCENDANT_OF);
            if (attrValue != null) {
              propertyName = (attrValue).getName();
            }
          }

          If init = If.isNull(This.ref(propertyName))
              .then(This.ref(propertyName)
                  .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.emptyList())))
              .end();

          Method addSingleItemAtIndex = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(addVarargMethodName).withReturnType(returnType)
              .addToArguments(INDEX_FIELD.asArgument())
              .addToArguments(unwrappedProperty).withNewBlock()
              .withStatements(init,
                  This.ref(propertyName).call("add", Argument.newArgument("index"), Argument.newArgument("item")),
                  new Return(Expression.cast(returnType, new This())))
              .endBlock()
              .build();

          Method setSingleItemAtIndex = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(setMethodName).withReturnType(returnType)
              .addToArguments(INDEX_FIELD.asArgument())
              .addToArguments(unwrappedProperty).withNewBlock()
              .withStatements(init,
                  This.ref(propertyName).call("set", Argument.newArgument("index"), Argument.newArgument("item")),
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
            Field item = Field.newField(unwrapped, "item");
            Field builder = Field.newField(BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Argument.newArgument("items"),
                Block.wrap(
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), item)),
                    This.ref("_visitables").call("get", ValueRef.from(propertyName)).call("add", builder),
                    This.ref(propertyName).call("add", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));

            addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), Argument.newArgument("item"))),
                    createAddOrSetIndex("add", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

            setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), Argument.newArgument("item"))),
                    createAddOrSetIndex("set", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

          } else if (!descendants.isEmpty()) {
            final ClassRef targetType = (ClassRef) unwrapped;
            parameters.addAll(GetDefinition.of(targetType).getParameters());

            Field item = Field.newField(targetType, "item");
            Field builder = Field.newField(VISITABLE_BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Argument.newArgument("items"),
                Block.wrap(
                    new Declare(builder, Expression.newCall("builder", item)),
                    This.ref("_visitables").call("get", ValueRef.from(propertyName)).call("add", builder),
                    This.ref(propertyName).call("add", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));

            addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.newCall("builder", Argument.newArgument("item"))),
                    createAddOrSetIndex("add", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

            setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex).withParameters(parameters).editBlock()
                .withStatements(init,
                    new Declare(builder, Expression.newCall("builder", Argument.newArgument("item"))),
                    createAddOrSetIndex("set", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build();

            methods
                .add(
                    new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters)
                        .withName(addVarargMethodName).withReturnType(returnType).withArguments(builderProperty).withNewBlock()
                        .addToStatements(init,
                            This.ref("_visitables").call("get", ValueRef.from(propertyName)).call("add",
                                Argument.newArgument("builder")),
                            This.ref(propertyName).call("add", Argument.newArgument("builder")),
                            new Return(Expression.cast(returnType, new This())))
                        .endBlock().build());

            methods.add(new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters)
                .withName(addVarargMethodName).withReturnType(returnType)
                .withArguments(INDEX_FIELD.asArgument(), builderProperty).withNewBlock()
                .addToStatements(init, createAddOrSetIndex("add", propertyName, returnType.toString()),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build());

          } else {
            Field item = Field.newField(unwrapped, "item");
            statements.add(new Foreach(item, Argument.newArgument("items"),
                This.ref(property.getName()).call("add", item)));
            statements.add(new Return(Expression.cast(returnType, new This())));
          }

          Method addVaragToCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(addVarargMethodName).withReturnType(returnType).withArguments(items)
              .withVarArgPreferred(true).withNewBlock().addAllToStatements(statements).endBlock()
              .build();

          Method addAllToCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(addAllMethodName).withReturnType(returnType)
              .withArguments(new ArgumentBuilder(items).withTypeRef(COLLECTION.toReference(unwrapped)).build()).withNewBlock()
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
          Expression index = Argument.newArgument("index");
          Expression property = This.ref(propertyName);
          ValueRef propertyNameValue = ValueRef.from(propertyName);
          Expression _visitables = This.ref("_visitables");
          Expression builder = Argument.newArgument("builder");
          ValueRef zero = ValueRef.from(0);
          return If.condition(Expression.or(new LessThan(index, zero), new GreaterThanOrEqual(index, property.call("size"))))
              .then(_visitables.call("get", propertyNameValue).call("add", builder),
                  property.call("add", builder))
              .orElse(_visitables.call("get", propertyNameValue).call("add", builder),
                  property.call(op, index, builder));
        }
      });

  static final Function<Field, List<Method>> REMOVE_FROM_COLLECTION = FunctionFactory
      .cache(new Function<Field, List<Method>>() {
        @Override
        public List<Method> apply(final Field property) {
          List<Method> methods = new ArrayList<>();
          ClassRef baseType = (ClassRef) UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
          TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

          TypeRef builderType = VISITABLE_BUILDER_REF.apply(baseType);
          Argument visitableBuilderArgument = Argument.newArgument(builderType, "builder");

          TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
          final TypeRef unwrapped = BOXED_OF.apply(combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef()));
          Argument items = Argument.newArgument(unwrapped.withDimensions(1), "items");
          List<TypeParamDef> parameters = new ArrayList<>();

          String removeVarargMethodName = "removeFrom" + property.getNameCapitalized();
          String removeAllMethodName = "removeAllFrom" + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef);
          String removeMatchingMethodName = "removeMatchingFrom"
              + BuilderUtils.qualifyPropertyName(property, baseType, originTypeDef);

          String propertyName = property.getName();
          List<Statement> statements = new ArrayList<>();
          boolean isSimple = false;

          Set<Field> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);
          if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
            final ClassRef targetType = (ClassRef) unwrapped;
            if (property.hasAttribute(Constants.DESCENDANT_OF)) {
              Field attrValue = property.getAttribute(Constants.DESCENDANT_OF);
              if (attrValue != null) {
                propertyName = attrValue.getName();
              }
            }
            String targetClass = targetType.getFullyQualifiedName();
            parameters.addAll(GetDefinition.of(targetType).getParameters());
            String builderClass = targetClass + "Builder";

            //We need to do it more elegantly
            statements.add(nullCheck(returnType, propertyName));
            Field item = Field.newField(targetType, "item");
            LocalVariable builder = LocalVariable.newLocalVariable(BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, items,
                Block.wrap(
                    new Declare(builder, Expression.createNew(BUILDER_REF.apply(targetType), item)),
                    Field.newField("_visitables").call("get", ValueRef.from(propertyName)).call("remove", builder),
                    This.ref(propertyName).call("remove", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));
          } else if (!descendants.isEmpty()) {
            final ClassRef targetType = (ClassRef) unwrapped;
            parameters.addAll(GetDefinition.of(targetType).getParameters());
            statements.add(nullCheck(returnType, propertyName));
            Field item = Field.newField(targetType, "item");
            LocalVariable builder = LocalVariable.newLocalVariable(VISITABLE_BUILDER_REF.apply(targetType), "builder");
            statements.add(new Foreach(item, Argument.newArgument("items"),
                Block.wrap(
                    new Declare(builder, Expression.newCall("builder", item)),
                    This.ref("_visitables").call("get", ValueRef.from(property.getName())).call("remove", builder),
                    This.ref(property.getName()).call("remove", builder))));
            statements.add(new Return(Expression.cast(returnType, new This())));

            methods.add(new MethodBuilder().withNewModifiers().withPublic().endModifiers().withParameters(parameters)
                .withName(removeVarargMethodName).withReturnType(returnType).withArguments(visitableBuilderArgument)
                .withNewBlock()
                .addToStatements(nullCheck(returnType, propertyName),
                    This.ref("_visitables").call("get", ValueRef.from(propertyName)).call("remove",
                        Argument.newArgument("builder")),
                    This.ref(propertyName).call("remove", Argument.newArgument("builder")),
                    new Return(Expression.cast(returnType, new This())))
                .endBlock().build());
          } else {
            isSimple = true;
            statements.add(nullCheck(returnType, propertyName));
            Field item = Field.newField(unwrapped, "item");
            statements.add(new Foreach(item, Argument.newArgument("items"),
                This.ref(property.getName()).call("remove", item)));
            statements.add(new Return(Expression.cast(returnType, new This())));
          }

          Method removeVarargFromCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withName(removeVarargMethodName).withParameters(parameters).withReturnType(returnType).withArguments(items)
              .withVarArgPreferred(true).withNewBlock().withStatements(statements).endBlock().build();

          Method removeAllFromCollection = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withParameters(parameters).withName(removeAllMethodName).withReturnType(returnType)
              .withArguments(new ArgumentBuilder(items).withTypeRef(COLLECTION.toReference(unwrapped)).build()).withNewBlock()
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
            // Create field and local variable references for cleaner code
            Field propertyRef = Field.newField(propertyName);
            LocalVariable eachProperty = LocalVariable.newLocalVariable(
                new ClassRefBuilder().withFullyQualifiedName("java.util.Iterator").withArguments(builder).build(), "each");
            LocalVariable visitablesProperty = LocalVariable
                .newLocalVariable(new ClassRefBuilder().withFullyQualifiedName("java.util.List").build(), "visitables");

            LocalVariable builderProperty = LocalVariable.newLocalVariable(builder, "builder");
            Argument predicateProperty = Argument.newArgument("predicate");

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
                        This.ref("_visitables").call("get", ValueRef.from(propertyName))),
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
          return If.isNull(This.ref(propertyName))
              .then(new Return(Expression.cast(returnType, new This())))
              .end();
        }
      });

  static final Function<Field, Method> ADD_MAP_TO_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeRef mapType = property.getTypeRef();
    Argument mapProperty = new FieldBuilder().withName("map").withTypeRef(mapType).build().asArgument();
    String methodName = "addTo" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(mapProperty)
        .withNewBlock()
        .withStatements(
            If.condition(This.ref(property.getName()).isNull().and(Argument.newArgument("map").notNull()))
                .then(This.ref(property.getName())
                    .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.emptyList())))
                .end(),
            If.notNull(Argument.newArgument("map"))
                .then(This.ref(property.getName()).call("putAll", Argument.newArgument("map")))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Field, List<Method>> ADD_NEW_VALUE_TO_MAP = property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Map type and found:" + property.getTypeRef());
    }
    ClassRef mapType = (ClassRef) property.getTypeRef();
    TypeRef keyType = mapType.getArguments().get(0);
    TypeRef valueType = mapType.getArguments().get(1);

    Argument keyProperty = new FieldBuilder().withName("key").withTypeRef(keyType).build().asArgument();
    Argument valueProperty = new FieldBuilder().withName("value").withTypeRef(valueType).build().asArgument();
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
      Field attrValue = property.getAttribute(Constants.DESCENDANT_OF);
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

    // Create field and local variable references for cleaner code
    Expression thisPropertyRef = This.ref(propertyName);
    Argument keyRef = Argument.newArgument(keyProperty.getName());
    LocalVariable toEditProperty = LocalVariable.newLocalVariable(
        new ClassRefBuilder().withFullyQualifiedName(fullyQualifiedBaseType).build(),
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

  static final Function<Field, Method> ADD_TO_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Map type and found:" + property.getTypeRef());
    }
    ClassRef mapType = (ClassRef) property.getTypeRef();
    TypeRef keyType = mapType.getArguments().get(0);
    TypeRef valueType = mapType.getArguments().get(1);

    Argument keyProperty = new FieldBuilder().withName("key").withTypeRef(keyType).build().asArgument();
    Argument valueProperty = new FieldBuilder().withName("value").withTypeRef(valueType).build().asArgument();
    String methodName = "addTo" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(new Argument[] { keyProperty, valueProperty })
        .withNewBlock()
        .withStatements(
            If.condition(This.ref(property.getName()).isNull()
                .and(Argument.newArgument("key").notNull())
                .and(Argument.newArgument("value").notNull()))
                .then(This.ref(property.getName())
                    .assign(property.getAttribute(INIT_EXPRESSION_FUNCTION).apply(Collections.emptyList())))
                .end(),
            If.condition(Argument.newArgument("key").notNull().and(Argument.newArgument("value").notNull()))
                .then(This.ref(property.getName()).call("put", Argument.newArgument("key"),
                    Argument.newArgument("value")))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Field, Method> REMOVE_MAP_FROM_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    TypeRef mapType = property.getTypeRef();
    Argument mapProperty = new FieldBuilder().withName("map").withTypeRef(mapType).build().asArgument();
    String methodName = "removeFrom" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(mapProperty)
        .withNewBlock()
        .withStatements(
            If.isNull(This.ref(property.getName()))
                .then(new Return(Expression.cast(returnType, new This())))
                .end(),
            If.notNull(Argument.newArgument("map"))
                .then(
                    new Foreach(LocalVariable.newLocalVariable(Object.class, "key"), Argument.newArgument("map").call("keySet"),
                        If.notNull(This.ref(property.getName()))
                            .then(This.ref(property.getName()).call("remove", LocalVariable.newLocalVariable("key")))
                            .end()))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Field, Method> REMOVE_FROM_MAP = FunctionFactory.cache(property -> {
    TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
    ClassRef mapType = (ClassRef) property.getTypeRef();
    TypeRef keyType = mapType.getArguments().get(0);

    Argument keyProperty = new FieldBuilder().withName("key").withTypeRef(keyType).build().asArgument();
    String methodName = "removeFrom" + property.getNameCapitalized();
    return new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withName(methodName)
        .withReturnType(returnType)
        .withArguments(keyProperty)
        .withNewBlock()
        .withStatements(
            If.isNull(This.ref(property.getName()))
                .then(new Return(Expression.cast(returnType, new This())))
                .end(),
            If.condition(Argument.newArgument("key").notNull().and(This.ref(property.getName()).notNull()))
                .then(This.ref(property.getName()).call("remove", Argument.newArgument("key")))
                .end(),
            new Return(Expression.cast(returnType, new This())))
        .endBlock()
        .build();
  });

  static final Function<Field, Method> WITH_NEW_NESTED = property -> {
    ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF)
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

  static final Function<Field, Set<Method>> WITH_NESTED_INLINE = property -> {
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
        Field attrValue = property.getAttribute(Constants.DESCENDANT_OF);
        if (attrValue != null) {
          delegateName = delegatePrefix + (attrValue).getNameCapitalized();
        }
      }

      String args = Strings.join(constructor.getArguments(), Argument::getName, ", ");

      result.add(new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(returnType)
          .withArguments(constructor.getArguments()).withName(ownName).withParameters(baseType.getParameters()).withNewBlock()
          .withStatements(new Return(Expression.cast(returnType,
              new This().call(delegateName, Expression.createNew(baseType.toInternalReference(),
                  constructor.getArguments().toArray(new Expression[0]))))))
          .endBlock().build());
    }

    return result;
  };

  static final Function<Field, Method> EDIT_OR_NEW = property -> {
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

  static final Function<Field, Method> EDIT_OR_NEW_LIKE = property -> {
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

    Expression elseValue = Argument.newArgument("item");
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

  static final Function<Field, Method> WITH_NEW_LIKE_NESTED = property -> {
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
            ? Expression.createNew(rewraped, ValueRef.from(-1), Argument.newArgument("item"))
            : Expression.createNew(rewraped, Argument.newArgument("item"))))
        .endBlock()
        .build();

  };

  static final Function<Field, Method> WITH_NEW_LIKE_NESTED_AT_INDEX = property -> {
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
        .addToArguments(0, INDEX_FIELD.asArgument())
        .withName(method.getName().replaceFirst("add", "set"))
        .editBlock()
        .withStatements(new Return(Expression.createNew(rewraped, Argument.newArgument("index"), Argument.newArgument("item"))))
        .endBlock()
        .build();
  };

  static final Function<Field, List<Method>> EDIT_NESTED = property -> {
    List<Method> methods = new ArrayList<>();
    TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);
    if (!(property.getTypeRef() instanceof ClassRef)) {
      throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
    }
    ClassRef unwrapped = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF)
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
      // Create field and local variable references for cleaner code
      Field propertyRef = Field.newField(property.getName());
      LocalVariable indexProperty = LocalVariable.newLocalVariable(int.class, "index");

      methods.add(new MethodBuilder(base)
          .withArguments(INDEX_FIELD.asArgument())
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

      // Additional local variables for editMatching method
      LocalVariable iProperty = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "i");
      Argument predicateProperty = Argument.newArgument("predicate");

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

  private static String createWithNewStatement(Field property, String methodNameBase, String elseValue) {
    return "return withNew" + methodNameBase + "Like(Optional.ofNullable("
        + getterOrBuildMethodName(property) + "())"
        + (isOptional(property.getTypeRef()) ? ".flatMap(Function.identity())" : "")
        + ".orElse(" + elseValue + "));";
  }

  private static Statement createWithNewStatementTyped(Field property, String methodNameBase, Expression elseValue) {
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

  static final Function<Field, Method> AND = new Function<Field, Method>() {
    @Override
    public Method apply(Field property) {
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
        Field attrValue = property.getAttribute(Constants.DESCENDANT_OF);
        if (attrValue != null) {
          withMethodName = prefix + (attrValue).getNameCapitalized();
        }
      }

      List<Expression> args = new ArrayList<>();
      if (isArray || isList) {
        args.add(Argument.newArgument("index"));
      } else if (isMap) {
        args.add(Argument.newArgument("key"));
      }
      args.add(Argument.newArgument("builder").call("build"));

      Expression target = classPrefix.isEmpty() ? new This()
          : Field.newField(classPrefix.substring(0, classPrefix.length() - 1)); // Remove trailing "."

      return new MethodBuilder().withNewModifiers().withPublic().endModifiers().withReturnType(N_REF).withName("and")
          .withNewBlock()
          .withStatements(new Return(Expression.cast(N_REF, target.call(withMethodName, args.toArray(new Expression[0])))))
          .endBlock().build();

    }

    private String getClassPrefix(Field property) {
      ClassRef memberOf = property.getAttribute(OUTER_TYPE);
      if (memberOf != null) {
        return memberOf.getName() + ".this.";
      } else {
        return "";
      }
    }

  };

  static final Function<Field, Method> END = FunctionFactory.cache(property -> {
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
