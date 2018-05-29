public class Testing {

   public static void main(String[] args) {

      int[] a1 = {2,1,3};

      MedianOfThree(a1, 0, 2);

   
   
   
   }

   public static void MedianOfThree(int[] data, int first, int last) {
      System.out.println("Original: " + toString(data));

         int mid = (first+last)/2;

         // FIND THE MEDIAN
         // Create an array that holds the three values, first, last, and pivot (unsorted)
         int[] three = new int[3];
         three[0] = data[first];
         three[1] = data[mid];
         three[2] = data[last];

         // sort the three #'s to find the median of three
         // after sorted, median will be at three[1]
         if (compare(three[1],three[0]) < 0)
            Swap(three, 1, 0);
         if (compare(three[2], three[1]) < 0) {
            Swap(three, 2, 1);
            if (compare(three[1], three[0]) < 0)
               Swap(three, 1, 0);
         }
        
/*
         for (int i = 1; i<three.length; i++) {
            for (int j = i-1; j >= 0; j--) {
               if (compare(three[i], three[j]) == -1) {
                  Swap(three, i, j);
               }
            }
         } */

         System.out.println("After sorted: " +toString(three));
   }

   public static String toString(int[] x) {
      String s = "[";
      for (int i = 0; i < x.length; i++) {
         s = s.concat(" " + x[i] + ",");
      }
      s = s.concat("]");
      return s;
   }

   private static int compare(int left, int right) 
   {
      if (left < right)
         return -1;
      else if (left > right)
         return 1;
      else
         return 0;
   }

   public static void Swap(int[] data, int i, int j) {
      // Initiate variables that hold the values at indices i and j of the list
      int i_obj = data[i];
      int j_obj = data[j];
      data[i] = j_obj;
      data[j] = i_obj;
   }

}
