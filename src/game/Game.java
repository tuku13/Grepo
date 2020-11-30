package game;

import com.sun.istack.internal.NotNull;
import enums.GroundUnitType;
import enums.NavalUnitType;
import tasks.TaskManager;
import tasks.Tickable;
import units.Army;
import units.GroundUnit;
import units.NavalUnit;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game implements Tickable, Serializable{
    private static final long serialVersionUID = 42357842569827483L;
    private List<Player> players;
    private List<Island> islands;
    private transient Player authenticatedPlayer;
    //public transient TaskManager taskManager;

    public Game(){
        players = new ArrayList<>();
        islands = new ArrayList<>();
        //taskManager = TaskManager.getInstance();

        if(Main.DEBUG){
            tesztAdatokFeltöltése();//todo törlése ha nem kell
        }

        save();
    }

    private void tesztAdatokFeltöltése(){
        Player p = new Player("admin","admin");
        players.add(p);
        HashMap<Location,City> woodCities = new HashMap<>();
        Island woodIsland = new Island("Wood Island",1.0,0.9,1.1,woodCities);
        City c = new City(woodIsland,"Admin városa");
        Army a = new Army();
        a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        a.add(new GroundUnit(GroundUnitType.ARCHER));
        a.add(new GroundUnit(GroundUnitType.HOPLITE));
        a.add(new NavalUnit(NavalUnitType.TRANSPORT_BOAT));
        a.add(new NavalUnit(NavalUnitType.COLONY_SHIP));
        c.getArmy().add(a);
        c.setPlayer(p);
        City c2 = new City(woodIsland, "Második város");
        woodCities.put(new Location(-180,140),c);
        c2.setPlayer(p);
        woodCities.put(new Location(-160,75),c2);
        woodCities.put(new Location(20,50),new City(woodIsland, "wood3"));
        woodCities.put(new Location(100,50),new City(woodIsland, "wood4"));
        woodCities.put(new Location(-10,240),new City(woodIsland, "wood5"));
        islands.add(woodIsland);

        HashMap<Location,City> stoneCities = new HashMap<>();
        Island stoneIsland = new Island("Stone Island",1.1,1.0,0.9,stoneCities);
        stoneCities.put(new Location(-90,-60),new City(stoneIsland, "stone1"));
        stoneCities.put(new Location(-230,-10),new City(stoneIsland, "stone2"));
        stoneCities.put(new Location(-400,-65),new City(stoneIsland, "stone3"));
        stoneCities.put(new Location(-370,-260),new City(stoneIsland, "stone4"));
        stoneCities.put(new Location(-190,-120),new City(stoneIsland, "stone5"));
        islands.add(stoneIsland);

        HashMap<Location,City> silverCities = new HashMap<>();
        Island silverIsland = new Island("Silver Island",0.9,1.1,1.0,silverCities);
        silverCities.put(new Location(210,-40),new City(silverIsland, "silver1"));
        silverCities.put(new Location(230,-10),new City(silverIsland, "silver2"));
        silverCities.put(new Location(400,0),new City(silverIsland, "silver3"));
        silverCities.put(new Location(320,-180),new City(silverIsland, "silver4"));
        silverCities.put(new Location(240,-140),new City(silverIsland, "silver5"));
        islands.add(silverIsland);
    }

    @Override
    public void tick() {
        for (Island i : islands){
            i.tick();
        }
        TaskManager.getInstance().tick();
        save();
    }

    //régi serializálás
    /*public void load(){
        try{
            FileInputStream f = new FileInputStream("data" + File.separator + "players");
            ObjectInputStream in = new ObjectInputStream(f);
            players = (List<Player>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }

        try{
            FileInputStream f = new FileInputStream("data" + File.separator +"islands");
            ObjectInputStream in = new ObjectInputStream(f);
            islands = (List<Island>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }*/

    public void save(){
        try{
            FileOutputStream f = new FileOutputStream("data" + File.separator +"game.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            out.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public void setAuthenticatedPlayer(Player authenticatedPlayer) {
        this.authenticatedPlayer = authenticatedPlayer;
    }

    public Player getPlayer(String name){
        for(Player p : players){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void registerPlayer(@NotNull String name, @NotNull String pw){
        players.add(new Player(name,pw));
        save();
    }

    public boolean authenticate(Player pl,String pw){
        if(pl != null && pl.password.equals(pw)){
            authenticatedPlayer = pl;
            return true;
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public Player getAuthenticatedPlayer() {
        return authenticatedPlayer;
    }

}
