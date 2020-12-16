package il.ac.hit.test;

import il.ac.hit.exceptions.AddException;
import il.ac.hit.model.DataBaseModel;
import il.ac.hit.exceptions.DeleteException;
import il.ac.hit.model.IModel;
import il.ac.hit.view.Appointment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class AppointmentToolsTest {

    ResultSet rs;
    IModel model = DataBaseModel.getInstance();
    Appointment appointment = new Appointment("21-03-20","08:00","Yossi","Normal","0531324323");
    String nameOfTable ="AppointmentTestTable";

    @Before
    public void setUp() throws Exception {
        model.createTableIfNotExist(nameOfTable, "Date",
                "varchar(300)",
                "Hour1",
                "varchar(300)",
                "CustomerName",
                "varchar(300)",
                "Type",
                "varchar(300)",
                "Telephone",
                "varchar(300)");
    }

    @After
    public void tearDown() throws Exception {
        model.deleteTable(nameOfTable);
    }

    @Test
    public void addNewAppointment() throws AddException {

        assertTrue(model.addValue(nameOfTable,"(" +"'"+appointment.getDate()+"'" +
                "," +"'"+appointment.getHour()+"'" +
                "," +"'"+appointment.getCustomerName()+"'" +
                "," +"'"+appointment.getTypeHairCut()+"'" +
                "," +"'"+appointment.getCustomerTel()+"')"));
    }

    @Test
    public void getAppointments() {
        assertNotNull(model.query("SELECT * FROM "+ nameOfTable +" WHERE " + nameOfTable + ".Date ='" + appointment.getDate() + "'" + "ORDER BY hour1"));
    }


    @Test
    public void removeAppointment() throws DeleteException {
        assertEquals(true,model.deleteValue(nameOfTable, appointment.getDate(), appointment.getHour()));
    }
}