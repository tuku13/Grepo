package tasks;

import game.City;
import units.Army;

import javax.swing.*;

/**
 * Egységek utazását reprezentáló folyamat
 */
public class TravellingTask extends Task{
    private Army army;

    /**
     * Konstruktor
     * @param l utazás ideje
     * @param city utazás célpontja
     * @param army utazó sereg
     */
    public TravellingTask(Long l, City city,Army army) {
        super(l, city);
        this.army = army;
    }

    /**
     * Lefutáskor a sereg hozzáadódik a város meglévő egységeihez
     */
    @Override
    protected void execute() {
        city.getArmy().add(army);
        JOptionPane.showMessageDialog(null,"A katonák megérkeztek " + city.getName() + " városba.","Információ",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * {@link Task#toString()}
     */
    @Override
    public String toString() {
        return "Hadsereg érkezik: " + city.getName() + " nevű városba";
    }

}
