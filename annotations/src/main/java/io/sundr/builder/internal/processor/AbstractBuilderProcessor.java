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

package io.sundr.builder.internal.processor;

import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.processor.JavaGeneratingProcessor;

public abstract class AbstractBuilderProcessor extends JavaGeneratingProcessor {

    public static final String DEFAULT_INTERFACE_TEMPLATE_LOCATION = "templates/builder/interface.vm";
    public static final String DEFAULT_FLUENT_TEMPLATE_LOCATION = "templates/builder/fluent.vm";
    public static final String DEFAULT_BUILDER_TEMPLATE_LOCATION = "templates/builder/builder.vm";
    public static final String VALIDATING_BUILDER_TEMPLATE_LOCATION = "templates/builder/validating-builder.vm";

    void generateLocalDependenciesIfNeeded() {
        BuilderContext context = BuilderContextManager.getContext();
        if (!Constants.DEFAULT_BUILDER_PACKAGE.equals(context.getTargetPackage())) {
            try {
                generateFromClazz(context.getBuilderInterface(),
                        DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );

                generateFromClazz(context.getFluentInterface(),
                        DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );

                generateFromClazz(context.getNestedInterface(),
                        DEFAULT_INTERFACE_TEMPLATE_LOCATION
                );
            } catch (Exception e) {
                //
            }
        }
    }

    /**
     * Selects a builder template based on the criteria.
     * @param validationEnabled Flag that indicates if validationEnabled is enabled.
     * @return
     */
    String selectBuilderTemplate(boolean validationEnabled) {
        if (validationEnabled) {
            return VALIDATING_BUILDER_TEMPLATE_LOCATION;
        } else {
            return DEFAULT_BUILDER_TEMPLATE_LOCATION;
        }
    }
}
