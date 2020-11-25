package game;

import java.io.Serializable;

public class Location implements Serializable {
    double x,y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

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
