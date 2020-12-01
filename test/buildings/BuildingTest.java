package buildings;

import enums.BuildingType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class BuildingTest {

    BuildingType buildingType;

    public BuildingTest(BuildingType buildingType){
        this.buildingType = buildingType;
    }

    @Test
    public void upgradeTest() {
        Building building = new Building(buildingType,buildingType.getMaxLevel()-1, null);
        building.upgrade();
        Assert.assertEquals(building.getLevel(),building.getBuildingType().getMaxLevel());
    }

    @Test
    public void UpgradeAtMaxLevelTest() {
        Building building = new Building(buildingType,buildingType.getMaxLevel(), null);
        building.upgrade();
        Assert.assertEquals(building.getLevel(),building.getBuildingType().getMaxLevel());
    }


    @Parameterized.Parameters
    public static List<Object> parameters() {
        List<Object> list = new ArrayList<>();
        Collections.addAll(list, Arrays.stream(BuildingType.values()).toArray());
        return list;
    }

}