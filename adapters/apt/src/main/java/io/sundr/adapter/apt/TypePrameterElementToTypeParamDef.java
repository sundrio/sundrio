/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.adapter.apt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.SundrException;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeRef;

public class TypePrameterElementToTypeParamDef implements Function<TypeParameterElement, TypeParamDef> {

  private final AptContext context;
  private final Function<TypeMirror, TypeRef> referenceAdapterFunction;

  public TypePrameterElementToTypeParamDef(AptContext context, Function<TypeMirror, TypeRef> referenceAdapterFunction) {
    this.context = context;
    this.referenceAdapterFunction = referenceAdapterFunction;
  }

  public TypeParamDef apply(TypeParameterElement item) {
    List<ClassRef> typeRefs = new ArrayList<>();

    try {
      for (TypeMirror typeMirror : item.getBounds()) {
        TypeRef typeRef = referenceAdapterFunction.apply(typeMirror);
        if (typeRef instanceof ClassRef) {
          typeRefs.add((ClassRef) referenceAdapterFunction.apply(typeMirror));
        }
      }
    } catch (Exception e) {
      //if we can't process bound just return the type without any.
      throw new SundrException("Failed to get bounds of type: " + item.toString() + ". This may be caused due to the compiler not being able to resolve a type. Please check for unresolved symbols!", e);
    }

    return new TypeParamDefBuilder().withName(item.getSimpleName().toString()).withBounds(typeRefs).build();
  }

}
