package dao;


import entity.beerPackageOperations.BottleVolumeEnum;
import entity.beerPackageOperations.FillBottleOperation;
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
class FillBottleOperationRepositoryTest {

    @InjectMocks
    private FillBottleOperationRepository fillBottleOperationRepository;

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

    private List<FillBottleOperation> fillBottleOperations;

    private FillBottleOperation fillBottleOperation;

    @BeforeEach
    public void setUp() {
        fillBottleOperations = new ArrayList<>();
        fillBottleOperation = new FillBottleOperation(1L, BottleVolumeEnum.FIVE_HUNDRED_MILLILITERS, 1L);
        fillBottleOperations.add(fillBottleOperation);
    }

    @Test
    void getAllFillBottleOperations() throws SQLException {
        String query = "SELECT id, operation_name, volume, bottle_volume_enum, keg_volume_enum, program_id FROM package_operations WHERE operation_name = 'Bottling';";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(fillBottleOperation.getId()).when(resultSet).getLong("id");
        doReturn(fillBottleOperation.getBottleVolumeEnum().toString()).when(resultSet).getString("bottle_volume_enum");
        doReturn(fillBottleOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<FillBottleOperation> result = fillBottleOperationRepository.getAllFillBottleOperations();
        Assertions.assertEquals(fillBottleOperations.size(), result.size());
    }

    @Test
    void getAllFillBottleOperationsByProgramId() throws SQLException {
        String query = "SELECT id, operation_name, volume, bottle_volume_enum, keg_volume_enum, program_id FROM package_operations" +
                " WHERE operation_name = 'Bottling' AND program_id = " + fillBottleOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(fillBottleOperation.getId()).when(resultSet).getLong("id");
        doReturn(fillBottleOperation.getBottleVolumeEnum().toString()).when(resultSet).getString("bottle_volume_enum");
        doReturn(fillBottleOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<FillBottleOperation> result = fillBottleOperationRepository.getAllFillBottleOperationsByProgramId(fillBottleOperation.getProgram_id());
        Assertions.assertEquals(fillBottleOperations.size(), result.size());
    }

    @Test
    void getFillBottleOperationById() throws SQLException {
        String query = "SELECT id, operation_name, volume, bottle_volume_enum, keg_volume_enum, program_id FROM package_operations WHERE id = " +
                fillBottleOperation.getId() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(fillBottleOperation.getId()).when(resultSet).getLong("id");
        doReturn(fillBottleOperation.getBottleVolumeEnum().toString()).when(resultSet).getString("bottle_volume_enum");;
        doReturn(fillBottleOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        FillBottleOperation result = fillBottleOperationRepository.getFillBottleOperationById(fillBottleOperation.getId());
        Assertions.assertEquals(fillBottleOperation.getVolume(), result.getVolume());
        Assertions.assertEquals(fillBottleOperation.getOperationName(), result.getOperationName());
    }

    @Test
    void insertCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        fillBottleOperationRepository.insertFillBottleOperation(fillBottleOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void updateCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        fillBottleOperationRepository.updateFillBottleOperation(fillBottleOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void deleteCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        fillBottleOperationRepository.deleteFillBottleOperation(fillBottleOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

}