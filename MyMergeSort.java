import java.util.Random;


public class MyMergeSort {

   public static void main(String[] args) {

      Random rand = new Random();      

      int limit = 10000;
      int[] x = new int[limit];
      for (int i = 0; i < limit; i++) {
         x[i] = rand.nextInt(50);
      }

      System.out.println(toString(x));

      int[] g = mergeSort(x);

      System.out.println(toString(g));

   }

   public static int[] mergeSort(int[] a) {

      if (a.length <= 1) return a;

      int mid = a.length/2;

      int[] x = new int[mid];
      int[] y = new int[a.length - mid];

      int idx = -1;
      for (int i = 0; i < x.length; i++) {
         idx++;
         x[i] = a[idx];
      }
      for (int j = 0; j < y.length; j++) {
         idx++;
         y[j] = a[idx];
      }

      int[] l1 = mergeSort(x);
      int[] l2 = mergeSort(y);
      return merge(l1,l2);

      
   }

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

/*
   private static Comparable[] merge(Comparable[] x,Comparable[] y)
   {
      Comparable[] z = new Comparable[x.length + y.length];
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
         else if (x[i].compareTo(y[j]) <= 0) {
            z[curr] = x[i];
            i++;
            curr++;
         }
         else if (y[j].compareTo(x[i]) <= 0) {
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
*/
/*
   private static Comparable[] merge(Comparable A[], int begin, int mid, int end)
   {
      Comparable[] z = new Comparable[end - begin + 1];

      // points to idx of xlist and ylist and zlist
      int i = begin;
      int j = mid+1;
      int curr = 0;
System.out.println(begin);

  
      boolean oneSorted = false;

      // sorts x and y list until one list is finished sorting
      while (oneSorted == false) {
         // if one of the lists has been completely sorted, stop comparing the lists
         if ((i == mid+1) || (j == end+1)) {
            oneSorted = true;
         }
         else if (A[i].compareTo(A[j]) <= 0) {
            z[curr] = A[i];
            i++;
            curr++;
         }
         else if (A[i].compareTo(A[j]) >= 0){
            z[curr] = A[j];
            j++;
            curr++;
         }
      }

      // if one list is sorted before the other, assign the rest of remaining list to z-list
      if (i == mid+1) {
         while (j != end+1) {
            z[curr] = A[j];
            j++;
            curr++;
         }
      }
      else if (j == end+1) {
         while (i != mid+1) {
            z[curr] = A[i];
            i++;
            curr++;
         }
      }

System.out.println("z: " + toString(z));
System.out.println(begin + ":" + mid + "   " + (mid+1) + ":" + end);
      return z;
   }   
*/

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
