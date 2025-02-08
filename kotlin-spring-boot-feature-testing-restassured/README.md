## Kotlin /w Spring Boot implementation of API with Object mapping using testing with RESTassured

## Getting Started
REST API that exposing endpoints to allow access of features representing data with its associated metadata.

### Requirements
* Spring Boot 3.4
* Jackson
* JUnit
* Mockk
* RestAssured

### Building and running the application

To build and run the application using Gradle run this command:
```
./gradlew clean build

./gradlew bootRun
```

### API Endpoint
```
GET /features

GET /features/{featureId}/quicklook
```

#### GET request to fetch a list of all features:
```
curl --location --request GET 'http://localhost:8080/features'
```

The response returns a list of all features in the following format :
```
{
"id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
"timestamp": 1554831167697,
"beginViewingDate": 1554831167697,
"endViewingDate": 1554831202043,
"missionName": "Sentinel-1B"
}
```

#### GET request to fetch an image for the given feature id:
```
curl --location --request GET 'http://localhost:8080/features/39c2f29e-c0f8-4a39-a98b-deed547d6aea/quicklook'
```

The response returns an image/png as a content type
