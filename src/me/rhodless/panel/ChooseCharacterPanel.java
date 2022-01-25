package me.rhodless.panel;

import me.rhodless.Game;
import me.rhodless.GameData;
import me.rhodless.GameFrame;
import me.rhodless.binding.Character;
import me.rhodless.panel.manager.WindowJPanel;
import me.rhodless.swinger.STexturedButton;
import me.rhodless.swinger.SwingerEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ChooseCharacterPanel extends WindowJPanel {

    private final Image background;
    private final STexturedButton changeRight;
    private final STexturedButton changeLeft;
    private final STexturedButton character;
    private final STexturedButton firstCharacter;
    private final STexturedButton secondCharacter;
    private final STexturedButton ready;
    private final JLabel name;

    public ChooseCharacterPanel() {
        super();
        this.addComponentListener(new FrameListen());

        this.background = Game.getResource("ingame.png");

        changeRight = new STexturedButton(Game.getResource("arrow-right-solid.png"));
        changeLeft = new STexturedButton(Game.getResource("arrow-left-solid.png"));
        ready = new STexturedButton(Game.getResource("ready.png"));
        character = new STexturedButton(GameData.getCharacter().getImage());
        firstCharacter = new STexturedButton(Character.before(GameData.getCharacter()).getBImage());
        secondCharacter = new STexturedButton(Character.next(GameData.getCharacter()).getBImage());

        name = new JLabel(GameData.getCharacter().getName());

        char[] string = GameData.getCharacter().getName().toCharArray();

        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setVerticalAlignment(SwingConstants.CENTER);
        name.setBounds(new Rectangle(string.length + 250, 150, 412, 300));
        name.setFont(Game.FONT.deriveFont(Font.PLAIN, 28));
        name.setForeground(Color.white);
        name.setVisible(true);

        Dimension screenSize = GameFrame.getInstance().getSize();

        character.setBounds((int) (screenSize.getWidth() / 2.25242718), (int) (screenSize.getHeight() / 2.68));
        character.setSize(new Dimension((int) (screenSize.getWidth() / 9.28), (int) (screenSize.getHeight() / 5.36)));
        character.setTextureHover(this.character.getTexture());

        firstCharacter.setBounds(332, 200);
        firstCharacter.setSize(new Dimension(80, 80));
        firstCharacter.setTextureHover(Character.before(GameData.getCharacter()).getImage());
        firstCharacter.addEventListener(this);

        secondCharacter.setBounds(510, 200);
        secondCharacter.setSize(new Dimension(80, 80));
        secondCharacter.setTextureHover(Character.next(GameData.getCharacter()).getImage());
        secondCharacter.addEventListener(this);

        ready.setBounds(310, 410);
        ready.setSize(new Dimension(255, 84));
        ready.setTextureHover(Game.getResource("ready_hover.png"));
        ready.addEventListener(this);


        changeLeft.setBounds(357, 283);
        changeLeft.setSize(new Dimension(28, 32));
        changeLeft.addEventListener(this);
        changeLeft.setTextureHover(Game.getResource("arrow-left-hover.png"));

        changeRight.setBounds(540, 283);
        changeRight.setSize(new Dimension(28, 32));
        changeRight.addEventListener(this);
        changeRight.setTextureHover(Game.getResource("arrow-right-hover.png"));

        add(changeLeft);
        add(changeRight);
        add(character);
        add(firstCharacter);
        add(secondCharacter);
        add(name);
        add(ready);
    }


    @Override
    public void onEvent(SwingerEvent event) {
        super.onEvent(event);

        Dimension screenSize = GameFrame.getInstance().getSize();

        if (event.getSource() == firstCharacter || event.getSource() == changeLeft) {
            GameData.setCharacter(Character.before(GameData.getCharacter()));
            updateCharacter(screenSize);
        }
        if (event.getSource() == secondCharacter || event.getSource() == changeRight) {
            GameData.setCharacter(Character.next(GameData.getCharacter()));
            updateCharacter(screenSize);
        }

        if (event.getSource() == ready) {
            GameFrame.getInstance().setGamePanel(new InGamePanel());
            GameFrame.getInstance().setVisible(true);
        }

    }

    private void updateCharacter(Dimension screenSize) {
        this.character.setTexture(GameData.getCharacter().getImage());

        character.setBounds((int) (screenSize.getWidth() / 2.25242718), (int) (screenSize.getHeight() / 2.68));
        character.setSize(new Dimension((int) (screenSize.getWidth() / 9.28), (int) (screenSize.getHeight() / 5.36)));
        character.setTextureHover(this.character.getTexture());

        name.setText(GameData.getCharacter().getName());

        firstCharacter.setBounds((int) (screenSize.getWidth() / 2.79518072F), (int) (screenSize.getHeight() / 2.68));
        firstCharacter.setSize(new Dimension((int) (screenSize.getWidth() / 11.6), (int) (screenSize.getHeight() / 6.7)));
        firstCharacter.setTexture(Character.before(GameData.getCharacter()).getBImage());
        firstCharacter.setTextureHover(Character.before(GameData.getCharacter()).getImage());

        secondCharacter.setBounds((int) (screenSize.getWidth() / 1.81960784), (int) (screenSize.getHeight() / 2.68));
        secondCharacter.setSize(new Dimension((int) (screenSize.getWidth() / 11.6), (int) (screenSize.getHeight() / 6.7)));
        secondCharacter.setTexture(Character.next(GameData.getCharacter()).getBImage());
        secondCharacter.setTextureHover(Character.next(GameData.getCharacter()).getImage());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private class FrameListen implements ComponentListener {
        public void componentHidden(ComponentEvent arg0) {
        }

        public void componentMoved(ComponentEvent arg0) {
        }

        public void componentResized(ComponentEvent arg0) {
            try {
                Dimension screenSize = GameFrame.getInstance().getSize();

                character.setBounds((int) (screenSize.getWidth() / 2.25242718), (int) (screenSize.getHeight() / 2.68));
                character.setSize(new Dimension((int) (screenSize.getWidth() / 9.28), (int) (screenSize.getHeight() / 5.36)));

                changeLeft.setBounds((int) (screenSize.getWidth() / 2.59943978), (int) (screenSize.getHeight() / 1.89399293));
                changeLeft.setSize(new Dimension((int) (screenSize.getWidth() / 33.1428571), (int) (screenSize.getHeight() / 16.75)));

                changeRight.setBounds((int) (screenSize.getWidth() / 1.71851852), (int) (screenSize.getHeight() / 1.89399293));
                changeRight.setSize(new Dimension((int) (screenSize.getWidth() / 33.1428571), (int) (screenSize.getHeight() / 16.75)));

                firstCharacter.setBounds((int) (screenSize.getWidth() / 2.79518072F), (int) (screenSize.getHeight() / 2.68));
                firstCharacter.setSize(new Dimension((int) (screenSize.getWidth() / 11.6), (int) (screenSize.getHeight() / 6.7)));

                ready.setBounds((int) (screenSize.getWidth() / 2.99354839), (int) (screenSize.getHeight() / 1.30731707));
                ready.setSize(new Dimension((int) (screenSize.getWidth() / 3.63921569), (int) (screenSize.getHeight() / 6.38095238)));

                secondCharacter.setBounds((int) (screenSize.getWidth() / 1.81960784), (int) (screenSize.getHeight() / 2.68));
                secondCharacter.setSize(new Dimension((int) (screenSize.getWidth() / 11.6), (int) (screenSize.getHeight() / 6.7)));

                char[] string = GameData.getCharacter().getName().toCharArray();

                float i = (928.0F / (string.length + 252.0F));
                name.setBounds(new Rectangle((int) (screenSize.getWidth() / i), (int) (screenSize.getHeight() / 2.97777778), (int) (screenSize.getWidth() / 2.25242718), (int) (screenSize.getHeight() / 1.78666667)));
                name.setFont(Game.FONT.deriveFont(Font.PLAIN, (float) (screenSize.getWidth() / 28)));
            } catch (Exception ignored) {
            }

        }

        public void componentShown(ComponentEvent arg0) {

        }
    }


}
