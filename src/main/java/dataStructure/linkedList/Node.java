package dataStructure.linkedList;

public class Node<E> {
    private final E element;
    private Node<E> previous;

    public Node(E element, Node<E> previous) {
        this.element = element;
        this.previous = previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
}