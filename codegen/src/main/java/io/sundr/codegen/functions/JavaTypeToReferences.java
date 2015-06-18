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
import io.sundr.codegen.model.JavaType;

import java.util.HashSet;
import java.util.Set;

public enum  JavaTypeToReferences implements Function<JavaType, Set<JavaType>> {

    FUNCTION;
    
    @Override
    public Set<JavaType> apply(JavaType item) {
        Set<JavaType> result = new HashSet<JavaType>();
        if (item != null) {
            result.add(item);

            if (item.getSuperClass() != null) {
                result.addAll(apply(item.getSuperClass()));
            }

            for (JavaType t : item.getInterfaces()) {
                result.addAll(apply(t));
            }

            for (JavaType t : item.getGenericTypes()) {
                result.addAll(apply(t));
            }

            if (item.getDefaultImplementation() != null) {
                result.addAll(apply(item.getDefaultImplementation()));
            }
        }
        return result;
    }
}
