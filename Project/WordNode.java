
/**
 * Class containing the data for each word within the DictionaryTree.
 * 
 * @author (Ryan Hutton) 
 */
public class WordNode
{
    //Data varaibles
    public String ID;
    public String english;
    public String french;

    //Node Pointers
    public WordNode prev;
    public WordNode left;
    public WordNode right;

    /**
     * WordNode Blank Constructor
     *
     */
    public WordNode()
    {
        // initialise instance variables      
        ID = ""; 
        english = "";
        french = "";

        prev = null;
        left = null;
        right = null;
    }
    
     /**
      * WordNode Normal Constructor
      *
      * @param ID A parameter for the words sorting ID
      * @param english A parameter for the word in english
      * @param french A parameter for the word in french
      */
     public WordNode(String ID, String english, String french)
    {
        this.ID = ID;
        this.english = english;
        this.french = french; 
        
        prev = null; 
        left = null; 
        right = null;
    }
    
    /**
     * Method getID
     *
     * @return The return value for the Word in the language we are sorting the Node by
     */
    public String getID(){
        return this.ID;
    }

    /**
     * Method getEng
     *
     * @return The return value for the word in English
     */
    public String getEng()
    {
        return english;
    }

    /**
     * Method getFren
     *
     * @return The return value of the word in French
     */
    public String getFren()
    {
        return french;
    }

    /**
     * Method getPrev
     *
     * @return The return value of the previous node in the DictionaryTree from this one.
     */
    public WordNode getPrev()
    {
        return prev;
    }

    /**
     * Method getLeft
     *
     * @return The return value of the Node left in the tree from this one.
     */
    public WordNode getLeft()
    {
        return left;
    }

    /**
     * Method getRight
     *
     * @return The return value of the Node right in the tree from this one.
     */
    public WordNode getRight()
    {
        return right;
    }
    
    /**
     * Method setLeft
     *
     * @param left A parameter for the Node we want to set left of this one to
     * @return The return value returns the Node we just set to the left.
     */
    public WordNode setLeft(WordNode left)
    {        
        this.left = left;
        return left;       
    }

    /**
     * Method setRight
     *
     * @param left A parameter for the Node we want to set right of this one to
     * @return The return value returns the Node we just set to the right.
     */
    public WordNode setRight(WordNode right)
    {
        this.right = right;
        return right; 
    }

    /**
     * Method setPrev
     *
     * @param left A parameter for the Node we want to set previous of this one to
     * @return The return value returns the Node we just set to the previous.
     */
    public WordNode setPrev(WordNode prev)
    {
        this.prev = prev;
        return prev;
    }   


    
}
