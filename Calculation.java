import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class Calculation {

    static Hashtable<String, Double> probabilitiesTable; // Probabilities Table -> The probability that Test is any kin

    public static void setProbability(String filePath) throws FileNotFoundException, IOException{
        
        FileReader      fr;
        BufferedReader  br;

        String          kin, word, line;
        String[]        testWords;
        Enumeration     kins, kinWords;
        double          probability;
        long            numKinWords;

        probabilitiesTable  = new Hashtable<>();
        
        fr                  = new FileReader(filePath);
        br                  = new BufferedReader(fr); 

        line                = br.readLine();
        probability         = 0.0;
        kins                = Lesson.kinsTable.keys();

        // Cycle through all kins to compute each of their respective probabilities in respect to Test
        while (kins.hasMoreElements()) {

            kin         = (String) kins.nextElement();
            numKinWords = 0;
            
            // Get the number of total words (even repeated), n for each kin. Here numKinWords
            kinWords = Lesson.wordsTables.get(Lesson.linksTable.get(kin)).keys();

            while (kinWords.hasMoreElements()) {
                word           = (String) kinWords.nextElement();
                numKinWords   += Lesson.wordsTables.get(Lesson.linksTable.get(kin)).get(word);
            }//end while (words in kins)

            /*CHECK THAT TOTAL NUMBER OF WORDS IN KIN IS CORRECT*/
            System.out.println("Words in "+ kin + ": " + numKinWords);

            //Prepare first value of probability
            probability = (double) Lesson.kinsTable.get(kin)/ (double) Lesson.examples;

            // Read Test in order to compute probability of kin. 
            fr          = new FileReader(filePath);
            br          = new BufferedReader(fr);
            line        = br.readLine();

            //This while is here because the test may be more than one line long
            while (line != null) {

                testWords = line.split(" ");
                
                for (String w : testWords) {
                    if (Lesson.vocabulary.contains(w)) {

                        if (Lesson.wordsTables.get(Lesson.linksTable.get(kin)).containsKey(w)) {
                            probability *=  (double) (Lesson.wordsTables.get(Lesson.linksTable.get(kin)).get(w) + 1) / (double) ( numKinWords + Lesson.vocabulary.size() );
                        } else {
                            probability *= (double) 1.0 / (double) ( numKinWords + Lesson.vocabulary.size() );
                        }//end if-else (¿Word is in kin's known words?)

                    } else {
                        probability *= (double) 0.001 / (double) ( numKinWords + Lesson.vocabulary.size() );
                    }//end if-else (¿Word is in the vocabulary?)
                }//end for

                line = br.readLine();

            }//end while (reading Test file)

            probabilitiesTable.put(kin, probability);
            System.out.println("Probability of: " + kin + " = " + probability);

        }//end while - (kins)

        br.close();
         
    }//end setProbability

    public static String predict() {
        
        // Get the Biggest Probability
        Enumeration kins;
        String      kin, prediction;
        double      biggest;

        kins        = probabilitiesTable.keys();
        kin         = "";
        prediction  = "";
        biggest     = 0;

        while (kins.hasMoreElements()) {

            kin     = (String) kins.nextElement();

            if (probabilitiesTable.get(kin) > biggest) {
                biggest     = probabilitiesTable.get(kin);
                prediction  = kin;
            }//end if (¿Is there a new champion?)

        }//end while

    return prediction;
    
    }//end predict

}//end Calculation