package buildings;

import com.sun.istack.internal.NotNull;
import enums.BuildingType;
import exceptions.BuildingReachedMaxLevel;
import game.City;
import game.Tickable;

import java.io.Serializable;

public class Building implements Serializable, Tickable {
    protected final City city;
    protected final BuildingType buildingType;
    protected int level;

    public Building(BuildingType buildingType,int level,@NotNull City city){
        this.buildingType = buildingType;
        this.level = level;
        this.city = city;
    }

    public final void upgrade() throws BuildingReachedMaxLevel{
        if(level >= buildingType.getMaxLevel()){
            throw new BuildingReachedMaxLevel();
        }
        else{
            ++level;
        }
    }

    @Override
    public void tick(){
        switch (buildingType){
            case QUARRY:
                city.getResources().add(0,(level * 20.0 * city.getIsland().getStoneMultiplier()) / 1800,0);
                break;
            case SILVER_MINE:
                city.getResources().add(0,0,(level * 20.0 * city.getIsland().getSilverMultiplier()) / 1800);
                break;
            case TIMBER_CAMP:
                city.getResources().add((level * 20.0 * city.getIsland().getWoodMultiplier()) / 1800,0,0);
                break;
            case TEMPLE:
                city.getResources().add(0,0,0,(level * 25.0) / 1800);
                break;
        }
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
