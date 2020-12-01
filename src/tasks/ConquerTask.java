package tasks;

import game.City;
import units.Army;

import javax.swing.*;

public class ConquerTask extends WarTask {

    public ConquerTask(Long l, City city, Army army, City target) {
        super(l, city, army, target);
    }

    @Override
    protected void execute() {
        Army winner = army.battle(target.getArmy());

        if(winner == army){
            target.setPlayer(city.getPlayer());
            target.getArmy().add(army);
            JOptionPane.showMessageDialog(null,target.getName() + " nevű várost elfoglaltad!.","Információ",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null,target.getName() + " elfoglalása nem sikerült.","Információ",JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return "Város elfoglalási kísérlet: " + target;
    }
}
