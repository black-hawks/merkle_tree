package dataStructure.linkedList;

public class LinkedList<E> {
    private Node<E> tail;
    private int size;

    public LinkedList() {
        tail = null;
        size = 0;
    }

    public void insert(E element) {
        Node<E> newNode = new Node<E>(element, null);
        if (tail == null) {
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    public Node<E> getLast() {
        return tail;
    }

    public int size() {
        return size;
    }
}
