package me.rhodless.panel.manager;

import me.rhodless.Game;
import me.rhodless.GameFrame;
import me.rhodless.swinger.STexturedButton;
import me.rhodless.swinger.SwingerEvent;
import me.rhodless.swinger.SwingerEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class WindowJPanel extends JPanel implements SwingerEventListener {

    private final STexturedButton quitButton;
    private final STexturedButton windowButton;
    private final STexturedButton hideButton;


    public WindowJPanel() {
        quitButton = new STexturedButton(Game.getResource("exit.png"));
        windowButton = new STexturedButton(Game.getResource("window.png"));
        hideButton = new STexturedButton(Game.getResource("hide.png"));

        setLayout(null);

        quitButton.setBounds(899, 4);
        quitButton.setSize(new Dimension(25, 25));
        quitButton.addEventListener(this);

        windowButton.setBounds(870, 4);
        windowButton.setSize(new Dimension(25, 25));
        windowButton.addEventListener(this);

        hideButton.setBounds(839, 4);
        hideButton.setSize(new Dimension(25, 25));
        hideButton.addEventListener(this);

        add(quitButton);
        add(windowButton);
        add(hideButton);

        this.addComponentListener(new FrameListen());
    }


    @Override
    public void onEvent(SwingerEvent event) {
        Game.doWindowCheck(event, quitButton, windowButton, hideButton);
    }

    private class FrameListen implements ComponentListener {
        public void componentHidden(ComponentEvent arg0) {
        }

        public void componentMoved(ComponentEvent arg0) {
        }

        public void componentResized(ComponentEvent arg0) {
            try {
                Dimension screenSize = GameFrame.getInstance().getSize();

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
