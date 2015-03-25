package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaMethodFluent<T extends JavaMethodFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {
        private String name ;
        
	public T withName(String name) {
		this.name=name;
		return (T)this;
	}
                        
	public String getName() {
		return this.name;
	}
                private JavaType returnType ;
        
	public T withReturnType(JavaType returnType) {
		this.returnType=returnType;
		return (T)this;
	}
                        
	public JavaType getReturnType() {
		return this.returnType;
	}
                
	public ReturnTypeNested<T> addReturnType() {
		return new ReturnTypeNested<T>();
	}
        
	public class ReturnTypeNested<N> extends JavaTypeFluent<ReturnTypeNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endReturnType() { return and(); }
		public N and() {
			return (N) withReturnType(builder.build());
		}
	}
            private final List<JavaProperty> arguments = new ArrayList<>();
        
	public T withArguments(JavaProperty[] arguments) {
		for(JavaProperty item : arguments) {
			this.arguments.add(item);
		}
		return (T)this;
	}
        
	public T addToArguments(JavaProperty item) {
		this.arguments.add(item);
		return (T)this;
	}
        
	public JavaProperty[] getArguments() {
		return this.arguments.toArray(new JavaProperty[arguments.size()]);
	}
                
	public ArgumentsNested<T> addArguments() {
		return new ArgumentsNested<T>();
	}
        
	public class ArgumentsNested<N> extends JavaPropertyFluent<ArgumentsNested<N>> implements Nested<N> {
		private final JavaPropertyBuilder builder = new JavaPropertyBuilder(this);
		public N endArguments() { return and(); }
		public N and() {
			return (N) addToArguments(builder.build());
		}
	}
            private Set<JavaType> exceptions  = new LinkedHashSet();
        
	public T withExceptions(Set exceptions) {
		this.exceptions=exceptions;
		return (T)this;
	}
        
	public T addToExceptions(JavaType item) {
		this.exceptions.add(item);
		return (T)this;
	}
                
	public Set<JavaType> getExceptions() {
		return this.exceptions;
	}
                
	public ExceptionsNested<T> addExceptions() {
		return new ExceptionsNested<T>();
	}
        
	public class ExceptionsNested<N> extends JavaTypeFluent<ExceptionsNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endExceptions() { return and(); }
		public N and() {
			return (N) addToExceptions(builder.build());
		}
	}
    }