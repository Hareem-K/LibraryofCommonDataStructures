package main.java.mylib.datastructures.nodes;

 /**
 * DNode is a class representing a node in a doubly linked list. It stores an integer value
 * as data and maintains references to its next and previous nodes in the list.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class DNode {
    private int data;
    private DNode next;
    private DNode previous;

    /**
     * Constructs a new `DNode` object with the specified data.
     *
     * @param data The data to be stored in the node.
     */
    public DNode(int data){
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /**
     * Returns the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public int getData(){
        return this.data;
    }

    /**
     * Sets the data of the node to the specified value.
     *
     * @param data The new data to be set in the node.
     */
    public void setData(int data){
        this.data = data;
    }

    /**
     * Returns the next node in the doubly linked list.
     *
     * @return The next node in the doubly linked list, or null if there is no next node.
     */
    public DNode getNext(){
        return this.next;
    }

    /**
     * Sets the next node in the doubly linked list.
     *
     * @param next The node to be set as the next node.
     */
    public void setNext(DNode next){
        this.next = next;
    }

    /**
     * Returns the previous node in the doubly linked list.
     *
     * @return The previous node in the doubly linked list, or null if there is no previous node.
     */
    public DNode getPrevious(){
        return this.previous;
    }

    /**
     * Sets the previous node in the doubly linked list.
     *
     * @param previous The node to be set as the previous node.
     */
    public void setPrevious(DNode previous){
        this.previous = previous;
    }

}
