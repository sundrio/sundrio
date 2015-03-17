package me.builder.internal.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.util.List;

public final class ModelUtils {

    public static TypeElement getClassElement(Element element) {
        if (element instanceof PackageElement) {
            throw new IllegalArgumentException("Invalid element. A package element can't be used to retrieve a class element");
        } else if (element instanceof TypeElement && element.getEnclosingElement() instanceof PackageElement) {
            return (TypeElement) element;
        } else {
            return getClassElement(element.getEnclosingElement());
        }
    }

    public static PackageElement getPackageElement(Element element) {
        if (element instanceof PackageElement) {
            return (PackageElement) element;
        } else {
            return getPackageElement(element.getEnclosingElement());
        }
    }

    public static String getClassName(Element element) {
        TypeElement typeElement = getClassElement(element);
        String qualifiedName = typeElement.getQualifiedName().toString();
        String packageName = getPackageName(typeElement);
        if (packageName.isEmpty()) {
            return qualifiedName;
        }
        String result = qualifiedName.substring(packageName.length() + 1);
        return result;
    }

    public static String getPackageName(Element element) {
        return getPackageElement(element).getQualifiedName().toString();
    }

    public static List<VariableElement> getFields(TypeElement element) {
        return ElementFilter.fieldsIn(element.getEnclosedElements());
    }

     static final TypeElement getElementMatching(Iterable<TypeElement> typeElements, TypeMirror type) {
        for (TypeElement typeElement : typeElements) {
            if (typeElement.asType().equals(type)) {
                return typeElement;
            }
        }
        return null;
    }
}
