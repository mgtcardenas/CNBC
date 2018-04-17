import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class Utils {

    public static final String TRAINING_FILE = "Files/Check Tweets.csv";
    public static final String TEST_FILE = "Files/Check Tweets Test.csv";

    public static String getKin(String line) {

        String kin;

        kin = line.substring(0, line.indexOf(','));
        kin = kin.toLowerCase();

        return kin;

    }//end getKin

    public static String getExample(String line) {

        String example;

        example = line.substring(line.indexOf(',') + 1, line.length());
        example = example.replaceAll("<p>", "");
        example = example.replaceAll("</p>", "");
        example = example.replaceAll(",,,", "");
        example = example.toLowerCase();

        return example;

    }//end getExample

    public static String getTest(String line) {

        String test;

        test = line.substring(line.indexOf(',') + 1, line.length());
        test = test.replaceAll("<p>", "");
        test = test.replaceAll("</p>", "");
        test = test.replaceAll(",,,", "");
        test = test.toLowerCase();

        return test;

    }//end getTest

    public static String[] getWords(String example){

        String[] words;

        words = null;

        example     = example.toLowerCase();
        example     = example.replaceAll("<p>", "");
        example     = example.replaceAll("</p>", "");
        example     = example.replaceAll(",,,", "");
        words       = example.split("\\W+");

        return words;

    }//end getWords

    public static void ignoreFirstLine(BufferedReader br) throws IOException, FileNotFoundException{

        br.readLine();

    }//end ignoreFirstLine

    public static void ignoreLines(BufferedReader br, int numLines) throws IOException, FileNotFoundException {
        
        for (int i = 0; i < numLines; i++) {
            br.readLine();
        }//end for

    }//end ignoreLines

    private void Utils() {
    }//end Utils
    
}


