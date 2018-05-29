import java.util.Random;


public class MyMergeSort_2 {

   public static void main(String[] args) {

      Random rand = new Random();      

      int limit = 1000;
      int[] x = new int[limit];
      for (int i = 0; i < limit; i++) {
         x[i] = rand.nextInt(10);
      }

      System.out.println(toString(x));

      mergeSort(x, 0, limit-1);

      System.out.println(toString(x));

   }

   public static void mergeSort(int[] x, int begin, int end){
      
      if (end-begin <= 0) return;

      int mid = (begin + end)/2;

      mergeSort(x, begin, mid);
      mergeSort(x, mid+1, end);

      merge(x, begin, mid, end);
   }
   
   public static void merge(int[] x, int begin, int mid, int end) {
   
      int[] z = new int[end-begin+1];

      int i = begin;
      int j = mid + 1;
      int cur = 0;

      while (i <= mid && j <= end) {
         if (x[i] <= x[j]) {
            z[cur] = x[i];
            i++;
            cur++;
         }
         else if (x[i] > x[j]) {
            z[cur] = x[j];
            j++;
            cur++;
         }
      }

      while (j <= end) {
         z[cur] = x[j];
         j++;
         cur++;
      }
      while (i <= mid) {
         z[cur] = x[i];
         i++;
         cur++;
      }

      int r = begin;
      for (int p = 0; p < z.length; p++) {
         x[r] = z[p];
         r++;
      }
   }
   

     


   public static String toString(Comparable[] x) {
      String s = "[";
      for (int i = 0; i < x.length; i++) {
         s = s.concat(" " + x[i] + ",");
      }
      s = s.concat("]");
      return s;
   }
   public static String toString(int[] x) {
      String s = "[";
      for (int i = 0; i < x.length; i++) {
         s = s.concat(" " + x[i] + ",");
      }
      s = s.concat("]");
      return s;
   }

}
