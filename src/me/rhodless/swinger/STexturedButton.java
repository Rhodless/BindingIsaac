package me.rhodless.swinger;

import me.rhodless.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class STexturedButton extends AbstractButton {
    private Image texture;

    private Image textureHover;

    private Image textureDisabled;

    public STexturedButton(BufferedImage texture) {
        this(texture, (BufferedImage) null, (BufferedImage) null);
    }

    public STexturedButton(BufferedImage texture, BufferedImage textureHover) {
        this(texture, textureHover, (BufferedImage) null);
    }

    public STexturedButton(BufferedImage texture, BufferedImage textureHover, BufferedImage textureDisabled) {
        if (texture == null)
            throw new IllegalArgumentException("texture == null");
        this.texture = texture;
        if (textureHover == null) {
            this.textureHover = Game.fillImage(Game.copyImage(texture), Game.HOVER_COLOR, getParent());
        } else {
            this.textureHover = textureHover;
        }
        if (textureDisabled == null) {
            this.textureDisabled = Game.fillImage(Game.copyImage(texture), Game.DISABLED_COLOR, getParent());
        } else {
            this.textureDisabled = textureDisabled;
        }
    }

    public void paintComponent(Graphics g) {
        Image texture;
        super.paintComponent(g);
        if (!isEnabled()) {
            texture = this.textureDisabled;
        } else if (isHover()) {
            texture = this.textureHover;
        } else {
            texture = this.texture;
        }
        Game.drawFullsizedImage(g, (JComponent) this, texture);
        if (getText() != null) {
            Game.activateAntialias(g);
            if (getTextColor() != null)
                g.setColor(getTextColor());
            Game.drawCenteredString(g, getText(), getBounds());
        }
    }

    public void setTexture(Image texture) {
        if (texture == null)
            throw new IllegalArgumentException("texture == null");
        this.texture = texture;
        repaint();
    }

    public void setTextureHover(Image textureHover) {
        if (textureHover == null)
            throw new IllegalArgumentException("textureHover == null");
        this.textureHover = textureHover;
        repaint();
    }

    public void setTextureDisabled(Image textureDisabled) {
        if (textureDisabled == null)
            throw new IllegalArgumentException("textureDisabled == null");
        this.textureDisabled = textureDisabled;
        repaint();
    }

    public Image getTexture() {
        return this.texture;
    }

    public Image getTextureHover() {
        return this.textureHover;
    }

    public Image getTextureDisabled() {
        return this.textureDisabled;
    }

    public void setBounds(int x, int y) {
        setBounds(x, y, this.texture.getWidth(getParent()), this.texture.getHeight(getParent()));
    }
}
