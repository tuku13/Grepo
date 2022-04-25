package units;

import enums.GroundUnitType;
import enums.NavalUnitType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Hadsereget reprezentáló osztály
 */
public class Army implements Serializable {
    private final List<GroundUnit> groundArmy;
    private final List<NavalUnit> navalArmy;

    /**
     * Konstruktor
     */
    public Army(){
        groundArmy = new ArrayList<>();
        navalArmy = new ArrayList<>();
    }

    /**
     * Seregek összecsapását vezérlő függvény
     * @param enemy megtámadott sereg
     * @return győztes sereg, döntetlen esetén a védekező egységek
     */
    public Army battle(Army enemy){
        if(groundArmy.size() == 0){
            return enemy;
        }
        if(enemy.groundArmy.size() == 0){
            return this;
        }
        GroundUnit offensiveGroundUnit = this.getGroundUnit(0);
        GroundUnit defensiveGroundUnit = enemy.getGroundUnit(0);

        while (this.hasAliveGroundUnit() && enemy.hasAliveGroundUnit() && offensiveGroundUnit != null && defensiveGroundUnit != null){
            offensiveGroundUnit.attack(defensiveGroundUnit);
            offensiveGroundUnit = getNextGroundUnit(offensiveGroundUnit);
            defensiveGroundUnit = enemy.getNextGroundUnit(defensiveGroundUnit);
        }

        //töröl minden, csatában legyőzött egységet
        enemy.removeDeadUnits();
        this.removeDeadUnits();

        //ha van a támadó csapatnak élő katonája, akkor nyert különben a védekező nyer.
        return this.hasAliveGroundUnit() ? this : enemy;
    }

    /**
     * Visszadja a paraméterben megadott egység után következő élő egységet.
     * Ciklikus működésű tehát az utolsó katona után az első következik.
     * @param gu a jelenlegi egység
     * @return következő élő egység, ha nincs null
     */
    private GroundUnit getNextGroundUnit(GroundUnit gu){
        if(this.hasAliveGroundUnit()){
            for(int i = groundArmy.indexOf(gu)+1; i <= groundArmy.indexOf(gu)+groundArmy.size(); i++){
                if(getGroundUnit(i).isAlive()){
                    return getGroundUnit(i);
                }
            }
        }
        return null;
    }

    /**
     * Vissza adja a paraméterben megadott indexű mod n-edik szárazföldi egységet
     * @param id sorszám
     * @return sorszám mod n-edik szárazföldi egység
     */
    private GroundUnit getGroundUnit(int id){
        return groundArmy.get(id % groundArmy.size());
    }

    /**
     * Vissza adja a paraméterben megadott indexű mod n-edik vízi egységet
     * @param id sorszám
     * @return sorszám mod n-edik vízi egység
     */
    private NavalUnit getNavalUnit(int id){
        return navalArmy.get(id % navalArmy.size());
    }

    /**
     * Hozzáadja a sereghez a paraméterben kapott vízi egységet
     * @param g hozzá adandó egység
     */
    public void add(GroundUnit g){
        groundArmy.add(g);
    }

    /**
     * Hozzáadja a sereghez a paraméterben kapott vízi egységet
     * @param n hozzá adandó egység
     */
    public void add(NavalUnit n) {
        navalArmy.add(n);
    }

    /**
     * Vissza adja, hogy van e még élő szárazföldi egység
     * @return van-e élő szárazföldi egység
     */
    public boolean hasAliveGroundUnit(){
        for(GroundUnit u : groundArmy){
            if(u.isAlive()){
                return true;
            }
        }
        return false;
    }

    /**
     * Kitöröl minden halott egységet a seregből
     */
    private void removeDeadUnits(){
        List<GroundUnit> toRemoveGround = new ArrayList<>();
        for (GroundUnit gu : groundArmy){
            if(!gu.isAlive()){
                toRemoveGround.add(gu);
            }
        }
        groundArmy.removeAll(toRemoveGround);

        List<NavalUnit> toRemoveNaval = new ArrayList<>();
        for (NavalUnit nu : navalArmy){
            if(!nu.isAlive()){
                toRemoveNaval.add(nu);
            }
        }
        navalArmy.removeAll(toRemoveNaval);
    }

    /**
     * Hozzáadja a paraméterben megadott sereget, önmagához
     * @param a
     */
    public void add(Army a){
        this.groundArmy.addAll(a.groundArmy);
        this.navalArmy.addAll(a.navalArmy);
    }

    /**
     * Kiszámítja a szárazföldi katonák átlagsebességét
     * @return  szárazföldi katona átlag sebesség
     */
    public double averageSpeed(){
        if(groundArmy.size() == 0){
            return 0;
        }
        double d = 0;
        for(GroundUnit gu : groundArmy){
            d += gu.getMaxSpeed();
        }
        return (d / groundArmy.size());
    }

    /**
     * Kitöröl egy paraméterben megadott típusú szárazföldi egységet
     * @param type
     * @return
     */
    public GroundUnit remove(GroundUnitType type){
        Iterator<GroundUnit> it = this.groundArmy.iterator();
        while (it.hasNext()){
            GroundUnit groundUnit = it.next();
            if(groundUnit.getType() == type){
                it.remove();
                return groundUnit;
            }
        }
        return null;
    }

    /**
     * Kitöröl egy paraméterben megadott típusú vízi egységet
     * @param type
     * @return
     */
    public NavalUnit remove(NavalUnitType type){
        Iterator<NavalUnit> it = this.navalArmy.iterator();
        while (it.hasNext()){
            NavalUnit navalUnit = it.next();
            if(navalUnit.getType() == type){
                it.remove();
                return navalUnit;
            }
        }
        return null;
    }

    /**
     * Hozzáadja a sereghez a paraméterben megadott seregből, a megadott szárazföldi egységeket
     * @param from melyik egységből veszi át
     * @param groundTypes melyik típusból mennyit vesz át
     */
    public void moveGroundUnits(Army from, HashMap<GroundUnitType, Integer> groundTypes){
        for(GroundUnitType type : groundTypes.keySet()){
            for (int i = 0; i <= groundTypes.get(type) - 1;i++){
                GroundUnit gu = from.remove(type);
                if(gu != null){
                    this.add(gu);
                }
            }
        }
    }

    /**
     * Hozzáadja a sereghez a paraméterben megadott seregből, a megadott vízi egységeket
     * @param from melyik egységből veszi át
     * @param navalTypes melyik típusból mennyit vesz át
     */
    public void moveNavalUnits(Army from, HashMap<NavalUnitType, Integer> navalTypes){
        for(NavalUnitType type : navalTypes.keySet()){
            for (int i = 0; i <= navalTypes.get(type) - 1;i++){
                NavalUnit nu = from.remove(type);
                if(nu != null){
                    this.add(nu);
                }
            }
        }
    }

    /**
     * Megszámolja a paraméterben megadott egységből mennyi van
     * @param groundUnitType típus
     * @return megadott egységek száma
     */
    public int count(GroundUnitType groundUnitType){
        int c = 0;
        for(GroundUnit gu : groundArmy){
            if(gu.getType() == groundUnitType){
                c += 1;
            }
        }
        return c;
    }

    /**
     * Megszámolja a paraméterben megadott egységből mennyi van
     * @param navalUnitType típus
     * @return megadott egységek száma
     */
    public int count(NavalUnitType navalUnitType){
        int c = 0;
        for(NavalUnit nu : navalArmy){
            if(nu.getType() == navalUnitType){
                c += 1;
            }
        }
        return c;
    }

    /**
     * Visszadja a hajók össz. szárazföldi egység szállítási kapacitását
     * @return szállítható  szárazföldi egységek max. száma
     */
    public int maxCapacity(){
        int c = 0;
        for(NavalUnit nu : navalArmy){
            c += nu.getType().getCapacity();
        }
        return c;
    }

    /**
     * Visszadja, hogy van-e a seregben gyarmatosító hajó
     * @return tud-e várost foglalni az egység
     */
    public boolean isColonizingArmy() {
        for(NavalUnit navalUnit : navalArmy){
            if(navalUnit.getType() == NavalUnitType.COLONY_SHIP){
                return true;
            }
        }
        return false;
    }

    public int usedCapacity(){
        return groundArmy.size();
    }

    public List<GroundUnit> getGroundArmy() {
        return groundArmy;
    }

    public List<NavalUnit> getNavalArmy() {
        return navalArmy;
    }
}
