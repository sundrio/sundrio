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

import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BuildableRepository {

    private final Set<TypeElement> buildables = new LinkedHashSet<>();

    public void register(TypeElement buildable) {
        buildables.add(buildable);
    }

    public Set<TypeElement> getBuildables() {
        return Collections.unmodifiableSet(buildables);
    }

    public boolean isBuildable(TypeElement buildable) {
        return buildable != null && buildables.contains(buildable.getQualifiedName().toString());
    }

    public void clear() {
        buildables.clear();
    }
}
