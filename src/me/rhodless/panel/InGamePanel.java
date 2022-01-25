package me.rhodless.panel;

import me.rhodless.Game;
import me.rhodless.GameData;
import me.rhodless.GameFrame;
import me.rhodless.handler.KeyHandler;
import me.rhodless.panel.manager.WindowJPanel;
import me.rhodless.swinger.SwingerEvent;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Arrays;

public class InGamePanel extends WindowJPanel implements Runnable {

    private final Thread thread;
    private final KeyHandler keyHandler;
    private final Image background;
    private int playerX = 928 / 2 - 32;
    private int playerY = 536 / 2 - 32;
    private int playerSpeed = 2;

    public InGamePanel() {
        super();

        this.background = Game.getResource("ingame.png");

        System.out.println(Arrays.toString(Arrays.stream(this.getKeyListeners()).toArray()));
        keyHandler = GameFrame.getInstance().getKeyHandler();

        this.addComponentListener(new FrameListen());

        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void onEvent(SwingerEvent event) {
        super.onEvent(event);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

        Dimension screenSize = GameFrame.getInstance().getSize();

        g.drawImage(GameData.getCharacter().getImage(),
                (int) (screenSize.getWidth() / (928.0F / playerX)),
                (int) (screenSize.getHeight() / (536.0F / playerY)),
                (int) (screenSize.getWidth() / 14.5),
                (int) (screenSize.getHeight() / 8.375),
                null);

    }

    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            update();
            repaint();
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyHandler.rightPressed) {
            playerX += playerSpeed;
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


            } catch (Exception ignored) {
            }

        }

        public void componentShown(ComponentEvent arg0) {

        }
    }

}
