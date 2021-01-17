/**
 * Main utility class for running the program,calculating and printing report information about calculations performed in the program.
 */
public interface ReportUtil {

    /**
     * Print report to containing parameters, predicted and actual False Positive Rate.
     */
    void generateReport();

    /**
     * Calculate predicted False Positive percent - P.
     * According to formula: ((1-e^(-kn/m))^k
     *
     * @param params parameters to extract
     * @param n      number of items in input
     * @return predicted P
     */
    double getPredictedFalsePositive(HashParams params, int n);

    /**
     * Rerun the and report predicted P and actual P' False Positive percent for different m,k values and same input values.
     *
     * @param m new bit set size
     * @param k new number of hash functions
     */
    void compareWithValues(Integer m, Integer k);

    /**
     * Generate report according to optimal number of hash functions for given m and n.
     * Formula: k=ln(2)*(m/n)
     * Rounded down for integer value.
     */
    void checkWithOptimalK(Integer m, Integer k);

    /**
     * Generate new input values and rerun the reports with given m,k,n.
     */
    void rerunReport();
}
