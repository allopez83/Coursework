package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * All GUI and visual related aspects of SimpleCalendar
 * @author Hansen Wu
 *
 */
public class View extends JFrame
{
    private final int DAY_IN_WEEK = 7, WEEK_IN_MONTH = 6, DAY_HOURS = 24;
    private JPanel left, right, top;
    private JPanel day, dayTime, dayEvents;
    private JPanel month;
    private JButton next, prev, create, quit;
    private JLabel monthLabel, dayLabel;

    private GregorianCalendar currentDay;
    private Events events;
    private ActionListener monthAction;
    private DayViewComponent dvc;
    private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

    /**
     * Creates the primary window
     */
    public View()
    {
        System.out.println("View");

        create = new JButton("Create Event");
        prev = new JButton("<- Prev");
        next = new JButton("Next ->");
        quit = new JButton("Quit");

        monthLabel = new JLabel();
        dayLabel = new JLabel();
    }

    public void display()
    {
        System.out.println("View-display");

        topPanel();
        leftPanel();
        rightPanel();

        top.setBorder(blackBorder);
        left.setBorder(blackBorder);
        right.setBorder(blackBorder);
        month.setBorder(blackBorder);
        day.setBorder(blackBorder);
        dayTime.setBorder(blackBorder);
        dayEvents.setBorder(blackBorder);
        day.setPreferredSize(new Dimension(300, 800));

        // Main
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void topPanel()
    {
        System.out.println("View-topPanel");

        top = new JPanel();

        top.add(create);
        top.add(prev);
        top.add(next);
        top.add(quit);
    }

    /**
     * Creates left SimpleCalendar panel with month view along with create event
     * and navigation buttons
     */
    private void leftPanel()
    {
        System.out.println("View-leftPanel");

        left = new JPanel(new BorderLayout());
        month = new JPanel(new GridBagLayout());

        left.add(monthLabel, BorderLayout.NORTH);
        left.add(month, BorderLayout.CENTER);

        drawMonth(); // Initially nothing
    }

    private Integer getMonthDelay()
    {
        GregorianCalendar gc = (GregorianCalendar) currentDay.clone();
        gc.set(Calendar.DATE, 1);
        return gc.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Creates right SimpleCalendar panel with day view
     */
    private void rightPanel()
    {
        System.out.println("View-rightPanel");

        right = new JPanel(new BorderLayout());
        day = new JPanel(new BorderLayout());
        dayTime = new JPanel(new GridLayout(24 * 2, 2));
        dayEvents = new JPanel(new BorderLayout());

        right.add(dayLabel, BorderLayout.NORTH);
        right.add(day, BorderLayout.CENTER);

        day.add(dayTime, BorderLayout.WEST);
        day.add(dayEvents, BorderLayout.CENTER);

        dvc = new DayViewComponent(dayEvents, events);

        drawDayTime();
        drawDayEvents(events); // Draw the day
    }

    /**
     * Redraw the month and day view to reflect new day or view to look at
     * @param Integer
     */
    public void redraw()
    {
        System.out.println("View-redraw");

        drawDayEvents(events);
        drawMonth();
    }

    /**
     * Draw a month
     */
    public void drawMonth()
    {
        System.out.println("View-drawMonth");

        // Prevent duplicate buttons
        month.removeAll();

        int days = 1;
        int countdown = this.getMonthDelay();
        int max = currentDay.getActualMaximum(Calendar.DATE);
        GridBagConstraints c = new GridBagConstraints();
        int today = currentDay.get(Calendar.DATE);

        String[] weekdayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri",
                "Sat" };
        JLabel weekday;
        JButton dayButton;

        // Draw weekday labels
        for (int i = 0; i < DAY_IN_WEEK; i++) // week
        {
            weekday = new JLabel(weekdayNames[i]);
            weekday.setBorder(blackBorder);
            c.fill = GridBagConstraints.BOTH;
            c.gridx = i;
            c.gridy = 0;
            month.add(weekday, c);
        }

        // Draw on buttons
        for (int i = 0; i < WEEK_IN_MONTH && days < max + 1; i++) // week
        {
            for (int j = 0; j < DAY_IN_WEEK && days < max + 1; j++) // day
            {
                countdown--;
                if (days > countdown)
                {
                    dayButton = new JButton(days + "");
                    dayButton.addActionListener(monthAction);
                    if (days == today)
                        dayButton.setBorder(BorderFactory.createLineBorder(
                                Color.BLACK, 3));
                    days++;
                    c.fill = GridBagConstraints.BOTH;
                    c.ipady = 20;
                    c.gridx = j;
                    c.gridy = i + 1; // +1 because of weekday row
                    month.add(dayButton, c);
                }
            }
        }

        month.repaint();
        month.updateUI();
    }

    /**
     * Draws timestamp on dayTime JPanel. Only needs to be done once.
     * @param events
     */
    public void drawDayTime()
    {
        System.out.println("View-drawDay");

        // Draw time of day labels
        for (int i = 0; i < DAY_HOURS; i++) // Week
        {
            dayTime.add(new JLabel(i + ":00"));
            dayTime.add(new JLabel("")); // Extra space
        }
    }

    /**
     * Draw new events that are on the current day.
     * @param events
     */
    private void drawDayEvents(Events events)
    {
        System.out.println("View-drawEvents");

        // Remove current day events
        dayEvents.removeAll();
        dayEvents.repaint();

        if (events != null)
        {
            // Draw the new day
            dvc.setEvents(events);
            this.dayEvents.add(dvc);
        }
        else
        {
            // Draw simple background
        }
    }

    void addQuitListener(ActionListener l)
    {
        quit.addActionListener(l);
    }

    void addPrevListener(ActionListener l)
    {
        prev.addActionListener(l);
    }

    void addNextListener(ActionListener l)
    {
        next.addActionListener(l);
    }

    void addCreateListener(ActionListener l)
    {
        create.addActionListener(l);
    }

    void addMonthViewListener(ActionListener l)
    {
        monthAction = l;
    }

    public void setMonthText(String m)
    {
        System.out.println("View-setMonthText");

        this.monthLabel.setText(m);
    }

    public void setDay(GregorianCalendar gc)
    {
        System.out.println("View-setDay");

        this.currentDay = gc;
    }

    public void setDayText(String d)
    {
        System.out.println("View-setDayText");

        this.dayLabel.setText(d);
    }

    public void setEvents(Events events)
    {
        System.out.println("View-setEvents");
        this.events = events;
    }

    public void repaint()
    {
        System.out.println("View-repaint");
        super.repaint();
    }

}
