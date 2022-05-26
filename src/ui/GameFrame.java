package ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    
    public GameFrame() {
        this.add(new GamePanel());

        this.setTitle("Poopie Mafrun");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null); //opens window in the middle of a screen
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("gamefiles/toilet.png"));
    }

}
