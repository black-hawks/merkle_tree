import blockchain.Transaction;
import dataStructure.merkleTree.MerkleTree;
import dataStructure.merkleTree.MerkleTreeNode;
import dataStructure.merkleTree.NodeDirection;
import miner.Miner;
import util.Encrypt;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Miner miner = new Miner();
        List<Transaction> transactions = miner.getTransactions();
        long startTime = System.currentTimeMillis();
        MerkleTreeNode root = MerkleTree.generateMerkleRoot(transactions);
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + " ms to generate Merkle tree for "
                + transactions.size() + " transactions");
        Stack<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, 6);
        MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transactions.get(6)), merklePath);

    }

}
