package blockchainMerkleTree.dataStructure.merkleTree;

import blockchainMerkleTree.blockchain.Transaction;
import blockchainMerkleTree.util.Encrypt;

import java.util.*;


/**
 * MerkleTree, This class will contain all the function
 * that can be executed on MerkleTree
 */
public class MerkleTree {

    private final List<Transaction> transactions;

    public MerkleTree(List<Transaction> transactions) {
        this.transactions = transactions;
    }


    /**
     * Generates the Merkle Root for the given List of Transaction
     * Creates MerkleTreeNode for all the Transactions and calls buildMerkleTree
     *
     * @return MerkleTreeNode as merkleRoot
     */
    public MerkleTreeNode generateMerkleRoot() {
        List<MerkleTreeNode> childNodes = new ArrayList<>();
        for (Transaction transaction : transactions) {
            childNodes.add(new MerkleTreeNode(null, null, Encrypt.encryptThisTransaction(transaction)));
        }
        return buildMerkleTree(childNodes);
    }

    /**
     * Print the entire merkle tree
     * @param root MerkleTreeNode
     */
    public void printTree(MerkleTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<MerkleTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                MerkleTreeNode node = queue.poll();
                System.out.print(node.getHashValue().substring(0, 4) + " ");

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

            System.out.println();
        }
    }

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
    private MerkleTreeNode buildMerkleTree(List<MerkleTreeNode> children) {
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
                parents.add(parent);
                index += 2;
            }
            children = parents;
            parents = new ArrayList<>();
        }
        return children.get(0);
    }

    /**
     * To generate Merkle Path for the tree
     * Merkle path is minimum number of hashes required to generate the root
     * for the given transaction index
     * @param root
     * @param transactionIndex
     * @return merklePath
     */
    public static Deque<Map.Entry<MerkleTreeNode, NodeDirection>> getMerklePath(MerkleTreeNode root, int transactionIndex) {
        long startTime = System.nanoTime();
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> path = new ArrayDeque<>();
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
        System.out.println("Took " + (double) (System.nanoTime() - startTime) / 1000000 + " ms to find the merkle path in a tree of height " + height);
        return path;
    }


    /**
     * Compare the MeklePath with Current Root hash Values
     *
     * @param merklePath take the merkle path
     * @return boolean true if proof is verified else false
     */
    public static String getMerkleProof(String transactionHash, Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath) {
        long startTime = System.nanoTime();
        int merklePathLength = merklePath.size();
        String currentHash = transactionHash;
        while (!merklePath.isEmpty()) {
            Map.Entry<MerkleTreeNode, NodeDirection> path = merklePath.pop();
            if (path.getValue().equals(NodeDirection.LEFT)) {
                currentHash = Encrypt.generateHash(path.getKey().getHashValue() + currentHash);
            } else {
                currentHash = Encrypt.generateHash(currentHash + path.getKey().getHashValue());
            }
        }
        System.out.println("Took " + (double) (System.nanoTime() - startTime) / 1000000 + " ms to get the merkle proof where merkle path is of length " + merklePathLength);
        return currentHash;
    }

    /**
     * Height of Merkle Tree
     * @param root
     * @return height
     */
    private static int getHeight(MerkleTreeNode root) {
        int height = 0;
        while (root.getLeft() != null) {
            height++;
            root = root.getLeft();
        }
        return height;
    }
}

