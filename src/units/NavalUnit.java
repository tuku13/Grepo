package units;

import enums.NavalUnitType;
import exceptions.HealDeadUnitException;

public class NavalUnit extends Unit implements Naval{
    private NavalUnitType type;
    private int defence;

    public NavalUnit(NavalUnitType type){
        this.type = type;
        this.defence = this.type.getMaxDefence();
    }

    @Override
    public void attack(NavalUnit other) {
        damage(other);
        if(isAlive()){
            other.damage(this);
        }
    }

    @Override
    public void damage(NavalUnit other) {
        other.setDefence(other.getDefence() - type.getAttack());
    }

    @Override
    public void heal() throws HealDeadUnitException {
        if(this.defence <= 0){
            throw new HealDeadUnitException();
        }
        this.defence = this.type.getMaxDefence();
    }

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
