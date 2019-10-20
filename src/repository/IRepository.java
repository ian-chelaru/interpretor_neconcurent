package repository;

import exception.repositoryexception.LogFileException;
import model.adt.PrgState;

public interface IRepository
{
    PrgState getCurrentProgram();
    void addProgram(PrgState newProgram);
    void logPrgStateExec() throws LogFileException;
}
