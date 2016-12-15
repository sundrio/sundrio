/*
 *      Copyright 2016 The original authors.
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

import java.util.List;
import java.util.Map;

import io.sundr.builder.Editable;

public class EditableMethod extends Method implements Editable<MethodBuilder>{


    public EditableMethod(List<String> comments,List<AnnotationRef> annotations,List<TypeParamDef> parameters,String name,TypeRef returnType,List<Property> arguments,boolean varArgPreferred,List<ClassRef> exceptions,Block block,int modifiers,Map<AttributeKey,Object> attributes){
            super(comments, annotations, parameters, name, returnType, arguments, varArgPreferred, exceptions, block, modifiers, attributes);
    }

    public MethodBuilder edit(){
            return new MethodBuilder(this);
    }




}
