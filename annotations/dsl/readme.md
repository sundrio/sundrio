## DSL Annotations

The DSL annotations project provides a set of annotations, that can help you model your DSL and an annotation processor that will generate the required interfaces that define the DSL.

The idea is that you use the annotations in order to define a context free grammar, which is used as base for your DSL.

### Why?

Handcrafting generic-heavy interfaces in java gets old very fast. Interfaces that express a DSL are not easy to modify and often are pain to implement.
It's much easier to modify a context free grammar expressed by a set of annotated methods of a single interface.

### A quick taste

    @Dsl
    public interface MyCrudDSL {
    
        @EntryPoint                             //Entry point
        @Terminal                               //Terminal
        Entity create(Entity e);                //Code: Entity e = crud.create(someEntity);
        
        @EntryPoint                             //Entry point
        @Keyword("query")                       //Provides "Query" keyword
        void query();
        
        @Multiple                               //Can occur multiple times
        @All(keywords="Query")                  //Requires "Query" keyword
        void filter(String key, String value);
        
        @Terminal
        @All(keywords="Query")                  //Requires a method that provides: "Query" keyword
        List<Entity> list();
        
        @EntryPoint                             //Entry point
        @Named                                  //Provides "Named.class"
        void withName(String name);               
        
        @Terminal                               /Terminal method
        @All(keywords="Named")                  //Requires: "Named" keyword
        Entity delete();                        //Code: Entity removed = crud.withName("someName").delete();
        
        @Terminal                               //Terminal method
        @All(keywords="Named")                  //Requires: "Named" keyword
        Entity update(Entity e);                //Code: Entity removed = crud.withName("someName").delete();
    }

### Annotations

#### @Dsl

Each interface annotated with the @Dsl annotation will be processed and a DSL will be generated out of it.
For each method of the @Dsl annotated interface a new single-method interface will be generated, that will contain the annotated method (generified).
Each method may contain additional annotations that define the

- entry points
- the terminals
- and the transition rules

Based on the transition rules additional interfaces will be generate that will express the transitions.


#### @EntryPoint

It is used to define which methods are the entry points of the Dsl.

#### @InterfaceName

This annotation can be used to define the generated interface name. It can be used on the @Dsl annotated interface or on any of its methods. 

#### @MethodName

It can be used to define the generated interfaces method name. In most cases it shouldn't be needed. Could be used to avoid naming clashes within a single @Dsl annotated interface.