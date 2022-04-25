package game;

import buildings.Building;
import enums.BuildingType;
import enums.GroundUnitType;
import enums.NavalUnitType;
import tasks.Task;
import tasks.Tickable;
import units.Army;
import units.GroundUnit;
import units.NavalUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Várost reprezentáló osztály
 */
public final class City implements Tickable, Serializable {
    private Player player;
    private final ResourceStack resources;
    private final Island island;
    private final List<Building> buildings;
    private final Army army;
    private String name;
    private final HashSet<Task> tasks;
    private transient HashSet<Task> futureTasks;

    /**
     * Konstruktor
     * @param island sziget, melyen található a város
     * @param name város neve
     */
    public City(Island island,String name){
        resources = new ResourceStack(1500,1500,1500,0);
        this.island = island;
        this.name = name;

        this.tasks = new HashSet<>();
        this.futureTasks = new HashSet<>();

        buildings = new ArrayList<>();
        initBuildings();

        this.army = new Army();
        initArmy();
    }

    /**
     * Létrehozza az épületeket a megfelelő értékekkel
     */
    private void initBuildings(){
        buildings.add(new Building(BuildingType.SENATE,1,this));
        buildings.add(new Building(BuildingType.QUARRY,1,this));
        buildings.add(new Building(BuildingType.TIMBER_CAMP,1,this));
        buildings.add(new Building(BuildingType.SILVER_MINE,1,this));
        buildings.add(new Building(BuildingType.BARRACKS,1,this));
        buildings.add(new Building(BuildingType.HARBOR,0,this));
        buildings.add(new Building(BuildingType.TEMPLE,0,this));
    }

    /**
     * Létrehozza egy város alap hadseregét
     */
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

    /**
     * Megkeresi a típushoz tartozó épületet.
     * Mindig lesz találat, mert minden éplületből pontosan 1 db van minden városban
     * @param buildingType éplület típusa
     * @return típushoz tartozó épület
     */
    public Building getBuilding(BuildingType buildingType){
        for(Building b : buildings){
            if(b.getBuildingType() == buildingType){
                return b;
            }
        }
        return null;
    }

    private void removeExecutedTasks(){
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()){
            if(it.next().isExecuted()){
                it.remove();
            }
        }
    }

    /**
     * Időzítő hívására végig megy minden épületen melyek elvégzik a feladatokat
     */
    @Override
    public void tick() {
        if(futureTasks == null){
            futureTasks = new HashSet<>();
        }

        tasks.addAll(futureTasks);
        futureTasks.clear();
        for(Task t : tasks){
            try{
                t.tick();
            }
            catch (Exception exc){
                t.setExecuted(true);
            }
        }
        removeExecutedTasks();
        for(Building b : buildings){
            b.tick();
        }
    }

    public void addTask(Task t){
        futureTasks.add(t);
    }

    /**
     * {@link City#getPlayer()}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return játékos neve
     */
    public Player getPlayer() {
        return player;
    }

    public HashSet<Task> getTasks() {
        return tasks;
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
