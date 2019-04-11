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

public class DefaultTest {

    @Test
    public void shouldReturnNull() {
        Default item = new DefaultBuilder().build();
        assertNull(item.getMap());
    }

    @Test
    public void shouldRespectWithNullMap() {
        Default item = new DefaultBuilder().withMap(null).build();
        assertNull(item.getMap());
    }

    @Test
    public void shouldRespectWithMap() {
        Default item = new DefaultBuilder().withMap(new HashMap<String,String>()).build();
        assertNotNull(item.getMap());
        assertEquals(0, item.getMap().size());
    }

    @Test
    public void shouldRespecAddToMap() {
        Default item = new DefaultBuilder().addToMap("foo", "bar").build();
        assertNotNull(item.getMap());
        assertEquals(1, item.getMap().size());
        assertEquals("bar", item.getMap().get("foo"));
    }
}