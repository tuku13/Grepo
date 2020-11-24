package units;

import exceptions.HealDeadUnitException;

import java.io.Serializable;

public abstract class Unit implements Serializable {

    public abstract boolean isAlive();
    public abstract void heal() throws HealDeadUnitException;

}
