package blockchainMerkleTree.dataStructure.linkedList;

/**
 * The LinkedList class represents a linked list data structure.
 * <p>
 * It consists of nodes, each containing an element and a reference to the next node.
 *
 * @param <E> The type of elements stored in the linked list.
 */
public class LinkedList<E> {
    /**
     * The tail of the linked list, i.e. the last node in the list.
     */
    private Node<E> tail;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        tail = null;
    }

    /**
     * Inserts a new element at the end of the linked list.
     *
     * @param element The element to be inserted.
     */
    public void insert(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (tail != null) {
            newNode.setPrevious(tail);
        }
        tail = newNode;
    }

}
