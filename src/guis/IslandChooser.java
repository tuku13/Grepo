package guis;

import game.Game;
import models.IslandModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class IslandChooser extends JFrame {
    private Game game;
    private JPanel panel;

    public IslandChooser(Game game) throws HeadlessException, IOException {
        this.game = game;
        this.setTitle("Grepo - Válasz szigetet!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        panel = new ImagePanel(ImageIO.read(new File("images/islands.png")));
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickIslandEvent());

        JPanel header = new ImagePanel(ImageIO.read(new File("images/header.png")));
        //JLabel headerLabel = new JLabel("Sziget nézet");
        //headerLabel.setForeground(new Color(255,255,255));
        //headerLabel.setFont(new Font("Dialog",Font.BOLD,16));
        //header.add(headerLabel);

        JComboBox comboBox = new JComboBox(new IslandModel(game.getIslands(),game.getAuthenticatedPlayer()));
        header.add(comboBox);

        this.add(panel,BorderLayout.CENTER);
        this.add(header,BorderLayout.NORTH);
        //this.pack();

    }

    private class ImagePanel extends JPanel{
        private Image img;

        public ImagePanel(Image img){
            this.img = img;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
        }

    }

    private class ClickIslandEvent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println(e.getPoint());
            if((new Point(190,566)).distance(e.getPoint()) <= 250){
                System.out.println("bal oldali");
                panel.setVisible(false);
            }

            if((new Point(470,140)).distance(e.getPoint()) <= 200){
                System.out.println("középső");
            }

            if((new Point(911,454)).distance(e.getPoint()) <= 300){
                System.out.println("jobb oldali");
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
