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
import java.util.Map;

@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = true, lazyCollectionInitEnabled = false, builderPackage = "io.sundrio.it")
public class LabelSelector {

    private final Map<String, String> matchLabels;
    private final List<MatchExpression> matchExpressions;

    public LabelSelector(Map<String, String> matchLabels, List<MatchExpression> matchExpressions) {
        this.matchLabels = matchLabels;
        this.matchExpressions = matchExpressions;
    }

    public Map<String, String> getMatchLabels() {
        return matchLabels;
    }

    public List<MatchExpression> getMatchExpressions() {
        return matchExpressions;
    }
}