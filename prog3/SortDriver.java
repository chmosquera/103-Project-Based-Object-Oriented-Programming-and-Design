import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Font;

public class SortDriver extends Applet {
   
   private int array[];  // array to be sorted
   private int limit = 1000;  // size of array to be sorted - you may have to make
                             // this bigger for faster sorts
   private int largestNum; // need to know for color scaling purposes in paint()

  // flag to tell paint() whether to paint a single location or the whole array
   private enum PaintType {ALL, RANGE, SINGLE};
   private PaintType doPaint = PaintType.ALL;

   // tells paint() what color to paint in
   private enum PaintColor {GREEN, BLUE, RED, PINK};
   private PaintColor color = PaintColor.GREEN;

   private int index = -1;  // index of single array location to be painted
   private int leftRange = -1;  // left end of range to be drawn
   private int rightRange = -1;  // right end of range to be drawn
   
   //this listener object responds to button events
   private ButtonActionListener buttonListener;
   
   //button to start the sort
   private JButton startSort;
   private JButton updateOrder;
   
   // the picture of the sort will appear on this canvas
   private SortCanvas picture;
   private final int pictureWidth = 1001;  // size of the sort bar
   private final int pictureHeight = 100;

   // put buttons and canvas on this panel
   private JPanel sortPanel;

   // put radio buttons on this panel
   private JPanel radioPanel, orderPanel, colorPanel;

   // Set fonts
   private Font font;

   // declarations for some more GUI elements
   private JLabel label; // a non-interactive text field
   private JRadioButton r1, r2, r3, r4; // radio buttons
   private JRadioButton o1, o2, o3; 
   private JRadioButton col1, col2, col3, col4;
   private ButtonGroup rButtons, oButtons, colButtons;
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

      // Header Label at top of window
      sortPanel.add(Box.createVerticalStrut(20)); // creates empty space in panel
      label = new JLabel("On today's Sort menu...");
      sortPanel.add(label);
      label.setAlignmentX(CENTER_ALIGNMENT); // sets up visual aspects of label
      font = new Font("Serif", Font.BOLD, 36);
      label.setFont(font);
      sortPanel.add(Box.createVerticalStrut(10));     
      
      // first place the sort bar on top
      picture = new SortCanvas();
      sortPanel.add(picture);
      sortPanel.add(Box.createVerticalStrut(10)); 
      
      // now place a button
      startSort = new JButton("Sort!");
      // the listener is triggered when the button is clicked
      startSort.addActionListener(buttonListener);
      startSort.setAlignmentX(CENTER_ALIGNMENT);
      sortPanel.add(startSort);
      sortPanel.add(Box.createVerticalStrut(10)); 

      updateOrder = new JButton("Update Order");
      updateOrder.addActionListener(buttonListener);
      updateOrder.setAlignmentX(CENTER_ALIGNMENT);
      sortPanel.add(updateOrder);
      sortPanel.add(Box.createVerticalStrut(10)); 

      // text field with room for 20 characters
      rText = new JTextField("How many slices would you like?", 20); 
      rText.addActionListener(buttonListener); 
      sortPanel.add(rText);
      sortPanel.add(Box.createVerticalStrut(10)); 

      // put these buttons in their own panel
      radioPanel = new JPanel();
      radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));

      orderPanel = new JPanel();
      orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.X_AXIS));

      colorPanel = new JPanel();
      colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
      
      // radio buttons
      r1 = new JRadioButton("Bubble", true); // true sets this button by default
      r1.addActionListener(buttonListener);  // sort method
      radioPanel.add(r1);

      r2 = new JRadioButton("Insertion", false); 
      r2.addActionListener(buttonListener);
      radioPanel.add(r2);

      r3 = new JRadioButton("Quick", false); 
      r3.addActionListener(buttonListener);
      radioPanel.add(r3);

      r4 = new JRadioButton("Merge", false); 
      r4.addActionListener(buttonListener);
      radioPanel.add(r4);   
      
      o1 = new JRadioButton("increasing", false); // initial list ordering
      o1.addActionListener(buttonListener);
      orderPanel.add(o1);

      o2 = new JRadioButton("decreasing", false);
      o2.addActionListener(buttonListener);
      orderPanel.add(o2);

      o3 = new JRadioButton("random", true);
      o3.addActionListener(buttonListener);
      orderPanel.add(o3);

      col1 = new JRadioButton("green", true);
      col1.addActionListener(buttonListener);
      colorPanel.add(col1);
 
      col2 = new JRadioButton("blue", false);
      col2.addActionListener(buttonListener);
      colorPanel.add(col2);

      col3 = new JRadioButton("red", true);
      col3.addActionListener(buttonListener);
      colorPanel.add(col3);

      col4 = new JRadioButton("pink", true);
      col4.addActionListener(buttonListener);
      colorPanel.add(col4);

      // radio buttons have to be added to a ButtonGroup to work
      rButtons = new ButtonGroup(); 
      rButtons.add(r1);
      rButtons.add(r2);
      rButtons.add(r3);
      rButtons.add(r4);

      oButtons = new ButtonGroup();
      oButtons.add(o1);
      oButtons.add(o2);
      oButtons.add(o3);

      colButtons = new ButtonGroup();
      colButtons.add(col1);
      colButtons.add(col2);
      colButtons.add(col3);
      colButtons.add(col4);

      // now add the radio panels (and their labels) to the sort panel
      font = new Font("Serif", Font.BOLD, 20); // create new font for following labels
 
      label = new JLabel("Pick your sort"); // label to go above button group
      label.setAlignmentX(CENTER_ALIGNMENT);
      label.setFont(font);
      sortPanel.add(label); // add labels and panels onto the main panel
      sortPanel.add(radioPanel);
      sortPanel.add(Box.createVerticalStrut(10)); 

      label = new JLabel("Pick your color");
      label.setAlignmentX(CENTER_ALIGNMENT);
      label.setFont(font);
      sortPanel.add(label);
      sortPanel.add(colorPanel);
      sortPanel.add(Box.createVerticalStrut(10)); 

      label = new JLabel("How would you like your sort initially ordered?");
      label.setAlignmentX(CENTER_ALIGNMENT);
      label.setFont(font);
      sortPanel.add(label);
      sortPanel.add(orderPanel);
      sortPanel.add(Box.createVerticalStrut(10));

      // add labels at foot of window
      font = new Font("Serif", Font.PLAIN, 12);

      label = new JLabel("\n * Make sure to update order after any changes you've made");
      label.setAlignmentX(CENTER_ALIGNMENT);
      label.setFont(font);
      sortPanel.add(label);
      sortPanel.add(Box.createVerticalStrut(20)); 


      // add the panel to the window
      add(sortPanel);
      
      picture.paint(picture.getGraphics());
   }
   
   // this object is triggered whenever a button is clicked
   private class ButtonActionListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {

         // set paint type
         doPaint = PaintType.ALL;
          
         // find out which button was clicked 
         Object source = event.getSource();
         
         // start sort button was clicked
         if (source == startSort) {
            // call the sort
            if (r1.isSelected())
               doBubblesort();
            else if (r2.isSelected())
               doInsertionsort(array, 0, limit-1);
            else if (r3.isSelected())
               doQuicksort();
            else if (r4.isSelected())
               doMergesort();
         }
         // called when user hits return in text field
         else if (source == rText) {
            limit = Integer.parseInt(rText.getText());
            if (o1.isSelected()) incrOrder();            
            else if (o2.isSelected()) decrOrder();
            else if (o3.isSelected()) randOrder();
            picture.paint(picture.getGraphics());

         }
         else if (source == updateOrder) {
            // call the array order
            if (o1.isSelected()) incrOrder();            
            else if (o2.isSelected()) decrOrder();
            else if (o3.isSelected()) randOrder();

            picture.paint(picture.getGraphics());
         }

        else if (source == col1) color = PaintColor.GREEN;
        else if (source == col2) color = PaintColor.BLUE;
        else if (source == col3) color = PaintColor.RED;
        else if (source == col4) color = PaintColor.PINK;

      }    
   }

   /* ------------------------------------------------- */
   /*  ----- LIST ORDERING ----- */
   // these methods get called on when changing order of list

   private void incrOrder() {

      array = new int[limit];

      for (int i=0; i<limit; i++) {
          array[i] = i;
      }
      largestNum = array[limit-1];
   }

   private void decrOrder() {

      array = new int[limit];
      largestNum = limit;

      int x = limit;
      for (int i=0; i<limit; i++) {
          array[i] = x;
          x--;
      }
      largestNum = array[0];

   }

   private void randOrder() {
      Random rand = new Random();

      array = new int[limit];

      largestNum = array[0] = rand.nextInt(1000000);
      for (int i=1; i<limit; i++) {
          array[i] = rand.nextInt(10000000);
          if (array[i] > largestNum) largestNum = array[i]; 
      }
   }

   /* ------------------------------------------------- */
   /*  ----- SORT METHODS ----- */
   // These methods are called on when choosing sort method

   private void doBubblesort() {
      int temp;

      for (int i=0; i<limit-1; i++) {
         for (int j=0; j<limit-1-i; j++) {
            if (array[j]>array[j+1]) {
               temp = array[j]; array[j] = array[j+1]; array[j+1] = temp;
              
               // draws the bars between j and j+1
               doPaint = PaintType.RANGE;
               leftRange = j;
               rightRange = j+1;
               picture.paint(picture.getGraphics());

            }
         }
      }
   }   

   private void doInsertionsort(int[] data,int first,int last)
   {
      for(int i=first+1;i<=last;++i)
      {
         int target = binarySearch(data,data[i],first,i-1);
         if (target != i)
         {
            int temp = data[i];
            for(int j=i;j>target;--j) {
               data[j] = data[j-1];

               // draw the bars between j and j-1
               doPaint = PaintType.RANGE;
               leftRange = j-1;
               rightRange = j;
               picture.paint(picture.getGraphics()); 
            }
            data[target] = temp;
         }
      }
   }

   private void doQuicksort()  
   {
      // Initiate a stack and TOS
      int[][] s = new int[array.length][2];
      int TOS = -1;

      // Push data[] into stack
      TOS++;
      s[TOS][0] = 0;
      s[TOS][1] = array.length - 1;
    
      // Loop while stack is not empty
      while (TOS >= 0) {

         // Pop problem 'p' from stack 's'
         int begin = s[TOS][0];
         int end = s[TOS][1];
         TOS--;

         // Compute size of p
         // Size will be last idx - first idx
         int size = (end - begin + 1);
         
         // Check the size of 'p' and do the corresponding action
         if (size < 2) {}
         else if (size <= 20) {
            doInsertionsort(array, begin, end);
         }
         else {
            medianOfThree(array, begin, end);
            int pivot = partition(array, begin, end);
            TOS++;
            s[TOS][0] = begin;
            s[TOS][1] = pivot-1;
            TOS++;
            s[TOS][0] = pivot+1;
            s[TOS][1] = end;
         }
      }
   }

   private void doMergesort() {
      mergeSort(array, 0, limit-1);
   }
   /* ------------------------------------------------- */
   /*  ----- SORT HELPER METHODS ----- */
   // These methods are called on by the above sort methods

   public void mergeSort(int[] x, int begin, int end){
      
      if (end-begin <= 0) return;

      int mid = (begin + end)/2;

      mergeSort(x, begin, mid);
      mergeSort(x, mid+1, end);

      merge(x, begin, mid, end);
   }
   
   public void merge(int[] x, int begin, int mid, int end) {
   
      int[] z = new int[end-begin+1];

      int i = begin;
      int j = mid + 1;
      int cur = 0;

      while (i <= mid && j <= end) {
         if (x[i] <= x[j]) {
            z[cur] = x[i];
            i++;
            cur++;
         }
         else if (x[i] > x[j]) {
            z[cur] = x[j];
            j++;
            cur++;
         }
      }
      while (j <= end) {
         z[cur] = x[j];
         j++;
         cur++;
      }
      while (i <= mid) {
         z[cur] = x[i];
         i++;
         cur++;
      }
      int r = begin;
      for (int p = 0; p < z.length; p++) {
         x[r] = z[p];
         r++;
      }

      // Draw from range begin to end
      doPaint = PaintType.RANGE;
      leftRange = begin;
      rightRange = end;
      picture.paint(picture.getGraphics()); 
   }

   private int binarySearch(int[] data,int x,int first,int last)
   {
      int middle = 0;
      while(first<=last)
      {
         middle = (first+last)/2;
         if (x < data[middle])
            last = middle - 1;
         else if (x > data[middle])
            first = middle + 1;
         else
            return middle + 1;  
      }
      if (x > data[middle])
         return middle + 1;
      else
         return middle;
   }

   private void medianOfThree(int[] data,int first,int last)  
   {
      int temp,middle,median;

      if (last-first+1 < 3)
         return;
      middle = (first+last)/2;
      if (data[first] <= data[middle])
         if (data[middle] <= data[last])
            median = middle;
         else if (data[last] <= data[first])
            median = first;
         else
            median = last;
      else
         if (data[first] <= data[last])
            median = first;
         else if (data[last] <= data[middle])
            median = middle;
         else
            median = last;
      temp = data[first];
      data[first] = data[median];
      data[median] = temp;
   }

   private int partition(int[] data,int first,int last)
   {
      int left = first+1;
      int right = last;
      int temp;
      while(true)
      {
         while(left<=right && data[left] <= data[first])
            ++left;
         while(right>=left && data[first] <= data[right])
            --right;
         if (left > right)
            break;
         temp = data[left];
         data[left] = data[right];
         data[right] = temp;
         ++left;
         --right;
      } 
      temp = data[first];
      data[first] = data[right];
      data[right] = temp;

      // draw bar at range from first to last
      doPaint = PaintType.RANGE;
      leftRange = first;
      rightRange = last;
      picture.paint(picture.getGraphics());


      return right;
   }
   /* ------------------------------------------------- */

             
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

               // Set the color
               if (color == PaintColor.GREEN) g.setColor(new Color(0.0f, green, 0.0f));
               if (color == PaintColor.BLUE) g.setColor(new Color(0.0f, 0.0f, green));
               if (color == PaintColor.RED) g.setColor(new Color(green, 0.0f, 0.0f));
               if (color == PaintColor.PINK) g.setColor(new Color(0.9f, green, 0.9f));


               // if limit is less than picture Width, rectangles will be drawn instead of lines
               if (limit < pictureWidth) {
                  g.fillRect((int)(i*pictureWidth/limit), 0,
                             (int)(pictureWidth/limit), pictureHeight);
               }

               // array location 0 is painted at left; 
               //   array location limit-1 is painted to right
               //this is a single vertical line in the bar
               else {
                  g.drawLine((int)(i*pictureWidth/limit), 0,
                             (int)(i*pictureWidth/limit), pictureHeight);
               }
            }
         }
         
         else if (doPaint == PaintType.RANGE) {
            for (int i=leftRange; i<=rightRange; i++) {
               float green = (float)(array[i]/(float)largestNum);
               if (green<0f) green = 0f;
               if (green>1f) green = 1f;

               // Set the color
               if (color == PaintColor.GREEN) g.setColor(new Color(0.0f, green, 0.0f));
               if (color == PaintColor.BLUE) g.setColor(new Color(0.0f, 0.0f, green));
               if (color == PaintColor.RED) g.setColor(new Color(green, 0.0f, 0.0f));
               if (color == PaintColor.PINK) g.setColor(new Color(0.9f, green, 0.9f));


               if (limit < pictureWidth) {
                  g.fillRect((int)(i*pictureWidth/limit), 0,
                             (int)(pictureWidth/limit), pictureHeight);
               }
               else {g.drawLine((int)(i*pictureWidth/limit), 0,
                                (int)(i*pictureWidth/limit), pictureHeight);
               }
            }
         }
         else {   // just paint one location on the bar
            float green = (float)(array[index]/(float)largestNum);
            if (green<0f) green = 0f;
            if (green>1f) green = 1f;

            // Set the color
            if (color == PaintColor.GREEN) g.setColor(new Color(0.0f, green, 0.0f));
            if (color == PaintColor.BLUE) g.setColor(new Color(0.0f, 0.0f, green));
            if (color == PaintColor.RED) g.setColor(new Color(green, 0.0f, 0.0f));
            if (color == PaintColor.PINK) g.setColor(new Color(0.9f, green, 0.9f));

            if (limit < pictureWidth) {
               g.fillRect((int)(index*pictureWidth/limit), 0, 
                          (int)(pictureWidth/limit), pictureHeight);
            }
            else {g.drawLine((int)(index*pictureWidth/limit), 0, 
                            (int)(index*pictureWidth/limit), pictureHeight);
            }
         }   
      }
   }
}
