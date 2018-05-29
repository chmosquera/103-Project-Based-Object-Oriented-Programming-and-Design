// Name: Chanelle Mosquera
// Class: CPE 103

public class InsertionTest {

   public static void sortLinear(Comparable[] x) {

      // Stores the values in order
      for (int i = 1; i < x.length; i++) {
         Comparable obj = x[i];

         // Get the index 'obj' should be put in to achieve sorted order
         int location = linearSearch(x, obj, 0, i);

         // Adjust objects in list to make room for 'obj' to plug in
         for (int j = i; j > location; j--) {
            x[j] = x[j-1];

         }

         // Place 'obj' in correct loction
         x[location] = obj;
      }      
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


   /* Assuming the the array is already sorted from indices 'first' to ('last'-1), this method 
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

}
