package blockchainMerkleTree.blockchain;

import blockchainMerkleTree.dataStructure.linkedList.LinkedList;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Blockchain class represents a chain of blocks in a blockchain network.
 * <p>
 * It consists of a linked list of blocks and a transaction index.
 * <p>
 * A transaction index is a hash map that stores each transaction's ID and its location
 * <p>
 * within the blockchain. This index allows for quick access to a specific transaction within the
 * <p>
 * blockchain.
 * <p>
 * The class provides methods for adding a block to the blockchain and updating the transaction index.
 * <p>
 * It also provides a method for getting a specific transaction's location within the blockchain.
 */
public class Blockchain {
    private final LinkedList<Block> blocks; // A linked list of blocks in the blockchain.
    private final Map<String, AbstractMap.SimpleEntry<Integer, Block>> transactionIndex; // A transaction index for quick access to transactions in the blockchain.

    /**
     * Creates a new instance of the Blockchain class.
     */
    public Blockchain() {
        this.blocks = new LinkedList<>();
        this.transactionIndex = new HashMap<>();
    }

    /**
     * Adds a block to the blockchain.
     *
     * @param block The block to be added to the blockchain.
     */
    public void addBlock(Block block) {
        this.blocks.insert(block);
        this.updateTransactionIndex(block);
    }

    /**
     * Updates the transaction index with the transactions in the given block.
     *
     * @param block The block to be added to the transaction index.
     */
    private void updateTransactionIndex(Block block) {
        List<Transaction> transactions = block.getTransactions();
        for (int i = 0, transactionsSize = transactions.size(); i < transactionsSize; i++) {
            Transaction transaction = transactions.get(i);
            transactionIndex.put(transaction.getId(), new AbstractMap.SimpleEntry<>(i, block));
        }
    }

    /**
     * Gets the location of a specific transaction within the blockchain.
     *
     * @param transactionId The ID of the transaction to be located.
     * @return The location of the transaction within the blockchain as an AbstractMap.SimpleEntry
     * containing the index of the transaction within its block and the block itself.
     */
    public AbstractMap.SimpleEntry<Integer, Block> getTransactionBlock(String transactionId) {
        return transactionIndex.get(transactionId);
    }
}
