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
        String str = (Math.round(time / 3600)) + ":" +  (time / 60) + ":" + (time % 60);
        return building.getBuildingType().getName() + " lvl " + (building.getLevel()+1) + "-re, hátralévő idő: " + str;
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
