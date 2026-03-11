# JWT Authentication Backend

This backend provides APIs for a **User Authentication System** using **Spring Boot, Spring Security, JWT, and PostgreSQL**.

## Tech Stack

* Spring Boot
* Spring Security
* JWT
* PostgreSQL
* JPA / Hibernate

---

# API Endpoints

## Register User

POST `/api/register`

Request Body

```
{
 "name": "Vivek",
 "email": "vivek@gmail.com",
 "password": "123456"
}
```

---

## Login User

POST `/api/login`

Response

```
{
 "token": "JWT_TOKEN"
}
```

---

## Get User Profile

GET `/api/profile`

Header

```
Authorization: Bearer JWT_TOKEN
```

Response

```
{
 "name": "Vivek",
 "email": "vivek@gmail.com"
}
```

---

# Database Schema

Table: `users`

| Column   | Type    |
| -------- | ------- |
| id       | bigint  |
| name     | varchar |
| email    | varchar |
| password | varchar |

Passwords are stored using **BCrypt hashing**.

---

# Setup Instructions

## 1. Clone Repository

```
git clone <repository-url>
```

## 2. Configure Database

Update `application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 3. Run Application

Run the Spring Boot application.

Server will start at

```
http://localhost:8080
```

---

# Security Implementation

* Password hashing using BCrypt
* JWT token generation on login
* Stateless authentication
* Spring Security protected APIs
