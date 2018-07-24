# Velocity Transformations

Boilerplate code doesn't stop at builders. There is often need for `Factory`, `Manager`, `Handler`, `Wrapper` and so on, that needs to be created per object of a specific domain.
Wouldn't it be great if you could instead create a template and have the boilerplate get automatically generated for you?

There are tons of template engines out there, but they require a couple of things:

- A way to hook them into the build.
- A way to model your classes and feed them to the template.

This is complicates things enough to make writing boilerplate code seem more appealing! 

Simplifying the process described above is the goal of this module....

## Usage
This module introduces `@VelocityTrasnformation` which accepts the path of velocity template and can be used to annotate any type.
Upon compilation the annotated class will be passed to the Velocity Context using the key `model`. Then the template will be rendered
and the output will be stored inside a new source file. The path of the source file will be determined by the fully qualified name of the generated class and the standard generated source location.

### Example

As an example let's generate a Singleton.

The first thing we need is a template:

    package ${model.packageName};
    
    public class ${model.name}Singleton {
    
       private static ${model.name} instance;
       
       public static synchronized ${model.name} getInstance() {
          if (instance == null) {
              instance = new ${model.name}();
          }
          return instance;
       }
    }

We can now use the `@VelocityTransformation` to annotate any type:

    
    @VelocityTransformation("transformation.vm")
    public class SomeClass {
       //some code
    }

This will result in the generation of a new class called `SomeClassSingleton` in the same package.

Note: The name and the package are determined by the output class.

### Template placement

There are three ways to pass your templates to the annotation:

- as local resources 
- as local resources relative to the package
- as classpath resources

#### Local resources

In this setup, you just need to put the templates under `src/main/resources` and reference them as `/my-template.vm`.


    package your.package.here;
    
    @VelocityTransformation("/my-template.vm")
    public class MyClass {
        //some code
    }

**Note:** The leading `/` is indicates that we want to try and read the template from the local root (a.k.a `target/classes`). The name and the suffix of the template can be anything you choose.

#### Local resource relative to the path.

In this setup, you just need to put the template under `src/main/resources/<your>/<package>/<here>`. Then you can reference it realtively to your package. For example:

    package your.package.here;
    
    @VelocityTransformation("my-template.vm")
    public class MyClass {
       //some code
    }
    
#### As classpath resource    

It can work in the same manner as described above in the `local` setups. The only real difference is that the template lives in an other artifact that is present in the classpath.

## Using multiple transformations per class.

In the case were we want more than one transformation per class, we can wrap them inside `@VelocityTransformations`. For example:

    package your.package.here;
    
    @VelocityTransformations({
        @VelocityTransformation("factory.vm"),
        @VelocityTransformation("manager.vm")
    })
    public class MyClass {
       //some code
    }
    