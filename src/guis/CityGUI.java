package guis;

import enums.BuildingType;
import game.City;
import game.Game;
import models.IslandModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class CityGUI extends JFrame {
    private Game game;
    private JPanel panel;
    private ResourcePanel resourcePanel;
    private City city;
    private JComboBox comboBox;
    private JFrame openedFrame = null;
    private Timer timer;

    public CityGUI(Game game) throws HeadlessException, IOException {
        this.game = game;
        this.setTitle("Grepo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        timer = new Timer(1000,new TickListener());
        timer.setRepeats(true);
        timer.start();

        panel = new ImagePanel(ImageIO.read(new File("images/city.png")));
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickOnBuildingEvent());

        JPanel header = new ImagePanel(ImageIO.read(new File("images/header.png")));
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        header.setLayout(flowLayout);
        header.add(new JLabel());

        this.comboBox = new JComboBox(new IslandModel(game.getIslands(),game.getAuthenticatedPlayer()));
        this.city = (City) comboBox.getSelectedItem();
        comboBox.addItemListener(new ChangeCityListener());
        header.add(comboBox,BorderLayout.CENTER);
        resourcePanel = new ResourcePanel(city.getResources());
        header.add(resourcePanel,BorderLayout.EAST);

        this.add(panel,BorderLayout.CENTER);
        this.add(header,BorderLayout.NORTH);

    }

    private class TickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            game.tick();
            resourcePanel.tick();
        }
    }

    private class ChangeCityListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            city = (City) comboBox.getSelectedItem();
            if(openedFrame != null){
                openedFrame.dispose();
            }
        }
    }

    private class ClickOnBuildingEvent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getPoint());

            //templom 456, 303 < 30
            if((new Point(456, 303)).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceInfoFrame(city,city.getBuilding(BuildingType.TEMPLE));
                openedFrame.setVisible(true);
            }

            //ezüst 508, 256 < 30
            if((new Point(508, 256)).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceInfoFrame(city,city.getBuilding(BuildingType.SILVER_MINE));
                openedFrame.setVisible(true);
            }

            //favago 765, 480  < 30
            if((new Point(736,470 )).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceInfoFrame(city,city.getBuilding(BuildingType.TIMBER_CAMP));
                openedFrame.setVisible(true);
            }

            //kobanya 492, 385 < 40
            if((new Point(492, 385 )).distance(e.getPoint()) <= 40){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new ResourceInfoFrame(city,city.getBuilding(BuildingType.QUARRY));
                openedFrame.setVisible(true);
            }

            //szenatus 706, 342 < 40
            if((new Point(706, 342 )).distance(e.getPoint()) <= 40){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new SenateFrame(city);
                openedFrame.setVisible(true);
            }

            //kikoto 608, 458 < 45
            if((new Point(608, 458 )).distance(e.getPoint()) <= 45){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                System.out.println("Kikötő");
            }

            //kaszarnya 466, 340 < 30
            if((new Point(560,343)).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new BarracksFrame(city);
                openedFrame.setVisible(true);
            }


            //todo
            //templom 456, 303 < 30
            //ezüst 508, 256 < 30
            //kikoto 608, 458 < 45
            //favago 765, 480  < 30
            //kaszarnya 466, 340 < 30
            //szenatus 706, 342 < 40
            //kobanya 492, 385 < 40

        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }
}
