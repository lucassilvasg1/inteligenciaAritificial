package algoritmos.model;

import java.util.ArrayList;
import java.util.List;


/*
 * Referencia de : http://sujitpal.blogspot.com.br/2006/05/java-data-structure-generic-tree.html
 */

public class Tree {
 
    private Node rootElement;
     
    /**
     * Default ctor.
     */
    public Tree() {
        super();
    }
 
    /**
     * Return the root Node of the tree.
     * @return the root element.
     */
    public Node getRootElement() {
        return this.rootElement;
    }
 
    /**
     * Set the root Element for the tree.
     * @param rootElement the root element to set.
     */
    public void setRootElement(Node rootElement) {
        this.rootElement = rootElement;
    }
     
    /**
     * Returns the Tree as a List of Node objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * @return a List<Node>.
     */
    public List<Node> toList() {
        List<Node> list = new ArrayList<Node>();
        walk(rootElement, list);
        return list;
    }
     
    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    public String toString() {
        return toList().toString();
    }
     
    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(Node element, List<Node> list) {
        list.add(element);
        for (Node data : element.getChildren()) {
            walk(data, list);
        }
    }
    
    /**
     * Return the root Node of the tree.
     * @return the root element.
     */
    public List<Node> convertToList()
    {
       List<Node> list = new ArrayList<Node>();
       caminhar(rootElement, list);
       return list;
    }
    
    private void caminhar(Node element, List<Node> list)
    {
       if(element.filhoUnico != null)
       {
          list.add(element);
          caminhar(element.filhoUnico, list);
       }
       else
       {
          list.add(element);
       }
    }

}