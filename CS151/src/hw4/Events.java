package hw4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Keeps track of events in a day, specified by the internal GregorianCalendar
 * @author Hansen Wu
 *
 */
public class Events implements Serializable
{
    private static final long serialVersionUID = 1L;
    private GregorianCalendar day;
    private ArrayList<Event> events;

    public Events(GregorianCalendar gc)
    {
        this.day = gc;
        events = new ArrayList<Event>();
    }

    /*
     * void setDay(GregorianCalendar d) { this.day = d; }
     * 
     * GregorianCalendar getDay() { return day; }
     */

    /**
     * Create an individual event. The day it is on is represented by the Events
     * object's internal GregorianCalendar.
     * @param name name of the event
     * @param start when it starts in 24hr format
     * @param end when it ends in 24hr format
     */
    public void add(String name, int start, int end)
    {
        events.add(new Event(name, start, end));
    }

    public ArrayList<Event> getEvents()
    {
        return events;
    }

    public int getQuantity()
    {
        return events.size();
    }

    /**
     * Check if existing events conflict with the given time parameter. Events
     * starting/ending on or within any other existing event will be a conflict.
     * @param start start of timeframe to check
     * @param end end of timeframe to check
     * @return true for overlap, false if the time is available
     */
    public boolean checkOverlap(int start, int end)
    {
        System.out.println("Events-checkOverlap");
        for (Event e : events)
        {
            int eStart = e.getStart();
            int eEnd = e.getEnd();
            // If the start or end time is on or within event duration
            // Equals sign check for exactly the same start or end times
            if (eStart <= start && start < eEnd) return true;
            if (eStart < end && end <= eEnd) return true;
        }
        return false;
    }

}

class Event implements Serializable
{
    private static final long serialVersionUID = 2L;
    private String name;
    private int start;
    private int end;

    /**
     * Event entry with the given data, where time is in 24 hours, i.e. 1425 ->
     * 2:25 pm
     * @param n name of the event
     * @param s start of the event
     * @param e end of the event
     */
    public Event(String n, int s, int e)
    {
        this.name = n;
        this.start = s;
        this.end = e;
    }

    // Get and Set methods
    public String getStartHours() { return String.format("%02d", start / 100); }
    public String getStartMinutes() { return String.format("%02d", start % 100); }
    public String getEndHours() { return String.format("%02d", end / 100); }
    public String getEndMinutes() { return String.format("%02d", end % 100); }

    public String getName() { return name; }
    public int getStart() { return start; }
    public int getEnd() { return end; }

    /**
     * Creates string in HH:mm-HH:mm; TITLE format representing event
     * information
     * @return string of event information
     */
    public String toSring()
    {
        String s = getStartHours() + ":" + getStartMinutes();
        String e = getEndHours() + ":" + getEndMinutes();
        return s + "-" + e + "; " + name;
    }
}
