package guis;

import components.ImagePanel;
import components.ResourcePanel;
import enums.BuildingType;
import game.City;
import game.Game;
import models.CityModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Fő ablak, melyen a város és a különböző menük láthetók
 */
public class CityFrame extends JFrame {
    private Game game;
    private JPanel panel;
    private ResourcePanel resourcePanel;
    private City city;
    private JComboBox comboBox;
    private JFrame openedFrame = null;
    private Timer timer;
    private CityModel cityModel;

    /**
     * Konstruktor
     * @param game játék
     */
    public CityFrame(Game game){
        this.game = game;
        this.setTitle("Grepo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setResizable(false);

        timer = new Timer(1000,new TickListener());
        timer.setRepeats(true);
        timer.start();

        try {
            panel = new ImagePanel(ImageIO.read(new File("images/city.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.setSize(1024,768);
        panel.addMouseListener(new ClickOnBuildingEvent());

        JPanel header;
        try {
            header = new ImagePanel(ImageIO.read(new File("images/header.png")));
        } catch (IOException e) {
            e.printStackTrace();
            header = new JPanel();
        }

        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        header.setLayout(flowLayout);
        JButton notificationButton = new JButton("Értesítések");
        notificationButton.addActionListener(new ClickNotificationButtonListener());
        header.add(notificationButton);

        JButton islandsButton = new JButton("Sziget nézet");
        islandsButton.addActionListener(new ClickIslandButtonListener());
        header.add(islandsButton);

        JButton armyButton = new JButton("Katonák");
        armyButton.addActionListener(new ViewArmyListener());
        header.add(armyButton);

        this.cityModel = new CityModel(game.getIslands(),game.getAuthenticatedPlayer());
        this.comboBox = new JComboBox(cityModel);
        this.city = (City) comboBox.getSelectedItem();
        comboBox.addItemListener(new ChangeCityListener());
        header.add(comboBox,BorderLayout.CENTER);
        resourcePanel = new ResourcePanel(city.getResources());
        header.add(resourcePanel,BorderLayout.EAST);

        Timer comboBoxTimer = new Timer(5000,new RefreshComboBox());
        comboBoxTimer.start();

        this.add(panel,BorderLayout.CENTER);
        this.add(header,BorderLayout.NORTH);
    }

    /**
     * Timer tick-elését kezeli és lépteti a játékot időben
     */
    private class TickListener implements ActionListener{

        /**
         * Lépteti a játékot időben
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            game.tick();
            resourcePanel.tick();
        }
    }

    /**
     * Megnyitja az értesítések ablakot
     */
    private class ClickNotificationButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(openedFrame != null){
                openedFrame.dispose();
            }
            openedFrame = new NotificationFrame(city);
            openedFrame.setVisible(true);
        }
    }

    /**
     * Megnyitja az egységeket megjelenítő tabot
     */
    private class ViewArmyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(openedFrame != null){
                openedFrame.dispose();
            }
            openedFrame = new ArmyFrame(city);
            openedFrame.setVisible(true);
        }
    }

    /**
     * Kezeli a város váltást JComboBox itemChange hatására
     */
    private class ChangeCityListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            city = (City) comboBox.getSelectedItem();
            resourcePanel.setResourceStack(city.getResources());
            if(openedFrame != null){
                openedFrame.dispose();
            }
        }
    }

    /**
     * Megjeleníti a sziget nézetet
     */
    private class ClickIslandButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(openedFrame != null){
                openedFrame.dispose();
            }
            try {
                openedFrame = new IslandFrame(game,city);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            openedFrame.setVisible(true);
        }
    }

    /**
     * Megnyitja a kiválaszottt épülethez tartozó ablakot
     */
    private class ClickOnBuildingEvent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

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
                openedFrame = new HarborFrame(city);
                openedFrame.setVisible(true);
            }

            //kaszarnya 560, 343 < 30
            if((new Point(560,343)).distance(e.getPoint()) <= 30){
                if(openedFrame != null){
                    openedFrame.dispose();
                }
                openedFrame = new BarracksFrame(city);
                openedFrame.setVisible(true);
            }

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

    /**
     * Hozzáadja az új elfoglalat várost a JComboBox-hoz
     */
    private class RefreshComboBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Collection<ItemListener> listeners = Arrays.asList(comboBox.getItemListeners());
            for ( ItemListener itemListener: listeners) {
                comboBox.removeItemListener(itemListener);
            }
            comboBox.setModel(new CityModel(game.getIslands(),game.getAuthenticatedPlayer()));
            comboBox.setSelectedItem(city);
            for ( ItemListener itemListener: listeners) {
                comboBox.addItemListener( itemListener);
            }

        }
    }
}
