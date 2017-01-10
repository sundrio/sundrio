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

import com.sun.tools.javac.code.Symbol;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import io.sundr.codegen.utils.IOUtils;
import io.sundr.resourcecify.annotations.Resourcecify;

import static io.sundr.codegen.utils.ModelUtils.getPackageName;

@SupportedAnnotationTypes("io.sundr.resourcecify.annotations.Resourcecify")
public class ResourcecifyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Filer filer = processingEnv.getFiler();

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                Resourcecify resourcecify = element.getAnnotation(Resourcecify.class);
                if (resourcecify == null) {
                    continue;
                }

                if (element instanceof Symbol.ClassSymbol) {
                    Symbol.ClassSymbol s = (Symbol.ClassSymbol) element;
                    try {
                        String packageName = getPackageName(s);

                        JavaFileObject source = s.sourcefile;
                        File sourceFile = new File(source.getName()).getAbsoluteFile();
                        String sourceFileName = sourceFile.getName();

                        FileObject target = filer.createResource(StandardLocation.CLASS_OUTPUT, packageName, sourceFileName, s);
                        IOUtils.copy(source, target);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
        return false;
    }

}
