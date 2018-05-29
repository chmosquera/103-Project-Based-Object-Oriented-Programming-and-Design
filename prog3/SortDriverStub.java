import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
import java.awt.event.*;

public class SortDriver extends Applet {
   
   private int array[];  // array to be sorted
   private int limit = 1000;  // size of array to be sorted - you may have to make
                             // this bigger for faster sorts
   private int largestNum; // need to know for color scaling purposes in paint()

  // flag to tell paint() whether to paint a single location or the whole array
   private enum PaintType {ALL, RANGE, SINGLE};
   private PaintType doPaint = PaintType.ALL;

   private int index = -1;  // index of single array location to be painted
   private int leftRange = -1;  // left end of range to be drawn
   private int rightRange = -1;  // right end of range to be drawn
   
   //this listener object responds to button events
   private ButtonActionListener buttonListener;
   
   //button to start the sort
   private JButton startSort;
   
   // the picture of the sort will appear on this canvas
   private SortCanvas picture;
   private final int pictureWidth = 1001;  // size of the sort bar
   private final int pictureHeight = 50;

   // put buttons and canvas on this panel
   private JPanel sortPanel;

   // put radio buttons on this panel
   private JPanel radioPanel;

   // declarations for some more GUI elements
   private JLabel label; // a non-interactive text field
   private JRadioButton r1, r2, r3, r4; // radio buttons
   private ButtonGroup rButtons;
   private JTextField rText; // you can type text into this field
     
   public void init() {

      buttonListener = new ButtonActionListener();

      array = new int[limit];
      // load the array
      largestNum = array[0] = (int) (Math.random()*1000000.0);
      for (int i=1; i<limit; i++) {
          array[i] = (int) (Math.random()*1000000.0);
          // also keep track of the largest so that we can scale by it in paint()
          if (array[i] > largestNum) largestNum = array[i]; 
      }

      // set up the window
      sortPanel = new JPanel();
      sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.Y_AXIS));
      
      // first place the sort bar on top
      picture = new SortCanvas();
      sortPanel.add(picture);
      
      // now place a button
      startSort = new JButton("Start");
      // the listener is triggered when the button is clicked
      startSort.addActionListener(buttonListener);
      sortPanel.add(startSort);

      // here's some more code that you may find useful
      // they're all wide because of the BoxLayout:Y-AXIS

      label = new JLabel("Messages can go here");
      sortPanel.add(label);

      // text field with room for 20 characters
      rText = new JTextField("Type some text here", 20); 
      rText.addActionListener(buttonListener); 
      sortPanel.add(rText);

      // put these buttons in their own panel
      radioPanel = new JPanel();
      radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
      
      // radio buttons
      r1 = new JRadioButton("One", true); // true sets this button by default
      r1.addActionListener(buttonListener);
      radioPanel.add(r1);

      r2 = new JRadioButton("Two", false); 
      r2.addActionListener(buttonListener);
      radioPanel.add(r2);

      r3 = new JRadioButton("Three", false); 
      r3.addActionListener(buttonListener);
      radioPanel.add(r3);

      r4 = new JRadioButton("Four", false); 
      r4.addActionListener(buttonListener);
      radioPanel.add(r4);
   
      // radio buttons have to be added to a ButtonGroup to work
      rButtons = new ButtonGroup(); 
      rButtons.add(r1);
      rButtons.add(r2);
      rButtons.add(r3);
      rButtons.add(r4);

      // now add the radio panel to the sort panel
      sortPanel.add(radioPanel);

      // add the panel to the window
      add(sortPanel);
      
      picture.paint(picture.getGraphics());
   }
   
   // this object is triggered whenever a button is clicked
   private class ButtonActionListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
          
         // find out which button was clicked 
         Object source = event.getSource();
         
         // start sort button was clicked
         if (source == startSort) {
            // call the sort
            doBubblesort();
         }
         // called when user hits return in text field
         if (source == rText) {
            int size = Integer.parseInt(rText.getText());
         }
      }    
   }

   private void doBubblesort() {
      int temp;

      // this is just bubblesort
      for (int i=0; i<limit-1; i++) {
         for (int j=0; j<limit-1-i; j++) {
            if (array[j]>array[j+1]) {
               temp = array[j]; array[j] = array[j+1]; array[j+1] = temp;
              
               // draws the bars between j and j+1
               doPaint = PaintType.RANGE;
               leftRange = j;
               rightRange = j+1;
               picture.paint(picture.getGraphics());
               
               // here's another way to do the same thing
               // redraw only locations j and j+1
               /*doPaint = PaintType.SINGLE;  
               index = j; 
               picture.paint(picture.getGraphics());
               index = j+1; 
               picture.paint(picture.getGraphics());*/
               
               // here's yet another way to do it, but it takes too much time
               /*doPaint = PaintType.ALL;
               picture.paint(picture.getGraphics()); */
            }
         }
      }
   }      
             
   class SortCanvas extends Canvas {
      // this class paints the sort bar 
       
      SortCanvas() {
         setSize(pictureWidth, pictureHeight);
         setBackground(Color.white);
      }
       
      public void paint(Graphics g) {
         
         if (doPaint == PaintType.ALL) {
            // paint whole array - this takes time so it shouldn't be done too frequently
            setBackground(Color.white);
            g.setColor(Color.white);
            g.fillRect(0, 0, pictureWidth, pictureHeight);
            
            for (int i=0; i<limit; i++) {
               // the larger the number, the brighter green it is
               // green is between 0.0 and 1.0
               // divide by the largest number to get a value between 0 and 1
               float green = (float)(array[i]/(float)largestNum);

               // clamp if necessary - it shouldn't be
               if (green<0f) green = 0f;
               if (green>1f) green = 1f;

               g.setColor(new Color(0.0f, green, 0.0f));
               // array location 0 is painted at left; 
               //   array location limit-1 is painted to right
               //this is a single vertical line in the bar
               g.drawLine((int)(i*pictureWidth/limit), 0, 
                          (int)(i*pictureWidth/limit), pictureHeight);
            }
         }
         
         else if (doPaint == PaintType.RANGE) {
            for (int i=leftRange; i<=rightRange; i++) {
               float green = (float)(array[i]/(float)largestNum);
               if (green<0f) green = 0f;
               if (green>1f) green = 1f;

               g.setColor(new Color(0.0f, green, 0.0f));
               g.drawLine((int)(i*pictureWidth/limit), 0, 
                          (int)(i*pictureWidth/limit), pictureHeight);
            }
         }
         else {   // just paint one location on the bar
            float green = (float)(array[index]/(float)largestNum);
            if (green<0f) green = 0f;
            if (green>1f) green = 1f;
            g.setColor(new Color(0.0f, green, 0.0f));
            g.drawLine((int)(index*pictureWidth/limit), 0, 
                       (int)(index*pictureWidth/limit), pictureHeight);
         }   
      }
   }
}