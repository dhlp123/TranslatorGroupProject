
/**
 * Write a description of class WordNode here.
 * 
 * @author (Ryan Hutton) 
 */
public class WordNode
{
    public Integer ID;
    public String english;
    public String french;

    public WordNode prev;
    public WordNode left;
    public WordNode right;

    public WordNode()
    {
        // initialise instance variables      
        ID = 0; 
        english = "";
        french = "";

        prev = null;
        left = null;
        right = null;
    }
    
     public WordNode(String english, String french)
    {
        this.english = english;
        this.french = french; 
        
        prev = null; 
        left = null; 
        right = null;
    }

    public String getEnglish()
    {
        return english;
    }

    public String getFrench()
    {
        return french;
    }

    public WordNode getPrev()
    {
        return prev;
    }

    public WordNode getLeft()
    {
        return left;
    }

    public WordNode getRight()
    {
        return right;
    }
    
    public WordNode setLeft(WordNode left)
    {        
        this.left = left;
        return left;       
    }

    public WordNode setRight(WordNode right)
    {
        this.right = right;
        return right; 
    }

    public WordNode setPrev()
    {
        this.prev = prev;
        return prev;
    }   


    
}

