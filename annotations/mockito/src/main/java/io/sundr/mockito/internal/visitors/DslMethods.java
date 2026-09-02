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

package io.sundr.mockito.internal.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.sundr.mockito.internal.Constants;
import io.sundr.mockito.internal.MockTarget;
import io.sundr.model.Argument;
import io.sundr.model.Assign;
import io.sundr.model.Block;
import io.sundr.model.Cast;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Construct;
import io.sundr.model.Declare;
import io.sundr.model.Equals;
import io.sundr.model.Expression;
import io.sundr.model.GreaterThan;
import io.sundr.model.If;
import io.sundr.model.Kind;
import io.sundr.model.Lambda;
import io.sundr.model.LocalVariable;
import io.sundr.model.LogicalAnd;
import io.sundr.model.Method;
import io.sundr.model.NotEquals;
import io.sundr.model.Property;
import io.sundr.model.Return;
import io.sundr.model.Statement;
import io.sundr.model.This;
import io.sundr.model.Throw;
import io.sundr.model.Try;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeParamRefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.ValueRef;
import io.sundr.model.WildcardRef;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

/**
 * Shared factories for the members of the generated stub and verify builder classes.
 */
final class DslMethods {

  private DslMethods() {
  }

  /**
   * Creates an inner class holding the mock reference: modifiers, the mock field and a
   * constructor that assigns the mock and initializes one slot per mocked method argument.
   */
  static TypeDefBuilder innerClass(MockTarget target, String name) {
    ClassRef targetRef = target.getTargetRef();
    return new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(name)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withStatic().withFinal().endModifiers()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetRef).withName(Constants.MOCK)
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Assign(This.ref(Constants.MOCK),
            LocalVariable.newLocalVariable(targetRef, Constants.MOCK)))
        .endBlock()
        .endConstructor();
  }

  /**
   * Creates an inner class of the answer-first do-family: it holds a Mockito {@code Stubber}
   * alongside the mock, and a constructor that assigns both then initializes one slot per
   * mocked method argument. The stubbing value/answer is carried by the stubber, so the
   * terminal only needs to replay {@code stubber.when(mock).method(matchers)}.
   */
  static TypeDefBuilder doInnerClass(MockTarget target, String name) {
    ClassRef targetRef = target.getTargetRef();
    return new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(name)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withStatic().withFinal().endModifiers()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER)
        .endField()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetRef).withName(Constants.MOCK)
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(Constants.STUBBER_TYPE).withName(Constants.STUBBER).endArgument()
        .addNewArgument().withTypeRef(targetRef).withName(Constants.MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Assign(This.ref(Constants.STUBBER),
            LocalVariable.newLocalVariable(Constants.STUBBER_TYPE, Constants.STUBBER)))
        .addToStatements(new Assign(This.ref(Constants.MOCK),
            LocalVariable.newLocalVariable(targetRef, Constants.MOCK)))
        .endBlock()
        .endConstructor();
  }

  static void addSlots(TypeDefBuilder builder, Collection<Argument> arguments) {
    List<Statement> slotInits = new ArrayList<>();
    for (Argument argument : arguments) {
      ClassRef slotRef = Constants.slotFor(argument.getTypeRef());
      String slot = slotName(argument);
      builder.addNewField()
          .withNewModifiers().withPrivate().withFinal().endModifiers()
          .withTypeRef(slotRef).withName(slot)
          .endField();
      slotInits.add(new Assign(This.ref(slot),
          new Construct(slotRef, new ArrayList<>(slotRef.getArguments()), Collections.emptyList())));
    }
    builder.editFirstConstructor()
        .editBlock()
        .addAllToStatements(slotInits)
        .endBlock()
        .endConstructor();
  }

  /**
   * Adds the pinned-name set, the overload selector, the exact flag and the
   * {@code andNoOtherArgs()} refiner used by shared builders of overloaded methods.
   */
  static void addOverloadSelection(TypeDefBuilder builder, List<Method> overloads, ClassRef selfRef) {
    ClassRef pinnedRef = new ClassRefBuilder(Constants.SET).withArguments(Constants.STRING).build();
    builder.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(pinnedRef).withName(Constants.PINNED)
        .endField();
    builder.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(Constants.OVERLOAD_SELECTOR).withName(Constants.SELECTOR)
        .endField();
    builder.addNewField()
        .withNewModifiers().withPrivate().endModifiers()
        .withTypeRef(Types.PRIMITIVE_BOOLEAN_REF).withName(Constants.EXACT)
        .endField();

    Expression selectorInit = new Construct(Constants.OVERLOAD_SELECTOR);
    for (Method method : overloads) {
      Expression[] names = method.getArguments().stream()
          .map(argument -> (Expression) ValueRef.from(slotName(argument)))
          .toArray(Expression[]::new);
      selectorInit = selectorInit.call("overload", names);
    }

    List<Statement> inits = new ArrayList<>();
    inits.add(new Assign(This.ref(Constants.PINNED),
        new Construct(Constants.LINKED_HASH_SET, Collections.singletonList((TypeRef) Constants.STRING),
            Collections.emptyList())));
    inits.add(new Assign(This.ref(Constants.SELECTOR), selectorInit));
    builder.editFirstConstructor()
        .editBlock()
        .addAllToStatements(inits)
        .endBlock()
        .endConstructor();

    List<Statement> statements = new ArrayList<>();
    statements.add(new Assign(This.ref(Constants.EXACT), ValueRef.from(true)));
    statements.add(new Return(new This()));
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef)
        .withName("andNoOtherArgs")
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .endMethod();
  }

  static void addWithers(TypeDefBuilder builder, Collection<Argument> arguments, ClassRef selfRef,
      boolean trackPinned) {
    for (Argument argument : arguments) {
      String slot = slotName(argument);
      String capitalized = Strings.capitalizeFirst(argument.getName());
      boolean generic = isTypeVariableArgument(argument);
      List<TypeParamDef> typeParameters = generic
          ? Collections.singletonList(freshTypeParam(capitalized))
          : Collections.emptyList();
      TypeRef argumentType = generic ? freshTypeParamRef(capitalized) : argument.getTypeRef();

      Expression valueArg = LocalVariable.newLocalVariable(argumentType, "value");
      if (generic) {
        valueArg = new Cast(Constants.OBJECT, valueArg);
      }
      addFluentMethod(builder, selfRef, "with" + capitalized, argumentType, "value",
          This.ref(slot).call("eq", valueArg), slot, trackPinned, typeParameters);

      ClassRef matcherRef = new ClassRefBuilder(Constants.ARGUMENT_MATCHER)
          .withArguments(Types.box(argumentType)).build();
      ClassRef objectMatcherRef = new ClassRefBuilder(Constants.ARGUMENT_MATCHER)
          .withArguments(Constants.OBJECT).build();
      Expression matcherArg = LocalVariable.newLocalVariable(matcherRef, "matcher");
      if (generic) {
        matcherArg = new Cast(objectMatcherRef, matcherArg);
      }
      addFluentMethod(builder, selfRef, "with" + capitalized + "Matching", matcherRef, "matcher",
          This.ref(slot).call("matching", matcherArg), slot, trackPinned, typeParameters);

      if (Constants.STRING.equals(argument.getTypeRef())) {
        addStringPredicateWithers(builder, selfRef, capitalized, slot, trackPinned);
      }
    }
  }

  /**
   * Whether the argument's declared type was one of the mocked type's own type variables (tagged by
   * {@code MockTarget}). Such an argument keeps its {@code Object} erasure for the slot and mock
   * invocation, but its withers are made generic so the call site infers the concrete type.
   */
  private static boolean isTypeVariableArgument(Argument argument) {
    return argument.hasAttribute(Constants.TYPE_VARIABLE_ARG)
        && Boolean.TRUE.equals(argument.getAttribute(Constants.TYPE_VARIABLE_ARG));
  }

  /** A fresh method type parameter named after the argument, e.g. {@code E_Message}. */
  private static TypeParamDef freshTypeParam(String capitalized) {
    return new TypeParamDefBuilder().withName("E_" + capitalized).build();
  }

  private static TypeRef freshTypeParamRef(String capitalized) {
    return new TypeParamRefBuilder().withName("E_" + capitalized).build();
  }

  private static final List<String> STRING_PREDICATES = Arrays.asList(
      "Contains", "StartsWith", "EndsWith", "Matches");

  /**
   * Adds the string-predicate withers of a {@link String} argument: {@code withXxxContains},
   * {@code withXxxStartsWith}, {@code withXxxEndsWith} and {@code withXxxMatches}. Each pins the slot
   * to a null-safe matcher {@code s -> s != null && s.<predicate>(expected)}, giving the same reach
   * as Mockito's {@code contains}/{@code startsWith}/{@code endsWith}/{@code matches} without the
   * caller writing a lambda.
   */
  private static void addStringPredicateWithers(TypeDefBuilder builder, ClassRef selfRef, String capitalized,
      String slot, boolean trackPinned) {
    for (String predicate : STRING_PREDICATES) {
      LocalVariable expected = LocalVariable.newLocalVariable(Constants.STRING, "expected");
      LocalVariable candidate = LocalVariable.newLocalVariable(Constants.STRING, "s");
      Expression predicateCall = candidate.call(predicateMethod(predicate), expected);
      Expression body = new LogicalAnd(new NotEquals(candidate, ValueRef.NULL), predicateCall);
      Statement slotStatement = This.ref(slot).call("matching", new Lambda("s", body));
      addFluentMethod(builder, selfRef, "with" + capitalized + predicate, Constants.STRING, "expected",
          slotStatement, slot, trackPinned);
    }
  }

  private static String predicateMethod(String predicate) {
    return Character.toLowerCase(predicate.charAt(0)) + predicate.substring(1);
  }

  /**
   * Adds the {@code withXxxAny()} pins of shared builders: they mark the parameter as
   * present for overload selection while leaving its matcher as {@code any()}.
   */
  static void addAnyPins(TypeDefBuilder builder, Collection<Argument> arguments, ClassRef selfRef) {
    for (Argument argument : arguments) {
      String slot = slotName(argument);
      List<Statement> statements = new ArrayList<>();
      statements.add(This.ref(Constants.PINNED).call("add", slot));
      statements.add(new Return(new This()));
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(selfRef)
          .withName("with" + Strings.capitalizeFirst(argument.getName()) + "Any")
          .withNewBlock()
          .withStatements(statements)
          .endBlock()
          .endMethod();
    }
  }

  static void addCapturing(TypeDefBuilder builder, Collection<Argument> arguments, ClassRef selfRef,
      boolean trackPinned) {
    for (Argument argument : arguments) {
      String slot = slotName(argument);
      String capitalized = Strings.capitalizeFirst(argument.getName());
      boolean generic = isTypeVariableArgument(argument);
      List<TypeParamDef> typeParameters = generic
          ? Collections.singletonList(freshTypeParam(capitalized))
          : Collections.emptyList();
      TypeRef captured = generic ? freshTypeParamRef(capitalized) : Types.box(argument.getTypeRef());
      ClassRef captorRef = new ClassRefBuilder(Constants.ARGUMENT_CAPTOR).withArguments(captured).build();
      Expression captorArg = LocalVariable.newLocalVariable(captorRef, "captor");
      if (generic) {
        ClassRef objectCaptorRef = new ClassRefBuilder(Constants.ARGUMENT_CAPTOR)
            .withArguments(Constants.OBJECT).build();
        captorArg = new Cast(objectCaptorRef, captorArg);
      }
      addFluentMethod(builder, selfRef, "capturing" + capitalized, captorRef, "captor",
          This.ref(slot).call("capturing", captorArg), slot, trackPinned, typeParameters);
    }
  }

  private static void addFluentMethod(TypeDefBuilder builder, ClassRef selfRef, String name, TypeRef argumentType,
      String argumentName, Statement slotStatement, String slot, boolean trackPinned) {
    addFluentMethod(builder, selfRef, name, argumentType, argumentName, slotStatement, slot, trackPinned,
        Collections.emptyList());
  }

  /**
   * Adds a fluent wither, optionally generic over {@code typeParameters}. A type-variable argument
   * keeps its {@code Object} slot but exposes a fresh generic parameter here, so the call site's
   * compiler infers the concrete type (e.g. a caller's {@code ArgumentCaptor<StreamEvent>} binds the
   * fresh parameter rather than failing against {@code ArgumentCaptor<Object>}).
   */
  private static void addFluentMethod(TypeDefBuilder builder, ClassRef selfRef, String name, TypeRef argumentType,
      String argumentName, Statement slotStatement, String slot, boolean trackPinned,
      List<TypeParamDef> typeParameters) {
    List<Statement> statements = new ArrayList<>();
    statements.add(slotStatement);
    if (trackPinned) {
      statements.add(This.ref(Constants.PINNED).call("add", slot));
    }
    statements.add(new Return(new This()));
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withParameters(typeParameters)
        .withReturnType(selfRef)
        .withName(name)
        .addNewArgument().withTypeRef(argumentType).withName(argumentName).endArgument()
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .endMethod();
  }

  /**
   * Adds the non-void stubbing terminals of a single-overload builder. The private {@code stub()}
   * returns a {@link io.sundr.mockito.DoStubbing} whose applier replays the answer sequence as
   * {@code doXxx(...).when(mock).method(matchers)}. That form never invokes the mocked method, so
   * registering a stubbing never fires a previously registered broad answer (avoiding the NPE the
   * {@code when(mock.method(matchers))} form triggers) while keeping the fluent chaining surface.
   */
  static void addStubbingTerminals(TypeDefBuilder builder, Method method, TypeRef returnType,
      List<ClassRef> exceptions) {
    Statement stubReturn = new Return(Constants.DO_STUBBING.call("of", doApplier(method)));
    addNonVoidTerminals(builder, returnType, Collections.singletonList(stubReturn), exceptions);
  }

  /**
   * Adds the public {@code thenXxx} terminals and the private {@code stub()} of a non-void
   * builder. Every terminal delegates to {@code this.stub().thenXxx(...)}; {@code stub()} returns
   * a {@link io.sundr.mockito.DoStubbing} whose {@code doXxx(...).when(mock).method(matchers)}
   * applier(s) never invoke the mocked method, so registering a stub never fires a prior broad
   * answer, and consecutive answers chain by replaying the whole sequence through the do-family.
   */
  private static void addNonVoidTerminals(TypeDefBuilder builder, TypeRef returnType, List<Statement> stubBody,
      List<ClassRef> exceptions) {
    TypeRef boxed = Types.box(returnType);
    ClassRef ongoingRef = new ClassRefBuilder(Constants.ONGOING_STUBBING).withArguments(boxed).build();
    ClassRef doStubbingRef = new ClassRefBuilder(Constants.DO_STUBBING).withArguments(boxed).build();
    ClassRef wildcardAnswerRef = new ClassRefBuilder(Constants.ANSWER).withArguments(new WildcardRef()).build();
    ClassRef typedAnswerRef = new ClassRefBuilder(Constants.ANSWER).withArguments(boxed).build();

    LocalVariable value = LocalVariable.newLocalVariable(returnType, "value");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenReturn")
        .addNewArgument().withTypeRef(returnType).withName("value").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenReturn", value)))
        .endBlock()
        .endMethod();

    LocalVariable throwable = LocalVariable.newLocalVariable(Constants.THROWABLE, "throwable");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenThrow")
        .addNewArgument().withTypeRef(Constants.THROWABLE).withName("throwable").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenThrow", throwable)))
        .endBlock()
        .endMethod();

    LocalVariable answer = LocalVariable.newLocalVariable(wildcardAnswerRef, "answer");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenAnswer")
        .addNewArgument().withTypeRef(wildcardAnswerRef).withName("answer").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenAnswer", answer)))
        .endBlock()
        .endMethod();

    LocalVariable typedAnswer = LocalVariable.newLocalVariable(typedAnswerRef, "answer");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenAnswerTyped")
        .addNewArgument().withTypeRef(typedAnswerRef).withName("answer").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenAnswer", typedAnswer)))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenCallRealMethod")
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenCallRealMethod")))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPrivate().endModifiers()
        .withReturnType(doStubbingRef)
        .withName("stub")
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(stubBody)
        .endBlock()
        .endMethod();
  }

  /**
   * Builds the {@code stubber -> stubber.when(this.mock).method(matchers)} applier passed to
   * {@link io.sundr.mockito.DoStubbing}; it registers the accumulated do-family stubber against
   * the mocked method without ever invoking it. The applier runs as a {@code Consumer<Stubber>}
   * whose {@code accept} cannot throw checked exceptions, yet the invocation names a method
   * declared to throw. Registering a stub never actually invokes the method, so any declared
   * checked exception is laundered into a {@code RuntimeException}, keeping the lambda legal.
   */
  private static Expression doApplier(Method method) {
    return doApplier(method, Collections.emptySet());
  }

  private static Expression doApplier(Method method, java.util.Set<String> widenedNames) {
    return new Lambda(Constants.STUBBER, launderedApplierBody(method, widenedNames));
  }

  private static Statement launderedApplierBody(Method method, java.util.Set<String> widenedNames) {
    LocalVariable stubber = LocalVariable.newLocalVariable(Constants.STUBBER_TYPE, Constants.STUBBER);
    Statement register = (Statement) stubber.call("when", This.ref(Constants.MOCK))
        .call(method.getName(), resolveSlots(method, widenedNames));
    if (method.getExceptions().isEmpty()) {
      return register;
    }
    LocalVariable caught = LocalVariable.newLocalVariable(Constants.THROWABLE, "t");
    Try tryCatch = new Try(
        new Block(register),
        Collections.singletonList(new Try.Catch(Property.newProperty(Constants.THROWABLE, "t"),
            new Block(new Throw(new Construct(Constants.RUNTIME_EXCEPTION, caught))))),
        Optional.empty());
    return new Block(tryCatch);
  }

  static void addVoidTerminals(TypeDefBuilder builder, BiFunction<String, Expression[], List<Statement>> bodies,
      List<ClassRef> exceptions) {
    ClassRef wildcardAnswerRef = new ClassRefBuilder(Constants.ANSWER).withArguments(new WildcardRef()).build();
    ClassRef typedAnswerRef = new ClassRefBuilder(Constants.ANSWER).withArguments(Constants.BOXED_VOID).build();

    LocalVariable throwable = LocalVariable.newLocalVariable(Constants.THROWABLE, "throwable");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("thenThrow")
        .addNewArgument().withTypeRef(Constants.THROWABLE).withName("throwable").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(bodies.apply("doThrow", new Expression[] { throwable }))
        .endBlock()
        .endMethod();

    LocalVariable answer = LocalVariable.newLocalVariable(wildcardAnswerRef, "answer");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("thenAnswer")
        .addNewArgument().withTypeRef(wildcardAnswerRef).withName("answer").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(bodies.apply("doAnswer", new Expression[] { answer }))
        .endBlock()
        .endMethod();

    LocalVariable typedAnswer = LocalVariable.newLocalVariable(typedAnswerRef, "answer");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("thenAnswerTyped")
        .addNewArgument().withTypeRef(typedAnswerRef).withName("answer").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(bodies.apply("doAnswer", new Expression[] { typedAnswer }))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("doNothing")
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(bodies.apply("doNothing", new Expression[0]))
        .endBlock()
        .endMethod();
  }

  /**
   * Adds the stubbing side of a shared builder: every overload matching the pinned arguments is
   * stubbed through the answer-first do-family, so registering a stub never invokes the mocked
   * method and never fires a prior broad answer. The private {@code stub()} collects one applier
   * per matched overload and hands them to {@link io.sundr.mockito.DoStubbing}, which fans the
   * shared answer chain out to all of them, leniently when more than one matches so strict stubbing
   * does not flag the variants a test never exercises.
   */
  static void addFanOutStubbing(TypeDefBuilder builder, List<Method> overloads, TypeRef returnType,
      List<ClassRef> exceptions) {
    ClassRef stubberConsumerRef = new ClassRefBuilder(Constants.CONSUMER).withArguments(Constants.STUBBER_TYPE).build();
    ClassRef listOfAppliers = new ClassRefBuilder(Constants.LIST).withArguments(stubberConsumerRef).build();

    LocalVariable selected = selectedVar();
    LocalVariable appliers = LocalVariable.newLocalVariable(listOfAppliers, "appliers");

    java.util.Set<String> widened = widenedArgumentNames(overloads);
    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    statements.add(new Declare(appliers,
        new Construct(Constants.ARRAY_LIST, Collections.singletonList((TypeRef) stubberConsumerRef),
            Collections.emptyList())));
    for (int i = 0; i < overloads.size(); i++) {
      statements.add(new If(selected.call("contains", i),
          new Block(appliers.call("add", doApplier(overloads.get(i), widened)))));
    }
    statements.add(new Return(Constants.DO_STUBBING.call("ofAll", appliers)));

    addNonVoidTerminals(builder, returnType, statements, exceptions);
  }

  /**
   * Adds the answer-first {@code done()} terminal of a single-overload do-family builder: it
   * replays the pre-built stubber as {@code stubber.when(mock).method(matchers)}, which never
   * invokes the real method and so is spy- and void-safe.
   */
  static void addDoTerminal(TypeDefBuilder builder, Method method, List<ClassRef> exceptions) {
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("done")
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(doStubbingApply(method))
        .endBlock()
        .endMethod();
  }

  /**
   * Adds the {@code done()} terminal of a shared do-family builder: it fans out the pre-built
   * stubber to every overload matching the pinned arguments.
   */
  static void addFanOutDoTerminal(TypeDefBuilder builder, List<Method> overloads, List<ClassRef> exceptions) {
    java.util.Set<String> widened = widenedArgumentNames(overloads);
    LocalVariable selected = selectedVar();
    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    for (int i = 0; i < overloads.size(); i++) {
      statements.add(new If(selected.call("contains", i),
          new Block(doStubbingApply(overloads.get(i), widened))));
    }
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("done")
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .endMethod();
  }

  /**
   * Builds {@code this.stubber.when(this.mock).method(slot.resolve(), ...)}; the answer was
   * supplied up front to the stubber, so this replays it against the mock's method.
   */
  private static Statement doStubbingApply(Method method) {
    return doStubbingApply(method, Collections.emptySet());
  }

  private static Statement doStubbingApply(Method method, java.util.Set<String> widenedNames) {
    return This.ref(Constants.STUBBER).call("when", This.ref(Constants.MOCK))
        .call(method.getName(), resolveSlots(method, widenedNames));
  }

  static void addVerifyTerminals(TypeDefBuilder builder, Function<LocalVariable, List<Statement>> bodies,
      List<Statement> neverBody, List<ClassRef> exceptions) {
    LocalVariable mode = LocalVariable.newLocalVariable(Constants.VERIFICATION_MODE, "mode");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("verified")
        .addNewArgument().withTypeRef(Constants.VERIFICATION_MODE).withName("mode").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(bodies.apply(mode))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("never")
        .withExceptions(exceptions)
        .withNewBlock()
        .withStatements(neverBody)
        .endBlock()
        .endMethod();

    addVerificationModeShortcut(builder, "called", Constants.MOCKITO.call("times", 1), exceptions);
    addVerificationModeShortcut(builder, "atLeastOnce", Constants.MOCKITO.call("atLeastOnce"), exceptions);
    addVerificationModeShortcut(builder, "only", Constants.MOCKITO.call("only"), exceptions);
    addInvocationCountShortcut(builder, "times", exceptions);
    addInvocationCountShortcut(builder, "atLeast", exceptions);
    addInvocationCountShortcut(builder, "atMost", exceptions);
  }

  private static void addVerificationModeShortcut(TypeDefBuilder builder, String name, Expression modeExpression,
      List<ClassRef> exceptions) {
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName(name)
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new This().call("verified", modeExpression))
        .endBlock()
        .endMethod();
  }

  private static void addInvocationCountShortcut(TypeDefBuilder builder, String name, List<ClassRef> exceptions) {
    LocalVariable invocations = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "invocations");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName(name)
        .addNewArgument().withTypeRef(Types.PRIMITIVE_INT_REF).withName("invocations").endArgument()
        .withExceptions(exceptions)
        .withNewBlock()
        .addToStatements(new This().call("verified", Constants.MOCKITO.call(name, invocations)))
        .endBlock()
        .endMethod();
  }

  static List<Statement> fanOutVoidBody(List<Method> overloads, String doName, Expression[] doArgs) {
    java.util.Set<String> widened = widenedArgumentNames(overloads);
    LocalVariable selected = selectedVar();
    LocalVariable lenient = lenientVar();

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    statements.add(new Declare(lenient, new GreaterThan(selected.call("size"), ValueRef.from(1))));
    for (int i = 0; i < overloads.size(); i++) {
      Method method = overloads.get(i);
      Statement doLenient = doStubbing(method, Constants.MOCKITO.call("lenient").call(doName, doArgs), widened);
      Statement doStrict = doStubbing(method, Constants.MOCKITO.call(doName, doArgs), widened);
      statements.add(new If(selected.call("contains", i),
          new Block(new If(lenient, new Block(doLenient), new Block(doStrict)))));
    }
    return statements;
  }

  /**
   * Builds the {@code never()} body of a shared verify builder: never fans out to every
   * matching overload, asserting none of them was called.
   */
  static List<Statement> fanOutNeverBody(List<Method> overloads) {
    java.util.Set<String> widened = widenedArgumentNames(overloads);
    LocalVariable selected = selectedVar();

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    for (int i = 0; i < overloads.size(); i++) {
      Method method = overloads.get(i);
      Statement verifyNever = Constants.MOCKITO.call("verify", This.ref(Constants.MOCK), Constants.MOCKITO.call("never"))
          .call(method.getName(), resolveSlots(method, widened));
      statements.add(new If(selected.call("contains", i), new Block(verifyNever)));
    }
    return statements;
  }

  /**
   * Builds the statements dispatching to the single overload matching the pinned
   * arguments; positive verifications must name one concrete signature.
   */
  static List<Statement> selectOneDispatch(List<Method> overloads, Function<Method, Statement> branch) {
    LocalVariable selected = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "selected");
    Statement chain = new Block(branch.apply(overloads.get(overloads.size() - 1)));
    for (int i = overloads.size() - 2; i >= 0; i--) {
      chain = new If(new Equals(selected, ValueRef.from(i)), new Block(branch.apply(overloads.get(i))), chain);
    }
    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectOneInvokedCall(overloads)));
    statements.add(chain);
    return statements;
  }

  /**
   * Builds {@code this.selector.selectOneInvoked(pinned, exact, this.mock, "method", matchers)}
   * where {@code matchers} is one {@code Predicate<Object[]>} per overload, index-aligned with
   * registration, testing whether a recorded invocation's arguments belong to that overload.
   */
  private static Expression selectOneInvokedCall(List<Method> overloads) {
    Expression[] matchers = overloads.stream()
        .map(DslMethods::overloadInvocationMatcher)
        .toArray(Expression[]::new);
    Expression matcherList = Constants.ARRAYS.call("asList", matchers);
    return This.ref(Constants.SELECTOR).call("selectOneInvoked", This.ref(Constants.PINNED), This.ref(Constants.EXACT),
        This.ref(Constants.MOCK), overloads.get(0).getName(), matcherList);
  }

  /**
   * Builds {@code args -> args.length == n && this.slot0.matches(args[0]) && ...} for one overload:
   * a recorded invocation belongs to the overload when its argument count matches and every pinned
   * slot accepts the recorded value in that position.
   */
  private static Expression overloadInvocationMatcher(Method overload) {
    LocalVariable args = LocalVariable.newLocalVariable(Constants.OBJECT_ARRAY, "args");
    List<Argument> arguments = overload.getArguments();
    Expression condition = new Equals(args.property("length"), ValueRef.from(arguments.size()));
    for (int i = 0; i < arguments.size(); i++) {
      Expression slotMatches = This.ref(slotName(arguments.get(i))).call("matches", args.index(i));
      condition = new LogicalAnd(condition, slotMatches);
    }
    return new Lambda("args", condition);
  }

  private static LocalVariable selectedVar() {
    ClassRef listOfInteger = new ClassRefBuilder(Constants.LIST).withArguments(Constants.INTEGER).build();
    return LocalVariable.newLocalVariable(listOfInteger, "selected");
  }

  private static LocalVariable lenientVar() {
    return LocalVariable.newLocalVariable(Types.PRIMITIVE_BOOLEAN_REF, "lenient");
  }

  private static Expression selectAllCall() {
    return This.ref(Constants.SELECTOR).call("selectAll", This.ref(Constants.PINNED), This.ref(Constants.EXACT));
  }

  /**
   * The shared builder's arguments, one per distinct parameter name across the overloads. When a
   * name appears with more than one type (for example {@code findAllByIdIn(List<String>)} and
   * {@code findAllByIdIn(Set<String>)} both naming the parameter {@code ids}), its slot is widened
   * to {@code Object} so a single slot serves every overload; the invoked overload is still selected
   * at runtime from the actual argument, and {@code withXxxAny()}/matchers are unaffected.
   */
  static Collection<Argument> unionArguments(List<Method> overloads) {
    Map<String, Argument> union = new LinkedHashMap<>();
    Map<String, String> renderedType = new LinkedHashMap<>();
    for (Method method : overloads) {
      for (Argument argument : method.getArguments()) {
        String name = argument.getName();
        String rendered = argument.getTypeRef().render();
        if (!union.containsKey(name)) {
          union.put(name, argument);
          renderedType.put(name, rendered);
        } else if (!renderedType.get(name).equals(rendered)
            && !Constants.OBJECT.equals(union.get(name).getTypeRef())) {
          union.put(name, new io.sundr.model.ArgumentBuilder(argument).withTypeRef(Constants.OBJECT).build());
        }
      }
    }
    return union.values();
  }

  /**
   * Collects the exceptions declared across every overload of the group, deduplicated by
   * fully qualified name in a stable order. Generated DSL methods emit real invocations of
   * the mocked method, so they must propagate its checked exceptions to compile.
   */
  static List<ClassRef> unionExceptions(List<Method> overloads) {
    Map<String, ClassRef> union = new LinkedHashMap<>();
    for (Method method : overloads) {
      for (ClassRef exception : method.getExceptions()) {
        union.putIfAbsent(exception.getFullyQualifiedName(), exception);
      }
    }
    return new ArrayList<>(union.values());
  }

  /**
   * Builds {@code doXxx(...).when(this.mock).method(slot.resolve(), ...)} for void methods.
   */
  static Statement doStubbing(Method method, Expression doCall) {
    return doStubbing(method, doCall, Collections.emptySet());
  }

  static Statement doStubbing(Method method, Expression doCall, java.util.Set<String> widenedNames) {
    return doCall.call("when", This.ref(Constants.MOCK))
        .call(method.getName(), resolveSlots(method, widenedNames));
  }

  static Statement verification(Method method, LocalVariable mode) {
    return verification(method, mode, Collections.emptySet());
  }

  static Statement verification(Method method, LocalVariable mode, java.util.Set<String> widenedNames) {
    return Constants.MOCKITO.call("verify", This.ref(Constants.MOCK), mode).call(method.getName(),
        resolveSlots(method, widenedNames));
  }

  private static Expression[] resolveSlots(Method method) {
    return resolveSlots(method, Collections.emptySet());
  }

  /**
   * The {@code slot.resolve()} expressions passed to the mocked method invocation, one per argument.
   * When an overload group widened a slot to {@code Object} (a parameter name had conflicting types
   * across overloads, listed in {@code widenedNames}), {@code resolve()} returns {@code Object} and
   * is cast to this overload's declared parameter type so the concrete invocation compiles. Slots
   * that were not widened keep their exact type and need no cast.
   */
  private static Expression[] resolveSlots(Method method, java.util.Set<String> widenedNames) {
    List<Expression> resolves = method.getArguments().stream()
        .map(argument -> {
          Expression resolved = This.ref(slotName(argument)).call("resolve");
          return widenedNames.contains(argument.getName())
              ? new Cast(argument.getTypeRef(), resolved)
              : resolved;
        })
        .collect(Collectors.toList());
    return resolves.toArray(new Expression[0]);
  }

  /**
   * The parameter names whose slot was widened to {@code Object} because their type differs across
   * the overload group (see {@link #unionArguments}). Empty for a single-method builder.
   */
  static java.util.Set<String> widenedArgumentNames(List<Method> overloads) {
    Map<String, String> renderedType = new LinkedHashMap<>();
    java.util.Set<String> widened = new java.util.LinkedHashSet<>();
    for (Method method : overloads) {
      for (Argument argument : method.getArguments()) {
        String name = argument.getName();
        String rendered = argument.getTypeRef().render();
        String existing = renderedType.putIfAbsent(name, rendered);
        if (existing != null && !existing.equals(rendered)) {
          widened.add(name);
        }
      }
    }
    return widened;
  }

  static String slotName(Argument argument) {
    return Constants.MOCK.equals(argument.getName()) ? "mockArg" : argument.getName();
  }
}
