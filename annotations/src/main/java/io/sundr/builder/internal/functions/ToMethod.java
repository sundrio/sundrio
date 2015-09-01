/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.builder.internal.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.StringUtils;

import javax.lang.model.element.Modifier;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.BUILDABLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.Constants.N;
import static io.sundr.builder.Constants.SIMPLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.Constants.VOID;
import static io.sundr.builder.internal.functions.TypeAs.BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.VISITABLE_BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.combine;
import static io.sundr.builder.internal.utils.BuilderUtils.getPropertyBuildableAncestors;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.builder.internal.utils.BuilderUtils.isList;
import static io.sundr.builder.internal.utils.BuilderUtils.isMap;
import static io.sundr.builder.internal.utils.BuilderUtils.isSet;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
import static io.sundr.codegen.utils.StringUtils.singularize;


public enum ToMethod implements Function<JavaProperty, JavaMethod> {

    WITH {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "with" + property.getNameCapitalized();

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
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
                    sb.append("if (" + name + " != null) {this." + name + ".putAll(" + name + ");} return (T) this;");
                } else if (className.contains("List") || className.contains("Set")) {
                    JavaType unwraped = TypeAs.UNWRAP_COLLECTION_OF.apply(property.getType());
                    String addToMethodName = "addTo" + property.getNameCapitalized();
                    sb.append("if (" + name + " != null) {for (" + unwraped.getSimpleName() + " item : " + name + "){this." + addToMethodName + "(item);}} return (T) this;");
                }
                return sb.toString();
            } else if (isBuildable(property)) {
                JavaType builder = combine(UNWRAP_COLLECTION_OF, BUILDER).apply(property.getType());
                String propertyName = property.getName();
                String builderClass = builder.getSimpleName();
                return "if (" + propertyName + "!=null){ this." + propertyName + "= new " + builderClass + "(" + propertyName + "); _visitables.add(this." + propertyName + ");} return (T) this;";
            }
            return "this." + property.getName() + "=" + property.getName() + "; return (T) this;";
        }

    }, WITH_ARRAY {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "with" + property.getNameCapitalized();
            JavaType unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getType());
            String addToMethodName = "addTo" + property.getNameCapitalized();

            JavaProperty arrayProperty = new JavaPropertyBuilder(property)
                    .withType(new JavaTypeBuilder(unwraped)
                            .withArray(true)
                            .build())
                    .withArray(true)
                    .build();

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{arrayProperty})
                    .addToAttributes(BODY, "this." + property.getName() + ".clear(); if (" + property.getName() + " != null) {for (" + unwraped.getSimpleName() + " item :" + property.getName() + "){ this." + addToMethodName + "(item);}} return (T) this;")
                    .build();
        }

    }, GETTER {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String prefix = property.getType().isBoolean() ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            String body = null;

            Set<JavaProperty> descendants = getPropertyBuildableAncestors(property);
            if (isMap(property.getType())) {
                body = "return this." + property.getName() + ";";
            } else if (isBuildable(property)) {
                if (isList(property.getType()) || isSet(property.getType())) {
                    body = "return build(" + property.getName() + ");";
                } else {
                    body = "return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;";
                }
            } else if (descendants.size() > 0) {
                if (isList(property.getType()) || isSet(property.getType())) {
                    String names = StringUtils.join(descendants, new Function<JavaProperty, String>() {
                        @Override
                        public String apply(JavaProperty item) {
                            return "build(" + item.getName() + ")";
                        }
                    }, ", ");
                    body = "return aggregate(" + names + ");";
                } else {
                    //TODO: What are we doing in this case?
                    body = "return this." + property.getName() + ";";
                }
            } else {
                body = "return this." + property.getName() + ";";
            }

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(property.getType())
                    .withArguments(new JavaProperty[]{})
                    .addToAttributes(BODY, body)
                    .build();
        }
    }, GETTER_ARRAY {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String prefix = property.getType().isBoolean() ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            JavaType type = property.getType();
            Boolean isBuildable = isBuildable(type);
            JavaType targetType = isBuildable ? VISITABLE_BUILDER.apply(type) : TypeAs.UNWRAP_ARRAY_OF.apply(type);
            String body = String.format(isBuildable ? BUILDABLE_ARRAY_GETTER_TEXT : SIMPLE_ARRAY_GETTER_TEXT,type.getClassName(), targetType.getSimpleName(), property.getName(), type.getClassName());

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(property.getType())
                    .withArguments(new JavaProperty[]{})
                    .addToAttributes(BODY, body)
                    .build();
        }
    },
    SETTER {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "set" + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(VOID)
                    .withArguments(new JavaProperty[]{property})
                    .addToAttributes(BODY, "this." + property.getName() + "=" + property.getName() + ";")
                    .build();
        }
    },
    ADD_TO_COLLECTION {
        @Override
        public JavaMethod apply(final JavaProperty property) {
            JavaProperty item = new JavaPropertyBuilder(property)
                    .withName("items")
                    .withArray(true)
                    .withType(TypeAs.UNWRAP_COLLECTION_OF.apply(property.getType()))
                    .build();

            String methodName = "addTo" + property.getNameCapitalized();
            String body = "";
            Set<JavaProperty> descendants = getPropertyBuildableAncestors(property);
            if (isBuildable(property)) {
                JavaType builder = combine(UNWRAP_COLLECTION_OF, BUILDER).apply(property.getType());
                String builderClass = builder.getSimpleName();
                body = "for (" + item.getType().getSimpleName() + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.add(builder);this." + property.getName() + ".add(builder);} return (T)this;";
            } else if (descendants.size() > 0) {
                body = "for (" + item.getType().getSimpleName() + " item : items) {" + StringUtils.join(descendants, new Function<JavaProperty, String>() {
                    @Override
                    public String apply(JavaProperty item) {
                        JavaType t = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
                        String addToMethodName = "addTo" + captializeFirst(item.getName());
                        return "if (item instanceof " + t.getSimpleName() + "){" + addToMethodName + "((" + t.getSimpleName() + ")item);}\n";
                    }
                }, " else ");

                body += "} return (T)this;";
            } else {
                body = "for (" + item.getType().getSimpleName() + " item : items) {this." + property.getName() + ".add(item);} return (T)this;";
            }

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{item})
                    .addToAttributes(BODY, body)
                    .build();
        }
    },
    REMOVE_FROM_COLLECTION {
        @Override
        public JavaMethod apply(final JavaProperty property) {
            JavaProperty item = new JavaPropertyBuilder(property)
                    .withName("items")
                    .withArray(true)
                    .withType(TypeAs.UNWRAP_COLLECTION_OF.apply(property.getType()))
                    .build();

            String methodName = "removeFrom" + property.getNameCapitalized();
            String body = "";
            Set<JavaProperty> descendants = getPropertyBuildableAncestors(property);
            if (isBuildable(property)) {
                JavaType builder = combine(UNWRAP_COLLECTION_OF, BUILDER).apply(property.getType());
                String builderClass = builder.getSimpleName();
                body = "for (" + item.getType().getSimpleName() + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.remove(builder);this." + property.getName() + ".remove(builder);} return (T)this;";
            } else if (descendants.size() > 0) {
                body = "for (" + item.getType().getSimpleName() + " item : items) {" + StringUtils.join(descendants, new Function<JavaProperty, String>() {
                    @Override
                    public String apply(JavaProperty item) {
                        JavaType t = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
                        String removeFromMethodName = "removeFrom" + captializeFirst(item.getName());
                        return "if (item instanceof " + t.getSimpleName() + "){" + removeFromMethodName + "((" + t.getSimpleName() + ")item);}\n";
                    }
                }, " else ");

                body += "} return (T)this;";
            } else {
                body = "for (" + item.getType().getSimpleName() + " item : items) {this." + property.getName() + ".remove(item);} return (T)this;";
            }

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{item})
                    .addToAttributes(BODY, body)
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
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{keyProperty, valueProperty})
                    .addToAttributes(BODY, "if(key != null && value != null) {this." + property.getName() + ".put(key, value);} return (T)this;")
                    .build();
        }
    },  REMOVE_FROM_MAP {
        @Override
        public JavaMethod apply(JavaProperty property) {
            JavaType mapType = property.getType();
            JavaType keyType = mapType.getGenericTypes()[0];

            JavaProperty keyProperty = new JavaPropertyBuilder().withName("key").withType(keyType).build();
            String methodName = "removeFrom" + property.getNameCapitalized();
            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName(methodName)
                    .withReturnType(T)
                    .withArguments(new JavaProperty[]{keyProperty})
                    .addToAttributes(BODY, "if(key != null) {this." + property.getName() + ".remove(key);} return (T)this;")
                    .build();
        }
    }, WITH_NEW_NESTED {
        @Override
        public JavaMethod apply(JavaProperty property) {
            //We need to repackage because we are nesting under this class.
            JavaType nestedType = PropertyAs.NESTED_TYPE.apply(property);
            JavaType rewraped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[]{T}).build();
            String prefix = property.getType().isCollection() ? "addNew" : "withNew";
            String methodName = prefix + captializeFirst(property.getType().isCollection()
                    ? singularize(property.getName())
                    : property.getName());

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .addToAttributes(BODY, "return new " + rewraped.getSimpleName() + "();")
                    .build();

        }
    }, WITH_NEW_LIKE_NESTED {
        @Override
        public JavaMethod apply(JavaProperty property) {
            JavaType baseType = TypeAs.UNWRAP_COLLECTION_OF.apply(property.getType());
            JavaType nestedType = PropertyAs.NESTED_TYPE.apply(property);
            //JavaType nestedUnwrapped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[0]).build();

            //We need to repackage because we are nesting under this class.
            JavaType rewraped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[]{T}).build();
            String prefix = property.getType().isCollection() ? "addNew" : "withNew";
            String suffix = "Like";
            String methodName = prefix + captializeFirst(property.getType().isCollection()
                    ? singularize(property.getName())
                    : property.getName()) + suffix;

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .addNewArgument()
                    .withName("item")
                    .withType(baseType)
                    .endArgument()
                    .addToAttributes(BODY, "return new " + rewraped.getSimpleName() + "(item);")
                    .build();

        }
    }, EDIT_NESTED {
        @Override
        public JavaMethod apply(JavaProperty property) {
            JavaType nestedType = PropertyAs.NESTED_TYPE.apply(property);
            //We need to repackage because we are nesting under this class.
            JavaType rewraped = new JavaTypeBuilder(nestedType).withGenericTypes(new JavaType[]{T}).build();
            String prefix = "edit";
            String methodNameBase = captializeFirst(property.getName());
            String methodName = prefix + methodNameBase;

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .addToAttributes(BODY, "return withNew" + methodNameBase + "Like(get" + methodNameBase + "());")
                    .build();

        }
    }, AND {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String builderName = TypeAs.UNWRAP_COLLECTION_OF.apply(property.getType()).getSimpleName("Builder");
            String classPrefix = getClassPrefix(property);
            String prefix = property.getType().isCollection() ? "addTo" : "with";
            String withMethodName = prefix + captializeFirst(property.getName());
            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withReturnType(N)
                    .withName("and")
                    .addToAttributes(BODY, "return (N) " + classPrefix + withMethodName + "(builder.build());")
                    .build();

        }

        private String getClassPrefix(JavaProperty property) {
            Object memberOf = property.getAttributes().get(MEMBER_OF);
            if (memberOf instanceof JavaType) {
                return ((JavaType) memberOf).getClassName() + ".this.";
            } else return "";
        }

    }, END {
        @Override
        public JavaMethod apply(JavaProperty property) {
            String methodName = "end" + captializeFirst(property.getType().isCollection()
                    ? singularize(property.getName())
                    : property.getName());

            return new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withReturnType(N)
                    .withName(methodName)
                    .addToAttributes(BODY, "return and();")
                    .build();
        }
    };

    private static final String BUILDABLE_ARRAY_GETTER_TEXT = loadResourceQuietly(BUILDABLE_ARRAY_GETTER_SNIPPET);
    private static final String SIMPLE_ARRAY_GETTER_TEXT = loadResourceQuietly(SIMPLE_ARRAY_GETTER_SNIPPET);
}
