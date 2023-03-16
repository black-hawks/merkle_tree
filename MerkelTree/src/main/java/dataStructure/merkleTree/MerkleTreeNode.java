package dataStructure.merkleTree;

/**
 * MerkleTreeNode class is a POJO class
 * which represent the structure of a node in a MerkleTree
 * 
 * Since MerkleTree is a Binary Tree
 * each node in MerkleTree(except leaf node) will have left child, right child and the value
 * @author vivek
 *
 */

public class MerkleTreeNode {

	MerkleTreeNode left;
	MerkleTreeNode right;

    String hashValue;


    public MerkleTreeNode getLeft(){
        return left;
    }

    public MerkleTreeNode getRight(){
        return right;
    }

    public String getHashValue(){
        return hashValue;
    }

    public MerkleTreeNode(MerkleTreeNode left, MerkleTreeNode right, String hashValue){
        this.left = left;
        this.right = right;
        this.hashValue=hashValue;
    }
    public void setLeft(MerkleTreeNode left) {
        this.left = left;
    }

    public void setRight(MerkleTreeNode right) {
        this.right = right;
    }

    public void setHashValue(String value) {
        this.hashValue = value;
    }
}
