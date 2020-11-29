package tasks;

import enums.GroundUnitType;
import enums.NavalUnitType;
import game.City;
import guis.UnitPanel;
import units.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWarFrame extends JFrame {
    City from,to;
    Army army;

    public StartWarFrame(City from, City to){
        this.from = from;
        this.to = to;
        army = new Army();
        init();
    }

    private void init(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setTitle("Grepo - Hadjárat indítása");
        this.setSize(1024,768);
        this.setResizable(false);
        GridBagConstraints gc = new GridBagConstraints();

        JPanel groundPanel = new UnitPanel(from,true,true);
        groundPanel.setBackground(new Color(255,0,0));
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(groundPanel,gc);


        JPanel navalPanel = new UnitPanel(from,false,true);
        navalPanel.setBackground(new Color(0,255,0));
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(navalPanel,gc);


        JPanel infoPanel = new JPanel();
        //todo infoPanel gombok és kapacitás felirat
        infoPanel.setBackground(new Color(0,0,255));
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 1;
        this.add(infoPanel,gc);

    }

    private class StartWarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            double speed = army.averageSpeed();
            double distance = from.getIsland().getLocation(from).distance(to.getIsland().getLocation(to));

            long time = Math.round(10 * distance / speed);

            if(army.isColonizingArmy()){
                TaskManager.getInstance().add(new ConquerTask(time,from,army,to));
            }
            else{
                TaskManager.getInstance().add(new WarTask(time,from,army,to));
            }
            dispose();
        }
    }
}
