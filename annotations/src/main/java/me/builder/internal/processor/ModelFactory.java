package me.builder.internal.processor;

import me.builder.annotations.Buildable;
import me.builder.internal.processor.model.Model;
import me.builder.internal.processor.model.Type;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

import static me.builder.internal.processor.ModelUtils.getClassElement;
import static me.builder.internal.processor.ModelUtils.getClassName;
import static me.builder.internal.processor.ModelUtils.getElementMatching;
import static me.builder.internal.processor.ModelUtils.getPackageName;

public class ModelFactory {

    private final Elements elements;
    private final Types types;
    private final Set<TypeElement> buildables;

    public ModelFactory(Elements elements, Types types, Set<TypeElement> buildables) {
        this.elements = elements;
        this.types = types;
        this.buildables = buildables;
    }

    Model create(ExecutableElement constructor) {
        TypeElement buildableElement = elements.getTypeElement(Buildable.class.getName());
        
        TypeElement classElement = getClassElement(constructor);

        Model.Builder builder = new Model.Builder()
                .withType(getPackageName(classElement), getClassName(classElement));

        //Populate constructor parameters
        for (VariableElement variableElement : constructor.getParameters()) {
            Type type = toType(variableElement);
            String name = variableElement.getSimpleName().toString();
            builder = builder.addArgument(type, name);
        }

        //Populate Fields
        for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            Type type = toType(variableElement);
            String name = variableElement.getSimpleName().toString();
            TypeMirror variableType = toTypeMirror(variableElement);
            boolean isBuildable = isBuildable(variableType);
            builder = builder.addField(type, name, isBuildable);
        }

        //Check SuperClass
        TypeMirror superClass = classElement.getSuperclass();
        elements.getTypeElement(superClass.toString());
        TypeElement superClassElement = getElementMatching(buildables, superClass);
        if (superClassElement != null) {
            String superClassPackage = getPackageName(superClassElement);
            String superClassName = getClassName(superClassElement);
            builder = builder.withSuperClass(superClassPackage, superClassName);
        }

        return builder.build();
    }

    private TypeMirror toTypeMirror(VariableElement variableElement) {
        TypeMirror variableType = variableElement.asType();
        if (variableType instanceof ArrayType) {
            variableType = ((ArrayType) variableType).getComponentType();
        }
        return variableType;
    }

    private boolean isBuildable(TypeMirror typeMirror) {
        Element typeElement = types.asElement(typeMirror);
        if (typeElement != null) {
            for (Element el : types.asElement(typeMirror).getEnclosedElements()) {
                if (el.getAnnotation(Buildable.class) != null) {
                    return true;
                }
            }
        }  
        return false;
    }

    private Type toType(VariableElement variableElement) {
        boolean isArrayType = false;
        TypeMirror variableType = variableElement.asType();
        if (variableType instanceof ArrayType) {
            variableType = ((ArrayType) variableType).getComponentType();
            isArrayType = true;
        }
        Element typeElement = types.asElement(variableType);
        String fullName = variableElement.asType().toString();
        String packageName = null;
        String className = null;


        if (typeElement == null) {
            className = fullName;
        } else {
            packageName = elements.getPackageOf(typeElement).toString();
            className = fullName.contains(packageName) ? fullName.substring(packageName.length() + 1) : fullName;
        }

        if (isArrayType && className.endsWith("[]")) {
            className = className.substring(0, className.length() - 2);
        }
        return new Type(packageName, className, isArrayType);
    }
}
