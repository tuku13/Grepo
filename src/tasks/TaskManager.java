package tasks;

import game.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TaskManager implements Serializable{
    private static TaskManager taskManager;
    private List<Task> tasks;
    private transient List<Task> futureTasks;

    private TaskManager(){
        this.tasks = new ArrayList<>();
        this.futureTasks = new ArrayList<>();
        if(!Main.DEBUG){
            load();
        }
    }

    public static TaskManager getInstance(){
        if(taskManager == null){
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    public final void load(){
        try{
            FileInputStream f = new FileInputStream("data" + File.separator +"tasks.ser");
            ObjectInputStream in = new ObjectInputStream(f);
            tasks = (List<Task>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }
    private final void save(){
        try{
            FileOutputStream f = new FileOutputStream("data" + File.separator +"tasks.ser");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(tasks);
            out.close();

        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addFutureTask(Task t){
        futureTasks.add(t);
    }

    public void add(Task t){
        this.tasks.add(t);
    }

    public void addAll(Collection<Task> collection){
        this.tasks.addAll(collection);
    }

    public void tick(){
        for(Task t : futureTasks){
            tasks.add(t);
        }
        futureTasks.clear();
        for(Task t : tasks){
            try{
                t.tick();
            }
            catch (NullPointerException exc){
                t.setExecuted(true);
            }
        }
        removeExecutedTasks();
        save();
    }

    public void remove(Task t){
        this.tasks.remove(t);
    }

    private void removeExecutedTasks(){
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()){
            if(it.next().executed == true){
                it.remove();
            }
        }
    }

    protected Object readObject(){
        return taskManager;
    }
}
