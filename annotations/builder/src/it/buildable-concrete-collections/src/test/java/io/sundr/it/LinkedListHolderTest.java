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

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListHolderTest {

    @Test
    public void shouldReturnNull() {
        LinkedListHolder item = new LinkedListHolderBuilder().build();
        assertNull(item.getList());
    }

    @Test
    public void shouldRespectWithNull() {
        LinkedListHolder item = new LinkedListHolderBuilder().withList((LinkedList<Thing>)null).build();
        assertNull(item.getList());
    }

    @Test
    public void shouldRespectWithList() {
        LinkedListHolder item = new LinkedListHolderBuilder().withList(new LinkedList<Thing>()).build();
        assertNotNull(item.getList());
        assertEquals(0, item.getList().size());
    }

    @Test
    public void shouldRespecAddToList() {
        LinkedListHolder item = new LinkedListHolderBuilder().addToList(new Thing(1, "foo")).build();
        assertNotNull(item.getList());
        assertEquals(1, item.getList().size());
        assertEquals("foo", item.getList().get(0).getName());
    }

    @Test
    public void shouldSupportNestedBuilderMethods() {
        // Test nested builder pattern: addNewList()...endList()
        // This verifies JDK 25 compatibility for generic type parameter resolution
        LinkedListHolder item = new LinkedListHolderBuilder()
            .addNewList()
                .withId(42)
                .withName("nested-builder-test")
            .endList()
            .build();

        assertNotNull(item.getList());
        assertEquals(1, item.getList().size());
        assertEquals(42, item.getList().get(0).getId());
        assertEquals("nested-builder-test", item.getList().get(0).getName());
    }

    @Test
    public void shouldSupportMultipleNestedBuilders() {
        // Test multiple nested builder calls
        // This tests that annotation processor handles List<T> bounds correctly
        LinkedListHolder item = new LinkedListHolderBuilder()
            .addNewList()
                .withId(1)
                .withName("first")
            .endList()
            .addNewList()
                .withId(2)
                .withName("second")
            .endList()
            .build();

        assertNotNull(item.getList());
        assertEquals(2, item.getList().size());
        assertEquals(1, item.getList().get(0).getId());
        assertEquals("first", item.getList().get(0).getName());
        assertEquals(2, item.getList().get(1).getId());
        assertEquals("second", item.getList().get(1).getName());
    }

    @Test
    public void shouldSupportEditNestedBuilder() {
        // Test editing existing items with nested builders
        LinkedListHolder original = new LinkedListHolderBuilder()
            .addNewList()
                .withId(10)
                .withName("original")
            .endList()
            .build();

        LinkedListHolder modified = new LinkedListHolderBuilder(original)
            .editList(0)
                .withId(20)
                .withName("modified")
            .endList()
            .build();

        assertEquals(1, modified.getList().size());
        assertEquals(20, modified.getList().get(0).getId());
        assertEquals("modified", modified.getList().get(0).getName());
    }
}
