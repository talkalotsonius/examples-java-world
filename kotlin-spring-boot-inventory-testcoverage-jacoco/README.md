## Kotlin /w Spring Boot Inventory
REST API that accepts CSV-formatted inventory lists with (discount) prices as an input and
returns the inventory list with current and previous prices of the inventory.

### Requirements
* Spring Boot 3.4
* Jackson
* Thymeleaf
* JUnit 
* Jacoco
* Mockito
* H2 Database

### Building and running the application

To build and run the application using Gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

API endpoint
```
GET /cars - to provide the data from the .csv file

/errors admin endpoint that can be used for debugging
```

GET request to fetch a list of inventories:
```
curl --location --request GET 'http://localhost:8080/cars'
```
