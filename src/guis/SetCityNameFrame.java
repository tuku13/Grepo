package guis;

import game.City;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetCityNameFrame extends JFrame {
    private Game game;
    private City city;
    private JTextField cityNameTextField;

    public SetCityNameFrame(Game game, City city){
        this.game = game;
        this.city = city;

        init();
    }

    private void init() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,300));
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        JPanel panel = new JPanel();
        this.add(panel,gc);
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel("Új Város neve: "));
        this.cityNameTextField = new JTextField(15);
        panel.add(cityNameTextField);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.PAGE_END;
        JButton foundButton = new JButton("Alapít");
        foundButton.addActionListener(new FoundCityListener());
        this.add(foundButton,gc);
    }

    private class FoundCityListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(cityNameTextField.getText() == null || cityNameTextField.getText().length() <= 4){
                JOptionPane.showMessageDialog(null,"Hiányzó városnév vagy a választott városnév rövidebb, mint 4 karakter","Figyelmeztetés",JOptionPane.ERROR_MESSAGE);
            }
            else{
                city.setName(cityNameTextField.getText());
                city.setPlayer(game.getAuthenticatedPlayer());

                CityFrame frame = new CityFrame(game);
                dispose();
                frame.setVisible(true);
            }
        }
    }
}
