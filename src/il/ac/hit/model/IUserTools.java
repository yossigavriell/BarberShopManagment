package il.ac.hit.model;

import il.ac.hit.exceptions.AddException;
import org.jetbrains.annotations.NotNull;

/**
 * interface for user tools.
 */
public interface IUserTools {
    /**
     * Add new user to the system using user table in database
     * @param userName user name to add, Can't be null
     * @param password password to add, Can't be null
     * @return true if add success, false if not
     * @throws AddException specific exception to for new value to database tables
     */
    public boolean addUserName(@NotNull String userName,@NotNull String password) throws AddException;

    /**
     * Check if user exist in user table in database
     * @param userName user name to check, Can't be null
     * @param password password to check, Can't be null
     * @return true is exist, false if not
     */
    public boolean checkIfUserExist(@NotNull String userName,@NotNull String password);

    /**
     * Set the model (class that implements IModel interface)
     * @param m class that implements IModel interface, Can't be null
     */
    public void setModel(@NotNull IModel m);
}

