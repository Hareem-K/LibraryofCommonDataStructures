package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

 /**
 * The CDLL (Circular Doubly Linked List) class is a Java class that represents a circular 
 * doubly linked list. It is a subclass of the DLL (Doubly Linked List) class and inherits 
 * its properties and methods. The CDLL class provides methods to insert, delete, and search 
 * for nodes in the circular doubly linked list.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class CDLL extends DLL {

    /**
     * Default constructor to create an empty circular doubly linked list (CDLL).
     */
    public CDLL() {
        super();
    }

    /**
     * Constructor to create a CDLL with a given node as the head and tail.
     *
     * @param node The node to be set as the head and tail of the CDLL.
     */
    public CDLL(DNode node) {
        super(node);
    }


    /**
     * Inserts a node at the head of the CDLL.
     *
     * @param node The node to be inserted.
     */
    @Override
    public void InsertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
            node.setNext(node);
            node.setPrevious(node);
        } else {
            node.setNext(head);
            node.setPrevious(tail);
            head.setPrevious(node);
            tail.setNext(node);
            head = node;
            /* updating the tail pointer and connecting the
            ends of the CDLL together after inserting the
            node at the head - see if necessary
            */
            //tail.setNext(head);
            //head.setPrevious(tail);
        }
        size++;
    }

    /**
     * Inserts a node at the tail of the CDLL.
     *
     * @param node The node to be inserted.
     */
    @Override
    public void InsertTail(DNode node) {
        if (tail == null) {
            head = node;
            tail = node;
            node.setNext(node);
            node.setPrevious(node);
        } else {
            node.setPrevious(tail);
            node.setNext(head);
            tail.setNext(node);
            head.setPrevious(node);
            tail = node;
        }
        size++;
    }

    /**
     * Inserts a node at the specified position in the CDLL.
     *
     * @param node     The node to be inserted.
     * @param position The position at which the node should be inserted.
     * @throws IndexOutOfBoundsException if the specified position is out of bounds.
     */
    @Override
    public void Insert(DNode node, int position) throws IndexOutOfBoundsException{
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException();
        }

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
            node.setPrevious(current);
            current.getNext().setPrevious(node);
            current.setNext(node);
            size++;
        }
    }

    /**
     * Inserts a node into the CDLL while keeping the list sorted in ascending order.
     *
     * @param node The node to be inserted.
     */
    @Override
    public void SortedInsert(DNode node){
        super.SortedInsert(node);
    }

    /**
     * Search for a node.
     *
     * @param node The node to search for.
     * @return The found node or null if the node is not found.
     */
    @Override
    public DNode Search(DNode node) {
        return super.Search(node);
    }
     

    /**
     * Deletes the node at the head of the CDLL.
     * This method calls the superclass's DeleteHead() to delete the node at the head position.
     * It also updates the tail's previous reference to make the list circular.
     */
    @Override
    public void DeleteHead() {
        if (this.size == 0) {
            return;
        }
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            super.DeleteHead();
            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);
        }
    }

    /**
     * Deletes the node at the tail of the CDLL.
     * If there is only one node in the CDLL, it sets head and tail to null.
     * Otherwise, it updates tail to its previous node and sets the next reference of the new tail node to null.
     */
    @Override
    public void DeleteTail() {
        if (tail != null) {
            if (head == tail) { // If there is only one node in the CDLL
                head = null;
                tail = null;
            } else {
                tail = tail.getPrevious(); // Update tail to its previous node
                tail.setNext(null); // Set the next reference of the new tail node to null
            }
            size--;
        }
    }

    /**
     * Deletes the specified node from the CDLL.
     *
     * @param node The node to be deleted.
     */
    @Override
    public void Delete(DNode node) {
        if (this.head == null) {
            return;
        }
        if (this.head.getData() == node.getData()) {
            this.DeleteHead(); // Call DeleteHead() if the node to delete is the head
        } else if (this.tail.getData() == node.getData()) {
            this.DeleteTail(); // Call DeleteTail() if the node to delete is the tail
        } else {
            DNode temp = this.head;
            for (int i = 0; i < this.size - 1; i++) {
                if (temp.getData() == node.getData()) {
                    DNode previousDNode = temp.getPrevious();
                    previousDNode.setNext(temp.getNext());
                    previousDNode.getNext().setPrevious(previousDNode);
                    this.size--;
                    return;
                }
                temp = temp.getNext();
            }
        }
    }

    /**
     * Sorts the CDLL in ascending order using bubble sort algorithm.
     */
    @Override
    public void Sort() {
        if (head == null || head.getNext() == null) {
            // List is empty or has only one element, nothing to sort
            return;
        }

        DNode current = head.getNext(); // Start from second element

        while (current != head) {
            // Traverse the list from second element to last element

            if (current == null || current.getPrevious() == null || current.getNext() == null) {
                break; // or handle the null case appropriately
            }

            DNode prev = current.getPrevious();
            DNode next = current.getNext();

            // Find the correct position to insert current element
            if (current.getData() < prev.getData()) {
                // Add null checks here
                if (prev.getPrevious() == null || next == null) {
                    break; // or handle the null case appropriately
                }

                DNode temp = prev;
                while (temp != tail && current.getData() < temp.getData()) {
                    temp = temp.getPrevious();
                }

                // Update the pointers to insert current element at the correct position
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());

                current.setNext(temp.getNext());
                current.setPrevious(temp);
                temp.getNext().setPrevious(current);
                temp.setNext(current);
            } else {
                current = current.getNext();
            }
        }
    }

    /**
     * Returns a boolean value indicating if the Circular Doubly Linked List (CDLL) is sorted in ascending order.
     * CDLL is considered sorted if it is empty (i.e., has no elements) or has only one element.
     * Otherwise, it iterates through the CDLL and compares each element with its adjacent element.
     * If any element is greater than its adjacent element, CDLL is considered not sorted.
     * If the last element is reached, it wraps around to the head and compares with it to check for sorted order.
     *
     * @return true if CDLL is sorted, false otherwise.
     */
    @Override
    public boolean isSorted() {
        if (head == null || head.getNext() == null) {
            // CDLL is empty or has only one element, it is considered sorted
            return true;
        }
    
        DNode current = head;
        do {
            if (current == null || current.getNext() == null || current.getData() > current.getNext().getData()) {
                // If current element is greater than the next element, CDLL is not sorted
                return false;
            } else if (current.getNext() == null) {
                // If current.getNext() is null, it means current is the last element, and we need to wrap around to the head
                return current.getData() <= head.getData(); // compare current with head to check for sorted order
            }            
            current = current.getNext();
        } while (current != head); // Continue until we reach back to the head
    
        return true;
    }

    /**
     * Prints the elements of the Circular Doubly Linked List (CDLL) to the standard output (console).
     * It also prints the length of the CDLL and whether the CDLL is sorted or not.
     * If the CDLL is empty, it prints an empty line for the list content.
     */
    @Override
    public void Print() {
        System.out.println("");
        System.out.println("CDLL TEST");
        System.out.println("List length: " + size);

        if (isSorted()) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is not sorted");
        }

        System.out.print("List content: ");
        if (head != null) {
            DNode current = head;
            do {
                if (current == null) {
                    // If current is null, break out of the loop to avoid NullPointerException
                    break;
                }
                System.out.print(current.getData() + " ");
                current = current.getNext();
            } while (current != head);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        //CDLL TESTING

        DNode node1 = new DNode(1);
        DNode node2 = new DNode(2);
        DNode node3 = new DNode(3);
        DNode node4 = new DNode(4);
        
        // Create a new CDLL
        CDLL cdll = new CDLL();

        // Test CDLL constructor with no parameter
        CDLL cdll1 = new CDLL();
        System.out.println("");
        System.out.println("Default Constructor Test:");
        cdll1.Print(); // should output: List length: 0, List is empty

        // Test CDLL constructor with a node parameter
        DNode nodeG = new DNode(1);
        CDLL cdll2 = new CDLL(nodeG);
        System.out.println("");
        System.out.println("Head Node Constructor Test:");
        cdll2.Print(); // should output: List length: 1, List is sorted, List content: 1


        // Insert some nodes
        cdll.InsertHead(node2);
        cdll.InsertHead(node1);
        cdll.InsertTail(node4);
        cdll.Insert(node3, 2);

        // Print the list
        System.out.println("");
        System.out.print("List after inserting nodes: ");
        cdll.Print(); // should output: List length: 4, List is sorted, List content: 1 2 3 4

        // Delete some nodes
        cdll.DeleteHead();
        cdll.DeleteTail();
        cdll.Delete(cdll.Search(node2));

        // Print the list again
        System.out.println("");
        System.out.print("List after deleting nodes: ");
        cdll.Print(); // should output: List length: 1, List is sorted, List content: 3

        DNode node5 = new DNode(5);
        DNode node0 = new DNode(0);
        // Test SortedInsert
        cdll.SortedInsert(node5);
        cdll.SortedInsert(node0);

        // Print the list after SortedInsert
        System.out.println("");
        System.out.print("List after SortedInsert: ");
        cdll.Print(); // should output: List length: 3, List is sorted, List content: 0 3 5

        // Test Search
        DNode searchNode = cdll.Search(node3);
        System.out.println("Search result: " + (searchNode != null ? searchNode.getData() : "Node not found")); // should output: Search result: 3

        DNode node6 = new DNode(6);
        // Test Delete with a node not in the list
        cdll.Delete(node6);
        System.out.println("");
        System.out.print("List after trying to delete a node not in the list: ");
        cdll.Print(); // should output: List length: 3, List is sorted, List content: 0 3 5

        // Test Delete with the last node
        cdll.Delete(node5);
        System.out.println("");
        System.out.print("List after deleting the last node: ");
        cdll.Print(); // should output: List length: 2, List is sorted, List content: 0 3

        // Test Delete with the head node
        cdll.Delete(node0);
        System.out.println("");
        System.out.print("List after deleting the head node: ");
        cdll.Print(); // should output: List length: 1, List is sorted, List content: 3
    }
}