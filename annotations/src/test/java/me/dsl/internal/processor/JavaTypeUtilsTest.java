package me.dsl.internal.processor;

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaType;
import me.dsl.internal.processor.matchers.TypeNamed;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static me.dsl.internal.processor.matchers.TypeNamed.*;
import static me.dsl.internal.processor.JavaTypeUtils.*;

public class JavaTypeUtilsTest {


    private final Context context = new Context();
    private final Elements elements = JavacElements.instance(context);
    private final Types types = JavacTypes.instance(context);
    private final DslProcessorContext dslContext = new DslProcessorContext(elements, types);

    @Test
    public void testExecutableToInterface() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        
        JavaClazz simpleClazz = JavaTypeUtils.executableToInterface(dslContext, simple);
        assertThat(simpleClazz.getType().getClassName(), equalTo("MethodAInterface"));
        assertThat(simpleClazz.getType().getPackageName(), equalTo(getClass().getPackage().getName()));
        assertThat(simpleClazz.getType().getGenericTypes().length, is(1));
        assertThat(simpleClazz.getType().getInterfaces().size(), is(0));
        assertThat((JavaType)simpleClazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), typeNamed("Void"));
        assertFalse((Boolean) simpleClazz.getType().getAttributes().get(IS_TERMINAL));
        
        JavaClazz terminalClazz = JavaTypeUtils.executableToInterface(dslContext, terminal);
        assertThat(terminalClazz.getType().getClassName(), equalTo("MethodBInterface"));
        assertThat(terminalClazz.getType().getPackageName(), equalTo(getClass().getPackage().getName()));
        assertThat(terminalClazz.getType().getGenericTypes().length, is(1));
        assertThat(terminalClazz.getType().getInterfaces().size(), is(0));
        assertThat((JavaType)terminalClazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), typeNamed("String"));
        assertTrue((Boolean) terminalClazz.getType().getAttributes().get(IS_TERMINAL));
    }
    
    @Test
    public void testCombineTwoWithTerminal() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoWithTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement simple = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        ExecutableElement terminal = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        JavaClazz simpleClazz = JavaTypeUtils.executableToInterface(dslContext, simple);
        JavaClazz teminalClazz = JavaTypeUtils.executableToInterface(dslContext, terminal);
        JavaClazz combined = JavaTypeUtils.combine(simpleClazz, teminalClazz);
        Assert.assertNotNull(combined);

        assertThat(combined.getType().getClassName(), equalTo("MethodAMethodBInterface"));
        assertThat(combined.getType().getPackageName(), equalTo(getClass().getPackage().getName()));
        assertThat(combined.getType().getGenericTypes().length, is(1));
        assertThat(combined.getType().getGenericTypes()[0].toString(), equalTo("T"));
        assertThat(combined.getType().getInterfaces().size(), is(2));
        assertThat(combined.getType().getInterfaces(), hasItem(typeNamed("MethodAInterface<MethodBInterface<T>>")));
        assertThat(combined.getType().getInterfaces(), hasItem(typeNamed("MethodBInterface<T>")));
        assertFalse((Boolean) combined.getType().getAttributes().get(IS_TERMINAL));
    }


    @Test
    public void testCombineTwoNonTerminal() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoNonTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement left = isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        ExecutableElement right = !isVoid(methods.get(0)) ? methods.get(0) : methods.get(1);
        JavaClazz leftClazz = executableToInterface(dslContext, left);
        JavaClazz rightClazz = executableToInterface(dslContext, right);
        JavaClazz combined = combine(leftClazz, rightClazz);
        Assert.assertNotNull(combined);

        assertThat(combined.getType().getClassName(), equalTo("MethodAMethodBInterface"));
        assertThat(combined.getType().getPackageName(), equalTo(getClass().getPackage().getName()));
        assertThat(combined.getType().getGenericTypes().length, is(1));
        assertThat(combined.getType().getGenericTypes()[0].toString(), equalTo("T"));
        assertThat(combined.getType().getInterfaces().size(), is(2));
        assertThat(combined.getType().getInterfaces(), hasItem(TypeNamed.typeNamed("MethodAInterface<T>")));
        assertThat(combined.getType().getInterfaces(), hasItem(TypeNamed.typeNamed("MethodBInterface<T>")));
        assertEquals(combined.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), VOID);
        assertFalse((Boolean) combined.getType().getAttributes().get(IS_TERMINAL));
    }

    @Test
    public void testCombineTwoTerminal() throws Exception {
        TypeElement typeElement = elements.getTypeElement(TwoTerminal.class.getCanonicalName());
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        ExecutableElement left = methods.get(0);
        ExecutableElement right = methods.get(1);
        JavaClazz leftClazz = executableToInterface(dslContext, left);
        JavaClazz rightClazz = executableToInterface(dslContext, right);
        JavaClazz combined = combine(leftClazz, rightClazz);
        Assert.assertNotNull(combined);

        assertThat(combined.getType().getClassName(), equalTo("MethodAMethodBInterface"));
        assertThat(combined.getType().getPackageName(), equalTo(getClass().getPackage().getName()));
        assertThat(combined.getType().getGenericTypes().length, is(0));
        assertThat(combined.getType().getInterfaces().size(), is(2));
        assertThat(combined.getType().getInterfaces(), hasItem(TypeNamed.typeNamed("MethodAInterface<Integer>")));
        assertThat(combined.getType().getInterfaces(), hasItem(TypeNamed.typeNamed("MethodBInterface<Long>")));
        assertEquals(combined.getType().getAttributes().get(ORIGINAL_RETURN_TYPE), VOID);
        assertTrue((Boolean) combined.getType().getAttributes().get(IS_TERMINAL));
    }

    @Test
    public void testCombineMoreMethods() throws Exception {
        TypeElement typeElement = elements.getTypeElement(MoreMethods.class.getCanonicalName());
        Set<JavaClazz> clazzes = new LinkedHashSet<>();
        List<ExecutableElement> methods = ElementFilter.methodsIn(typeElement.getEnclosedElements());
        for (ExecutableElement element : methods) {
            clazzes.add(executableToInterface(dslContext, element));
        }
        Set<JavaClazz> combined = combine(clazzes);
        assertThat(combined.size(), is(3));
    }
    
}