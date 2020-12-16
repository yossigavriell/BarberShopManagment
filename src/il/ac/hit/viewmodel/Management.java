package il.ac.hit.viewmodel;

import il.ac.hit.model.*;
import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DeleteException;
import il.ac.hit.view.*;

import javax.swing.*;
import java.util.logging.Logger;

public class Management implements IViewModel {

    /**
    This class manages the program (VM), selects the order of forms and links them to the model (DataBaseModel) and classes that assist it.
    Through this class we can communicate with almost all the existing classes in the program.
    This class includes methods such as:
    * Show/hide all forms with its needs.
    * check if user exist
    * Add new user or appointment.
    * Delete appointment using Appointment tools class.
     */

    private static Object lock = new Object();
    static Logger logger= Logger.getLogger(String.valueOf(MakeAppointmentForm.class));
    private static Management s_Manage=null;
    private LoginForm loginForm;
    private CalendarForm calendarForm;
    private TableForm tableForm;
    private SignUpForm signUpForm;
    public UserTools userTools;
    public AppointmentTools appointmentTools;
    public MakeAppointmentForm makeAppointmentForm;
    public String userName;
    public IModel model;
    public IView view;

    /**
     * Constructor - Create the management between UI to Data base and inverse
     */
    public Management(){
        loginForm = new LoginForm();
        calendarForm = new CalendarForm();
        tableForm = new TableForm("");
        signUpForm =new SignUpForm();
        makeAppointmentForm =new MakeAppointmentForm("");
        userTools = new UserTools();
        appointmentTools = new AppointmentTools();
        userTools.setModel(DataBaseModel.getInstance());
        appointmentTools.setModel(DataBaseModel.getInstance());
    }

    public static Management getManage() {
        if (s_Manage == null)
        {
            synchronized (lock)
            {
                if (s_Manage == null)
                {
                    s_Manage = new Management();
                }
            }
        }

        return s_Manage;
    }

    /**
     * Show make appointment form
     * @param date getting the picked date
     */
    public void showMakeApp(String date)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                makeAppointmentForm.setDate(date);
                makeAppointmentForm.showForm();

            }
        });
    }

    /**
     * Show sign Up form
     */
    public void showSignUpForm()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                 signUpForm.showForm();
                 logger.info("Showing form..");

            }
        });
    }

    /**
     * Show calendar form
     */
    public void showCalendarForm()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                calendarForm.showForm();
                logger.info("Showing form..");

            }
        });
    }

    /**
     * Show Login Form
     */
    public void showLoginForm()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                loginForm.showForm();
                logger.info("Showing form..");
            }
        });
    }

    /**
     * Start the program
     */
    public void startProgram()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                logger.info("Starting program...");
                loginForm.showForm();


            }
        });
    }

    /**
     * Show appointments table form
     * @param date getting the picked date
     */
    public void showTableForm(String date)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {

                try {
                    tableForm.setRs(appointmentTools.getAppointments(date));
                    tableForm.setDate(date);
                    tableForm.showForm();
                    logger.info("Showing form..");
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("Error on show table form");

                }

            }
        });
    }

    /**
     *
     * @param userName username to check
     * @param password password to check
     * @return true if exist, false if not
     */
    public boolean checkIfUserExist(String userName, String password) {
        boolean valueToReturn = false;

        if (userTools.checkIfUserExist(userName, password)) //checking with user tools class that communicate with the model
        {
            valueToReturn = true;
            this.userName = userName;
            logger.info("Checking if user exist...");
        }

        return valueToReturn;
    }

    /**
     *
     * @param userName username to add
     * @param password password to add
     * @return true if success, false if not
     * @throws AddException specific exception for new value to database tables
     */
        public boolean addUserName(String userName, String password) throws AddException {

        if(!userTools.checkIfUserExist(userName,password)) //NOT user existence
        {
            if(userTools.addUserName(userName, password));
                logger.info("Registering...");
                return true;
        }
        else return false;
    }

    /**
     * Add new appointment using appointment tools class that communicate with database model
     * @param appointment appointment to add
     * @return true if success, false if not
     * @throws AddException specific exception for new value to database tables
     */
    public boolean addNewAppointment(Appointment appointment) throws AddException
    {
        if (!(appointmentTools.checkIfExist(appointment.getDate(), appointment.getHour())))
        {
            appointmentTools.addNewAppointment(appointment);
            logger.info("Appointment added");
            return true;
        }
        else {
            logger.info("Failed to add new appointment");
            return false;
        }
    }

    /**
     * Delete appointment using appointment tools class that communicate with database model
     * @param date date to the query
     * @param hour hour to the query
     * @return true if success, false if not
     * @throws DeleteException specific exception for delete value to database tables
     */
    public boolean removeAppointment(String date,String hour) throws DeleteException
    {
        logger.info("Deleting appointment...");
        appointmentTools.removeAppointment(date, hour);
        return true;
    }

    /**
     * Set the database model
     * @param m class that implements IModel interface
     */
    @Override
    public void setModel(IModel m)
    {
        this.model = m;
    }

    /**
     *Set view
     * @param v - class that implements IView interface
     */
}
