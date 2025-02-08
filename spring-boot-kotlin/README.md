## Kotlin /w Spring Boot application for a set of employees 
This repository contains an implementation of a simple data class holding employee information, 
where the JVM resource usage can also be monitored.

### Requirements
* Spring Boot 3.4
* Jackson
* JUnit
* Jacoco
* Mockito
* PostgreSQL
* Prometheus
* Grafana

### Building and running the application
To build the application run this command in the project directory:
```shell
./gradlew build
```
To start the application run this command:
```shell
./gradlew bootRun
```
```shell
docker compose up
```
The endpoints can be accessed on:
```
http://localhost:8080/
```
Grafana dashboard (user: admin; pw: admin):
```
http://localhost:3000/
```
