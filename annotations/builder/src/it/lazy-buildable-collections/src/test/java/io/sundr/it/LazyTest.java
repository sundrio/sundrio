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
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LazyTest {

    @Test
    public void shouldReturnNull() {
        Lazy item = new LazyBuilder().build();
        assertNull(item.getList());
    }

    @Test
    public void shouldRespectWithNull() {
        Lazy item = new LazyBuilder().withList((List<Thing>)null).build();
        assertNull(item.getList());
    }

    @Test
    public void shouldRespectWithList() {
        Lazy item = new LazyBuilder().withList(new ArrayList<Thing>()).build();
        assertNotNull(item.getList());
        assertEquals(0, item.getList().size());
    }

    @Test
    public void shouldRespecAddToList() {
        Lazy item = new LazyBuilder().addToList(new Thing(1, "foo")).build();
        assertNotNull(item.getList());
        assertEquals(1, item.getList().size());
        assertEquals("foo", item.getList().get(0).getName());
    }
}