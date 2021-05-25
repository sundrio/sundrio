/*
 * Copyright 2015 The original authors.
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

package io.sundr.codegen.processor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.FilerException;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import io.sundr.codegen.generator.CodeGeneratorBuilder;
import io.sundr.codegen.generator.CodeGeneratorContext;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.utils.Patterns;

public abstract class JavaGeneratingProcessor extends AbstractProcessor {

  private static final String SOURCE_SUFFIX = ".java";

  public static final String PACKAGE = ".*package\\s+(.*)\\s*\\;";
  public static final String CLASS_NAME = ".*(enum|class|interface)\\s+(\\w+).*\\{";

  protected CodeGeneratorContext context = new CodeGeneratorContext();

  /**
   * Generates a source file from the specified {@link io.sundr.model.TypeDef}.
   *
   * @param model The model of the class to generate.
   * @param resourceName The template to use.
   * @throws IOException If it fails to create the source file.
   */
  public void generateFromResources(TypeDef model, String resourceName) throws IOException {
    try {
      generateFromResources(model, processingEnv
          .getFiler()
          .createSourceFile(model.getFullyQualifiedName()), resourceName);
    } catch (FilerException e) {
      // TODO: Need to avoid duplicate interfaces here.
    }
  }

  /**
   * Generates a source file from the specified {@link io.sundr.model.TypeDef}.
   *
   * @param model The model of the class to generate.
   * @param fileObject Where to save the generated class.
   * @param resourceName The template to use.
   * @throws IOException If it fails to create the source file.
   */
  public void generateFromResources(TypeDef model, JavaFileObject fileObject, String resourceName) throws IOException {
    if (classExists(model)) {
      printSkipping(model.getFullyQualifiedName(), "Class already exists.");
      return;
    }
    try (Writer writer = fileObject.openWriter()) {
      new CodeGeneratorBuilder<TypeDef>()
          .withContext(context)
          .withModel(model)
          .withWriter(writer)
          .withTemplateResource(resourceName)
          .build()
          .generate();
    }
  }

  /**
   * Generates a source file from the specified {@link io.sundr.model.TypeDef}.
   *
   * @param model The model of the class to generate.
   * @param content The template to use.
   * @throws IOException If it fails to create the source file.
   */
  public void generateFromStringTemplate(TypeDef model, String[] parameters, String content) throws IOException {
    TypeDef newModel = createTypeFromTemplate(model, parameters, content);
    if (processingEnv.getElementUtils().getTypeElement(newModel.getFullyQualifiedName()) != null) {
      printSkipping(newModel.getFullyQualifiedName(), "Class already exists.");
      return;
    }
    if (classExists(newModel)) {
      printSkipping(newModel.getFullyQualifiedName(), "Class already exists.");
      return;
    }
    generateFromStringTemplate(model, parameters, processingEnv.getFiler().createSourceFile(newModel.getFullyQualifiedName()),
        content);
  }

  /**
   * Generates a source file from the specified {@link io.sundr.model.TypeDef}.
   *
   * @param model The model of the class to generate.
   * @param parameters The external parameters to pass to the template.
   * @param fileObject Where to save the generated class.
   * @param content The template to use.
   * @throws IOException If it fails to create the source file.
   */
  public <T> void generateFromStringTemplate(T model, String[] parameters, FileObject fileObject, String content)
      throws IOException {
    if (fileObject.getName().endsWith(SOURCE_SUFFIX)) {
      TypeDef newModel = createTypeFromTemplate(model, parameters, content);
      if (processingEnv.getElementUtils().getTypeElement(newModel.getFullyQualifiedName()) != null) {
        printSkipping(fileObject.getName(), "File already exists.");
        return;
      }
      if (classExists(newModel)) {
        printSkipping(newModel.getFullyQualifiedName(), "Class already exists.");
        return;
      }
    }
    try (Writer writer = fileObject.openWriter()) {
      new CodeGeneratorBuilder<T>()
          .withContext(context)
          .withModel(model)
          .withParameters(parameters)
          .withWriter(writer)
          .withTemplateContent(content)
          .build()
          .generate();
    }
  }

  /**
   * Generates a source file from the specified {@link io.sundr.model.TypeDef}.
   *
   * @param model The model of the class to generate.
   * @param outputPath Where to save the generated class.
   * @param content The template to use.
   * @throws IOException If it fails to create the source file.
   */
  public <T> void generateFromStringTemplate(T model, String outputPath, String content) throws IOException {
    generateFromStringTemplate(model, new String[0], outputPath, content);
  }

  /**
   * Generates a source file from the specified {@link io.sundr.model.TypeDef}.
   *
   * @param model The model of the class to generate.
   * @param outputPath Where to save the generated class.
   * @param content The template to use.
   * @throws IOException If it fails to create the source file.
   */
  public <T> void generateFromStringTemplate(T model, String parameters[], String outputPath, String content)
      throws IOException {
    if (model instanceof TypeDef) {
      generateFromStringTemplate((TypeDef) model, parameters, content);
    } else if (outputPath == null || outputPath.isEmpty()) {
      throw new IllegalArgumentException(
          "Please specify either an outout path or a model that implies one (e.g. a class definition).");
    } else if (outputPath.endsWith(SOURCE_SUFFIX)) {
      String fqcn = outputPath.substring(0, outputPath.length() - SOURCE_SUFFIX.length()).replaceAll("/|\\\\", ".");
      generateFromStringTemplate(model, parameters, processingEnv.getFiler().createSourceFile(fqcn), content);
    } else {
      generateFromStringTemplate(model, parameters,
          processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", outputPath), content);
    }
  }

  /**
   * Generate a {@link TypeDef} from the specified model, parameters and template.
   */
  public <T> TypeDef createTypeFromTemplate(T model, String[] parameters, String content) {
    try (StringWriter writer = new StringWriter()) {
      new CodeGeneratorBuilder<T>()
          .withContext(context)
          .withModel(model)
          .withParameters(parameters)
          .withWriter(writer)
          .withTemplateContent(content)
          .build()
          .generate();

      ByteArrayInputStream bis = new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8));
      return parseTypeDef(bis);
    } catch (IOException e) {
      return null;
    }
  }

  private static TypeDef parseTypeDef(InputStream is) {
    String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
        .collect(Collectors.joining("\n"));
    return parseTypeDef(text);
  }

  private static TypeDef parseTypeDef(String text) {
    String pkg = Patterns.match(text, PACKAGE).orElse(null);
    String name = Patterns.match(text, CLASS_NAME, 2)
        .orElseThrow(() -> new IllegalStateException("Cannot extract fully qualified name from generated code."));
    return new TypeDefBuilder().withPackageName(pkg).withName(name).build();
  }

  /**
   * Checks if class already exists.
   *
   * @param typeDef The type definition to check if exists.
   * @return True if class can be found, false otherwise.
   */
  private static boolean classExists(TypeDef typeDef) {
    try {
      Class.forName(typeDef.getFullyQualifiedName());
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private void printSkipping(String name, String msg) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER,
        String.format("Skipping: %s. %-120s", name, msg));
  }
}
