# Finance_Manager-API
API REST for the Finance Manager application

## Technologies
 
- Java
- Spring Boot
- MySQL
- JPA
- OpenSwagger
- H2
- JUnit


## Features

- Save a Despesa
- Get all Despesas
- Get a specific Despesa
- Uptade a specific Despesa
- Delete a specific Despesa
- Search Despesas

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. MySQL - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Itor-Carlos/Finance_Manager-API.git
```

**2. Create MySQL database**
```bash
create database finance_manager
```

**3. You can choose the profile that will be used**
 
 + To choose the profile, acess `src/main/resources/application.properties`
 
 + If you choose test profile, acess `src/main/resources/application.properties` and change the parameter `spring.profiles.active` to `test`

 + If you chose dev profile, acess `src/main/resources/application.properties` and  change the parameter `spring.profiles.active` to `test`. After the previous step, acess `src/main/resources/application-dev.properties` and change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation


**4. Build and run the app using maven**

```bash
mvn spring-boot:run
```

 + The application will be start running at <http://localhost:8080>

 + You can acess the api-docs from this application. To do this, go to <http://localhost:8080/despesa-api/swagger-ui-custom.html> after start application


## This API will be modified in the future
  + profiles(test,dev)
  + CORS configuration
  + Unit Tests and Integration Tests
  + Docker
