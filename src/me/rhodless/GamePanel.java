package me.rhodless;

import me.rhodless.swinger.STexturedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GamePanel extends JPanel {

    private final Image lobbyBackground;
    private final STexturedButton playButton;

    public GamePanel() {
        this.addComponentListener(new FrameListen());

        this.lobbyBackground = Game.getResource("lobby_background.png");
        this.playButton = new STexturedButton(Game.getResource("start.png"));

        this.setLayout(null);

        this.playButton.setBounds(150, 350);
        this.playButton.setSize(new Dimension(255, 84));
        this.playButton.setTextureHover(Game.getResource("start_hover.png"));
        this.add(playButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(lobbyBackground, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private class FrameListen implements ComponentListener {
        public void componentHidden(ComponentEvent arg0) {
        }

        public void componentMoved(ComponentEvent arg0) {
        }

        public void componentResized(ComponentEvent arg0) {
            Dimension screenSize = GameFrame.getInstance().getSize();
            System.out.println(screenSize.toString());
            playButton.setBounds((int) (screenSize.getWidth() / 6.18666667), (int) (screenSize.getHeight() / 1.53142857));
            playButton.setSize((int) (screenSize.getWidth() / 3.63921569), (int) (screenSize.getHeight() / 6.38095238));

        }

        public void componentShown(ComponentEvent arg0) {

        }
    }

}
