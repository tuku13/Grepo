package game;

import tasks.Tickable;

import java.io.*;
import java.util.ArrayList;

import java.util.List;

/**
 * Játék osztály, ez tartalmaz minden játékos és a pálya(sziget) adatot.
 * Össze fogja az adatokat és lehetővé teszi a fájlba mentést és betöltést
 */
public class Game implements Tickable, Serializable{
    private final List<Player> players;
    private final List<Island> islands;
    private transient Player authenticatedPlayer;

    /**
     * Konstruktor
     * Inicializálja a változókat
     */
    public Game(){
        players = new ArrayList<>();
        islands = new ArrayList<>();

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
