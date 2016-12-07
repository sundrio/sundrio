/*
 * Copyright 2016 The original authors.
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

package io.sundr.examples.codegen;

import io.sundr.builder.annotations.Buildable;

import java.util.Collections;
import java.util.Map;

@Buildable
public class VoidRef extends TypeRef {

    private static final String VOID = "void";

    public VoidRef() {
        this(Collections.<AttributeKey, Object>emptyMap());
    }

    public VoidRef(Map<AttributeKey, Object> attributes) {
        super(attributes);
    }

    @Override
    public boolean isAssignableFrom(TypeRef ref) {
        return false;
    }

    @Override
    public int getDimensions() {
        return 0;
    }

    @Override
    public TypeRef withDimensions(int dimensions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return VOID;
    }

}
