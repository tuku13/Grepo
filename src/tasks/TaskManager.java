package tasks;

import game.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Folyamatokat kezelő Singleton osztály
 */
public final class TaskManager implements Serializable{
    private static TaskManager taskManager;
    private List<Task> tasks;
    private transient List<Task> futureTasks;

    /**
     * Privát konstruktor
     * Létrehozza a folyamatok listáját és betölti a meglévőket
     */
    private TaskManager(){
        this.tasks = new ArrayList<>();
        this.futureTasks = new ArrayList<>();

        if(!Main.DEBUG){
            load();
        }
    }

    /**
     * Visszaadja az egyetlen példányát, ha nem létezik létrehozza
     * @return Egyetlen folyamat kezelő példány
     */
    public static TaskManager getInstance(){
        if(taskManager == null){
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    /**
     * FBetölti a folyamatokat fájlból.
     */
    public void load(){
        try{
            FileInputStream f = new FileInputStream("data" + File.separator +"tasks.ser");
            ObjectInputStream in = new ObjectInputStream(f);
            tasks = (List<Task>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    /**
     * Elmenti fájlba a folyamatokat
     */
    private void save(){
        try{
            FileOutputStream f = new FileOutputStream("data" + File.separator +"tasks.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(tasks);
            out.close();

        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    /**
     * Hozzáadja listához a paraméterben megadott folyamatot.
     * Concurrent modification exception biztos.
     * @param t hozzáadni kívánt folyamat
     */
    public void addFutureTask(Task t){
        futureTasks.add(t);
    }

    /**
     * Hozzáadja listához a paraméterben megadott folyamatot
     * @param t hozzáadni kívánt folyamat
     */
    public void add(Task t){
        this.tasks.add(t);
    }

    /**
     * Hozzáad minden paraméterben megadott folyamatot
     * @param collection hozzáadandó folyamatok
     */
    public void addAll(Collection<Task> collection){
        this.tasks.addAll(collection);
    }

    /**
     * Hozzáadja az új folyamatokat, majd minden folyamaton meghívja a tick() függvényt.
     * Töröl minden lefutott folyamatot és elmenti a futó folyamatok adatait fájlba.
     * Ha egy folyamat során kivétel keletkezik, azonnal megsemmisíti.
     */
    public void tick(){
        tasks.addAll(futureTasks);
        futureTasks.clear();
        for(Task t : tasks){
            try{
                t.tick();
            }
            catch (Exception exc){
                t.setExecuted(true);
            }
        }
        removeExecutedTasks();
        save();
    }

    /**
     * Törli a paraméterben megadott folyamatot
     * @param t törölni kívánt folyamat
     */
    public void remove(Task t){
        this.tasks.remove(t);
    }

    /**
     * Töröl, minden lefutott folyamatot
     */
    private void removeExecutedTasks(){
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()){
            if(it.next().executed){
                it.remove();
            }
        }
    }

    /**
     * Vissza adja futó folyamatokat
     * @return futó folyamatok listája
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
