package guis;

import enums.BuildingType;
import game.City;
import game.Game;
import game.Island;
import game.Location;
import models.IslandModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class IslandFrame extends JFrame {
    private Game game;
    private JPanel panel;
    private City city;
    private JFrame openedFrame;

    public IslandFrame(Game game,City city) throws HeadlessException, IOException {
        this.game = game;
        this.city = city;
        this.setTitle("Grepo - Sziget nézet");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        panel = new ImagePanel(ImageIO.read(new File("images/islands.png")));
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickIslandEvent());

        //JLabel headerLabel = new JLabel("Sziget nézet");
        //headerLabel.setForeground(new Color(255,255,255));
        //headerLabel.setFont(new Font("Dialog",Font.BOLD,16));
        //header.add(headerLabel);

        this.add(panel,BorderLayout.CENTER);

    }

    private class ClickIslandEvent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getPoint());

            if((new Point(190,566)).distance(e.getPoint()) <= 250){
                System.out.println("bal oldali");
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new IslandListFrame(game,game.getIslands().get(0),city);
                openedFrame.setVisible(true);
            }

            if((new Point(470,140)).distance(e.getPoint()) <= 200){
                System.out.println("középső");
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new IslandListFrame(game,game.getIslands().get(1),city);
                openedFrame.setVisible(true);
            }

            if((new Point(911,454)).distance(e.getPoint()) <= 300){
                System.out.println("jobb oldali");
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new IslandListFrame(game,game.getIslands().get(2),city);
                openedFrame.setVisible(true);
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
