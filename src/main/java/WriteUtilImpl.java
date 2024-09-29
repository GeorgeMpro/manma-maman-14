import java.io.*;
import java.util.*;

public class WriteUtilImpl implements WriteUtil {

    @Override
    public Set<String> generateStringSet(int inputSize) {
        Set<String> stringSet = new HashSet<>();
        boolean shouldChange = false;
        do {
            shouldChange = addValues(stringSet, shouldChange);

        } while (stringSet.size() < inputSize);
        return stringSet;
    }

    @Override
    public File generateInputCSV(String name, Set<String> set) {
        File file = new File(name);
        try {
            writeFile(name, set);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public Set<Integer> generateIntegerSet(int inputSize) {
        int i = 0;
        Set<Integer> integerSet = new HashSet<>();
        Random random = new Random();
        int[] randoms = random.ints(15L * inputSize).distinct().toArray();
        do {
            integerSet.add(randoms[i]);
            i++;
        } while (integerSet.size() < inputSize);

        return integerSet;
    }

    @Override
    public File generateInputIntegerCSV(String name, Set<Integer> set) {
        File file = new File(name);
        try {
            FileWriter fw = new FileWriter(name);
            PrintWriter writer = new PrintWriter(fw);
            for (Integer value : set) {
                writer.write(value.toString());
                writer.write(",");
            }


            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return file;
    }

    /**
     * Switch between sources of randomly generated strings.
     *
     * @param stringSet    destination for file addition
     * @param shouldChange affect which source to choose
     * @return updated value for which source to choose
     */
    private boolean addValues(Set<String> stringSet, boolean shouldChange) {
        stringSet.add(shouldChange ? generateString() : generateAlphaNumeric());
        return !shouldChange;
    }

    /**
     * Generate CSV formatted file
     *
     * @param name file name
     * @param set  values to insert
     * @throws IOException
     */
    private void writeFile(String name, Set<String> set) throws IOException {
        FileWriter fw = new FileWriter(name);
        PrintWriter writer = new PrintWriter(fw);
        writeWithCommas(set, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Write given values to file in CSV format.
     *
     * @param set    values to write
     * @param writer file write to write with
     */
    private void writeWithCommas(Set<String> set, PrintWriter writer) {
        for (String value : set) {
            writer.write(value);
            writer.write(",");
        }
    }

    /**
     * Generate a random alpha numeric value ready to be inserted as string.
     */
    private String generateAlphaNumeric() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Generate UUID value formatted for insertion as a single string value.
     */
    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
