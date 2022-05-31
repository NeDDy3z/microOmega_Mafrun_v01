package level;

import input.GameInput;
import ui.GamePanel;
import ui.GameRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static ui.GamePanel.*;

public class GameLevel {

    GameRender gR = new GameRender(this);
    GameInput gI = new GameInput(this);

    GamePanel gP;
    public GameLevel(GamePanel gP) {
        this.gP = gP;
    }


    //everything has to be static otherwise nothing works thanks to GamePanel (sadge)
    private static ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>(); //Arraylist of arraylists of maps
    private static String[] levelPath = {"gamefiles/levels/level1.csv", "gamefiles/levels/level2.csv", "gamefiles/levels/level3.csv"}; //All map layout files

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

    //removes everything from arraylist (used for "try again" feature)
    public static void delete() {
        for (int i = 0; i < levels.size(); i++) levels.remove(i);
    }
}