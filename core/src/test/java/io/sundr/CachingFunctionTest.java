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

package io.sundr;

import org.junit.Assert;
import org.junit.Test;

public class CachingFunctionTest
{


    private static final Function<String, String> TEST_FUNCTION = CachingFunction.wrap(new Function<String, String>() {
        public String apply(String item) {
            return "R" + TEST_FUNCTION.apply(item);
        }
    }, new Function<String, String>() {
        public String apply(String item) {
            return "overflow";
        }
    }, 10);


    @Test
    public void testCachingWithOverflowProtection() {
        String expectedResult = "RRRRRRRRRRoverflow";
        String result = TEST_FUNCTION.apply("");
        Assert.assertEquals(expectedResult, result);

        result = TEST_FUNCTION.apply("");
        Assert.assertEquals(expectedResult, result);
    }
}
