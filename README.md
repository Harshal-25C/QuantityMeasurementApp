# Quantity Measurement App♨️📈

## UC15/N-Tier Architecture Refactoring

### Branch: `feature/UC15-N-Tier-Architecture`

---

## Objective

UC15 refactors the **Quantity Measurement Application** from a monolithic structure into a **professional N-Tier architecture** to improve:

* Separation of Concerns
* Maintainability
* Scalability
* Testability

The application is now organized into structured layers following enterprise application design.

---

## Architecture🌿

```
Application Layer
        |
        v
Controller Layer
        |
        v
Service Layer
        |
        v
Repository Layer
        |
        v
Entity / Model Layer
```

---

## Layers Description🏗️

### 1. Application Layer

**Class**

* `QuantityMeasurementApp`

**Responsibilities**

* Entry point of the application
* Initializes components
* Invokes controller operations

---

### 2. Controller Layer

**Class**

* `QuantityMeasurementController`

**Responsibilities**

* Handles user requests
* Delegates operations to service layer
* Returns responses to application layer

---

### 3. Service Layer

**Interface**

* `IQuantityMeasurementService`

**Implementation**

* `QuantityMeasurementServiceImpl`

**Business Operations**

* Compare quantities
* Add quantities
* Subtract quantities
* Divide quantities

Contains core **business logic** of the system.

---

### 4. Repository Layer

**Interface**

* `IQuantityMeasurementRepository`

**Implementation**

* `QuantityMeasurementCacheRepository`

**Responsibilities**

* Store quantity operation history
* Provide data access methods

**Design Pattern Used**

Singleton Pattern ensures **only one repository instance** exists.

---

### 5. Entity / Model Layer

Data representation classes:

* `QuantityDTO`
* `QuantityModel`
* `QuantityMeasurementEntity`

| Class  | Purpose                               |
| ------ | ------------------------------------- |
| DTO    | Transfer data between layers          |
| Model  | Internal service layer representation |
| Entity | Store measurement records             |

---

## Design Principles Applied

* Separation of Concerns
* SOLID Principles
* Dependency Injection

---

## Design Patterns Used

* Singleton Pattern
* Factory Pattern
* Facade Pattern

---

## Testing💡

JUnit tests verify:

* Service layer operations
* Controller request flow
* Repository behavior
* Edge cases and validation

---

## Learning Outcomes

* Implementation of enterprise **layered architecture**
* Proper **responsibility separation**
* Improved **testability and maintainability**
* Application of **design patterns in real architecture**
* Clean modular system design

---

### Author👨‍💻

[Harshal Choudhary](https://github.com/Harshal-25C) - Software Developer👨‍💻 | Cloud Enthusiast            
B.Tech - `[Computer Science & Engineering]`         
Java | Maven | OOPs | Clean Architecture 
