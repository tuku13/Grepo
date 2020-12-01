package game;

import guis.LoginFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {
    public static final boolean DEBUG = true;
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
