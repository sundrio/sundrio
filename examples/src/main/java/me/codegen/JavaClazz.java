package me.codegen;

import me.builder.annotations.Buildable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class JavaClazz extends AttributeSupport implements Clazz<JavaType, JavaProperty> {

    private final JavaType type;
    private final Set<JavaMethod> methods;
    private final Set<JavaMethod> constructors;
    private final Set<JavaProperty> fields;
    private final Set<JavaType> imports;
    private final Set<JavaClazz> nested;

    @Buildable
    public JavaClazz(JavaType type, Set<JavaMethod> constructors, Set<JavaMethod> methods, Set<JavaProperty> fields, Set<JavaType> imports, Map<String, Object> attributes, Set<JavaClazz> nested) {
        super(attributes);
        this.type = type;
        this.methods = methods;
        this.constructors = constructors;
        this.fields = fields;
        this.imports = imports;
        this.nested = nested;
    }

    private Set<JavaType> getReferencedTypes() {
        return Collections.emptySet();
    }

    public Set<JavaMethod> getConstructors() {
        return constructors;
    }

    @Override
    public JavaType getType() {
        return type;
    }

    @Override
    public Set<JavaMethod> getMethods() {
        return methods;
    }

    @Override
    public Set<JavaProperty> getFields() {
        return fields;
    }

    @Override
    public Set<JavaType> getImports() {
        Set<JavaType> result = new CopyOnWriteArraySet<>();
        Set<JavaType> tmp = new CopyOnWriteArraySet<>();
        tmp.addAll(this.imports);
        tmp.addAll(getReferencedTypes());

        for (JavaType t : tmp) {
            if (t.getPackageName() != null && !t.getPackageName().equals(getType().getPackageName()) && !t.getPackageName().equals("java.lang")) {
                result.add(t);
            }
        }
        return result;
    }

    public Set<JavaClazz> getNested() {
        return nested;
    }

    @Override
    public String toString() {
        return type.getClassName();
    }
}
