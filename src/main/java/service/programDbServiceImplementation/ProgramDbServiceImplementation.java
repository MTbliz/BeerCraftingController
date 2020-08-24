package service.programDbServiceImplementation;

import dao.ProgramRepository;
import entity.Program;
import service.ProgramDbService;

import java.sql.SQLException;
import java.util.List;

public class ProgramDbServiceImplementation implements ProgramDbService {

    ProgramRepository programRepository;

    public ProgramDbServiceImplementation() {
    }

    public ProgramDbServiceImplementation(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

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
