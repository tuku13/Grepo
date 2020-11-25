package enums;

import game.ResourceStack;

public enum BuildingType {
    SENATE(15,3,1.78,new ResourceStack(5,2,2),1.5,"Szenátus"),
    TIMBER_CAMP(20,2,1.54,new ResourceStack(3,2,1),1.47,"Fatelep"),
    QUARRY(20,2,1.54,new ResourceStack(1,3,2),1.47,"Kőfejtő"),
    SILVER_MINE(20,2,1.54,new ResourceStack(1,3,2),1.47,"Ezüstbánya"),
    BARRACKS(8,10,2.3,new ResourceStack(70,20,40),1.6,"Kaszárnya"),
    TEMPLE(10,14,1.8,new ResourceStack(25,45,30),1.58,"Templom"),
    HARBOR(5,15,3.2,new ResourceStack(400,200,100),1.46,"Kikötő");

    private final int maxLevel;
    private final long defaultBuildingTime;
    private final double timeMultiplier,costMultiplier;
    private final ResourceStack cost;
    private final String name;

    BuildingType(int maxLevel,long defaultBuildingTime,double timeMultiplier,ResourceStack cost,double costMultiplier,String name){
        this.maxLevel = maxLevel;
        this.defaultBuildingTime = defaultBuildingTime;
        this.timeMultiplier = timeMultiplier;
        this.cost = cost;
        this.costMultiplier = costMultiplier;
        this.name = name;
    }

    public long getBuildingTime(int level){
        return Math.round(defaultBuildingTime * Math.pow(timeMultiplier,level));
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public long getDefaultBuildingTime() {
        return defaultBuildingTime;
    }

    public double getTimeMultiplier() {
        return timeMultiplier;
    }

    public double getCostMultiplier() {
        return costMultiplier;
    }

    public ResourceStack getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
