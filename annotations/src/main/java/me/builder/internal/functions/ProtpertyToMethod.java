package me.builder.internal.functions;

import me.Function;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import static me.codegen.utils.StringUtils.captializeFirst;
import static me.codegen.utils.TypeUtils.newGeneric;

public enum ProtpertyToMethod implements Function<JavaProperty, JavaMethod> {

    WITH {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "with" + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{property})
                    .addToAttributes(BODY, getBody(property))
                    .build();
        }

        private String getBody(JavaProperty property) {
            String name = property.getName();
            JavaType type = property.getType();
            String className = type.getClassName();
            StringBuilder sb = new StringBuilder();
            if (type.isCollection()) {
                sb.append("this." + name + ".clear();");
                if (className.contains("Map")) {
                    sb.append("this." + name + ".putAll(" +name + "); return (T) this;");
                } else if (className.contains("List") || className.contains("Set")) {
                    sb.append("this." +name + ".addAll(" + name + "); return (T) this;");
                }
                return sb.toString();
            }
            return "this." + property.getName() + "=" + property.getName() + "; return (T) this;";
        }

    }, WITH_ARRAY {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "with" + property.getNameCapitalized();
            JavaType unwraped = TypeTo.UNWRAP_ARRAY_OF.apply(property.getType());

            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{property})
                    .addToAttributes(BODY, "this." + property.getName() + ".clear(); for (" + unwraped.getSimpleName() + " item :" + property.getName() + "){ this." + property.getName() + ".add(item);} return (T) this;")
                    .build();
        }

    }, GETTER {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String prefix = property.getType().isBoolean() ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(property.getType())
                    .withArguments(new JavaProperty[]{})
                    .addToAttributes(BODY, "return this." + property.getName() + ";")
                    .build();
        }
    }, GETTER_ARRAY {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String prefix = property.getType().isBoolean() ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(property.getType())
                    .withArguments(new JavaProperty[]{})
                    .addToAttributes(BODY, "return this." + property.getName() + ".toArray(new " + property.getType().getClassName() + "[" + property.getName() + ".size()]);")
                    .build();
        }
    },
    SETTER {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "set" + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(VOID)
                    .withArguments(new JavaProperty[]{property})
                    .addToAttributes(BODY, "this." + property.getName() + "=" + property.getName() + ";")
                    .build();
        }
    },
    ADD_TO_COLLECTION {
        @Override
        public JavaMethod apply(JavaProperty property) {
            JavaProperty item = new JavaPropertyBuilder(property)
                    .withName("item")
                    .withType(TypeTo.UNWRAP_COLLECTION_OF.apply(property.getType()))
                    .build();

            String methodName = "addTo" + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{item})
                    .addToAttributes(BODY, "this." + property.getName() + ".add(item); return (T)this;")
                    .build();
        }
    },
    ADD_TO_MAP {
        @Override
        public JavaMethod apply(JavaProperty property) {
            JavaType mapType = property.getType();
            JavaType keyType = mapType.getGenericTypes()[0];
            JavaType valueType = mapType.getGenericTypes()[1];

            JavaProperty keyProperty = new JavaPropertyBuilder().withName("key").withType(keyType).build();
            JavaProperty valueProperty = new JavaPropertyBuilder().withName("value").withType(valueType).build();
            String methodName = "addTo" + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{keyProperty, valueProperty})
                    .addToAttributes(BODY, "this." + property.getName() + ".put(key, value); return (T)this;")
                    .build();
        }
    }, ADD_NESTED {
        @Override
        public JavaMethod apply(JavaProperty property) {
            //We need to repackage because we are nesting under this class.
            JavaType nestedType = PropretyTo.NESTED.apply(property);
            JavaType rewraped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[]{T}).build();
            return new JavaMethodBuilder()
                    .withReturnType(rewraped)
                    .withName("add" + captializeFirst(property.getName()))
                    .addToAttributes(BODY, "return new " + rewraped.getSimpleName() + "();")
                    .build();

        }
    }, AND {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String builderName = TypeTo.UNWRAP_COLLECTION_OF.apply(property.getType()).getClassName() + "Builder";
            String prefix = property.getType().isCollection() ? "addTo" : "with";
            String withMethodName = prefix + captializeFirst(property.getName());
            return new JavaMethodBuilder()
                    .withReturnType(N)
                    .withName("and")
                    .addToAttributes(BODY, "return (N) " + withMethodName + "(new " + builderName + "(this).build());")
                    .build();

        }
    }, END {
        @Override
        public JavaMethod apply(JavaProperty property) {
            return new JavaMethodBuilder()
                    .withReturnType(N)
                    .withName("end" + captializeFirst(property.getName()))
                    .addToAttributes(BODY, "return and();")
                    .build();

        }
    };

    static final String BODY = "BODY";
    static final JavaType VOID = new JavaTypeBuilder().withClassName("void").build();
    private static final JavaType T = newGeneric("T");
    private static final JavaType N = newGeneric("N");
}
