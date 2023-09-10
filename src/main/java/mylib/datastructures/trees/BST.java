package main.java.mylib.datastructures.trees;

import main.java.mylib.datastructures.nodes.TNode;
import java.util.*;

/**
 *  BST is an implementation of a Binary Search Tree (BST) data structure. A binary search tree
 *  is a binary tree in which the value of the node to the left of the root is less than or
 *  equal to the value of the root, and the value of the node to the right of the root is
 *  greater than or equal to the value of the root. The BST class provides methods to insert,
 *  delete, search, and print the elements of a binary search tree.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class BST {
    protected TNode root;

    /**
     * Constructs an empty binary search tree.
     */
    public BST(){
        this.root = null;
    }

    /**
     * Constructs a binary search tree with a root node containing the given value.
     * @param val The value to be stored in the root node.
     */
    public BST(int val){
        this.root = new TNode(val, 0, null, null, null);
    }

    /**
     * Constructs a binary search tree with the given root node.
     * @param obj The root node of the binary search tree.
     */
    public BST(TNode obj){
        this.root = obj;
    }

    /**
     * Retrieves the root node of the binary search tree.
     * @return The root node of the binary search tree.
     */
    public TNode getRoot(){
        return this.root;
    }

    /**
     * Sets the root node of the binary search tree.
     * @param root The root node to be set.
     */
    public void setRoot(TNode root){
        this.root = root;
    }

    /**
     * Inserts a new node with the given value into the binary search tree.
     * @param val The value to be inserted into the binary search tree.
     */
    public void Insert(int val){
        TNode newNode = new TNode(val, 0, null, null, null);
        Insert(newNode);
    }

    /**
     * Inserts a new node into the binary search tree.
     * @param node The node to be inserted into the binary search tree.
     */
    public void Insert(TNode node){
        if (root == null){
            root = node;
        }
        else {
            TNode current = root;
            TNode parent;
            while (true){
                parent = current;
                if (node.getData() < current.getData()){
                    current = current.getLeft();
                    if (current == null){
                        parent.setLeft(node);
                        node.setParent(parent);
                        break;
                    }
                }
                else {
                    current = current.getRight();
                    if (current == null){
                        parent.setRight(node);
                        node.setParent(parent);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Deletes a node with the given value from the binary search tree.
     * @param val The value to be deleted from the binary search tree.
     */
    public void Delete(int val){
        root = deleteNode(root, val);
    }

    /**
     * Deletes a node with the given value from the binary search tree.
     * @param node The root node of the subtree to be searched for the node to be deleted.
     * @param val The value to be deleted from the binary search tree.
     * @return The root node of the updated subtree after deletion.
     */
    private TNode deleteNode(TNode node, int val){
        if (node == null){
            System.out.println("Value not found in tree");
            return null;
        }
        else if (val < node.getData()){
            node.setLeft(deleteNode(node.getLeft(), val));
        }
        else if (val > node.getData()) {
            node.setRight(deleteNode(node.getRight(), val));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else if (node.getLeft() == null) {
                node = node.getRight();
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else {
                TNode minRight = findMinNode(node.getRight());
                node.setData(minRight.getData());
                node.setRight(deleteNode(node.getRight(), minRight.getData()));
            }
        }
        return node;
    }

    /**
     * Finds the node with the minimum value in the given subtree.
     * @param node The root of the subtree.
     * @return The node with the minimum value.
     */
    private TNode findMinNode(TNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Searches for a node with the given value in the BST.
     * @param val The value to be searched.
     * @return The node with the given value, or null if not found.
     */
    public TNode Search(int val){
        TNode current = root;
        while (current != null) {
            if (current.getData() == val) {
                return current;
            } else if (val < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    /**
     * Prints the elements of the binary search tree in in-order traversal.
     * Starts traversal from the root of the tree.
     * In-order traversal visits the nodes in ascending order of their values.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(h), where h is the height of the tree.
     * 
     * @return void
     */
    public void printInOrder(){
        printInOrder(root);
    }

    /**
     * Helper method that performs in-order traversal of the binary search tree.
     * Recursively visits the nodes in ascending order of their values.
     * 
     * @param node The current node being visited during the traversal.
     * @return void
     */
    private void printInOrder(TNode node){
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            printInOrder(node.getRight());
        }
    }

    /**
     * Prints the elements of the binary search tree in breadth-first traversal order.
     * Starts traversal from the root of the tree.
     * Breadth-first traversal visits the nodes level by level, from left to right.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     * Space Complexity: O(w), where w is the maximum width of the tree (maximum number of nodes at any level).
     * 
     * @return void
     */
    public void printBF() {
        if (root == null) {
            return;
        }
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TNode node = queue.poll();
                System.out.print(node.getData() + " ");
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("\nBST Tests");

        // Create a new binary search tree
        //BST TEST
        BST bst = new BST();

        // Insert some nodes into the tree
        bst.Insert(5);
        bst.Insert(3);
        bst.Insert(7);
        bst.Insert(1);
        bst.Insert(4);
        bst.Insert(6);
        bst.Insert(8);

        // Print the tree in order
        System.out.print("In Order Traversal: ");
        bst.printInOrder(); // Expected output: 1 3 4 5 6 7 8
        System.out.println("\n");

        // Print the tree in breadth-first order
        System.out.println("Breadth-First Traversal:");
        bst.printBF(); /* Expected output: 5
                                           3 7
                                           1 4 6 8 */
        System.out.println();

        // Search for a node in the tree
        TNode tnode = bst.Search(7);
        if (tnode != null) {
            System.out.println("Node found: " + tnode.getData() + "\n"); // Expected output: Node found: 7
        } else {
            System.out.println("Node not found");
        }

        // Delete a node from the tree
        bst.Delete(3);

        // Print the tree in order after deletion
        System.out.print("In Order Traversal after deletion: ");
        bst.printInOrder(); // Expected output: 1 4 5 6 7 8
        System.out.println("\n");

        // Print the tree in breadth-first order after deletion
        System.out.println("Breadth-First Traversal after deletion:");
        bst.printBF(); /* Expected output: 5
                                           4 7
                                           1 6 8 */
        System.out.println();

    }
    
}