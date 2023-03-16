/**
 * A block in a blockchain.
 */
package DataStructures;

import java.util.List;

public class Block {
    private final MerkleTree merkleRoot;
    private final long timestamp;
    private Block previousBlock;
    private Block nextBlock;
    private List<Transaction> transactions;

    /**
     * Creates a new block with the given parameters.
     *
     * @param merkleRoot   The Merkle root hash of the transactions in the block.
     * @param timestamp    The timestamp when the block was created.
     * @param previousBlock The previous block in the blockchain.
     * @param nextBlock     The next block in the blockchain.
     * @param transactions The list of transactions included in the block.
     */
    public Block(MerkleTree merkleRoot, long timestamp, Block previousBlock, Block nextBlock, List<Transaction> transactions) {
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
        this.previousBlock = previousBlock;
        this.nextBlock = nextBlock;
        this.transactions = transactions;
    }

    /**
     * Returns the Merkle root hash of the transactions in the block.
     *
     * @return The Merkle root hash of the transactions in the block.
     */
    public MerkleTree getMerkleRoot() {
        return merkleRoot;
    }

    /**
     * Returns the timestamp when the block was created.
     *
     * @return The timestamp when the block was created.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the previous block in the blockchain.
     *
     * @return The previous block in the blockchain.
     */
    public Block getPreviousBlock() {
        return previousBlock;
    }

    /**
     * Sets the previous block in the blockchain.
     *
     * @param previousBlock The previous block in the blockchain.
     */
    public void setPreviousBlock(Block previousBlock) {
        this.previousBlock = previousBlock;
    }

    /**
     * Returns the next block in the blockchain.
     *
     * @return The next block in the blockchain.
     */
    public Block getNextBlock() {
        return nextBlock;
    }

    /**
     * Sets the next block in the blockchain.
     *
     * @param nextBlock The next block in the blockchain.
     */
    public void setNextBlock(Block nextBlock) {
        this.nextBlock = nextBlock;
    }

    /**
     * Returns the list of transactions included in the block.
     *
     * @return The list of transactions included in the block.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}

