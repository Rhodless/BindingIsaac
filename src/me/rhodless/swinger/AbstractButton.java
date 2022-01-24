package me.rhodless.swinger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public abstract class AbstractButton extends JComponent implements MouseListener {
    private String text;

    private Color textColor;

    private ArrayList<SwingerEventListener> eventListeners = new ArrayList<SwingerEventListener>();

    private boolean hover = false;

    public AbstractButton() {
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (isEnabled())
            for (SwingerEventListener eventListener : this.eventListeners)
                eventListener.onEvent(new SwingerEvent(this, 0));
    }

    public void mouseEntered(MouseEvent e) {
        this.hover = true;
        this.
                repaint();
    }

    public void mouseExited(MouseEvent e) {
        this.hover = false;
        repaint();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        repaint();
    }

    public void setText(String text) {
        if (text == null)
            throw new IllegalArgumentException("text == null");
        this.text = text;
        repaint();
    }

    public String getText() {
        return this.text;
    }

    public void setTextColor(Color textColor) {
        if (textColor == null)
            throw new IllegalArgumentException("textColor == null");
        this.textColor = textColor;
        repaint();
    }

    public Color getTextColor() {
        return this.textColor;
    }

    public void addEventListener(SwingerEventListener eventListener) {
        if (eventListener == null)
            throw new IllegalArgumentException("eventListener == null");
        this.eventListeners.add(eventListener);
    }

    public ArrayList<SwingerEventListener> getEventListeners() {
        return this.eventListeners;
    }

    public boolean isHover() {
        return this.hover;
    }
}
