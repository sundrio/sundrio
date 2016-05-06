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
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeRef;

import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

public class TypeParameterElementToTypeParamDef implements Function<TypeParameterElement, TypeParamDef> {

    private final Function<TypeMirror, TypeRef> toTypeRef;

    public TypeParameterElementToTypeParamDef(Function<TypeMirror, TypeRef> toTypeRef) {
        this.toTypeRef = toTypeRef;
    }

    public TypeParamDef apply(TypeParameterElement item) {
        List<ClassRef> typeRefs = new ArrayList();

        for(TypeMirror typeMirror : item.getBounds()) {
            //TODO: Fix this
            //typeRefs.add(toTypeRef.apply(typeMirror));
        }

        return new TypeParamDefBuilder()
                .withName(item.getSimpleName().toString())
                .withBounds(typeRefs.toArray(new ClassRef[typeRefs.size()]))
                .build();
    }
}
