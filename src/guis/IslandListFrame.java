package guis;

import enums.BuildingType;
import game.City;
import game.Game;
import game.Island;
import tasks.StartWarFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IslandListFrame extends JFrame {
    private Game game;
    private Island island;
    private City city;

    public IslandListFrame(Game game, Island island, City city){
        this.game = game;
        this.island = island;
        this.city = city;

        this.setMinimumSize(new Dimension(400,island.getCities().size() * 100));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Grepo - " + island.getName());
        this.setResizable(false);
        this.setLayout(new GridLayout(island.getCities().size(), 1));

        for(City c : island.getCities().values()){
            JPanel panel = new JPanel();
            panel.setBackground(new Color(254,225,157));
            panel.setLayout(new FlowLayout(/*FlowLayout.CENTER,100,0*/));

            panel.add(new JLabel(c.getName()));

            JButton button = new JButton("Saját város");
            button.setEnabled(false);
            if(c.getPlayer() != game.getAuthenticatedPlayer()){
                button.setText("Támadás");
                button.addActionListener(new AttackCityListener(city,c));
                button.setEnabled(true);
            }
            panel.add(button);

            this.add(panel);
        }

    }
    private class AttackCityListener implements ActionListener{

        City from,to;

        public AttackCityListener(City from, City to){
            this.from = from;
            this.to = to;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            StartWarFrame frame = new StartWarFrame(from,to);
            frame.setVisible(true);
        }
    }
}
