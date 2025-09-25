/*
 * Copyright 2016 The original authors.
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

package io.sundr.model.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import io.sundr.model.Assign;
import io.sundr.model.ClassRef;
import io.sundr.model.For;
import io.sundr.model.Foreach;
import io.sundr.model.If;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.MethodCall;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.Return;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;

public class MethodReferenceTest {

  DefinitionRepository repository = DefinitionRepository.createRepository();

  @Test
  public void shouldReturnEmptyOnEmptyMethods() {
    Method empty = new MethodBuilder()
        .withName("empty")
        .build();

    Set<MethodReference> references = MethodReference.getMethodReferences(empty, repository);
    assertTrue("Should return empty set for method without calls", references.isEmpty());
  }

  @Test
  public void shouldReturnEmptyOnMethodsWithoutCalls() {
    Method getter = new MethodBuilder()
        .withName("empty")
        .withNewBlock()
        .withStatements(Return.variable("value"))
        .endBlock()
        .build();

    Set<MethodReference> references = MethodReference.getMethodReferences(getter, repository);
    assertTrue("Should return empty set for method without calls", references.isEmpty());
  }

  @Test
  public void shouldReturnLocalMethodCall() {
    Method method = new MethodBuilder()
        .withName("method")
        .withNewBlock()
        .withStatements(Return.variable("value"))
        .endBlock()
        .build();

    Method caller = new MethodBuilder()
        .withName("caller")
        .withNewBlock()
        .withStatements(Return.call("method"))
        .endBlock()
        .build();

    TypeDef def = new TypeDefBuilder()
        .withName("MyType")
        .withMethods(method, caller)
        .build();

    repository.register(def);

    Set<MethodReference> references = MethodReference.getMethodReferences(caller, repository);
    assertEquals("Should return local method call", 1, references.size());

    // Verify that the found method reference is correct
    MethodReference foundReference = references.iterator().next();
    assertEquals("Should find the local method", "method", foundReference.getMethod().getName());
    assertEquals("Should find the correct owning type", "MyType", foundReference.getOwningType().getName());
  }

  @Test
  public void shouldReturnMethodCallInIfCondition() {
    Method isValid = new MethodBuilder()
        .withName("isValid")
        .withReturnType(ClassRef.forClass(Boolean.class))
        .withNewBlock()
        .withStatements(Return.True())
        .endBlock()
        .build();

    Method conditionalMethod = new MethodBuilder()
        .withName("conditionalMethod")
        .withNewBlock()
        .withStatements(new If(new MethodCall("isValid", (io.sundr.model.Expression) null), Return.value("valid")))
        .endBlock()
        .build();

    TypeDef def = new TypeDefBuilder()
        .withName("MyType")
        .withMethods(isValid, conditionalMethod)
        .build();

    repository.register(def);

    Set<MethodReference> references = MethodReference.getMethodReferences(conditionalMethod, repository);
    assertEquals("Should return method call from if condition", 1, references.size());

    // Verify that the found method reference is correct
    MethodReference foundReference = references.iterator().next();
    assertEquals("Should find the condition method", "isValid", foundReference.getMethod().getName());
    assertEquals("Should find the correct owning type", "MyType", foundReference.getOwningType().getName());
  }

  @Test
  public void shouldReturnMethodCallInAssignment() {
    Method getValue = new MethodBuilder()
        .withName("getValue")
        .withReturnType(ClassRef.forClass(String.class))
        .withNewBlock()
        .withStatements(Return.value("test"))
        .endBlock()
        .build();

    Property result = new PropertyBuilder()
        .withName("result")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    Method assignmentMethod = new MethodBuilder()
        .withName("assignmentMethod")
        .withNewBlock()
        .withStatements(new Assign(result.toReference(), new MethodCall("getValue", (io.sundr.model.Expression) null)))
        .endBlock()
        .build();

    TypeDef def = new TypeDefBuilder()
        .withName("MyType")
        .withMethods(getValue, assignmentMethod)
        .build();

    repository.register(def);

    Set<MethodReference> references = MethodReference.getMethodReferences(assignmentMethod, repository);
    assertEquals("Should return method call from assignment", 1, references.size());

    // Verify that the found method reference is correct
    MethodReference foundReference = references.iterator().next();
    assertEquals("Should find the getValue method", "getValue", foundReference.getMethod().getName());
    assertEquals("Should find the correct owning type", "MyType", foundReference.getOwningType().getName());
  }

  @Test
  public void shouldReturnSuperclassMethodCall() {
    // Create a superclass with a method
    Method parentMethod = new MethodBuilder()
        .withName("parentMethod")
        .withReturnType(ClassRef.forClass(String.class))
        .withNewBlock()
        .withStatements(Return.value("parent"))
        .endBlock()
        .build();

    TypeDef parentClass = new TypeDefBuilder()
        .withName("ParentClass")
        .withMethods(parentMethod)
        .build();

    // Create a subclass that calls the parent method
    Method childMethod = new MethodBuilder()
        .withName("childMethod")
        .withNewBlock()
        .withStatements(Return.call("parentMethod"))
        .endBlock()
        .build();

    TypeDef childClass = new TypeDefBuilder()
        .withName("ChildClass")
        .withExtendsList(ClassRef.forName("ParentClass"))
        .withMethods(childMethod)
        .build();

    // Register both classes in the repository
    repository.register(parentClass);
    repository.register(childClass);

    Set<MethodReference> references = MethodReference.getMethodReferences(childMethod, repository);
    assertEquals("Should return superclass method call", 1, references.size());

    // Verify that the found method reference is correct
    MethodReference foundReference = references.iterator().next();
    assertEquals("Should find the parent method", "parentMethod", foundReference.getMethod().getName());
    assertEquals("Should find the correct owning type", "ParentClass", foundReference.getOwningType().getName());
  }

  @Test
  public void shouldReturnTransitiveDependencies() {
    // Create method C (leaf method - no dependencies)
    Method methodC = new MethodBuilder()
        .withName("methodC")
        .withNewBlock()
        .withStatements(Return.value("C"))
        .endBlock()
        .build();

    // Create method B that calls method C
    Method methodB = new MethodBuilder()
        .withName("methodB")
        .withNewBlock()
        .withStatements(Return.call("methodC"))
        .endBlock()
        .build();

    // Create method A that calls method B
    Method methodA = new MethodBuilder()
        .withName("methodA")
        .withNewBlock()
        .withStatements(Return.call("methodB"))
        .endBlock()
        .build();

    TypeDef def = new TypeDefBuilder()
        .withName("TransitiveTestClass")
        .withMethods(methodA, methodB, methodC)
        .build();

    repository.register(def);

    Set<MethodReference> references = MethodReference.getMethodReferences(methodA, repository);
    assertEquals("Should return both direct and transitive dependencies", 2, references.size());

    // Verify that both methodB and methodC are found
    Set<String> foundMethodNames = references.stream()
        .map(ref -> ref.getMethod().getName())
        .collect(java.util.stream.Collectors.toSet());

    assertTrue("Should find methodB", foundMethodNames.contains("methodB"));
    assertTrue("Should find methodC", foundMethodNames.contains("methodC"));

    // Verify that all method references have the correct owning type
    for (MethodReference ref : references) {
      assertEquals("Should find the correct owning type", "TransitiveTestClass", ref.getOwningType().getName());
    }
  }

  @Test
  public void shouldReturnMethodCallInForLoop() {
    // Create helper methods used in the for loop
    Method getStart = new MethodBuilder()
        .withName("getStart")
        .withReturnType(ClassRef.forClass(Integer.class))
        .withNewBlock()
        .withStatements(Return.value(0))
        .endBlock()
        .build();

    Method getEnd = new MethodBuilder()
        .withName("getEnd")
        .withReturnType(ClassRef.forClass(Integer.class))
        .withNewBlock()
        .withStatements(Return.value(10))
        .endBlock()
        .build();

    Method increment = new MethodBuilder()
        .withName("increment")
        .withReturnType(ClassRef.forClass(Integer.class))
        .withNewBlock()
        .withStatements(Return.value(1))
        .endBlock()
        .build();

    Method processItem = new MethodBuilder()
        .withName("processItem")
        .withNewBlock()
        .withStatements(Return.value("processed"))
        .endBlock()
        .build();

    // Create a method that uses a for loop with method calls in init, condition, update, and body
    Method forLoopMethod = new MethodBuilder()
        .withName("forLoopMethod")
        .withNewBlock()
        .withStatements(new For(
            java.util.Arrays.asList(new MethodCall("getStart", (io.sundr.model.Expression) null)), // init
            new MethodCall("getEnd", (io.sundr.model.Expression) null), // condition
            java.util.Arrays.asList(new MethodCall("increment", (io.sundr.model.Expression) null)), // update
            new MethodCall("processItem", (io.sundr.model.Expression) null) // body
        ))
        .endBlock()
        .build();

    TypeDef def = new TypeDefBuilder()
        .withName("ForTestClass")
        .withMethods(getStart, getEnd, increment, processItem, forLoopMethod)
        .build();

    repository.register(def);

    Set<MethodReference> references = MethodReference.getMethodReferences(forLoopMethod, repository);
    assertEquals("Should return all method calls from for loop", 4, references.size());

    // Verify that all expected methods are found
    Set<String> foundMethodNames = references.stream()
        .map(ref -> ref.getMethod().getName())
        .collect(java.util.stream.Collectors.toSet());

    assertTrue("Should find getStart method", foundMethodNames.contains("getStart"));
    assertTrue("Should find getEnd method", foundMethodNames.contains("getEnd"));
    assertTrue("Should find increment method", foundMethodNames.contains("increment"));
    assertTrue("Should find processItem method", foundMethodNames.contains("processItem"));

    // Verify that all method references have the correct owning type
    for (MethodReference ref : references) {
      assertEquals("Should find the correct owning type", "ForTestClass", ref.getOwningType().getName());
    }
  }

  @Test
  public void shouldReturnMethodCallInForeachLoop() {
    // Create helper methods used in the foreach loop
    Method getItems = new MethodBuilder()
        .withName("getItems")
        .withReturnType(ClassRef.forName("java.util.List"))
        .withNewBlock()
        .withStatements(Return.value("items"))
        .endBlock()
        .build();

    Method processItem = new MethodBuilder()
        .withName("processItem")
        .withNewBlock()
        .withStatements(Return.value("processed"))
        .endBlock()
        .build();

    // Create a variable declaration property for the foreach loop variable
    Property itemProperty = new PropertyBuilder()
        .withName("item")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    // Create a method that uses a foreach loop with method calls in iterable expression and body
    Method foreachLoopMethod = new MethodBuilder()
        .withName("foreachLoopMethod")
        .withNewBlock()
        .withStatements(new Foreach(
            itemProperty, // loop variable declaration
            new MethodCall("getItems", (io.sundr.model.Expression) null), // iterable expression
            new MethodCall("processItem", (io.sundr.model.Expression) null) // body
        ))
        .endBlock()
        .build();

    TypeDef def = new TypeDefBuilder()
        .withName("ForeachTestClass")
        .withMethods(getItems, processItem, foreachLoopMethod)
        .build();

    repository.register(def);

    Set<MethodReference> references = MethodReference.getMethodReferences(foreachLoopMethod, repository);
    assertEquals("Should return all method calls from foreach loop", 2, references.size());

    // Verify that all expected methods are found
    Set<String> foundMethodNames = references.stream()
        .map(ref -> ref.getMethod().getName())
        .collect(java.util.stream.Collectors.toSet());

    assertTrue("Should find getItems method", foundMethodNames.contains("getItems"));
    assertTrue("Should find processItem method", foundMethodNames.contains("processItem"));

    // Verify that all method references have the correct owning type
    for (MethodReference ref : references) {
      assertEquals("Should find the correct owning type", "ForeachTestClass", ref.getOwningType().getName());
    }
  }

  @Test
  public void shouldReturnTransitiveDependenciesAcrossMultipleTypes() {
    // Create TypeA with methodA that calls TypeB.methodB
    Method methodA = new MethodBuilder()
        .withName("methodA")
        .withNewBlock()
        .withStatements(Return.call("methodB"))
        .endBlock()
        .build();

    TypeDef typeA = new TypeDefBuilder()
        .withName("TypeA")
        .withMethods(methodA)
        .build();

    // Create TypeB with methodB that calls TypeC.methodC
    Method methodB = new MethodBuilder()
        .withName("methodB")
        .withNewBlock()
        .withStatements(Return.call("methodC"))
        .endBlock()
        .build();

    TypeDef typeB = new TypeDefBuilder()
        .withName("TypeB")
        .withMethods(methodB)
        .build();

    // Create TypeC with methodC (leaf method)
    Method methodC = new MethodBuilder()
        .withName("methodC")
        .withNewBlock()
        .withStatements(Return.value("leaf"))
        .endBlock()
        .build();

    TypeDef typeC = new TypeDefBuilder()
        .withName("TypeC")
        .withMethods(methodC)
        .build();

    // Register all types
    repository.register(typeA);
    repository.register(typeB);
    repository.register(typeC);

    Set<MethodReference> references = MethodReference.getMethodReferences(methodA, repository);
    assertEquals("Should return transitive dependencies across multiple types", 2, references.size());

    // Verify that both methodB and methodC are found with correct owning types
    boolean foundMethodB = false;
    boolean foundMethodC = false;

    for (MethodReference ref : references) {
      if ("methodB".equals(ref.getMethod().getName())) {
        foundMethodB = true;
        assertEquals("methodB should be owned by TypeB", "TypeB", ref.getOwningType().getName());
      } else if ("methodC".equals(ref.getMethod().getName())) {
        foundMethodC = true;
        assertEquals("methodC should be owned by TypeC", "TypeC", ref.getOwningType().getName());
      }
    }

    assertTrue("Should find methodB from TypeB", foundMethodB);
    assertTrue("Should find methodC from TypeC", foundMethodC);
  }
}
