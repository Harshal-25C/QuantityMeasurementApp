## UC7 – Addition with Target Unit Specification

### 📌 Description

Extends UC6 by allowing the caller to explicitly specify the **target unit** for the addition result.

Instead of defaulting to the unit of the first operand, the result can be expressed in any supported `LengthUnit` (FEET, INCHES, YARDS, CENTIMETERS).

Example: `1 FEET + 12 INCHES in YARDS ≈ 0.667 YARDS`

---

### 🎯 Objective

- Add two length measurements  
- Allow explicit target unit specification  
- Preserve immutability  
- Maintain floating-point precision  
- Keep backward compatibility with UC6  

---

### ➕ Addition Logic

- Validate operands and target unit (non-null, finite values)  
- Convert both operands to base unit  
- Add base values  
- Convert sum to explicitly specified target unit  
- Return new `QuantityLength` object  

---

### ✅ Example

- `add(Quantity(1.0, FEET), Quantity(12.0, INCHES), FEET)`  
  → `Quantity(2.0, FEET)`  

- `add(Quantity(1.0, FEET), Quantity(12.0, INCHES), INCHES)`  
  → `Quantity(24.0, INCHES)`  

- `add(Quantity(1.0, FEET), Quantity(12.0, INCHES), YARDS)`  
  → `Quantity(~0.667, YARDS)`  

- `add(Quantity(36.0, INCHES), Quantity(1.0, YARDS), FEET)`  
  → `Quantity(6.0, FEET)`  

---

### ✨ Features

- Explicit target unit control  
- Cross-unit addition  
- Backward compatibility with implicit `add()`  
- Commutative property preserved  
- Zero and negative value handling  
- Large/small scale conversion support  
- Precision-safe arithmetic  

---

### 🛡 Validation Rules

- Null operands throw exception  
- Null or invalid target unit throws `IllegalArgumentException`  
- NaN or infinite values rejected  
- All units must belong to same measurement category  

---

### 🧠 Concepts Covered

- Method Overloading  
- Explicit Parameter Passing  
- DRY Principle Reuse  
- Base Unit Normalization  
- Immutability  
- API Design Clarity  
- Mathematical Properties (Commutativity)  
- Exception Handling  
- Functional Programming Style  

---

### 🌟 Benefits

- Flexible result representation  
- Clear caller intent  
- Scalable unit system  
- Clean and maintainable architecture  
- Fully backward compatible (UC1–UC6 preserved)

**Code Link:** [UC-7 feature](https://github.com/Harshal-25C/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition)
  
