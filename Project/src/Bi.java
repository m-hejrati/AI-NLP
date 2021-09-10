import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Bi {

    private int allVocabCount;
    private HashMap<String, Integer> countVocab;
    private Uni uni;

    public Bi (String path){

        allVocabCount = 0;
        countVocab = new HashMap<String, Integer>();
        uni = new Uni(path);

        read(path);
    }


    public void read(String path){

        Scanner sc1 = null;
        try {
            sc1 = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String pre;
        String now;
        String pair;

        // start of sentence: |
        // end of sentence: /

        while (sc1.hasNextLine()) {
            Scanner sc2 = new Scanner(sc1.nextLine());

            pre = "|";

            while (sc2.hasNext()) {

                now = sc2.next();
                pair = pre + "." + now;

                update(pair);

                pre = now;
                allVocabCount ++;
            }

            now = "/";
            pair = pre + "." + now;
            update(pair);
            allVocabCount ++;
        }

        System.out.println("Bigram: " +  allVocabCount);
    }


    public void update(String pair){

        if(countVocab.containsKey(pair)){
            int count = countVocab.get(pair) + 1;
            countVocab.replace(pair,count);
        }else {
            countVocab.put(pair,1);
        }
    }


    public double prob(String s) {

        String[] splitByDot = s.split("\\.");

        int proPre = 0;
        if (splitByDot[0].equals("|"))
            proPre = uni.getAllLineCount();
        else
            proPre = uni.prob(splitByDot[0]);


        double pBi = 0;
        if (countVocab.containsKey(s))
            pBi = countVocab.get(s) / (double)proPre;

        double pUni = proPre/(double)allVocabCount;


        double L1 = 0.9;
        double L2 = 0.05;
        double L3 = 0.05;
        double E = 0.000001;

        return L1*pBi + L2*pUni + L3*E;
    }
}
