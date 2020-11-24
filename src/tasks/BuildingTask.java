package tasks;

import enums.BuildingType;
import game.City;

public class BuildingTask extends Task {

    private BuildingType buildingType;

    public BuildingTask(Long l,City city,BuildingType buildingType) {
        super(l,city);
        this.buildingType = buildingType;
    }

    @Override
    public void execute() {
        city.upgrade(buildingType);
    }
}
