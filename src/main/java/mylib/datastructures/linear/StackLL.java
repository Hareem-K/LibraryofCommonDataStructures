package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

 /**
 * The StackLL class is a Java class that represents a stack data structure implemented 
 * using a linked list. It extends the SLL class, which presumably represents a singly 
 * linked list. The StackLL class provides methods for pushing, popping, peeking, searching, 
 * clearing, and checking if the stack is empty, which are typical operations associated
 * with a stack.
 * 
 * @author Eeman Abid
 *         <a href="mailto:eeman.abid@ucalgary.ca">eeman.abid@ucalgary.ca</a>
 * @author Hareem Khan
 *         <a href="mailto:hareem.khan@ucalgary.ca">hareem.khan@ucalgary.ca</a>
 */

public class StackLL extends SLL{
    
    /**
     * Constructs an empty StackLL object.
     */
    public StackLL(){
        super();
    }

    /**
     * Constructs a StackLL object with the given head node.
     * @param head The head node of the StackLL.
     */
    public StackLL(DNode head){
        super(head);
    }

    /**
     * Pushes a new node onto the top of the stack.
     * @param node The node to be pushed onto the stack.
     */
    public void push(DNode node){
        super.InsertHead(node);
    }

    /**
     * Pops the top node from the stack.
     */
    public void pop(){
        super.DeleteHead();
    }

    /**
     * Peeks at the top node of the stack without removing it.
     * @return The top node of the stack.
     */
    public DNode peek(){
        return this.head;
    }

    /**
     * Searches for a node in the stack and returns its position in the stack.
     * @param node The node to be searched for.
     * @return The position of the node in the stack, or -1 if the node is not found.
     */
    public int search(DNode node) {
        int position = 1;
        DNode current = this.head;
        while (current != null) {
            if (current == node) {
                return position;
            }
            current = current.getNext();
            position++;
        }
        return -1;
    }
    

    /**
     * Clears all nodes from the stack.
     */
    public void Clear(){
        super.Clear();
    }

    /**
     * Checks if the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    public boolean empty(){
        return this.head == null;
    }

    // Overrides for methods in the SLL class that are not supported in a stack implementation

    @Override
    public void InsertTail(DNode node){}

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

    /**
     * Prints the contents of the stack.
     */
    @Override
    public void Print() {
        System.out.println("");
        System.out.println("STACKLL TEST");
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
        //STACKLL TESTING
        // Test InsertHead()
        System.out.println("");
        System.out.println("STACKLL TEST");

        // Create an empty StackLL
        StackLL stack = new StackLL();
    
        // Test push() method
        DNode nodeO = new DNode(1);
        stack.push(nodeO);
        System.out.println("Push() Test Passed: push() - List content after push: "); // Expected output: List content after push: 1
        stack.Print();
        System.out.println("");
    
        // Test peek() method
        DNode peekNode = stack.peek();
        System.out.println("Peek() Test Passed: peek() - Peek node value: " + peekNode.getData()); // Expected output: Peek node value: 1
        System.out.println("");
    
        // Test search() method
        int searchResult = stack.search(nodeO);
        System.out.println("Seach() Test Passed: search() - Search result: " + searchResult); // Expected output: Search result: 1
        System.out.println("");
    
        // Test empty() method
        boolean isEmpty = stack.empty();
        System.out.println("empty() Test Passed: empty() - Is stack empty? " + isEmpty); // Expected output: Is stack empty? false
        System.out.println("");
    
        // Test pop() method
        stack.pop();
        System.out.println("pop() Test Passed: pop() - List content after pop: "); // Expected output: List content after pop: 
        stack.Print();
        System.out.println("");
    
        // Test Clear() method
        DNode nodeP = new DNode(2);
        stack.push(nodeP);
        stack.Clear();
        System.out.println("Clear() Test Passed: Clear() - List content after clear: "); // Expected output: List content after clear: 
        stack.Print();
        System.out.println("");
       
        // Test StackLL(DNode head) constructor
        DNode nodeQ = new DNode(3);
        StackLL stack2 = new StackLL(nodeQ);
        System.out.println("StackLL(DNode head) Test Passed: StackLL(DNode head) constructor - List content (head only): " + stack2.peek().getData() + "\n"); // Expected output: List content: 3

        // Another stack
        // Create an empty StackLL
        StackLL stack3 = new StackLL();
    
        // Test push() method
        DNode stackNode = new DNode(1);
        DNode stackNode2 = new DNode(2);
        DNode stackNode3 = new DNode(3);
        stack3.push(stackNode);
        stack3.push(stackNode2);
        stack3.push(stackNode3);
        System.out.println("Push() Test Passed: push() - List content after push: "); // Expected output: List content after push: 3 2 1
        stack3.Print();
        System.out.println("");
    
        // Test peek() method
        DNode peekNode2 = stack3.peek();
        System.out.println("Peek() Test Passed: peek() - Peek node value: " + peekNode2.getData()); // Expected output: Peek node value: 3
        System.out.println("");
    
        // Test search() method
        int searchResult2 = stack3.search(stackNode2);
        System.out.println("Seach() Test Passed: search() - Search result: " + searchResult2 + "\n"); // Expected output: Search result: 2
    
        // Test empty() method
        boolean isEmpty2 = stack3.empty();
        System.out.println("empty() Test Passed: empty() - Is stack empty? " + isEmpty2 + "\n"); // Expected output: Is stack empty? false
    
        // Test pop() method
        stack3.pop();
        System.out.println("pop() Test Passed: pop() - List content after pop: "); // Expected output: List content after pop: 2 1
        stack3.Print();
        System.out.println("");
    
        // Test Clear() method
        stack3.Clear();
        System.out.println("Clear() Test Passed: Clear() - List content after clear: "); // Expected output: List content after clear: 
        stack3.Print();
        System.out.println("");
       
        // Test StackLL(DNode head) constructor
        DNode stackNode4 = new DNode(4);
        StackLL stack4 = new StackLL(stackNode4);
        System.out.println("StackLL(DNode head) Test Passed: StackLL(DNode head) constructor - List content (head only): " + stack4.peek().getData() + "\n"); // Expected output: List content: 4
    }
}
