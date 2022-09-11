package io.sundr.model.utils;

import static io.sundr.model.utils.Collections.E;
import static io.sundr.model.utils.Collections.K;
import static io.sundr.model.utils.Collections.V;
import static io.sundr.model.utils.Types.OPTIONAL;
import static io.sundr.model.utils.Types.VOID;
import static org.junit.Assert.assertEquals;

import io.sundr.builder.Visitor;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.utils.Strings;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

// Credits to: Dusan Jakub (xRodney).
public class TypeArgumentsTest {

    public static final TypeDef MULTI_HASH_MAP = new TypeDefBuilder(TypeDef.forName("com.example.MultiHashMap"))
            .withKind(Kind.CLASS).withParameters(K, V)
            .withExtendsList(
                    Collections.HASH_MAP.toReference(K.toReference(), Collections.LIST.toReference(V.toReference())))
            .build();

    public static final TypeDef MY_SUPERCLASS = new TypeDefBuilder(TypeDef.forName("com.example.MySuperclass"))
            .withKind(Kind.CLASS).withParameters(E)
            .accept(addBackedProperty(E.toReference(), "superValue"))
            .accept(addBackedProperty(Collections.LIST.toReference(E.toReference()), "superValues"))
            .build();

    public static final TypeDef MY_SUBCLASS = new TypeDefBuilder(TypeDef.forName("com.example.MySubclass"))
            .withKind(Kind.CLASS).withParameters(E)
            .accept(addBackedProperty(E.toReference(), "subValue"))
            .accept(addBackedProperty(Collections.LIST.toReference(E.toReference()), "subValues"))
            .withExtendsList(MY_SUPERCLASS.toReference(OPTIONAL.toReference(E.toReference())))
            .build();

    @Before
    public void setUp() {
        DefinitionRepository.getRepository().register(Collections.MAP);
        DefinitionRepository.getRepository().register(Collections.HASH_MAP);
        DefinitionRepository.getRepository().register(Collections.LIST);
        DefinitionRepository.getRepository().register(Types.STRING);
        DefinitionRepository.getRepository().register(Types.INT);
        DefinitionRepository.getRepository().register(MULTI_HASH_MAP);
        DefinitionRepository.getRepository().register(MY_SUBCLASS);
        DefinitionRepository.getRepository().register(MY_SUPERCLASS);
    }

    @Test
    public void shouldNotCauseStackOverflow() {
        ClassRef classRef = MULTI_HASH_MAP.toReference(Types.STRING_REF, Types.INT_REF);
        TypeDef typeDef = GetDefinition.of(classRef);
        RichTypeDef richDef = TypeArguments.apply(GetDefinition.of(classRef));

        System.out.println(typeDef.render());
        System.out.println(richDef.render());

        // This goes beyond the purpose of the tests, which is to check against SO.
        // Still, it's nice to know that the rendered code is not affected (for THIS scenario).
        assertEquals(typeDef.render(), richDef.render());
    }

    @Test
    public void propertiesAndMethodsFromSupertypesAreResolvedCorrectly() {
        ClassRef classRef = MY_SUBCLASS.toReference(Types.STRING_REF);
        TypeDef typeDef = GetDefinition.of(classRef);
        RichTypeDef richDef = TypeArguments.apply(GetDefinition.of(classRef));

        System.out.println(typeDef.render());
        System.out.println(richDef.render());

        Map<String, Property> propertiesByName = richDef.getAllProperties().stream()
                .collect(Collectors.toMap(Property::getName, Function.identity()));
        assertEquals(4, propertiesByName.size());
        assertEquals("E subValue", propertiesByName.get("subValue").render());
        assertEquals("java.util.List<E> subValues", propertiesByName.get("subValues").render());
        assertEquals("java.util.Optional<E> superValue", propertiesByName.get("superValue").render());
        assertEquals("java.util.List<java.util.Optional<E>> superValues", propertiesByName.get("superValues").render());

        Map<String, Method> methodsByName = richDef.getAllMethods().stream()
                .collect(Collectors.toMap(Method::getName, Function.identity()));
        assertEquals(8, methodsByName.size());

        assertEquals("E getSubValue();", methodsByName.get("getSubValue").render());
        assertEquals("void setSubValue(E subValue);", methodsByName.get("setSubValue").render());
        assertEquals("java.util.List<E> getSubValues();", methodsByName.get("getSubValues").render());
        assertEquals("void setSubValues(java.util.List<E> subValues);", methodsByName.get("setSubValues").render());

        assertEquals("java.util.Optional<E> getSuperValue();", methodsByName.get("getSuperValue").render());
        assertEquals("void setSuperValue(java.util.Optional<E> superValue);",
                methodsByName.get("setSuperValue").render());
        assertEquals("java.util.List<java.util.Optional<E>> getSuperValues();",
                methodsByName.get("getSuperValues").render());
        assertEquals("void setSuperValues(java.util.List<java.util.Optional<E>> superValues);",
                methodsByName.get("setSuperValues").render());
    }

    /**
     * Register a field with a type and name, and corresponding getter and setter methods
     */
    private static Visitor<TypeDefBuilder> addBackedProperty(TypeRef type, String name) {
        return new Visitor<TypeDefBuilder>() {
            @Override
            public void visit(TypeDefBuilder typeDef) {
                typeDef.addNewProperty().withName(name).withTypeRef(type).endProperty()

                        .addNewMethod().withName("get" + Strings.capitalizeFirst(name)).withReturnType(type).endMethod()

                        .addNewMethod().withName("set" + Strings.capitalizeFirst(name)).withVoidRefReturnType(VOID)
                        .addNewArgument().withTypeRef(type).withName(name).endArgument().endMethod();
            }
        };
    }
}
