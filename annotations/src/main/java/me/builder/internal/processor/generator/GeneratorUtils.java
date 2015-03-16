package me.builder.internal.processor.generator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public final class GeneratorUtils {

    public static final String DEFAULT_FLUENT_TEMPLATE_LOCATION = "templates/fluent.vm";
    public static final String DEFAULT_BUILDER_TEMPLATE_LOCATION = "templates/builder.vm";

    private GeneratorUtils() {
        //Utility Class
    }

    static File getPackage(File src, String packageName) {
        String[] directories = packageName.split(".");
        File current = src;
        for (String dir : directories) {
            current = new File(current, dir);
        }
        return current;
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

    static void createPackage(File src, String packageName) {
        String[] directories = packageName.split(".");
        if (!src.exists() && src.mkdirs()) {
            throw new IllegalStateException("Can't create directories for package:" + packageName);
        }
        File current = src;
        for (String dir : directories) {
            current = new File(current, dir);
            if (!current.exists() && current.mkdirs()) {
                throw new IllegalStateException("Can't create directories for package:" + packageName);
            }
        }
    }
}
