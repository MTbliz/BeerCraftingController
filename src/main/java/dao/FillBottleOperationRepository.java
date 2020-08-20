package dao;

import entity.beerPackageOperations.BottleVolumeEnum;
import entity.beerPackageOperations.FillBottleOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FillBottleOperationRepository {

    DbConnector dbConnector = new DbConnector();

    public List<FillBottleOperation> getAllFillBottleOperations() {
        List<FillBottleOperation> fillBottleOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, volume, bottle_volume_enum, keg_volume_enum, program_id FROM package_operations WHERE operation_name = 'Bottling';";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                FillBottleOperation fillBottleOperation = new FillBottleOperation();
                Long id = resultSet.getLong("id");
                String bottle_volume_enum = resultSet.getString("bottle_volume_enum");
                Long program_id = resultSet.getLong("program_id");
                BottleVolumeEnum bottleVolumeEnum = BottleVolumeEnum.valueOf(bottle_volume_enum);
                fillBottleOperation = new FillBottleOperation(id, bottleVolumeEnum, program_id);
                fillBottleOperations.add(fillBottleOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillBottleOperations;
    }

    public List<FillBottleOperation> getAllFillBottleOperationsByProgramId(Long programId) {
        List<FillBottleOperation> fillBottleOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, volume, bottle_volume_enum, keg_volume_enum, program_id FROM package_operations" +
                " WHERE operation_name = 'Bottling' AND program_id = " + programId + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                FillBottleOperation fillBottleOperation = new FillBottleOperation();
                Long id = resultSet.getLong("id");
                String bottle_volume_enum = resultSet.getString("bottle_volume_enum");
                Long program_id = resultSet.getLong("program_id");
                BottleVolumeEnum bottleVolumeEnum = BottleVolumeEnum.valueOf(bottle_volume_enum);
                fillBottleOperation = new FillBottleOperation(id, bottleVolumeEnum, program_id);
                fillBottleOperations.add(fillBottleOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillBottleOperations;
    }

    public FillBottleOperation getFillBottleOperationById(Long operation_id) {
        FillBottleOperation fillBottleOperation = new FillBottleOperation();
        String query = "SELECT id, operation_name, volume, bottle_volume_enum, keg_volume_enum, program_id FROM package_operations WHERE id = " + operation_id + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String bottle_volume_enum = resultSet.getString("bottle_volume_enum");
                Long program_id = resultSet.getLong("program_id");
                BottleVolumeEnum bottleVolumeEnum = BottleVolumeEnum.valueOf(bottle_volume_enum);
                fillBottleOperation = new FillBottleOperation(id, bottleVolumeEnum, program_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillBottleOperation;
    }

    public void insertFillBottleOperation(FillBottleOperation fillBottleOperation) {
        String operationName = fillBottleOperation.getOperationName();
        double volume = fillBottleOperation.getVolume();
        String bottleVolumeEnum = String.valueOf(fillBottleOperation.getBottleVolumeEnum());
        String query = "INSERT INTO PACKAGE_OPERATIONS " +
                "(operation_name, volume, bottle_volume_enum, program_id) " +
                "VALUES (?,?,?,?);";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, volume);
            pstmt.setString(3, bottleVolumeEnum);
            pstmt.setLong(4, fillBottleOperation.getProgram_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFillBottleOperation(FillBottleOperation fillBottleOperation) {
        String operationName = fillBottleOperation.getOperationName();
        double volume = fillBottleOperation.getVolume();
        String bottleVolumeEnum = String.valueOf(fillBottleOperation.getBottleVolumeEnum());
        String query = "UPDATE PACKAGE_OPERATIONS " +
                "SET operation_name=?, volume=?, bottle_volume_enum=? " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, volume);
            pstmt.setString(3, bottleVolumeEnum);
            pstmt.setLong(4, fillBottleOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFillBottleOperation(FillBottleOperation fillBottleOperation) {
        String query = "DELETE FROM PACKAGE_OPERATIONS " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setLong(1, fillBottleOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
