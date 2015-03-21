package me.codegen.directives;

import me.codegen.model.JavaClazz;
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

public class ClassDirective extends Directive {

    private static final JavaType OBJECT_TYPE = new JavaType("java.lang", "Object", false, true, null, null, new JavaType[0], Collections.<String, Object>emptyMap());
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
                
        writer.append("public class ").append(clazz.getType().getClassName());
        if (clazz.getType().getSuperClass() != null && !OBJECT_TYPE.equals(clazz.getType().getSuperClass())) {
            writer.append(" extends ").append(clazz.getType().getSuperClass().getClassName());
        }
        writer.append("{");
        writer.append(block).append("}\n");
        return true;
    }
}
