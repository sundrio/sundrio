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

package io.sundr.mockito.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.sundr.model.Argument;
import io.sundr.model.Assign;
import io.sundr.model.Block;
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
import io.sundr.model.Method;
import io.sundr.model.Return;
import io.sundr.model.Statement;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.ValueRef;
import io.sundr.model.VoidRef;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

/**
 * Functions assembling the generated mock DSL classes from the sundr code model.
 */
public final class MockClasses {

  private static final String MOCK = "mock";
  private static final String PINNED = "pinned";
  private static final String SELECTOR = "selector";
  private static final String EXACT = "exact";
  private static final String STUB_ROUTER = "Stub";
  private static final String VERIFY_ROUTER = "Verify";

  private MockClasses() {
  }

  /**
   * Assembles the mock DSL class for the given target.
   *
   * @param target the mockable target.
   * @return the type definition of the generated class.
   */
  public static TypeDef mockClass(MockTarget target) {
    ClassRef targetRef = target.getTargetRef();
    ClassRef mockRef = target.getMockRef();
    ClassRef stubRouterRef = ClassRef.forName(STUB_ROUTER);
    LocalVariable mockVar = LocalVariable.newLocalVariable(targetRef, MOCK);

    TypeDefBuilder builder = new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(target.getMockName())
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers();

    builder.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetRef).withName(MOCK)
        .endField();

    builder.addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(targetRef).withName(MOCK).endArgument()
        .withNewBlock().addToStatements(new Assign(This.ref(MOCK), mockVar)).endBlock()
        .endConstructor();

    builder.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(targetRef)
        .withName(MOCK)
        .withNewBlock()
        .addToStatements(new Return(MockRefs.MOCKITO.call(MOCK, targetRef)))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(mockRef)
        .withName("stub")
        .addNewArgument().withTypeRef(targetRef).withName(MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Return(new Construct(mockRef, mockVar)))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(stubRouterRef)
        .withName("when")
        .withNewBlock()
        .addToStatements(new Return(new Construct(stubRouterRef, This.ref(MOCK))))
        .endBlock()
        .endMethod();

    if (target.isVerificationEnabled()) {
      ClassRef verifyRouterRef = ClassRef.forName(VERIFY_ROUTER);
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(verifyRouterRef)
          .withName("verify")
          .withNewBlock()
          .addToStatements(new Return(new Construct(verifyRouterRef, This.ref(MOCK))))
          .endBlock()
          .endMethod();
    }

    builder.addToInnerTypes(stubRouter(target));
    if (target.isVerificationEnabled()) {
      builder.addToInnerTypes(verifyRouter(target));
    }
    for (Map.Entry<String, List<Method>> entry : target.getMethodsByName().entrySet()) {
      builder.addToInnerTypes(methodStub(target, entry.getKey(), entry.getValue()));
      if (target.isVerificationEnabled()) {
        builder.addToInnerTypes(methodVerify(target, entry.getKey(), entry.getValue()));
      }
    }
    return builder.build();
  }

  /**
   * Assembles the aggregator class carrying one {@code stub} overload per mockable target,
   * so a single static import covers every mock of the suite.
   *
   * @param packageName the package of the marker carrying the {@code Mockables} annotation.
   * @param name the simple name of the aggregator class.
   * @param targets the mockable targets listed by the marker.
   * @return the type definition of the aggregator class.
   */
  public static TypeDef aggregatorClass(String packageName, String name, List<MockTarget> targets) {
    TypeDefBuilder builder = new TypeDefBuilder()
        .withPackageName(packageName)
        .withName(name)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers();

    builder.addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .withNewBlock().endBlock()
        .endConstructor();

    for (MockTarget target : targets) {
      ClassRef mockRef = target.getMockRef();
      ClassRef targetRef = target.getTargetRef();
      builder.addNewMethod()
          .withNewModifiers().withPublic().withStatic().endModifiers()
          .withReturnType(mockRef)
          .withName("stub")
          .addNewArgument().withTypeRef(targetRef).withName(MOCK).endArgument()
          .withNewBlock()
          .addToStatements(new Return(mockRef.call("stub", LocalVariable.newLocalVariable(targetRef, MOCK))))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private static TypeDef stubRouter(MockTarget target) {
    TypeDefBuilder builder = innerClass(target, STUB_ROUTER);

    for (String name : target.getMethodsByName().keySet()) {
      ClassRef stubRef = ClassRef.forName(MockTarget.stubClassName(name));
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(stubRef)
          .withName(name)
          .withNewBlock()
          .addToStatements(new Return(new Construct(stubRef, This.ref(MOCK))))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private static TypeDef verifyRouter(MockTarget target) {
    TypeDefBuilder builder = innerClass(target, VERIFY_ROUTER);

    for (String name : target.getMethodsByName().keySet()) {
      ClassRef verifyRef = ClassRef.forName(MockTarget.verifyClassName(name));
      builder.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(verifyRef)
          .withName(name)
          .withNewBlock()
          .addToStatements(new Return(new Construct(verifyRef, This.ref(MOCK))))
          .endBlock()
          .endMethod();
    }
    return builder.build();
  }

  private static TypeDef methodStub(MockTarget target, String name, List<Method> overloads) {
    String className = MockTarget.stubClassName(name);
    ClassRef selfRef = ClassRef.forName(className);
    TypeDefBuilder builder = innerClass(target, className);
    boolean isVoid = overloads.get(0).getReturnType() instanceof VoidRef;

    if (overloads.size() == 1) {
      Method method = overloads.get(0);
      addSlots(builder, method.getArguments());
      addWithers(builder, method.getArguments(), selfRef, false);
      if (isVoid) {
        addVoidTerminals(builder,
            (doName, doArgs) -> Collections.singletonList(doStubbing(method, MockRefs.MOCKITO.call(doName, doArgs))));
      } else {
        addStubbingTerminals(builder, method.getReturnType(),
            Collections.singletonList(new Return(MockRefs.MOCKITO.call("when", invocation(method)))));
      }
    } else {
      Collection<Argument> union = unionArguments(overloads);
      addSlots(builder, union);
      addOverloadSelection(builder, overloads, selfRef);
      addWithers(builder, union, selfRef, true);
      addAnyPins(builder, union, selfRef);
      if (isVoid) {
        addVoidTerminals(builder, (doName, doArgs) -> fanOutVoidBody(overloads, doName, doArgs));
      } else {
        addFanOutStubbing(builder, overloads, overloads.get(0).getReturnType());
      }
    }
    return builder.build();
  }

  private static TypeDef methodVerify(MockTarget target, String name, List<Method> overloads) {
    String className = MockTarget.verifyClassName(name);
    ClassRef selfRef = ClassRef.forName(className);
    TypeDefBuilder builder = innerClass(target, className);

    if (overloads.size() == 1) {
      Method method = overloads.get(0);
      addSlots(builder, method.getArguments());
      addWithers(builder, method.getArguments(), selfRef, false);
      addCapturing(builder, method.getArguments(), selfRef, false);
      addVerifyTerminals(builder, mode -> Collections.singletonList(verification(method, mode)),
          Collections.singletonList(new This().call("verified", MockRefs.MOCKITO.call("never"))));
    } else {
      Collection<Argument> union = unionArguments(overloads);
      addSlots(builder, union);
      addOverloadSelection(builder, overloads, selfRef);
      addWithers(builder, union, selfRef, true);
      addAnyPins(builder, union, selfRef);
      addCapturing(builder, union, selfRef, true);
      addVerifyTerminals(builder, mode -> selectOneDispatch(overloads, method -> verification(method, mode)),
          fanOutNeverBody(overloads));
    }
    return builder.build();
  }

  /**
   * Creates an inner class holding the mock reference: modifiers, the mock field and a
   * constructor that assigns the mock and initializes one slot per mocked method argument.
   */
  private static TypeDefBuilder innerClass(MockTarget target, String name) {
    ClassRef targetRef = target.getTargetRef();
    return new TypeDefBuilder()
        .withPackageName(target.getPackageName())
        .withName(name)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withStatic().withFinal().endModifiers()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetRef).withName(MOCK)
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPrivate().endModifiers()
        .addNewArgument().withTypeRef(targetRef).withName(MOCK).endArgument()
        .withNewBlock()
        .addToStatements(new Assign(This.ref(MOCK), LocalVariable.newLocalVariable(targetRef, MOCK)))
        .endBlock()
        .endConstructor();
  }

  private static void addSlots(TypeDefBuilder builder, Collection<Argument> arguments) {
    List<Statement> slotInits = new ArrayList<>();
    for (Argument argument : arguments) {
      ClassRef slotRef = MockRefs.slotFor(argument.getTypeRef());
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
  private static void addOverloadSelection(TypeDefBuilder builder, List<Method> overloads, ClassRef selfRef) {
    ClassRef pinnedRef = new ClassRefBuilder(MockRefs.SET).withArguments(MockRefs.STRING).build();
    builder.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(pinnedRef).withName(PINNED)
        .endField();
    builder.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(MockRefs.OVERLOAD_SELECTOR).withName(SELECTOR)
        .endField();
    builder.addNewField()
        .withNewModifiers().withPrivate().endModifiers()
        .withTypeRef(Types.PRIMITIVE_BOOLEAN_REF).withName(EXACT)
        .endField();

    Expression selectorInit = new Construct(MockRefs.OVERLOAD_SELECTOR);
    for (Method method : overloads) {
      Expression[] names = method.getArguments().stream()
          .map(argument -> (Expression) ValueRef.from(slotName(argument)))
          .toArray(Expression[]::new);
      selectorInit = selectorInit.call("overload", names);
    }

    List<Statement> inits = new ArrayList<>();
    inits.add(new Assign(This.ref(PINNED),
        new Construct(MockRefs.LINKED_HASH_SET, Collections.singletonList((TypeRef) MockRefs.STRING),
            Collections.emptyList())));
    inits.add(new Assign(This.ref(SELECTOR), selectorInit));
    builder.editFirstConstructor()
        .editBlock()
        .addAllToStatements(inits)
        .endBlock()
        .endConstructor();

    List<Statement> statements = new ArrayList<>();
    statements.add(new Assign(This.ref(EXACT), ValueRef.from(true)));
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

  private static void addWithers(TypeDefBuilder builder, Collection<Argument> arguments, ClassRef selfRef,
      boolean trackPinned) {
    for (Argument argument : arguments) {
      String slot = slotName(argument);
      String capitalized = Strings.capitalizeFirst(argument.getName());
      TypeRef argumentType = argument.getTypeRef();

      addFluentMethod(builder, selfRef, "with" + capitalized, argumentType, "value",
          This.ref(slot).call("eq", LocalVariable.newLocalVariable(argumentType, "value")), slot, trackPinned);

      ClassRef matcherRef = new ClassRefBuilder(MockRefs.ARGUMENT_MATCHER)
          .withArguments(Types.box(argumentType)).build();
      addFluentMethod(builder, selfRef, "with" + capitalized + "Matching", matcherRef, "matcher",
          This.ref(slot).call("matching", LocalVariable.newLocalVariable(matcherRef, "matcher")), slot, trackPinned);
    }
  }

  /**
   * Adds the {@code withXxxAny()} pins of shared builders: they mark the parameter as
   * present for overload selection while leaving its matcher as {@code any()}.
   */
  private static void addAnyPins(TypeDefBuilder builder, Collection<Argument> arguments, ClassRef selfRef) {
    for (Argument argument : arguments) {
      String slot = slotName(argument);
      List<Statement> statements = new ArrayList<>();
      statements.add(This.ref(PINNED).call("add", ValueRef.from(slot)));
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

  private static void addCapturing(TypeDefBuilder builder, Collection<Argument> arguments, ClassRef selfRef,
      boolean trackPinned) {
    for (Argument argument : arguments) {
      String slot = slotName(argument);
      ClassRef captorRef = new ClassRefBuilder(MockRefs.ARGUMENT_CAPTOR)
          .withArguments(Types.box(argument.getTypeRef())).build();
      addFluentMethod(builder, selfRef, "capturing" + Strings.capitalizeFirst(argument.getName()), captorRef, "captor",
          This.ref(slot).call("capturing", LocalVariable.newLocalVariable(captorRef, "captor")), slot, trackPinned);
    }
  }

  private static void addFluentMethod(TypeDefBuilder builder, ClassRef selfRef, String name, TypeRef argumentType,
      String argumentName, Statement slotStatement, String slot, boolean trackPinned) {
    List<Statement> statements = new ArrayList<>();
    statements.add(slotStatement);
    if (trackPinned) {
      statements.add(This.ref(PINNED).call("add", ValueRef.from(slot)));
    }
    statements.add(new Return(new This()));
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef)
        .withName(name)
        .addNewArgument().withTypeRef(argumentType).withName(argumentName).endArgument()
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .endMethod();
  }

  private static void addStubbingTerminals(TypeDefBuilder builder, TypeRef returnType, List<Statement> stubBody) {
    TypeRef boxed = Types.box(returnType);
    ClassRef ongoingRef = new ClassRefBuilder(MockRefs.ONGOING_STUBBING).withArguments(boxed).build();
    ClassRef answerRef = new ClassRefBuilder(MockRefs.ANSWER).withArguments(boxed).build();

    LocalVariable value = LocalVariable.newLocalVariable(returnType, "value");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenReturn")
        .addNewArgument().withTypeRef(returnType).withName("value").endArgument()
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenReturn", value)))
        .endBlock()
        .endMethod();

    LocalVariable throwable = LocalVariable.newLocalVariable(MockRefs.THROWABLE, "throwable");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenThrow")
        .addNewArgument().withTypeRef(MockRefs.THROWABLE).withName("throwable").endArgument()
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenThrow", throwable)))
        .endBlock()
        .endMethod();

    LocalVariable answer = LocalVariable.newLocalVariable(answerRef, "answer");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenAnswer")
        .addNewArgument().withTypeRef(answerRef).withName("answer").endArgument()
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenAnswer", answer)))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName("thenCallRealMethod")
        .withNewBlock()
        .addToStatements(new Return(new This().call("stub").call("thenCallRealMethod")))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPrivate().endModifiers()
        .withReturnType(ongoingRef)
        .withName("stub")
        .withNewBlock()
        .withStatements(stubBody)
        .endBlock()
        .endMethod();
  }

  private static void addVoidTerminals(TypeDefBuilder builder,
      BiFunction<String, Expression[], List<Statement>> bodies) {
    ClassRef answerRef = new ClassRefBuilder(MockRefs.ANSWER).withArguments(MockRefs.BOXED_VOID).build();

    LocalVariable throwable = LocalVariable.newLocalVariable(MockRefs.THROWABLE, "throwable");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("thenThrow")
        .addNewArgument().withTypeRef(MockRefs.THROWABLE).withName("throwable").endArgument()
        .withNewBlock()
        .withStatements(bodies.apply("doThrow", new Expression[] { throwable }))
        .endBlock()
        .endMethod();

    LocalVariable answer = LocalVariable.newLocalVariable(answerRef, "answer");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("thenAnswer")
        .addNewArgument().withTypeRef(answerRef).withName("answer").endArgument()
        .withNewBlock()
        .withStatements(bodies.apply("doAnswer", new Expression[] { answer }))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("doNothing")
        .withNewBlock()
        .withStatements(bodies.apply("doNothing", new Expression[0]))
        .endBlock()
        .endMethod();
  }

  private static void addVerifyTerminals(TypeDefBuilder builder, Function<LocalVariable, List<Statement>> bodies,
      List<Statement> neverBody) {
    LocalVariable mode = LocalVariable.newLocalVariable(MockRefs.VERIFICATION_MODE, "mode");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("verified")
        .addNewArgument().withTypeRef(MockRefs.VERIFICATION_MODE).withName("mode").endArgument()
        .withNewBlock()
        .withStatements(bodies.apply(mode))
        .endBlock()
        .endMethod();

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName("never")
        .withNewBlock()
        .withStatements(neverBody)
        .endBlock()
        .endMethod();

    addVerificationModeShortcut(builder, "called", MockRefs.MOCKITO.call("times", ValueRef.from(1)));
    addVerificationModeShortcut(builder, "atLeastOnce", MockRefs.MOCKITO.call("atLeastOnce"));
    addVerificationModeShortcut(builder, "only", MockRefs.MOCKITO.call("only"));
    addInvocationCountShortcut(builder, "times");
    addInvocationCountShortcut(builder, "atLeast");
    addInvocationCountShortcut(builder, "atMost");
  }

  private static void addVerificationModeShortcut(TypeDefBuilder builder, String name, Expression modeExpression) {
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName(name)
        .withNewBlock()
        .addToStatements(new This().call("verified", modeExpression))
        .endBlock()
        .endMethod();
  }

  private static void addInvocationCountShortcut(TypeDefBuilder builder, String name) {
    LocalVariable invocations = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "invocations");
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(Types.VOID)
        .withName(name)
        .addNewArgument().withTypeRef(Types.PRIMITIVE_INT_REF).withName("invocations").endArgument()
        .withNewBlock()
        .addToStatements(new This().call("verified", MockRefs.MOCKITO.call(name, invocations)))
        .endBlock()
        .endMethod();
  }

  /**
   * Adds the stubbing side of a shared builder: every overload matching the pinned
   * arguments is stubbed, multi-matches leniently so strict stubbing does not flag the
   * variants a test never exercises. Mockito forbids starting a stubbing while another is
   * unfinished, so each terminal completes every overload's stubbing inline, right as it
   * is started, and chains span all matches via FanOutStubbing.
   */
  private static void addFanOutStubbing(TypeDefBuilder builder, List<Method> overloads, TypeRef returnType) {
    TypeRef boxed = Types.box(returnType);
    ClassRef ongoingRef = new ClassRefBuilder(MockRefs.ONGOING_STUBBING).withArguments(boxed).build();
    ClassRef answerRef = new ClassRefBuilder(MockRefs.ANSWER).withArguments(boxed).build();

    LocalVariable lenient = lenientVar();
    for (int i = 0; i < overloads.size(); i++) {
      Method method = overloads.get(i);
      builder.addNewMethod()
          .withNewModifiers().withPrivate().endModifiers()
          .withReturnType(ongoingRef)
          .withName("stub" + i)
          .addNewArgument().withTypeRef(Types.PRIMITIVE_BOOLEAN_REF).withName("lenient").endArgument()
          .withNewBlock()
          .addToStatements(new If(lenient,
              new Block(new Return(MockRefs.MOCKITO.call("lenient").call("when", invocation(method))))))
          .addToStatements(new Return(MockRefs.MOCKITO.call("when", invocation(method))))
          .endBlock()
          .endMethod();
    }

    LocalVariable value = LocalVariable.newLocalVariable(returnType, "value");
    addFanOutTerminal(builder, overloads, ongoingRef, "thenReturn", returnType, "value", value);
    LocalVariable throwable = LocalVariable.newLocalVariable(MockRefs.THROWABLE, "throwable");
    addFanOutTerminal(builder, overloads, ongoingRef, "thenThrow", MockRefs.THROWABLE, "throwable", throwable);
    LocalVariable answer = LocalVariable.newLocalVariable(answerRef, "answer");
    addFanOutTerminal(builder, overloads, ongoingRef, "thenAnswer", answerRef, "answer", answer);
    addFanOutTerminal(builder, overloads, ongoingRef, "thenCallRealMethod", null, null, null);
  }

  private static void addFanOutTerminal(TypeDefBuilder builder, List<Method> overloads, ClassRef ongoingRef,
      String name, TypeRef argumentType, String argumentName, Expression argumentVar) {
    ClassRef supplierRef = new ClassRefBuilder(MockRefs.SUPPLIER).withArguments(ongoingRef).build();
    ClassRef listOfStarters = new ClassRefBuilder(MockRefs.LIST).withArguments(supplierRef).build();
    LocalVariable selected = selectedVar();
    LocalVariable lenient = lenientVar();
    LocalVariable starters = LocalVariable.newLocalVariable(listOfStarters, "starters");
    LocalVariable stubbing = LocalVariable.newLocalVariable("stubbing");

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    statements.add(new Declare(lenient, new GreaterThan(selected.call("size"), ValueRef.from(1))));
    statements.add(new Declare(starters,
        new Construct(MockRefs.ARRAY_LIST, Collections.singletonList((TypeRef) supplierRef), Collections.emptyList())));
    for (int i = 0; i < overloads.size(); i++) {
      Expression starter = new Lambda(Collections.emptyList(), (Expression) new This().call("stub" + i, lenient));
      statements.add(new If(selected.call("contains", ValueRef.from(i)),
          new Block(starters.call("add", starter))));
    }
    Expression step = new Lambda("stubbing",
        (Expression) (argumentVar == null ? stubbing.call(name) : stubbing.call(name, argumentVar)));
    statements.add(new Return(MockRefs.FAN_OUT_STUBBING.call("of", starters, step)));

    List<Argument> arguments = argumentType == null
        ? Collections.emptyList()
        : Collections.singletonList(Argument.newArgument(argumentType, argumentName));
    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(ongoingRef)
        .withName(name)
        .withArguments(arguments)
        .withNewBlock()
        .withStatements(statements)
        .endBlock()
        .endMethod();
  }

  private static List<Statement> fanOutVoidBody(List<Method> overloads, String doName, Expression[] doArgs) {
    LocalVariable selected = selectedVar();
    LocalVariable lenient = lenientVar();

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    statements.add(new Declare(lenient, new GreaterThan(selected.call("size"), ValueRef.from(1))));
    for (int i = 0; i < overloads.size(); i++) {
      Method method = overloads.get(i);
      Statement doLenient = doStubbing(method, MockRefs.MOCKITO.call("lenient").call(doName, doArgs));
      Statement doStrict = doStubbing(method, MockRefs.MOCKITO.call(doName, doArgs));
      statements.add(new If(selected.call("contains", ValueRef.from(i)),
          new Block(new If(lenient, new Block(doLenient), new Block(doStrict)))));
    }
    return statements;
  }

  /**
   * Builds the {@code never()} body of a shared verify builder: never fans out to every
   * matching overload, asserting none of them was called.
   */
  private static List<Statement> fanOutNeverBody(List<Method> overloads) {
    LocalVariable selected = selectedVar();

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected, selectAllCall()));
    for (int i = 0; i < overloads.size(); i++) {
      Method method = overloads.get(i);
      Statement verifyNever = MockRefs.MOCKITO.call("verify", This.ref(MOCK), MockRefs.MOCKITO.call("never"))
          .call(method.getName(), resolveSlots(method));
      statements.add(new If(selected.call("contains", ValueRef.from(i)), new Block(verifyNever)));
    }
    return statements;
  }

  /**
   * Builds the statements dispatching to the single overload matching the pinned
   * arguments; positive verifications must name one concrete signature.
   */
  private static List<Statement> selectOneDispatch(List<Method> overloads, Function<Method, Statement> branch) {
    LocalVariable selected = LocalVariable.newLocalVariable(Types.PRIMITIVE_INT_REF, "selected");
    Statement chain = new Block(branch.apply(overloads.get(overloads.size() - 1)));
    for (int i = overloads.size() - 2; i >= 0; i--) {
      chain = new If(new Equals(selected, ValueRef.from(i)), new Block(branch.apply(overloads.get(i))), chain);
    }
    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(selected,
        This.ref(SELECTOR).call("selectOne", This.ref(PINNED), This.ref(EXACT))));
    statements.add(chain);
    return statements;
  }

  private static LocalVariable selectedVar() {
    ClassRef listOfInteger = new ClassRefBuilder(MockRefs.LIST).withArguments(MockRefs.INTEGER).build();
    return LocalVariable.newLocalVariable(listOfInteger, "selected");
  }

  private static LocalVariable lenientVar() {
    return LocalVariable.newLocalVariable(Types.PRIMITIVE_BOOLEAN_REF, "lenient");
  }

  private static Expression selectAllCall() {
    return This.ref(SELECTOR).call("selectAll", This.ref(PINNED), This.ref(EXACT));
  }

  private static Collection<Argument> unionArguments(List<Method> overloads) {
    Map<String, Argument> union = new LinkedHashMap<>();
    for (Method method : overloads) {
      for (Argument argument : method.getArguments()) {
        union.putIfAbsent(argument.getName(), argument);
      }
    }
    return union.values();
  }

  /**
   * Builds {@code doXxx(...).when(this.mock).method(slot.resolve(), ...)} for void methods.
   */
  private static Statement doStubbing(Method method, Expression doCall) {
    return doCall.call("when", This.ref(MOCK)).call(method.getName(), resolveSlots(method));
  }

  private static Statement verification(Method method, LocalVariable mode) {
    return MockRefs.MOCKITO.call("verify", This.ref(MOCK), mode).call(method.getName(), resolveSlots(method));
  }

  /**
   * Builds {@code this.mock.method(slot.resolve(), ...)}; slots resolve left to right so
   * Mockito registers one matcher per argument in parameter order.
   */
  private static Expression invocation(Method method) {
    return This.ref(MOCK).call(method.getName(), resolveSlots(method));
  }

  private static Expression[] resolveSlots(Method method) {
    List<Expression> resolves = method.getArguments().stream()
        .map(argument -> (Expression) This.ref(slotName(argument)).call("resolve"))
        .collect(Collectors.toList());
    return resolves.toArray(new Expression[0]);
  }

  static String slotName(Argument argument) {
    return MOCK.equals(argument.getName()) ? "mockArg" : argument.getName();
  }
}
