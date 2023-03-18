
package blockchain;

import dataStructure.merkleTree.MerkleTree;
import dataStructure.merkleTree.MerkleTreeNode;

import java.util.List;

/**
 * This class represents the Block which computes the merkle hash for the list of transactions present in this block.
 */
public class Block {

    /**
     * The Merkle root of the list of transactions present in the block.
     */
    private final MerkleTreeNode merkleRoot;

    /**
     * The timestamp when the block was created.
     */
    private final long timestamp;

    /**
     * The list of transactions included in the block.
     */
    private final List<Transaction> transactions;

    /**
     * Creates a new block with the given parameters.
     *
     * @param timestamp    the timestamp when the block was created
     * @param transactions the list of transactions included in the block
     */
    public Block(long timestamp, List<Transaction> transactions) {
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.merkleRoot = MerkleTree.generateMerkleRoot(transactions);
    }

    /**
     * Returns the Merkle root hash of the transactions in the block.
     *
     * @return the Merkle root hash of the transactions in the block
     */
    public MerkleTreeNode getMerkleRoot() {
        return merkleRoot;
    }

    /**
     * Returns the timestamp when the block was created.
     *
     * @return the timestamp when the block was created
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the list of transactions included in the block.
     *
     * @return the list of transactions included in the block
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}


