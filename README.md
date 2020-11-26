Coverage: 80%
# Inventory Management System Project

The goal of this project is to create an Inventory Management System (IMS) using:
* Java
* Maven
* JUnit
* MySQL

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The Software you will need to install

```
IDE - Eclipse (https://www.eclipse.org/downloads/)
JAVA - Java SE Development Kit (https://www.oracle.com/uk/java/technologies/javase/javase-jdk8-downloads.html)
Database - MySQL (https://dev.mysql.com/downloads/mysql/5.7.html)
Maven - Apache Maven (https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/)
```

### Installing

A step by step series of examples that tell you how to get a development env running


1. After setting up all software, you need to create a database on the local MySQL server
  - Open MySQL Workbench
  - Select the local database instance and enter your credentials
  - In the query section make a new database called 'ims' using this query:
```
CREATE DATABASE `ims`;
```
2. To run the IMS system simply navigate to the directory where the pom.xml file is located and run the command:
```
mvn clean package
```
This will create a directory 'target' which will contain a jar file, run the jar file by navigating to target file and running the following command:
```
java -jar ims-jar-with-dependencies.jar
```

## Running the tests

Tests automatically run when "mvn clean package" in run. However more detailed output tests can be run using eclipse:
1. In eclipse open the folder containing the source code as a Maven project
2. Right click on the project and click Coverage as -> JUnit Test, this will give a more broken down test result

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Version

IMS 0.0.1

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Usama Malik** - *Built onto Chris Perrins work* [usama-malik89](https://github.com/usama-malik89)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Chris Perrins** who created the initial project structure
* **Logic Big** - [logicbig.com](https://www.logicbig.com/how-to/code-snippets/jcode-java-cmd-command-line-table.html) who's command line table creator class I used
