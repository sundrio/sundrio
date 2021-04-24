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

package io.sundr.codegen.functions;

import static io.sundr.codegen.functions.ClassTo.TYPEDEF;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Function;

import io.sundr.FunctionFactory;
import io.sundr.codegen.utils.TypeUtils;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class Optionals {

  public static final TypeDef OPTIONAL = TYPEDEF.apply(Optional.class);
  public static final TypeDef OPTIONAL_INT = TYPEDEF.apply(OptionalInt.class);
  public static final TypeDef OPTIONAL_DOUBLE = TYPEDEF.apply(OptionalDouble.class);
  public static final TypeDef OPTIONAL_LONG = TYPEDEF.apply(OptionalLong.class);

  public static final Function<TypeRef, Boolean> IS_OPTIONAL = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return TypeUtils.isInstanceOf(type, OPTIONAL, IS_OPTIONAL);
    }
  });

  public static final Function<TypeRef, Boolean> IS_OPTIONAL_INT = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return TypeUtils.isInstanceOf(type, OPTIONAL_INT, IS_OPTIONAL_INT);
    }
  });

  public static final Function<TypeRef, Boolean> IS_OPTIONAL_DOUBLE = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return TypeUtils.isInstanceOf(type, OPTIONAL_DOUBLE, IS_OPTIONAL_DOUBLE);
    }
  });

  public static final Function<TypeRef, Boolean> IS_OPTIONAL_LONG = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return TypeUtils.isInstanceOf(type, OPTIONAL_LONG, IS_OPTIONAL_LONG);
    }
  });

}
