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

package io.sundr.builder.internal;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import io.sundr.builder.Builder;
import io.sundr.builder.Constants;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.annotations.Inline;

public class BuilderContextManager {

  private BuilderContextManager() {
  }

  private static BuilderContext context = null;

  public synchronized static BuilderContext create(Elements elements, Types types) {
    if (elements == null) {
      throw new NullPointerException("Elements cannot be null!");
    }
    if (types == null) {
      throw new NullPointerException("Types cannot be null!");
    }

    context = new BuilderContext(elements, types, false, Builder.class.getPackage().getName());
    return context;
  }

  /**
   * @deprecated Use {@link #create(Elements, Types, Buildable, Inline...)} or
   *             {@link #create(Elements, Types, ExternalBuildables, Inline...)} instead.
   */
  @Deprecated
  public static BuilderContext create(Elements elements, Types types, Boolean generateBuilderPackage,
      String builderPackage, Inline... inlineables) {
    return create(elements, types, generateBuilderPackage, builderPackage,
        false, Constants.DEFAULT_VALIDATION_PACKAGE, inlineables);
  }

  public static BuilderContext create(Elements elements, Types types, Boolean generateBuilderPackage,
      String builderPackage, String validationPackage, Inline... inlineables) {
    return create(elements, types, generateBuilderPackage, builderPackage,
        false, validationPackage, inlineables);
  }

  public static BuilderContext create(Elements elements, Types types, Boolean generateBuilderPackage,
      String builderPackage, Boolean generateValidationPackage, String validationPackage,
      Inline... inlineables) {
    if (context == null) {
      context = new BuilderContext(elements, types, generateBuilderPackage, builderPackage,
          generateValidationPackage, validationPackage, inlineables);
      return context;
    } else {
      if (!builderPackage.equals(context.getBuilderPackage())) {
        throw new IllegalStateException("Cannot use different builder package names in a single project. Used:"
            + builderPackage + " but package:"
            + context.getBuilderPackage() + " already exists.");
      } else if (!generateBuilderPackage.equals(context.getGenerateBuilderPackage())) {
        throw new IllegalStateException("Cannot use different values for generate builder package in a single project.");
      } else if (!validationPackage.equals(context.getValidationPackage())) {
        throw new IllegalStateException("Cannot use different validation package names in a single project. Used:"
            + validationPackage + " but package:"
            + context.getValidationPackage() + " already exists.");
      } else {
        return context;
      }
    }
  }

  public static BuilderContext create(Elements elements, Types types, Buildable buildable, Inline... inlineables) {
    String builderPackage = effectiveBuilderPackage(buildable.basePackage(), buildable.builderPackage());
    String validationPackage = effectiveValidationPackage(buildable.basePackage(), buildable.validationPackage());
    return create(elements, types, buildable.generateBuilderPackage(), builderPackage,
        buildable.generateValidationPackage(), validationPackage, inlineables);
  }

  public static BuilderContext create(Elements elements, Types types, ExternalBuildables buildable, Inline... inlineables) {
    String builderPackage = effectiveBuilderPackage(buildable.basePackage(), buildable.builderPackage());
    String validationPackage = effectiveValidationPackage(buildable.basePackage(), buildable.validationPackage());
    return create(elements, types, buildable.generateBuilderPackage(), builderPackage,
        buildable.generateValidationPackage(), validationPackage, inlineables);
  }

  private static String effectiveBuilderPackage(String basePackage, String builderPackage) {
    if (!basePackage.isEmpty() && Constants.DEFAULT_BUILDER_PACKAGE.equals(builderPackage)) {
      return basePackage + ".builder";
    }
    return builderPackage;
  }

  private static String effectiveValidationPackage(String basePackage, String validationPackage) {
    if (!basePackage.isEmpty() && Constants.DEFAULT_VALIDATION_PACKAGE.equals(validationPackage)) {
      return basePackage + ".validation";
    }
    return validationPackage;
  }

  public static synchronized BuilderContext getContext() {
    if (context == null) {
      throw new IllegalStateException("Builder context not available.");
    }
    return context;
  }
}
