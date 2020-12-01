package buildings;

import com.sun.istack.internal.NotNull;
import enums.BuildingType;
import game.City;
import tasks.Task;
import tasks.Tickable;

import java.io.Serializable;

public class Building implements Serializable, Tickable {
    protected final City city;
    protected final BuildingType buildingType;
    protected int level;
    protected Task task;

    public Building(BuildingType buildingType,int level,@NotNull City city){
        this.buildingType = buildingType;
        this.level = level;
        this.city = city;
    }

    public final void upgrade(){
        if(level < buildingType.getMaxLevel()){
            ++level;
        }
    }

    @Override
    public void tick(){
        if(task != null && task.isExecuted()){
            task = null;
        }
        switch (buildingType){
            case QUARRY:
                city.getResources().add(0,(level * 20.0 * city.getIsland().getStoneMultiplier()) / 180,0);
                break;
            case SILVER_MINE:
                city.getResources().add(0,0,(level * 20.0 * city.getIsland().getSilverMultiplier()) / 100);
                break;
            case TIMBER_CAMP:
                city.getResources().add((level * 20.0 * city.getIsland().getWoodMultiplier()) / 100,0,0);
                break;
            case TEMPLE:
                city.getResources().add(0,0,0,(level * 25.0) / 180);
                break;
        }
    }

    public boolean hasTask(){
        return (task != null);
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public City getCity() {
        return city;
    }


}
