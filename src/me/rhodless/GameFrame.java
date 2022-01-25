package me.rhodless;

import com.sun.awt.AWTUtilities;
import me.rhodless.binding.Character;
import me.rhodless.panel.WaitingPanel;
import me.rhodless.swinger.Animator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {

    private static GameFrame instance;
    private JPanel gamePanel;

    public GameFrame() throws IOException, FontFormatException {
        instance = this;
        GameFrame.getInstance().setUndecorated(true);
        Game.setResourcePath("/me/rhodless/resources/");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Game.FONT = Font.createFont(Font.TRUETYPE_FONT, new File("src/me/rhodless/resources/font.ttf"));

        AWTUtilities.setWindowOpacity(this, 0.0F);

        this.setTitle("The Binding of Isaac");
        this.setSize(928, 536);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setIconImage(Game.getResource("logo.png"));
        this.setContentPane(gamePanel = new WaitingPanel());

        this.setVisible(true);
        Animator.fadeInFrame(this, 5);

        GameData.setCharacter(Character.ISAAC);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        try {
            new GameFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GameFrame getInstance() {
        return instance;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.remove(gamePanel);
        this.gamePanel = gamePanel;
        this.setContentPane(gamePanel);
    }
}
