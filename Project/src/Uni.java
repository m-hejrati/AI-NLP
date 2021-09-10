import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Uni {

    private int allVocabCount;
    private HashMap<String, Integer> countVocab;
    private int allLineCount;

    public Uni (String path){

        allVocabCount = 0;
        countVocab = new HashMap<String, Integer>();
        allLineCount = 0;

        read(path);
    }


    public void read(String path){

        Scanner sc1 = null;
        try {
            sc1 = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc1.hasNextLine()) {
            allLineCount ++;

            Scanner sc2 = new Scanner(sc1.nextLine());
            while (sc2.hasNext()) {
                String s = sc2.next();

                if(countVocab.containsKey(s)){
                    int count = countVocab.get(s) + 1;
                    countVocab.replace(s,count);
                }else {
                    countVocab.put(s,1);
                }

                allVocabCount ++;
            }
        }


        System.out.println("Vocabs: " + allVocabCount);
        System.out.println("Lines: " + allLineCount);
    }


    public int prob(String s) {

        if (countVocab.containsKey(s))
            return countVocab.get(s);
        else
            return 0;
    }

    public int getAllLineCount(){
        return allLineCount;
    }
}