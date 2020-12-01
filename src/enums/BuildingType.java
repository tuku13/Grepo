package enums;

import game.ResourceStack;

public enum BuildingType {
    SENATE(15,3,1.28,new ResourceStack(5,2,2),1.5,"Szenátus","images/senate.png"),
    TIMBER_CAMP(20,2,1.24,new ResourceStack(3,2,1),1.47,"Fatelep","images/timber_camp.png"),
    QUARRY(20,2,1.23,new ResourceStack(1,3,2),1.47,"Kőfejtő","images/quarry.png"),
    SILVER_MINE(20,2,1.23,new ResourceStack(1,3,2),1.47,"Ezüstbánya","images/silver_mine.png"),
    BARRACKS(8,10,1.6,new ResourceStack(70,20,40),1.6,"Kaszárnya","images/barracks.png"),
    TEMPLE(10,14,1.5,new ResourceStack(25,45,30),1.58,"Templom","images/temple.png"),
    HARBOR(5,15,2.1,new ResourceStack(400,200,100),1.46,"Kikötő","images/harbor.png");

    private final int maxLevel;
    private final long defaultBuildingTime;
    private final double timeMultiplier,costMultiplier;
    private final ResourceStack cost;
    private final String name,imageName;

    BuildingType(int maxLevel,long defaultBuildingTime,double timeMultiplier,ResourceStack cost,double costMultiplier,String name,String imageName){
        this.maxLevel = maxLevel;
        this.defaultBuildingTime = defaultBuildingTime;
        this.timeMultiplier = timeMultiplier;
        this.cost = cost;
        this.costMultiplier = costMultiplier;
        this.name = name;
        this.imageName = imageName;
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

    public ResourceStack getCost(int level) {
        ResourceStack cost = new ResourceStack(this.cost.getWood(),this.cost.getStone(),this.cost.getSilver());
        cost.multiply(costMultiplier);
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }
}
