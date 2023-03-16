package dataStructure.linkedList;

public class Node<E> {
    private E element;
    private Node<E> prev;

    public Node(E element, Node<E> prev) {
        this.element = element;
        this.prev = prev;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getPrevious() {
        return prev;
    }

    public void setPrevious (Node<E> prev) {
        this.prev = prev;
    }
}