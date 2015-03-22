package me.codegen.directives;

import me.codegen.model.JavaProperty;
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

public class NestedClassDirective extends Directive {

    @Override
    public String getName() {
        return "nestedClass";
    }

    @Override
    public int getType() {
        return BLOCK;
    }
    
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        String block = "";
        JavaProperty property = null;
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) != null) {
                if (!(node.jjtGetChild(i) instanceof ASTBlock)) {
                    //reading and casting inline parameters
                    if (i == 0) {
                        property = (JavaProperty) node.jjtGetChild(i).value(context);
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
        String name = property.getNameCapitalized();
        String className = property.getType().getClassName();
        writer.append("\n\tpublic class ").append(name).append("Nested<N> extends ")
                .append(className).append("Fluent<").append(name).append("Nested<N>> implements Nested<N> {\n");
        writer.append("\t\tprivate final ").append(className).append("Builder builder = new ").append(className).append("Builder();\n");
        writer.append("\t\tpublic N end").append(name).append("() { return and(); }\n");
        writer.append("\t\tpublic N and() {\n");
        if (property.isArray()) {
            writer.append("\t\t\treturn (N) addTo").append(name).append("(builder.build());\n");
        } else {
            writer.append("\t\t\treturn (N) with").append(name).append("(builder.build());\n");
        }
        writer.append("\t\t}\n");
        writer.append(block).append("\t}\n");
        return true;
    }
}
