package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

 /**
 * The SLL class is an implementation of a singly linked list with additional methods 
 * to insert, delete, and search for nodes in the list. The SLL class uses DNode objects, 
 * which represent the nodes of the singly linked list. Each DNode object contains data 
 * and a reference to the next node in the list.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class SLL {
    protected DNode head;
    protected DNode tail;
    protected int size;

    /**
     * Default constructor for SLL class.
     */
    public SLL(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructor for SLL class with initial head node.
     *
     * @param head The initial head node of the list.
     */
    public SLL(DNode head){
        this.head = head;
        this.tail = null;
        this.size = 1;
    }

    /**
     * Inserts a node at the head of the list.
     *
     * @param node The node to be inserted at the head.
     */
    public void InsertHead(DNode node){
        if (head == null){
            head = node;
            tail = node;
        }
        else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

    /**
     * Inserts a node at the tail of the list.
     *
     * @param node The node to be inserted at the tail.
     */
    public void InsertTail(DNode node){
        if (tail == null){
            head = node;
            tail = node;
        }
        else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Inserts a node at a specified position in the list.
     *
     * @param node The node to be inserted.
     * @param position The position at which the node should be inserted.
     */
    public void Insert(DNode node, int position){
        if (position <= 0){
            InsertHead(node);
        }
        else if (position >= size){
            InsertTail(node);
        }
        else {
            DNode current = head;
            for (int i = 1; i < position; i++){
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
            size++;
        }
    }

    /**
     * Inserts a node in sorted order into the list.
     *
     * @param node The node to be inserted.
     */
    public void SortedInsert(DNode node){
        if (head == null){
            head = node;
            tail = node;
        }
        else {
            if (!isSorted()){
                Sort();
            }

            DNode current = head;
            DNode prev = null;

            while (current != null && node.getData() > current.getData()){
                prev = current;
                current = current.getNext();
            }

            if (prev == null){
                InsertHead(node);
            }
            else {
                node.setNext(current);
                prev.setNext(node);
                size++;
            }
        }
    }

    /**
     * Searches for a node with a given value in the list.
     *
     * @param node The node to be searched for.
     * @return The node with the given value, or null if not found.
     */
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
     * Deletes the head node of the list.
     */
    public void DeleteHead(){
        if (head != null){
            head = head.getNext();
            size--;

            if (head == null){
                tail = null;
            }
        }
    }

    /**
     * Deletes the tail node from the doubly linked list.
     */
    public void DeleteTail(){
        if (tail != null){
            if (head == tail){
                head = null;
                tail = null;
            }
            else {
                DNode current = head;
                while (current.getNext() != tail){
                    current = current.getNext();
                }
                tail = current;
                tail.setNext(null);
            }
            size--;
        }
    }

    /**
     * Deletes a specified node from the doubly linked list.
     * @param node The node to be deleted.
     */
    public void Delete(DNode node){
        if (head == null) {
            return;
        }
        if (head == node){
            DeleteHead();
        }
        else if (tail == node){
            DeleteTail();
        }
        else {
            DNode current = head;
            while (current != null && current.getNext() != node){
                current = current.getNext();
            }
            if (current != null && current.getNext() != null){
                current.setNext(node.getNext());
                size--;
            }
        }
    }    

    /**
     * Sorts the doubly linked list in ascending order.
     */
    public void Sort() {
        if (this.head == null || this.head.getNext() == null || this.isSorted()) {
            return;
        }

        DNode current = this.head.getNext();
        DNode prev = this.head;
        while (current != this.tail.getNext()) {
            if (current.getData() < prev.getData()) {
                prev.setNext(current.getNext());
                this.size--;

                SortedInsert(current);

                if (prev.getNext() == null) {
                    this.tail = prev;
                }

                current = prev.getNext();
            } else {
                prev = current;
                current = current.getNext();
            }
        }
    }

    /**
     * Checks if the doubly linked list is sorted in ascending order.
     * @return True if the list is sorted, false otherwise.
     */
    public boolean isSorted(){
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

    /**
     * Clears all the nodes in the doubly linked list.
     */
    public void Clear() {
        DNode current = head;
        while (current != null) {
            DNode next = current.getNext();
            current.setNext(null);
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Prints the information about the doubly linked list, including list length,
     * whether the list is sorted or not, and the list content.
     */
    public void Print() {
        System.out.println("");
        System.out.println("SLL TEST");
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
    }

    public static void main(String[] args) {
         //SLL TESTING
         SLL sll = new SLL();

         // Test default constructor
         SLL sll1 = new SLL();
         System.out.println("");
         System.out.println("Default Constructor Test:");
         sll1.Print(); // expected output: List length: 0
 
         // Test constructor with head node
         DNode headNode = new DNode(2);
         SLL sll2 = new SLL(headNode);
         System.out.println("");
         System.out.println("Head Node Constructor Test:");
         sll2.Print(); // expected output: List length: 1
 
         // insert some nodes
         sll.InsertHead(new DNode(2));
         sll.InsertHead(new DNode(1));
         sll.InsertTail(new DNode(4));
         sll.Insert(new DNode(3), 2);
         
         // print the list
         sll.Print(); // should output: List length: 4, List is sorted, List content: 1 2 3 4
         
         // delete some nodes
         sll.DeleteHead();
         sll.DeleteTail();
         sll.Delete(sll.Search(new DNode(2)));
         
         // print the list again
         sll.Print(); // should output: List length: 1, List is sorted, List content: 3
 
         // test Sort
         SLL sll3 = new SLL();
 
         // insert some nodes
         sll3.InsertHead(new DNode(4));
         sll3.InsertTail(new DNode(2));
         sll3.Insert(new DNode(3), 1);
         sll3.Print(); // prints: 4 3 2 not sorted
 
         sll3.Sort();
         sll3.Print(); // prints: 2 3 4 sorted
 
         sll3.Clear();
         sll3.InsertHead(new DNode(4));
         sll3.InsertTail(new DNode(2));
         sll3.SortedInsert(new DNode(3));
         sll3.Print(); // prints: 2 3 4
 
    }

}
