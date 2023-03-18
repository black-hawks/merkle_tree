import blockchain.Block;
import blockchain.Transaction;
import dataStructure.merkleTree.MerkleTree;
import miner.Miner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BlockIntegrityTest {
    static Block block;

    @BeforeEach
    public void createBlock() throws IOException {
        Miner miner = new Miner();
        block = miner.createBlock();
    }

    @Test
    @DisplayName("Block is not tampered")
    public void blockIsNotTampered() {
        String actualHash = block.getMerkleRoot().getHashValue();
        List<Transaction> transactions = block.getTransactions();
        String expectedHash = MerkleTree.generateMerkleRoot(transactions).getHashValue();
        assertEquals(expectedHash, actualHash, "Block merkle root hash was expected to be correct");
    }

    @Test
    @DisplayName("Block is tampered by appending an invalid transaction")
    public void blockIsTamperedWithNewTransaction() {
        Transaction invalidTransaction = new Transaction("XXX", "victim", "attacker", 100000, System.currentTimeMillis());
        block.getTransactions().add(invalidTransaction);
        String actualHash = block.getMerkleRoot().getHashValue();
        List<Transaction> transactions = block.getTransactions();
        String expectedHash = MerkleTree.generateMerkleRoot(transactions).getHashValue();
        assertNotEquals(expectedHash, actualHash, "Block merkle root hash was expected to be incorrect");
    }

    @Test
    @DisplayName("Block is tampered by replacing a valid transaction")
    public void blockIsTamperedByReplacingTransaction() {
        Transaction invalidTransaction = new Transaction("XXX", "victim", "attacker", 100000, System.currentTimeMillis());
        block.getTransactions().add(0, invalidTransaction);
        String actualHash = block.getMerkleRoot().getHashValue();
        List<Transaction> transactions = block.getTransactions();
        String expectedHash = MerkleTree.generateMerkleRoot(transactions).getHashValue();
        assertNotEquals(expectedHash, actualHash, "Block merkle root hash was expected to be incorrect");
    }

    @Test
    @DisplayName("Block is tampered by changing a valid transaction")
    public void blockIsTamperedByChangingTransaction() {
        block.getTransactions().get(0).setAmount(999999);
        String actualHash = block.getMerkleRoot().getHashValue();
        List<Transaction> transactions = block.getTransactions();
        String expectedHash = MerkleTree.generateMerkleRoot(transactions).getHashValue();
        assertNotEquals(expectedHash, actualHash, "Block merkle root hash was expected to be incorrect");
    }
}