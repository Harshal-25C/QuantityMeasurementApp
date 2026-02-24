# UC2 – Feet and Inches Measurement Equality

## 📌 Description

Extends UC1 by adding equality comparison for Inches along with Feet. Both measurements are treated separately and compared independently.

---

## 🎯 Objective

Return:

- `true` if two Feet values are equal  
- `true` if two Inches values are equal  
- `false` otherwise  

---

## 🛠 Implementation

- Separate `Feet` and `Inches` classes  
- Encapsulated `private final double value`  
- Immutable design  
- Overridden `equals()` using `Double.compare()`  
- Null and type safety checks  
- Separate static methods for Feet and Inches comparison  

---

## ✅ Example

**Input:**  
1.0 inch and 1.0 inch  

**Output:**  
Equal: `true`  

**Input:**  
1.0 ft and 1.0 ft  

**Output:**  
Equal: `true`  

---

## 🧠 Concepts Covered

- Object Equality Contract  
- Floating-point comparison  
- Null safety  
- Type safety  
- Encapsulation  
- Unit testing best practices  

---

## ⚠️ Limitation

Violates **DRY principle** since `Feet` and `Inches` classes contain duplicated logic.  
A better design would use a **generic Quantity class** or **unit parameter** to reduce redundancy.
