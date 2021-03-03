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

package io.sundr.dsl.internal.element.functions.filter;

import static io.sundr.dsl.internal.utils.GraphUtils.getClasses;
import static io.sundr.dsl.internal.utils.GraphUtils.getKeywords;
import static io.sundr.dsl.internal.utils.GraphUtils.getMethods;

import java.util.Collection;
import java.util.Set;

import io.sundr.codegen.model.TypeDef;

/**
 * Function that determines if a set of visited keywords contains No Classes/Keywords/Methods.
 */
public class RequiresNoneFilter implements TransitionFilter {

  public Boolean apply(Collection<TypeDef> items) {
    Set<String> pathClasses = getClasses(items);
    Set<String> pathKeywords = getKeywords(items);
    Set<String> pathMethods = getMethods(items);

    //methods can't be empty as there will always be at least the entry point
    return pathClasses.isEmpty() && pathKeywords.isEmpty() && pathMethods.size() < 2;

  }

}
