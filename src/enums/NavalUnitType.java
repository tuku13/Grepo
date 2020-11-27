package enums;

public enum NavalUnitType {
    TRANSPORT_BOAT(0,1,26,8),
    FAST_TRANSPORT_SHIP(1,0,10,15);
    /*BIREME(24,160,0,15),
    LIGHT_SHIP(200,60,0,13),
    FIRE_SHIP(20,1,0,5),
    TRIREME(250,250,0,15),
    COLONY_SHIP(0,1,0,3);*/

    private final int attack,maxDefence,capacity,maxSpeed;

    NavalUnitType(int attack,int maxDefence,int capacity, int maxSpeed){
        this.attack = attack;
        this.maxDefence = maxDefence;
        this.capacity = capacity;
        this.maxSpeed = maxSpeed;
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
}
