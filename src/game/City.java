package game;

import buildings.Building;
import enums.BuildingType;
import tasks.Tickable;
import units.Army;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class City implements Tickable, Serializable {
    private Player player;
    private ResourceStack resources;
    private Island island;
    private List<Building> buildings;
    private Army army;
    private String name;
    long defeatTime;

    public City(Island island,String name){
        resources = new ResourceStack(100,100,100,0);
        this.island = island;
        this.name = name;
        //todo talán nincs értelme: this.name = (name == null) ? (player.name + " városa") : name;
        buildings = new ArrayList<>();
        buildings.add(new Building(BuildingType.SENATE,1,this));
        buildings.add(new Building(BuildingType.QUARRY,1,this));
        buildings.add(new Building(BuildingType.TIMBER_CAMP,1,this));
        buildings.add(new Building(BuildingType.SILVER_MINE,1,this));
        buildings.add(new Building(BuildingType.BARRACKS,1,this));
        buildings.add(new Building(BuildingType.HARBOR,0,this));
        buildings.add(new Building(BuildingType.TEMPLE,0,this));

    this.army = new Army();
        defeatTime = 0;
    }

    public Building getBuilding(BuildingType buildingType){
        for(Building b : buildings){
            if(b.getBuildingType() == buildingType){
                return b;
            }
        }
        return null;
    }

    public void conquer(Player p){
        //TODO talán kivétel, mert nem legyőzött várost nem lehet elfoglalni
        if(defeatTime > 0){
            return;
        }
        this.player = p;
    }

    @Override
    public void tick() {
        for(Building b : buildings){
            b.tick();
        }
        if(defeatTime > 0){
            --defeatTime;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public ResourceStack getResources() {
        return resources;
    }

    public Island getIsland() {
        return island;
    }

    public long getDefeatTime() {
        return defeatTime;
    }

    public Army getArmy() {
        return army;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    @Override
    public String toString() {
        return name;
    }

    //TODO nem kell csak tesztéshez
    public void setPlayer(Player player) {
        this.player = player;
    }
}
