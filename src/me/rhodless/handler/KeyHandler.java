package me.rhodless.handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("tesssssssst");

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            upPressed = true;
        }

        if(key == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if(key == KeyEvent.VK_S) {
            downPressed = true;
        }

        if(key == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("tesssssssst2");

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            upPressed = false;
        }

        if(key == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if(key == KeyEvent.VK_S) {
            downPressed = false;
        }

        if(key == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("tesssssssst3");

    }
}
