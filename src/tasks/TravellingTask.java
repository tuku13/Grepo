package tasks;

import game.City;
import units.Army;

import javax.swing.*;

public class TravellingTask extends Task{
    private Army army;

    public TravellingTask(Long l, City city,Army army) {
        super(l, city);
        this.army = army;
    }

    @Override
    protected void execute() {
        city.getArmy().add(army);
        JOptionPane.showMessageDialog(null,"A katonák megérkeztek " + city.getName() + " városba.","Információ",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String toString() {
        return "Hadsereg érkezik: " + city.getName() + " nevű városba";
    }

}
