## QuantityManagementSystem♨️📈

A Maven-based Java application that demonstrates object-oriented design principles
by implementing length equality comparison across multiple development stages (UC1, UC2, UC3).

The project evolves incrementally using Git branches to demonstrate refactoring,
DRY principle, abstraction, polymorphism, and clean architecture.

---

### 📌 Project Overview

A generic, extensible, and SOLID-compliant Java application for performing measurement operations across multiple categories:

- Length
- Weight
- Volume
- Temperature (with selective arithmetic support)

The system evolves incrementally from **UC1 to UC14**, demonstrating clean architecture, generics, functional interfaces, lambda expressions, and interface refactoring principles.

Design a scalable and type-safe measurement system that:

- Supports unit conversion
- Maintains cross-category isolation
- Centralizes arithmetic logic
- Enforces operation validity constraints
- Demonstrates real-world object-oriented design

---

### Project Structure🏗 

#### 🏗 Project Structure (Maven)

```
quantity-measurement-app/
│
├── pom.xml
├── .gitignore
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/quantity/
│   │           ├── app/
│   │           └── domain/
│   │               ├── length/
|   |               └── weight/
│   │
│   └── test/
│       └── java/
│           └── com/quantity/
│               └── domain/
│                   └── length/
│
└── target/
```

---

## 🌿 Branch Structure

The project follows incremental development across branches:

### 🚀 Features Overview (UC1 – UC14)

#### 🔹 UC1 – Equality for Same Unit
- Compare same-unit quantities.

#### 🔹 UC2 – Null Handling
- Null unit validation.
- equals(null) handling.

#### 🔹 UC3 – Different Value Inequality
- Detect unequal quantities.

#### 🔹 UC4 – Cross Unit Comparison (Length)
- Feet ↔ Inch comparison.
- Base unit conversion introduced.

#### 🔹 UC5 – Additional Length Units
- Yard, Centimeter support.
- Scalable enum design.

#### 🔹 UC6 – Weight Measurement
- Gram, Kilogram support.
- Cross-category restriction.

#### 🔹 UC7 – Volume Measurement
- Liter, Milliliter, Gallon.
- Generic isolation via `<U extends IMeasurable>`.

#### 🔹 UC8 – Addition Operation
- Add compatible quantities.
- Base-unit arithmetic logic.

#### 🔹 UC9 – Subtraction Operation
- Subtract quantities.
- Precision rounding.

#### 🔹 UC10 – Division Operation
- Division with zero validation.

#### 🔹 UC11 – Volume Arithmetic Support
- Extended arithmetic support for volume.

#### 🔹 UC12 – Centralized Arithmetic Enum
- Introduced `ArithmeticOperation`.
- Used `DoubleBinaryOperator`.
- Removed duplication.

#### 🔹 UC13 – Arithmetic Refactoring
- Unified `performArithmetic()` method.
- Clean reusable design.

#### 🔹 UC14 – Temperature Measurement (Selective Arithmetic)

#### ✔ Added Units
- CELSIUS
- FAHRENHEIT
- KELVIN

#### ✔ Supported
- Equality
- Conversion

#### ❌ Unsupported
- Addition
- Subtraction
- Division

Temperature arithmetic is disabled because:
100°C + 50°C ≠ meaningful result
100°C ÷ 50°C = meaningless ratio

#### Uses:
`validateOperationSupport()`

#### Throws:
`UnsupportedOperationException`

---

### 🌡 Temperature Conversion Formulas

#### Celsius → Fahrenheit
`°F = (°C × 9/5) + 32`

### Fahrenheit → Celsius
`°C = (°F − 32) × 5/9`

### Kelvin → Celsius
`°C = K − 273.15`

---

## 🌿 Branch Strategy

### `main`
- Stable production-ready branch
- Contains UC1 – UC14

### `dev`
- Integration branch
- All feature branches merged before main

### Feature Branches🌿

| Branch | Description |
|--------|------------|
| feature/UC1 | Basic equality |
| feature/UC2 | Null validation |
| feature/UC3 | Inequality logic |
| feature/UC4 | Length conversion |
| feature/UC5 | Additional length units |
| feature/UC6 | Weight measurement |
| feature/UC7 | Volume measurement |
| feature/UC8 | Addition support |
| feature/UC9 | Subtraction support |
| feature/UC10 | Division support |
| feature/UC11 | Volume arithmetic |
| feature/UC12 | Arithmetic enum refactor |
| feature/UC13 | Centralized arithmetic logic |
| feature/UC14 | Temperature with selective arithmetic |

---

### 🧠 Core Concepts Implemented

- Generics
- Functional Interfaces
- Lambda Expressions
- Enum Polymorphism
- Default Interface Methods
- Interface Segregation Principle (ISP)
- SOLID Principles
- Capability-Based Design
- Non-linear Conversions
- Epsilon-based Floating Point Comparison

---

### 📌 Example Output

Equality: `true`         
Convert 100C to F: Quantity{212.0 FAHRENHEIT}          
Temperature does not support ADD operation.

---

### 🧪Testing Strategy

JUnit 5 coverage includes:

- Same-unit equality
- Cross-unit equality
- Conversion accuracy
- Round-trip conversion
- Symmetric & reflexive properties
- Unsupported operation validation
- Cross-category prevention
- Division-by-zero handling
- Null validation
- Precision tolerance (epsilon)

---

### 🔐Type Safety

Compile-time:
`Quantity<TemperatureUnit> ≠ Quantity<LengthUnit>`


Runtime:
`equals()` checks `unit.getClass()`

Cross-category comparisons return false.

---

### 📏 Floating-Point Precision

- private static final double EPSILON = 0.01;

Prevents precision-related equality errors.

---

### 🏛 Design Principles

#### ▶️ Single Responsibility
Each enum handles only conversion logic.

#### ▶️ Open/Closed Principle
New categories can be added without modifying core logic.

#### ▶️ Interface Segregation
Optional arithmetic via default methods.

#### ▶️ Liskov Substitution
All measurable units behave consistently for conversion.

#### ▶️ Dependency Inversion
`Quantity` depends on `IMeasurable`, not concrete enums.

---

### 🔮 Future Enhancements

- Compile-time arithmetic restriction
- Temperature difference modeling
- REST API integration
- Spring Boot wrapper
- Persistence layer
- Additional measurement categories

---

### ⚙ How to Run

#### 1️⃣ Clone Repository

```
git clone <repository-url>
```


#### 2️⃣ Switch Branch

```
git checkout feature/UC3-GenericQuantityClassForDRYPrinciple
```

#### 3️⃣ Run Tests

```
mvn clean test
```

#### 4️⃣ Run Application

Run `QuantityMeasurementApp.java` from IDE.

---

### Author👨‍💻

[Harshal Choudhary](https://github.com/Harshal-25C) - Software Developer👨‍💻 | Cloud Enthusiast            
B.Tech - `[Computer Science & Engineering]`         
Java | Maven | OOPs | Clean Architecture          

