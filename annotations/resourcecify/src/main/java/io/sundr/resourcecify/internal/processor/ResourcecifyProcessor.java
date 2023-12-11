/*
 *      Copyright 2017 The original authors.
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

package io.sundr.resourcecify.internal.processor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import io.sundr.resourcecify.annotations.Resourcecify;

@SupportedAnnotationTypes("io.sundr.resourcecify.annotations.Resourcecify")
public class ResourcecifyProcessor extends AbstractProcessor {

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
    Filer filer = processingEnv.getFiler();

    for (TypeElement typeElement : annotations) {
      for (Element element : env.getElementsAnnotatedWith(typeElement)) {
        Resourcecify resourcecify = element.getAnnotation(Resourcecify.class);
        if (resourcecify == null) {
          continue;
        }

        if (element instanceof TypeElement) {
          TypeElement s = (TypeElement) element;
          try {
            String packageName = getPackageName(s);
            String className = s.getSimpleName().toString();
            FileObject source = filer.getResource(StandardLocation.SOURCE_PATH, packageName, className + ".java");
            File sourceFile = new File(source.getName()).getAbsoluteFile();
            String sourceFileName = sourceFile.getName();

            FileObject target = filer.createResource(StandardLocation.CLASS_OUTPUT, packageName, sourceFileName, s);
            copy(source, target);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

      }
    }
    return false;
  }

  //
  //
  // The utilities below are duplicate. We have them here so that this processor doesn't depend on any other sundrio module (avoid cyclic refs).
  //
  //
  public static String getPackageName(Element element) {
    return getPackageElement(element).getQualifiedName().toString();
  }

  public static PackageElement getPackageElement(Element element) {
    if (element instanceof PackageElement) {
      return (PackageElement) element;
    } else {
      return getPackageElement(element.getEnclosingElement());
    }
  }

  /**
   * Copy one {@link FileObject} into an other.
   *
   * @param source The source {@link FileObject}.
   * @param target The target {@link FileObject}.
   * @throws IOException
   */
  public static void copy(FileObject source, FileObject target) throws IOException {
    InputStream in = source.openInputStream();
    OutputStream out = target.openOutputStream();
    try {
      byte[] buffer = new byte[1024];
      int len = in.read(buffer);
      while (len != -1) {
        out.write(buffer, 0, len);
        len = in.read(buffer);
      }
    } finally {
      in.close();
      out.close();
    }
  }
}
