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

import io.sundr.builder.Editable;

import javax.lang.model.element.Modifier;
import java.util.Map;
import java.util.Set;

public class EditableJavaMethod extends JavaMethod implements Editable<JavaMethodBuilder> {


    public EditableJavaMethod(Set<JavaType> annotations, Set<Modifier> modifiers, Set<JavaType> typeParameters, String name, JavaType returnType, JavaProperty[] arguments, Set<JavaType> exceptions, Map<String, Object> attributes) {
        super(annotations, modifiers, typeParameters, name, returnType, arguments, exceptions, attributes);
    }

    public JavaMethodBuilder edit() {
        return new JavaMethodBuilder(this);
    }


}
    