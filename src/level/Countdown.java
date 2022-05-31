/**
 * Countdown
 * Countdown timer for game, if it drops to 0 game ends, player lost
 */
package level;

import input.GameInput;
import ui.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GamePanel.gameState;

public class Countdown implements ActionListener {

    //region constructors
    GamePanel gP;

    public Countdown(GamePanel gP) {
        this.gP = gP;
    }

    GameInput gI = new GameInput(this);
    //endregion

    public Timer countdownTimer = new Timer(1000, this);

    @Override
    public void actionPerformed(ActionEvent e) {
        gP.setCountdown(gP.getCountdown() - 1);
        if (gP.getCountdown() <= 0) gameState = GamePanel.STATE.GAMEOVER;
    }
}
