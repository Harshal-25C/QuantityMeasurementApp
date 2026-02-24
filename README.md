## UC11 – Volume Measurement: Equality, Conversion, and Addition  
(Litre, Millilitre, Gallon)

### Overview  
UC11 extends the Quantity Measurement Application to support **volume measurements** along with existing **length** and **weight** categories introduced up to UC10.  

This use case validates that the **generic architecture from UC10** scales seamlessly when a new measurement category is added. No changes are required in the existing `Quantity<U>`, `IMeasurable`, or application logic.  

A new `VolumeUnit` enum implementing `IMeasurable` is introduced to support volume-specific units and conversions.

---

### Objectives  

- Add support for volume measurement  
- Reuse the existing generic `Quantity<U extends IMeasurable>` class  
- Ensure no changes to existing UC1–UC10 implementation  
- Validate scalability of the refactored architecture  

---

### Supported Volume Units  

| Unit        | Symbol | Conversion to Base (Litre) |
|-------------|--------|-----------------------------|
| Litre       | L      | 1.0 (Base Unit)             |
| Millilitre  | mL     | 0.001                       |
| Gallon (US) | gal    | 3.78541                     |

Base unit for volume: **Litre (L)**  

---

### Design  

UC11 introduces only one new component:


All operations such as equality, conversion, and addition are handled by the existing generic `Quantity<U>` class without any modification.

---

## Functionalities  

### Equality Comparison  
- 1 L = 1000 mL  
- 1 gal = 3.78541 L  
- Equality is checked by converting values to the base unit (litre)  
- Floating-point precision is handled using epsilon tolerance  

### Unit Conversion  
Examples:  
- 1 L → 1000 mL  
- 1000 mL → 1 L  
- 1 gal → 3.78541 L  
- 1 L → 0.264172 gal  

### Addition  
Examples:  
- 1 L + 1000 mL = 2 L  
- 500 mL + 0.5 L = 1000 mL  
- 1 gal + 3.78541 L ≈ 2 gal  

Supports:  
- Implicit target unit (first operand’s unit)  
- Explicit target unit specification  

### Cross-Category Safety  
- Volume cannot be compared or added with length or weight  
- Generic type constraints prevent mixing categories  
- Runtime checks ensure category isolation  

---

### Test Coverage  

UC11 test cases cover:  

- Same-unit equality (L, mL, gal)  
- Cross-unit equality (L ↔ mL, L ↔ gal, mL ↔ gal)  
- Unit conversion between all unit pairs  
- Addition with same and different units  
- Addition with explicit target unit  
- Zero, negative, large, and small values  
- Floating-point precision handling  
- Immutability of `Quantity` objects  
- Volume vs length/weight incompatibility  
- Backward compatibility with UC1–UC10 test cases  

---

**Code Link:** [UC-11 feature](https://github.com/Harshal-25C/QuantityMeasurementApp/tree/feature/UC11-VolumeMeasurementEquality)

