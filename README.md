## UC12 – Subtraction and Division Operations on Quantity Measurements

### 1. Introduction 

UC12 extends the Quantity Measurement Application by introducing two additional arithmetic operations:
**subtraction** and **division** on generic quantities.

Earlier use cases (UC1–UC11) supported equality comparison, unit conversion, and addition across
multiple measurement categories such as **length**, **weight**, and **volume**.  

With UC12, the system evolves into a more complete arithmetic model for quantities, allowing users
to compute differences between measurements and ratios of measurements, while preserving the
generic and extensible architecture introduced in UC10.

---

### 2. Scope  

This use case covers:

- Subtraction between two quantities of the **same measurement category**
- Division to compute a **dimensionless ratio** between two quantities
- Cross-unit arithmetic within the same category (e.g., feet vs inches, litre vs millilitre)
- Explicit and implicit target unit support for subtraction
- Strong validation and error handling
- Backward compatibility with UC1–UC11  

The following categories are supported:

- Length (`LengthUnit`)
- Weight (`WeightUnit`)
- Volume (`VolumeUnit`)

---

### 3. Design Overview  

UC12 follows the same design principles established in UC10:

- **Generic Quantity Class**  
  All arithmetic logic is implemented inside `Quantity<U extends IMeasurable>`.  
  No changes are required in unit enums or in the `IMeasurable` interface.

- **Separation of Concerns**  
  - Units handle conversion logic  
  - Quantity handles arithmetic and comparison  
  - Application layer handles demonstration  

- **Open–Closed Principle**  
  The system is open for extension (new operations) and closed for modification
  (existing functionality remains unchanged).

---

## 4. New APIs Introduced  

### Subtraction  

```java
Quantity<U> subtract(Quantity<U> other)
Quantity<U> subtract(Quantity<U> other, U targetUnit)
```

### Division


```java
Quantity<U> divide(Quantity<U> other)
Quantity<U> divide(Quantity<U> other, U targetUnit)
```

**Code Link:** [UC-12 feature](https://github.com/Harshal-25C/QuantityMeasurementApp/tree/feature/UC12-SubtractionAndDivision)
