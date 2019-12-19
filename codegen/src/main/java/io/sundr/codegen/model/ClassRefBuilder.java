/*
 *      Copyright 2019 The original authors.
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

package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import static io.sundr.codegen.model.Kind.CLASS;
import static io.sundr.codegen.model.Kind.ENUM;
import static io.sundr.codegen.model.Kind.INTERFACE;

public class ClassRefBuilder extends ClassRefFluentImpl<ClassRefBuilder> implements VisitableBuilder<ClassRef,ClassRefBuilder>{

    private static final Map<CacheKey, EditableClassRef> CACHE = new ConcurrentHashMap<>();
    private static final class CacheKey {
        private final String fullyQualifiedName;
        private final int dimensions;
        private final int modifiers;
        private final List<TypeRef> arguments;

        private CacheKey(ClassRefFluent<?> fluent, TypeDef definition) {
            this.fullyQualifiedName = fluent.getFullyQualifiedName();
            this.dimensions = fluent.getDimensions();
            final Optional<TypeDef> optionalTypeDef = Optional.ofNullable(definition);
            this.modifiers = optionalTypeDef.map(TypeDef::getModifiers).orElse(-1);
            this.arguments = fluent.buildArguments();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) o;
            return dimensions == cacheKey.dimensions &&
                modifiers == cacheKey.modifiers &&
                Objects.equals(fullyQualifiedName, cacheKey.fullyQualifiedName) &&
                Objects.equals(arguments, cacheKey.arguments);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fullyQualifiedName, dimensions, modifiers, arguments);
        }
    }

    ClassRefFluent<?> fluent;
    Boolean validationEnabled;

    public ClassRefBuilder(){
            this(true);
    }
    public ClassRefBuilder(Boolean validationEnabled){
            this.fluent = this; this.validationEnabled=validationEnabled;
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent){
            this(fluent, true);
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent,Boolean validationEnabled){
            this.fluent = fluent; this.validationEnabled=validationEnabled;
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent,ClassRef instance){
            this(fluent, instance, true);
    }
    public ClassRefBuilder(ClassRefFluent<?> fluent,ClassRef instance,Boolean validationEnabled){
            this.fluent = fluent;
            fluent.withDefinition(instance.getDefinition());
            fluent.withFullyQualifiedName(instance.getFullyQualifiedName());
            fluent.withDimensions(instance.getDimensions());
            fluent.withArguments(instance.getArguments());
            fluent.withAttributes(instance.getAttributes());
            this.validationEnabled = validationEnabled;
    }
    public ClassRefBuilder(ClassRef instance){
            this(instance,true);
    }
    public ClassRefBuilder(ClassRef instance,Boolean validationEnabled){
            this.fluent = this;
            this.withDefinition(instance.getDefinition());
            this.withFullyQualifiedName(instance.getFullyQualifiedName());
            this.withDimensions(instance.getDimensions());
            this.withArguments(instance.getArguments());
            this.withAttributes(instance.getAttributes());
            this.validationEnabled = validationEnabled;
    }

    private static boolean canCache(List<TypeRef> arguments) {
        if (arguments.isEmpty()) {
            return true;
        }
        return arguments.stream().noneMatch(
            ((Predicate<TypeRef>) TypeParamRef.class::isInstance).negate().and(
                ((Predicate<TypeRef>) WildcardRefBuilder.class::isInstance).negate()).and(
                ((Predicate<TypeRef>) WildcardRef.class::isInstance).negate()));
    }

    private static boolean canCache(ClassRefFluent<?> fluent, TypeDef definition) {
        return definition != null
            && Arrays.asList(CLASS, INTERFACE, ENUM).contains(definition.getKind())
            && definition.getParameters().isEmpty()
            && canCache(fluent.buildArguments());
    }

    private static EditableClassRef newInstance(ClassRefFluent<?> fluent, TypeDef definition) {
        return new EditableClassRef(definition, fluent.getFullyQualifiedName(),
            fluent.getDimensions(), fluent.buildArguments(),fluent.getAttributes());
    }

    public EditableClassRef build(){
        final TypeDef definition = fluent.buildDefinition();
        if (canCache(fluent, definition)) {
            final CacheKey key = new CacheKey(fluent, definition);
            // ConcurrentHashMap#computeIfAbsent -> dead-lock
            return Optional.ofNullable(CACHE.get(key)).orElseGet(() -> {
                final EditableClassRef ret = newInstance(fluent, definition);
                CACHE.put(key, ret);
                return ret;
            });
        }
        return newInstance(fluent, definition);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            ClassRefBuilder that = (ClassRefBuilder) o;
            if (fluent != null &&fluent != this ? !fluent.equals(that.fluent) :that.fluent != null &&fluent != this ) return false;

            if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) :that.validationEnabled != null) return false;
            return true;
    }




}
