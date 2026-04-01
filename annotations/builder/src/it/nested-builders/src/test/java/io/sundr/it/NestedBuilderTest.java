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

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test nested builder methods for generic types.
 * This verifies that annotation processing correctly generates
 * addNew* and end* methods for buildable collection elements.
 */
public class NestedBuilderTest {

    @Test
    public void testAddNewMatchExpression() {
        // Test the nested builder pattern: addNewMatchExpression()...endMatchExpression()
        LabelSelector selector = new LabelSelectorBuilder()
            .addNewMatchExpression()
                .withKey("app")
                .withOperator("In")
                .withValues(Arrays.asList("frontend", "backend"))
            .endMatchExpression()
            .build();

        assertNotNull(selector);
        assertNotNull(selector.getMatchExpressions());
        assertEquals(1, selector.getMatchExpressions().size());

        MatchExpression expr = selector.getMatchExpressions().get(0);
        assertEquals("app", expr.getKey());
        assertEquals("In", expr.getOperator());
        assertEquals(2, expr.getValues().size());
        assertTrue(expr.getValues().contains("frontend"));
        assertTrue(expr.getValues().contains("backend"));
    }

    @Test
    public void testMultipleNestedBuilders() {
        // Test multiple nested builders
        LabelSelector selector = new LabelSelectorBuilder()
            .addNewMatchExpression()
                .withKey("environment")
                .withOperator("In")
                .withValues(Arrays.asList("production"))
            .endMatchExpression()
            .addNewMatchExpression()
                .withKey("tier")
                .withOperator("NotIn")
                .withValues(Arrays.asList("cache"))
            .endMatchExpression()
            .build();

        assertNotNull(selector);
        assertNotNull(selector.getMatchExpressions());
        assertEquals(2, selector.getMatchExpressions().size());

        MatchExpression expr1 = selector.getMatchExpressions().get(0);
        assertEquals("environment", expr1.getKey());
        assertEquals("In", expr1.getOperator());

        MatchExpression expr2 = selector.getMatchExpressions().get(1);
        assertEquals("tier", expr2.getKey());
        assertEquals("NotIn", expr2.getOperator());
    }

    @Test
    public void testMixedAddMethods() {
        // Test mixing addNew* with addTo* methods
        MatchExpression existingExpr = new MatchExpressionBuilder()
            .withKey("existing")
            .withOperator("Exists")
            .build();

        LabelSelector selector = new LabelSelectorBuilder()
            .addToMatchExpressions(existingExpr)
            .addNewMatchExpression()
                .withKey("new")
                .withOperator("In")
                .withValues(Arrays.asList("value"))
            .endMatchExpression()
            .build();

        assertNotNull(selector);
        assertEquals(2, selector.getMatchExpressions().size());
        assertEquals("existing", selector.getMatchExpressions().get(0).getKey());
        assertEquals("new", selector.getMatchExpressions().get(1).getKey());
    }

    @Test
    public void testNestedBuilderWithEmptyValues() {
        // Test nested builder with null/empty values
        LabelSelector selector = new LabelSelectorBuilder()
            .addNewMatchExpression()
                .withKey("test")
                .withOperator("Exists")
            .endMatchExpression()
            .build();

        assertNotNull(selector);
        assertEquals(1, selector.getMatchExpressions().size());
        assertEquals("test", selector.getMatchExpressions().get(0).getKey());
        assertEquals("Exists", selector.getMatchExpressions().get(0).getOperator());
    }

    @Test
    public void testEditMatchExpression() {
        // Test editing an existing match expression
        LabelSelector original = new LabelSelectorBuilder()
            .addNewMatchExpression()
                .withKey("original")
                .withOperator("In")
                .withValues(Arrays.asList("v1"))
            .endMatchExpression()
            .build();

        LabelSelector modified = new LabelSelectorBuilder(original)
            .editMatchExpression(0)
                .withKey("modified")
                .withOperator("NotIn")
                .withValues(Arrays.asList("v2", "v3"))
            .endMatchExpression()
            .build();

        assertEquals(1, modified.getMatchExpressions().size());
        assertEquals("modified", modified.getMatchExpressions().get(0).getKey());
        assertEquals("NotIn", modified.getMatchExpressions().get(0).getOperator());
        assertEquals(2, modified.getMatchExpressions().get(0).getValues().size());
    }

    @Test
    public void testComplexNestedStructure() {
        // Test a complex nested structure combining multiple features
        LabelSelector selector = new LabelSelectorBuilder()
            .addToMatchLabels("version", "1.0")
            .addToMatchLabels("release", "stable")
            .addNewMatchExpression()
                .withKey("component")
                .withOperator("In")
                .withValues(Arrays.asList("api", "web", "worker"))
            .endMatchExpression()
            .addNewMatchExpression()
                .withKey("deprecated")
                .withOperator("DoesNotExist")
            .endMatchExpression()
            .build();

        assertNotNull(selector.getMatchLabels());
        assertEquals(2, selector.getMatchLabels().size());
        assertEquals("1.0", selector.getMatchLabels().get("version"));
        assertEquals("stable", selector.getMatchLabels().get("release"));

        assertEquals(2, selector.getMatchExpressions().size());
        assertEquals(3, selector.getMatchExpressions().get(0).getValues().size());
    }
}