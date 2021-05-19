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

package io.sundr.builder.internal.processor;

import java.lang.annotation.Annotation;
import java.util.concurrent.Callable;

import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.junit.Before;
import org.junit.Rule;

import com.google.testing.compile.CompilationRule;

import io.sundr.adapter.apt.AptContext;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;
import io.sundr.model.repo.DefinitionRepository;

public class AbstractProcessorTest {

  public @Rule CompilationRule rule = new CompilationRule();

  private Elements elements;
  private Types types;
  private AptContext context;
  protected BuilderContext builderContext;

  @Before
  public void setup() {
    elements = rule.getElements();
    types = rule.getTypes();
    context = AptContext.create(elements, types, DefinitionRepository.getRepository());
    builderContext = BuilderContextManager.create(elements, types);
  }

  final Inline inline = new Inline() {
    public Class<? extends Annotation> annotationType() {
      return Inline.class;
    }

    public String value() {
      return "call";
    }

    public String prefix() {
      return "Callable";
    }

    public String name() {
      return "";
    }

    public String suffix() {
      return "";
    }

    public Class type() {
      return Callable.class;
    }

    public Class returnType() {
      return null;
    }
  };

  static boolean hasMethod(TypeDef typeDef, String name, TypeRef... arguments) {
    for (Method method : typeDef.getMethods()) {
      if (!method.getName().equals(name)) {
        continue;
      } else if (method.getArguments().size() != arguments.length) {
        continue;
      }

      boolean argumentsMatched = true;
      for (int i = 0; i < arguments.length; i++) {
        if (!arguments[i].equals(method.getArguments().get(i).getTypeRef())) {
          argumentsMatched = false;
        }
      }
      if (argumentsMatched) {
        return true;
      }
    }
    return false;
  }
}
