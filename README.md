## QuantityManagementSystem♨️📈

A Maven-based Java application that demonstrates object-oriented design principles
by implementing length equality comparison across multiple development stages (UC1, UC2, UC3).

The project evolves incrementally using Git branches to demonstrate refactoring,
DRY principle, abstraction, polymorphism, and clean architecture.

---

### 📌 Project Overview

This application compares length measurements such as:

- Feet
- Inches

The equality comparison follows proper object-oriented design and respects:

- Equality Contract (equals method rules)
- DRY Principle
- Encapsulation
- Abstraction
- Open-Closed Principle
- Refactoring Best Practices

---

### 🏗 Project Structure (Maven)

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
│   │               └── length/
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

### 🌿 Branch Structure

The project follows incremental development across branches:

---

### 🔹 UC1 – Feet Equality

#### Objective:
Compare two feet measurements for equality.

#### Branch Name:
```
feature/UC1-FeetEquality
```

#### Folder Structure:

```
domain/length/
   └── Feet.java

test/domain/length/
   └── FeetTest.java
```

#### Implementation:

- Class: `Feet`
- Constructor: `Feet(double value)`
- Overridden `equals()` method
- Compares only feet-to-feet
- No cross-unit comparison

#### Concepts Covered:

- equals() override
- Equality contract basics
- Value-based equality
- Unit testing with JUnit

---

### 🔹 UC2 – Feet and Inches Comparison

#### Objective:
Compare Feet and Inches across units.

#### Branch Name:
```
feature/UC2-FeetInchesComparison
```

#### Folder Structure:

```
domain/length/
   ├── Feet.java
   └── Inches.java

test/domain/length/
   ├── FeetTest.java
   └── InchesTest.java
```

#### Implementation:

- Two separate classes: `Feet` and `Inches`
- Conversion logic added manually
- 1 Foot = 12 Inches
- Cross-unit comparison supported

#### Limitation:

Code duplication:
- Same constructor structure
- Same equals() logic
- Violates DRY principle

#### Concepts Covered:

- Cross-unit equality
- Conversion logic
- Refactoring awareness
- Code duplication problem

---

### 🔹 UC3 – Generic Quantity Class (DRY Principle)

## Objective:
Eliminate duplication by introducing a generic Quantity class.

#### Branch Name:
```
feature/UC3-GenericQuantityClassForDRYPrinciple
```

#### Folder Structure:

```
domain/length/
   ├── LengthUnit.java
   └── Quantity.java

test/domain/length/
   └── QuantityTest.java
```

#### Implementation:

#### LengthUnit Enum

- FEET
- INCH
- Conversion factor stored inside enum
- Centralized conversion logic

#### Quantity Class

- Attributes:
  - double value
  - LengthUnit unit
- Converts values to base unit (feet)
- Clean equals() implementation
- Proper hashCode() override

## Advantages:

✔ DRY Principle applied  
✔ Single Responsibility Principle  
✔ Type-safe enum usage  
✔ Scalable (easy to add new units)  
✔ No duplication  
✔ Backward compatible  

### Concepts Covered:

- DRY Principle
- Polymorphism
- Enum encapsulation
- Abstraction
- Encapsulation
- Open-Closed Principle
- Refactoring strategy
- Equality contract compliance
- Null safety
- Type safety

---

### 🧪 Testing Strategy

All branches include JUnit test cases covering:

- Same-unit equality
- Cross-unit equality
- Different values
- Null comparison
- Same reference comparison
- Invalid unit handling

---

### ⚙ How to Run

## 1️⃣ Clone Repository

```
git clone <repository-url>
```

## 2️⃣ Switch Branch

```
git checkout feature/UC3-GenericQuantityClassForDRYPrinciple
```

## 3️⃣ Run Tests

```
mvn clean test
```

## 4️⃣ Run Application

Run `QuantityMeasurementApp.java` from IDE.

---

# 🔁 Git Workflow

Feature Branches:
```
feature/UC1
feature/UC2
feature/UC3
```

Integration Flow:
```
feature/* → dev → main
```

- `dev` = integration branch
- `main` = production-ready branch

---

# 📚 Key Learning Outcomes

- Understanding value-based equality
- Avoiding code duplication
- Refactoring legacy design
- Clean object-oriented modeling
- Writing scalable domain models
- Following equality contract strictly
- Designing extensible architecture

---

# 🚀 Future Enhancements

- Add Meter, Yard, Centimeter
- Introduce Weight measurement
- Use BigDecimal for precision
- Convert to Spring Boot REST API
- Apply Strategy Pattern for conversion
- Implement Generic Quantity<T>

---

# 👨‍💻 Author

Harshal Choudhary  
B.Tech Computer Science & Engineering  
Java | Maven | OOP | Clean Architecture
