package me.builder.internal.processor.model;

public class Property {
    
    private final Type type;
    private final String name;
    private final boolean buildable;

    public Property(Type type, String name) {
        this(type, name, false);
    }
    
    public Property(Type type, String name, boolean buildable) {
        this.type = type;
        this.name = name;
        this.buildable = buildable;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public String getGetter() {
        StringBuilder sb = new StringBuilder();
        if (type.isBoolean()) {
            sb.append("is");
        } else {
            sb.append("get");
        }
        sb.append(name.substring(0,1).toUpperCase());
        sb.append(name.substring(1));
        return sb.toString();
    }

    public String getWithSetter() {
        StringBuilder sb = new StringBuilder();
        sb.append("with");
        sb.append(name.substring(0, 1).toUpperCase());
        sb.append(name.substring(1));
        return sb.toString();
    }

    public String getAddSetter() {
        StringBuilder sb = new StringBuilder();
        sb.append("add");
        sb.append(name.substring(0, 1).toUpperCase());
        sb.append(name.substring(1));
        return sb.toString();
    }

    public String getNestedClassName() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0, 1).toUpperCase());
        sb.append(name.substring(1));
        sb.append("Nested");
        return sb.toString();
    }
}
