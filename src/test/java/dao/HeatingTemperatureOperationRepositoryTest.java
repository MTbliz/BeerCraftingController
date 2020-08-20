package dao;

import entity.temperatureOperations.HeatingTemperatureOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HeatingTemperatureOperationRepositoryTest {

    @InjectMocks
    private HeatingTemperatureOperationRepository heatingTemperatureOperationRepository;

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

    private List<HeatingTemperatureOperation> heatingTemperatureOperations;

    private HeatingTemperatureOperation heatingTemperatureOperation;

    @BeforeEach
    public void setUp() {
        heatingTemperatureOperations = new ArrayList<>();
        heatingTemperatureOperation = new HeatingTemperatureOperation(1L, 1000.0, 800.0, "Mashing", 1L);
        heatingTemperatureOperations.add(heatingTemperatureOperation);
    }

    @Test
    void getAllHeatingTemperatureOperations() throws SQLException {
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'Heating';";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(heatingTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(heatingTemperatureOperation.getFinalTemperature()).when(resultSet).getDouble("temperature");
        doReturn(heatingTemperatureOperation.getSpeed()).when(resultSet).getDouble("speed");
        doReturn(heatingTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(heatingTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<HeatingTemperatureOperation> result = heatingTemperatureOperationRepository.getAllHeatingTemperatureOperations();
        Assertions.assertEquals(heatingTemperatureOperations.size(), result.size());
    }

    @Test
    void getAllHeatingTemperatureOperationsByProgramId() throws SQLException {
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'Heating' AND program_id = " + heatingTemperatureOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(heatingTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(heatingTemperatureOperation.getFinalTemperature()).when(resultSet).getDouble("temperature");
        doReturn(heatingTemperatureOperation.getSpeed()).when(resultSet).getDouble("speed");
        doReturn(heatingTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(heatingTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<HeatingTemperatureOperation> result = heatingTemperatureOperationRepository.getAllHeatingTemperatureOperationsByProgramId(heatingTemperatureOperation.getProgram_id());
        Assertions.assertEquals(heatingTemperatureOperations.size(), result.size());
    }

    @Test
    void getHeatingTemperatureOperationById() throws SQLException {
        String query = "SELECT id, operation_name, temperature, speed, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " +
                heatingTemperatureOperation.getId() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(heatingTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(heatingTemperatureOperation.getFinalTemperature()).when(resultSet).getDouble("temperature");
        doReturn(heatingTemperatureOperation.getSpeed()).when(resultSet).getDouble("speed");
        doReturn(heatingTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(heatingTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        HeatingTemperatureOperation result = heatingTemperatureOperationRepository.getHeatingTemperatureOperationById(heatingTemperatureOperation.getId());
        Assertions.assertEquals(heatingTemperatureOperation.getOperationType(), result.getOperationType());
        Assertions.assertEquals(heatingTemperatureOperation.getFinalTemperature(), result.getFinalTemperature());
        Assertions.assertEquals(heatingTemperatureOperation.getSpeed(), result.getSpeed());
    }

    @Test
    void insertHeatingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        heatingTemperatureOperationRepository.insertHeatingOperation(heatingTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void updateHeatingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        heatingTemperatureOperationRepository.updateHeatingOperation(heatingTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void deleteHeatingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        heatingTemperatureOperationRepository.deleteHeatingOperation(heatingTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }
}