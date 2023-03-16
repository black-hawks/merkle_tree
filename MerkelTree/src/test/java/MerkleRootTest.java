import blockchain.Blockchain;
import miner.Miner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class MerkleRootTest {
    static Blockchain blockchain;

    @BeforeAll
    public static void createBlockChain() throws IOException {
        blockchain = new Blockchain();
        Miner miner = new Miner(blockchain);
        miner.mine();
    }

    @Test
    public void testMerklePathTest1() {
    }


}