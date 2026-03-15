package com.quantity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityMeasurementEntity {

    public QuantityMeasurementEntity(String string, String string2, String string3, String valueOf, Object object) {
		// TODO Auto-generated constructor stub
	}
	private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String errorMessage;

}
