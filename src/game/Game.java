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

/**
 * Játék osztály, ez tartalmaz minden játékos és a pálya(sziget) adatot.
 * Össze fogja az adatokat és lehetővé teszi a fájlba mentést és betöltést
 */
public class Game implements Tickable, Serializable{
    private List<Player> players;
    private List<Island> islands;
    private transient Player authenticatedPlayer;

    /**
     * Konstruktor
     * Inicializálja a változókat
     */
    public Game(){
        players = new ArrayList<>();
        islands = new ArrayList<>();

        if(Main.DEBUG){
            tesztAdatokFeltöltése();
        }

        save();
    }

    /**
     * Vissz adja a játékoshoz tartozó összes várost
     * @param player játékos
     * @return játékoshoz tartozó városok listája
     */
    public List<City> getPlayerCities(Player player){
        List<City> cities = new ArrayList<>();

        for(Island island: islands){
            for(City city : island.getCities().values()){
                if(city.getPlayer() == player){
                    cities.add(city);
                }
            }
        }

        return cities;
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

    /**
     * Időzítő hatására meghívódó függvény.
     * Végig megy Minden szigeten, majd a TaskManager-t is lépteti majd elmenti a változásokat.
     * A játék fő irányító függvénye.
     */
    @Override
    public void tick() {
        for (Island i : islands){
            i.tick();
        }
        TaskManager.getInstance().tick();
        save();
    }

    /**
     * Fájlba elmenti önmagát, hogy kásőbb újra tölthető legyen
     */
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

    /**
     * Megkeresi, hogy tartozik e játékos a megadott névhez.
     * @param name játákos név
     * @return névhez tartozó játékos, egyébként null
     */
    public Player getPlayer(String name){
        for(Player p : players){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Létrehozza a játékost megadott névvel és jelszóval
     * @param name név
     * @param pw jelszó
     */
    public void registerPlayer(String name,String pw){
        players.add(new Player(name,pw));
        save();
    }

    /**
     * Beállítja a játékost, így elindulhat a játék, megfelelő jelszó esetén.
     * @param pl játékos
     * @param pw jelszó
     * @return igaz ha sikerült a belépés
     */
    public boolean authenticate(Player pl,String pw){
        if(pl != null && pl.password.equals(pw)){
            authenticatedPlayer = pl;
            return true;
        }
        return false;
    }

    public void setAuthenticatedPlayer(Player authenticatedPlayer) {
        this.authenticatedPlayer = authenticatedPlayer;
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
