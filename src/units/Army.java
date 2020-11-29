package units;

import enums.GroundUnitType;
import enums.NavalUnitType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Army implements Serializable {
    private List<GroundUnit> groundArmy;
    private List<NavalUnit> navalArmy;
    private boolean colonizingArmy = false;


    public Army battle(Army enemy){
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

    public Army(){
        groundArmy = new ArrayList<>();
        navalArmy = new ArrayList<>();
    }

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

    private GroundUnit getGroundUnit(int id){
        return groundArmy.get(id % groundArmy.size());
    }

    private NavalUnit getNavalUnit(int id){
        return navalArmy.get(id % navalArmy.size());
    }

    public void add(GroundUnit u){
        groundArmy.add(u);
    }

    public void add(NavalUnit u){
        if(u.getType() == NavalUnitType.COLONY_SHIP){
            colonizingArmy = true;
        }
        navalArmy.add(u);
    }

    public boolean hasAliveGroundUnit(){
        for(GroundUnit u : groundArmy){
            if(u.isAlive()){
                return true;
            }
        }
        return false;
    }

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

    public void add(Army a){
        this.groundArmy.addAll(a.groundArmy);
        this.navalArmy.addAll(a.navalArmy);
    }

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

    public int usedCapacity(){
        return groundArmy.size();
    }

    public int count(GroundUnitType groundUnitType){
        int c = 0;
        for(GroundUnit gu : groundArmy){
            if(gu.getType() == groundUnitType){
                c += 1;
            }
        }
        return c;
    }

    public int count(NavalUnitType navalUnitType){
        int c = 0;
        for(NavalUnit nu : navalArmy){
            if(nu.getType() == navalUnitType){
                c += 1;
            }
        }
        return c;
    }

    public int maxCapacity(){
        int c = 0;
        for(NavalUnit nu : navalArmy){
            c += nu.getType().getCapacity();
        }
        return c;
    }

    public boolean isColonizingArmy() {
        return colonizingArmy;
    }

    public List<GroundUnit> getGroundArmy() {
        return groundArmy;
    }

    public List<NavalUnit> getNavalArmy() {
        return navalArmy;
    }
}
