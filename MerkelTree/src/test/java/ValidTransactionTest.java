import blockchain.Block;
import blockchain.Blockchain;
import dataStructure.merkleTree.MerkleTree;
import dataStructure.merkleTree.MerkleTreeNode;
import miner.Miner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidTransactionTest {
    static Blockchain blockchain;

    @BeforeAll
    public static void createBlockchain() throws IOException {
        blockchain = new Blockchain();
        Miner miner = new Miner(blockchain);
        miner.mine();
    }

    @Test
    public void validTransaction() {
        AbstractMap.SimpleEntry<Integer, Block> entry = blockchain.getTransactionBlock("2e1816ea-a330-4313-a7d8-4635da002d98");
        String actualHash = entry.getValue().getMerkleRoot().getHashValue();
        MerkleTree merkleTree = new MerkleTree(entry.getValue().getTransactions());
        merkleTree.getMerkleRoot();
        LinkedHashMap<MerkleTreeNode, String> merklePath = merkleTree.getMerklePath(entry.getKey());
        System.out.println("Merkel Proof= "+ merkleTree.verifyMerkleProof(merklePath));
        String expectedHash = merkleTree.getMerkleProof(merklePath);
        assertEquals(expectedHash, actualHash, "Block merkle proof hash was expected to be correct");
    }
}
