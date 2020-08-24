package dao;

import entity.temperatureOperations.SetTemperatureOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import service.TemperatureService;
import service.temperatureServiceImplementation.TemperatureServiceImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SetTemperatureOperationRepositoryTest {

    @InjectMocks
    private SetTemperatureOperationRepository setTemperatureOperationRepository;

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

    private List<SetTemperatureOperation> setTemperatureOperations;

    private SetTemperatureOperation setTemperatureOperation;

    @BeforeEach
    public void setUp() {
        TemperatureService temperatureService = new TemperatureServiceImplementation();
        setTemperatureOperations = new ArrayList<>();
        setTemperatureOperation = new SetTemperatureOperation(1L, 1000.0,  "Mashing", 1L, temperatureService);
        setTemperatureOperations.add(setTemperatureOperation);
    }

    @Test
    void getAllSetTemperatureOperations() throws SQLException {
        String query = "SELECT id, operation_name, temperature, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'SetTemperature';";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(setTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(setTemperatureOperation.getTemperature()).when(resultSet).getDouble("temperature");
        doReturn(setTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(setTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<SetTemperatureOperation> result = setTemperatureOperationRepository.getAllSetTemperatureOperations();
        Assertions.assertEquals(setTemperatureOperations.size(), result.size());
    }

    @Test
    void getAllSetTemperatureOperationsByProgramId() throws SQLException {
        String query = "SELECT id, operation_name, temperature, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'SetTemperature' AND program_id = " + setTemperatureOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(setTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(setTemperatureOperation.getTemperature()).when(resultSet).getDouble("temperature");
        doReturn(setTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(setTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<SetTemperatureOperation> result = setTemperatureOperationRepository.getAllSetTemperatureOperationsByProgramId(setTemperatureOperation.getProgram_id());
        Assertions.assertEquals(setTemperatureOperations.size(), result.size());
    }

    @Test
    void getSetTemperatureOperationById() throws SQLException {
        String query = "SELECT id, operation_name, temperature, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " +
                setTemperatureOperation.getId() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(setTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(setTemperatureOperation.getTemperature()).when(resultSet).getDouble("temperature");
        doReturn(setTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(setTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        SetTemperatureOperation result = setTemperatureOperationRepository.getSetTemperatureOperationById(setTemperatureOperation.getId());
        Assertions.assertEquals(setTemperatureOperation.getOperationType(), result.getOperationType());
        Assertions.assertEquals(setTemperatureOperation.getTemperature(), result.getTemperature());
    }

    @Test
    void insertSetOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        setTemperatureOperationRepository.insertSetTemperatureOperation(setTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void updateSetOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        setTemperatureOperationRepository.updateSetTemperatureOperation(setTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void deleteSetOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        setTemperatureOperationRepository.deleteSetTemperatureOperation(setTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }
}