import java.util.BitSet;

/**
 * Utility class for using accessing given hashing parameters
 */
public class HashParams {
    private int m;
    private int k;
    private BitSet bitSet;

    /*Constructor*/
    public HashParams(int m, int k) {
        this.m = m;
        this.k = k;
        this.bitSet = new BitSet(m);
    }

    /*Getters*/
    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    public BitSet getBitSet() {
        return bitSet;
    }
}
