package main.java.mylib.datastructures.trees;

import main.java.mylib.datastructures.nodes.TNode;

/**
 * AVL class represents an AVL tree, which is a type of binary search tree that is
 * self-balancing to maintain a balanced tree structure for efficient operations.
 * It extends the BST (Binary Search Tree) class.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class AVL extends BST{
    
    /**
     * Default constructor for AVL class.
     * Creates an empty AVL tree by calling the constructor of the parent BST class.
     */
    public AVL(){
        super();
    }

    /**
     * Constructor for AVL class that takes an initial value.
     * Creates an AVL tree with a root node containing the given value,
     * by calling the constructor of the parent BST class with the given value.
     *
     * @param val The initial value for the root node of the AVL tree.
     */
    public AVL(int val){
        super(val);
    }

    /**
     * Constructor for AVL class that takes a TNode object.
     * Creates an AVL tree with the given TNode object as the root node,
     * by calling the constructor of the parent BST class with the given TNode object.
     * Also performs AVL balancing on the root node.
     *
     * @param obj The TNode object to be set as the root node of the AVL tree.
     */
    public AVL(TNode obj){
        super(obj);
        this.root = balance(obj);
    }

    /**
     * Get the root node of the AVL tree.
     *
     * @return The root node of the AVL tree.
     */
    public TNode getRoot(){
        return this.root;
    }

    /**
     * Private method to balance a given node in the AVL tree.
     * This method performs AVL rotations on the given node if it is unbalanced,
     * to restore the balance of the tree.
     * This method is called recursively on the ancestors of the given node during insertion and deletion.
     *
     * @param node The node to be balanced.
     * @return The balanced node after AVL rotations.
     */
    private TNode balance(TNode node){
        if (node == null){
            return null;
        }

        int balance = getBalance(node);

        if (balance > 1){
            if (getBalance(node.getLeft()) >= 0){
                node = rotateRight(node);
            }
            else {
                node.setLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }
        else if (balance < -1){
            if (getBalance(node.getRight()) <= 0){
                node = rotateLeft(node);
            }
            else {
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        }
        return node;
    }

    /**
     * Private method to get the balance factor of a given node.
     * The balance factor of a node is the height of its left subtree minus the height of its right subtree.
     * A balance factor of 0 indicates the node is balanced, a positive value indicates left-heavy,
     * and a negative value indicates right-heavy.
     *
     * @param node The node to get the balance factor from.
     * @return The balance factor of the given node.
     */
    private int getBalance(TNode node){
        if (node == null){
            return 0;
        }
        return (getHeight(node.getLeft()) - getHeight(node.getRight()));
    }

    /**
     * Private method to get the height of a given node.
     * The height of a node is the number of edges in the longest path from the node to a leaf node.
     *
     * @param node The node to get the height from.
     * @return The height of the given node.
     */
    private int getHeight(TNode node){
        if (node == null){
            return 0;
        }
        return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }

    /**
     * This method performs a left rotation on the given node in the AVL tree.
     *  It updates the references of the nodes involved in the rotation to
     *  maintain the balance of the tree. The right child of the input
     *  node becomes the new root of the rotated subtree, the left
     *  child of the right child becomes the new right child of the
     *  input node, and the input node becomes the left child of the
     *  new root. The method returns the new root of the rotated subtree.
     * @param node
     * @return
     */
    private TNode rotateLeft(TNode node){
        TNode right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }

    /**
     * This method performs a right rotation on the given node in the AVL tree. It updates
     *  the references of the nodes involved in the rotation to maintain the balance of the
     *  tree. The left child of the input node becomes the new root of the rotated subtree,
     *  the right child of the left child becomes the new left child of the input node, and
     *  the input node becomes the right child of the new root. The method returns the new
     *  root of the rotated subtree.
     * @param node
     * @return
     */
    private TNode rotateRight(TNode node){
        TNode left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }

    /**
     * This method overrides the Insert method from the parent class BST to insert a new node
     *  with the given val into the AVL tree. After inserting the node, it calls the balance
     *  method with the root of the tree to ensure that the tree remains balanced.
     */
    @Override
    public void Insert(int val) {
        super.Insert(val);
        this.root = balance(this.root);
    }
    
    /**
     * This method overrides the Insert method from the parent class BST to insert a new node
     *  with the given node into the AVL tree. After inserting the node, it calls the balance
     *  method with the root of the tree to ensure that the tree remains balanced.
     */
    @Override
    public void Insert(TNode node) {
        super.Insert(node);
        this.root = balance(this.root);
    }

    /**
     * This method overrides the Delete method from the parent class BST to delete a node with
     * the given val from the AVL tree. After deleting the node, it calls the balance method
     *  with the root of the tree to ensure that the tree remains balanced.
     */
    @Override
    public void Delete(int val){
        super.Delete(val);
        this.root = balance(this.root);
    }

    @Override
    public TNode Search(int val){
        return super.Search(val);
    }

    @Override
    public void printInOrder(){
        super.printInOrder();
    }

    @Override
    public void printBF(){
        super.printBF();
    }

    public static void main(String[] args) {
        System.out.println("\nAVL Tests");

        AVL tree = new AVL();
        
        // Insert nodes
        tree.Insert(10);
        tree.Insert(20);
        tree.Insert(30);
        tree.Insert(40);
        tree.Insert(50);
        tree.Insert(25);
        
        // Print tree
        System.out.println("AVL tree after insertion:");
        tree.printInOrder(); //should output: 10 20 25 30 40 50 
        System.out.println("\n");
        System.out.println("Breadth-First Traversal:");
        tree.printBF(); /*should output: 30 
                                         20 40 
                                         10 25 50 */
        System.out.println();
        
        // Delete nodes
        tree.Delete(25);
        tree.Delete(40);
        
        // Print tree
        System.out.println("AVL tree after deletion:");
        tree.printInOrder(); //should output: 10 20 30 50 
        System.out.println("\n");
        System.out.println("Breadth-First Traversal:");
        tree.printBF(); /*should output:  30
                                          20 50
                                          10 */
        System.out.println();
        
        // Test getRoot method
        TNode root = tree.getRoot();
        System.out.println("Root node value: " + root.getData() + "\n"); //should output: 30

        System.out.println("AVL Tests");

        AVL tree2 = new AVL(7);
        
        // Insert nodes
        tree2.Insert(5);
        tree2.Insert(8);
        tree2.Insert(2);
        tree2.Insert(10);
        tree2.Insert(1);
        
        // Print tree
        System.out.println("AVL tree after insertion:");
        tree2.printInOrder(); //should output: 1 2 5 7 8 10
        System.out.println("\n");
        System.out.println("Breadth-First Traversal:");
        tree2.printBF(); /*should output: 7
                                          5 8
                                          2 10
                                          1 */
        
        System.out.println();
        
        // Delete nodes
        tree2.Delete(1);
        tree2.Delete(10);
        
        // Print tree
        System.out.println("AVL tree after deletion:");
        tree2.printInOrder(); //should output: 2 5 7 8
        System.out.println("\n");
        System.out.println("Breadth-First Traversal:");
        tree2.printBF(); /*should output: 7
                                          5 8
                                          2 */
        System.out.println();
        
        // Test getRoot method
        TNode root2 = tree2.getRoot();
        System.out.println("Root node value: " + root2.getData() + "\n"); //should output: 7
    }
}
