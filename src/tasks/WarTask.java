package tasks;

import game.City;
import units.Army;

import javax.swing.*;

public class WarTask extends Task{
    protected City target;
    protected Army army;

    public WarTask(Long l, City city, Army army, City target) {
        super(l, city);
        this.target = target;
        this.army = army;
    }

    @Override
    protected void execute() {
        Army winner = army.battle(target.getArmy());
        if(winner == army){
            double speed = army.averageSpeed();
            double distance = city.getIsland().getLocation(city).distance(target.getIsland().getLocation(target));
            long time = Math.round(distance / speed);
            JOptionPane.showMessageDialog(null,"Legyőted " + city.getName() + "nevű várost.","Információ",JOptionPane.INFORMATION_MESSAGE);

            TaskManager.getInstance().addFutureTask(new TravellingTask(time,city,army));
            return;
        }
        JOptionPane.showMessageDialog(null,city.getName() + " elleni támadás nem sikerült.","Információ",JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return "Hadjárat: " + city.getName() + "-ból " + target.getName() + "-ba. ";
    }
}
