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

package io.sundr.codegen.converters;

import io.sundr.Function;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;

import javax.lang.model.type.TypeVariable;

public class TypeVariableToTypeParamRef implements Function<TypeVariable, TypeParamRef> {

    public TypeParamRef apply(TypeVariable item) {
        return new TypeParamRefBuilder().withName(item.asElement().getSimpleName().toString()).build();
    }
}
