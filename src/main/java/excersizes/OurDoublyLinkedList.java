package excersizes;

public class OurDoublyLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    static class Node<T> {
        T element;
        Node<T> previous;
        Node<T> next;
    }

    public void add(T element) {

        /** create brand new node */
        Node<T> newNode = new Node<>();
        newNode.element = element;

        /** only for the first - special case */
        if (this.head == null) {
            this.tail = this.head = newNode;
            return;
        }

        /** attaching new node */
        this.tail.next = newNode;

        /** because it doubly linked */
        newNode.previous = this.tail;

        /** now that done - the brand new one is the last */
        this.tail = newNode;

    }

    public void reverse() {
        // ??
    }

    public void printAllValues() {
        Node<T> current = this.head;
        System.out.println(current.element);
        while (current.next != null) {
            current = current.next;
            System.out.println(current.element);
        }
    }

}
