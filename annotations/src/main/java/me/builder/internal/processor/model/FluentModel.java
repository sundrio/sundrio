package me.builder.internal.processor.model;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Model wrapper specific to Fluent class generator*
 */
public class FluentModel extends Model {

    public static FluentModel wrap(Model model) {
        return new FluentModel(model.getType(), model.getSuperClass(), model.getArguments(), model.getFields(), model.getNestedTypes());
    }

    private FluentModel(Type type, Type superClass, Set<Property> arguments, Set<Property> fields, Set<Type> nestedTypes) {
        super(type, superClass, arguments, fields, nestedTypes);
    }

    public Set<Type> getReferencedTypes() {
        Set<Type> types = new LinkedHashSet<>();
        for (Property field : getFields()) {
            types.add(field.getType());
            types.addAll(Arrays.asList(field.getType().getGenericTypes()));
        }

        for (Property field : getArguments()) {
            types.add(field.getType());
            types.addAll(Arrays.asList(field.getType().getGenericTypes()));
        }
        if (getSuperClass() != null) {
            types.add(new Type(getSuperClass().getPackageName(), getSuperClass().getClassName() + "Fluent"));
        }
        return types;
    }

    public Set<String> getImports() {
        Set<String> imports = new LinkedHashSet<>();
        for (Type t : getReferencedTypes()) {
            if (t.isArrayType()) {
                imports.add("java.util.List");
                imports.add("java.util.ArrayList");
                        
            }
            if (t.getPackageName() != null && !t.getPackageName().equals(getType().getPackageName())) {
                imports.add(t.getPackageName() + "." + t.getClassName());
            }
        }
        return imports;
    }
}
