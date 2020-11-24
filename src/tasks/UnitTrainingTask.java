package tasks;

import game.City;
import units.Army;

public class UnitTrainingTask extends Task{
    Army army;

    public UnitTrainingTask(Long l, City city, Army army) {
        super(l,city);
        this.army = army;
    }

    @Override
    protected void execute() {
        city.getArmy().add(army);
    }
}
