# Brelinx EduSuite

A comprehensive Student Management System built with Spring Boot.

## Project Overview

Brelinx EduSuite is a modern web application designed to manage educational institutions, including schools, students, teachers, and courses. The system provides a robust foundation for educational management with proper entity relationships and security features.

## Features

- **School Management**: Manage multiple schools with their details
- **Student Management**: Track student information, enrollment, and course assignments
- **Teacher Management**: Manage teacher profiles and course assignments
- **Course Management**: Create and manage courses with student enrollments
- **Security**: JWT-based authentication and authorization
- **API Documentation**: Swagger/OpenAPI integration
- **Database Migration**: Flyway for database version control

## Technology Stack

- **Backend**: Spring Boot 3.4.12
- **Database**: PostgreSQL (Production), H2 (Testing)
- **Security**: Spring Security with JWT
- **Documentation**: SpringDoc OpenAPI
- **Build Tool**: Maven
- **Java Version**: 17

## Entity Relationships

### Core Entities

1. **BaseEntity**: Abstract base class with common fields (id, createdAt, updatedAt)
2. **School**: Represents educational institutions
3. **Student**: Student information and course enrollments
4. **Teacher**: Teacher profiles and course assignments
5. **Course**: Course details with student-teacher relationships

### Relationships

- School → Students (One-to-Many)
- School → Teachers (One-to-Many)
- School → Courses (One-to-Many)
- Teacher → Courses (One-to-Many)
- Course ↔ Students (Many-to-Many)

## Getting Started

### Prerequisites

- Java 17 or higher
- PostgreSQL database
- Maven 3.6+

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Brelinx-EduSuite
   ```

2. Configure the database in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/brelinx_edusuite
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project:
   ```bash
   ./mvnw clean compile
   ```

4. Run tests:
   ```bash
   ./mvnw test
   ```

5. Start the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### API Documentation

Once the application is running, you can access the API documentation at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/api-docs

## Configuration

### Database Configuration

The application uses PostgreSQL for production and H2 for testing. Database configurations are managed through Spring profiles:

- **Production**: Uses PostgreSQL with Flyway migrations
- **Test**: Uses H2 in-memory database with schema auto-creation

### Security Configuration

The application includes Spring Security with JWT token-based authentication. Security configurations can be customized in the security package.

## Development

### Project Structure

```
src/
├── main/
│   ├── java/com/brelinx/edusuite/
│   │   ├── model/          # Entity classes
│   │   └── BrelinxEdusuiteApplication.java
│   └── resources/
│       └── application.properties
└── test/
    ├── java/
    └── resources/
        └── application-test.properties
```

### Running Tests

Tests use H2 in-memory database and can be run with:
```bash
./mvnw test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.