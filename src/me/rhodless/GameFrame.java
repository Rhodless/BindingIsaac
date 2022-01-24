package me.rhodless;

import javax.swing.*;

public class GameFrame extends JFrame {

    private static GameFrame instance;
    private final GamePanel gamePanel;

    public GameFrame() {
        instance = this;
        Game.setResourcePath("/me/rhodless/resources/");

        this.setTitle("The Binding of Isaac");
        this.setSize(928, 536);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setIconImage(Game.getResource("logo.png"));
        this.setContentPane(gamePanel = new GamePanel());

        this.setVisible(true);
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

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
