package me.dsl.internal.processor.matchers;

import me.codegen.model.JavaType;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class TypeNamed extends BaseMatcher<JavaType> {

    private final String expectedValue;

    public TypeNamed(String equalArg) {
        expectedValue = equalArg;
    }


    @Override
    public boolean matches(Object item) {
        if (item instanceof JavaType) {
            JavaType type = (JavaType) item;
            if (type.getSimpleName().equals(expectedValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expectedValue);
    }

    @Factory
    public static Matcher<JavaType> typeNamed(String operand) {
        return new TypeNamed(operand);
    }
}
