package il.ac.hit.view;
/*
NO TEST NEEDED BECAUSE THE METHODS ARE ALL ABOUT SHOW AND EDIT THE FORM
 */
import il.ac.hit.exceptions.AddException;
import il.ac.hit.viewmodel.Management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.logging.Logger;

public class SignUpForm extends JFrame implements ActionListener, IView {

/**
This class creates a registration form where the customer is given the option to add new users to the system using a username and password.
This class includes methods such as:
* Show/hide form - show/hide the registration form to/from the main screen.
* Set location of the components.
* Add action listeners and action events.
 */
    static Logger logger=Logger.getLogger(String.valueOf(SignUpForm.class));

    Container frame = getContentPane();
    JTextField tfUsername = new JTextField(10);
    JPasswordField tfPassword = new JPasswordField(10);
    JPasswordField tfRePassword = new JPasswordField(10);
    JButton btRegister = new JButton("Register");
    JButton btCancel = new JButton("Cancel");
    JLabel titleLabel = new JLabel("Registration");
    JLabel userLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JLabel rePasswordLabel = new JLabel("Re-Password: ");

    /**
     * Constructor- create sign up form
     */
    public SignUpForm()
    {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        logger.info("in sign process");
    }

    /**
     * Show the form
     */
    @Override
    public void showForm()
    {
        setTitle("SignUp");
        setSize(400,600);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.info("Show sign up form");
    }

    /**
     * Hide the form
     */
    @Override
    public void visibleOffForm()
    {
        setVisible(false);
        tfPassword.setText("");
        tfRePassword.setText("");
        tfUsername.setText("");
    }
    /**
     * Set layout
     */
    private void setLayoutManager()
    {
        frame.setLayout(null);
        frame.setBackground(new Color(204,229,255));
    }

    private void setLocationAndSize()  //Set Location and size in frame
    {
        titleLabel.setBounds(150,50,150,30);
        titleLabel.setFont(new Font("David", Font.ROMAN_BASELINE, 20));
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        rePasswordLabel.setBounds(50, 270, 100, 30);
        tfUsername.setBounds(150, 150, 150, 30);
        tfPassword.setBounds(150, 220, 150, 30);
        tfRePassword.setBounds(150, 270, 150, 30);
        btRegister.setBounds(50, 350, 100, 30);
        btCancel.setBounds(200, 350, 100, 30);
    }

    /**
     * Add Components
     */
    private void addComponentsToContainer()
    {
        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(rePasswordLabel);
        frame.add(tfUsername);
        frame.add(tfPassword);
        frame.add(tfRePassword);
        frame.add(btRegister);
        frame.add(btCancel);
    }

    /**
     * Add action listeners
     */
    private void addActionEvent()
    {
        btRegister.addActionListener(this);
        btCancel.addActionListener(this);
    }
    /**
     * Action performed
     * @param e - the event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btRegister){
            String pass, rePass,userText;
            pass = tfPassword.getText();
            rePass = tfRePassword.getText();
            userText = tfUsername.getText();
            if((pass.equals(rePass)) && (!userText.equals("")) && (!pass.equals(""))) {
                try {
                    if(Management.getManage().addUserName(userText,pass))//If return false - the User is already in the User table (database).
                    {
                        JOptionPane.showMessageDialog(this, "Sign up Successfully");
                        visibleOffForm();
                        Management.getManage().showLoginForm();
                        logger.info("Sign up success");
                    }else {
                        JOptionPane.showMessageDialog(this, "User is already registered");
                        logger.info("Sign up failed - User is already registered");
                    }
                } catch (AddException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Sign up failed - no matching information");
                logger.info("Sign up failed - no matching information");
            }
        }
        else if(e.getSource()==btCancel){
            visibleOffForm();
            Management.getManage().showLoginForm();
            logger.info("Cancel from sign up");
        }
    }
}