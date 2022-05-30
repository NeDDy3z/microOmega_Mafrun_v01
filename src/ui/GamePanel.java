package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import data.GameLevel;
import input.GameInput;

import static data.GameLevel.*;

public class GamePanel extends JPanel implements ActionListener {

    //region data
    public final static int SCR_WIDTH = 1000; //width of window
    public final static int SCR_HEIGHT = 1000; //height of window
    public final static int UNIT = 50; //size of "blocks"

    int playerX = 1 * UNIT;
    int playerY = 19 * UNIT;
    int levelSelection = 0;
    int countdown = 75;
    boolean alive = false;
    boolean win = false;
    public Timer timer;

    public static enum STATE {
        MAINMENU,
        LEVELMENU,
        GAME,
        GAMEOVER
    }
    public static STATE gameState = STATE.MAINMENU;

    GameRender gR = new GameRender(this);
    GameLevel gL = new GameLevel(this);
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
    public ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public Runnable runnable = new Runnable() {

        public void run() {
            countdown--;
            if (countdown <= 0) {
                scheduler.shutdown();
                gameState = STATE.GAMEOVER;
            }
        }
    };

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
    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }
    public Runnable getRunnable() {
        return runnable;
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
    public void setScheduler(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }
    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
//endregion
}