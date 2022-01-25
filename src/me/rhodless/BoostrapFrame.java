package me.rhodless;

import com.sun.awt.AWTUtilities;
import me.rhodless.panel.BoostrapPanel;
import me.rhodless.swinger.Animator;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class BoostrapFrame extends JFrame {

    private static BoostrapFrame instance;

    public BoostrapFrame() {
        instance = this;
        BoostrapFrame.getInstance().setUndecorated(true);
        Game.setResourcePath("/me/rhodless/resources/");

        AWTUtilities.setWindowOpacity(this, 0.0F);

        this.setTitle("The Binding of Isaac");
        this.setSize(358, 536);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setIconImage(Game.getResource("logo.png"));
        this.setContentPane(new BoostrapPanel());

        this.setVisible(true);
        Animator.fadeInFrame(this, 5);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                GameFrame.main(new String[]{});
                setVisible(false);
            }
        }, 2000);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new BoostrapFrame();
    }

    public static BoostrapFrame getInstance() {
        return instance;
    }

}
