
/**
 * Class used to Translate a sentence string from one language to another.
 * 
 * @author Douglas Parkinson
 * @version 14/03/17
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
    
    public String translateWord(String word, boolean isEnglish){
        String translation = "";
        
        
        return translation;
    }
    
    public String translateSentence(String sentence, boolean isEnglish){
        String translation = "";
        String[] words = sentence.split(" ");
        for(String word : words){
            translation += " "+translateWord(word, isEnglish);
        }
        return translation;
    }
    
    public void loadDictionarys(String engDict, String frenDict){
        if(fileExistsAndCanRead(engDict) == true && fileExistsAndCanRead(frenDict) == true){
            try{
                //Set up for english file
                FileReader engReader = new FileReader(engDict);
                BufferedReader engBuffer = new BufferedReader(engReader);
                //Set up for french file
                FileReader frenReader = new FileReader(frenDict);
                BufferedReader frenBuffer = new BufferedReader(frenReader);
                
                //Reads from dictionary
                String engLine = engBuffer.readLine();
                String frenLine = frenBuffer.readLine();
                do{
                    //Generates ID for each language word node
                    char[] engChars = engLine.toCharArray();
                    char[] frenChars = frenLine.toCharArray();
                    int engID = 0;
                    int frenID = 0;
                    String tempID = "";
                    for(char c : engChars){
                        int tempInt = (int) c;
                        String tempString = String.valueOf(tempInt);
                        tempID += tempString;
                    }
                    engID = Integer.parseInt(tempID);
                    tempID = "";
                    for(char c : frenChars){
                        int tempInt = (int) c;
                        String tempString = String.valueOf(tempInt);
                        tempID += tempString;
                    }
                    frenID = Integer.parseInt(tempID);
                    
                    //Creates New Node for each Dictionary Tree
                    english.addToTree(engID, engLine, frenLine);
                    french.addToTree(frenID, engLine, frenLine);
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
    
}
