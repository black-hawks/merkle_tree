package util;

import dataStructure.merkleTree.MerkleTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreePrinter {
    // Prints the given binary tree level by level
    public static void printTree(MerkleTreeNode root) {
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

            System.out.println("\n"); // Move to next line after printing each level
        }
    }
}

