// Name: Chanelle Mosquera
// Class: CPE 103

import java.util.Random;

public class Timed{

   public static void main(String[] args) {

      // Initiate variables - values will change for each group of array and its sort method
      Comparable[] s;
      int amt, before, after, elapsed;

   /* _____ LINEAR SEARCH SORT - OBJECTS IN ORDER ______ */

      // initial amount of objects in array
      amt = 1000;

      // Call Linear Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 3; j++) {

         // Fill array with objects in increasing order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++)
            s[i] = new Integer(i);

         // Sort array, calculate ellapsed time, and print result
         before = (int)System.currentTimeMillis();
         InsertionTest.sortLinear(s);
         after = (int)System.currentTimeMillis();
         elapsed = after - before;
         System.out.println(printTime(amt, "", "Linear Search", elapsed, isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();

   /* _____ BINARY SEARCH SORT - OBJECTS IN ORDER ______ */

      // initial amount of objects in array
      amt = 1000;

      // Call Binary Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 3; j++) {

         // Fill array with objects in increasing order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++)
            s[i] = new Integer(i);

         // Sort array, calculate ellapsed time, and print result
         before = (int)System.currentTimeMillis();
         InsertionTest.sortBinary(s);
         after = (int)System.currentTimeMillis();
         elapsed = after - before;
         System.out.println(printTime(amt, "", "Binary Search", elapsed, isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();
      
   /* _____ LINEAR SEARCH SORT - OBJECTS IN REVERSE ORDER ______ */

      // initial amount of objects in array
      amt = 1000;

      // Call Linear Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 3; j++) {

         // Fill array with objects in decreasing order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++)
            s[i] = new Integer(-i);

         // Sort array, calculate elapsed time, and print result
         before = (int)System.currentTimeMillis();
         InsertionTest.sortLinear(s);
         after = (int)System.currentTimeMillis();
         elapsed = after - before;
         System.out.println(printTime(amt, "reverse", "Linear Search", elapsed, isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();

   /* _____ BINARY SEARCH SORT - OBJECTS IN REVERSE ORDER ______ */

      // initial amount of objects in array
      amt = 1000;

      // Call Binary Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 3; j++) {

         // Fill array with objects in decreasing order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++)
            s[i] = new Integer(-i);

         // Sort array, calculate elapsed time, and print result
         before = (int)System.currentTimeMillis();
         InsertionTest.sortBinary(s);
         after = (int)System.currentTimeMillis();
         elapsed = after - before;
         System.out.println(printTime(amt, "reverse", "Binary Search", elapsed, isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();

   /* _____ LINEAR SEARCH SORT - OBJECTS IN RANDOM ORDER ______ */

      // initial amount of objects in array
      amt = 1000;

      // Call Linear Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 3; j++) {

         // Fill array with objects in random order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++) {
            Random rand = new Random();
            s[i] = new Integer(rand.nextInt(i+1));
         }

         // Sort array, calculate elapsed time, and print result
         before = (int)System.currentTimeMillis();
         InsertionTest.sortLinear(s);
         after = (int)System.currentTimeMillis();
         elapsed = after - before;
         System.out.println(printTime(amt, "random", "Linear Search", elapsed, isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();

   /* _____ BINARY SEARCH SORT - OBJECTS IN RANDOM ORDER ______ */

      // initial amount of objects in array
      amt = 1000;
	
      // Call Binary Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 3; j++) {

         // Fill array with objects in random order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++) {
            Random rand = new Random();
            s[i] = new Integer(rand.nextInt(i+1));
         }

         // Sort array, calculate elapsed time, and print result
         before = (int)System.currentTimeMillis();
         InsertionTest.sortBinary(s);
         after = (int)System.currentTimeMillis();
         elapsed = after - before;
         System.out.println(printTime(amt, "random", "Binary Search", elapsed, isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();

   /* _____ END OF MAIN _____ */

   }



   /* Checks if the array has been sorted - all objects should be in increasing order */
   public static boolean isSorted(Comparable[] x) {

      boolean sorted = true;

      // Compare each value with the next value to see if they are in order
      // If value is greater than the next value, they are not in order!
      for (int i = 0; i < x.length-1; i++){
         if (x[i].compareTo(x[i+1]) > 0)
            sorted = false;
      }

      return sorted;
   }


   /* Creates string of result to print */
   public static String printTime(int amt, String orderType, String sortMethod, int time, boolean sorted) {
      String s = "For " + amt + " objects in " + orderType + " order, the " + sortMethod + " sort time is " + time + " milliseconds - ";
      if (sorted)
         s = s.concat("Sorting verified");
      else 
         s = s.concat("Sorting failed");
      return s;
   }



}
