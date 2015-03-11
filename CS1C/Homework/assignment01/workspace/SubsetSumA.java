package assignment01.workspace;

import java.util.ArrayList;

/**
 * CS1C, week 1, Part A
 * 
 * @author Hansen Wu
 * 
 */
public class SubsetSumA
{
   public static void main(String[] args)
   {
      boolean perfectFound = false;
      final double limit = 48.0;
      ArrayList<Sublist> collection = new ArrayList<Sublist>();
      final ArrayList<Double> masterList = new ArrayList<Double>();
      Sublist list = new Sublist(masterList),
            perfectSublist = new Sublist(masterList);

      masterList.add(2.2);
      masterList.add(12.5);
      masterList.add(22.7);
      masterList.add(5.8);
      masterList.add(15.4);
      masterList.add(25.3);
      masterList.add(9.9);
      masterList.add(19.7);
      masterList.add(29.6);

      collection.add(list);
      // Go through all 10 numbers in the master set
      for (int i = 0; i < masterList.size() && !perfectFound; i++)
      {
         int collectionSize = collection.size();
         // Go through all Sublists 'list' in 'collection'
         for (int k = 0; k < collectionSize && !perfectFound; k++)
         {
            list = collection.get(k);
            double sum = sum(list, masterList.get(i));
            if (sum <= limit)
            {
               try
               {
                  if (sum == limit)
                  {
                     perfectSublist = list.addItem(i);
                     perfectFound = true;
                  }
                  if (sum < limit)
                  {
                     collection.add(list.addItem(i));
                  }
               } catch (CloneNotSupportedException e)
               {
                  e.printStackTrace();
               }
            }
         }
      }
      // Find the largest TuneList, assign it to 'list'
      if (perfectSublist == null)
         list = largestSum(collection);
      else
         list = perfectSublist;
      System.out.println("Double SubsetSum\n" + "Limit: " + limit + "\nSum: "
            + list.getSum());
      list.showSublist();
   }

   /**
    * Adds up the elements of a Sublist along with a new element
    * @param sublist a Sublist containing numbers to add together
    * @param newElement double that will also be added
    * @return double representing the total value
    */
   private static double sum(Sublist sublist, Double newElement)
   {
      double total = 0;
      total += sublist.getSum();
      total += newElement;
      return total;
   }

   /**
    * Searches for the Sublist with the largest sum in a provided ArrayList
    * @param arrayList ArrayList that will be searched
    * @return Sublist that has the largest sum
    */
   private static Sublist largestSum(ArrayList<Sublist> arrayList)
   {
      Sublist largestSublist = arrayList.get(0);
      // Go through all Sublists
      for (int k = 0; k < arrayList.size(); k++)
      {
         // Replace current largestSublist is a larger one is found
         if (largestSublist.getSum() < arrayList.get(k).getSum())
         {
            largestSublist = arrayList.get(k);
         }
      }
      return largestSublist;
   }

   static class Sublist implements Cloneable
   {
      private double sum = 0;
      private ArrayList<Double> originalObjects;
      private ArrayList<Integer> indices;

      /**
       * Constructor for Sublist objects
       * @param orig master ArrayList of all elements to be considered
       */
      public Sublist(ArrayList<Double> orig)
      {
         sum = 0;
         originalObjects = orig;
         indices = new ArrayList<Integer>();
      }

      /**
       * Retrieves the sum of all elements referred to in the Sublist
       * @return sum of all elements referred to in Sublist
       */
      double getSum()
      {
         return sum;
      }

      /**
       * Creates a deep copy of the Sublist
       * @return deep copy of Sublist
       */
      @SuppressWarnings("unchecked")
      public Object clone() throws CloneNotSupportedException
      {
         // Shallow copy
         Sublist new_object = (Sublist) super.clone();
         // Deep copy
         new_object.indices = (ArrayList<Integer>) indices.clone();
         return new_object;
      }

      /**
       * Adds a new element to the Sublist
       * @param index element to be added to the Sublist
       * @return the Sublist with added element
       * @throws CloneNotSupportedException
       */
      Sublist addItem(int index) throws CloneNotSupportedException
      {
         Sublist newList = (Sublist) this.clone();
         newList.sum += originalObjects.get(index);
         newList.indices.add(index);
         return newList;
      }

      /**
       * Prints the sublist to the console
       */
      void showSublist()
      {
         System.out.print("[ ");
         for (int k = 0; k < indices.size(); k++)
         {
            // Retrieve index value, then use it to retrieve the element
            System.out.print(originalObjects.get(indices.get(k)) + " ");
         }
         System.out.println("]");
      }
   }
}

/* PASTE FROM CONSOLE --------------------------------
Double SubsetSum
Limit: 48.0
Sum: 48.0
[ 22.7 25.3 ]

 */