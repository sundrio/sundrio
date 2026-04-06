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

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Verifies that nested builder methods are generated correctly when a @BuildableReference
 * type (LabelSelector) has fields whose element types (MatchExpression) are also buildable
 * and come from a compiled dependency JAR rather than source.
 *
 * Without the fix, LabelSelectorFluent would be missing addNewMatchExpression() and the
 * MatchExpressionsNested inner class, because MatchExpression would not be registered
 * in the buildable repository when processing the consumer module.
 */
public class CrossModuleBuildableRefTest {

    @Test
    public void testNestedBuilderViaBuilableReference() {
        // LabelSelector comes from the library JAR via @BuildableReference.
        // MatchExpression is a field element type that must also be recognized as buildable
        // so that addNewMatchExpression() is generated on LabelSelectorFluent.
        PolicyBinding binding = new PolicyBindingBuilder()
            .withName("test-binding")
            .withNewSelector()
                .addNewMatchExpression()
                    .withKey("environment")
                    .withOperator("In")
                    .withValues(Arrays.asList("production", "staging"))
                .endMatchExpression()
            .endSelector()
            .build();

        assertNotNull(binding);
        assertEquals("test-binding", binding.getName());
        assertNotNull(binding.getSelector());
        assertEquals(1, binding.getSelector().getMatchExpressions().size());

        MatchExpression expr = binding.getSelector().getMatchExpressions().get(0);
        assertEquals("environment", expr.getKey());
        assertEquals("In", expr.getOperator());
        assertEquals(2, expr.getValues().size());
    }

    @Test
    public void testMultipleNestedExpressionsViaBuilableReference() {
        PolicyBinding binding = new PolicyBindingBuilder()
            .withName("multi")
            .withNewSelector()
                .addNewMatchExpression()
                    .withKey("app")
                    .withOperator("In")
                    .withValues(Arrays.asList("frontend"))
                .endMatchExpression()
                .addNewMatchExpression()
                    .withKey("tier")
                    .withOperator("NotIn")
                    .withValues(Arrays.asList("cache"))
                .endMatchExpression()
            .endSelector()
            .build();

        assertEquals(2, binding.getSelector().getMatchExpressions().size());
        assertEquals("app", binding.getSelector().getMatchExpressions().get(0).getKey());
        assertEquals("tier", binding.getSelector().getMatchExpressions().get(1).getKey());
    }
}