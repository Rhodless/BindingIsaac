package me.rhodless;

import me.rhodless.swinger.Animator;
import me.rhodless.swinger.STexturedButton;
import me.rhodless.swinger.SwingerEvent;
import me.rhodless.swinger.SwingerEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WaitingPanel extends JPanel implements SwingerEventListener {

    private final Image lobbyBackground;
    private final STexturedButton playButton;
    private final STexturedButton quitButton;
    private final STexturedButton windowButton;
    private final STexturedButton hideButton;

    public WaitingPanel() {
        this.addComponentListener(new FrameListen());

        lobbyBackground = Game.getResource("lobby_background.png");
        playButton = new STexturedButton(Game.getResource("start.png"));
        quitButton = new STexturedButton(Game.getResource("exit.png"));
        windowButton = new STexturedButton(Game.getResource("window.png"));
        hideButton = new STexturedButton(Game.getResource("hide.png"));

        setLayout(null);

        playButton.setBounds(150, 350);
        playButton.setSize(new Dimension(255, 84));
        playButton.setTextureHover(Game.getResource("start_hover.png"));


        quitButton.setBounds(899, 4);
        quitButton.setSize(new Dimension(25, 25));
        quitButton.addEventListener(this);

        windowButton.setBounds(870, 4);
        windowButton.setSize(new Dimension(25, 25));
        windowButton.addEventListener(this);

        hideButton.setBounds(839, 4);
        hideButton.setSize(new Dimension(25, 25));
        hideButton.addEventListener(this);

        add(playButton);
        add(quitButton);
        add(windowButton);
        add(hideButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lobbyBackground, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void onEvent(SwingerEvent event) {
        if(event.getSource() == quitButton) {
            Animator.fadeOutFrame(GameFrame.getInstance(), 2, () -> System.exit(0));
        }
        if(event.getSource() == windowButton) {
            if(GameFrame.getInstance().getExtendedState() == Frame.MAXIMIZED_BOTH) {
                GameFrame.getInstance().setExtendedState(Frame.NORMAL);
            } else {
                GameFrame.getInstance().setExtendedState(Frame.MAXIMIZED_BOTH);
            }
        }
        if(event.getSource() == hideButton) {
            GameFrame.getInstance().setState(JFrame.ICONIFIED);
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

                quitButton.setBounds((int) (screenSize.getWidth() / 1.03225806), 4);
                quitButton.setSize((int) (screenSize.getWidth() / 37.12), (int) (screenSize.getHeight() / 21.44));

                windowButton.setBounds((int) (screenSize.getWidth() / 1.06666667), 4);
                windowButton.setSize((int) (screenSize.getWidth() / 37.12), (int) (screenSize.getHeight() / 21.44));

                hideButton.setBounds((int) (screenSize.getWidth() / 1.10607867), 4);
                hideButton.setSize((int) (screenSize.getWidth() / 37.12), (int) (screenSize.getHeight() / 21.44));
            } catch (Exception ignored) {}

        }

        public void componentShown(ComponentEvent arg0) {

        }
    }

}
