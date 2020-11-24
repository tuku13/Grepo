package enums;

import game.ResourceStack;

public enum GroundUnitType {
    DIVINE_ENVOY(WeaponType.BLUNT,40,40,40,40,16,1,new ResourceStack(0,0,0,12),40),
    SWORDSMAN(WeaponType.BLUNT,5,14,8,30,8,2,new ResourceStack(95,0,85),32),
    SLINGER(WeaponType.DISTANCE,23,7,8,2,14,3,new ResourceStack(55,100,40),36),
    ARCHER(WeaponType.DISTANCE,8,7,25,13,12,4,new ResourceStack(120,0,75),34),
    HOPLITE(WeaponType.SHARP,16,18,12,7,6,5,new ResourceStack(0,75,150),41),
    HORSEMAN(WeaponType.BLUNT,60,18,1,24,22,6,new ResourceStack(240,120,360),128),
    CHARIOT(WeaponType.SHARP,56,76,16,56,18,7,new ResourceStack(200,440,320),171),
    CATAPULT(WeaponType.DISTANCE,100,30,30,30,2,8,new ResourceStack(700,700,700),373);

    private final WeaponType weaponType;
    private final int attack,maxBluntDefence,maxSharpDefence,maxDistanceDefence,maxSpeed, requiredLevel;
    private final ResourceStack cost;
    private final long trainingTime;

    GroundUnitType(WeaponType weaponType, int attack, int maxBluntDefence, int maxSharpDefence, int maxDistanceDefence, int maxSpeed,int requiredLevel,ResourceStack cost,long trainingTime){
        this.weaponType = weaponType;
        this.attack = attack;
        this.maxBluntDefence = maxBluntDefence;
        this.maxSharpDefence = maxSharpDefence;
        this.maxDistanceDefence = maxDistanceDefence;
        this.maxSpeed = maxSpeed;
        this.requiredLevel = requiredLevel;
        this.cost = cost;
        this.trainingTime = trainingTime;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getAttack() {
        return attack;
    }

    public int getMaxBluntDefence() {
        return maxBluntDefence;
    }

    public int getMaxSharpDefence() {
        return maxSharpDefence;
    }

    public int getMaxDistanceDefence() {
        return maxDistanceDefence;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public ResourceStack getCost() {
        return cost;
    }

    public long getTrainingTime() {
        return trainingTime;
    }
}
