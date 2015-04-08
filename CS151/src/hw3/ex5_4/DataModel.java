package hw3.ex5_4;

import javax.swing.event.*;

/**
 * A Subject class for the observer pattern.
 */
public class DataModel
{
   Integer scale;
   ChangeListener car;

   public DataModel(Integer i)
   {
      scale = i;
   }

   /**
    * Attach a listener to the Model
    * @param c the listener
    */
   public void attach(ChangeListener c)
   {
      car = c;
   }

   /**
    * Change the data in the model at a particular location
    * @param location the index of the field to change
    * @param value the new value
    */
   public void update(Integer i)
   {
      scale = i;
      car.stateChanged(new ChangeEvent(this));
   }
}
