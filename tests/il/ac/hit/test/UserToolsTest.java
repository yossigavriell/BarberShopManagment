package il.ac.hit.test;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.model.DataBaseModel;
import il.ac.hit.model.UserTools;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserToolsTest {

    UserTools user;

    @Before
    public void setUp() throws Exception {
        user=new UserTools();
        DataBaseModel.getInstance().createTableIfNotExist("UserTestTable","UserNameTest", "varchar(200)","PasswordTest","varchar(200)");
    }

    @After
    public void tearDown() throws Exception {
        DataBaseModel.getInstance().deleteTable("UserTestTable");
    }

    @Test
    public void addUserName() throws AddException {
        assertEquals(true,user.addUserName("Yoyo","123"));
    }
}