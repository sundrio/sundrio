package me.codegen.model;

import me.codegen.Clazz;

import java.util.Map;
import java.util.Set;

public class JavaClazz extends AttributeSupport implements Clazz<JavaType, JavaProperty> {

    private final JavaType type;
    private final Set<JavaMethod> methods;
    private final Set<JavaMethod> constructors;
    private final Set<JavaProperty> fields;
    private final Set<JavaType> imports;

    public JavaClazz(JavaType type, Set<JavaMethod> constructors, Set<JavaMethod> methods, Set<JavaProperty> fields, Set<JavaType> imports, Map<String, Object> attributes) {
        super(attributes);
        this.type = type;
        this.methods = methods;
        this.constructors = constructors;
        this.fields = fields;
        this.imports = imports;
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
        return imports;
    }

    @Override
    public String toString() {
        return type.getClassName();
    }
}
