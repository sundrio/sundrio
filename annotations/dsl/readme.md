## DSL Annotations

The DSL annotations project provides a set of annotations, that can help you model your DSL and an annotation processor that will generate the required interfaces that define the DSL.

The idea is that you use the annotations in order to define a context free grammar, which is used as base for your DSL.

### Why?

Handcrafting generic-heavy interfaces in java gets old very fast. Interfaces that express a DSL are not easy to modify and often are pain to implement.
It's much easier to modify a context free grammar expressed by a set of annotated methods of a single interface.

### A quick glimpse on what it looks like.

The code below will generate a very simple DSL for crud operations:

    @Dsl
    public interface MyCrudDSL {
    
        @EntryPoint                             //Entry point
        @Terminal                               //Terminal
        Entity create(Entity e);                //Code: Entity e = crud.create(someEntity);
        
        @EntryPoint                             //Entry point
        void query();
        
        @Multiple                               //Can occur multiple times
        @All(keywords="query()")                //Requires "query()" method
        void filter(String key, String value);
        
        @Terminal
        @All(keywords="query()")                 //Requires a method that provides: "Query" keyword
        List<Entity> list();
        
        @EntryPoint                             //Entry point
        @Named                                  //Provides "Named.class"
        void withName(String name);               
        
        @Terminal                               /Terminal method
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

The transition rules are defined using annotations that "tag" methods with keywords and annotation that express requirements on those keywords.

#### @EntryPoint

It is used to define which methods are the entry points of the Dsl.

#### @Terminal

A terminal method of the DSL.


#### @Keyword

Can be used on any method to define that it provides one or more characteristics (e.g. "method is a list option").

It can also be used to create custom annotations, that can be used to define aggregate rules (e.g. "requires the list keyword or any of delete or update keyword"). More details on that later.

Note: all methods by will have at least the method name as a keyword. (e.g. "withName()")

#### @All

Can be used on any method to express it dependency on a group of keywords (as "requires all of the specified keywords"). 

#### @Any

Can be used on any method to express it dependency on a group of keywords (as "requires any of the specified keywords").

#### @None

Can be used on any method to express it dependency on a group of keywords (as "conflicts with all of the specified keywords").

#### @Only

Can be used on any method to express it dependency on a group of keywords (as "compatible only with the specified keywords").


#### @Or

When a method contains more than a single annotation the defines transition rules, the rules applied with a logical and. If the @Or annotation is present a logical or will be used instead.

#### @InterfaceName

This annotation can be used to define the generated interface name. It can be used on the @Dsl annotated interface or on any of its methods. 

#### @MethodName

It can be used to define the generated interfaces method name. In most cases it shouldn't be needed. Could be used to avoid naming clashes within a single @Dsl annotated interface.

### @Multiple

It can be used to specify that a method can be called multiple consecutive times.

### @Begin

Can be used to designate that the method creates a new scope. (More on scopes below).

### @End

Can be used to designate that the method ends a scope. (More on scopes below).

## Custom annotations

On more complex DSL it may worth creating custom keyword annotations. A custom keyword annotation can encapsulate one or more rules and act as way around the lack of annotation inheritance.
All of the rule defining annotations can be used on annotations too and the annotation processor will handle them. Also all annotations can be used with either string keywords or annotation classes, to support both styles of working.

## Scopes

There are times that you need a DSL inside a DSL. Why?

- To have a repeatable method groups.
- To forbid access to terminal methods until certain conditions are meet. (e.g. "if this X method has been called also require Y before calling Z")

Imaging the snippet below:

    List<Entity> list = crud.query().withNewFilter()
                            .withKey("key1","value1")            
                            .withNewFilter()    
                            .withValue("key2","value2")
                            .list();    

In this code withNewFilter() - withValue() define a scope.
Using scopes allows those methods to be repeatable (remember @Multiple is for single consecutive methods) and also prevents abnormal termination like calling the withNewFilter() and then list() before calling withValue() first.

A scope is treated as a single interface (also is implemented like one) that has all the keywords of the methods of the scope. The snipet above could be generated by something like:

    @Dsl
    public interface MyCrudDSL {
        
        @EntryPoint                             //Entry point
        void query();
        
        @Multiple                               //Can occur multiple times
        @Begin("Filter")                        //Begins a new scope
        @All(keywords="query()")                //Requires "query()" method
        void withNewFilter();
        
        @All(keywords="Filter")                 //Can only be used inside the scope
        void withKey(String key);
        
        @End("Filter")                          //Scope termination
        void withValue(String key);
              
        @Terminal
        @All(keywords="query()")                 //Requires a method that provides: "Query" keyword
        List<Entity> list();      
    }    


Regardless of the style of prefernce (e.g. method names, keywords or annotations) on thing remain the same: "You define on each methods its requirements". In other words you always define what the previous steps should or should not be.
There are times though tha



The [Curator DSL Example](../examples/curator/src/main/java/io/sundr/examples/curator/CuratorDsl.java) is using the string style.

The [Kubernetes DSL Example](../examples/kubernetes/src/main/java/io/sundr/examples/kuberentes/KubernetesDsl.java) is using the annotation style.