## UC10 – Generic Quantity Class with Unit Interface

### Description
Refactors UC1–UC9 into a single generic design using:

- `IMeasurable` interface
- Generic `Quantity<U extends IMeasurable>` class
- Unit enums implementing the interface (LengthUnit, WeightUnit)

Eliminates duplication between QuantityLength and QuantityWeight.
Establishes scalable multi-category measurement architecture.

## Architecture

### IMeasurable (Interface)
Defines unit contract:
- getConversionFactor()
- convertToBaseUnit(double)
- convertFromBaseUnit(double)
- getUnitName()

### Unit Enums
- LengthUnit implements IMeasurable
- WeightUnit implements IMeasurable
- Encapsulate conversion logic
- Immutable and thread-safe

### Generic Quantity<U extends IMeasurable>
- private final double value
- private final U unit
- equals() using base-unit normalization
- convertTo(U targetUnit)
- add(Quantity<U>)
- add(Quantity<U>, U targetUnit)
- Immutable value object
- hashCode() consistent with equals()

### Example Usage

Length:

new Quantity<>(1.0, LengthUnit.FEET)
    .equals(new Quantity<>(12.0, LengthUnit.INCHES))
→ true

Weight:

new Quantity<>(1.0, WeightUnit.KILOGRAM)
    .convertTo(WeightUnit.GRAM)
→ Quantity(1000.0, GRAM)

Addition:

new Quantity<>(1.0, LengthUnit.FEET)
    .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET)
→ Quantity(2.0, FEET)

### Cross-Category Safety

new Quantity<>(1.0, LengthUnit.FEET)
    .equals(new Quantity<>(1.0, WeightUnit.KILOGRAM))
→ false

Compiler prevents:
Quantity<LengthUnit> ≠ Quantity<WeightUnit>

### Improvements Over UC9

- Removes duplicate Quantity classes
- Removes duplicate unit enum logic
- DRY principle enforced
- SRP restored
- Single source of truth for equality & arithmetic
- Code growth becomes linear (not exponential)
- Simplified QuantityMeasurementApp

### Concepts Covered

- Generic Programming (<U extends IMeasurable>)
- Interface-Based Design
- DRY Principle
- Single Responsibility Principle
- Open-Closed Principle
- Polymorphism & Delegation
- Type Safety with Generics
- Runtime Category Validation
- Immutability
- Composition Over Inheritance
- Scalable Multi-Category Architecture

### Scalability Pattern

To add new category:

1. Create enum implementing IMeasurable (e.g., VolumeUnit)
2. Use existing Quantity<VolumeUnit>
3. No modification to Quantity class required

### Benefits

- One Quantity class for all categories
- Unified comparison, conversion, addition logic
- Minimal code duplication
- Enterprise-ready extensible design
- Backward compatible (UC1–UC9 preserved)
- Cleaner, maintainable architecture

---

**Code Link:** [UC-10 feature](https://github.com/Harshal-25C/QuantityMeasurementApp/tree/feature/UC10-MultiCategoryUnit)
