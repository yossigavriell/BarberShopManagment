package il.ac.hit.test;
import static org.junit.Assert.*;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.exceptions.DeleteException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import il.ac.hit.model.DataBaseModel;

public class DataBaseModelTest {
    private DataBaseModel model;
    private String nameOfTable = "TestTable";

    @Before
    public void setUp() throws Exception {
        model= DataBaseModel.getInstance();
        model.createTableIfNotExist(nameOfTable,"Date", "varchar(200)","Hour1","varchar(200)");
    }

    @After
    public void tearDown() throws Exception {
        model.deleteTable(nameOfTable);
    }

    @Test
    public void insertValue() throws AddException {
        assertEquals(true,(model.addValue(nameOfTable,("(" +"'29-03-2020'" + "," +"'08:00')"))));
    }

    @Test
    public void deleteValue() throws DeleteException {
        assertEquals(true, model.deleteValue(nameOfTable, "29-03-2020", "08:00"));
    }

}