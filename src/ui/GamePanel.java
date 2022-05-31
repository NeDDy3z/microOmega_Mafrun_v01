/**
 * GamePanel
 * initates JPanel
 * Starting game
 * All game variables are here
 * Rendering what is needed
 */
package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import level.Countdown;
import level.GameLevel;
import input.GameInput;

import static level.GameLevel.*;

public class GamePanel extends JPanel implements ActionListener {

    //region variables, constructors, enum
    public final static int SCR_WIDTH = 1000; //width of window
    public final static int SCR_HEIGHT = 1000; //height of window
    public final static int UNIT = 50; //size of "blocks"

    private int playerX;
    private int playerY;
    private int levelSelection = 0; //initial selected level
    private int countdown;
    private boolean alive;
    private boolean win;
    private Timer timer;

    public static enum STATE {
        MAINMENU,
        LEVELMENU,
        GAME,
        GAMEOVER
    }
    public static STATE gameState = STATE.MAINMENU;

    GameRender gR = new GameRender(this);
    GameLevel gL = new GameLevel(this);
    Countdown cd = new Countdown(this);
    //endregion

    //region panel + game start
    public GamePanel() { //initiates panel
        this.setPreferredSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new GameInput(this));
        this.addMouseListener(new GameInput(this));

        gameStart();
    }

    public void gameStart() { //start game - read levels from files, set initial parameters for player
        readLevels();

        playerX = 1 * UNIT;
        playerY = 19 * UNIT;
        countdown = 75;
        alive = true;
        win = false;

        timer = new Timer(60, this);
        timer.start();
    }
    //endregion

    //region timer
    public void countdownStart() {
        cd.countdownTimer.start();
    }
    public void countdownStop() {
        cd.countdownTimer.stop();
    }
    //endregion

    //region render
    public void paintComponent(Graphics g) { //chooses what to render based on enum state
        super.paintComponent(g);

        switch (gameState) {
            case MAINMENU -> gR.renderMainMenu(g);
            case LEVELMENU -> gR.renderLevelSelection(g);
            case GAME -> {
                if (alive) gR.renderGame(g);
                else gameState = STATE.GAMEOVER;
            }
            case GAMEOVER -> gR.renderGameOver(g);
        }
    }
    //endregion

    //region action performed
    public void actionPerformed(ActionEvent e) { //if anything happens it forces rerender
        repaint();

        if (playerX == 18 * UNIT && playerY == 19 * UNIT) { //check if player stepped on finish "block"
            win = true;
            alive = false;
        }
    }
    //endregion

    //region get&set
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
    public int getLevelSelection() {
        return levelSelection;
    }
    public int getCountdown() {
        return countdown;
    }
    public boolean isAlive() {
        return alive;
    }
    public boolean isWin() {
        return win;
    }
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
    public void setLevelSelection(int levelSelection) {
        this.levelSelection = levelSelection;
    }
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setWin(boolean win) {
        this.win = win;
    }
    //endregion
}