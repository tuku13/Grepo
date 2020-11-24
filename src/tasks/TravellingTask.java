package tasks;

import game.City;
import units.Army;

public class TravellingTask extends Task{
    private Army army;

    public TravellingTask(Long l, City city,Army army) {
        super(l, city);
        this.army = army;
    }

    @Override
    protected void execute() {
        city.getArmy().add(army);
    }

}
