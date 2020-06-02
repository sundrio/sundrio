# Shapes extension

The purpose of this example is to demonstrate how to combine use the @Buildable annotations across multiple projects.

# Using BuilderReference

As the builder annotations are not retained insdie the class, it is not possible for a module to know if the annotations is present in classes of another module.

In this example the annotation processor trigerred by `Triangle` doesn't know that `AbstractShape` is also @Buildable. 
We address this problem by adding a @BuildableReference for the `AbstractShape` class.