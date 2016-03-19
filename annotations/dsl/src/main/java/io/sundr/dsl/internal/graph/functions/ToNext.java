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

package io.sundr.dsl.internal.graph.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.graph.NodeContext;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.dsl.internal.utils.GraphUtils.exclusion;
import static io.sundr.dsl.internal.utils.GraphUtils.isSatisfied;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEndScope;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEntryPoint;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;

public class ToNext implements Function<NodeContext, Set<JavaClazz>> {

    public Set<JavaClazz> apply(NodeContext context) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>();
        Boolean inScope = !context.getActiveScopes().isEmpty();

        if ((inScope && isEndScope(context.getItem()) || isTerminal(context.getItem()))) {
          return result;
        }

        List<JavaType> currentPath = context.getPathTypes();
        currentPath.add(context.getItem().getType());
        for (JavaClazz candidate : exclusion(context.getAll(), context.getVisitedTypes())) {
            if (!isEntryPoint(candidate)  && isSatisfied(candidate, currentPath)) {
                result.add(candidate);
            }
        }
        result.remove(context.getItem());
        return result;
    }
}
