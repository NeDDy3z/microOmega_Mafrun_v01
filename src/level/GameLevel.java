package level;

import ui.GamePanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameLevel {

    static ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();

    static String[] levelPath = {"gamefiles/level1.csv", "gamefiles/level2.csv", "gamefiles/level3.csv"};

    public static ArrayList <ArrayList<Integer>> getMapLayout() {
        return levels;
    }

    public static void readLevels() { //reads map from file
        for (int i = 0; i < levelPath.length; i++) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(levelPath[i]));
                String line;
                while ((line = br.readLine()) != null) {
                    var input = line.split(",");

                    for (int j = 0; j < input.length; j++) {
                        tmp.add(Integer.parseInt(input[j]));
                    }
                }
                levels.add(tmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static int blockCoords(int x, int y) { //convert block coordinations to position in "map list"
        int pos = 0;

        for (int i = 1; i < (y / 50) + 1; i++) {
            pos += 20;
        }
        pos += (x / 50);

        return pos;
    }
}
