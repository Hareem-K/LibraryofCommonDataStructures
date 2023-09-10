package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

 /**
 * QueueLL is a class that represents a queue data structure implemented using 
 * a singly linked list. It provides methods to enqueue, dequeue, peek, search, 
 * clear, and check if the queue is empty.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class QueueLL extends SLL{
    /*
     * Default constructor to create an empty QueueLL object.
     */
    public QueueLL(){
        super();
    }

    /**
     * Constructor to create a QueueLL object with a given head node.
     *
     * @param head The head node of the QueueLL.
     */
    public QueueLL(DNode head){
        super(head);
    }

    /**
     * Enqueues a new node to the end of the queue.
     *
     * @param node The node to be enqueued.
     */
    public void enqueue(DNode node){
        super.InsertTail(node);
    }

    /**
     * Dequeues the node from the front of the queue.
     */
    public void dequeue(){
        super.DeleteHead();
    }

    /**
     * Returns the node at the front of the queue without dequeuing it.
     *
     * @return The node at the front of the queue.
     */
    public DNode peek(){
        return this.head;
    }

    /**
     * Searches for a node in the queue and returns its position in the queue.
     *
     * @param node The node to be searched for.
     * @return The position of the node in the queue. Returns -1 if the node is not found.
     */
    public int search(DNode node) {
        int position = 0;
        DNode current = this.head;
        while (current != null) {
            if (current.getData() == node.getData()) {
                return position;
            }
            current = current.getNext();
            position++;
        }
        return -1;
    }

    /**
     * Clears all nodes from the queue.
     */
    public void Clear(){
        super.Clear();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean empty(){
        return this.head == null;
    }

    @Override
    public void InsertHead(DNode node){}

    @Override
    public void Insert(DNode node, int position){}

    @Override
    public void SortedInsert(DNode node){}

    @Override
    public DNode Search(DNode node) {return null;}

    @Override
    public void DeleteTail(){}

    @Override
    public void Delete(DNode node){}

    @Override
    public void Sort(){}

    @Override
    public void Print(){
        System.out.println("");
        System.out.println("QUEUELL TEST");
        System.out.println("List length: " + size);
        System.out.println("Is list empty: " + empty());

        System.out.print("List content: ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueLL queue = new QueueLL();

        // Testing out enqueue
        queue.enqueue(new DNode(1));
        queue.enqueue(new DNode(2));
        queue.enqueue(new DNode(0));
        System.out.println("\n-----Testing out enqueue method insertion-----");
        queue.Print(); //should output: List length: 3, is list empty: false, List content: 1 2 0

        // Testing out the dequeue return value
        queue.dequeue();
        System.out.println("\n-----Testing out dequeue method return-----");
        queue.Print(); //should output: List length: 2, is list empty: false, List content: 2 0

        // Dequeue until the Queue is empty
        while (!queue.empty()) {
            queue.dequeue();
        }
        System.out.println("\n-----Testing the dequeue method to clear the queue");
        queue.Print(); //should output: List length: 0, is list empty: true, List content:

        // Testing out the second constructor
        queue = new QueueLL(new DNode(10));
        System.out.println("\n-----Testing the second constructor for the queue-----");
        queue.Print(); //should output: List length: 1, is list empty: false, List content: 10

        // Testing the peek method after a few enqueues
        queue.enqueue(new DNode(5));
        queue.enqueue(new DNode(100));
        System.out.println("\n-----Testing the peek method for the queue-----");
        queue.Print(); 
        System.out.println("Peeked data: " + queue.peek().getData()); //should output: List length: 3, is list empty: false, List content: 5 100, Peeked data: 5

        // Searching for a node that exists
        int position = queue.search(new DNode(5));
        System.out.println("\n-----Searching for the position of the node with 5 as data (exists)-----");
        System.out.println("position: " + position); //should output: position: 0

        // Searching for a node that exists
        position = queue.search(new DNode(100));
        System.out.println("\n-----Searching for the position of the node with 100 as data (exists)-----");
        System.out.println("position: " + position); //should output: position: 1

        // Searching for a node that does not exist
        position = queue.search(new DNode(9));
        System.out.println("\n-----Searching for the position of the node with 9 as data (does not exist)-----");
        System.out.println("position: " + position); //should output: position: -1

        // Testing the clear method
        queue.Clear();
        System.out.println("\n-----Testing the clear method for the queue-----");
        queue.Print(); //should output: List length: 0, is list empty: true, List content:
    }
}
