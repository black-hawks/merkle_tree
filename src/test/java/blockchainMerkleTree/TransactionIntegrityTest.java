package blockchainMerkleTree;

import blockchainMerkleTree.blockchain.Block;
import blockchainMerkleTree.blockchain.Blockchain;
import blockchainMerkleTree.blockchain.Transaction;
import blockchainMerkleTree.dataStructure.merkleTree.MerkleTree;
import blockchainMerkleTree.dataStructure.merkleTree.MerkleTreeNode;
import blockchainMerkleTree.dataStructure.merkleTree.NodeDirection;
import blockchainMerkleTree.miner.Miner;
import blockchainMerkleTree.util.Encrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Deque;
import java.util.Map;

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
    @DisplayName("Transaction is valid")
    public void validTransaction() {
        AbstractMap.SimpleEntry<Integer, Block> entry = blockchain.getTransactionBlock("2e1816ea-a330-4313-a7d8-4635da002d98");
        MerkleTreeNode root = entry.getValue().getMerkleRoot();
        String actualHash = root.hashValue();
        Transaction transaction = entry.getValue().getTransactions().get(entry.getKey());
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, entry.getKey());
        String expectedHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transaction), merklePath);
        assertEquals(expectedHash, actualHash, "Block merkle proof hash was expected to be correct");
    }

    @Test
    @DisplayName("Transaction is tampered")
    public void tamperedTransaction() {
        AbstractMap.SimpleEntry<Integer, Block> entry = blockchain.getTransactionBlock("2e1816ea-a330-4313-a7d8-4635da002d98");
        MerkleTreeNode root = entry.getValue().getMerkleRoot();
        String actualHash = root.hashValue();
        entry.getValue().getTransactions().get(entry.getKey()).setAmount(999999);
        Transaction transaction = entry.getValue().getTransactions().get(entry.getKey());
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, entry.getKey());
        String expectedHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transaction), merklePath);
        assertNotEquals(expectedHash, actualHash, "Block merkle proof hash was expected to be incorrect");
    }

    @Test
    @DisplayName("Transaction is valid, but block is compromised")
    public void compromisedBlock() {
        AbstractMap.SimpleEntry<Integer, Block> entry = blockchain.getTransactionBlock("2e1816ea-a330-4313-a7d8-4635da002d98");
        MerkleTreeNode root = entry.getValue().getMerkleRoot();
        String actualHash = root.hashValue();
        entry.getValue().getTransactions().get(0).setAmount(999999);
        Transaction transaction = entry.getValue().getTransactions().get(entry.getKey());
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, entry.getKey());
        String expectedHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transaction), merklePath);
        assertEquals(expectedHash, actualHash, "Block merkle proof hash was expected to be correct");
    }
}
