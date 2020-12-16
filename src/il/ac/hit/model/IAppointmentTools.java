package il.ac.hit.model;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DeleteException;
import il.ac.hit.view.Appointment;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for appointment tools
 */
public interface IAppointmentTools {
    /**
     * Add new appointment to the appointment table in database
     * @param appointment appointment to add, Can't be null
     * @return true if success, false if not
     * @throws AddException specific exception for new value to database tables
     */
    public boolean addNewAppointment(@NotNull Appointment appointment) throws AddException;

    /**
     *
     * @param date date to remove, Can't be null
     * @param hour hour to remove, Can't be null
     * @return true if success false if not
     * @throws DeleteException specific exception for delete value to database tables
     */
    public boolean removeAppointment(@NotNull String date,@NotNull String hour) throws DeleteException;

    /**
     * Set the model (class that implements IModel interface)
     * @param m class that implements IModel interface, Can't be null
     */
    public void setModel(IModel m);
}
