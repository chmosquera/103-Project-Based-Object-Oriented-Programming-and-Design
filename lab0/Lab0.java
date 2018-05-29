import mycs1.*;


public class Lab0
{
   public static void main(String[] args)
   {

      int t_value = 0;
      boolean neg = false;
      String base2;
      int value;
      int base;
      char ch;

      do {

         System.out.print("Enter value and base: ");
         value = Keyboard.readInt();
         base = Keyboard.readInt();

         if (base < 2 || base > 16) {
            throw new RuntimeException();
         } 
   
         //___check if value is negative___
         if (value < 0) {
            t_value = value * -1;
            neg = true;
         }

         //___changeBase___
         if (neg == true) {
            base2 = changeBase(t_value, base);
          }
          else base2 = changeBase(value, base);

         System.out.print("input: " + value + " " + base + "\noutput: " + base2  +"\n");

         System.out.print("\nEnter c to continue, or q to quit. ");
         ch = Keyboard.readChar();
      }
      while (ch == 'c');

   }

   private static final char[] digits = {'0','1','2','3','4','5','6','7','8',
                                         '9','A','B','C','D','E','F'};

   // value must be >= 0 and base must be in 2..16

   private static String changeBase(long value,int base)
   {
      if (value < base)
         return "" + digits[(int)value];
      else
         return changeBase(value/base,base) + digits[(int)(value%base)];
   }
}
