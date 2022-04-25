package units;

import enums.GroundUnitType;
import exceptions.HealDeadUnitException;

import java.io.Serializable;

/**
 * Szárazföldi egységeket reprezentáló osztály
 */
public class GroundUnit extends Unit implements Ground {
    private final GroundUnitType type;
    private int bluntDefence,sharpDefence,distanceDefence;

    /**
     * Konstruktor
     * @param type egység típusa
     */
    public GroundUnit(GroundUnitType type){
        this.type = type;

        //beállítja az alapérttelmezett védekező értékeket a maximumra
        this.bluntDefence = this.type.getMaxBluntDefence();
        this.sharpDefence = this.type.getMaxSharpDefence();
        this.distanceDefence = this.type.getMaxDistanceDefence();
    }

    /**
     * {@link Ground#attack(GroundUnit)}
     * @param other megtámadandó szárazföldi egység
     */
    @Override
    public void attack(GroundUnit other) {
        damage(other);
        if(other.isAlive()){
            other.damage(this);
        }
    }

    /**
     * {@link Ground#damage(GroundUnit)}
     * @param other megsebzendő szárazföldi egység
     */
    @Override
    public void damage(GroundUnit other) {
        switch (type.getWeaponType()){
            case BLUNT:
                other.setBluntDefence(other.getBluntDefence() - type.getAttack());
                break;
            case SHARP:
                other.setSharpDefence(other.getSharpDefence() - type.getAttack());
                break;
            case DISTANCE:
                other.setDistanceDefence(other.getDistanceDefence() - type.getAttack());
                break;
        }
    }

    /**
     * Feltölti az egység minden élet pontját a maximumra, ha él
     * @throws HealDeadUnitException kivétel dobódik, ha az egység halott
     */
    @Override
    public void heal() throws HealDeadUnitException {
        if(bluntDefence <= 0 || sharpDefence <= 0 || distanceDefence <= 0){
            throw new HealDeadUnitException();
        }
        this.bluntDefence = this.type.getMaxBluntDefence();
        this.sharpDefence = this.type.getMaxSharpDefence();
        this.distanceDefence = this.type.getMaxDistanceDefence();
    }

    /**
     * Vissza adja, hogy él e még az egység
     * Ha minden élet pontja legalább 1 akkor él
     * @return él-e az egység
     */
    @Override
    public boolean isAlive() {
        return bluntDefence > 0 && sharpDefence > 0 && distanceDefence > 0;
    }

    public int getMaxSpeed() {
        return type.getMaxSpeed();
    }

    public int getBluntDefence() {
        return bluntDefence;
    }

    public void setBluntDefence(int bluntDefence) {
        this.bluntDefence = bluntDefence;
    }

    public int getSharpDefence() {
        return sharpDefence;
    }

    public void setSharpDefence(int sharpDefence) {
        this.sharpDefence = sharpDefence;
    }

    public int getDistanceDefence() {
        return distanceDefence;
    }

    public void setDistanceDefence(int distanceDefence) {
        this.distanceDefence = distanceDefence;
    }

    public GroundUnitType getType() {
        return type;
    }

}
