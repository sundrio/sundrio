package me.codegen.generator;

import me.builder.Builder;

public class CodeGeneratorBuilder<M> extends CodeGeneratorFluent<M, CodeGeneratorBuilder<M>> implements Builder<CodeGenerator> {

    public CodeGeneratorBuilder() {
    }
    
    public CodeGeneratorBuilder(CodeGenerator<M> instance) {
        withModel(instance.getModel());
        withWriter(instance.getWriter());
        withTemplateResource(instance.getTemplateResource());
        withDirectives(instance.getDirectives());
    }
    
    public CodeGenerator build() {
       return new CodeGenerator( getModel(), getWriter(), getTemplateResource(), getDirectives() );
    }
}