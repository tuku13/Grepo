package game;

import enums.GroundUnitType;
import guis.LoginScreen;
import tasks.TravellingTask;
import units.Army;
import units.GroundUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {
    public static final boolean DEBUG = true;
    public static void main(String[] args){

        Army x = new Army();
        x.add(new GroundUnit(GroundUnitType.ARCHER));
        x.add(new GroundUnit(GroundUnitType.ARCHER));

        Army y = new Army();
        y.add(new GroundUnit(GroundUnitType.SWORDSMAN));
        y.add(new GroundUnit(GroundUnitType.SWORDSMAN));

        Army győztes = x.battle(y);

        System.out.println("életben maradó katonák: " + győztes.getGroundArmy());

        Game game;
        if(DEBUG){
            game = new Game();
        }
        else{
            game = loadGame();
        }

        /*Island island = new Island("Szigetke",1,1.0,0.9,new HashMap<>());
        City city = new City(island,"városke");
        island.getCities().put(new Location(100,100),city);
        ResourceInfoFrame gui = new ResourceInfoFrame(city,city.getBuilding(BuildingType.TIMBER_CAMP));
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
            //todo nem kell talán: game.taskManager.add(new TravellingTask(60L,null,new Army()));
            return game;
        } catch (Exception exc){
            exc.printStackTrace();
        }
        return null;
    }
}
