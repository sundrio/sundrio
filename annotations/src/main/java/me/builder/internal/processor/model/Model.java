package me.builder.internal.processor.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Model {
    
    public static class Builder implements me.builder.Builder<Model> {
        
        private String packageName;
        private String className;
        private Model superClassModel;
        private Set<Type> fields = new LinkedHashSet<>();
        private Set<Type> constructorArguments = new LinkedHashSet<>();
        
        public Builder withPackageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder withClassName(String className) {
            this.className = className;
            return this;
        }

        public Builder withSuperClassModel(Model superClassModel) {
            this.superClassModel = superClassModel;
            return this;
        }
        
        public Builder addConstructorArgument(Type parameter) {
            this.constructorArguments.add(parameter);
            return this;
        }

        public Builder withConstructorArgument(String type, String name) {
            return addConstructorArgument(new Type(type, name));
        }

        public Builder addField(Type parameter) {
            this.fields.add(parameter);
            return this;
        }

        public Builder withField(String type, String name) {
            return addField(new Type(type, name));
        }

        @Override
        public Model build() {
            return new Model(packageName, className,
                    superClassModel, Collections.unmodifiableSet(fields), 
                    Collections.unmodifiableSet(constructorArguments));
        }
    }
    
    private final String packageName;
    private final String className;
    private final Model superClassModel;
    private final Set<Type> fields;
    private final Set<Type> constructorArguments;

    public Model(String packageName, String className, Model superClassModel, Set<Type> fields, Set<Type> constructorArguments) {
        this.packageName = packageName;
        this.className = className;
        this.superClassModel = superClassModel;
        this.fields = fields;
        this.constructorArguments = constructorArguments;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public Set<Type> getFields() {
        return fields;
    }

    public Set<Type> getConstructorArguments() {
        return constructorArguments;
    }

    public Model getSuperClassModel() {
        return superClassModel;
    }
}
