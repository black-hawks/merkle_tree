package blockchainMerkleTree.dataStructure.merkleTree;

import blockchainMerkleTree.blockchain.Transaction;
import blockchainMerkleTree.util.Encrypt;

import java.util.*;


/**
 * MerkleTree, This class will contain all the function
 * that can be executed on MerkleTree
 */
public class MerkleTree {

    private List<Transaction> transactions;

    public MerkleTree(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public MerkleTreeNode generateMerkleRoot() {
        List<MerkleTreeNode> childNodes = new ArrayList<>();
        for (Transaction transaction : transactions) {
            childNodes.add(new MerkleTreeNode(null, null, Encrypt.encryptThisTransaction(transaction)));
        }
        return buildMerkleTree(childNodes);
    }

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
                System.out.print(node.hashValue().substring(0, 4) + " ");

                if (node.left() != null) {
                    queue.offer(node.left());
                }

                if (node.right() != null) {
                    queue.offer(node.right());
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
                    rightChild = new MerkleTreeNode(null, null, leftChild.hashValue());
                }
                String parentHash = Encrypt.generateHash(leftChild.hashValue() + rightChild.hashValue());
                MerkleTreeNode parent = new MerkleTreeNode(leftChild, rightChild, parentHash);
                parents.add(parent);
                index += 2;
            }
            children = parents;
            parents = new ArrayList<>();
        }
        return children.get(0);
    }

    public static Deque<Map.Entry<MerkleTreeNode, NodeDirection>> getMerklePath(MerkleTreeNode root, int transactionIndex) {
        long startTime = System.nanoTime();
        Deque<Map.Entry<MerkleTreeNode, NodeDirection>> path = new ArrayDeque<>();
        int height = getHeight(root);
        int start = 0;
        int end = (int) (Math.pow(2, height) - 1);
        while (root.left() != null) {
            int median = (end + start) / 2;
            if (transactionIndex <= median) {
                path.push(new AbstractMap.SimpleEntry<>(root.right(), NodeDirection.RIGHT));
                root = root.left();
                end = median;
            } else {
                path.push(new AbstractMap.SimpleEntry<>(root.left(), NodeDirection.LEFT));
                root = root.right();
                start = median + 1;
            }
        }
        System.out.println("Took " + (double) (System.nanoTime() - startTime) / 1000000 + " ms to find the merkle path in a tree of height " + height);
        return path;
    }

    //
//    /**
//     * Compare the MeklePath with Current Root hash Values
//     *
//     * @param merklePath take the merkle path
//     * @return boolean true if proof is verified else false
//     */
    public static String getMerkleProof(String transactionHash, Deque<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath) {
        long startTime = System.nanoTime();
        int merklePathLength = merklePath.size();
        String currentHash = transactionHash;
        while (!merklePath.isEmpty()) {
            Map.Entry<MerkleTreeNode, NodeDirection> path = merklePath.pop();
            if (path.getValue().equals(NodeDirection.LEFT)) {
                currentHash = Encrypt.generateHash(path.getKey().hashValue() + currentHash);
            } else {
                currentHash = Encrypt.generateHash(currentHash + path.getKey().hashValue());
            }
        }
        System.out.println("Took " + (double) (System.nanoTime() - startTime) / 1000000 + " ms to get the merkle proof where merkle path is of length " + merklePathLength);
        return currentHash;
    }

    private static int getHeight(MerkleTreeNode root) {
        int height = 0;
        while (root.left() != null) {
            height++;
            root = root.left();
        }
        return height;
    }
}

