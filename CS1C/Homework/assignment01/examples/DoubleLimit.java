package assignment01.examples;

// USES SUBLIST, NOT SUBSET, THIS IS JUST TO REMOVE COMPILE ERRORS
import java.lang.Character.Subset;
import java.util.ArrayList;

public class DoubleLimit
{
   // ------- main --------------
   @SuppressWarnings("unused")
   public static void main(String[] args) throws Exception
   {
      int TARGET = 72;
      ArrayList<Double> data_set = new ArrayList<Double>();
      ArrayList<Subset> choices = new ArrayList<Subset>();
      int k, j, num_sets, k_best;
      double max;
      boolean found_perfect;

      data_set.add(2.2);
      data_set.add(12.5);
      data_set.add(22.7);
      data_set.add(5.1);
      data_set.add(15.4);
      data_set.add(25.0);
      data_set.add(9.9);
      data_set.add(19.7);
      data_set.add(29.6);

      // code supplied by student

      //COMMENTED TO REMOVE COMPILE ERRORS
      //choices.get(k_best).showSublist();
   }
}