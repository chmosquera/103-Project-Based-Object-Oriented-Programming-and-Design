// Name: Chanelle Mosquera
// Class: CPE 103-14

import java.util.Scanner;
import java.io.File;

public class CheckMessage {

   public static void main(String[] args) {

      /******************************************************/  
      // Variables
      /******************************************************/
      // arguments passed through command-line
      File msgFile, dictFile;
      File[] alarmFiles;

      // BSTs
      RecordBST dictBST;
      RecordBST alarmBST;

      // Scanner used to parse files
      Scanner scan;

      // String points to current word in message file
      String curWord;



      /******************************************************/
      // Store files passed through arguments into variables
      /******************************************************/
      System.out.println("Analyzing the file message.txt... \n");

      msgFile = new File(args[0]);
      dictFile = new File(args[1]);
      alarmFiles = new File[args.length - 2];

      // Store alarm files into array (since we don't know how many there are)
      int j = 0;
      for (int i = 2; i < args.length; i++) {
         alarmFiles[j] = new File(args[i]);
         j++;
      }

      /******************************************************/
      // Create dictionary (spell-check) BST
      /******************************************************/

      // assign BST
      dictBST = new RecordBST();

      // Can only create scanner for file if file is valid
      // Once file is scanned, parse through file and add words into BST
      try {
         scan = new Scanner(dictFile);

         // parse through file and insert each word into the BST
         while(scan.hasNext()) {
            dictBST.insert(scan.next());
         }
      
         // close scanner when done parsing file
         scan.close();
      
      }
      catch (Exception e) {
         System.out.println("INVALID FILE: Please give a valid spell-check file.");
      }


      /******************************************************/
      // Create alarm BST
      /******************************************************/

      // assign BST
      alarmBST = new RecordBST();
      String word;
      try {
         
         // Creating scanners must be in loop so every alarm file gets scanned
         for (int i = 0; i < alarmFiles.length; i++) {
            scan = new Scanner(alarmFiles[i]);
        
            // parse through file and insert each word into the BST
            while(scan.hasNext()) {
               word = scan.next();
               alarmBST.insert(word);
            }
            // close scanner when done parsing file
            scan.close();

         } 
      }
      catch (Exception e) {
         System.out.println("INVALID FILE: Please give valid alarm file(s).");
      }  
      

      /******************************************************/
      // Scan message file against the spell-check BST
      /******************************************************/
      System.out.println("Checking against the file " + dictFile.toString() + ":");

      try {
         boolean found = false;
         scan = new Scanner(msgFile);

         // Print out words not found in spell-check BST
         System.out.println("these words were not found:");

         // for each word in the msgFile, check it against the entire spell-check BST 
         while (scan.hasNext()) {
            curWord = scan.next();
            found = dictBST.search(curWord); // Don't need to use IR's flags for spell-check BST

            // if the current word is not found in the dictionary, complete the following actions
            // otherwise, move on with the loop and look at the next word

            // if it has an 's' at the end, look for it in the dictionary without the 's'
            if (!found && (curWord.charAt(curWord.length() - 1) == 's')) {
               String newWord = curWord.substring(0,curWord.length()-1);
               found = dictBST.search(newWord);       
            }     
            // if still not found, check if it has an 'es' at the end
            // then look for it in the dictionary without the 'es'
            if (!found && (curWord.substring(curWord.length()-2, curWord.length()).equals("es"))) {
               String newWord = curWord.substring(0,curWord.length()-2);
               found = dictBST.search(newWord); 
            }
            // if still not found, check if it has an 'ing' at the end
            // then look for it in the dictionary without the 'ing'
            if (!found && (curWord.substring(curWord.length()-3, curWord.length()).equals("ing"))) {
               String newWord = curWord.substring(0,curWord.length()-3);
               found = dictBST.search(newWord); 

               // if its still not found, try adding an "e" at the end to create base word
               // some words drop the 'e' and add "ing". (Grammar rule)
               if (!found) {
                  newWord = newWord.concat("e");
                  found = dictBST.search(newWord); 
               }

            }
            // if still not found, check for a mispelling
            // then look for it in the dictionary
            if (!found) {
               System.out.print(curWord + "           possible spellings: ");
               
               // Swap adjacent characters starting from left to right then check against BST
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

                  // Check new word against BST
                  // if the new word is found, then it is a possible spelling
                  found = dictBST.search(newWord);
                  if (found) {
                     System.out.print(newWord + " ");
                  }
               }
               System.out.println(""); // new line

            }                
         } 
         System.out.println("");    

         scan.close(); 
      }
      catch (Exception e) {
         System.out.println("INVALID FILE: Please give valid message file.");
      }

      /******************************************************/
      // Scan message file against the alarm BST
      /******************************************************/
      System.out.print("Checking against the alarm files "); // System printing more complicated than usual
      for (int i = 0; i < alarmFiles.length; i++) {
         System.out.print(alarmFiles[i].toString());
         if (i == alarmFiles.length-1) System.out.println(":");
         else System.out.print(", ");
      }
      System.out.println("these words were found:");

      try {
         scan = new Scanner(msgFile);

         // for each word in the msgFile, check if it is in alarm BST
         while (scan.hasNext()) {
            curWord = scan.next();
            alarmBST.searchFlag(curWord); // Must use IR's flags for alarm BST
         }

         // print all words found against alarm BST
         alarmBST.printFlaggedBST();
         System.out.println("");
      
         scan.close();
      }
      catch (Exception e) {
         System.out.println("INVALID FILE: Please give valid message file.");
      }

   }


   

}

