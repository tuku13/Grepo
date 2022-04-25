package game;

import exceptions.HealDeadUnitException;
import exceptions.NotEnoughResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceStackTest {

    ResourceStack r;

    @Before
    public void setUp(){
        r = new ResourceStack(100,100,100,100);
    }

    @Test
    public void addTest() {
        r.add(new ResourceStack(100,100,100,100));
        Assert.assertEquals(200,r.getWood(),0.00001);
        Assert.assertEquals(200,r.getFavour(),0.00001);
        Assert.assertEquals(200,r.getSilver(),0.00001);
        Assert.assertEquals(200,r.getStone(),0.00001);
    }

    @Test
    public void multiplyTest() {
        r.multiply(5.0);
        Assert.assertEquals(500,r.getWood(),0.00001);
        Assert.assertEquals(0,r.getFavour(),0.00001);
        Assert.assertEquals(500,r.getSilver(),0.00001);
        Assert.assertEquals(500,r.getStone(),0.00001);
    }

    @Test(expected = NotEnoughResource.class)
    public void noHasEnoughTest() throws NotEnoughResource {
        ResourceStack r = new ResourceStack(100,100,100);
        r.subtract(new ResourceStack(100,100,100,100));
    }

    @Test
    public void HasEnoughTest() throws NotEnoughResource {
        r.subtract(new ResourceStack(100,100,100,100));
        Assert.assertEquals(0,r.getWood(),0.00001);
        Assert.assertEquals(0,r.getFavour(),0.00001);
        Assert.assertEquals(0,r.getSilver(),0.00001);
        Assert.assertEquals(0,r.getStone(),0.00001);
    }
}