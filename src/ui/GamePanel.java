package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.TimerTask;

import level.Countdown;
import level.GameLevel;
import input.GameInput;

import static level.GameLevel.*;

public class GamePanel extends JPanel implements ActionListener {

    //region data
    public final static int SCR_WIDTH = 1000; //width of window
    public final static int SCR_HEIGHT = 1000; //height of window
    public final static int UNIT = 50; //size of "blocks"

    private int playerX = 1 * UNIT;
    private int playerY = 19 * UNIT;
    private int levelSelection = 0;
    private int countdown = 100;
    private boolean alive = false;
    private boolean win = false;
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

    public void gameStart() { //start game
        delete();
        readLevels();

        playerX = 1 * UNIT;
        playerY = 19 * UNIT;

        alive = true;
        win = false;
        countdown = 75;

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

    //region call for render
    public void paintComponent(Graphics g) {
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

    //region actionperformed
    public void actionPerformed(ActionEvent e) {
        repaint();

        if (playerX == 18 * UNIT && playerY == 19 * UNIT) {
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