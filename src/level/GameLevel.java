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

    public static void read() { //reads map from file

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

    public static int blockCoords(int x, int y) { //convert block coordinations to position in "map list"
        int pos = 0;

        for (int i = 1; i < (y / 50) + 1; i++) {
            pos += 12;
        }
        pos += (x / 50);

        return pos;
    }

}
