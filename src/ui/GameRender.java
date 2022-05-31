package ui;

import java.awt.*;

import level.GameLevel;

import static ui.GamePanel.*;
import static level.GameLevel.*;

public class GameRender {

    //region constructors
    GamePanel gP;
    GameLevel gL;

    public GameRender(GamePanel gP) {
        this.gP = gP;
    }
    public GameRender(GameLevel gL) {
        this.gL = gL;
    }
    //endregion

    //region game
    public void renderGame(Graphics g) { //render of player position & countdown timer
        getMapLayout().get(gP.getLevelSelection()).set(blockCoords(gP.getPlayerX(), gP.getPlayerY()), 2); //uncovering paths

        renderLevel(g);

        //player
        g.setColor(Color.BLUE);
        g.fillOval(gP.getPlayerX() + 5, gP.getPlayerY() + 5, UNIT - 10, UNIT - 10);

        //countdown
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(String.valueOf(gP.getCountdown()), (SCR_WIDTH - g.getFontMetrics().stringWidth(String.valueOf(gP.getCountdown()))) / 2, g.getFont().getSize());
    }

    //render level
    public void renderLevel(Graphics g) { //render of map layout
        int x = 0;
        int y = 0;
        int block = 0;
        for (int i = 0; i < SCR_HEIGHT / UNIT; i++) {
            for (int j = 0; j < SCR_WIDTH / UNIT; j++) {
                g.setColor(Color.BLACK);
                if (getMapLayout().get(gP.getLevelSelection()).get(block).equals(0) || getMapLayout().get(gP.getLevelSelection()).get(block).equals(1)) g.fillRect(x * UNIT, y * UNIT, UNIT, UNIT);
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
        g.fillRect(1 * UNIT, 19 * UNIT, UNIT, UNIT); //start
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/images/toilet.png"), 18 * UNIT,19 * UNIT, null); //finish
    }

    public void renderGameOver(Graphics g) {
        gP.countdownStop();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        //selection
        g.drawString("Menu", (SCR_WIDTH - g.getFontMetrics().stringWidth("Menu")) / 4, 900);
        g.drawString("Try again", (SCR_WIDTH - g.getFontMetrics().stringWidth("Try again")) / 2, 900);
        //game message
        if (gP.isWin()) {
            g.drawString("After "+ gP.getCountdown() +"s - You found your throne, now you can peacefully shit.", (SCR_WIDTH - g.getFontMetrics().stringWidth("After "+ gP.getCountdown() +"s - You found your throne, now you can peacefully shit.")) / 2, SCR_HEIGHT / 2);
            if (gP.getLevelSelection() + 1 < getMapLayout().size()) g.drawString("Next level", (SCR_WIDTH - g.getFontMetrics().stringWidth("Next level")) / 4 * 3, 900);
        }
        else g.drawString("You shat yourself while searching for restroom.", (SCR_WIDTH - g.getFontMetrics().stringWidth("You shat yourself while searching for restroom.")) / 2, SCR_HEIGHT / 2);
    }
    //endregion

    //region menu
    public void renderMainMenu(Graphics g) { //render mainmenu
        //background
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/images/menu.png"), 0, 0, null);

        g.setColor(Color.WHITE);
        //title
        g.setFont(new Font("Segoe UI", Font.BOLD, 100));
        g.drawString("Poopie Mafrun", (SCR_WIDTH - g.getFontMetrics().stringWidth("Poopie Mafrun")) / 2, 300);
        //subtitle
        g.setFont(new Font("Segoe UI", Font.ITALIC, 25));
        g.drawString("Level selected: "+ (gP.getLevelSelection() + 1), (SCR_WIDTH - g.getFontMetrics().stringWidth("Level selected: "+ gP.getLevelSelection() +" ")) / 2, 350);
        //selection
        g.setFont(new Font("Segoe UI", Font.BOLD, 50));
        g.drawString("Play", (SCR_WIDTH - g.getFontMetrics().stringWidth("Play")) / 2, 500);
        g.drawString("Select Level", (SCR_WIDTH - g.getFontMetrics().stringWidth("Select Level")) / 2, 600);
        g.drawString("Exit", (SCR_WIDTH - g.getFontMetrics().stringWidth("Exit")) / 2, 700);
    }

    public void renderLevelSelection(Graphics g) { //render submenu
        //background
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/images/menu.png"), 0, 0, null);

        g.setColor(Color.WHITE);
        //title
        g.setFont(new Font("Segoe UI", Font.BOLD, 100));
        g.drawString("Poopie Mafrun", (SCR_WIDTH - g.getFontMetrics().stringWidth("Poopie Mafrun")) / 2, 300);
        //subtitle
        g.setFont(new Font("Segoe UI", Font.ITALIC, 25));
        g.drawString("Level selected: "+ (gP.getLevelSelection() + 1), (SCR_WIDTH - g.getFontMetrics().stringWidth("Level selected: "+ gP.getLevelSelection() +" ")) / 2, 350);
        //selection
        g.setFont(new Font("Segoe UI", Font.BOLD, 50));
        g.drawString("Level 1", (SCR_WIDTH - g.getFontMetrics().stringWidth("Level 1")) / 2, 550);
        g.drawString("Level 2", (SCR_WIDTH - g.getFontMetrics().stringWidth("Level 2")) / 2, 650);
        g.drawString("Level 3", (SCR_WIDTH - g.getFontMetrics().stringWidth("Level 3")) / 2, 750);

        g.drawString("Back", (SCR_WIDTH - g.getFontMetrics().stringWidth("Back")) / 2, 850);
    }
    //endregion
}