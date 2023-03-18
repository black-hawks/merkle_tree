import blockchain.Block;
import blockchain.Blockchain;
import blockchain.Transaction;
import dataStructure.merkleTree.MerkleTree;
import dataStructure.merkleTree.MerkleTreeNode;
import dataStructure.merkleTree.NodeDirection;
import miner.Miner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Encrypt;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransactionIntegrityTest {
    static Blockchain blockchain;

    @BeforeEach
    public void createBlockchain() throws IOException {
        blockchain = new Blockchain();
        Miner miner = new Miner(blockchain);
        miner.mine();
    }

    @Test
    public void validTransaction() {
        AbstractMap.SimpleEntry<Integer, Block> entry = blockchain.getTransactionBlock("2e1816ea-a330-4313-a7d8-4635da002d98");
        MerkleTreeNode root = entry.getValue().getMerkleRoot();
        String actualHash = root.getHashValue();
        Transaction transaction = entry.getValue().getTransactions().get(entry.getKey());
        Stack<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, entry.getKey());
        String expectedHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transaction), merklePath);
        assertEquals(expectedHash, actualHash, "Block merkle proof hash was expected to be correct");
    }

    @Test
    public void tamperedTransaction() {
        AbstractMap.SimpleEntry<Integer, Block> entry = blockchain.getTransactionBlock("2e1816ea-a330-4313-a7d8-4635da002d98");
        MerkleTreeNode root = entry.getValue().getMerkleRoot();
        String actualHash = root.getHashValue();
        entry.getValue().getTransactions().get(entry.getKey()).setAmount(999999);
        Transaction transaction = entry.getValue().getTransactions().get(entry.getKey());
        Stack<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, entry.getKey());
        String expectedHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transaction), merklePath);
        assertNotEquals(expectedHash, actualHash, "Block merkle proof hash was expected to be incorrect");
    }
}
