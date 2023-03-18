package blockchainMerkleTree.dataStructure.linkedList;

/**
 * The Node class represents a node in a linked list.
 * <p>
 * It contains an element of type E and a reference to the previous node.
 *
 * @param <E> The type of element stored in the node.
 */
public class Node<E> {
    /**
     * The element stored in the node.
     */
    private final E element;
    /**
     * The previous node in the linked list.
     */
    private Node<E> previous;

    /**
     * Constructs a new node with the given element and previous node.
     *
     * @param element  The element to be stored in the node.
     * @param previous The previous node in the linked list.
     */
    public Node(E element, Node<E> previous) {
        this.element = element;
        this.previous = previous;
    }

    /**
     * Sets the previous node in the linked list.
     *
     * @param previous The previous node to be set.
     */
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
}