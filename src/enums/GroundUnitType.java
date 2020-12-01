package enums;

import game.ResourceStack;

/**
 * Szárazföldi egységeket és ahhoz tartozó konstansokat felsoroló enum
 */
public enum GroundUnitType {
    DIVINE_ENVOY(WeaponType.BLUNT,40,40,40,40,16,1,new ResourceStack(0,0,0,12),4,"Istenek küldötte","images/divine_envoy.png"),
    SWORDSMAN(WeaponType.BLUNT,5,14,8,30,8,2,new ResourceStack(95,0,85),3,"Kardforgató","images/swordsman.png"),
    SLINGER(WeaponType.DISTANCE,23,7,8,2,14,3,new ResourceStack(55,100,40),4,"Parittyás","images/slinger.png"),
    ARCHER(WeaponType.DISTANCE,8,7,25,13,12,4,new ResourceStack(120,0,75),6, "Íjász","images/archer.png"),
    HOPLITE(WeaponType.SHARP,16,18,12,7,6,5,new ResourceStack(0,75,150),5,"Hoplita","images/hoplite.png"),
    HORSEMAN(WeaponType.BLUNT,60,18,1,24,22,6,new ResourceStack(240,120,360),12,"Lovas","images/horseman.png"),
    CHARIOT(WeaponType.SHARP,56,76,16,56,18,7,new ResourceStack(200,440,320),17,"Harci szekér","images/chariot.png"),
    CATAPULT(WeaponType.DISTANCE,100,30,30,30,2,8,new ResourceStack(700,700,700),26, "Katapult","images/catapult.png");

    private final WeaponType weaponType;
    private final int attack, maxBluntDefence, maxSharpDefence, maxDistanceDefence, maxSpeed, requiredLevel;
    private final ResourceStack cost;
    private final long trainingTime;
    private final String name, imageName;

    /**
     * Konstruktor
     * @param weaponType fegyvertípus
     * @param attack támadás pont
     * @param maxBluntDefence ütő fegyver elleni élet pont
     * @param maxSharpDefence szúró fegyver elleni élet pont
     * @param maxDistanceDefence távolsági fegyver elleni élet pont
     * @param maxSpeed egység utazási sebessége
     * @param requiredLevel kiképzéshez szükséges minimális barakk szint
     * @param cost kiképzési költség
     * @param trainingTime képzési idő
     * @param name megjelenített név
     * @param imageName képhez tartozó elérési út
     */
    GroundUnitType(WeaponType weaponType, int attack, int maxBluntDefence, int maxSharpDefence, int maxDistanceDefence, int maxSpeed,int requiredLevel,ResourceStack cost,long trainingTime,String name,String imageName){
        this.weaponType = weaponType;
        this.attack = attack;
        this.maxBluntDefence = maxBluntDefence;
        this.maxSharpDefence = maxSharpDefence;
        this.maxDistanceDefence = maxDistanceDefence;
        this.maxSpeed = maxSpeed;
        this.requiredLevel = requiredLevel;
        this.cost = cost;
        this.trainingTime = trainingTime;
        this.name = name;
        this.imageName = imageName;
    }

    public String getImageName() { return imageName; }

    public String getName() { return name; }

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
