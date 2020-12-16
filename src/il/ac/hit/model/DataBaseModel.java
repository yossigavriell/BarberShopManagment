package il.ac.hit.model;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DataBaseException;
import il.ac.hit.exceptions.DeleteException;
import org.jetbrains.annotations.NotNull;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DataBaseModel implements IModel{

/**
This class uses the data base connection class and sends SQL queries and data management.
This class use in Singleton design pattern.
This class include methods such as:
* query-send the questions to database and get response.
* addValue-insert appointment to the system.
* deleteValue-delete appointment from the system.
* createTableIfNotExist-this method check if the table in data base is exist.
 */

    private static Object lock = new Object();
    static Logger logger=Logger.getLogger(String.valueOf(DataBaseModel.class));
    private static DataBaseModel s_Instance;
    private DataBaseConnection mainDataBase = DataBaseConnection.getInstance();
    private Statement statement = DataBaseConnection.getInstance().getStatement();
    private DatabaseMetaData metaData = null;

    /**
     * Constructor - database model that implements IModel interface
     */
    private DataBaseModel() {
        try {
            metaData = mainDataBase.getConnection().getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseModel getInstance() {

        if (s_Instance==null) {
            synchronized (lock)
            {
                if (s_Instance == null)
                {
                    s_Instance = new DataBaseModel();
                }
            }
        }
        return s_Instance;
    }

    /**
     * Execute sql query.
     * @param query string edited query to database, Can't be null
     * @return result set that contains the requested information
     */
    @Override
    public ResultSet query(@NotNull String query){
        ResultSet resultSet=null;

        try
        {
            resultSet = statement.executeQuery(query);
            logger.info("Query executed successfully ");
        }
        catch (SQLException e)
        {
            logger.info("Query execute failed");
            e.printStackTrace();
        }
        return  resultSet;
    }

    /**
     * Add value with the help of sql query
     * @param nameOfTable name of the table in the database, Can't be null
     * @param parameters parameters to insert, Can't be null
     * @return true if success, false if not
     * @throws AddException specific exception for add new value to database tables
     */
    //
    @Override
    public boolean addValue(@NotNull String nameOfTable,@NotNull String parameters) throws AddException {
        boolean valToReturn=false;
        try {
            statement.executeUpdate("insert into "+nameOfTable.toUpperCase()+" values "+parameters);
            logger.info("Insert Value");
            valToReturn = true;
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("Insert value failed");
            throw new AddException("Added Failed",e);
        }
        finally {
            return valToReturn;
        }
    }

    /**
     * Delete value with the help of sql query.
     * @param nameOfTable name of the table in database, Can't be null
     * @param date date to the query, Can't be null
     * @param hour hour ti the query, Can't be null
     * @return true if success, false if not
     * @throws DeleteException specific exception for delete value from database tables
     */
    @Override
    public boolean deleteValue(@NotNull String nameOfTable,@NotNull String date,@NotNull String hour) throws DeleteException
    {
        boolean valToReturn = false;
        try {
            statement.executeUpdate("delete from "+nameOfTable+" where "+nameOfTable+".Date ='"+date+"' and "+
                    nameOfTable+".Time ='"+hour+"'");
            logger.info("Delete success");
            valToReturn = true;
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.info("Delete Failed");
            throw new DeleteException("Delete failed");
        }
        finally {

            return valToReturn;
        }
    }

    /**
     * Create new table in database
     * @param nameOfTable name of the table to create, Can't be null
     * @param parameters parameters of the table, Can't be null
     * @throws Exception General exception
     */
    @Override
    public void createTableIfNotExist(@NotNull String nameOfTable,@NotNull String... parameters) throws Exception {
        if (parameters.length % 2 != 0) {
            throw new DataBaseException
                    ("Please enter the parameters as follows!");
        }
        String str = stringsLinking(parameters);
        try {
            ResultSet rs = metaData.getTables(null, "APP", nameOfTable.toUpperCase(), null);
            if (!rs.next()) {
                statement.execute("create table " + nameOfTable + str);
                logger.info("Creating Table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Failed creating table in DB");
            throw new DataBaseException("creating Failed",e);
        }
    }

    /**
     * Linking the params in database table.
     * @param strings parameters to the query, Can't be null
     * @return StringBuffer.toString() - edited string ready to query
     */
    private String stringsLinking(@NotNull String... strings) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = strings.length - 1;
        stringBuffer.append("(");
        for (int i = 0; i < length; i += 2) {
            stringBuffer.append(strings[i]);
            stringBuffer.append(" ");
            stringBuffer.append(strings[i + 1]);
            if (i < length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    /**
     * Delete table from database
     * @param nameOfTable name of the table to delete, Can't be null
     * @throws DeleteException delete table
     */
    @Override
    public void deleteTable(@NotNull String nameOfTable) throws DeleteException {
        try {
            statement.execute("DROP TABLE "+nameOfTable);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DeleteException("Table delete problem",e);
        }
    }
}
