package game;

import buildings.Building;
import enums.BuildingType;
import enums.GroundUnitType;
import enums.NavalUnitType;
import tasks.Tickable;
import units.Army;
import units.GroundUnit;
import units.NavalUnit;

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

    public City(Island island,String name){
        resources = new ResourceStack(1500,1500,1500,0);
        this.island = island;
        this.name = name;

        buildings = new ArrayList<>();
        initBuildings();

        this.army = new Army();
        initArmy();
    }

    private void initBuildings(){
        buildings.add(new Building(BuildingType.SENATE,1,this));
        buildings.add(new Building(BuildingType.QUARRY,1,this));
        buildings.add(new Building(BuildingType.TIMBER_CAMP,1,this));
        buildings.add(new Building(BuildingType.SILVER_MINE,1,this));
        buildings.add(new Building(BuildingType.BARRACKS,1,this));
        buildings.add(new Building(BuildingType.HARBOR,0,this));
        buildings.add(new Building(BuildingType.TEMPLE,0,this));
    }

    private void initArmy(){
        army.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        army.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        army.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        army.add(new GroundUnit(GroundUnitType.SLINGER));
        army.add(new GroundUnit(GroundUnitType.SLINGER));
        army.add(new GroundUnit(GroundUnitType.HOPLITE));

        army.add(new NavalUnit(NavalUnitType.TRANSPORT_BOAT));
        army.add(new NavalUnit(NavalUnitType.TRANSPORT_BOAT));
        army.add(new NavalUnit(NavalUnitType.COLONY_SHIP));
    }

    public Building getBuilding(BuildingType buildingType){
        for(Building b : buildings){
            if(b.getBuildingType() == buildingType){
                return b;
            }
        }
        return null;
    }

    @Override
    public void tick() {
        for(Building b : buildings){
            b.tick();
        }
    }

    public void setName(String name) {
        this.name = name;
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

    public Army getArmy() {
        return army;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
