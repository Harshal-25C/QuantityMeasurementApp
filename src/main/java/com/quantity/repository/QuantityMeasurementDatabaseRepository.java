package com.quantity.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.util.ConnectionPool;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    public QuantityMeasurementDatabaseRepository() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {

        String sql = """
            CREATE TABLE IF NOT EXISTS QUANTITY_MEASUREMENT_ENTITY (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                operation VARCHAR(50),
                operand1 VARCHAR(100),
                operand2 VARCHAR(100),
                result VARCHAR(100),
                error_message VARCHAR(255),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;

        try (Connection connection = ConnectionPool.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMeasurement(QuantityMeasurementEntity entity) {

        String sql = """
            INSERT INTO QUANTITY_MEASUREMENT_ENTITY
            (operation, operand1, operand2, result, error_message)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection connection = ConnectionPool.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getOperation());
            ps.setString(2, entity.getOperand1());
            ps.setString(3, entity.getOperand2());
            ps.setString(4, entity.getResult());
            ps.setString(5, entity.getErrorMessage());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save measurement", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM QUANTITY_MEASUREMENT_ENTITY";

        try (Connection connection = ConnectionPool.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                QuantityMeasurementEntity entity =
                        new QuantityMeasurementEntity(
                                rs.getString("operation"),
                                rs.getString("operand1"),
                                rs.getString("operand2"),
                                rs.getString("result"),
                                rs.getString("error_message")
                        );

                list.add(entity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}