# 📏 Quantity Measurement Application  
## 🚀 Branch: feature/UC1-FeetEquality

---

## 📌 Overview

This branch implements **UC1 – Feet Equality**.

UC1 is the foundational use case of the Quantity Measurement Application.  
It verifies that two length measurements in **Feet** are equal when their values are the same.

This use case establishes:

- Basic object modeling  
- Equality comparison logic  
- Unit encapsulation  
- Null safety  
- Clean OOP structure  

---

## 🎯 Objective

Validate that:
1 ft == 1 ft → true
1 ft != 2 ft → false


This is the first step toward building a scalable and extensible measurement system.

---

## 🏗 Project Structure (UC1)
```
quantity-measurement-app
│
└── src
├── main
│ └── java
│ └── com.quantity
│ ├── model
│ │ └── Quantity.java
│ │
│ └── unit
│ └── LengthUnit.java
│
└── test
└── java
└── com.quantity
└── QuantityTest.java
```


---

## 🧠 Concepts Implemented

- Object-Oriented Programming (OOP)
- `equals()` method overriding
- Proper `hashCode()` implementation
- Null validation
- Enum usage
- Encapsulation
- Immutable object design

---

## 📦 Implementation Details

### 1️⃣ LengthUnit Enum

Currently supports:

No conversion logic is implemented in UC1.  
Only a single unit comparison is supported.

---

### 2️⃣ Quantity Class

#### Attributes

#### Responsibilities

- Store measurement value
- Store measurement unit
- Override `equals()` for logical comparison
- Maintain immutability

#### Equality Rule

Two quantities are equal if:

- Units are the same
- Values are numerically equal

---

## 💻 Example Implementation

```java
Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
Quantity q3 = new Quantity(2.0, LengthUnit.FEET);

System.out.println(q1.equals(q2));  // true
System.out.println(q1.equals(q3));  // false
System.out.println(q1.equals(null)); // false