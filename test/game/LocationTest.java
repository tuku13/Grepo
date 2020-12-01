package game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    Location x,y,z;

    @Before
    public void setUp(){
        x = new Location(100,0);
        y = new Location(0,0);
        z = new Location(100,100);
    }

    @Test
    public void distanceXToYTest() {
        Assert.assertEquals(100.0,x.distance(y),0.001);
    }

    @Test
    public void distanceYToXTest() {
        Assert.assertEquals(100.0,y.distance(x),0.001);
    }

    @Test
    public void distanceYToZTest() {
        Assert.assertEquals(Math.sqrt(Math.pow(100,2) + Math.pow(100,2)),y.distance(z),0.001);
    }
}