package il.ac.hit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class DataBaseConnection {

/**
* This class is the connection to data base with the help of drive and protocol. .
* This class include methods such as:
* Getters statements and connection.
*/

    private static DataBaseConnection s_Instance;
    private static Object s_Lock = new Object();
    static Logger logger=Logger.getLogger(String.valueOf(DataBaseConnection.class));

    private final String drive = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String protocol = "jdbc:derby:BarberShopDB;create=true";
    private Connection connection = null;
    private Statement statement = null;

    /**
     *  Constructor - connect to the database using driver and protocol
     */
    private DataBaseConnection()  {
        try {
            Class.forName(drive);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
            logger.info("Connection to DB server was success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("Connection to DB failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Get instance of DataBaseCreator implements singleton
     * @return s_Instance
     */
    public static DataBaseConnection getInstance()
    {
        if (s_Instance==null)
        {
            synchronized (s_Lock)
            {
                if (s_Instance == null)
                {
                    s_Instance = new DataBaseConnection();
                }
            }
        }
        return s_Instance;
    }

    /**
     * Get Statement
     * @return statement variable
     */
    public Statement getStatement()
    {
        return statement;
    }

    /**
     * Get Connection
     * @return connection variable
     */
    public Connection getConnection()
    {
        return connection;
    }
}
