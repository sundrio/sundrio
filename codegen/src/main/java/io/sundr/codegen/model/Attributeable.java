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

package io.sundr.codegen.model;

import io.sundr.Function;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Attributeable extends Node {

    AttributeKey<Collection<ClassRef>> ALSO_IMPORT = new AttributeKey<Collection<ClassRef>>("ALSO_IMPORT", Collection.class);
    AttributeKey<String> INIT = new AttributeKey<String>("INIT", String.class);
    AttributeKey<String> LAZY_INIT = new AttributeKey<String>("LAZY_INIT", String.class);
    AttributeKey<Function<List<String>, String>> INIT_FUNCTION = new AttributeKey<Function<List<String>, String>>("INIT_FUNCTION", Function.class);


    Map<AttributeKey, Object> getAttributes();

    <T> T getAttribute(AttributeKey<T> key);

    <T> boolean hasAttribute(AttributeKey<T> key);
}
