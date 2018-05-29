import java.io.*;
import java.util.Iterator;

public class LinkedList implements Cloneable
{

   private class Node
   {
      private Object item;
      private Node next;
      private Node prev;

      private Node(Object x)
      {
         item = x;
         next = null;
         prev = null;
      }
   }

   // Self explanatory instance variables

   private Node first;
   private Node last;
   private int cnt = 0;

   // Default Constructor

   public LinkedList()
   {
      first = null;
      last = null;
   }

   // Insert the given object at the beginning of the list.

   public void addFirst(Object item)
   {
      // Supply details as in the assignment description

      Node n = new Node(item);

      if (cnt <= 0) {
         first = n;
         last = n;
      }
      else {
         first.prev =  n;
         n.next = first;
         first = n;
      }
      cnt++;
   }

   // Insert the given object at the end of the list.

   public void addLast(Object item)
   {
      // Supply details as in the assignment description
      Node n = new Node(item);

      if (cnt <= 0) {
         first = n;
         last = n;
      }
      else {
         last.next = n;
         n.prev = last;
         last = n;
     }
      
      cnt++;
   }

   // Return the number of items in the list

   public int length()
   {
      // Supply details as in the assignment description
      return cnt;
   }

   // Determine if the list contains no items

   public boolean isEmpty()
   {
      if (first == null && last == null)
         return true;
      return false;
   }

   // (Virtually) remove all items from the list

   public void clear()
   {
      first = null;
      last = null;

      cnt = 0;
   }

   // Determine if the list contains the given item

// TWO WAYS FOR CONTAIN METHOD
/*   public boolean contains(Object i)
   {  
      boolean found = false;
      Node pointer = first;

      while (pointer != null) {
         if ((pointer.item).equals(i)) {
            found = true;
            pointer = null;
         }
         else {
         pointer = pointer.next;
         }
      }

      return found;
   }
*/

   public boolean contains(Object i) {
      for (Node n = first; n != null; n = n.next) {
         if (n.item.equals(i))
            return true;
      }
      return false;
   }

   // Remove first item on the list and return it
   
   public Object removeFirst()
   {
      if (cnt <= 0) 
         throw new Error("List is empty.");
      Object x = first.item;
      // Supply details as in the assignment description
      if (cnt == 1){
         clear();
      }
      else {
         first = first.next;
         first.prev = null;
         cnt--;
      }

      return x;
   }

   // Remove last item on the list and return it
   
   public Object removeLast()
   {
      if (cnt <= 0) 
         throw new Error("List is empty.");
      Object x = last.item;
      // Supply details as in the assignment description
      if (cnt == 1)
         clear();
      else {
         last = last.prev;
         last.next = null;
         cnt --;
      }
      return x;
   }
   
   // Determine if two LinkedLists are equal
      
   public boolean equals(Object o)
   {
      // Supply details as in the assignment description
      if (o == null)
         return false;
      if (!(o instanceof LinkedList))
         return false;

      LinkedList p = (LinkedList)o;

      if (p.length() != this.length())
         return false;

      Node x = this.first;
      Node y = p.first;

      while (x != null) {
         if (! (x.item.equals(y.item)) )
            return false;
         x = x.next;
         y = y.next;
      }

      return true;
   }

   public boolean remove(Object obj) {
      boolean found = false;
      Node pointer = first;
      
      while (pointer != null) {
         if (pointer.item.equals(obj)) {
            if (pointer == first){
               removeFirst();
            }
            else if (pointer == last) {
               removeLast();
            }
            else {
               Node x = pointer.prev;
               Node y = pointer.next;
               pointer = null;
               x.next = y;
               y.prev = x;
               cnt--;
            }
            found = true;
            pointer = null;
         }
         else pointer = pointer.next;
      }
      return found;
   }

   public Object clone(){
      LinkedList cloneList = new LinkedList();
      Node pointer = this.first;
      while (pointer != null) {
         cloneList.addLast(pointer.item);
         pointer = pointer.next;
      }
      return cloneList;
   }

   public Iterator iterator() {
      Iterator iter = new LLIterator();
      return iter;
   }

   public class LLIterator implements Iterator{

      Node pointer = null;

      public LLIterator () {
         pointer = first;
      }

      public boolean hasNext(){
         if (pointer == null)
            return false;
         return true;
      }

      public Object next(){
         if (pointer == null) 
            throw new Error("No more items in LinkedList");
         Object x = pointer.item;
         pointer = pointer.next;
         return x;
      }
 
      public void remove(){
         throw new UnsupportedOperationException();
      }


   }
   // **********************************************************************

   // FOR THE PURPOSES OF THIS LAB, YOU DON'T NEED TO SPEND TIME READING THE
   // REST OF THIS FILE.  HOWEVER, YOU SHOULD DO SO LATER ON YOUR OWN TIME.

   // The following specifies the maximum number of items in the list that
   // will be included in the toString method.
 
   private static int printLimit = 20;

   // This method allows the client to control the number of list items
   // that will be included in the toString method.

   public static void setPrintLimit(int limit)
   {
      if (limit >= 1)
         printLimit = limit;
   }

   // This method produces a string of the form {item1,item2,...} where item1 
   // is the first item in the list. The number of items included is the smaller 
   // of the number of items in the list and the value of printLimit. The "..."
   // is shown only if there are list items that were not included.  

   public String toString()
   {
      String answer = "{";
      int ctr = 0;
      for(Node curr=first;curr!=null;curr=curr.next)
      {
         answer = answer + curr.item;
         ++ctr;
         if (curr.next != null)
            if (ctr == printLimit)
               return answer + ",...}";
            else
               answer = answer + ",";
      }
      return answer + "}";
   }

   public static class Error extends RuntimeException
   {
      public Error(String message)
      {
         super(message);
      }
   }
}


