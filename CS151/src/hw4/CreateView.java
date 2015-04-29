package hw4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;

public class CreateView extends JFrame
{
    private JPanel btm;
    private JTextField name, date, start, end;
    private JLabel to;
    private JButton save;

    public CreateView(String day)
    {
        System.out.println("CreateView");

        name = new JTextField();
        date = new JTextField(day);
        start = new JTextField("9:00am");
        to = new JLabel(" to ");
        end = new JTextField("11:30am");
        save = new JButton("SAVE");

        btm = new JPanel(new FlowLayout());

        btm.add(date);
        btm.add(start);
        btm.add(to);
        btm.add(end);
        btm.add(save);
        
        name.setText("Untitled Event");

        this.setLayout(new BorderLayout());
        this.add(name, BorderLayout.NORTH);
        this.add(btm, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    void addSaveListener(ActionListener l) { save.addActionListener(l); }

    public String getName()
    {
        return name.getText();
    }

    public String getDate()
    {
        return date.getText();
    }

    public String getStart()
    {
        return start.getText();
    }

    public String getEnd()
    {
        return end.getText();
    }

    /**
     * Displays a popup when user's event has time conflict
     */
    public void fail()
    {
        System.out.println("CreateView-fail");
        JOptionPane.showMessageDialog(
                this,
                "Event time conflict: overlapping events detected",
                "Time Conflict", JOptionPane.ERROR_MESSAGE);
    }
}
