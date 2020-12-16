package il.ac.hit.view;
/*
NO TEST NEEDED BECAUSE THE METHODS ARE ALL ABOUT SHOW AND EDIT THE FORM
 */
import il.ac.hit.viewmodel.Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class LoginForm extends JFrame implements ActionListener, IView{

/**
This class creates a login form that allows the customer to identify with the system and log into the app.
This class includes methods such as:
* Show/hide form - show/hide the login form to/from the main screen.
* Set location of the components.
* Add action listeners and action events.
 */

    Container frame=getContentPane();
    JLabel titleLabel = new JLabel("BarberShop Appointment Manager");
    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JButton signUpButton = new JButton(("SignUp"));
    JCheckBox showPassword = new JCheckBox("Show Password");
    static Logger logger= Logger.getLogger(String.valueOf(LoginForm.class));

    /**
     * Constructor Create the login form
     */
    public LoginForm()
    {

        showForm();
        setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        showPassword.setBackground(new Color(204,229,255));
        loginButton.setBackground(new Color(153,204,255));
        signUpButton.setBackground(new Color(153,204,255));
        resetButton.setBackground(new Color(153,204,255));
    }

    /**
     * Show the login form
     */
    @Override
    public void showForm()
    {

        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.info("Show login form");
    }

    /**
     * Hide the login form
     */
    @Override
    public void visibleOffForm()
    {
        setVisible(false);
        userTextField.setText("");
        passwordField.setText("");
    }

    /**
     * Set layout
     */
    public void setLayoutManager()
    {

        frame.setLayout(null);
        frame.setBackground(new Color(204,229,255));
    }

    /**
     * Set Location and size in frame
     */
    public void setLocationAndSize() {
        titleLabel.setBounds(50,50,300,50);
        titleLabel.setFont(new Font("David", Font.ROMAN_BASELINE, 20));
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        signUpButton.setBounds(125,350,100,30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    /**
     * Add components
     */
    public void addComponentsToContainer() {
        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(showPassword);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(signUpButton);
    }

    /**
     * Add action listeners
     */
    public void addActionEvent()
    {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        signUpButton.addActionListener(this);
    }

    /**
     *
     * @param e the event that occurred
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());
            if (Management.getManage().userTools.checkIfUserExist(userText,pwdText))//Validation test (User existence)
            {
                JOptionPane.showMessageDialog(this, "Welcome " +userText+" Login Successful");
                visibleOffForm();
               Management.getManage().showCalendarForm();
                logger.info("Login successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                logger.info("Login failed - invalid Username or Password");
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) //Reviling the password
        {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
                logger.warning("Showing the password");
            } else {
                passwordField.setEchoChar('*');
            }
        }
        if(e.getSource()== signUpButton)
        {
            logger.info("preparing to registration");
            visibleOffForm();
            Management.getManage().showSignUpForm();
        }
    }
}

