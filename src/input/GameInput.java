package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.GamePanel;

import static java.util.concurrent.TimeUnit.SECONDS;
import static ui.GamePanel.*;
import static level.GameLevel.*;

public class GameInput implements MouseListener, KeyListener {

    //movement of player with arrowkeys
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: //up arrowkey moves player up
                if (!getMapLayout().get(levelSelection).get(blockCoords(playerX, playerY - UNIT)).equals(0)) playerY -= UNIT;
                break;

            case KeyEvent.VK_DOWN:

                if (getMapLayout().get(levelSelection).get(blockCoords(playerX, playerY + UNIT)).equals(0)) playerY += UNIT;
                break;

            case KeyEvent.VK_LEFT:
                if (getMapLayout().get(levelSelection).get(blockCoords(playerX - UNIT, playerY)).equals(0)) playerX -= UNIT;
                break;

            case KeyEvent.VK_RIGHT:
                if (!getMapLayout().get(levelSelection).get(blockCoords(playerX + UNIT, playerY)).equals(0)) playerX += UNIT;
                break;
        }
    }



    //main menu selection [needs some bugfix in level selection]
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GamePanel.gameState) {
            case MAINMENU:
                //play button
                if (e.getX() >= 450 && e.getX() <= 550) {
                    if (e.getY() >= 460 && e.getY() <= 510) {
                        gameState = STATE.GAME;
                        scheduler.scheduleAtFixedRate(runnable, 0,1, SECONDS);
                    }
                }
                //level select button
                if (e.getX() >= 360 && e.getX() <= 635) {
                    if (e.getY() >= 560 && e.getY() <= 600) gameState = STATE.LEVELMENU;
                }
                //exit
                if (e.getX() >= 455 && e.getX() <= 545) {
                    if (e.getY() >= 660 && e.getY() <= 700) System.exit(0);
                }

            case LEVELMENU:
                if (e.getX() >= 420 && e.getX() <= 580) {
                    //lvl1
                    if (e.getY() >= 460 && e.getY() <= 500) {
                        levelSelection = 0;

                    }
                    //lvl2
                    if (e.getY() >= 560 && e.getY() <= 600) {
                        levelSelection = 1;
                        gameState = STATE.MAINMENU;
                    }
                    //lvl3
                    if (e.getY() >= 660 && e.getY() <= 700) {
                        levelSelection = 2;
                        gameState = STATE.MAINMENU;
                    }
                }

        }
    }
    //region unused methods that must be here because god said so...
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    //endregion
}
