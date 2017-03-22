
/**
 * Write a description of class WordNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordNode
{
    private String ID;
    private String english;
    private String french;
    
    private WordNode prev;
    private WordNode left;
    private WordNode right;

    /**
     * Constructor for objects of class WordNode
     */
    public WordNode(String id, String eng, String fren)
    {
        ID = id;
        english = eng;
        french = fren;
    }
    
    public WordNode(){
        ID = "";
        english = "";
        french = "";
    }
    
    public String getID(){
        return ID;
    }
    
    public String getEng(){
        return english;
    }
    
    public String getFren(){
        return french;
    }
    
    public WordNode getLeft(){
        return left;
    }
    
    public void setLeft(WordNode n){
        left = n;
    }
    
    public WordNode getRight(){
        return right;
    }
    
    public void setRight(WordNode n){
        right = n;
    }
    
    public WordNode getPrev(){
        return prev;
    }
    
    public void setPrev(WordNode n){
        prev = n;
    }
    
    public String getSummaryData(){
        return "";
    }
    

}
