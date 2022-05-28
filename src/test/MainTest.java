package test;

import level.GameLevel;
import ui.GameFrame;
import ui.GamePanel;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void main() {

        //new GameFrame();



        GamePanel gPan = new GamePanel();

        System.out.println("Size of arraylist: "+ GameLevel.getMapLayout().get(0).size());

        System.out.println("Block order in maplist: "+ GameLevel.blockCoords(50,550));
        System.out.println("Value of the block: "+ GameLevel.getMapLayout().get(0).get(GameLevel.blockCoords(50,550)));

        System.out.println("LVL1: "+ GameLevel.getMapLayout().get(0).toString());
        System.out.println("LVL2: "+ GameLevel.getMapLayout().get(1).toString());
        System.out.println("LVL3: "+ GameLevel.getMapLayout().get(2).toString());



    }
}