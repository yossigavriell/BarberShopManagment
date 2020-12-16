package il.ac.hit.view;

/**
 * This interface handle for all the view
 */
public interface IView {

    /**
     * show the form to the client
     * @throws Exception General exception
     */
    public void showForm() throws Exception;

    /**
     * Hide the form from the client
     */
    public void visibleOffForm();
}
