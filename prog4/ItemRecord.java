public class ItemRecord implements Comparable <ItemRecord>{

   // Instance Variables
   public String item;
   public boolean found;

   public ItemRecord(String s) {
      item = s;
      found = false;
   }


   // Comparison is based on the ItemRecord's item
   // The items are String Objects which implement Comparable
   // Case does not matter
   public int compareTo(ItemRecord other) {
      if (this.item.compareToIgnoreCase(other.item) < 0) return -1;
      else if (this.item.compareToIgnoreCase(other.item) == 0) return 0;
      return 1;      
   }

   public String toString() {
      return item;
   }

}
