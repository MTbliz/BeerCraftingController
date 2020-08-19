package dao;

import entity.Program;
import entity.temperatureOperations.HeatingTemperatureOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeatingTemperatureOperationRepository {

    public List<HeatingTemperatureOperation> getAllHeatingTemperatureOperations(){
        List<HeatingTemperatureOperation> heatingTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'Heating';";
        try (
                Connection connection = DbConnector.connect();
                Statement  statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                HeatingTemperatureOperation heatingTemperatureOperation = new HeatingTemperatureOperation();
                Long id = resultSet.getLong("id");
                double finalTemperature = resultSet.getDouble("temperature");
                double speed = resultSet.getDouble("speed");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                heatingTemperatureOperation = new HeatingTemperatureOperation(id, finalTemperature, speed, operationType, program_id);
                heatingTemperatureOperations.add(heatingTemperatureOperation);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heatingTemperatureOperations;
    }

    public List<HeatingTemperatureOperation> getAllHeatingTemperatureOperationsByProgramId(Long programId){
        List<HeatingTemperatureOperation> heatingTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'Heating' AND program_id = " + programId + ";";
        try (
                Connection connection = DbConnector.connect();
                Statement  statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                HeatingTemperatureOperation heatingTemperatureOperation = new HeatingTemperatureOperation();
                Long id = resultSet.getLong("id");
                double finalTemperature = resultSet.getDouble("temperature");
                double speed = resultSet.getDouble("speed");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                heatingTemperatureOperation = new HeatingTemperatureOperation(id, finalTemperature, speed, operationType, program_id);
                heatingTemperatureOperations.add(heatingTemperatureOperation);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heatingTemperatureOperations;
    }

    public HeatingTemperatureOperation getHeatingTemperatureOperationById(Long operation_id){
        HeatingTemperatureOperation heatingTemperatureOperation = new HeatingTemperatureOperation();
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " + operation_id + ";";
        try (
                Connection connection = DbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                double finalTemperature = resultSet.getDouble("temperature");
                double speed = resultSet.getDouble("speed");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                heatingTemperatureOperation = new HeatingTemperatureOperation(id, finalTemperature, speed, operationType, program_id);
            }
            //resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heatingTemperatureOperation;
    }

    public void insertHeatingOperation(HeatingTemperatureOperation heatingTemperatureOperation) {
        String operationName = heatingTemperatureOperation.getOperationName();
        double finalTemperature = heatingTemperatureOperation.getFinalTemperature();
        double speed = heatingTemperatureOperation.getSpeed();
        String operationType = heatingTemperatureOperation.getOperationType();
        String query = "INSERT INTO TEMPERATURE_OPERATIONS " +
                "(operation_name, temperature, speed, operation_type, program_id) " +
                "VALUES (?,?,?,?,?);";
        Connection connection = DbConnector.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, finalTemperature);
            pstmt.setDouble(3, speed);
            pstmt.setString(4, operationType);
            pstmt.setLong(5, heatingTemperatureOperation.getProgram_id());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void updateHeatingOperation(HeatingTemperatureOperation heatingTemperatureOperation) {
        String operationName = heatingTemperatureOperation.getOperationName();
        double finalTemperature = heatingTemperatureOperation.getFinalTemperature();
        double speed = heatingTemperatureOperation.getSpeed();
        String query = "UPDATE TEMPERATURE_OPERATIONS " +
                "SET operation_name=?, temperature=?, speed=? " +
                "WHERE id = ?";
        Connection connection = DbConnector.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, finalTemperature);
            pstmt.setDouble(3, speed);
            pstmt.setLong(4, heatingTemperatureOperation.getId());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void deleteHeatingOperation(HeatingTemperatureOperation heatingTemperatureOperation) {
        String query = "DELETE FROM TEMPERATURE_OPERATIONS " +
                "WHERE id = ?";
        Connection connection = DbConnector.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setLong(1, heatingTemperatureOperation.getId());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}

