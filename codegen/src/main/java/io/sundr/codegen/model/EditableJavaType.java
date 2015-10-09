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

import java.util.Map;
import java.util.Set;

public class EditableJavaType extends JavaType implements Editable<JavaTypeBuilder>{


public EditableJavaType( JavaKind kind , String packageName , String className , boolean array , boolean collection , boolean concrete , JavaType defaultImplementation , JavaType superClass , Set<JavaType> interfaces , JavaType[] genericTypes , Map<String, Object> attributes ){
    super(kind, packageName, className, array, collection, concrete, defaultImplementation, superClass, interfaces, genericTypes, attributes);
}

public JavaTypeBuilder edit(){
    return new JavaTypeBuilder(this);
}


}
    
