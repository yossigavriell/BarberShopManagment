package il.ac.hit.view;
/*
NO TEST NEEDED BECAUSE THE METHODS ARE ALL ABOUT SHOW AND EDIT THE FORM
 */
import il.ac.hit.exceptions.AddException;
import il.ac.hit.viewmodel.Management;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class MakeAppointmentForm extends  JFrame implements ActionListener, IView {

/**
This class displays the form of adding a new appointment to the barber where you can set a date, time, customer name, hairstyle type and phone number.
This class includes methods such as:
* Show/hide form - show/hide the appointment form to/from the main screen.
* Set location of the components.
* Add action listeners and action events.
* Save button that insert the appointment to the table.
 */
    static Logger logger= Logger.getLogger(String.valueOf(MakeAppointmentForm.class));
    Container frame = getContentPane();
    JTextField tfClientName = new JTextField(10);
    JTextField tfClientTel = new JTextField(10);
    JTextField tfClientType = new JTextField(10);
    JButton btSave = new JButton("Save");
    JButton btBack = new JButton("Back");
    JLabel clientNameLabel = new JLabel("Name: ");
    JLabel clientTelLabel = new JLabel("Tel: ");
    JLabel clientTypeLabel = new JLabel("Type: ");
    JLabel hourLabel = new JLabel("Hour: ");
    JLabel dateLabel;
    String[] m_Hours = {"08:00","08:30","09:00","09:30","10:00",
                        "10:30","11:00","11:30","12:00","12:30",
                        "13:00","13:30","14:00","14:30","15:00",
                        "15:30","16:00","16:30","17:00","17:30",
                        "18:00","18:30","19:00","19:30","20:00"};

    JComboBox hourComboBox = new JComboBox(m_Hours);
    String date;
    Appointment appointment =new Appointment("","","","","");

    /**
     * Constructor - Create appointment form
     * @param date - getting the picked date
     */
    public MakeAppointmentForm(@NotNull String date)
    {
        dateLabel = new JLabel(date);
        appointment.setDate(date);
        dateLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        hourComboBox.setBackground(new Color(153,204,255));
        btSave.setBackground(new Color(153,204,255));
        btBack.setBackground(new Color(153,204,255));
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Show the form
     */
    @Override
    public void showForm()
    {
        appointment.setDate(date);
        dateLabel.setText(appointment.getDate());
        setTitle("MakeAppointment");
        setSize(400, 600);
        setVisible(true);
        setResizable(false);
        logger.info("Show make appointment form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Hide the form
     */
    @Override
    public void visibleOffForm()
    {
        setVisible(false);
        tfClientName.setText("");
        tfClientTel.setText("");
        tfClientType.setText("");
    }
    /**
     * Set layout
     */

    private void setLayoutManager() {
        frame.setLayout(null);
        frame.setBackground(new Color(204,229,255));
    }

    /**
     * Set location and size in frame
     */
    private void setLocationAndSize() {
        dateLabel.setBounds(150, 50, 100, 70);
        clientNameLabel.setBounds(50, 150, 100, 30);
        clientTypeLabel.setBounds(50, 200, 100, 30);
        clientTelLabel.setBounds(50, 250, 100, 30);
        hourLabel.setBounds(50, 300, 100, 30);
        tfClientName.setBounds(150, 150, 150, 30);
        tfClientType.setBounds(150, 200, 150, 30);
        tfClientTel.setBounds(150, 250, 150, 30);
        hourComboBox.setBounds(150,300,100,30);
        btSave.setBounds(50, 430, 100, 30);
        btBack.setBounds(200, 430, 100, 30);
    }

    /**
     * Add components
     */
    private void addComponentsToContainer() {
        frame.add(dateLabel);
        frame.add(clientNameLabel);
        frame.add(clientTypeLabel);
        frame.add(clientTelLabel);
        frame.add(hourLabel);
        frame.add(hourComboBox);
        frame.add(tfClientName);
        frame.add(tfClientType);
        frame.add(tfClientTel);
        frame.add(btSave);
        frame.add(btBack);
    }

    /**
     * Add action listeners
     */
    private void addActionEvent()
    {
        btSave.addActionListener(this);
        btBack.addActionListener(this);
    }
    /**
     *  Action performed in event
     * @param e - the event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btSave) {
            addAppointment();
            try {
                if(Management.getManage().addNewAppointment(appointment))//if return false - the date and hour that was selected is occupied.
                {
                    logger.info("Appointment added");
                    JOptionPane.showMessageDialog(this, "Saved!");
                }
                else JOptionPane.showMessageDialog(this, "Hour is occupied");
            } catch (AddException ex) {
                ex.printStackTrace();
            }
            logger.info("Hour is occupied");

        }
         if (e.getSource() == btBack) {
            visibleOffForm();
            Management.getManage().showCalendarForm();
        }
    }

    /**
     * Set the appointment details
     */
    public void addAppointment()
    {
        appointment.setHour((String)hourComboBox.getItemAt(hourComboBox.getSelectedIndex()));
        appointment.setCustomerName(tfClientName.getText());
        appointment.setTypeHairCut(tfClientType.getText());
        appointment.setCustomerTel(tfClientTel.getText());
        logger.info("Setting the new appointment");
    }

    /**
     * Set the date
     * @param date updated date, Can't be null
     */
    public void setDate(String date) {
        this.date = date;
    }
}

