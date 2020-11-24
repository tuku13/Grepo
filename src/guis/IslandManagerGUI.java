package guis;

import game.City;
import game.Game;
import game.Island;
import game.Location;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class IslandManagerGUI extends JFrame {
    private Game game;
    private HashMap<Location,City> cities;

    public IslandManagerGUI(Game game, HashMap<Location,City> cities) throws HeadlessException {
        this.game = game;
        this.cities = cities;

        this.setLayout(new GridLayout(cities.keySet().size(),2));
        for(Map.Entry<Location,City> c : cities.entrySet()){
            JPanel panel = new JPanel();
            panel.setLayout(null);
            this.add(new JLabel(c.getKey().getX() + " " + c.getKey().getY()));
            JButton button = new JButton("Elfoglal");
            button.setSize(130,40);
            panel.add(button);
            this.add(panel);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(240,320);
        this.setResizable(false);

        this.setVisible(true);
    }
}
