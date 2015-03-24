package me.dsl.internal.model;

public class Type {

    private final String packageName;
    private final String className;
    private final boolean arrayType;
    private final Type[] genericTypes;

    public Type(String packageName, String className) {
        this(packageName, className, false);
    }

    public Type(String packageName, String className, boolean arrayType) {
        this(packageName, className, arrayType, new Type[0]);
    }

    public Type(String packageName, String className, boolean arrayType, Type[] genericTypes) {
        this.packageName = packageName;
        this.className = className;
        this.arrayType = arrayType;
        this.genericTypes = genericTypes;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public boolean isArrayType() {
        return arrayType;
    }

    public Type[] getGenericTypes() {
        return genericTypes;
    }

    public String getSimpleName() {
        StringBuilder sb = new StringBuilder();
        sb.append(className);
        if (genericTypes.length > 0) {
            sb.append("<");
            for (int i = 0; i < genericTypes.length; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                sb.append(genericTypes[i].getSimpleName());
            }
            sb.append(">");
        }
        if (arrayType) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public boolean isBoolean() {
        return (("boolean".equals(className)) ||
                ("Boolean".equals(className)));
    }
}
