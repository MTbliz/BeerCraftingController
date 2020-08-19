package service.programDbServiceImplementation;

import dao.ProgramRepository;
import entity.Program;
import service.ProgramDbService;

import java.sql.SQLException;
import java.util.List;

public class ProgramDbServiceImplementation implements ProgramDbService {

    ProgramRepository programRepository = new ProgramRepository();

    @Override
    public void saveProgram(Program program) {
        try {
            programRepository.insertProgram(program);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Program getProgramById(Long id) {
     return programRepository.getProgramById(id);
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.getAllPrograms();
    }
}
