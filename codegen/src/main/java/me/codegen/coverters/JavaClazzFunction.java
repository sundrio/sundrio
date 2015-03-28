package me.codegen.coverters;

import me.Function;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;

import static me.codegen.utils.ModelUtils.getClassName;
import static me.codegen.utils.ModelUtils.getPackageName;

public class JavaClazzFunction implements Function<TypeElement, JavaClazz> {

    private final Function<String, JavaType> toJavaType;
    private final Function<ExecutableElement, JavaMethod> toJavaMethod;
    private final Function<VariableElement, JavaProperty> toJavaProperty;

    public JavaClazzFunction(Types types, Function<String, JavaType> toJavaType, Function<ExecutableElement, JavaMethod> toJavaMethod, Function<VariableElement, JavaProperty> toJavaProperty) {
        this.toJavaType = toJavaType;
        this.toJavaMethod = toJavaMethod;
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaClazz apply(TypeElement classElement) {
        //Check SuperClass
        TypeMirror superClass = classElement.getSuperclass();
        JavaType superClassType = toJavaType.apply(superClass.toString());
        JavaClazzBuilder builder = new JavaClazzBuilder()
                .withType(new JavaTypeBuilder()
                        .withPackageName(getPackageName(classElement))
                        .withClassName(getClassName(classElement))
                        .withSuperClass(superClassType)
                        .build());

        for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
            builder.addToConstructors(toJavaMethod.apply(constructor));
        }

        //Populate Fields
        for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            builder.addToFields(toJavaProperty.apply(variableElement));
        }

        return builder.build();
    }
}
