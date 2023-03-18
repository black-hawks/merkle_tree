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

/**
 * Simulates the Whole Project To generate Merkle Tree
 */
public class Main {


    /**
     * Simulates generation of MerkleTree for list of transaction
     * and validate the transaction is part of the generated merkle tree
     * by calculating merkle path and comparing against the root of Merkle Tree
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Miner miner = new Miner();
        //Miner gets all the transaction for a block
        List<Transaction> transactions = miner.getTransactions();
        long startTime = System.currentTimeMillis();
        //Pas List of transactions to MerkleTree
        MerkleTree merkleTree = new MerkleTree(transactions);
        //Generate Merkle Tree and return merkle root
        MerkleTreeNode root = merkleTree.generateMerkleRoot();
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + " ms to generate Merkle tree for "
                + transactions.size() + " transactions");
        System.out.println("Merkle root hash: " + root.getHashValue());
        //Now let's validate the transaction in the Block
        //For instance take index : 123
        int transactionIndex = 123;
        System.out.println("Validating transaction at index " + transactionIndex);
        //Find the merkle path for the given transaction index
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, transactionIndex);
        System.out.print("Merkle Path: ");
        //Prints the first 4 character of hash value of each node in merkle path
        for (Map.Entry<MerkleTreeNode, NodeDirection> entry : merklePath) {
            System.out.print(entry.getKey().getHashValue().substring(0, 4) + " => ");
        }
        System.out.println();
        //Verify if the hashed value from the merkle path actually matches with the merkle tree root
        String merkleProofHash = MerkleTree.getMerkleProof(Encrypt.encryptThisTransaction(transactions.get(transactionIndex)), merklePath);
        System.out.println("Merkle proof hash: " + merkleProofHash);
        if (merkleProofHash.equals(root.getHashValue())) {
            System.out.println("Merkle proof hash matches with the root hash value.");
        } else {
            System.out.println("Merkle proof hash does not matches with the root hash value. Tampering detected.");
        }
    }
}
