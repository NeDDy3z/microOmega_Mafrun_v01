package ui;

import level.GameLevel;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class GamePanel extends JPanel implements ActionListener {

    //region data
    static int SCR_WIDTH = 1000; //width of window
    static int SCR_HEIGHT = 1000; //height of window
    static int UNIT = 50; //size of "blocks"

    int playerX = 1 * 50;
    int playerY = 19 * 50;

    boolean alive = false;
    boolean win = false;

    int countdown = 75;
    int levelSelection = 0;

    Timer timer;

    private enum STATE {
        MENU,
        GAME
    };
    //endregion



    //region panel + game start&end
    public GamePanel() { //initiates panel
        this.setPreferredSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        gameStart();
    }

    public void gameStart() { //start game
        GameLevel.readLevels();
        alive = true;

        timer = new Timer(60, this);
        timer.start();

        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    public void gameOver(Graphics g) { //game end
        g.setColor(Color.BLACK);
        if (win) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("You found your throne, now you can peacefully shit.", (SCR_WIDTH - metrics.stringWidth("You found your throne, now you can peacefully shit.")) / 2, SCR_HEIGHT / 2);
        }
        else {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("You shat yourself while searching for restroom.", (SCR_WIDTH - metrics.stringWidth("You shat yourself while searching for restroom.")) / 2, SCR_HEIGHT / 2);
        }

    }
    //endregion



    //region timer
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    final Runnable runnable = new Runnable() {

        public void run() {
            countdown--;
            if (countdown < 0) {
                scheduler.shutdown();
                alive = false;
            }
        }
    };
    //endregion



    //region rendering
    public void paintComponent(Graphics g) { //calls for render
        super.paintComponent(g);
        render(g);
    }

    public void render(Graphics g) { //render of player movement
        if (alive) {
            GameLevel.getMapLayout().get(levelSelection).set(GameLevel.blockCoords(playerX, playerY), 2);

            renderMap(g);

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

    public void renderMap(Graphics g) { //renders map layout
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
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/toilet.png"), 18 * UNIT,19 * UNIT, null);
    }
    //endregion



    //region keyboard input
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        if (playerX == 18 * UNIT && playerY == 19 * UNIT) {
            win = true;
            alive = false;
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP: //up arrowkey moves player up
                    if (!GameLevel.getMapLayout().get(levelSelection).get(GameLevel.blockCoords(playerX, playerY - UNIT)).equals(0)) playerY -= UNIT;
                    break;

                case KeyEvent.VK_DOWN:
                    if (!GameLevel.getMapLayout().get(levelSelection).get(GameLevel.blockCoords(playerX, playerY + UNIT)).equals(0)) playerY += UNIT;
                    break;

                case KeyEvent.VK_LEFT:
                    if (!GameLevel.getMapLayout().get(levelSelection).get(GameLevel.blockCoords(playerX - UNIT, playerY)).equals(0)) playerX -= UNIT;
                    break;

                case KeyEvent.VK_RIGHT:
                    if (!GameLevel.getMapLayout().get(levelSelection).get(GameLevel.blockCoords(playerX + UNIT, playerY)).equals(0)) playerX += UNIT;
                    break;
            }
        }
    }
    //endregion
}