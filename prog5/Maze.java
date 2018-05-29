// Name: Chanelle Mosquera

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Iterator;
import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class Maze extends Applet {
   
   // array that holds the maze info
   private int[][] maze;

   // graph containing maze info
   private Graph mazeGraph;
   private int[] shortestPath;
   private boolean pathExists = false;
   //private int pathSteps;

   //number of rows and columns in the maze
   private int rows, cols;

   // initial size of maze - if bigger may go off window
   private final int MAXROWS = 20;
   private final int MAXCOLS = 30;
   
   // size of each block in pixels
   private final int blkSize = 20;
   
   //inner class that displays the maze
   private MazeCanvas mazeField;

   // everything is put on this panel   
   private JPanel mazePanel;

   // label, textfield, string, and load button for the file
   private JLabel fileLabel;
   private JTextField fileText;
   String fileName;
   private JButton fileButton;
   
   // restart button
   private JButton restartButton;
   
   // pop up dialog 
   private JFrame frame = new JFrame();
   private JOptionPane popup = new JOptionPane();

   // deals with advancing through the maze
   private JButton goButton;
   private boolean mazeStarted = false; // whether maze game has begun (go has been pressed for the first time)
   private boolean blocking = false; // indicates which part of the GO cycle is next
   private boolean setupStart = true; // if true, mouse click will set Start location, false - end location
   private int start = -1; //vertex ID of start/end vertices
   private int end = -1;
   private final int NEAR = 8; // how many steps until goal is near and no more walls should generate
   
   //this listener object responds to button events
   private ButtonActionListener buttonListener;
   private MouseEventListener mouseListener;


   // this method sets up the canvas and starts it off
   public void init() {
      System.out.println("Maze started"); // goes to console 
      
      mouseListener = new MouseEventListener();
      buttonListener = new ButtonActionListener();
               
      // the mazePanel is the panel that contains the maze interfaces, including
      // the buttons and output display
      mazePanel = new JPanel();
      // Y_AXIS layout places components from top to bottom, in order of adding
      mazePanel.setLayout(new BoxLayout(mazePanel, BoxLayout.Y_AXIS));
      
      // components for loading the filename
      fileLabel = new JLabel("File name:");
      mazePanel.add(fileLabel);
      fileText = new JTextField("", 20);
      mazePanel.add(fileText);
      fileButton = new JButton("Load File");
      mazePanel.add(fileButton);
      fileButton.addActionListener(buttonListener);
      
      // components for advancincing through maze
      goButton = new JButton("Go");
      mazePanel.add(goButton);
      goButton.addActionListener(buttonListener);

      // this is where the maze is drawn
      // if you add more to this layout after the mazeField, 
      //   it may be below the bottom of the window, depending on window size
      mazeField = new MazeCanvas();
      mazePanel.add(mazeField);
      mazeField.addMouseListener(mouseListener);

      // now add the maze panel to the applet
      add(mazePanel);

   }
   
   // this object is triggered whenever a button is clicked
   private class ButtonActionListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
          
         // find out which button was clicked 
         Object source = event.getSource();
         
         if (source == fileButton)
         {
            fileName = fileText.getText();
            makeMaze(fileName);
            
         }
         else if (source == goButton)
         {
            if (!mazeStarted) { // Start the game and find the shortest path
               if (start != -1 && end != -1) {
                  pathExists = mazeGraph.findShortestPath(start,end);
                  shortestPath = mazeGraph.shortestPath(); 
                  mazeStarted = true;       
                  blocking = true;   
               }
            }
            else { // Continue on by blocking, finding new paths, and advancing through maze
            
               if (pathExists) { 
                  if (!blocking) {     // FIRST find and display shortest path
                    advancePath();                  
                     blocking = true;
                  }
                  else if (blocking) { // SECOND display the block in path
                     blockPath();
                     blocking = false;
                  }                  
                  mazeField.paint(mazeField.getGraphics());
               }                         
               if (!pathExists) {
                  System.out.println("Game Over. You lost.");
                  popup.showMessageDialog(frame, "Game over", "No path exists.", 
                                          JOptionPane.PLAIN_MESSAGE);
               }            
               if (start == end) {
                  System.out.println("Game Over. You won.");               
                  popup.showMessageDialog(frame, "You won!", "No path exists.",
                                          JOptionPane.PLAIN_MESSAGE);               
               }                                          
            }
            mazeField.paint(mazeField.getGraphics());
         }
      } 
   }

   private class MouseEventListener implements MouseListener
   {
      public void mouseClicked(MouseEvent e)
      {
         try{
            // location on the mazeCanvas where mouse was clicked
            // upper-left is (0,0)
            int startX = e.getX();
            int startY = e.getY();

            // the x and y location of the tile clicked on
            int tileRow = startY/blkSize;
            int tileCol = startX/blkSize;

            // The mouse click alternates between setting up the start tile and setting up the end tile
            // Can only create start/end tile if mouse click is on the maze (not just the window)
            //    and if the tile clicked on is NOT a wall
            if ((tileCol <= cols) && (tileRow <= rows) && (maze[tileRow][tileCol] != -1)) {
               if (setupStart) {
                  start = mazeGraph.vertexID(tileRow, tileCol);
                  setupStart = false;
               }
               else {
                  end = mazeGraph.vertexID(tileRow, tileCol);               
                  setupStart = true;
               }
            }
            mazeField.paint(mazeField.getGraphics());
         }
         catch (Exception ex) {}
         
     }
      
      public void mousePressed(MouseEvent e) { }
      public void mouseReleased(MouseEvent e) { }
      public void mouseEntered(MouseEvent e) { }
      public void mouseExited(MouseEvent e) { }
      
   }   
   
   public void blockPath() {
      Random rand = new Random();
            
      // if end goal is NOT near, create a wall along remainder of path
      if (shortestPath.length > NEAR) {
         int x = rand.nextInt(shortestPath.length - 4);
         int vertex = shortestPath[1 + x];

         maze[mazeGraph.row(vertex)][mazeGraph.col(vertex)] = -1;         
         
         // recreate Graph object with new updated maze
         mazeGraph = new Graph(maze);
         mazeGraph.insertEdges();

         // until new shortestPath is found, must notify shortestPath that this vertex is now a wall
         shortestPath[x+1] = -1;
      }            
   }
   
   public void advancePath() {
      // advance one step through the path
      start = shortestPath[shortestPath.length - 2];  
      
      // recreate Graph object with new updated maze
      mazeGraph = new Graph(maze);
      mazeGraph.insertEdges();                  
      
      // find new path             
      pathExists = mazeGraph.findShortestPath(start,end);
      shortestPath = mazeGraph.shortestPath();
   }

   public boolean makeMaze(String fileName) 
   {
      try
      {
         Scanner scanner = new Scanner(new File(fileName));
         rows = scanner.nextInt();
         cols = scanner.nextInt();
         maze = new int[rows][cols];
         //fill out maze matrix
         for(int i=0; i<rows; i++)
         {
            for(int j=0; j<cols; j++)
            {
               maze[i][j] = scanner.nextInt();
            }
         }
         // create graph and edges
         mazeGraph = new Graph(maze);
         mazeGraph.insertEdges();
   
   
         // now draw it
         mazeField.paint(mazeField.getGraphics());
         return true;
      }
      catch(Exception e)
      {
         return false;
      }
   } 
        
   class MazeCanvas extends Canvas {
      // this class paints the output window 
       
     // the constructor sets it up
      MazeCanvas() {
         rows = MAXROWS;
         cols = MAXCOLS;
         maze = new int[MAXROWS][MAXCOLS];
         setSize(cols*blkSize, rows*blkSize);
         setBackground(Color.white);
      }
       
      public void paint(Graphics g)
      {
         g.setColor(Color.white);
         g.fillRect(0, 0, cols*blkSize, rows*blkSize);
         
         for (int i=0; i<rows; i++)
         { 
            for (int j=0; j<cols; j++)
            {
               if (maze[i][j] == -1)
               {
                  // location is a wall
                  g.setColor(Color.black);
               }
               else
               {
                  // location is clear
                  g.setColor(Color.white);
               }
               // draw the location
               g.fillRect(j*blkSize, i*blkSize, blkSize, blkSize);
            }
         }
         
         // draw tiles in path - if they exist
         if (shortestPath != null) {
            g.setColor(Color.yellow);
            for (int i = 0; i < shortestPath.length; i++) {
               if (shortestPath[i] != -1) {
                  g.fillRect(mazeGraph.col(shortestPath[i])*blkSize, mazeGraph.row(shortestPath[i])*
                             blkSize, blkSize, blkSize);
              }
            }
         }         
         
         // draw the start and end tiles - only if they exist
         if (start != -1) {
            g.setColor(Color.green);
            g.fillRect(mazeGraph.col(start)*blkSize, mazeGraph.row(start)*blkSize, blkSize, blkSize);
         }
         if (end != -1) {
            g.setColor(Color.red);
            g.fillRect(mazeGraph.col(end)*blkSize, mazeGraph.row(end)*blkSize, blkSize, blkSize);            
         }

      }
   }
}
