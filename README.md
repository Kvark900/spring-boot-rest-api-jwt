# spring-boot-rest-api
###### Bookstore REST API with JWT authentication

### Prerequisites
- JDK 1.8+  
- Maven

### Technology stack:
* Spring Boot
* Spring Data JPA
* MySQL
* Spring Security
* JWT (JSON Web Tokens)

There are three user accounts :
```
Admin - admin:admin
User - user:password
Disabled - disabled:password (this user is disabled)
```

All endpoints for CRUD operations can be seen in:
 - [AuthorController](https://github.com/Kvark900/spring-boot-rest-api/blob/master/src/main/java/com/kvark900/api/controller/AuthorController.java#L23)  
 - [BookController](https://github.com/Kvark900/spring-boot-rest-api/blob/master/src/main/java/com/kvark900/api/controller/BookController.java#L21)  
 - [TopicController](https://github.com/Kvark900/spring-boot-rest-api/blob/master/src/main/java/com/kvark900/api/controller/TopicController.java#L18)  
```
/auth - authentication endpoint (HTTP method: POST) - place your credentials in JSON format in request body as JwtAuthenticationRequest 

Use Bearer Token for any listed request:
/authors/** - endpoint for CRUD operations on authors (a valid JWT token must be present in the request header)   
/books/** - endpoint for CRUD operations on books (a valid JWT token must be present in the request header)   
/topics/** - endpoint for CRUD operations on topics (a valid JWT token must be present in the request header)   
```


### Set up MySQL
Configure database according to [application.properties](https://github.com/Kvark900/spring-boot-rest-api/blob/master/src/main/resources/application.properties) file, or update this file with yours properties.
