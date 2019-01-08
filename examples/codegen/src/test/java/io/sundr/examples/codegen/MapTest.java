/*
 *      Copyright 2018 The original authors.
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

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class MapTest {

    private final AttributeKey key1 = new AttributeKey("key1", String.class);
    private final AttributeKey key2 = new AttributeKey("key2", String.class);


    @Test
    public void testWithMap() {
        Map<AttributeKey, Object> map = new HashMap<>();

        map.put(key1, "value1");
        map.put(key2, "value2");
        AttributeSupport attributeSupport = new AttributeSupportBuilder()
                .withAttributes(map)
                .build();

        assertEquals("value1", attributeSupport.getAttributes().get(key1));
        assertEquals("value2", attributeSupport.getAttributes().get(key2));
    }

    @Test
    public void testAddToMap() {
        AttributeSupport attributeSupport = new AttributeSupportBuilder()
                .addToAttributes(key1, "value1")
                .addToAttributes(key2, "value2")
                .build();

        assertEquals("value1", attributeSupport.getAttributes().get(key1));
        assertEquals("value2", attributeSupport.getAttributes().get(key2));
    }

    @Test
    public void testRemoveFromMap() {
        AttributeSupport attributeSupport = new AttributeSupportBuilder()
                .removeFromAttributes(key1)
                .build();

        assertEquals(attributeSupport.getAttributes(), Collections.emptyMap());
    }

    @Test
    public void testRemoveMapFromMap() {
        Map<AttributeKey, Object> map = new HashMap<>();

        map.put(key1, "value1");
        map.put(key2, "value2");
        AttributeSupport attributeSupport = new AttributeSupportBuilder()
                .removeFromAttributes(map)
                .build();

        assertEquals(attributeSupport.getAttributes(), Collections.emptyMap());
    }
}
