package me.builder.internal.functions;

import me.Function;
import me.codegen.model.AttributeSupport;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public enum ClazzTo implements Function<JavaClazz, JavaClazz> {

    FLUENT {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> methods = new LinkedHashSet<>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<>();
            Set<JavaProperty> properties = new LinkedHashSet<>();

            for (JavaProperty property : item.getFields()) {
                boolean buildable = (boolean) property.getType().getAttributes().get(BUILDABLE);
                if (property.isArray()) {
                    methods.add(ProtpertyToMethod.WITH_ARRAY.apply(property));
                    methods.add(ProtpertyToMethod.GETTER_ARRAY.apply(property));
                    properties.add(arrayAsList(property, buildable));
                } else {
                    properties.add(new JavaPropertyBuilder(property).addToAttributes(BUILDABLE, buildable).build());
                    methods.add(ProtpertyToMethod.GETTER.apply(property));
                    methods.add(ProtpertyToMethod.WITH.apply(property));
                }
            }

            for (JavaProperty property : properties) {
                if (property.getType().isCollection()) {
                    if (property.getType().getClassName().contains("Set") || property.getType().getClassName().contains("List")) {
                        methods.add(ProtpertyToMethod.ADD_TO_COLLECTION.apply(property));
                    } else if (property.getType().getClassName().contains("Map")) {
                        methods.add(ProtpertyToMethod.ADD_TO_MAP.apply(property));
                    }
                }

                if (isBuildable(property)) {
                    methods.add(ProtpertyToMethod.ADD_NESTED.apply(property));
                    nestedClazzes.add(toNestedClazz(property));
                }
            }

            return new JavaClazzBuilder(item)
                    .withType(TypeTo.FLUENT.apply(item.getType()))
                    .withFields(properties)
                    .withNested(nestedClazzes)
                    .withMethods(methods)
                    .build();
        }
    }, BUILDER {
        @Override
        public JavaClazz apply(JavaClazz item) {
            return new JavaClazzBuilder(item)
                    .withType(TypeTo.BUILDER.apply(item.getType()))
                    .addToAttributes(BUILDS, item.getType())
                    .build();
        }

    };

    private static final String BUILDABLE = "BUILDABLE";
    private static final String BUILDS = "BUILDS";

    private static JavaProperty arrayAsList(JavaProperty property, boolean buildable) {
        return new JavaPropertyBuilder(property)
                .withArray(false)
                .withType(TypeTo.ARRAY_AS_LIST.apply(property.getType()))
                .addToAttributes(BUILDABLE, buildable)
                .build();
    }

    private static JavaClazz toNestedClazz(JavaProperty property) {
        JavaType nestedType = PropretyTo.NESTED.apply(property);
        Set<JavaMethod> nestedMethods = new HashSet<>();
        nestedMethods.add(ProtpertyToMethod.AND.apply(property));
        nestedMethods.add(ProtpertyToMethod.END.apply(property));
        return createNestedClazz(nestedType, TypeTo.UNWRAP_COLLECTION_OF.apply(property.getType()), nestedMethods);
    }

    private static JavaClazz createNestedClazz(JavaType nestedType, JavaType originalType, Set<JavaMethod> methods) {
        Set<JavaProperty> properties = new HashSet<>();

        properties.add(new JavaPropertyBuilder()
                .withName("builder")
                .withType(TypeTo.SHALLOW_BUILDER.apply(originalType)).build());

        return new JavaClazzBuilder()
                .withType(nestedType)
                .withFields(properties)
                .withMethods(methods)
                .build();
    }

    /**
     * Checks if {@link me.codegen.model.JavaType} has the BUILDABLE attribute set to true.
     *
     * @param item The type to check.
     * @return
     */
    private static boolean isBuildable(AttributeSupport item) {
        if (item == null) {
            return false;
        } else if (item.getAttributes().containsKey(BUILDABLE)) {
            return (Boolean) item.getAttributes().get(BUILDABLE);
        }
        return false;
    }
}
