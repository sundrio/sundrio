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

package io.sundr.model.utils;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Function;

import io.sundr.FunctionFactory;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeRef;

public class Optionals {

  public static TypeParamDef E = new TypeParamDefBuilder().withName("E").build();

  public static final TypeDef OPTIONAL = new TypeDefBuilder(TypeDef.forName(Optional.class.getName()))
      .withParameters(E)
      .addNewMethod()
      .withNewModifiers().withPublic().withStatic().endModifiers()
      .withName("of")
      .addNewArgument()
      .withName("value")
      .withTypeRef(E.toReference())
      .endArgument()
      .endMethod()
      .build();

  public static final TypeDef OPTIONAL_INT = new TypeDefBuilder(TypeDef.forName(OptionalInt.class.getName()))
      .addNewMethod()
      .withNewModifiers().withPublic().withStatic().endModifiers()
      .withName("of")
      .addNewArgument()
      .withName("value")
      .withTypeRef(Types.PRIMITIVE_INT_REF)
      .endArgument()
      .endMethod()
      .build();

  public static final TypeDef OPTIONAL_DOUBLE = new TypeDefBuilder(TypeDef.forName(OptionalDouble.class.getName()))
      .addNewMethod()
      .withNewModifiers().withPublic().withStatic().endModifiers()
      .withName("of")
      .addNewArgument()
      .withName("value")
      .withTypeRef(Types.PRIMITIVE_DOUBLE_REF)
      .endArgument()
      .endMethod()
      .build();

  public static final TypeDef OPTIONAL_LONG = new TypeDefBuilder(TypeDef.forName(OptionalLong.class.getName()))
      .addNewMethod()
      .withNewModifiers().withPublic().withStatic().endModifiers()
      .withName("of")
      .addNewArgument()
      .withName("value")
      .withTypeRef(Types.PRIMITIVE_LONG_REF)
      .endArgument()
      .endMethod()
      .build();

  public static final Function<TypeRef, Boolean> IS_OPTIONAL = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, OPTIONAL, IS_OPTIONAL);
    }
  });

  public static final Function<TypeRef, Boolean> IS_OPTIONAL_INT = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, OPTIONAL_INT, IS_OPTIONAL_INT);
    }
  });

  public static final Function<TypeRef, Boolean> IS_OPTIONAL_DOUBLE = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, OPTIONAL_DOUBLE, IS_OPTIONAL_DOUBLE);
    }
  });

  public static final Function<TypeRef, Boolean> IS_OPTIONAL_LONG = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, OPTIONAL_LONG, IS_OPTIONAL_LONG);
    }
  });

  /**
   * Checks if a {@link TypeRef} is a {@link java.util.Optional}.
   *
   * @param type The type to check.
   * @return True if its a {@link java.util.Optional}.
   */
  public static boolean isOptional(TypeRef type) {
    return IS_OPTIONAL.apply(type);
  }

  /**
   * Checks if a {@link TypeRef} is a {@link java.util.OptionalInt}.
   *
   * @param type The type to check.
   * @return True if its a {@link java.util.OptionalInt}.
   */
  public static boolean isOptionalInt(TypeRef type) {
    return IS_OPTIONAL_INT.apply(type);
  }

  /**
   * Checks if a {@link TypeRef} is a {@link java.util.OptionalDouble}.
   *
   * @param type The type to check.
   * @return True if its a {@link java.util.OptionalDouble}.
   */
  public static boolean isOptionalDouble(TypeRef type) {
    return IS_OPTIONAL_DOUBLE.apply(type);
  }

  /**
   * Checks if a {@link TypeRef} is a {@link java.util.OptionalLong}.
   *
   * @param type The type to check.
   * @return True if its a {@link java.util.OptionalLong}.
   */
  public static boolean isOptionalLong(TypeRef type) {
    return IS_OPTIONAL_LONG.apply(type);
  }
}
