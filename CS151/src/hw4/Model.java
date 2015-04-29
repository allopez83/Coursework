package hw4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Date;

import javax.swing.JButton;

/**
 * Engine of SimpleCalendar, interacts with data, does the processing, and
 * manipulates data
 * @author Hansen Wu
 *
 */
public class Model
{
    // Contains days and events
    HashMap<Integer, Events> calendar;

    // Buttons on view and associated listeners
    ArrayList<JButton> buttons;
    ArrayList<EventListener> listeners;

    // Stores the day user's system is on
    GregorianCalendar today;
    // Day that calendar is looking at
    GregorianCalendar currentDay;

    Events events;
    View view;
    DateFormatSymbols dfs;
    
    FileIO io;

    public Model()
    {
        System.out.println("Model");

        // Read file
        // loadFile();
        io = new FileIO();;

        calendar = new HashMap<Integer, Events>();
        today = new GregorianCalendar();
        currentDay = today;
        
        addDemoEvent();

        // Are there any events on the current day?
        Integer key = gcToKey(currentDay);
        events = calendar.get(key);

        dfs = new DateFormatSymbols();
    }

    private void addDemoEvent()
    {
        events = new Events(currentDay);
        events.add("zerohour", 0, 600);
        events.add("same end time", 800, 1400);
        events.add("same start time", 1400, 1530);
        events.add("long name for event off in the middle of nowhere", 1700,
                2300);
        
        calendar.put(this.gcToKey(today), events);
        
        GregorianCalendar tomorrow = new GregorianCalendar();
        tomorrow.add(Calendar.DATE, 1);
        events = new Events(currentDay);
        events.add("same end time", 800, 1400);
        events.add("same start time", 1400, 1530);

        calendar.put(this.gcToKey(tomorrow), events);
        
        events = null;
    }

    /**
     * Initial association between model and view
     * @param v
     */
    public void setView(View v)
    {
        System.out.println("Model-setView");

        this.view = v;
        updateViewData();
    }

    /**
     * Activated when the day changes. Updates View to focus on the new day.
     */
    private void updateView()
    {
        System.out.println("Model-updateView");

        // Are there any events on the current day?
        Integer key = gcToKey(currentDay);
        events = calendar.get(key);

        // Update date information on view
        updateViewData();
        view.redraw();
    }

    /**
     * Push new data for view to use
     */
    private void updateViewData()
    {
        System.out.println("Model-updateViewData");

        view.setEvents(events);
        view.setDay(currentDay);
        view.setDayText(getDayViewString(currentDay));
        view.setMonthText(getMonthViewString(currentDay));
    }

    public void next()
    {
        System.out.println("Model-next");

        currentDay.add(Calendar.DATE, 1);
        updateView();
    }

    public void prev()
    {
        System.out.println("Model-prev");

        currentDay.add(Calendar.DATE, -1);
        updateView();
    }

    public void jumpToDay(int day)
    {
        System.out.println("Model-jumpToDay");

        currentDay.set(Calendar.DATE, day);
        updateView();
    }

    public void createMenu()
    {
        System.out.println("Model-createMenu");

        String day = "";
        int y = currentDay.get(Calendar.YEAR) % 100;
        int m = currentDay.get(Calendar.MONTH) + 1;
        int d = currentDay.get(Calendar.DATE);
        day = m + "/" + d + "/" + y;
        CreateView menu = new CreateView(day);

        menu.addSaveListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(" > Save pressed");

                if (saveEvent(menu)) // calendar.put(key, value);
                {
                    menu.dispose();
                    updateView();
                }
                else
                    menu.fail();
            }
        });
    }

    /**
     * Save an event user created in event creation menu "CreateView"
     * @param menu the CreateView with user input to save as event
     * @return boolean representign if method succeded or not
     */
    private boolean saveEvent(CreateView menu)
    {
        System.out.println("Model-saveEvent");
        String name = menu.getName();
        String dateString = menu.getDate();
        String startString = menu.getStart();
        String endString = menu.getEnd();
        Date result;
        int start = 0, end = 0;

        SimpleDateFormat displayFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");

        try
        {
            // Start
            result = parseFormat.parse(startString);
            start = Integer.parseInt(displayFormat.format(result));
            // End
            result = parseFormat.parse(endString);
            end = Integer.parseInt(displayFormat.format(result));
        }
        catch (ParseException e) { e.printStackTrace(); }

        // If target day's Events entry didn't exist yet, create and enter it
        int key = stringToKey(dateString);
        Events target = calendar.get(key);
        if (target == null)
        {
            String[] split = dateString.split("/");
            int y = Integer.parseInt(split[2]);
            int m = Integer.parseInt(split[0]);
            int d = Integer.parseInt(split[1]);
            target = new Events(new GregorianCalendar(y, m, d));
            calendar.put(key, target);
        }
        // Otherwise, make sure there isn't a time conflict
        else
        {
            if (target.checkOverlap(start, end)) return false;
        }

        target.add(name, start, end);
        
        return true;
    }

    /**
     * Produces an integer in yyyymmdd format from given Gregorian Calendar
     * @param gc Gregorian Calendar object to extract date from
     * @return date of gc in yyyymmdd format, i.e. 20150024 for January 24th
     *         2015
     */
    private Integer gcToKey(GregorianCalendar gc)
    {
        int year = gc.get(Calendar.YEAR);
        int month = gc.get(Calendar.MONTH);
        int day = gc.get(Calendar.DATE);

        int result = 0;
        result += day;
        result += month * 100;
        result += year * 100 * 100;
        // Results in yyyymmdd int

        return result;
    }

    /**
     * Parses a MM/DD/YY input to the yyyymmdd key format for using with HashMap
     * @param s string in mm/dd/yyyy format to convert
     * @return Integer in yyyymmdd format
     */
    private Integer stringToKey(String s)
    {
        String[] split = s.split("/"); // MM/DD/YYYY format

        String year = String.format("%02d", Integer.parseInt(split[2]));
        year = "20" + year; // Dates are assumed to only be in 21st century
        String month = String.format("%02d", Integer.parseInt(split[0])-1); // Month starts at zero 
        String day = String.format("%02d", Integer.parseInt(split[1]));
        // Results in yyyymmdd format

        return Integer.parseInt(year + month + day);
    }

    /**
     * Get month as a String from a GregorianCalendar
     * @param gc GregorianCalendar to extract month name from
     * @return String of month name, ex: January, February, etc.
     */
    private String getMonthViewString(GregorianCalendar gc)
    {
        int monthInt = gc.get(Calendar.MONTH);
        String[] months = dfs.getMonths();
        return months[monthInt] + " " + gc.get(Calendar.YEAR);
    }

    private String getDayViewString(GregorianCalendar gc)
    {
        String result = "";

        // Get time units
        String weekday = dfs.getWeekdays()[currentDay.get(Calendar.DAY_OF_WEEK)];
        int month = currentDay.get(Calendar.MONTH) + 1; // month starts at zero
        int day = currentDay.get(Calendar.DATE);
        result = weekday + " " + month + "/" + day;
        return result;
    }

    public void saveData()
    {
        System.out.println("Model-saveData");
        
        io.saveCalendar();
    }

}
