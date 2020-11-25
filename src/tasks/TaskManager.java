package tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TaskManager implements Serializable{
    private static TaskManager taskManager;
    private List<Task> tasks;

    private TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public static TaskManager getInstance(){
        if(taskManager == null){
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    /*public final void load(){
        try{
            FileInputStream f = new FileInputStream("data" + File.separator +"tasks");
            ObjectInputStream in = new ObjectInputStream(f);
            tasks = (List<Task>) in.readObject();
            in.close();
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }
    private final void save(){
        try{
            FileOutputStream f = new FileOutputStream("data" + File.separator +"tasks");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(tasks);
            out.close();

        } catch (Exception exc){
            exc.printStackTrace();
        }
    }*/

    public void add(Task t){
        this.tasks.add(t);
    }

    public void addAll(Collection<Task> collection){
        this.tasks.addAll(collection);
    }

    public void tick(){
        for(Task t : tasks){
            t.tick();
        }
        removeExecutedTasks();
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
}
