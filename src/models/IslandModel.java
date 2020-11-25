package models;

import game.City;
import game.Island;
import game.Player;
import javafx.print.PageLayout;
import org.hamcrest.core.Is;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class IslandModel implements ComboBoxModel {
    private City selected = null;
    private List<Island> islands;
    private Player player;
    private List<City> cities;

    public IslandModel(List<Island> islands,Player player){
        this.islands = islands;
        this.player = player;
        initCities();
        if(cities != null && !cities.isEmpty()){
            selected = cities.get(0);
        }
    }

    public void initCities(){
        cities = new ArrayList<>();
        for(Island i : islands){
            for(City c : i.getCities().values()){
                if(c == null) continue;
                if(c.getPlayer() == player){
                    cities.add(c);
                }
            }
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (City) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return cities.size();
    }

    @Override
    public Object getElementAt(int index) {
        return cities.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
