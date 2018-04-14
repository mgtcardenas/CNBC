public final class Utils {

    public static final String EXAMPLES_FILE = "Examples.csv";
    public static final String TEST_FILE = "Test.txt";

    public static String getKin(String line) {

        String kin;

        kin = line.substring(0, line.indexOf(','));
        kin = kin.toLowerCase();

        return kin;

    }//end getKin

    public static String getExample(String line) {

        String example;

        example = line.substring(line.indexOf(',') + 1, line.length() - 3);
        example = example.toLowerCase();

        return example;

    }//end getExample

    public static String[] getWords(String example) {

        String[] words;

        words = example.split(" ");

        return words;

    }//end getWords

    private void Utils() {
    }//end Utils
    
}


