# Project Name
User Authentication and Authorization System

This project implements a user authentication and 
authorization system using Spring Boot and JWT (JSON Web Token). 
It allows users to register, login, and access certain endpoints based on their roles (e.g., USER, ADMIN).

## Table of Contents

1. [Technologies Used](#technologies-used)
2. [Setup Instructions](#setup-instructions)
    - [Prerequisites](#prerequisites)
    - [Steps to Run the Application](#steps-to-run-the-application)
3. [API Documentation](#api-documentation)
    - [Endpoints](#endpoints)
    - [Authentication](#authentication)
4. [Security Considerations](#security-considerations)
5. [Database](#database)

## Technologies Used

- Spring Boot
-  Spring Security
- JWT (JSON Web Token)
- BCrypt (for password hashing)
- MySql (Relational database)

## Setup Instructions

### Prerequisites

- Ensure you have the following installed:
- Java Development Kit (JDK) 11 or higher
- Maven (for building and managing dependencies)
- MySql (or any other relational database)
- IDE (Eclipse, IntelliJ IDEA, etc.)

### Steps to Run the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/your-repository.git
   cd your-repository

2. **Database Configuration:**

- Create a MySql database named user_service.

- Update the database configuration in src/main/resources/application.yml:


    url: jdbc:mysql://localhost:3306/user_service
    username: root  to "your username"
    password: '' to "your password" 
    driver-class-name: com.mysql.cj.jdbc.Driver

1. **Build and Run the Application:**

- Open a terminal or command prompt and navigate to the project root directory.

- Build the project:

  mvn clean package

1. **Run Application:**
 
- java -jar target/your-application-name.jar
- The application should now be running on http://localhost:5001.


## API Documentation
## Endpoints
The following endpoints are available:

- POST http://localhost:5001/auth/signUp: Register a new user.
- POST http://localhost:5001/auth/signIn: Authenticate and receive a JWT token.
- GET http://localhost:5001/api/users: Get All users profile.
- GET http://localhost:5001/api/users/profile: Get only a specific user based on Jwt.
- GET http://localhost:5001/api/users/admin: Retrieve all users (only accessible to users with ADMIN role).


## Authentication
- POST /api/auth/signUp

Endpoint to register a new user.

- Request body:


    {
    "fullName": "example",
    "email": "test@gmail.com",
    "password": "1234kljkk10",
    "role": "ROLE_ADMIN"
    }

- POST /api/auth/signIn

Endpoint to authenticate and receive a JWT token.

Request body:


    {
    "email": "test@gmail.com",
    "password": "1234kljkk10"
    }

Response:

    {
    "jwt": "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MjAwODkyNDcsImV4cCI6MTcyMDE3NTY0NywiZW1haWwiOiJ0ZXN0QGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.lr1vGj2MDY8yNSqzNsj0B0CMwY2FlWX_IelHVPy2E917LTVp2xjnYnD_Nm0smGhKKaUn6io7gne07Y7gX_4ugw",
    "message": "Login Successful",
    "status": true
    }

- User Management

GET http://localhost:5001/api/users

Endpoint to retrieve all users.

Response:

    [
    {
        "id": 1,
        "password": "$2a$10$blTino7io/vefpgVzmBN5uvfNow1OCkhrMMYd79oyEDj8FjcG9JSq",
        "email": "chidimma@gmail.com",
        "role": "ROLE_ADMIN",
        "fullName": "Chidimma"
    },
    {
        "id": 2,
        "password": "$2a$10$Ok.Cn1cQ1ojNoTWuXqXv2OBt8doE.Nv1ZiE2mRchR0xd1XQAMHYcK",
        "email": "tochi@gmail.com",
        "role": "ROLE_USER",
        "fullName": "Tochukwu"
    },
    }


## Security Considerations
JWT tokens are securely generated and verified.
Passwords are hashed using BCrypt before storing in the database.
Role-based access control is implemented to restrict endpoints based on user roles.

## Database
MySql is used as the relational database to store user credentials and roles.
Schema and table creation are handled automatically (spring.jpa.hibernate.ddl-auto=update).

##
Future Improvements
Implement user profile management (update user details, change password, etc.).
Enhance error handling and validation for API endpoints.