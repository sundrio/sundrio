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

package io.sundr.codegen.functions;

import io.sundr.FunctionFactory;
import io.sundr.Function;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeRef;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TypeReferencesFrom  {

    public static final Function<TypeRef,Set<TypeRef>> TYPEREF = FunctionFactory.cache(new Function<TypeRef, Set<TypeRef>>() {

        public Set<TypeRef> apply(TypeRef item) {
            Set<TypeRef> result = new LinkedHashSet<TypeRef>();
            result.add(item);

            if (item instanceof ClassRef) {
                ClassRef classRef = (ClassRef) item;
                for (TypeRef argument : classRef.getArguments()) {
                    result.addAll(TYPEREF.apply(argument));
                }
            }
            return result;
        }
    });

    public static final Function<Property,Set<TypeRef>> PROPERTY = FunctionFactory.cache(new Function<Property, Set<TypeRef>>() {

        public Set<TypeRef> apply(Property item) {
            Set<TypeRef> result = new LinkedHashSet<TypeRef>();
            result.addAll(TYPEREF.apply(item.getTypeRef()));

            for (ClassRef annotationRef : item.getAnnotations()) {
                result.addAll(TYPEREF.apply(annotationRef));
            }

            return result;
        }
    });

    public static final Function<Method,Set<TypeRef>> METHOD = FunctionFactory.cache(new Function<Method, Set<TypeRef>>() {

        public Set<TypeRef> apply(Method item) {
            Set<TypeRef> result = new HashSet<TypeRef>();
            if (item.getReturnType() != null) {
                result.add(item.getReturnType());
            }

            for (TypeRef t : item.getExceptions()) {
                result.add(t);
            }

            for (Property p : item.getArguments()) {
                result.addAll(PROPERTY.apply(p));
            }

            for (ClassRef annotationRef : item.getAnnotations()) {
                result.addAll(TYPEREF.apply(annotationRef));
            }
            return result;
        }
    });
}
