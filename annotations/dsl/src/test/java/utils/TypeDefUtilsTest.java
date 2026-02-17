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

package utils;

import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TRANSPARENT_REF;
import static io.sundr.dsl.internal.utils.TypeDefUtils.executableToInterface;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isVoid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.sundr.adapter.apt.AptContext;
import io.sundr.dsl.internal.processor.DslContext;
import io.sundr.dsl.internal.processor.DslContextManager;
import io.sundr.dsl.internal.type.functions.Combine;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.utils.TypeDefUtils;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.repo.DefinitionRepository;
import net.serverpeon.testing.compile.CompilationExtension;

@ExtendWith(CompilationExtension.class)
public class TypeDefUtilsTest {

  private Elements elements;
  private Types types;
  private AptContext context;
  protected DslContext dslContext;

  private final TypeDef STRING = new TypeDefBuilder().withPackageName("java.lang").withName("String").build();
  private final TypeDef INTEGER = new TypeDefBuilder().withPackageName("java.lang").withName("Integer").build();
  private final TypeDef LONG = new TypeDefBuilder().withPackageName("java.lang").withName("Long").build();

  @BeforeAll
  public static void setupClass() {
    String version = System.getProperty("java.specification.version");
    assumeTrue(version.trim().startsWith("11"));
  }

  @BeforeEach
  public void setup(Elements elements, Types types) {
    this.elements = elements;
    this.types = types;
    context = AptContext.create(elements, types, DefinitionRepository.getRepository());
    dslContext = DslContextManager.create(elements, types);
  }

  @Test
  public void testExecutableToInterface() throws Exception {
    TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
    List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
    ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
    ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);

    TypeDef simpleClazz = TypeDefUtils.executableToInterface(dslContext, simple);
    assertEquals("MethodAInterface", simpleClazz.getName());
    assertEquals(getClass().getPackage().getName(), simpleClazz.getPackageName());
    assertEquals(1, simpleClazz.getParameters().size());
    assertEquals(0, simpleClazz.getExtendsList().size());
    assertFalse((Boolean) simpleClazz.getAttributes().get(IS_TERMINAL));

    TypeDef terminalClazz = TypeDefUtils.executableToInterface(dslContext, terminal);
    assertEquals("MethodBInterface", terminalClazz.getName());
    assertEquals(getClass().getPackage().getName(), terminalClazz.getPackageName());
    assertEquals(1, terminalClazz.getParameters().size());
    assertEquals(0, terminalClazz.getExtendsList().size());
    assertTrue((Boolean) terminalClazz.getAttributes().get(IS_TERMINAL));
  }

  @Test
  public void testCombineTwoWithTerminal() throws Exception {
    TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
    List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
    ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
    ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
    TypeDef simpleClazz = TypeDefUtils.executableToInterface(dslContext, simple);
    TypeDef teminalClazz = TypeDefUtils.executableToInterface(dslContext, terminal);
    TypeDef combined = Combine.TYPEDEFS.apply(Arrays.asList(simpleClazz, teminalClazz));
    assertNotNull(combined);

    String T = Generics.MAP.apply(TRANSPARENT_REF).getName();
    assertEquals("MethodABInterface", combined.getName());
    assertEquals(getClass().getPackage().getName(), combined.getPackageName());
    assertEquals(2, combined.getExtendsList().size());
    assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodAInterface<" + T + ">"))
        .findAny().isPresent());

    assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodBInterface<" + T + ">"))
        .findAny().isPresent());

  }

  @Test
  public void testCombineTwoNonTerminal() throws Exception {
    TypeElement typeElement = elements.getTypeElement(TwoNonTerminal.class.getCanonicalName());
    List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
    ExecutableElement left = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
    ExecutableElement right = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
    TypeDef leftClazz = executableToInterface(dslContext, left);
    TypeDef rightClazz = executableToInterface(dslContext, right);
    TypeDef combined = Combine.TYPEDEFS.apply(Arrays.asList(leftClazz, rightClazz));
    assertNotNull(combined);

    assertEquals("MethodABInterface", combined.getName());
    assertEquals(getClass().getPackage().getName(), combined.getPackageName());
    assertEquals(2, combined.getExtendsList().size());
    assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodAInterface<T>"))
        .findAny().isPresent());

    assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodBInterface<T>"))
        .findAny().isPresent());

    assertEquals(TRANSPARENT_REF, combined.getAttributes().get(ORIGINAL_RETURN_TYPE));
  }

  @Test
  public void testCombineTwoTerminal() throws Exception {
    TypeElement typeElement = elements.getTypeElement(TwoTerminal.class.getCanonicalName());
    List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
    ExecutableElement left = methods.get(0);
    ExecutableElement right = methods.get(1);
    TypeDef leftClazz = executableToInterface(dslContext, left);
    TypeDef rightClazz = executableToInterface(dslContext, right);
    TypeDef combined = Combine.TYPEDEFS.apply(Arrays.asList(leftClazz, rightClazz));
    assertNotNull(combined);

    assertEquals("MethodABInterface", combined.getName());
    assertEquals(getClass().getPackage().getName(), combined.getPackageName());
    assertEquals(2, combined.getExtendsList().size());

    assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodAInterface<T>"))
        .findAny().isPresent());

    assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodBInterface<T>"))
        .findAny().isPresent());

    assertEquals(TRANSPARENT_REF, combined.getAttributes().get(ORIGINAL_RETURN_TYPE));
  }

}
