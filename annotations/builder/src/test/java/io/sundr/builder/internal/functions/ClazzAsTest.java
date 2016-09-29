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

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import org.junit.Test;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class ClazzAsTest {

    private final Context context = new Context();
    private final Elements elements = JavacElements.instance(context);
    private final Types types = JavacTypes.instance(context);

    @Test
    public void testToFluent() {
        BuilderContext builderContext = BuilderContextManager.create(elements, types);

        TypeDef type = new TypeDefBuilder()
                .withName("MyClass")
                .withPackageName(getClass().getPackage().getName())
                .withParameters()
                .build();

        Method constructor = new MethodBuilder()
                .withReturnType(type.toReference())
                .withAnnotations(Constants.BUILDABLE_ANNOTATION)
                .build();

        type = new TypeDefBuilder(type)
                .withConstructors(constructor)
                .build();

        TypeDef result = ClazzAs.FLUENT_IMPL.apply(type);
        System.out.println(result);
    }
}