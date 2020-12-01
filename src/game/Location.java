package game;

import java.io.Serializable;

/**
 * 2D koordinátákat reprezentáló osztály
 */
public class Location implements Serializable {
    private double x,y;

    /**
     * Konstruktor
     * @param x valós érték x tengely mentén
     * @param y valós érték y tengely mentén
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Kiszámítja önmaga és a paraméterben megadott pont távolságát
     * @param l másik koordináta
     * @return 2 koordináta távolsága
     */
    public double distance(Location l){
        return Math.sqrt( Math.pow(l.getX() - this.getX(),2) + Math.pow(l.getY() - this.getY(),2) );
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
