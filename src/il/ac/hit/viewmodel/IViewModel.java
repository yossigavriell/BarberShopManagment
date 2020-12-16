package il.ac.hit.viewmodel;

import il.ac.hit.model.IAppointmentTools;
import il.ac.hit.model.IModel;
import il.ac.hit.model.IUserTools;

/**
 * interface for View Model that extends IUserTools and IAppointmentTools interfaces.
 * This interface also manage the forms change using the "showForm" method from IView interface.
 */
public interface IViewModel extends IUserTools, IAppointmentTools {
    /**
     * Set the model (class that implements IModel interface)
     * @param m class that implements IModel interface
     */
    public void setModel(IModel m);
}
