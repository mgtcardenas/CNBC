import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class Calculation {

    static Hashtable<String, Double> pt; // Probabilities Table -> The probability that Test is any kin

    public static void setProbability(String filePath) throws FileNotFoundException, IOException{
        
        FileReader      fr;
        BufferedReader  br;
        String          kin, word, line;
        String[]        testWords;
        Enumeration     kins, kinWords;
        double          probability;
        long            totalKinWords;

        pt              = new Hashtable<>();
        fr              = new FileReader(filePath);
        br              = new BufferedReader(fr);
        line            = br.readLine();
        testWords       = null;
        totalKinWords   = 0;
        probability     = 0.0;
        word            = "";
        kinWords        = null;
        kin             = "";
        kins            = Lesson.kinsTable.keys();

        // Cycle through all kins to compute each of their respective probabilities in respect to Test
        while (kins.hasMoreElements()) {

            kin   = (String) kins.nextElement();

            // Get the number of total words (even repeated), n for each kin 
            kinWords = Lesson.wordsTables.get(Lesson.linksTable.get(kin)).keys();
            while (kinWords.hasMoreElements()) {
                word             = (String) kinWords.nextElement();
                totalKinWords   += Lesson.wordsTables.get(Lesson.linksTable.get(kin)).get(word);
            }//end while (words in kins)

            // Read Test in order to compute probability of kin. Also, prepare first value of probability
            probability = (double) Lesson.kinsTable.get(kin)/ (double) Lesson.examples;
            fr          = new FileReader(filePath);
            br          = new BufferedReader(fr);
            line        = br.readLine();

            while (line != null) {
                testWords = line.split(" ");
                
                for (String w : testWords) {
                    if (Lesson.vocabulary.contains(w)) {

                        if (Lesson.wordsTables.get(Lesson.linksTable.get(kin)).get(w) != null) {
                            probability *=  (double) (Lesson.wordsTables.get(Lesson.linksTable.get(kin)).get(w) + 1) / (double) ( totalKinWords + Lesson.vocabulary.size() );
                        } else {
                            probability *= (double) 1.0 / (double) ( totalKinWords + Lesson.vocabulary.size() );
                        }//end if-else (¿Word is in kin's known words?)

                    } else {
                        probability *= (double) 0.001 / (double) ( totalKinWords + Lesson.vocabulary.size() );
                    }//end if-else (¿Word is in the vocabulary?)
                }//end for

                line = br.readLine();
            }//end while (reading Test file)

            pt.put(kin, probability);
            System.out.println("Probability of: " + kin + " = " + probability);

        }//end while - (kins)

        br.close();
         
    }//end setProbability

    public static String predict() {
        
        // Get the Biggest Probability
        Enumeration kins;
        String      kin, prediction;
        double      biggest;

        kins        = pt.keys();
        kin         = "";
        prediction  = "";
        biggest     = 0;

        while (kins.hasMoreElements()) {

            kin     = (String) kins.nextElement();

            if (pt.get(kin) > biggest) {
                biggest     = pt.get(kin);
                prediction  = kin;
            }//end if (¿Is there a new champion?)

        }//end while

    return prediction;
    
    }//end predict

}//end Calculation