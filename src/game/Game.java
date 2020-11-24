package game;

import com.sun.istack.internal.NotNull;
import tasks.TaskManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game implements Tickable {
    private List<Player> players;
    private List<Island> islands;
    private Player authenticatedPlayer;
    public TaskManager taskManager;

    public Game(){
        players = new ArrayList<>();
        islands = new ArrayList<>();
        taskManager = TaskManager.getInstance();
        load();
    }

    @Override
    public void tick() {
        for (Island i : islands){
            i.tick();
        }
        taskManager.tick();
        save();
    }

    public void load(){
        try{
            FileInputStream f = new FileInputStream("data" + File.separator + "players");
            ObjectInputStream in = new ObjectInputStream(f);
            players = (List<Player>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }

        try{
            FileInputStream f = new FileInputStream("data" + File.separator +"islands");
            ObjectInputStream in = new ObjectInputStream(f);
            islands = (List<Island>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }

    }
    public void save(){
        try{
            FileOutputStream f = new FileOutputStream("data" + File.separator +"islands");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(islands);

            f = new FileOutputStream("data" + File.separator +"players");
            out = new ObjectOutputStream(f);
            out.writeObject(players);
            out.close();

        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public void setAuthenticatedPlayer(Player authenticatedPlayer) {
        this.authenticatedPlayer = authenticatedPlayer;
    }

    public Player getPlayer(String name){
        for(Player p : players){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void registerPlayer(@NotNull String name, @NotNull String pw){
        players.add(new Player(name,pw));
        save();
    }

    public boolean authenticate(Player pl,String pw){
        if(pl != null && pl.password.equals(pw)){
            authenticatedPlayer = pl;
            return true;
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public Player getAuthenticatedPlayer() {
        return authenticatedPlayer;
    }
}
