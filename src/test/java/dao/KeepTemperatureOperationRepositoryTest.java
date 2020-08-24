package dao;

import entity.temperatureOperations.KeepTemperatureOperation;
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
class KeepTemperatureOperationRepositoryTest {

    @InjectMocks
    private KeepTemperatureOperationRepository keepTemperatureOperationRepository;

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

    private List<KeepTemperatureOperation> keepTemperatureOperations;

    private KeepTemperatureOperation keepTemperatureOperation;

    @BeforeEach
    public void setUp() {
        TemperatureService temperatureService = new TemperatureServiceImplementation();
        keepTemperatureOperations = new ArrayList<>();
        keepTemperatureOperation = new KeepTemperatureOperation(1L, 100, "Mashing", 1L, temperatureService);
        keepTemperatureOperations.add(keepTemperatureOperation);
    }

    @Test
    void getAllKeepTemperatureOperations() throws SQLException {
        String query = "SELECT id, operation_name, minutes, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE operation_name = 'KeepTemperature';";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(keepTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(keepTemperatureOperation.getMinutes()).when(resultSet).getInt("minutes");
        doReturn(keepTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(keepTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<KeepTemperatureOperation> result = keepTemperatureOperationRepository.getAllKeepTemperatureOperations();
        Assertions.assertEquals(keepTemperatureOperations.size(), result.size());
    }

    @Test
    void getAllKeepTemperatureOperationsByProgramId() throws SQLException {
        String query = "SELECT id, operation_name, minutes, operation_type, program_id FROM TEMPERATURE_OPERATIONS" +
                " WHERE operation_name = 'KeepTemperature' AND program_id = " + keepTemperatureOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(keepTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(keepTemperatureOperation.getMinutes()).when(resultSet).getInt("minutes");
        doReturn(keepTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(keepTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<KeepTemperatureOperation> result = keepTemperatureOperationRepository.getAllKeepTemperatureOperationsByProgramId(keepTemperatureOperation.getProgram_id());
        Assertions.assertEquals(keepTemperatureOperations.size(), result.size());
    }

    @Test
    void getKeepTemperatureOperationById() throws SQLException {
        String query = "SELECT id, operation_name, minutes, operation_type, program_id FROM TEMPERATURE_OPERATIONS WHERE id = " +
                keepTemperatureOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(keepTemperatureOperation.getId()).when(resultSet).getLong("id");
        doReturn(keepTemperatureOperation.getMinutes()).when(resultSet).getInt("minutes");
        doReturn(keepTemperatureOperation.getOperationType()).when(resultSet).getString("operation_type");
        doReturn(keepTemperatureOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        KeepTemperatureOperation result = keepTemperatureOperationRepository.getKeepTemperatureOperationById(keepTemperatureOperation.getId());
        Assertions.assertEquals(keepTemperatureOperation.getOperationType(), result.getOperationType());
        Assertions.assertEquals(keepTemperatureOperation.getMinutes(), result.getMinutes());
    }

    @Test
    void insertKeepOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        keepTemperatureOperationRepository.insertKeepTemperatureOperation(keepTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void updateKeepOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        keepTemperatureOperationRepository.updateKeepTemperatureOperation(keepTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void deleteKeepOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        keepTemperatureOperationRepository.deleteKeepTemperatureOperation(keepTemperatureOperation);
        verify(pstmt, times(1)).executeUpdate();
    }
}