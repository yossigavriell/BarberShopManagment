package il.ac.hit.view;
/*
NO TEST NEEDED BECAUSE THE METHODS ARE ALL ABOUT SHOW AND EDIT THE FORM
 */
import il.ac.hit.exceptions.DeleteException;
import il.ac.hit.viewmodel.Management;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Logger;

public class TableForm extends JFrame implements ActionListener, IView {

/**
This class displays a table of all appointments to the barber set on a specific date selected. The user can also mark an appointment and delete it.
This class includes methods such as:
* Show/hide form - show/hide the appointment form to/from the main screen.
* Set location of the components.
* Add action listeners and action events.
* Delete an appointment or go back to calendar form.
 */

    static Logger logger=Logger.getLogger("TableForm");
    String date;
    ResultSet rs;
    JTable table = new JTable();
    JPanel northPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel centerPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel southPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton backButton =new JButton("Back");
    JButton deleteButton =new JButton("Delete");
    JFrame frame =new JFrame();
    JLabel dateLabel =new JLabel("");
    JScrollPane pane = new JScrollPane(table);

    /**
     * Constructor - create table form
     * @param date getting the picked date, Can't be null
     */
    public TableForm(@NotNull String date) {
        setDate(date);
        setLayoutManager();
        editPanel();
        addActionEvent();
        backButton.setBackground(new Color(153,204,255));
        deleteButton.setBackground(new Color(153,204,255));
        northPanel.setBackground(new Color(204,229,255));
        centerPanel.setBackground(new Color(204,229,255));
        southPanel.setBackground(new Color(204,229,255));
        frame.add(northPanel,BorderLayout.NORTH);
        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(southPanel,BorderLayout.SOUTH);
        frame.pack();
    }

    /**
     * Set layout
     */
    public void setLayoutManager()
    {
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(204,229,255));
    }

    /**
     * Edit panel
     */
    public void editPanel()
    {
        dateLabel.setFont(new Font("David", Font.ROMAN_BASELINE, 20));
        northPanel.add(dateLabel);

        table.setFillsViewportHeight(true);

        centerPanel.add(new JScrollPane(table));

        southPanel.add(deleteButton);
        southPanel.add(backButton);
    }

    /**
     * Show the form
     * @throws Exception general exception
     */
    @Override
    public void showForm() throws Exception
    {
        dateLabel.setText(date);
        showApp(rs);
        editPanel();
        frame.setTitle("Table Form");
        frame.setVisible(true);
        frame.setSize(500,600);
        frame.setResizable(false);
        logger.info("Show Table Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Hide the form
     */
    @Override
    public void visibleOffForm()
    {
        frame.setVisible(false);
    }

    /**
     * Add action listeners
     */
    public void addActionEvent()
    {
        deleteButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * Action Performed
     * @param e - the event that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == deleteButton) {
            int index= table.getSelectedRow();
            if(index == -1 )// No selection
            {
                JOptionPane.showMessageDialog(this, "No Appointment selected!");
            }else {
                String date = (String) table.getValueAt(index, 0);
                String hour = (String) table.getValueAt(index, 1);
                try {
                    Management.getManage().removeAppointment(date, hour);
                } catch (DeleteException ex) {
                    ex.printStackTrace();
                }
                visibleOffForm();
                Management.getManage().showCalendarForm();
                logger.info("Delete from table was success");
            }
        }
        if(e.getSource()== backButton)
        {
            northPanel.removeAll();
            visibleOffForm();
            Management.getManage().showCalendarForm();
            logger.info("Back to calendar");
        }
    }

    /**
     * Build new table model with the result set of the specified date.
     * @param rs result set from data base, Can't be null
     * @return updated table model
     * @throws SQLException - syntactic query
     */
    public DefaultTableModel buildTableModel(@NotNull ResultSet rs) throws SQLException
    {

        DefaultTableModel defaultTableModel=new DefaultTableModel(){

        @Override
        public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }
    };
        ResultSetMetaData metaData = rs.getMetaData();

        //Name of columns:
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column < columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
            defaultTableModel.addColumn(metaData.getColumnName(column));
        }

        //Data of the table:
        while(rs.next()){
            Object[] objects = new Object[columnCount];
            for(int i=0;i<columnCount;i++){
                objects[i]=rs.getObject(i+1);
            }
            defaultTableModel.addRow(objects);
        }
        logger.info("Building new model to show");
        return defaultTableModel;
    }

    /**
     * Show appointment
     * @param rs result set from data base, Can't be null
     * @throws Exception general exception
     */
    public void showApp(@NotNull ResultSet rs) throws Exception {

        table.setModel(buildTableModel(rs));
        logger.info("Updating table");

    }

    /**
     * Setting all the appointments that found in the specified date.
     * @param getAppointments get result set of the asked appointments, Can't be null
     */
    public void setRs(@NotNull ResultSet getAppointments)
    {
        this.rs = getAppointments;
    }

    /**
     * Set the date
     * @param date getting updated date, Can't be null
     */
    public void setDate(@NotNull String date) {
        this.date = date;
    }
}

