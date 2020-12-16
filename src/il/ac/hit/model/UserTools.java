package il.ac.hit.model;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DataBaseException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserTools implements IUserTools{

/**
This class makes contact with the Data Base with regards to the user, receiving, removing and adding users to the Data Base
This class include methods such as:
* addUserName-add user to database.
* createTableIfNotExist-this method check if the user name in data base is exist.
*/
    private IModel dataBase;
    private final String nameOfTable = "User1";
    static Logger logger=Logger.getLogger(String.valueOf(UserTools.class));

    /**
     * Constructor create some user tool to use it in data base
     */
    public UserTools()
    {
        try {
            this.dataBase = DataBaseModel.getInstance();
            dataBase.createTableIfNotExist(
                    nameOfTable,
                    "UserName",
                    "varchar(300)",
                    "Password",
                    "varchar(300)");
            logger.info("Creating Table");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add user to data base.
     * @param userName username to add query, Can't be null
     * @param password password to add query, Can't be null
     * @return true if success, false if not
     * @throws AddException specific exception to for new value to database tables
     */
    @Override
    public boolean addUserName(@NotNull String userName,@NotNull String password) throws AddException {
        String parameters =
                "(" +"'"+userName+"'" +
                        "," +"'"+password+"')";

        dataBase.addValue(nameOfTable, parameters);
        logger.info("Add new user");
        return true;
    }

    /**
     * Check if the user exist.
     * @param userName username to check, Can't be null
     * @param password password to check, Can't be null
     * @return true if exist, false if not
     */
    @Override
    public boolean checkIfUserExist(@NotNull String userName,@NotNull String password)
    {
        boolean valToReturn = false;
        try {
        ResultSet rs = dataBase.query("select * from "+ nameOfTable +" where "+ nameOfTable +".USERNAME ='"+userName+"' and "+
                nameOfTable +".PASSWORD ='"+password+"'");
            if(rs.next())
            {
                logger.info("User is exist");
                return valToReturn = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Failed finding user existence");
            throw new DataBaseException("Create failed");
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
    public void setModel(@NotNull IModel m){
        this.dataBase = m;
    }
}
