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
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;

import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.dsl.internal.processor.DslContext;
import io.sundr.dsl.internal.type.functions.Combine;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.utils.TypeDefUtils;

public class TypeDefUtilsTest {

  private final Context context = new Context();
  private final Elements elements = JavacElements.instance(context);
  private final Types types = JavacTypes.instance(context);
  private final DslContext dslContext = new DslContext(elements, types);
  private final TypeDef STRING = new TypeDefBuilder().withPackageName("java.lang").withName("String").build();
  private final TypeDef INTEGER = new TypeDefBuilder().withPackageName("java.lang").withName("Integer").build();
  private final TypeDef LONG = new TypeDefBuilder().withPackageName("java.lang").withName("Long").build();

  @BeforeClass
  public static void setupClass() {
    String version = System.getProperty("java.specification.version");
    Assume.assumeTrue(version.trim().startsWith("1.8"));
  }

  @Test
  public void testExecutableToInterface() throws Exception {
    TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
    List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
    ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
    ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);

    TypeDef simpleClazz = TypeDefUtils.executableToInterface(dslContext, simple);
    Assert.assertThat(simpleClazz.getName(), CoreMatchers.equalTo("MethodAInterface"));
    Assert.assertThat(simpleClazz.getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
    Assert.assertThat(simpleClazz.getParameters().size(), CoreMatchers.is(1));
    Assert.assertThat(simpleClazz.getExtendsList().size(), CoreMatchers.is(0));
    //Assert.assertThat((ClassRef) simpleClazz.getAttributes().get(ORIGINAL_RETURN_TYPE), TypeNamed.typeNamed("T"));
    Assert.assertFalse((Boolean) simpleClazz.getAttributes().get(IS_TERMINAL));

    TypeDef terminalClazz = TypeDefUtils.executableToInterface(dslContext, terminal);
    Assert.assertThat(terminalClazz.getName(), CoreMatchers.equalTo("MethodBInterface"));
    Assert.assertThat(terminalClazz.getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
    Assert.assertThat(terminalClazz.getParameters().size(), CoreMatchers.is(1));
    Assert.assertThat(terminalClazz.getExtendsList().size(), CoreMatchers.is(0));
    //Assert.assertThat((ClassRef) terminalClazz.getAttributes().get(ORIGINAL_RETURN_TYPE), TypeNamed.typeNamed("T"));
    Assert.assertTrue((Boolean) terminalClazz.getAttributes().get(IS_TERMINAL));
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
    Assert.assertNotNull(combined);

    String T = Generics.MAP.apply(TRANSPARENT_REF).getName();
    Assert.assertThat(combined.getName(), CoreMatchers.equalTo("MethodABInterface"));
    Assert.assertThat(combined.getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
    //Assert.assertThat(combined.getParameters().size(), CoreMatchers.is(1));
    //Assert.assertThat(combined.getParameters().get(0).getName(), CoreMatchers.equalTo(T));
    Assert.assertThat(combined.getExtendsList().size(), CoreMatchers.is(2));
    Assert.assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodAInterface<" + T + ">"))
        .findAny().isPresent());

    Assert.assertTrue(combined.getExtendsList().stream()
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
    Assert.assertNotNull(combined);

    Assert.assertThat(combined.getName(), CoreMatchers.equalTo("MethodABInterface"));
    Assert.assertThat(combined.getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
    //Assert.assertThat(combined.getParameters().size(), CoreMatchers.is(1));
    //Assert.assertThat(combined.getParameters().get(0).getName(), CoreMatchers.equalTo("T"));
    Assert.assertThat(combined.getExtendsList().size(), CoreMatchers.is(2));
    Assert.assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodAInterface<T>"))
        .findAny().isPresent());

    Assert.assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodBInterface<T>"))
        .findAny().isPresent());

    assertEquals(combined.getAttributes().get(ORIGINAL_RETURN_TYPE), TRANSPARENT_REF);
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
    Assert.assertNotNull(combined);

    Assert.assertThat(combined.getName(), CoreMatchers.equalTo("MethodABInterface"));
    Assert.assertThat(combined.getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
    //Assert.assertThat(combined.getParameters().size(), CoreMatchers.is(1));
    Assert.assertThat(combined.getExtendsList().size(), CoreMatchers.is(2));

    Assert.assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodAInterface<T>"))
        .findAny().isPresent());

    Assert.assertTrue(combined.getExtendsList().stream()
        .map(Object::toString)
        .filter(s -> s.equals("utils.MethodBInterface<T>"))
        .findAny().isPresent());

    assertEquals(combined.getAttributes().get(ORIGINAL_RETURN_TYPE), TRANSPARENT_REF);
  }

}
