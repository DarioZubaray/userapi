# User API

Spring-boot project created with [spring initializr](https://start.spring.io/)
#### specifications:

| Type          | Description   |
| ------------- | ------------- |
|project        | Maven         |
|language       | Java          |
|spring-boot    | version 3.2.3 |
|packaging      | jar           |
|java           | 17            |

#### Dependencies

|dependencies           | Version   |
| --------------------- | --------- |
| Spring web            | 3.2.3     |
| Spring Data JPA       | 3.2.3     |
| Spring Test           | 3.2.3     |
| Spring-boot devtools  | 3.2.3     |
| Starter Validation    | 3.2.3     |
| spring security       | 2.3.2     |
| spring security test  | 2.3.2     |
| H2 Database           | 2.2.224   |
| Lombok                | 1.18.30   |
| jbcrypt               | 0.4       |
| jjwt api              | 0.11.5    |
| jjwt impl             | 0.11.5    |
| jjwt jackson          | 0.11.5    |
| mockito core          | 3.12.4    |

### Run
From the root path of the project

for windows:
```
mvnw.cmd spring-boot:run
```
for linux o mac:
```
./mvnw spring-boot:run
```

### H2 console

Database name, user and password can be found in application properties

```
	http://localhost:8080/h2-console
```

| credentials           |           |
| --------------------- | --------- |
| url datasource        | jdbc:h2:mem:testdb |
| username              | sa               |
| password              | -empty password- |


### Swagger

swagger user interface can be found at:

```
	http://localhost:8080/swagger-ui/index.html
```


### Postman

A postman collection as a [json file](UserApi.postman_collection.json)  can be found in the root of the project, just need to be imported and that's it!

