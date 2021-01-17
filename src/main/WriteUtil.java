import java.io.File;
import java.util.Set;

/**
 * Utility class for generating values and writing them to file.
 */
public interface WriteUtil {

    /**
     * Write a vile in comma separated value (CSV) format
     *
     * @param name name to generate
     * @param set  unique set of items
     * @return return file containing given values
     */
    File generateInputCSV(String name, Set<String> set);

    /**
     * Write a vile in comma separated value (CSV) format
     *
     * @param name name to generate
     * @param set  unique set of items
     * @return return file containing given values
     */
    File generateInputIntegerCSV(String name, Set<Integer> set);

    /**
     * Generate a set of unique integers.
     *
     * @param inputSize number of unique values to generate
     * @return unique set of integers
     */
    Set<Integer> generateIntegerSet(int inputSize);

    /**
     * Generate a set of unique strings.
     *
     * @param inputSize number of unique values to generate
     * @return unique set of strings
     */
    Set<String> generateStringSet(int inputSize);
}
