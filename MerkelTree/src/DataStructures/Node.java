/**
 * This represents a block in a blockchain.
 */
package DataStructures;

public class Node {

    /**
     * The block of the node.
     */
    private final Block merkleBlock;

    /**
     * The previous node in the blockchain.
     */
    private final Node previousNode;

    /**
     * The next node in the blockchain.
     */
    private final Node nextNode;

    /**
     * Creates a new node with the given parameters.
     *
     * @param merkleBlock   the block of the node
     * @param previousNode  the previous node in the blockchain
     * @param nextNode      the next node in the blockchain
     */
    public Node(Block merkleBlock, Node previousNode, Node nextNode) {
        this.merkleBlock = merkleBlock;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    /**
     * Returns the block of the node.
     *
     * @return the block of the node
     */
    public Block getMerkleBlock() {
        return merkleBlock;
    }

    /**
     * Returns the previous node in the blockchain.
     *
     * @return the previous node in the blockchain
     */
    public Node getPreviousNode() {
        return previousNode;
    }

    /**
     * Returns the next node in the blockchain.
     *
     * @return the next node in the blockchain
     */
    public Node getNextNode() {
        return nextNode;
    }
}
