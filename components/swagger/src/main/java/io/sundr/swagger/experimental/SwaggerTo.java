/*
 *      Copyright 2018 The original authors.
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

package io.sundr.swagger.experimental;

import io.sundr.Function;
import io.sundr.FunctionFactory;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.swagger.models.Model;

public class SwaggerTo {

  private static final Function<Model, TypeDef> INTERNAL_MODEL = new Function<Model, TypeDef>() {

    public TypeDef apply(Model model) {

      return new TypeDefBuilder()
          .withKind(Kind.CLASS)
          .build();
    }
  };

  private static final Function<Model, TypeDef> INTERNAL_SHALLOW_MODEL = new Function<Model, TypeDef>() {

    public TypeDef apply(Model model) {

      return new TypeDefBuilder()
          .withKind(Kind.CLASS)
          .build();
    }
  };

  public static final Function<Model, TypeDef> MODEL = FunctionFactory.cache(INTERNAL_MODEL)
      .withFallback(INTERNAL_SHALLOW_MODEL).withMaximumRecursionLevel(5).withMaximumNestingDepth(5);
}
