/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Fluent;

import java.util.HashMap;
import java.util.Map;

public class AttributeSupportFluent<T extends AttributeSupportFluent<T>> extends BaseFluent<T> implements Fluent<T> {

    Map<String, Object> attributes = new HashMap();

    public T addToAttributes(String key, Object value) {
        if (key != null && value != null) {
            this.attributes.put(key, value);
        }
        return (T) this;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public T withAttributes(Map<String, Object> attributes) {
        this.attributes.clear();
        if (attributes != null) {
            this.attributes.putAll(attributes);
        }
        return (T) this;
    }


}
