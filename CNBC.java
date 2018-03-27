import java.io.FileNotFoundException;
import java.io.IOException;

public class CNBC {

    public static void main(String[] args) throws IOException, FileNotFoundException{
        
        Learn.populateTables("Train.csv");
        Learn.count();

        Calculation.setProbability("Test.txt");
        System.out.println("I think this is: " + Calculation.predict());

    }//end main
    
}//end CNBC