package me.codegen.directives;

import me.Function;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;

import static me.codegen.utils.StringUtils.join;

public class MethodDirective extends Directive {

    @Override
    public String getName() {
        return "method";
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
        String block = "";
        JavaMethod method = null;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) != null) {
                if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
                    //reading and casting inline parameters
                    if (i == 0) {
                        method = (JavaMethod) node.jjtGetChild(i).value(context);
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
        writeMethod(writer, method, block);
        return true;
    }

    private void writeMethod(Writer writer, JavaMethod method, String block) throws IOException {
        if (method != null) {

            writer.append("public ")
                    .append(method.getReturnType().getSimpleName())
                    .append(" ")
                    .append(method.getName())
                    .append("(")
                    .append(join(method.getArguments(), JavaPropertyToString.INSTANCE, ", "))
                    .append(")");

            writeExceptions(writer, method);
            writer.append("{\n");
            writer.append(block).append("}\n");
        }
    }

    private void writeExceptions(Writer writer, JavaMethod method) throws IOException {
        if (method.getExceptions().size() > 0) {
            writer.append(" throws ").append(join(method.getExceptions(), ""));
        }

    }

    //Enum Singleton
    private enum JavaPropertyToString implements Function<JavaProperty, String> {
        INSTANCE;

        @Override
        public String apply(JavaProperty item) {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getType().getSimpleName()).append(" ").append(item.getName());
            return sb.toString();
        }
    }
}
