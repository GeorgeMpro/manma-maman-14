import java.util.Set;

/**
 * Utility class for reading input from user and files.
 */
interface ReadUtil {

    /**
     * Get parameter from user input
     */
    Integer getUserArgument(String m, String argument);

    /**
     * read values from file and insert to set.
     *
     * @param fileName file to read
     * @return set of numbers containing file values
     */
    Set<Integer> fromFileToIntSet(String fileName);

    /**
     * read values from file and insert to set.
     *
     * @param fileName file to read
     * @return set of string containing file values
     */
    Set<String> fromFileToStringSet(String fileName);
}
