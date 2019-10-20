package model.adt;

import java.util.ArrayList;

public class MyList<E> implements MyIList<E>
{
    private ArrayList<E> list;

    public MyList()
    {
        list = new ArrayList<>();
    }

    public void add(E e)
    {
        list.add(0,e);
    }

    @Override
    public String toString()
    {
        String s = "[ ";
        for (E e : list)
        {
            s += e + " ";
        }
        s += "]";
        return s;
    }
}
