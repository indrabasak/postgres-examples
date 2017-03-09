[![Build Status][travis-badge]][travis-badge-url]

Spring Data and PostgreSQL Examples
=========================================
This is a [**Spring Cloud**](http://projects.spring.io/spring-cloud/) based microservices examples backed by
[**PostgreSQL**](https://www.postgresql.org/) database.

# Modules
The project consist of multiple modules and can be classified into following categories:

1. **Services**
    1. `postgres-spring-model` - contains all the models used in our examples
    2. `postgres-spring-service` - example Spring cloud project which uses Spring Boot and Spring Data to connect to PostgreSQL databse. 
    Please see project specific README to find more about the project.


# Build
Execute the following command from the parent directory:
```
mvn clean install
```

[travis-badge]: https://travis-ci.org/indrabasak/spring-cloud-example.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/spring-cloud-example/