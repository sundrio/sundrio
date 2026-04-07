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
import io.sundr.builder.annotations.BuildableReference;

/**
 * Consumer class that references LabelSelector from the library dependency via @BuildableReference.
 * This tests the cross-module scenario where LabelSelector has a List&lt;MatchExpression&gt; field
 * and MatchExpression is also buildable. The consumer module should generate nested builder
 * methods (addNewMatchExpression()) on LabelSelectorFluent even though MatchExpression
 * comes from a compiled JAR rather than source.
 */
@Buildable(editableEnabled = false, validationEnabled = false, generateBuilderPackage = true, lazyCollectionInitEnabled = false, builderPackage = "io.sundrio.it", refs = { @BuildableReference(LabelSelector.class) })
public class PolicyBinding {

    private final String name;
    private final LabelSelector selector;

    public PolicyBinding(String name, LabelSelector selector) {
        this.name = name;
        this.selector = selector;
    }

    public String getName() {
        return name;
    }

    public LabelSelector getSelector() {
        return selector;
    }
}