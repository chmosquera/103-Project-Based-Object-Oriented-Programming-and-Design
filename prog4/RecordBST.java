public class RecordBST {

   // Instance Variables
   private Node root;
   
   // Constructor
   public RecordBST() {
      root = null;

   }

   // Adds a new element to the BST
   public void insert(String data) {

      boolean inserted = false; // flag indicates whether item has been inserted yet
      Node p; // points at current node
      Node n; // node to be inserted

      // Create ItemRecord Object with item and create new Node
      n = new Node( new ItemRecord(data) );

      // Insert Node into BST
      // Check if first item to be inserted and insert accordingly
      if (root == null) {
         root = n;
      }
      else {
         p = root;

         try {
            while (!inserted) {
               if (n.item.compareTo(p.item) == 0) {
                  throw new Exception();
               }
               if (n.item.compareTo(p.item) < 0) { 
                  if (p.left == null) { // left child is null -- end of tree is reached
                     p.left = n;
                     inserted = true;
                  }
                  else {
                     p = p.left; // traverse left
                  }
               }
               else if (n.item.compareTo(p.item) > 0) { 
                  if (p.right == null) { // right child is null -- end of tree is reached
                     p.right = n;
                     inserted = true;
                  }
                  else {
                     p = p.right; // traverse right
                  }
               }
            }
         }
         catch (Exception e) {
         };
      }

   }

   // prints bst in order
   // Note: did not use this method in CheckMessage.java but was used for testing purposes
   public void printBST() {
      printItem(root);
   }

   private void printItem(Node n){ // Each child of a tree is a root of its subtree.
       if (n.left != null){
           printItem(n.left);
       }
       System.out.println(n.item); // Prints using ItemRecord's toString()
       if (n.right != null){
           printItem(n.right);
       }
   }


   // prints bst in order -- only found flagged items.
   public void printFlaggedBST() {
      printFlaggedItem(root);
   }
 
   private void printFlaggedItem(Node n){
       if (n.left != null){
           printFlaggedItem(n.left);
       }
       if (n.item.found == true) {
          System.out.println(n.item); 
       }
       if (n.right != null){
           printFlaggedItem(n.right);
       }
   }   

   // finds String object (data) in the BST
   public boolean search(String data) {
      Node p = root;
      ItemRecord ir = new ItemRecord(data);

      while (p != null) {
         if (ir.compareTo(p.item) == 0) {
            return true;
         }
         else if (ir.compareTo(p.item) < 0) p = p.left;
         else if (ir.compareTo(p.item) > 0) p = p.right;
      }
      return false;
   }

   // finds String object (data) in the BST and may trigger IR's flag
   public boolean searchFlag(String data) {
      Node p = root;
      ItemRecord ir = new ItemRecord(data);

      while (p != null) {
         if (ir.compareTo(p.item) == 0) {
            p.item.found = true;
            return true;
         }
         else if (ir.compareTo(p.item) < 0) p = p.left;
         else if (ir.compareTo(p.item) > 0) p = p.right;
      }
      return false;
   }

   private class Node {
      
      Node left, right;
      ItemRecord item;

      public Node(ItemRecord x) {
         item = x;
         left = null;
         right = null;
      }      
   }


}
