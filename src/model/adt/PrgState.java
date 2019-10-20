package model.adt;

import model.stmt.IStmt;

import java.io.BufferedReader;

public class PrgState
{
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Integer> symTable;
    private MyIList<Integer> out;
    private MyIMap<MyITuple<String,BufferedReader>> fileTable;
    private MyIMap<Integer> heap;
    private IStmt originalProgram;

    public PrgState(MyIStack<IStmt> exeStack,MyIDictionary<String,Integer> symTable,MyIList<Integer> out,
            MyIMap<MyITuple<String,BufferedReader>> fileTable,MyIMap<Integer> heap,IStmt program)
    {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = program;
        //originalProgram = deepCopy(program);
        exeStack.push(program);
    }

    public MyIStack<IStmt> getExeStack()
    {
        return exeStack;
    }

    public MyIDictionary<String,Integer> getSymTable()
    {
        return symTable;
    }

    public MyIList<Integer> getOut()
    {
        return out;
    }

    public MyIMap<MyITuple<String,BufferedReader>> getFileTable()
    {
        return fileTable;
    }

    public MyIMap<Integer> getHeap()
    {
        return heap;
    }

    public IStmt getOriginalProgram()
    {
        return originalProgram;
    }

    public String toString()
    {
        return "\tProgram State\nExecution Stack\n" + exeStack.toString() +
                "\n\nTable of Symbols: " + symTable.toString() +
                "\n\nOutput: " + out.toString() +
                "\n\nFile Table: " + fileTable.toString() +
                "\n\nHeap: " + heap.toString() + "\n\n\n\n";
    }
}
