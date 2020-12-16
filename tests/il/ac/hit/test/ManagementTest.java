package il.ac.hit.test;

import static org.junit.Assert.*;
/*
because we only have the try&catch text on Management class we run tests that check the visibility of the forms.
 */
import il.ac.hit.model.AppointmentTools;
import il.ac.hit.view.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManagementTest {
    LoginForm loginForm;
    TableForm tableForm;
    CalendarForm calendarForm;
    MakeAppointmentForm makeAppointmentForm;
    SignUpForm signUp;
    AppointmentTools appointmentTools;

    @Before
    public void setUp() throws Exception {
        loginForm = new LoginForm();
        tableForm= new TableForm("23-03-20");
        calendarForm= new CalendarForm();
        makeAppointmentForm =new MakeAppointmentForm("21-03-20");
        signUp=new SignUpForm();

    }

    @After
    public void tearDown() throws Exception {

        loginForm.dispose();
        tableForm.dispose();
        calendarForm.dispose();
        makeAppointmentForm.dispose();
        signUp.dispose();

    }

    @Test
    public void showMakeApp() {
        makeAppointmentForm.showForm();
    }

    @Test
    public void showSignUpForm() {
        signUp.showForm();

    }

    @Test
    public void showCalendarForm() {
        calendarForm.showForm();
    }

    @Test
    public void showLoginForm() {
        loginForm.showForm();
    }

    @Test
    public void showTableForm() {
        try {
            tableForm.setRs(appointmentTools.getAppointments(null));
            tableForm.setDate(null);
            tableForm.showForm();
            fail("Exception not thrown");
        } catch (Exception e) {
            assertNull("Operation Not Supported", e.getMessage());
        }
    }
}
