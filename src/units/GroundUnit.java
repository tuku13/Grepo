package units;

import enums.GroundUnitType;
import exceptions.HealDeadUnitException;

import java.io.Serializable;

public class GroundUnit extends Unit implements Ground {
    private GroundUnitType type;
    private int bluntDefence,sharpDefence,distanceDefence;

    public GroundUnit(GroundUnitType type){
        this.type = type;

        //beállítja az alapérttelmezett védekező értékeket a maximumra
        this.bluntDefence = this.type.getMaxBluntDefence();
        this.sharpDefence = this.type.getMaxSharpDefence();
        this.distanceDefence = this.type.getMaxDistanceDefence();
    }

    //megtámadja a paraméterként kapott ellenfelet, ha túléli visszatámad
    @Override
    public void attack(GroundUnit other) {
        damage(other);
        if(other.isAlive()){
            other.damage(this);
        }
    }

    //megsebzi a paraméterként kapott ellenfelet a fegyvertípúsa szerint
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

    //minden védekező értéket beállít a maximumra
    @Override
    public void heal() throws HealDeadUnitException {
        if(bluntDefence <= 0 || sharpDefence <= 0 || distanceDefence <= 0){
            throw new HealDeadUnitException();
        }
        this.bluntDefence = this.type.getMaxBluntDefence();
        this.sharpDefence = this.type.getMaxSharpDefence();
        this.distanceDefence = this.type.getMaxDistanceDefence();
    }

    //ha minden védekező érték nagyobb, mint 0 akkor él a katona
    @Override
    public boolean isAlive() {
        if(bluntDefence > 0 && sharpDefence > 0 && distanceDefence > 0){
            return true;
        }
        return false;
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

    @Override
    public String toString() {
        return "GroundUnit{" +
                "type=" + type +
                ", attack=" + type.getAttack() +
                ", bluntDefence=" + bluntDefence +
                ", sharpDefence=" + sharpDefence +
                ", distanceDefence=" + distanceDefence +
                '}';
    }
}
