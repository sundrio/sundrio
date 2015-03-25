package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

public class JavaPropertyFluent<T extends JavaPropertyFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {
        private JavaType type ;
        
	public T withType(JavaType type) {
		this.type=type;
		return (T)this;
	}
                        
	public JavaType getType() {
		return this.type;
	}
                
	public TypeNested<T> addType() {
		return new TypeNested<T>();
	}
        
	public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {
		private final JavaTypeBuilder builder = new JavaTypeBuilder(this);
		public N endType() { return and(); }
		public N and() {
			return (N) withType(builder.build());
		}
	}
            private String name ;
        
	public T withName(String name) {
		this.name=name;
		return (T)this;
	}
                        
	public String getName() {
		return this.name;
	}
                private boolean array ;
        
	public T withArray(boolean array) {
		this.array=array;
		return (T)this;
	}
                        
	public boolean isArray() {
		return this.array;
	}
        }