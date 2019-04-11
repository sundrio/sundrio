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
import java.util.HashMap;

import static org.junit.Assert.*;

public class EagerTest {

    @Test
    public void shouldReturnEmpty() {
        Eager item = new EagerBuilder().build();
        assertNotNull(item.getMap());
        assertEquals(0, item.getMap().size());
    }

    @Test
    public void shouldRespectWithNullMap() {
        Eager item = new EagerBuilder().withMap(null).build();
        assertNull(item.getMap());
    }

    @Test
    public void shouldRespectWithMap() {
        Eager item = new EagerBuilder().withMap(new HashMap<String,String>()).build();
        assertNotNull(item.getMap());
        assertEquals(0, item.getMap().size());
    }

    @Test
    public void shouldRespecAddToMap() {
        Eager item = new EagerBuilder().addToMap("foo", "bar").build();
        assertNotNull(item.getMap());
        assertEquals(1, item.getMap().size());
        assertEquals("bar", item.getMap().get("foo"));
    }
}