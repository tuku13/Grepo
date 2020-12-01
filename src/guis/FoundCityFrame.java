package guis;

import game.City;
import game.Game;
import game.Island;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoundCityFrame extends JFrame {
    private Game game;
    private Island island;
    private JFrame chooseIslandFrame;

    public FoundCityFrame(Game game, Island island, JFrame frame){
        this.game = game;
        this.island = island;
        this.chooseIslandFrame = frame;

        this.setMinimumSize(new Dimension(400,island.getCities().size() * 100));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Grepo - " + island.getName());
        this.setResizable(false);
        this.setLayout(new GridLayout(island.getCities().size(), 1));

        for(City c : island.getCities().values()){
            JPanel panel = new JPanel();
            panel.setBackground(new Color(254,225,157));
            panel.setLayout(new FlowLayout());

            panel.add(new JLabel(c.getName()));

            JButton button = new JButton("Foglalt");
            button.setEnabled(false);
            if(c.getPlayer() == null){
                button.setText("Alapít");
                button.addActionListener(new FoundCityListener(c));
                button.setEnabled(true);
            }
            panel.add(button);

            this.add(panel);
        }

    }
    private class FoundCityListener implements ActionListener{
        private City chosenCity;

        public FoundCityListener(City chosenCity) {
            this.chosenCity = chosenCity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.getPlayerCities(game.getAuthenticatedPlayer()).size() != 0){
                dispose();
                JOptionPane.showMessageDialog(null,"Van már városod, indítsd újra a játékot, hogy megjelenjen.","Hiba",JOptionPane.ERROR_MESSAGE);
            }
            else{
                chooseIslandFrame.dispose();
                dispose();
                SetCityNameFrame frame = new SetCityNameFrame(game,chosenCity);
                frame.setVisible(true);
            }

        }
    }
}
