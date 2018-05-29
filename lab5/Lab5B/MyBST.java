import java.util.Iterator;
import java.lang.Math;

public class MyBST extends BST {


   public Comparable parent(Comparable x) {

      Node p = root;
      Node o = null;
   
      while (p != null) {
         if (x.equals(root.item)) return null;
         else if (x.compareTo(p.item) == 0) return o.item;
         else if (x.compareTo(p.item) < 0) {
            o = p;
            p = p.left;
         }
         else {
            o = p;
            p = p.right;
         }
      } 
      
      return null;
   }

   public Comparable predecessor(Comparable x) {
      
      Node p = root;
      Comparable lastRight = null;

      while (p != null) {
         if (x.compareTo(p.item) == 0) {
            if (p.left != null) {
               p = p.left;
               while (p.right != null) {
                  p = p.right;
               }
               return p.item;
            }
            else return lastRight;
         }
         else if (x.compareTo(p.item) < 0) {
            p = p.left;
         }
         else {
            lastRight = p.item;
            p = p.right;
         }
      }
      return null;
     
   }

   public Comparable successor(Comparable x){

      
      Node p = root;
      Comparable lastLeft = null;

      while (p != null) {
         if (x.compareTo(p.item) == 0) {
            if (p.right != null) {
               p = p.right;
               while (p.left != null) {
                  p = p.left;
               }
               return p.item;
            }
            else return lastLeft;
         }
         else if (x.compareTo(p.item) > 0) {
            p = p.right;
         }
         else {
            lastLeft = p.item;
            p = p.left;
         }
      }
      return null;
     
   }

   public int height() {
      return height(root);
   }

   private int height(Node p) {
      if (p==null) return 0;
      return 1 + Math.max(height(p.left), height(p.right));
   }

   private boolean isBalanced(Node p) {
      int lHeight = 0;
      int rHeight = 0;
      boolean balanced = true;

      if (p == null) return true;

      if (p.left != null) lHeight = height(p.left);
      if (p.right != null) rHeight = height(p.right);

      if (Math.abs(lHeight-rHeight) > 1 ) balanced = false;

      return (balanced && isBalanced(p.right) && isBalanced(p.left));
   }

   public boolean isBalanced() {
      return isBalanced(root);
   }

}
