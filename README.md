# Mars Rover Assessment

Solution for [EventMobi Mars Rover Assessment](https://github.com/EventMobi/mars-rover-assessment) implemented in Java using a standard Maven project structure.

## Software dependencies
* Java Development Kit version 1.8+
* Apache Maven 3

## How to build
**Option 1:** Run the provided buid.sh script
```
./build.sh
```

**Oprion 2:** Run maven directly
```
mvn clean package
```
## How to run

**Option 1:** Run the provided execution script. Input is read from the console or from an input file is a path is provided as argument
```
./mars-rover-assessment.sh
```
```
./mars-rover-assessment.sh src/test/resources/standard-input.txt
```

**Option 2:** Run the java command directly. Input is read from the console or from an input file is a path is provided as argument
```
java -jar target/mars-rover-assessment-1.0.jar
```
```
java -jar target/mars-rover-assessment-1.0.jar src/test/resources/standard-input.txt
```
