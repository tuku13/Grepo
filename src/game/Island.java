package game;

import tasks.Tickable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Szigetet reprezentáló osztály.
 * Összegyűjti koordináták szerint a városokat
 */
public class Island implements Tickable, Serializable {
    private String name;
    private double stoneMultiplier,silverMultiplier,woodMultiplier;
    private HashMap<Location,City> cities;

    /**
     * Konstruktor
     * @param name sziget neve
     * @param stoneMultiplier kő termelési szorzó, általában 0.9, 1.0, vagy 1.1
     * @param silverMultiplier ezüst termelési szorzó, általában 0.9, 1.0, vagy 1.1
     * @param woodMultiplier fa termelési szorzó, általában 0.9, 1.0, vagy 1.1
     * @param cities lokáció - város értékeket tartalmazó HashMap
     */
    public Island(String name, double stoneMultiplier, double silverMultiplier, double woodMultiplier, HashMap<Location, City> cities) {
        this.name = name;
        this.stoneMultiplier = stoneMultiplier;
        this.silverMultiplier = silverMultiplier;
        this.woodMultiplier = woodMultiplier;
        this.cities = cities;
    }

    /**
     * Végig meg minden szigeten, hogy elvégezzék a feladatokat
     */
    @Override
    public void tick() {
        for(City c : cities.values()){
            if(c == null) continue;
            c.tick();
        }
    }

    /**
     * Visszaadja a városhoz tartozó helyszín adatokat
     * @param city város
     * @return városhoz tartozó Location koordináta
     */
    public Location getLocation(City city){
        for(Map.Entry<Location,City> c : cities.entrySet()){
            if(c.getValue() == city){
                return c.getKey();
            }
        }
        return new Location(0,0);
    }

    public double getStoneMultiplier() {
        return stoneMultiplier;
    }

    public double getSilverMultiplier() {
        return silverMultiplier;
    }

    public double getWoodMultiplier() {
        return woodMultiplier;
    }

    public String getName() {
        return name;
    }

    public HashMap<Location, City> getCities() {
        return cities;
    }
}
