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

import java.lang.reflect.Modifier;
import java.util.Map;

@Buildable
public class ModifierSupport extends AttributeSupport {

    private final int modifiers;

    public ModifierSupport(int modifiers, Map<String, Object> attributes) {
        super(attributes);
        this.modifiers = modifiers;
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(modifiers);
    }

    public boolean isProtected() {
        return Modifier.isProtected(modifiers);
    }

    public boolean isPublic() {
        return Modifier.isPublic(modifiers);
    }

    public boolean isFinal() {
        return Modifier.isFinal(modifiers);
    }

    public boolean isStatic() {
        return Modifier.isStatic(modifiers);
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(modifiers);
    }

    public boolean isSynchronized() {
        return Modifier.isSynchronized(modifiers);
    }

    public boolean isTransient() {
        return Modifier.isSynchronized(modifiers);
    }

    public int getModifiers() {
        return modifiers;
    }
}
