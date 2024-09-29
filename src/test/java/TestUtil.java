/**
 * Utility class for setting up tests and giving default values.
 */
public class TestUtil {

    private final String inputNumbers = "src/resources/input_number_1_000_000.txt";
    private final String inputStrings = "src/resources/input_string_1_000_000.txt";
    private final int MAX_BIT_ARRAY_SIZE = 32 * getPower(10, 6);
    private final int MAX_NUMBER_OF_HASH_FUNCTIONS = 13;
    private final int MAX_NUMBER_OF_INPUT_VALUES = getPower(10, 6);
    HashParams params;
    HashUtil hashUtil;
    ReadUtil readUtil;
    WriteUtil writeUtil;

    /*setup*/
    public TestUtil() {
        this.params = new HashParams(
                MAX_BIT_ARRAY_SIZE,
                MAX_NUMBER_OF_HASH_FUNCTIONS);
        this.hashUtil = new HashUtilImpl(params);
        this.readUtil = new ReadUtilImpl();
        this.writeUtil = new WriteUtilImpl();
    }

    public TestUtil(int m, int k) {
        this.params = new HashParams(m, k);
        this.hashUtil = new HashUtilImpl(params);
        this.readUtil = new ReadUtilImpl();
        this.writeUtil = new WriteUtilImpl();
    }

    /*Getters*/
    public String getIntFileName() {
        return inputNumbers;
    }

    public String getStrFileName() {
        return inputStrings;
    }

    public WriteUtil getWriteUtil() {
        return writeUtil;
    }

    public int getMAX_NUMBER_OF_INPUT_VALUES() {
        return MAX_NUMBER_OF_INPUT_VALUES;
    }

    public int getMAX_BIT_ARRAY_SIZE() {
        return MAX_BIT_ARRAY_SIZE;
    }

    public int getMAX_NUMBER_OF_HASH_FUNCTIONS() {
        return MAX_NUMBER_OF_HASH_FUNCTIONS;
    }

    public HashParams getParams() {
        return params;
    }

    public HashUtil getHashUtil() {
        return hashUtil;
    }

    public ReadUtil getReadUtil() {
        return readUtil;
    }

    /*Utility Functions*/
    int getPower(int base, int exponent) {
        return (int) Math.pow(base, exponent);
    }


    public int generateRandomSet() {
        return (int) (Math.random() * Integer.MAX_VALUE);
    }
}


