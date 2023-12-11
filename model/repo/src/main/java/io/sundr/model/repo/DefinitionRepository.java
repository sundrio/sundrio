/*
 * Copyright 2016 The original authors.
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

package io.sundr.model.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.SundrException;
import io.sundr.model.AttributeKey;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;

public class DefinitionRepository {

  private static DefinitionRepository INSTANCE;
  private static DefinitionRepository SCOPE;

  private final ConcurrentMap<String, TypeDef> definitions = new ConcurrentHashMap<String, TypeDef>();
  private final ConcurrentMap<String, Supplier<TypeDef>> suppliers = new ConcurrentHashMap<String, Supplier<TypeDef>>();

  private Map<String, String> snapshot;

  private DefinitionRepository() {
  }

  /**
   * Get or create a DefinitionRepository.
   * This is a traditional Singleton getInstance() method with a twist ...
   * Along with the static final instance that is used by common singletons this also uses a scoped instance.
   * The scoped instance is something that may be used in the context of a {@link Function} or a {@link Callable}.
   * So, when this method is used from within a call to Definition.withRepository(repo).call(...) it will return the value of
   * repo instead of the Singleton intance.
   * The same goes for Definition.withNewRepository(repo -{@literal >} { ... }).
   *
   * @return the scoped instance if called from within withRepo/withNewRepo lambda, or the singleton instance otherwise.
   */
  public static synchronized final DefinitionRepository getRepository() {
    if (SCOPE != null) {
      return SCOPE;
    }
    if (INSTANCE == null) {
      INSTANCE = new DefinitionRepository();
    }
    return INSTANCE;
  }

  /**
   * Create a new instance of the repository, without messing with the singleton
   *
   * @return a new instance
   */
  public static DefinitionRepository createRepository() {
    return new DefinitionRepository();
  }

  public static WithRepo withRepository(DefinitionRepository repository) {
    return new WithRepo(repository);
  }

  public static WithRepo withNewRepository() {
    return new WithRepo(new DefinitionRepository());
  }

  public synchronized void registerIfAbsent(String fqcn, Supplier<TypeDef> supplier) {
    if (definitions.containsKey(fqcn)) {
      return;
    }

    if (suppliers.containsKey(fqcn)) {
      return;
    }

    suppliers.put(fqcn, supplier);
  }

  public synchronized void registerIfAbsent(TypeDef definition) {
    if (definition == null) {
      return;
    }

    String fqcn = definition.getFullyQualifiedName();
    definitions.putIfAbsent(fqcn, definition);
  }

  public synchronized TypeDef register(TypeDef definition) {
    definitions.put(definition.getFullyQualifiedName(), definition);
    return definition;
  }

  public synchronized TypeDef register(TypeDef definition, String... flags) {
    TypeDefBuilder builder = new TypeDefBuilder(definition);
    for (String flag : flags) {
      builder.addToAttributes(new AttributeKey<Boolean>(flag, Boolean.class), true);
    }
    return register(builder.build());
  }

  public synchronized TypeDef register(TypeDef definition, AttributeKey<Boolean>... flags) {
    TypeDefBuilder builder = new TypeDefBuilder(definition);
    for (AttributeKey<Boolean> flag : flags) {
      builder.addToAttributes(flag, true);
    }
    return register(builder.build());
  }

  public synchronized Set<TypeDef> getDefinitions(String... flags) {
    Set<TypeDef> result = new LinkedHashSet<TypeDef>();
    for (TypeDef candidate : definitions.values()) {
      boolean matches = true;
      for (String flag : flags) {
        AttributeKey<Boolean> attributeKey = new AttributeKey<Boolean>(flag, Boolean.class);
        if (!candidate.hasAttribute(attributeKey) || !(candidate.getAttribute(attributeKey))) {
          matches = false;
          break;
        }
      }
      if (matches) {
        result.add(candidate);
      }
    }

    return Collections.unmodifiableSet(result);
  }

  public synchronized Set<TypeDef> getDefinitions(AttributeKey<Boolean>... attributeKeys) {

    Set<TypeDef> result = new LinkedHashSet<TypeDef>();
    for (TypeDef candidate : definitions.values()) {
      boolean matches = true;
      for (AttributeKey<Boolean> attributeKey : attributeKeys) {
        if (!candidate.hasAttribute(attributeKey) || !(candidate.getAttribute(attributeKey))) {
          matches = false;
          break;
        }
      }
      if (matches) {
        result.add(candidate);
      }
    }

    return Collections.unmodifiableSet(result);
  }

  public synchronized boolean hasDefinition(String fullyQualifiedName) {
    return definitions.containsKey(fullyQualifiedName) || suppliers.containsKey(fullyQualifiedName);
  }

  public TypeDef getDefinition(String fullyQualifiedName) {
    return getDefinition(fullyQualifiedName, true);
  }

  public synchronized TypeDef getDefinition(String fullyQualifiedName, boolean computeIfSupplied) {
    TypeDef definition = definitions.get(fullyQualifiedName);
    if (definition != null || !computeIfSupplied) {
      return definition;
    }
    Supplier<TypeDef> supplier = suppliers.remove(fullyQualifiedName);
    if (supplier != null) {
      TypeDef typeDef = supplier.get();
      definitions.put(fullyQualifiedName, typeDef);
      return typeDef;
    }
    return null;
  }

  public synchronized TypeDef getDefinition(TypeRef type) {
    if (type instanceof ClassRef) {
      return getDefinition(((ClassRef) type).getFullyQualifiedName());
    }
    return null;
  }

  public synchronized Collection<TypeDef> getDefinitions() {
    return Stream.concat(definitions.keySet().stream(), suppliers.keySet().stream()).distinct().map(k -> getDefinition(k))
        .collect(Collectors.toSet());
  }

  public synchronized void updateReferenceMap() {
    snapshot = getReferenceMapInternal();
  }

  public synchronized Map<String, String> getReferenceMap() {
    if (snapshot == null) {
      snapshot = getReferenceMapInternal();
    }
    return snapshot;
  }

  private Map<String, String> getReferenceMapInternal() {
    Map<String, String> mapping = new HashMap<String, String>();
    List<ClassRef> refs = new ArrayList<ClassRef>();
    for (TypeDef typeDef : getDefinitions()) {
      refs.add(typeDef.toInternalReference());
    }

    //It's best to have predictable order, so that we can generate uniform code.
    Collections.sort(refs, new Comparator<ClassRef>() {
      @Override
      public int compare(ClassRef o1, ClassRef o2) {
        return o1.getFullyQualifiedName().compareTo(o2.getFullyQualifiedName());
      }
    });

    for (ClassRef classRef : refs) {
      String key = classRef.getName();
      if (!mapping.containsKey(key)) {
        mapping.put(key, classRef.getFullyQualifiedName());
      }
    }

    return mapping;
  }

  public synchronized void clear() {
    definitions.clear();
    suppliers.clear();
  }

  public static class WithRepo {

    public WithRepo(DefinitionRepository repository) {
      this.repository = repository;
    }

    private final DefinitionRepository repository;

    public synchronized <V> V apply(Function<DefinitionRepository, V> function) {
      try {
        SCOPE = repository;
        return function.apply(repository);
      } catch (Exception e) {
        throw new SundrException(e);
      } finally {
        SCOPE = null;
      }
    }

    public synchronized <V> V call(Callable<V> callable) {
      try {
        SCOPE = repository;
        return callable.call();
      } catch (Exception e) {
        throw new SundrException(e);
      } finally {
        SCOPE = null;
      }
    }
  }
}
