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

package io.sundr.codegen.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;

import java.util.HashSet;
import java.util.Set;

public enum JavaClazzToReferences implements Function<JavaClazz, Set<JavaType>> {

    FUNCTION;

    @Override
    public Set<JavaType> apply(JavaClazz item) {
        Set<JavaType> result = new HashSet<>();

        result.addAll(JavaTypeToReferences.FUNCTION.apply(item.getType()));

        for (JavaMethod c : item.getConstructors()) {
            result.addAll(JavaMethodToReferences.FUNCTION.apply(c));
        }
        for (JavaProperty p : item.getFields()) {
            result.addAll(JavaPropertyToReferences.FUNCTION.apply(p));
        }
        for (JavaMethod m : item.getMethods()) {
            result.addAll(JavaMethodToReferences.FUNCTION.apply(m));
        }
        for (JavaClazz nested : item.getNested()) {
            result.addAll(apply(nested));
        }
        return result;
    }
}
