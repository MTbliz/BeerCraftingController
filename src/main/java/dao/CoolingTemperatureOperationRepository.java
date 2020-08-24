package dao;


import entity.temperatureOperations.CoolingTemperatureOperation;
import service.TemperatureService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CoolingTemperatureOperationRepository {

    DbConnector dbConnector;

    TemperatureService temperatureService;

    public CoolingTemperatureOperationRepository() {
    }

    public CoolingTemperatureOperationRepository(DbConnector dbConnector, TemperatureService temperatureService) {
        this.dbConnector = dbConnector;
        this.temperatureService = temperatureService;
    }

    public List<CoolingTemperatureOperation> getAllCoolingTemperatureOperations() {
        List<CoolingTemperatureOperation> coolingTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'Cooling';";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                CoolingTemperatureOperation coolingTemperatureOperation = new CoolingTemperatureOperation();
                Long id = resultSet.getLong("id");
                double finalTemperature = resultSet.getDouble("temperature");
                double speed = resultSet.getDouble("speed");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                coolingTemperatureOperation = new CoolingTemperatureOperation(id, finalTemperature, speed, operationType, program_id, temperatureService);
                coolingTemperatureOperations.add(coolingTemperatureOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coolingTemperatureOperations;
    }

    public List<CoolingTemperatureOperation> getAllCoolingTemperatureOperationsByProgramId(Long programId) {
        List<CoolingTemperatureOperation> coolingTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'Cooling' AND program_id = " + programId + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                CoolingTemperatureOperation coolingTemperatureOperation = new CoolingTemperatureOperation();
                Long id = resultSet.getLong("id");
                double finalTemperature = resultSet.getDouble("temperature");
                double speed = resultSet.getDouble("speed");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                coolingTemperatureOperation = new CoolingTemperatureOperation(id, finalTemperature, speed, operationType, program_id, temperatureService);
                coolingTemperatureOperations.add(coolingTemperatureOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coolingTemperatureOperations;
    }

    public CoolingTemperatureOperation getCoolingTemperatureOperationById(Long operation_id) {
        CoolingTemperatureOperation coolingTemperatureOperation = new CoolingTemperatureOperation();
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " + operation_id + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                double finalTemperature = resultSet.getDouble("temperature");
                double speed = resultSet.getDouble("speed");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                coolingTemperatureOperation = new CoolingTemperatureOperation(id, finalTemperature, speed, operationType, program_id, temperatureService);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coolingTemperatureOperation;
    }

    public void insertCoolingOperation(CoolingTemperatureOperation coolingTemperatureOperation) {
        String operationName = coolingTemperatureOperation.getOperationName();
        double finalTemperature = coolingTemperatureOperation.getFinalTemperature();
        double speed = coolingTemperatureOperation.getSpeed();
        String operationType = coolingTemperatureOperation.getOperationType();
        String query = "INSERT INTO TEMPERATURE_OPERATIONS " +
                "(operation_name, temperature, speed, operation_type, program_id) " +
                "VALUES (?,?,?,?,?);";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, finalTemperature);
            pstmt.setDouble(3, speed);
            pstmt.setString(4, operationType);
            pstmt.setLong(5, coolingTemperatureOperation.getProgram_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoolingOperation(CoolingTemperatureOperation coolingTemperatureOperation) {
        String operationName = coolingTemperatureOperation.getOperationName();
        double finalTemperature = coolingTemperatureOperation.getFinalTemperature();
        double speed = coolingTemperatureOperation.getSpeed();
        String query = "UPDATE TEMPERATURE_OPERATIONS " +
                "SET operation_name=?, temperature=?, speed=? " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, finalTemperature);
            pstmt.setDouble(3, speed);
            pstmt.setLong(4, coolingTemperatureOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCoolingOperation(CoolingTemperatureOperation coolingTemperatureOperation) {
        String query = "DELETE FROM TEMPERATURE_OPERATIONS " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setLong(1, coolingTemperatureOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
