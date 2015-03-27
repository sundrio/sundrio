package me.codegen.coverters;

import me.Converter;
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

public class JavaClazzConverter implements Converter<TypeElement, JavaClazz> {

    private final Converter<String, JavaType> toJavaType;
    private final Converter<ExecutableElement, JavaMethod> toJavaMethod;
    private final Converter<VariableElement, JavaProperty> toJavaProperty;

    public JavaClazzConverter(Types types, Converter<String, JavaType> toJavaType, Converter<ExecutableElement, JavaMethod> toJavaMethod, Converter<VariableElement, JavaProperty> toJavaProperty) {
        this.toJavaType = toJavaType;
        this.toJavaMethod = toJavaMethod;
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaClazz covert(TypeElement classElement) {
        //Check SuperClass
        TypeMirror superClass = classElement.getSuperclass();
        JavaType superClassType = toJavaType.covert(superClass.toString());
        JavaClazzBuilder builder = new JavaClazzBuilder()
                .withType(new JavaTypeBuilder()
                        .withPackageName(getPackageName(classElement))
                        .withClassName(getClassName(classElement))
                        .withSuperClass(superClassType)
                        .build());

        for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
            builder.addToConstructors(toJavaMethod.covert(constructor));
        }

        //Populate Fields
        for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            builder.addToFields(toJavaProperty.covert(variableElement));
        }

        return builder.build();
    }
}
