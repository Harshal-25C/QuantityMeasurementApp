package com.quantity.controller;

import com.quantity.dto.QuantityInputDTO;

import com.quantity.dto.QuantityMeasurementDTO;
import com.quantity.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // -------------------- COMPARE ------------------------

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public ResponseEntity<QuantityMeasurementDTO> compareQuantities(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {

        try {
            QuantityMeasurementDTO result = service.compare(quantityInputDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            QuantityMeasurementDTO error = new QuantityMeasurementDTO();
            error.setErrorMessage(e.getMessage());
            error.setError(true);
            return ResponseEntity.badRequest().body(error);
        }
    }

    // -------------------- CONVERT ----------------------

    @PostMapping("/convert")
    @Operation(summary = "Convert a quantity to another unit")
    public ResponseEntity<QuantityMeasurementDTO> convertQuantity(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {

        try {
            QuantityMeasurementDTO result = service.convert(quantityInputDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            QuantityMeasurementDTO error = new QuantityMeasurementDTO();
            error.setErrorMessage(e.getMessage());
            error.setError(true);
            return ResponseEntity.badRequest().body(error);
        }
    }

    // -------------------- ADD -----------------------

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public ResponseEntity<QuantityMeasurementDTO> addQuantities(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {

        try {
            QuantityMeasurementDTO result = service.add(quantityInputDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            QuantityMeasurementDTO error = new QuantityMeasurementDTO();
            error.setErrorMessage(e.getMessage());
            error.setError(true);
            return ResponseEntity.badRequest().body(error);
        }
    }

    // -------------------- SUBTRACT -----------------------

    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities")
    public ResponseEntity<QuantityMeasurementDTO> subtractQuantities(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {

        try {
            QuantityMeasurementDTO result = service.subtract(quantityInputDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            QuantityMeasurementDTO error = new QuantityMeasurementDTO();
            error.setErrorMessage(e.getMessage());
            error.setError(true);
            return ResponseEntity.badRequest().body(error);
        }
    }

    // ------------------- DIVIDE ----------------------

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public ResponseEntity<QuantityMeasurementDTO> divideQuantities(
            @Valid @RequestBody QuantityInputDTO quantityInputDTO) {

        try {
            QuantityMeasurementDTO result = service.divide(quantityInputDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            QuantityMeasurementDTO error = new QuantityMeasurementDTO();
            error.setErrorMessage(e.getMessage());
            error.setError(true);
            return ResponseEntity.badRequest().body(error);
        }
    }

    // ------------------ HISTORY BY OPERATION ----------------------

    @GetMapping("/history/operation/{operation}")
    @Operation(summary = "Get quantity measurement history by operation type")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(
            @PathVariable String operation) {

        try {
            List<QuantityMeasurementDTO> result = service.getHistoryByOperation(operation);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ----------------- HISTORY BY MEASUREMENT TYPE -------------------

    @GetMapping("/history/type/{measurementType}")
    @Operation(summary = "Get quantity measurement history by measurement type")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByMeasurementType(
            @PathVariable String measurementType) {

        try {
            List<QuantityMeasurementDTO> result = service.getHistoryByMeasurementType(measurementType);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --------------- OPERATION COUNT -----------------

    @GetMapping("/count/{operation}")
    @Operation(summary = "Get count of successful operations by operation type")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {

        try{
            long count = service.getOperationCount(operation);
            return ResponseEntity.ok(count);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // --------------- ERROR HISTORY -------------------

    @GetMapping("/history/errored")
    @Operation(summary = "Get all errored quantity measurements")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {

        try{
            List<QuantityMeasurementDTO> result = service.getErrorHistory();
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}