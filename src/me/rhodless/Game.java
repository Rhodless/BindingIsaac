package me.rhodless;

import me.rhodless.swinger.Animator;
import me.rhodless.swinger.STexturedButton;
import me.rhodless.swinger.SwingerEvent;
import sun.font.TrueTypeFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.*;

public class Game {

    private static String resourcePath;

    public static final int LITTLE_TRANSPARENT = 50;

    public static final Color LITTLE_TRANSPARENT_WHITE = getTransparentWhite(50);

    public static final Color HOVER_COLOR = LITTLE_TRANSPARENT_WHITE;

    public static final Color DISABLED_COLOR = getTransparentInstance(Color.GRAY, 50);

    public static final BufferedImage EMPTY = getResourceIgnorePath("/me/rhodless/resources/empty.png");

    public static Font FONT;

    public static void setResourcePath(String resourcePath) {
        Game.resourcePath = resourcePath.endsWith("/") ? resourcePath.substring(0, resourcePath.length() - 1) : resourcePath;
    }

    public static String getResourcePath() {
        return resourcePath;
    }

    public static BufferedImage getResource(String resource) {
        try {
            return ImageIO.read(Game.class.getResourceAsStream(getResourcePath() + "/" + resource));
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't load the given resource (" + getResourcePath() + "/" + resource + ") : " + e);
        }
    }

    public static Color getTransparentWhite(int transparency) {
        return getTransparentInstance(Color.WHITE, transparency);
    }

    public static Color getTransparentInstance(Color color, int transparency) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency);
    }

    public static Image fillImage(Image image, Color color, ImageObserver imageObserver) {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(imageObserver), image.getHeight(imageObserver));
        return image;
    }

    public static BufferedImage copyImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static BufferedImage getResourceIgnorePath(String resource) {
        try {
            return ImageIO.read(Game.class.getResourceAsStream(resource));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't load the given resource (" + resource + ") : " + e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't load the given resource (" + resource + ") : " + e);
        }
    }

    public static int percentage(int value, int maximum) {
        return crossMult(value, maximum, 100);
    }

    public static int crossMult(int value, int maximum, int coefficient) {
        return (int)(value / maximum * coefficient);
    }

    public static Point getRecCenterPos(Rectangle parent, Rectangle rectangle) {
        double x = parent.getWidth() / 2.0D - rectangle.getWidth() / 2.0D;
        double y = parent.getHeight() / 2.0D - rectangle.getHeight() / 2.0D;
        return new Point((int)x, (int)y);
    }

    public static void drawCenteredString(Graphics g, String str, Rectangle parent) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D stringBounds = fm.getStringBounds(str, g);
        Point centerPos = getRecCenterPos(parent, stringBounds.getBounds());
        g.drawString(str, (int)centerPos.getX(), (int)centerPos.getY());
    }

    public static void activateAntialias(Graphics g) {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    public static BufferedImage colorImage(BufferedImage image, int red, int green, int blue) {
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), 3);
        Graphics2D graphics = img.createGraphics();
        Color newColor = new Color(red, green, blue, 0);
        graphics.setXORMode(newColor);
        graphics.drawImage(image, (BufferedImageOp)null, 0, 0);
        graphics.dispose();
        return img;
    }

    public static void drawFullsizedImage(Graphics g, JComponent component, Image image) {
        g.drawImage(image, 0, 0, component.getWidth(), component.getHeight(), component);
    }

    public static void fillFullsizedRect(Graphics g, JComponent component) {
        g.fillRect(0, 0, component.getWidth(), component.getHeight());
    }

    public static void fillFullsizedRect(Graphics g, JComponent component, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, component.getWidth(), component.getHeight());
    }

    public static void doWindowCheck(SwingerEvent event, STexturedButton quitButton, STexturedButton windowButton, STexturedButton hideButton) {
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

}
