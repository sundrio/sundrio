/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.swagger.experimental;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.Generator;
import io.swagger.codegen.ignore.CodegenIgnoreProcessor;
import io.swagger.codegen.languages.AbstractJavaCodegen;
import io.swagger.models.ComposedModel;
import io.swagger.models.Model;
import io.swagger.models.RefModel;
import io.swagger.models.Swagger;

public class SundrioGenerator implements Generator {

  protected final Logger LOGGER = LoggerFactory.getLogger(SundrioGenerator.class);
  protected static final String DOT = ".";
  protected static final String EMPTY = "";

  protected AbstractJavaCodegen config;

  protected final ClientOptInput opts;
  protected final Swagger swagger;

  protected CodegenIgnoreProcessor ignoreProcessor;
  private Boolean generateApis = null;
  private Boolean generateModels = null;
  private Boolean generateSupportingFiles = null;
  private Boolean generateApiTests = null;
  private Boolean generateApiDocumentation = null;
  private Boolean generateModelTests = null;
  private Boolean generateModelDocumentation = null;
  private String basePath;
  private String basePathWithoutHost;
  private String contextPath;

  public SundrioGenerator() {
    this.opts = null;
    this.swagger = null;
  }

  public SundrioGenerator(ClientOptInput opts) {
    this.opts = opts;
    this.swagger = opts.getSwagger();
  }

  @Override
  public Generator opts(ClientOptInput opts) {
    return new SundrioGenerator(opts);
  }

  @Override
  public List<File> generate() {
    List<File> result = new ArrayList<>();
    final Map<String, Model> definitions = swagger.getDefinitions();
    if (definitions == null) {
      return result;
    }
    for (Map.Entry<String, Model> entry : definitions.entrySet()) {
      String name = entry.getKey();
      Model model = entry.getValue();
      //TODO: skip models with import mapping.

      Map<String, Model> modelMap = new HashMap<String, Model>();
      modelMap.put(name, model);
    }
    return result;
  }

  public TypeDef createModel(String name, Model model, AbstractJavaCodegen config, Map<String, Model> allDefinitions) {

    String prefix = name.contains(DOT)
        ? name.substring(0, name.lastIndexOf(DOT))
        : EMPTY;

    String packageName = prefix.isEmpty() ? config.modelPackage() : config.modelPackage() + DOT + prefix;
    String className = prefix.isEmpty() ? name : name.substring(name.lastIndexOf(DOT) + 1);

    ClassRef superClass = null;
    List<ClassRef> interfaces = new ArrayList<>();
    List<Property> fields = new ArrayList<>();
    List<Method> methods = new ArrayList<>();

    if (model instanceof ComposedModel) {
      ComposedModel composed = (ComposedModel) model;

      // interfaces (intermediate models)
      if (composed.getInterfaces() != null) {
        for (RefModel _interface : composed.getInterfaces()) {
          Model interfaceModel = null;
          if (allDefinitions != null) {
            interfaceModel = allDefinitions.get(_interface.getSimpleRef());
          }

        }

        return new TypeDefBuilder()
            .withKind(Kind.CLASS)
            .withPackageName(packageName)
            .withName(className)
            .withImplementsList(interfaces)
            .withExtendsList(superClass)
            .withProperties(fields)
            .withMethods(methods)
            .build();
      }
    }
    return null;
  }

}
