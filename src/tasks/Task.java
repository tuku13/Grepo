package tasks;

import game.City;

import java.io.Serializable;

public abstract class Task implements Serializable {
    protected long time;
    protected City city;
    protected boolean active,executed;

    public Task(Long l,City city){
        time = l;
        this.city = city;
        active = true;
        executed = false;
    }

    protected abstract void execute();

    public final void tick() {
        if(active && !executed){
            --time;
            if(time <= 0){
                execute();
                active = false;
                executed = true;
            }
        }
    }

    public abstract String toString();

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setExecuted(boolean executed) { this.executed = executed; }

    public boolean isExecuted() {
        return executed;
    }

    public City getCity() {
        return city;
    }
}
