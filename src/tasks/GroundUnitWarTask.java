package tasks;

import game.City;
import units.Army;

public class GroundUnitWarTask extends Task{
    private City target;
    private Army army;

    public GroundUnitWarTask(Long l, City city, Army army, City target) {
        super(l, city);
        this.target = target;
        this.army = army;
    }

    @Override
    protected void execute() {
        army.battle(target.getArmy());
        double distance = city.getIsland().getLocation(city).distance(target.getIsland().getLocation(target));
        long time = 0;
        TaskManager.getInstance().add(new TravellingTask(time,city,army));
    }
}
