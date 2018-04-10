import java.io.FileNotFoundException;
import java.io.IOException;

public class CNBC {

    public static void main(String[] args) throws IOException, FileNotFoundException{
        
        Lesson.fillTables("Learn.csv"); //fillTables reads Learn.csv once
        Lesson.count(); //gets the total number of examples by adding the # of exapmles of each kin

        Calculation.setProbability("Test.txt"); //Set probability reads Test.txt # of learned kins times
        System.out.println("I think this is: " + Calculation.predict()); //Gets the biggest probability from the probabilities table

    }//end main
    
}//end CNBC