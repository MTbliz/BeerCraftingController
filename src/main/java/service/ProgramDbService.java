package service;

import entity.Program;

import java.util.List;

public interface ProgramDbService {

    void saveProgram(Program program);

    Program getProgramById(Long id);

    List<Program> getAllPrograms();
}
