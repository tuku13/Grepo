package guis;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IslandChooser extends JFrame {
    private Game game;
    private JLabel clickableImage;

    public IslandChooser(Game game) throws HeadlessException {
        this.game = game;
        this.setTitle("Grepo - Válasz szigetet!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        clickableImage = new JLabel("");
        clickableImage.setIcon(new ImageIcon("images/sziget.png"));
        clickableImage.addMouseListener(new click());

        this.add(clickableImage,BorderLayout.CENTER);
        this.setVisible(true);
    }

    private class click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("kattintáke");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("lenyomvake");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("elengedva");
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
