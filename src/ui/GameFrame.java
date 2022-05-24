package ui;

import javax.swing.*;

public class GameFrame extends JFrame{

    public GameFrame() {
        this.add(new GamePanel());

        this.setTitle("Poopie Mafrun");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //opens window in the middle of a screen
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

}
