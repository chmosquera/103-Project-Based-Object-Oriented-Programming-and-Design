public class Testing {

   public static void main(String[] args) {

   //------ TESTS IF BST PUTS ITEMS INORDER ------
      String[] unsorted = {"Opal", "Coffee", "Tea", "Jacket", "Television", "Playstation",
                           "RollerCoaster", "Headphones"};
      RecordBST mybst = new RecordBST();

      // insert items into BST
      for (int i = 0; i < unsorted.length; i++) {
         mybst.insert(unsorted[i]);
      }

      mybst.printBST();

      // testing searchFlag()
      boolean found;
      found = mybst.searchFlag("playstation");
      System.out.println("Testing search() - Find 'playstation': " + found);
      found = mybst.searchFlag("Alligator");
      System.out.println("Testing search() - Find 'Alligator': " + found);
      
      // Swap letters
      for (int j = 0; j < unsorted.length; j++) {
         String curWord = unsorted[j];
         for (int i = 0; i < curWord.length()-1; i++) {
            String newWord;

            // If swapping first two letters
            if (i == 0) {
               newWord = String.valueOf(curWord.charAt(i+1)) + String.valueOf(curWord.charAt(i))
                          + curWord.substring(i+2, curWord.length());
            }
            // If swapping last two letters
            else if (i == curWord.length()-1) {
               newWord = curWord.substring(0,i) + curWord.charAt(i+1) + 
                         curWord.charAt(i);
            }
            else {
               newWord = curWord.substring(0, i) + curWord.charAt(i+1) + 
                         curWord.charAt(i) + curWord.substring(i+2, curWord.length());
            }
            System.out.println("Swapped letters: " + curWord.charAt(i) + " and " + curWord.charAt(i+1) +
                               " -> " + newWord);
         }
      }
   }

}
