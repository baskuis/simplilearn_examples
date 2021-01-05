package excersizes;

/*
- Does not have an end
- The 'last' item - should point to the first
 */
public class OurCircularLinkedList<T> {

    Node<T> head;
    Node<T> tail;

    static class Node<T> {
        T element;
        Node<T> next;
    }

    public OurCircularLinkedList() {

    }

    public OurCircularLinkedList(OurSinglyLinkedList<T> list) {
        OurSinglyLinkedList.Node<T> current = list.head;
        while (current != null) {
            add(current.element);
            current = current.next;
        }
    }

    public void add(T element) {
        Node<T> newNode = new Node<>();
        newNode.element = element;
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }
        this.tail.next = newNode;
        newNode.next = this.head;
        this.tail = newNode;
    }

    public void printSomeEntries(int number) {
        int counter = 0;
        Node<T> current = this.head;
        while (counter <= number) {
            System.out.println("Value: " + current.element);
            current = current.next;
            counter++;
        }
    }

}
