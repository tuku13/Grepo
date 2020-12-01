package enums;

import game.ResourceStack;

/**
 * Vízi egységeket (jelenleg hajók) és ahhoz tartozó konstansokat felsoroló enum
 */
public enum NavalUnitType {
    TRANSPORT_BOAT(0,1,26,8, 1, new ResourceStack(50,50,50), 30, "Szállítóbárka", "images/transport_boat.png"),
    FAST_TRANSPORT_SHIP(1,0,10,15, 2, new ResourceStack(80,0,40), 60, "Gyors szállítóhajó", "images/fast_transport_ship.png"),
    COLONY_SHIP(0,1,0,3, 3, new ResourceStack(1000,1000,1000), 180, "Gyarmatosító hajó", "images/colony_ship.png");

    private final int attack, maxDefence, capacity, maxSpeed, requiredLevel;
    private final ResourceStack cost;
    private final long trainingTime;
    private final String name, imageName;

    /**
     * Konstruktor
     * @param attack támadási pont
     * @param maxDefence védelmi pont
     * @param capacity ennyi egységet képes vízen szállítani
     * @param maxSpeed max sebesség
     * @param requiredLevel építéshez szükséges kikötő szint
     * @param cost építés költsége
     * @param trainingTime építés ideje
     * @param name megjelenített név
     * @param imageName képhez tartozó elérési út
     */
    NavalUnitType(int attack, int maxDefence, int capacity, int maxSpeed, int requiredLevel, ResourceStack cost, long trainingTime, String name, String imageName){
        this.attack = attack;
        this.maxDefence = maxDefence;
        this.capacity = capacity;
        this.maxSpeed = maxSpeed;
        this.requiredLevel = requiredLevel;
        this.cost = cost;
        this.trainingTime = trainingTime;
        this.name = name;
        this.imageName = imageName;
    }

    public int getAttack() {
        return attack;
    }

    public int getMaxDefence() {
        return maxDefence;
    }

    public int getCapacity() {
        return capacity;
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

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }
}
