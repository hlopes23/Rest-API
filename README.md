# Car Shop Application

A Spring Boot application for managing vehicles and accounts in a car shop context.

## Features

- Manage accounts and vehicles
- Associate vehicles with accounts
- Activate/deactivate vehicles and accounts
- Retrieve vehicles by various criteria

## Technologies

- Java
- Spring Boot
- Maven
- JUnit 5 & Mockito (for testing)

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

## Project Structure

- src/main/java/com/example/car_shop/ — Main application code
- src/test/java/com/example/car_shop/ — Unit and integration tests

### Example Endpoints

- GET /vehicles — List all vehicles
- POST /vehicles — Create a new vehicle
- PUT /vehicles/{id}/activate — Activate a vehicle
- PUT /vehicles/{id}/deactivate — Deactivate a vehicle

### Build & Run

```bash
mvn clean install
mvn spring-boot:runmvn test

