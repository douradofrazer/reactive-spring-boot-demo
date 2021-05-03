# Reactive Spring Boot Demo

This is a demo to test out reactive spring boot.

### Tech Stack:

- Java 11
- Spring WebFlux
- R2DBC
- PostgresSQL
- Flyway
- Docker
- OpenApi/Swagger
- Junit 5

### Prerequisites:

- Java 11
- Docker needs to be installed.

### Set-up Steps:

- Clone Repo.
- Enter code directory:
    - Run command `docker-compose up -d`
    - Run command `mvn clean install`
    - Run command `mvn spring-boot:run`

### API Documentation:

 - [Open API / Swagger](http://localhost:8080/swagger-ui.html) - Only accessible once the demo service is up.
