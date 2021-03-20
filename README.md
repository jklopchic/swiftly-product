# swiftly-product

<img src="https://ci.appveyor.com/api/projects/status/43iqt75vr3pb62gn?svg=true" alt="Project Badge" width="300">

## Overview

This project defines a library to be used to parse and ingest product data, with a robust model to allow for large extensibility.

## Usage

This is a maven project that you should be able to import by loading its pom.xml file.

You can run the main method in Main.java, and it will parse a copy of the sample input that I've put in src/test/java/resources/input.txt.

There isn't any serialization implemented, so if you want to view the parsed objects, I would suggest debugging and putting a breakpoint on the console print line in the main method.

Running the test in IntegrationTest.java will also verify that the example inputs work correctly. 

[![License: CC0-1.0](https://licensebuttons.net/l/zero/1.0/80x15.png)]
