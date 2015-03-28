package me.codegen.directives;

import me.Function;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaType;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;

import static me.codegen.utils.StringUtils.join;

public class ClassDirective extends Directive {

    private static final JavaType OBJECT_TYPE = new JavaType(JavaKind.CLASS, "java.lang", "Object", false, true, null, null, null, new JavaType[0], Collections.<String, Object>emptyMap());

    @Override
    public String getName() {
        return "class";
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
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

        if (clazz != null) {
            JavaType type = clazz.getType();
            JavaKind kind = type.getKind() != null ? type.getKind() : JavaKind.CLASS;

            writer.append("public ").append(kind.name().toLowerCase()).append(" ");
            writer.append(JavaTypeToString.INSTANCE.apply(type));
            if (type.getKind() == JavaKind.INTERFACE && !type.getInterfaces().isEmpty()) {
                writer.append(" extends ").append(join(type.getInterfaces(), JavaTypeToString.INSTANCE, ", "));
            }

            if (type.getSuperClass() != null && !OBJECT_TYPE.equals(type.getSuperClass())) {
                writer.append(" extends ").append(type.getSuperClass().getClassName());
                writer.append(" implements ").append(join(type.getInterfaces(), JavaTypeToString.INSTANCE, ", "));
            }

            writer.append("{");
            writer.append(block).append("}\n");
        }
        return true;
    }
    
    //Enum Singleton
    private enum JavaTypeToString implements Function<JavaType, String> {
        INSTANCE;
        @Override
        public String apply(JavaType item) {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getClassName());
            if (item.getGenericTypes() != null && item.getGenericTypes().length > 0) {
                sb.append("<").append(join(item.getGenericTypes(), ",")).append(">");
            }
            return sb.toString();
        }
    }
}
