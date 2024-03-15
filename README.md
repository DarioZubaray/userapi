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
| Postgresql            | 42.6.1    |
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
for linux or mac:
```
./mvnw spring-boot:run
```

### Profiles

The application have 2 profiles

- dev: uses h2 in memory, DEBUG log level
- test: uses postgresql database, INFO log level

> by default the profile is set to _test_

```
	http://localhost:8080/h2-console
```

### H2 console

Database name, user and password can be found in application properties: dev

```
	http://localhost:8080/h2-console
```

| credentials           |                    |
| --------------------- | ------------------ |
| url datasource        | jdbc:h2:mem:testdb |
| username              | sa                 |
| password              | -empty password-   |


### PgAdmin

Database name, user and password can be found in application properties: test

| credentials           |                    |
| --------------------- | ------------------ |
| url datasource        | jdbc:postgresql://localhost:5432/mydb |
| username              | postgres           |
| password              | mysecretpassword   |

### Swagger

swagger user interface can be found at:

```
	http://localhost:8080/swagger-ui/index.html
```


### Postman

A postman collection as a [json file](UserApi.postman_collection.json)  can be found in the root of the project, just need to be imported and that's it!


### Docker

Assuming you've already intalled docker on your computer or [donwload here](https://www.docker.com/products/docker-desktop/), you only need to create the image and then run the container

_From workspace path:_
```
	docker build . -t user-api-image
```

```
	docker run -p 8089:8080 -i --name user_api_container user-api-image
```

> The exposed port was changed for exercise purposes.