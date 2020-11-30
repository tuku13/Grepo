package tasks;

import buildings.Building;
import enums.BuildingType;
import exceptions.BuildingReachedMaxLevel;
import game.City;

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
        try {
            building.upgrade();
        } catch (BuildingReachedMaxLevel buildingReachedMaxLevel) {
            buildingReachedMaxLevel.printStackTrace();
        }
    }
}
