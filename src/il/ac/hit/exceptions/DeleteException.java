package il.ac.hit.exceptions;

public class DeleteException extends Exception {

    public DeleteException(){
        super();
    }

    public DeleteException (String msg,Throwable d)
    {
        super(msg,d);
    }

    public DeleteException (String msg)
    {
        super(msg);
    }
}
