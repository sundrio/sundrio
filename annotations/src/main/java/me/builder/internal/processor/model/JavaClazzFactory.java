package me.builder.internal.processor.model;

import me.builder.annotations.Buildable;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static me.builder.internal.processor.ModelUtils.getClassElement;
import static me.builder.internal.processor.ModelUtils.getClassName;
import static me.builder.internal.processor.ModelUtils.getFullyQualifiedName;
import static me.builder.internal.processor.ModelUtils.getPackageName;
import static me.builder.internal.processor.ModelUtils.splitTypes;

public class JavaClazzFactory {

    public static final String BUILDABLE = "BUILDABLE";

    private final Elements elements;
    private final Types types;
    private final Set<TypeElement> buildables;
    private final Map<String, JavaType> knownTypes = new HashMap<>();
    private final Set<String> visited = new HashSet<>();

    public JavaClazzFactory(Elements elements, Types types, Set<TypeElement> buildables) {
        this.elements = elements;
        this.types = types;
        this.buildables = buildables;
    }

    public JavaClazz create(ExecutableElement constructor) {
        TypeElement classElement = getClassElement(constructor);

        //Check SuperClass
        TypeMirror superClass = classElement.getSuperclass();
        JavaType superClassType = toType(superClass);

        JavaClazzBuilder builder = new JavaClazzBuilder()
                .withType(new JavaTypeBuilder()
                        .withPackageName(getPackageName(classElement))
                        .withClassName(getClassName(classElement))
                        .withSuperClass(superClassType)
                        .build())
                .withConstructor(toJavaMethod(constructor));

        //Populate Fields
        for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            builder.addToFields(toJavaProperty(variableElement));
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

    private JavaMethod toJavaMethod(ExecutableElement executableElement) {
        JavaMethodBuilder constructorBuilder = new JavaMethodBuilder();
        //Populate constructor parameters
        for (VariableElement variableElement : executableElement.getParameters()) {
            constructorBuilder = constructorBuilder.addToArguments(
                    toJavaProperty(variableElement));
        }
        return constructorBuilder.build();
    }

    private JavaProperty toJavaProperty(VariableElement variableElement) {
        String name = variableElement.getSimpleName().toString();
        TypeMirror variableType = toTypeMirror(variableElement);
        boolean isArray = variableElement.asType().toString().endsWith("[]");
        JavaType type = toType(variableElement);
        
        boolean isBuildable = isBuildable(variableType);
        return new JavaPropertyBuilder()
                .withName(name)
                .withType(type)
                .withArray(isArray)
                .addAttributes(BUILDABLE, isBuildable)
                .build();

    }


    private JavaType toType(VariableElement variableElement) {
        return toType(variableElement.asType());
    }

    private JavaType toType(TypeMirror variableType) {
        return toType(variableType.toString(), true);
    }

    private JavaType toType(String fullName, boolean recursive) {
        if (knownTypes.containsKey(fullName)) {
            return knownTypes.get(fullName);
        }
        
        boolean isBuildable = false;
        String packageName = null;
        String className = null;
        List<JavaType> interfaces = new ArrayList<>();
        List<JavaType> genericTypes = new ArrayList<>();

        TypeElement typeElement = elements.getTypeElement(getFullyQualifiedName(fullName));
        if (typeElement == null) {
            className = fullName;
        } else {
            for (TypeMirror interfaceType : typeElement.getInterfaces()) {
                if (recursive) {
                    interfaces.add(toType(interfaceType.toString(), false));
                }
            }
            
            TypeMirror typeMirror = typeElement.asType();
            isBuildable = isBuildable(typeMirror);
            packageName = elements.getPackageOf(typeElement).toString();
            className = fullName.contains(packageName) ? fullName.substring(packageName.length() + 1) : fullName;
        }

        if (fullName.endsWith("[]")) {
            className = className.substring(0, className.indexOf("["));
        }
        if (className.contains("<")) {
            String genericTypeList = fullName.substring(fullName.indexOf("<") + 1, fullName.lastIndexOf(">"));
            for (String genericType : splitTypes(genericTypeList)) {
                JavaType t = toType(genericType, false);
                genericTypes.add(t);
            }
            className = className.substring(0, className.indexOf("<"));
        }
        JavaType defaultImplementation = null;
        String qualifiedName = packageName + "." + className;
        boolean collection = false;
        if (qualifiedName.equals(Set.class.getCanonicalName())) {
            defaultImplementation = toType(LinkedHashSet.class.getCanonicalName(), false);
            collection = true;
        } else if (qualifiedName.equals(List.class.getCanonicalName())) {
            defaultImplementation = toType(ArrayList.class.getCanonicalName(), false);
            collection = true;
        } else if (qualifiedName.equals(Map.class.getCanonicalName())) {
            defaultImplementation = toType(HashMap.class.getCanonicalName(), false);
            collection = true;
        }

        JavaType type = new JavaTypeBuilder()
                .withKind(JavaKind.CLASS)
                .withPackageName(packageName)
                .withClassName(className)
                .addAttributes(BUILDABLE, isBuildable)
                .withConcrete(defaultImplementation == null)
                .withCollection(collection)
                .withDefaultImplementation(defaultImplementation)
                .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()])).build();
        
        knownTypes.put(fullName, type);
        return type;
    }
}
