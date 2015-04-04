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

import io.sundr.codegen.Property;

import java.util.Map;

public class JavaProperty extends AttributeSupport implements Property<JavaType> {

    private final JavaType type;
    private final String name;
    private final boolean array;

    public JavaProperty(JavaType type, String name, Map<String, Object> attributes, boolean array) {
        super(attributes);
        this.type = type;
        this.name = name;
        this.array = array;
    }

    public String getNameCapitalized() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0,1).toUpperCase());
        sb.append(name.substring(1));
        return sb.toString();
    }

    public String getNameCapitalizedSingular() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0,1).toUpperCase());
        //TODO: We need to do something better here.
        if (name.endsWith("s")) {
            sb.append(name.substring(1, name.length() - 1));
        } else {
            sb.append(name.substring(1));
        }
        return sb.toString();
    }

    public String getGetter() {
        StringBuilder sb = new StringBuilder();
        if (type.isBoolean()) {
            sb.append("is");
        } else {
            sb.append("get");
        }
        sb.append(getNameCapitalized());
        return sb.toString();
    }

    @Override
    public JavaType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isArray() {
        return array;
    }
}
