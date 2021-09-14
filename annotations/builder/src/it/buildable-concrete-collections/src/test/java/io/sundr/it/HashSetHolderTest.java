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
import java.util.HashSet;

import static org.junit.Assert.*;

public class HashSetHolderTest {

    @Test
    public void shouldReturnNull() {
        HashSetHolder item = new HashSetHolderBuilder().build();
        assertNull(item.getSet());
    }

    @Test
    public void shouldRespectWithNull() {
        HashSetHolder item = new HashSetHolderBuilder().withSet((HashSet<Thing>)null).build();
        assertNull(item.getSet());
    }

    @Test
    public void shouldRespectWithSet() {
        HashSetHolder item = new HashSetHolderBuilder().withSet(new HashSet<Thing>()).build();
        assertNotNull(item.getSet());
        assertEquals(0, item.getSet().size());
    }
}
