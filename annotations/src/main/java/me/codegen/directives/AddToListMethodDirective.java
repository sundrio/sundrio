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

public class AddToListMethodDirective extends Directive {

    private static final String FORMAT = "\tpublic T %s(%s item) {\n\t\tthis.%s.add(item);\n\t\treturn (T)this;\n\t}\n";
    @Override
    public String getName() {
        return "addToListMethod";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        JavaProperty property = (JavaProperty) node.jjtGetChild(0).value(context);
        String type = property.getType().getClassName();
        String methodName = "addTo" + property.getNameCapitalized();
        String name = property.getName();
        if (property.isArray()) {
            writer.write(String.format(FORMAT, methodName, type, name, name, name));
        }  else if (property.getType().isCollection() && (type.equals("Set") || type.equals("List"))) {
            type = property.getType().getGenericTypes()[0].getClassName();
            writer.write(String.format(FORMAT, methodName, type, name));
        }
        return true;
    }
}
