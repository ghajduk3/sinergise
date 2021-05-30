# SINERGISE BACKEND DEVELOPER CHALLENGE
##### author: Gojko Hajdukovic, 05.2021

Table of contents:
1. [Introduction](#introduction)
2. [Setup](#setup)



<a name="introduction"></a>
### Introduction
This project implements a simple WKT format geometry reader and writer. 


<a name="setup"></a>
### Setup
These instructions assume that the user is in repo's root.
```shell script
cd <repo_root>
```
This project utilizes Maven for project's build automation. In order to compile and package the project to JAR file issue:
```shell script
mvn clean package
```
After the package process is finished, observe the project's JAR `io-1.0.jar` in [`target`](./target) directory

In addition already packaged project `io-1.0.jar` is to be found in [data folder](./data).

