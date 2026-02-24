# UC6 – Addition of Two Length Units (Same Category)

## 📌 Description

Extends UC5 by introducing **addition operations between length measurements**.

Supports adding two `QuantityLength` objects of possibly different units and returns the result in the unit of the first operand (or specified target unit).

Example: `1 FEET + 12 INCHES = 2 FEET`

---

## 🎯 Objective

- Add two length measurements  
- Normalize to base unit before arithmetic  
- Preserve immutability  
- Maintain floating-point precision  

---

## ➕ Addition Logic

- Validate both operands (non-null, finite values, valid units)  
- Convert both values to base unit  
- Add base values  
- Convert sum to target unit (unit of first operand)  
- Return new `QuantityLength` object  

---

## ✅ Example

- `add(Quantity(1.0, FEET), Quantity(2.0, FEET))`  
  → `Quantity(3.0, FEET)`  

- `add(Quantity(1.0, FEET), Quantity(12.0, INCHES))`  
  → `Quantity(2.0, FEET)`  

- `add(Quantity(12.0, INCHES), Quantity(1.0, FEET))`  
  → `Quantity(24.0, INCHES)`  

- `add(Quantity(1.0, YARDS), Quantity(3.0, FEET))`  
  → `Quantity(2.0, YARDS)`  

- `add(Quantity(2.54, CENTIMETERS), Quantity(1.0, INCHES))`  
  → `Quantity(~5.08, CENTIMETERS)`  

---

## ✨ Features

- Cross-unit addition  
- Same-unit addition  
- Commutative property  
- Identity element (adding zero)  
- Negative value handling  
- Large and small value support  
- Precision-safe floating point arithmetic  
- Immutable result object  

---

## 🛡 Validation Rules

- Null operands throw exception  
- Null or invalid units throw `IllegalArgumentException`  
- NaN or infinite values rejected  

---

## 🧠 Concepts Covered

- Arithmetic on Value Objects  
- Immutability  
- Base Unit Normalization  
- DRY Principle Reuse  
- Method Overloading  
- Exception Handling  
- Mathematical Properties (Commutativity, Identity)  
- Clean API Design  

---

## 🌟 Benefits

- Reuses conversion logic from UC5  
- No code duplication  
- Domain-driven arithmetic behavior  
- Scalable for future measurement operations  
- Backward compatible (UC1–UC5 preserved)  
