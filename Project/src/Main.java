import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // train
        Bi ferdowsi = new Bi("ferdowsi_train.txt");
        Bi hafez = new Bi("hafez_train.txt");
        Bi molavi = new Bi("molavi_train.txt");


        Scanner sc1 = null;
        try {
            sc1 = new Scanner(new File("test_file.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String pre;
        String now;
        String pair;

        double ferdowsiRes = 1;
        double hafezRes = 1;
        double molaviRes = 1;
        int correct = 0;
        int incorrect = 0;

        while (sc1.hasNextLine()) {
            Scanner sc2 = new Scanner(sc1.nextLine());

            int ans = 0;
            pre = "|";

            while (sc2.hasNext()) {

                if (ans == 0) {
                    ans = sc2.nextInt();
                } else {

                    now = sc2.next();
                    pair = pre + "." + now;

                    ferdowsiRes *= ferdowsi.prob(pair);
                    hafezRes *= hafez.prob(pair);
                    molaviRes *= molavi.prob(pair);

                    pre = now;
                }
            }

            now = "/";
            pair = pre + "." + now;

            ferdowsiRes *= ferdowsi.prob(pair);
            hafezRes *= hafez.prob(pair);
            molaviRes *= molavi.prob(pair);

            int result;
            if (ferdowsiRes >= hafezRes && ferdowsiRes >= molaviRes)
                result = 1;
            else if (hafezRes >= ferdowsiRes && hafezRes >= molaviRes)
                result = 2;
            else
                result = 3;

            System.out.println("Real answer: " + ans);
            System.out.println("Ferdowsi: \t" + ferdowsiRes);
            System.out.println("Hafez: \t\t" + hafezRes);
            System.out.println("Molavi: \t " + molaviRes);
            System.out.println(result);
            System.out.println();

            if (result == ans)
                correct ++;
            else
                incorrect ++;

            ferdowsiRes = 1;
            hafezRes = 1;
            molaviRes = 1;
        }

        System.out.println("Correct: \t" + correct);
        System.out.println("Incorrect: \t" + incorrect);
        System.out.println("Accuracy Percentage: " + 100 * correct/(double)(correct+incorrect));
    }
}