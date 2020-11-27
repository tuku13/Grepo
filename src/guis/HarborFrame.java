package guis;

import enums.BuildingType;
import enums.NavalUnitType;
import game.City;

import javax.swing.*;
import java.awt.*;

public class HarborFrame extends JFrame {
    private City city;

    public HarborFrame(City city){
        this.city = city;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(BuildingType.HARBOR.getName() + " lvl " + city.getBuilding(BuildingType.HARBOR).getLevel());
        this.setResizable(false);
        this.setLayout(new GridLayout(1, NavalUnitType.values().length, 0,50));
    }
}
