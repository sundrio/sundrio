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

package io.sundr.builder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BaseFluent<F extends BaseFluent<F>> implements Fluent<F> {

    public static <T> ArrayList<T> build(List<? extends Builder<T>> list) {
        ArrayList<T> result = new ArrayList<>();
        for (Builder<T> builder : list) {
            result.add(builder.build());
        }
        return result;
    }

    public static <T> LinkedHashSet<T> build(Set<? extends Builder<T>> list) {
        LinkedHashSet<T> result = new LinkedHashSet<>();
        for (Builder<T> builder : list) {
            result.add(builder.build());
        }
        return result;
    }
}
