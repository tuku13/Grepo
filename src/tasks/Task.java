package tasks;

import game.City;

import java.io.Serializable;

/**
 * Egy absztrakt folyamatokat reprezentáló osztály
 */
public abstract class Task implements Serializable {
    protected long time;
    protected City city;
    protected boolean active;
    protected boolean executed;

    /**
     * Konstruktor
     * @param l folyamat ideje
     * @param city folyamathoz tartozó város
     */
    protected Task(Long l,City city){
        time = l;
        this.city = city;
        active = true;
        executed = false;
    }

    /**
     * Lejáró idő esetén lefutó függvény.
     * Minden leszármazottnak kötelező megvalósítania.
     */
    protected abstract void execute();

    /**
     * Időzítő hatására meghívódo függvény.
     * Csökkenti minden a folyamat idejét, ha eléri a 0 meghívja az execute függvényt, majd leállítja önmagát
     */
    public final void tick() {
        if(active && !executed){
            --time;
            if(time <= 0){
                execute();
                active = false;
                executed = true;
            }
        }
    }

    /**
     * Folyamat szöveges leírása.
     * Minden leszármazott kötelezően megvalósítja.
     * @return Folyamat szöveges leírása
     */
    public abstract String toString();

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setExecuted(boolean executed) { this.executed = executed; }

    public boolean isExecuted() {
        return executed;
    }

    public City getCity() {
        return city;
    }
}
