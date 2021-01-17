import java.util.Set;

public class ReportUtilImpl implements ReportUtil {
    // Default file names
    private static final String inputNumbers = "src/resources/input_number_1_000_000.txt";
    private static final String inputStrings = "src/resources/input_string_1_000_000.txt";

    // Inner utility functions
    Set<String> strSet;
    Set<Integer> intSet;
    HashParams params;
    HashUtil hashUtil;
    ReadUtil readUtil;
    WriteUtil writeUtil;

    /*Constructor*/
    public ReportUtilImpl(int m, int k) {
        this.params = new HashParams(m, k);
        hashUtil = new HashUtilImpl(this.params);
        readUtil = new ReadUtilImpl();
        writeUtil = new WriteUtilImpl();
        intSet = readUtil.fromFileToIntSet(inputNumbers);
        strSet = readUtil.fromFileToStringSet(inputStrings);
    }

    @Override
    public void compareWithValues(Integer m, Integer k) {
        // setup new values
        params = new HashParams(m, k);
        hashUtil.setParams(params);

        // generate new report
        generateReport();
    }

    @Override
    public void generateReport() {
        // Hash input to hash table
        hashUtil.stringSetToHashTable(this.strSet);

        // count collisions with unique new values
        int collisions = hashUtil.countAlreadyHashedInt(intSet);

        printReport(collisions);
    }

    @Override
    public void checkWithOptimalK(Integer m, Integer k) {
        System.out.println("\nRunning with optimal number of hash functions.");

        int n = this.strSet.size();
        int optimalK = (int) (Math.log(2) * (m / n));
        compareWithValues(m, optimalK);
    }

    @Override
    public void rerunReport() {
        int intSetSize = intSet.size();
        int strSetSize = strSet.size();
        this.intSet = writeUtil.generateIntegerSet(intSetSize);
        this.strSet = writeUtil.generateStringSet(strSetSize);
    }

    /**
     * Evaluate, print and format the hashing and comparison report.
     *
     * @param falsePos - number of false positively identified values
     */
    private void printReport(int falsePos) {
        int n = strSet.size();
        int compare = intSet.size();
        int m = params.getM();
        int k = params.getK();
        double actualFalse = (double) falsePos / compare;
        double P = getPredictedFalsePositive(params, intSet.size());
        int oneIn = (int) (1 / P);

        System.out.printf("\n%-4s|\t%-10s|\t%-10s|\t%-10s|\t%-12s|\t%-10s"
                , "k", "m", "n", "n'", "P", "P'");
        System.out.printf("\n%-4d|\t%-10d|\t%-10d|\t%-10d|\t%-10.10f|\t%-10.10f",
                k, m, n, compare, P, actualFalse);

        System.out.printf("\nExpected False Positive P=%.10f or 1 in %d", P, oneIn);
        System.out.printf("\nActual False Positive while checking n' values P'=%.10f (or total  %d).\n", actualFalse, falsePos);
    }


    @Override
    public double getPredictedFalsePositive(HashParams params, int n) {
        int k = params.getK();
        double m = params.getM();

        /*-kn/m*/
        double exp = -k * (n / m);

        /*(1-e^(-kn/m))*/
        float inBrackets = (float) (1 - Math.pow(Math.E, exp));

        /*((1-e^(-kn/m))^k*/
        return (float) Math.pow(inBrackets, k);
    }

}
