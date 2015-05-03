package hw4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Handles all file IO operations
 * @author Hansen Wu
 *
 */
public class FileIO
{
    private static final boolean DEBUG = true;
    private final File calFile = new File("events.txt");
    private FileInputStream fileIn;
    private ObjectInputStream reader;
    private FileOutputStream fileOut;
    private ObjectOutputStream writer;

    public FileIO()
    {
        System.out.println("FileIO");
    }

    /**
     * Loads HashMap of events from events.txt using serializable
     * @return HashMap containing events, or a new HashMap if none found
     */
    HashMap<Integer, Events> loadCalendar()
    {
        String fail = "ERROR: FileIO-saveCalendar: ";

        HashMap<Integer, Events> h = null;

        // New calendar if no save found
        if (!calFile.exists()) return new HashMap<Integer, Events>();

        // Create file if it doesn't exist
        if (DEBUG) System.out.println("Reading in " + calFile);
        try
        {
            fileIn = new FileInputStream(calFile);
            reader = new ObjectInputStream(fileIn);

            h = (HashMap<Integer, Events>) reader.readObject();

            reader.close();
            fileIn.close();
        }
        catch (Exception e)
        {
            System.out.println(fail + e);
            e.printStackTrace();
        }

        return h;

    }

    /**
     * Writes calendar HashMap to events.txt using serializable
     * @param cal calendar HashMap to write
     */
    void saveCalendar(HashMap<Integer, Events> cal)
    {
        String fail = "ERROR: FileIO-saveCalendar: ";

        // Create new empty file
        if (calFile.exists()) calFile.delete();
        try
        {
            if (DEBUG) System.out.println("Creating " + calFile);
            calFile.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println(fail + e);
            e.printStackTrace();
        }

        // Write calendar to file
        if (DEBUG) System.out.println("Saving to " + calFile);
        try
        {
            fileOut = new FileOutputStream(calFile);
            writer = new ObjectOutputStream(fileOut);

            writer.writeObject(cal);

            writer.close();
            fileOut.close();
        }
        catch (Exception e)
        {
            System.out.println(fail + e);
            e.printStackTrace();
        }
    }

}
