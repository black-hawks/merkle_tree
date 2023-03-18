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

    private final MerkleTreeNode left; // The left child node of this node.
    private final MerkleTreeNode right; // The right child node of this node.
    private final String hashValue; // The hash value of this node.
    /**
     Constructs a new MerkleTreeNode object with the specified left and right child nodes and hash value.
     @param left the left child node of this node.
     @param right the right child node of this node.
     @param hashValue the hash value of this node.
     */
    public MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right, String hashValue) {
        this.left = left;
        this.right = right;
        this.hashValue = hashValue;
    }
    /**
     Gets the left child node of this node.
     @return the left child node.
     */
    public MerkleTreeNode getLeft() {
        return left;
    }
    /**
     Gets the right child node of this node.
     @return the right child node.
     */
    public MerkleTreeNode getRight() {
        return right;
    }
    /**
     Gets the hash value of this node.
     @return the hash value.
     */
    public String getHashValue() {
        return hashValue;
    }
    /**

     Returns a string representation of this node, showing only the first 4 characters of its hash value for readability.
     @return a string representation of this node.
     */
    public String toString() {
        return "MerkleTreeNode{" +
                "hashValue='" + hashValue.substring(0, 4) + '\'' +
                '}';
    }
}