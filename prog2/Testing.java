import java.util.Random;

public class Testing {

   public static void main(String[] args) {

      // Initiate variables - values will change for each group of array and its sort method
      Comparable[] s;
      int amt, before, after, elapsed;
      Random rand = new Random();

      // initial amount of objects in array
      amt = 100;

      // Call Linear Search Sort on three different sized arrays - sizes: 1E3, 1E4, 1E5;
      for (int j = 0; j < 1; j++) {

         // Fill array with objects in decreasing order
         s = new Comparable[amt];   
         for (int i = 0; i < s.length; i++)
            s[i] = new Integer(rand.nextInt(i+1));

         // Print out array before sort
         System.out.println(toString(s));

         // Sort array, calculate elapsed time, and print result
         before = (int)System.currentTimeMillis();
         sortBinary(s);
         after = (int)System.currentTimeMillis();

         // Print out array after sort
         System.out.println(toString(s));

         elapsed = after - before;
         System.out.println(Timed.printTime(amt, "random", "Binary Search", elapsed, Timed.isSorted(s)));
 
         // Update amount of objects of next array
         amt *= 10;
      }

      System.out.println();

      //________ COMPARABLE - STRING TEST _____________
      Comparable[] s2 = {"Bananas", "Juice", "Zebra", "Lion", "Alligator", "Apples", "Pencils"};

         // Print out array before sort
         System.out.println(toString(s2));

         // Sort array, calculate elapsed time, and print result
         before = (int)System.currentTimeMillis();
         sortBinary(s2);
         after = (int)System.currentTimeMillis();

         // Print out array after sort
         System.out.println(toString(s2));

         elapsed = after - before;
         System.out.println(Timed.printTime(amt, "random", "Binary Search", elapsed, Timed.isSorted(s2)));


   }

   /* Assuming the the array is already sorted from indices 0 to ('last'-1),this method 
      will give the location 'obj' must be placed in to achieve sorted order using linear search */
   public static int linearSearch(Comparable[] y, Comparable obj, int first, int last) {

      // initiate an int to be the starting index to search from   
      boolean found = false;
      int idx = last;

      while (!found) {
         if (last == 0) {
            found = true;
         }
         else if (obj.compareTo(y[last - 1]) >= 0) {
            found = true;
         }
         else
            last--;
      }
      return last;      
   }

   /* Assuming the the array is already sorted from indices 0 to ('last'-1), this method 
      will give the location 'obj' must be placed in to achieve sorted order using binary search*/
   public static int binarySearch(Comparable[] y, Comparable obj, int first, int last) {

      while (last >= first) {

        // Initiate the mid of the sub-array
        int mid = (first + last)/2;
        
        if (obj.compareTo(y[mid]) <= 0) 
           last = mid-1;
        else if (obj.compareTo(y[mid]) >= 0)
           first = mid +1;
      }

      return first;
   }

   /* Assuming the the array is already sorted from indices 0 to ('last'-1), this method 
      will give the location 'obj' must be placed in to achieve sorted order using recursive binary search*/
   public static int recursiveBinarySearch(Comparable[] y, Comparable obj, int first, int last) {

     // Initiate the mid of the sub-array
     int mid = (first + last) / 2;

      // Base case - ends the recursion
      // 'last' becomes less than 'first' when 'obj' should be placed before the object stored in index 'first'
      if (last < first){
         return first;
     }

     // Compare 'obj' to the object stored in y[mid] and begin binary search in corresponding half
     if (obj.compareTo( y[mid] ) <= 0) {       
        return binarySearch(y, obj, first, mid-1);
     }
     else if (obj.compareTo( y[mid] ) >= 0)
        return binarySearch(y, obj, mid+1, last);
    
     return -1;
   }

   public static void sortBinary(Comparable[] x){

      // Stores the values in order
      for (int i = 1; i < x.length; i++) {
         Comparable obj = x[i];

         // Get the index 'obj' should be put in to achieve sorted order
         int location = binarySearch(x, obj, 0, i);

         // Adjust objects in list to make room for 'obj' to plug in
         for (int j = i; j > location; j--) {
            x[j] = x[j-1];

         }

         // Place 'obj' in correct loction
         x[location] = obj;
      }
   }

   // Prints array
   public static String toString(Comparable[] x) {
      String s = "[";
      for (int i = 0; i < x.length; i++) {
         s = s.concat(" " + x[i].toString() + ",");
      }
      s = s.concat("]");
      return s;
   }

}


