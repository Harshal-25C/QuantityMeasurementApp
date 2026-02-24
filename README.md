## UC13 – Centralized Arithmetic Logic to Enforce DRY in Quantity Operations

### Overview  
UC13 refactors the arithmetic operations (`add`, `subtract`, `divide`) introduced in UC12 to remove
code duplication and enforce the **DRY (Don’t Repeat Yourself)** principle.  
The public API and behavior remain unchanged; only the **internal implementation** is improved by
centralizing validation, base-unit conversion, and arithmetic execution into reusable helper
methods.

This refactoring improves maintainability, consistency, readability, and scalability, and prepares
the system for adding future arithmetic operations (e.g., multiplication, modulo) without
duplicating logic.

---

### What Changed in UC13 (High Level)

- Introduced a **centralized validation helper** for all arithmetic operations  
- Introduced a **single core arithmetic helper** that performs base-unit normalization and
  arithmetic  
- Introduced an **enum-based arithmetic dispatcher** (`ArithmeticOperation`) to avoid `if/else`
  or `switch` blocks  
- Refactored `add`, `subtract`, and `divide` to delegate to these helpers  
- **No changes to public method signatures or behavior**

---

### What Did NOT Change

- No new features or user-facing functionality  
- No changes to `IMeasurable`, `LengthUnit`, `WeightUnit`, `VolumeUnit`  
- No changes to existing test cases (UC12 tests pass unchanged)  
- No change in arithmetic results or error semantics

---

### Motivation (Why UC13 Was Needed)

The UC12 implementation repeated the same logic across multiple methods:

- Null checks and unit validation  
- Cross-category compatibility checks  
- Finiteness checks for numeric values  
- Base-unit conversions  
- Target unit handling  

This duplication violated DRY, increased maintenance cost, and made the code harder to read and
extend. UC13 centralizes this logic into private helpers, creating a **single source of truth** for
validation and arithmetic.

---

## Refactoring Design

### Centralized Validation

A single private method validates all operands and (when applicable) target units.  
All arithmetic operations call this method, ensuring consistent error handling and messages.

### Core Arithmetic Helper

A single private method:
1. Converts both operands to base units  
2. Applies the requested arithmetic operation  
3. Returns the base-unit result  

Public methods then convert the result to the requested target unit (for add/subtract) or return
a dimensionless scalar (for divide).

### Enum-Based Operation Dispatch

An internal `ArithmeticOperation` enum encapsulates operation-specific logic (`ADD`, `SUBTRACT`,
`DIVIDE`). This avoids scattered conditionals and makes it trivial to add future operations like
`MULTIPLY` without changing validation or conversion code.

---


**Code Link:** [UC-13 feature](https://github.com/Harshal-25C/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic)
