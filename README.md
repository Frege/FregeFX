# FregeFX
Frege language binding and utilities to create JavaFX user interfaces

## Prerequisites

Java8_u40 or higher (which includes JavaFX 8, which we need here)

## Usage

Run the example via
    
    gradlew run

Known uses:

* [The FregeFX repl](https://github.com/Dierk/frepl-gui)
* The [animated image viewer](https://github.com/Dierk/fregeTutorial/blob/master/src/main/frege/CoverFlow.fr) 
  in the [Frege Tutorial](https://github.com/Dierk/fregeTutorial/),  
  [video](https://www.youtube.com/watch?v=pxKJ_KPLml8)
* Purely Functional Doodle [video](https://www.youtube.com/watch?v=9V7w-RSC_1A)
* Ant Colony STM Demo [video](https://www.youtube.com/watch?v=mu6urVc2Z8Q)
  
## Build & Install

For local build and install use
 
    gradlew install
    
For running the contained `example` project use
    
    gradlew run
    
## Release Notes

### Release 0.3.1

The first release to support the 3.24-7.x Frege compiler.
    
The API has been to a large extend commented out as it will change with upcoming changes to the
Frege compiler and native declarations where things will get dramatically simpler. 
Anyway, enough is left to support the 
[Frege Tutorial](https://github.com/Dierk/fregeTutorial)

All compilation targets Java 7 (even though you need to compile and run with Java 8).
The user of this library also needs to compile with the same setting, i.e.:

* use Java 8 for compiling and running
* set target=1.7 for both, the Java and Frege compiler
* use the 3.24-7.30 Frege compiler.

# Copyright and License

Copyright (c) Dierk König, 2016. All rights reserved.
The use and distribution terms for this software are covered by the
[BSD 3-clause license](http://opensource.org/licenses/BSD-3-Clause)
which can be found in the file LICENSE.txt at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by the terms of this license.
You must not remove this notice, or any other, from this software.
