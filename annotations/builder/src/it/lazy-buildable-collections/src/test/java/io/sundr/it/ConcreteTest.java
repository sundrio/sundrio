/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.it;

import org.junit.Test;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;


public class ConcreteTest {

    @Test
    public void shouldReturnNull() {
        Default item = new DefaultBuilder().build();
        assertNull(item.getList());
        assertNull(item.getSet());
    }

    @Test
    public void shouldRespectWithNull() {
        Default item = new DefaultBuilder().withList((LinkedList<Thing>)null).withSet((HashSet<Thing>)null).build();
        assertNull(item.getList());
        assertNull(item.getSet());
    }

    @Test
    public void shouldRespectWith() {
        Default item = new DefaultBuilder().withList(new LinkedList<>()).withSet(new HashSet<>()).build();
        assertNotNull(item.getList());
        assertEquals(0, item.getList().size());
        assertEquals(0, item.getSet().size());
    }
}
