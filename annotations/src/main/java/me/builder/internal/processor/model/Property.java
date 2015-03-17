package me.builder.internal.processor.model;

public class Property {
    
    private final Type type;
    private final String name;

    public Property(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
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
        sb.append(name.substring(0,1).toUpperCase());
        sb.append(name.substring(1));
        return sb.toString();
    }

    public String getAddSetter() {
        StringBuilder sb = new StringBuilder();
        sb.append("add");
        sb.append(type.getClassName().substring(0, 1).toUpperCase());
        sb.append(type.getClassName().substring(1));
        return sb.toString();
    }
}
