package tasks;

import buildings.Building;
import game.City;

import javax.swing.*;

public class BuildingTask extends Task {

    private Building building;

    public BuildingTask(Long l,City city, Building building) {
        super(l,city);
        this.building = building;
    }

    @Override
    public String toString() {
        return "Épület fejlesztése: " + building.getBuildingType().getName() + " lvl " + (building.getLevel()+1);
    }

    @Override
    public void execute() {
        building.upgrade();
        JOptionPane.showMessageDialog(null,building.getBuildingType().getName() + " fejlesztése befejeződött.","Információ",JOptionPane.INFORMATION_MESSAGE);
    }
}
