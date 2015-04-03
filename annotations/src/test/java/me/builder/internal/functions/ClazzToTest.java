package me.builder.internal.functions;

import me.Function;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaType;
import org.junit.Test;

import static me.codegen.utils.StringUtils.join;

public class ClazzToTest {

    @Test
    public void testToFluent() {

        JavaClazz clazz = new JavaClazzBuilder()
                .addType()
                    .withClassName("MyClass")
                    .withPackageName(getClass().getPackage().getName())
                .endType().build();
        
        JavaClazz result = ClazzTo.FLUENT.apply(clazz);
        System.out.println(JavaTypeToString.INSTANCE.apply(result.getType()));
    }

    //Enum Singleton
    private enum JavaTypeToString implements Function<JavaType, String> {
        INSTANCE;
        @Override
        public String apply(JavaType item) {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getClassName());
            if (item.getKind() == JavaKind.GENERIC && item.getSuperClass() != null) {
                sb.append(" extends " + apply(item.getSuperClass()));
            }
            if (item.getGenericTypes() != null && item.getGenericTypes().length > 0) {
                sb.append("<").append(join(item.getGenericTypes(), JavaTypeToString.INSTANCE, ",")).append(">");
            }
            return sb.toString();
        }
    }
}