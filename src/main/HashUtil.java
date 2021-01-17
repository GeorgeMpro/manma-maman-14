import java.util.Set;

/**
 * Utility class for hashing given keys.
 * Generates non-negative values.
 */
public interface HashUtil {

    /**
     * Update the inner {@link HashParams}
     */
    void setParams(HashParams params);

    /**
     * Generate Hash value from given key.
     * Hash value is limited by array length.
     *
     * @param seed for randomization
     * @param key  to hash
     * @return hashed value
     */
    Integer hashNumber(int seed, int key);

    /**
     * Generate Hash value from given key.
     * Hash value is limited by array length.
     *
     * @param seed for randomization
     * @param key  to hash
     * @return hashed value
     */
    Integer hashString(int seed, String key);

    /**
     * Check whether given index of the bit set is active
     */
    boolean isSet(int index);

    /**
     * Insert given values to hash table.
     *
     * @param stringSet set of unique values to insert
     */
    void stringSetToHashTable(Set<String> stringSet);

    /**
     * Hash given key to table according to specified k,m values.
     *
     * @param key to hash
     */
    void hashIntToTable(int key);

    /**
     * Check whether given key appears to be already hashed in table
     *
     * @param key to check
     * @return is in table
     */
    boolean isAlreadyHashed(int key);

    /**
     * Check whether given values already appear hashed in the hash table without inserting them
     *
     * @param stringSet values to check
     * @return number items which already exist in table
     */
    int countAlreadyHashedStr(Set<String> stringSet);

    /**
     * Check whether given values already appear hashed in the hash table without inserting them
     *
     * @param set values to check
     * @return number items which already exist in table
     */
    int countAlreadyHashedInt(Set<Integer> set);
}
