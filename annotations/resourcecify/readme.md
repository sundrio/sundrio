# Resourcecify

There are some rare cases where you want to package a source file as is inside a jar.
One options is to place it under resources, but in this case the file won't be compiled, opening up the possibility of errors.
Also, refactorings won't be applied to that file (though it depends on the tool and type of refactoring).

This annotation processors allows you to copy any source file of your choice in the jar as is.

**Note:** This is something completely useless to 99% and might make sense to tooling developers.

## Usage
This module introduces `@Resourcecify` which can be added on top of any type. 
The source file of the annotated type will be copied inside the jar.
