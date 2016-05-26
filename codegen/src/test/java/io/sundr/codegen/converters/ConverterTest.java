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

package io.sundr.codegen.converters;

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.PrimitiveRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeRef;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static org.junit.Assert.*;

public class ConverterTest {

    private final Context context = new Context();
    private final Elements elements = JavacElements.instance(context);
    private final Types types = JavacTypes.instance(context);

    @Before
    public void setUp() {
        CodegenContext.create(elements, types);
    }

    @Test
    public void testWithSimpleClass() {
        TypeElement typeElement = elements.getTypeElement(SimpleClass.class.getCanonicalName());
        TypeDef typeDef = ElementTo.TYPEDEF.apply(typeElement);
        assertNotNull(typeDef);
        assertEquals("SimpleClass", typeDef.getName());
    }

    @Test
    public void testWithArray() {
        TypeElement typeElement = elements.getTypeElement(ClassWithArray.class.getCanonicalName());
        TypeDef typeDef =  ElementTo.TYPEDEF.apply(typeElement);

        assertNotNull(typeDef);
        assertEquals("ClassWithArray", typeDef.getName());
        assertEquals(1, typeDef.getProperties().size());
        TypeRef typeRef = typeDef.getProperties().iterator().next().getTypeRef();

        assertTrue(typeRef instanceof ClassRef);
        assertEquals(1, ((ClassRef)typeRef).getDimensions());
    }

    @Test
    public void testWithPrimitiveArray() {
        TypeElement typeElement = elements.getTypeElement(ClassWithPrimitiveArray.class.getCanonicalName());
        TypeDef typeDef =  ElementTo.TYPEDEF.apply(typeElement);

        assertNotNull(typeDef);
        assertEquals("ClassWithPrimitiveArray", typeDef.getName());
        assertEquals(1, typeDef.getProperties().size());
        TypeRef typeRef = typeDef.getProperties().iterator().next().getTypeRef();

        assertTrue(typeRef instanceof PrimitiveRef);
        assertEquals(1, ((PrimitiveRef)typeRef).getDimensions());
    }

    @Test
    public void testWithParam() {
        TypeElement typeElement = elements.getTypeElement(ClassWithParam.class.getCanonicalName());
        TypeDef typeDef =  ElementTo.TYPEDEF.apply(typeElement);
        assertNotNull(typeDef);

        assertEquals("ClassWithParam", typeDef.getName());
        assertEquals(1, typeDef.getProperties().size());
        assertEquals(1, typeDef.getParameters().size());

        assertEquals("T", typeDef.getParameters().get(0).getName());
        assertEquals(0, typeDef.getParameters().get(0).getBounds().size());
    }

    @Test
    public void testWithSelfRefParam() {
        TypeElement typeElement = elements.getTypeElement(ClassWithSelfRefParam.class.getCanonicalName());
        TypeDef typeDef =  ElementTo.TYPEDEF.apply(typeElement);
        assertNotNull(typeDef);

        assertEquals("ClassWithSelfRefParam", typeDef.getName());
        assertEquals(1, typeDef.getProperties().size());
        assertEquals(1, typeDef.getParameters().size());

        assertEquals("T", typeDef.getParameters().get(0).getName());
        assertEquals(1, typeDef.getParameters().get(0).getBounds().size());
        assertEquals(1, typeDef.getParameters().get(0).getBounds().get(0).getArguments().size());
    }

}