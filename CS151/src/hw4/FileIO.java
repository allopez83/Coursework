package hw4;

import java.io.File;

/**
 * Handles all file IO operations
 * @author Hansen Wu
 *
 */
public class FileIO
{
    File savedCalendar;

    public FileIO()
    {
        System.out.println("FileIO");
        
        savedCalendar = new File("events.txt");
        if (savedCalendar.exists())
        {
            loadCalendar();
        }

    }

    void loadCalendar()
    {
        // TODO implement here
    }

    void saveCalendar()
    {
        // TODO implement here
    }

}