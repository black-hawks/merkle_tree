package dataStructure.merkleTree;

import blockchain.Transaction;
import util.Encrypt;

import java.util.*;


/**
 * MerkleTree, This class will contain all the function
 * that can be executed on MerkleTree
 */
public class MerkleTree {

    public static MerkleTreeNode generateMerkleRoot(List<Transaction> transactions) {
        List<MerkleTreeNode> childNodes = new ArrayList<>();
        for (Transaction transaction : transactions) {
            childNodes.add(new MerkleTreeNode(null, null, Encrypt.encryptThisTransaction(transaction)));
        }
        return buildMerkleTree(childNodes);
    }

    /**
     * This Method take list of transaction and then first encrypt the Transactions
     * Then calls  buildMerkleTree to create the tree
     *
     * @param transactions List of transactions that will be part of MerkleTree
     * @return MerkleTreeNode The root node of MerkleTree
     */
//    public static MerkleTreeNode generateTree(List<Transaction> transactions) {
//
//    }

    /**
     * This method takes List of child nodes for the MerkleTree
     * Then generate hash for each pair of child nodes, this hash will be the hashValue of ParentNode
     * If the number of child nodes are odd, then to create parent node we first
     * create sibling right node which is copy of left most child
     * such
     *
     * @param children List of Child nodes
     * @return MerkleTreeRoot Root of the MerkleTree
     */
    private static MerkleTreeNode buildMerkleTree(List<MerkleTreeNode> children) {
        List<MerkleTreeNode> parents = new ArrayList<>();

        while (children.size() != 1) {
            int index = 0, length = children.size();
            while (index < length) {
                MerkleTreeNode leftChild = children.get(index);
                MerkleTreeNode rightChild;

                if ((index + 1) < length) {
                    rightChild = children.get(index + 1);
                } else {
                    rightChild = new MerkleTreeNode(null, null, leftChild.getHashValue());
                }
                String parentHash = Encrypt.generateHash(leftChild.getHashValue() + rightChild.getHashValue());
                MerkleTreeNode parent = new MerkleTreeNode(leftChild, rightChild, parentHash);
                leftChild.setParent(parent);
                rightChild.setParent(parent);
                parents.add(parent);
                index += 2;
            }
            children = parents;
            parents = new ArrayList<>();
        }
        return children.get(0);
    }

    public static Stack<Map.Entry<MerkleTreeNode, NodeDirection>> getMerklePath(MerkleTreeNode root, int transactionIndex) {
        Stack<Map.Entry<MerkleTreeNode, NodeDirection>> path = new Stack<>();
        int height = getHeight(root);
        int start = 0;
        int end = (int) (Math.pow(2, height) - 1);
        while (root.getLeft() != null) {
            int median = (end + start) / 2;
            if (transactionIndex <= median) {
                path.push(new AbstractMap.SimpleEntry<>(root.getRight(), NodeDirection.RIGHT));
                root = root.getLeft();
                end = median;
            } else {
                path.push(new AbstractMap.SimpleEntry<>(root.getLeft(), NodeDirection.LEFT));
                root = root.getRight();
                start = median + 1;
            }
        }
        return path;
    }

    //
//    /**
//     * Compare the MeklePath with Current Root hash Values
//     *
//     * @param merklePath take the merkle path
//     * @return boolean true if proof is verified else false
//     */
    public static String getMerkleProof(String transactionHash, Stack<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath) {
        System.out.println("Generating Merkle Proof...");
        String currentHash = transactionHash;
        while (!merklePath.isEmpty()) {
            Map.Entry<MerkleTreeNode, NodeDirection> path = merklePath.pop();
            if (path.getValue().equals(NodeDirection.LEFT)) {
                currentHash = Encrypt.generateHash(path.getKey().getHashValue() + currentHash);
            } else {
                currentHash = Encrypt.generateHash(currentHash + path.getKey().getHashValue());
            }
        }
        return currentHash;
    }

    private static int getHeight(MerkleTreeNode root) {
        int height = 0;
        while (root.getLeft() != null) {
            height++;
            root = root.getLeft();
        }
        return height;
    }
}

