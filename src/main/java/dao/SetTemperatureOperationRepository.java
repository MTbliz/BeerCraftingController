package dao;

import entity.temperatureOperations.SetTemperatureOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SetTemperatureOperationRepository {

    public List<SetTemperatureOperation> getAllSetTemperatureOperations(){
        List<SetTemperatureOperation> setTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, temperature, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'SetTemperature';";
        try (
                Connection connection = DbConnector.connect();
                Statement  statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                SetTemperatureOperation setTemperatureOperation = new SetTemperatureOperation();
                Long id = resultSet.getLong("id");
                double temperature = resultSet.getDouble("temperature");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                setTemperatureOperation = new SetTemperatureOperation(id, temperature, operationType, program_id);
                setTemperatureOperations.add(setTemperatureOperation);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setTemperatureOperations;
    }

    public List<SetTemperatureOperation> getAllSetTemperatureOperationsByProgramId(Long programId){
        List<SetTemperatureOperation> setTemperatureOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, temperature, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'SetTemperature' AND program_id = " + programId + ";";
        try (
                Connection connection = DbConnector.connect();
                Statement  statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                SetTemperatureOperation setTemperatureOperation = new SetTemperatureOperation();
                Long id = resultSet.getLong("id");
                double temperature = resultSet.getDouble("temperature");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                setTemperatureOperation = new SetTemperatureOperation(id, temperature, operationType, program_id);
                setTemperatureOperations.add(setTemperatureOperation);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setTemperatureOperations;
    }

    public SetTemperatureOperation getSetTemperatureOperationById(Long operation_id){
        SetTemperatureOperation setTemperatureOperation = new SetTemperatureOperation();
        String query = "SELECT id, operation_name, temperature, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " + operation_id + ";";
        try (
                Connection connection = DbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int temperature = resultSet.getInt("temperature");
                String operationType = resultSet.getString("operation_type");
                Long program_id = resultSet.getLong("program_id");
                setTemperatureOperation = new SetTemperatureOperation(id, temperature, operationType, program_id);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setTemperatureOperation;
    }

    public void insertSetTemperatureOperation(SetTemperatureOperation setTemperatureOperation) {
        String operationName = setTemperatureOperation.getOperationName();
        double temperature = setTemperatureOperation.getTemperature();
        String operationType = setTemperatureOperation.getOperationType();
        String query = "INSERT INTO TEMPERATURE_OPERATIONS " +
                "(operation_name, temperature, operation_type, program_id) " +
                "VALUES (?,?,?,?);";
        Connection connection = DbConnector.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, temperature);
            pstmt.setString(3, operationType);
            pstmt.setLong(4, setTemperatureOperation.getProgram_id());
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

    public void updateSetTemperatureOperation(SetTemperatureOperation setTemperatureOperation) {
        String operationName = setTemperatureOperation.getOperationName();
        double temperature = setTemperatureOperation.getTemperature();
        String query = "UPDATE TEMPERATURE_OPERATIONS " +
                "SET operation_name=?, temperature=? " +
                "WHERE id = ?";
        Connection connection = DbConnector.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, temperature);
            pstmt.setLong(3, setTemperatureOperation.getId());
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

    public void deleteSetTemperatureOperation(SetTemperatureOperation setTemperatureOperation) {
        String query = "DELETE FROM TEMPERATURE_OPERATIONS " +
                "WHERE id = ?";
        Connection connection = DbConnector.connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setLong(1, setTemperatureOperation.getId());
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
