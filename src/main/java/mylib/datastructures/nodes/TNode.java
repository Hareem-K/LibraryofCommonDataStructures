package main.java.mylib.datastructures.nodes;

 /**
 * The TNode class is a node class that represents a node in a binary tree data structure.
 * It contains methods for setting and getting the data, left and right child nodes, parent
 * node, and balance factor of a node. It also includes a placeholder method for printing 
 * the node, which can be implemented as needed.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class TNode {
    private int data;
    private TNode left;
    private TNode right;
    private TNode parent;
    private int balance;

    // Constructors
    /**
     * Constructs a new `TNode` object with default values.
     */
    public TNode(){
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * Constructs a new `TNode` object with the specified values.
     *
     * @param data The data to be stored in the node.
     * @param balance The balance factor of the node.
     * @param parent The parent node of this node.
     * @param left The left child node of this node.
     * @param right The right child node of this node.
     */
    public TNode(int data,int balance, TNode parent, TNode left, TNode right){
        this.data = data;
        this.balance = balance;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    // Getter and setter for "data"
    /**
     * Returns the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public int getData() {
        return data;
    }

    /**
     * Sets the data of the node to the specified value.
     *
     * @param data The new data to be set in the node.
     */
    public void setData(int data) {
        this.data = data;
    }

    // Getter and setter for "left"
    /**
     * Returns the left child node of this node.
     *
     * @return The left child node of this node, or null if there is no left child.
     */
    public TNode getLeft() {
        return left;
    }

    /**
     * Sets the left child node of this node.
     *
     * @param left The node to be set as the left child.
     */
    public void setLeft(TNode left) {
        this.left = left;
    }

    // Getter and setter for "right"
    /**
     * Returns the right child node of this node.
     *
     * @return The right child node of this node, or null if there is no right child.
     */
    public TNode getRight() {
        return right;
    }

    /**
     * Sets the right child node of this node.
     *
     * @param right The node to be set as the right child.
     */
    public void setRight(TNode right) {
        this.right = right;
    }

    // Getter and setter for "parent"
    /**
     * Returns the parent node of this node.
     *
     * @return The parent node of this node, or null if there is no parent node.
     */
    public TNode getParent() {
        return parent;
    }

    /**
     * Sets the parent node of this node.
     *
     * @param parent The node to be set as the parent.
     */
    public void setParent(TNode parent) {
        this.parent = parent;
    }

    // Getter and setter for "balance"
    /**
     * Returns the balance factor of this node.
     *
     * @return The balance factor of this node.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance factor of this node.
     *
     * @param balance The balance factor to be set.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Placeholder method for printing the node. Implementation can be added as needed.
     */
    public void print(){
        System.out.println("placeholder");
    }

}