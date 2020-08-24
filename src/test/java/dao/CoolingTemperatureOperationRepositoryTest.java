package dao;

import entity.temperatureOperations.CoolingTemperatureOperation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoolingTemperatureOperationRepositoryTest {

    @InjectMocks
    private CoolingTemperatureOperationRepository coolingTemperatureOperationRepository;

    @Mock
    private DbConnector dbConnector;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement pstmt;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    private List<CoolingTemperatureOperation> coolingTemperatureOperations;

    private CoolingTemperatureOperation coolingTemperatureOperation;

    @BeforeEach
    public void setUp() {
        TemperatureService temperatureService = new TemperatureServiceImplementation();
        coolingTemperatureOperations = new ArrayList<>();
        coolingTemperatureOperation = new CoolingTemperatureOperation(1L, 1000.0, 800.0, "Mashing", 1L, temperatureService);
        coolingTemperatureOperations.add(coolingTemperatureOperation);
    }

    @Test
    void getAllCoolingTemperatureOperations() throws SQLException {
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'Cooling';";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(coolingTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(coolingTemperatureOperation.getFinalTemperature()).when(resultSet).getDouble("temperature");
        doReturn(coolingTemperatureOperation.getSpeed()).when(resultSet).getDouble("speed");
        doReturn(coolingTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(coolingTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<CoolingTemperatureOperation> result = coolingTemperatureOperationRepository.getAllCoolingTemperatureOperations();
        Assertions.assertEquals(coolingTemperatureOperations.size(), result.size());
    }

    @Test
    void getAllCoolingTemperatureOperationsByProgramId() throws SQLException {
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'Cooling' AND program_id = " + coolingTemperatureOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(coolingTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(coolingTemperatureOperation.getFinalTemperature()).when(resultSet).getDouble("temperature");
        doReturn(coolingTemperatureOperation.getSpeed()).when(resultSet).getDouble("speed");
        doReturn(coolingTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(coolingTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<CoolingTemperatureOperation> result = coolingTemperatureOperationRepository.getAllCoolingTemperatureOperationsByProgramId(coolingTemperatureOperation.getProgram_id());
        Assertions.assertEquals(coolingTemperatureOperations.size(), result.size());
    }

    @Test
    void getCoolingTemperatureOperationById() throws SQLException {
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " +
                coolingTemperatureOperation.getId() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(coolingTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(coolingTemperatureOperation.getFinalTemperature()).when(resultSet).getDouble("temperature");
        doReturn(coolingTemperatureOperation.getSpeed()).when(resultSet).getDouble("speed");
        doReturn(coolingTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(coolingTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        CoolingTemperatureOperation result = coolingTemperatureOperationRepository.getCoolingTemperatureOperationById(coolingTemperatureOperation.getId());
        Assertions.assertEquals(coolingTemperatureOperation.getOperationType(), result.getOperationType());
        Assertions.assertEquals(coolingTemperatureOperation.getFinalTemperature(), result.getFinalTemperature());
        Assertions.assertEquals(coolingTemperatureOperation.getSpeed(), result.getSpeed());
    }

    @Test
    void insertCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        coolingTemperatureOperationRepository.insertCoolingOperation(coolingTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void updateCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        coolingTemperatureOperationRepository.updateCoolingOperation(coolingTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void deleteCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        coolingTemperatureOperationRepository.deleteCoolingOperation(coolingTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }
}