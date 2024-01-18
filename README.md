# 배달 플랫폼 서비스 API 프로젝트
- Java, JPA, Spring Boot, Multi-Module 기반 배달 플랫폼 API

## TECH-STACK
- Java 17
- Spring Boot 2.7.9
- Spring Data JPA
- MySQL
- RabbitMQ
- Gradle
- Lombok
- JUnit5
- Swagger UI
- IntelliJ IDEA 2022.1.4 (Ultimate Edition)

## REQUIREMENT
- OO

## BUILD & RUN
- OO

## API
### Swagger-UI
- http://localhost:8080/swagger-ui/index.html

## INFRASTRUCTURE
### Docker
- docker-compose.yml
- `docker-compose up -d`

### RABBITMQ
- http://localhost:15672
- RABBITMQ Management 활성화 커맨드 입력
  - docker dashboard -> container -> open in terminal
  - `rabbitmq-plugins enable rabbitmq_management`


## application.yml
- api/src/main/resources/application.yml
```yml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: ****
    password: ****

  jpa:
    show-sql: true
    properties:
      format_sql: true
      dialect: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: validate
  datasource:
    url: jdbc:mysql://localhost:3307/delivery?useSSL=false&useUnicode=true&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ****
    password: ****

token:
  secret:
    key: SpringBootJWTHelperTokenSecretKeyValue123!@#
  access-token:
    plus-hour: 1
  refresh-token:
    plus-hour: 12
```

- store-admin/src/main/resources/application.yml
```yml
server:
  port: 8081

spring:
  application:
    name: store-admin
  jpa:
    show-sql: true
    properties:
      format_sql: true
      dialect: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: validate
  datasource:
    url: jdbc:mysql://localhost:3307/delivery?useSSL=false&useUnicode=true&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ****
    password: ****
```

- db/src/main/resources/application.yml
```yml
spring:
  application:
    name: store-admin
  jpa:
    show-sql: true
    properties:
      format_sql: true
      dialect: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: validate
  datasource:
    url: jdbc:mysql://localhost:3307/delivery?useSSL=false&useUnicode=true&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ****
    password: ****
```

- docker-compose.yml
```yml
version: "3"
services:
  db:
    image: mysql:8.0.26
    restart: always
    command:
      - --lower_case_table_names=1
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    container_name: mysql_course3
    ports:
      - "3307:3306"
    environment:
      - MYSQL_DATABASE=****
      - MYSQL_ROOT_PASSWORD=****
      - TZ=Asia/Seoul
#    volumes:
#      - C:\Temp\MYSQL:/var/lib/mysql
```