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
import java.util.Set;
import java.util.LinkedHashSet;

import static org.junit.Assert.*;

public class DefaultTest {

    @Test
    public void shouldReturnNull() {
        Default item = new DefaultBuilder().build();
        assertNull(item.getSet());
    }

    @Test
    public void shouldRespectWithNull() {
        Default item = new DefaultBuilder().withSet((Set<Thing>)null).build();
        assertNull(item.getSet());
    }

    @Test
    public void shouldRespectWithSet() {
        Default item = new DefaultBuilder().withSet(new LinkedHashSet<Thing>()).build();
        assertNotNull(item.getSet());
        assertEquals(0, item.getSet().size());
    }

    @Test
    public void shouldRespecAddToSet() {
        Default item = new DefaultBuilder().addToSet(new Thing(1, "foo")).build();
        assertNotNull(item.getSet());
        assertEquals(1, item.getSet().size());
        assertEquals("foo", item.getSet().iterator().next().getName());
    }
}
