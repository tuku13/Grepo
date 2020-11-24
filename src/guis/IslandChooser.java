package guis;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IslandChooser extends JFrame {
    private Game game;
    private JButton clickableImage;

    public IslandChooser(Game game) throws HeadlessException {
        this.game = game;
        this.setTitle("Grepo - Válasz szigetet!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(true);

        clickableImage = new JButton(new ImageIcon("images/sziget.png"));
        clickableImage.setSize(255,255);
        clickableImage.setOpaque(false);
        clickableImage.setContentAreaFilled(false);
        clickableImage.setBorderPainted(false);

        JPanel panel = new JPanel();
        panel.add(clickableImage);
        this.add(panel,BorderLayout.CENTER);
        this.setVisible(true);

    }

}
