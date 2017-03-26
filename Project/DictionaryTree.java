
/**
 * Class that handles the binary tree methods
 * 
 * @author Momoko Werner 
 * @version 20/03
 */
public class DictionaryTree
{
    //var
    private WordNode root;

    /**
     * Constructor for objects of class DictionaryTree
     */
    public DictionaryTree()
    {
        // initialise instance variables
        root = null;
    }

    /**
     * Get the root of the tree
     * 
     * @return root reference to WordNode object
     */
    public WordNode getRoot()
    {
        return root;
    }

    /**
     * Set the root of the tree to the given node
     * 
     * @param  newRoot  reference to a WordNode object which will be the root
     * @return  None
     */
    public void setRoot(WordNode newRoot)
    {
        root = newRoot;
    }

    /**
     * Add a new node to the tree which will contain the data provided ().
     * 
     * @param  ID   integer, ID of the word       
     * @param  english, french  Strings holding a word and its translation respectively     
     * @return boolean  shows whether word has been added successfully or not
     */
    public boolean addToTree(String ID, String english, String french)
    {
        // create a new node
        WordNode newNode = new WordNode(ID, english, french); 
        WordNode previousNode = new WordNode();
        WordNode currentNode = new WordNode();
        Boolean found = false;
        previousNode = null;
        currentNode = root;

        //set new node to be the root if tree empty
        if (root == null)
        {
            //tree is empty, set new node to be root
            root = newNode;
        }
        else 
        {
            //find available free space
            while (currentNode != null)
            {
                previousNode = currentNode;
                if ((newNode.getID()).equals(currentNode.getID())){
                    return false;
                }
                
                else if ((newNode.getID()).compareTo((currentNode.getID())) < 0)
                {
                    //new Node's id smaller than current's go left
                    currentNode = currentNode.getLeft();
                }
                else if ((newNode.getID()).compareTo((currentNode.getID())) > 0)
                {
                    //new Node's id greater than current's go right
                    currentNode = currentNode.getRight();
                }
            }
            if ((newNode.getID()).compareTo((previousNode.getID())) < 0)
            {
                //new Node's id smaller, set as left
                previousNode.setLeft(newNode);
            }
            else if ((newNode.getID()).compareTo((previousNode.getID())) > 0)
            {
                //new Node's id greater, set as right
                previousNode.setRight(newNode);
            }
        }
        return true;
    }

    /**
     * Print contents of the tree
     * 
     * @param None
     * @return None
     */
    public void displayTree()
    {
        //traverse tree beginning at the root
        traverseTree(root);
    }

    /**
     * Traverse throught the tree inorder
     * 
     * @param  processNode  Node that is being processed
     * @return None
     */
    public void traverseTree(WordNode processNode)
    {
        
        if (processNode != null)
        {
            
            traverseTree(processNode.getLeft());
            System.out.println("English: "+processNode.getEng() + " French: " + processNode.getFren());   //Changed this to print the details without the addition of another method
            traverseTree(processNode.getRight());
        }
    }

    /**
     * Find a translation by searching for a word
     * 
     * @param id     holds word that is being searched for
     * @return word  returns translation or deafult 'false' value
     */
    public WordNode searchTree(String id)
    {
        boolean found = false;
        WordNode foundNode = null; 
        WordNode processNode = new WordNode();
        //start processing at root
        processNode = root;

        while ((processNode != null) && (found == false))
        {
            //if IDs are the same find and return the mark of the student
            if (id.equals(processNode.getID()))
            {
                foundNode = processNode;
                found = true;
                return foundNode;
            }
            else if (id.compareTo((processNode.getID())) < 0)
            {
                //given ID is smaller -> follow left branch
                processNode = processNode.getLeft();
            }
            else if (id.compareTo((processNode.getID())) > 0)
            {
                //given ID is greater -> follow right branch
                processNode = processNode.getRight();
            }
        }
        //should return default 'false' value here
        return foundNode;
    }

    /**
     * Method that determines whether the tree is empty
     * 
     * @param  None
     * @return Boolean - whether tree is empty or not
     */
    public boolean isTreeEmpty()
    {
        //return true if empty/root is still null
        if (root==null)
            return true;
        else
            return false;
    }

    /**
     * Method to delete nodes from the tree
     * 
     * @param   id      Holds ID of the word to be deleted 
     * @return Boolean  whether deleted successfully or not
     */
    public boolean removeFromTree(String id)
    {
        boolean found = false;
        WordNode currentNode = new WordNode();
        currentNode = root;
        WordNode nodeToDelete = null;
        WordNode previousNode = null;
        //find node with id
        while ((currentNode != null) && (found == false))
        {
            //compare IDs to see if they're the same
            if (id.equals(currentNode.getID()))
            {
                found = true;
            }
            else if (id.compareTo((currentNode.getID())) < 0)
            {
                //save reference to previous node, given ID is smaller -> follow left branch
                previousNode = currentNode;
                currentNode = currentNode.getLeft();
            }
            else if (id.compareTo((currentNode.getID())) > 0)
            {
                //save reference to previous node, given ID is greater -> follow right branch
                previousNode = currentNode;
                currentNode = currentNode.getRight();
            }
        }
        if (found == false)
        {
            //node with same ID wasn't found -> couldn't be deleted
            return false;
        }
        else    
        {
            //check whether the node to be deleted has children
            if ( ((currentNode.getRight())== null) && ((currentNode.getLeft())== null) )
            {
                //no children, delete reference from previous node
                if (previousNode != null)
                {
                    if ((currentNode.getID()).compareTo((previousNode.getID())) < 0)
                        previousNode.setLeft(null);
                    else
                        previousNode.setRight(null);
                }
                else
                    root = null;
                //delete direct reference
                currentNode = null;
                return true;
            }
            else if ( ((currentNode.getRight())== null) && ((currentNode.getLeft())!= null) )
            {
                //1 child, left; set child as subtree to previous node
                if (previousNode != null)
                {
                    if (((currentNode.getLeft()).getID()).compareTo(previousNode.getID()) < 0)
                    {
                        previousNode.setLeft(currentNode.getLeft());
                    }
                    else 
                        previousNode.setRight(currentNode.getLeft());
                }
                else
                    root = currentNode.getLeft();
                currentNode = null;
                return true;
            }
            else if ( ((currentNode.getRight())!= null) && ((currentNode.getLeft())== null) )
            {
                //1 child, right; set child as subtree to previous node
                if (previousNode != null)
                {
                    if (((currentNode.getRight()).getID()).compareTo(previousNode.getID()) < 0)
                    {
                        previousNode.setLeft(currentNode.getRight());
                    }
                    else 
                        previousNode.setRight(currentNode.getRight());
                }
                else
                    root = currentNode.getRight();
                currentNode = null;
                return true;
            }
            else if ( ((currentNode.getRight())!= null) && ((currentNode.getLeft())!= null) )
            {
                //2 children
                previousNode = null;
                nodeToDelete = currentNode;
                currentNode = (nodeToDelete.getLeft());
                while (currentNode != null)
                {
                    //follow right branch of left subtree to find rightmost node
                    previousNode = currentNode;
                    currentNode = currentNode.getRight();
                }
                //reconnect tree correctly and remove node from tree
                if (previousNode != null)
                {
                    if (previousNode.getID().compareTo(nodeToDelete.getID()) < 0)
                        previousNode.setRight(nodeToDelete.getRight());
                    else
                        previousNode.setLeft(nodeToDelete.getLeft());
                }
                else 
                    nodeToDelete.setLeft(currentNode.getLeft());
                if (nodeToDelete == root)
                {
                    root = previousNode;
                }
                nodeToDelete = null;
                return true;
            }
        }
        return false;
    }
}
