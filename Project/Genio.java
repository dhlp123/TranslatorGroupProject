import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * General purpose class to enable simple coding of programs requiring data input at 
 * a text based interface.
 * 
 * The class is under development, so where unexpected exceptions occur, please could
 * you report them to me at pgregor@computing.dundee.ac.uk
 * 
 * @author Peter Gregor
 * @version 1.2 16th September 2003
 */

    
public class Genio
{


    /**
     * Constructor for objects of class genio, but nothing needing constructed!
     */
    public Genio()
    {
    }

     
    /** 
     * getStr()  is a private method which safely returns a string for use
     * by the public methods getString() and getCharacter() in the class.
     * 
     * @return String for further processing withing the class
     */
    
    private static String getStr() 
    {
        String inputLine = "";
        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(System.in));
        try 
        {
            inputLine = reader.readLine();
        }
        
        catch(Exception exc) 
        {
            System.out.println ("There was an error during reading: "
                                + exc.getMessage());
        }
        return inputLine;
    }
    
    /** 
     * getInteger() returns an integer value. Exception handling is used to trap
     * invalid data - including floating point numbers, non-numeric characters
     * and no data. In the event of an exception, the user is prompted to enter
     * the correct data in the correct format.
     * 
     * @return validated int value 
     */
    public static int getInteger()
    {
        int temp=0;
        boolean OK = false;
        
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        do 
        {
            try
            {
                temp = Integer.parseInt(keyboard.readLine());
                OK = true;
            }

            catch (Exception eRef)
            {
                if (eRef instanceof NumberFormatException) 
                {
                    System.out.print("Integer value needed: ");
                }
                else
                {
                    System.out.println("Please report this error: "+eRef.toString());
                }
            }
          
        } while(OK == false);
        return(temp);
     }
     
    /** 
     * getFloat() returns a floating point value. Exception handling is used to trap
     * invalid data - including non-numeric characters and no data.
     * In the event of an exception (normally no data or alpha), the user is prompted to enter
     * data in the correct format
     * 
     * @return validated float value
     */        
    public static float getFloat()
    {
        float temp=0;
        boolean OK = false;

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        do 
        {
            try
            {
                temp = Float.parseFloat(keyboard.readLine());
                OK = true;
            }


            catch (Exception eRef)
            {
                if (eRef instanceof NumberFormatException) 
                {
                    System.out.print("Number needed: ");
                } 
                else
                {
                    System.out.println("Please report this error: "+eRef.toString());
                }
            }
           
        } while(OK == false);
        
        return(temp);
     }
     
    /** 
     * getDouble() returns a double precision floating point value. 
     * Exception handling is used to trap invalid data - including non-numeric
     * characters and no data.
     * In the event of an exception, the user is prompted to enter
     * data in the correct format
     * 
     * @return validated double precision value
     */        
    public static double getDouble()
    {
        double temp=0;
        boolean OK = false;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        do 
        {
            try
            {
                temp = Double.parseDouble(keyboard.readLine());
                OK = true;
            }

            catch (Exception eRef)
            {
                if (eRef instanceof NumberFormatException) 
                {
                    System.out.print("Number needed: ");
                }
                else
                {
                    System.out.println("Please report this error: "+eRef.toString());
                }
            }
           
        } while(OK == false);
        
        return(temp);
     }

    /** 
     * getCharacter() returns a character from the keyboard. It does this by 
     * reading a string then taking the first character read. Subsequent characters
     * are discarded without raising an exception.
     * The method checks to ensure a character has been entered, and prompts 
     * if it has not.
     * 
     * @return validated character value
     */
        
     public static char getCharacter()
     {
         String tempStr="";
         char temp=' ';
         boolean OK = false;
         do 
         {
             try
             {
                 tempStr = getStr();
                 temp = tempStr.charAt(0);
                 OK = true;
             }

             catch (Exception eRef)
             {
                 if (eRef instanceof StringIndexOutOfBoundsException)
                 {
                     // means nothing was entered so prompt ...
                     System.out.print("Enter a character: ");
                 }            
                 else 
                 {
                     System.out.println("Please report this error: "+eRef.toString());
                 }
             }
           
         } while(OK == false);
        
         return(temp);
     }
     
     /** 
      * getString() returns a String entered at the keyboard.
      * @return String value
      */
     
     public static String getString()
     {
        String temp="";
        try
        {
            temp = getStr();
        }
        catch (Exception eRef)
        {
            System.out.println("Please report this error: "+eRef.toString());
        }
        return(temp);
     }
     
}


