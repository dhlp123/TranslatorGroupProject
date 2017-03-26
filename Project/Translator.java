
/**
 * Class used to Translate a sentence string from one language to another.
 * 
 * @author Douglas Parkinson, Nathan Darroch and Ryan Hutton.
 * @version 26/03/17
 */

//Imports Dependencies
import java.io.*;

public class Translator
{   
    //Dictionary Tree Structures
    private DictionaryTree english;
    private DictionaryTree french;

    /**
     * Constructor for objects of class Translator
     */
    public Translator()
    {
        english = new DictionaryTree();
        french = new DictionaryTree();
    }

    /**
     * Method translateWord
     * Takes in a string and translates it by searching the binary tree for it's translated counterpart.
     *
     * @param word A parameter for the word taken in to be translated.
     * @param isEnglish A parameter for whether the word is in English or not.
     * @return The return value for the translated word.
     */
    public String translateWord(String word, boolean isEnglish){
        String translation = "";

        if (isEnglish == true){
            WordNode node = english.searchTree(word);
            if(node == null){
                return word;
            }else{
                translation = node.getFren();
            }
        }
        else if(isEnglish == false){
            WordNode node = french.searchTree(word);
            if(node == null){
                return word;
            }else{
                translation = node.getEng();
            }
        }

        return translation;
    }

    /**
     * Method translateSentence
     * Takes in a string and translates it to another language.
     *
     * @param sentence A parameter for the sentence that we want translated.
     * @param isEnglish A parameter for whether this sentence is in english or not.
     * @return The return value for the translated sentence.
     */
    public String translateSentence(String sentence, boolean isEnglish){
        String translation = "";
        String[] words = sentence.split(" ");
        String twoWords = "";
        String threeWords = "";
        for(int i=0;i<words.length;i++){
            if(i+1 < words.length){
                twoWords = translateWord(words[i].toLowerCase()+ " " + words[i+1].toLowerCase(), isEnglish);
            }
            if(i+2 < words.length){
                threeWords = translateWord(words[i].toLowerCase() + " " + words[i+1].toLowerCase() + " " + words[i+2].toLowerCase(), isEnglish);
            }

            if(twoWords.equals("") == false && twoWords.equals(words[i].toLowerCase()+ " " + words[i+1].toLowerCase()) == false){
                translation += " "+twoWords;
                i +=1;
            }
            else if(threeWords.equals("") == false && threeWords.equals(words[i].toLowerCase() + " " + words[i+1].toLowerCase() + " " + words[i+2].toLowerCase()) == false){
                translation += " "+threeWords;
                i +=2;
            }
            else{
                translation += " "+translateWord(words[i].toLowerCase(), isEnglish);
            }
            twoWords = "";
            threeWords = "";
        }

        return translation;
    }

    /**
     * Method displayDictionary
     *  Displays the dictionary in alphabetical order.
     */
    public void displayDictionary(){
        System.out.println("Dictionary:\n ");
        english.displayTree();
    }

    /**
     * Method isTreeEmpty
     *
     * @return The return value of whether the tree is emtpy or not.
     */
    public boolean isTreeEmpty(){
        if(english.isTreeEmpty()){
            return true;
        }

        return false;
    }

    /**
     * Method loadDictionarys
     *
     * @param randDict A parameter for the file name of the main Dictionary file.
     */
    public void loadDictionarys(String randDict){
        if(fileExistsAndCanRead(randDict)){
            try{

                //Set up for rand Dictionary file
                FileReader dictReader = new FileReader(randDict);
                BufferedReader dictBuffer = new BufferedReader(dictReader);

                //Reads from main dictionary
                String dictLine = dictBuffer.readLine();
                do{
                    String engLine = dictLine.split("~")[0];
                    String frenLine = dictLine.split("~")[1];

                    System.out.println(engLine + " " + frenLine);

                    //Creates New Node for each Dictionary Tree
                    english.addToTree(engLine, engLine, frenLine);
                    french.addToTree(frenLine, engLine, frenLine);

                    //Reads next lines
                    dictLine = dictBuffer.readLine();

                }
                while(dictLine != null);

            }catch(IOException e){
                System.out.println("IOException: Cannot read from file.");
            }
        }
        if(fileExistsAndCanRead("customEnglishDictionary.txt") == true && fileExistsAndCanRead("customFrenchDictionary.txt") == true){
            try{

                //Set up for custom Dictionary files
                FileReader engReader = new FileReader("customEnglishDictionary.txt");
                BufferedReader engBuffer = new BufferedReader(engReader);
                FileReader frenReader = new FileReader("customFrenchDictionary.txt");
                BufferedReader frenBuffer = new BufferedReader(frenReader);

                //Reads from main dictionary
                String engLine = engBuffer.readLine();
                String frenLine = frenBuffer.readLine();
                do{

                    System.out.println(engLine + " " + frenLine);

                    //Creates New Node for each Dictionary Tree
                    if(engLine != null && frenLine != null){
                        english.addToTree(engLine, engLine, frenLine);
                        french.addToTree(frenLine, engLine, frenLine);
                    }

                    //Reads next lines
                    engLine = engBuffer.readLine();
                    frenLine = frenBuffer.readLine();
                }
                while(engLine != null);

            }catch(IOException e){
                System.out.println("IOException: Cannot read from file.");
            }
        }
    }

    /**
     * Method for checking if a file exists and is readable.
     * 
     * @param String for file the user wants to check
     * @return Boolean for wether the file does or does not exist and is readable.
     */
    public boolean fileExistsAndCanRead(String fileName){
        if(new File(fileName).exists()==true && new File(fileName).canRead()==true){
            return true;
        }
        return false;
    }

    /**
     * Searches for a word to delete from the dictionary, deletes it and saves the dictionary
     */
    public boolean deleteWords() {
        String cEngDictName = "customEnglishDictionary.txt";
        String cFrenchDictName = "customFrenchDictionary.txt";

        createCustomDictionaryFiles(cEngDictName);
        createCustomDictionaryFiles(cFrenchDictName);

        String cEngText = openUserFileAndRead(cEngDictName);
        String cFrText = openUserFileAndRead(cFrenchDictName);

        String userSearch = "";
        String frenchWord = "";
        
        int index = 0;
        int indexEnd = 0;
        int savedIndex = 0;
        
        int indexFrench = 0;
        int indexEndFrench = 0;
        int savedIndexFrench = 0;
        
        char userChoice = 'b';
        
        boolean userEnd = false;
        boolean deleted = false;
        
        StringBuffer searchableText = new StringBuffer(cEngText);
        StringBuffer searchableTextFrench = new StringBuffer(cFrText);
        StringBuffer finalText = searchableText;
        StringBuffer finalTextFrench = searchableTextFrench;
        
        System.out.println(cEngText);
        System.out.println("Enter the word you would like to search for");
        userSearch = Genio.getString();
        while (userEnd == false) {
            if (cEngText.indexOf(userSearch) < 0) {
                System.out.print("String not found, please enter another:");
                userSearch = Genio.getString();
                continue;
            }
            frenchWord = english.searchTree(userSearch).getFren();
            while (userEnd == false) {
                index = searchableText.indexOf(userSearch);
                indexEnd = index + userSearch.length();
                indexFrench = searchableTextFrench.indexOf(frenchWord);
                indexEndFrench = indexFrench + frenchWord.length();
                
                System.out.println("String " + userSearch + " found in custom dictionary");
                System.out.println("Delete " + searchableText.substring(index, indexEnd) + "? [Y/N/Q]");
                
                userChoice = Genio.getCharacter();
                while (true) {
                    if (userChoice == 'q' || userChoice == 'Q') {
                        userEnd = true;
                        break;
                    }
                    if (userChoice == 'y' || userChoice == 'Y') {
                        finalText.replace(index, indexEnd + 1, "");
                        finalTextFrench.replace(indexFrench, indexEndFrench + 1, "");
                        english.removeFromTree(userSearch);
                        french.removeFromTree(frenchWord);
                        userEnd = true;
                        break;
                    }
                    if (userChoice == 'n' || userChoice == 'N') {
                        savedIndex = indexEnd + 1;
                        savedIndexFrench = indexEndFrench + 1;
                        
                        searchableText.replace(0, indexEnd + 1, "");
                        searchableTextFrench.replace(0, indexEndFrench + 1, "");
                        break;
                    }
                    System.out.println("Invalid character entered, Y to delete, N to search for next instance of \""
                        + userSearch + "\" or Q to return to menu");
                    userChoice = Genio.getCharacter();
                }
            }
            // System.out.println(finalText.toString());
            deleted = overrideCustomDictionary(finalText.toString(), cEngDictName);
            overrideCustomDictionary(finalTextFrench.toString(), cFrenchDictName);
        }
        return deleted;
    }

    /**
     * Overrides a dictionary with new contents
     * 
     * @param ovrText,
     *            the text to override with
     * @param dictName,
     *            the dictionary to override
     */
    public boolean overrideCustomDictionary(String ovrText, String dictName) {

        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            outputStream = new FileOutputStream(dictName);
            printWriter = new PrintWriter(outputStream);
        } catch (IOException e) {
            System.out.println("An error occurred overriding files");
            return false;
        }
        printWriter.print(ovrText);
        printWriter.close();

        return true;
    }

    /**
     * Allows the user to add words to the custom dictionary files to be loaded
     * by the loadDictionaries method
     */
    public boolean addWords() {
        String cEngDictName = "customEnglishDictionary.txt";
        String cFrenchDictName = "customFrenchDictionary.txt";

        createCustomDictionaryFiles(cEngDictName);
        createCustomDictionaryFiles(cEngDictName);

        BufferedWriter engBW = null;
        FileWriter engFW = null;
        BufferedWriter frBW = null;
        FileWriter frFW = null;

        try {
            File cEng = new File(cEngDictName);
            File cFr = new File(cFrenchDictName);

            engFW = new FileWriter(cEng.getAbsoluteFile(), true);
            engBW = new BufferedWriter(engFW);

            frFW = new FileWriter(cFr.getAbsoluteFile(), true);
            frBW = new BufferedWriter(frFW);

        } catch (IOException e) {
            System.out.println("An error occurred opening the custom english dictionary to write");
            return false;
        }
        String userString = "";
        int numOfEntries = 0;
        String eng = "";
        String fren = "";
        System.out.println(
            "Enter each word you would like to add to the dictionary, pressing return between words\nLeave your entry blank and press return to return to the menu");
        System.out.print("English word: ");
        userString = Genio.getString().toLowerCase();
        eng = userString;
        while (userString.length() != 0) {
            if (numOfEntries % 2 == 0) {
                try {
                    engBW.write(userString + "\n");
                    System.out.print("French word: "); // appends current word
                    // to custom english
                    // dictionary and asks
                    // for the french
                    // equivalent
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                try {
                    frBW.write(userString + "\n");
                    System.out.print("English word: "); // appends current word
                    // to french dictionary
                    // and asks for a new
                    // word to be added to
                    // the english
                    // dictionary
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            numOfEntries++;
            userString = Genio.getString().toLowerCase();
            fren = userString;
            if(eng != null && fren != null){
                english.addToTree(eng, eng, fren);
                french.addToTree(fren, eng, fren);
                eng = "";
                fren = "";
            }
        }
        try {
            if (engBW != null)
                engBW.close();
            if (frBW != null)
                frBW.close();
            if (engFW != null)
                engFW.close();
            if (frFW != null)
                frFW.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method to ask the user for a filename and read the file at that location
     * 
     * @return readFile, a String containing the contents of the file at the
     *         location
     */
    public String openUserFileAndRead(String dictName) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(dictName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return readFile(fileReader);
    }

    /**
     * method to open a file
     * 
     * @param fileReader,
     *            a FileReader to be obtained by the openFileToRead() method
     * @return fulltext, a String conatining the full text of the read file
     */
    public static String readFile(FileReader fileReader) {
        String fullText = "";
        String nextLine = "";
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            fullText = (fullText + bufferedReader.readLine());
            nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                fullText = (fullText + "\n" + nextLine);
                nextLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred when attempting to close the file");
                }
            }
        }
        return fullText;
    }

    /**
     * If the custom dictionary files aren't found, this method shall create
     * them
     * 
     * @param dName,
     *            name of the dictionary to be created
     */
    public void createCustomDictionaryFiles(String dName) {
        if (fileExistsAndCanRead(dName) == false) {
            try {
                File dict = new File(dName);

                if (dict.createNewFile()) {
                    System.out.println("Dictionary created");
                } else {
                    System.out.println("Dictionary already exists");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
