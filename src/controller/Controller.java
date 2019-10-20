package controller;

import exception.modelexception.*;
import exception.repositoryexception.LogFileException;
import model.adt.MyIMap;
import model.adt.MyIStack;
import model.adt.MyITuple;
import model.adt.PrgState;
import model.stmt.IStmt;
import repository.IRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller
{
    private IRepository repo;

    public Controller(IRepository repo)
    {
        this.repo = repo;
    }

    public PrgState oneStep(PrgState state) throws VariableNotDefinedException, EmptyStackException,
            InvalidOperatorException, FileAlreadyOpenedException, FileDescriptorException, OpenReadFileException,
            IOException, DivisionByZeroException, HeapAllocationException
    {
        MyIStack<IStmt> stk = state.getExeStack();
        IStmt currentStmt = stk.pop();
        return currentStmt.execute(state);
    }

    public void allSteps() throws VariableNotDefinedException, InvalidOperatorException, FileAlreadyOpenedException,
            FileDescriptorException, OpenReadFileException, IOException, DivisionByZeroException, LogFileException,
            HeapAllocationException
    {
        PrgState program = repo.getCurrentProgram();
        repo.logPrgStateExec();
        try
        {
            while (true)
            {
                oneStep(program);
                program.getHeap().setContent(conservativeGarbageCollector(program.getSymTable().getContent().values()
                        ,program.getHeap().getContent()));
                repo.logPrgStateExec();
            }
        }
        catch (EmptyStackException e)
        {

        }
        finally
        {
            MyIMap<MyITuple<String,BufferedReader>> fileTable = program.getFileTable();
            closeOpenedFiles(fileTable);
            fileTable.setContent(new HashMap<>());
            repo.logPrgStateExec();
        }
    }

    public Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
            Map<Integer,Integer> heap)
    {
        return heap.entrySet().stream().filter(e -> symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    public void closeOpenedFiles(MyIMap<MyITuple<String,BufferedReader>> fileTable)
    {
        fileTable.getContent().values().stream().map(v -> v.getY()).forEach(br ->
        {
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                System.out.println("Could not close all the opened files");
            }
        });
    }

    public void displayCurrentState()
    {
        System.out.println(repo.getCurrentProgram().toString());
    }

}
