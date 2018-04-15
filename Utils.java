import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class Utils {

    public static final String EXAMPLES_FILE = "Examples.csv";
    public static final String TEST_FILE = "Examples_Test.txt";

    public static String getKin(String line) {

        String kin;

        kin = line.substring(0, line.indexOf(','));
        kin = kin.toLowerCase();

        return kin;

    }//end getKin

    public static String getExample(String line) {

        String example;

        example = line.substring(line.indexOf(',') + 1, line.length());
        example = example.toLowerCase();

        return example;

    }//end getExample

    public static String[] getWords(String example){

        String[] words;
        String tempExample;

        words = null;

        example     = example.toLowerCase();
        tempExample = example.replaceAll("<p>", "");
        tempExample = tempExample.replaceAll("</p>", "");
        words = tempExample.split("\\W+");

        return words;

    }//end getWords

    public static void ignoreFirstLine(BufferedReader br) throws IOException, FileNotFoundException{

        br.readLine();

    }//end

    private void Utils() {
    }//end Utils
    
}


