/*
 *      Copyright 2019 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder.internal.processor;

import io.sundr.builder.internal.functions.ToPojo;
import io.sundr.codegen.functions.Sources;
import io.sundr.codegen.model.TypeDef;
import org.junit.Assert;
import org.junit.Test;



public class SimpleAnnotationTest extends AbstractProcessorTest {

    TypeDef simpleClassDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF.apply(getClass().getClassLoader().getResourceAsStream("SimpleAnnotation.java"));

    @Test
    public void testPojo() {
        System.out.println(simpleClassDef);
        TypeDef pojo = new ToPojo().apply(simpleClassDef);
        Assert.assertNotNull(pojo);
    }


}
