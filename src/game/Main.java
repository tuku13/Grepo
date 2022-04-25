package game;

import enums.GroundUnitType;
import enums.NavalUnitType;
import guis.LoginFrame;
import units.Army;
import units.GroundUnit;
import units.NavalUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Fő osztály, betölti és elindítja a játékot
 */
public class Main {
    public static void main(String[] args){
        Game game = loadGame();

        LoginFrame loginFrame = new LoginFrame(game);
        loginFrame.setVisible(true);

    }

    /**
     * Betölti a játékot
     * @return visszadja a betöltött játékot
     */
    private static Game loadGame(){
        Game game;
        try{
            FileInputStream f = new FileInputStream("data" + File.separator + "game.ser");
            ObjectInputStream in = new ObjectInputStream(f);
            game = (Game) in.readObject();
            in.close();
        } catch (Exception exc){
            //Szerializálási hiba esetén új játék létrehozása néhány teszt adattal
            game = new Game();
            Player p = new Player("admin","admin");
            game.getPlayers().add(p);
            HashMap<Location,City> woodCities = new HashMap<>();
            Island woodIsland = new Island("Wood Island",1.0,0.9,1.1,woodCities);
            City c = new City(woodIsland,"Admin városa");
            Army a = new Army();
            a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
            a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
            a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
            a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
            a.add(new GroundUnit(GroundUnitType.SWORDSMAN));
            a.add(new GroundUnit(GroundUnitType.ARCHER));
            a.add(new GroundUnit(GroundUnitType.HOPLITE));
            a.add(new NavalUnit(NavalUnitType.TRANSPORT_BOAT));
            a.add(new NavalUnit(NavalUnitType.COLONY_SHIP));
            c.getArmy().add(a);
            c.setPlayer(p);
            City c2 = new City(woodIsland, "Admin másik városa");
            woodCities.put(new Location(-180,140),c);
            c2.setPlayer(p);
            woodCities.put(new Location(-160,75),c2);
            woodCities.put(new Location(20,50),new City(woodIsland, "Wood3"));
            woodCities.put(new Location(100,50),new City(woodIsland, "Wood4"));
            woodCities.put(new Location(-10,240),new City(woodIsland, "Wood5"));
            game.getIslands().add(woodIsland);

            HashMap<Location,City> stoneCities = new HashMap<>();
            Island stoneIsland = new Island("Stone Island",1.1,1.0,0.9,stoneCities);
            stoneCities.put(new Location(-90,-60),new City(stoneIsland, "Stone1"));
            stoneCities.put(new Location(-230,-10),new City(stoneIsland, "Stone2"));
            stoneCities.put(new Location(-400,-65),new City(stoneIsland, "Stone3"));
            stoneCities.put(new Location(-370,-260),new City(stoneIsland, "Stone4"));
            stoneCities.put(new Location(-190,-120),new City(stoneIsland, "Stone5"));
            game.getIslands().add(stoneIsland);

            HashMap<Location,City> silverCities = new HashMap<>();
            Island silverIsland = new Island("Silver Island",0.9,1.1,1.0,silverCities);
            silverCities.put(new Location(210,-40),new City(silverIsland, "Silver1"));
            silverCities.put(new Location(230,-10),new City(silverIsland, "Silver2"));
            silverCities.put(new Location(400,0),new City(silverIsland, "Silver3"));
            silverCities.put(new Location(320,-180),new City(silverIsland, "Silver4"));
            silverCities.put(new Location(240,-140),new City(silverIsland, "Silver5"));
            game.getIslands().add(silverIsland);
        }
        return game;
    }
}
