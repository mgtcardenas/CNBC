import java.io.FileNotFoundException;
import java.io.IOException;

public class CNBC {

    public static void main(String[] args) throws IOException, FileNotFoundException{
        
        Lesson.fillTables(Utils.EXAMPLES_FILE); //fillTables reads Examples.csv once
        Lesson.count(); //gets the total number of examples by adding the # of examples of each kin
        
        /* CHECK SECTION */
        // System.out.println(Lesson.kinsTable.get("spam")+" + "+Lesson.kinsTable.get("ham")+" = "+Lesson.examples);
        // System.out.println(Lesson.vocabulary);
        // System.out.println("# Times 'sentence' appears in ham : "+Lesson.wordsTables.get(1).get("sentence"));

        Calculation.setProbability(Utils.TEST_FILE); //Set probability reads Test.txt # of learned kins times
        System.out.println("I think this is: " + Calculation.getPrediction()); //Gets the biggest probability from the probabilities table

    }//end main
    
}//end CNBC