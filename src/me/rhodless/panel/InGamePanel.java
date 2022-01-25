package me.rhodless.panel;

import me.rhodless.GameFrame;
import me.rhodless.panel.manager.WindowJPanel;
import me.rhodless.swinger.SwingerEvent;
import me.rhodless.swinger.SwingerEventListener;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class InGamePanel extends WindowJPanel implements SwingerEventListener, Runnable {

    Thread gameThread;

    public InGamePanel() {
        super();
        this.addComponentListener(new FrameListen());
        this.setBackground(Color.black);

        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void onEvent(SwingerEvent event) {
        super.onEvent(event);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, 48, 48);
        g2.dispose();
    }

    public void update() {

    }

    @Override
    public void run() {

        while (gameThread != null) {
            update();
            repaint();
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
