/*
 *      Copyright 2026 The original authors.
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

package io.sundr.it;

import io.sundr.builder.annotations.Buildable;
import java.util.List;

@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = true, lazyCollectionInitEnabled = false, builderPackage = "io.sundrio.it")
public class MatchExpression {

    private final String key;
    private final String operator;
    private final List<String> values;

    public MatchExpression(String key, String operator, List<String> values) {
        this.key = key;
        this.operator = operator;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public String getOperator() {
        return operator;
    }

    public List<String> getValues() {
        return values;
    }
}