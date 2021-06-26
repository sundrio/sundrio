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

package io.sundr.swagger.language;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sundr.utils.Strings;
import io.swagger.codegen.v3.CliOption;
import io.swagger.codegen.v3.CodegenModel;
import io.swagger.codegen.v3.CodegenOperation;
import io.swagger.codegen.v3.CodegenParameter;
import io.swagger.codegen.v3.CodegenProperty;
import io.swagger.codegen.v3.CodegenType;
import io.swagger.codegen.v3.SupportingFile;
import io.swagger.codegen.v3.generators.java.JavaClientCodegen;
import io.swagger.v3.oas.models.Operation;

public class JavaFluentCodegen extends JavaClientCodegen {

  private static final Logger LOGGER = LoggerFactory.getLogger(JavaFluentCodegen.class);

  public static final String GENERATE_BUILDERS = "generateBuilders";
  public static final String EDITABLE_ENABLED = "editableEnabled";
  public static final String VALIDATION_ENABLED = "validationEnabled";
  public static final String LAZY_MAP_INIT_ENABLED = "lazyMapInitEnabled";
  public static final String GENERATE_BUILDER_PACKAGE = "generateBuilderPackage";
  public static final String BUILDER_PACKAGE = "builderPackage";
  public static final String BUILDER_ARTIFACT = "builderArtifact";
  public static final String BUILDER_GROUP_ID = "builderGroupId";
  public static final String BUILDER_ARTIFACT_ID = "builderArtifactId";
  public static final String BUILDER_VERSION = "builderVersion";
  public static final String BUILDER_CLASSIFIER = "builderClassifier";
  public static final String HAS_BUILDER_ARTIFACT = "hasBuilderArtifact";
  public static final String HAS_BUILDER_CLASSIFIER = "hasBuilderClassifier";

  protected boolean generateBuilders = true;
  protected boolean editableEnabled = true;
  protected boolean validationEnabled = false;
  protected boolean lazyMapInitEnabled = true;
  protected boolean generateBuilderPackage = false;
  protected String builderPackage = null;
  protected String builderArtifact = null;
  protected String builderGroupId = null;
  protected String builderArtifactId = null;
  protected String builderVersion = null;
  protected String builderClassifier = null;

  public JavaFluentCodegen() {
    super();
    outputFolder = "generated-code" + File.separator + "java";
    embeddedTemplateDir = templateDir = "handlebars/JavaFluent";
    invokerPackage = "io.swagger.client";
    artifactId = "swagger-java-client";
    apiPackage = "io.sundr.client.api";
    modelPackage = "io.sundr.client.model";

    cliOptions.add(CliOption.newBoolean(GENERATE_BUILDERS, "Whether to generate builders for model objects."));
    cliOptions.add(CliOption.newBoolean(EDITABLE_ENABLED, "Flag to specify if editable classes should to be generated."));
    cliOptions.add(CliOption.newBoolean(VALIDATION_ENABLED,
        "Flag to specify if validation code should be part of the generated builders."));
    cliOptions.add(CliOption.newBoolean(LAZY_MAP_INIT_ENABLED, "Flag to specify if maps should be lazily initialized."));

    cliOptions.add(CliOption.newBoolean(BUILDER_PACKAGE, "The package that contains the builder helper classes."));
    cliOptions.add(CliOption.newBoolean(GENERATE_BUILDER_PACKAGE,
        "Flag that specifies wether the builder package should be generated or not."));
    cliOptions.add(CliOption.newBoolean(BUILDER_ARTIFACT,
        "The maven artifact that contains the builder utility classes. The format is <groupId>:<artifactId>:<version>(:<classifier>)"));

    supportedLibraries.put("okhttp-jackson", "HTTP client: OkHttp 2.7.5. JSON processing: Jackson 2.8.9.");
  }

  @Override
  public void processOpts() {
    super.processOpts();
    if (additionalProperties.containsKey(GENERATE_BUILDERS)) {
      this.setGenerateBuilders(Boolean.valueOf(additionalProperties.get(GENERATE_BUILDERS).toString()));
    }
    additionalProperties.put(GENERATE_BUILDERS, generateBuilders);

    if (additionalProperties.containsKey(EDITABLE_ENABLED)) {
      this.setEditableEnabled(Boolean.valueOf(additionalProperties.get(EDITABLE_ENABLED).toString()));
    }
    additionalProperties.put(EDITABLE_ENABLED, editableEnabled);

    if (additionalProperties.containsKey(VALIDATION_ENABLED)) {
      this.setValidationEnabled(Boolean.valueOf(additionalProperties.get(VALIDATION_ENABLED).toString()));
    }
    additionalProperties.put(VALIDATION_ENABLED, validationEnabled);

    if (additionalProperties.containsKey(LAZY_MAP_INIT_ENABLED)) {
      this.setLazyMapInitEnabled(Boolean.valueOf(additionalProperties.get(LAZY_MAP_INIT_ENABLED).toString()));
    }
    additionalProperties.put(LAZY_MAP_INIT_ENABLED, lazyMapInitEnabled);

    if (additionalProperties.containsKey(GENERATE_BUILDER_PACKAGE)) {
      this.setGenerateBuilderPackage(Boolean.valueOf(additionalProperties.get(GENERATE_BUILDER_PACKAGE).toString()));
    }
    additionalProperties.put(GENERATE_BUILDER_PACKAGE, generateBuilderPackage);

    if (additionalProperties.containsKey(BUILDER_PACKAGE)) {
      this.setBuilderPackage(additionalProperties.get(BUILDER_PACKAGE).toString());
    } else if (!additionalProperties.containsKey(BUILDER_ARTIFACT)) {
      //When builder package does not exist, we need to generate one, relative to the model.
      this.setGenerateBuilderPackage(true);
      this.setBuilderPackage(modelPackage + ".builder");
    }
    additionalProperties.put(BUILDER_PACKAGE, builderPackage);
    additionalProperties.put(GENERATE_BUILDER_PACKAGE, generateBuilderPackage);

    if (additionalProperties.containsKey(BUILDER_ARTIFACT)) {
      this.setBuilderArtifact(additionalProperties.get(BUILDER_ARTIFACT).toString());
      this.setBuilderGroupId(ArtifactUtil.groupId(builderArtifact));
      this.setBuilderArtifactId(ArtifactUtil.artifactId(builderArtifact));
      this.setBuilderVersion(ArtifactUtil.version(builderArtifact));
      ArtifactUtil.classifier(builderArtifact).ifPresent(c -> this.setBuilderClassifier(c));
    }
    additionalProperties.put(BUILDER_ARTIFACT, builderArtifact);

    if (additionalProperties.containsKey(BUILDER_GROUP_ID)) {
      this.setBuilderGroupId(additionalProperties.get(BUILDER_GROUP_ID).toString());
    }
    additionalProperties.put(BUILDER_GROUP_ID, builderGroupId);

    if (additionalProperties.containsKey(BUILDER_ARTIFACT_ID)) {
      this.setBuilderArtifactId(additionalProperties.get(BUILDER_ARTIFACT_ID).toString());
    }
    additionalProperties.put(BUILDER_ARTIFACT_ID, builderArtifactId);

    if (additionalProperties.containsKey(BUILDER_VERSION)) {
      this.setBuilderVersion(additionalProperties.get(BUILDER_VERSION).toString());
    }
    additionalProperties.put(BUILDER_VERSION, builderVersion);

    if (additionalProperties.containsKey(BUILDER_CLASSIFIER)) {
      this.setBuilderClassifier(additionalProperties.get(BUILDER_CLASSIFIER).toString());
    }
    additionalProperties.put(BUILDER_CLASSIFIER, builderClassifier);

    additionalProperties.put(HAS_BUILDER_ARTIFACT, !Strings.isNullOrEmpty(builderGroupId)
        && !Strings.isNullOrEmpty(builderArtifactId) && !Strings.isNullOrEmpty(builderVersion));
    additionalProperties.put(HAS_BUILDER_CLASSIFIER, !Strings.isNullOrEmpty(builderClassifier));

    final String invokerFolder = (sourceFolder + File.separator + invokerPackage).replace(".", File.separator);

    if ("okhttp-jackson".equals(getLibrary())) {
      supportingFiles.add(new SupportingFile("JSON.mustache", invokerFolder, "JSON.java"));
      supportingFiles.add(new SupportingFile("ApiResponse.mustache", invokerFolder, "ApiResponse.java"));
      supportingFiles.add(new SupportingFile("ApiCallback.mustache", invokerFolder, "ApiCallback.java"));
      supportingFiles.add(new SupportingFile("ProgressRequestBody.mustache", invokerFolder, "ProgressRequestBody.java"));
      supportingFiles.add(new SupportingFile("ProgressResponseBody.mustache", invokerFolder, "ProgressResponseBody.java"));
      supportingFiles.add(new SupportingFile("GzipRequestInterceptor.mustache", invokerFolder, "GzipRequestInterceptor.java"));
      additionalProperties.put("jackson", "true");
    }
  }

  @Override
  public CodegenType getTag() {
    return CodegenType.CLIENT;
  }

  @Override
  public String getName() {
    return "java-fluent";
  }

  @Override
  public String getHelp() {
    return "Generates a Java fluent model.";
  }

  @Override
  public String getDefaultTemplateDir() {
    return "JavaFluent";
  }

  //
  // Customizations
  //
  private final Map<String, String> importMappings = new HashMap<>();
  private final Map<String, String> classMappings = new HashMap<>();
  private final List<Map<String, Object>> knownModels = new ArrayList<>();

  @Override
  public Map<String, Object> postProcessModels(Map<String, Object> objs) {
    knownModels.add(objs);
    String pkg = (String) objs.getOrDefault("package", "");

    Object o = objs.getOrDefault("models", Collections.emptyMap());
    if (o instanceof Map) {
      refactorModel(pkg, (Map<String, Object>) o, objs);
    } else if (o instanceof Iterable) {
      for (Object i : (Iterable<? extends Object>) o) {
        if (i instanceof Map) {
          refactorModel(pkg, (Map<String, Object>) i, objs);
        }
      }
    }

    return super.postProcessModels(objs);
  }

  @Override
  public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
    String pkg = (String) objs.getOrDefault("package", "");

    Object o = objs.getOrDefault("operations", Collections.emptyMap());
    if (o instanceof Map) {
      refactorOperation(pkg, (Map<String, Object>) o, objs);
    } else if (o instanceof Iterable) {
      for (Object i : (Iterable<? extends Object>) o) {
        if (i instanceof Map) {
          refactorOperation(pkg, (Map<String, Object>) i, objs);
        }
      }
    }
    return objs;
  }

  /**
   * Refactors model classes. 1. Adds the @Buildable and @Inline annotations to
   * import list. 2. Stips dotted prefixes for name and moves them to the package.
   * 3. Set the `GENERATE_BUILDERS` property to the model so that template
   * rendering can use them.
   */
  private Map<String, Object> refactorModel(String pkg, Map<String, Object> model, Map<String, Object> models) {
    CodegenModel cm = (CodegenModel) model.getOrDefault("model", "");
    String importPath = (String) model.getOrDefault("importPath", "");
    String name = cm.name;
    String classname = cm.classname;

    if (generateBuilders) {
      List<Map<String, String>> imports = (List<Map<String, String>>) models.getOrDefault("imports",
          new ArrayList<Map<String, String>>());
      imports.add(new HashMap<String, String>() {
        {
          put("import", "io.sundr.builder.annotations.Buildable");
        }
      });

      imports.add(new HashMap<String, String>() {
        {
          put("import", "io.sundr.builder.annotations.Inline");
        }
      });

      models.put("imports", imports);
    }

    if (name.contains(".")) {

      String prefix = name.substring(0, name.lastIndexOf("."));
      String newPackage = pkg + "." + prefix;

      String newName = name.substring(name.lastIndexOf(".") + 1);
      cm.name = newName;
      cm.classname = newName;

      String newClassFilename = prefix.replaceAll("[\\.]", File.separator) + File.separator + cm.name;
      cm.classFilename = newClassFilename;

      String newImportPath = importPath.replace(pkg, newPackage).replace(classname, cm.classname);
      importMappings.put(name, newImportPath);
      classMappings.put(name, newName);

      model.put("importPath", newImportPath);
      models.put("package", newPackage);

      updateImports(knownModels, importPath, newImportPath);
      for (CodegenProperty cp : cm.vars) {
        cp.complexType = removePackage(cp.complexType, pkg, prefix);
        cp.baseType = removePackage(cp.baseType, pkg, prefix);
        cp.datatypeWithEnum = removePackage(cp.datatypeWithEnum, pkg, prefix);
        cp.defaultValue = removePackage(cp.defaultValue, pkg, prefix);
      }
    }
    models.put(GENERATE_BUILDERS, true);
    return model;
  }

  /**
   * Refactors operation classes. 1. Stips dotted prefixes for name and moves them
   * to the package. 2. Updates the model references.
   */
  private Map<String, Object> refactorOperation(String pkg, Map<String, Object> operation,
      Map<String, Object> operations) {
    String name = (String) operation.get("classname");
    if (name.contains(".")) {
      String prefix = name.substring(0, name.lastIndexOf("."));
      String newPackage = pkg + "." + prefix;
      String newName = name.substring(name.lastIndexOf(".") + 1);
      operation.put("classname", newName);
      operations.put("package", newPackage);
    }
    Object obj = operation.get("operation");
    if (obj instanceof Iterable) {
      for (Object o : (Iterable<? extends Object>) obj) {
        if (o instanceof CodegenOperation) {
          CodegenOperation co = (CodegenOperation) o;
          updateModelRefs(co);
          List<Map<String, String>> imports = (List<Map<String, String>>) operations.get("imports");
          for (String i : co.imports) {
            if (!importExists(i, imports)) {
              Map<String, String> importMap = new HashMap<>();
              importMap.put("import", i);
              imports.add(importMap);
            }
          }
        }
      }
    }
    return operation;
  }

  private static void updateImports(List<Map<String, Object>> models, String importPath, String newImportPath) {
    for (Map<String, Object> objs : models) {
      Object o = objs.getOrDefault("imports", Collections.emptyMap());
      if (o instanceof Map) {
        updateImports((Map) o, importPath, newImportPath);
      } else if (o instanceof Iterable) {
        for (Object i : (Iterable<? extends Object>) o) {
          if (i instanceof Map) {
            updateImports((Map) i, importPath, newImportPath);
          }
        }
      }
    }
  }

  private static void updateImports(Map<String, String> imports, String importPath, String newImportPath) {
    Map<String, String> copy = new HashMap<>(imports);
    for (Map.Entry<String, String> e : copy.entrySet()) {
      String key = e.getKey();
      String value = e.getValue();
      if (value.equals(importPath)) {
        imports.put(key, newImportPath);
      }
    }
  }

  private void updateModelRefs(CodegenOperation operation) {
    String returnType = operation.returnType;
    String returnBaseType = operation.returnBaseType;
    if (classMappings.get(returnBaseType) != null) {
      operation.returnBaseType = classMappings.get(returnBaseType);
    }
    if (importMappings.get(returnBaseType) != null) {
      operation.imports.add(importMappings.get(returnBaseType));
    }
    if (classMappings.get(returnType) != null) {
      operation.returnType = classMappings.get(returnType);
    }
    if (importMappings.get(returnType) != null) {
      operation.imports.add(importMappings.get(returnType));
    }

    for (CodegenParameter parameter : operation.allParams) {
      updateModelRefs(operation, parameter);
    }

    for (CodegenProperty property : operation.responseHeaders) {
      updateModelRefs(operation, property);
    }

    updateModelRefs(operation, operation.bodyParam);
    for (CodegenParameter parameter : operation.bodyParams) {
      updateModelRefs(operation, parameter);
    }

    for (CodegenParameter parameter : operation.formParams) {
      updateModelRefs(operation, parameter);
    }

    for (CodegenParameter parameter : operation.headerParams) {
      updateModelRefs(operation, parameter);
    }
  }

  private void updateModelRefs(CodegenOperation operation, CodegenProperty property) {
    if (property == null) {
      return;
    }
    String baseType = property.baseType;
    String datatype = property.datatype;
    String datatypeWithEnum = property.datatypeWithEnum;
    // baseType
    if (classMappings.get(baseType) != null) {
      property.baseType = classMappings.get(baseType);
    }
    if (importMappings.get(baseType) != null) {
      operation.imports.add(importMappings.get(baseType));
    }
    // dataType
    if (classMappings.get(datatype) != null) {
      property.datatype = classMappings.get(datatype);
    }
    if (importMappings.get(datatype) != null) {
      operation.imports.add(importMappings.get(datatype));
    }
    // dataTypeWithEnum
    if (classMappings.get(datatypeWithEnum) != null) {
      property.datatypeWithEnum = classMappings.get(datatypeWithEnum);
    }
    if (importMappings.get(datatypeWithEnum) != null) {
      operation.imports.add(importMappings.get(datatypeWithEnum));
    }

  }

  private void updateModelRefs(CodegenOperation operation, CodegenParameter parameter) {
    if (parameter == null) {
      return;
    }
    String baseType = parameter.baseType;
    String datatype = parameter.dataType;
    String datatypeWithEnum = parameter.datatypeWithEnum;
    // baseType
    if (classMappings.get(baseType) != null) {
      parameter.baseType = classMappings.get(baseType);
    }
    if (importMappings.get(baseType) != null) {
      operation.imports.add(importMappings.get(baseType));
    }
    // dataType
    if (classMappings.get(datatype) != null) {
      parameter.dataType = classMappings.get(datatype);
    }
    if (importMappings.get(datatype) != null) {
      operation.imports.add(importMappings.get(datatype));
    }
    // dataTypeWithEnum
    if (classMappings.get(datatypeWithEnum) != null) {
      parameter.datatypeWithEnum = classMappings.get(datatypeWithEnum);
    }
    if (importMappings.get(datatypeWithEnum) != null) {
      operation.imports.add(importMappings.get(datatypeWithEnum));
    }
  }

  /**
   * Add operation to group
   *
   * @param tag name of the tag
   * @param resourcePath path of the resource
   * @param operation Swagger Operation object
   * @param co Codegen Operation object
   * @param operations map of Codegen operations
   */
  @SuppressWarnings("static-method")
  public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co,
      Map<String, List<CodegenOperation>> operations) {
    String prefix = co.returnBaseType != null && co.returnBaseType.contains(".")
        ? co.returnBaseType.substring(0, co.returnBaseType.lastIndexOf("."))
        : "";

    String newTag = !prefix.isEmpty() ? prefix + "." + tag : tag;

    super.addOperationToGroup(newTag, resourcePath, operation, co, operations);
  }

  private static String removePackage(String name, String basePackage, String subPackage) {
    if (name == null || name.isEmpty()) {
      return name;
    }

    if (name.contains("<") && name.contains(">")) {
      String ref = name.substring(0, name.indexOf("<"));
      String[] args = name.substring(name.indexOf("<") + 1, name.lastIndexOf(">")).split(",");
      StringBuilder sb = new StringBuilder();
      sb.append(removePackage(ref, basePackage, subPackage)).append("<")
          .append(Strings.join(args, new Function<String, String>() {
            @Override
            public String apply(String s) {
              return removePackage(s, basePackage, subPackage);
            }
          }, ",")).append(">").append(name.substring(name.lastIndexOf(">") + 1));
      return sb.toString();
    }

    if (name.contains(".")) {
      String prefix = name.substring(0, name.lastIndexOf("."));
      String trimmed = name.substring(name.lastIndexOf(".") + 1);
      if (prefix.equals(subPackage)) {
        return trimmed;
      } else {
        return basePackage + "." + prefix + "." + trimmed;
      }
    }
    return name;
  }

  @Override
  public String toModelName(String name) {
    return name;
  }

  @Override
  public String toModelFilename(String name) {
    return name.replaceAll(Pattern.quote("."), File.separator);
  }

  @Override
  public String toModelTestFilename(String name) {
    return super.toModelTestFilename(name).replaceAll(Pattern.quote("."), File.separator);
  }

  @Override
  public String toModelImport(String name) {
    return super.toModelImport(name);
  }

  @Override
  public String toVarName(String name) {
    return super.toVarName(name);
  }

  @Override
  public String toBooleanGetter(String name) {
    return "is" + getterAndSetterCapitalize(name);
  }

  @Override
  public String toApiDocFilename(String name) {
    return super.toApiDocFilename(name);
  }

  @Override
  public String toApiName(String name) {
    return name;
  }

  @Override
  public String toApiFilename(String name) {
    return name.replaceAll(Pattern.quote("."), File.separator);
  }

  @Override
  public String toApiTestFilename(String name) {
    return super.toApiTestFilename(name).replaceAll(Pattern.quote("."), File.separator);
  }

  @Override
  public String toApiVarName(String name) {
    return super.toApiVarName(name);
  }

  @Override
  public String toApiImport(String name) {
    return super.toApiImport(name);
  }

  public boolean isGenerateBuilders() {
    return generateBuilders;
  }

  public void setGenerateBuilders(boolean generateBuilders) {
    this.generateBuilders = generateBuilders;
  }

  public boolean isEditableEnabled() {
    return editableEnabled;
  }

  public void setEditableEnabled(boolean editableEnabled) {
    this.editableEnabled = editableEnabled;
  }

  public boolean isValidationEnabled() {
    return validationEnabled;
  }

  public void setValidationEnabled(boolean validationEnabled) {
    this.validationEnabled = validationEnabled;
  }

  public boolean isLazyMapInitEnabled() {
    return lazyMapInitEnabled;
  }

  public void setLazyMapInitEnabled(boolean lazyMapInitEnabled) {
    this.lazyMapInitEnabled = lazyMapInitEnabled;
  }

  public boolean isGenerateBuilderPackage() {
    return generateBuilderPackage;
  }

  public void setGenerateBuilderPackage(boolean generateBuilderPackage) {
    this.generateBuilderPackage = generateBuilderPackage;
  }

  public String getBuilderPackage() {
    return builderPackage;
  }

  public void setBuilderPackage(String builderPackage) {
    this.builderPackage = builderPackage;
  }

  public String getBuilderArtifact() {
    return builderArtifact;
  }

  public void setBuilderArtifact(String builderArtifact) {
    this.builderArtifact = builderArtifact;
  }

  public String getBuilderGroupId() {
    return builderGroupId;
  }

  public void setBuilderGroupId(String builderGroupId) {
    this.builderGroupId = builderGroupId;
  }

  public String getBuilderArtifactId() {
    return builderArtifactId;
  }

  public void setBuilderArtifactId(String builderArtifactId) {
    this.builderArtifactId = builderArtifactId;
  }

  public String getBuilderVersion() {
    return builderVersion;
  }

  public void setBuilderVersion(String builderVersion) {
    this.builderVersion = builderVersion;
  }

  public String getBuilderClassifier() {
    return this.builderClassifier;
  }

  public void setBuilderClassifier(String builderClassifier) {
    this.builderClassifier = builderClassifier;
  }

  public Map<String, String> getImportMappings() {
    return importMappings;
  }

  public Map<String, String> getClassMappings() {
    return classMappings;
  }

  private static String className(String fqcn) {
    return !fqcn.contains(".") ? fqcn : fqcn.substring(fqcn.lastIndexOf(".") + 1);
  }

  private static boolean importExists(String candidate, List<Map<String, String>> imports) {
    return imports.stream().flatMap(i -> i.values().stream()).map(i -> className(i))
        .filter(i -> i.equals(className(candidate))).findAny().isPresent();
  }
}
