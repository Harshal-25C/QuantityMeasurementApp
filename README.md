# Quantity Measurement App♨️📈

## UC16/Database Integration with JDBC

#### Branch: `feature/UC16-Database-Integration`

---

## Objective

UC16 enhances the **Quantity Measurement Application** by introducing **database persistence using JDBC**.
This replaces the in-memory repository with a **database-backed repository** while maintaining the **N-Tier architecture introduced in UC15**.

The system now supports **persistent storage, historical tracking, and database queries for measurement operations**.

---

## Key Enhancements

### 1. JDBC-Based Repository

Implemented:

`QuantityMeasurementDatabaseRepository`

Responsibilities:

* Save quantity measurement operations
* Retrieve stored measurements
* Query by operation type
* Query by measurement type
* Delete stored measurements
* Get measurement statistics

Uses **PreparedStatement** to prevent SQL injection.

---

### 2. Maven Project Structure

The project now follows **standard Maven layout**:

```
src/main/java
src/main/resources
src/test/java
pom.xml
```

Packages organized by layers:

* controller
* service
* repository
* entity
* exception
* unit
* util

---

### 3. Database Configuration

Database used:

* **MySQL** for development and testing

Future support prepared for:

* H2 (Memory-Based)
* PostgreSQL

Configuration stored in:

```
src/main/resources/application.properties
```

---

### 4. Connection Pool Implementation

Created utility class:

`ConnectionPool`

Features:

* Reusable database connections
* Pool statistics monitoring
* Reduced connection overhead
* Improved performance

---

### 5. Configuration Utility

Created:

`ApplicationConfig`

Responsibilities:

* Load database configuration
* Manage environment-based settings
* Provide centralized access to application properties

---

### 6. Custom Database Exception

Created:

`DatabaseException`

Purpose:

* Wrap JDBC exceptions
* Provide meaningful error messages
* Improve error handling across layers

---

### 7. Dependency Injection Support

Service layer now supports both repositories:

* `QuantityMeasurementCacheRepository`
* `QuantityMeasurementDatabaseRepository`

Repository implementation can be selected using configuration.

---

## Database Schema

Tables created using `schema.sql`:

* `quantity_measurement_entity`
* `quantity_measurement_history`

Features:

* Indexed columns
* Timestamp tracking
* Operation logging

---

## Maven Dependencies Used

* JDBC Drivers
* Mysql Database
* JUnit 4.13.2
* Mockito
* SLF4J
* Logback

Plugins:

* Maven Compiler Plugin
* Maven Surefire Plugin
* Maven Shade Plugin

---

## Testing

Test coverage includes:

* Repository CRUD operations
* Service layer integration
* Controller workflow
* Connection pool behavior
* SQL injection prevention
* Transaction rollback scenarios
* Integration testing with H2 database

All **UC1 – UC15 tests remain fully compatible**.

---

## Key Concepts Implemented

* JDBC Database Integration
* Connection Pooling
* Parameterized SQL Queries
* Transaction Management
* Maven Build System
* Repository Pattern
* Dependency Injection
* Environment-Based Configuration
* Integration Testing

---

## Learning Outcomes

* Building database-driven Java applications
* Implementing JDBC repositories
* Designing scalable persistence layers
* Using Maven for project management
* Managing database resources efficiently
* Writing integration tests with in-memory databases

---
