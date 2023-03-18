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

public class MerkleTreeNode {

    private final MerkleTreeNode left;
    private final MerkleTreeNode right;
    private final String hashValue;

    public MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right, String hashValue) {
        this.left = left;
        this.right = right;
        this.hashValue = hashValue;
    }

    public MerkleTreeNode getLeft() {
        return left;
    }

    public MerkleTreeNode getRight() {
        return right;
    }

    public String getHashValue() {
        return hashValue;
    }

    public String toString() {
        return "MerkleTreeNode{" +
                "hashValue='" + hashValue.substring(0, 4) + '\'' +
                '}';
    }
}