package me.rhodless;

import com.sun.awt.AWTUtilities;
import me.rhodless.swinger.Animator;

import javax.swing.*;

public class GameFrame extends JFrame {

    private static GameFrame instance;
    private JPanel gamePanel;

    public GameFrame() {
        instance = this;
        GameFrame.getInstance().setUndecorated(true);
        Game.setResourcePath("/me/rhodless/resources/");

        AWTUtilities.setWindowOpacity(this, 0.0F);

        this.setTitle("The Binding of Isaac");
        this.setSize(928, 536);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setIconImage(Game.getResource("logo.png"));
        this.setContentPane(gamePanel = new WaitingPanel());

        this.setVisible(true);
        Animator.fadeInFrame(this, 5);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new GameFrame();
    }

    public static GameFrame getInstance() {
        return instance;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }
}
