package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import input.GameInput;

import static level.GameLevel.*;

public class GamePanel extends JPanel implements ActionListener {

    //region data
    public final static int SCR_WIDTH = 1000; //width of window
    public final static int SCR_HEIGHT = 1000; //height of window
    public final static int UNIT = 50; //size of "blocks"

    public static int playerX = 1 * 50;
    public static int playerY = 19 * 50;
    public static int levelSelection = 0;
    private static int countdown = 75;

    private boolean alive = false;
    private boolean win = false;

    private Timer timer;

    public static enum STATE {
        MAINMENU,
        LEVELMENU,
        GAME
    }

    public static STATE gameState = STATE.MAINMENU;
    //endregion



    //region panel + game start&end
    public GamePanel() { //initiates panel
        this.setPreferredSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new GameInput());
        this.addMouseListener(new GameInput());

        gameStart();
    }

    public void gameStart() { //start game
        readLevels();
        alive = true;

        timer = new Timer(60, this);
        timer.start();
    }

    public void gameOver(Graphics g) { //game end
        scheduler.shutdown();

        g.setColor(Color.BLACK);
        if (win) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("After "+ countdown+"s - You found your throne, now you can peacefully shit.", (SCR_WIDTH - metrics.stringWidth("After "+ countdown+"s - You found your throne, now you can peacefully shit.")) / 2, SCR_HEIGHT / 2);
        }
        else {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("You shat yourself while searching for restroom.", (SCR_WIDTH - metrics.stringWidth("You shat yourself while searching for restroom.")) / 2, SCR_HEIGHT / 2);
        }

    }
    //endregion



    //region timer
    public final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public final static Runnable runnable = new Runnable() {

        public void run() {
            countdown--;
            if (countdown <= 0) {
                scheduler.shutdown();
            }
        }
    };
    //endregion



    //region rendering
    public void paintComponent(Graphics g) { //calls for render
        super.paintComponent(g);

        if (gameState == STATE.MAINMENU) GameMenu.renderMainMenu(g);
        if (gameState == STATE.LEVELMENU) GameMenu.renderLevelSelection(g);
        if (gameState == STATE.GAME) renderGame(g);
    }

    //render of map & player movement & countdown timer / game ending
    public void renderGame(Graphics g) {
        if (alive || countdown > 0) {
            getMapLayout().get(levelSelection).set(blockCoords(playerX, playerY), 2);

            renderLevel(g);

            //player
            g.setColor(Color.BLUE);
            g.fillOval(playerX + 5, playerY + 5, UNIT - 10, UNIT - 10); //+5 & -10 is so that player is smaller and in middle of "block"

            //countdown
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(String.valueOf(countdown), (SCR_WIDTH - g.getFontMetrics(g.getFont()).stringWidth(String.valueOf(countdown))) / 2, g.getFont().getSize());
        }
        else gameOver(g);
    }
    //endregion



    //after any action repaint (also checks if player got to the finish)
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        if (playerX == 18 * UNIT && playerY == 19 * UNIT) {
            win = true;
            alive = false;
        }
    }
}