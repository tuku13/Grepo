package units;

import exceptions.HealDeadUnitException;

import java.io.Serializable;

/**
 * Egységek ősosztálya
 */
public abstract class Unit implements Serializable {

    /**
     * Visszadja, hogy él-e a még az egység
     * @return
     */
    public abstract boolean isAlive();

    /**
     * Felgógyítja az egységet
     * @throws HealDeadUnitException kivétel dobódik ha, valamelyik életpont 0 vagy negatív
     */
    public abstract void heal() throws HealDeadUnitException;

}
