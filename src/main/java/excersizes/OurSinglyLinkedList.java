package excersizes;

public class OurSinglyLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    static class Node<T> {
        T element;
        Node<T> next;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>();
        newNode.element = element;
        newNode.next = null;
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }
        this.tail.next = newNode;
        this.tail = newNode;
    }

    public void printAllValues() {
        Node<T> current = this.head;
        System.out.println(current.element);
        while (current.next != null) {
            current = current.next;
            System.out.println(current.element);
        }
    }


    /**
     * Singly Linked List
     *
     * -------------------
     *
     * head -> First Node
     *
     * Node -> next Node
     * Node -> next Node
     * Node -> next Node
     * Node -> next Node
     *
     * tail -> Last Node
     *
     *
     * Real world example:
     * Track the names of people on the call
     */
}
