package dao;

import entity.beerPackageOperations.BottleVolumeEnum;
import entity.beerPackageOperations.FillBottleOperation;
import entity.beerPackageOperations.FillKegOperation;
import entity.beerPackageOperations.KegVolumeEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FillKegOperationRepository {

    DbConnector dbConnector = new DbConnector();

    public List<FillKegOperation> getAllFillKegOperations() {
        List<FillKegOperation> fillKegOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, volume, keg_volume_enum, program_id FROM package_operations WHERE operation_name = 'Kegging';";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                FillKegOperation fillKegOperation = new FillKegOperation();
                Long id = resultSet.getLong("id");
                String keg_volume_enum = resultSet.getString("keg_volume_enum");
                Long program_id = resultSet.getLong("program_id");
                KegVolumeEnum kegVolumeEnum = KegVolumeEnum.valueOf(keg_volume_enum);
                fillKegOperation = new FillKegOperation(id, kegVolumeEnum, program_id);
                fillKegOperations.add(fillKegOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillKegOperations;
    }

    public List<FillKegOperation> getAllFillKegOperationsByProgramId(Long programId) {
        List<FillKegOperation> fillKegOperations = new ArrayList<>();
        String query = "SELECT id, operation_name, volume, keg_volume_enum, program_id FROM package_operations WHERE operation_name = 'Kegging' AND program_id = " + programId + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                FillKegOperation fillKegOperation = new FillKegOperation();
                Long id = resultSet.getLong("id");
                String keg_volume_enum = resultSet.getString("keg_volume_enum");
                Long program_id = resultSet.getLong("program_id");
                KegVolumeEnum kegVolumeEnum = KegVolumeEnum.valueOf(keg_volume_enum);
                fillKegOperation = new FillKegOperation(id, kegVolumeEnum, program_id);
                fillKegOperations.add(fillKegOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillKegOperations;
    }

    public FillKegOperation getFillKegOperationById(Long operation_id) {
        FillKegOperation fillKegOperation = new FillKegOperation();
        String query = "SELECT id, operation_name, volume, keg_volume_enum, program_id FROM package_operations WHERE id = " + operation_id + ";";
        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String keg_volume_enum = resultSet.getString("keg_volume_enum");
                Long program_id = resultSet.getLong("program_id");
                KegVolumeEnum kegVolumeEnum = KegVolumeEnum.valueOf(keg_volume_enum);
                fillKegOperation = new FillKegOperation(id, kegVolumeEnum, program_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillKegOperation;
    }

    public void insertFillKegOperation(FillKegOperation fillKegOperation) {
        String operationName = fillKegOperation.getOperationName();
        double volume = fillKegOperation.getVolume();
        String kegVolumeEnum = String.valueOf(fillKegOperation.getKegVolumeEnum());
        String query = "INSERT INTO PACKAGE_OPERATIONS " +
                "(operation_name, volume, keg_volume_enum, program_id) " +
                "VALUES (?,?,?,?);";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, volume);
            pstmt.setString(3, kegVolumeEnum);
            pstmt.setLong(4, fillKegOperation.getProgram_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFillKegOperation(FillKegOperation fillKegOperation) {
        String operationName = fillKegOperation.getOperationName();
        double volume = fillKegOperation.getVolume();
        String bottleVolumeEnum = String.valueOf(fillKegOperation.getKegVolumeEnum());
        String query = "UPDATE PACKAGE_OPERATIONS " +
                "SET operation_name=?, volume=?, keg_volume_enum=? " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, operationName);
            pstmt.setDouble(2, volume);
            pstmt.setString(3, bottleVolumeEnum);
            pstmt.setLong(4, fillKegOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFillKegOperation(FillKegOperation fillKegOperation) {
        String query = "DELETE FROM PACKAGE_OPERATIONS " +
                "WHERE id = ?";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setLong(1, fillKegOperation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
