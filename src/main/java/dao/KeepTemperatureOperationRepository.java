package dao;

import entity.temperatureOperations.KeepTemperatureOperation;
import service.TemperatureService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeepTemperatureOperationRepository {

    DbConnector dbConnector;

    TemperatureService temperatureService;

    public KeepTemperatureOperationRepository() {
    }

    public KeepTemperatureOperationRepository(DbConnector dbConnector, TemperatureService temperatureService) {
        this.dbConnector = dbConnector;
        this.temperatureService = temperatureService;
    }

    public List<KeepTemperatureOperation> getAllKeepTemperatureOperations() {
        List<KeepTemperatureOperation> keepTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, minutes, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'KeepTemperature';";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                KeepTemperatureOperation keepTemperatureOperation = new KeepTemperatureOperation();
                Long id = resultSet.getLong("id");
                int minutes = resultSet.getInt("minutes");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                keepTemperatureOperation = new KeepTemperatureOperation(id, minutes, operationType, program_id, temperatureService);
                keepTemperatureOperations.add(keepTemperatureOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keepTemperatureOperations;
    }

    public List<KeepTemperatureOperation> getAllKeepTemperatureOperationsByProgramId(Long programId) {
        List<KeepTemperatureOperation> keepTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, minutes, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'KeepTemperature' AND program_id = " + programId + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                KeepTemperatureOperation keepTemperatureOperation = new KeepTemperatureOperation();
                Long id = resultSet.getLong("id");
                int minutes = resultSet.getInt("minutes");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                keepTemperatureOperation = new KeepTemperatureOperation(id, minutes, operationType, program_id, temperatureService);
                keepTemperatureOperations.add(keepTemperatureOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keepTemperatureOperations;
    }

    public KeepTemperatureOperation getKeepTemperatureOperationById(Long operation_id) {
        KeepTemperatureOperation keepTemperatureOperation = new KeepTemperatureOperation();
        String query = "SELECT id, operation_name, minutes, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " + operation_id + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int minutes = resultSet.getInt("minutes");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                keepTemperatureOperation = new KeepTemperatureOperation(id, minutes, operationType, program_id, temperatureService);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keepTemperatureOperation;
    }

    public void insertKeepTemperatureOperation(KeepTemperatureOperation keepTemperatureOperation) {
        String operationName = keepTemperatureOperation.getOperationName();
        double minutes = keepTemperatureOperation.getMinutes();
        String operationType = keepTemperatureOperation.getOperationType();
        String query = "INSERT INTO TEMPERATURE_OPERATIONS " +
                "(operation_name, minutes, operation_type, program_id) " +
                "VALUES (?,?,?,?);";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, minutes);
            pstmt.setString(3, operationType);
            pstmt.setLong(4, keepTemperatureOperation.getProgram_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKeepTemperatureOperation(KeepTemperatureOperation keepTemperatureOperation) {
        String operationName = keepTemperatureOperation.getOperationName();
        double minutes = keepTemperatureOperation.getMinutes();
        String query = "UPDATE TEMPERATURE_OPERATIONS " +
                "SET operation_name=?, minutes=? " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, minutes);
            pstmt.setLong(3, keepTemperatureOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKeepTemperatureOperation(KeepTemperatureOperation keepTemperatureOperation) {
        String query = "DELETE FROM TEMPERATURE_OPERATIONS " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setLong(1, keepTemperatureOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
