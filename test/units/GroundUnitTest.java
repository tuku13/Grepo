package units;

import enums.GroundUnitType;
import enums.WeaponType;
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


    @Test
    public void damageATargetTest() {
        //kardforgató megtámadja a célpontot
        GroundUnit target = new GroundUnit(GroundUnitType.SWORDSMAN);
        swordsman.attack(target);

        //várt eremdmény: nem lesz minden védelmi érték a maximumon mert mergsérül
        Assert.assertFalse(target.getBluntDefence() == GroundUnitType.SWORDSMAN.getMaxBluntDefence() && target.getSharpDefence() == GroundUnitType.SWORDSMAN.getMaxSharpDefence() && target.getDistanceDefence() == GroundUnitType.SWORDSMAN.getMaxDistanceDefence());
    }

    @Test
    public void healSingleValueTest() throws HealDeadUnitException {
        //távolsági védelem érték csökkentése 2-vel
        swordsman.setDistanceDefence(swordsman.getDistanceDefence()-2);

        //gyógyítás
        swordsman.heal();

        //eredmény
        Assert.assertEquals(GroundUnitType.SWORDSMAN.getMaxDistanceDefence(), swordsman.getDistanceDefence());
    }

    @Test
    public void healMultipleValueTest() throws HealDeadUnitException {
        //minden védelmi érték csökkentése
        swordsman.setBluntDefence(swordsman.getBluntDefence()-3);
        swordsman.setSharpDefence(swordsman.getSharpDefence()-1);
        swordsman.setDistanceDefence(swordsman.getDistanceDefence()-2);

        //egység felgyógyítása
        swordsman.heal();

        //eredmény
        Assert.assertEquals(GroundUnitType.SWORDSMAN.getMaxBluntDefence(),swordsman.getBluntDefence());
        Assert.assertEquals(GroundUnitType.SWORDSMAN.getMaxSharpDefence(),swordsman.getSharpDefence());
        Assert.assertEquals(GroundUnitType.SWORDSMAN.getMaxDistanceDefence(),swordsman.getDistanceDefence());
    }

    @Test(expected = HealDeadUnitException.class)
    public void healDeadGroundUnitTes() throws Exception{
        //kardforgató megölése
        swordsman.setBluntDefence(-1);

        //halott kardforgató gyógyítása
        swordsman.heal();
    }

    @Test
    public void isAliveAliveUnitTest() {
        Assert.assertTrue(swordsman.isAlive());
    }

    @Test
    public void swordsmanAttacksAndEnemyAttacksBackTest(){
        GroundUnit hoplite = new GroundUnit(GroundUnitType.HOPLITE);

        swordsman.attack(hoplite);

        Assert.assertEquals(hoplite.getBluntDefence(),hoplite.getType().getMaxBluntDefence() - swordsman.getType().getAttack());
        Assert.assertEquals(swordsman.getSharpDefence(),swordsman.getType().getMaxSharpDefence() - hoplite.getType().getAttack());
    }

    @Test
    public void groundUnitKillOtherTest(){
        GroundUnit hoplite = new GroundUnit(GroundUnitType.HOPLITE);

        hoplite.attack(swordsman);

        Assert.assertEquals(WeaponType.SHARP,hoplite.getType().getWeaponType());
        Assert.assertEquals(swordsman.getType().getMaxSharpDefence() - hoplite.getType().getAttack(),swordsman.getSharpDefence());
        Assert.assertFalse(swordsman.isAlive());
        Assert.assertEquals(hoplite.getType().getMaxBluntDefence(),hoplite.getBluntDefence());
    }

    @Test
    public void isAliveDeadUnitTest(){
        //egyik védelmi pont csökkentése 0 alá
        swordsman.setSharpDefence(-1);

        Assert.assertFalse(swordsman.isAlive());
    }
}