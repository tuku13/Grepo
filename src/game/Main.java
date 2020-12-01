package game;

import guis.LoginFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Fő osztály, betölti és elindítja a játékot
 */
public class Main {
    public static final boolean DEBUG = true;//TODO DEBUG eltüntetése leadás előtt
    public static void main(String[] args){

        Game game;
        if(DEBUG){
            game = new Game();
        }
        else{
            game = loadGame();
        }

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
            exc.printStackTrace();
            game = new Game();
        }
        return game;
    }
}
