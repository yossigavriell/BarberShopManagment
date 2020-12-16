package il.ac.hit.exceptions;

public class DataBaseException extends Exception{

    public DataBaseException(){
        super();
    }

    public DataBaseException (String msg,Throwable d)
    {
        super(msg,d);
    }

    public DataBaseException (String msg)
    {
        super(msg);
    }



}
