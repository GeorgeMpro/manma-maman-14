import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportUtilTest {
    TestUtil util = new TestUtil();
    private final int n = util.getMAX_NUMBER_OF_INPUT_VALUES();
    ReportUtil reportUtil = new ReportUtilImpl(util.getMAX_BIT_ARRAY_SIZE(), util.getMAX_NUMBER_OF_HASH_FUNCTIONS());
    HashParams params = util.getParams();

    @Test
    void shouldCalculateFalsePositiveChance() {

        double P = reportUtil.getPredictedFalsePositive(
                params,
                n
        );
        double expected = 6.401418772838952E-7;
        assertEquals(expected, P);
    }
}
