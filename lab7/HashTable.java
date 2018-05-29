public class HashTable {

   private int capacity;
   private int nonZeroBucketCount = 0;
   private int numElements = 0;
   private LinkedList[] table;

   public HashTable(int capacity) {
   
      this.capacity = capacity;
      table = new LinkedList[capacity];
      
   }
   
   public void add(Object element) {
      int index = Math.abs(element.hashCode() % capacity);
      
      if (table[index] == null) { // create linked list
         table[index] = new LinkedList();
      }
      
      table[index].addFirst(element);
      
      numElements++;
   }
   
   public boolean contains(Object element) {
      int index = Math.abs(element.hashCode() % capacity);
      
      if (table[index] != null) {
         return table[index].contains(element);
      }      
       
      return false;     
   }
   
   public int numElements() {
      return numElements;
   }
   
   public int capacity() { return capacity; }
   
   public int maxBucketCount() { 
      int max = 0;
      for (int i = 0; i < capacity; i++) {
         if ((table[i] != null) && (table[i].length() > max)) {
            max = table[i].length();
         }
      }
      
      return max;
   }
   
   public int nonZeroBucketCount() {
      int count = 0;
      for (int i = 0; i < capacity; i ++) {
         if (table[i] == null) {
            count++;
         }
      }
      return count;
   }
   
   public float avgNonZeroBucketCount() {
      int numBuckets = 0;
      int totalLengthOfBuckets = 0;
      float avg;
      
      for (int i = 0; i < capacity; i++) {
         if (table[i] != null) {
            numBuckets++;
            totalLengthOfBuckets += table[i].length();
         }
      }
      avg = totalLengthOfBuckets/numBuckets;
      
      return avg;
   
   }
   
   public static class Error extends RuntimeException {
   
      public Error(String msg){
         System.out.println("Error occured.");
      }
   }
   
   

}
