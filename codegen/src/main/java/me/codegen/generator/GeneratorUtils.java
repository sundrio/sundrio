package me.codegen.generator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class GeneratorUtils {

    private GeneratorUtils() {
        //Utility Class
    }


    static void generate(VelocityContext context, Writer writer, Template template) {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(writer)
        ) {
            template.merge(context, bufferedWriter);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void generate(VelocityContext context, File dir, String fileName, Template template) {
        try (
                FileWriter fw = new FileWriter(new File(dir, fileName));
        ) {
            generate(context, fw, template);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
