import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;

public class Training {

    //This is needed to keep count of the kins that have been found
    static int                                   kinIndex;

    //All below this is needed to compute probability
    static long                                  examples;   // Total number of exmaples (aka files or lines in this case)
    static HashSet<String>                       vocabulary; // All known words
    static Hashtable<String, Integer>            kinsTable;  // Kins Table   -> # of examples of each kin (how many spams/hams you have)
    static Hashtable<String, Integer>            linksTable; // Links Table  -> Index of each kin in the Word Tables Array List (So I can get those tables)
    static ArrayList<Hashtable<String, Integer>> wordsTables;// Words Tables -> ArrayList with Tables with # times each word happens in each kin

    private static void setTables(String line) throws IOException, FileNotFoundException{

        String kin, example;
        String[] words;
        
        kin     = Utils.getKin(line);
        example = Utils.getExample(line);
        words   = Utils.getWords(example);

        if (kinsTable.containsKey(kin)) {
            
            kinsTable.put(kin, kinsTable.get(kin) + 1);

            for (String w : words) {

                vocabulary.add(w);

                if (wordsTables.get(linksTable.get(kin)).containsKey(w)) {
                    wordsTables.get(linksTable.get(kin)).put( w , wordsTables.get(linksTable.get(kin)).get(w) + 1 );
                } else {
                    wordsTables.get(linksTable.get(kin)).put( w , 1 );
                }//end if-esle (¿Has this word already happened in kin's Word Table?)

            }//end for (Count all words of kin and put them in its Word Table)

        } else {

            kinsTable.put(kin, 1);

            wordsTables.add(new Hashtable<String, Integer>());
            linksTable.put(kin, kinIndex);
            kinIndex++;

            for (String w : words) {

                vocabulary.add(w);

                if (wordsTables.get(linksTable.get(kin)).containsKey(w)) {
                    wordsTables.get(linksTable.get(kin)).put( w , wordsTables.get(linksTable.get(kin)).get(w) + 1 );
                } else {
                    wordsTables.get(linksTable.get(kin)).put( w , 1 );
                }//end if-esle (¿Has this word already happened in kin's Word Table?)

            }//end for (Count all words of kin and put them in its Word Table)

        }//end if-else (¿Do I already know this kin?)

    }//end setTables

    private static void setNumExamples() {

        Enumeration kins;
        String      kin;

        kins        = kinsTable.keys();
        kin         = "";
        examples    = 0;

        while (kins.hasMoreElements()) {
            kin = (String) kins.nextElement();
            examples += kinsTable.get(kin);
        }//end while
        
    }//end setNumExamples

    public static void train(String filePath) throws IOException, FileNotFoundException{
        
        FileReader      fr;
        BufferedReader  br;
        String          line;

        fr          = new FileReader(filePath);
        br          = new BufferedReader(fr);

        vocabulary  = new HashSet<>();
        kinsTable   = new Hashtable<>();
        linksTable  = new Hashtable<>();
        wordsTables = new ArrayList<>();

        kinIndex    = 0;

        Utils.ignoreFirstLine(br);

        line = br.readLine();

        while (line != null) {
            
            setTables(line);

            line = br.readLine();

        }//end while

        setNumExamples();

        br.close();

    }//end train

}//end Training