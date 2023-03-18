package miner;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.Transaction;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

public class Miner {
    private final Blockchain blockchain;
    private final TransactionReader transactionReader;

    public Miner() throws FileNotFoundException {
        this(new Blockchain());
    }

    public Miner(Blockchain blockchain) throws FileNotFoundException {
        this.blockchain = blockchain;
        transactionReader = new TransactionReader("transactions.csv", Duration.ofSeconds(1));
    }

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

    public Block createBlock() {
        List<Transaction> transactions = this.getTransactions();
        if (transactions == null) {
            return null;
        }
        return new Block(transactions.get(0).getTimestamp(), transactions);
    }

    public List<Transaction> getTransactions() {
        return transactionReader.getTransactions();
    }
}
