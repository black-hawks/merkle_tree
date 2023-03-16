package DataStructures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import Util.Encrypt;


/**
 * MerkleTree, This class will contain all the function
 * that can be executed on MerkleTree
 * @author vivek
 *
 */
public class MerkleTree {
    private List<Transaction> transactions;
    private List<MerkleTreeNode> merkleTree;
    
    private final String left="LEFT";
    private final String right="RIGHT";
   
    
    

    public MerkleTree(List<Transaction> transactions) {
        this.transactions = transactions;
        this.merkleTree = new ArrayList<>();
    }

    public MerkleTreeNode getMerkleRoot() {
    	MerkleTreeNode merkleRoot = generateTree(transactions);
        return merkleRoot;
    }
 
    /**
     * This Method take list of transaction and then first encrypt the Transactions
     * Then calls  buildMerkleTree to create the tree
     * @param transactions List of transactions that will be part of MerkleTree
     * @return MerkleTreeNode The root node of MerkleTree
     */
    public MerkleTreeNode generateTree(List<Transaction> transactions) {
        List<MerkleTreeNode> childNodes = new ArrayList<>();

        for (Transaction transaction : transactions) {
            childNodes.add(new MerkleTreeNode(null, null,Encrypt.encryptThisTransaction(transaction) ));
        }
        merkleTree.addAll(childNodes);

        return buildMerkleTree(childNodes);
    }

    /**
     * This method takes List of child nodes for the MerkleTree
     * Then generate hash for each pair of child nodes, this hash will be the hashValue of ParentNode
     * If the number of child nodes are odd, then to create parent node we first 
     * create sibling right node which is copy of left most child
     * such 
     * @param children List of Child nodes
     * @return MerkleTreeRoot Root of the MerkleTree
     */
    private MerkleTreeNode buildMerkleTree(List<MerkleTreeNode> children) {
        List<MerkleTreeNode> parents = new ArrayList<>();

        while (children.size() != 1) {
            int index = 0, length = children.size();
            while (index < length) {
            	MerkleTreeNode leftChild = children.get(index);
            	MerkleTreeNode rightChild = null;

                if ((index + 1) < length) {
                    rightChild = children.get(index + 1);
                } else {
                    rightChild = new MerkleTreeNode(null, null, leftChild.getHashValue());
                }

                String parentHash = Encrypt.generateHash(leftChild.getHashValue() + rightChild.getHashValue());
                parents.add(new MerkleTreeNode(leftChild, rightChild, parentHash));
                index += 2;
            }
            merkleTree.addAll(parents);
            children = parents;
            parents = new ArrayList<>();
        }
        return children.get(0);
    }

    /**
     * Simple Traversal Method which first prints the parent
     * then the child element (left to right)
     * @param root Root of the MerkleTree
     */
    public void printLevelOrderTraversal(MerkleTreeNode root) {
        if (root == null) {
            return;
        }

        if ((root.getLeft() == null && root.getRight() == null)) {
            System.out.println(root.getHashValue());
        }
        Queue<MerkleTreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
        	MerkleTreeNode node = queue.poll();
            if (node != null) {
                System.out.println(node.getHashValue());
            } else {
                System.out.println();
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }

            if (node != null && node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node != null && node.getRight() != null) {
                queue.add(node.getRight());
            }

        }
        
   
    }
    
    /** Find the Merkle Path 
     * 
     * @param index leaf node index
     * @return LinkedHashMap the MerkleTreeNode and its location with respect to its parent
     */
    public LinkedHashMap<MerkleTreeNode,String> getMerklePath(int index) {
    	LinkedHashMap<MerkleTreeNode,String> merklePath = new LinkedHashMap<>();
        int currentIndex = index;
        if(currentIndex % 2 == 0) {
        	merklePath.put(merkleTree.get(currentIndex),left);
        }else {
        	merklePath.put(merkleTree.get(currentIndex),right);
        }
        for (int i = 1; currentIndex < (merkleTree.size()-1); i *= 2) {
        	//Enter sibling of the current index
        	if (currentIndex % 2 == 0) {
                merklePath.put(merkleTree.get(currentIndex + 1),right);
            } else {
                merklePath.put(merkleTree.get(currentIndex - 1),left);
            }
        	currentIndex = currentIndex / 2 + merkleTree.size() / 2+1;
        }
        return merklePath;
    }
    
    /**
     * Compare the MeklePath with Current Root hash Values
     * @param merklePath take the merkle path
     * @return boolean true if proof is verified else false
     */
    public boolean getMerkleProof(LinkedHashMap<MerkleTreeNode,String> merklePath ) {
        System.out.println("Generating Merkle Proof...");
        Set<MerkleTreeNode> hashKeys= merklePath.keySet();
        MerkleTreeNode[] nodeArray= hashKeys.toArray(new MerkleTreeNode[hashKeys.size()]);
        String currHash=nodeArray[0].hashValue;
        for (int i=1;i<nodeArray.length;i++) {
        	String nodeLocation=merklePath.get(nodeArray[i]);
        	if(nodeLocation.equals(left)) {
        		currHash = Encrypt.generateHash(nodeArray[i].getHashValue() + currHash);
        	}else {
        		currHash = Encrypt.generateHash(currHash+ nodeArray[i].getHashValue());
        	}
        }
        
        if(currHash.equals(getMerkleRoot().hashValue)) {
        	return true;
        }
        return false;
    }
}

