import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriterUtilTest {
    private static TestUtil util = new TestUtil();
    private static WriteUtil writeUtil = util.getWriteUtil();
    private static Set<Integer> intSet;
    private static Set<String> strSet;
    private static int inputSize = util.getMAX_NUMBER_OF_INPUT_VALUES();
    ReadUtil readUtil = util.getReadUtil();
    private final String fileName = "src/resources/generated_string.txt";
    private final String fileNum = "src/resources/generated_num.txt";

    @BeforeAll
    static void initAll() {
        intSet = writeUtil.generateIntegerSet(inputSize);
        strSet = writeUtil.generateStringSet(inputSize);
    }

    @Test
    void shouldWriteStringSet() {

        int size = writeUtil.generateStringSet(inputSize).size();
        assertEquals(inputSize, size);
    }

    @Test
    void shouldGenerateStringCSV() {

        File file = writeUtil.generateInputCSV(fileName, strSet);

        assertTrue(file.exists());
        assertEquals(inputSize, readUtil.fromFileToStringSet(fileName).size());
    }

    @Test
    void shouldGenerateNumberSet() {
        assertEquals(inputSize, intSet.size());
    }

    @Test
    void shouldGenerateNumberCSV() {
        File file = writeUtil.generateInputIntegerCSV(fileNum, intSet);
        assertTrue(file.exists());
        assertEquals(inputSize, readUtil.fromFileToIntSet(fileNum).size());
    }
}
