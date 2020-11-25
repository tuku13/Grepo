package units;

import enums.GroundUnitType;
import exceptions.HealDeadUnitException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroundUnitTest {

    GroundUnit swordsman;

    @Before
    public void setUp(){
        swordsman = new GroundUnit(GroundUnitType.SWORDSMAN);
    }

    //TODO attack() függvények tesztelése

    @Test
    public void testDamageATarget() {
        //kardforgató megtámadja a célpontot
        GroundUnit target = new GroundUnit(GroundUnitType.SWORDSMAN);
        swordsman.attack(target);

        //várt eremdmény: nem lesz minden védelmi érték a maximumon mert mergsérül
        Assert.assertFalse(target.getBluntDefence() == GroundUnitType.SWORDSMAN.getMaxBluntDefence() && target.getSharpDefence() == GroundUnitType.SWORDSMAN.getMaxSharpDefence() && target.getDistanceDefence() == GroundUnitType.SWORDSMAN.getMaxDistanceDefence());
    }

    @Test
    public void testHealSingleValue() throws HealDeadUnitException {
        //távolsági védelem érték csökkentése 2-vel
        swordsman.setDistanceDefence(swordsman.getDistanceDefence()-2);

        //gyógyítás
        swordsman.heal();

        //eredmény
        Assert.assertEquals(GroundUnitType.SWORDSMAN.getMaxDistanceDefence(), swordsman.getDistanceDefence());
    }

    @Test
    public void testHealMultipleValue() throws HealDeadUnitException {
        //minden védelmi érték csökkentése
        swordsman.setBluntDefence(swordsman.getBluntDefence()-3);
        swordsman.setSharpDefence(swordsman.getSharpDefence()-1);
        swordsman.setDistanceDefence(swordsman.getDistanceDefence()-2);

        //egység felgyógyítása
        swordsman.heal();

        //eredmény
        Assert.assertTrue(swordsman.getBluntDefence() == GroundUnitType.SWORDSMAN.getMaxBluntDefence() && swordsman.getSharpDefence() == GroundUnitType.SWORDSMAN.getMaxSharpDefence() && swordsman.getDistanceDefence() == GroundUnitType.SWORDSMAN.getMaxDistanceDefence());
    }

    @Test(expected = HealDeadUnitException.class)
    public void testHealDeadGroundUnit() throws Exception{
        //kardforgató megölése
        swordsman.setBluntDefence(-1);

        //halott kardforgató gyógyítása
        swordsman.heal();
    }

    @Test
    public void testIsAliveAliveUnit() {
        Assert.assertTrue(swordsman.isAlive());
    }

    @Test
    public void testIsAliveDeadUnit(){
        //egyik védelmi pont csökkentése 0 alá
        swordsman.setSharpDefence(-1);

        Assert.assertFalse(swordsman.isAlive());
    }
}