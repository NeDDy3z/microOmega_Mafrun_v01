package level;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static ui.GamePanel.*;

public class GameLevel {

    static ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>(); //Arraylist of arraylists of maps
    static String[] levelPath = {"gamefiles/levels/level1.csv", "gamefiles/levels/level2.csv", "gamefiles/levels/level3.csv"}; //All map layout files


    //return arraylist of arraylists
    public static ArrayList <ArrayList<Integer>> getMapLayout() {
        return levels;
    }

    //read map layout from files
    public static void readLevels() {
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



    //return blocks position in its map arraylist
    public static int blockCoords(int x, int y) {
        int pos = 0;

        for (int i = 1; i < (y / 50) + 1; i++) {
            pos += 20;
        }
        pos += (x / 50);

        return pos;
    }



    //render map layout
    public static void renderLevel(Graphics g) { //renders map layout
        int x = 0;
        int y = 0;
        int block = 0;
        for (int i = 0; i < SCR_HEIGHT / UNIT; i++) {
            for (int j = 0; j < SCR_WIDTH / UNIT; j++) {
                g.setColor(Color.BLACK);
                if (GameLevel.getMapLayout().get(levelSelection).get(block).equals(0) || GameLevel.getMapLayout().get(levelSelection).get(block).equals(1)) g.fillRect(x * UNIT, y * UNIT, UNIT, UNIT);
                else {
                    g.setColor(Color.WHITE);
                    g.fillRect(x * 50, y * 50, UNIT, UNIT);
                }
                x++;
                block++;
            }
            y++;
            x = 0;
        }
        g.setColor(Color.white);
        g.fillRect(1 * UNIT, 19 * UNIT, UNIT, UNIT);
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/images/toilet.png"), 18 * UNIT,19 * UNIT, null);
    }
}