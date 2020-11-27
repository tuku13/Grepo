package tasks;

import game.City;
import units.Army;

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
            double distance = city.getIsland().getLocation(city).distance(target.getIsland().getLocation(target));
            long time = 0; //TODO utazási idő kiszámítása
            TaskManager.getInstance().add(new TravellingTask(time,city,army));
        }
    }
}
