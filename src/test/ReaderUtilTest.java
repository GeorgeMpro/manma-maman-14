import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReaderUtilTest {
    TestUtil util = new TestUtil();
    private final int maxInput = util.getMAX_NUMBER_OF_INPUT_VALUES();
    ReadUtil readUtil = util.getReadUtil();

    @Test
    void shouldAcceptArrayLength() {
        assertEquals(100, readUtil.getUserArgument("100", "m - array"));
        assertEquals(13, readUtil.getUserArgument("13", "m - array"));
    }

    @Test
    void nullOnInvalidValue() {
        assertNull(readUtil.getUserArgument("not valid", "m - array"));
    }

    @Test
    void shouldReadFromFile() {
        Set<Integer> numbers = readUtil.fromFileToIntSet(util.getIntFileName());

        assertEquals(maxInput, numbers.size());
    }

    @Test
    void shouldReadFromString() {
        Set<String> stringSet = readUtil.fromFileToStringSet(util.getStrFileName());

        assertEquals(maxInput, stringSet.size());
    }
}
