package repository;

import exception.repositoryexception.LogFileException;
import model.adt.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repository implements IRepository
{
    private ArrayList<PrgState> programs;
    private String logFilePath;

    public Repository(String logFilePath)
    {
        programs = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    public PrgState getCurrentProgram()
    {
        return programs.get(0);
    }

    public void addProgram(PrgState newProgram)
    {
        programs.add(newProgram);
    }

    public void logPrgStateExec() throws LogFileException
    {
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.printf(getCurrentProgram().toString());
            logFile.close();
        }
        catch (IOException e)
        {
            throw new LogFileException();
        }
    }
}
