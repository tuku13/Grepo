package tasks;

import game.City;
import units.Army;

public class ConquerTask extends WarTask {
    public ConquerTask(Long l, City city, Army army, City target) {
        super(l, city, army, target);
    }

    @Override
    protected void execute() {
        Army winner = army.battle(target.getArmy());

        if(winner == army){
            target.conquer(city.getPlayer());
            target.getArmy().add(army);
        }

    }
}
