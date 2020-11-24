package game;

import enums.GroundUnitType;
import guis.IslandManagerGUI;
import units.Army;
import units.GroundUnit;

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

        Game game = new Game();

        //LoginScreen loginScreen = new LoginScreen(game);
        //loginScreen.setVisible(true);
        //IslandChooser islandChooser = new IslandChooser(null);
        IslandManagerGUI gui = new IslandManagerGUI(game,game.getIslands().get(0).getCities());
    }
}
