package tasks;

import game.City;
import units.Army;

import javax.swing.*;

public class UnitTrainingTask extends Task{
    private Army army;

    public UnitTrainingTask(Long l, City city, Army army) {
        super(l,city);
        this.army = army;
    }

    @Override
    protected void execute() {
        city.getArmy().add(army);
        if(army.hasAliveGroundUnit()){
            JOptionPane.showMessageDialog(null,"Egy " + army.getGroundArmy().get(0).getType().getName() + " kipzése befejeződött.","Információ",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"Egy " + army.getNavalArmy().get(0).getType().getName() + " építése befejeződött.","Információ",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        if(army.hasAliveGroundUnit()){
            return "Egység kiképzés: " + army.getGroundArmy().get(0).getType().getName();
        }
        else{
            return "Hajó építés: " + army.getNavalArmy().get(0).getType().getName();
        }
    }
}
