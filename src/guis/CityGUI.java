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

public class CityGUI extends JFrame {
    private Game game;
    private JPanel panel;
    private City city;
    private JFrame openedFrame = null;

    public CityGUI(Game game) throws HeadlessException, IOException {
        this.game = game;
        this.setTitle("Grepo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        panel = new ImagePanel(ImageIO.read(new File("images/city.png")));
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickOnBuildingEvent());

        JPanel header = new ImagePanel(ImageIO.read(new File("images/header.png")));

        JComboBox comboBox = new JComboBox(new IslandModel(game.getIslands(),game.getAuthenticatedPlayer()));
        header.add(comboBox);

        this.city = (City) comboBox.getSelectedItem();

        this.add(panel,BorderLayout.CENTER);
        this.add(header,BorderLayout.NORTH);

    }

    private class ClickOnBuildingEvent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println(e.getPoint());

            //templom 456, 303 < 30
            if((new Point(456, 303)).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceProducerGUI(city,city.getBuilding(BuildingType.TEMPLE));
                openedFrame.setVisible(true);
            }

            //ezüst 508, 256 < 30
            if((new Point(508, 256)).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceProducerGUI(city,city.getBuilding(BuildingType.SILVER_MINE));
                openedFrame.setVisible(true);
            }

            //favágó de rossz koordináta
            if((new Point(774, 471 )).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceProducerGUI(city,city.getBuilding(BuildingType.TIMBER_CAMP));
                openedFrame.setVisible(true);
            }

            //todo
            //templom 456, 303 < 30
            //ezüst 508, 256 < 30
            //kikoto 608, 458 < 45
            //favago 774, 471 < 30 rossz
            //kaszarnya 466, 340 < 30
            //szenatus 706, 342 < 40
            //kobanya 492, 385 < 40

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
