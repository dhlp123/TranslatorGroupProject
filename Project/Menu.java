
/**
 * Class that allows the user to access the program's features
 * 
 * @author Momoko Werner 
 * @version 1.2
 */
public class Menu
{
    // instance variables
    Translator translator;
    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {
        // initialise instance variables
        translator = new Translator();
    }

    public static void main(String[] args)
    {
        Menu startMenu = new Menu();
        startMenu.translator.loadDictionarys("randDictionary.txt");
        System.out.println("Loading Done");
        startMenu.processUserChoices();
    }

    /**
     * Method displaying the menu options to the user
     * 
     * @param  No parameters
     * @return None
     */
    public void displayMenu()
    {
        System.out.println("French-English Dictionary: Menu\n");
        System.out.println("Please choose one of the options below (0-5): \n");
        System.out.println("1. Translate from English to French.");
        System.out.println("2. Translate from French to English.");
        System.out.println("3. Add a word.");
        System.out.println("4. Remove a word.");
        System.out.println("5. Display the dictionary's contents.");
        System.out.println("0. Exit the program\n");
    }

    /**
     * Method allowing user to choose between menu options and processing their choice
     * 
     * @param No parameters
     * @return None
     */
    public void processUserChoices()
    {
        int choice;

        //translate word fr-en, en-fr and translate sentence fr-en, en-f
        do
        {
            //display the menu options
            displayMenu();
            System.out.print("Choice: ");
            //Prompt a choice from user
            choice = Genio.getInteger();
            //Compare input with menu options
            if (choice == 1) //translate en-fr
                translateEn();
            else if (choice == 2) //translate fr-en
                translateFr(); 
            else if (choice == 3) //Add
                addWord();
            else if (choice == 4) //Remove
                delWord();
            else if (choice == 5) //Display
                display();
            else if (choice == 0) 
                Menu.exit();
            else 
            {
                System.out.println("\nYour chosen option does not exist.");
                System.out.println("Please press Enter to continue and try again.");
                // Clear terminal
                Genio.getString();
                System.out.print('\u000C');
            }
        }
        while (choice != 0);
    }

    /**
     * Translate from English to French     //Error catching for translation??
     * 
     * @param No parameters 
     * @return None 
     */
    public void translateEn()
    {
        String enWord; 
        String frWord;

        //receive word to translate from user
        System.out.println("Please give the English word you want to translate:"); 
        enWord = Genio.getString();
        frWord = translator.translateSentence(enWord, true);
        //display the translation
        System.out.println("\nThe translation for " +enWord+ " is: \n" +frWord+ ".");
        System.out.println("\nPlease press Enter to continue.");
        // Clear terminal
        Genio.getString();
        System.out.print('\u000C');
    }

    /**
     * Translate from French to English //call Translator
     * 
     * @param No parameters 
     * @return None 
     */
    public void translateFr()
    {
        String enWord; 
        String frWord;

        //receive word to translate from user
        System.out.println("Please give the French word you want to translate:"); 
        frWord = Genio.getString();
        enWord = translator.translateSentence(frWord, false);
        //display the translation
        System.out.println("\nThe translation for " +frWord+ " is: \n" +enWord+ ".");
        System.out.println("\nPlease press Enter to continue.");
        // Clear terminal
        Genio.getString();
        System.out.print('\u000C');
    }

    /**
     * Add a word given by the user     //call Translator
     * 
     * @param No parameters 
     * @return None 
     */
    public void addWord()
    {   
        //error catching in translator class??
        
        if ((translator.addWords()) == true)
        {
            System.out.println("\nWord added successfully!");
        }
        else 
        {
            System.out.print("\nError, word couldn't be added");
        }

        System.out.println("\nPlease press Enter to continue.");
        // Clear terminal
        Genio.getString();
        System.out.print('\u000C');
    }

    /**
     * Remove a word  //call Translator 
     * 
     * @param No parameters 
     * @return None 
     */
    public void delWord()
    {
        
        //error catching in translator class??
        if ((translator.deleteWords() == true))
        {
            System.out.println("\nWord removed successfully!");
        }
        else 
        {
            System.out.print("\nError, word couldn't be removed");
        }

        System.out.println("\nPlease press Enter to continue.");
        // Clear terminal
        Genio.getString();
        System.out.print('\u000C');
    }

    /**
     * Method that displays the dictionary's contents   //call tree
     * 
     * @param No parameters 
     * @return None 
     */
    public void display()
    {
        if ((translator.isTreeEmpty())== false)
        { 
            translator.displayDictionary();
        }
        else 
            System.out.println("Error: Dictionary is empty.");

        System.out.println("\nPlease press Enter to continue.");
        // Clear terminal
        Genio.getString();
        System.out.print('\u000C');
    }

    /**
     * Method to quit the menu
     * 
     * @param No parameters 
     * @return None 
     */
    public static void exit()
    {
        System.out.println("\nGoodbye.");
    }
}
