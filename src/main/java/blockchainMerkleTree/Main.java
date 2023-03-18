package blockchainMerkleTree;

import blockchainMerkleTree.blockchain.Transaction;
import blockchainMerkleTree.dataStructure.merkleTree.MerkleTree;
import blockchainMerkleTree.dataStructure.merkleTree.MerkleTreeNode;
import blockchainMerkleTree.dataStructure.merkleTree.NodeDirection;
import blockchainMerkleTree.miner.Miner;
import blockchainMerkleTree.util.Encrypt;

import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Miner miner = new Miner();
        List<Transaction> transactions = miner.getTransactions();
        long startTime = System.currentTimeMillis();
        MerkleTree merkleTree = new MerkleTree(transactions);
        MerkleTreeNode root = merkleTree.generateMerkleRoot();
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + " ms to generate Merkle tree for "
                + transactions.size() + " transactions");
        System.out.println("Merkle root hash: " + root.hashValue());
        int transactionIndex = 123;
        System.out.println("Validating transaction at index " + transactionIndex);
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, transactionIndex);
        System.out.print("Merkle Path: ");
        for (Map.Entry<MerkleTreeNode, NodeDirection> entry : merklePath) {
            System.out.print(entry.getKey().hashValue().substring(0, 4) + " => ");
        }
        System.out.println();
        String merkleProofHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transactions.get(transactionIndex)), merklePath);
        System.out.println("Merkle proof hash: " + merkleProofHash);
        if (merkleProofHash.equals(root.hashValue())) {
            System.out.println("Merkle proof hash matches with the root hash value.");
        } else {
            System.out.println("Merkle proof hash does not matches with the root hash value. Tampering detected.");
        }
    }
}
