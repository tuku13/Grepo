package guis;

import components.UnitPanel;
import game.City;

import javax.swing.*;
import java.awt.*;

public class ArmyFrame extends JFrame {
    private City city;

    public ArmyFrame(City city) {
        this.city = city;
        init();
    }

    private void init(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setTitle("Grepo - Hadsereg");
        this.setBackground(new Color(254,225,157));
        this.setSize(800,500);
        this.setResizable(false);
        GridBagConstraints gc = new GridBagConstraints();

        JPanel groundPanel = new UnitPanel(city,true);
        groundPanel.setBackground(new Color(254,225,157));
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(groundPanel,gc);

        JPanel navalPanel = new UnitPanel(city,false);
        navalPanel.setBackground(new Color(254,225,157));
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(navalPanel,gc);
    }
}
