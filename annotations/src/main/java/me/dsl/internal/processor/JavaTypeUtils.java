package me.dsl.internal.processor;

import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;
import me.codegen.utils.ModelUtils;
import me.dsl.annotations.TargetName;
import me.dsl.annotations.Terminal;

import javax.lang.model.element.ExecutableElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static me.codegen.utils.StringUtils.captializeFirst;

public class JavaTypeUtils {

    static final String[] GENERIC_NAMES = {"X", "Y", "Z"};
    
    static final String INTERFACE_SUFFIX = "Interface";
    static final String ORIGINAL_RETURN_TYPE = "ORIGINAL_RETURN_TYPE";
    static final String TERMINATING_TYPES = "TERMINATING_TYPES";
    static final String IS_TERMINAL = "IS_TERMINAL";
    static final String IS_COMPOSITE = "IS_COMPOSITE";
    static final String COMBINATION_OF = "COMBINATION_OF";
    static final JavaType VOID = new JavaTypeBuilder().withClassName("Void").build();

    static final Map<JavaType, JavaType> GENERIC_MAPPINGS = new HashMap<>();
    static  {
        GENERIC_MAPPINGS.put(VOID, new JavaTypeBuilder().withClassName("T").build());
    }
    
    private static int counter = 0;
    
    
    /**
     * Convert an {@link javax.lang.model.element.ExecutableElement} to a {@link me.codegen.model.JavaClazz}
     *
     * @param context           The context of the operation.
     * @param executableElement The target element.
     * @return An instance of {@link me.codegen.model.JavaClazz} that describes the interface.
     */
    public static JavaClazz executableToInterface(DslProcessorContext context, ExecutableElement executableElement) {
        //Do generate the interface
        Boolean isTerminal = executableElement.getAnnotation(Terminal.class) != null
                || !isVoid(executableElement);

        JavaType returnType = isVoid(executableElement) ?
                VOID :
                context.getToType().apply(executableElement.getReturnType().toString());
        
        JavaType genericType = getMapping(returnType);

        JavaMethod sourceMethod = context.getToMethod().apply(executableElement);
        JavaMethod targetMethod = isVoid(executableElement) ?
                new JavaMethodBuilder(sourceMethod).withReturnType(genericType).build() :
                sourceMethod;

        TargetName targetInterfaceName = executableElement.getAnnotation(TargetName.class);

        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(targetMethod.getName());

        return new JavaClazzBuilder().addType()
                .withPackageName(ModelUtils.getPackageElement(executableElement).toString())
                .withClassName(interfaceName)
                .addToGenericTypes(genericType)
                .withKind(JavaKind.INTERFACE)
                .addAttributes(ORIGINAL_RETURN_TYPE, returnType)
                .addAttributes(IS_TERMINAL, isTerminal)
                .addAttributes(TERMINATING_TYPES, isTerminal ? new LinkedHashSet<>(Arrays.asList(returnType)) : Collections.emptySet())
                .addAttributes(IS_COMPOSITE, false)
                .and()
                .addToMethods(targetMethod)
                .build();
    }

    public static JavaClazz combine(JavaClazz left, JavaClazz right) {
        boolean isTerminal = false;
        JavaType originalReturnType = VOID;
        Set<JavaType> genericTypes = new LinkedHashSet<>();
        Set<JavaType> interfaceTypes = new LinkedHashSet<>();
        Set<JavaType> terminatingTypes = new LinkedHashSet<>();

        String className = toInterfaceName(
                stripSuffix(left.getType().getClassName())
                        + stripSuffix(right.getType().getClassName())
        );

        terminatingTypes.addAll(getTerminatingTypes(left.getType()));
        terminatingTypes.addAll(getTerminatingTypes(right.getType()));
        for (JavaType type : terminatingTypes) {
            genericTypes.add(getMapping(type));
        }
        
        if (isTerminal(left) && isTerminal(right)) {
            isTerminal = true;
            interfaceTypes.add(unwrapGenerics(left).getType());
            interfaceTypes.add(unwrapGenerics(right).getType());
        } else if (isTerminal(left)) {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(new JavaTypeBuilder(right.getType()).withGenericTypes(new JavaType[]{left.getType()}).build());
            originalReturnType = (JavaType) left.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (isTerminal(right)) {
            interfaceTypes.add(right.getType());
            interfaceTypes.add(new JavaTypeBuilder(left.getType()).withGenericTypes(new JavaType[]{right.getType()}).build());
            originalReturnType = (JavaType) right.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (hasReturnType(left)) {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(new JavaTypeBuilder(right.getType()).withGenericTypes(new JavaType[]{left.getType()}).build());
            originalReturnType = (JavaType) left.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (hasReturnType(right)) {
            interfaceTypes.add(right.getType());
            interfaceTypes.add(new JavaTypeBuilder(left.getType()).withGenericTypes(new JavaType[]{right.getType()}).build());
            originalReturnType = (JavaType) right.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (!isComposite(left) && isComposite(right)) {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(new JavaTypeBuilder(right.getType()).withGenericTypes(new JavaType[]{left.getType()}).build());
            originalReturnType = (JavaType) left.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (isComposite(left) && !isComposite(right)) {
            interfaceTypes.add(right.getType());
            interfaceTypes.add(new JavaTypeBuilder(left.getType()).withGenericTypes(new JavaType[]{right.getType()}).build());
            originalReturnType = (JavaType) right.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        }else {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(right.getType());
            genericTypes.add(getMapping(VOID));
        }
        
        return new JavaClazzBuilder()
                .addType()
                .withKind(JavaKind.INTERFACE)
                .withClassName(className)
                .withPackageName(left.getType().getPackageName())
                .withInterfaces(interfaceTypes)
                .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()]))
                .addAttributes(ORIGINAL_RETURN_TYPE, originalReturnType)
                .addAttributes(TERMINATING_TYPES, terminatingTypes)
                .addAttributes(IS_TERMINAL, isTerminal)
                .addAttributes(IS_COMPOSITE, true)
                .addAttributes(COMBINATION_OF, Arrays.asList(left, right))
                .endType()
                .build();
    }

    public static JavaClazz combine(Set<JavaClazz> clazzes, Set<JavaClazz> alsoRequired) {
        if (clazzes.size() <= 1) {
            return clazzes.iterator().next();
        } else if (clazzes.size() == 2) {
            Iterator<JavaClazz> iterator = clazzes.iterator();
            return combine(iterator.next(), iterator.next());
        } else {
            Set<JavaClazz> subSet = new LinkedHashSet<>(clazzes);
            JavaClazz first = subSet.iterator().next();
            subSet.remove(first);
            JavaClazz second = combine(subSet, alsoRequired);
            alsoRequired.add(second);
            Set<JavaClazz> newSet = new LinkedHashSet<>(Arrays.asList(first, second));
            return combine(newSet, alsoRequired);
        }
    }

    public static Set<JavaClazz> combine(Set<JavaClazz> clazzes) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        JavaClazz combined = combine(clazzes, result);
        result.add(combined);
        return result;
    }

    static final JavaClazz unwrapGenerics(JavaClazz clazz) {
        if (clazz.getType().getGenericTypes().length == 0) {
            return clazz;
        } else if (clazz.getType().getGenericTypes().length == 1) {
            Object originalType = clazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
            if (originalType instanceof JavaType) {
                JavaType unwrapped = new JavaTypeBuilder(clazz.getType()).withGenericTypes(new JavaType[]{(JavaType) originalType}).build();
                return new JavaClazzBuilder(clazz).withType(unwrapped).build();
            }
            throw new IllegalStateException("Invalid original type");
        } else {
            throw new UnsupportedOperationException("Unwrapping types with multiple generic arguments is currently not supported");
        }
    }

    static final Set<JavaType> getTerminatingTypes(JavaType type) {
        Set<JavaType> result = new LinkedHashSet<>();
        if (type.getAttributes().containsKey(IS_COMPOSITE) && (Boolean) type.getAttributes().get(IS_TERMINAL)) {
            result.add((JavaType) type.getAttributes().get(ORIGINAL_RETURN_TYPE));
        }
        if (type.getAttributes().containsKey(TERMINATING_TYPES)) {
            result.addAll((Collection<JavaType>) type.getAttributes().get(TERMINATING_TYPES));
        }
        return result;
    }

    static final String stripSuffix(String str) {
        if (str.endsWith(INTERFACE_SUFFIX)) {
            return str.substring(0, str.length() - INTERFACE_SUFFIX.length());
        }
        return str;
    }

    static final Map<JavaType, JavaType> toGenericTypes(Set<JavaType> terminatingTypes) {
        Map<JavaType, JavaType> result = new HashMap<>();
        JavaType[] terminatingTypeArray = terminatingTypes.toArray(new JavaType[terminatingTypes.size()]);
        for (int i = 0; i < terminatingTypeArray.length; i++) {
            int iteration = i / GENERIC_NAMES.length;
            String name = GENERIC_NAMES[i % GENERIC_NAMES.length];
            if (iteration > 0) {
                name += iteration;
            }
            result.put(terminatingTypeArray[i], new JavaTypeBuilder().withClassName(name).build());
        }
        return result;
    }

    static synchronized final JavaType getMapping(JavaType type) {
        if (!GENERIC_MAPPINGS.containsKey(type)) {
            int iteration = counter / GENERIC_NAMES.length;
            String name = GENERIC_NAMES[counter % GENERIC_NAMES.length];
            if (iteration > 0) {
                name += iteration;
            }
            counter++;
            GENERIC_MAPPINGS.put(type, new JavaTypeBuilder().withClassName(name).build());
        }
        return GENERIC_MAPPINGS.get(type);
    }

    static synchronized final JavaType getReverseMapping(JavaType type) {
        for (Map.Entry<JavaType, JavaType> enty : GENERIC_MAPPINGS.entrySet()) {
            JavaType value = enty.getValue();
            if (value.equals(type)) {
                return enty.getKey();
            }
        }
        return type;
    }

    static synchronized final JavaType unwrapGenerics(JavaType type) {
        JavaType result = type;
        Set<JavaType> interfaces = new LinkedHashSet<>();
        Set<JavaType> generics = new LinkedHashSet<>();

        if (GENERIC_MAPPINGS.containsValue(type)) {
            return getReverseMapping(type);
        } else {
            for (JavaType iface : type.getInterfaces()) {
                interfaces.add(unwrapGenerics(iface));
            }
            for (JavaType generic : type.getGenericTypes()) {
                generics.add(unwrapGenerics(generic));
            }
            return new JavaTypeBuilder(type)
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withInterfaces(interfaces)
                    .build();
        }
    }
    
    

    static String toInterfaceName(String name) {
        if (name.endsWith(INTERFACE_SUFFIX)) {
            return name;
        }
        return captializeFirst(name) + INTERFACE_SUFFIX;
    }

    static boolean isVoid(ExecutableElement executableElement) {
        return executableElement.getReturnType().toString().equals("void");
    }

    static boolean hasReturnType(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(ORIGINAL_RETURN_TYPE)
                && (clazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE) instanceof JavaType)
                && !((JavaType) clazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE)).equals(VOID);
    }

    static boolean isComposite(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(IS_COMPOSITE)
                && (clazz.getType().getAttributes().get(IS_COMPOSITE) instanceof Boolean)
                && !(Boolean) clazz.getType().getAttributes().get(IS_COMPOSITE);
    }
    
    static boolean isTerminal(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(IS_TERMINAL)
                && (clazz.getType().getAttributes().get(IS_TERMINAL) instanceof Boolean)
                && (Boolean) clazz.getType().getAttributes().get(IS_TERMINAL);
    }
}
