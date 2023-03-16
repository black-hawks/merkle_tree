package blockchain;

import dataStructure.linkedList.LinkedList;

public class Blockchain {
    private final LinkedList<Block> blocks;

    public Blockchain() {
        this.blocks = new LinkedList<>();
    }

    public void addBlock(Block block) {
        this.blocks.insert(block);
    }
}
