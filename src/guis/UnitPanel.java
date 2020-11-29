package guis;

import enums.GroundUnitType;
import enums.NavalUnitType;
import game.City;

import javax.swing.*;
import java.awt.*;

public class UnitPanel  extends JPanel {
    City city;

    public UnitPanel(City city, boolean isGround,boolean needSpinner){
        this.city = city;

        if(isGround){
            this.setLayout(new GridLayout(1, GroundUnitType.values().length));
            for(GroundUnitType groundUnitType : GroundUnitType.values()){
                JPanel groundUnitPanel = new JPanel();
                groundUnitPanel.setLayout(new GridLayout(3,1));

                JLabel nameLabel = new JLabel(groundUnitType.getName());
                nameLabel.setIcon(new ImageIcon(groundUnitType.getImageName()));
                nameLabel.setVerticalAlignment(JLabel.CENTER);
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
                nameLabel.setHorizontalTextPosition(JLabel.CENTER);
                groundUnitPanel.add(nameLabel);

                JPanel middlePanel = new JPanel();
                middlePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                middlePanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                JLabel maxCountLabel = new JLabel();
                int maxCount = city.getArmy().count(groundUnitType);
                maxCountLabel.setText(maxCount + "");
                middlePanel.add(maxCountLabel);
                groundUnitPanel.add(middlePanel);

                if(needSpinner){
                    JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,maxCount,1));
                    groundUnitPanel.add(spinner);
                }

                this.add(groundUnitPanel);
            }
        }
        else{
            this.setLayout(new GridLayout(1, NavalUnitType.values().length));
            for(NavalUnitType navalUnitType : NavalUnitType.values()){
                JPanel navalUnitPanel = new JPanel();
                navalUnitPanel.setLayout(new GridLayout(3,1));

                JLabel nameLabel = new JLabel(navalUnitType.getName());
                nameLabel.setIcon(new ImageIcon(navalUnitType.getImageName()));
                nameLabel.setVerticalAlignment(JLabel.CENTER);
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                nameLabel.setVerticalTextPosition(JLabel.BOTTOM);
                nameLabel.setHorizontalTextPosition(JLabel.CENTER);
                navalUnitPanel.add(nameLabel);

                JPanel middlePanel = new JPanel();
                middlePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                middlePanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                JLabel maxCountLabel = new JLabel();
                int maxCount = city.getArmy().count(navalUnitType);
                maxCountLabel.setText(maxCount + "");
                middlePanel.add(maxCountLabel);
                navalUnitPanel.add(middlePanel);

                if(needSpinner){
                    JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,maxCount,1));
                    navalUnitPanel.add(spinner);
                }

                this.add(navalUnitPanel);
            }
        }

    }
}
