package components;

import enums.GroundUnitType;
import enums.NavalUnitType;
import game.City;

import javax.swing.*;
import java.awt.*;

/**
 * Egységeket megjelenítő panel
 */
public class UnitPanel  extends JPanel {
    private City city;
    private boolean isGround;

    /**
     * Konstruktor
     * @param city mely város egységeit kell megjeleníteni
     * @param isGround szárazföldiek kellenek
     */
    public UnitPanel(City city, boolean isGround){
        this.city = city;
        this.isGround = isGround;

        init();
    }

    /**
     * Felépíti a panelt a megfelelő egységekkel
     */
    private void init(){
        this.setBackground(new Color(254,225,157));

        if(isGround){
            this.setLayout(new GridLayout(1, GroundUnitType.values().length));
            for(GroundUnitType groundUnitType : GroundUnitType.values()){
                JPanel groundUnitPanel = new JPanel();
                groundUnitPanel.setBackground(new Color(254,225,157));
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

                this.add(groundUnitPanel);
            }
        }
        else{
            this.setLayout(new GridLayout(1, NavalUnitType.values().length));
            for(NavalUnitType navalUnitType : NavalUnitType.values()){
                JPanel navalUnitPanel = new JPanel();
                navalUnitPanel.setBackground(new Color(254,225,157));
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

                this.add(navalUnitPanel);
            }
        }
    }
}
