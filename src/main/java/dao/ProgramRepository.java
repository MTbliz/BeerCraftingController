package dao;

import entity.Program;
import entity.beerPackageOperations.BeerPackageOperation;
import entity.beerPackageOperations.FillBottleOperation;
import entity.beerPackageOperations.FillKegOperation;
import entity.temperatureOperations.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramRepository {

    DbConnector dbConnector = new DbConnector();
    HeatingTemperatureOperationRepository heatingTemperatureOperationRepository = new HeatingTemperatureOperationRepository();
    CoolingTemperatureOperationRepository coolingTemperatureOperationRepository = new CoolingTemperatureOperationRepository();
    KeepTemperatureOperationRepository keepTemperatureOperationRepository = new KeepTemperatureOperationRepository();
    SetTemperatureOperationRepository setTemperatureOperationRepository = new SetTemperatureOperationRepository();
    FillKegOperationRepository fillKegOperationRepository = new FillKegOperationRepository();
    FillBottleOperationRepository fillBottleOperationRepository = new FillBottleOperationRepository();

    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String query = "SELECT id, program_name FROM programs;";

        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String programName = resultSet.getString("program_name");
                List<TemperatureOperation> mashingOperations = new ArrayList<>();
                List<TemperatureOperation> fermentationOperations = new ArrayList<>();
                List<BeerPackageOperation> beerPackageOperations = new ArrayList<>();
                //Get all fermentation operations for program with id.
                fermentationOperations.addAll(heatingTemperatureOperationRepository.getAllHeatingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                fermentationOperations.addAll(coolingTemperatureOperationRepository.getAllCoolingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                fermentationOperations.addAll(keepTemperatureOperationRepository.getAllKeepTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                fermentationOperations.addAll(setTemperatureOperationRepository.getAllSetTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                //Get all mashing operations for program with id.
                mashingOperations.addAll(heatingTemperatureOperationRepository.getAllHeatingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                mashingOperations.addAll(coolingTemperatureOperationRepository.getAllCoolingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                mashingOperations.addAll(keepTemperatureOperationRepository.getAllKeepTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                mashingOperations.addAll(setTemperatureOperationRepository.getAllSetTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                //Get all bottling operations for program with id.
                beerPackageOperations.addAll(fillBottleOperationRepository.getAllFillBottleOperationsByProgramId(id));
                //Sort mashing and fermentation operations
                mashingOperations.sort(Comparator.comparing((tempOperation -> tempOperation.getId())));
                fermentationOperations.sort(Comparator.comparing((tempOperation -> tempOperation.getId())));
                Program program = new Program(id, programName, mashingOperations, fermentationOperations, beerPackageOperations);
                programs.add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

    public Program getProgramById(Long programId) {
        Program program = new Program();
        String programName = "";
        List<TemperatureOperation> mashingOperations = new ArrayList<>();
        List<TemperatureOperation> fermentationOperations = new ArrayList<>();
        List<BeerPackageOperation> beerPackageOperations = new ArrayList<>();

        String query = "SELECT id, program_name FROM programs WHERE id = " + programId + ";";

        try (
                Connection connection = dbConnector.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                programName = resultSet.getString("program_name");
                //Get all fermentation operations for program with id.
                fermentationOperations.addAll(heatingTemperatureOperationRepository.getAllHeatingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                fermentationOperations.addAll(coolingTemperatureOperationRepository.getAllCoolingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                fermentationOperations.addAll(keepTemperatureOperationRepository.getAllKeepTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                fermentationOperations.addAll(setTemperatureOperationRepository.getAllSetTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Fermentation")).collect(Collectors.toList()));
                //Get all mashing operations for program with id.
                mashingOperations.addAll(heatingTemperatureOperationRepository.getAllHeatingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                mashingOperations.addAll(coolingTemperatureOperationRepository.getAllCoolingTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                mashingOperations.addAll(keepTemperatureOperationRepository.getAllKeepTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                mashingOperations.addAll(setTemperatureOperationRepository.getAllSetTemperatureOperationsByProgramId(id).stream()
                        .filter(o -> o.getOperationType().equals("Mashing")).collect(Collectors.toList()));
                //Get all bottling operations for program with id.
                beerPackageOperations.addAll(fillBottleOperationRepository.getAllFillBottleOperationsByProgramId(id));
                beerPackageOperations.addAll(fillKegOperationRepository.getAllFillKegOperationsByProgramId(id));
            }
            program = new Program(programId, programName, mashingOperations, fermentationOperations, beerPackageOperations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return program;
    }

    public void insertProgram(Program program) throws SQLException {
        String query = "INSERT INTO PROGRAMS " +
                "(program_name) " +
                "VALUES (?);";
        try (
                Connection connection = dbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, program.getProgramName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating program failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long programId = generatedKeys.getLong(1);
                    program.getMashingOperations().forEach(o -> {
                        o.setProgram_id(programId);
                        insertAppropriateTemperatureOperation(o);
                    });
                    program.getFermentationOperations().forEach(o -> {
                        o.setProgram_id(programId);
                        insertAppropriateTemperatureOperation(o);
                    });
                    program.getBeerPackageOperations().forEach(o -> {
                        o.setProgram_id(programId);
                        insertAppropriatePackageOperation(o);
                    });

                } else {
                    throw new SQLException("Creating program failed, no ID obtained.");
                }
            }
        }
    }

    private void insertAppropriateTemperatureOperation(TemperatureOperation temperatureOperation) {
        if (temperatureOperation instanceof HeatingTemperatureOperation) {
            heatingTemperatureOperationRepository.insertHeatingOperation((HeatingTemperatureOperation) temperatureOperation);
        } else if (temperatureOperation instanceof CoolingTemperatureOperation) {
            coolingTemperatureOperationRepository.insertCoolingOperation((CoolingTemperatureOperation) temperatureOperation);
        } else if (temperatureOperation instanceof KeepTemperatureOperation) {
            keepTemperatureOperationRepository.insertKeepTemperatureOperation((KeepTemperatureOperation) temperatureOperation);
        } else if (temperatureOperation instanceof SetTemperatureOperation) {
            setTemperatureOperationRepository.insertSetTemperatureOperation((SetTemperatureOperation) temperatureOperation);
        }
    }

    private void insertAppropriatePackageOperation(BeerPackageOperation beerPackageOperation) {
        if (beerPackageOperation instanceof FillBottleOperation) {
            fillBottleOperationRepository.insertFillBottleOperation((FillBottleOperation) beerPackageOperation);
        } else if (beerPackageOperation instanceof FillKegOperation) {
            fillKegOperationRepository.insertFillKegOperation((FillKegOperation) beerPackageOperation);
        }
    }
}