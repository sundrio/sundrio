# Template Transformations

Boilerplate code doesn't stop at builders. There is often need for `Factory`, `Manager`, `Handler`, `Wrapper` and so on, that needs to be created per object of a specific domain.
Wouldn't it be great if you could instead create a template and have the boilerplate get automatically generated for you?

There are tons of template engines out there, but they require a couple of things:

- A way to hook them into the build.
- A way to model your classes and feed them to the template.

This is complicates things enough to make writing boilerplate code seem more appealing! 

Simplifying the process described above is the goal of this module....

## Usage
This module introduces `@TemplateTrasnformation` which accepts the path of a template and can be used to annotate any type.
Upon compilation the annotated class will be passed to the Template Context using the key `model`. Then the template will be rendered
and the output will be stored inside a new source file. The path of the source file will be determined by the fully qualified name of the generated class and the standard generated source location.

To get access to the `@TemplateTransformation` you need the following dependency:

```xml
    <dependency>
        <groupId>io.sundr</groupId>
        <artifactId>transform-annotations</artifactId>
        <version>${sundrio.version}</version>
    </dependency>
```

At the moment the supported template engines are:

- Apache Velocity 
- String Template

However, an api/spi is provided that allows easily connecting any other kind of template engines.

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

We can now use the `@TemplateTransformation` to annotate any type:

    
    @TemplateTransformation("transformation.vm")
    public class SomeClass {
       //some code
    }

This will result in the generation of a new class called `SomeClassSingleton` in the same package.

Note: The name and the package are determined by the output class.


### Template Engines

#### Velocity 

To use Apache Velocity as the template engine, you need to add:

```xml
    <dependency>
        <groupId>io.sundr</groupId>
        <artifactId>sundr-codegen-velocity</artifactId>
        <version>${sundrio.version}</version>
    </dependency>
```

If you prefer to have velocity and its transitive dependencies shaded into the artifact (to avoid dependency management headaches) you can use instead:

```xml
    <dependency>
        <groupId>io.sundr</groupId>
        <artifactId>sundr-codegen-velocity-nodeps</artifactId>
        <version>${sundrio.version}</version>
    </dependency>
```

#### String Template

To use String Template 4 as the template engine, you need to add:

```xml
    <dependency>
        <groupId>io.sundr</groupId>
        <artifactId>sundr-codegen-st4</artifactId>
        <version>${sundrio.version}</version>
    </dependency>
```

If you prefer to have String Template 4 and its transitive dependencies shaded into the artifact (to avoid dependency management headaches) you can use instead:

```xml
    <dependency>
        <groupId>io.sundr</groupId>
        <artifactId>sundr-codegen-st4-nodeps</artifactId>
        <version>${sundrio.version}</version>
    </dependency>
```

### Template placement

There are three ways to pass your templates to the annotation:

- as local resources 
- as local resources relative to the package
- as classpath resources

#### Local resources

In this setup, you just need to put the templates under `src/main/resources` and reference them as `/my-template.vm`.


    package your.package.here;
    
    @TemplateTransformation("/my-template.vm")
    public class MyClass {
        //some code
    }

**Note:** The leading `/` is indicates that we want to try and read the template from the local root (a.k.a `target/classes`). The name and the suffix of the template can be anything you choose.

#### Local resource relative to the path.

In this setup, you just need to put the template under `src/main/resources/<your>/<package>/<here>`. Then you can reference it realtively to your package. For example:

    package your.package.here;
    
    @TemplateTransformation("my-template.vm")
    public class MyClass {
       //some code
    }
    
#### As classpath resource    

It can work in the same manner as described above in the `local` setups. The only real difference is that the template lives in an other artifact that is present in the classpath.

## Using multiple transformations per class.

In the case were we want more than one transformation per class, we can wrap them inside `@TemplateTransformations`. For example:

    package your.package.here;
    
    @TemplateTransformations({
        @TemplateTransformation("factory.vm"),
        @TemplateTransformation("manager.vm")
    })
    public class MyClass {
       //some code
    }
    
