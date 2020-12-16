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

public class CalendarForm extends JFrame implements IView {

    /**
    This class displays a calendar where the user can switch between months and select a specific date. When selected, three options appear: create a meeting, view appointments on that date, cancel.
    This class includes methods such as:
    * Show/hide form - show/hide the calendar form to/from the main screen.
    * Add action listeners and action events.
    * button array to pick specific date.
    * Set picked date.
     */

        static Logger logger= Logger.getLogger(String.valueOf(CalendarForm.class));
        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
        JLabel l = new JLabel("", JLabel.CENTER);
        private String day =new String();
        JFrame frame;
        Object[] options = {"Make appointment", "Show all appointments","Cancel"};
        JButton[] button = new JButton[49];

    /**
     * Constructor
     * Create the frame and the calendar
     */
        public CalendarForm() {
            frame = new JFrame();
            String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
            JPanel p1 = new JPanel(new GridLayout(7, 7));
            p1.setBackground(new Color(204,229,255));
            p1.setPreferredSize(new Dimension(430, 120));
            logger.info("Building calendar...");
            for (int x = 0; x < button.length; x++) {
                final int selection = x;
                button[x] = new JButton();
                //add event to button
                button[x].setFocusPainted(false);
                button[x].setBackground(new Color(204,229,255));
                if (x > 6)
                    button[x].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            day = button[selection].getActionCommand();
                            int choice = JOptionPane.showOptionDialog(null, "                                             Date Selected : "+setPickedDate(), "Choice Message",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,options[2]);
                            if (choice == 0 )//Make Appointment
                            {
                                visibleOffForm();
                                Management.getManage().showMakeApp(setPickedDate());
                                logger.info("Make appointment");
                            }
                            else if(choice==1)//Show all appointments
                            {
                                visibleOffForm();
                                Management.getManage().showTableForm(setPickedDate());
                                logger.info("Preparing show all appointments...");
                            }
                            else//cancel
                            {
                                visibleOffForm();
                                Management.getManage().showCalendarForm();

                            }
                        }
                    });
                if (x < 7) {
                    button[x].setText(header[x]);
                    button[x].setForeground(new Color(0,128,255));
                }
                p1.add(button[x]);
            }
            JPanel p2 = new JPanel(new GridLayout(1, 3));
            p2.setBackground(new Color(204,229,255));
            JButton previous = new JButton("<< Previous");
            previous.setBackground(new Color (153,204,255));
            previous.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    month--;
                    displayDate();
                }
            });
            p2.add(previous);
            p2.add(l);
            JButton next = new JButton("Next >>");
            next.setBackground(new Color (153,204,255));
            next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    month++;
                    displayDate();
                }
            });
            p2.add(next);
            frame.add(p1, BorderLayout.CENTER);
            frame.add(p2, BorderLayout.SOUTH);
            frame.pack();
            displayDate();

        }

    /**
     * Show the form
     */
    @Override
        public void showForm()
        {
            frame.setTitle("Calendar Form");
            frame.setVisible(true);
            frame.setSize(450,600);
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
     * Get the array of button
     * @return array button
     */
    public JButton[] getButton() {
            return button;
        }

    /**
     * Display the date
     */
    public void displayDate()
        {
            for (int x = 7; x < button.length; x++)
                button[x].setText("");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "MMMM yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month, 1);
            int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
            int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
            for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
                button[x].setText("" + day);
            l.setText(sdf.format(cal.getTime()));
        }

    /**
     * Set the date
     * @return - time
     */
    public String setPickedDate() {
            if (day.equals(""))
                return day;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "dd-MM-yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month, Integer.parseInt(day));
            logger.info("Date was picked");
            return sdf.format(cal.getTime());
        }
}

