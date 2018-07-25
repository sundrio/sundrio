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

package io.sundr.transform.internal;

import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.processor.JavaGeneratingProcessor;
import io.sundr.codegen.utils.ModelUtils;
import io.sundr.transform.annotations.VelocityTransformation;
import io.sundr.transform.annotations.VelocityTransformations;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes( { "io.sundr.transform.annotations.VelocityTransformation", "io.sundr.transform.annotations.VelocityTransformations" } )
public class VelocityTransformationProcessor extends JavaGeneratingProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Elements elements = processingEnv.getElementUtils();
        Types types = processingEnv.getTypeUtils();
        Filer filer = processingEnv.getFiler();
        CodegenContext.create(elements, types);

        for (TypeElement typeElement : annotations) {
            for (Element element : env.getElementsAnnotatedWith(typeElement)) {
                VelocityTransformations transformations = element.getAnnotation(VelocityTransformations.class);
                VelocityTransformation transformation = element.getAnnotation(VelocityTransformation.class);

                List<VelocityTransformation> all = new ArrayList<>();
                if (transformation != null) {
                    all.add(transformation);
                }

                if (transformations != null) {
                    for (VelocityTransformation t : transformations.value()) {
                        all.add(t);
                    }
                }

                TypeDef def = new TypeDefBuilder(ElementTo.TYPEDEF.apply(ModelUtils.getClassElement(element)))
                        .build();


                for (VelocityTransformation t : all) {
                    String template = t.value();
                    try {

                        generateFromStringTemplateAndDetermineOutput(def, readTemplate(filer, def.getPackageName(), template));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return false;
    }

    private String readTemplate(Filer filer, String pkg, String template) throws IOException {
        FileObject o;
        if (template == null) {
            throw new IllegalArgumentException("Template in:" + VelocityTransformation.class.getName() + " cannot be null.");
        }

        String targetPkg = template != null && template.startsWith("/") ? "" : pkg;
        String targetTemplate = template.startsWith("/") ? template.substring(1) : template;

        try {
            o = filer.getResource(StandardLocation.SOURCE_PATH, targetPkg, targetTemplate);
        } catch (IOException e) {
            try {
                o = filer.getResource(StandardLocation.CLASS_PATH, targetPkg, targetTemplate);
            } catch (IOException ex) {
                throw e;
            }
        }
        if (o == null) {
            throw new IOException("Template resource: " + template+ " couldn't be found in sources or classpath.");
        }
        return o.getCharContent(false).toString();
    }

}
