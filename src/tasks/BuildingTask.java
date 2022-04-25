package tasks;

import buildings.Building;
import game.City;

import javax.swing.*;

/**
 * Építési folyamatot reprezentáló osztály
 */
public class BuildingTask extends Task {
    private final Building building;

    /**
     * Konstruktor
     * @param l folyamat ideje
     * @param city folyamathoz tartozó város
     * @param building az építéshez tartozó építési folyamat
     */
    public BuildingTask(Long l,City city, Building building) {
        super(l,city);
        this.building = building;
    }

    /**
     * Lefutáskor növeli az épület szintjét 1 szinttel
     */
    @Override
    public void execute() {
        building.upgrade();
        JOptionPane.showMessageDialog(null,building.getBuildingType().getName() + " fejlesztése befejeződött.","Információ",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * {@link Task#toString()}
     */
    @Override
    public String toString() {
        return "Épület fejlesztése: " + building.getBuildingType().getName() + " lvl " + (building.getLevel()+1);
    }

}
