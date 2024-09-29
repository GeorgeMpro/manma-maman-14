
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * Utility class for hashing functionality.
 * Uses the Google Guava Murmur3 Hash implementation for hashing.
 * Note: Marked unstable due to the implementation being beta.
 */
public class HashUtilImpl implements HashUtil {

    private HashParams params;

    public HashUtilImpl(HashParams params) {
        this.params = params;
    }

    @Override
    public void setParams(HashParams params) {
        this.params = params;
    }

    @Override
    public Integer hashNumber(int seed, int key) {
        HashFunction hf = Hashing.murmur3_32(seed);
        int hashedValue = hf.hashLong(key)
                .asInt();

        return formatHashedValue(hashedValue);
    }

    @Override
    public Integer hashString(int seed, String key) {
        HashFunction hf = Hashing.murmur3_32(seed);
        int hashedValue = hf.hashString(key, StandardCharsets.UTF_8)
                .asInt();

        return formatHashedValue(hashedValue);
    }

    /**
     * Keep the hashed value a non-negative value <=m.
     */
    private int formatHashedValue(int hashedValue) {
        boolean isNegative = hashedValue < 0;
        if (isNegative) {
            hashedValue *= -1;
        }

        return hashedValue % params.getM();
    }

    @Override
    public void stringSetToHashTable(Set<String> stringSet) {
        for (String val : stringSet) {
            insertStringToTable(val);
        }
    }

    /**
     * Insert key to hash table with given {@link HashParams} values.
     */
    private void insertStringToTable(String val) {
        int i = 0;
        int toSet;
        do {
            toSet = hashString(i, val);
            params.getBitSet().set(toSet);
            i++;
        } while (i < params.getK());
    }

    @Override
    public void hashIntToTable(int key) {
        int i = 0;
        do {
            params.getBitSet().set(hashNumber(i, key));
            i++;
        } while (i < params.getK());
    }

    @Override
    public boolean isSet(int index) {
        return params.getBitSet().get(index);
    }


    @Override
    public boolean isAlreadyHashed(int key) {
        int i = 0;
        boolean exists;
        do {
            exists = isSet(hashNumber(i, key));
            i++;
        } while (i < params.getK() && !exists);

        return exists;
    }

    @Override
    public int countAlreadyHashedStr(Set<String> stringSet) {
        return countFalsePosStr(stringSet);
    }

    @Override
    public int countAlreadyHashedInt(Set<Integer> set) {
        return countFalsePosInt(set);
    }

    /**
     * Go over all unique set values and count number of items that appear to exists.
     */
    private int countFalsePosStr(Set<String> set) {
        int falsePos = 0;
        int k = params.getK();
        for (String val : set) {
            falsePos += checkStrValue(k, val);
        }
        return falsePos;
    }

    /**
     * Go over all unique set values and count number of items that appear to exists.
     */
    private int countFalsePosInt(Set<Integer> set) {
        int falsePos = 0;
        int k = params.getK();
        for (Integer val : set) {
            falsePos += checkIntValue(k, val);
        }
        return falsePos;
    }


    /**
     * Check whether given key hash value for k hash functions already exists.
     *
     * @param k   number of hash functions
     * @param val key
     * @return 1 or 0 according to result
     */
    private int checkStrValue(int k, String val) {
        int i = 0;
        boolean isAlreadyHashed;
        boolean doesMatch;
        isAlreadyHashed = true;

        do {
            doesMatch = isSet(hashString(i, val));
            if (!doesMatch) {
                isAlreadyHashed = false;
            }
            i++;
        } while (i < k && !doesMatch);

        return (isAlreadyHashed) ? 1 : 0;
    }

    /**
     * Check whether given key hash value for k hash functions already exists.
     *
     * @param k   number of hash functions
     * @param val key
     * @return 1 or 0 according to result
     */
    private int checkIntValue(int k, Integer val) {
        int i = 0;
        boolean isAlreadyHashed;
        boolean doesMatch;
        isAlreadyHashed = true;

        do {
            doesMatch = isSet(hashNumber(i, val));
            if (!doesMatch) {
                isAlreadyHashed = false;
            }
            i++;
        } while (i < k && !doesMatch);

        return (isAlreadyHashed) ? 1 : 0;
    }
}
