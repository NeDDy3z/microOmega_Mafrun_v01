package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements ActionListener {

    //region data
    static final int SCR_WIDTH = 600; //width of window
    static final int SCR_HEIGHT = 600; //hieght of window

    static final int UNIT = 50; //size of "blocks"

    boolean alive = false;
    int playerX;
    int playerY;

    Timer timer;
    //endregion



    //region panel + game start&end
    GamePanel() { //initiates window and starts game
        this.setPreferredSize(new Dimension(SCR_WIDTH, SCR_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        gameStart();
    }

    public void gameStart() { //starts timer
        newPlayer();
        alive = true;

        timer = new Timer(50, this);
        timer.start();
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("You survived:  without shitting", (SCR_WIDTH - metrics.stringWidth("You survived:  without shitting")) / 2, g.getFont().getSize());
    }

    public void newPlayer() { //spawn of player
        playerX = 1 * 50;
        playerY = 11 * 50;
    }
    //endregion



    //region rendering
    public void paintComponent(Graphics g) { //calls for render (ig)
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) { //render of movement etc.

        if (alive) {
            //region GRID
            for (int i = 0; i < SCR_HEIGHT / UNIT; i++) {
                g.drawLine(i * UNIT, 0, i * UNIT, SCR_HEIGHT);
                g.drawLine(0, i * UNIT, SCR_WIDTH, i * UNIT);
            }
            //endregion

            g.setColor(Color.BLUE);
            g.fillOval(playerX + 5, playerY + 5, UNIT - 10, UNIT - 10); //+5 & -10 is so that player is smaller and in middle of "block"


            /*
            //region Score
            g.setColor(Color.red); //až potom co udělám tělo
            g.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics metricsScore = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metricsScore.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
            //endregion
             */
        }
        else gameOver(g);
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
                case KeyEvent.VK_UP: //left arrowkey moves player to the left
                    playerY -= UNIT;
                    break;

                case KeyEvent.VK_DOWN:
                    playerY += UNIT;
                    break;

                case KeyEvent.VK_LEFT:
                    playerX -= UNIT;
                    break;

                case KeyEvent.VK_RIGHT:
                    playerX += UNIT;
                    break;
            }
        }
    }
    //endregion
}