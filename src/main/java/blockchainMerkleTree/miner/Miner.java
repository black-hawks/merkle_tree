package blockchainMerkleTree.miner;

import blockchainMerkleTree.blockchain.Block;
import blockchainMerkleTree.blockchain.Blockchain;
import blockchainMerkleTree.blockchain.Transaction;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

/**
 * The Miner class is responsible for mining new blocks by creating a block
 * from a list of transactions and adding it to the blockchain.
 */
public class Miner {
    /**
     * The blockchain instance.
     */
    private final Blockchain blockchain;
    /**
     * The transaction reader instance that reads transactions from a CSV file.
     */
    private final TransactionReader transactionReader;

    /**
     * Constructs a new Miner instance with a new Blockchain instance.
     *
     * @throws FileNotFoundException if the transactions CSV file cannot be found.
     */
    public Miner() throws FileNotFoundException {
        this(new Blockchain());
    }

    /**
     * Constructs a new Miner instance with a given blockchain instance.
     *
     * @param blockchain the blockchain instance to use.
     * @throws FileNotFoundException if the transactions CSV file cannot be found.
     */
    public Miner(Blockchain blockchain) throws FileNotFoundException {
        this.blockchain = blockchain;
        transactionReader = new TransactionReader("transactions.csv", Duration.ofSeconds(1));
    }


    /**
     * Mines new blocks by creating a block from a list of transactions and adding it to the blockchain.
     * Prints the number of transactions and blocks mined, and the time taken to mine them.
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
     * Creates a new block from a list of transactions read from the transactions CSV file.
     *
     * @return a new block instance, or null if there are no transactions to create a block from.
     */
    public Block createBlock() {
        List<Transaction> transactions = this.getTransactions();
        if (transactions == null) {
            return null;
        }
        return new Block(transactions.get(0).getTimestamp(), transactions);
    }


    /**
     * Returns a list of transactions read from the transactions CSV file.
     *
     * @return a list of transaction instances.
     */
    public List<Transaction> getTransactions() {
        return transactionReader.getTransactions();
    }
}
