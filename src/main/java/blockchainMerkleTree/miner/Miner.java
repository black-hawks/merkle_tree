package blockchainMerkleTree.miner;

import blockchainMerkleTree.blockchain.Block;
import blockchainMerkleTree.blockchain.Blockchain;
import blockchainMerkleTree.blockchain.Transaction;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;


/**
 * A class representing a Miner, responsible for mining blocks from a list of transactions.
 */
public class Miner {
    private final Blockchain blockchain; // The blockchain to which mined blocks will be added.
    private final TransactionReader transactionReader; // A reader for getting transactions from a CSV file.

    /**
     * Constructs a new Miner object with a new instance of a Blockchain object and a default TransactionReader object.
     *
     * @throws FileNotFoundException if the CSV file with transactions cannot be found.
     */
    public Miner() throws FileNotFoundException {
        this(new Blockchain());
    }

    /**
     * Constructs a new Miner object with the specified Blockchain object and a default TransactionReader object.
     *
     * @param blockchain the Blockchain object to use for storing mined blocks.
     * @throws FileNotFoundException if the CSV file with transactions cannot be found.
     */
    public Miner(Blockchain blockchain) throws FileNotFoundException {
        this.blockchain = blockchain;
        transactionReader = new TransactionReader("transactions.csv", Duration.ofSeconds(1));
    }

    /**
     * Mines new blocks by continuously creating and adding them to the blockchain until there are no more transactions left.
     */
    public void mine() {
        long start = System.currentTimeMillis();
        int transactionCount = 0;
        int blockCount = 0;
        while (true) {
            Block block = this.createBlock();
            if (block == null) {
                break;
            }
            blockchain.addBlock(block);
            transactionCount = transactionCount + block.getTransactions().size();
            blockCount++;
        }
        System.out.println("Miner took " + (System.currentTimeMillis() - start) + " ms to mine " + transactionCount
                + " transactions and created " + blockCount + " blocks");
    }

    /**
     * Creates a new block from a list of transactions obtained from the TransactionReader object.
     *
     * @return the newly created Block object or null if there are no more transactions left.
     */
    public Block createBlock() {
        List<Transaction> transactions = this.getTransactions();
        if (transactions == null) {
            return null;
        }
        return new Block(transactions.get(0).getTimestamp(), transactions);
    }

    /**
     * Gets a list of transactions from the TransactionReader object.
     *
     * @return the list of transactions or null if there are no more transactions left.
     */
    public List<Transaction> getTransactions() {
        return transactionReader.getTransactions();
    }
}