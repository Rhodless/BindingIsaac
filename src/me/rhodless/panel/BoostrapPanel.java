package me.rhodless.panel;

import me.rhodless.Game;

import javax.swing.*;
import java.awt.*;

public class BoostrapPanel extends JPanel {

    private final Image lobbyBackground;

    public BoostrapPanel() {
        lobbyBackground = Game.getResource("waiting_background.png");

        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lobbyBackground, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
