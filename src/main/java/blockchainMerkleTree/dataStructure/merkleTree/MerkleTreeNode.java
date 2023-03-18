package blockchainMerkleTree.dataStructure.merkleTree;

/**
 * MerkleTreeNode class is a POJO class
 * which represent the structure of a node in a MerkleTree
 * <p>
 * Since MerkleTree is a Binary Tree
 * each node in MerkleTree(except leaf node) will have left child, right child and the value
 *
 * @author vivek
 */

public record MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right, String hashValue) {
    public String toString() {
        return "MerkleTreeNode{" +
                "hashValue='" + hashValue.substring(0, 4) + '\'' +
                '}';
    }
}
