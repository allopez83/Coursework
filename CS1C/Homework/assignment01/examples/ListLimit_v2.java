package assignment01.examples;

import java.util.ArrayList;

public class ListLimit_v2
{
   public static void main(String[] args)
   {
      boolean perfectListFound = false;
      final int limit = 37;
      ArrayList<Sublist> collection = new ArrayList<Sublist>();
      final ArrayList<Integer> masterSet = new ArrayList<Integer>();
      
      masterSet.add(5);
      masterSet.add(14);
      masterSet.add(8);
      masterSet.add(9);
      masterSet.add(7);
      masterSet.add(4);
      masterSet.add(11);
      masterSet.add(18);
      masterSet.add(13);
      masterSet.add(6);

      // Sublist that will be checked if it can hold more values without
      // exceeding target
      Sublist L = new Sublist(masterSet);
      // Initialize array to hold arrays determined to be valid
      collection.add(L);
      // Go through all 10 numbers in the master set
      for (int i = 0; i < masterSet.size() && !perfectListFound; i++)
      {
         int x = masterSet.get(i);
         System.out.println("S location: " + i);
         // Save size as integer since it will fluctuate as program runs
         int collectionSize = collection.size();
         System.out.println("collectionSize: " + collectionSize);
         // Go through all Sublists'L' in 'Collection'
         for (int k = 0; k < collectionSize; k++)
         {
            System.out.println("Col location: " + k);
            L = collection.get(k);
            int sum = sum(L, x);
            System.out.println("Sum of operation: " + sum);
            if (sum <= limit)
            {
               try
               {
                  collection.add(L.addItem(i));
               } catch (CloneNotSupportedException e)
               {
                  e.printStackTrace();
               }
            }
            if (sum == limit)
            {
               perfectListFound = true;
            }
         }
      }
      // Find the largest Sublist, assign it to 'L'
      L = largestSum(collection);
      L.showSublist();
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
         // If the current largest Sublist is less than the Sublist currently
         // on, replace it with the new one
         if (largestSublist.getSum() < arrayList.get(k).getSum())
         {
            largestSublist = arrayList.get(k);
         }
      }
      return largestSublist;
   }

   /**
    * Adds up the elements of a Sublist along with a new element
    * @param sublist a Sublist containing numbers to add together
    * @param newElement integer that will also be added
    * @return integer representing the total value
    */
   private static int sum(Sublist sublist, int newElement)
   {
      return sublist.getSum() + newElement;
   }
}

class Sublist implements Cloneable
{
   // Total of all elements stored inside
   private int sum = 0;
   // Copy of 'S'
   private ArrayList<Integer> originalObjects;
   // List of indices that work
   private ArrayList<Integer> indices;

   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<Integer> orig)
   {
      sum = 0;
      originalObjects = orig;
      indices = new ArrayList<Integer>();
   }

   /**
    * Retrieves the sum of all elements referred to in the Sublist
    * @return sum of all elements referred to in Sublist
    */
   int getSum()
   {
      return sum;
   }

   // I have done the clone() for you, since you will need clone() inside
   // addItem().
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

   void showSublist()
   {
      System.out.print("[ ");
      for (int k = 0; k < indices.size(); k++)
      {
         System.out.print(originalObjects.get(indices.get(k)) + " ");
      }
      System.out.println("]");
   }
};