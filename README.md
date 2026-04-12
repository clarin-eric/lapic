# Linkchecker API Client (lapic)

## Goal of the project
The goal of the project is to create a simple shell client to access the 
[link checker API](https://linkchecker.clarin.eu/api/swagger-ui/index.html). 
Ideally every API call should have its corresponding client command.  

## System Requirements
The program requires a JDK version 21 or higher for building and a JRE version 21 or higher for running. 

## Getting the program
### Download the source code
Either clone the github repository or download the compressed file, unzip at and cd to the lapic directory. 
There execute the command 

### Adapting the configuration file

### Building the jar
The source code is bundled with a maven wrapper. Move to the lapic directory and execute 
```
./mvnw clean install
```
This will create a target directory with a file lapic-<version>.jar

## Running the program
```
java -jar lapic-<version>.jar
```


