package input;

import data.GameLevel;
import ui.GamePanel;

import java.awt.event.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static ui.GamePanel.*;

public class GameInput implements MouseListener, KeyListener {

    GamePanel gP;
    GameLevel gL;

    public GameInput(GamePanel gP) {
        this.gP = gP;
    }

    public GameInput(GameLevel gL) {
        this.gL = gL;
    }

    //region input
    //main menu selection
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (gameState) {
            case MAINMENU:
                //play button
                if (e.getX() >= 450 && e.getX() <= 550) {
                    if (e.getY() >= 460 && e.getY() <= 510) {
                        gameState = STATE.GAME;
                        gP.getScheduler().scheduleAtFixedRate(gP.getRunnable(), 0,1, SECONDS);
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
                    if (e.getY() >= 510 && e.getY() <= 550) gP.setLevelSelection(0);
                    //lvl2
                    if (e.getY() >= 610 && e.getY() <= 650) gP.setLevelSelection(1);
                    //lvl3
                    if (e.getY() >= 710 && e.getY() <= 750) gP.setLevelSelection(2);
                    //back
                    if (e.getY() >= 810 && e.getY() <= 850) gameState = STATE.MAINMENU;
                }
            case GAMEOVER:
                if (e.getY() >= 875 && e.getY() <= 900) {
                    //menu
                    if (e.getX() >= 230 && e.getX() <= 310) gameState = STATE.MAINMENU;
                    //try again
                    if (e.getX() >= 435 && e.getX() <= 565) {
                        gP.gameStart();
                        gameState = STATE.GAME;
                    }
                    //next level
                    if (e.getX() >= 645 && e.getX() <= 780 && gP.isWin()) {
                        gP.setLevelSelection(gP.getLevelSelection() + 1);
                        gP.gameStart();
                        gameState = STATE.GAME;
                    }
                }
        }
    }

    //movement of player with arrowkeys
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: //up arrowkey moves player up
                if (!gL.getMapLayout().get(gP.getLevelSelection()).get(gL.blockCoords(gP.getPlayerX(), gP.getPlayerY() - UNIT)).equals(0)) gP.setPlayerY(gP.getPlayerY() - UNIT);
                break;

            case KeyEvent.VK_DOWN:
                if (!gL.getMapLayout().get(gP.getLevelSelection()).get(gL.blockCoords(gP.getPlayerX(), gP.getPlayerY() + UNIT)).equals(0)) gP.setPlayerY(gP.getPlayerY() + UNIT);
                break;

            case KeyEvent.VK_LEFT:
                if (!gL.getMapLayout().get(gP.getLevelSelection()).get(gL.blockCoords(gP.getPlayerX() - UNIT, gP.getPlayerY())).equals(0)) gP.setPlayerX(gP.getPlayerX() - UNIT);
                break;

            case KeyEvent.VK_RIGHT:
                if (!gL.getMapLayout().get(gP.getLevelSelection()).get(gL.blockCoords(gP.getPlayerX() + UNIT, gP.getPlayerY())).equals(0)) gP.setPlayerX(gP.getPlayerX() + UNIT);
                break;
        }
    }
    //endregion

    //region unused methods that must be here because god said so...
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    //endregion
}