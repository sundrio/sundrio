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

package io.sundr.utils;

import java.util.HashMap;
import java.util.Map;

public final class Maps {

  private Maps() {
    //Utility class
  }

  public static <K, V> Map<K, V> create(K key, V value) {
    Map<K, V> result = new HashMap<>();
    result.put(key, value);
    return result;
  }

  public static <K, V> Map<K, V> create(K key1, V value1, K key2, V value2) {
    Map<K, V> result = new HashMap<>();
    result.put(key1, value1);
    result.put(key2, value2);
    return result;
  }

  public static <K, V> Map<K, V> create(K key1, V value1, K key2, V value2, K key3, V value3) {
    Map<K, V> result = new HashMap<>();
    result.put(key1, value1);
    result.put(key2, value2);
    result.put(key3, value3);
    return result;
  }
}
