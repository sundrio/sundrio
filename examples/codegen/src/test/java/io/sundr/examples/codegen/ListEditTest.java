/*
 *      Copyright 2016 The original authors.
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

package io.sundr.examples.codegen;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import io.sundr.builder.Builder;
import io.sundr.builder.Predicate;

public class ListEditTest {

    @Test
    public void testEditWithIndex() {
        TypeRef intRef = new PrimitiveRef("int", 0, new HashMap<AttributeKey, Object>());

        Method method = new MethodBuilder()
                .withName("mymethod")
                .addNewArgument()
                    .withTypeRef(intRef)
                    .withName("a")
                .endArgument()
                .addNewArgument()
                .withTypeRef(intRef)
                    .withName("b")
                .endArgument()
                .addNewArgument()
                .withTypeRef(intRef)
                    .withName("c")
                .endArgument()
                .build();

        Method capitalA = new MethodBuilder(method).editFirstArgument().withName("A").endArgument().build();
        Assert.assertEquals(3, capitalA.getArguments().size());
        Assert.assertEquals("A", capitalA.getArguments().get(0).getName());

        Method capitalB = new MethodBuilder(method).editArgument(1).withName("B").endArgument().build();
        Assert.assertEquals(3, capitalB.getArguments().size());
        Assert.assertEquals("B", capitalB.getArguments().get(1).getName());

        Method capitalC = new MethodBuilder(method).editLastArgument().withName("C").endArgument().build();
        Assert.assertEquals(3, capitalC.getArguments().size());
        Assert.assertEquals("C", capitalC.getArguments().get(2).getName());


        Method capitalC1 = new MethodBuilder(method).editMatchingArgument(new Predicate<PropertyBuilder>() {
            @Override
            public boolean apply(PropertyBuilder item) {
                return item.getName().equals("c");
            }
        }).withName("C1").endArgument().build();

        Assert.assertEquals(3, capitalC1.getArguments().size());
        Assert.assertEquals("C1", capitalC1.getArguments().get(2).getName());
    }
}
