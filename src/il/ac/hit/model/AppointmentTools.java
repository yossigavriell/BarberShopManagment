package il.ac.hit.model;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DeleteException;
import il.ac.hit.view.Appointment;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AppointmentTools implements IAppointmentTools{

/**
This class is in contact with the Data Base when it comes to setting queues, receiving, removing and adding queues
This class include methods such as:
* getAppointment-response the answer from database.
* addNewAppointment-add the appointment to data base.
* removeAppointment-delete appointment from the data base.
* createTableIfNotExist-this method check if the appointment data base is exist.
*/
    private IModel dataBase;
    private final String nameOfTable = "Appointment";
    static Logger logger=Logger.getLogger(String.valueOf(AppointmentTools.class));

    /**
     * Constructor create some appointment tool to use it in data base
     */
    public AppointmentTools()
    {
        try {
            this.dataBase = DataBaseModel.getInstance();
            dataBase.createTableIfNotExist(
                    nameOfTable,
                    "Date",
                    "varchar(300)",
                    "Time",
                    "varchar(300)",
                    "Name",
                    "varchar(300)",
                    "Type",
                    "varchar(300)",
                    "Telephone",
                    "varchar(300)");
            logger.info("Creating Table");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Failed creating table");
        }
    }

    /**
     * Get the appointment from data base.
     * @param date date picked to the query, Can't be null
     * @return ResultSet result set from data base with the date request
     */
    public ResultSet getAppointments(@NotNull String date){
            ResultSet rs = dataBase.query("SELECT * FROM " + nameOfTable + " WHERE " + nameOfTable + ".Date ='" + date +"'"+ "ORDER BY Time");
            logger.info("Getting all appointments...");
            return rs;
    }

    /**
     * Add appointment to data base.
     * @param appointment getting the appointment to add, Can't be null
     * @return true if success, false if not
     * @throws AddException specific exception for new value to database tables
     */
    @Override
    public boolean addNewAppointment(@NotNull Appointment appointment) throws AddException {
        String parameters =
                "(" +"'"+appointment.getDate()+"'" +
                        "," +"'"+appointment.getHour()+"'" +
                        "," +"'"+appointment.getCustomerName()+"'" +
                        "," +"'"+appointment.getTypeHairCut()+"'" +
                        "," +"'"+appointment.getCustomerTel()+"')";

        dataBase.addValue(nameOfTable, parameters);
        logger.info("Adding new appointment");
        return true;
    }

    /**
     * Remove appointment from data base.
     * @param date date for the delete query, Can't be null
     * @param hour hour for the delete query, Can't be null
     * @return true if success, false if not
     * @throws DeleteException specific exception for delete value to database tables
     */
    @Override
    public boolean removeAppointment(@NotNull String date,@NotNull String hour) throws DeleteException {
        dataBase.deleteValue(nameOfTable,date,hour);
        logger.info("Deleting appointment");
        return true;
    }

    /**
     * Check if the appointment exist.
     * @param date date for the query, Can't be null
     * @param hour hour for the query, Can't be null
     * @return true if exist, false if not
     */
    public boolean checkIfExist(@NotNull String date,@NotNull String hour){
        ResultSet rs = dataBase.query("select * from "+ nameOfTable +" where "+ nameOfTable +".Date ='"+date+"' and "+
                nameOfTable +".Time ='"+hour+"'");
        boolean valToReturn = false;
        try {
            if(rs.next())
            {
                logger.info("Appointment exist");
                return valToReturn = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Failed checking if appointment is exist");
        }
        finally {
            return valToReturn;
        }
    }

    /**
     * Set model chose.
     * @param m class that implement IModel interface, Can't be null
     */
    @Override
    public void setModel(@NotNull IModel m)
    {
        this.dataBase = m;
    }
}
