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
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import io.sundr.builder.VisitableBuilder;
import io.sundr.model.ClassRef;

import static org.junit.Assert.*;

public class BuildableOptionalDescendantsTest {

    @Test
    public void shouldBuildContainerWithFirstSimple() {
        ContainerType container = new ContainerTypeBuilder()
            .withSimple(Optional.of(new FirstSimple("test", "first")))
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("test", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof FirstSimple);
        assertEquals("first", ((FirstSimple) container.getSimple().get()).getType());
    }

    @Test
    public void shouldBuildContainerWithSecondSimple() {
        ContainerType container = new ContainerTypeBuilder()
            .withSimple(Optional.of(new SecondSimple("test", 42)))
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("test", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof SecondSimple);
        assertEquals(42, ((SecondSimple) container.getSimple().get()).getValue());
    }

    @Test
    public void shouldBuildContainerWithNewFirstSimple() {
        ContainerType container = new ContainerTypeBuilder()
            .withNewFirstSimple("test", "first")
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("test", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof FirstSimple);
        assertEquals("first", ((FirstSimple) container.getSimple().get()).getType());
    }

    @Test
    public void shouldBuildContainerWithNewSecondSimple() {
        ContainerType container = new ContainerTypeBuilder()
            .withNewSecondSimple("test", 42)
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("test", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof SecondSimple);
        assertEquals(42, ((SecondSimple) container.getSimple().get()).getValue());
    }

    @Test
    public void shouldBuildContainerWithNewFirstSimpleLike() {
        FirstSimple template = new FirstSimple("template", "first");
        ContainerType container = new ContainerTypeBuilder()
            .withNewFirstSimpleLike(template)
            .endFirstSimple()
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("template", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof FirstSimple);
        assertEquals("first", ((FirstSimple) container.getSimple().get()).getType());
    }

    @Test
    public void shouldBuildContainerWithNewSecondSimpleLike() {
        SecondSimple template = new SecondSimple("template", 99);
        ContainerType container = new ContainerTypeBuilder()
            .withNewSecondSimpleLike(template)
            .endSecondSimple()
            .build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("template", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof SecondSimple);
        assertEquals(99, ((SecondSimple) container.getSimple().get()).getValue());
    }

    @Test
    public void shouldBuildContainerWithEmptyOptional() {
        ContainerType container = new ContainerTypeBuilder()
            .withSimple(Optional.empty())
            .build();
        
        assertFalse(container.getSimple().isPresent());
    }

    @Test
    public void shouldVerifyFluentMethodsExist() throws Exception {
        ContainerTypeFluent<?> fluent = new ContainerTypeBuilder();
        
        // Verify withNewFirstSimple method exists
        Method withNewFirstSimple = ContainerTypeFluent.class.getMethod("withNewFirstSimple", String.class, String.class);
        assertNotNull(withNewFirstSimple);
        
        // Verify withNewSecondSimple method exists
        Method withNewSecondSimple = ContainerTypeFluent.class.getMethod("withNewSecondSimple", String.class, int.class);
        assertNotNull(withNewSecondSimple);
        
        // Verify withNewFirstSimpleLike method exists
        Method withNewFirstSimpleLike = ContainerTypeFluent.class.getMethod("withNewFirstSimpleLike", FirstSimple.class);
        assertNotNull(withNewFirstSimpleLike);
        
        // Verify withNewSecondSimpleLike method exists
        Method withNewSecondSimpleLike = ContainerTypeFluent.class.getMethod("withNewSecondSimpleLike", SecondSimple.class);
        assertNotNull(withNewSecondSimpleLike);
    }

    @Test
    public void shouldVerifySimplePropertyTypeInFluent() throws Exception {
        // Get the getSimple method from ContainerTypeFluent
        Method getSimpleMethod = ContainerTypeFluent.class.getMethod("buildSimple");
        assertNotNull(getSimpleMethod);
        
        // Verify return type is Optional<? extends VisitableBuilder>
        Type returnType = getSimpleMethod.getGenericReturnType();
        assertTrue(returnType instanceof ParameterizedType);
        
        ParameterizedType paramType = (ParameterizedType) returnType;
        assertEquals(Optional.class, paramType.getRawType());
        
        Type[] typeArgs = paramType.getActualTypeArguments();
        assertEquals(1, typeArgs.length);
        
        assertEquals("io.sundr.it.SimpleType", typeArgs[0].getTypeName());
    }

    @Test
    public void shouldTestFluentBuilderMethods() {
        ContainerTypeFluent<?> fluent = new ContainerTypeBuilder();
        
        // Test fluent method chaining with FirstSimple
        fluent.withNewFirstSimple("fluent-test", "first-type");
        ContainerType container = ((ContainerTypeBuilder) fluent).build();
        
        assertTrue(container.getSimple().isPresent());
        assertEquals("fluent-test", container.getSimple().get().getName());
        assertTrue(container.getSimple().get() instanceof FirstSimple);
        assertEquals("first-type", ((FirstSimple) container.getSimple().get()).getType());
    }
}
