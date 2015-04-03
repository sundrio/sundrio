package me.codegen.directives;

import me.codegen.model.JavaProperty;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.Writer;

public class WithArrayMethodDirective extends Directive {
    
    private static final String FORMAT = "\n\tpublic T %s(%s[] %s) {\n\t\tthis.%s.clear();\n\t\tfor(%s item : %s) {\n\t\t\tthis.%s.add(item);\n\t\t}\n\t\treturn (T)this;\n\t}\n";
    @Override
    public String getName() {
        return "withArrayMethod";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException {
        JavaProperty property = (JavaProperty) node.jjtGetChild(0).value(context);
        String methodName = "with" + property.getNameCapitalized();
        String type = property.getType().getClassName();
        String name = property.getName();
        writer.write(String.format(FORMAT, methodName, type, name, name, type, name, name));
        return true;
    }
}
