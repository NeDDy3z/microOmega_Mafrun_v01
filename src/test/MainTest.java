package test;

import level.GameLevel;
import logic.GameLogic;
import ui.GameFrame;
import ui.GamePanel;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void main() {

        new GameFrame();



        GamePanel gp = new GamePanel();
        GameLogic gl = new GameLogic();

        System.out.println("Size of arraylist: "+ GameLevel.getLayout().size());
        System.out.println("Player posX: "+ gp.getPlayerX() +"\nPlayer posY: "+ gp.getPlayerY());

        System.out.println(GameLevel.blockCoords(50,550));
        System.out.println(GameLevel.getLayout().get(GameLevel.blockCoords(50,550)));

    }
}