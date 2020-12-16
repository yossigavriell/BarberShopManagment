package il.ac.hit.exceptions;

/**
 *
 */
public class AddException extends Exception {

    /**
     *
     */
    public AddException(){
        super();
    }

    public AddException(String msg,Throwable d)
    {
        super(msg,d);
    }

    public AddException(String msg)
    {
        super(msg);
    }
}
