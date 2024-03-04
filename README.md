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
| Spring web            |           |
| Spring Data JPA       |           |
| Spring Test           |           |
| Spring-boot devtools  |           |
| Starter Validation    |           |
| H2 Database           |           |
| Lombok                |           |
| jbcrypt               | 0.4       |
| springdoc             | 2.3.0     |

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

### Swagger

swagger user interface can be found at:

```
	http://localhost:8080/swagger-ui/index.html
```


### H2 console

Database name, user and password can be found in application properties

```
	http://localhost:8080/h2-console
```