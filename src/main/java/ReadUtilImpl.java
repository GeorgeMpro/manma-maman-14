import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ReadUtilImpl implements ReadUtil {
    private final String delimiter = "\\s*,\\s*";

    @Override
    public Integer getUserArgument(String mLength, String argument) {
        String msg = "Invalid " + argument + " [%s]";
        if (mLength.matches("\\d+")) {
            return Integer.parseInt(mLength);
        } else {
            System.out.printf(msg, mLength);
            return null;
        }
    }

    @Override
    public Set<Integer> fromFileToIntSet(String fileName) {
        Set<Integer> integers = new HashSet<>();
        try {
            getNumbersFromCSV(fileName, delimiter, integers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integers;
    }

    @Override
    public Set<String> fromFileToStringSet(String fileName) {
        String line;
        Set<String> stringSet = new HashSet<>();
        try {
            BufferedReader
                    bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(delimiter);
                stringSet.addAll(Arrays.asList(strings));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return stringSet;
    }

    /**
     * Extract numbers from given file into integer set
     *
     * @param fileName  file to read
     * @param delimiter specified file separation
     * @param integers  set to fill
     * @return set of numbers from file
     * @throws IOException
     */
    private Set<Integer> getNumbersFromCSV(String fileName, String delimiter, Set<Integer> integers) throws IOException {
        String line;
        BufferedReader
                bufferedReader = new BufferedReader(new FileReader(fileName));
        while ((line = bufferedReader.readLine()) != null) {

            extractNumbers(delimiter, integers, line);

        }
        return integers;
    }

    /**
     * Go over given line and extract values.
     *
     * @param delimiter specified separating value
     * @param integers  destination for file addition
     * @param line      line to read
     */
    private void extractNumbers(String delimiter, Set<Integer> integers, String line) {
        String[] numbers = line.split(delimiter);
        for (String numberString : numbers) {
            integers.add(Integer.parseInt(numberString));
        }
    }

}
