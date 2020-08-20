package dao;

import entity.beerPackageOperations.FillKegOperation;
import entity.beerPackageOperations.KegVolumeEnum;
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
class FillKegOperationRepositoryTest {

    @InjectMocks
    private FillKegOperationRepository fillKegOperationRepository;

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

    private List<FillKegOperation> fillKegOperations;

    private FillKegOperation fillKegOperation;

    @BeforeEach
    public void setUp() {
        fillKegOperations = new ArrayList<>();
        fillKegOperation = new FillKegOperation(1L, KegVolumeEnum.TWENTY_LITERS, 1L);
        fillKegOperations.add(fillKegOperation);
    }

    @Test
    void getAllFillKegOperations() throws SQLException {
        String query = "SELECT id, operation_name, volume, keg_volume_enum, program_id FROM package_operations WHERE operation_name = 'Kegging';";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(fillKegOperation.getId()).when(resultSet).getLong("id");
        doReturn(fillKegOperation.getKegVolumeEnum().toString()).when(resultSet).getString("keg_volume_enum");
        doReturn(fillKegOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<FillKegOperation> result = fillKegOperationRepository.getAllFillKegOperations();
        Assertions.assertEquals(fillKegOperations.size(), result.size());
    }

    @Test
    void getAllFillKegOperationsByProgramId() throws SQLException {
        String query = "SELECT id, operation_name, volume, keg_volume_enum, program_id FROM package_operations WHERE operation_name = 'Kegging' AND program_id = " +
                fillKegOperation.getProgram_id() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(fillKegOperation.getId()).when(resultSet).getLong("id");
        doReturn(fillKegOperation.getKegVolumeEnum().toString()).when(resultSet).getString("keg_volume_enum");
        doReturn(fillKegOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        List<FillKegOperation> result = fillKegOperationRepository.getAllFillKegOperationsByProgramId(fillKegOperation.getProgram_id());
        Assertions.assertEquals(fillKegOperations.size(), result.size());
    }

    @Test
    void getFillKegOperationById() throws SQLException {
        String query = "SELECT id, operation_name, volume, keg_volume_enum, program_id FROM package_operations WHERE id = " + fillKegOperation.getId() + ";";
        doReturn(connection).when(dbConnector).connect();
        doReturn(statement).when(connection).createStatement();
        doReturn(resultSet).when(statement).executeQuery(query);
        doReturn(fillKegOperation.getId()).when(resultSet).getLong("id");
        doReturn(fillKegOperation.getKegVolumeEnum().toString()).when(resultSet).getString("keg_volume_enum");
        ;
        doReturn(fillKegOperation.getProgram_id()).when(resultSet).getLong("program_id");
        when(resultSet.next()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return true;

                return false;
            }
        });
        FillKegOperation result = fillKegOperationRepository.getFillKegOperationById(fillKegOperation.getId());
        Assertions.assertEquals(fillKegOperation.getVolume(), result.getVolume());
        Assertions.assertEquals(fillKegOperation.getOperationName(), result.getOperationName());
    }

    @Test
    void insertCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        fillKegOperationRepository.insertFillKegOperation(fillKegOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void updateCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        fillKegOperationRepository.updateFillKegOperation(fillKegOperation);
        verify(pstmt, times(1)).executeUpdate();
    }

    @Test
    void deleteCoolingOperation() throws SQLException {
        doReturn(connection).when(dbConnector).connect();
        doReturn(pstmt).when(connection).prepareStatement(any(String.class));
        doReturn(1).when(pstmt).executeUpdate();
        fillKegOperationRepository.deleteFillKegOperation(fillKegOperation);
        verify(pstmt, times(1)).executeUpdate();
    }
}