import java.io.FileNotFoundException;
import java.io.IOException;

public class CNBC {

    public static void main(String[] args) throws IOException, FileNotFoundException{
        
        Learn.populateTables();
        Learn.count();

        Calculation.setProbability();
        System.out.println("I think this is: " + Calculation.predict());

    }//end main
    
}//end CNBC