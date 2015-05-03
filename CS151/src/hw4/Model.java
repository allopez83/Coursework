package hw4;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Engine of SimpleCalendar, interacts with data, does the processing, and
 * manipulates data
 * @author Hansen Wu
 *
 */
public class Model
{
    private static final boolean DEBUG = true;
    private static final boolean DEMO_EVENTS = false;
    private HashMap<Integer, Events> calendar;// Contains days and events
    private GregorianCalendar today; // Stores the day user's system is on
    private GregorianCalendar currentDay; // Day that calendar is looking at
    private Events events;
    private View view;
    private CreateView menu; 
    private DateFormatSymbols dfs;
    private FileIO io;

    public Model()
    {
        if (DEBUG) System.out.println("Model");

        io = new FileIO();
        dfs = new DateFormatSymbols();
        today = new GregorianCalendar();
        currentDay = today;

        calendar = io.loadCalendar();

        if (DEMO_EVENTS) addDemoEvent();

        // Check for events today
        events = calendar.get(gcToKey(currentDay));
    }

    /**
     * Adds a list of events for test and debugging
     */
    private void addDemoEvent()
    {
        if (DEBUG) System.out.println("Model-addDemoEvents");

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
     * @param v View to associate with this model
     */
    public void setView(View v)
    {
        if (DEBUG) System.out.println("Model-setView");

        this.view = v;
        updateViewData();
    }

    /**
     * Activated when the day changes. Updates View to focus on the new day.
     */
    private void updateView()
    {
        if (DEBUG) System.out.println("Model-updateView");

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
        if (DEBUG) System.out.println("Model-updateViewData");

        view.setEvents(events);
        view.setDay(currentDay);
        view.setDayText(getDayViewString(currentDay));
        view.setMonthText(getMonthViewString(currentDay));
    }

    /**
     * Advance day by 1 to the next day
     */
    public void next()
    {
        if (DEBUG) System.out.println("Model-next");

        currentDay.add(Calendar.DATE, 1);
        updateView();
    }

    /**
     * Go back by 1 day to previous day
     */
    public void prev()
    {
        if (DEBUG) System.out.println("Model-prev");

        currentDay.add(Calendar.DATE, -1);
        updateView();
    }

    /**
     * Go to some day of the current month
     * @param day day of the month to go to
     */
    public void jumpToDay(int day)
    {
        if (DEBUG) System.out.println("Model-jumpToDay");

        currentDay.set(Calendar.DATE, day);
        updateView();
    }

    /**
     * Launches event creation menu
     */
    public void createMenu()
    {
        if (DEBUG) System.out.println("Model-createMenu");

        String day = "";
        int y = currentDay.get(Calendar.YEAR) % 100;
        int m = currentDay.get(Calendar.MONTH) + 1;
        int d = currentDay.get(Calendar.DATE);
        day = m + "/" + d + "/" + y;
        menu = view.createMenu(day);
    }

    /**
     * Save an event user created in event creation menu "CreateView"
     * @return boolean representing if method succeeded or not
     */
    public boolean saveEvent()
    {
        if (DEBUG) System.out.println("Model-saveEvent");
        
        String name = menu.getName();
        String dateString = menu.getDate();
        String startString = menu.getStart();
        String endString = menu.getEnd();
        Date result;
        int start = 0, end = 0;

        // Convert time to int
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
        catch (ParseException e) { e.printStackTrace(); } // Should not happen

        // If target day's Events entry didn't exist yet, create and enter it
        int key = stringToKey(dateString);
        Events entry = calendar.get(key);
        if (entry == null)
        {
            String[] split = dateString.split("/");
            int y = Integer.parseInt(split[2]);
            int m = Integer.parseInt(split[0]);
            int d = Integer.parseInt(split[1]);
            entry = new Events(new GregorianCalendar(y, m, d));
            calendar.put(key, entry);
        }
        // Otherwise there's events, make sure there isn't a time conflict
        else
        {
            if (entry.checkOverlap(start, end))
            {
                // Conflict
                menu.fail();
                return false;
            }
        }

        entry.add(name, start, end);
        updateView();
        menu.dispose();

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
        return dfs.getMonths()[monthInt] + " " + gc.get(Calendar.YEAR);
    }

    /**
     * Renders the string for dayview of the current focused day in WKD M/D
     * format, i.e. "Wednesday 4/29"
     * @param gc GregorianCalendar of the day in focus
     * @return String in WKD M/D format representing focused day
     */
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

    /**
     * Save all created events to disk
     */
    public void saveData()
    {
        if (DEBUG) System.out.println("Model-saveData");
        io.saveCalendar(calendar);
    }

}
