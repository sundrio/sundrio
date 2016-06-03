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

package utils.matchers;

import io.sundr.codegen.model.ClassRef;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class TypeNamed extends BaseMatcher<ClassRef> {

    private final String expectedValue;

    public TypeNamed(String equalArg) {
        expectedValue = equalArg;
    }

    public boolean matches(Object item) {
        if (item instanceof ClassRef) {
            ClassRef type = (ClassRef) item;
            if (type.getDefinition().toString().equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }

    public void describeTo(Description description) {
        description.appendValue(expectedValue);
    }

    @Factory
    public static Matcher<ClassRef> typeNamed(String operand) {
        return new TypeNamed(operand);
    }
}
