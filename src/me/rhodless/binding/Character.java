package me.rhodless.binding;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Character {

    ISAAC("Isaac", "/me/rhodless/resources/entities/isaac.png", "/me/rhodless/resources/entities/isaac_b.png"),
    MAGDALENE("Magdalene", "/me/rhodless/resources/entities/magdalene.png", "/me/rhodless/resources/entities/magdalene_b.png"),
    CAIN("Cain", "/me/rhodless/resources/entities/cain.png", "/me/rhodless/resources/entities/cain_b.png");

    private final String name;
    private final String path;
    private final String b_path;

    Character(String name, String path, String b_path) {
        this.name = name;
        this.path = path;
        this.b_path = b_path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getB_path() {
        return b_path;
    }

    public BufferedImage getImage() {
        try {
            return ImageIO.read(this.getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getBImage() {
        try {
            return ImageIO.read(this.getClass().getResourceAsStream(b_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Character next(Character old) {
        int i = 0;
        for (Character value : values()) {
            if (value == old) break;
            i++;
        }
        if (i + 1 >= values().length) {
            return ISAAC;
        }
        return values()[i + 1];
    }

    public static Character before(Character old) {
        int i = 0;
        for (Character value : values()) {
            if (value == old) break;
            i++;
        }
        if (i - 1 < 0) {
            return values()[values().length - 1];
        }
        return values()[i - 1];
    }

}

