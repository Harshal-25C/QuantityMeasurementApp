## UC5 – Unit-to-Unit Conversion (Same Measurement Type)

### 📌 Description

Extends UC4 by adding explicit **unit-to-unit conversion support**.

Provides a public API:

`static double convert(double value, LengthUnit source, LengthUnit target)`

Supports conversion across:

- FEET  
- INCHES  
- YARDS  
- CENTIMETERS  

---

### 🎯 Objective

- Convert between any supported length units  
- Normalize to a base unit before conversion  
- Preserve mathematical accuracy within floating-point precision   
 