package level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameLevel {

    final static ArrayList<Integer> level = new ArrayList<Integer>();

    final static boolean readingDone = false;



    public static ArrayList<Integer> getLayout() {

        if (!readingDone) read();
        return level;

    }

    public static void read() {

        try {

            BufferedReader br;
            br = new BufferedReader(new FileReader("gamefiles/level.csv"));

            String line;
            while ((line = br.readLine()) != null) {
                var input = line.split(",");

                for (int i = 0; i < input.length; i++) {
                    level.add(Integer.parseInt(input[i]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
