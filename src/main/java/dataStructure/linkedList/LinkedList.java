package dataStructure.linkedList;

public class LinkedList<E> {
    private Node<E> tail;

    public LinkedList() {
        tail = null;
    }

    public void insert(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (tail != null) {
            newNode.setPrevious(tail);
        }
        tail = newNode;
    }

}
