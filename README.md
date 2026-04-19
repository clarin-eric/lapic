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
    name:  "<your username>" #(2)
    password:  "<your password>" #(3)
  base-url: "https://linkchecker.clarin.eu/api"
```

- change enabled (1) to `true` if you want to start the application in interactive mode
- set your name (2) and password (3) 

### Alternative ways to set the properties
## By environment variables
The corresponding environment variables are (1) `SPRING_SHELL_INTERACTIVE_ENABLED`, (2) `LAPIC_USER_NAME` and (3) `LAPIC_USER_PASSWORD`
For example: 
```
export SPRING_SHELL_INTERACTIVE_ENABLED=true
export LAPIC_USER_NAME=wowasa
export LAPIC_USER_PASSWORD=pssst
```

## By program parameters
The corresponding program parameters are (1) `spring.shell.interactive.enabled`, (2) `lapic.user.name` and (3) `lapic.user.password`

For further information on setting spring boot parameters see https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html

### Building the jar
The source code is bundled with a maven wrapper. Move to the lapic directory and execute 
```
./mvnw clean install
```
This will create a target directory with a file lapic-<version>.jar

## Running the program
The general call is:
```
java -jar lapic-<version>.jar <command> <parameters>
```

### help
The help command gives you an overview of all available commands:  
```
java -jar lapic-<version>.jar help
```

For information on a specific command you call
```
java -jar lapic-<version>.jar help <specific command>
```

## Examples
### Calling for the latest status of a URL
```
java -jar lapic-<version>.jar status -u https://www.myurl.com
```

When you pass username (wowasa) and password (pssst) as program parameters, the same call would look like this:
```
java -jar lapic-<version>.jar status -u https://www.myurl.com --lapic.user.name=wowasa --lapic.user.password=pssst
```

### Uploading your URLs to check
First you have to create a json file with the URLs to check, which might look like this
```
[
  {
    "url": "http://www.wowasa.com/page1"
  }, 
  {
    "url": "http://www.wowasa.com/page2"
  }
]
```
This file (here: myfile.json) you can upload with the check command and the option -f
```
java -jar lapic-<version>.jar check -f ~/myfile.json
```

You will receive a batch Id as response

### Getting checking results
To receive the status results for a specific batch ID, you call
```
java -jar lapic-<version>.jar result -i <batch ID>
```

If you don't give a batch ID as parameter, you will receive a list status results of all URLs you have uploaded so far





