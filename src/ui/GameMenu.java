package ui;

import java.awt.*;

public class GameMenu {

    public static void renderMainMenu(Graphics g) {
        //background
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/images/menu.png"), 0, 0, null);

        g.setColor(Color.WHITE);
        //title
        g.setFont(new Font("Segoe UI", Font.BOLD, 100));
        g.drawString("Poopie Mafrun", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Poopie Mafrun")) / 2, 300);
        //subtitle
        g.setFont(new Font("Segoe UI", Font.ITALIC, 25));
        g.drawString("Level selected: "+ (GamePanel.levelSelection + 1), (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Level selected: "+ GamePanel.levelSelection +" ")) / 2, 350);
        //title select things...
        g.setFont(new Font("Segoe UI", Font.BOLD, 50));
        g.drawString("Play", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Play")) / 2, 500);
        g.drawString("Select Level", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Select Level")) / 2, 600);
        g.drawString("Exit", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Exit")) / 2, 700);
    }

    public static void renderLevelSelection(Graphics g) {
        //background
        g.drawImage(Toolkit.getDefaultToolkit().getImage("gamefiles/images/menu.png"), 0, 0, null);

        g.setColor(Color.WHITE);
        //title
        g.setFont(new Font("Segoe UI", Font.BOLD, 100));
        g.drawString("Poopie Mafrun", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Poopie Mafrun")) / 2, 300);
        //subtitle
        g.setFont(new Font("Segoe UI", Font.ITALIC, 25));
        g.drawString("Level selected: "+ (GamePanel.levelSelection + 1), (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Level selected: "+ GamePanel.levelSelection +" ")) / 2, 350);
        //level select things...
        g.setFont(new Font("Segoe UI", Font.BOLD, 50));
        g.drawString("Level 1", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Level 1")) / 2, 500);
        g.drawString("Level 2", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Level 2")) / 2, 600);
        g.drawString("Level 3", (GamePanel.SCR_WIDTH - g.getFontMetrics().stringWidth("Level 3")) / 2, 700);
    }
}
