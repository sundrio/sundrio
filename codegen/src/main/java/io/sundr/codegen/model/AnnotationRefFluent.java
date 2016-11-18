package io.sundr.codegen.model;

import java.util.Map;
import java.lang.String;

import io.sundr.builder.Nested;

import java.lang.Object;

public interface AnnotationRefFluent<A extends AnnotationRefFluent<A>> extends AttributeSupportFluent<A> {


    public ClassRef getClassRef();

    public A withClassRef(ClassRef classRef);

    public AnnotationRefFluent.ClassRefNested<A> withNewClassRef();

    public AnnotationRefFluent.ClassRefNested<A> withNewClassRefLike(ClassRef item);

    public AnnotationRefFluent.ClassRefNested<A> editClassRef();

    public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRef();

    public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRefLike(ClassRef item);

    public A addToParameters(String key, Object value);

    public A addToParameters(Map<String, Object> map);

    public A removeFromParameters(String key);

    public A removeFromParameters(Map<String, Object> map);

    public Map<String, Object> getParameters();

    public A withParameters(Map<String, Object> parameters);

    public interface ClassRefNested<N> extends Nested<N>, ClassRefFluent<AnnotationRefFluent.ClassRefNested<N>> {


        public N and();

        public N endClassRef();
    }


}
