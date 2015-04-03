package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaMethodFluent<T extends JavaMethodFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T>{

    private String name ;
    private JavaType returnType ;
    private List<JavaProperty> arguments  = new ArrayList();
    private Set<JavaType> exceptions  = new LinkedHashSet();

    public String getName(){
    return this.name;
    }
    public T withName(String name){
    this.name=name; return (T) this;
    }
    public JavaType getReturnType(){
    return this.returnType;
    }
    public T withReturnType(JavaType returnType){
    this.returnType=returnType; return (T) this;
    }
    public T withArguments(JavaProperty[] arguments){
    this.arguments.clear(); for (JavaProperty item :arguments){ this.arguments.add(item);} return (T) this;
    }
    public JavaProperty[] getArguments(){
    return this.arguments.toArray(new JavaProperty[arguments.size()]);
    }
    public Set<JavaType> getExceptions(){
    return this.exceptions;
    }
    public T withExceptions(Set<JavaType> exceptions){
    this.exceptions.clear();this.exceptions.addAll(exceptions); return (T) this;
    }
    public ReturnTypeNested<T> addReturnType(){
    return new ReturnTypeNested<T>();
    }
    public T addToArguments(JavaProperty item){
    this.arguments.add(item); return (T)this;
    }
    public ArgumentsNested<T> addArguments(){
    return new ArgumentsNested<T>();
    }
    public T addToExceptions(JavaType item){
    this.exceptions.add(item); return (T)this;
    }
    public ExceptionsNested<T> addExceptions(){
    return new ExceptionsNested<T>();
    }

    public class ReturnTypeNested<N> extends JavaTypeFluent<ReturnTypeNested<N>> implements Nested<N>{
    
            public N and(){
            return (N) withReturnType(new JavaTypeBuilder(this).build());
        }
            public N endReturnType(){
            return and();
        }
    
}
    public class ArgumentsNested<N> extends JavaPropertyFluent<ArgumentsNested<N>> implements Nested<N>{
    
            public N and(){
            return (N) addToArguments(new JavaPropertyBuilder(this).build());
        }
            public N endArguments(){
            return and();
        }
    
}
    public class ExceptionsNested<N> extends JavaTypeFluent<ExceptionsNested<N>> implements Nested<N>{
    
            public N and(){
            return (N) addToExceptions(new JavaTypeBuilder(this).build());
        }
            public N endExceptions(){
            return and();
        }
    
}


}
