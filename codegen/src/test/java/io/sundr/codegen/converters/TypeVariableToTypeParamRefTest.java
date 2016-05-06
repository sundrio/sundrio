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
import io.sundr.codegen.model.TypeDef;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class TypeVariableToTypeParamRefTest {

    private final Context context = new Context();
    private final Elements elements = JavacElements.instance(context);
    private final Types types = JavacTypes.instance(context);


    private final ModifiersToInt modifiersToInt = new ModifiersToInt();
    private final TypeVariableToTypeParamRef toTypeParamRef = new TypeVariableToTypeParamRef();

    private final TypeRefTypeVisitor typeRefTypeVisitor = new TypeRefTypeVisitor(toTypeParamRef);

    private final TypeMirrorToTypeRef toTypeRef = new TypeMirrorToTypeRef(typeRefTypeVisitor);
    private final VariableElementToProperty toProperty = new VariableElementToProperty(toTypeRef);
    private final ExecutableElementToMethod toMethod = new ExecutableElementToMethod(toTypeRef,toProperty, modifiersToInt);

    private final TypeParameterElementToTypeParamDef toTypeParamDef = new TypeParameterElementToTypeParamDef(toTypeRef);

    private final TypeDefElementVisitor typeDefElementVisitor = new TypeDefElementVisitor(toProperty, toMethod, toTypeParamDef);

    private final TypeElementToTypeDef toTypeDef = new TypeElementToTypeDef(elements, toTypeRef, toMethod, toProperty);

    @Before
    public void setUp() {
        typeRefTypeVisitor.setElementVisitor(typeDefElementVisitor);
    }

    @Test
    public void testWithSimpleClass() {
        TypeElement typeElement = elements.getTypeElement(SimpleClass.class.getCanonicalName());
        TypeDef typeDef = toTypeDef.apply(typeElement);
        Assert.assertNotNull(typeElement);
    }

    @Test
    public void testWithParameterizedClass() {
        TypeElement typeElement = elements.getTypeElement(ClassWithParam.class.getCanonicalName());
        TypeDef typeDef = toTypeDef.apply(typeElement);
        Assert.assertNotNull(typeElement);
    }

}