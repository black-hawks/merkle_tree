package blockchainMerkleTree.blockchain;

import blockchainMerkleTree.dataStructure.linkedList.LinkedList;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blockchain {
    private final LinkedList<Block> blocks;
    private final Map<String, AbstractMap.SimpleEntry<Integer, Block>> transactionIndex;

    public Blockchain() {
        this.blocks = new LinkedList<>();
        this.transactionIndex = new HashMap<>();
    }

    public void addBlock(Block block) {
        this.blocks.insert(block);
        this.updateTransactionIndex(block);
    }

    private void updateTransactionIndex(Block block) {
        List<Transaction> transactions = block.getTransactions();
        for (int i = 0, transactionsSize = transactions.size(); i < transactionsSize; i++) {
            Transaction transaction = transactions.get(i);
            transactionIndex.put(transaction.getId(), new AbstractMap.SimpleEntry<>(i, block));
        }
    }

    public AbstractMap.SimpleEntry<Integer, Block> getTransactionBlock(String transactionId) {
        return transactionIndex.get(transactionId);
    }
}
