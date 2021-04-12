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

import java.util.function.Function;
import java.util.function.Predicate;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.FunctionFactory;
import io.sundr.codegen.functions.element.ElementContext;
import io.sundr.codegen.functions.element.TypeElementToTypeDef;
import io.sundr.codegen.model.TypeDef;

public class ElementTo {

  private static final String JAVA_PEFIX = "java.";
  private static final String JAVAX_PEFIX = "javax.";
  private static final String COM_SUN_PREFIX = "com.sun.";

  private static final Predicate<TypeMirror> IS_JAVA_TYPE_MIRROR = new Predicate<TypeMirror>() {
    public boolean test(TypeMirror item) {
      String fqn = item.toString();
      return fqn.startsWith(JAVA_PEFIX) || fqn.startsWith(JAVAX_PEFIX) || fqn.startsWith(COM_SUN_PREFIX);
    }
  };

  private static final Predicate<TypeElement> IS_JAVA_ELEMENT = new Predicate<TypeElement>() {
    public boolean test(TypeElement item) {
      String fqn = item.toString();
      return fqn.startsWith(JAVA_PEFIX) || fqn.startsWith(JAVAX_PEFIX) || fqn.startsWith(COM_SUN_PREFIX);
    }
  };

  public static final ElementContext DEEP = new ElementContext(true);
  public static final ElementContext SHALLOW = new ElementContext(false);

  private static final Function<TypeElement, TypeDef> DEEP_TYPEELEMENT_TO_TYPEDEF = new TypeElementToTypeDef(DEEP);
  private static final Function<TypeElement, TypeDef> SHALLOW_TYPEELEMENT_TO_TYPEDEF = new TypeElementToTypeDef(SHALLOW);

  public static final Function<TypeElement, TypeDef> TYPEDEF = FunctionFactory.cache(DEEP_TYPEELEMENT_TO_TYPEDEF);

}
