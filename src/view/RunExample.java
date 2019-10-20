package view;

import controller.Controller;
import exception.modelexception.*;
import exception.repositoryexception.LogFileException;

import java.io.IOException;

public class RunExample extends Command
{
    private Controller ctr;

    public RunExample(String key,String description,Controller ctr)
    {
        super(key,description);
        this.ctr = ctr;
    }

    @Override
    public void execute()
    {
        try
        {
            ctr.allSteps();
        }
        catch (VariableNotDefinedException e)
        {
            System.out.println("VariableNotDefined");
        }
        catch (DivisionByZeroException e)
        {
            System.out.println("Division by 0");
        }
        catch (InvalidOperatorException e)
        {
            System.out.println("InvalidOperator");
        }
        catch (FileAlreadyOpenedException e)
        {
            System.out.println("FileAlreadyOpened");
        }
        catch (FileDescriptorException e)
        {
            System.out.println("File descriptor not found");
        }
        catch (OpenReadFileException e)
        {
            System.out.println("File can not be opened for reading");
        }
        catch (IOException e)
        {
            System.out.println("I/O error");
        }
        catch (LogFileException e)
        {
            System.out.println("Log file could not be opened");
        }
        catch (HeapAllocationException e)
        {
            System.out.println("Heap location is not allocated");
        }
    }
}
