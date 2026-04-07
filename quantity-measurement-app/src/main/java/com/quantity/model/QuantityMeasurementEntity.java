package com.quantity.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurement_entity", indexes = { @Index(name = "idx_operation", columnList = "operation"),
		@Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
		@Index(name = "idx_created_at", columnList = "created_at") })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "this_value", nullable = false)
	private double thisValue;
	@Column(name = "this_unit", nullable = false)
	private String thisUnit;
	@Column(name = "this_measurement_type", nullable = false)
	private String thisMeasurementType;

	@Column(name = "that_value", nullable = false)
	private double thatValue;
	@Column(name = "that_unit", nullable = false)
	private String thatUnit;
	@Column(name = "that_measurement_type", nullable = false)
	private String thatMeasurementType;

	@Column(name = "operation", nullable = false)
	private String operation;

	@Column(name = "result_string")
	private String resultString;
	@Column(name = "result_value")
	private double resultValue;
	@Column(name = "result_unit")
	private String resultUnit;
	@Column(name = "result_measurement_type")
	private String resultMeasurementType;

	@Column(name = "error_message")
	private String errorMessage;
	@Column(name = "is_error", nullable = false)
	private boolean isError;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}