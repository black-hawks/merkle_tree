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
        while (true) {
            Block block = this.createBlock();
            if (block == null) {
                break;
            }
            blockchain.addBlock(block);
        }
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
