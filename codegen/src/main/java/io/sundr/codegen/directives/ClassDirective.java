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

package io.sundr.codegen.directives;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;

import static io.sundr.codegen.utils.StringUtils.join;

public class ClassDirective extends Directive {

    private static final JavaType OBJECT_TYPE = new JavaType(JavaKind.CLASS, "java.lang", "Object", false, false, true, null, null, null, new JavaType[0], Collections.<String, Object>emptyMap());
    private static final String MEMBER_OF = "MEMBER_OF";
    private static final String ABSTRACT = " abstract ";
    private static final String EXTENDS = " extends ";
    private static final String IMPLEMENTS = " implements ";
    private static final String PUBLIC = "public ";
    private static final String CLASS = "class";
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private static final String NEWLINE = "\n";
    private static final String BRACKETS_LR = "[]";

    private static final String SQUIGGLE_L = "{";
    private static final String SQUIGGLE_R = "}";

    private static final String LT = "<";
    private static final String GT = ">";


    @Override
    public String getName() {
        return CLASS;
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
        String block = "";
        JavaClazz clazz = null;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) != null) {
                if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
                    //reading and casting inline parameters
                    if (i == 0) {
                        clazz = (JavaClazz) node.jjtGetChild(i).value(context);
                    } else {
                        break;
                    }
                } else {
                    //reading block content and rendering it
                    StringWriter blockContent = new StringWriter();
                    node.jjtGetChild(i).render(context, blockContent);

                    block = blockContent.toString();
                    break;
                }
            }
        }
        writeClazz(writer, clazz, block);
        return true;
    }

    private void writeClazz(Writer writer, JavaClazz clazz, String block) throws IOException {
        JavaTypeToString toString = new JavaTypeToString(getEnclosingType(clazz));

        if (clazz != null) {
            JavaType type = clazz.getType();
            JavaKind kind = type.getKind() != null ? type.getKind() : JavaKind.CLASS;

            writer.append(PUBLIC);
            if (kind == JavaKind.CLASS && !clazz.getType().isConcrete()) {
                writer.append(ABSTRACT);
            }
            writer.append(kind.name().toLowerCase()).append(SPACE);
            writer.append(toString.apply(type));

            writeExtends(writer, type, toString);
            writeImplements(writer, type, toString);

            writer.append(SQUIGGLE_L).append(NEWLINE);
            writer.append(block).append(NEWLINE).append(SQUIGGLE_R).append(NEWLINE);
        }
    }

    private void writeExtends(Writer writer, JavaType type, Function<JavaType, String> toString) throws IOException {
        if (type.getKind() != JavaKind.INTERFACE) {
            if (type.getSuperClass() != null && !OBJECT_TYPE.equals(type.getSuperClass())) {
                writer.append(EXTENDS).append(toString.apply(type.getSuperClass()));
            }
        } else {
            if (type.getInterfaces().size() > 0) {
                writer.append(EXTENDS).append(join(type.getInterfaces(), toString, COMMA));
            }
        }
    }

    private void writeImplements(Writer writer, JavaType type, Function<JavaType, String> toString) throws IOException {
        if (type.getKind() != JavaKind.INTERFACE && type.getInterfaces().size() > 0) {
                writer.append(IMPLEMENTS).append(join(type.getInterfaces(), toString, COMMA));
            }
    }

    private static JavaType getEnclosingType(JavaClazz clazz) {
        Object obj = clazz.getAttributes().get(MEMBER_OF);
        if (obj instanceof JavaType) {
            return (JavaType) obj;
        } else {
            return clazz.getType();
        }
    }


    private static class JavaTypeToString implements Function<JavaType, String> {

        private final JavaType enclosingType;

        JavaTypeToString(JavaType enclosingType) {
            this.enclosingType = enclosingType;
        }

        @Override
        public String apply(JavaType item) {
            StringBuilder sb = new StringBuilder();
            if (item.getClassName().equals(enclosingType.getClassName())
                    && !item.getFullyQualifiedName().equals(enclosingType.getFullyQualifiedName())) {
                sb.append(item.getFullyQualifiedName());
            } else sb.append(item.getClassName());
            if (item.isArray()) {
                sb.append(BRACKETS_LR);
            }
            if (item.getKind() == JavaKind.GENERIC && item.getSuperClass() != null) {
                sb.append(EXTENDS).append(apply(item.getSuperClass()));
            }
            if (item.getGenericTypes() != null && item.getGenericTypes().length > 0) {
                sb.append(LT).append(join(item.getGenericTypes(), this, COMMA)).append(GT);
            }
            return sb.toString();
        }
    }
}
