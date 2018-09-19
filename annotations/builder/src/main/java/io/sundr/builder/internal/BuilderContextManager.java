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

import io.sundr.builder.Builder;
import io.sundr.builder.annotations.Inline;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class BuilderContextManager {
    
    private BuilderContextManager() {}

    private static BuilderContext context = null;

    public synchronized static BuilderContext create(Elements elements, Types types) {
        context = new BuilderContext(elements, types, false, false, false, Builder.class.getPackage().getName());
        return context;
    }

    public static BuilderContext create(Elements elements, Types types, Boolean validationEnabled, Boolean lazyCollectionsEnabled, Boolean generateBuilderPackage, String packageName, Inline... inlineables) {
        if (context == null) {
            context = new BuilderContext(elements, types, generateBuilderPackage, validationEnabled, lazyCollectionsEnabled, packageName, inlineables);
            return context;
        } else {
            if (!packageName.equals(context.getBuilderPackage())) {
                throw new IllegalStateException("Cannot use different builder package names in a single project. Used:"
                        + packageName + " but package:"
                        + context.getGenerateBuilderPackage() + " already exists.");
            } else if (!generateBuilderPackage.equals(context.getGenerateBuilderPackage())) {
                throw new IllegalStateException("Cannot use different values for generate builder package in a single project.");
            } else {
                return context;
            }
        }
    }

    public static synchronized BuilderContext getContext() {
        if (context== null) {
            throw new IllegalStateException("Builder context not available.");
        }
        return context;
    }
}
