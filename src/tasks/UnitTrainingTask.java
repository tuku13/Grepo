package tasks;

import game.City;
import units.Army;
import units.GroundUnit;

public class UnitTrainingTask extends Task{
    private Army army;

    public UnitTrainingTask(Long l, City city, Army army) {
        super(l,city);
        this.army = army;
    }

    @Override
    protected void execute() {
        city.getArmy().add(army);
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
