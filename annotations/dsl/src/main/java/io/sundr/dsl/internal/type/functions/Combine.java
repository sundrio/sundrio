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

package io.sundr.dsl.internal.type.functions;

import io.sundr.Function;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;
import io.sundr.dsl.internal.utils.TypeDefUtils;
import io.sundr.dsl.internal.visitors.TypeParamDefColletor;
import io.sundr.dsl.internal.visitors.TypeParamRefColletor;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.INTERFACE_SUFFIX;
import static io.sundr.dsl.internal.Constants.IS_COMPOSITE;
import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.REMOVABLE_PREFIXES;
import static io.sundr.dsl.internal.Constants.TERMINATING_TYPES;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.Constants.TRANSPARENT_REF;
import static io.sundr.dsl.internal.utils.TypeDefUtils.getTerminatingTypes;
import static io.sundr.dsl.internal.utils.TypeDefUtils.toInterfaceName;

public class Combine {


    public static Function<Collection<ClassRef>, TypeDef> TYPEREFS = new Function<Collection<ClassRef>, TypeDef>() {
        public TypeDef apply(Collection<ClassRef> alternatives) {
            String key = createKeyForClasses(alternatives);
            if (combinations.containsKey(key)) {
                return combinations.get(key);
            }

            Set<TypeParamDef> parameters = new LinkedHashSet<TypeParamDef>();
            List<ClassRef> interfaces = new ArrayList<ClassRef>();
            Set<TypeDef> terminatingTypes = new LinkedHashSet<TypeDef>();

            ClassRef fallback = null;
            for (ClassRef alternative : alternatives) {
                if (!canBeExcluded(alternative, alternatives)) {
                    interfaces.add(alternative);
                    terminatingTypes.addAll(getTerminatingTypes(alternative));

                    for (TypeParamDef candidate : extractParameters(alternative)) {
                        if (!candidate.equals(TRANSPARENT)) {
                            parameters.add(candidate);
                        }
                    }

                } else {
                    if (fallback == null) {
                        fallback = alternative;
                    } else if (canBeExcluded(fallback, Arrays.asList(alternative))) {
                        fallback = alternative;
                    }
                }
            }

            if (interfaces.isEmpty()) {
                interfaces.add(fallback);
                terminatingTypes.addAll(getTerminatingTypes(fallback));
                for (TypeParamDef candidate : extractParameters(fallback.getDefinition())) {
                    if (!candidate.equals(TRANSPARENT)) {
                        parameters.add(candidate);
                    }
                }
            }
            String className = CLASSREFS_TO_NAME.apply(interfaces);
            String packageName = CLASSREFS_TO_PACKAGE.apply(interfaces);

            TypeDef combination = new TypeDefBuilder()
                    .withKind(Kind.INTERFACE)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(className)
                    .withPackageName(packageName)
                    .withExtendsList(interfaces)
                    .withParameters(parameters.toArray(new TypeParamDef[parameters.size()]))
                    .addToAttributes(ORIGINAL_RETURN_TYPE, TRANSPARENT_REF)
                    .addToAttributes(TERMINATING_TYPES, terminatingTypes)
                    .addToAttributes(IS_TERMINAL, false)
                    .addToAttributes(IS_COMPOSITE, false)
                    .build();

            combinations.put(key, combination);
            return combination;
        }
    };

    public static Function<Collection<TypeDef>, TypeDef> TYPEDEFS = new Function<Collection<TypeDef>, TypeDef>() {

        public TypeDef apply(Collection<TypeDef> alternatives) {
            String key = createKeyForTypes(alternatives);
            if (combinations.containsKey(key)) {
                return combinations.get(key);
            }

            Set<TypeParamDef> parameters = new LinkedHashSet<TypeParamDef>();
            List<ClassRef> interfaces = new ArrayList<ClassRef>();
            Set<TypeDef> terminatingTypes = new LinkedHashSet<TypeDef>();

            TypeDef fallback = null;
            for (TypeDef alternative : alternatives) {
                if (!canBeExcluded(alternative, alternatives)) {
                    interfaces.add(alternative.toInternalReference());
                    terminatingTypes.addAll(getTerminatingTypes(alternative));

                    for (TypeParamDef candidate : extractParameters(alternative)) {
                        if (!candidate.equals(TRANSPARENT)) {
                            parameters.add(candidate);
                        }
                    }

                } else {
                    if (fallback == null) {
                        fallback = alternative;
                    } else if (canBeExcluded(fallback, Arrays.asList(alternative))) {
                        fallback = alternative;
                    }
                }
            }

            if (interfaces.isEmpty()) {
                interfaces.add(fallback.toInternalReference());
                terminatingTypes.addAll(getTerminatingTypes(fallback));
                for (TypeParamDef candidate : extractParameters(fallback)) {
                    if (!candidate.equals(TRANSPARENT)) {
                        parameters.add(candidate);
                    }
                }
            }
            String className = CLASSREFS_TO_NAME.apply(interfaces);
            String packageName = CLASSREFS_TO_PACKAGE.apply(interfaces);

            TypeDef combination = new TypeDefBuilder()
                    .withKind(Kind.INTERFACE)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(className)
                    .withPackageName(packageName)
                    .withExtendsList(interfaces)
                    .withParameters(parameters.toArray(new TypeParamDef[parameters.size()]))
                    .addToAttributes(ORIGINAL_RETURN_TYPE, TRANSPARENT_REF)
                    .addToAttributes(TERMINATING_TYPES, terminatingTypes)
                    .addToAttributes(IS_TERMINAL, false)
                    .addToAttributes(IS_COMPOSITE, false)
                    .build();

            combinations.put(key, combination);
            return combination;
        }
    };

    public static final Function<Collection<TypeDef>, String> TYPEDEFS_TO_NAME = new Function<Collection<TypeDef>, String>() {
        public String apply(Collection<TypeDef> types) {
            final Function<TypeDef, String> toString = new Function<TypeDef, String>() {
                public String apply(TypeDef item) {
                    return stripSuffix(item.getName());
                }
            };

            final String prefix = StringUtils.getPrefix(types, toString);

            return  toInterfaceName(prefix + compact(StringUtils.join(types, new Function<TypeDef, String>() {
                public String apply(TypeDef item) {
                    String str = stripPrefix(stripSuffix(item.getName()));
                    if (str.length() > prefix.length()) {
                        return str.substring(prefix.length());
                    } else {
                        return str;
                    }
                }
            }, "")));
        }
    };

    public static final Function<Collection<ClassRef>, String> CLASSREFS_TO_NAME = new Function<Collection<ClassRef>, String>() {
        public String apply(Collection<ClassRef> types) {
            final Function<ClassRef, String> toString = new Function<ClassRef, String>() {
                public String apply(ClassRef item) {
                    return stripSuffix(item.getDefinition().getName());
                }
            };

            final String prefix = StringUtils.getPrefix(types, toString);

            return  toInterfaceName(prefix + compact(StringUtils.join(types, new Function<ClassRef, String>() {
                public String apply(ClassRef item) {
                    String str = stripPrefix(stripSuffix(item.getDefinition().getName()));
                    if (str.length() > prefix.length()) {
                        return str.substring(prefix.length());
                    } else {
                        return str;
                    }
                }
            }, "")));
        }
    };

    public static final Function<List<ClassRef>, String> CLASSREFS_TO_PACKAGE = new Function<List<ClassRef>,String>() {

        public String apply(List<ClassRef> types) {
            Iterator<ClassRef> iterator = types.iterator();
            if (iterator.hasNext()) {
                return  iterator.next().getDefinition().getPackageName();
            }
            return "";
        }
    };



    /**
     * Remove repeating strings that are appearing in the name.
     * This is done by splitting words (camel case) and using each word once.
     * @param name  The name to compact.
     * @return      The compact name.
     */
    private static final String compact(String name) {
        Set<String> parts = new LinkedHashSet<String>();
        for (String part : name.split(SPLITTER_REGEX)) {
            parts.add(part);
        }
        return StringUtils.join(parts,"");
    }

    private static boolean canBeExcluded(TypeDef candidate, Iterable<TypeDef> provided) {
        Set<TypeDef> allOther = new LinkedHashSet<TypeDef>();
        for (TypeDef c : provided) {
            if (!c.equals(candidate)) {
                allOther.add(c);
            }
        }

        Set<ClassRef> allProvided = TypeDefUtils.extractInterfacesFromTypes(allOther);
        for (ClassRef type : TypeDefUtils.extractInterfacesFromType(candidate)) {
            if (!allProvided.contains(type)) {
                return false;
            }
        }
        return true;
    }


    private static boolean canBeExcluded(ClassRef candidate, Iterable<ClassRef> provided) {
        Set<ClassRef> allOther = new LinkedHashSet<ClassRef>();
        for (ClassRef c : provided) {
            if (!c.equals(candidate)) {
                allOther.add(c);
            }
        }

        Set<ClassRef> allProvided = TypeDefUtils.extractInterfacesFromClassRefs(allOther);
        for (ClassRef type : TypeDefUtils.extractInterfacesFromClassRef(candidate)) {
            if (!allProvided.contains(type)) {
                return false;
            }
        }
        return true;
    }

    private static final Set<TypeParamDef> extractParameters(TypeDef typeDef) {
        final Set<TypeParamDef> result = new LinkedHashSet<TypeParamDef>();
        final Set<TypeParamRef> refs = new LinkedHashSet<TypeParamRef>();

        TypeDef ignored = new TypeDefBuilder(typeDef).accept(new TypeParamDefColletor(result)).accept(new TypeParamRefColletor(refs)).build();
        for (TypeParamRef typeParamRef : refs) {
            result.add(new TypeParamDefBuilder().withName(typeParamRef.getName()).withAttributes(typeParamRef.getAttributes()).build());
        }

        return result;
    }

    private static final Set<TypeParamDef> extractParameters(ClassRef classRef) {
        final Set<TypeParamDef> result = new LinkedHashSet<TypeParamDef>();
        final Set<TypeParamRef> refs = new LinkedHashSet<TypeParamRef>();

        ClassRef ignored = new ClassRefBuilder(classRef).accept(new TypeParamDefColletor(result)).accept(new TypeParamRefColletor(refs)).build();
        for (TypeParamRef typeParamRef : refs) {
            result.add(new TypeParamDefBuilder().withName(typeParamRef.getName()).withAttributes(typeParamRef.getAttributes()).build());
        }

        return result;
    }

    private static final String stripPrefix(String str) {
        for (String prefix : REMOVABLE_PREFIXES) {
            if (str.startsWith(prefix)) {
                return str.substring(prefix.length());
            }
        }
        return str;
    }

    private static final String stripSuffix(String str) {
        if (str.endsWith(INTERFACE_SUFFIX)) {
            return str.substring(0, str.length() - INTERFACE_SUFFIX.length());
        }
        return str;
    }

    private static String createKeyForClasses(Collection<ClassRef> alternatives) {
        List<TypeDef> typeDefs = new LinkedList<TypeDef>();
        for (ClassRef classRef : alternatives) {
            typeDefs.add(classRef.getDefinition());
        }
        return createKeyForTypes(typeDefs);
    }

    private static String createKeyForTypes(Collection<TypeDef> alternatives) {
        List<TypeDef> clazzes = new LinkedList<TypeDef>(alternatives);
        Collections.sort(clazzes, new Comparator<TypeDef>() {
            public int compare(TypeDef left, TypeDef right) {
                return left.getFullyQualifiedName().compareTo(right.getFullyQualifiedName());
            }
        });
        return StringUtils.join(clazzes, new Function<TypeDef, String>() {
            public String apply(TypeDef item) {
                return item.getFullyQualifiedName();
            }
        }, "#");
    }

    private static Map<String, TypeDef> combinations = new HashMap<String, TypeDef>();

    private static final String SPLITTER_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";
}
