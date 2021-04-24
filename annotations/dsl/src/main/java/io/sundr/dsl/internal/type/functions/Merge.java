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

package io.sundr.dsl.internal.type.functions;

import java.util.function.Function;

import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;

public final class Merge {

  private Merge() {
  }

  public static final Function<TypeDef[], TypeDef> TYPES = new Function<TypeDef[], TypeDef>() {

    public TypeDef apply(TypeDef... items) {
      if (items == null || items.length == 0) {
        throw new IllegalArgumentException("Items cannot be empty.");
      } else if (items.length == 1) {
        return items[0];
      } else if (items.length == 2) {
        TypeDefBuilder builder = new TypeDefBuilder(items[0]);

        for (ClassRef classRef : items[1].getExtendsList()) {
          builder = builder.addToExtendsList(classRef);
        }

        for (TypeParamDef type : items[1].getParameters()) {
          if (!items[0].getParameters().contains(type)) {
            builder = builder.addToParameters(type);
          }
        }
        return builder.build();
      } else {
        TypeDef[] rest = new TypeDef[items.length - 1];
        System.arraycopy(items, 1, rest, 0, rest.length);
        return TYPES.apply(new TypeDef[] { items[0], TYPES.apply(rest) });
      }
    }
  };

  public static final Function<TypeDef[], TypeDef> CLASSES = new Function<TypeDef[], TypeDef>() {
    public TypeDef apply(TypeDef... items) {
      if (items == null || items.length == 0) {
        throw new IllegalArgumentException("Items cannot be empty.");
      } else if (items.length == 1) {
        return items[0];
      } else if (items.length == 2) {
        TypeDef mergedType = TYPES.apply(new TypeDef[] { items[0], items[1] });

        TypeDefBuilder builder = new TypeDefBuilder(mergedType);
        for (Method constructor : items[1].getConstructors()) {
          builder = builder.addToConstructors(constructor);
        }

        for (Method method : items[1].getMethods()) {
          builder = builder.addToMethods(method);
        }
        for (Property property : items[1].getProperties()) {
          builder = builder.addToProperties(property);
        }
        return builder.build();
      } else {
        TypeDef[] rest = new TypeDef[items.length - 1];
        System.arraycopy(items, 1, rest, 0, rest.length);
        CLASSES.apply(new TypeDef[] { items[0], CLASSES.apply(rest) });
      }
      return null;

    }
  };
}
