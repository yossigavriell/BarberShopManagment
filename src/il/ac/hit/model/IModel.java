package il.ac.hit.model;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DeleteException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

/**
 * Interface for IModel
 */
public interface IModel {
    /**
     * Create new table in database if not exist (table)
     * @param nameOfTable name of the table to create, Can't be null
     * @param parameters parameters that include the columns names and size of every argument, Can't be null
     * @throws Exception General exception
     */
    public void createTableIfNotExist(String nameOfTable, String... parameters) throws Exception;

    /**
     * Add a value to specific table in database
     * @param nameOfTable name of the table, Can't be null
     * @param parameters parameters by order to insert to the table, Can't be null
     * @return true if success, false if not
     * @throws AddException specific exception for add new value to database tables
     */
    public boolean addValue(@NotNull String nameOfTable,@NotNull String parameters) throws AddException;

    /**
     *
     * @param nameOfTable delete a value from a specific table in database, Can't be null
     * @param date date to delete, Can't be null
     * @param hour hour to delete, Can't be null
     * @return true id success, false if not
     * @throws DeleteException specific exception for delete value from database tables
     */
    public boolean deleteValue(@NotNull String nameOfTable,@NotNull String date,@NotNull String hour) throws DeleteException;

    /**
     * Build and execute SQL query to the database
     * @param query string query to execute, Can't be null
     * @return result set came from the database that contain the information that requested
     */
    public ResultSet query(@NotNull String query);

    /**
     * Delete table from database
     * @param nameOfTable name of the table to delete, Can't be null
     * @throws DeleteException specific exception for delete value to database tables
     */
    public void deleteTable(@NotNull String nameOfTable) throws DeleteException;


}
