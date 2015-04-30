package hw4;

/**
 * Launches SimpleCalendar
 * @author Hansen Wu
 *
 */
public class SimpleCalendar
{

    public static void main(String[] args)
    {
        new Controller();

        // Check time conversion
        // int time = 1408;
        // System.out.println("raw: " + time);
        // System.out.println("hour: " + (time / 100));
        // System.out.println("min: " + (time % 100));

        // GC to int conversion
        // GregorianCalendar gc = new GregorianCalendar();
        // int year = gc.get(Calendar.YEAR);
        // int month = gc.get(Calendar.MONTH);
        // int day = gc.get(Calendar.DATE);
        // int result = 0;
        // result += day;
        // result += month * 100;
        // result += year * 100 * 100;
        // System.out.println(result);
        // should result in yyyymmdd int

        // String date = "3/31/14";
        // String[] split = date.split("/"); // MM/DD/YYYY format
        // String year = String.format("%02d", Integer.parseInt(split[2]));
        // year = "20" + year;
        // String month = String.format("%02d", Integer.parseInt(split[0]));
        // String day = String.format("%02d", Integer.parseInt(split[1]));
        // String result = year + "" + month + day + "";
        // System.out.println(result);
    }

}
