package me.codegen.coverters;

import me.Function;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static me.codegen.utils.ModelUtils.getFullyQualifiedName;
import static me.codegen.utils.ModelUtils.splitTypes;

public class JavaTypeFunction implements Function<String, JavaType> {

    private final Elements elements;
    private final boolean deep;
    private final JavaTypeFunction shallow;

    public JavaTypeFunction(Elements elements, boolean deep) {
        this.elements = elements;
        this.deep = deep;
        this.shallow = deep ? new JavaTypeFunction(elements, false) : this;
    }

    @Override
    public JavaType apply(String fullName) {
        String packageName = null;
        String className = null;
        List<JavaType> interfaces = new ArrayList<>();
        List<JavaType> genericTypes = new ArrayList<>();

        TypeElement typeElement = elements.getTypeElement(getFullyQualifiedName(fullName));
        if (typeElement == null) {
            className = fullName;
        } else {
            for (TypeMirror interfaceType : typeElement.getInterfaces()) {
                if (deep) {
                    interfaces.add(shallow.apply(interfaceType.toString()));
                }
            }

            TypeMirror typeMirror = typeElement.asType();
            packageName = elements.getPackageOf(typeElement).toString();
            className = fullName.contains(packageName) ? fullName.substring(packageName.length() + 1) : fullName;
        }

        if (fullName.endsWith("[]")) {
            className = className.substring(0, className.indexOf("["));
        }
        if (className.contains("<")) {
            String genericTypeList = fullName.substring(fullName.indexOf("<") + 1, fullName.lastIndexOf(">"));
            for (String genericType : splitTypes(genericTypeList)) {
                JavaType t = apply(genericType);
                genericTypes.add(t);
            }
            className = className.substring(0, className.indexOf("<"));
        }
        JavaType defaultImplementation = null;
        String qualifiedName = packageName + "." + className;
        boolean collection = false;
        if (qualifiedName.equals(Set.class.getCanonicalName())) {
            defaultImplementation = apply(LinkedHashSet.class.getCanonicalName());
            collection = true;
        } else if (qualifiedName.equals(List.class.getCanonicalName())) {
            defaultImplementation = apply(ArrayList.class.getCanonicalName());
            collection = true;
        } else if (qualifiedName.equals(Map.class.getCanonicalName())) {
            defaultImplementation = apply(HashMap.class.getCanonicalName());
            collection = true;
        }

        JavaType type = new JavaTypeBuilder()
                .withKind(JavaKind.CLASS)
                .withPackageName(packageName)
                .withClassName(className)
                .withConcrete(defaultImplementation == null)
                .withCollection(collection)
                .withDefaultImplementation(defaultImplementation)
                .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()])).build();
        return type;
    }
}
