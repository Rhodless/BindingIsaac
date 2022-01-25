package me.rhodless.panel;

import me.rhodless.Game;
import me.rhodless.GameFrame;
import me.rhodless.panel.manager.WindowJPanel;
import me.rhodless.swinger.STexturedButton;
import me.rhodless.swinger.SwingerEvent;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Timer;
import java.util.TimerTask;

public class WaitingPanel extends WindowJPanel {

    private final Image lobbyBackground;
    private final STexturedButton playButton;

    public WaitingPanel() {
        super();
        this.addComponentListener(new FrameListen());

        lobbyBackground = Game.getResource("lobby_background.png");
        playButton = new STexturedButton(Game.getResource("start.png"));

        playButton.setBounds(150, 350);
        playButton.setSize(new Dimension(255, 84));
        playButton.setTextureHover(Game.getResource("start_hover.png"));
        playButton.addEventListener(this);

        add(playButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lobbyBackground, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void onEvent(SwingerEvent event) {
        super.onEvent(event);

        if(event.getSource() == playButton) {
            playButton.setTexture(Game.getResource("loading.png"));
            playButton.setTextureHover(Game.getResource("loading.png"));

            GameFrame.getInstance().setGamePanel(new ChooseCharacterPanel());
            GameFrame.getInstance().setVisible(true);
        }

    }

    private class FrameListen implements ComponentListener {
        public void componentHidden(ComponentEvent arg0) {
        }

        public void componentMoved(ComponentEvent arg0) {
        }

        public void componentResized(ComponentEvent arg0) {
            try {
                Dimension screenSize = GameFrame.getInstance().getSize();
                playButton.setBounds((int) (screenSize.getWidth() / 6.18666667), (int) (screenSize.getHeight() / 1.53142857));
                playButton.setSize((int) (screenSize.getWidth() / 3.63921569), (int) (screenSize.getHeight() / 6.38095238));

            } catch (Exception ignored) {}

        }

        public void componentShown(ComponentEvent arg0) {

        }
    }

}
