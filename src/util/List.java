//Dependencys
package util;

/*
 * Simple linked list implemented for
 * manipulate graphs using linked adjacency list.
 *
 * This class does not have all the usual methods 
 * of standard list classes because it was made 
 * only for manipulating and structuring graphs 
 * in this system.
 * 
 * This class implements only methods that'll 
 * be needed for the system.
*/
public class List {
    
    //Arbitrary node
    private Node head;
    private Node tail;

    public List() {
        this.head = new Node();
        this.tail = head;
    }

    /**
     * brief add an element on linked list
     * 
     * @param element that'll be inserted
     * @return true if was successfuly inserted and false otherwise
    */
    public boolean add(int element) {
        tail.setNext(new Node(element));
        tail = tail.getNext();
        return true;
    }

    /**
     * brief add an element on linked list
     * 
     * @param element that'll be checked if contains in list
     * @return true if that element cointains in the list and false otherwise
    */
    public boolean contains(int element) {
        boolean contain = false;
        for (Node n = head.getNext().getNext(); n != null; n = n.getNext()) {
            if (n.getElement() == element) {
                contain = true;
            }
        }
        return contain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(head.getNext().getElement() + ": ");
        for (Node n = head.getNext().getNext(); n != null; n = n.getNext()) {
            sb.append(n.getElement() + " - ");
        }
        return sb.toString();
    }

}

/*
 * Auxiliar class for LinkedList class
*/
class Node {

    private int element;
    private Node next;

    public Node() {
        this.element = 0;
        this.next = null;
    }
    public Node(int element) {
        this.element = element;
        this.next = null;
    }

    public int getElement() {
        return element;
    }
    public Node getNext() {
        return next;
    }

    public void setElement(int element) {
        this.element = element;
    }
    public void setNext(Node next) {
        this.next = next;
    }

}
