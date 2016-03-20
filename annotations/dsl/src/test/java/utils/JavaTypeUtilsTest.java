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

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.processor.DslProcessorContext;
import io.sundr.dsl.internal.type.functions.Combine;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.utils.JavaTypeUtils;
import org.hamcrest.CoreMatchers;
import utils.matchers.TypeNamed;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Arrays;
import java.util.List;

import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.executableToInterface;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isVoid;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JavaTypeUtilsTest {


    private final Context context = new Context();
    private final Elements elements = JavacElements.instance(context);
    private final Types types = JavacTypes.instance(context);
    private final DslProcessorContext dslContext = new DslProcessorContext(elements, types);
    private final JavaType STRING = new JavaTypeBuilder().withPackageName("java.lang").withClassName("String").build();
    private final JavaType INTEGER = new JavaTypeBuilder().withPackageName("java.lang").withClassName("Integer").build();
    private final JavaType LONG = new JavaTypeBuilder().withPackageName("java.lang").withClassName("Long").build();

    @Test
    public void testExecutableToInterface() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);

        JavaClazz simpleClazz = JavaTypeUtils.executableToInterface(dslContext, simple);
        Assert.assertThat(simpleClazz.getType().getClassName(), CoreMatchers.equalTo("MethodAInterface"));
        Assert.assertThat(simpleClazz.getType().getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
        Assert.assertThat(simpleClazz.getType().getGenericTypes().length, CoreMatchers.is(1));
        Assert.assertThat(simpleClazz.getType().getInterfaces().size(), CoreMatchers.is(0));
        Assert.assertThat((JavaType) simpleClazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), TypeNamed.typeNamed("T"));
        Assert.assertFalse((Boolean) simpleClazz.getType().getAttributes().get(IS_TERMINAL));

        JavaClazz terminalClazz = JavaTypeUtils.executableToInterface(dslContext, terminal);
        Assert.assertThat(terminalClazz.getType().getClassName(), CoreMatchers.equalTo("MethodBInterface"));
        Assert.assertThat(terminalClazz.getType().getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
        Assert.assertThat(terminalClazz.getType().getGenericTypes().length, CoreMatchers.is(1));
        Assert.assertThat(terminalClazz.getType().getInterfaces().size(), CoreMatchers.is(0));
        Assert.assertThat((JavaType) terminalClazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), TypeNamed.typeNamed("T"));
        Assert.assertTrue((Boolean) terminalClazz.getType().getAttributes().get(IS_TERMINAL));
    }

    @Test
    public void testCombineTwoWithTerminal() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        JavaClazz simpleClazz = JavaTypeUtils.executableToInterface(dslContext, simple);
        JavaClazz teminalClazz = JavaTypeUtils.executableToInterface(dslContext, terminal);
        JavaClazz combined = Combine.FUNCTION.apply(Arrays.asList(simpleClazz, teminalClazz));
        Assert.assertNotNull(combined);

        String T = Generics.MAP.apply(TRANSPARENT).getClassName();
        Assert.assertThat(combined.getType().getClassName(), CoreMatchers.equalTo("MethodABInterface"));
        Assert.assertThat(combined.getType().getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
        Assert.assertThat(combined.getType().getGenericTypes().length, CoreMatchers.is(1));
        Assert.assertThat(combined.getType().getGenericTypes()[0].toString(), CoreMatchers.equalTo(T));
        Assert.assertThat(combined.getType().getInterfaces().size(), CoreMatchers.is(2));
        Assert.assertThat(combined.getType().getInterfaces(), CoreMatchers.hasItem(TypeNamed.typeNamed("MethodAInterface<" + T + ">")));
        Assert.assertThat(combined.getType().getInterfaces(), CoreMatchers.hasItem(TypeNamed.typeNamed("MethodBInterface<" + T + ">")));
    }


    @Test
    public void testCombineTwoNonTerminal() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoNonTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement left = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        ExecutableElement right = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        JavaClazz leftClazz = executableToInterface(dslContext, left);
        JavaClazz rightClazz = executableToInterface(dslContext, right);
        JavaClazz combined = Combine.FUNCTION.apply(Arrays.asList(leftClazz, rightClazz));
        Assert.assertNotNull(combined);

        Assert.assertThat(combined.getType().getClassName(), CoreMatchers.equalTo("MethodABInterface"));
        Assert.assertThat(combined.getType().getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
        Assert.assertThat(combined.getType().getGenericTypes().length, CoreMatchers.is(1));
        Assert.assertThat(combined.getType().getGenericTypes()[0].toString(), CoreMatchers.equalTo("T"));
        Assert.assertThat(combined.getType().getInterfaces().size(), CoreMatchers.is(2));
        Assert.assertThat(combined.getType().getInterfaces(), CoreMatchers.hasItem(TypeNamed.typeNamed("MethodAInterface<T>")));
        Assert.assertThat(combined.getType().getInterfaces(), CoreMatchers.hasItem(TypeNamed.typeNamed("MethodBInterface<T>")));
        assertEquals(combined.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), TRANSPARENT);
    }

    @Test
    public void testCombineTwoTerminal() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement left = methods.get(0);
        ExecutableElement right = methods.get(1);
        JavaClazz leftClazz = executableToInterface(dslContext, left);
        JavaClazz rightClazz = executableToInterface(dslContext, right);
        JavaClazz combined = Combine.FUNCTION.apply(Arrays.asList(leftClazz, rightClazz));
        Assert.assertNotNull(combined);

        Assert.assertThat(combined.getType().getClassName(), CoreMatchers.equalTo("MethodABInterface"));
        Assert.assertThat(combined.getType().getPackageName(), CoreMatchers.equalTo(getClass().getPackage().getName()));
        Assert.assertThat(combined.getType().getGenericTypes().length, CoreMatchers.is(1));
        Assert.assertThat(combined.getType().getInterfaces().size(), CoreMatchers.is(2));
        Assert.assertThat(combined.getType().getInterfaces(), CoreMatchers.hasItem(TypeNamed.typeNamed("MethodAInterface<T>")));
        Assert.assertThat(combined.getType().getInterfaces(), CoreMatchers.hasItem(TypeNamed.typeNamed("MethodBInterface<T>")));
        assertEquals(combined.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), TRANSPARENT);
    }

}