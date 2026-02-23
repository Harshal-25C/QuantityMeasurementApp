package com.quantity.unit;

import java.util.function.DoubleBinaryOperator;

public enum ArithmeticOperation {

    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    DIVIDE((a, b) -> {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    });

    private final DoubleBinaryOperator operator;

    ArithmeticOperation(DoubleBinaryOperator operator) {
        this.operator = operator;
    }

    public double apply(double a, double b) {
        return operator.applyAsDouble(a, b);
    }
}