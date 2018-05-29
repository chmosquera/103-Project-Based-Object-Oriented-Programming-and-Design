public class MyMergeSort {


   public 

   private static int[] merge(int[] x,int[] y)
   {
      int[] z = new int[x.length + y.length];

      // points to idx of xlist and ylist and zlist
      int i = 0;
      int j = 0;
      int curr = 0;
  
      boolean oneSorted = false;

      // sorts x and y list until one list is finished sorting
      while (oneSorted == false) {
         // if one of the lists has been completely sorted, stop comparing the lists
         if ((i == x.length) || (j == y.length)) {
            oneSorted = true;
         }
         else if (x[i] <= y[j]) {
            z[curr] = x[i];
            i++;
            curr++;
         }
         else if (y[j] <= x[i]){
            z[curr] = y[j];
            j++;
            curr++;
         }
      }

      // if one list is sorted before the other, assign the rest of remaining list to z-list
      if (i == x.length) {
         while (j != y.length) {
            z[curr] = y[j];
            j++;
            curr++;
         }
      }
      else if (j == y.length) {
         while (i != x.length) {
            z[curr] = x[i];
            i++;
            curr++;
         }
      }

      return z;
   }   

}
