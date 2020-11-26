package game;

import tasks.Tickable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Island implements Tickable, Serializable {
    private String name;
    private double stoneMultiplier,silverMultiplier,woodMultiplier;
    private HashMap<Location,City> cities;

    public Island(String name, double stoneMultiplier, double silverMultiplier, double woodMultiplier, HashMap<Location, City> cities) {
        this.name = name;
        this.stoneMultiplier = stoneMultiplier;
        this.silverMultiplier = silverMultiplier;
        this.woodMultiplier = woodMultiplier;
        this.cities = cities;
    }

    @Override
    public void tick() {
        for(City c : cities.values()){
            if(c == null) continue;
            c.tick();
        }
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

    public Location getLocation(City city){
        for(Map.Entry<Location,City> c : cities.entrySet()){
            if(c.getValue() == city){
                return c.getKey();
            }
        }
        return new Location(0,0);
    }

    public HashMap<Location, City> getCities() {
        return cities;
    }
}
