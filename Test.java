import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class Test {

    static private String                     prediction;
    static Hashtable<String, Double>  probabilitiesTable; // Probabilities Table -> The probability that Test is any kin

    private static void setProbability(String test) {
        
        String          kin, word;
        String[]        testWords;
        Enumeration     kins, kinWords;
        double          probability;
        long            numKinWords;

        probabilitiesTable  = new Hashtable<>();
    
        // Cycle through all kins to compute each of their respective probabilities in respect to Test
        kins            = Training.kinsTable.keys();

        while (kins.hasMoreElements()) {

            kin         = (String) kins.nextElement();
            numKinWords = 0;
            
            // Get the number of total words (even repeated), n for each kin. Here numKinWords
            kinWords = Training.wordsTables.get(Training.linksTable.get(kin)).keys();

            while (kinWords.hasMoreElements()) {
                word           = (String) kinWords.nextElement();
                // System.out.println("Word: "+ word + " in kin: " + kin + " is # times: " + Lesson.wordsTables.get(Lesson.linksTable.get(kin)).get(word));
                numKinWords   += Training.wordsTables.get(Training.linksTable.get(kin)).get(word);
            }//end while (words in kins)

            /*THIS IS FOR CHECKING THAT TOTAL NUMBER OF WORDS IN KIN IS CORRECT*/
            // System.out.println("Words in "+ kin + ": " + numKinWords);

            //Prepare first value of probability
            probability = Math.log(Training.kinsTable.get(kin)) - Math.log(Training.examples);
            // probability = (double) Training.kinsTable.get(kin)/ (double) Training.examples;

            /*CHECK THAT PROBABILITY IS FINE BEFORE CALCULATIONS*/
            // System.out.println("Probability of " + kin + ": " + Math.exp(probability));

            //Calculate probabilities of each word, therefore, get the words as an array
            testWords = Utils.getWords(test);

            for (String w : testWords) {

                /*CHECK THAT THE WORDS ARE CORRECT */
                //System.out.println(w);

                if (Training.vocabulary.contains(w)) {

                    if (Training.wordsTables.get(Training.linksTable.get(kin)).containsKey(w)) {
                        probability +=  Math.log(Training.wordsTables.get(Training.linksTable.get(kin)).get(w) + 1) - Math.log( numKinWords + Training.vocabulary.size() );
                        // System.out.println("Probability of " + kin + ": " + Math.exp(probability));
                    } else {
                        probability += Math.log(1.0) - Math.log( numKinWords + Training.vocabulary.size() );
                    }//end if-else (¿Word is in kin's known words?)

                } else {
                    probability += Math.log(0.001) - Math.log( numKinWords + Training.vocabulary.size() );
                }//end if-else (¿Word is in the vocabulary?)
            }//end for

            /*CHECK THAT THE PROBABILITIES ARE NOT 0*/
            // System.out.println("Probability of " + kin + ": " + probability);
            probabilitiesTable.put(kin, probability);

        }//end while - (kins)
  
    }//end setProbability

    private static void setPrediction() {
        
        // Get the Biggest Probability
        Enumeration kins;
        String      kin, pred;
        double      biggest;

        kins        = probabilitiesTable.keys();
        kin         = "";
        pred        = "";
        biggest     = - Double.MAX_VALUE;

        while (kins.hasMoreElements()) {

            kin     = (String) kins.nextElement();

            /*CHECK THAT THE PROBABILITIES ARE NOT 0*/
            // System.out.println("Probability of " + kin + ": " + probabilitiesTable.get(kin));
            

            if (probabilitiesTable.get(kin) > biggest) {
                biggest = probabilitiesTable.get(kin);

                /*CHECK THAT THE BIGGEST PROBABILITY IS CORRECT */
                // System.out.println("The biggest now is: " + biggest);
                
                pred    = kin;
            }//end if (¿Is there a new champion?)

        }//end while

        prediction = pred;
        // System.out.println("My prediction is: " + prediction);
    }//end predict

    public static void test(String filePath) throws FileNotFoundException, IOException{
        
        FileReader      fr;
        BufferedReader  br;
        String          line, test, kin;
        int             lineNumber;

        fr          = new FileReader(filePath);
        br          = new BufferedReader(fr);
        lineNumber  = 2;//I do this on purpose because remember that line 1 doesn't count

        Utils.ignoreFirstLine(br);

        line = br.readLine();

        while (line != null) {
            
            kin  = Utils.getKin(line);
            test = Utils.getTest(line);
            setProbability(test);
            setPrediction();

            if (kin.equals(prediction)) {
                System.out.println("I think line " + lineNumber+" is "+ prediction + " and I am right");
            } else {
                System.out.println("I think line " + lineNumber+" is "+ prediction + ", but I am wrong");
            }//end if-else

            lineNumber++;
            line = br.readLine();

        }//end while

        br.close();

    }//end getSuccessRate

}//end Test