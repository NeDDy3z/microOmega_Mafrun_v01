package ui;

import level.GameLevel;
import logic.GameLogic;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements ActionListener {

    //region data
    static final int SCR_WIDTH = 600; //width of window
    static final int SCR_HEIGHT = 600; //height of window

    static final int UNIT = 50; //size of "blocks"

    boolean alive = false;
    int playerX = 1 * 50;
    int playerY = 11 * 50;

    Timer timer;
    //endregion



    //region panel + game start&end
    public GamePanel() { //initiates panel
        this.setPreferredSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        gameStart();
    }

    public void gameStart() { //start game
        alive = true;

        timer = new Timer(50, this);
        timer.start();
    }

    public void gameOver(Graphics g) { //game end
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("You survived:  without shitting", (SCR_WIDTH - metrics.stringWidth("You survived:  without shitting")) / 2, g.getFont().getSize());
    }
    //endregion



    //region rendering
    public void paintComponent(Graphics g) { //calls for render
        super.paintComponent(g);
        draw(g);
        mapLoad(g);
    }

    public void draw(Graphics g) { //render of player movement

        if (alive) {
            /* GRID
            for (int i = 0; i < SCR_HEIGHT / UNIT; i++) {
                g.drawLine(i * UNIT, 0, i * UNIT, SCR_HEIGHT);
                g.drawLine(0, i * UNIT, SCR_WIDTH, i * UNIT);
            }
            */
            g.setColor(Color.BLUE);
            g.fillOval(playerX + 5, playerY + 5, UNIT - 10, UNIT - 10); //+5 & -10 is so that player is smaller and in middle of "block"
        }
        else gameOver(g);
    }

    public void mapLoad(Graphics g) { //loads map layout
        g.setColor(Color.BLACK);
        int x = 0;
        int y = 0;
        int block = 0;
        for (int i = 0; i < SCR_HEIGHT / UNIT; i++) {
            for (int j = 0; j < SCR_WIDTH / UNIT; j++) {
                if (GameLevel.getLayout().get(block).equals(0)) g.fillRect(x * 50, y * 50, UNIT, UNIT);
                x++;
                block++;
            }
            y++;
            x = 0;
        }
    }
    //endregion



    //region keybind
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP: //up arrowkey moves player up
                    if (GameLevel.getLayout().get(GameLevel.blockCoords(playerX, playerY - UNIT)).equals(1)) playerY -= UNIT; //prevents from entering walls - if block is empty --> 1 then u can go
                    break;

                case KeyEvent.VK_DOWN:
                    if (GameLevel.getLayout().get(GameLevel.blockCoords(playerX, playerY + UNIT)).equals(1)) playerY += UNIT;
                    break;

                case KeyEvent.VK_LEFT:
                    if (GameLevel.getLayout().get(GameLevel.blockCoords(playerX - UNIT, playerY)).equals(1)) playerX -= UNIT;
                    break;

                case KeyEvent.VK_RIGHT:
                    if (GameLevel.getLayout().get(GameLevel.blockCoords(playerX + UNIT, playerY)).equals(1)) playerX += UNIT;
                    break;
            }
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

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
    //endregion
}