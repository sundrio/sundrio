package io.sundr.adapter.source;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.model.ContextRef;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.visitors.context.resolver.UnresolvedContextRefLocator;

/**
 * Tests to verify that ContextRef resolution works correctly during TypeDef creation from source.
 */
public class ContextRefResolutionTest {

  @Test
  public void testPropertyResolution() {
    TypeDef typeDef = parseTypeDefFromResource("PropertyResolution.java");

    Optional<Method> testMethod = typeDef.getMethods().stream()
        .filter(m -> "testMethod".equals(m.getName()))
        .findFirst();

    assertTrue("testMethod should exist", testMethod.isPresent());

    UnresolvedContextRefLocator locator = UnresolvedContextRefLocator.forType(typeDef);
    assertFalse("All ContextRef objects should be resolved, but found: " + locator.getUnresolvedContextRefs(),
        locator.hasUnresolvedContextRefs());
  }

  @Test
  public void testMethodArgumentResolution() {
    TypeDef typeDef = parseTypeDefFromResource("MethodArgumentResolution.java");

    Optional<Method> testMethod = typeDef.getMethods().stream()
        .filter(m -> "testMethod".equals(m.getName()))
        .findFirst();

    assertTrue("testMethod should exist", testMethod.isPresent());

    UnresolvedContextRefLocator locator = UnresolvedContextRefLocator.forType(typeDef);
    assertFalse("All ContextRef objects should be resolved, but found: " + locator.getUnresolvedContextRefs(),
        locator.hasUnresolvedContextRefs());

  }

  @Test
  public void testLocalVariableResolution() {
    TypeDef typeDef = parseTypeDefFromResource("LocalVariableResolution.java");

    Optional<Method> testMethod = typeDef.getMethods().stream()
        .filter(m -> "testMethod".equals(m.getName()))
        .findFirst();

    assertTrue("testMethod should exist", testMethod.isPresent());
    UnresolvedContextRefLocator locator = UnresolvedContextRefLocator.forType(typeDef);
    assertFalse("All ContextRef objects should be resolved, but found: " + locator.getUnresolvedContextRefs(),
        locator.hasUnresolvedContextRefs());

  }

  @Test
  public void testStaticMethodResolution() {
    TypeDef typeDef = parseTypeDefFromResource("StaticMethodResolution.java");

    Optional<Method> testMethod = typeDef.getMethods().stream()
        .filter(m -> "testMethod".equals(m.getName()))
        .findFirst();

    assertTrue("testMethod should exist", testMethod.isPresent());
    UnresolvedContextRefLocator locator = UnresolvedContextRefLocator.forType(typeDef);
    assertFalse("All ContextRef objects should be resolved, but found: " + locator.getUnresolvedContextRefs(),
        locator.hasUnresolvedContextRefs());
  }

  @Test
  public void testStaticMethodFromSamePackage() {
    TypeDef typeDef = parseTypeDefFromResource("StaticMethodFromSamePackage.java");

    Optional<Method> testMethod = typeDef.getMethods().stream()
        .filter(m -> "testMethod".equals(m.getName()))
        .findFirst();

    assertTrue("testMethod should exist", testMethod.isPresent());

    UnresolvedContextRefLocator locator = UnresolvedContextRefLocator.forType(typeDef);
    assertFalse("All ContextRef objects should be resolved, but found: " + locator.getUnresolvedContextRefs(),
        locator.hasUnresolvedContextRefs());
  }

  @Test
  public void testContextRefToPropertyRefResolution() {
    TypeDef typeDef = parseTypeDefFromResource("ContextRefToPropertyRefResolution.java");

    Optional<Method> testMethod = typeDef.getMethods().stream()
        .filter(m -> "testMethod".equals(m.getName()))
        .findFirst();

    assertTrue("testMethod should exist", testMethod.isPresent());
    UnresolvedContextRefLocator locator = UnresolvedContextRefLocator.forType(typeDef);
    assertFalse("All ContextRef objects should be resolved, but found: " + locator.getUnresolvedContextRefs(),
        locator.hasUnresolvedContextRefs());
  }

  private TypeDef parseTypeDefFromResource(String resourceName) {
    try {
      DefinitionRepository repository = DefinitionRepository.getRepository();
      AdapterContext context = AdapterContext.create(repository);

      TypeDef typeDef = Sources.readTypeDefFromResource("context-ref-resolution/" + resourceName, context);
      return typeDef;
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse source", e);
    }
  }
}
