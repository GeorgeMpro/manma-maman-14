import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HashTest {
    Integer x;
    int seed;
    static TestUtil util = new TestUtil();
    HashUtil hashUtil = util.getHashUtil();
    HashParams params = util.getParams();
    static ReadUtil readUtil = util.getReadUtil();
    private static Set<String> stringSet;
    private static Set<Integer> integerSet;

    @BeforeAll
    static void beforeAll() {
        stringSet = readUtil.fromFileToStringSet(util.getStrFileName());
        integerSet = readUtil.fromFileToIntSet(util.getIntFileName());
    }

    @BeforeEach
    void init() {
        x = null;
        seed = util.generateRandomSet();
    }

    @Test
    void shouldHashNumber() {
        int key = 1231;
        x = getIntKey(key);
        assertNotNull(x);
    }

    @Test
    void shouldHashString() {
        Integer x;
        String key = "Hello";
        x = hashUtil.hashString(util.generateRandomSet(), key);
        assertNotNull(x);
    }

    @Test
    void hashShouldNotExceedArrayLength() {
        int numKey = 1234578;
        String sKey = "Hellozzz";
        assertTrue(getIntKey(numKey) < util.getMAX_BIT_ARRAY_SIZE(), "Hashed Key " + numKey + " should not exceed array length  " + util.getMAX_BIT_ARRAY_SIZE());
        assertTrue(getStringKey(sKey) < util.getMAX_BIT_ARRAY_SIZE(), "Hashed Key " + numKey + " should not exceed array length  " + util.getMAX_BIT_ARRAY_SIZE());
    }

    @Test
    void shouldReturnNonNegativeKey() {
        assertTrue(0 <= getIntKey(-100));
        assertTrue(0 <= getStringKey("abcEda"));
    }

    @Test
    void shouldCreateBitArrayOfGivenLength() {
        assertLargeEnoughArray(util.getMAX_BIT_ARRAY_SIZE());
        assertLargeEnoughArray(util.getMAX_NUMBER_OF_INPUT_VALUES());
    }

    @Test
    void shouldCheckIfBitIsActive() {
        int index = 100;
        assertFalse(hashUtil.isSet(index));

        params.getBitSet().set(index);
        assertTrue(hashUtil.isSet(index));
    }

    @Test
    void shouldCheckIfInArray() {
        HashParams tmp = new HashParams(1000, 13);
        HashUtil tmpUtil = new HashUtilImpl(tmp);
        int key = 10012312;
        boolean inArray = tmpUtil.isAlreadyHashed(key);
        assertFalse(inArray);

        tmpUtil.hashIntToTable(key);
        assertTrue(tmpUtil.isAlreadyHashed(key));

    }

    @Test
    void shouldHashGivenStringSet() {
        hashUtil.stringSetToHashTable(stringSet);
        int howManyLitBits = params.getBitSet().cardinality();

        assertTrue(stringSet.size() < howManyLitBits, "Should set more bits than values");
    }

    @Test
    void shouldCheck_AlreadyHashedString() {
        int numberOfPositives;
        hashUtil.stringSetToHashTable(stringSet);

        numberOfPositives = hashUtil.countAlreadyHashedStr(stringSet);


        assertEquals(stringSet.size(), numberOfPositives, "Should return true on already hashed values");
    }

    @Test
    void shouldCheck_AlreadyHashedInt() {
        int expectedWithGivenInput = 333692;
        int numberOfPositives;
        hashUtil.stringSetToHashTable(stringSet);

        numberOfPositives = hashUtil.countAlreadyHashedInt(integerSet);


        assertEquals(expectedWithGivenInput, numberOfPositives, "Should return true on already hashed values");

    }

    /*Utility Methods*/
    private void assertLargeEnoughArray(int m) {
        HashParams params = new HashParams(m, 0);
        assertTrue(m <= params.getBitSet().size(), "Should be able to contain given m array length");
    }

    private Integer getIntKey(int numKey) {
        return hashUtil.hashNumber(seed, numKey);
    }

    private Integer getStringKey(String sKey) {
        return hashUtil.hashString(seed, sKey);
    }

}
