import java.util.Scanner;

/**
 * Maman 14 program, calculating false positive rate of hashing k times to given bitset.
 */
public class Main {

    public static void main(String[] args) {
        Integer m, k;
        ReportUtil reportUtil;
        ReadUtil util = new ReadUtilImpl();
        String userInput = "";

        /*Messages for user*/
        String startRoundMsg = "\nReading values from input file...";
        String infoForUser = "\nHashing values and making comparisons...";
        String endMsg = "\n\nPress 'q' to quit.\nPress 'n' to rerun with given k,m and new input files.\nAny other key to continue.";

        /*Main program loop*/
        while (!userInput.equalsIgnoreCase("q")) {
            Scanner sc = new Scanner(System.in); //System.in is a standard input stream

            System.out.print("\nSpecify m - array length:\n");
            String str = sc.nextLine();              //reads string
            m = util.getUserArgument(str, "m - array");
            System.out.print("\nSpecify k - number of hash functions\n");
            str = sc.nextLine();
            k = util.getUserArgument(str, "k - number of hash functions");

            // Running reports
            System.out.println(startRoundMsg);
            reportUtil = new ReportUtilImpl(m, k);
            System.out.println(infoForUser);
            reportUtil.generateReport();
            reportUtil.checkWithOptimalK(m, k);

            System.out.println("\nReport with different m and k values.");
            reportUtil.compareWithValues(m, k * 2);
            reportUtil.compareWithValues(m, k / 2);
            reportUtil.compareWithValues(m * 2, k);
            reportUtil.compareWithValues(m * 3, k);

            // end of loop and option to continue, quit, or rerun
            System.out.println(endMsg);
            userInput = sc.nextLine();

            boolean shouldRerun = userInput.charAt(0) == 'n';
            if (shouldRerun) {
                userInput = rerunReport(m, k, reportUtil);
            }
        }

    }

    /**
     * Run the report with given m and k values but with different unique keys.
     */
    private static String rerunReport(Integer m, Integer k, ReportUtil reportUtil) {
        String userInput;
        // reset input
        userInput = "";

        //Generate new reports
        System.out.println("\nGenerating unique input values...");
        reportUtil.rerunReport();
        reportUtil.compareWithValues(m, k);


        System.out.println("\nReport with different m and k values.");
        reportUtil.compareWithValues(m, k * 2);
        reportUtil.compareWithValues(m, k / 2);
        reportUtil.compareWithValues(m * 2, k);
        reportUtil.compareWithValues(m * 3, k);

        return userInput;
    }
}
