## UC17 — Spring Boot & REST API Integration for Quantity Measurement Application♨️💡

### Branch: `feature/UC17-SpringBoot-Backend-Integration`

---

## Overview📂

UC17 transforms the standalone Quantity Measurement Application (UC16) into a **Spring Boot REST Service**.
The Quantity Measurement Application is designed to validate equality, conversion, and arithmetic operations between different measurement units such as Feet, Inches, Yards, etc.

This project was implemented incrementally using:

✅ Test-Driven Development (TDD)
✅ Feature Branch Workflow
✅ Clean Code Practices
✅ DRY (Don't Repeat Yourself) Principle
✅ Proper Unit Conversion Strategy

| Feature | UC16 | UC17 |
|---------|------|------|
| Framework | Plain Java | Spring Boot 3.1.0 |
| Database Access | JDBC (manual) | Spring Data JPA (automatic) |
| API Exposure | None | REST Endpoints |
| Configuration | Manual properties | Spring auto-configuration |
| Dependency Injection | Manual | Spring `@Autowired` |
| Testing | JUnit | MockMvc + SpringBootTest |
| Documentation | None | Swagger / OpenAPI |
| Security | None | Spring Security |
| Server | None | Embedded Tomcat (port 8080) |

---

## Project Structure

```
quantity-measurement-app/
│
├── pom.xml                                          Spring Boot dependencies
│
├── src/main/java/com/apps/
│   ├── app/
│   │   └── QuantityMeasurementApplication.java      Spring Boot main class
│   │
│   ├── config/
│   │   └── SecurityConfig.java                      Security configuration
│   │
│   ├── controller/
│   │   └── QuantityMeasurementController.java        REST endpoints
│   │
│   ├── core/                                         NO CHANGE - business logic
│   │   ├── IMeasurable.java
│   │   ├── LengthUnit.java
│   │   ├── Quantity.java
│   │   ├── SupportsArithmetic.java
│   │   ├── TemperatureUnit.java
│   │   ├── VolumeUnit.java
│   │   └── WeightUnit.java
│   │
│   ├── dto/
│   │   ├── OperationType.java                        NEW - operation enum
│   │   ├── QuantityDTO.java                          MODIFIED - added validation
│   │   ├── QuantityInputDTO.java                     NEW - REST input wrapper
│   │   └── QuantityMeasurementDTO.java               NEW - REST output with factory methods
│   │
│   ├── exception/
│   │   ├── DatabaseException.java                    NO CHANGE
│   │   ├── GlobalExceptionHandler.java               NEW - centralized error handling
│   │   └── QuantityMeasurementException.java         NO CHANGE
│   │
│   ├── model/
│   │   ├── QuantityMeasurementEntity.java            MODIFIED - JPA annotations
│   │   └── QuantityModel.java                        NO CHANGE
│   │
│   ├── repository/
│   │   └── QuantityMeasurementRepository.java        NEW - Spring Data JPA
│   │
│   └── service/
│       ├── IQuantityMeasurementService.java          MODIFIED - new signatures
│       └── QuantityMeasurementServiceImpl.java       MODIFIED - Spring @Service
│
├── src/main/resources/
│   ├── application.properties                        spring.application.name only
│   ├── application-dev.properties                    H2 database + DEBUG logging
│   └── application-prod.properties                   MySQL + WARN logging
│
├── src/test/java/com/apps/
│   ├── app/
│   │   └── QuantityMeasurementApplicationTests.java  Integration tests
│   └── controller/
│       └── QuantityMeasurementControllerTest.java    MockMvc unit tests
│
└── dump/                                             UC16 deprecated files
    ├── QuantityMeasurementApp.java
    ├── IQuantityMeasurementRepository.java
    ├── QuantityMeasurementCacheRepository.java
    ├── QuantityMeasurementDatabaseRepository.java
    ├── ApplicationConfig.java
    └── ConnectionPool.java
```

---

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming language |
| Spring Boot | 3.1.0 | Application framework |
| Spring Web | 3.1.0 | REST endpoints + Embedded Tomcat |
| Spring Data JPA | 3.1.0 | ORM - replaces JDBC |
| Spring Security | 3.1.0 | Authentication/Authorization |
| Spring Actuator | 3.1.0 | Health checks + Metrics |
| H2 Database | Runtime | In-memory DB for development |
| MySQL | Runtime | Production database |
| Hibernate | Auto | JPA implementation |
| HikariCP | Auto | Connection pooling |
| SpringDoc OpenAPI | 2.1.0 | Swagger UI documentation |
| Lombok | Latest | Reduce boilerplate code |
| JUnit 5 | Auto | Unit + Integration testing |
| Mockito | Auto | Mocking in tests |

---

### Author👨‍💻

[Harshal Choudhary](https://github.com/Harshal-25C) - Software Developer👨‍💻 | Cloud Enthusiast            
B.Tech - `[Computer Science & Engineering]`         
Java | Maven | OOPs | Clean Architecture 