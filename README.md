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

### Adapting the configuration file
The configuration file is in the lapic directory under <lapicdirectory>/src/main/resources/application.yml 
```
spring:
  shell:
    interactive:
      enabled: false #(1)
  application:
    name: lapic
lapic:
  user:
    name: ${USER_NAME} #(2)
    password: ${USER_PASSWORD} #(3)
  base-url: "https://alpha-linkchecker.clarin.eu/api" #(4)
```

- change enabled (1) to `true` if you want to start the application in interactive mode
- your name (2) and password (3) may either be set in the configuration file by replacing ${USER_NAME} and ${USER_PASSWORD}, or can be set as environment variables USER_NAME and USER_PASSWORD
- the base-url (4) may either be `https://linkchecker.clarin.eu/api` for production, `https://alpha-linkchecker.clarin.eu/api` or `https://beta-linkchecker.clarin.eu/api` for testing

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


