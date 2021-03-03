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

package io.sundr.dsl.internal;

import java.util.Set;

import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.model.AttributeKey;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;

public final class Constants {

  private Constants() {
  }

  public static final AttributeKey<Boolean> IS_GENERATED = new AttributeKey<Boolean>("IS_GENERATED", Boolean.class);

  public static final AttributeKey<TypeRef> ORIGINAL_RETURN_TYPE = new AttributeKey<TypeRef>("ORIGINAL_RETURN_TYPE",
      TypeRef.class);
  public static final AttributeKey<Set<TypeDef>> TERMINATING_TYPES = new AttributeKey<Set<TypeDef>>("TERMINATING_TYPES",
      Set.class);
  public static final AttributeKey<Boolean> IS_ENTRYPOINT = new AttributeKey<Boolean>("IS_ENTRYPOINT", Boolean.class);
  public static final AttributeKey<Boolean> IS_TERMINAL = new AttributeKey<Boolean>("IS_TERMINAL", Boolean.class);
  public static final AttributeKey<Boolean> IS_GENERIC = new AttributeKey<Boolean>("IS_GENERIC", Boolean.class);
  public static final AttributeKey<Set<String>> CLASSES = new AttributeKey<Set<String>>("CLASSES", Set.class);
  public static final AttributeKey<Set<String>> KEYWORDS = new AttributeKey<Set<String>>("KEYWORDS", Set.class);
  public static final AttributeKey<Set<String>> METHODS = new AttributeKey<Set<String>>("METHODS", Set.class);
  public static final AttributeKey<String> BEGIN_SCOPE = new AttributeKey<String>("BEGIN_SCOPE", String.class);
  public static final AttributeKey<String> END_SCOPE = new AttributeKey<String>("END_SCOPE", String.class);
  public static final AttributeKey<Boolean> IS_COMPOSITE = new AttributeKey<Boolean>("IS_COMPOSITE", Boolean.class);

  public static final AttributeKey<Boolean> IS_TRANSITION = new AttributeKey<Boolean>("IS_TRANSITION", Boolean.class);
  public static final AttributeKey<Boolean> CARDINALITY_MULTIPLE = new AttributeKey<Boolean>("CARDINALITY_MULTIPLE",
      Boolean.class);
  public static final AttributeKey<String> METHOD_NAME = new AttributeKey<String>("METHOD_NAME", String.class);

  public static final AttributeKey<TypeRef> ORIGINAL_REF = new AttributeKey<TypeRef>("ORIGINAL_REF", TypeRef.class);
  public static final AttributeKey<TransitionFilter> FILTER = new AttributeKey<TransitionFilter>("FILTER",
      TransitionFilter.class);

  public static final String[] REMOVABLE_PREFIXES = { "With" };
  public static final String INTERFACE_SUFFIX = "Interface";

  public static final TypeDef VOID = ClassTo.TYPEDEF.apply(Void.class);
  public static final ClassRef VOID_REF = VOID.toInternalReference();

  public static final TypeParamDef TRANSPARENT = new TypeParamDefBuilder().withName("T").addToAttributes(IS_GENERIC, true)
      .build();
  public static final TypeParamRef TRANSPARENT_REF = new TypeParamRefBuilder().withName("T").addToAttributes(IS_GENERIC, true)
      .build();

  public static final String SCOPE_SUFFIX = "Scope";
}
