package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

/**
 * The DLL class represents a Doubly Linked List, which is a linear data structure 
 * that consists of a collection of nodes. Each node contains a data element and 
 * two pointers, one pointing to the next node in the list, and one pointing to 
 * the previous node in the list.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class DLL extends SLL {
    protected DNode head;
    protected DNode tail;
    protected int size;
    
    //constructors

    /**
    * Default constructor for DoublyLinkedList class.
    * Initializes tail, head, and size to null and 0 respectively.
    */
    public DLL() {
        //super();
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    /**
    * Constructor for DoublyLinkedList class with one argument.
    * Initializes tail and head to the given node, and size to 1.
    @param node The initial node to set as tail and head of the list.
    */
    public DLL(DNode node) {
        //super(node); // call super constructor to initialize head to the given node and size to 1
        this.tail = node; // initialize tail to the given node
        this.head = node;
        this.size = 1;
    }

    //methods
    /**
     * Inserts a node at the head of the doubly linked list.
     *
     * @param node the node to be inserted
     */
    @Override
    public void InsertHead(DNode node){
        if (head == null){
            head = node;
            tail = node;
        }
        else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        size++;
    }

    /**
     * Inserts a node at the tail of the doubly linked list.
     *
     * @param node the node to be inserted
     */
    @Override
    public void InsertTail(DNode node){
        if (tail == null){
            head = node;
            tail = node;
        }
        else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        size++;
    }

    /**
     * Inserts a node at the given position in the doubly linked list.
     *
     * @param node     the node to be inserted
     * @param position the position at which the node should be inserted
     */
    @Override
    public void Insert(DNode node, int position) {
        if (position <= 0) {
            InsertHead(node);
        } else if (position >= size) {
            InsertTail(node);
        } else {
            DNode current = head;
            for (int i = 1; i < position; i++) {
                current = current.getNext();
            }
            node.setPrevious(current);
            node.setNext(current.getNext());
            current.getNext().setPrevious(node);
            current.setNext(node);
            size++;
        }
    }

    /**
     * Inserts a node into the doubly linked list in a sorted manner.
     * Assumes that the list is already sorted in ascending order.
     *
     * @param node the node to be inserted
     */
    @Override
    public void SortedInsert(DNode node){
        if (!isSorted()) {
            Sort();
        }

        DNode ptr = this.head;
        Boolean inserted = false;
        int i = 0;

        while (i < this.size && !inserted) {
            if (ptr.getData() < node.getData())
                ptr = ptr.getNext();
            else {
                Insert(node, i);
                inserted = true;
            }
            i++;
        }
        if (!inserted)
            InsertTail(node);
    }
    
    /**
     * Search for a node in the doubly linked list.
     *
     * @param node The node to search for.
     * @return The found node or null if the node is not found.
     */
    @Override
    public DNode Search(DNode node) {
        DNode current = this.head;
        while (current != null) {
            if (current == node){
                return current;
            }
            else if (current.getData() == node.getData()){
                return current;
            }
            else {
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Delete the head node of the doubly linked list.
     * If the list is empty, no action is taken.
     */
    @Override
    public void DeleteHead() {
        if (head != null) {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            } else {
                tail = null;
            }
            size--;
        }
    }

    /**
     * Delete the tail node of the doubly linked list.
     * If the list is empty, no action is taken.
     */
    @Override
    public void DeleteTail() {
        if (tail != null) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.getPrevious();
                tail.setNext(null);
            }
            size--;
        }
    }

    /**
     * Delete a given node from the doubly linked list.
     *
     * @param node The node to be deleted.
     */
    @Override
    public void Delete(DNode node) {
        if (node == null) {
            return;
        }
    
        // Search for the node in the list
        DNode current = head;
        while (current != null && current != node) {
            current = current.getNext();
        }
    
        // If the node is not found in the list, return without deleting
        if (current == null) {
            return;
        }
    
        // If the node is the head of the list, call DeleteHead() to delete it
        if (head == node) {
            DeleteHead();
        }
    
        // If the node is the tail of the list, call DeleteTail() to delete it
        else if (tail == node) {
            DeleteTail();
        }
    
        // Otherwise, remove the node from the list
        else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
            size--;
        }
    }
    
    /**
     * Sort the doubly linked list in ascending order using bubble sort algorithm.
     * If the list is empty or already sorted, no action is taken.
     */
    @Override
    public void Sort(){
        if (head == null || head.getNext() == null || isSorted()){
            return;
        }
        DNode current = head.getNext();
        DNode prev = head;
        while (current != this.tail.getNext()){
                if (current.getData() < prev.getData()){
                    prev.setNext(current.getNext());

                    if (current.getNext() != null) {
                        current.getNext().setPrevious(prev);
                    }     
                    size--;
    
                    SortedInsert(current);
    
                    if (prev.getNext() == null){
                        tail = prev;
                    }
                    current = prev.getNext();
                }
                else {
                    prev = current;
                    current = current.getNext();
                }
            }
        
    }

    //helper function - change to consider CDLL?
    /**
     * Check if the doubly linked list is sorted in ascending order.
     *
     * @return True if the list is sorted, false otherwise.
     */
    @Override
    public boolean isSorted() {
        if (head == null || head.getNext() == null) {
            return true;
        }
        DNode current = head;
        while (current.getNext() != null) {
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    // Deletes the whole list
    /**
     * Clear the doubly linked list by deleting all nodes.
     */
    @Override
    public void Clear() {
        if (head == null) {
            return;
        }
        DNode current = head;
        while (current != null) {
            DNode next = current.getNext();
            current.setPrevious(null);
            current.setNext(null);
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
    * Prints the contents of the doubly linked list.
    * It displays the list length, whether the list is sorted or not,
    * the list content in forward and reverse order.
    */
    @Override
    public void Print() {
        System.out.println("");
        System.out.println("DLL TEST");
        System.out.println("List length: " + size);
    
        if (isSorted()) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is not sorted");
        }
    
        System.out.print("List content: ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    
        System.out.print("Reverse list content: ");
        DNode reverseCurrent = tail;
        while (reverseCurrent != null) {
            System.out.print(reverseCurrent.getData() + " ");
            reverseCurrent = reverseCurrent.getPrevious();
        }
        System.out.println();
    }

    public static void main(String[] args) {
         //DLL TESTING
         DLL dll = new DLL();

         // Test default constructor
         DLL dll1 = new DLL();
         System.out.println("");
         System.out.println("Default Constructor Test:");
         dll1.Print(); // should output: List length: 0, List content:
 
         // Test one argument constructor
         DNode node1 = new DNode(1);
         DLL dll2 = new DLL(node1);
         System.out.println("");
         System.out.println("Head Node Constructor Test:");
         dll2.Print(); // should output: List length: 1, List content: 1
         
         dll.Print(); // should output: List length: 0, List content:
 
         // Test InsertHead()
         DNode node = new DNode(1);
         dll.InsertHead(node);
         dll.Print(); //should output: List length: 1, List content: 1
 
         DNode node2 = new DNode(2);
         dll.InsertTail(node2);
         dll.Print(); //should output: List length: 2, List content: 1 2
 
         DNode node3 = new DNode(3);
         dll.Insert(node3, 1);
         dll.Print(); //should output: List length: 3, List content: 1 3 2
 
         dll.DeleteHead();
         dll.Print(); //should output: List length: 2, List content: 3 2
 
         dll.DeleteTail(); //should output: List length: 1, List content: 3
         dll.Print();
 
         DNode node4 = new DNode(4);
         dll.Delete(node4);
         dll.Print(); //should output: List length: 1, List content: 3
 
         dll.Clear();
         dll.Print(); // should output: List length: 0, List content:
 
         dll.InsertHead(node);
         dll.InsertTail(node4);
         dll.Insert(node3, 1);
         dll.Delete(dll.Search(new DNode(3)));
         dll.Print(); // should output: 1 4
 
         dll.Clear();
         dll.InsertHead(node);
         dll.InsertTail(node4);
         dll.SortedInsert(node3);
         dll.Print(); // should output: 1 3 4
 
         dll.Clear();
         dll.InsertHead(node);
         dll.InsertTail(node4);
         dll.InsertTail(node3);
         dll.Print(); // should output: 1 4 3
         dll.Sort();
         dll.Print(); // should output: 1 3 4
    }
    
}
