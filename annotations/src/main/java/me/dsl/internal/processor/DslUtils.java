package me.dsl.internal.processor;

import me.codegen.model.JavaClazz;
import me.codegen.model.JavaClazzBuilder;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;
import me.dsl.annotations.EntryPoint;
import me.dsl.annotations.TargetName;

import javax.lang.model.element.ExecutableElement;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static me.dsl.internal.processor.JavaTypeUtils.combine;
import static me.dsl.internal.processor.JavaTypeUtils.executableToInterface;
import static me.dsl.internal.processor.JavaTypeUtils.toInterfaceName;
import static me.dsl.internal.processor.JavaTypeUtils.unwrapGenerics;

public final class DslUtils {
    
    private DslUtils() {
        //Utility Class
    }
    
    static Set<JavaClazz> createGenericInterfaces(DslProcessorContext context, Collection<ExecutableElement> elements) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        for (ExecutableElement current : elements) {
            if (current.getAnnotation(EntryPoint.class) != null) {
                //Let's skip entry points and focus on generic types.
                continue;
            }
            result.add(executableToInterface(context, current));
        }
        return result;
    }


    static Set<JavaClazz> createTransitionInterface(DslProcessorContext context, ExecutableElement current, Set<ExecutableElement> next) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        JavaMethod method = context.getToMethod().apply(current);
        TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(method.getName());

        Set<JavaClazz> nextInterfaces = new LinkedHashSet<>();
        for (ExecutableElement element : next) {
            nextInterfaces.add(executableToInterface(context, element));
        }

        JavaClazz dependencyInterface = combine(nextInterfaces, result);
        
        JavaClazz entryInterface = new JavaClazzBuilder()
                .addType()
                    .withKind(JavaKind.INTERFACE)
                    .withPackageName(dependencyInterface.getType().getPackageName())
                    .withClassName(interfaceName)
                    .addToInterfaces(unwrapGenerics(dependencyInterface.getType()))
                .endType()
                .build();
        
        result.add(entryInterface);
        result.add(dependencyInterface);
        return result;
    }

    static JavaMethod createEntryPointMethod(DslProcessorContext context, ExecutableElement current) {
        JavaMethod method = context.getToMethod().apply(current);
        TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(method.getName());

        JavaType returnType = new JavaTypeBuilder()
                .withClassName(interfaceName)
                .withPackageName(context.getElements().getPackageOf(current).getQualifiedName().toString())
                .build();
        
        return new JavaMethodBuilder(method).withReturnType(returnType).build();
    }
}
