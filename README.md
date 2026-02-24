## 📏 Quantity Measurement Application  
### 🚀 Branch: dev

---

### 📌 Overview

The `dev` branch represents the **active development branch** of the Quantity Measurement Application.

This branch consolidates all completed and in-progress use cases (UC1 – UC14) and serves as the integration branch before merging into `main`.

It contains:

- Core domain models
- Unit abstractions
- Conversion logic
- Arithmetic operations
- Temperature handling
- Volume support
- Comprehensive test coverage

---

### 🎯 Project Objective

To build a scalable and extensible **Quantity Measurement System** that:

- Supports multiple measurement types  
- Allows unit conversions  
- Enables arithmetic operations  
- Maintains strict type safety  
- Ensures domain correctness through test-driven development  

---

### 🏗 Architecture Overview

The system follows clean domain-driven design principles.

### Core Concepts

- `Quantity` → Represents measurable value
- `IMeasurable` → Interface for measurement types
- Unit Enums → Length, Weight, Volume, Temperature
- Operation validation per measurement type
- Conversion normalization strategy

---

### 🔄 Implemented Use Cases

| UC | Description |
|----|-------------|
| UC1 | Feet Equality |
| UC2 | Null Handling |
| UC3 | Different Unit Inequality |
| UC4 | Inch ↔ Feet Conversion |
| UC5 | Length Addition |
| UC6 | Weight Equality |
| UC7 | Weight Addition |
| UC8 | Gallon ↔ Liter Conversion |
| UC9 | Volume Addition |
| UC10 | Refactored Design with Interface |
| UC11 | Volume Support Extension |
| UC12 | Temperature Equality |
| UC13 | Temperature Conversion (C ↔ F) |
| UC14 | Restrict Temperature Arithmetic |

---

### 🧠 Design Principles

- ✔ Object-Oriented Design
- ✔ Interface-based abstraction
- ✔ Strategy-like unit conversion handling
- ✔ Immutability for domain safety
- ✔ Proper equals() and hashCode()
- ✔ Clean separation of concerns
- ✔ Test-driven development approach

---

### 🔬 Measurement Types Supported

### 📏 Length
- Feet
- Inch
- Yard (if implemented)

### ⚖ Weight
- Gram
- Kilogram
- Tonne

### 🧪 Volume
- Liter
- Gallon
- Milliliter

### 🌡 Temperature
- Celsius
- Fahrenheit

---

### ➕ Arithmetic Support

| Measurement | Addition | Subtraction | Conversion |
|-------------|----------|------------|------------|
| Length | ✔ | ✔ | ✔ |
| Weight | ✔ | ✔ | ✔ |
| Volume | ✔ | ✔ | ✔ |
| Temperature | ❌ | ❌ | ✔ |

Temperature arithmetic is intentionally restricted due to physical domain rules.

---

### 🧪 Testing Strategy

- JUnit 5
- Positive scenarios
- Negative scenarios
- Cross-unit validation
- Exception validation
- Edge case handling

All use cases are covered with deterministic tests.
