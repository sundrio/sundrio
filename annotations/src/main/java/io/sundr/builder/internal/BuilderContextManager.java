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

import javax.lang.model.util.Elements;
import java.util.concurrent.atomic.AtomicReference;

public class BuilderContextManager {

    private static final AtomicReference<BuilderContext> context = new AtomicReference<BuilderContext>();

    public static BuilderContext create(Elements elements) {
        BuilderContext ctx = new BuilderContext(elements, Builder.class.getPackage().getName());
        context.set(ctx);
        return ctx;
    }

    public static BuilderContext create(Elements elements, String packageName) {
        BuilderContext ctx = new BuilderContext(elements, packageName);
        if (context.compareAndSet(null, ctx)) {
            return ctx;
        } else {
            BuilderContext existing = context.get();
            if (packageName.equals(existing.getTargetPackage())) {
                return existing;
            }
            throw new IllegalStateException("Cannot use different target package names in a single project. Used:"
                    + packageName + " but package:"
                    + existing.getTargetPackage() + " already exists.");
        }
    }

    public static synchronized BuilderContext getContext() {
        if (context.get() == null) {
            throw new IllegalStateException("Builder context not available.");
        }
        return context.get();
    }
}
