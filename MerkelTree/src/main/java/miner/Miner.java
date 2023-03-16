package miner;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.Transaction;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Miner {
    private Blockchain blockchain;

    public Miner(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void mine() throws IOException {
        TransactionReader transactionReader = new TransactionReader("sorted.csv", Duration.ofSeconds(1));
        while (true) {
            List<Transaction> transactions = transactionReader.getTransactions();
            if (transactions == null) {
                break;
            }
            Block block = new Block(transactions.get(0).getTimestamp(), transactions);
            blockchain.addBlock(block);
        }
    }
}
