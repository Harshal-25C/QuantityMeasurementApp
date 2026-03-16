package com.quantity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityMeasurementEntity {

    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation,
                                     String operand1,
                                     String operand2,
                                     String result,
                                     String errorMessage) {

        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
//    @Override
//    public String toString() {
//        return operation + " | " + operand1 + " | " + operand2 + " | " + result;
//    }
}
