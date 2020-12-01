package units;

import enums.NavalUnitType;
import exceptions.HealDeadUnitException;

/**
 * Vízi egységeket reprezentáló osztály
 */
public class NavalUnit extends Unit implements Naval{
    private NavalUnitType type;
    private int defence;

    /**
     * Konstruktor
     * @param type egység típusa
     */
    public NavalUnit(NavalUnitType type){
        this.type = type;
        this.defence = this.type.getMaxDefence();
    }

    /**
     * {@link Naval#attack(NavalUnit)}
     * @param other megtámadandó vízi egység
     */
    @Override
    public void attack(NavalUnit other) {
        damage(other);
        if(isAlive()){
            other.damage(this);
        }
    }

    /**
     * {@link Naval#damage(NavalUnit)}
     * @param other megsebzendő vízi egység
     */
    @Override
    public void damage(NavalUnit other) {
        other.setDefence(other.getDefence() - type.getAttack());
    }

    /**
     * Vissza tölti az egység, életét a maximumra
     * @throws HealDeadUnitException kivétel dobódik, ha az egység élete 0 vagy negatív
     */
    @Override
    public void heal() throws HealDeadUnitException {
        if(this.defence <= 0){
            throw new HealDeadUnitException();
        }
        this.defence = this.type.getMaxDefence();
    }

    /**
     * Vissza adja, hogy él-e még az egység
     * @return él-e még
     */
    @Override
    public boolean isAlive() {
        return (defence > 0);
    }

    public NavalUnitType getType() {
        return type;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
