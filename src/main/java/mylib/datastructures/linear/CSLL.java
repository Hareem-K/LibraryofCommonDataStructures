package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

 /**
 * Circular Singly Linked List (CSLL) implementation that extends Singly Linked List (SLL).
 * CSLL is a linear data structure in which each node points to the next node in a circular manner.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class CSLL extends SLL {
    
    //constructors
    /**
     * Default constructor that initializes an empty CSLL.
     */
    public CSLL() {
        super();
    }

    /**
     * Constructor that initializes a CSLL with a single node.
     * @param node The node to be set as both head and tail of the CSLL.
     */
    public CSLL(DNode node) {
        super(node);
        this.head = node;
        this.tail = node;
        node.setNext(node);
        this.size = 1;
    }

    /**
     * Overrides the InsertHead method of SLL to update tail's next pointer.
     */
    @Override
    public void InsertHead(DNode node) {
        super.InsertHead(node);
        this.tail.setNext(head);
    }
    
    //should I add @Override
    /**
     * Overrides the InsertTail method of SLL to update tail's next pointer.
     */
    @Override
    public void InsertTail(DNode node) {
        super.InsertTail(node);
        this.tail.setNext(this.head);
    }

    /**
     * Overrides the Insert method of SLL to update tail's next pointer.
     */
    @Override
    public void Insert(DNode node, int position) throws IndexOutOfBoundsException{
        super.Insert(node, position);
        tail.setNext(head);
    }
 
    /**
     * Inserts a node into a sorted CSLL in ascending order based on node's data.
     * @param sortedList The head of the sorted CSLL.
     * @param node The node to be inserted into the sorted CSLL.
     * @return The head of the sorted CSLL after inserting the node.
     */
    private DNode insertIntoSortedList(DNode sortedList, DNode node) {
        if (sortedList == null || sortedList.getData() >= node.getData()) {
            node.setNext(sortedList);
            sortedList = node;
        } 
        else {
            DNode current = sortedList;
            
            while (current.getNext() != null && current.getNext() != sortedList && current.getNext().getData() < node.getData()) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
        }
        return sortedList;
    }

    /**
     * Overrides the Sort method of SLL to sort the CSLL in ascending order.
     */
    @Override
    public void Sort() {
        if (head == null || head.getNext() == null) {
            return;
        }

        DNode sortedList = null;
        DNode current = head;

        do {
            DNode nextNode = current.getNext();
            sortedList = insertIntoSortedList(sortedList, current);
            current = nextNode;
        } 
        while (current != head);

        head = sortedList;
        tail = head;

        while (tail.getNext() != null && tail.getNext() != head) {
            tail = tail.getNext();
        }
        tail.setNext(head);
    }

    /**
     * Overrides the Search method of SLL to search for a node in the CSLL.
     */
    @Override
    public DNode Search(DNode node) {
        if (head == null) {
            return null;
        }
        DNode current = head;
        do {
            if (current == node) {
                return current;
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    /** 
     * Overrides the DeleteHead method of the parent class
     * Deletes the head node and sets the tail's next pointer to the new head
    */
    @Override
    public void DeleteHead() {
        super.DeleteHead();
        tail.setNext(head);
    }

    /**
     * Overrides the DeleteTail method of the parent class
     * Deletes the tail node and sets the new tail's next pointer to the head
     */
    //using parent method, and then modifies the tails next pointer
    @Override
    public void DeleteTail() {
        super.DeleteTail();
        this.tail.setNext(this.head);
    }

    /**
     * Overrides the Delete method of the parent class
     * Deletes the given node from the circular singly linked list
     * If the node is the head or tail, invokes DeleteHead or DeleteTail methods respectively
     * Otherwise, iterates through the list to find the previous node of the given node
     * Updates the previous node's next pointer to skip the given node, effectively deleting it
     */
    @Override
    public void Delete(DNode node) {
        if (head == node) {
            DeleteHead();
        } else if (tail == node) {
            DeleteTail();
        } else {
            DNode current = head;
            while (current != null && current.getNext() != node) {
                current = current.getNext();
            }
            if (current != null) {
                current.setNext(node.getNext());
                size--;
            }
        }
    }

    /**
     * Overrides the isSorted method of the parent class
     * Checks if the circular singly linked list is sorted in ascending order
     * Returns true if the list is empty or has only one element
     * Otherwise, iterates through the list and compares each node's data with its next node's data
     * If any node's data is greater than its next node's data, returns false
     * Otherwise, returns true
     */
    @Override
    public boolean isSorted() {
        if (size == 0 || size == 1) {
            return true;
        }
        DNode current = this.head;
        while (current.getNext() != this.head){
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Overrides the SortedInsert method of the parent class
     * Inserts a new node in sorted order into the circular singly linked list
     * If the list is empty or has only one element, sets the new node as both head and tail
     * Otherwise, if the list is not sorted, invokes the Sort method to sort it
     * Then iterates through the list to find the correct position to insert the new node
     * Updates the next pointers of the previous node and the new node to insert it in the correct position
     * If the new node is inserted at the head, updates the tail to the new node
     */
    @Override
    public void SortedInsert(DNode node) { //should this be updated to handle the case where the list is initially empty?
        if (head == null || size == 0) {
            head = node;
            tail = node;
            node.setNext(node); // set next to itself for circular reference
            size = 1;
        } else {
            if (!isSorted()) {
                Sort();
            }
            
            DNode current = head;
            DNode prev = tail;
    
            while (current != head && node.getData() > current.getData()){
                prev = current;
                current = current.getNext();
            }

            if (prev == tail && node.getData() < current.getData()){
                InsertHead(node);
            }
            else {
                node.setNext(current);
                prev.setNext(node);
                if (current == head) {
                    tail = node; // update tail if inserting at head
                }
                size++;
            }
        }
    }

    /**
     * Clears the entire circular singly linked list by invoking the parent class's Clear() method.
     * This method removes all nodes in the list and sets the head and tail pointers to null.
     * The size of the list is set to 0.
     */
    @Override
    public void Clear() {
        super.Clear();
    }
    
    /**
     * Prints information about the circular singly linked list.
     * This method prints the list length, whether the list is sorted or not,
     * and the content of the list in order.
     * The list content is printed as space-separated values.
     */
    @Override
    public void Print() {
        System.out.println("");
        System.out.println("CSLL TEST");
        System.out.println("List length: " + size);
    
        if (isSorted()) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is not sorted");
        }
    
        System.out.print("List content: ");
        System.out.print("List content: ");
        DNode current = this.head;
        for (int i = 0; i < this.size; i++) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //CSLL TESTING
        CSLL csll = new CSLL();

        // Test the first constructor of CSLL
        CSLL csll1 = new CSLL();

        // Insert nodes into the CSLL
        DNode nodeE = new DNode(1);
        DNode nodeF = new DNode(2);
        csll1.InsertHead(nodeE);
        csll1.InsertTail(nodeF);
        System.out.println("");
        System.out.println("Default Constructor Test:");

        // Print the CSLL
        csll1.Print(); // prints: CSLL TEST
                    //         List length: 2
                    //         List is not sorted
                    //         List content: 1 2

        // Test the second constructor of CSLL
        CSLL csll2 = new CSLL(nodeE);

        System.out.println("");
        System.out.println("Head Node Constructor Test:");
        // Print the CSLL
        csll2.Print(); // prints: CSLL TEST
                    //         List length: 1
                    //         List is not sorted
                    //         List content: 1

        
        DNode nodeA = new DNode(1);
        DNode nodeB = new DNode(2);
        DNode nodeC = new DNode(3);
        DNode nodeD = new DNode(4);
 
        csll.InsertHead(nodeA);
        csll.InsertTail(nodeB);
        csll.Insert(nodeC, 1);
        csll.Print(); // prints: 1 3 2 not sorted
        
        csll.SortedInsert(nodeD);
        csll.Print(); // prints: 1 2 3 4
 
        csll.Clear();
        csll.InsertHead(nodeA);
        csll.InsertTail(nodeB);
        csll.Insert(nodeC, 1);
        csll.Sort();
        csll.Print(); // prints: 1 2 3

        csll.SortedInsert(nodeD);
        csll.Delete(nodeB);
        csll.Print(); // prints: 1 3 4
 
        csll.DeleteHead();
        csll.Print(); // prints: 3 4
 
        csll.DeleteTail();
        csll.Print(); // prints: 3

        // Search Test
        DNode foundNode = csll.Search(nodeC);
        System.out.println("");
        System.out.println("CSLL TEST");
        System.out.println("Testing Search():");
        if (foundNode != null) {
            System.out.println("Node with value 3 found.");
        } else {
            System.out.println("Node with value 3 not found.");
        }

        // Clear the list
        csll.Clear();
        System.out.println("");
        System.out.println("List after clearing:");
        csll.Print(); // Print the list after clearing
        
    }
}
