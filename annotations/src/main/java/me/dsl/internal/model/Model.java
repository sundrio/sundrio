package me.dsl.internal.model;

public class Model {

    public static class Builder implements me.builder.Builder<Model> {

        private Type type;


        public Builder withType(Type type) {
            this.type = type;
            return this;
        }

        public Builder withType(String packageName, String className) {
            this.type = new Type(packageName, className);
            return this;
        }


        @Override
        public Model build() {
            return new Model(type);
        }
    }

    private final Type type;

    public Model(Type type) {
        this.type = type;
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


}
