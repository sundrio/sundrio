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

package io.sundr.builder;

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.codegen.functions.Sources;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeRef;
import org.junit.Test;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleClassTest {

    private final Context context = new Context();
    private final Elements elements = JavacElements.instance(context);
    private final Types types = JavacTypes.instance(context);
    private final BuilderContext builderContext = BuilderContextManager.create(elements, types);
    TypeDef simpleClassDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF.apply(getClass().getClassLoader().getResourceAsStream("SimpleClass.java"));



    @Test
    public void testFluent() {
        TypeDef fluent = ClazzAs.FLUENT_INTERFACE.apply(simpleClassDef);

        assertEquals(Kind.INTERFACE, fluent.getKind());
        assertEquals("SimpleClassFluent", fluent.getName());
        assertEquals(1, fluent.getExtendsList().size());


        ClassRef superClass = fluent.getExtendsList().iterator().next();
        assertEquals("Fluent", superClass.getDefinition().getName());
        assertEquals(1, superClass.getArguments().size());
        assertEquals("A", superClass.getArguments().iterator().next().toString());

        System.out.println(fluent);
    }


    @Test
    public void testFluentImpl() {
        TypeDef typeDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF.apply(getClass().getClassLoader().getResourceAsStream("SimpleClass.java"));
        TypeDef fluent = ClazzAs.FLUENT_IMPL.apply(typeDef);

        assertEquals(Kind.CLASS, fluent.getKind());
        assertEquals("SimpleClassFluentImpl", fluent.getName());
        assertEquals(1, fluent.getExtendsList().size());

        ClassRef superClass = fluent.getExtendsList().iterator().next();
        assertEquals("BaseFluent", superClass.getDefinition().getName());
        assertEquals(1, superClass.getArguments().size());
        assertEquals("A", superClass.getArguments().iterator().next().toString());

        System.out.println(fluent);
    }

    @Test
    public void testBuilder() {
        TypeDef typeDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF.apply(getClass().getClassLoader().getResourceAsStream("SimpleClass.java"));
        TypeDef fluent = ClazzAs.BUILDER.apply(typeDef);

        assertEquals(Kind.CLASS, fluent.getKind());
        assertEquals("SimpleClassBuilder", fluent.getName());
        assertEquals(1, fluent.getExtendsList().size());


        ClassRef superClass = fluent.getImplementsList().iterator().next();
        assertEquals("VisitableBuilder", superClass.getDefinition().getName());
        assertEquals(2, superClass.getArguments().size());
        Iterator<TypeRef> argIterator = superClass.getArguments().iterator();
        assertEquals("SimpleClass", argIterator.next().toString());
        assertEquals("SimpleClassBuilder", argIterator.next().toString());
        System.out.println(fluent);
    }
}
