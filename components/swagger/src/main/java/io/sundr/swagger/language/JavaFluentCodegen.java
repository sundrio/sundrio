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

import io.sundr.Function;
import io.sundr.codegen.utils.StringUtils;
import io.swagger.codegen.v3.CliOption;
import io.swagger.codegen.v3.CodegenModel;
import io.swagger.codegen.v3.CodegenOperation;
import io.swagger.codegen.v3.CodegenProperty;
import io.swagger.codegen.v3.CodegenType;
import io.swagger.codegen.v3.CodegenParameter;
import io.swagger.codegen.v3.generators.java.JavaClientCodegen;
import io.swagger.v3.oas.models.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JavaFluentCodegen extends JavaClientCodegen {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaFluentCodegen.class);

    public static final String GENERATE_BUILDERS = "generateBuilders";

    protected boolean generateBuilders = true;

    public JavaFluentCodegen() {
        super();
        outputFolder = "generated-code" + File.separator + "java";
        embeddedTemplateDir = templateDir = "JavaFluent";
        invokerPackage = "io.swagger.client";
        artifactId = "swagger-java-client";
        apiPackage = "io.sundr.client.api";
        modelPackage = "io.sundr.client.model";

        cliOptions.add(CliOption.newBoolean(GENERATE_BUILDERS, "Whether to generate builders for model objects."));
    }

    @Override
    public void processOpts() {
        super.processOpts();
        if (additionalProperties.containsKey(GENERATE_BUILDERS)) {
            this.setGenerateBuilders(Boolean.valueOf(additionalProperties.get(GENERATE_BUILDERS).toString()));
        }

        final String invokerFolder = (sourceFolder + '/' + invokerPackage).replace(".", "/");
        final String modelFolder = (sourceFolder + '/' + modelPackage).replace(".", "/");
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

    public void setGenerateBuilders(boolean generateBuilders) {
        this.generateBuilders = generateBuilders;
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
            refactorModel(pkg, (Map<String, Object>) o,objs);
        } else if (o instanceof Iterable) {
            for (Object i : (Iterable<? extends Object>) o) {
              if  (i instanceof Map) {
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
            refactorOperation(pkg, (Map<String, Object>) o,objs);
        } else if (o instanceof Iterable) {
            for (Object i : (Iterable<? extends Object>) o) {
              if  (i instanceof Map) {
                  refactorOperation(pkg, (Map<String, Object>) i, objs);
              }
            }
        }
        return objs;
    }


  /**
   * Refactors model classes.
   * 1. Adds the @Buildable and @Inline annotations to import list.
   * 2. Stips dotted prefixes for name and moves them to the package.
   * 3. Set the `GENERATE_BUILDERS` property to the model so that template rendering can use them.
   */
    private Map<String, Object> refactorModel(String pkg, Map<String, Object> model, Map<String, Object> models) {
            CodegenModel cm = (CodegenModel) model.getOrDefault("model", "");
            String importPath = (String) model.getOrDefault("importPath", "");
            String name = cm.name;
            String classname = cm.classname;

            if (generateBuilders) {
                List<Map<String, String>> imports = (List<Map<String, String>>) models.getOrDefault("imports", new ArrayList<Map<String, String>>());
                imports.add(new HashMap<String, String>() {{
                    put("import", "io.sundr.builder.annotations.Buildable");
                }});

                imports.add(new HashMap<String, String>() {{
                    put("import", "io.sundr.builder.annotations.Inline");
                }});

                models.put("imports", imports);
            }

            if (name.contains(".")) {

                String prefix = name.substring(0, name.lastIndexOf("."));
                String newPackage = pkg + "." + prefix;

                String newName = name.substring(name.lastIndexOf(".") + 1);
                cm.name = newName;
                cm.classname = newName;

                String newClassFilename = prefix.replaceAll("[\\.]",File.separator) + File.separator + cm.name;
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
   * Refactors operation classes.
   * 1. Stips dotted prefixes for name and moves them to the package.
   * 2. Updates the model references.
   */
    private Map<String, Object> refactorOperation(String pkg, Map<String, Object> operation, Map<String, Object> operations) {
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
                    for (String i: co.imports) {
                        if (!importExists(i, imports))  {
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
              if  (i instanceof Map) {
                  updateImports((Map) i, importPath, newImportPath);
              }
            }
        }
       }
    }

    private static void updateImports(Map<String, String> imports, String importPath, String newImportPath) {
        Map<String ,String> copy = new HashMap<>(imports);
        for (Map.Entry<String, String> e : copy.entrySet())  {
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
        if (classMappings.get(returnBaseType) != null)  {
            operation.returnBaseType = classMappings.get(returnBaseType);
        }
        if (importMappings.get(returnBaseType) != null)  {
            operation.imports.add(importMappings.get(returnBaseType));
        }
        if (classMappings.get(returnType) != null)  {
            operation.returnType = classMappings.get(returnType);
        }
        if (importMappings.get(returnType) != null)  {
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

    private void updateModelRefs(CodegenOperation operation, CodegenProperty property)  {
        if  (property == null) {
            return;
        }
        String baseType = property.baseType;
        String datatype = property.datatype;
        String datatypeWithEnum = property.datatypeWithEnum;
        //baseType
        if (classMappings.get(baseType) != null)  {
            property.baseType = classMappings.get(baseType);
        }
        if (importMappings.get(baseType) != null) {
            operation.imports.add(importMappings.get(baseType));
        }
        //dataType
        if (classMappings.get(datatype) != null)  {
            property.datatype = classMappings.get(datatype);
        }
        if (importMappings.get(datatype) != null) {
            operation.imports.add(importMappings.get(datatype));
        }
        //dataTypeWithEnum
        if (classMappings.get(datatypeWithEnum) != null)  {
            property.datatypeWithEnum = classMappings.get(datatypeWithEnum);
        }
        if (importMappings.get(datatypeWithEnum) != null) {
            operation.imports.add(importMappings.get(datatypeWithEnum));
        }

    }

    private void updateModelRefs(CodegenOperation operation, CodegenParameter parameter)  {
        if  (parameter == null) {
            return;
        }
        String baseType = parameter.baseType;
        String datatype = parameter.dataType;
        String datatypeWithEnum = parameter.datatypeWithEnum;
        //baseType
        if (classMappings.get(baseType) != null)  {
            parameter.baseType = classMappings.get(baseType);
        }
        if (importMappings.get(baseType) != null) {
            operation.imports.add(importMappings.get(baseType));
        }
        //dataType
        if (classMappings.get(datatype) != null)  {
            parameter.dataType = classMappings.get(datatype);
        }
        if (importMappings.get(datatype) != null) {
            operation.imports.add(importMappings.get(datatype));
        }
        //dataTypeWithEnum
        if (classMappings.get(datatypeWithEnum) != null)  {
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
    public void addOperationToGroup(String tag, String resourcePath, Operation operation, CodegenOperation co, Map<String, List<CodegenOperation>> operations) {
        String prefix =  co.returnBaseType != null && co.returnBaseType.contains(".")
                ? co.returnBaseType.substring(0, co.returnBaseType.lastIndexOf("."))
                : "";

        String newTag = !prefix.isEmpty()
                ? prefix + "." + tag
                : tag;

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
            sb.append(removePackage(ref, basePackage, subPackage))
                    .append("<")
                    .append( StringUtils.join(args, new Function<String, String>() {
                @Override
                public String apply(String s) {
                    return removePackage(s, basePackage, subPackage);
                }
            }, ","))
                    .append(">")
            .append(name.substring(name.lastIndexOf(">") + 1));
            return sb.toString();
        }

        if  (name.contains(".")) {
            String prefix = name.substring(0 , name.lastIndexOf("."));
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

  private static String className(String fqcn) {
    return !fqcn.contains(".") ? fqcn : fqcn.substring(fqcn.lastIndexOf(".") + 1);
  }

  private static boolean importExists(String candidate, List<Map<String, String>> imports) {
    return imports.stream().flatMap(i -> i.values().stream())
      .map(i -> className(i))
      .filter(i -> i.equals(className(candidate)))
      .findAny()
      .isPresent();
  }


/*
        @Override
    public Map<String, Object> postProcessModels(Map<String, Object> objs) {
        String pkg = (String) objs.getOrDefault("package", "");

        Object o = objs.getOrDefault("models", Collections.emptyMap());
        if (o instanceof Map) {
            fixClassName((Map)o);
        } else if (o instanceof Iterable) {
            for (Object i : (Iterable<? extends Object>) o) {
              if  (i instanceof Map) {
                  fixClassName((Map)i);
              }
            }
        }

        return super.postProcessModels(objs);
    }

    public void fixClassName(Map<String, Object> map) {
        CodegenModel cm = (CodegenModel) map.getOrDefault("model", "");
        String name = cm.name;
         if (name != null && !name.isEmpty() && name.contains(".")) {
             cm.name = name.substring(name.lastIndexOf(".") + 1);
             cm.classname = cm.name;
        }
    }

    @Override
    public String toModelName(String name) {
            return name;
    }

    @Override
    public String toModelImport(String name) {
        return super.toModelImport(name);
    }



    @Override
    public String toModelFilename(String name) {
        if (name == null || name.isEmpty() || !name.contains(".")) {
            return super.toModelName(name);
        }
        String prefix = name.substring(0, name.lastIndexOf("."));
        return prefix.replaceAll("[\\.]",File.separator) + File.separator + name.substring(name.lastIndexOf(".") + 1);
    }

    @Override
    public String toBooleanGetter(String name) {
        return "is" + super.toBooleanGetter(name);
    }
    */
}
