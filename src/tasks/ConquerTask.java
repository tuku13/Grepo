package tasks;

import game.City;
import units.Army;

import javax.swing.*;

/**
 * Foglalási folyamatot reprezentáló osztály
 */
public class ConquerTask extends WarTask {

    /**
     * Konstruktor
     * @param l folyamat ideje
     * @param city folyamathoz tartozó város
     * @param army foglalásban résztvevő hadsereg
     * @param target foglalási cél város
     */
    public ConquerTask(Long l, City city, Army army, City target) {
        super(l, city, army, target);
    }

    /**
     * Lefutáskor megtámadja a cél város, majd győzelem esetén el is foglalja.
     */
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

    /**
     * {@link Task#toString()}
     */
    @Override
    public String toString() {
        return "Város elfoglalási kísérlet: " + target;
    }
}
