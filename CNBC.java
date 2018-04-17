import java.io.FileNotFoundException;
import java.io.IOException;

public class CNBC {

    public static void main(String[] args) throws IOException, FileNotFoundException{
        
        Training.train(Utils.TRAINING_FILE);
        
        /* CHECK SECTION */
        // System.out.println(Training.kinsTable.get("spam") + " + " + Training.kinsTable.get("ham") + " = " + Training.examples);
        // System.out.println(Training.vocabulary);

        Test.test(Utils.TEST_FILE);

    }//end main
    
}//end CNBC