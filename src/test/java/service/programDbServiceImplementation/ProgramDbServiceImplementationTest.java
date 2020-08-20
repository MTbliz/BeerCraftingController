package service.programDbServiceImplementation;

import dao.ProgramRepository;
import entity.Program;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ProgramDbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgramDbServiceImplementationTest {

    @InjectMocks
    ProgramDbService programDbService = new ProgramDbServiceImplementation();

    @Mock
    ProgramRepository programRepository;

    @Test
    void saveProgram() throws SQLException {
        programDbService.saveProgram(new Program());
        verify(programRepository, times(1)).insertProgram(any(Program.class));
    }

    @Test
    void getProgramById() {
        Program program = new Program(1L, "program", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(programRepository.getProgramById(program.getId())).thenReturn(program);
        assertEquals(program, programDbService.getProgramById(program.getId()));
    }

    @Test
    void getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        Program program1 = new Program(1L, "program", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Program program2 = new Program(1L, "program", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        programs.add(program1);
        programs.add(program2);
        when(programRepository.getAllPrograms()).thenReturn(programs);
        assertEquals(programs.size(), programDbService.getAllPrograms().size());
    }
}