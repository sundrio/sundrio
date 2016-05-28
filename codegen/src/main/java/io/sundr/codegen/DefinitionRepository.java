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

package io.sundr.codegen;

import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeRef;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DefinitionRepository {

    private static DefinitionRepository INSTANCE;

    private final Map<String, TypeDef> definitions = new HashMap<String, TypeDef>();

    private DefinitionRepository() {
    }

    public static synchronized final DefinitionRepository getRepository() {
        if (INSTANCE == null) {
            INSTANCE = new DefinitionRepository();
        }
        return INSTANCE;
    }

    public TypeDef register(TypeDef definition) {
        if (definition != null) {
            definitions.put(definition.getFullyQualifiedName(), definition);
        }
        return definition;
    }

    public Set<TypeDef> getDefinitions() {
        return Collections.unmodifiableSet(new LinkedHashSet<TypeDef>(definitions.values()));
    }

    public TypeDef getDefinition(String fullyQualifiedName) {
       return definitions.get(fullyQualifiedName);
    }

    public TypeDef getDefinition(TypeRef type) {
        if (type instanceof ClassRef) {
            return definitions.get(((ClassRef)type).getDefinition().getFullyQualifiedName());
        }
        return null;
    }

    public void clear() {
        definitions.clear();
    }
}
