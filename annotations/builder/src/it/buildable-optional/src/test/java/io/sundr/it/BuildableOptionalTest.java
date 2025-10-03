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
package io.sundr.it;

import org.junit.Test;
import java.util.Optional;

import static org.junit.Assert.*;

public class BuildableOptionalTest {

    @Test
    public void shouldBuildContainerWithSimple() {
        ContainerType container = new ContainerTypeBuilder()
            .withSimple(Optional.of(new SimpleType("test")))
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("test", container.getSimple().get().getName());
    }

    @Test
    public void shouldBuildContainerWithNewSimple() {
        ContainerType container = new ContainerTypeBuilder()
            .withNewSimple("test")
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("test", container.getSimple().get().getName());
    }

    @Test
    public void shouldBuildContainerWithNewSimpleLike() {
        SimpleType template = new SimpleType("template");
        ContainerType container = new ContainerTypeBuilder()
            .withNewSimpleLike(template)
            .endSimple()
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("template", container.getSimple().get().getName());
    }

    @Test
    public void shouldBuildContainerWithEmptyOptional() {
        ContainerType container = new ContainerTypeBuilder()
            .withSimple(Optional.empty())
            .build();
        
        assertFalse(container.getSimple().isPresent());
    }

    @Test
    public void shouldBuildSimpleType() {
        SimpleType simple = new SimpleTypeBuilder()
            .withName("simple")
            .build();
        
        assertEquals("simple", simple.getName());
    }

    @Test
    public void shouldTestFluentMethods() {
        ContainerTypeFluent<?> fluent = new ContainerTypeBuilder();
        
        fluent.withNewSimple("fluent-test");
        ContainerType container = ((ContainerTypeBuilder) fluent).build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("fluent-test", container.getSimple().get().getName());
    }
}
