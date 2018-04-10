import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;

public class Lesson {

    static long                                  examples;   // Total number of exmaples (aka files or lines in this case)
    static HashSet<String>                       vocabulary; // All known words
    static Hashtable<String, Integer>            kt;         // Kins Table             -> # of examples of each kin (how many spams/hams you have)
    static Hashtable<String, Integer>            at;         // Association Table      -> Index of each kin in the Word Tables Array List (So I can get those tables)
    static ArrayList<Hashtable<String, Integer>> wt;         // Word Tables            -> AL with Tables with # times each word happens in each kin

    public static void fillTables(String filePath) throws IOException, FileNotFoundException{

        FileReader      fr;
        BufferedReader  br;
        String          line, kin, text;
        String[]        words;
        int             kinIndex;

        kinIndex    = 0;
        fr          = new FileReader(filePath);
        br          = new BufferedReader(fr);
        line        = br.readLine();

        vocabulary  = new HashSet<>();
        kt          = new Hashtable<>();
        at          = new Hashtable<>();
        wt          = new ArrayList<>();

        while(line != null){

            kin         = line.substring(0, line.indexOf(','));
            text        = line.substring(line.indexOf(',') + 1, line.length() - 3);
            words       = text.split(" ");

            if (kt.containsKey(kin)) {
                
                kt.put(kin, kt.get(kin) + 1);

                for (String w : words) {

                    vocabulary.add(w);

                    if (wt.get(at.get(kin)).contains(w)) {
                        wt.get(at.get(kin)).put( w , wt.get(at.get(kin)).get(w) + 1 );
                    } else {
                        wt.get(at.get(kin)).put( w , 1 );
                    }//end if-esle (¿Has this word already happened in kin's Word Table?)

                }//end for (Count all words of kin and put them in its Word Table)

            } else {

                kt.put(kin, 1);

                wt.add(new Hashtable<String, Integer>());
                at.put(kin, kinIndex);
                kinIndex++;

                for (String w : words) {

                    vocabulary.add(w);

                    if (wt.get(at.get(kin)).contains(w)) {
                        wt.get(at.get(kin)).put( w , wt.get(at.get(kin)).get(w) + 1 );
                    } else {
                        wt.get(at.get(kin)).put( w , 1 );
                    }//end if-esle (¿Has this word already happened in kin's Word Table?)

                }//end for (Count all words of kin and put them in its Word Table)

            }//end if-else (¿Do I already know this kin?)

            line = br.readLine();

        }//end while

        br.close();

    }//end populateTables

    public static void count() {

        Enumeration kins;
        String      kin;

        kins        = kt.keys();
        kin         = "";
        examples    = 0;

        while (kins.hasMoreElements()) {
            kin = (String) kins.nextElement();
            examples += kt.get(kin);
        }//end while
        
    }//end Count

}//end Learn