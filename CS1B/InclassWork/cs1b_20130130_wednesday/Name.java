package cs1b_20130130_wednesday;

/**
 * one object represents someone's first and last name
 */
public class Name implements Cloneable{
    private String first;
    private String last;
    public void setFirst(String newFirst) {
        first = newFirst;
    }
    public String toString() {
        return first + " " + last;
    }
    /**
     * calls the default clone() method from Object to make a copy
     * of the object that calls this method and returns the copy.
     */
    public Object clone() {
        try {
            return super.clone();  // block copy
        } catch (CloneNotSupportedException c){
            System.out.println( c );
            return null;
        }
    }
}