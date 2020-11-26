package game;

import buildings.Building;
import enums.BuildingType;
import enums.GroundUnitType;
import guis.IslandChooser;
import guis.IslandManagerGUI;
import guis.LoginScreen;
import guis.ResourceProducerGUI;
import units.Army;
import units.GroundUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){

        Army x = new Army();
        x.add(new GroundUnit(GroundUnitType.ARCHER));
        x.add(new GroundUnit(GroundUnitType.ARCHER));

        Army y = new Army();
        y.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        y.add(new GroundUnit(GroundUnitType.SWORDSMAN));

        Army győztes = x.battle(y);

        System.out.println("életben maradó katonák: " + győztes.getGroundArmy());

        Game game = loadGame();
        //Game game = new Game();

        /*Island island = new Island("Szigetke",1,1.0,0.9,new HashMap<>());
        City city = new City(island,"városke");
        island.getCities().put(new Location(100,100),city);
        ResourceProducerGUI gui = new ResourceProducerGUI(city,city.getBuilding(BuildingType.TIMBER_CAMP));
        gui.setVisible(true);*/

        LoginScreen loginScreen = new LoginScreen(game);
        loginScreen.setVisible(true);

        //IslandManagerGUI gui = new IslandManagerGUI(game,game.getIslands().get(0).getCities());
    }

    private static Game loadGame(){
        try{
            FileInputStream f = new FileInputStream("data" + File.separator + "game.ser");
            ObjectInputStream in = new ObjectInputStream(f);
            Game game = (Game) in.readObject();
            in.close();
            return game;
        } catch (Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
}
