package me.builder.internal.processor.model;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Model {

    public static class Builder implements me.builder.Builder<Model> {

        private Type type;
        private Type superClass;
        private Set<Property> arguments = new LinkedHashSet<>();
        private Set<Property> fields = new LinkedHashSet<>();
        private Set<Type> nestedTypes = new LinkedHashSet<>();

        public Builder withType(Type type) {
            this.type = type;
            return this;
        }

        public Builder withType(String packageName, String className) {
            this.type = new Type(packageName, className);
            return this;
        }

        public Builder withSuperClass(Type superClass) {
            this.superClass = superClass;
            return this;
        }

        public Builder withSuperClass(String packageName, String className) {
            this.superClass = new Type(packageName, className);
            return this;
        }

        public Builder addNested(Type nested) {
            this.nestedTypes.add(nested);
            return this;
        }

        public Builder addNested(String packageName, String className) {
            this.nestedTypes.add(new Type(packageName, className));
            return this;
        }

        public Builder addArgument(String packageName, String className, String name) {
            this.arguments.add(new Property(new Type(packageName, className), name));
            return this;
        }

        public Builder addArgument(Type type, String name) {
            this.arguments.add(new Property(type, name));
            return this;
        }

        public Builder addField(String packageName, String className, String name) {
            this.fields.add(new Property(new Type(packageName, className), name));
            return this;
        }

        public Builder addField(String packageName, String className, String name, boolean isArrayType) {
            this.fields.add(new Property(new Type(packageName, className, isArrayType), name));
            return this;
        }

        public Builder addField(Type type, String name) {
            this.fields.add(new Property(type, name));
            return this;
        }

        @Override
        public Model build() {
            return new Model(type, superClass, arguments, fields, nestedTypes);
        }
    }

    private final Type type;
    private final Type superClass;
    private final Set<Property> arguments;
    private final Set<Property> fields;
    private final Set<Type> nestedTypes;

    public Model(Type type, Type superClass, Set<Property> arguments, Set<Property> fields, Set<Type> nestedTypes) {
        this.type = type;
        this.superClass = superClass;
        this.arguments = arguments;
        this.fields = fields;
        this.nestedTypes = nestedTypes;
    }

    public Type getType() {
        return type;
    }

    public String getPackageName() {
        return type.getPackageName();
    }

    public String getClassName() {
        return type.getClassName();
    }

    public Set<Property> getFields() {
        return fields;
    }

    public Set<Property> getArguments() {
        return arguments;
    }

    public Type getSuperClass() {
        return superClass;
    }

    public Set<Type> getNestedTypes() {
        return nestedTypes;
    }

}
